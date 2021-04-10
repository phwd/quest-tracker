package X;

import android.content.Context;
import android.util.SparseArray;
import com.facebook.analytics2.logger.DefaultHandlerThreadFactory;
import com.facebook.analytics2.logger.HandlerThreadFactory;

/* renamed from: X.7p  reason: invalid class name */
public final class AnonymousClass7p {
    public final Context A00;
    public final SparseArray A01 = new SparseArray(2);

    public static synchronized void A00(AnonymousClass7p r11, AnonymousClass7k r12, AnonymousClass7l r13) {
        synchronized (r11) {
            int i = r12.A00;
            fV fVVar = new fV(r11, i, r13);
            Context context = r11.A00;
            AnonymousClass6k A002 = AnonymousClass6k.A00(context);
            AnonymousClass7j r2 = r12.A01;
            HandlerThreadFactory handlerThreadFactory = (HandlerThreadFactory) AnonymousClass6k.A02(A002, A002.A02, r2.A07);
            if (handlerThreadFactory == null) {
                handlerThreadFactory = new DefaultHandlerThreadFactory(A002.A00);
                C0139Dd.A0E("ContextConstructorHelper", "Unable to create instance for HandlerThreadFactory");
            }
            SparseArray sparseArray = r11.A01;
            if (sparseArray.get(i) == null || ((AnonymousClass7o) sparseArray.get(i)).A00 == null) {
                int i2 = 19;
                if (r2.A02 == AnonymousClass09.A01) {
                    i2 = 18;
                }
                AnonymousClass7m r5 = new AnonymousClass7m(context, handlerThreadFactory.A1W(AnonymousClass08.A00("UploadJobHandlerManager-", i), i2), handlerThreadFactory.A4b(), r12, fVVar);
                AnonymousClass7o r0 = (AnonymousClass7o) sparseArray.get(i);
                if (r0 == null) {
                    r0 = new AnonymousClass7o();
                    sparseArray.put(i, r0);
                }
                r0.A00 = r5;
                r5.sendMessage(r5.obtainMessage(1));
            } else {
                throw new IllegalStateException(AnonymousClass08.A04("Trying to create a new handler when one already exists for jobId: ", String.valueOf(i)));
            }
        }
    }

    public AnonymousClass7p(Context context) {
        this.A00 = context;
    }
}
