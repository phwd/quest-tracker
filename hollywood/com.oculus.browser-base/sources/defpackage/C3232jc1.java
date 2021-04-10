package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.Callback;

/* renamed from: jc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3232jc1 implements AbstractC3403kc1 {

    /* renamed from: a  reason: collision with root package name */
    public List f10218a;

    public C3232jc1() {
        if (N.M6bsIDpc("CloseTabSuggestions", "baseline_tab_suggestions", false)) {
            this.f10218a = new ArrayList();
            if (N.M6bsIDpc("CloseTabSuggestions", "baseline_group_tab_suggestions", false)) {
                this.f10218a.add(new C0993Qg(0));
            }
            if (N.M6bsIDpc("CloseTabSuggestions", "baseline_close_tab_suggestions", false)) {
                this.f10218a.add(new C0993Qg(1));
                return;
            }
            return;
        }
        this.f10218a = new ArrayList(Arrays.asList(new UZ0()));
    }

    @Override // defpackage.AbstractC3403kc1
    public void a(C3836n61 n61, Callback callback) {
        ArrayList arrayList = new ArrayList();
        for (AbstractC3062ic1 ic1 : this.f10218a) {
            List a2 = ic1.a(n61);
            if (a2 != null && !a2.isEmpty()) {
                arrayList.addAll(a2);
            }
        }
        ((C4087oc1) callback).onResult(new C3574lc1(arrayList, n61));
    }

    @Override // defpackage.AbstractC3403kc1
    public boolean isEnabled() {
        return true;
    }
}
