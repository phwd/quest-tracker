package org.chromium.chrome.browser.password_manager;

import android.content.res.Resources;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordGenerationDialogBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10738a;
    public final C1760ay0 b;
    public String c;

    public PasswordGenerationDialogBridge(WindowAndroid windowAndroid, long j) {
        this.f10738a = j;
        this.b = new C1760ay0((ChromeActivity) windowAndroid.s0().get());
    }

    public static PasswordGenerationDialogBridge create(WindowAndroid windowAndroid, long j) {
        return new PasswordGenerationDialogBridge(windowAndroid, j);
    }

    public final void destroy() {
        this.f10738a = 0;
        C1760ay0 ay0 = this.b;
        ay0.f9503a.b(ay0.d, 4);
    }

    public void showDialog(String str, String str2) {
        this.c = str;
        C1760ay0 ay0 = this.b;
        C1580Zx0 zx0 = new C1580Zx0(this);
        C2102cy0 cy0 = ay0.b;
        TH0 th0 = C2102cy0.c;
        cy0.m(th0, str);
        TH0 th02 = C2102cy0.d;
        cy0.m(th02, str2);
        C2102cy0 cy02 = ay0.b;
        PasswordGenerationDialogCustomView passwordGenerationDialogCustomView = ay0.c;
        passwordGenerationDialogCustomView.F.setText((String) cy02.g(th0));
        passwordGenerationDialogCustomView.F.setInputType(131217);
        passwordGenerationDialogCustomView.G.setText((String) cy02.g(th02));
        PasswordGenerationDialogCustomView passwordGenerationDialogCustomView2 = ay0.c;
        Resources resources = passwordGenerationDialogCustomView2.getResources();
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, new C1931by0(zx0));
        hh0.d(AbstractC3258jl0.c, resources, R.string.f57930_resource_name_obfuscated_RES_2131953110);
        hh0.e(AbstractC3258jl0.f, passwordGenerationDialogCustomView2);
        hh0.d(AbstractC3258jl0.g, resources, R.string.f57940_resource_name_obfuscated_RES_2131953111);
        hh0.d(AbstractC3258jl0.j, resources, R.string.f57920_resource_name_obfuscated_RES_2131953109);
        UH0 a2 = hh0.a();
        ay0.d = a2;
        ay0.f9503a.i(a2, 0, false);
    }
}
