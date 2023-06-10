package co.devine_solutions.runtimer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform