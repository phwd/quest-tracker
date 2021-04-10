package X;

import android.content.ContentResolver;
import com.oculus.horizon.linkedaccounts.database.contract.LinkedAccountsSQLiteContract;
import java.util.concurrent.Executor;

/* renamed from: X.1ka  reason: invalid class name and case insensitive filesystem */
public final class C09491ka extends AnonymousClass1pU {
    public static final String[] A01 = {LinkedAccountsSQLiteContract.Columns._ID, "_data"};
    public final ContentResolver A00;

    public C09491ka(Executor executor, AnonymousClass1pV r2, ContentResolver contentResolver) {
        super(executor, r2);
        this.A00 = contentResolver;
    }
}
