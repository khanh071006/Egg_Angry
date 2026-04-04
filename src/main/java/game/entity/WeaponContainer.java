package game.entity;


import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.Node;
import godot.api.Node2D;
import godot.core.VariantArray;

// Import cái này vì sếp đang quản lý danh sách vũ khí bằng Java List
import java.util.List;

@RegisterClass
public class WeaponContainer extends Node2D {

    // Khai báo các biến lưu trữ 6 cái "Khuôn" (Preset Nodes)
    private Node2D layout1;
    private Node2D layout2;
    private Node2D layout3;
    private Node2D layout4;
    private Node2D layout5;
    private Node2D layout6;

    @RegisterFunction
    @Override
    public void _ready() {
        // Lấy các Node con có tên từ "1" đến "6"
        layout1 = (Node2D) getNode("1");
        layout2 = (Node2D) getNode("2");
        layout3 = (Node2D) getNode("3");
        layout4 = (Node2D) getNode("4");
        layout5 = (Node2D) getNode("5");
        layout6 = (Node2D) getNode("6");
    }

    // Ở bài trước ta dùng List<Node> (ArrayList), nên tui truyền List vào đây luôn
    public void updateWeaponsPosition(List<Node> weapons) {
        int count = weapons.size();

        // Nếu không có vũ khí nào thì thoát luôn
        if (count == 0) return;

        Node2D referenceNode = null;

        // Tương đương với lệnh "match count" trong GDScript
        switch (count) {
            case 1: referenceNode = layout1; break;
            case 2: referenceNode = layout2; break;
            case 3: referenceNode = layout3; break;
            case 4: referenceNode = layout4; break;
            case 5: referenceNode = layout5; break;
            case 6: referenceNode = layout6; break;
            default: return; // Game thiết kế tối đa 6 vũ khí
        }

        // Nếu tìm thấy cái Khuôn tương ứng
        if (referenceNode != null) {

            // getChildren() của Godot trả về một VariantArray chứa các Node con (Marker2D)
            VariantArray<Node> markers = referenceNode.getChildren();

            // Biện pháp an toàn: Đảm bảo số Marker khớp với số Vũ khí
            if (markers.size() != count) {
                return;
            }

            // Vòng lặp gán vị trí của từng Vũ khí = Vị trí của Marker
            for (int i = 0; i < count; i++) {

                Node2D weaponNode = (Node2D) weapons.get(i);
                Node2D markerNode = (Node2D) markers.get(i);

                // Gán vị trí Toàn cục (Global Position)
                weaponNode.setGlobalPosition(markerNode.getGlobalPosition());
            }
        }
    }
}