package X;

import android.content.Context;
import java.io.File;

/* renamed from: X.1ju  reason: invalid class name and case insensitive filesystem */
public class C09831ju implements AbstractC00750Ik<File> {
    public final /* synthetic */ AnonymousClass1lV A00;

    public C09831ju(AnonymousClass1lV r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC00750Ik
    public final File get() {
        Context context = this.A00.A00;
        if (context != null) {
            return context.getApplicationContext().getCacheDir();
        }
        throw null;
    }
}
