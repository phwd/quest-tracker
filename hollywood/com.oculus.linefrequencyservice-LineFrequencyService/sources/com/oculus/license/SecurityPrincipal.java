package com.oculus.license;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import oculus.internal.BuildCompat;
import oculus.internal.HexString;

public final class SecurityPrincipal {
    private static final String LICENSE_SECURITY_PRINCIPAL_HMAC_KEY = "a6bb1610c2153a2a9b0e1c62d6c4715b1ec6f4683067ada1b70311ddb2686d41111b1914b8cbeee272216a47bef7c90f4f42393fc62f0b6fdba7348ad0058cd0479d054e26d780b4bbd3e94f9db46c53aa086b95655fc5ab4f0bf3c658bc9c67b44b448e190d2ddd1a1e52cc319cff4ce0af50f19f5fb76bd85f0cf04652e81034122856ffdbe713e7ca8bb750999e661b12b45975c411f06d24902f576d8b853f0c907b70176a580bf254afa65c10b9f9cd2ae75bc116f8fbe0b6487498d17761ecb356332ce040a7e911ea59eb7632ade872f2058528c9e3f1f4c23284838bca6d2712d4c7732f1d17bc1c9546f180690d9910cb9457cd5eb4661d220eae5a";

    public static Set<String> computeWithDeviceSerial(Set<String> identifiers) {
        Set<String> identifiersWithDeviceSerial = new HashSet<>();
        if (identifiers != null) {
            identifiersWithDeviceSerial.addAll(identifiers);
        }
        identifiersWithDeviceSerial.add(new BuildCompat().getSerial());
        return compute(identifiersWithDeviceSerial);
    }

    public static Set<String> compute(Set<String> identifiers) {
        return (Set) identifiers.stream().map($$Lambda$jtes1X33Otps_tg89hnf5Akupc.INSTANCE).collect(Collectors.toSet());
    }

    public static String getDeviceSerialSecurityPrincipal() {
        return generate(new BuildCompat().getSerial());
    }

    static String generate(String identifier) {
        try {
            Mac shaMac = Mac.getInstance("HmacSHA256");
            shaMac.init(new SecretKeySpec(HexString.decode(LICENSE_SECURITY_PRINCIPAL_HMAC_KEY), "HmacSHA256"));
            try {
                return HexString.encode(shaMac.doFinal(identifier.getBytes("UTF-8")));
            } catch (Exception e) {
                throw new IllegalArgumentException("Could not decode identifier", e);
            }
        } catch (Exception e2) {
            throw new RuntimeException("Could not init hmac-sha", e2);
        }
    }
}
