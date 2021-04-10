package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: s81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4696s81 implements Runnable {
    public final TabImpl F;
    public final AbstractC5818ym0 G;

    public RunnableC4696s81(TabImpl tabImpl, AbstractC5818ym0 ym0) {
        this.F = tabImpl;
        this.G = ym0;
    }

    public void run() {
        TabImpl tabImpl = this.F;
        AbstractC5818ym0 ym0 = this.G;
        tabImpl.K = ym0;
        Objects.requireNonNull(ym0);
        long j = tabImpl.F;
        IT it = (IT) tabImpl.K;
        N.MhCci$0r(j, it.f8227a, it.b);
        tabImpl.m0(0);
    }
}
