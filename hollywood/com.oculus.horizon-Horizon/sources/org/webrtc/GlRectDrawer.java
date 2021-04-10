package org.webrtc;

import X.AnonymousClass006;
import android.opengl.GLES20;
import java.nio.FloatBuffer;
import java.util.IdentityHashMap;
import java.util.Map;
import org.webrtc.RendererCommon;

public class GlRectDrawer implements RendererCommon.GlDrawer {
    public static final FloatBuffer FULL_RECTANGLE_BUF = GlUtil.createFloatBuffer(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
    public static final FloatBuffer FULL_RECTANGLE_TEX_BUF = GlUtil.createFloatBuffer(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    public static final String OES_FRAGMENT_SHADER_STRING = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 interp_tc;\n\nuniform samplerExternalOES oes_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(oes_tex, interp_tc);\n}\n";
    public static final String RGB_FRAGMENT_SHADER_STRING = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D rgb_tex;\n\nvoid main() {\n  gl_FragColor = texture2D(rgb_tex, interp_tc);\n}\n";
    public static final String VERTEX_SHADER_STRING = "varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n";
    public static final String YUV_FRAGMENT_SHADER_STRING = "precision mediump float;\nvarying vec2 interp_tc;\n\nuniform sampler2D y_tex;\nuniform sampler2D u_tex;\nuniform sampler2D v_tex;\n\nvoid main() {\n  float y = texture2D(y_tex, interp_tc).r;\n  float u = texture2D(u_tex, interp_tc).r - 0.5;\n  float v = texture2D(v_tex, interp_tc).r - 0.5;\n  gl_FragColor = vec4(y + 1.403 * v,                       y - 0.344 * u - 0.714 * v,                       y + 1.77 * u, 1);\n}\n";
    public final Map<String, Shader> shaders = new IdentityHashMap();

    public static class Shader {
        public final GlShader glShader;
        public final int texMatrixLocation;

        public Shader(String str) {
            GlShader glShader2 = new GlShader("varying vec2 interp_tc;\nattribute vec4 in_pos;\nattribute vec4 in_tc;\n\nuniform mat4 texMatrix;\n\nvoid main() {\n    gl_Position = in_pos;\n    interp_tc = (texMatrix * in_tc).xy;\n}\n", str);
            this.glShader = glShader2;
            this.texMatrixLocation = glShader2.getUniformLocation("texMatrix");
        }
    }

    private void prepareShader(String str, float[] fArr) {
        Shader shader;
        GlShader glShader;
        String str2;
        if (this.shaders.containsKey(str)) {
            shader = this.shaders.get(str);
        } else {
            shader = new Shader(str);
            this.shaders.put(str, shader);
            shader.glShader.useProgram();
            if (str == YUV_FRAGMENT_SHADER_STRING) {
                GLES20.glUniform1i(shader.glShader.getUniformLocation("y_tex"), 0);
                GLES20.glUniform1i(shader.glShader.getUniformLocation("u_tex"), 1);
                GLES20.glUniform1i(shader.glShader.getUniformLocation("v_tex"), 2);
            } else {
                if (str == RGB_FRAGMENT_SHADER_STRING) {
                    glShader = shader.glShader;
                    str2 = "rgb_tex";
                } else if (str == OES_FRAGMENT_SHADER_STRING) {
                    glShader = shader.glShader;
                    str2 = "oes_tex";
                } else {
                    throw new IllegalStateException(AnonymousClass006.A05("Unknown fragment shader: ", str));
                }
                GLES20.glUniform1i(glShader.getUniformLocation(str2), 0);
            }
            GlUtil.checkNoGLES2Error("Initialize fragment shader uniform values.");
            shader.glShader.setVertexAttribArray("in_pos", 2, FULL_RECTANGLE_BUF);
            shader.glShader.setVertexAttribArray("in_tc", 2, FULL_RECTANGLE_TEX_BUF);
        }
        shader.glShader.useProgram();
        GLES20.glUniformMatrix4fv(shader.texMatrixLocation, 1, false, fArr, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawOes(int i, float[] fArr, int i2, int i3, int i4, int i5) {
        prepareShader(OES_FRAGMENT_SHADER_STRING, fArr);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, i);
        drawRectangle(i2, i3, i4, i5);
        GLES20.glBindTexture(36197, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawRgb(int i, float[] fArr, int i2, int i3, int i4, int i5) {
        prepareShader(RGB_FRAGMENT_SHADER_STRING, fArr);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        drawRectangle(i2, i3, i4, i5);
        GLES20.glBindTexture(3553, 0);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void drawYuv(int[] iArr, float[] fArr, int i, int i2, int i3, int i4) {
        prepareShader(YUV_FRAGMENT_SHADER_STRING, fArr);
        for (int i5 = 0; i5 < 3; i5++) {
            GLES20.glActiveTexture(33984 + i5);
            GLES20.glBindTexture(3553, iArr[i5]);
        }
        drawRectangle(i, i2, i3, i4);
        int i6 = 0;
        do {
            GLES20.glActiveTexture(i6 + 33984);
            GLES20.glBindTexture(3553, 0);
            i6++;
        } while (i6 < 3);
    }

    @Override // org.webrtc.RendererCommon.GlDrawer
    public void release() {
        for (Shader shader : this.shaders.values()) {
            shader.glShader.release();
        }
        this.shaders.clear();
    }

    private void drawRectangle(int i, int i2, int i3, int i4) {
        GLES20.glViewport(i, i2, i3, i4);
        GLES20.glDrawArrays(5, 0, 4);
    }
}
