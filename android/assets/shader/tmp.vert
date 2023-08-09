attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;

varying vec4 v_position;
varying vec2 v_texCoord0;

varying vec4 v_color;
void main()
{
    gl_Position =  u_projTrans * a_position;
//    gl_Position.w = a_position.w;
//    gl_Position.z = -1.1;
    v_position = a_position;
    v_texCoord0 = a_texCoord0;
    v_color= a_color;
}