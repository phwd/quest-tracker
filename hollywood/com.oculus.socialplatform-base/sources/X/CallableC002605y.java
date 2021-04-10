package X;

import android.content.Context;
import android.graphics.Typeface;
import java.util.concurrent.Callable;

/* renamed from: X.05y  reason: invalid class name and case insensitive filesystem */
public class CallableC002605y implements Callable<AnonymousClass06F> {
    public final /* synthetic */ int A00;
    public final /* synthetic */ Context A01;
    public final /* synthetic */ C002505x A02;
    public final /* synthetic */ String A03;

    public CallableC002605y(Context context, C002505x r2, int i, String str) {
        this.A01 = context;
        this.A02 = r2;
        this.A00 = i;
        this.A03 = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final AnonymousClass06F call() throws Exception {
        AnonymousClass06F A002 = AnonymousClass06G.A00(this.A01, this.A02, this.A00);
        Typeface typeface = A002.A01;
        if (typeface != null) {
            AnonymousClass06G.A00.A01(this.A03, typeface);
        }
        return A002;
    }
}
