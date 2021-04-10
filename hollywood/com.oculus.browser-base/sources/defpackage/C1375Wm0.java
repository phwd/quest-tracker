package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.url.GURL;

/* renamed from: Wm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1375Wm0 implements AdapterView.OnItemClickListener {
    public final Profile F;
    public final Context G;
    public final ListPopupWindow H;
    public final NavigationController I;

    /* renamed from: J  reason: collision with root package name */
    public C0948Pm0 f9172J;
    public final C1314Vm0 K;
    public final int L;
    public final int M;
    public final View.OnLayoutChangeListener N;
    public C3713mO O;
    public C3542lO P;
    public boolean Q;

    public C1375Wm0(Profile profile, Context context, NavigationController navigationController, int i) {
        this.F = profile;
        this.G = context;
        Resources resources = context.getResources();
        this.I = navigationController;
        this.L = i;
        boolean z = i == 2;
        boolean z2 = i == 0;
        C0948Pm0 z3 = navigationController.z(z, 8);
        this.f9172J = z3;
        z3.f8712a.add(new NavigationEntry(-1, new GURL("chrome://history/"), GURL.emptyGURL(), GURL.emptyGURL(), GURL.emptyGURL(), resources.getString(R.string.f61810_resource_name_obfuscated_RES_2131953498), null, 0, 0));
        C1314Vm0 vm0 = new C1314Vm0(this, null);
        this.K = vm0;
        ListPopupWindow listPopupWindow = new ListPopupWindow(context, null, 0, R.style.f68990_resource_name_obfuscated_RES_2132017472);
        this.H = listPopupWindow;
        listPopupWindow.setOnDismissListener(new C1070Rm0(this));
        listPopupWindow.setBackgroundDrawable(AbstractC3153j7.c(resources, z2 ? R.drawable.f34610_resource_name_obfuscated_RES_2131231501 : R.drawable.f34630_resource_name_obfuscated_RES_2131231503));
        listPopupWindow.setModal(true);
        listPopupWindow.setInputMethodMode(2);
        listPopupWindow.setHeight(-2);
        listPopupWindow.setOnItemClickListener(this);
        listPopupWindow.setAdapter(vm0);
        listPopupWindow.setWidth(resources.getDimensionPixelSize(z2 ? R.dimen.f22860_resource_name_obfuscated_RES_2131165905 : R.dimen.f20690_resource_name_obfuscated_RES_2131165688));
        if (z2) {
            listPopupWindow.setVerticalOffset(0);
            this.N = new View$OnLayoutChangeListenerC1192Tm0(this);
        } else {
            this.N = null;
        }
        this.M = resources.getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);
    }

    public final String a(String str) {
        return AbstractC2531fV.h(new StringBuilder(), this.L == 2 ? "ForwardMenu_" : "BackMenu_", str);
    }

    public final void b() {
        int width = (this.H.getAnchorView().getWidth() - this.H.getWidth()) / 2;
        if (width > 0) {
            this.H.setHorizontalOffset(width);
        }
        this.H.show();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        NavigationEntry navigationEntry = (NavigationEntry) adapterView.getItemAtPosition(i);
        if (navigationEntry.f10939a == -1) {
            AbstractC3535lK0.a(a("ShowFullHistory"));
            ChromeActivity chromeActivity = (ChromeActivity) this.G;
            AbstractC4755sX.a(chromeActivity, chromeActivity.K0());
        } else {
            StringBuilder i2 = AbstractC2531fV.i("HistoryClick");
            i2.append(i + 1);
            AbstractC3535lK0.a(a(i2.toString()));
            int i3 = navigationEntry.f10939a;
            AbstractC3100ip1.f10165a.a("Navigation.BackForward.NavigatingToEntryMarkedToBeSkipped", this.I.n(i3));
            this.I.v(i3);
        }
        this.H.dismiss();
    }
}
