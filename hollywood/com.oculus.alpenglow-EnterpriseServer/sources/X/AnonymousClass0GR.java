package X;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.annotation.NonNull;
import java.io.File;

/* renamed from: X.0GR  reason: invalid class name */
public abstract class AnonymousClass0GR {
    public final int A00;

    public void A02(@NonNull AnonymousClass0GQ r1) {
    }

    public abstract void A03(@NonNull AnonymousClass0GQ v);

    public abstract void A05(@NonNull AnonymousClass0GQ v, int i, int i2);

    public static void A00(String str) {
        if (!str.equalsIgnoreCase(":memory:") && str.trim().length() != 0) {
            Log.w("SupportSQLite", AnonymousClass006.A05("deleting the database file: ", str));
            try {
                SQLiteDatabase.deleteDatabase(new File(str));
            } catch (Exception e) {
                Log.w("SupportSQLite", "delete failed: ", e);
            }
        }
    }

    public void A04(@NonNull AnonymousClass0GQ r3, int i, int i2) {
        throw new SQLiteException(AnonymousClass006.A03("Can't downgrade database from version ", i, " to ", i2));
    }

    public AnonymousClass0GR(int i) {
        this.A00 = i;
    }
}
