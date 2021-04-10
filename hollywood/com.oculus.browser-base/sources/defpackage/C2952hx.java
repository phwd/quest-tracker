package defpackage;

import android.graphics.Bitmap;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: hx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2952hx {

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f10112a;
    public byte[] b;
    public BS0 c;
    public AtomicBoolean d = new AtomicBoolean();

    public C2952hx(Bitmap bitmap, BS0 bs0, boolean z) {
        this.f10112a = bitmap;
        this.c = bs0;
        bs0.b(new RunnableC2439ex(this, z));
    }

    public final void a() {
        if (!d()) {
            this.c.a(new RunnableC2781gx(this), 50);
            return;
        }
        Bitmap bitmap = this.f10112a;
        if (bitmap != null) {
            bitmap.recycle();
            this.f10112a = null;
        }
        this.b = null;
        e();
    }

    public void b() {
        this.c.b(new RunnableC1927bx(this));
    }

    public final void c() {
        if (!d()) {
            this.c.a(new RunnableC2610fx(this), 50);
            return;
        }
        Bitmap bitmap = this.f10112a;
        if (!(bitmap == null || this.b == null)) {
            bitmap.recycle();
            this.f10112a = null;
        }
        e();
    }

    public boolean d() {
        return this.d.compareAndSet(false, true);
    }

    public boolean e() {
        return this.d.compareAndSet(true, false);
    }

    public boolean equals(Object obj) {
        Bitmap bitmap;
        byte[] bArr;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C2952hx)) {
            return false;
        }
        C2952hx hxVar = (C2952hx) obj;
        byte[] bArr2 = this.b;
        if (bArr2 != null && (bArr = hxVar.b) != null) {
            return Arrays.equals(bArr2, bArr);
        }
        Bitmap bitmap2 = this.f10112a;
        if (bitmap2 == null || (bitmap = hxVar.f10112a) == null) {
            return false;
        }
        return bitmap2.equals(bitmap);
    }
}
