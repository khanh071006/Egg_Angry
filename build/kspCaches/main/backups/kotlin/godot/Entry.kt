// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry.ALCfrmDWDbZqvUGVSkbZ

import game.animation.Trail
import game.arena.Arena
import game.arena.CameraController
import game.autoloads.Global
import game.components.HitBoxComponent
import game.components.HurtBoxComponent
import game.entity.BaseUnit
import game.entity.Enemy
import game.entity.Player
import game.resources.EnemyStats
import game.resources.PlayerStats
import game.resources.UnitStats
import godot.entry.ArenaRegistrar
import godot.entry.BaseUnitRegistrar
import godot.entry.CameraControllerRegistrar
import godot.entry.EnemyRegistrar
import godot.entry.EnemyStatsRegistrar
import godot.entry.GlobalRegistrar
import godot.entry.HitBoxComponentRegistrar
import godot.entry.HurtBoxComponentRegistrar
import godot.entry.PlayerRegistrar
import godot.entry.PlayerStatsRegistrar
import godot.entry.TrailRegistrar
import godot.entry.UnitStatsRegistrar
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
  public override val classRegistrarCount: Int = 12

  public override val projectName: String = "project-3"

  public override fun Context.`init`(): Unit {
    TrailRegistrar().register(registry)
    ArenaRegistrar().register(registry)
    CameraControllerRegistrar().register(registry)
    GlobalRegistrar().register(registry)
    HitBoxComponentRegistrar().register(registry)
    HurtBoxComponentRegistrar().register(registry)
    BaseUnitRegistrar().register(registry)
    EnemyRegistrar().register(registry)
    PlayerRegistrar().register(registry)
    EnemyStatsRegistrar().register(registry)
    PlayerStatsRegistrar().register(registry)
    UnitStatsRegistrar().register(registry)
  }

  public override fun Context.initEngineTypes(): Unit {
    registerVariantMapping()
    registerEngineTypes()
    registerEngineTypeMethods()
  }

  public override fun Context.getRegisteredClasses(): List<KClass<*>> = listOf(Trail::class,
      Arena::class, CameraController::class, Global::class, HitBoxComponent::class,
      HurtBoxComponent::class, BaseUnit::class, Enemy::class, Player::class, EnemyStats::class,
      PlayerStats::class, UnitStats::class)
}
