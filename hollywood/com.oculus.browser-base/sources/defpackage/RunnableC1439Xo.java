package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: Xo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1439Xo implements Runnable {
    public final int F;

    public RunnableC1439Xo(int i) {
        this.F = i;
    }

    public void run() {
        int i = this.F;
        C3762mi0 mi0 = C3762mi0.f10441a;
        Objects.requireNonNull(mi0);
        Object obj = ThreadUtils.f10596a;
        if (i >= mi0.b) {
            mi0.c(i);
        }
    }
}
