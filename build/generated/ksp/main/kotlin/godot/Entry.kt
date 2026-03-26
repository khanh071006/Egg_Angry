// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry.ALCfrmDWDbZqvUGVSkbZ

import game.BaseUnit
import game.Player
import godot.entry.BaseUnitRegistrar
import godot.entry.PlayerRegistrar
import godot.registerEngineTypeMethods
import godot.registerEngineTypes
import godot.registerVariantMapping
import godot.registration.Entry
import godot.registration.Entry.Context
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.listOf
import kotlin.reflect.KClass

public class Entry : Entry() {
  public override val classRegistrarCount: Int = 2

  public override val projectName: String = "project-3"

  public override fun Context.`init`(): Unit {
    BaseUnitRegistrar().register(registry)
    PlayerRegistrar().register(registry)
  }

  public override fun Context.initEngineTypes(): Unit {
    registerVariantMapping()
    registerEngineTypes()
    registerEngineTypeMethods()
  }

  public override fun Context.getRegisteredClasses(): List<KClass<*>> = listOf(BaseUnit::class,
      Player::class)
}
