# ==============================================================================
# PIET Minor Project (NSP) - B.Tech II Year (III Sem)
# Project: Alumni Directory & Networking Management System
# Student: ARPEET UPADHAYAY (PIET25CR007) | Section: 3CSD
# College: Poornima Institute of Engineering & Technology
# ==============================================================================

Write-Host "================================================================================" -ForegroundColor Cyan
Write-Host "  PIET Minor Project: Alumni Directory & Networking Management System" -ForegroundColor Yellow
Write-Host "  Student: ARPEET UPADHAYAY (PIET25CR007) | Section: 3CSD" -ForegroundColor White
Write-Host "================================================================================" -ForegroundColor Cyan

if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

Write-Host "`n[1/2] Compiling Java Sources..." -ForegroundColor Green
$sources = (Get-ChildItem -Path "src" -Filter "*.java" -Recurse).FullName
javac -encoding UTF-8 -cp "lib\mysql-connector-j-8.4.0.jar" -d bin $sources

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Compilation failed. Please inspect errors above." -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host "[2/2] Compilation successful! Launching GUI..." -ForegroundColor Green
java -cp "bin;lib\mysql-connector-j-8.4.0.jar" Main
