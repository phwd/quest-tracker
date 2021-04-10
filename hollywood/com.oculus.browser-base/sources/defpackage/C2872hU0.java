package defpackage;

import org.chromium.content_public.browser.WebContents;

/* renamed from: hU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2872hU0 implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public final WebContents f10073a;

    public C2872hU0(WebContents webContents) {
        this.f10073a = webContents;
    }

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        return new C2530fU0(this.f10073a, new C2701gU0(this));
    }
}
