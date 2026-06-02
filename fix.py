import re

with open('scenes/ui/selection_panel/selection_panel.tscn', 'r', encoding='utf-8') as f:
    content = f.read()

# Replace the root node
root_decl = '''[sub_resource type="StyleBoxFlat" id="StyleBoxFlat_bg"]
bg_color = Color(0.20392157, 0.20392157, 0.20392157, 1)

[node name="SelectionPanel" type="Panel"]'''

content = content.replace('[node name="SelectionPanel" type="MarginContainer"]', root_decl)

# Remove the margin properties from the root node
content = content.replace('theme_override_constants/margin_left = 32\n', '')
content = content.replace('theme_override_constants/margin_top = 32\n', '')
content = content.replace('theme_override_constants/margin_right = 32\n', '')
content = content.replace('theme_override_constants/margin_bottom = 32\n', '')

# Insert MarginContainer and theme_override_styles/panel for root
margin_container = '''theme_override_styles/panel = SubResource("StyleBoxFlat_bg")

[node name="MarginContainer" type="MarginContainer" parent="."]
layout_mode = 1
anchors_preset = 15
anchor_right = 1.0
anchor_bottom = 1.0
grow_horizontal = 2
grow_vertical = 2
theme_override_constants/margin_left = 32
theme_override_constants/margin_top = 32
theme_override_constants/margin_right = 32
theme_override_constants/margin_bottom = 32

[node name="VBoxContainer" type="VBoxContainer" parent="MarginContainer"]'''

content = content.replace('[node name="VBoxContainer" type="VBoxContainer" parent="."]', margin_container)

# Now prepend MarginContainer/ to all parent paths that start with VBoxContainer
content = re.sub(r'parent="VBoxContainer', 'parent="MarginContainer/VBoxContainer', content)

with open('scenes/ui/selection_panel/selection_panel.tscn', 'w', encoding='utf-8') as f:
    f.write(content)
