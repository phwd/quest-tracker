package com.oculus.video;

import android.opengl.GLES30;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* access modifiers changed from: package-private */
public class OESTextureCopier {
    private static final int FLOAT_SIZE_BYTES = 4;
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    private static final String TAG = "OESTextureCopier";
    private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
    private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 20;
    private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
    private static final String VERTEX_SHADER = "attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = aPosition;\n  vTextureCoord = vec2(aTextureCoord.x, 1.0 - aTextureCoord.y);\n}\n";
    private int mFrameBuffer = 0;
    private long mLastFrameTimestampNs = -1;
    private int mOesTexture = 0;
    private int mProgram;
    private int mTargetTexture = 0;
    private FloatBuffer mTriangleVertices = ByteBuffer.allocateDirect(this.mTriangleVerticesData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    private final float[] mTriangleVerticesData = {-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, -1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f};
    private int mUnitSquareArray = 0;
    private int[] mUnitSquareBuffers;
    private int maPositionHandle;
    private int maTextureHandle;

    public OESTextureCopier() {
        this.mTriangleVertices.put(this.mTriangleVerticesData).position(0);
        setupCopyProgram();
        createOesTexture();
        createUnitSquare();
    }

    public int getOesTexture() {
        return this.mOesTexture;
    }

    private void createFrameBuffer() {
        releaseFrameBuffer();
        if (this.mTargetTexture != 0) {
            int[] iArr = new int[1];
            GLES30.glGenFramebuffers(1, iArr, 0);
            this.mFrameBuffer = iArr[0];
            GLES30.glBindFramebuffer(36160, this.mFrameBuffer);
            checkGlError("glBindFramebuffer mFrameBuffer");
            GLES30.glFramebufferTexture2D(36160, 36064, 3553, this.mTargetTexture, 0);
            checkGlError("glFramebufferTexture2D mTargetTexture");
            GLES30.glBindFramebuffer(36160, 0);
        }
    }

    private void createUnitSquare() {
        int[] iArr = new int[1];
        GLES30.glGenVertexArrays(1, iArr, 0);
        this.mUnitSquareArray = iArr[0];
        GLES30.glBindVertexArray(this.mUnitSquareArray);
        checkGlError("glBindVertexArray mUnitSquareArray");
        int[] iArr2 = new int[2];
        GLES30.glGenBuffers(2, iArr2, 0);
        this.mUnitSquareBuffers = iArr2;
        GLES30.glBindBuffer(34962, iArr2[0]);
        checkGlError("glBindBuffer buffers[0]");
        this.mTriangleVertices.position(0);
        GLES30.glBufferData(34962, this.mTriangleVertices.remaining() * 4, this.mTriangleVertices, 35044);
        checkGlError("glBufferData buffers[0]");
        GLES30.glVertexAttribPointer(this.maPositionHandle, 3, 5126, false, 20, 0);
        checkGlError("glVertexAttribPointer maPosition");
        GLES30.glEnableVertexAttribArray(this.maPositionHandle);
        checkGlError("glEnableVertexAttribArray maPositionHandle");
        GLES30.glBindBuffer(34962, iArr2[1]);
        checkGlError("glBindBuffer buffers[1]");
        this.mTriangleVertices.position(3);
        GLES30.glBufferData(34962, this.mTriangleVertices.remaining() * 4, this.mTriangleVertices, 35044);
        checkGlError("glBufferData buffers[1]");
        GLES30.glVertexAttribPointer(this.maTextureHandle, 2, 5126, false, 20, 0);
        checkGlError("glVertexAttribPointer maTextureHandle");
        GLES30.glEnableVertexAttribArray(this.maTextureHandle);
        checkGlError("glEnableVertexAttribArray maTextureHandle");
        GLES30.glBindBuffer(34962, 0);
        GLES30.glBindVertexArray(0);
    }

    public void copyTexture(int i, long j, int i2, int i3, boolean z) {
        if (i != 0) {
            if (i != this.mTargetTexture) {
                this.mTargetTexture = i;
                createFrameBuffer();
                this.mLastFrameTimestampNs = -1;
            }
            if (this.mLastFrameTimestampNs != j) {
                this.mLastFrameTimestampNs = j;
                if (this.mFrameBuffer != 0) {
                    checkGlError("onDrawFrame start");
                    GLES30.glBindFramebuffer(36160, this.mFrameBuffer);
                    checkGlError("glBindFramebuffer mFrameBuffer");
                    int[] iArr = {36064};
                    GLES30.glInvalidateFramebuffer(36160, iArr.length, iArr, 0);
                    checkGlError("glInvalidateFramebuffer mFrameBuffer");
                    GLES30.glViewport(0, 0, i2, i3);
                    GLES30.glScissor(0, 0, i2, i3);
                    GLES30.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
                    GLES30.glClear(16384);
                    GLES30.glActiveTexture(33984);
                    GLES30.glBindTexture(36197, this.mOesTexture);
                    checkGlError("glBindTexture mOesTexture");
                    GLES30.glUseProgram(this.mProgram);
                    checkGlError("glUseProgram");
                    GLES30.glBindVertexArray(this.mUnitSquareArray);
                    checkGlError("glBindVertexArray mUnitSquareArray");
                    GLES30.glDrawArrays(4, 0, 6);
                    checkGlError("glDrawArrays");
                    GLES30.glBindVertexArray(0);
                    GLES30.glActiveTexture(33984);
                    GLES30.glBindTexture(36197, 0);
                    GLES30.glBindFramebuffer(36160, 0);
                    GLES30.glUseProgram(0);
                    if (z) {
                        GLES30.glActiveTexture(33984);
                        GLES30.glBindTexture(3553, this.mTargetTexture);
                        GLES30.glGenerateMipmap(3553);
                        GLES30.glBindTexture(3553, 0);
                    }
                    GLES30.glFlush();
                }
            }
        }
    }

    private void setupCopyProgram() {
        this.mProgram = createProgram(VERTEX_SHADER, FRAGMENT_SHADER);
        int i = this.mProgram;
        if (i != 0) {
            this.maPositionHandle = GLES30.glGetAttribLocation(i, "aPosition");
            checkLocation(this.maPositionHandle, "aPosition");
            this.maTextureHandle = GLES30.glGetAttribLocation(this.mProgram, "aTextureCoord");
            checkLocation(this.maTextureHandle, "aTextureCoord");
            return;
        }
        throw new RuntimeException("failed creating program");
    }

    private void createOesTexture() {
        int[] iArr = new int[1];
        GLES30.glGenTextures(1, iArr, 0);
        this.mOesTexture = iArr[0];
        GLES30.glBindTexture(36197, this.mOesTexture);
        checkGlError("glBindTexture mOesTexture");
        GLES30.glTexParameterf(36197, 10241, 9729.0f);
        GLES30.glTexParameterf(36197, 10240, 9729.0f);
        GLES30.glTexParameteri(36197, 10242, 33071);
        GLES30.glTexParameteri(36197, 10243, 33071);
        checkGlError("glTexParameter");
        GLES30.glBindTexture(36197, 0);
    }

    private int loadShader(int i, String str) {
        int glCreateShader = GLES30.glCreateShader(i);
        checkGlError("glCreateShader type=" + i);
        GLES30.glShaderSource(glCreateShader, str);
        GLES30.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES30.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        String str2 = TAG;
        Log.e(str2, "Could not compile shader " + i + ":");
        String str3 = TAG;
        Log.e(str3, " " + GLES30.glGetShaderInfoLog(glCreateShader));
        GLES30.glDeleteShader(glCreateShader);
        return 0;
    }

    private int createProgram(String str, String str2) {
        int loadShader;
        int loadShader2 = loadShader(35633, str);
        if (loadShader2 == 0 || (loadShader = loadShader(35632, str2)) == 0) {
            return 0;
        }
        int glCreateProgram = GLES30.glCreateProgram();
        if (glCreateProgram == 0) {
            Log.e(TAG, "Could not create program");
        }
        GLES30.glAttachShader(glCreateProgram, loadShader2);
        checkGlError("glAttachShader");
        GLES30.glAttachShader(glCreateProgram, loadShader);
        checkGlError("glAttachShader");
        GLES30.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES30.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] == 1) {
            return glCreateProgram;
        }
        Log.e(TAG, "Could not link program: ");
        Log.e(TAG, GLES30.glGetProgramInfoLog(glCreateProgram));
        GLES30.glDeleteProgram(glCreateProgram);
        return 0;
    }

    private void releaseFrameBuffer() {
        int i = this.mFrameBuffer;
        if (i != 0) {
            GLES30.glDeleteFramebuffers(1, new int[]{i}, 0);
            this.mFrameBuffer = 0;
        }
    }

    private void releaseOesTexture() {
        int i = this.mOesTexture;
        if (i != 0) {
            GLES30.glDeleteTextures(1, new int[]{i}, 0);
            this.mOesTexture = 0;
        }
    }

    private void releaseUnitSquare() {
        int i = this.mUnitSquareArray;
        if (i != 0) {
            GLES30.glDeleteVertexArrays(1, new int[]{i}, 0);
            this.mOesTexture = 0;
        }
        int[] iArr = this.mUnitSquareBuffers;
        if (iArr != null) {
            GLES30.glDeleteBuffers(iArr.length, iArr, 0);
            this.mUnitSquareBuffers = null;
        }
    }

    public void release() {
        releaseFrameBuffer();
        releaseOesTexture();
        releaseUnitSquare();
        GLES30.glDeleteProgram(this.mProgram);
    }

    private void checkGlError(String str) {
        int glGetError = GLES30.glGetError();
        if (glGetError != 0) {
            String str2 = TAG;
            Log.e(str2, str + ": glError " + glGetError);
            throw new RuntimeException(str + ": glError " + glGetError);
        }
    }

    private void checkLocation(int i, String str) {
        if (i < 0) {
            throw new RuntimeException("Unable to locate '" + str + "' in program");
        }
    }
}
