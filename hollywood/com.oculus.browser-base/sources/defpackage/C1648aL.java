package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.chromium.mojo.system.impl.CoreImpl;

/* renamed from: aL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1648aL {

    /* renamed from: a  reason: collision with root package name */
    public int f9425a;
    public final ZK b;

    public C1648aL(SA sa, int i) {
        ZK zk = new ZK(sa, i, null);
        this.b = zk;
        this.f9425a = zk.d;
    }

    public void a(double d, int i) {
        this.b.b.putDouble(this.f9425a + i, d);
    }

    public void b(float f, int i) {
        this.b.b.putFloat(this.f9425a + i, f);
    }

    public void c(int i, int i2) {
        this.b.b.putInt(this.f9425a + i2, i);
    }

    public void d(long j, int i) {
        this.b.b.putLong(this.f9425a + i, j);
    }

    public void e(CC cc) {
        ZK zk = this.b;
        zk.d += ((cc.f7794a + 8) - 1) & -8;
        if (zk.b.capacity() < zk.d) {
            int capacity = zk.b.capacity();
            do {
                capacity *= 2;
            } while (capacity < zk.d);
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(capacity);
            allocateDirect.order(ByteOrder.nativeOrder());
            zk.b.position(0);
            ByteBuffer byteBuffer = zk.b;
            byteBuffer.limit(byteBuffer.capacity());
            allocateDirect.put(zk.b);
            zk.b = allocateDirect;
        }
        c(cc.f7794a, 0);
        c(cc.b, 4);
    }

    public void f(PW pw, int i, boolean z) {
        if (pw == null || !pw.a()) {
            r(i, z);
            return;
        }
        c(this.b.c.size(), i);
        this.b.c.add(pw);
    }

    public void g(AbstractC3313k30 k30, int i, boolean z, AbstractC2630g30 g30) {
        if (k30 == null) {
            r(i, z);
            c(0, i + 4);
            return;
        }
        SA sa = this.b.f9338a;
        if (sa == null) {
            throw new UnsupportedOperationException("The encoder has been created without a Core. It can't encode an interface.");
        } else if (k30 instanceof AbstractC2972i30) {
            C2288e30 e30 = (C2288e30) ((AbstractC2972i30) k30).i();
            f((AbstractC1552Zj0) ((RW) e30.G).x(), i, z);
            c(e30.I, i + 4);
        } else {
            C1576Zv0 b2 = ((CoreImpl) sa).b(null);
            g30.c(k30, (AbstractC1552Zj0) b2.f9384a);
            f((PW) b2.b, i, z);
            c(g30.g(), i + 4);
        }
    }

    public void h(B30 b30, int i, boolean z) {
        if (b30 == null) {
            r(i, z);
        } else if (this.b.f9338a != null) {
            f(b30.x(), i, z);
        } else {
            throw new UnsupportedOperationException("The encoder has been created without a Core. It can't encode an interface.");
        }
    }

    public void i(AbstractC4340q31 q31, int i, boolean z) {
        if (q31 == null) {
            s(i, z);
            return;
        }
        u(i);
        q31.a(this);
    }

    public void j(AbstractC5658xp1 xp1, int i, boolean z) {
        if (xp1 == null && !z) {
            throw new FS0("Trying to encode a null pointer for a non-nullable type.");
        } else if (xp1 == null) {
            d(0, i);
            d(0, i + 8);
        } else {
            xp1.a(this, i);
        }
    }

    public void k(String str, int i, boolean z) {
        if (str == null) {
            s(i, z);
        } else {
            n(str.getBytes(Charset.forName("utf8")), i, z ? 1 : 0, -1);
        }
    }

    public void l(short s, int i) {
        this.b.b.putShort(this.f9425a + i, s);
    }

    public void m(boolean z, int i, int i2) {
        if (z) {
            this.b.b.put(this.f9425a + i, (byte) (this.b.b.get(this.f9425a + i) | ((byte) (1 << i2))));
        }
    }

    public void n(byte[] bArr, int i, int i2, int i3) {
        if (bArr == null) {
            s(i, AbstractC5802yh.b(i2));
        } else if (i3 == -1 || i3 == bArr.length) {
            q(bArr, bArr.length, i);
        } else {
            throw new FS0("Trying to encode a fixed array of incorrect length.");
        }
    }

    public void o(float[] fArr, int i, int i2, int i3) {
        if (fArr == null) {
            s(i, AbstractC5802yh.b(i2));
            return;
        }
        C1648aL v = v(4, fArr.length, i, i3);
        v.b.b.position(v.f9425a + 8);
        v.b.b.asFloatBuffer().put(fArr);
    }

    public void p(int[] iArr, int i, int i2, int i3) {
        if (iArr == null) {
            s(i, AbstractC5802yh.b(i2));
            return;
        }
        C1648aL v = v(4, iArr.length, i, i3);
        v.b.b.position(v.f9425a + 8);
        v.b.b.asIntBuffer().put(iArr);
    }

    public final void q(byte[] bArr, int i, int i2) {
        C1648aL w = w(bArr.length, i, i2);
        w.b.b.position(w.f9425a + 8);
        w.b.b.put(bArr);
    }

    public void r(int i, boolean z) {
        if (z) {
            this.b.b.putInt(this.f9425a + i, -1);
            return;
        }
        throw new FS0("Trying to encode an invalid handle for a non-nullable type.");
    }

    public void s(int i, boolean z) {
        if (z) {
            this.b.b.putLong(this.f9425a + i, 0);
            return;
        }
        throw new FS0("Trying to encode a null pointer for a non-nullable type.");
    }

    public C1648aL t(int i, int i2, int i3) {
        return v(8, i, i2, i3);
    }

    public final void u(int i) {
        d(((long) this.b.d) - ((long) (this.f9425a + i)), i);
    }

    public final C1648aL v(int i, int i2, int i3, int i4) {
        if (i4 == -1 || i4 == i2) {
            return w(i * i2, i2, i3);
        }
        throw new FS0("Trying to encode a fixed array of incorrect length.");
    }

    public final C1648aL w(int i, int i2, int i3) {
        u(i3);
        int i4 = i + 8;
        ZK zk = this.b;
        C1648aL aLVar = new C1648aL(zk);
        zk.d += ((i4 + 8) - 1) & -8;
        if (zk.b.capacity() < zk.d) {
            int capacity = zk.b.capacity();
            do {
                capacity *= 2;
            } while (capacity < zk.d);
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(capacity);
            allocateDirect.order(ByteOrder.nativeOrder());
            zk.b.position(0);
            ByteBuffer byteBuffer = zk.b;
            byteBuffer.limit(byteBuffer.capacity());
            allocateDirect.put(zk.b);
            zk.b = allocateDirect;
        }
        aLVar.c(i4, 0);
        aLVar.b.b.putInt(aLVar.f9425a + 4, i2);
        return aLVar;
    }

    public C1648aL x(CC cc) {
        C1648aL aLVar = new C1648aL(this.b);
        aLVar.e(cc);
        return aLVar;
    }

    public C2740gj0 y() {
        this.b.b.position(0);
        ZK zk = this.b;
        zk.b.limit(zk.d);
        ZK zk2 = this.b;
        return new C2740gj0(zk2.b, zk2.c);
    }

    public C1648aL(ZK zk) {
        this.b = zk;
        this.f9425a = zk.d;
    }
}
