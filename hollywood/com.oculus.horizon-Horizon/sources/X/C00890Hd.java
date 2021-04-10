package X;

import android.content.Context;
import android.util.SparseArray;
import com.facebook.analytics2.logger.DefaultHandlerThreadFactory;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0Hd  reason: invalid class name and case insensitive filesystem */
public final class C00890Hd {
    public final Context A00;
    @GuardedBy("this")
    public final SparseArray<AnonymousClass0Hc> A01 = new SparseArray<>(2);

    public static synchronized void A00(C00890Hd r11, AnonymousClass0HY r12, AnonymousClass0HZ r13) {
        synchronized (r11) {
            int i = r12.A00;
            AnonymousClass0q5 r10 = new AnonymousClass0q5(r11, i, r13);
            Context context = r11.A00;
            AnonymousClass0GM A002 = AnonymousClass0GM.A00(context);
            AnonymousClass0HX r2 = r12.A01;
            HandlerThreadFactory handlerThreadFactory = (HandlerThreadFactory) AnonymousClass0GM.A02(A002, A002.A02, r2.A07);
            if (handlerThreadFactory == null) {
                handlerThreadFactory = new DefaultHandlerThreadFactory(A002.A00);
                AnonymousClass0NO.A0A("ContextConstructorHelper", "Unable to create instance for HandlerThreadFactory");
            }
            SparseArray<AnonymousClass0Hc> sparseArray = r11.A01;
            if (sparseArray.get(i) == null || sparseArray.get(i).A00 == null) {
                int i2 = 19;
                if (r2.A02 == AnonymousClass007.A01) {
                    i2 = 18;
                }
                AnonymousClass0Ha r5 = new AnonymousClass0Ha(context, handlerThreadFactory.A20(AnonymousClass006.A01("UploadJobHandlerManager-", i), i2), handlerThreadFactory.A7m(), r12, r10);
                AnonymousClass0Hc r0 = sparseArray.get(i);
                if (r0 == null) {
                    r0 = new AnonymousClass0Hc();
                    sparseArray.put(i, r0);
                }
                r0.A00 = r5;
                r5.sendMessage(r5.obtainMessage(1));
            } else {
                throw new IllegalStateException(AnonymousClass006.A05("Trying to create a new handler when one already exists for jobId: ", String.valueOf(i)));
            }
        }
    }

    public C00890Hd(Context context) {
        this.A00 = context;
    }
}
