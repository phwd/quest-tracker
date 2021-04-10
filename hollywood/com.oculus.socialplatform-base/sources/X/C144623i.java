package X;

import java.util.concurrent.CountDownLatch;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;

/* renamed from: X.23i  reason: invalid class name and case insensitive filesystem */
public class C144623i implements HandshakeCompletedListener {
    public final /* synthetic */ AnonymousClass23V A00;
    public final /* synthetic */ CountDownLatch A01;

    public C144623i(AnonymousClass23V r1, CountDownLatch countDownLatch) {
        this.A00 = r1;
        this.A01 = countDownLatch;
    }

    public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
        this.A01.countDown();
    }
}
