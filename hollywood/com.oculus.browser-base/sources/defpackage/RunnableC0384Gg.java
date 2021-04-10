package defpackage;

import J.N;
import android.content.Intent;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: Gg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0384Gg implements Runnable {
    public final AbstractC0749Mg F;
    public final AutocompleteMatch G;
    public final int H;

    public RunnableC0384Gg(AbstractC0749Mg mg, AutocompleteMatch autocompleteMatch, int i) {
        this.F = mg;
        this.G = autocompleteMatch;
        this.H = i;
    }

    public void run() {
        TabModel m;
        AbstractC0749Mg mg = this.F;
        AutocompleteMatch autocompleteMatch = this.G;
        int i = this.H;
        C2379ed edVar = (C2379ed) mg.b;
        AutocompleteController autocompleteController = edVar.P;
        Tab tab = (Tab) N.MlBcDCXz(autocompleteController.f10726a, autocompleteController, autocompleteMatch.j);
        C0560Jd1 a2 = AbstractC0621Kd1.a();
        if (tab == null || a2 == null) {
            edVar.m(autocompleteMatch, i, autocompleteMatch.j);
            return;
        }
        if (tab.i().t0() == 5 || tab.i().t0() == 6) {
            Intent a3 = AbstractC0409Gr.a(tab.getId());
            a3.addFlags(268435456);
            U20.q(ContextUtils.getApplicationContext(), a3);
        } else {
            TabModel tabModel = null;
            int i2 = 0;
            while (true) {
                if (i2 < a2.H.size()) {
                    AbstractC0124Ca1 ca1 = (AbstractC0124Ca1) a2.H.get(i2);
                    if (ca1 != null && (m = ((AbstractC0246Ea1) ca1).m(tab.getId())) != null) {
                        tabModel = m;
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
            tabModel.x(AbstractC1160Ta1.e(tabModel, tab.getId()), 4);
        }
        edVar.p(i, 10, autocompleteMatch);
    }
}
