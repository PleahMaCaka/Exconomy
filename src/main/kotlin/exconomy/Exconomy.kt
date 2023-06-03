package exconomy

import exconomy.events.ConsumeChecker
import exconomy.kommands.createExpLottoKommand
import exconomy.kommands.exconomyKommand
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Exconomy : JavaPlugin() {

    companion object {
        lateinit var instance: Exconomy
            private set
    }

    override fun onEnable() {
        logger.info("Exconomy Plugin Enabled!")

        instance = this
        registerKommands()

        //events
        server.pluginManager.registerEvents(ConsumeChecker, this)
    }

    private fun registerKommands() {
        exconomyKommand(this)
        createExpLottoKommand(this)
    }

}