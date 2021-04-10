package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.browser.HydraApplication;
import com.oculus.browser.PanelApp;
import java.util.Map;
import java.util.Objects;

/* renamed from: lY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3562lY extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ HydraApplication f10350a;

    public C3562lY(HydraApplication hydraApplication) {
        this.f10350a = hydraApplication;
    }

    public void onReceive(Context context, Intent intent) {
        String[] stringArrayExtra = intent.getStringArrayExtra("response_permission");
        int[] intArrayExtra = intent.getIntArrayExtra("results_permission");
        for (Map.Entry entry : this.f10350a.Q.entrySet()) {
            C0054Aw0 aw0 = ((PanelApp) entry.getValue()).f0.O;
            Objects.requireNonNull(aw0);
            Log.i("PanelWindowAndroid", "onRequestPermissionResponse");
            HB0 hb0 = aw0.b0;
            if (hb0 != null) {
                hb0.b(stringArrayExtra, intArrayExtra);
                aw0.b0 = null;
            }
        }
    }
}
