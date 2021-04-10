package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: as0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1742as0 implements AbstractC1385Wr0 {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f9496a;

    public C1742as0(Tab tab) {
        this.f9496a = tab;
    }

    @Override // defpackage.AbstractC1385Wr0
    public void c(LoadUrlParams loadUrlParams) {
        this.f9496a.c(loadUrlParams);
    }
}
