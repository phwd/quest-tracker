package defpackage;

import org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl;

/* renamed from: C5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5 implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public static AndroidOverlayProviderImpl f7783a;

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        if (f7783a == null) {
            f7783a = new AndroidOverlayProviderImpl();
        }
        return f7783a;
    }
}
