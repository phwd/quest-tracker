package org.chromium.chrome.browser.preferences;

import J.N;
import android.util.ArrayMap;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrefChangeRegistrar {

    /* renamed from: a  reason: collision with root package name */
    public final Map f10748a = new ArrayMap();
    public long b = N.MiTdj3xP(this);

    public final void onPreferenceChange(String str) {
        ((UE0) this.f10748a.get(str)).a();
    }
}
