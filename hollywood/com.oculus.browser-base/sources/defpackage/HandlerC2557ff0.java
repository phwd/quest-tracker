package defpackage;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import java.util.List;
import java.util.Objects;

/* renamed from: ff0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC2557ff0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DialogC3240jf0 f9939a;

    public HandlerC2557ff0(DialogC3240jf0 jf0) {
        this.f9939a = jf0;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            DialogC3240jf0 jf0 = this.f9939a;
            Objects.requireNonNull(jf0);
            jf0.P = SystemClock.uptimeMillis();
            jf0.L.clear();
            jf0.L.addAll((List) message.obj);
            jf0.M.notifyDataSetChanged();
        }
    }
}
