package org.chromium.net;

import android.os.Handler;
import android.os.HandlerThread;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AndroidCellularSignalStrength {

    /* renamed from: a  reason: collision with root package name */
    public static final AndroidCellularSignalStrength f10998a = new AndroidCellularSignalStrength();
    public volatile int b = Integer.MIN_VALUE;

    public AndroidCellularSignalStrength() {
        HandlerThread handlerThread = new HandlerThread("AndroidCellularSignalStrength");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Q4(this));
    }

    public static int getSignalStrengthLevel() {
        return f10998a.b;
    }
}
