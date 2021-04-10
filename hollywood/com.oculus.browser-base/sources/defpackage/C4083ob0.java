package defpackage;

/* renamed from: ob0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4083ob0 implements Cloneable {
    public static final Object F = new Object();
    public boolean G = false;
    public long[] H;
    public Object[] I;

    /* renamed from: J  reason: collision with root package name */
    public int f10560J;

    public C4083ob0() {
        int f = AbstractC0179Cy.f(10);
        this.H = new long[f];
        this.I = new Object[f];
    }

    public void a() {
        int i = this.f10560J;
        Object[] objArr = this.I;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f10560J = 0;
        this.G = false;
    }

    /* renamed from: b */
    public C4083ob0 clone() {
        try {
            C4083ob0 ob0 = (C4083ob0) super.clone();
            ob0.H = (long[]) this.H.clone();
            ob0.I = (Object[]) this.I.clone();
            return ob0;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public boolean c(long j) {
        if (this.G) {
            d();
        }
        return AbstractC0179Cy.b(this.H, this.f10560J, j) >= 0;
    }

    public final void d() {
        int i = this.f10560J;
        long[] jArr = this.H;
        Object[] objArr = this.I;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != F) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.G = false;
        this.f10560J = i2;
    }

    public Object e(long j) {
        return f(j, null);
    }

    public Object f(long j, Object obj) {
        int b = AbstractC0179Cy.b(this.H, this.f10560J, j);
        if (b >= 0) {
            Object[] objArr = this.I;
            if (objArr[b] != F) {
                return objArr[b];
            }
        }
        return obj;
    }

    public boolean g() {
        return k() == 0;
    }

    public long h(int i) {
        if (this.G) {
            d();
        }
        return this.H[i];
    }

    public void i(long j, Object obj) {
        int b = AbstractC0179Cy.b(this.H, this.f10560J, j);
        if (b >= 0) {
            this.I[b] = obj;
            return;
        }
        int i = ~b;
        int i2 = this.f10560J;
        if (i < i2) {
            Object[] objArr = this.I;
            if (objArr[i] == F) {
                this.H[i] = j;
                objArr[i] = obj;
                return;
            }
        }
        if (this.G && i2 >= this.H.length) {
            d();
            i = ~AbstractC0179Cy.b(this.H, this.f10560J, j);
        }
        int i3 = this.f10560J;
        if (i3 >= this.H.length) {
            int f = AbstractC0179Cy.f(i3 + 1);
            long[] jArr = new long[f];
            Object[] objArr2 = new Object[f];
            long[] jArr2 = this.H;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.I;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.H = jArr;
            this.I = objArr2;
        }
        int i4 = this.f10560J;
        if (i4 - i != 0) {
            long[] jArr3 = this.H;
            int i5 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i5, i4 - i);
            Object[] objArr4 = this.I;
            System.arraycopy(objArr4, i, objArr4, i5, this.f10560J - i);
        }
        this.H[i] = j;
        this.I[i] = obj;
        this.f10560J++;
    }

    public void j(long j) {
        Object[] objArr;
        Object obj;
        int b = AbstractC0179Cy.b(this.H, this.f10560J, j);
        if (b >= 0 && (objArr = this.I)[b] != (obj = F)) {
            objArr[b] = obj;
            this.G = true;
        }
    }

    public int k() {
        if (this.G) {
            d();
        }
        return this.f10560J;
    }

    public Object l(int i) {
        if (this.G) {
            d();
        }
        return this.I[i];
    }

    public String toString() {
        if (k() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f10560J * 28);
        sb.append('{');
        for (int i = 0; i < this.f10560J; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(h(i));
            sb.append('=');
            Object l = l(i);
            if (l != this) {
                sb.append(l);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
