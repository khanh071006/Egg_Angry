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
    private godot.api.Button combineButton;

    @RegisterFunction
    @Override
    public void _ready() {
        itemsContainer = getNodeOrNull("%ItemsContainer");
        passivesContainer = getNodeOrNull("%PassivesContainer");
        weaponsContainer = getNodeOrNull("%WeaponsContainer");
        combineButton = (godot.api.Button) getNodeOrNull("%CombineButton");

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
        ItemCard contextCard = card;
        boolean canMerge = false;

        if (card.item != null && card.item.itemType == ItemBase.ItemType.WEAPON) {
            int count = 0;
            // Đếm số lượng vũ khí cùng tên trong balo
            for (game.resources.items.weapons.ItemWeapon weapon : Global.instance.equippedWeapons) {
                if (weapon.itemName.equals(card.item.itemName)) {
                    count++;
                }
            }

            if (count >= 2) {
                canMerge = true;
            }
        }

        // Vô hiệu hoá nút Combine nếu không thể ghép
        if (combineButton != null) {
            combineButton.setDisabled(!canMerge);
        }

        GD.print("Đã chọn thẻ vũ khí: " + card.item.itemName + " | Có thể ghép: " + canMerge);
    }

    @RegisterFunction
    public void _on_combine_button_pressed() {
        game.resources.items.weapons.ItemWeapon clickedWeapon = Global.instance.selectedWeapon;
        if (clickedWeapon == null) return;
        if (clickedWeapon.upgradeTo == null) return;

        java.util.List<godot.api.Node> weaponsToRemove = new java.util.ArrayList<>();
        if (Global.player != null && Global.player.getCurrentWeapons() != null) {
            for (godot.api.Node child : Global.player.getCurrentWeapons()) {
                if (child instanceof game.items.weapons.Weapon) {
                    game.items.weapons.Weapon w = (game.items.weapons.Weapon) child;
                    if (w.data != null && w.data.itemName.equals(clickedWeapon.itemName)) {
                        weaponsToRemove.add(child);
                        if (weaponsToRemove.size() == 2) break;
                    }
                }
            }
        }

        java.util.List<ItemCard> cardsToRemove = new java.util.ArrayList<>();
        if (weaponsContainer != null) {
            godot.core.VariantArray<godot.api.Node> children = weaponsContainer.getChildren();
            for (int i = 0; i < children.size(); i++) {
                godot.api.Node child = children.get(i);
                if (child instanceof ItemCard) {
                    ItemCard c = (ItemCard) child;
                    if (c.item != null && c.item.itemName.equals(clickedWeapon.itemName)) {
                        cardsToRemove.add(c);
                        if (cardsToRemove.size() == 2) break;
                    }
                }
            }
        }

        if (weaponsToRemove.size() < 2 || cardsToRemove.size() < 2) {
            GD.printErr("ShopPanel: Không đủ 2 vũ khí và thẻ để ghép!");
            return;
        }

        // Delete weapons
        for (godot.api.Node wNode : weaponsToRemove) {
            game.items.weapons.Weapon w = (game.items.weapons.Weapon) wNode;
            if (Global.player != null) {
                Global.player.removeWeapon(w);
            }
            Global.instance.equippedWeapons.remove(w.data);
        }

        // Delete cards
        for (ItemCard c : cardsToRemove) {
            c.queueFree();
        }

        // Create new weapon
        game.resources.items.weapons.ItemWeapon upgradedWeapon = null;
        try {
            upgradedWeapon = (game.resources.items.weapons.ItemWeapon) clickedWeapon.upgradeTo;
        } catch (Exception e) {
            GD.printErr("Lỗi khi ép kiểu vũ khí nâng cấp: " + e.getMessage());
        }

        if (upgradedWeapon != null) {
            if (Global.player != null) {
                Global.player.addWeapon(upgradedWeapon);
            }
            Global.instance.equippedWeapons.add(upgradedWeapon);

            // Create new item card
            if (Global.instance.itemCardScene != null) {
                godot.api.Node newCardNode = Global.instance.itemCardScene.instantiate();
                if (newCardNode instanceof ItemCard) {
                    ItemCard newCard = (ItemCard) newCardNode;
                    if (weaponsContainer != null) {
                        weaponsContainer.addChild(newCard);
                    }
                    newCard.setItem(upgradedWeapon);
                    newCard.onItemCardSelected.connect(
                        godot.core.Callable.create(this, new godot.core.StringName("_on_item_card_selected")),
                        0
                    );
                }
            }
        }

        Global.instance.selectedWeapon = null;
        if (combineButton != null) {
            combineButton.setDisabled(true);
        }
    }
}
