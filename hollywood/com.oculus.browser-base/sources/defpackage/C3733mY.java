package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.oculus.browser.HydraApplication;
import com.oculus.browser.PanelApp;
import java.util.Map;

/* renamed from: mY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3733mY extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ HydraApplication f10430a;

    public C3733mY(HydraApplication hydraApplication) {
        this.f10430a = hydraApplication;
    }

    public void onReceive(Context context, Intent intent) {
        for (Map.Entry entry : this.f10430a.Q.entrySet()) {
            ((PanelApp) entry.getValue()).C();
        }
    }
}
