package org.webrtc;

import X.AnonymousClass006;
import android.opengl.GLES20;

public class GlTextureFrameBuffer {
    public final int frameBufferId;
    public int height;
    public final int pixelFormat;
    public final int textureId;
    public int width;

    public void release() {
        GLES20.glDeleteTextures(1, new int[]{this.textureId}, 0);
        GLES20.glDeleteFramebuffers(1, new int[]{this.frameBufferId}, 0);
        this.width = 0;
        this.height = 0;
    }

    public void setSize(int i, int i2) {
        if (i == 0 || i2 == 0) {
            throw new IllegalArgumentException(AnonymousClass006.A03("Invalid size: ", i, "x", i2));
        } else if (i != this.width || i2 != this.height) {
            this.width = i;
            this.height = i2;
            GLES20.glBindFramebuffer(36160, this.frameBufferId);
            GlUtil.checkNoGLES2Error("glBindFramebuffer");
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.textureId);
            int i3 = this.pixelFormat;
            GLES20.glTexImage2D(3553, 0, i3, i, i2, 0, i3, 5121, null);
            int glCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
            if (glCheckFramebufferStatus == 36053) {
                GLES20.glBindFramebuffer(36160, 0);
                GLES20.glBindTexture(3553, 0);
                return;
            }
            throw new IllegalStateException(AnonymousClass006.A01("Framebuffer not complete, status: ", glCheckFramebufferStatus));
        }
    }

    public GlTextureFrameBuffer(int i) {
        switch (i) {
            case 6407:
            case 6408:
            case 6409:
                this.pixelFormat = i;
                this.textureId = GlUtil.generateTexture(3553);
                this.width = 0;
                this.height = 0;
                int[] iArr = new int[1];
                GLES20.glGenFramebuffers(1, iArr, 0);
                int i2 = iArr[0];
                this.frameBufferId = i2;
                GLES20.glBindFramebuffer(36160, i2);
                GlUtil.checkNoGLES2Error("Generate framebuffer");
                GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.textureId, 0);
                GlUtil.checkNoGLES2Error("Attach texture to framebuffer");
                GLES20.glBindFramebuffer(36160, 0);
                return;
            default:
                throw new IllegalArgumentException(AnonymousClass006.A01("Invalid pixel format: ", i));
        }
    }

    public int getFrameBufferId() {
        return this.frameBufferId;
    }

    public int getHeight() {
        return this.height;
    }

    public int getTextureId() {
        return this.textureId;
    }

    public int getWidth() {
        return this.width;
    }
}
