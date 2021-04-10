package X;

import java.util.List;

/* renamed from: X.2N9  reason: invalid class name */
public class AnonymousClass2N9 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.AsyncListDiffer$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass2MJ A01;
    public final /* synthetic */ Runnable A02;
    public final /* synthetic */ List A03;
    public final /* synthetic */ List A04;

    public AnonymousClass2N9(AnonymousClass2MJ r1, List list, List list2, int i, Runnable runnable) {
        this.A01 = r1;
        this.A04 = list;
        this.A03 = list2;
        this.A00 = i;
        this.A02 = runnable;
    }

    public final void run() {
        this.A01.A01.execute(new AnonymousClass2N8(this, AnonymousClass2OV.A00(new AnonymousClass2NA(this))));
    }
}
