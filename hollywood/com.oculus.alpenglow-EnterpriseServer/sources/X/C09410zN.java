package X;

import java.util.concurrent.CountDownLatch;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;

/* renamed from: X.0zN  reason: invalid class name and case insensitive filesystem */
public class C09410zN implements HandshakeCompletedListener {
    public final /* synthetic */ AnonymousClass0z6 A00;
    public final /* synthetic */ CountDownLatch A01;

    public C09410zN(AnonymousClass0z6 r1, CountDownLatch countDownLatch) {
        this.A00 = r1;
        this.A01 = countDownLatch;
    }

    public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
        this.A01.countDown();
    }
}
