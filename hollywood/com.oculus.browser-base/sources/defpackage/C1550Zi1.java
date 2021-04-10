package defpackage;

import android.content.Context;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.toolbar.top.ToggleTabStackButton;

/* renamed from: Zi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1550Zi1 {

    /* renamed from: a  reason: collision with root package name */
    public final C1128Sl f9361a;
    public final Context b;
    public final ToggleTabStackButton c;
    public final Vr1 d;
    public final AbstractC1487Yi e;
    public final AbstractC1509Ys0 f;
    public final AbstractC1509Ys0 g;
    public final Callback h;
    public final AB i;
    public AbstractC2642g70 j;
    public I70 k;
    public boolean l;

    public C1550Zi1(Context context, ToggleTabStackButton toggleTabStackButton, Vr1 vr1, AbstractC1487Yi yi, AbstractC1509Ys0 ys0, AbstractC1509Ys0 ys02, AbstractC1509Ys0 ys03, Callback callback, AbstractC0956Pq0 pq0, Tm1 tm1) {
        C1128Sl sl = new C1128Sl();
        this.f9361a = sl;
        this.b = context;
        this.c = toggleTabStackButton;
        this.d = vr1;
        this.e = yi;
        this.f = ys0;
        this.g = ys02;
        this.h = callback;
        ys03.g(sl.b(new C1245Ui1(this)));
        this.i = new AB(pq0, new C1428Xi1(this));
        AbstractC0173Cv.a(tm1, "IPH_TabSwitcherButton", "cohortFeatureName");
    }
}
