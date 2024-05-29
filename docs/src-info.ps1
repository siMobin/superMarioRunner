$srcDir = "src"
$lines = Get-ChildItem -Path $srcDir -Recurse -Include *.java | ForEach-Object {
    (Get-Content $_.FullName | Measure-Object -Line).Lines
}

$tree = tree $srcDir
$count = $lines.Count
$sum = $lines | Measure-Object -Sum
$average = $lines | Measure-Object -Average
$maximum = $lines | Measure-Object -Maximum
$minimum = $lines | Measure-Object -Minimum

Write-Output $tree
Write-Output " "
Write-Output "Total Files     : $count"
Write-Output "Average Lines   : $($average.Average)"
Write-Output "Total Lines    : $($sum.Sum)"
Write-Output "Maximum Lines  : $($maximum.Maximum)"
Write-Output "Minimum Lines  : $($minimum.Minimum)"
