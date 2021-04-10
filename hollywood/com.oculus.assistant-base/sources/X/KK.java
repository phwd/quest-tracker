package X;

import android.os.StrictMode;
import java.io.File;
import java.util.Map;

public abstract class KK {
    public final int A04(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        Object obj;
        int A07;
        if (!(this instanceof C0965ph)) {
            return ((C0964pg) this).A02.A04(str, i, threadPolicy);
        }
        C0965ph phVar = (C0965ph) this;
        if (!(phVar instanceof VS)) {
            return phVar.A07(str, i, phVar.A00, threadPolicy);
        }
        VS vs = (VS) phVar;
        Map map = vs.A03;
        synchronized (map) {
            obj = map.get(str);
            if (obj == null) {
                obj = new Object();
                map.put(str, obj);
            }
        }
        synchronized (obj) {
            A07 = vs.A07(str, i, ((C0965ph) vs).A00, threadPolicy);
        }
        return A07;
    }

    public final String A05(String str) {
        if (this instanceof C0965ph) {
            File file = new File(((C0965ph) this).A00, str);
            if (file.exists()) {
                return file.getCanonicalPath();
            }
            return null;
        } else if (this instanceof C0964pg) {
            return ((C0964pg) this).A02.A05(str);
        } else {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01cf, code lost:
        if ((r38 & 2) == 0) goto L_0x0531;
     */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0294 A[Catch:{ Exception -> 0x027b, all -> 0x05bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x05a9  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06(int r38) {
        /*
        // Method dump skipped, instructions count: 1525
        */
        throw new UnsupportedOperationException("Method not decompiled: X.KK.A06(int):void");
    }

    public String toString() {
        return getClass().getName();
    }
}
