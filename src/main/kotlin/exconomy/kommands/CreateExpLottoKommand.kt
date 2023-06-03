package exconomy.kommands

import exconomy.items.giveLottoItem
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin

fun createExpLottoKommand(plugins: JavaPlugin) {
    plugins.kommand {
        register("경험치복권") {
            requires { isPlayer && isOp }
            executes {
                player.sendMessage("최소치와 최대치를 입력해주세요!")
            }
            then("min" to int(), "max" to int()) {
                requires { isPlayer && isOp }
                executes {
                    val min: Int by it
                    val max: Int by it
                    if (min > max) {
                        player.sendMessage("최소치가 최대치보다 큽니다!")
                    } else if (min == max) {
                        player.sendMessage("최소치와 최대치가 같습니다!")
                    } else {
                        giveLottoItem(player, min, max)
                    }
                }
            }
        }
    }
}
