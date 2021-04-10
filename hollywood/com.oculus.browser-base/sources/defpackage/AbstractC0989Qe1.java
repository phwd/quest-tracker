package defpackage;

import android.os.Bundle;
import org.chromium.chrome.browser.offlinepages.TriggerConditions;

/* renamed from: Qe1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0989Qe1 {
    public static TriggerConditions a(Bundle bundle) {
        return new TriggerConditions(bundle.getBoolean("PowerConnected", true), bundle.getInt("BatteryPercentage", 100), bundle.getBoolean("UnmeteredNetwork", true));
    }
}
