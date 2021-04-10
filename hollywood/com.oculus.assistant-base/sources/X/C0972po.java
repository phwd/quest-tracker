package X;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: X.po  reason: case insensitive filesystem */
public final class C0972po implements KW {
    @Override // X.KW
    public final String A2a(String str) {
        ReentrantReadWriteLock reentrantReadWriteLock = KJ.A06;
        reentrantReadWriteLock.readLock().lock();
        try {
            String str2 = null;
            if (KJ.A04 != null) {
                int i = 0;
                while (str2 == null) {
                    KK[] kkArr = KJ.A04;
                    if (i >= kkArr.length) {
                        break;
                    }
                    str2 = kkArr[i].A05(str);
                    i++;
                }
            }
            return str2;
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // X.KW
    public final boolean A3i(String str, int i) {
        int i2 = 0;
        if ((i & 1) != 0) {
            i2 = 16;
        }
        return KJ.A05(str, i2 | 0);
    }

    @Override // X.KW
    public final int A2u() {
        return KJ.A0A;
    }
}
