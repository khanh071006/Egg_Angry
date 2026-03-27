// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry

import game.autoloads.Global
import godot.`annotation`.RegisteredClassMetadata
import godot.core.KtConstructor0
import godot.registration.ClassRegistrar
import godot.registration.ClassRegistry
import kotlin.Unit
import kotlin.collections.listOf

@RegisteredClassMetadata(
  "Global",
  "Node",
  "game.autoloads.Global",
  "src/main/java/game/autoloads/Global.java",
  "gdj/game/autoloads/Global.gdj",
  "project-3",
  "godot.api.Node,godot.api.Object,godot.core.KtObject,godot.common.interop.NativeWrapper,godot.common.interop.NativePointer,kotlin.Any",
  "",
  "",
  "",
  true,
)
public open class GlobalRegistrar : ClassRegistrar {
  public override fun register(registry: ClassRegistry): Unit {
    with(registry) {
      registerClass<Global>(listOf(), Global::class, false, "Node", "Global", "src/main/java/game/autoloads/Global.java", "gdj/game/autoloads/Global.gdj") {
        constructor(KtConstructor0(::Global))
        notificationFunctions(listOf())
      }
    }
  }
}
