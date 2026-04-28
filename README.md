import React, { useEffect, useRef } from "react";
import * as d3 from "d3";

type NodeType = "person" | "client" | "account" | "group";

type GraphNode = {
  id: string;
  label: string;
  type: NodeType;
  depth: number;
  size?: number;
};

type GraphLink = {
  source: string;
  target: string;
  label?: string;
};

const nodes: GraphNode[] = [
  { id: "john", label: "John Doe", type: "person", depth: 0, size: 42 },

  { id: "client1", label: "CLIENT-001", type: "client", depth: 1 },
  { id: "client2", label: "CLIENT-002", type: "client", depth: 1 },
  { id: "client3", label: "CLIENT-003", type: "client", depth: 1 },
  { id: "client4", label: "CLIENT-004", type: "client", depth: 1 },
  { id: "client7", label: "CLIENT-007", type: "client", depth: 1 },

  { id: "acc1", label: "ACC-001", type: "account", depth: 2 },
  { id: "acc2", label: "ACC-002", type: "account", depth: 2 },
  { id: "acc3", label: "ACC-003", type: "account", depth: 2 },
  { id: "acc7", label: "ACC-007", type: "account", depth: 2 },
  { id: "acc8", label: "ACC-008", type: "account", depth: 2 },

  { id: "family", label: "Family Trust (5)", type: "group", depth: 3 },
  { id: "investment", label: "Investment Group (8)", type: "group", depth: 3 },
  { id: "estate", label: "Estate Planning (4)", type: "group", depth: 3 },
  { id: "corporate", label: "Corporate Holdings (3)", type: "group", depth: 3 },
];

const links: GraphLink[] = [
  { source: "john", target: "client1" },
  { source: "john", target: "client2" },
  { source: "john", target: "client3" },
  { source: "john", target: "client4" },
  { source: "john", target: "client7" },

  { source: "client7", target: "acc1", label: "Account Manager" },
  { source: "client7", target: "acc2", label: "Advisor" },

  { source: "client2", target: "acc3", label: "Portfolio Manager" },
  { source: "client4", target: "acc7", label: "Trustee" },
  { source: "client4", target: "acc8", label: "Beneficiary" },

  { source: "john", target: "family" },
  { source: "john", target: "investment" },
  { source: "john", target: "estate" },
  { source: "john", target: "corporate" },
];

const colorByType: Record<NodeType, string> = {
  person: "#7b6df0",
  client: "#f97316",
  account: "#16a34a",
  group: "#8b5cf6",
};

export default function RelationshipGraph() {
  const svgRef = useRef<SVGSVGElement | null>(null);

  useEffect(() => {
    const width = 1000;
    const height = 700;
    const centerX = width / 2;
    const centerY = height / 2;

    const svg = d3.select(svgRef.current);
    svg.selectAll("*").remove();

    const graphNodes = nodes.map((d) => ({ ...d }));
    const graphLinks = links.map((d) => ({ ...d }));

    svg
      .attr("viewBox", `0 0 ${width} ${height}`)
      .style("width", "100%")
      .style("height", "100%")
      .style("background", "#fafafa");

    const container = svg.append("g");

    svg.call(
      d3
        .zoom<SVGSVGElement, unknown>()
        .scaleExtent([0.4, 3])
        .on("zoom", (event) => {
          container.attr("transform", event.transform);
        })
    );

    const linkGroup = container.append("g");
    const linkLabelGroup = container.append("g");
    const nodeGroup = container.append("g");
    const labelGroup = container.append("g");

    const simulation = d3
      .forceSimulation<GraphNode>(graphNodes)
      .force(
        "link",
        d3
          .forceLink<GraphNode, any>(graphLinks)
          .id((d) => d.id)
          .distance((d) => {
            const target = d.target as GraphNode;
            return target.depth * 70 + 80;
          })
          .strength(0.8)
      )
      .force("charge", d3.forceManyBody().strength(-550))
      .force("collision", d3.forceCollide<GraphNode>().radius((d) => getRadius(d) + 35))
      .force("center", d3.forceCenter(centerX, centerY))
      .force(
        "radial",
        d3
          .forceRadial<GraphNode>(
            (d) => d.depth * 120,
            centerX,
            centerY
          )
          .strength(0.9)
      );

    const link = linkGroup
      .selectAll("line")
      .data(graphLinks)
      .join("line")
      .attr("stroke", "#d6d6d6")
      .attr("stroke-width", 1.4);

    const linkLabels = linkLabelGroup
      .selectAll("text")
      .data(graphLinks.filter((d) => d.label))
      .join("text")
      .text((d) => d.label ?? "")
      .attr("font-size", 11)
      .attr("fill", "#64748b")
      .attr("text-anchor", "middle");

    const node = nodeGroup
      .selectAll("circle")
      .data(graphNodes)
      .join("circle")
      .attr("r", getRadius)
      .attr("fill", (d) => colorByType[d.type])
      .attr("stroke", (d) => (d.type === "person" ? "#1e293b" : "#ffffff"))
      .attr("stroke-width", (d) => (d.type === "person" ? 2.5 : 1.5))
      .style("cursor", "grab")
      .call(
        d3
          .drag<SVGCircleElement, GraphNode>()
          .on("start", dragStarted)
          .on("drag", dragged)
          .on("end", dragEnded)
      );

    const labels = labelGroup
      .selectAll("text")
      .data(graphNodes)
      .join("text")
      .text((d) => d.label)
      .attr("font-size", (d) => (d.type === "person" ? 18 : 12))
      .attr("font-weight", (d) => (d.type === "person" ? 700 : 400))
      .attr("fill", "#334155")
      .attr("text-anchor", "middle")
      .attr("pointer-events", "none");

    simulation.on("tick", () => {
      link
        .attr("x1", (d: any) => d.source.x)
        .attr("y1", (d: any) => d.source.y)
        .attr("x2", (d: any) => d.target.x)
        .attr("y2", (d: any) => d.target.y);

      linkLabels
        .attr("x", (d: any) => (d.source.x + d.target.x) / 2)
        .attr("y", (d: any) => (d.source.y + d.target.y) / 2 - 8);

      node
        .attr("cx", (d: any) => d.x)
        .attr("cy", (d: any) => d.y);

      labels
        .attr("x", (d: any) => d.x)
        .attr("y", (d: any) => d.y - getRadius(d) - 10);
    });

    function getRadius(d: GraphNode) {
      if (d.type === "person") return d.size ?? 40;
      if (d.type === "client") return 14;
      if (d.type === "account") return 7;
      return 9;
    }

    function dragStarted(event: any, d: any) {
      if (!event.active) simulation.alphaTarget(0.3).restart();
      d.fx = d.x;
      d.fy = d.y;
    }

    function dragged(event: any, d: any) {
      d.fx = event.x;
      d.fy = event.y;
    }

    function dragEnded(event: any, d: any) {
      if (!event.active) simulation.alphaTarget(0);
      d.fx = null;
      d.fy = null;
    }

    return () => {
      simulation.stop();
    };
  }, []);

  return (
    <div style={{ width: "100%", height: "700px" }}>
      <h1 style={{ fontSize: "32px", fontWeight: 700 }}>
        Hello React, from the MFE
      </h1>
      <svg ref={svgRef} />
    </div>
  );
}
