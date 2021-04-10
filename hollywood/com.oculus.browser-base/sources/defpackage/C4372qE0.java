package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: qE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4372qE0 {

    /* renamed from: a  reason: collision with root package name */
    public K41 f11125a;
    public Runnable b;

    public C4372qE0(Context context, Runnable runnable) {
        this.b = runnable;
        K41 k41 = new K41(context);
        this.f11125a = k41;
        k41.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        K41 k412 = this.f11125a;
        int color = k412.getResources().getColor(R.color.f10950_resource_name_obfuscated_RES_2131099785);
        k412.P.setBackgroundColor(color);
        k412.T.f9935J.w = color;
        this.f11125a.f(R.color.f11100_resource_name_obfuscated_RES_2131099800);
        this.f11125a.setEnabled(true);
        this.f11125a.G = new C4030oE0(this);
    }
}
