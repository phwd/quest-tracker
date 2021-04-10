package defpackage;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: EB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class EB1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Status f7943a = new Status(8, "The connection to Google Play services was lost");
    public static final BasePendingResult[] b = new BasePendingResult[0];
    public final Set c = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    public final FB1 d = new DB1(this);
    public final Map e;

    public EB1(Map map) {
        this.e = map;
    }

    public final void a() {
        boolean z;
        BasePendingResult[] basePendingResultArr = (BasePendingResult[]) this.c.toArray(b);
        for (BasePendingResult basePendingResult : basePendingResultArr) {
            basePendingResult.h.set(null);
            synchronized (basePendingResult.b) {
                if (((YV) basePendingResult.d.get()) == null || !basePendingResult.n) {
                    basePendingResult.a();
                }
                synchronized (basePendingResult.b) {
                    z = basePendingResult.l;
                }
            }
            if (z) {
                this.c.remove(basePendingResult);
            }
        }
    }

    public final void b(BasePendingResult basePendingResult) {
        this.c.add(basePendingResult);
        basePendingResult.h.set(this.d);
    }
}
