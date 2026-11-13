@echo off
REM ============================================================================
REM PIET Minor Project (NSP) - B.Tech II Year (III Sem)
REM Project: Alumni Directory & Networking Management System
REM Student: ARPEET UPADHAYAY (PIET25CR007) | Section: 3CSD
REM College: Poornima Institute of Engineering & Technology
REM ============================================================================

echo ============================================================================
echo   Alumni Directory ^& Networking Management System
echo   Student: ARPEET UPADHAYAY (PIET25CR007) - PIET 3CSD
echo ============================================================================

echo [INFO] Compiling Alumni Directory Application...
if not exist bin mkdir bin

javac -encoding UTF-8 -cp "lib\mysql-connector-j-8.4.0.jar" -d bin src\db\*.java src\model\*.java src\dao\*.java src\ui\*.java src\*.java

if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed. Please check JDK installation and path.
    pause
    exit /b %ERRORLEVEL%
)

echo [SUCCESS] Compilation complete. Launching application...
java -cp "bin;lib\mysql-connector-j-8.4.0.jar" Main

pause
