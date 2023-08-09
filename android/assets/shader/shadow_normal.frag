#ifdef GL_ES
precision mediump float;
#endif

uniform vec4      coverColor;
uniform sampler2D u_texture;

varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec4 v_position;
void main( out vec4 fragColor  ) {

//    fragColor = texture2D(u_texture, v_texCoord0);
//    vec4 textColor = v_color*texture2D(u_texture, v_texCoord0);
//    fragColor = vec4(v_color.rgb,v_color.a*textColor.a);
//    fragColor = v_color;

    v_texCoord0-=0.5;
    fragColor=v_color*(1-2*length(v_texCoord0));

}