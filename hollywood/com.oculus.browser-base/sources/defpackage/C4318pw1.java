package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: pw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4318pw1 implements AbstractC1593a20 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1593a20 f11103a;

    public C4318pw1(AbstractC1593a20 a20) {
        this.f11103a = a20;
    }

    @Override // defpackage.AbstractC1593a20
    public void a(boolean z) {
        AbstractC1593a20 a20 = this.f11103a;
        C2780gw1 gw1 = VrModuleProvider.f10799a;
        if (z) {
            VrModuleProvider.f10799a = null;
            Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
        }
        a20.a(z);
    }
}
