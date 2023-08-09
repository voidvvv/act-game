#ifdef GL_ES
precision mediump float;
#endif

uniform float     iTime;

uniform sampler2D u_texture;
varying vec2 v_texCoord0;
varying vec2 v_uv0;
uniform int       phase;

varying vec4 v_gl_position;
varying vec4 v_position;

uniform vec4 u_light_color01;
uniform vec2 u_light_color_position01;

varying vec4 v_color;
//vec4 texColor = texture2D(u_texture, v_texCoord0);


//https://iquilezles.org/articles/palettes/
vec4 palette( float t ) {
    vec4 a = vec4(0.5, 0.5, 0.5,0);
    vec4 b = vec4(0.5, 0.5, 0.5,0);
    vec4 c = vec4(1.0, 1.0, 1.0,0);
    vec4 d = vec4(0.263,0.416,0.557,0);

    return a + b*cos( 6.28318*(c*t+d) );
}

void main( out vec4 fragColor  ) {
    vec2 uv = v_texCoord0;
    //    vec2 uv = v_gl_position.xy/iResolution.xy;
    //vec3(0.0)
    float l = length(abs(v_position.xy-u_light_color_position01));
    vec4 texColor = texture2D(u_texture, v_texCoord0);

    vec2 uv0 = uv;
    vec4 finalColor = v_color*texColor;

    vec4 darkColor = u_light_color01;

    for (float i = 0.0; i < 4.0; i++) {
        uv = fract(uv * 1.5) - 0.5;

        float d = length(uv) * exp(-length(uv0));

        vec4 col = palette(length(uv0) + i*.4 + iTime*.4);

        d = sin(d*8. + iTime)/8.;
        d = abs(d);

        d = pow(0.01 / d, 1.2);

        finalColor += col * d;
    }

    darkColor = darkColor*(1-smoothstep(20,50,l));
    darkColor.w = 1.;
    vec4 realDark = vec4(0.0);
    realDark += (1-smoothstep(20,50,l));
    //    darkColor.z = 0.01;


    fragColor = finalColor * darkColor * vec4(realDark.xyz,1);
}
