package X;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.File;

/* renamed from: X.1ba  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07081ba<DataT> implements AbstractC07401c9<Uri, DataT> {
    public final Context A00;
    public final Class<DataT> A01;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Uri, DataT> A1o(@NonNull C07381c7 r6) {
        Context context = this.A00;
        Class<DataT> cls = this.A01;
        return new C07071bZ(context, r6.A00(File.class, cls), r6.A00(Uri.class, cls), cls);
    }

    public AbstractC07081ba(Context context, Class<DataT> cls) {
        this.A00 = context;
        this.A01 = cls;
    }
}
