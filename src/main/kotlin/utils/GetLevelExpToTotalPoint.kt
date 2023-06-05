package utils

import org.bukkit.entity.Player
import kotlin.math.roundToInt

fun getTotalExp(player: Player): Int {
    val level = player.level
    val exp = player.exp

    val totalExp: Int = if (level <= 16) {
        (level * level) + (6 * level)
    } else if (level <= 31) {
        (2.5 * level * level - 40.5 * level + 360).toInt()
    } else {
        (4.5 * level * level - 162.5 * level + 2220).toInt()
    }

    return totalExp + (exp * player.expToLevel).roundToInt()
}
