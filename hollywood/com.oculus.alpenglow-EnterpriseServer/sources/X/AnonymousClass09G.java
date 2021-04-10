package X;

import android.content.Context;
import android.graphics.Typeface;
import java.util.concurrent.Callable;

/* renamed from: X.09G  reason: invalid class name */
public class AnonymousClass09G implements Callable<AnonymousClass09X> {
    public final /* synthetic */ int A00;
    public final /* synthetic */ Context A01;
    public final /* synthetic */ AnonymousClass09F A02;
    public final /* synthetic */ String A03;

    public AnonymousClass09G(Context context, AnonymousClass09F r2, int i, String str) {
        this.A01 = context;
        this.A02 = r2;
        this.A00 = i;
        this.A03 = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final AnonymousClass09X call() throws Exception {
        AnonymousClass09X A002 = AnonymousClass09Y.A00(this.A01, this.A02, this.A00);
        Typeface typeface = A002.A01;
        if (typeface != null) {
            AnonymousClass09Y.A00.A01(this.A03, typeface);
        }
        return A002;
    }
}
