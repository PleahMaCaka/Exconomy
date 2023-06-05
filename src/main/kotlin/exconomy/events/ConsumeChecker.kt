package exconomy.events

import exconomy.items.CHECK_ITEM
import exconomy.items.LOTTO_ITEM
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import utils.getTotalExp

object ConsumeChecker : Listener {

    @EventHandler
    fun consumeCheck(event: PlayerInteractEvent) {
        // item works
        if (event.item?.type != CHECK_ITEM) return
        val lore = event.item?.itemMeta?.lore ?: return

        // displayName() is a return component and can't compare
//        if (event.item?.itemMeta?.displayName != CHECK_ITEM_NAME) return
        if (lore.size != 1) return

        // player works
        val player = event.player
        player.giveExp(lore[0].toIntOrNull() ?: return)

        event.item?.amount = event.item?.amount?.minus(1) ?: return

        // send messages
        player.sendMessage(
            Component.text("경험치 수표를 사용했습니다!    ${ChatColor.GREEN}[ +${lore[0]} -> ${getTotalExp(player)} ]")
        )
    }

    // event handler for lotto
    // parse "$from ~ $to Exp." from lore[1] and give random exp
    @EventHandler
    fun consumeLotto(event: PlayerInteractEvent) {
        if (event.item?.type != LOTTO_ITEM) return
        val lore = event.item?.itemMeta?.lore ?: return

//        if (event.item?.itemMeta?.displayName != LOTTO_ITEM_NAME) return
        if (lore.size != 2) return

        val player = event.player
        val from = lore[1].substringBefore("~").trim().toIntOrNull() ?: return
        val to = lore[1].substringAfter("~").substringBefore("Exp.").trim().toIntOrNull() ?: return
        val exp = (from..to).random()
        player.giveExp(exp)

        event.item?.amount = event.item?.amount?.minus(1) ?: return

        player.sendMessage(
            Component.text("경험치 복권을 사용했습니다!    ${ChatColor.GREEN}[ + $exp ]")
        )

        // wow
        if (exp == 0) player.sendMessage(Component.text("우와... 꽝이라니.."))

        if (exp == to) player.sendMessage(Component.text("와우! 가능한 최대 경험치를 획득했습니다!    ${ChatColor.RED}${ChatColor.BOLD}[ + $to ]"))
    }

}