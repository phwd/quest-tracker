package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: wb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5446wb1 implements AbstractC1097Sa {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f11553a;

    public C5446wb1(Tab tab, Runnable runnable) {
        this.f11553a = tab;
    }

    @Override // defpackage.AbstractC1097Sa
    public WebContents l() {
        return null;
    }

    @Override // defpackage.AbstractC1097Sa
    public Tab m() {
        return this.f11553a;
    }
}
