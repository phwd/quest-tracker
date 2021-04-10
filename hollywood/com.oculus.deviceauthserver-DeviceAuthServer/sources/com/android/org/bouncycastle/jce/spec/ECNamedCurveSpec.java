package com.android.org.bouncycastle.jce.spec;

import com.android.org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import com.android.org.bouncycastle.math.ec.ECAlgorithms;
import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.field.FiniteField;
import com.android.org.bouncycastle.math.field.Polynomial;
import com.android.org.bouncycastle.math.field.PolynomialExtensionField;
import com.android.org.bouncycastle.util.Arrays;
import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.EllipticCurve;

public class ECNamedCurveSpec extends ECParameterSpec {
    private String name;

    private static EllipticCurve convertCurve(ECCurve curve, byte[] seed) {
        return new EllipticCurve(convertField(curve.getField()), curve.getA().toBigInteger(), curve.getB().toBigInteger(), seed);
    }

    private static ECField convertField(FiniteField field) {
        if (ECAlgorithms.isFpField(field)) {
            return new ECFieldFp(field.getCharacteristic());
        }
        Polynomial poly = ((PolynomialExtensionField) field).getMinimalPolynomial();
        int[] exponents = poly.getExponentsPresent();
        return new ECFieldF2m(poly.getDegree(), Arrays.reverse(Arrays.copyOfRange(exponents, 1, exponents.length - 1)));
    }

    public ECNamedCurveSpec(String name2, ECCurve curve, ECPoint g, BigInteger n) {
        super(convertCurve(curve, null), EC5Util.convertPoint(g), n, 1);
        this.name = name2;
    }

    public ECNamedCurveSpec(String name2, EllipticCurve curve, java.security.spec.ECPoint g, BigInteger n) {
        super(curve, g, n, 1);
        this.name = name2;
    }

    public ECNamedCurveSpec(String name2, ECCurve curve, ECPoint g, BigInteger n, BigInteger h) {
        super(convertCurve(curve, null), EC5Util.convertPoint(g), n, h.intValue());
        this.name = name2;
    }

    public ECNamedCurveSpec(String name2, EllipticCurve curve, java.security.spec.ECPoint g, BigInteger n, BigInteger h) {
        super(curve, g, n, h.intValue());
        this.name = name2;
    }

    public ECNamedCurveSpec(String name2, ECCurve curve, ECPoint g, BigInteger n, BigInteger h, byte[] seed) {
        super(convertCurve(curve, seed), EC5Util.convertPoint(g), n, h.intValue());
        this.name = name2;
    }

    public String getName() {
        return this.name;
    }
}
