package X;

import android.content.Context;
import androidx.annotation.NonNull;
import com.oculus.alpenglow.database.ApplicationDatabase;

/* renamed from: X.0Fm  reason: invalid class name and case insensitive filesystem */
public final class C01230Fm {
    /* JADX WARN: Incorrect args count in method signature: <T:LX/0Fr;>(Landroid/content/Context;Ljava/lang/Class<TT;>;Ljava/lang/String;)LX/0Fn<TT;>; */
    @NonNull
    public static AnonymousClass0Fn A00(@NonNull Context context) {
        if (ApplicationDatabase.DATABASE_NAME.trim().length() != 0) {
            return new AnonymousClass0Fn(context);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }
}
