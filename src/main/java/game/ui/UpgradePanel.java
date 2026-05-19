package game.ui;

import game.resources.items.upgrades.ItemUpgrade;
import godot.annotation.Export;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterProperty;
import godot.api.Node;
import godot.api.PackedScene;
import godot.api.Panel;
import godot.core.VariantArray;
import godot.global.GD;

import java.util.Random;

@RegisterClass
public class UpgradePanel extends Panel {

    @Export
    @RegisterProperty
    public PackedScene upgradeCardScene;

    @Export
    @RegisterProperty
    public VariantArray<ItemUpgrade> upgradeList = game.Helper.GodotHelper.createItemUpgradeArray();

    private Node itemsContainer;
    private Random random = new Random();

    @RegisterFunction
    @Override
    public void _ready() {
        itemsContainer = getNodeOrNull("%ItemsContainer");
        if (itemsContainer == null) {
            GD.printErr("UpgradePanel: Không tìm thấy node %ItemsContainer!");
            return;
        }
        loadUpgrades();
    }

    @RegisterFunction
    public void loadUpgrades() {
        if (itemsContainer == null) return;

        // 1. Xóa tất cả các thẻ nâng cấp có sẵn (Placeholder)
        VariantArray<Node> children = itemsContainer.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            if (child != null) {
                child.queueFree();
            }
        }

        // Kiểm tra điều kiện an toàn
        if (upgradeList.isEmpty() || upgradeCardScene == null) {
            GD.printErr("UpgradePanel: Danh sách nâng cấp (upgradeList) trống hoặc chưa gắn Scene (upgradeCardScene)!");
            return;
        }

        // 2. Tạo 4 thẻ nâng cấp ngẫu nhiên mới
        for (int i = 0; i < 4; i++) {
            // Bốc ngẫu nhiên 1 nâng cấp từ danh sách
            int randomIndex = random.nextInt(upgradeList.size());
            ItemUpgrade randomUpgrade = upgradeList.get(randomIndex);

            if (randomUpgrade == null) continue;

            // Tạo instance của thẻ (Card)
            Node cardInstanceNode = upgradeCardScene.instantiate();
            
            // Gắn vào vùng chứa trên UI
            itemsContainer.addChild(cardInstanceNode);

            // Cập nhật dữ liệu cho thẻ
            if (cardInstanceNode instanceof UpgradeCard) {
                UpgradeCard cardInstance = (UpgradeCard) cardInstanceNode;
                // Truyền dữ liệu nâng cấp ngẫu nhiên vào Card
                cardInstance.setData(randomUpgrade);
            } else {
                GD.printErr("UpgradePanel: Node vừa sinh ra không phải là UpgradeCard!");
            }
        }
    }
}
