package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;

/* renamed from: m81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3671m81 extends AbstractC1099Sa1 {
    public C3671m81(AbstractC0124Ca1 ca1) {
        super(ca1);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && !tab.a()) {
            Integer num = navigationHandle.d;
            if (navigationHandle.i || (num != null && (num.intValue() & 255) == 5)) {
                AbstractC3842n81.d("IPH_TabGroupsQuicklyComparePages", tab.b(), null);
                AbstractC3842n81.f10474a.destroy();
            }
        }
    }
}
