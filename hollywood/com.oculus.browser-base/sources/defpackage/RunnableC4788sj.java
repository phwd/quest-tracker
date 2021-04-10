package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;
import org.chromium.components.browser_ui.bottomsheet.TouchRestrictingFrameLayout;

/* renamed from: sj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4788sj implements Runnable {
    public final C5638xj F;
    public final Callback G;
    public final Window H;
    public final C3493l60 I;

    /* renamed from: J  reason: collision with root package name */
    public final Q31 f11294J;

    public RunnableC4788sj(C5638xj xjVar, Callback callback, Window window, C3493l60 l60, Q31 q31) {
        this.F = xjVar;
        this.G = callback;
        this.H = window;
        this.I = l60;
        this.f11294J = q31;
    }

    public void run() {
        C5638xj xjVar = this.F;
        Callback callback = this.G;
        Window window = this.H;
        C3493l60 l60 = this.I;
        Q31 q31 = this.f11294J;
        Objects.requireNonNull(xjVar);
        LayoutInflater.from(((ViewGroup) q31.get()).getContext()).inflate(R.layout.f37180_resource_name_obfuscated_RES_2131624027, (ViewGroup) q31.get());
        BottomSheet bottomSheet = (BottomSheet) ((ViewGroup) q31.get()).findViewById(R.id.bottom_sheet);
        xjVar.F = bottomSheet;
        callback.onResult(bottomSheet);
        BottomSheet bottomSheet2 = xjVar.F;
        View view = (View) bottomSheet2.getParent();
        TouchRestrictingFrameLayout touchRestrictingFrameLayout = (TouchRestrictingFrameLayout) bottomSheet2.findViewById(R.id.bottom_sheet_toolbar_container);
        bottomSheet2.d0 = touchRestrictingFrameLayout;
        touchRestrictingFrameLayout.setBackgroundResource(R.drawable.f35420_resource_name_obfuscated_RES_2131231582);
        bottomSheet2.e0 = bottomSheet2.d0.findViewById(R.id.bottom_sheet_toolbar);
        bottomSheet2.getLayoutParams().height = -1;
        TouchRestrictingFrameLayout touchRestrictingFrameLayout2 = (TouchRestrictingFrameLayout) bottomSheet2.findViewById(R.id.bottom_sheet_content);
        bottomSheet2.b0 = touchRestrictingFrameLayout2;
        touchRestrictingFrameLayout2.F = bottomSheet2;
        touchRestrictingFrameLayout2.setBackgroundResource(R.drawable.f35420_resource_name_obfuscated_RES_2131231582);
        bottomSheet2.P = view.getWidth();
        int height = view.getHeight();
        bottomSheet2.Q = height;
        bottomSheet2.k0 = height + bottomSheet2.L;
        bottomSheet2.R = bottomSheet2.P;
        view.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC3593lj(bottomSheet2, window, l60));
        bottomSheet2.d0.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC3764mj(bottomSheet2));
        ViewGroup viewGroup = (ViewGroup) bottomSheet2.getParent();
        bottomSheet2.M = viewGroup;
        viewGroup.removeView(bottomSheet2);
        xjVar.F.j0 = xjVar.O;
        xjVar.G = new PriorityQueue(1, new C4958tj());
        Map c = UH0.c(MP0.l);
        NH0 nh0 = MP0.f8474a;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = 0;
        HashMap hashMap = (HashMap) c;
        hashMap.put(nh0, jh0);
        MH0 mh0 = MP0.b;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(mh0, gh0);
        OH0 oh0 = MP0.c;
        BottomSheet bottomSheet3 = xjVar.F;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = bottomSheet3;
        hashMap.put(oh0, lh0);
        MH0 mh02 = MP0.d;
        GH0 gh02 = new GH0(null);
        gh02.f8081a = false;
        hashMap.put(mh02, gh02);
        OH0 oh02 = MP0.f;
        RunnableC5128uj ujVar = new RunnableC5128uj(xjVar);
        LH0 lh02 = new LH0(null);
        lh02.f8415a = ujVar;
        UH0 o = AbstractC2531fV.o(hashMap, oh02, lh02, c, null);
        BottomSheet bottomSheet4 = xjVar.F;
        bottomSheet4.H.b(new C5468wj(xjVar, o));
        for (int i = 0; i < xjVar.f11628J.size(); i++) {
            xjVar.F.H.b((AbstractC0576Jj) xjVar.f11628J.get(i));
        }
        xjVar.f11628J.clear();
        xjVar.I = null;
    }
}
