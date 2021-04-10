package defpackage;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: hg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2901hg {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f10092a = new byte[768];
    public final byte[] b;
    public int c;
    public int d;
    public int e;
    public boolean f;
    public boolean g;
    public int[] h = new int[256];
    public int i;
    public int j;
    public int k;

    public C2901hg(byte[] bArr) {
        this.b = bArr;
        C2730gg ggVar = new C2730gg(this, bArr, null);
        ggVar.skip((long) 0);
        try {
            a(ggVar);
            this.e = ggVar.getPosition();
        } catch (IOException unused) {
            this.g = true;
        }
        try {
            ggVar.close();
        } catch (IOException unused2) {
        }
    }

    public final void a(InputStream inputStream) {
        boolean z = true;
        int i2 = 0;
        if (!(((inputStream.read() == 71) && inputStream.read() == 73) && inputStream.read() == 70)) {
            this.g = true;
            return;
        }
        inputStream.skip(3);
        this.c = inputStream.read() | (inputStream.read() << 8);
        this.d = inputStream.read() | (inputStream.read() << 8);
        int read = inputStream.read();
        if ((read & 128) == 0) {
            z = false;
        }
        this.f = z;
        this.i = 2 << (read & 7);
        this.k = inputStream.read();
        inputStream.skip(1);
        if (this.f && !this.g) {
            int[] iArr = this.h;
            int i3 = this.i;
            byte[] bArr = f10092a;
            synchronized (bArr) {
                int i4 = i3 * 3;
                if (inputStream.read(bArr, 0, i4) >= i4) {
                    int i5 = 0;
                    while (i2 < i3) {
                        byte[] bArr2 = f10092a;
                        int i6 = i5 + 1;
                        int i7 = i6 + 1;
                        iArr[i2] = ((bArr2[i5] & 255) << 16) | -16777216 | ((bArr2[i6] & 255) << 8) | (bArr2[i7] & 255);
                        i2++;
                        i5 = i7 + 1;
                    }
                }
            }
            this.j = this.h[this.k];
        }
    }
}
