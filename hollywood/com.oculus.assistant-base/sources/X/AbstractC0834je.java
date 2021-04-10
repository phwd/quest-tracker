package X;

import android.database.Cursor;
import android.util.Log;

/* renamed from: X.je  reason: case insensitive filesystem */
public abstract class AbstractC0834je implements AbstractC0133Ct {
    public Exception A00;
    public final Cursor A01;

    public static void A00(AbstractC0834je jeVar) {
        if (jeVar.A01.isClosed()) {
            String stackTraceString = Log.getStackTraceString(jeVar.A00);
            C0139Dd.A0A("AbstractDAOItem", stackTraceString);
            throw new IllegalStateException(AnonymousClass08.A04("Can't access DAO when it is already closed: ", stackTraceString));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A01.close();
        this.A00 = new Exception();
    }

    public AbstractC0834je(Cursor cursor) {
        if (cursor != null) {
            this.A01 = cursor;
            return;
        }
        throw new IllegalArgumentException("cursor is null");
    }
}
