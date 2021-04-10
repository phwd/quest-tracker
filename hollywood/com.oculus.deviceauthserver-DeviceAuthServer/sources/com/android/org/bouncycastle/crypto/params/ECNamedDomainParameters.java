package com.android.org.bouncycastle.crypto.params;

import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.math.ec.ECConstants;
import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECPoint;
import java.math.BigInteger;

public class ECNamedDomainParameters extends ECDomainParameters {
    private ASN1ObjectIdentifier name;

    public ECNamedDomainParameters(ASN1ObjectIdentifier name2, ECCurve curve, ECPoint G, BigInteger n) {
        this(name2, curve, G, n, ECConstants.ONE, null);
    }

    public ECNamedDomainParameters(ASN1ObjectIdentifier name2, ECCurve curve, ECPoint G, BigInteger n, BigInteger h) {
        this(name2, curve, G, n, h, null);
    }

    public ECNamedDomainParameters(ASN1ObjectIdentifier name2, ECCurve curve, ECPoint G, BigInteger n, BigInteger h, byte[] seed) {
        super(curve, G, n, h, seed);
        this.name = name2;
    }

    public ECNamedDomainParameters(ASN1ObjectIdentifier name2, ECDomainParameters domainParameters) {
        super(domainParameters.getCurve(), domainParameters.getG(), domainParameters.getN(), domainParameters.getH(), domainParameters.getSeed());
        this.name = name2;
    }

    public ASN1ObjectIdentifier getName() {
        return this.name;
    }
}
