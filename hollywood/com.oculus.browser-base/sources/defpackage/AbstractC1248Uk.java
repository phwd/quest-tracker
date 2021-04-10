package defpackage;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: Uk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1248Uk implements Iterable, Serializable {
    public static final AbstractC1248Uk F = new C1309Vk(F30.b);
    public static final AbstractC1187Tk G = (P4.a() ? new C1370Wk(null) : new C1065Rk(null));
    public int H = 0;

    public static int b(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException(AbstractC2531fV.t(66, "Beginning index larger than ending index: ", i, ", ", i2));
        } else {
            throw new IndexOutOfBoundsException(AbstractC2531fV.t(37, "End index: ", i2, " >= ", i3));
        }
    }

    public static AbstractC1248Uk c(byte[] bArr, int i, int i2) {
        b(i, i + i2, bArr.length);
        return new C1309Vk(G.a(bArr, i, i2));
    }

    public abstract byte a(int i);

    public abstract void e(byte[] bArr, int i, int i2, int i3);

    public abstract boolean equals(Object obj);

    public abstract byte f(int i);

    public abstract AbstractC1248Uk h(int i, int i2);

    public final int hashCode() {
        int i = this.H;
        if (i == 0) {
            int size = size();
            C1309Vk vk = (C1309Vk) this;
            byte[] bArr = vk.I;
            int o = vk.o() + 0;
            Charset charset = F30.f7992a;
            int i2 = size;
            for (int i3 = o; i3 < o + size; i3++) {
                i2 = (i2 * 31) + bArr[i3];
            }
            i = i2 == 0 ? 1 : i2;
            this.H = i;
        }
        return i;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new C0943Pk(this);
    }

    public final byte[] l() {
        int size = size();
        if (size == 0) {
            return F30.b;
        }
        byte[] bArr = new byte[size];
        e(bArr, 0, 0, size);
        return bArr;
    }

    public final String m() {
        Charset charset = F30.f7992a;
        if (size() == 0) {
            return "";
        }
        C1309Vk vk = (C1309Vk) this;
        return new String(vk.I, vk.o(), vk.size(), charset);
    }

    public final String n() {
        return size() <= 50 ? AbstractC3928ng1.a(this) : String.valueOf(AbstractC3928ng1.a(h(0, 47))).concat("...");
    }

    public abstract int size();

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()), n());
    }
}
