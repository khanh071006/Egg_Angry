package game.ui;

import game.autoloads.Global;
import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.api.BoxContainer;
import godot.api.Label;
import godot.global.GD;

@RegisterClass
public class CoinsBag extends BoxContainer {
    
    private Label coinsLabel;

    @RegisterFunction
    @Override
    public void _ready() {
        // Lấy tham chiếu đến Label tên là "Coins" (như trong video)
        coinsLabel = (Label) getNodeOrNull("Coins");
        if (coinsLabel == null) {
            GD.printErr("CoinsBag: Không tìm thấy node Label có tên 'Coins'!");
        }
    }

    @RegisterFunction
    @Override
    public void _process(double delta) {
        if (coinsLabel != null) {
            // Cập nhật text liên tục dựa trên số xu thực tế
            coinsLabel.setText(String.valueOf(Global.coins));
        }
    }
}
