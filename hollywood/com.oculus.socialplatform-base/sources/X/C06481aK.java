package X;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import java.util.Map;

/* renamed from: X.1aK  reason: invalid class name and case insensitive filesystem */
public final class C06481aK implements AbstractC06491aL {
    public int A00;
    public final int A01;
    public final int A02;
    public final AbstractC06491aL A03;
    public final AnonymousClass1cO A04;
    public final Class<?> A05;
    public final Class<?> A06;
    public final Object A07;
    public final Map<Class<?>, AnonymousClass1eU<?>> A08;

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (!(obj instanceof C06481aK)) {
            return false;
        }
        C06481aK r4 = (C06481aK) obj;
        if (!this.A07.equals(r4.A07) || !this.A03.equals(r4.A03) || this.A01 != r4.A01 || this.A02 != r4.A02 || !this.A08.equals(r4.A08) || !this.A05.equals(r4.A05) || !this.A06.equals(r4.A06) || !this.A04.equals(r4.A04)) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        int i = this.A00;
        if (i != 0) {
            return i;
        }
        int hashCode = this.A07.hashCode();
        this.A00 = hashCode;
        int hashCode2 = (hashCode * 31) + this.A03.hashCode();
        this.A00 = hashCode2;
        int i2 = (hashCode2 * 31) + this.A02;
        this.A00 = i2;
        int i3 = (i2 * 31) + this.A01;
        this.A00 = i3;
        int hashCode3 = (i3 * 31) + this.A08.hashCode();
        this.A00 = hashCode3;
        int hashCode4 = (hashCode3 * 31) + this.A05.hashCode();
        this.A00 = hashCode4;
        int hashCode5 = (hashCode4 * 31) + this.A06.hashCode();
        this.A00 = hashCode5;
        int hashCode6 = (hashCode5 * 31) + this.A04.hashCode();
        this.A00 = hashCode6;
        return hashCode6;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("EngineKey{model=");
        sb.append(this.A07);
        sb.append(", width=");
        sb.append(this.A02);
        sb.append(", height=");
        sb.append(this.A01);
        sb.append(", resourceClass=");
        sb.append(this.A05);
        sb.append(", transcodeClass=");
        sb.append(this.A06);
        sb.append(", signature=");
        sb.append(this.A03);
        sb.append(", hashCode=");
        sb.append(this.A00);
        sb.append(", transformations=");
        sb.append(this.A08);
        sb.append(", options=");
        sb.append(this.A04);
        sb.append('}');
        return sb.toString();
    }

    public C06481aK(Object obj, AbstractC06491aL r4, int i, int i2, Map<Class<?>, AnonymousClass1eU<?>> map, Class<?> cls, Class<?> cls2, AnonymousClass1cO r10) {
        AnonymousClass1S2.A00(obj);
        this.A07 = obj;
        if (r4 != null) {
            this.A03 = r4;
            this.A02 = i;
            this.A01 = i2;
            AnonymousClass1S2.A00(map);
            this.A08 = map;
            if (cls != null) {
                this.A05 = cls;
                if (cls2 != null) {
                    this.A06 = cls2;
                    AnonymousClass1S2.A00(r10);
                    this.A04 = r10;
                    return;
                }
                throw new NullPointerException("Transcode class must not be null");
            }
            throw new NullPointerException("Resource class must not be null");
        }
        throw new NullPointerException("Signature must not be null");
    }
}
