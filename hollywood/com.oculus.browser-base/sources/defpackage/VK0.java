package defpackage;

/* renamed from: VK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VK0 {

    /* renamed from: a  reason: collision with root package name */
    public int f9076a = -1;
    public int b = 0;
    public int c = 0;
    public int d = 1;
    public int e = 0;
    public boolean f = false;
    public boolean g = false;
    public boolean h = false;
    public boolean i = false;
    public boolean j = false;
    public boolean k = false;
    public int l;
    public long m;
    public int n;

    public void a(int i2) {
        if ((this.d & i2) == 0) {
            StringBuilder i3 = AbstractC2531fV.i("Layout state should be one of ");
            i3.append(Integer.toBinaryString(i2));
            i3.append(" but it is ");
            i3.append(Integer.toBinaryString(this.d));
            throw new IllegalStateException(i3.toString());
        }
    }

    public int b() {
        if (this.g) {
            return this.b - this.c;
        }
        return this.e;
    }

    public String toString() {
        StringBuilder i2 = AbstractC2531fV.i("State{mTargetPosition=");
        i2.append(this.f9076a);
        i2.append(", mData=");
        i2.append((Object) null);
        i2.append(", mItemCount=");
        i2.append(this.e);
        i2.append(", mIsMeasuring=");
        i2.append(this.i);
        i2.append(", mPreviousLayoutItemCount=");
        i2.append(this.b);
        i2.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
        i2.append(this.c);
        i2.append(", mStructureChanged=");
        i2.append(this.f);
        i2.append(", mInPreLayout=");
        i2.append(this.g);
        i2.append(", mRunSimpleAnimations=");
        i2.append(this.j);
        i2.append(", mRunPredictiveAnimations=");
        i2.append(this.k);
        i2.append('}');
        return i2.toString();
    }
}
