import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function TaxonomySearchBar() {
  const [searchText, setSearchText] = useState("");
  const navigate = useNavigate();

  const handleSearch = () => {
    console.log("Searching:", searchText);

    // Navigate to tree page
    navigate("/taxonomy");
  };

  const handleCreate = () => {
    navigate("/taxonomy"); // or different route later
  };

  return (
    <div style={styles.container}>
      <div style={styles.wrapper}>
        <input
          type="text"
          placeholder="Search taxonomy..."
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === "Enter") handleSearch();
          }}
          style={styles.input}
        />

        <button onClick={handleSearch} style={styles.iconButton}>
          🔍
        </button>
      </div>

      <button onClick={handleCreate} style={styles.plusButton}>
        +
      </button>
    </div>
  );
}

const styles: Record<string, React.CSSProperties> = {
  container: {
    display: "flex",
    alignItems: "center",
    gap: "12px",
    justifyContent: "center",
    height: "100vh",
  },
  wrapper: {
    width: "520px",
    height: "48px",
    display: "flex",
    alignItems: "center",
    border: "1px solid #d1d5db",
    borderRadius: "999px",
    background: "#ffffff",
    padding: "0 10px 0 18px",
    boxShadow: "0 2px 8px rgba(0,0,0,0.08)",
  },
  input: {
    flex: 1,
    border: "none",
    outline: "none",
    fontSize: "15px",
    background: "transparent",
  },
  iconButton: {
    border: "none",
    background: "transparent",
    fontSize: "18px",
    cursor: "pointer",
  },
  plusButton: {
    width: "40px",
    height: "40px",
    borderRadius: "50%",
    border: "none",
    background: "#111827",
    color: "#ffffff",
    fontSize: "22px",
    cursor: "pointer",
  },
};
