package defpackage;

import com.google.android.gms.common.api.Status;

/* renamed from: ZD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ZD1 implements BM0 {

    /* renamed from: a  reason: collision with root package name */
    public final long f9330a;
    public final /* synthetic */ IL0 b;

    public ZD1(IL0 il0, long j) {
        this.b = il0;
        this.f9330a = j;
    }

    @Override // defpackage.BM0
    public final void a(AM0 am0) {
        Status status = (Status) am0;
        if (!status.x()) {
            MF1 mf1 = this.b.c.c;
            long j = this.f9330a;
            int i = status.K;
            for (QF1 qf1 : mf1.d) {
                qf1.e(j, i, null);
            }
        }
    }
}
