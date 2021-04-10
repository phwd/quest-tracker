package com.oculus.browser;

import android.util.Log;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NewTabMessageHandler implements AbstractC1910br0, AbstractC0530Iq0 {
    public long F;
    public String G;

    public NewTabMessageHandler(long j) {
        this.F = j;
        String str = C0591Jq0.b().f;
        nativeInit(j, str == null ? "" : str);
    }

    public static NewTabMessageHandler Create(long j) {
        return new NewTabMessageHandler(j);
    }

    public void OnDestroy() {
        C2081cr0 f = C2081cr0.f();
        synchronized (f.f9725J) {
            f.f9725J.remove(this);
        }
        Experimentation.e().h.remove(this);
        C0591Jq0.e(this);
        this.F = 0;
    }

    public void OnJavascriptAllowed() {
        C2081cr0 f = C2081cr0.f();
        synchronized (f.f9725J) {
            f.f9725J.add(this);
        }
        f.e();
        Experimentation.e().h.add(this);
        if (!Preferences.getInstance().a()) {
            Log.e("Experimentation", "Preferences not initialized!");
        } else {
            String string = Preferences.getInstance().getString("QE_CACHE", null);
            String string2 = Preferences.getInstance().getString("UNIVERSE_CACHE", null);
            if (!(string == null || string2 == null)) {
                long j = this.F;
                if (j != 0) {
                    nativeSetExperimentData(j, string2, string);
                }
            }
        }
        C0591Jq0.a(this);
    }

    @Override // defpackage.AbstractC0530Iq0
    public void a() {
        AbstractC1220Ua0.d("NewTabMessageHandler", "Failed to retrieve Oculus access token", new Object[0]);
    }

    @Override // defpackage.AbstractC0530Iq0
    public void c(String str) {
        this.G = str;
        AbstractC1220Ua0.d("NewTabMessageHandler", "onAccessTokenReceived", new Object[0]);
        ThreadUtils.g(new RunnableC1194Tn0(this));
    }

    public final native void nativeInit(long j, String str);

    public final native void nativePublishStringMessage(long j, String str, String str2);

    public final native void nativeSetAccessToken(long j, String str);

    public final native void nativeSetExperimentData(long j, String str, String str2);
}
