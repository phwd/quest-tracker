package com.oculus.browser;

import J.N;
import android.util.Log;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutomationServer {

    /* renamed from: a  reason: collision with root package name */
    public static AutomationServer f9697a;
    public int b = 1;
    public long c;
    public final C2950hw0 d;

    public AutomationServer(C2950hw0 hw0) {
        Log.i("BrowserAutomationServer", "starting");
        Objects.requireNonNull(hw0, "observer");
        this.d = hw0;
        this.c = N.MTJOhZVu(this);
    }

    public final void didBindPort(int i) {
        Log.i("BrowserAutomationServer", "didBindPort " + i);
        if (this.b != 1) {
            Log.e("BrowserAutomationServer", "didBindPort after server already started");
            return;
        }
        this.b = 2;
        long j = this.d.f10111a.f9704J;
        if (j != 0) {
            N.MB$Krq1I(j, i, "ChromeDevTools");
        } else {
            AbstractC1220Ua0.d("PanelApp", "mNativePanelApp is 0 during AutomationServer start callback. Callback will be no-oped (T77672304, T83899854).", new Object[0]);
        }
    }
}
