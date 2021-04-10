package X;

/* renamed from: X.1As  reason: invalid class name */
public class AnonymousClass1As {
    public int A00 = 0;
    public int A01;
    public int A02;
    public int A03 = 0;
    public long A04;
    public boolean A05 = false;
    public boolean A06 = false;
    public int A07 = 0;
    public int A08 = 1;
    public int A09 = -1;
    public boolean A0A = false;
    public boolean A0B = false;
    public boolean A0C = false;
    public boolean A0D = false;

    public final int A00() {
        if (this.A0A) {
            return this.A03 - this.A00;
        }
        return this.A07;
    }

    public final void A01(int i) {
        int i2 = this.A08;
        if ((i2 & i) == 0) {
            throw new IllegalStateException(AnonymousClass006.A0B("Layout state should be one of ", Integer.toBinaryString(i), " but it is ", Integer.toBinaryString(i2)));
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("State{mTargetPosition=");
        sb.append(this.A09);
        sb.append(", mData=");
        sb.append((Object) null);
        sb.append(", mItemCount=");
        sb.append(this.A07);
        sb.append(", mIsMeasuring=");
        sb.append(this.A0B);
        sb.append(", mPreviousLayoutItemCount=");
        sb.append(this.A03);
        sb.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
        sb.append(this.A00);
        sb.append(", mStructureChanged=");
        sb.append(this.A06);
        sb.append(", mInPreLayout=");
        sb.append(this.A0A);
        sb.append(", mRunSimpleAnimations=");
        sb.append(this.A05);
        sb.append(", mRunPredictiveAnimations=");
        sb.append(this.A0C);
        sb.append('}');
        return sb.toString();
    }
}
