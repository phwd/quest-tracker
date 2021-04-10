package X;

import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.SqliteHolder;

/* renamed from: X.1RW  reason: invalid class name */
public class AnonymousClass1RW extends Database.InitializedCallback {
    public final /* synthetic */ C06261Yh A00;

    public AnonymousClass1RW(C06261Yh r1) {
        this.A00 = r1;
    }

    @Override // com.facebook.msys.mci.Database.InitializedCallback
    public final void onInit(SqliteHolder sqliteHolder) {
        this.A00.A01.onInit(sqliteHolder);
    }
}
