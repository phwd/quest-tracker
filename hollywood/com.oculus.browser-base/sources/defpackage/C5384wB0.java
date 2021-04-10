package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: wB0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5384wB0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2892hd f11526a;
    public final /* synthetic */ AB0 b;

    public C5384wB0(AB0 ab0, C2892hd hdVar) {
        this.b = ab0;
        this.f11526a = hdVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2892hd hdVar = (C2892hd) obj;
        AB0 ab0 = this.b;
        if (ab0.w != null) {
            if (hdVar != null) {
                ab0.r.w = null;
                hdVar.m();
                this.b.s.d(hdVar);
                if (!hdVar.f9599a) {
                    AB0 ab02 = this.b;
                    ab02.C.c = -1;
                    ab02.o();
                } else {
                    if (this.f11526a == null) {
                        C5084uR0 ur0 = this.b.C;
                        ur0.f11410a.add(0, hdVar);
                        ur0.c = 0;
                    }
                    C3980ny nyVar = this.b.D;
                    if (nyVar != null) {
                        nyVar.i(hdVar);
                        AB0 ab03 = this.b;
                        ab03.w.o(3, ab03.D);
                    }
                    AB0 ab04 = this.b;
                    Objects.requireNonNull(ab04);
                    PersonalDataManager.c().i(hdVar.m, ab04);
                }
            } else {
                ab0.o();
            }
            if (!this.b.e.isEmpty()) {
                AB0 ab05 = this.b;
                ab05.d.post((Runnable) ab05.e.remove());
            }
        }
    }
}
