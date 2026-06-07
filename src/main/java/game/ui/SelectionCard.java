package game.ui;

import godot.annotation.RegisterClass;
import godot.annotation.RegisterFunction;
import godot.annotation.RegisterSignal;
import godot.core.Signal1;
import godot.api.Button;
import godot.api.Texture2D;

@RegisterClass
public class SelectionCard extends Button {

	public int playerIndex = -1;

	@RegisterSignal
	public Signal1<Integer> onCardSelected = Signal1.create(this, "onCardSelected");

	public SelectionCard() {
		super();
	}

	@RegisterFunction
	public void _on_pressed() {
		if (playerIndex >= 0) {
			onCardSelected.emit(playerIndex);
		}
	}

	@RegisterFunction
	public void setIconTexture(Texture2D texture) {
		this.set(new godot.core.StringName("icon"), texture);
	}
}
