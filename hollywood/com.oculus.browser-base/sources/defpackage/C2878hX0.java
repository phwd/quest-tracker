package defpackage;

import com.oculus.browser.Preferences;

/* renamed from: hX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2878hX0 {

    /* renamed from: a  reason: collision with root package name */
    public Preferences f10075a;
    public String b;
    public C3390kX0 c;

    public C2878hX0(C3390kX0 kx0, String str, Preferences preferences) {
        this.f10075a = preferences;
        this.c = kx0;
        this.b = str;
    }

    public final String a() {
        StringBuilder i = AbstractC2531fV.i("SINGLE_TAB_SELECTOR_URL:");
        String str = this.b;
        if (str == null) {
            str = "";
        }
        i.append(str);
        return i.toString();
    }
}
