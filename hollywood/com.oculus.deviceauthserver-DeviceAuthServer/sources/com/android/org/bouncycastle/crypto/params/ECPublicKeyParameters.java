package com.android.org.bouncycastle.crypto.params;

import com.android.org.bouncycastle.math.ec.ECPoint;

public class ECPublicKeyParameters extends ECKeyParameters {
    private final ECPoint Q;

    public ECPublicKeyParameters(ECPoint Q2, ECDomainParameters params) {
        super(false, params);
        this.Q = ECDomainParameters.validate(params.getCurve(), Q2);
    }

    public ECPoint getQ() {
        return this.Q;
    }
}
