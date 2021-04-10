package com.android.org.bouncycastle.jcajce.provider.asymmetric.dh;

import com.android.org.bouncycastle.crypto.params.DHParameters;
import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Fingerprint;
import com.android.org.bouncycastle.util.Strings;
import java.math.BigInteger;

class DHUtil {
    DHUtil() {
    }

    static String privateKeyToString(String algorithm, BigInteger x, DHParameters dhParams) {
        StringBuffer buf = new StringBuffer();
        String nl = Strings.lineSeparator();
        BigInteger y = dhParams.getG().modPow(x, dhParams.getP());
        buf.append(algorithm);
        buf.append(" Private Key [");
        buf.append(generateKeyFingerprint(y, dhParams));
        buf.append("]");
        buf.append(nl);
        buf.append("              Y: ");
        buf.append(y.toString(16));
        buf.append(nl);
        return buf.toString();
    }

    static String publicKeyToString(String algorithm, BigInteger y, DHParameters dhParams) {
        StringBuffer buf = new StringBuffer();
        String nl = Strings.lineSeparator();
        buf.append(algorithm);
        buf.append(" Public Key [");
        buf.append(generateKeyFingerprint(y, dhParams));
        buf.append("]");
        buf.append(nl);
        buf.append("             Y: ");
        buf.append(y.toString(16));
        buf.append(nl);
        return buf.toString();
    }

    private static String generateKeyFingerprint(BigInteger y, DHParameters dhParams) {
        return new Fingerprint(Arrays.concatenate(y.toByteArray(), dhParams.getP().toByteArray(), dhParams.getG().toByteArray())).toString();
    }
}
