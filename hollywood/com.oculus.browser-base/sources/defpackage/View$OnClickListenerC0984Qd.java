package defpackage;

import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;
import com.oculus.browser.R;
import java.util.Locale;

/* renamed from: Qd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC0984Qd implements View.OnClickListener {
    public final C1228Ud F;

    public View$OnClickListenerC0984Qd(C1228Ud ud) {
        this.F = ud;
    }

    public void onClick(View view) {
        C1228Ud ud = this.F;
        if (ud.K == null) {
            ud.K = new PopupWindow(ud.M);
            RunnableC1106Sd sd = new RunnableC1106Sd(ud);
            Locale locale = Locale.getDefault();
            Locale locale2 = AbstractC0630Kg1.f8381a;
            AbstractC0073Be.e(ud.M, ud.K, R.string.f47590_resource_name_obfuscated_RES_2131952076, new C1167Td(ud), TextUtils.getLayoutDirectionFromLocale(locale) == 0 ? ud.I : ud.f9035J, sd);
        }
    }
}
