package defpackage;

import android.content.ComponentName;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Qa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0975Qa implements AbstractC1097Sa {

    /* renamed from: a  reason: collision with root package name */
    public final LoadUrlParams f8769a;
    public final WebContents b;
    public final Integer c;
    public final ComponentName d;

    public C0975Qa(LoadUrlParams loadUrlParams) {
        this.f8769a = loadUrlParams;
        this.c = null;
        this.b = null;
        this.d = null;
    }

    @Override // defpackage.AbstractC1097Sa
    public WebContents l() {
        return this.b;
    }

    @Override // defpackage.AbstractC1097Sa
    public Tab m() {
        return null;
    }

    public C0975Qa(LoadUrlParams loadUrlParams, ComponentName componentName) {
        this.f8769a = loadUrlParams;
        this.c = null;
        this.b = null;
        this.d = componentName;
    }

    public C0975Qa(LoadUrlParams loadUrlParams, Integer num) {
        this.f8769a = loadUrlParams;
        this.c = num;
        this.b = null;
        this.d = null;
    }

    public C0975Qa(LoadUrlParams loadUrlParams, WebContents webContents) {
        this.f8769a = loadUrlParams;
        this.c = null;
        this.b = webContents;
        this.d = null;
    }
}
