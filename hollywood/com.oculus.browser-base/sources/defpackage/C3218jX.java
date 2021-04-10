package defpackage;

import androidx.browser.customtabs.CustomTabsSessionToken;

/* renamed from: jX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3218jX {

    /* renamed from: a  reason: collision with root package name */
    public C3048iX f10209a;

    public void a(CustomTabsSessionToken customTabsSessionToken) {
        C3048iX iXVar = this.f10209a;
        if (iXVar != null) {
            if (customTabsSessionToken == null || customTabsSessionToken.equals(iXVar.f10142a)) {
                this.f10209a.b.destroy();
                this.f10209a = null;
            }
        }
    }
}
