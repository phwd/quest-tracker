package X;

import android.database.Cursor;
import android.database.SQLException;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;

/* renamed from: X.0GQ  reason: invalid class name */
public interface AnonymousClass0GQ extends Closeable {
    void A1H();

    AbstractC03360cA A1l(String str);

    void A2K();

    void A2Q(String str) throws SQLException;

    List<Pair<String, String>> A30();

    boolean A58();

    Cursor A73(AnonymousClass0GX v);

    Cursor A74(String str);

    void A8D();

    String getPath();

    boolean isOpen();
}
