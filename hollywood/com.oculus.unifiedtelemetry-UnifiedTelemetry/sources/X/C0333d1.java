package X;

import com.facebook.acra.util.HttpRequest;

/* renamed from: X.d1  reason: case insensitive filesystem */
public final class C0333d1 {
    public final int A00;
    public final int A01;
    public final C0333d1[] A02;

    public C0333d1() {
        this.A02 = new C0333d1[HttpRequest.CHAR_ARRAY_BUFFER_SIZE];
        this.A00 = 0;
        this.A01 = 0;
    }

    public C0333d1(int i, int i2) {
        this.A02 = null;
        this.A00 = i;
        int i3 = i2 & 7;
        this.A01 = i3 == 0 ? 8 : i3;
    }
}
