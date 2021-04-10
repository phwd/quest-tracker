package X;

import android.database.SQLException;

public final class DV extends SQLException {
    public DV() {
        super("Cannot rename to a null column name.");
    }
}
