package defpackage;

import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.Rect;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: F11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F11 implements AbstractC3197jM0 {
    public Bitmap F;
    public final C3783mp0 G;
    public final Rect H;

    public F11(Bitmap bitmap) {
        this.F = bitmap;
        C3783mp0 mp0 = null;
        if (bitmap != null) {
            try {
                byte[] ninePatchChunk = bitmap.getNinePatchChunk();
                if (ninePatchChunk != null) {
                    if (NinePatch.isNinePatchChunk(ninePatchChunk)) {
                        ByteBuffer order = ByteBuffer.wrap(ninePatchChunk).order(ByteOrder.nativeOrder());
                        if (order.get() != 0) {
                            int i = order.get();
                            if (i != 0) {
                                if ((i & 1) == 0) {
                                    int i2 = order.get();
                                    if (i2 != 0) {
                                        if ((i2 & 1) == 0) {
                                            order.get();
                                            order.getInt();
                                            order.getInt();
                                            Rect rect = new Rect();
                                            rect.left = order.getInt();
                                            rect.right = order.getInt();
                                            rect.top = order.getInt();
                                            rect.bottom = order.getInt();
                                            order.getInt();
                                            int[] iArr = new int[i];
                                            for (int i3 = 0; i3 < i; i3++) {
                                                iArr[i3] = order.getInt();
                                            }
                                            int[] iArr2 = new int[i2];
                                            for (int i4 = 0; i4 < i2; i4++) {
                                                iArr2[i4] = order.getInt();
                                            }
                                            mp0 = new C3783mp0(bitmap.getWidth(), bitmap.getHeight(), rect, iArr, iArr2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (BufferUnderflowException unused) {
            }
        }
        this.G = mp0;
        this.H = new Rect(0, 0, this.F.getWidth(), this.F.getHeight());
    }

    @Override // defpackage.AbstractC3197jM0
    public Rect a() {
        return this.H;
    }

    @Override // defpackage.AbstractC3197jM0
    public C3783mp0 b() {
        return this.G;
    }

    @Override // defpackage.AbstractC3197jM0
    public Bitmap c() {
        Bitmap bitmap = this.F;
        this.F = null;
        return bitmap;
    }

    @Override // defpackage.AbstractC3197jM0
    public long d() {
        return AbstractC4052oM0.a(this.G);
    }
}
