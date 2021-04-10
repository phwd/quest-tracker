package defpackage;

import android.view.View;
import org.chromium.content_public.browser.NavigationEntry;

/* renamed from: en0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC2410en0 implements View.OnClickListener {
    public final C2923hn0 F;
    public final int G;
    public final NavigationEntry H;

    public View$OnClickListenerC2410en0(C2923hn0 hn0, int i, NavigationEntry navigationEntry) {
        this.F = hn0;
        this.G = i;
        this.H = navigationEntry;
    }

    public void onClick(View view) {
        String str;
        C2923hn0 hn0 = this.F;
        int i = this.G;
        NavigationEntry navigationEntry = this.H;
        C1558Zm0 zm0 = hn0.f10100a;
        int i2 = navigationEntry.f10939a;
        C2240dn0 dn0 = zm0.f9369a;
        C5455we1 we1 = dn0.S;
        if (i2 == -1) {
            we1.b.a(we1.f11557a);
        } else {
            we1.f11557a.l().f().v(i2);
        }
        int i3 = 0;
        dn0.w(false);
        if (dn0.X) {
            if (i2 == -1) {
                str = "ShowFullHistory";
            } else {
                StringBuilder i4 = AbstractC2531fV.i("HistoryClick");
                i4.append(i + 1);
                str = i4.toString();
            }
            AbstractC3535lK0.a("BackMenu_" + str);
            return;
        }
        AbstractC3364kK0.g("GestureNavigation.Sheet.Used", dn0.U ? 1 : 0, 2);
        if (i2 != -1) {
            i3 = dn0.U ? i + 1 : (-i) - 1;
        }
        AbstractC3100ip1.f10165a.d("GestureNavigation.Sheet.Selected", i3);
    }
}
