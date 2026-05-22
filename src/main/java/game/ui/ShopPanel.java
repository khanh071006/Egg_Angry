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
import godot.annotation.RegisterSignal;
import godot.core.Signal0;

@RegisterClass
public class ShopPanel extends Panel {

    @Export
    @RegisterProperty
    public PackedScene shopCardScene;

    @Export
    @RegisterProperty
    public VariantArray<godot.api.Resource> shopItems = game.Helper.GodotHelper.createResourceArray();

    @RegisterSignal
    public Signal0 onShopNextWave = Signal0.create(this, "onShopNextWave");

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

                // KẾT NỐI TÍN HIỆU KHI MUA HÀNG
                cardInstance.onItemPurchased.connect(
                    godot.core.Callable.create(this, new godot.core.StringName("_on_shop_card_on_item_purchased")),
                    0
                );
            } else {
                GD.printErr("ShopPanel: Node sinh ra không phải là ShopCard!");
            }
        }
    }

    @RegisterFunction
    public void _on_next_wave_button_pressed() {
        onShopNextWave.emit();
    }

    @RegisterFunction
    public void _on_shop_card_on_item_purchased(ItemBase purchasedItem) {
        if (Global.instance.itemCardScene == null) {
            GD.printErr("ShopPanel: Chưa gán itemCardScene trong Global!");
            return;
        }

        Node cardInstanceNode = Global.instance.itemCardScene.instantiate();
        if (cardInstanceNode instanceof ItemCard) {
            ItemCard cardInstance = (ItemCard) cardInstanceNode;

            // Kết nối tín hiệu khi click vào thẻ ItemCard (dùng cho video sau)
            cardInstance.onItemCardSelected.connect(
                godot.core.Callable.create(this, new godot.core.StringName("_on_item_card_selected")),
                0
            );

            // Kiểm tra xem món đồ vừa mua có phải là vũ khí không
            if (purchasedItem != null && purchasedItem.itemType == ItemBase.ItemType.WEAPON) {
                if (weaponsContainer != null) {
                    weaponsContainer.addChild(cardInstance);
                }

                // Ép kiểu sang ItemWeapon để ném cho Player
                if (purchasedItem instanceof game.resources.items.weapons.ItemWeapon) {
                    game.resources.items.weapons.ItemWeapon weapon = (game.resources.items.weapons.ItemWeapon) purchasedItem;
                    
                    if (Global.player != null) {
                        Global.player.addWeapon(weapon);
                    }
                    Global.instance.equippedWeapons.add(weapon);
                }

                // Cập nhật giao diện hình ảnh và khung cho thẻ
                cardInstance.setItem(purchasedItem);
            }
        }
    }

    @RegisterFunction
    public void _on_item_card_selected(ItemCard card) {
        // Dùng cho logic ghép vũ khí ở video tiếp theo
        GD.print("Đã chọn thẻ vũ khí: " + card.item.itemName);
    }
}
