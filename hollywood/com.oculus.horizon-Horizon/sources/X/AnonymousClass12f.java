package X;

import android.content.ContentResolver;
import android.content.Context;

/* renamed from: X.12f  reason: invalid class name */
public final class AnonymousClass12f {
    public final ContentResolver A00;
    public final AnonymousClass20E A01;

    public AnonymousClass12f(Context context) {
        AnonymousClass20E r1 = new AnonymousClass20E(context, context.getPackageManager());
        ContentResolver contentResolver = context.getContentResolver();
        this.A01 = r1;
        this.A00 = contentResolver;
    }
}
