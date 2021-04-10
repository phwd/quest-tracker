package X;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.2N8  reason: invalid class name */
public class AnonymousClass2N8 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.AsyncListDiffer$1$2";
    public final /* synthetic */ AnonymousClass2N9 A00;
    public final /* synthetic */ AnonymousClass2OT A01;

    public AnonymousClass2N8(AnonymousClass2N9 r1, AnonymousClass2OT r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    public final void run() {
        AnonymousClass2N9 r2 = this.A00;
        AnonymousClass2MJ r3 = r2.A01;
        if (r3.A00 == r2.A00) {
            List<T> list = r2.A03;
            AnonymousClass2OT r1 = this.A01;
            Runnable runnable = r2.A02;
            r3.A02 = list;
            r3.A03 = Collections.unmodifiableList(list);
            r1.A01(r3.A05);
            Iterator<AnonymousClass2NO<T>> it = r3.A06.iterator();
            while (it.hasNext()) {
                it.next();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
