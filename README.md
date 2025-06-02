# Selenium Projects

A curated collection of Java + Selenium WebDriver automation scripts demonstrating:

- *Web interaction & UI testing*  
- *Data scraping & persistence*  
- *Automated PDF report generation*  

> These examples are intended as quick‑start references for building reliable, maintainable browser‑automation suites.

---

## ✨ Features

| Category          | Highlights                                                |
|-------------------|-----------------------------------------------------------|
| Test Automation   | Page‑object pattern, waits, parameterised test data       |
| Web Interaction   | Form submission, dynamic element handling, file download |
| Reporting         | OpenPDF‑powered summary reports with screenshots          |
| CI‑ready          | Headless execution support (Chrome)                       |

---

## 🔧 Technologies Used

- *Java SDK/JRE 11+*  
- *Selenium WebDriver*  
- *ChromeDriver* (other drivers configurable)  
- *OpenPDF* – PDF generation & manipulation  
- *JUnit 5 / TestNG* – sample test frameworks (optional)  
- *Maven* – dependency & build management  

---

## 🏁 Quick Start

### 1. Prerequisites

| Tool            | Minimum Version | Notes                           |
|-----------------|-----------------|---------------------------------|
| Java SDK        | 11              | Set JAVA_HOME                 |
| Maven           | 3.6             | Builds & resolves dependencies  |
| Chrome Browser  | Latest stable   | Or match your chosen driver ver |
| ChromeDriver    | Same as Chrome  | Place on PATH or configure in webdriver.chrome.driver |

### 2. Clone and Build

```bash
git clone https://github.com/Daemon-Demons/selenium-projects.git
cd selenium-projects
mvn clean compile
