attribute vec4 a_position;
attribute vec2 a_texCoord0;

attribute vec2 a_uv0;
attribute vec4 a_color;

uniform mat4 u_projTrans;

varying vec2 v_texCoord0;
varying vec2 v_uv0;

varying vec4 v_gl_position;

varying vec4 v_position;

varying vec4 v_color;

void main() {
    gl_Position = u_projTrans  * a_position;
    v_texCoord0 = a_texCoord0;
    v_color = a_color;
    v_gl_position = u_projTrans  * a_position;
    v_uv0 = a_uv0;
    v_position = a_position;
}