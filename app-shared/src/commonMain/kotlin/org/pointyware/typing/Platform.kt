package org.pointyware.typing

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform