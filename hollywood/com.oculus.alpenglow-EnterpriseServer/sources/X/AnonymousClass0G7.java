package X;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0G7  reason: invalid class name */
public final class AnonymousClass0G7 {
    public static int A00(@NonNull Cursor cursor, @NonNull String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0) {
            return cursor.getColumnIndexOrThrow(AnonymousClass006.A07("`", str, "`"));
        }
        return columnIndex;
    }
}
