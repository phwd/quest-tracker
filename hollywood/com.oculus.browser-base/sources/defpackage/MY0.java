package defpackage;

/* renamed from: MY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MY0 implements Cloneable {
    public static final Object F = new Object();
    public boolean G = false;
    public int[] H;
    public Object[] I;

    /* renamed from: J  reason: collision with root package name */
    public int f8482J;

    public MY0(int i) {
        if (i == 0) {
            this.H = AbstractC0179Cy.f7849a;
            this.I = AbstractC0179Cy.c;
            return;
        }
        int e = AbstractC0179Cy.e(i);
        this.H = new int[e];
        this.I = new Object[e];
    }

    public void a(int i, Object obj) {
        int i2 = this.f8482J;
        if (i2 == 0 || i > this.H[i2 - 1]) {
            if (this.G && i2 >= this.H.length) {
                c();
            }
            int i3 = this.f8482J;
            if (i3 >= this.H.length) {
                int e = AbstractC0179Cy.e(i3 + 1);
                int[] iArr = new int[e];
                Object[] objArr = new Object[e];
                int[] iArr2 = this.H;
                System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
                Object[] objArr2 = this.I;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.H = iArr;
                this.I = objArr;
            }
            this.H[i3] = i;
            this.I[i3] = obj;
            this.f8482J = i3 + 1;
            return;
        }
        g(i, obj);
    }

    /* renamed from: b */
    public MY0 clone() {
        try {
            MY0 my0 = (MY0) super.clone();
            my0.H = (int[]) this.H.clone();
            my0.I = (Object[]) this.I.clone();
            return my0;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final void c() {
        int i = this.f8482J;
        int[] iArr = this.H;
        Object[] objArr = this.I;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != F) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.G = false;
        this.f8482J = i2;
    }

    public Object d(int i) {
        return e(i, null);
    }

    public Object e(int i, Object obj) {
        int a2 = AbstractC0179Cy.a(this.H, this.f8482J, i);
        if (a2 >= 0) {
            Object[] objArr = this.I;
            if (objArr[a2] != F) {
                return objArr[a2];
            }
        }
        return obj;
    }

    public int f(int i) {
        if (this.G) {
            c();
        }
        return this.H[i];
    }

    public void g(int i, Object obj) {
        int a2 = AbstractC0179Cy.a(this.H, this.f8482J, i);
        if (a2 >= 0) {
            this.I[a2] = obj;
            return;
        }
        int i2 = ~a2;
        int i3 = this.f8482J;
        if (i2 < i3) {
            Object[] objArr = this.I;
            if (objArr[i2] == F) {
                this.H[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (this.G && i3 >= this.H.length) {
            c();
            i2 = ~AbstractC0179Cy.a(this.H, this.f8482J, i);
        }
        int i4 = this.f8482J;
        if (i4 >= this.H.length) {
            int e = AbstractC0179Cy.e(i4 + 1);
            int[] iArr = new int[e];
            Object[] objArr2 = new Object[e];
            int[] iArr2 = this.H;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.I;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.H = iArr;
            this.I = objArr2;
        }
        int i5 = this.f8482J;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.H;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.I;
            System.arraycopy(objArr4, i2, objArr4, i6, this.f8482J - i2);
        }
        this.H[i2] = i;
        this.I[i2] = obj;
        this.f8482J++;
    }

    public void h(int i) {
        Object[] objArr;
        Object obj;
        int a2 = AbstractC0179Cy.a(this.H, this.f8482J, i);
        if (a2 >= 0 && (objArr = this.I)[a2] != (obj = F)) {
            objArr[a2] = obj;
            this.G = true;
        }
    }

    public int i() {
        if (this.G) {
            c();
        }
        return this.f8482J;
    }

    public Object j(int i) {
        if (this.G) {
            c();
        }
        return this.I[i];
    }

    public String toString() {
        if (i() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8482J * 28);
        sb.append('{');
        for (int i = 0; i < this.f8482J; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(f(i));
            sb.append('=');
            Object j = j(i);
            if (j != this) {
                sb.append(j);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
