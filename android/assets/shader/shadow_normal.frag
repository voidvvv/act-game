#ifdef GL_ES
precision lowp  float;
#endif

uniform vec4      coverColor;
uniform sampler2D u_texture;

varying vec4 v_color;
varying vec2 v_texCoord0;
varying vec4 v_position;
void main(  ) {
//    vec4 fragColor = vec4(1,1,1,1);
    //    fragColor = texture2D(u_texture, v_texCoord0);
//    vec4 textColor = v_color*texture2D(u_texture, v_texCoord0);
//    fragColor = vec4(v_color.rgb,v_color.a*textColor.a);
//    fragColor = v_color;

    vec2 tmp_texCoord0=v_texCoord0 - 0.5;
    float l = length(tmp_texCoord0);
    float i1 = 1.;
    float i2 = 2.;
    gl_FragColor=v_color*(i1-i2*l);

}