package com.android.org.bouncycastle.jcajce.provider.asymmetric.rsa;

import com.android.org.bouncycastle.asn1.ASN1Encodable;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.DERNull;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.crypto.params.RSAKeyParameters;
import com.android.org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import com.android.org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import com.android.org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import com.android.org.bouncycastle.util.Strings;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Enumeration;

public class BCRSAPrivateKey implements RSAPrivateKey, PKCS12BagAttributeCarrier {
    private static BigInteger ZERO = BigInteger.valueOf(0);
    static final long serialVersionUID = 5110188922551353628L;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier = new PKCS12BagAttributeCarrierImpl();
    protected BigInteger modulus;
    protected BigInteger privateExponent;

    protected BCRSAPrivateKey() {
    }

    BCRSAPrivateKey(RSAKeyParameters key) {
        this.modulus = key.getModulus();
        this.privateExponent = key.getExponent();
    }

    BCRSAPrivateKey(RSAPrivateKeySpec spec) {
        this.modulus = spec.getModulus();
        this.privateExponent = spec.getPrivateExponent();
    }

    BCRSAPrivateKey(RSAPrivateKey key) {
        this.modulus = key.getModulus();
        this.privateExponent = key.getPrivateExponent();
    }

    BCRSAPrivateKey(com.android.org.bouncycastle.asn1.pkcs.RSAPrivateKey key) {
        this.modulus = key.getModulus();
        this.privateExponent = key.getPrivateExponent();
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getPrivateExponent() {
        return this.privateExponent;
    }

    public String getAlgorithm() {
        return "RSA";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE);
        BigInteger modulus2 = getModulus();
        BigInteger bigInteger = ZERO;
        BigInteger privateExponent2 = getPrivateExponent();
        BigInteger bigInteger2 = ZERO;
        return KeyUtil.getEncodedPrivateKeyInfo(algorithmIdentifier, new com.android.org.bouncycastle.asn1.pkcs.RSAPrivateKey(modulus2, bigInteger, privateExponent2, bigInteger2, bigInteger2, bigInteger2, bigInteger2, bigInteger2));
    }

    public boolean equals(Object o) {
        if (!(o instanceof RSAPrivateKey)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        RSAPrivateKey key = (RSAPrivateKey) o;
        if (!getModulus().equals(key.getModulus()) || !getPrivateExponent().equals(key.getPrivateExponent())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getModulus().hashCode() ^ getPrivateExponent().hashCode();
    }

    @Override // com.android.org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public void setBagAttribute(ASN1ObjectIdentifier oid, ASN1Encodable attribute) {
        this.attrCarrier.setBagAttribute(oid, attribute);
    }

    @Override // com.android.org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier oid) {
        return this.attrCarrier.getBagAttribute(oid);
    }

    @Override // com.android.org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        String nl = Strings.lineSeparator();
        buf.append("RSA Private Key [");
        buf.append(RSAUtil.generateKeyFingerprint(getModulus()));
        buf.append("],[]");
        buf.append(nl);
        buf.append("            modulus: ");
        buf.append(getModulus().toString(16));
        buf.append(nl);
        return buf.toString();
    }
}
