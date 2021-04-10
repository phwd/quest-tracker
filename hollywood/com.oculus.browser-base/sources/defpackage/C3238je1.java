package defpackage;

import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: je1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3238je1 implements WT {

    /* renamed from: a  reason: collision with root package name */
    public final ChromeActivity f10223a;

    public C3238je1(ChromeActivity chromeActivity) {
        this.f10223a = chromeActivity;
    }

    @Override // defpackage.WT
    public Object apply(Object obj) {
        return Boolean.valueOf(this.f10223a.A0((Tab) obj));
    }
}
