import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function TaxonomySearchPage() {
  const [searchText, setSearchText] = useState("");
  const navigate = useNavigate();

  const handleSearch = () => {
    navigate(`/taxonomy?query=${encodeURIComponent(searchText)}`);
  };

  const handleCreate = () => {
    navigate("/taxonomy");
  };

  return (
    <div style={styles.container}>
      <div style={styles.searchBox}>
        <textarea
          placeholder="Search taxonomy..."
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === "Enter" && !e.shiftKey) {
              e.preventDefault();
              handleSearch();
            }
          }}
          style={styles.textarea}
        />

        <button
          onClick={handleCreate}
          style={styles.createIcon}
          title="Create taxonomy"
        >
          +
        </button>

        <button
          onClick={handleSearch}
          style={styles.searchIcon}
          title="Search taxonomy"
        >
          🔍
        </button>
      </div>
    </div>
  );
}

const styles: Record<string, React.CSSProperties> = {
  container: {
    height: "100vh",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    background: "#fafafa",
  },

  searchBox: {
    position: "relative",
    width: "560px",
    minHeight: "120px",
    border: "1px solid #d1d5db",
    borderRadius: "24px",
    background: "#ffffff",
    boxShadow: "0 4px 20px rgba(0,0,0,0.08)",
    padding: "16px 16px 52px 16px",
  },

  textarea: {
    width: "100%",
    minHeight: "70px",
    resize: "none",
    border: "none",
    outline: "none",
    fontSize: "15px",
    fontFamily: "Arial, sans-serif",
    background: "transparent",
  },

  createIcon: {
    position: "absolute",
    left: "16px",
    bottom: "12px",
    width: "34px",
    height: "34px",
    borderRadius: "50%",
    border: "1px solid #d1d5db",
    background: "#ffffff",
    color: "#9ca3af",
    fontSize: "22px",
    cursor: "pointer",
  },

  searchIcon: {
    position: "absolute",
    right: "16px",
    bottom: "12px",
    width: "34px",
    height: "34px",
    borderRadius: "50%",
    border: "1px solid #d1d5db",
    background: "#ffffff",
    color: "#9ca3af",
    fontSize: "16px",
    cursor: "pointer",
  },
};
