package defpackage;

import J.N;
import org.chromium.components.messages.MessageWrapper;

/* renamed from: rk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4622rk0 implements Runnable {
    public final MessageWrapper F;

    public RunnableC4622rk0(MessageWrapper messageWrapper) {
        this.F = messageWrapper;
    }

    public void run() {
        long j = this.F.f10854a;
        if (j != 0) {
            N.MVTCdx$k(j);
        }
    }
}
