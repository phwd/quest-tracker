package X;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: X.sN  reason: case insensitive filesystem */
public final class C0496sN implements AbstractC0432ho {
    @Override // X.AbstractC0432ho
    public final String A2a(String str) throws IOException {
        ReentrantReadWriteLock reentrantReadWriteLock = C0484rn.A07;
        reentrantReadWriteLock.readLock().lock();
        try {
            String str2 = null;
            if (C0484rn.A05 != null) {
                int i = 0;
                while (str2 == null) {
                    AbstractC0507sb[] sbVarArr = C0484rn.A05;
                    if (i >= sbVarArr.length) {
                        break;
                    }
                    str2 = sbVarArr[i].A06(str);
                    i++;
                }
            }
            return str2;
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // X.AbstractC0432ho
    public final boolean A3P(String str, int i) {
        int i2 = 0;
        if ((i & 1) != 0) {
            i2 = 16;
        }
        return C0484rn.A02(str, i2 | 0);
    }

    @Override // X.AbstractC0432ho
    public final int A2s() {
        return C0484rn.A0B;
    }
}
