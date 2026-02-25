import kotlin.math.abs

fun Number.commas(): String = String.format("%,d", this)


class Moon(
    val name: String,
    val diameter: Int
) {
    fun info(): String {
        return "$name (diameter ${diameter.commas()}km)"
    }
}


class Planet(
    val name: String,
    val type: String,
    val distanceToSun: Long,
    val diameter: Int,
    val moonCount: Int,
    val majorMoons: MutableList<Moon> = mutableListOf<Moon>()
) {
    var scanned = false

    fun addMoon(newMoon: Moon) {
        majorMoons.add(newMoon)
    }

    fun scan() {
        scanned = true
    }

    fun info(): String {
        var infoText =
            "$name ($type, diameter ${diameter.commas()}km, ${distanceToSun.commas()}km from star, $moonCount moons)"
        if (majorMoons.isNotEmpty()) {
            infoText += "\n    Major moons:"
            for (moon in majorMoons) {
                infoText += "\n     - " + moon.info()
            }
        }
        return infoText
    }
}


class SolarSystem {
    val starName = "Sol"
    val starDiameter = 1390000
    val planets = mutableListOf<Planet>()

    init {
        // Set up the major moons

        val luna = Moon("Luna", 3475)
        val phobos = Moon("Phobos", 22)
        val deimos = Moon("Deimos", 13)
        val ganymede = Moon("Ganymede", 5268)
        val callisto = Moon("Callisto", 4821)
        val io = Moon("Io", 3643)
        val europa = Moon("Europa", 3122)
        val titan = Moon("Titan", 5150)
        val enceladus = Moon("Enceladus", 504)
        val rhea = Moon("Rhea", 1528)
        val dione = Moon("Dione", 1123)
        val tethys = Moon("Tethys", 1062)
        val mimas = Moon("Mimas", 396)
        val iapetus = Moon("Iapetus", 1469)
        val titania = Moon("Titania", 1578)
        val oberon = Moon("Oberon", 1523)
        val umbriel = Moon("Umbriel", 1169)
        val ariel = Moon("Ariel", 1158)
        val miranda = Moon("Miranda", 472)
        val triton = Moon("Triton", 2707)
        val nereid = Moon("Nereid", 418)
        val proteus = Moon("Proteus", 340)

        // Set up the planets

        val mercury = Planet(
            "Mercury",
            "rocky",
            58000000,
            4879,
            0
        )
        val venus = Planet(
            "Venus",
            "rocky",
            108000000,
            12104,
            0
        )
        val earth = Planet(
            "Earth",
            "rocky",
            150000000,
            12756,
            1,
            mutableListOf(luna)
        )
        val mars = Planet(
            "Mars",
            "rocky",
            228000000,
            6792,
            2,
            mutableListOf(phobos, deimos)
        )
        val jupiter = Planet(
            "Jupiter",
            "gas giant",
            778000000,
            142984,
            95,
            mutableListOf(ganymede, callisto, io, europa)
        )
        val saturn = Planet(
            "Saturn",
            "gas giant",
            1430000000,
            120536,
            274,
            mutableListOf(titan, enceladus, rhea, dione, tethys, mimas, iapetus)
        )
        val uranus = Planet(
            "Uranus",
            "ice giant",
            2870000000,
            51118,
            28,
            mutableListOf(titania, oberon, umbriel, ariel, miranda)
        )
        val neptune = Planet(
            "Neptune",
            "ice giant",
            4500000000,
            49528,
            16,
            mutableListOf(triton, nereid, proteus)
        )

        // Set up the solar system
        planets.add(mercury)
        planets.add(venus)
        planets.add(earth)
        planets.add(mars)
        planets.add(jupiter)
        planets.add(saturn)
        planets.add(uranus)
        planets.add(neptune)
    }

    fun info(): String {
        var infoText = "This solar system has $starName at its centre (diameter ${starDiameter}km). "
        infoText += "Orbiting $starName are ${planets.size} planets:"
        planets.forEachIndexed { index, planet ->
            infoText += "\n${index + 1}. " + planet.info()
        }
        return infoText
    }

    fun map(location: Planet? = null): String {
        var mapText = ""

        if (location != null) {
            mapText += "       "
            for (planet in planets) mapText += if (planet == location) "You Are Here" else "           "
            mapText += "\n"

            mapText += "            "
            for (planet in planets) mapText += if (planet == location) "↓↓         " else "           "
            mapText += "\n"
        }

        mapText += " ⬤"
        for (planet in planets) mapText += " ┄┄┄┄┄┄┄┄ ○"
        mapText += "\n"

        mapText += starName.padEnd(10)
        for (planet in planets) mapText += planet.name.padEnd(11)
        mapText += "\n"

        for (i in 0..10) {
            var mapMoonText = "           "
            for (planet in planets) {
                mapMoonText += if (planet.scanned && planet.majorMoons.size > i)
                    "•" + planet.majorMoons[i].name.padEnd(10)
                else
                    "           "
            }
            if (mapMoonText.isNotBlank()) mapText += "$mapMoonText\n"
        }

        return mapText
    }
}


class Mission(
    val solarSystem: SolarSystem,
    var currentPlanet: Planet?
) {
    val log = mutableListOf<String>()
    var distance: Long = 0
    var fuel = 10000
    val KM_PER_KG = 1000000

    init {
        log.add("Mission begins at ${currentPlanet!!.name}...")
    }

    fun status() {
        println("╔═════════════════════════════════════════╗")
        println("║    Mission Status: ACTIVE               ║")
        println("║     Distance (km): ${distance.commas().padEnd(20)} ║")
        println("║    Fuel left (kg): ${fuel.commas().padEnd(20)} ║")
        println("╠═════════════════════════════════════════╣")

        if (currentPlanet != null) {
            println("║   Orbiting planet: ${currentPlanet!!.name.padEnd(20)} ║")
            println("║ Orbit radius (km): ${currentPlanet!!.distanceToSun.commas().padEnd(20)} ║")

            if (currentPlanet!!.scanned) {
                println("║       Planet type: ${currentPlanet!!.type.padEnd(20)} ║")
                println("║     Diameter (km): ${currentPlanet!!.diameter.commas().padEnd(20)} ║")
                println("║        Moon count: ${currentPlanet!!.moonCount.toString().padEnd(20)} ║")

                if (currentPlanet!!.majorMoons.isNotEmpty()) {
                    print("║       Major moons: ")
                    for (moon in currentPlanet!!.majorMoons) {
                        println("${moon.name.padEnd(20)} ║")
                        if (moon != currentPlanet!!.majorMoons.last()) {
                            print("║                    ")
                        }
                    }
                }
            }
            else {
                println("║     Planet status: UNSCANNED            ║")
            }
        }
        else {
            println("║          ADRIFT IN SPACE :-(            ║")
        }

        println("╚═════════════════════════════════════════╝")
        println()
    }

    fun showLog() {
        println()
        println("MISSION LOG")
        println("START")
        for (entry in log) {
            println(">>> $entry")
        }
        println("END")
        println()
    }

    fun logEntry(entry: String) {
        if (entry.isNotBlank()) {
            log.add(entry)
        }
    }

    fun getAction(): Char? {
        println("OPTIONS:")
        println("• View mission [L]og")
        println("• View system [M]ap")
        println("• [T]ravel to a planet")
        println("• [S]can local planet")
        println("• [Q]uit mission")
        println()
        print("> ")

        val action = readlnOrNull()?.firstOrNull()?.lowercaseChar()
        println()

        return action
    }

    fun getDestination(): Planet? {
        if (fuel > 0) {
            println("Select a destination:")
            solarSystem.planets.forEachIndexed { i, planet ->
                print("• [${i + 1}] ${planet.name.padEnd(10)}")

                if (planet == currentPlanet) {
                    print("CURRENT LOCATION  ")
                }
                else {
                    val distAway = abs(planet.distanceToSun - currentPlanet!!.distanceToSun).commas() + "km"
                    print(distAway.padEnd(18))
                }

                if (planet.scanned)
                    println("${planet.moonCount} moons")
                else
                    println("???")
            }
            println()
            print("> ")

            val planetIndex = readlnOrNull()?.toIntOrNull()
            if (planetIndex != null && planetIndex in 1..solarSystem.planets.size) {
                val planet = solarSystem.planets[planetIndex - 1]
                return planet
            }
        }

        return null
    }

    fun travelTo(destination: Planet) {
        if (currentPlanet != null && destination != currentPlanet && fuel > 0) {
            logEntry("Leaving ${currentPlanet!!.name} for ${destination.name}...")

            val tripDist = abs(destination.distanceToSun - currentPlanet!!.distanceToSun)
            val fuelUsed = (tripDist / KM_PER_KG).toInt()

            if (fuelUsed > fuel) {
                distance += fuel * KM_PER_KG
                fuel = 0
                currentPlanet = null
                logEntry("Ran out of fuel!")
                logEntry("ADRIFT IN SPACE  :-(")
            }
            else {
                distance += tripDist
                fuel -= fuelUsed
                currentPlanet = destination
                logEntry("Travelled ${tripDist.commas()}km")
                logEntry("Arrived at ${currentPlanet!!.name}")
            }
        }
    }

    fun scanPlanet() {
        if (currentPlanet != null) {
            logEntry("Scanning planet...")
            currentPlanet!!.scan()
            logEntry(currentPlanet!!.info())
        }
    }

    fun showMap() {
        println(solarSystem.map(currentPlanet))
    }
}


fun main() {
    val solarSystem = SolarSystem()
    println(solarSystem.map(solarSystem.planets[2]))

    val mission = Mission(solarSystem, solarSystem.planets[2])

    while (true) {
        mission.status()

        val action = mission.getAction()
        when (action) {
            'l' -> mission.showLog()
            'm' -> mission.showMap()
            't' -> {
                val destination = mission.getDestination() ?: continue
                mission.travelTo(destination)
            }

            's' -> {
                mission.scanPlanet()
            }

            'q' -> break
        }
    }

}