package defpackage;

import org.chromium.chrome.browser.tab.TabWebContentsDelegateAndroidImpl;

/* renamed from: Dd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0194Dd1 implements Runnable {
    public final TabWebContentsDelegateAndroidImpl F;

    public RunnableC0194Dd1(TabWebContentsDelegateAndroidImpl tabWebContentsDelegateAndroidImpl) {
        this.F = tabWebContentsDelegateAndroidImpl;
    }

    public void run() {
        TabWebContentsDelegateAndroidImpl tabWebContentsDelegateAndroidImpl = this.F;
        C1261Uq0 Y = tabWebContentsDelegateAndroidImpl.f10774a.Y();
        while (Y.hasNext()) {
            ((AbstractC1404Xa1) Y.next()).l(tabWebContentsDelegateAndroidImpl.f10774a);
        }
    }
}
