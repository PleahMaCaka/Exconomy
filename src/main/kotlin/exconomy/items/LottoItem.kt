package exconomy.items

import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

val LOTTO_ITEM = Material.PAPER

val LOTTO_ITEM_NAME = ChatColor.BOLD.toString() + ChatColor.WHITE + "경험치 복권"

fun giveLottoItem(player: Player, from: Int, to: Int) {
    val itemStack = ItemStack(CHECK_ITEM, 1)

    val itemMeta = itemStack.itemMeta
    itemMeta.displayName(Component.text(LOTTO_ITEM_NAME))

    itemMeta.lore(
        listOf(
            Component.text("${ChatColor.WHITE}아래에 표시된 [최소 ~ 최대] 경험치를 얻을 수 있습니다!"),
            Component.text("$from ~ $to Exp.")
        )
    )

    itemStack.itemMeta = itemMeta
    player.inventory.addItem(itemStack)
}