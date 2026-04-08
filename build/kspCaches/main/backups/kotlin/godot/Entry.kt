// THIS FILE IS GENERATED! DO NOT EDIT IT MANUALLY!
package godot.entry.ALCfrmDWDbZqvUGVSkbZ

import game.animation.Trail
import game.arena.Arena
import game.arena.CameraController
import game.autoloads.Global
import game.components.HealthComponent
import game.components.HitBoxComponent
import game.components.HurtBoxComponent
import game.entity.BaseUnit
import game.entity.Enemy
import game.entity.Player
import game.entity.WeaponContainer
import game.items.weapons.Weapon
import game.items.weapons.WeaponBehavior
import game.items.weapons.melee.MeleeBehavior
import game.items.weapons.projectiles.Projectile
import game.items.weapons.range.RangeBehavior
import game.resources.items.ItemBase
import game.resources.items.weapons.ItemWeapon
import game.resources.items.weapons.WeaponStats
import game.resources.units.EnemyStats
import game.resources.units.PlayerStats
import game.resources.units.UnitStats
import game.ui.FloatingText
import game.ui.HealthBar
import godot.entry.ArenaRegistrar
import godot.entry.BaseUnitRegistrar
import godot.entry.CameraControllerRegistrar
import godot.entry.EnemyRegistrar
import godot.entry.EnemyStatsRegistrar
import godot.entry.FloatingTextRegistrar
import godot.entry.GlobalRegistrar
import godot.entry.HealthBarRegistrar
import godot.entry.HealthComponentRegistrar
import godot.entry.HitBoxComponentRegistrar
import godot.entry.HurtBoxComponentRegistrar
import godot.entry.ItemBaseRegistrar
import godot.entry.ItemWeaponRegistrar
import godot.entry.MeleeBehaviorRegistrar
import godot.entry.PlayerRegistrar
import godot.entry.PlayerStatsRegistrar
import godot.entry.ProjectileRegistrar
import godot.entry.RangeBehaviorRegistrar
import godot.entry.TrailRegistrar
import godot.entry.UnitStatsRegistrar
import godot.entry.WeaponBehaviorRegistrar
import godot.entry.WeaponContainerRegistrar
import godot.entry.WeaponRegistrar
import godot.entry.WeaponStatsRegistrar
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
  public override val classRegistrarCount: Int = 24

  public override val projectName: String = "project-3"

  public override fun Context.`init`(): Unit {
    TrailRegistrar().register(registry)
    ArenaRegistrar().register(registry)
    CameraControllerRegistrar().register(registry)
    GlobalRegistrar().register(registry)
    HealthComponentRegistrar().register(registry)
    HitBoxComponentRegistrar().register(registry)
    HurtBoxComponentRegistrar().register(registry)
    BaseUnitRegistrar().register(registry)
    EnemyRegistrar().register(registry)
    PlayerRegistrar().register(registry)
    WeaponContainerRegistrar().register(registry)
    MeleeBehaviorRegistrar().register(registry)
    ProjectileRegistrar().register(registry)
    RangeBehaviorRegistrar().register(registry)
    WeaponRegistrar().register(registry)
    WeaponBehaviorRegistrar().register(registry)
    ItemBaseRegistrar().register(registry)
    ItemWeaponRegistrar().register(registry)
    WeaponStatsRegistrar().register(registry)
    EnemyStatsRegistrar().register(registry)
    PlayerStatsRegistrar().register(registry)
    UnitStatsRegistrar().register(registry)
    FloatingTextRegistrar().register(registry)
    HealthBarRegistrar().register(registry)
  }

  public override fun Context.initEngineTypes(): Unit {
    registerVariantMapping()
    registerEngineTypes()
    registerEngineTypeMethods()
  }

  public override fun Context.getRegisteredClasses(): List<KClass<*>> = listOf(Trail::class,
      Arena::class, CameraController::class, Global::class, HealthComponent::class,
      HitBoxComponent::class, HurtBoxComponent::class, BaseUnit::class, Enemy::class, Player::class,
      WeaponContainer::class, MeleeBehavior::class, Projectile::class, RangeBehavior::class,
      Weapon::class, WeaponBehavior::class, ItemBase::class, ItemWeapon::class, WeaponStats::class,
      EnemyStats::class, PlayerStats::class, UnitStats::class, FloatingText::class,
      HealthBar::class)
}
