package org.webrtc.videoengine;

import X.AnonymousClass006;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Process;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.webrtc.Logging;

public class ViESurfaceRenderer implements SurfaceHolder.Callback {
    public static final String TAG = "WEBRTC";
    public Bitmap bitmap = null;
    public ByteBuffer byteBuffer = null;
    public float dstBottomScale = 1.0f;
    public float dstLeftScale = 0.0f;
    public Rect dstRect = new Rect();
    public float dstRightScale = 1.0f;
    public float dstTopScale = 0.0f;
    public Rect srcRect = new Rect();
    public SurfaceHolder surfaceHolder;

    private void changeDestRect(int i, int i2) {
        Rect rect = this.dstRect;
        rect.right = (int) (((float) rect.left) + (this.dstRightScale * ((float) i)));
        rect.bottom = (int) (((float) rect.top) + (this.dstBottomScale * ((float) i2)));
    }

    private void saveBitmapToJPEG(int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(String.format("/sdcard/render_%d.jpg", Long.valueOf(System.currentTimeMillis())));
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException | IOException unused) {
        }
    }

    public Bitmap CreateBitmap(int i, int i2) {
        Logging.d(TAG, AnonymousClass006.A03("CreateByteBitmap ", i, ":", i2));
        if (this.bitmap == null) {
            try {
                Process.setThreadPriority(-4);
            } catch (Exception unused) {
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
        this.bitmap = createBitmap;
        Rect rect = this.srcRect;
        rect.left = 0;
        rect.top = 0;
        rect.bottom = i2;
        rect.right = i;
        return createBitmap;
    }

    public ByteBuffer CreateByteBuffer(int i, int i2) {
        Logging.d(TAG, AnonymousClass006.A03("CreateByteBuffer ", i, ":", i2));
        if (this.bitmap == null) {
            this.bitmap = CreateBitmap(i, i2);
            this.byteBuffer = ByteBuffer.allocateDirect((i * i2) << 1);
        }
        return this.byteBuffer;
    }

    public void DrawBitmap() {
        Canvas lockCanvas;
        if (this.bitmap != null && (lockCanvas = this.surfaceHolder.lockCanvas()) != null) {
            lockCanvas.drawBitmap(this.bitmap, this.srcRect, this.dstRect, (Paint) null);
            this.surfaceHolder.unlockCanvasAndPost(lockCanvas);
        }
    }

    public void DrawByteBuffer() {
        ByteBuffer byteBuffer2 = this.byteBuffer;
        if (byteBuffer2 != null) {
            byteBuffer2.rewind();
            this.bitmap.copyPixelsFromBuffer(this.byteBuffer);
            DrawBitmap();
        }
    }

    public void SetCoordinates(float f, float f2, float f3, float f4) {
        StringBuilder sb = new StringBuilder("SetCoordinates ");
        sb.append(f);
        sb.append(",");
        sb.append(f2);
        sb.append(":");
        sb.append(f3);
        sb.append(",");
        sb.append(f4);
        Logging.d(TAG, sb.toString());
        this.dstLeftScale = f;
        this.dstTopScale = f2;
        this.dstRightScale = f3;
        this.dstBottomScale = f4;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder2, int i, int i2, int i3) {
        Logging.d(TAG, "ViESurfaceRender::surfaceChanged");
        changeDestRect(i2, i3);
        StringBuilder sb = new StringBuilder("ViESurfaceRender::surfaceChanged in_width:");
        sb.append(i2);
        sb.append(" in_height:");
        sb.append(i3);
        sb.append(" srcRect.left:");
        Rect rect = this.srcRect;
        sb.append(rect.left);
        sb.append(" srcRect.top:");
        sb.append(rect.top);
        sb.append(" srcRect.right:");
        sb.append(rect.right);
        sb.append(" srcRect.bottom:");
        sb.append(rect.bottom);
        sb.append(" dstRect.left:");
        Rect rect2 = this.dstRect;
        sb.append(rect2.left);
        sb.append(" dstRect.top:");
        sb.append(rect2.top);
        sb.append(" dstRect.right:");
        sb.append(rect2.right);
        sb.append(" dstRect.bottom:");
        sb.append(rect2.bottom);
        Logging.d(TAG, sb.toString());
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder2) {
        Canvas lockCanvas = this.surfaceHolder.lockCanvas();
        if (lockCanvas != null) {
            Rect surfaceFrame = this.surfaceHolder.getSurfaceFrame();
            if (surfaceFrame != null) {
                int i = surfaceFrame.right;
                int i2 = surfaceFrame.left;
                int i3 = surfaceFrame.bottom;
                int i4 = surfaceFrame.top;
                changeDestRect(i - i2, i3 - i4);
                StringBuilder sb = new StringBuilder("ViESurfaceRender::surfaceCreated dst.left:");
                sb.append(i2);
                sb.append(" dst.top:");
                sb.append(i4);
                sb.append(" dst.right:");
                sb.append(surfaceFrame.right);
                sb.append(" dst.bottom:");
                sb.append(surfaceFrame.bottom);
                sb.append(" srcRect.left:");
                Rect rect = this.srcRect;
                sb.append(rect.left);
                sb.append(" srcRect.top:");
                sb.append(rect.top);
                sb.append(" srcRect.right:");
                sb.append(rect.right);
                sb.append(" srcRect.bottom:");
                sb.append(rect.bottom);
                sb.append(" dstRect.left:");
                Rect rect2 = this.dstRect;
                sb.append(rect2.left);
                sb.append(" dstRect.top:");
                sb.append(rect2.top);
                sb.append(" dstRect.right:");
                sb.append(rect2.right);
                sb.append(" dstRect.bottom:");
                sb.append(rect2.bottom);
                Logging.d(TAG, sb.toString());
            }
            this.surfaceHolder.unlockCanvasAndPost(lockCanvas);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder2) {
        Logging.d(TAG, "ViESurfaceRenderer::surfaceDestroyed");
        this.bitmap = null;
        this.byteBuffer = null;
    }

    public ViESurfaceRenderer(SurfaceView surfaceView) {
        SurfaceHolder holder = surfaceView.getHolder();
        this.surfaceHolder = holder;
        if (holder != null) {
            holder.addCallback(this);
        }
    }
}
