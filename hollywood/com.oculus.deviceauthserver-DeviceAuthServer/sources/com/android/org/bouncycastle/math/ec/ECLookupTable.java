package com.android.org.bouncycastle.math.ec;

public interface ECLookupTable {
    int getSize();

    ECPoint lookup(int i);
}
