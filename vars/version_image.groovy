def currentVersion = new File('version.txt').text.trim()

if (!currentVersion) {
    throw new RuntimeException("Current version is not available")
}

def versionParts = currentVersion.tokenize('.')
def major = versionParts[0].toInteger()
def minor = versionParts[1].toInteger()
def patch = versionParts[2].toInteger()

// Increment the patch version
patch += 1

// Create the new version
def newVersion = "${major}.${minor}.${patch}"

// Optionally, update the version in the file
new File('version.txt').text = newVersion
println "Updated version to: ${newVersion} in file"
