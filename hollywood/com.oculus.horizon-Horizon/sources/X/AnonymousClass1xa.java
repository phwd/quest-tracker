package X;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* renamed from: X.1xa  reason: invalid class name */
public class AnonymousClass1xa implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.cameracore.recording.controller.RecordingControllerImpl$MessageHandlerCallback$2";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AbstractC11151xn A01;
    public final /* synthetic */ C11071xZ A02;
    public final /* synthetic */ Object A03;
    public final /* synthetic */ String A04;
    public final /* synthetic */ CountDownLatch A05;

    public final void run() {
        String str = this.A04;
        this.A05.countDown();
        try {
            int i = this.A00;
            if (i == 4 || !this.A02.A02.A05.get()) {
                C11061xY r1 = this.A02.A02;
                AbstractC11151xn r7 = this.A01;
                Object obj = this.A03;
                if (i == 1) {
                    Object[] objArr = (Object[]) obj;
                    C11211xt r15 = r1.A03;
                    List list = (List) objArr[0];
                    C11211xt.A00(r15, list, new AnonymousClass1yL(r15, (AbstractC11131xk) objArr[1], (Handler) objArr[2], r7, list));
                } else if (i == 2) {
                    Object[] objArr2 = (Object[]) obj;
                    r1.A03.A06(r7, (C11191xr) objArr2[0], (AnonymousClass1xh) objArr2[1]);
                } else if (i == 3) {
                    Object[] objArr3 = (Object[]) obj;
                    C11211xt r152 = r1.A03;
                    List list2 = (List) objArr3[0];
                    C11211xt.A00(r152, list2, new AnonymousClass1yL(r152, new AnonymousClass1xj(r152, r7, (C11191xr) objArr3[1], (AnonymousClass1xh) objArr3[2]), r152.A00, null, list2));
                } else if (i == 4) {
                    r1.A03.A05(r7);
                    r1.A05.set(false);
                } else if (i == 5) {
                    r1.A03.A04(r7);
                } else {
                    throw new RuntimeException(AnonymousClass006.A01("Unknown Recording Operation: ", i));
                }
            } else {
                this.A01.A6B();
            }
        } catch (Exception e) {
            AnonymousClass0NO.A0B("RecordingControllerImpl", String.format(null, "Exception during operation %s", str), e);
            C11071xZ r0 = this.A02;
            AbstractC11091xe r5 = r0.A00;
            C11061xY r12 = r0.A02;
            r5.A5R("recording_controller_error", "RecordingControllerImpl", (long) r12.hashCode(), r12.A03.A01(), new C11081xd(e), "high", str);
            throw e;
        }
    }

    public AnonymousClass1xa(C11071xZ r1, String str, CountDownLatch countDownLatch, int i, AbstractC11151xn r5, Object obj) {
        this.A02 = r1;
        this.A04 = str;
        this.A05 = countDownLatch;
        this.A00 = i;
        this.A01 = r5;
        this.A03 = obj;
    }
}
