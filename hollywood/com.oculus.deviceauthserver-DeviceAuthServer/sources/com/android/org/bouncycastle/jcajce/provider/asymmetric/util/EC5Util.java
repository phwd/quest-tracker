package com.android.org.bouncycastle.jcajce.provider.asymmetric.util;

import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.x9.ECNamedCurveTable;
import com.android.org.bouncycastle.asn1.x9.X962Parameters;
import com.android.org.bouncycastle.asn1.x9.X9ECParameters;
import com.android.org.bouncycastle.crypto.ec.CustomNamedCurves;
import com.android.org.bouncycastle.crypto.params.ECDomainParameters;
import com.android.org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import com.android.org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.android.org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import com.android.org.bouncycastle.jce.spec.ECNamedCurveSpec;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EC5Util {
    private static Map customCurves = new HashMap();

    static {
        Enumeration e = CustomNamedCurves.getNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            X9ECParameters curveParams = ECNamedCurveTable.getByName(name);
            if (curveParams != null) {
                customCurves.put(curveParams.getCurve(), CustomNamedCurves.getByName(name).getCurve());
            }
        }
    }

    /* JADX INFO: Multiple debug info for r2v6 com.android.org.bouncycastle.math.ec.ECCurve: [D('ecP' com.android.org.bouncycastle.asn1.x9.X9ECParameters), D('curve' com.android.org.bouncycastle.math.ec.ECCurve)] */
    public static ECCurve getCurve(ProviderConfiguration configuration, X962Parameters params) {
        Set acceptableCurves = configuration.getAcceptableNamedCurves();
        if (params.isNamedCurve()) {
            ASN1ObjectIdentifier oid = ASN1ObjectIdentifier.getInstance(params.getParameters());
            if (acceptableCurves.isEmpty() || acceptableCurves.contains(oid)) {
                X9ECParameters ecP = ECUtil.getNamedCurveByOid(oid);
                if (ecP == null) {
                    ecP = (X9ECParameters) configuration.getAdditionalECParameters().get(oid);
                }
                return ecP.getCurve();
            }
            throw new IllegalStateException("named curve not acceptable");
        } else if (params.isImplicitlyCA()) {
            return configuration.getEcImplicitlyCa().getCurve();
        } else {
            if (acceptableCurves.isEmpty()) {
                return X9ECParameters.getInstance(params.getParameters()).getCurve();
            }
            throw new IllegalStateException("encoded parameters not acceptable");
        }
    }

    public static ECDomainParameters getDomainParameters(ProviderConfiguration configuration, ECParameterSpec params) {
        if (params != null) {
            return ECUtil.getDomainParameters(configuration, convertSpec(params, false));
        }
        com.android.org.bouncycastle.jce.spec.ECParameterSpec iSpec = configuration.getEcImplicitlyCa();
        return new ECDomainParameters(iSpec.getCurve(), iSpec.getG(), iSpec.getN(), iSpec.getH(), iSpec.getSeed());
    }

    public static ECParameterSpec convertToSpec(X962Parameters params, ECCurve curve) {
        if (params.isNamedCurve()) {
            ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) params.getParameters();
            X9ECParameters ecP = ECUtil.getNamedCurveByOid(oid);
            if (ecP == null) {
                Map additionalECParameters = BouncyCastleProvider.CONFIGURATION.getAdditionalECParameters();
                if (!additionalECParameters.isEmpty()) {
                    ecP = (X9ECParameters) additionalECParameters.get(oid);
                }
            }
            return new ECNamedCurveSpec(ECUtil.getCurveName(oid), convertCurve(curve, ecP.getSeed()), convertPoint(ecP.getG()), ecP.getN(), ecP.getH());
        } else if (params.isImplicitlyCA()) {
            return null;
        } else {
            X9ECParameters ecP2 = X9ECParameters.getInstance(params.getParameters());
            EllipticCurve ellipticCurve = convertCurve(curve, ecP2.getSeed());
            if (ecP2.getH() != null) {
                return new ECParameterSpec(ellipticCurve, convertPoint(ecP2.getG()), ecP2.getN(), ecP2.getH().intValue());
            }
            return new ECParameterSpec(ellipticCurve, convertPoint(ecP2.getG()), ecP2.getN(), 1);
        }
    }

    public static ECParameterSpec convertToSpec(X9ECParameters domainParameters) {
        return new ECParameterSpec(convertCurve(domainParameters.getCurve(), null), convertPoint(domainParameters.getG()), domainParameters.getN(), domainParameters.getH().intValue());
    }

    public static ECParameterSpec convertToSpec(ECDomainParameters domainParameters) {
        return new ECParameterSpec(convertCurve(domainParameters.getCurve(), null), convertPoint(domainParameters.getG()), domainParameters.getN(), domainParameters.getH().intValue());
    }

    public static EllipticCurve convertCurve(ECCurve curve, byte[] seed) {
        return new EllipticCurve(convertField(curve.getField()), curve.getA().toBigInteger(), curve.getB().toBigInteger(), null);
    }

    public static ECCurve convertCurve(EllipticCurve ec) {
        ECField field = ec.getField();
        BigInteger a = ec.getA();
        BigInteger b = ec.getB();
        if (field instanceof ECFieldFp) {
            ECCurve.Fp curve = new ECCurve.Fp(((ECFieldFp) field).getP(), a, b);
            if (customCurves.containsKey(curve)) {
                return (ECCurve) customCurves.get(curve);
            }
            return curve;
        }
        ECFieldF2m fieldF2m = (ECFieldF2m) field;
        int m = fieldF2m.getM();
        int[] ks = ECUtil.convertMidTerms(fieldF2m.getMidTermsOfReductionPolynomial());
        return new ECCurve.F2m(m, ks[0], ks[1], ks[2], a, b);
    }

    public static ECField convertField(FiniteField field) {
        if (ECAlgorithms.isFpField(field)) {
            return new ECFieldFp(field.getCharacteristic());
        }
        Polynomial poly = ((PolynomialExtensionField) field).getMinimalPolynomial();
        int[] exponents = poly.getExponentsPresent();
        return new ECFieldF2m(poly.getDegree(), Arrays.reverse(Arrays.copyOfRange(exponents, 1, exponents.length - 1)));
    }

    public static ECParameterSpec convertSpec(EllipticCurve ellipticCurve, com.android.org.bouncycastle.jce.spec.ECParameterSpec spec) {
        if (spec instanceof ECNamedCurveParameterSpec) {
            return new ECNamedCurveSpec(((ECNamedCurveParameterSpec) spec).getName(), ellipticCurve, convertPoint(spec.getG()), spec.getN(), spec.getH());
        }
        return new ECParameterSpec(ellipticCurve, convertPoint(spec.getG()), spec.getN(), spec.getH().intValue());
    }

    public static com.android.org.bouncycastle.jce.spec.ECParameterSpec convertSpec(ECParameterSpec ecSpec, boolean withCompression) {
        ECCurve curve = convertCurve(ecSpec.getCurve());
        if (ecSpec instanceof ECNamedCurveSpec) {
            return new ECNamedCurveParameterSpec(((ECNamedCurveSpec) ecSpec).getName(), curve, convertPoint(curve, ecSpec.getGenerator(), withCompression), ecSpec.getOrder(), BigInteger.valueOf((long) ecSpec.getCofactor()), ecSpec.getCurve().getSeed());
        }
        return new com.android.org.bouncycastle.jce.spec.ECParameterSpec(curve, convertPoint(curve, ecSpec.getGenerator(), withCompression), ecSpec.getOrder(), BigInteger.valueOf((long) ecSpec.getCofactor()), ecSpec.getCurve().getSeed());
    }

    public static ECPoint convertPoint(ECParameterSpec ecSpec, java.security.spec.ECPoint point, boolean withCompression) {
        return convertPoint(convertCurve(ecSpec.getCurve()), point, withCompression);
    }

    public static ECPoint convertPoint(ECCurve curve, java.security.spec.ECPoint point, boolean withCompression) {
        return curve.createPoint(point.getAffineX(), point.getAffineY());
    }

    public static java.security.spec.ECPoint convertPoint(ECPoint point) {
        ECPoint point2 = point.normalize();
        return new java.security.spec.ECPoint(point2.getAffineXCoord().toBigInteger(), point2.getAffineYCoord().toBigInteger());
    }
}
