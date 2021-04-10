package X;

import android.os.Handler;
import android.os.Looper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class AX {
    public final Handler A00;
    public final Map A01 = Collections.synchronizedMap(new HashMap());

    public static final void A00(AX ax, String str, Object obj) {
        if (str == null) {
            A00(ax, UUID.randomUUID().toString(), obj);
        } else {
            ax.A01.put(str, obj);
        }
    }

    public AX() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.A00 = handler;
    }
}
