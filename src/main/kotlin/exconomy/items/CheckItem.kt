package exconomy.items

import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

val CHECK_ITEM: Material = Material.PAPER

val CHECK_ITEM_NAME: String = ChatColor.BOLD.toString() + ChatColor.WHITE + "경험치 수표"

fun giveCheckToPlayer(player: Player, amount: Int) {
    val itemStack = ItemStack(CHECK_ITEM, 1)

    val itemMeta = itemStack.itemMeta
    itemMeta.displayName(Component.text(CHECK_ITEM_NAME))

    itemMeta.lore(listOf(Component.text(amount.toString())))

    itemStack.itemMeta = itemMeta
    player.inventory.addItem(itemStack)
}