package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: tU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC4919tU extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5089uU f11345a;

    public HandlerC4919tU(C5089uU uUVar, AbstractC4579rU rUVar) {
        this.f11345a = uUVar;
    }

    public void handleMessage(Message message) {
        if (message.what != 3) {
            super.handleMessage(message);
            return;
        }
        C5089uU uUVar = this.f11345a;
        if (uUVar.g != null) {
            Bundle bundle = (Bundle) message.obj;
            C4409qU qUVar = uUVar.d;
            bundle.getByteArray("ssb_service:ssb_state");
            Objects.requireNonNull(qUVar);
            AbstractC3364kK0.g("Search.GsaAccountChangeNotificationSource", 0, 2);
            C5259vU.b(this.f11345a.e).f(null);
            Callback callback = this.f11345a.f;
            if (callback != null) {
                callback.onResult(bundle);
            }
        }
    }
}
