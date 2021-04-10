package defpackage;

import J.N;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: qw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4489qw1 implements AbstractC1593a20 {

    /* renamed from: a  reason: collision with root package name */
    public final VrModuleProvider f11173a;
    public final C5305vl0 b;

    public C4489qw1(VrModuleProvider vrModuleProvider, C5305vl0 vl0) {
        this.f11173a = vrModuleProvider;
        this.b = vl0;
    }

    @Override // defpackage.AbstractC1593a20
    public void a(boolean z) {
        VrModuleProvider vrModuleProvider = this.f11173a;
        C5305vl0 vl0 = this.b;
        if (vrModuleProvider.c == 0) {
            return;
        }
        if (!z) {
            vl0.a();
            return;
        }
        vl0.c();
        N.Mmw1DU8y(vrModuleProvider.c, vrModuleProvider, z);
    }
}
