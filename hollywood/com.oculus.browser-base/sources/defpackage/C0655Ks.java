package defpackage;

import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import org.chromium.base.ContextUtils;

/* renamed from: Ks  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0655Ks extends AbstractC1677aZ0 {
    public Messenger b;

    @Override // defpackage.AbstractC1677aZ0
    public IBinder a(Intent intent) {
        Messenger messenger = new Messenger(new HandlerC0594Js(ContextUtils.getApplicationContext()));
        this.b = messenger;
        return messenger.getBinder();
    }
}
