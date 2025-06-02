# Selenium Chrome Automation with Java

This project is a workspace for basic Chrome automation using Selenium with Java. The repository contains the necessary source code and external libraries required for running Selenium-based test scripts.

---

## Setting up the Workspace

Import the project to your IDE and ensure to have a symbolic link of the libraries. In this project, the`ExternalLibraries` folder contains the necessary JAR files for Selenium to work properly. To use these libraries in your workspace, we create a symbolic link called `ExternalLibs` that links to the `ExternalLibraries` folder. This symbolic link will enable your IDE to recognize and use the libraries directly.

### Steps to Set up the Symbolic Link:

1. **Open PowerShell as Administrator**:
   - Right-click the Start button and select **Windows PowerShell (Admin)** to launch PowerShell with elevated privileges.

2. **Navigate to the Project Directory**:
   In PowerShell, change to the root directory of the cloned repository (the location where the `ExternalLibraries` folder is stored):
   
   ```bash
   mklink /D "ExternalLibs" "..\ExternalLibraries"

3. **Verify Links**:
    Ensure it lists the necessary Selenium JAR files.

    ```bash
    dir "ExternalLibs"

## Run Tests

Open the project in an IDE (e.g., IntelliJ, Eclipse, VSCode), right-click the test file → Run.
