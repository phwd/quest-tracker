package X;

import android.os.Handler;
import android.os.Message;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1xZ  reason: invalid class name and case insensitive filesystem */
public class C11071xZ implements Handler.Callback {
    public AbstractC11091xe A00;
    public final Handler A01;
    public final /* synthetic */ C11061xY A02;

    private void A00(int i, String str, int i2) {
        C11081xd r2 = new C11081xd(i, String.format(null, "%s, msg %s", str, C11061xY.A00(i2)));
        C11211xt r1 = this.A02.A03;
        r2.A01(r1.A02());
        r1.A03(r2);
    }

    public C11071xZ(C11061xY r1, Handler handler, AbstractC11091xe r3) {
        this.A02 = r1;
        this.A01 = handler;
        this.A00 = r3;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        Object obj = message.obj;
        CountDownLatch countDownLatch = new CountDownLatch(2);
        String A002 = C11061xY.A00(i);
        if (this.A01.post(new AnonymousClass1xa(this, A002, countDownLatch, i, new C11101xf(this, countDownLatch), obj))) {
            try {
                if (!countDownLatch.await(10, TimeUnit.SECONDS)) {
                    if (countDownLatch.getCount() == 2) {
                        A00(20002, "Timeout while waiting for operation to start executing", i);
                    } else if (countDownLatch.getCount() == 1) {
                        A00(20001, "Timeout while waiting for operation to finish", i);
                    }
                }
            } catch (InterruptedException e) {
                AbstractC11091xe r3 = this.A00;
                C11061xY r1 = this.A02;
                r3.A5R("recording_controller_error", "RecordingControllerImpl", (long) r1.hashCode(), r1.A03.A01(), new C11081xd(e), "high", A002);
                throw new RuntimeException("Message thread was interrupted");
            }
        } else {
            A00(20003, "Couldn't pass operation to queue, most likely it is exiting", i);
        }
        if (i == 5) {
            C11061xY r2 = this.A02;
            C09541mY.A01(r2.A00, false, true);
            C09541mY.A01(r2.A01, false, true);
        }
        return true;
    }
}
