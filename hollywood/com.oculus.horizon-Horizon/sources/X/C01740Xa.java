package X;

import java.util.concurrent.CountDownLatch;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;

/* renamed from: X.0Xa  reason: invalid class name and case insensitive filesystem */
public class C01740Xa implements HandshakeCompletedListener {
    public final /* synthetic */ AbstractC01750Xb A00;
    public final /* synthetic */ CountDownLatch A01;

    public C01740Xa(AbstractC01750Xb r1, CountDownLatch countDownLatch) {
        this.A00 = r1;
        this.A01 = countDownLatch;
    }

    public final void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
        this.A01.countDown();
    }
}
