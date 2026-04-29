    import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function TaxonomySearchBar() {
  const [searchText, setSearchText] = useState("");
  const navigate = useNavigate();

  const handleSearch = () => {
    console.log("Searching:", searchText);

    // Navigate to tree page with query
    navigate("/taxonomy?query=" + encodeURIComponent(searchText));
  };

  const handleCreate = () => {
    navigate("/taxonomy"); // later you can change to /create
  };

  return (
    <div style={styles.container}>
      <div style={styles.centerArea}>
        <div style={styles.searchRow}>
          {/* Search box */}
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

            <button
              onClick={handleSearch}
              style={styles.iconButton}
              title="Search"
            >
              🔍
            </button>
          </div>

          {/* + button OUTSIDE */}
          <button
            onClick={handleCreate}
            style={styles.plusButton}
            title="Create taxonomy"
          >
            +
          </button>
        </div>
      </div>
    </div>
  );
}

const styles: Record<string, React.CSSProperties> = {
  container: {
    height: "100vh",
    display: "flex",
    flexDirection: "column",
    background: "#fafafa",
  },

  centerArea: {
    flex: 1,
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  },

  searchRow: {
    display: "flex",
    alignItems: "center",
    gap: "12px",
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
    boxShadow: "0 4px 20px rgba(0,0,0,0.08)",
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
    width: "42px",
    height: "42px",
    borderRadius: "50%",
    border: "none",
    background: "#111827",
    color: "#ffffff",
    fontSize: "22px",
    cursor: "pointer",
  },
};
