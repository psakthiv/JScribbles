import { FaUserCircle, FaBell } from "react-icons/fa";

export default function Header() {
  return (
    <header style={styles.header}>
      {/* Left */}
      <div style={styles.left}>
        <span style={styles.logo}>J.P.Morgan</span>
        <span style={styles.separator}>|</span>
      </div>

      {/* Center */}
      <div style={styles.center}>
        Taxonomy
      </div>

      {/* Right */}
      <div style={styles.right}>
        <FaUserCircle size={18} />
        <span>Punitha Sakthivel</span>
        <FaBell size={16} />
      </div>
    </header>
  );
}

const styles: Record<string, React.CSSProperties> = {
  header: {
    height: "56px",
    background: "#000000",
    color: "#ffffff",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    padding: "0 20px",
    fontFamily: "Arial, sans-serif",
  },

  left: {
    display: "flex",
    alignItems: "center",
    gap: "8px",
    fontWeight: 600,
  },

  center: {
    fontSize: "18px",
    fontWeight: 600,
  },

  right: {
    display: "flex",
    alignItems: "center",
    gap: "10px",
    fontSize: "14px",
  },

  logo: {
    fontWeight: 700,
  },

  separator: {
    opacity: 0.6,
  },
};
