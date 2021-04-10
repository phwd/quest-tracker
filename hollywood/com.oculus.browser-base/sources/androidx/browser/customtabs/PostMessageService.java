package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PostMessageService extends Service {
    public AbstractBinderC3052iZ F = new JE0(this);

    public IBinder onBind(Intent intent) {
        return this.F;
    }
}
