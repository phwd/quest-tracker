package defpackage;

import android.graphics.Bitmap;
import java.util.Objects;

/* renamed from: z21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5868z21 {

    /* renamed from: a  reason: collision with root package name */
    public Integer f11717a;
    public int b;
    public Bitmap c;

    public C5868z21(String str, Bitmap bitmap, int i) {
        this.c = bitmap;
        this.b = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5868z21)) {
            return false;
        }
        C5868z21 z21 = (C5868z21) obj;
        if (this.b != z21.b || !Objects.equals(this.f11717a, z21.f11717a)) {
            return false;
        }
        Bitmap bitmap = this.c;
        if (bitmap == null) {
            return true;
        }
        if (bitmap == z21.c) {
            return true;
        }
        return false;
    }

    public C5868z21(int i, int i2) {
        this.f11717a = Integer.valueOf(i);
        this.b = i2;
    }
}
