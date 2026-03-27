package game.animation; // Lưu ý: Nếu bạn để file trong thư mục khác, hãy sửa lại tên package cho đúng nhé!

import godot.api.Line2D;
import godot.api.Node2D;
import godot.api.Timer;
import godot.core.Vector2;
import godot.core.PackedVector2Array;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.Export;
import godot.annotation.RegisterProperty;
import godot.global.GD;

import java.util.LinkedList;

@RegisterClass
public class Trail extends Line2D {

    // Ô chứa Nhân vật để cái bóng biết đường chạy theo
    @Export
    @RegisterProperty
    public Node2D player;

    // Chiều dài tối đa của cái bóng (25 điểm)
    @Export
    @RegisterProperty
    public int trailLength = 25;

    // Thời gian cái bóng tồn tại (1 giây)
    @Export
    @RegisterProperty
    public float trailDuration = 0.6f;

    private Timer trailTimer;

    private boolean isActive = false;

    // Dùng LinkedList của Java để thêm/xóa tọa độ nhanh
    private final LinkedList<Vector2> pointsList = new LinkedList<>();

    @RegisterFunction
    @Override
    public void _ready() {
        trailTimer = (Timer) getNode("%TrailTimer");

        // Lệnh cực kỳ quan trọng: Ép cái bóng nằm yên trên mặt đất, không bị dính cứng vào nhân vật
        setAsTopLevel(true);
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        // Nếu không bấm lướt, hoặc quên chưa gắn Player -> Bỏ qua, không làm gì cả
        if (!isActive || player == null) {
            return;
        }

        // 1. Cắm đinh mới: Lấy tọa độ hiện tại của nhân vật nhét vào cuối danh sách
        pointsList.add(player.getGlobalPosition());

        // 2. Nhổ đinh cũ: Nếu danh sách dài quá 25 điểm, xóa điểm cũ nhất ở ĐẦU danh sách đi
        if (pointsList.size() > trailLength) {
            pointsList.removeFirst();
        }

        // 3. Nối dây: Chép dữ liệu từ danh sách Java sang mảng của Godot để nó vẽ ra màn hình
        PackedVector2Array godotArray = new PackedVector2Array();
        for (Vector2 point : pointsList) {
            godotArray.append(point);
        }
        setPoints(godotArray);
    }

    // Hàm này sẽ được Player gọi khi bấm nút Dash
    public void startTrail() {
        isActive = true;
        clearPoints();
        pointsList.clear();

        if (trailTimer != null) {
            trailTimer.start(trailDuration);
            GD.print("Đã bật đồng hồ cái đuôi chạy " + trailDuration + " giây!"); // THÊM DÒNG NÀY
        } else {
            GD.print("CẢNH BÁO: Không tìm thấy TrailTimer!"); // VÀ DÒNG NÀY
        }
    }
    // Hàm này chạy khi đồng hồ đếm ngược 1 giây kết thúc
    @RegisterFunction
    public void _on_trail_timer_timeout() {
        isActive = false; // Ngừng cắm đinh
        clearPoints();    // Xóa hình vẽ
        pointsList.clear(); // Dọn rác bộ nhớ
    }
}