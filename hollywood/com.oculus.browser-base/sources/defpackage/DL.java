package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import java.util.Objects;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: DL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DL implements View.OnLayoutChangeListener {
    public final Context F;
    public final C2971i3 G;
    public final View H;
    public final C1595a3 I;

    /* renamed from: J  reason: collision with root package name */
    public final Q31 f7883J;
    public final AbstractC4448qj K;
    public final JL L = new JL();
    public IL M;
    public WebContents N;
    public AbstractViewGroup$OnHierarchyChangeListenerC1520Yy O;
    public NL P;
    public OK Q;
    public GURL R;
    public int S;
    public boolean T;
    public boolean U;
    public boolean V;

    public DL(Context context, C2971i3 i3Var, View view, C1595a3 a3Var, Q31 q31, AbstractC4448qj qjVar, boolean z) {
        this.F = context;
        this.G = i3Var;
        this.H = view;
        this.I = a3Var;
        this.f7883J = q31;
        this.K = qjVar;
    }

    public static boolean b() {
        return N.M09VlOh_("EphemeralTabUsingBottomSheet") && !SysUtils.isLowEndDevice();
    }

    public final int a() {
        Tab tab = this.I.H;
        if (tab == null || tab.b() == null) {
            return 0;
        }
        return tab.b().getHeight();
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int a2;
        if (this.P != null && (a2 = a()) != 0 && this.S != a2) {
            NL nl = this.P;
            Objects.requireNonNull(nl);
            if (a2 != 0) {
                C1544Zg1 zg1 = (C1544Zg1) nl.j;
                Objects.requireNonNull(zg1);
                zg1.getLayoutParams().height = ((int) (((float) a2) * 0.9f)) - nl.e;
                nl.g.requestLayout();
            }
            this.S = a2;
        }
    }
}
