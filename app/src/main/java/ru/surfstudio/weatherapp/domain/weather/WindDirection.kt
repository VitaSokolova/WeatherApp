package ru.surfstudio.weatherapp.domain.weather

/**
 *  Wind direction in intercardinal and cardinal direction terms
 */
enum class WindDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    companion object {
        fun getByDegrees(degrees: Double): WindDirection {
          return when {
                (degrees in 337.5..360.0) || (degrees in .0..22.5) -> NORTH
                (degrees in 22.5..67.5) -> NORTHEAST
                (degrees in 67.5..112.5) -> EAST
                (degrees in 112.5..157.5) -> SOUTHEAST
                (degrees in 157.5..202.5) -> SOUTH
                (degrees in 202.5..247.5) -> SOUTHWEST
                (degrees in 247.5..292.5) -> WEST
                (degrees in 292.5..337.5) -> NORTHWEST
              else -> error("Number of degrees is incorrect")
            }
        }
    }
}