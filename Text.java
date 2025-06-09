
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


Problem:
VOCAL aims to support carers by providing grants that allow them to take necessary breaks, thereby improving their overall wellbeing. However, the current grant application process is inefficient and burdensome. The existing system requires manual intervention, where VOCAL team members must download and manually input data from submitted forms into the case management system (CSM). This lack of integration between the online application form and the CSM results in delays, potential errors, and increased workload for staff. Additionally, the form itself has limitations regarding accessibility and API integration, making it difficult to meet the needs of all users effectively.

Process:
The current process begins with carers filling out an online form to apply for grants. Once the form is submitted, a VOCAL team member must manually download the form and enter the data into the case management system. This manual step is time-consuming and prone to errors, contributing to inefficiencies in processing grant applications. Furthermore, the existing form lacks integration with the CSM API, which would otherwise streamline the process by automating data transfer and improving accuracy.

People:
The people involved in this process include the carers who apply for the grants and the VOCAL staff responsible for processing these applications. Carers rely on these grants for essential respite, and any delays or issues in the application process directly impact their ability to receive timely support. On the other side, VOCAL staff are burdened with additional manual tasks that not only slow down the process but also divert their attention from other critical support activities.

Platform:
The current platform involves an online form and a separate case management system (CSM). The lack of integration between these two platforms is a significant pain point. The online form is outdated, with limitations in accessibility and API integration, which hampers its effectiveness. The case management system, while robust, requires manual data entry due to the absence of automation between the form and the system. This disjointed setup underscores the need for a more cohesive and integrated solution that can enhance efficiency, accuracy, and user experience for both carers and VOCAL staff.
