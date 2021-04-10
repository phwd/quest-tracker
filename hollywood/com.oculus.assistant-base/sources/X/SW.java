package X;

import android.os.Handler;
import android.os.Looper;

public class SW extends Handler {
    public SW() {
    }

    public SW(Looper looper) {
        super(looper);
    }

    public SW(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
