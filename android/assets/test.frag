#ifdef GL_ES
precision mediump float;
#endif

uniform vec3      iResolution;
uniform float     iTime;

uniform sampler2D u_texture;
varying vec2 v_texCoord0;
varying vec2 v_uv0;
uniform int       phase;

varying vec4 v_gl_position;
varying vec4 v_position;
//vec4 texColor = texture2D(u_texture, v_texCoord0);


//https://iquilezles.org/articles/palettes/
vec3 palette( float t ) {
    vec3 a = vec3(0.5, 0.5, 0.5);
    vec3 b = vec3(0.5, 0.5, 0.5);
    vec3 c = vec3(1.0, 1.0, 1.0);
    vec3 d = vec3(0.263,0.416,0.557);

    return a + b*cos( 6.28318*(c*t+d) );
}

void main( out vec4 fragColor  ) {
    vec2 uv = v_texCoord0-0.5;
    //    vec2 uv = v_gl_position.xy/iResolution.xy;
    //vec3(0.0)
    float l = length(v_position.xy);
    vec4 texColor = texture2D(u_texture, vec2(v_texCoord0.x,1-v_texCoord0.y));

    vec2 uv0 = uv;
    vec3 finalColor = texColor.xyz;

    vec4 darkColor = vec4(1,1,1,1);

    for (float i = 0.0; i < 4.0; i++) {
        uv = fract(uv * 1.5) - 0.5;

        float d = length(uv) * exp(-length(uv0));

        vec3 col = palette(length(uv0) + i*.4 + iTime*.4);

        d = sin(d*8. + iTime)/8.;
        d = abs(d);

        d = pow(0.01 / d, 1.2);

        finalColor += col * d;
    }

    darkColor = darkColor*(1-smoothstep(3.21,8.95,l));

    //    darkColor.z = 0.01;


    fragColor = vec4(finalColor.xyz, 1);
}
