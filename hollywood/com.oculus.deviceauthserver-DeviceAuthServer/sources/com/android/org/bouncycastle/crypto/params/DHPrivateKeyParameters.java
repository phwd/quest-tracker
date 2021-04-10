package com.android.org.bouncycastle.crypto.params;

import java.math.BigInteger;

public class DHPrivateKeyParameters extends DHKeyParameters {
    private BigInteger x;

    public DHPrivateKeyParameters(BigInteger x2, DHParameters params) {
        super(true, params);
        this.x = x2;
    }

    public BigInteger getX() {
        return this.x;
    }

    @Override // com.android.org.bouncycastle.crypto.params.DHKeyParameters
    public int hashCode() {
        return this.x.hashCode() ^ super.hashCode();
    }

    @Override // com.android.org.bouncycastle.crypto.params.DHKeyParameters
    public boolean equals(Object obj) {
        if ((obj instanceof DHPrivateKeyParameters) && ((DHPrivateKeyParameters) obj).getX().equals(this.x) && super.equals(obj)) {
            return true;
        }
        return false;
    }
}
