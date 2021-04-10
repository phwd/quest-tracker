package X;

import android.os.Handler;
import android.os.Looper;

public class SR extends Handler {
    public SR() {
    }

    public SR(Looper looper) {
        super(looper);
    }

    public SR(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
