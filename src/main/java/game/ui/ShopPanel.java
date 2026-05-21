package game.ui;

import game.autoloads.Global;
import game.resources.items.ItemBase;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node;
import godot.api.PackedScene;
import godot.api.Panel;
import godot.core.VariantArray;
import godot.global.GD;

@RegisterClass
public class ShopPanel extends Panel {

    @Export
    @RegisterProperty
    public PackedScene shopCardScene;

    @Export
    @RegisterProperty
    public VariantArray<godot.api.Resource> shopItems = game.Helper.GodotHelper.createResourceArray();

    private Node itemsContainer;
    private Node passivesContainer;
    private Node weaponsContainer;

    @RegisterFunction
    @Override
    public void _ready() {
        itemsContainer = getNodeOrNull("%ItemsContainer");
        passivesContainer = getNodeOrNull("%PassivesContainer");
        weaponsContainer = getNodeOrNull("%WeaponsContainer");

        if (passivesContainer != null) {
            VariantArray<Node> children = passivesContainer.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Node child = children.get(i);
                if (child != null) child.queueFree();
            }
        }

        if (weaponsContainer != null) {
            VariantArray<Node> children = weaponsContainer.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Node child = children.get(i);
                if (child != null) child.queueFree();
            }
        }
    }

    @RegisterFunction
    public void loadShop(int currentWave) {
        if (itemsContainer != null) {
            VariantArray<Node> children = itemsContainer.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Node child = children.get(i);
                if (child != null) child.queueFree();
            }
        }

        if (shopItems.isEmpty() || shopCardScene == null) {
            GD.printErr("ShopPanel: Danh sách shopItems trống hoặc chưa gắn Scene shopCardScene!");
            return;
        }

        java.util.Map<String, Global.TierConfig> config = Global.shopProbabilityConfig;
        
        // Chuyển Resource sang ItemBase để gọi hàm
        VariantArray<ItemBase> castedItems = game.Helper.GodotHelper.createItemBaseArray();
        for (int i = 0; i < shopItems.size(); i++) {
            godot.api.Resource res = shopItems.get(i);
            if (res instanceof ItemBase) {
                castedItems.append((ItemBase) res);
            }
        }
        
        VariantArray<ItemBase> selectedItems = Global.instance.selectItemsForOffer(castedItems, currentWave, config);

        for (int i = 0; i < selectedItems.size(); i++) {
            ItemBase eachShopItem = selectedItems.get(i);
            if (eachShopItem == null) continue;

            Node cardInstanceNode = shopCardScene.instantiate();
            if (itemsContainer != null) {
                itemsContainer.addChild(cardInstanceNode);
            }

            if (cardInstanceNode instanceof ShopCard) {
                ShopCard cardInstance = (ShopCard) cardInstanceNode;
                cardInstance.setShopItem(eachShopItem);
            } else {
                GD.printErr("ShopPanel: Node sinh ra không phải là ShopCard!");
            }
        }
    }
}
