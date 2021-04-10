package com.google.common.collect;

/* access modifiers changed from: package-private */
public final class Hashing {
    static int smear(int hashCode) {
        return (int) (461845907 * ((long) Integer.rotateLeft((int) (((long) hashCode) * -862048943), 15)));
    }

    static int smearedHash(Object o) {
        return smear(o == null ? 0 : o.hashCode());
    }
}
