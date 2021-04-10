package X;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AY {
    public final Handler A00;
    public final CopyOnWriteArrayList A01 = new CopyOnWriteArrayList();

    public AY() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.A00 = handler;
    }
}
