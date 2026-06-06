shader_type canvas_item;

uniform vec4 tint_color : source_color = vec4(0.0, 0.5, 1.0, 0.6);

void fragment() {
    vec4 color = texture(TEXTURE, UV);
    if (color.a > 0.0) {
        color.rgb = mix(color.rgb, tint_color.rgb, tint_color.a);
    }
    COLOR = color;
}
