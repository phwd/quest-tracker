package defpackage;

import com.google.android.gms.common.api.Status;

/* renamed from: Ie0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0501Ie0 implements BM0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0684Le0 f8237a;

    public C0501Ie0(C0684Le0 le0, C5573xH1 xh1) {
        this.f8237a = le0;
    }

    @Override // defpackage.BM0
    public final void a(AM0 am0) {
        Status b = ((HL0) am0).b();
        int i = b.K;
        if (i != 0) {
            this.f8237a.f8431a.b(String.format("Error fetching queue item ids, statusCode=%s, statusMessage=%s", Integer.valueOf(i), b.L), new Object[0]);
        }
        C0684Le0 le0 = this.f8237a;
        le0.n = null;
        if (!le0.i.isEmpty()) {
            this.f8237a.g();
        }
    }
}
