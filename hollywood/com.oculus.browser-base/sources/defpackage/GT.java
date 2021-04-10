package defpackage;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

/* renamed from: GT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GT {

    /* renamed from: a  reason: collision with root package name */
    public FT f8091a = new FT();
    public ByteBuffer b = null;
    public Bitmap c = null;

    public GT(AbstractC3689mE1 me1) {
    }

    public ByteBuffer a() {
        Bitmap bitmap = this.c;
        if (bitmap == null) {
            return this.b;
        }
        int width = bitmap.getWidth();
        int height = this.c.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        this.c.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((int) ((((float) Color.blue(iArr[i2])) * 0.114f) + (((float) Color.green(iArr[i2])) * 0.587f) + (((float) Color.red(iArr[i2])) * 0.299f)));
        }
        return ByteBuffer.wrap(bArr);
    }
}
