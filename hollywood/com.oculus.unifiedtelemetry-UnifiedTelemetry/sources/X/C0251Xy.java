package X;

import android.content.Context;

/* renamed from: X.Xy  reason: case insensitive filesystem */
public class C0251Xy implements IO<Context, AnonymousClass05> {
    public final /* synthetic */ C0250Xx A00;

    public C0251Xy(C0250Xx xx) {
        this.A00 = xx;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.IO
    public final AnonymousClass05 A3N(Context context) {
        return new AnonymousClass05(this.A00.A01, context);
    }
}
