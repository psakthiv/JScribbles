
import React from "react";

const JPMCHeader = () => {
  return (
    <header className="jpmc-header">
      <div className="logo">
        <span className="icon">🔷</span>
        <span className="title">JPMC</span>
      </div>
      <nav className="nav-links">
        <a href="#" className="nav-link active">Accounts</a>
        <a href="#" className="nav-link">Alerts <span className="badge">1</span></a>
        <a href="#" className="nav-link">Tools</a>
        <a href="#" className="nav-link">More</a>
      </nav>
    </header>
  );
};

export default JPMCHeader;





.jpmc-header {
  background-color: #003366;
  color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 60px;
  padding: 0 24px;
  font-family: "Helvetica Neue", Arial, sans-serif;
}

.logo {
  display: flex;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
}

.icon {
  font-size: 20px;
  margin-right: 8px;
}

.nav-links {
  display: flex;
  gap: 24px;
}

.nav-link {
  color: white;
  text-decoration: none;
  font-size: 14px;
  position: relative;
}

.nav-link.active {
  font-weight: bold;
  border-bottom: 2px solid #0072CE;
}

.badge {
  background-color: #0072CE;
  color: white;
  border-radius: 12px;
  padding: 2px 6px;
  font-size: 10px;
  margin-left: 4px;
  vertical-align: top;
}
