package X;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: X.0iq  reason: invalid class name and case insensitive filesystem */
public final class C02480iq implements AnonymousClass0lE {
    @Override // X.AnonymousClass0lE
    public final String A4F(String str) throws IOException {
        ReentrantReadWriteLock reentrantReadWriteLock = AnonymousClass0l0.A06;
        reentrantReadWriteLock.readLock().lock();
        try {
            String str2 = null;
            if (AnonymousClass0l0.A04 != null) {
                int i = 0;
                while (str2 == null) {
                    AnonymousClass0l1[] r1 = AnonymousClass0l0.A04;
                    if (i >= r1.length) {
                        break;
                    }
                    str2 = r1[i].A06(str);
                    i++;
                }
            }
            return str2;
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // X.AnonymousClass0lE
    public final boolean A6I(String str, int i) {
        int i2 = 0;
        if ((i & 1) != 0) {
            i2 = 16;
        }
        return AnonymousClass0l0.A07(str, i2 | 0);
    }

    @Override // X.AnonymousClass0lE
    public final int A4x() {
        return AnonymousClass0l0.A0A;
    }
}
