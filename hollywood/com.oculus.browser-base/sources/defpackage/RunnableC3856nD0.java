package defpackage;

import android.os.SystemClock;
import android.telephony.CellInfo;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.Callback;

/* renamed from: nD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3856nD0 implements Runnable {
    public final Callback F;
    public final List G;

    public RunnableC3856nD0(Callback callback, List list) {
        this.F = callback;
        this.G = list;
    }

    public void run() {
        Callback callback = this.F;
        List list = this.G;
        HashSet hashSet = new HashSet();
        if (list != null) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long currentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < list.size(); i++) {
                Hv1 d = AbstractC4027oD0.d((CellInfo) list.get(i), elapsedRealtime, currentTimeMillis);
                if (d.c != 0) {
                    hashSet.add(d);
                }
            }
        }
        callback.onResult(hashSet);
    }
}
