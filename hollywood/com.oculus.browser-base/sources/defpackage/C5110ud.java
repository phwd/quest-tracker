package defpackage;

import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: ud  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5110ud {

    /* renamed from: a  reason: collision with root package name */
    public static final C5110ud f11424a = new C5110ud();

    public static void a(Tab tab, Callback callback, boolean z) {
        C5305vl0 vl0 = new C5305vl0(tab, R.string.f47050_resource_name_obfuscated_RES_2131952022, new C4940td(tab, callback, z));
        if (z) {
            vl0.b();
        }
        AbstractC4430qd.f11152a.d(new C4770sd(callback, z, vl0));
    }
}
