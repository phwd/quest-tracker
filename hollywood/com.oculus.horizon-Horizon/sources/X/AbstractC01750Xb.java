package X;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0Xb  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01750Xb {
    public final ExecutorService A00;
    public final boolean A01;

    public abstract Socket A01(Socket socket, String str, int i) throws IOException;

    public static Socket A00(AbstractC01750Xb r3, Socket socket, String str, int i, long j) throws IOException {
        AnonymousClass1mA r2 = new AnonymousClass1mA(socket, str, i, new C06290mq());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        r2.addHandshakeCompletedListener(new C01740Xa(r3, countDownLatch));
        r2.startHandshake();
        try {
            if (countDownLatch.await(j, TimeUnit.MILLISECONDS)) {
                return r2;
            }
            throw new SocketTimeoutException("handshakeAndVerifySocket timeout");
        } catch (InterruptedException e) {
            StringBuilder sb = new StringBuilder("handshakeAndVerifySocket failed because of ");
            sb.append(e);
            throw new IOException(sb.toString());
        }
    }

    public AbstractC01750Xb(ExecutorService executorService, boolean z) {
        this.A00 = executorService;
        this.A01 = z;
    }
}
