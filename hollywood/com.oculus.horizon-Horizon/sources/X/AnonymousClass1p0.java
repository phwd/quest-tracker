package X;

import android.content.ContentResolver;
import android.graphics.Rect;
import com.oculus.horizon.linkedaccounts.database.contract.LinkedAccountsSQLiteContract;
import java.util.concurrent.Executor;

/* renamed from: X.1p0  reason: invalid class name */
public final class AnonymousClass1p0 extends AnonymousClass1pU implements AnonymousClass1p8<AnonymousClass1qQ> {
    public static final Rect A01 = new Rect(0, 0, 96, 96);
    public static final Rect A02 = new Rect(0, 0, 512, 384);
    public static final String[] A03 = {LinkedAccountsSQLiteContract.Columns._ID, "_data"};
    public static final String[] A04 = {"_data"};
    public final ContentResolver A00;

    public AnonymousClass1p0(Executor executor, AnonymousClass1pV r2, ContentResolver contentResolver) {
        super(executor, r2);
        this.A00 = contentResolver;
    }
}
