package X;

import android.content.Context;
import android.util.SparseArray;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class HE {
    public final Context A00;
    @GuardedBy("this")
    public final SparseArray<HD> A01 = new SparseArray<>(2);

    public static synchronized void A00(HE he, H9 h9, HA ha) {
        synchronized (he) {
            int i = h9.A00;
            YY yy = new YY(he, i, ha);
            Context context = he.A00;
            G7 A002 = G7.A00(context);
            H8 h8 = h9.A01;
            HandlerThreadFactory A03 = A002.A03(h8.A07);
            SparseArray<HD> sparseArray = he.A01;
            if (sparseArray.get(i) == null || sparseArray.get(i).A00 == null) {
                Integer num = h8.A02;
                int i2 = 19;
                if (num == AnonymousClass07.A01) {
                    i2 = 18;
                }
                HB hb = new HB(context, A03.A1f(AnonymousClass06.A01("UploadJobHandlerManager-", i), i2), A03.A4O(), h9, yy);
                HD hd = sparseArray.get(i);
                if (hd == null) {
                    hd = new HD();
                    sparseArray.put(i, hd);
                }
                hd.A00 = hb;
                hb.sendMessage(hb.obtainMessage(1));
            } else {
                throw new IllegalStateException(AnonymousClass06.A04("Trying to create a new handler when one already exists for jobId: ", String.valueOf(i)));
            }
        }
    }

    public HE(Context context) {
        this.A00 = context;
    }
}
