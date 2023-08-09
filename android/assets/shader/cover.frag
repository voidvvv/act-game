#ifdef GL_ES
precision mediump float;
#endif

uniform vec4      coverColor;
uniform int        lightFlag;
uniform vec2      u_light_position;
uniform float    minRange;
uniform float    maxRange;
varying vec4 v_position;
varying vec4 v_color;

void main( out vec4 fragColor  ) {
    fragColor = coverColor;
    if(lightFlag>1){
        float s = smoothstep(minRange,maxRange,length(v_position.xy-u_light_position));
        fragColor*=s;
    }

}