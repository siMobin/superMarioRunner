# Define the project root directory
$projectRoot = "./"

# Define the output directory where the .class files are located
$binDir = "$projectRoot\bin"

# Compile the project if necessary
if (-Not (Test-Path $binDir)) {
    write-host "Compilation required. Compiling now..."
    ./compiler.ps1
}

# Define the fully qualified name of the main class
$mainClass = "App" # [Entry point of the program]

# Run the Java program from the project root, setting
# the classpath to include the bin directory
Set-Location $projectRoot
& java -cp $binDir $mainClass

# Handle the exit code
if ($LASTEXITCODE -ne 0) {
    write-host "Program failed to compile!"
    write-host "Try to fix possible errors..."
    ./compiler.ps1
    write-host "Try to run again..."
    ./run.ps1
}
elseif ($LASTEXITCODE -eq 0) {
    Write-Host "Program ran successfully!"
    exit 0
}
else {
    Write-Host "Program failed to run!"
    exit -1
}
