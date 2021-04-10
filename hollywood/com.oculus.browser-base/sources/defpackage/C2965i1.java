package defpackage;

import J.N;
import android.app.Activity;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: i1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2965i1 {

    /* renamed from: a  reason: collision with root package name */
    public final C5353w1 f10118a;
    public final C4673s1 b;
    public final C5693y1 c;
    public final AbstractC4448qj d;
    public final AbstractC0576Jj e;

    public C2965i1(Activity activity, AbstractC4448qj qjVar, AbstractC5863z1 z1Var, TabModel tabModel, A61 a61, C2535fX fXVar) {
        C2792h00 h00 = new C2792h00(activity, tabModel, a61, fXVar);
        C2794h1 h1Var = new C2794h1(this);
        this.e = h1Var;
        C4072oW0 ow0 = C4072oW0.f10556a;
        ow0.b.c("Chrome.AccountPickerBottomSheet.ShownCount");
        AbstractC3901nW0.a(6);
        AbstractC3364kK0.g("Signin.AccountConsistencyPromoAction.Shown.Count", ow0.b.f("Chrome.AccountPickerBottomSheet.ShownCount", 0), 100);
        C4673s1 s1Var = new C4673s1(activity, z1Var, new RunnableC2281e1(this));
        this.b = s1Var;
        C5353w1 w1Var = new C5353w1(activity, s1Var);
        this.f10118a = w1Var;
        this.c = new C5693y1(w1Var.f, s1Var, null, N.M$3vpOHw());
        new C2621g00(w1Var.e.getChildAt(4), h00, new RunnableC2452f1());
        this.d = qjVar;
        ZH0.a(s1Var.H, w1Var, new C2623g1());
        C5638xj xjVar = (C5638xj) qjVar;
        xjVar.j(h1Var);
        xjVar.u(w1Var, true);
    }
}
