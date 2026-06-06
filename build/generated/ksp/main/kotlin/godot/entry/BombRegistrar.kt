// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.entity.Bomb
import godot.`annotation`.RegisteredClassMetadata
import godot.api.MultiplayerAPI.RPCMode.DISABLED
import godot.api.MultiplayerPeer.TransferMode.RELIABLE
import godot.core.KtConstructor0
import godot.core.KtRpcConfig
import godot.core.PropertyHint.NODE_TYPE
import godot.core.PropertyHint.NONE
import godot.core.VariantParser.NIL
import godot.core.VariantParser.OBJECT
import godot.core.VariantParser.VECTOR2
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import godot.registration.KtFunctionArgument
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "Bomb",
  "Node2D",
  "game.entity.Bomb",
  "src/main/java/game/entity/Bomb.java",
  "gdj/game/entity/Bomb.gdj",
  "project-3",
  "godot.api.Node2D,godot.api.CanvasItem,godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "game.entity.Bomb.hitbox,game.entity.Bomb.bombOffset,game.entity.Bomb.explosionOffset,game.entity.Bomb.bombScale,game.entity.Bomb.explosionScale",
  "game.entity.Bomb._ready,game.entity.Bomb._on_fuse_timer_timeout,game.entity.Bomb._on_animated_sprite_2d_animation_finished",
  true,
)
public open class BombRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Bomb>(listOf(), Bomb::class, false, "Node2D", "Bomb", "src/main/java/game/entity/Bomb.java", "gdj/game/entity/Bomb.gdj") {
        constructor(KtConstructor0(::Bomb))
        notificationFunctions(listOf())
        function(Bomb::_ready, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Bomb::_on_fuse_timer_timeout, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        function(Bomb::_on_animated_sprite_2d_animation_finished, NIL, KtFunctionArgument(NIL, "kotlin.Unit"), KtRpcConfig(DISABLED.id.toInt(), false, RELIABLE.id.toInt(), 0))
        property(Bomb::hitbox, OBJECT, OBJECT, "game.components.HitBoxComponent", NODE_TYPE, "HitBoxComponent", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Bomb::bombOffset, VECTOR2, VECTOR2, "godot.core.Vector2", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Bomb::explosionOffset, VECTOR2, VECTOR2, "godot.core.Vector2", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Bomb::bombScale, VECTOR2, VECTOR2, "godot.core.Vector2", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
        property(Bomb::explosionScale, VECTOR2, VECTOR2, "godot.core.Vector2", NONE, "", godot.core.PropertyUsageFlags.DEFAULT.flag)
      }
    }
  }
}
