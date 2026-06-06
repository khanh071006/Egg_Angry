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
import game.entity.Player
import game.entity.WeaponContainer
import game.entity.enemies.IceSpikeEffect
import game.entity.enemies.behaviors.ChaserBehavior
import game.entity.enemies.behaviors.ShootingBehavior
import game.entity.enemies.core.Enemy
import game.entity.enemies.spawner.Spawner
import game.entity.enemies.types.EnemyBomber
import game.entity.enemies.types.EnemyEgg
import game.entity.enemies.types.EnemyEggLayer
import game.entity.enemies.types.EnemyIceMage
import game.entity.enemies.types.EnemyMeteorMage
import game.items.Coins
import game.items.weapons.Weapon
import game.items.weapons.WeaponBehavior
import game.items.weapons.melee.MeleeBehavior
import game.items.weapons.projectiles.Projectile
import game.items.weapons.range.RangeBehavior
import game.resources.items.ItemBase
import game.resources.items.ItemPassive
import game.resources.items.upgrades.ItemUpgrade
import game.resources.items.weapons.ItemWeapon
import game.resources.items.weapons.WeaponStats
import game.resources.units.EnemyStats
import game.resources.units.PlayerStats
import game.resources.units.UnitStats
import game.resources.waves.WaveData
import game.resources.waves.WaveUnitData
import game.ui.CoinsBag
import game.ui.FloatingText
import game.ui.GameOverMenu
import game.ui.HealthBar
import game.ui.ItemCard
import game.ui.MainMenu
import game.ui.SelectionCard
import game.ui.SelectionPanel
import game.ui.ShopCard
import game.ui.ShopPanel
import game.ui.StatsContainer
import game.ui.StoryMenu
import game.ui.TutorialDummy
import game.ui.TutorialMenu
import game.ui.UpgradeCard
import game.ui.UpgradePanel
import godot.entry.ArenaRegistrar
import godot.entry.BaseUnitRegistrar
import godot.entry.CameraControllerRegistrar
import godot.entry.ChaserBehaviorRegistrar
import godot.entry.CoinsBagRegistrar
import godot.entry.CoinsRegistrar
import godot.entry.EnemyBomberRegistrar
import godot.entry.EnemyEggLayerRegistrar
import godot.entry.EnemyEggRegistrar
import godot.entry.EnemyIceMageRegistrar
import godot.entry.EnemyMeteorMageRegistrar
import godot.entry.EnemyRegistrar
import godot.entry.EnemyStatsRegistrar
import godot.entry.FloatingTextRegistrar
import godot.entry.GameOverMenuRegistrar
import godot.entry.GlobalRegistrar
import godot.entry.HealthBarRegistrar
import godot.entry.HealthComponentRegistrar
import godot.entry.HitBoxComponentRegistrar
import godot.entry.HurtBoxComponentRegistrar
import godot.entry.IceSpikeEffectRegistrar
import godot.entry.ItemBaseRegistrar
import godot.entry.ItemCardRegistrar
import godot.entry.ItemPassiveRegistrar
import godot.entry.ItemUpgradeRegistrar
import godot.entry.ItemWeaponRegistrar
import godot.entry.MainMenuRegistrar
import godot.entry.MeleeBehaviorRegistrar
import godot.entry.PlayerRegistrar
import godot.entry.PlayerStatsRegistrar
import godot.entry.ProjectileRegistrar
import godot.entry.RangeBehaviorRegistrar
import godot.entry.SelectionCardRegistrar
import godot.entry.SelectionPanelRegistrar
import godot.entry.ShootingBehaviorRegistrar
import godot.entry.ShopCardRegistrar
import godot.entry.ShopPanelRegistrar
import godot.entry.SpawnerRegistrar
import godot.entry.StatsContainerRegistrar
import godot.entry.StoryMenuRegistrar
import godot.entry.TrailRegistrar
import godot.entry.TutorialDummyRegistrar
import godot.entry.TutorialMenuRegistrar
import godot.entry.UnitStatsRegistrar
import godot.entry.UpgradeCardRegistrar
import godot.entry.UpgradePanelRegistrar
import godot.entry.WaveDataRegistrar
import godot.entry.WaveUnitDataRegistrar
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
  public override val classRegistrarCount: Int = 52

  public override val projectName: String = "project-3"

  public override fun Context.`init`(): Unit {
    WaveDataRegistrar().register(registry)
    WaveUnitDataRegistrar().register(registry)
    TrailRegistrar().register(registry)
    ArenaRegistrar().register(registry)
    CameraControllerRegistrar().register(registry)
    GlobalRegistrar().register(registry)
    HealthComponentRegistrar().register(registry)
    HitBoxComponentRegistrar().register(registry)
    HurtBoxComponentRegistrar().register(registry)
    BaseUnitRegistrar().register(registry)
    ChaserBehaviorRegistrar().register(registry)
    ShootingBehaviorRegistrar().register(registry)
    EnemyRegistrar().register(registry)
    IceSpikeEffectRegistrar().register(registry)
    SpawnerRegistrar().register(registry)
    EnemyBomberRegistrar().register(registry)
    EnemyEggRegistrar().register(registry)
    EnemyEggLayerRegistrar().register(registry)
    EnemyIceMageRegistrar().register(registry)
    EnemyMeteorMageRegistrar().register(registry)
    PlayerRegistrar().register(registry)
    WeaponContainerRegistrar().register(registry)
    CoinsRegistrar().register(registry)
    MeleeBehaviorRegistrar().register(registry)
    ProjectileRegistrar().register(registry)
    RangeBehaviorRegistrar().register(registry)
    WeaponRegistrar().register(registry)
    WeaponBehaviorRegistrar().register(registry)
    ItemBaseRegistrar().register(registry)
    ItemPassiveRegistrar().register(registry)
    ItemUpgradeRegistrar().register(registry)
    ItemWeaponRegistrar().register(registry)
    WeaponStatsRegistrar().register(registry)
    EnemyStatsRegistrar().register(registry)
    PlayerStatsRegistrar().register(registry)
    UnitStatsRegistrar().register(registry)
    CoinsBagRegistrar().register(registry)
    FloatingTextRegistrar().register(registry)
    GameOverMenuRegistrar().register(registry)
    HealthBarRegistrar().register(registry)
    ItemCardRegistrar().register(registry)
    MainMenuRegistrar().register(registry)
    SelectionCardRegistrar().register(registry)
    SelectionPanelRegistrar().register(registry)
    ShopCardRegistrar().register(registry)
    ShopPanelRegistrar().register(registry)
    StatsContainerRegistrar().register(registry)
    StoryMenuRegistrar().register(registry)
    TutorialDummyRegistrar().register(registry)
    TutorialMenuRegistrar().register(registry)
    UpgradeCardRegistrar().register(registry)
    UpgradePanelRegistrar().register(registry)
  }

  public override fun Context.initEngineTypes(): Unit {
    registerVariantMapping()
    registerEngineTypes()
    registerEngineTypeMethods()
  }

  public override fun Context.getRegisteredClasses(): List<KClass<*>> = listOf(WaveData::class,
      WaveUnitData::class, Trail::class, Arena::class, CameraController::class, Global::class,
      HealthComponent::class, HitBoxComponent::class, HurtBoxComponent::class, BaseUnit::class,
      ChaserBehavior::class, ShootingBehavior::class, Enemy::class, IceSpikeEffect::class,
      Spawner::class, EnemyBomber::class, EnemyEgg::class, EnemyEggLayer::class,
      EnemyIceMage::class, EnemyMeteorMage::class, Player::class, WeaponContainer::class,
      Coins::class, MeleeBehavior::class, Projectile::class, RangeBehavior::class, Weapon::class,
      WeaponBehavior::class, ItemBase::class, ItemPassive::class, ItemUpgrade::class,
      ItemWeapon::class, WeaponStats::class, EnemyStats::class, PlayerStats::class,
      UnitStats::class, CoinsBag::class, FloatingText::class, GameOverMenu::class, HealthBar::class,
      ItemCard::class, MainMenu::class, SelectionCard::class, SelectionPanel::class,
      ShopCard::class, ShopPanel::class, StatsContainer::class, StoryMenu::class,
      TutorialDummy::class, TutorialMenu::class, UpgradeCard::class, UpgradePanel::class)
}
