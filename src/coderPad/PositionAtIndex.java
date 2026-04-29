import { useState } from "react";
import TaxonomyTree from "../components/TaxonomyTree";

type TaxonomyNodeType = "root" | "term" | "property";

type TaxonomyNode = {
  name: string;
  type: TaxonomyNodeType;
  dataType?: string;
  children?: TaxonomyNode[];
};

const taxonomyData: TaxonomyNode = {
  name: "Passport",
  type: "root",
  children: [
    { name: "First Name", type: "property", dataType: "string" },
    { name: "Last Name", type: "property", dataType: "string" },
    {
      name: "Address",
      type: "term",
      children: [
        { name: "Door Number", type: "property", dataType: "number" },
        { name: "City", type: "property", dataType: "string" },
        { name: "Postcode", type: "property", dataType: "string" },
      ],
    },
  ],
};

function flattenNodes(node: TaxonomyNode): TaxonomyNode[] {
  return [node, ...(node.children?.flatMap(flattenNodes) ?? [])];
}

export default function TaxonomyViewPage() {
  const [rightPaneOpen, setRightPaneOpen] = useState(true);
  const [showNodes, setShowNodes] = useState(true);
  const [showProperties, setShowProperties] = useState(true);

  const allNodes = flattenNodes(taxonomyData);
  const terms = allNodes.filter((n) => n.type === "term");
  const properties = allNodes.filter((n) => n.type === "property");

  return (
    <div style={styles.page}>
      {/* LEFT FIXED FACET PANE */}
      <aside style={styles.leftPane}>
        <h3 style={styles.heading}>Taxonomy Facets</h3>

        <div style={styles.statBox}>
          <div>Total Nodes</div>
          <strong>{allNodes.length}</strong>
        </div>

        <div style={styles.statBox}>
          <div>Terms</div>
          <strong>{terms.length}</strong>
        </div>

        <div style={styles.statBox}>
          <div>Properties</div>
          <strong>{properties.length}</strong>
        </div>

        <div style={styles.section}>
          <button style={styles.accordion} onClick={() => setShowNodes(!showNodes)}>
            {showNodes ? "▾" : "▸"} Nodes
          </button>

          {showNodes && (
            <ul style={styles.list}>
              {terms.map((node) => (
                <li key={node.name}>{node.name}</li>
              ))}
            </ul>
          )}
        </div>

        <div style={styles.section}>
          <button
            style={styles.accordion}
            onClick={() => setShowProperties(!showProperties)}
          >
            {showProperties ? "▾" : "▸"} Properties
          </button>

          {showProperties && (
            <ul style={styles.list}>
              {properties.map((prop) => (
                <li key={prop.name}>
                  {prop.name}
                  <span style={styles.badge}>{prop.dataType}</span>
                </li>
              ))}
            </ul>
          )}
        </div>
      </aside>

      {/* CENTER TREE AREA */}
      <main style={styles.treeArea}>
        <TaxonomyTree data={taxonomyData} />
      </main>

      {/* RIGHT SLIDE PANE */}
      <aside
        style={{
          ...styles.rightPane,
          width: rightPaneOpen ? "320px" : "42px",
        }}
      >
        <button
          style={styles.toggleButton}
          onClick={() => setRightPaneOpen(!rightPaneOpen)}
          title={rightPaneOpen ? "Collapse pane" : "Expand pane"}
        >
          {rightPaneOpen ? "»" : "«"}
        </button>

        {rightPaneOpen && (
          <div style={styles.rightContent}>
            <h3>Details Pane</h3>
            <p>You can load edit form, metadata, audit history, etc. here.</p>
          </div>
        )}
      </aside>
    </div>
  );
}

const styles: Record<string, React.CSSProperties> = {
  page: {
    height: "100vh",
    display: "flex",
    overflow: "hidden",
    fontFamily: "Arial, sans-serif",
    background: "#f8fafc",
  },

  leftPane: {
    width: "260px",
    flexShrink: 0,
    padding: "16px",
    borderRight: "1px solid #e5e7eb",
    background: "#ffffff",
    overflowY: "auto",
  },

  heading: {
    margin: "0 0 16px",
    fontSize: "18px",
  },

  statBox: {
    display: "flex",
    justifyContent: "space-between",
    padding: "10px 12px",
    marginBottom: "8px",
    borderRadius: "10px",
    background: "#f1f5f9",
    fontSize: "14px",
  },

  section: {
    marginTop: "18px",
  },

  accordion: {
    width: "100%",
    textAlign: "left",
    border: "none",
    background: "transparent",
    cursor: "pointer",
    fontWeight: 700,
    fontSize: "14px",
    padding: "8px 0",
  },

  list: {
    listStyle: "none",
    padding: 0,
    margin: 0,
    fontSize: "13px",
    color: "#475569",
  },

  badge: {
    marginLeft: "8px",
    padding: "2px 6px",
    borderRadius: "999px",
    background: "#e0e7ff",
    color: "#4338ca",
    fontSize: "11px",
  },

  treeArea: {
    flex: 1,
    overflow: "hidden",
    background: "#fafafa",
  },

  rightPane: {
    position: "relative",
    flexShrink: 0,
    height: "100vh",
    borderLeft: "1px solid #e5e7eb",
    background: "#ffffff",
    transition: "width 0.25s ease",
    overflow: "hidden",
  },

  toggleButton: {
    position: "absolute",
    top: "50%",
    left: "-18px",
    transform: "translateY(-50%)",
    width: "36px",
    height: "36px",
    borderRadius: "50%",
    border: "1px solid #d1d5db",
    background: "#ffffff",
    cursor: "pointer",
    boxShadow: "0 2px 8px rgba(0,0,0,0.12)",
    fontSize: "20px",
    zIndex: 10,
  },

  rightContent: {
    padding: "20px",
    fontSize: "14px",
    color: "#475569",
  },
};
