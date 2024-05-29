# Define the source and output directories
$srcDir = "src"
$binDir = "bin"

# Create the output directory if it doesn't exist
if (-Not (Test-Path $binDir)) {
    New-Item -ItemType Directory -Path $binDir
    Write-Host "Created output directory: $binDir"
}

Write-Host "Compilation Running..."

# Recursively find all Java files in the source directory
$javaFiles = Get-ChildItem -Recurse -Filter *.java -Path $srcDir

# Generate a list of all Java file paths
$filePaths = $javaFiles | ForEach-Object { $_.FullName }

# Compile all Java files, specifying the output directory for compiled classes
& javac -d $binDir $filePaths

# Print file-name of all compiled classes
foreach ($file in $javaFiles) {
    Write-Host $file.FullName
}

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation finished successfully!"
}
else {
    Write-Host "Compilation failed!"
}
