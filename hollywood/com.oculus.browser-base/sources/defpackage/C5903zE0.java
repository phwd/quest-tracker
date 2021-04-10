package defpackage;

/* renamed from: zE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5903zE0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object[] f11736a;
    public int b;

    public C5903zE0(int i) {
        if (i > 0) {
            this.f11736a = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    public Object a() {
        int i = this.b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f11736a;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.b = i - 1;
        return obj;
    }

    public boolean b(Object obj) {
        int i;
        boolean z;
        int i2 = 0;
        while (true) {
            i = this.b;
            if (i2 >= i) {
                z = false;
                break;
            } else if (this.f11736a[i2] == obj) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (!z) {
            Object[] objArr = this.f11736a;
            if (i >= objArr.length) {
                return false;
            }
            objArr[i] = obj;
            this.b = i + 1;
            return true;
        }
        throw new IllegalStateException("Already in the pool!");
    }
}
