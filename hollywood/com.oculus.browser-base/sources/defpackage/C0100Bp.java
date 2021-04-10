package defpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import org.chromium.components.browser_ui.site_settings.ChosenObjectSettings;

/* renamed from: Bp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0100Bp implements Cy1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChosenObjectSettings f7759a;

    public C0100Bp(ChosenObjectSettings chosenObjectSettings, C5996zp zpVar) {
        this.f7759a = chosenObjectSettings;
    }

    @Override // defpackage.Cy1
    public void a(Collection collection) {
        if (this.f7759a.u() != null) {
            String str = ((C5316vp) this.f7759a.I0.get(0)).f11500J;
            this.f7759a.I0.clear();
            this.f7759a.J0 = new ArrayList();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                C3469ky1 ky1 = (C3469ky1) it.next();
                Iterator it2 = ((ArrayList) ky1.d()).iterator();
                while (it2.hasNext()) {
                    C5316vp vpVar = (C5316vp) it2.next();
                    if (vpVar.f11500J.equals(str)) {
                        this.f7759a.I0.add(vpVar);
                        if (this.f7759a.L0.isEmpty() || ky1.i().toLowerCase(Locale.getDefault()).contains(this.f7759a.L0)) {
                            this.f7759a.J0.add(ky1);
                        }
                    }
                }
            }
            if (this.f7759a.I0.isEmpty()) {
                this.f7759a.u().finish();
            } else {
                this.f7759a.o1();
            }
        }
    }
}
