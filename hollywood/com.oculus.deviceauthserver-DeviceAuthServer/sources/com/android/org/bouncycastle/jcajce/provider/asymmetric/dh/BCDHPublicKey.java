package com.android.org.bouncycastle.jcajce.provider.asymmetric.dh;

import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.pkcs.DHParameter;
import com.android.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.android.org.bouncycastle.asn1.x9.DomainParameters;
import com.android.org.bouncycastle.asn1.x9.ValidationParams;
import com.android.org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import com.android.org.bouncycastle.crypto.params.DHParameters;
import com.android.org.bouncycastle.crypto.params.DHPublicKeyParameters;
import com.android.org.bouncycastle.crypto.params.DHValidationParameters;
import com.android.org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import com.android.org.bouncycastle.jcajce.spec.DHDomainParameterSpec;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

public class BCDHPublicKey implements DHPublicKey {
    static final long serialVersionUID = -216691575254424324L;
    private transient DHPublicKeyParameters dhPublicKey;
    private transient DHParameterSpec dhSpec;
    private transient SubjectPublicKeyInfo info;
    private BigInteger y;

    BCDHPublicKey(DHPublicKeySpec spec) {
        this.y = spec.getY();
        this.dhSpec = new DHParameterSpec(spec.getP(), spec.getG());
        this.dhPublicKey = new DHPublicKeyParameters(this.y, new DHParameters(spec.getP(), spec.getG()));
    }

    BCDHPublicKey(DHPublicKey key) {
        this.y = key.getY();
        this.dhSpec = key.getParams();
        this.dhPublicKey = new DHPublicKeyParameters(this.y, new DHParameters(this.dhSpec.getP(), this.dhSpec.getG()));
    }

    BCDHPublicKey(DHPublicKeyParameters params) {
        this.y = params.getY();
        this.dhSpec = new DHDomainParameterSpec(params.getParameters());
        this.dhPublicKey = params;
    }

    BCDHPublicKey(BigInteger y2, DHParameterSpec dhSpec2) {
        this.y = y2;
        this.dhSpec = dhSpec2;
        if (dhSpec2 instanceof DHDomainParameterSpec) {
            this.dhPublicKey = new DHPublicKeyParameters(y2, ((DHDomainParameterSpec) dhSpec2).getDomainParameters());
        } else {
            this.dhPublicKey = new DHPublicKeyParameters(y2, new DHParameters(dhSpec2.getP(), dhSpec2.getG()));
        }
    }

    public BCDHPublicKey(SubjectPublicKeyInfo info2) {
        this.info = info2;
        try {
            this.y = ((ASN1Integer) info2.parsePublicKey()).getValue();
            ASN1Sequence seq = ASN1Sequence.getInstance(info2.getAlgorithm().getParameters());
            ASN1ObjectIdentifier id = info2.getAlgorithm().getAlgorithm();
            if (!id.equals(PKCSObjectIdentifiers.dhKeyAgreement)) {
                if (!isPKCSParam(seq)) {
                    if (id.equals(X9ObjectIdentifiers.dhpublicnumber)) {
                        DomainParameters params = DomainParameters.getInstance(seq);
                        ValidationParams validationParams = params.getValidationParams();
                        if (validationParams != null) {
                            this.dhPublicKey = new DHPublicKeyParameters(this.y, new DHParameters(params.getP(), params.getG(), params.getQ(), params.getJ(), new DHValidationParameters(validationParams.getSeed(), validationParams.getPgenCounter().intValue())));
                        } else {
                            this.dhPublicKey = new DHPublicKeyParameters(this.y, new DHParameters(params.getP(), params.getG(), params.getQ(), params.getJ(), (DHValidationParameters) null));
                        }
                        this.dhSpec = new DHDomainParameterSpec(this.dhPublicKey.getParameters());
                        return;
                    }
                    throw new IllegalArgumentException("unknown algorithm type: " + id);
                }
            }
            DHParameter params2 = DHParameter.getInstance(seq);
            if (params2.getL() != null) {
                this.dhSpec = new DHParameterSpec(params2.getP(), params2.getG(), params2.getL().intValue());
            } else {
                this.dhSpec = new DHParameterSpec(params2.getP(), params2.getG());
            }
            this.dhPublicKey = new DHPublicKeyParameters(this.y, new DHParameters(this.dhSpec.getP(), this.dhSpec.getG()));
        } catch (IOException e) {
            throw new IllegalArgumentException("invalid info structure in DH public key");
        }
    }

    public String getAlgorithm() {
        return "DH";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        SubjectPublicKeyInfo subjectPublicKeyInfo = this.info;
        if (subjectPublicKeyInfo != null) {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(subjectPublicKeyInfo);
        }
        DHParameterSpec dHParameterSpec = this.dhSpec;
        if (!(dHParameterSpec instanceof DHDomainParameterSpec) || ((DHDomainParameterSpec) dHParameterSpec).getQ() == null) {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.dhKeyAgreement, new DHParameter(this.dhSpec.getP(), this.dhSpec.getG(), this.dhSpec.getL()).toASN1Primitive()), new ASN1Integer(this.y));
        }
        DHParameters params = ((DHDomainParameterSpec) this.dhSpec).getDomainParameters();
        DHValidationParameters validationParameters = params.getValidationParameters();
        ValidationParams vParams = null;
        if (validationParameters != null) {
            vParams = new ValidationParams(validationParameters.getSeed(), validationParameters.getCounter());
        }
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.dhpublicnumber, new DomainParameters(params.getP(), params.getG(), params.getQ(), params.getJ(), vParams).toASN1Primitive()), new ASN1Integer(this.y));
    }

    public String toString() {
        return DHUtil.publicKeyToString("DH", this.y, new DHParameters(this.dhSpec.getP(), this.dhSpec.getG()));
    }

    public DHParameterSpec getParams() {
        return this.dhSpec;
    }

    public BigInteger getY() {
        return this.y;
    }

    public DHPublicKeyParameters engineGetKeyParameters() {
        return this.dhPublicKey;
    }

    private boolean isPKCSParam(ASN1Sequence seq) {
        if (seq.size() == 2) {
            return true;
        }
        if (seq.size() > 3) {
            return false;
        }
        if (ASN1Integer.getInstance(seq.getObjectAt(2)).getValue().compareTo(BigInteger.valueOf((long) ASN1Integer.getInstance(seq.getObjectAt(0)).getValue().bitLength())) > 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
    }

    public boolean equals(Object o) {
        if (!(o instanceof DHPublicKey)) {
            return false;
        }
        DHPublicKey other = (DHPublicKey) o;
        if (!getY().equals(other.getY()) || !getParams().getG().equals(other.getParams().getG()) || !getParams().getP().equals(other.getParams().getP()) || getParams().getL() != other.getParams().getL()) {
            return false;
        }
        return true;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.dhSpec = new DHParameterSpec((BigInteger) in.readObject(), (BigInteger) in.readObject(), in.readInt());
        this.info = null;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.dhSpec.getP());
        out.writeObject(this.dhSpec.getG());
        out.writeInt(this.dhSpec.getL());
    }
}
