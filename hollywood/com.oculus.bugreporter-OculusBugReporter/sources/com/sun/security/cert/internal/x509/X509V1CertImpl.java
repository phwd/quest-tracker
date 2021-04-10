package com.sun.security.cert.internal.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Date;
import javax.security.cert.CertificateEncodingException;
import javax.security.cert.CertificateExpiredException;
import javax.security.cert.CertificateNotYetValidException;
import javax.security.cert.X509Certificate;

public class X509V1CertImpl extends X509Certificate implements Serializable {
    static final long serialVersionUID = -2048442350420423405L;
    private java.security.cert.X509Certificate wrappedCert;

    private static synchronized CertificateFactory getFactory() throws CertificateException {
        CertificateFactory instance;
        synchronized (X509V1CertImpl.class) {
            instance = CertificateFactory.getInstance("X.509");
        }
        return instance;
    }

    public X509V1CertImpl() {
    }

    public X509V1CertImpl(byte[] certData) throws javax.security.cert.CertificateException {
        try {
            this.wrappedCert = (java.security.cert.X509Certificate) getFactory().generateCertificate(new ByteArrayInputStream(certData));
        } catch (CertificateException e) {
            throw new javax.security.cert.CertificateException(e.getMessage());
        }
    }

    public X509V1CertImpl(InputStream in) throws javax.security.cert.CertificateException {
        try {
            this.wrappedCert = (java.security.cert.X509Certificate) getFactory().generateCertificate(in);
        } catch (CertificateException e) {
            throw new javax.security.cert.CertificateException(e.getMessage());
        }
    }

    @Override // javax.security.cert.Certificate
    public byte[] getEncoded() throws CertificateEncodingException {
        try {
            return this.wrappedCert.getEncoded();
        } catch (java.security.cert.CertificateEncodingException e) {
            throw new CertificateEncodingException(e.getMessage());
        }
    }

    @Override // javax.security.cert.Certificate
    public void verify(PublicKey key) throws javax.security.cert.CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        try {
            this.wrappedCert.verify(key);
        } catch (CertificateException e) {
            throw new javax.security.cert.CertificateException(e.getMessage());
        }
    }

    @Override // javax.security.cert.Certificate
    public void verify(PublicKey key, String sigProvider) throws javax.security.cert.CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        try {
            this.wrappedCert.verify(key, sigProvider);
        } catch (CertificateException e) {
            throw new javax.security.cert.CertificateException(e.getMessage());
        }
    }

    @Override // javax.security.cert.X509Certificate
    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        checkValidity(new Date());
    }

    @Override // javax.security.cert.X509Certificate
    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        try {
            this.wrappedCert.checkValidity(date);
        } catch (java.security.cert.CertificateNotYetValidException e) {
            throw new CertificateNotYetValidException(e.getMessage());
        } catch (java.security.cert.CertificateExpiredException e2) {
            throw new CertificateExpiredException(e2.getMessage());
        }
    }

    @Override // javax.security.cert.Certificate
    public String toString() {
        return this.wrappedCert.toString();
    }

    @Override // javax.security.cert.Certificate
    public PublicKey getPublicKey() {
        return this.wrappedCert.getPublicKey();
    }

    @Override // javax.security.cert.X509Certificate
    public int getVersion() {
        return this.wrappedCert.getVersion() - 1;
    }

    @Override // javax.security.cert.X509Certificate
    public BigInteger getSerialNumber() {
        return this.wrappedCert.getSerialNumber();
    }

    @Override // javax.security.cert.X509Certificate
    public Principal getSubjectDN() {
        return this.wrappedCert.getSubjectDN();
    }

    @Override // javax.security.cert.X509Certificate
    public Principal getIssuerDN() {
        return this.wrappedCert.getIssuerDN();
    }

    @Override // javax.security.cert.X509Certificate
    public Date getNotBefore() {
        return this.wrappedCert.getNotBefore();
    }

    @Override // javax.security.cert.X509Certificate
    public Date getNotAfter() {
        return this.wrappedCert.getNotAfter();
    }

    @Override // javax.security.cert.X509Certificate
    public String getSigAlgName() {
        return this.wrappedCert.getSigAlgName();
    }

    @Override // javax.security.cert.X509Certificate
    public String getSigAlgOID() {
        return this.wrappedCert.getSigAlgOID();
    }

    @Override // javax.security.cert.X509Certificate
    public byte[] getSigAlgParams() {
        return this.wrappedCert.getSigAlgParams();
    }

    private synchronized void writeObject(ObjectOutputStream stream) throws IOException {
        try {
            stream.write(getEncoded());
        } catch (CertificateEncodingException e) {
            throw new IOException("getEncoded failed: " + e.getMessage());
        }
    }

    private synchronized void readObject(ObjectInputStream stream) throws IOException {
        try {
            this.wrappedCert = (java.security.cert.X509Certificate) getFactory().generateCertificate(stream);
        } catch (CertificateException e) {
            throw new IOException("generateCertificate failed: " + e.getMessage());
        }
    }

    public java.security.cert.X509Certificate getX509Certificate() {
        return this.wrappedCert;
    }
}
