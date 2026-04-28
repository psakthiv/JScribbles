import { useEffect, useRef } from "react";
import * as d3 from "d3";

type TaxonomyNodeType = "root" | "term" | "property";

type TaxonomyNode = {
  name: string;
  type: TaxonomyNodeType;
  children?: TaxonomyNode[];
  _children?: TaxonomyNode[];
  x0?: number;
  y0?: number;
};

const taxonomyData: TaxonomyNode = {
  name: "Passport",
  type: "root",
  children: [
    { name: "First Name", type: "property" },
    { name: "Last Name", type: "property" },
    { name: "Passport Number", type: "property" },
    {
      name: "Address",
      type: "term",
      children: [
        { name: "Door Number", type: "property" },
        { name: "Street", type: "property" },
        { name: "City", type: "property" },
        { name: "Postcode", type: "property" },
        { name: "Country", type: "property" },
      ],
    },
    {
      name: "Personal Details",
      type: "term",
      children: [
        { name: "Date of Birth", type: "property" },
        { name: "Place of Birth", type: "property" },
        { name: "Nationality", type: "property" },
      ],
    },
  ],
};

const nodeColors: Record<TaxonomyNodeType, string> = {
  root: "#6366f1",
  term: "#f97316",
  property: "#16a34a",
};

export default function TaxonomyTree() {
  const svgRef = useRef<SVGSVGElement | null>(null);

  useEffect(() => {
    const width = 1200;
    const height = 700;

    const margin = {
      top: 40,
      right: 220,
      bottom: 40,
      left: 160,
    };

    const svg = d3.select(svgRef.current as SVGSVGElement);
    svg.selectAll("*").remove();

    svg
      .attr("viewBox", `0 0 ${width} ${height}`)
      .style("width", "100%")
      .style("height", "700px")
      .style("background", "#fafafa");

    const container = svg.append("g");

    const g = container
      .append("g")
      .attr("transform", `translate(${margin.left},${margin.top})`);

    const zoom = d3
      .zoom<SVGSVGElement, unknown>()
      .scaleExtent([0.4, 2.5])
      .on("zoom", (event) => {
        container.attr("transform", event.transform.toString());
      });

    svg.call(zoom as any);

    const root = d3.hierarchy<TaxonomyNode>(taxonomyData) as any;

    root.x0 = height / 2;
    root.y0 = 0;

    const treeLayout = d3
      .tree<TaxonomyNode>()
      .size([
        height - margin.top - margin.bottom,
        width - margin.left - margin.right,
      ])
      .separation((a, b) => (a.parent === b.parent ? 1.2 : 1.8));

    update(root);

    function update(source: any) {
      treeLayout(root);

      const nodes = root.descendants();
      const links = root.links();

      nodes.forEach((d: any) => {
        d.y = d.depth * 260;
      });

      const node = g
        .selectAll<SVGGElement, any>("g.node")
        .data(nodes, (d: any) => d.data.name);

      const nodeEnter = node
        .enter()
        .append("g")
        .attr("class", "node")
        .attr("transform", () => `translate(${source.y0},${source.x0})`)
        .style("cursor", "pointer")
        .on("click", (_, d: any) => {
          if (d.children) {
            d.data._children = d.children.map((child: any) => child.data);
            d.children = undefined;
          } else if (d.data._children) {
            d.children = d.data._children.map((child: TaxonomyNode) =>
              d3.hierarchy(child)
            );
            d.data._children = undefined;
          }

          update(d);
        });

      nodeEnter
        .append("circle")
        .attr("r", 1e-6)
        .attr("fill", (d: any) => nodeColors[d.data.type])
        .attr("stroke", "#ffffff")
        .attr("stroke-width", 2);

      nodeEnter
        .append("text")
        .attr("dy", 4)
        .attr("x", 16)
        .attr("text-anchor", "start")
        .style("font-size", "13px")
        .style("fill", "#334155")
        .style("font-weight", (d: any) =>
          d.data.type === "root" ? "700" : "400"
        )
        .text((d: any) => d.data.name);

      nodeEnter
        .append("text")
        .attr("dy", 22)
        .attr("x", 16)
        .attr("text-anchor", "start")
        .style("font-size", "10px")
        .style("fill", "#64748b")
        .text((d: any) => d.data.type.toUpperCase());

      const nodeUpdate = nodeEnter.merge(node as any);

      nodeUpdate
        .transition()
        .duration(400)
        .attr("transform", (d: any) => `translate(${d.y},${d.x})`);

      nodeUpdate
        .select("circle")
        .transition()
        .duration(400)
        .attr("r", (d: any) => {
          if (d.data.type === "root") return 13;
          if (d.data.type === "term") return 10;
          return 7;
        })
        .attr("fill", (d: any) =>
          d.data._children ? "#94a3b8" : nodeColors[d.data.type]
        );

      node.exit()
        .transition()
        .duration(400)
        .attr("transform", () => `translate(${source.y},${source.x})`)
        .remove();

      const link = g
        .selectAll<SVGPathElement, any>("path.link")
        .data(links, (d: any) => d.target.data.name);

      const linkEnter = link
        .enter()
        .insert("path", "g")
        .attr("class", "link")
        .attr("fill", "none")
        .attr("stroke", "#cbd5e1")
        .attr("stroke-width", 1.4)
        .attr("d", () => {
          const o = { x: source.x0, y: source.y0 };
          return diagonal(o, o);
        });

      linkEnter
        .merge(link as any)
        .transition()
        .duration(400)
        .attr("d", (d: any) => diagonal(d.source, d.target));

      link.exit()
        .transition()
        .duration(400)
        .attr("d", () => {
          const o = { x: source.x, y: source.y };
          return diagonal(o, o);
        })
        .remove();

      nodes.forEach((d: any) => {
        d.x0 = d.x;
        d.y0 = d.y;
      });
    }

    function diagonal(source: any, target: any) {
      return `
        M ${source.y} ${source.x}
        C ${(source.y + target.y) / 2} ${source.x},
          ${(source.y + target.y) / 2} ${target.x},
          ${target.y} ${target.x}
      `;
    }
  }, []);

  return (
    <div style={{ width: "100%", height: "700px" }}>
      <h2>Passport Taxonomy</h2>
      <svg ref={svgRef}></svg>
    </div>
  );
}
