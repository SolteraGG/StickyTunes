rootProject.name = "StickyTunes"

include("nbs")
include("api")
include("plugin")

project(":nbs").name = "StickyTunesNBS"
project(":api").name = "StickyTunesAPI"
project(":plugin").name = "StickyTunes"
