param(
    [switch]$Build
)

# Build if requested or if classes are missing
if ($Build -or -not (Test-Path -Path "target\classes")) {
    mvn -q -DskipTests package
}

mvn -q -DskipTests exec:java


