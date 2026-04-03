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
import godot.core.VariantParser.COLOR
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
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
  "game.arena.Arena.normalColor,game.arena.Arena.blockColor,game.arena.Arena.critColor,game.arena.Arena.player",
  "game.arena.Arena._ready,game.arena.Arena.show_block_text,game.arena.Arena.show_damage_text",
  true,
)
public open class ArenaRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Arena>(listOf(), Arena::class, false, "Node2D", "Arena", "src/main/java/game/arena/Arena.java", "gdj/game/arena/Arena.gdj") {
        constructor(KtConstructor0(::Arena))
        notificationFunctions(listOf())
        function(Arena::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::show_block_text, NIL, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Node2D", "unit"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Arena::show_damage_text, NIL, OBJECT, OBJECT, KtFunctionArgument(OBJECT, "godot.api.Node2D", "unit"), KtFunctionArgument(OBJECT, "game.components.HitBoxComponent", "hitbox"), KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Arena::normalColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::blockColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::critColor, COLOR, COLOR, "godot.core.Color", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Arena::player, OBJECT, OBJECT, "game.entity.Player", NODE_TYPE, "Player", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
