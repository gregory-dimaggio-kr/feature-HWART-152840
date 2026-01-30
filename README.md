# hw-web-csr-web-manager

### Code repo Background:
This repository contains the source code for CSR web manager.

It currently builds the CSR WEB project consisting of the CSR Web WAR and CSR Web EAR.

This build was migrated from Team City [legacy URL here](https://teamcity.kroger.com/teamcity/admin/editProject.html?projectId=Eprn_CsrWeb)

Due to age/scope this build has only been tested on GHA with JDK8.

Tests are skipped due to lift/shift requirements from the [migration ticket](https://jira.kroger.com/jira/browse/HWART-97141)


This is a new repo created from the already existing eprn-core's modules/datawarehouse-rmi repo. So, that we can better manage this as an independent repo. Please refer the below OLD repo for all the changes or history of this code.

#### Old Repo:
https://github.com/krogertechnology/hw-eprn-data-warehouse/tree/main/EAF

#### Documentation:
The documentation can be accessed in the below GIT repo:

https://github.com/krogertechnology/hw-eprn-data-warehouse/blob/main/Documentation/EAF%20web%20apps/Data%20Warehouse%20webapp%20technical%20support%20guide.docx

https://github.com/krogertechnology/hw-eprn-data-warehouse/tree/main/Documentation/CSR

#### Current Code Owners:
HW-TLC-WARRIORS

### CSR Web manager Web App 
The CSR Manager web application provides maintenance functions for several aspects of the CSR process.  Users can set up new states for reporting and control various database-driven properties for the reporting process.  The application also serves as a secure point to download reports – usually to manually upload the report to a state agency or validate scripts info.
State reporting agencies often reject certain records from reports in the case of invalid data.  When rejects are reported, the contact can then use CSR Manager to update extract records, regenerate reports and resubmit them to the state agency.

### Purpose
This is used by our Business Compliance Team to access our PDMP file submissions, correct the data, generate the reports, and submit to the state agencies.




