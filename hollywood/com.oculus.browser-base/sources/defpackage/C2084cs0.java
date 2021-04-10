package defpackage;

import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* renamed from: cs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2084cs0 implements AbstractC1385Wr0 {

    /* renamed from: a  reason: collision with root package name */
    public final WebContents f9726a;

    public C2084cs0(WebContents webContents) {
        this.f9726a = webContents;
    }

    @Override // defpackage.AbstractC1385Wr0
    public void c(LoadUrlParams loadUrlParams) {
        this.f9726a.f().c(loadUrlParams);
    }
}
