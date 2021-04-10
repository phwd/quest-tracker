package X;

import android.app.Application;

public final class BX {
    public static volatile Application A00;

    public static Application A00() {
        if (A00 != null) {
            return A00;
        }
        throw new IllegalStateException("ApplicationHolder#set never called");
    }
}
