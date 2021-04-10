package X;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: X.1cK  reason: invalid class name */
public final class AnonymousClass1cK implements AbstractC06491aL {
    public static final AnonymousClass1cJ<Class<?>, byte[]> A08 = new AnonymousClass1cJ<>(50);
    public final Class<?> A00;
    public final int A01;
    public final int A02;
    public final AbstractC06491aL A03;
    public final AbstractC06491aL A04;
    public final AnonymousClass1cO A05;
    public final AnonymousClass1eU<?> A06;
    public final AnonymousClass1hX A07;

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        Object A012;
        AnonymousClass1hX r5 = this.A07;
        synchronized (r5) {
            A012 = AnonymousClass1hX.A01(r5, r5.A03.A00(8, byte[].class), byte[].class);
        }
        byte[] bArr = (byte[]) A012;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putInt(this.A02);
        wrap.putInt(this.A01);
        wrap.array();
        this.A03.AAv(messageDigest);
        this.A04.AAv(messageDigest);
        messageDigest.update(bArr);
        AnonymousClass1eU<?> r0 = this.A06;
        if (r0 != null) {
            r0.AAv(messageDigest);
        }
        this.A05.AAv(messageDigest);
        AnonymousClass1cJ<Class<?>, byte[]> r3 = A08;
        Class<?> cls = this.A00;
        byte[] A013 = r3.A01(cls);
        if (A013 == null) {
            A013 = cls.getName().getBytes(AbstractC06491aL.A00);
            r3.A04(cls, A013);
        }
        messageDigest.update(A013);
        r5.A05(bArr);
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass1cK)) {
            return false;
        }
        AnonymousClass1cK r4 = (AnonymousClass1cK) obj;
        if (this.A01 != r4.A01 || this.A02 != r4.A02 || !C08381eW.A07(this.A06, r4.A06) || !this.A00.equals(r4.A00) || !this.A04.equals(r4.A04) || !this.A03.equals(r4.A03) || !this.A05.equals(r4.A05)) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        int hashCode = (((((this.A04.hashCode() * 31) + this.A03.hashCode()) * 31) + this.A02) * 31) + this.A01;
        AnonymousClass1eU<?> r0 = this.A06;
        if (r0 != null) {
            hashCode = (hashCode * 31) + r0.hashCode();
        }
        return (((hashCode * 31) + this.A00.hashCode()) * 31) + this.A05.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ResourceCacheKey{sourceKey=");
        sb.append(this.A04);
        sb.append(", signature=");
        sb.append(this.A03);
        sb.append(", width=");
        sb.append(this.A02);
        sb.append(", height=");
        sb.append(this.A01);
        sb.append(", decodedResourceClass=");
        sb.append(this.A00);
        sb.append(", transformation='");
        sb.append(this.A06);
        sb.append('\'');
        sb.append(", options=");
        sb.append(this.A05);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass1cK(ArrayPool arrayPool, AbstractC06491aL r2, AbstractC06491aL r3, int i, int i2, AnonymousClass1eU<?> r6, Class<?> cls, AnonymousClass1cO r8) {
        this.A07 = arrayPool;
        this.A04 = r2;
        this.A03 = r3;
        this.A02 = i;
        this.A01 = i2;
        this.A06 = r6;
        this.A00 = cls;
        this.A05 = r8;
    }
}
