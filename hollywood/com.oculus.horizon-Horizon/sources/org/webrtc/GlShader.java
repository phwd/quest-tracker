package org.webrtc;

import X.AnonymousClass006;
import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.FloatBuffer;

public class GlShader {
    public static final String TAG = "GlShader";
    public int program;

    public int getAttribLocation(String str) {
        String str2;
        int i = this.program;
        if (i != -1) {
            int glGetAttribLocation = GLES20.glGetAttribLocation(i, str);
            if (glGetAttribLocation >= 0) {
                return glGetAttribLocation;
            }
            str2 = AnonymousClass006.A07("Could not locate '", str, "' in program");
        } else {
            str2 = "The program has been released";
        }
        throw new RuntimeException(str2);
    }

    public int getUniformLocation(String str) {
        String str2;
        int i = this.program;
        if (i != -1) {
            int glGetUniformLocation = GLES20.glGetUniformLocation(i, str);
            if (glGetUniformLocation >= 0) {
                return glGetUniformLocation;
            }
            str2 = AnonymousClass006.A07("Could not locate uniform '", str, "' in program");
        } else {
            str2 = "The program has been released";
        }
        throw new RuntimeException(str2);
    }

    public void release() {
        Logging.d(TAG, "Deleting shader.");
        int i = this.program;
        if (i != -1) {
            GLES20.glDeleteProgram(i);
            this.program = -1;
        }
    }

    public void setVertexAttribArray(String str, int i, FloatBuffer floatBuffer) {
        if (this.program != -1) {
            int attribLocation = getAttribLocation(str);
            GLES20.glEnableVertexAttribArray(attribLocation);
            GLES20.glVertexAttribPointer(attribLocation, i, 5126, false, 0, (Buffer) floatBuffer);
            GlUtil.checkNoGLES2Error("setVertexAttribArray");
            return;
        }
        throw new RuntimeException("The program has been released");
    }

    public void useProgram() {
        int i = this.program;
        if (i != -1) {
            GLES20.glUseProgram(i);
            GlUtil.checkNoGLES2Error("glUseProgram");
            return;
        }
        throw new RuntimeException("The program has been released");
    }

    public GlShader(String str, String str2) {
        String str3;
        int compileShader = compileShader(35633, str);
        int compileShader2 = compileShader(35632, str2);
        int glCreateProgram = GLES20.glCreateProgram();
        this.program = glCreateProgram;
        if (glCreateProgram != 0) {
            GLES20.glAttachShader(glCreateProgram, compileShader);
            GLES20.glAttachShader(this.program, compileShader2);
            GLES20.glLinkProgram(this.program);
            int[] iArr = {0};
            GLES20.glGetProgramiv(this.program, 35714, iArr, 0);
            if (iArr[0] == 1) {
                GLES20.glDeleteShader(compileShader);
                GLES20.glDeleteShader(compileShader2);
                GlUtil.checkNoGLES2Error("Creating GlShader");
                return;
            }
            Logging.e(TAG, AnonymousClass006.A05("Could not link program: ", GLES20.glGetProgramInfoLog(this.program)));
            str3 = GLES20.glGetProgramInfoLog(this.program);
        } else {
            str3 = AnonymousClass006.A01("glCreateProgram() failed. GLES20 error: ", GLES20.glGetError());
        }
        throw new RuntimeException(str3);
    }

    public static int compileShader(int i, String str) {
        String str2;
        int glCreateShader = GLES20.glCreateShader(i);
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            GLES20.glCompileShader(glCreateShader);
            int[] iArr = {0};
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            if (iArr[0] == 1) {
                GlUtil.checkNoGLES2Error("compileShader");
                return glCreateShader;
            }
            StringBuilder sb = new StringBuilder("Could not compile shader ");
            sb.append(i);
            sb.append(":");
            sb.append(GLES20.glGetShaderInfoLog(glCreateShader));
            Logging.e(TAG, sb.toString());
            str2 = GLES20.glGetShaderInfoLog(glCreateShader);
        } else {
            str2 = AnonymousClass006.A01("glCreateShader() failed. GLES20 error: ", GLES20.glGetError());
        }
        throw new RuntimeException(str2);
    }
}
