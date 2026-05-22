// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.arena.Arena
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.NONE
import godot.core.VariantCaster.FLOAT
import godot.core.VariantParser.COLOR
import godot.core.VariantParser.DOUBLE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.core.VariantParser.VECTOR2
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "Arena",
  "Node2D",
  "game.arena.Arena",
  "src/main/java/game/arena/Arena.java",
  "gdj/game/arena/Arena.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.arena.Arena.normalColor,game.arena.Arena.blockColor,game.arena.Arena.critColor,game.arena.Arena.hpColor,game.arena.Arena.player,game.arena.Arena.upgradePanel,game.arena.Arena.shopPanel,game.arena.Arena.coinsBag",
  "game.arena.Arena._process,game.arena.Arena._ready,game.arena.Arena.show_block_text,game.arena.Arena.show_damage_text,game.arena.Arena.show_heal_text,game.arena.Arena._on_wave_completed,game.arena.Arena.spawn_coins,game.arena.Arena.add_gold_deferred,game.arena.Arena.clean_arena,game.arena.Arena.show_upgrades,game.arena.Arena._on_upgrade_selected,game.arena.Arena._on_shop_next_wave,game.arena.Arena.startNewWave",
  true,
)
public open class ArenaRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Arena>(listOf(), Arena::class, false, "Node2D", "Arena", "src/main/java/game/arena/Arena.java", "gdj/game/arena/Arena.gdj") {
        constructor(KtConstructor0(::Arena))
        notificationFunctions(listOf())
        function(Arena::_process, NIL, DOUBLE, KtFunctionArgument(DOUBLE, "kotlin.Double", "delta"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::show_block_text, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Node2D", "unit"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::show_damage_text, NIL, OBJECT, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Node2D", "unit"), KtFunctionArgument(OBJECT, "game.components.HitBoxComponent", "hitbox"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::show_heal_text, NIL, OBJECT, FLOAT, KtFunctionArgument(OBJECT, "godot.api.Node2D", "unit"), KtFunctionArgument(FLOAT, "kotlin.Float", "heal"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::_on_wave_completed, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::spawn_coins, NIL, OBJECT, KtFunctionArgument(OBJECT, "game.entity.enemies.Enemy", "enemy"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::add_gold_deferred, NIL, OBJECT, VECTOR2, KtFunctionArgument(OBJECT, "game.items.Coins", "gold"), KtFunctionArgument(VECTOR2, "godot.core.Vector2", "pos"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::clean_arena, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::show_upgrades, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::_on_upgrade_selected, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::_on_shop_next_wave, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::startNewWave, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Arena::normalColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::blockColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::critColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::hpColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::player, OBJECT, OBJECT, "game.entity.Player", NODE_TYPE, "Player", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::upgradePanel, OBJECT, OBJECT, "game.ui.UpgradePanel", NODE_TYPE, "UpgradePanel", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::shopPanel, OBJECT, OBJECT, "game.ui.ShopPanel", NODE_TYPE, "ShopPanel", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::coinsBag, OBJECT, OBJECT, "game.ui.CoinsBag", NODE_TYPE, "CoinsBag", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
