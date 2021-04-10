package defpackage;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import java.util.List;
import java.util.Objects;

/* renamed from: Sf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC1113Sf0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DialogC1877bg0 f8907a;

    public HandlerC1113Sf0(DialogC1877bg0 bg0) {
        this.f8907a = bg0;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            DialogC1877bg0 bg0 = this.f8907a;
            Objects.requireNonNull(bg0);
            bg0.R = SystemClock.uptimeMillis();
            bg0.L.clear();
            bg0.L.addAll((List) message.obj);
            bg0.N.s();
        }
    }
}
