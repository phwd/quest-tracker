package defpackage;

import java.util.Objects;

/* renamed from: Vk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1309Vk extends AbstractC1248Uk {
    public final byte[] I;

    public C1309Vk(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.I = bArr;
    }

    @Override // defpackage.AbstractC1248Uk
    public byte a(int i) {
        return this.I[i];
    }

    @Override // defpackage.AbstractC1248Uk
    public void e(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.I, i, bArr, i2, i3);
    }

    @Override // defpackage.AbstractC1248Uk
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC1248Uk) || size() != ((AbstractC1248Uk) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof C1309Vk)) {
            return obj.equals(this);
        }
        C1309Vk vk = (C1309Vk) obj;
        int i = this.H;
        int i2 = vk.H;
        if (i != 0 && i2 != 0 && i != i2) {
            return false;
        }
        int size = size();
        if (size > vk.size()) {
            int size2 = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(size);
            sb.append(size2);
            throw new IllegalArgumentException(sb.toString());
        } else if (0 + size <= vk.size()) {
            byte[] bArr = this.I;
            byte[] bArr2 = vk.I;
            int o = o() + size;
            int o2 = o();
            int o3 = vk.o() + 0;
            while (o2 < o) {
                if (bArr[o2] != bArr2[o3]) {
                    return false;
                }
                o2++;
                o3++;
            }
            return true;
        } else {
            int size3 = vk.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: ");
            sb2.append(0);
            sb2.append(", ");
            sb2.append(size);
            sb2.append(", ");
            sb2.append(size3);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    @Override // defpackage.AbstractC1248Uk
    public byte f(int i) {
        return this.I[i];
    }

    @Override // defpackage.AbstractC1248Uk
    public final AbstractC1248Uk h(int i, int i2) {
        int b = AbstractC1248Uk.b(i, i2, size());
        if (b == 0) {
            return AbstractC1248Uk.F;
        }
        return new C1126Sk(this.I, o() + i, b);
    }

    public int o() {
        return 0;
    }

    @Override // defpackage.AbstractC1248Uk
    public int size() {
        return this.I.length;
    }
}
