package com.android.org.bouncycastle.jcajce.spec;

import com.android.org.bouncycastle.crypto.params.DHParameters;
import com.android.org.bouncycastle.crypto.params.DHValidationParameters;
import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;

public class DHDomainParameterSpec extends DHParameterSpec {
    private final BigInteger j;
    private final int m;
    private final BigInteger q;
    private DHValidationParameters validationParameters;

    public DHDomainParameterSpec(DHParameters domainParameters) {
        this(domainParameters.getP(), domainParameters.getQ(), domainParameters.getG(), domainParameters.getJ(), domainParameters.getM(), domainParameters.getL());
        this.validationParameters = domainParameters.getValidationParameters();
    }

    public DHDomainParameterSpec(BigInteger p, BigInteger q2, BigInteger g) {
        this(p, q2, g, null, 0);
    }

    public DHDomainParameterSpec(BigInteger p, BigInteger q2, BigInteger g, int l) {
        this(p, q2, g, null, l);
    }

    public DHDomainParameterSpec(BigInteger p, BigInteger q2, BigInteger g, BigInteger j2, int l) {
        this(p, q2, g, j2, 0, l);
    }

    public DHDomainParameterSpec(BigInteger p, BigInteger q2, BigInteger g, BigInteger j2, int m2, int l) {
        super(p, g, l);
        this.q = q2;
        this.j = j2;
        this.m = m2;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getJ() {
        return this.j;
    }

    public int getM() {
        return this.m;
    }

    public DHParameters getDomainParameters() {
        return new DHParameters(getP(), getG(), this.q, this.m, getL(), this.j, this.validationParameters);
    }
}
