package X;

import android.content.Context;
import java.io.File;

/* renamed from: X.1oc  reason: invalid class name and case insensitive filesystem */
public class C09591oc implements AnonymousClass0KW<File> {
    public final /* synthetic */ C09581ob A00;

    public C09591oc(C09581ob r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0KW
    public final File get() {
        Context context = this.A00.A00;
        if (context != null) {
            return context.getApplicationContext().getCacheDir();
        }
        throw null;
    }
}
