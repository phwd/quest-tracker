package defpackage;

import android.os.Handler;
import android.os.Message;
import androidx.preference.PreferenceScreen;

/* renamed from: bF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC1812bF0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC2324eF0 f9524a;

    public HandlerC1812bF0(AbstractC2324eF0 ef0) {
        this.f9524a = ef0;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            AbstractC2324eF0 ef0 = this.f9524a;
            PreferenceScreen preferenceScreen = ef0.z0.g;
            if (preferenceScreen != null) {
                ef0.A0.q0(new C3520lF0(preferenceScreen));
                preferenceScreen.v();
            }
        }
    }
}
