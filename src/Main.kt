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
//    val majorMoons = mutableListOf<Moon>()

    fun addMoon(newMoon: Moon) {
        majorMoons.add(newMoon)
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
//        val mercury = Planet("Mercury", "rocky", 58000000, 4879, 0)
//        val venus = Planet("Venus", "rocky", 108000000, 12104, 0)
//        val earth = Planet("Earth", "rocky", 150000000, 12756, 1)
//        val mars = Planet("Mars", "rocky", 228000000, 6792, 2)
//        val jupiter = Planet("Jupiter", "gas giant", 778000000, 142984, 95)
//        val saturn = Planet("Saturn", "gas giant", 1430000000, 120536, 274)
//        val uranus = Planet("Uranus", "ice giant", 2870000000, 51118, 28)
//        val neptune = Planet("Neptune", "ice giant", 4500000000, 49528, 16)

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

        // Assign moons to planets
//        earth.addMoon(luna)
//        mars.addMoon(phobos)
//        mars.addMoon(deimos)
//        jupiter.addMoon(ganymede)
//        jupiter.addMoon(callisto)
//        jupiter.addMoon(io)
//        jupiter.addMoon(europa)
//        saturn.addMoon(titan)
//        saturn.addMoon(enceladus)
//        saturn.addMoon(rhea)
//        saturn.addMoon(dione)
//        saturn.addMoon(tethys)
//        saturn.addMoon(mimas)
//        saturn.addMoon(iapetus)
//        uranus.addMoon(titania)
//        uranus.addMoon(oberon)
//        uranus.addMoon(umbriel)
//        uranus.addMoon(ariel)
//        uranus.addMoon(miranda)
//        neptune.addMoon(triton)
//        neptune.addMoon(nereid)
//        neptune.addMoon(proteus)
    }

    fun info(): String {
        var infoText = "This solar system has $starName at its centre (diameter ${starDiameter}km). "
        infoText += "Orbiting $starName are ${planets.size} planets:"
        planets.forEachIndexed { index, planet ->
            infoText += "\n${index + 1}. " + planet.info()
        }
        return infoText
    }
}


class Mission(val solarSystem: SolarSystem) {
    var currentPlanet = solarSystem.planets[2]
    val log = mutableListOf<String>()
    var distance: Long = 0

    init {
        log.add("Mission begins...")
    }

    fun status() {
        println()
        println("╔════════════════════════════════════════╗")
        println("║ Mission Status :: ACTIVE               ║")
        println("╠════════════════════════════════════════╣")
        println("║ Current location: ${currentPlanet.name.padEnd(20)} ║")
        println("║ Distance (km):    ${distance.commas().padEnd(20)} ║")
        println("╚════════════════════════════════════════╝")
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
        println("• [T]ravel to a planet")
        println("• [Q]uit mission")
        println()
        print("> ")

        val action = readlnOrNull()?.firstOrNull()?.lowercaseChar()

        return action
    }

    fun getDestination(): Planet? {
        println("Pick a planet to travel to:")
        solarSystem.planets.forEachIndexed { i, planet ->
            println("${i + 1}. ${planet.name}")
        }
        print("Destination: ")

        val planetIndex = readlnOrNull()?.toIntOrNull()
        if (planetIndex != null && planetIndex in 1..solarSystem.planets.size) {
            val planet = solarSystem.planets[planetIndex - 1]
            return planet
        }

        return null
    }

    fun travelTo(destination: Planet) {
        val currentDistFromSun = currentPlanet.distanceToSun
        val destinationDistFromSun = destination.distanceToSun
        val tripDist = abs(destinationDistFromSun - currentDistFromSun)
        distance += tripDist

        logEntry("Leaving ${currentPlanet.name} for ${destination.name}...")

        currentPlanet = destination

        logEntry("Travelled ${tripDist.commas()}km")
        logEntry("Arrived at ${currentPlanet.name}")

        logEntry("Scanning...")
        logEntry(currentPlanet.info())

    }
}


fun main() {
    val solarSystem = SolarSystem()
    val mission = Mission(solarSystem)

    while (true) {
        mission.status()

        val action = mission.getAction()
        when (action) {
            'l' -> mission.showLog()
            't' -> {
                val destination = mission.getDestination() ?: continue
                mission.travelTo(destination)
            }

            'q' -> break
        }
    }

}