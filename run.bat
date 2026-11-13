@echo off
title Alumni Directory & Networking Management System
color 0B
cls

echo =======================================================================
echo          POORNIMA INSTITUTE OF ENGG. ^& TECHNOLOGY
echo      Alumni Directory System - Minor Project (Session 2026-27)
echo      Student: ARPEET UPADHAYAY (PIET25CR007) - Section 3CSD
echo =======================================================================
echo.

:: Ensure bin directory exists
if not exist "bin\Main.class" (
    echo [INFO] Compiling project...
    if not exist bin mkdir bin
    javac -encoding UTF-8 -cp "lib\mysql-connector-j-8.4.0.jar" -d bin src\db\*.java src\model\*.java src\dao\*.java src\ui\*.java src\*.java
)

echo [INFO] Starting Alumni Directory Application...
echo.

java -cp "bin;lib\mysql-connector-j-8.4.0.jar" Main

if %ERRORLEVEL% neq 0 (
    echo.
    echo [NOTE] If an error occurred above, press any key to exit.
    pause
)
