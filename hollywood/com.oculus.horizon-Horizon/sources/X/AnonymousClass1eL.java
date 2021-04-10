package X;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Utility;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: X.1eL  reason: invalid class name */
public class AnonymousClass1eL implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.appevents.AppEventsLogger$4";

    public final void run() {
        HashSet hashSet = new HashSet();
        synchronized (AppEventsLogger.A03) {
            for (AnonymousClass1gV r0 : AppEventsLogger.A04.keySet()) {
                hashSet.add(r0.applicationId);
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Utility.queryAppSettings((String) it.next(), true);
        }
    }
}
