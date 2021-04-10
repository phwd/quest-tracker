package com.android.org.bouncycastle.jcajce.provider.util;

import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.jcajce.provider.config.ConfigurableProvider;

public abstract class AsymmetricAlgorithmProvider extends AlgorithmProvider {
    /* access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider provider, String algorithm, String className, ASN1ObjectIdentifier oid) {
        provider.addAlgorithm("Signature." + algorithm, className);
        provider.addAlgorithm("Alg.Alias.Signature." + oid, algorithm);
        provider.addAlgorithm("Alg.Alias.Signature.OID." + oid, algorithm);
    }

    /* access modifiers changed from: protected */
    public void addSignatureAlgorithm(ConfigurableProvider provider, String digest, String algorithm, String className, ASN1ObjectIdentifier oid) {
        String mainName = digest + "WITH" + algorithm;
        provider.addAlgorithm("Signature." + mainName, className);
        provider.addAlgorithm("Alg.Alias.Signature." + (digest + "with" + algorithm), mainName);
        provider.addAlgorithm("Alg.Alias.Signature." + (digest + "With" + algorithm), mainName);
        provider.addAlgorithm("Alg.Alias.Signature." + (digest + "/" + algorithm), mainName);
        provider.addAlgorithm("Alg.Alias.Signature." + oid, mainName);
        provider.addAlgorithm("Alg.Alias.Signature.OID." + oid, mainName);
    }

    /* access modifiers changed from: protected */
    public void registerOid(ConfigurableProvider provider, ASN1ObjectIdentifier oid, String name, AsymmetricKeyInfoConverter keyFactory) {
        provider.addAlgorithm("Alg.Alias.KeyFactory." + oid, name);
        provider.addAlgorithm("Alg.Alias.KeyPairGenerator." + oid, name);
        provider.addKeyInfoConverter(oid, keyFactory);
    }

    /* access modifiers changed from: protected */
    public void registerOidAlgorithmParameters(ConfigurableProvider provider, ASN1ObjectIdentifier oid, String name) {
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters." + oid, name);
    }

    /* access modifiers changed from: protected */
    public void registerOidAlgorithmParameterGenerator(ConfigurableProvider provider, ASN1ObjectIdentifier oid, String name) {
        provider.addAlgorithm("Alg.Alias.AlgorithmParameterGenerator." + oid, name);
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters." + oid, name);
    }
}
