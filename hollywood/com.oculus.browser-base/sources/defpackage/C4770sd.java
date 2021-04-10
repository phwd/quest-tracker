package defpackage;

import org.chromium.base.Callback;

/* renamed from: sd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4770sd implements AbstractC1593a20 {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f11287a;
    public final boolean b;
    public final C5305vl0 c;

    public C4770sd(Callback callback, boolean z, C5305vl0 vl0) {
        this.f11287a = callback;
        this.b = z;
        this.c = vl0;
    }

    @Override // defpackage.AbstractC1593a20
    public void a(boolean z) {
        Callback callback = this.f11287a;
        boolean z2 = this.b;
        C5305vl0 vl0 = this.c;
        if (z) {
            AbstractC4259pd.a(1);
            callback.onResult((AbstractC4600rd) AbstractC4430qd.f11152a.b());
        } else if (z2) {
            vl0.a();
        } else {
            callback.onResult(null);
        }
    }
}
