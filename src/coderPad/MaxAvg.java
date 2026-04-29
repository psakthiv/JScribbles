import { useEffect, useRef, useState } from "react";

export default function TaxonomySearchBar() {
  const [searchText, setSearchText] = useState("");
  const inputRef = useRef<HTMLInputElement | null>(null);

  const handleSearch = () => {
    console.log("Searching:", searchText);
  };

  const handleCreate = () => {
    console.log("Create taxonomy");
  };

  // Press "/" to focus search
  useEffect(() => {
    const handleShortcut = (event: KeyboardEvent) => {
      if (event.key === "/" && document.activeElement !== inputRef.current) {
        event.preventDefault();
        inputRef.current?.focus();
      }
    };

    window.addEventListener("keydown", handleShortcut);

    return () => {
      window.removeEventListener("keydown", handleShortcut);
    };
  }, []);

  return (
    <div style={styles.container}>
      <div style={styles.wrapper}>
        <input
          ref={inputRef}
          type="text"
          placeholder="Search taxonomy...  Press / to focus"
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
          title="Search taxonomy"
        >
          🔍
        </button>
      </div>

      <button
        onClick={handleCreate}
        style={styles.plusButton}
        title="Create taxonomy"
      >
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
    transition: "transform 0.15s ease, box-shadow 0.15s ease",
  },
};
