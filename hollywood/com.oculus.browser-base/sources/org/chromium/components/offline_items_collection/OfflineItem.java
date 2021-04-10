package org.chromium.components.offline_items_collection;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OfflineItem implements Cloneable {
    public C0788My F = new C0788My();
    public String G;
    public String H;
    public int I = 5;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10857J;
    public boolean K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public double P;
    public long Q;
    public boolean R;
    public long S;
    public long T;
    public long U;
    public boolean V;
    public String W;
    public String X;
    public String Y;
    public String Z;
    public boolean a0;
    public String b0;
    public int c0 = 2;
    public boolean d0;
    public boolean e0;
    public long f0;
    public C0288Er0 g0;
    public long h0;
    public boolean i0;
    public int j0;
    public int k0;
    public OfflineItemSchedule l0;

    @Override // java.lang.Object
    public Object clone() {
        OfflineItem offlineItem = new OfflineItem();
        C0788My my = this.F;
        offlineItem.F = my == null ? null : new C0788My(my.f8514a, my.b);
        offlineItem.G = this.G;
        offlineItem.H = this.H;
        offlineItem.I = this.I;
        offlineItem.f10857J = this.f10857J;
        offlineItem.K = this.K;
        offlineItem.L = this.L;
        offlineItem.M = this.M;
        offlineItem.Q = this.Q;
        offlineItem.R = this.R;
        offlineItem.S = this.S;
        offlineItem.T = this.T;
        offlineItem.U = this.U;
        offlineItem.V = this.V;
        offlineItem.W = this.W;
        offlineItem.X = this.X;
        offlineItem.N = this.N;
        offlineItem.O = this.O;
        offlineItem.P = this.P;
        offlineItem.Y = this.Y;
        offlineItem.Z = this.Z;
        offlineItem.a0 = this.a0;
        offlineItem.b0 = this.b0;
        offlineItem.c0 = this.c0;
        offlineItem.d0 = this.d0;
        offlineItem.e0 = this.e0;
        offlineItem.f0 = this.f0;
        offlineItem.h0 = this.h0;
        offlineItem.j0 = this.j0;
        offlineItem.k0 = this.k0;
        OfflineItemSchedule offlineItemSchedule = this.l0;
        if (offlineItemSchedule != null) {
            offlineItem.l0 = offlineItemSchedule.clone();
        }
        if (this.g0 != null) {
            C0288Er0 er0 = this.g0;
            offlineItem.g0 = new C0288Er0(er0.f7980a, er0.b, er0.c);
        }
        return offlineItem;
    }
}
