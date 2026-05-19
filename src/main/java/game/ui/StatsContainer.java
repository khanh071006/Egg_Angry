package game.ui;

import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Label;
import godot.api.Panel;

@RegisterClass
public class StatsContainer extends Panel {

    @Export
    @RegisterProperty
    public Label healthLabel;
    @Export
    @RegisterProperty
    public Label regenLabel;
    @Export
    @RegisterProperty
    public Label lifestealLabel;
    @Export
    @RegisterProperty
    public Label damageLabel;
    @Export
    @RegisterProperty
    public Label luckLabel;
    @Export
    @RegisterProperty
    public Label speedLabel;
    @Export
    @RegisterProperty
    public Label blockLabel;
    @Export
    @RegisterProperty
    public Label harvestingLabel;

    @RegisterFunction
    @Override
    public void _ready() {
        if (healthLabel == null)
            healthLabel = (Label) getNode("%HealthLabel");
        if (regenLabel == null)
            regenLabel = (Label) getNode("%RegenLabel");
        if (lifestealLabel == null)
            lifestealLabel = (Label) getNode("%LifestealLabel");
        if (damageLabel == null)
            damageLabel = (Label) getNode("%DamageLabel");
        if (luckLabel == null)
            luckLabel = (Label) getNode("%LuckLabel");
        if (speedLabel == null)
            speedLabel = (Label) getNode("%SpeedLabel");
        if (blockLabel == null)
            blockLabel = (Label) getNode("%BlockLabel");
        if (harvestingLabel == null)
            harvestingLabel = (Label) getNode("%HarvestingLabel");
    }
}
