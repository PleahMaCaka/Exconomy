package exconomy.kommands

import exconomy.items.giveCheckToPlayer
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import utils.getTotalExp


fun exconomyKommand(plugin: JavaPlugin) {
    plugin.kommand {
        // 경험치 확인
        // 경험치 수표 100
        register("경험치") {
            requires { isPlayer }
            then("확인") {
                executes {
                    player.sendMessage(Component.text("현재 경험치 : ${getTotalExp(player)}"))
                }
            }
            then("수표") {
                requires { isPlayer }
                executes {
                    player.sendMessage(
                        Component.text(
                            "수표로 교환할 경험치를 입력하세요!    ${ChatColor.GREEN}[ 현재: ${
                                getTotalExp(
                                    player
                                )
                            } ]"
                        )
                    )
                    player.sendMessage(Component.text("${ChatColor.GRAY}/경험치 수표 <경험치>"))
                }
                then("amount" to int()) {
                    executes {
                        val amount: Int by it
                        val beforeXp: Int = getTotalExp(player)
                        if (amount > getTotalExp(player)) {
                            player.sendMessage(Component.text("수표로 교환할 경험치가 부족합니다!"))
                        } else {
                            // remove exp from player
                            if (amount <= 0) {
                                player.sendMessage(Component.text("0 이상의 경험치를 입력해주세요!"))
                                return@executes
                            }
                            player.giveExp(-amount)
                            player.sendMessage(
                                Component.text(
                                    "$amount 경험치가 수표로 교환되었습니다!    ${ChatColor.RED}[ $beforeXp -> ${
                                        getTotalExp(
                                            player
                                        )
                                    } ]"
                                )
                            )
                            giveCheckToPlayer(player, amount)
                        }
                    }
                }
            }
        }
    }
}