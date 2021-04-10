package X;

import com.facebook.analytics2.logger.EventListener;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class IF extends AbstractC0274Yy {
    public final IW<EventListener> A00 = new IW<>();

    @Override // X.AbstractC0274Yy
    public final void A00(int i) {
        List<T> list;
        IW<EventListener> iw = this.A00;
        synchronized (iw) {
            list = iw.A02;
            iw.A00++;
        }
        try {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                list.get(i2).A00(i);
            }
        } finally {
            iw.A00();
        }
    }

    @Override // X.AbstractC0274Yy
    public final void A01(YE ye) {
        List<T> list;
        IW<EventListener> iw = this.A00;
        synchronized (iw) {
            list = iw.A02;
            iw.A00++;
        }
        try {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                T t = list.get(i);
                if (t != null) {
                    t.A01(ye);
                } else {
                    throw new UnsupportedOperationException("The debug event listener expects only onEventReceivedWithParamsCollectionMap to be called. For production, use EventListener instead.");
                }
            }
        } finally {
            iw.A00();
        }
    }

    public final void A02(AbstractC0274Yy yy) {
        IW<EventListener> iw = this.A00;
        synchronized (iw) {
            if (iw.A00 > 0) {
                ArrayList<T> arrayList = iw.A01;
                int size = arrayList.size();
                ArrayList<T> arrayList2 = new ArrayList<>(size + 1);
                iw.A01 = arrayList2;
                iw.A02 = Collections.unmodifiableList(arrayList2);
                for (int i = 0; i < size; i++) {
                    iw.A01.add(arrayList.get(i));
                }
            }
            iw.A01.add(yy);
        }
    }
}
