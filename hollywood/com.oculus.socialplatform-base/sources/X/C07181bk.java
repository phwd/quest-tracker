package X;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;

/* renamed from: X.1bk  reason: invalid class name and case insensitive filesystem */
public final class C07181bk<Data> implements AbstractC07011bT<Integer, Data> {
    public final Resources A00;
    public final AbstractC07011bT<Uri, Data> A01;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb A1r(@NonNull Integer num, int i, int i2, @NonNull AnonymousClass1cO r9) {
        Integer num2 = num;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("android.resource://");
            Resources resources = this.A00;
            int intValue = num2.intValue();
            sb.append(resources.getResourcePackageName(intValue));
            sb.append('/');
            sb.append(resources.getResourceTypeName(intValue));
            sb.append('/');
            sb.append(resources.getResourceEntryName(intValue));
            Uri parse = Uri.parse(sb.toString());
            if (parse != null) {
                return this.A01.A1r(parse, i, i2, r9);
            }
            return null;
        } catch (Resources.NotFoundException e) {
            if (!Log.isLoggable("ResourceLoader", 5)) {
                return null;
            }
            StringBuilder sb2 = new StringBuilder("Received invalid resource id: ");
            sb2.append(num2);
            Log.w("ResourceLoader", sb2.toString(), e);
            return null;
        }
    }

    public C07181bk(Resources resources, AbstractC07011bT<Uri, Data> r2) {
        this.A00 = resources;
        this.A01 = r2;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Integer num) {
        return true;
    }
}
