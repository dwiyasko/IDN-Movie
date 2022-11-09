package com.example.idnmedia.moviedetail.domain.model

data class MovieClip(
    val name: String,
    val key: String,
    val type: ClipType,
    val site: ClipSite,
    val isOfficial: Boolean,
)

enum class ClipSite {
    Youtube,
    Others;

    companion object {
        fun findMatches(withString: String): ClipSite {
            return values().find { it.name.equals(withString, ignoreCase = true) } ?: Others
        }
    }
}

enum class ClipType {
    Clip,
    Trailer,
    Teaser;

    companion object {
        fun findMatches(withString: String): ClipType {
            return values().find { it.name.equals(withString, ignoreCase = true) } ?: Clip
        }
    }
}
