package X;

/* renamed from: X.zC  reason: case insensitive filesystem */
public final class C1420zC {
    public final /* synthetic */ ZK A00;
    public final /* synthetic */ long A01;
    public final /* synthetic */ ZP A02;

    public C1420zC(ZP zp, long j, ZK zk) {
        this.A02 = zp;
        this.A01 = j;
        this.A00 = zk;
    }

    public final void A00(String str) {
        ZT zt = new ZT(this.A02.A0A);
        ZT.A0B(zt, AnonymousClass09.A01);
        ZT.A0C(zt, str);
        ZT.A09(zt);
        C0139Dd.A0O("DeviceConfigTelemetryLogger", "onFailure(%s)", str);
    }
}
