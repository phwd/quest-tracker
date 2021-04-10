package X;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/* renamed from: X.1na  reason: invalid class name */
public final class AnonymousClass1na {
    public final AnonymousClass1ng A00 = new AnonymousClass1ng();
    public final Map<String, AnonymousClass1ni> A01 = new HashMap();

    public final void A00(String str) {
        AnonymousClass1ni r4;
        synchronized (this) {
            Map<String, AnonymousClass1ni> map = this.A01;
            AnonymousClass1ni r42 = map.get(str);
            AnonymousClass1S2.A00(r42);
            r4 = r42;
            int i = r4.A00;
            if (i >= 1) {
                int i2 = i - 1;
                r4.A00 = i2;
                if (i2 == 0) {
                    AnonymousClass1ni remove = map.remove(str);
                    if (remove.equals(r4)) {
                        Queue<AnonymousClass1ni> queue = this.A00.A00;
                        synchronized (queue) {
                            if (queue.size() < 10) {
                                queue.offer(remove);
                            }
                        }
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Removed the wrong lock, expected to remove: ");
                        sb.append(r4);
                        sb.append(", but actually removed: ");
                        sb.append(remove);
                        sb.append(", safeKey: ");
                        sb.append(str);
                        throw new IllegalStateException(sb.toString());
                    }
                }
            } else {
                throw new IllegalStateException(AnonymousClass006.A0A("Cannot release a lock that is not held, safeKey: ", str, ", interestedThreads: ", i));
            }
        }
        r4.A01.unlock();
    }
}
