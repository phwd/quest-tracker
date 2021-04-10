package X;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.EngineResource;

/* renamed from: X.1f8  reason: invalid class name and case insensitive filesystem */
public final class C08701f8<Z> implements AnonymousClass1fR<Z> {
    public int A00;
    public boolean A01;
    public final AnonymousClass1fR<Z> A02;
    public final boolean A03;
    public final AbstractC06491aL A04;
    public final AbstractC08671f5 A05;
    public final boolean A06;

    public final void A00() {
        boolean z;
        synchronized (this) {
            int i = this.A00;
            if (i > 0) {
                z = true;
                int i2 = i - 1;
                this.A00 = i2;
                if (i2 != 0) {
                    z = false;
                }
            } else {
                throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
            }
        }
        if (z) {
            this.A05.A01(this.A04, this);
        }
    }

    public final synchronized void A01() {
        if (!this.A01) {
            this.A00++;
        } else {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
    }

    @Override // X.AnonymousClass1fR
    public final synchronized void A8u() {
        if (this.A00 > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (!this.A01) {
            this.A01 = true;
            if (this.A06) {
                this.A02.A8u();
            }
        } else {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
    }

    public final synchronized String toString() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append("EngineResource{isMemoryCacheable=");
        sb.append(this.A03);
        sb.append(", listener=");
        sb.append(this.A05);
        sb.append(", key=");
        sb.append(this.A04);
        sb.append(", acquired=");
        sb.append(this.A00);
        sb.append(", isRecycled=");
        sb.append(this.A01);
        sb.append(", resource=");
        sb.append(this.A02);
        sb.append('}');
        return sb.toString();
    }

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<Z> A4o() {
        return this.A02.A4o();
    }

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Z get() {
        return this.A02.get();
    }

    @Override // X.AnonymousClass1fR
    public final int getSize() {
        return this.A02.getSize();
    }

    public C08701f8(AnonymousClass1fR<Z> r1, boolean z, boolean z2, AbstractC06491aL r4, EngineResource.ResourceListener resourceListener) {
        AnonymousClass1S2.A00(r1);
        this.A02 = r1;
        this.A03 = z;
        this.A06 = z2;
        this.A04 = r4;
        AnonymousClass1S2.A00(resourceListener);
        this.A05 = resourceListener;
    }
}
