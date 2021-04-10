package defpackage;

import android.animation.Animator;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.ui.tablet.emptybackground.EmptyBackgroundViewTablet;
import org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButtonTablet;

/* renamed from: NK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NK {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f8540a;
    public final AbstractC0124Ca1 b;
    public final A61 c;
    public final AbstractC5783ya1 d = new KK(this);
    public final AbstractC0612Ka1 e = new LK(this);
    public final View$OnClickListenerC5098uY0 f;
    public C1128Sl g;
    public AbstractC2260du0 h;
    public EmptyBackgroundViewTablet i;
    public final AbstractC5717y9 j;

    public NK(AbstractC0124Ca1 ca1, A61 a61, Activity activity, AbstractC5717y9 y9Var, View$OnClickListenerC5098uY0 uy0, AbstractC1509Ys0 ys0) {
        C1128Sl sl = new C1128Sl();
        this.g = sl;
        this.f8540a = activity;
        this.j = y9Var;
        this.b = ca1;
        this.c = a61;
        this.f = uy0;
        ys0.g(sl.b(new JK(this)));
    }

    public static void a(NK nk) {
        Animator animator;
        AbstractC2260du0 du0;
        boolean z = false;
        TabModel l = ((AbstractC0246Ea1) nk.b).l(false);
        if (l != null) {
            boolean z2 = ((AbstractC0246Ea1) nk.b).l(true).getCount() == 0;
            boolean r = ((AbstractC0246Ea1) nk.b).r();
            if (l.getCount() == 0 && (((du0 = nk.h) == null || !((AbstractC3838n70) du0).C()) && (!r || z2))) {
                z = true;
            }
        }
        if (z && nk.i == null) {
            EmptyBackgroundViewTablet emptyBackgroundViewTablet = (EmptyBackgroundViewTablet) ((ViewStub) nk.f8540a.findViewById(R.id.empty_container_stub)).inflate();
            nk.i = emptyBackgroundViewTablet;
            emptyBackgroundViewTablet.G = nk.b;
            IncognitoToggleButtonTablet incognitoToggleButtonTablet = (IncognitoToggleButtonTablet) emptyBackgroundViewTablet.findViewById(R.id.empty_incognito_toggle_button);
            emptyBackgroundViewTablet.L = incognitoToggleButtonTablet;
            AbstractC0124Ca1 ca1 = emptyBackgroundViewTablet.G;
            incognitoToggleButtonTablet.H = ca1;
            if (ca1 != null) {
                incognitoToggleButtonTablet.d();
                S00 s00 = new S00(incognitoToggleButtonTablet);
                incognitoToggleButtonTablet.I = s00;
                ((AbstractC0246Ea1) incognitoToggleButtonTablet.H).c(s00);
            }
            if (ca1 != null) {
                incognitoToggleButtonTablet.e();
                incognitoToggleButtonTablet.K = new V00(incognitoToggleButtonTablet);
                for (TabModel tabModel : ((AbstractC0246Ea1) incognitoToggleButtonTablet.H).f7969a) {
                    tabModel.n(incognitoToggleButtonTablet.K);
                }
            }
            EmptyBackgroundViewTablet emptyBackgroundViewTablet2 = nk.i;
            emptyBackgroundViewTablet2.H = nk.c;
            AbstractC5717y9 y9Var = nk.j;
            if (y9Var != null) {
                C4697s9 s9Var = new C4697s9((C5887z9) y9Var);
                ((ImageButton) emptyBackgroundViewTablet2.findViewById(R.id.empty_menu_button)).setOnTouchListener(s9Var);
                s9Var.G = new FK();
            }
            nk.i.addOnAttachStateChangeListener(new MK(nk));
        }
        EmptyBackgroundViewTablet emptyBackgroundViewTablet3 = nk.i;
        if (emptyBackgroundViewTablet3 != null) {
            ViewGroup viewGroup = null;
            if (z && emptyBackgroundViewTablet3.getVisibility() != 0 && emptyBackgroundViewTablet3.I != (animator = emptyBackgroundViewTablet3.f10793J)) {
                C3493l60.F.d(emptyBackgroundViewTablet3);
            } else if (z || emptyBackgroundViewTablet3.getVisibility() == 8 || emptyBackgroundViewTablet3.I == (animator = emptyBackgroundViewTablet3.K)) {
                animator = null;
            }
            if (animator != null) {
                Animator animator2 = emptyBackgroundViewTablet3.I;
                if (animator2 != null) {
                    animator2.cancel();
                }
                emptyBackgroundViewTablet3.I = animator;
                animator.start();
            }
            View$OnClickListenerC5098uY0 uy0 = nk.f;
            if (z) {
                viewGroup = nk.i;
            }
            C5948zY0 zy0 = uy0.G;
            if (zy0 != null) {
                zy0.l.removeOnLayoutChangeListener(zy0.p);
                if (viewGroup == null) {
                    viewGroup = zy0.i;
                }
                zy0.j = viewGroup;
                if (zy0.b.isShown()) {
                    ((ViewGroup) zy0.b.getParent()).removeView(zy0.b);
                }
                zy0.j.addView(zy0.b);
                zy0.l.addOnLayoutChangeListener(zy0.p);
            }
        }
    }
}
