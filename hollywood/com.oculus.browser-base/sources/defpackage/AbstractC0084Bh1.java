package defpackage;

import android.os.Handler;
import android.os.HandlerThread;

/* renamed from: Bh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0084Bh1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Handler f7749a;

    static {
        HandlerThread handlerThread = new HandlerThread("InputConnectionHandlerThread", 5);
        handlerThread.start();
        f7749a = new Handler(handlerThread.getLooper());
    }
}
