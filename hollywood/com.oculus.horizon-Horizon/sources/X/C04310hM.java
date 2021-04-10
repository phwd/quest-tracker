package X;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: X.0hM  reason: invalid class name and case insensitive filesystem */
public final class C04310hM implements AbstractC03260cY {
    @Override // X.AbstractC03260cY
    public final String A3i(String str) throws IOException {
        ReentrantReadWriteLock reentrantReadWriteLock = C03160cK.A06;
        reentrantReadWriteLock.readLock().lock();
        try {
            String str2 = null;
            if (C03160cK.A04 != null) {
                int i = 0;
                while (str2 == null) {
                    AbstractC03170cL[] r1 = C03160cK.A04;
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

    @Override // X.AbstractC03260cY
    public final boolean A5F(String str, int i) {
        int i2 = 0;
        if ((i & 1) != 0) {
            i2 = 16;
        }
        return C03160cK.A05(str, i2 | 0);
    }

    @Override // X.AbstractC03260cY
    public final int A4O() {
        return C03160cK.A0A;
    }
}
