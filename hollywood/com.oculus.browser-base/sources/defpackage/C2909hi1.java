package defpackage;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* renamed from: hi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2909hi1 {

    /* renamed from: a  reason: collision with root package name */
    public final C1547Zh1 f10094a;
    public final RecyclerView b;
    public final C2000cL0 c;
    public final LinearLayoutManager d;
    public final LayoutAnimationController e;
    public final C4447qi1 f;

    public C2909hi1(Context context, C1547Zh1 zh1) {
        this.f10094a = zh1;
        C2226di1 di1 = new C2226di1(this, context);
        this.b = di1;
        di1.e0 = true;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(0, false);
        this.d = linearLayoutManager;
        di1.t0(linearLayoutManager);
        di1.g(new C2567fi1(this, context));
        di1.s0(null);
        this.e = AnimationUtils.loadLayoutAnimation(context, R.anim.f420_resource_name_obfuscated_RES_2130772010);
        di1.setLayoutAnimationListener(new animation.Animation$AnimationListenerC2396ei1(this));
        C4447qi1 qi1 = new C4447qi1(context);
        this.f = qi1;
        ZH0.a(zh1.H, di1, new C1884bi1());
        C2000cL0 cl0 = new C2000cL0(new C2738gi1(zh1), new C5807yi1(qi1));
        this.c = cl0;
        di1.q0(cl0);
        di1.post(new RunnableC2055ci1(cl0));
    }
}
