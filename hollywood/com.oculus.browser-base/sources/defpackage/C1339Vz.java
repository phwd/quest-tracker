package defpackage;

import android.text.TextUtils;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchContext;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* renamed from: Vz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1339Vz extends AbstractC2185dS0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ContextualSearchManager f9119a;

    public C1339Vz(ContextualSearchManager contextualSearchManager, ViewTreeObserver$OnGlobalFocusChangeListenerC0790Mz mz) {
        this.f9119a = contextualSearchManager;
    }

    @Override // defpackage.AbstractC2185dS0
    public void a() {
    }

    @Override // defpackage.AbstractC2185dS0
    public void d(String str) {
        ContextualSearchManager contextualSearchManager = this.f9119a;
        if (contextualSearchManager.T != null) {
            C4700sA sAVar = contextualSearchManager.P;
            if (sAVar.j) {
                sAVar.f = str;
                sAVar.j = false;
            } else {
                if (TextUtils.isEmpty(str) && !TextUtils.isEmpty(sAVar.f)) {
                    sAVar.f = str;
                    ((ContextualSearchManager) sAVar.b).S.a(4);
                    if (sAVar.g == 1) {
                        sAVar.g();
                    }
                }
                sAVar.f = str;
                if (sAVar.h) {
                    sAVar.d(str, sAVar.g);
                    sAVar.h = false;
                } else {
                    boolean h = sAVar.h(str);
                    ContextualSearchManager contextualSearchManager2 = (ContextualSearchManager) sAVar.b;
                    if (!contextualSearchManager2.n() && contextualSearchManager2.m()) {
                        if (h) {
                            contextualSearchManager2.T.v0(str);
                        } else {
                            contextualSearchManager2.i(5);
                        }
                    }
                }
            }
            C1786b61.j(this.f9119a.T.s0.K0(), 3, true);
        }
    }

    @Override // defpackage.AbstractC2185dS0
    public void e(int i, float f, float f2) {
        String str;
        C4700sA sAVar = this.f9119a.P;
        int i2 = 2;
        boolean z = false;
        if (i != 0) {
            if (i == 2) {
                sAVar.u = false;
                ((ContextualSearchManager) sAVar.b).h();
                sAVar.f();
            } else if (i == 4) {
                sAVar.t = true;
                String str2 = sAVar.f;
                Pattern pattern = AA.f7657a;
                if (!TextUtils.isEmpty(str2)) {
                    if (!AA.f7657a.matcher(str2.trim()).find()) {
                        AbstractC3535lK0.a("ContextualSearch.ManualRefineSingleWord");
                    } else {
                        AbstractC3535lK0.a("ContextualSearch.ManualRefineMultiWord");
                    }
                }
            }
            sAVar.k = f;
            sAVar.l = f2;
            if (z && (str = sAVar.f) != null) {
                sAVar.d(str, sAVar.g);
                return;
            }
        }
        sAVar.u = true;
        sAVar.h = false;
        if (sAVar.e.a()) {
            i2 = 3;
        }
        sAVar.g = i2;
        AbstractC3551lS0 c = sAVar.c();
        if (c != null) {
            sAVar.f = ((SelectionPopupControllerImpl) c).Z;
        }
        sAVar.t = false;
        Pattern pattern2 = AA.f7657a;
        AbstractC3535lK0.a("ContextualSearch.SelectionEstablished");
        z = true;
        sAVar.k = f;
        sAVar.l = f2;
        if (z) {
        }
    }

    @Override // defpackage.AbstractC2185dS0
    public boolean f(boolean z) {
        return false;
    }

    @Override // defpackage.AbstractC2185dS0
    public void g(boolean z, int i, int i2) {
        int i3;
        int i4;
        ContextualSearchManager contextualSearchManager = this.f9119a;
        int i5 = contextualSearchManager.s0;
        if (i5 > 0) {
            contextualSearchManager.s0 = i5 - 1;
        }
        if (contextualSearchManager.s0 <= 0 && contextualSearchManager.S.b(11)) {
            if (!z || this.f9119a.N.b()) {
                this.f9119a.i(0);
                return;
            }
            this.f9119a.h0.e(i, i2);
            ContextualSearchContext contextualSearchContext = this.f9119a.h0;
            String substring = (TextUtils.isEmpty(contextualSearchContext.c) || (i3 = contextualSearchContext.e) < (i4 = contextualSearchContext.d) || i4 < 0 || i3 > contextualSearchContext.c.length()) ? "" : contextualSearchContext.c.substring(contextualSearchContext.d, contextualSearchContext.e);
            if (!TextUtils.isEmpty(substring)) {
                this.f9119a.P.f = substring;
            }
            ContextualSearchManager contextualSearchManager2 = this.f9119a;
            String str = contextualSearchManager2.P.f;
            if (contextualSearchManager2.m()) {
                contextualSearchManager2.T.v0(str);
            }
            this.f9119a.S.c(11);
        }
    }
}
