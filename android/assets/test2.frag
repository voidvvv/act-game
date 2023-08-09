#ifdef GL_ES
precision mediump float;
#endif

uniform vec3      iResolution;
uniform float     iTime;
uniform float     iTimeDelta;
uniform float     iFrameRate;
uniform int       iFrame;
uniform float     iChannelTime[4];
uniform vec3      iChannelResolution[4];
uniform vec4      iMouse;
uniform vec4      iDate;

varying vec2 v_texCoord0;



void main( out vec4 fragColor  ) {
    vec2 uv = v_texCoord0-0.5;
//    vec2 uv = v_gl_position.xy/iResolution.xy;
//vec3(0.0)
//    step()
//    float f = smoothstep(5, 7, length(uv));

    fragColor = vec4(0,0,0,0.89);
}
