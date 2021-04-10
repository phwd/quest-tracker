package X;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: X.0mZ  reason: invalid class name and case insensitive filesystem */
public final class C06320mZ {
    public static final C06320mZ A01 = A00("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");
    public static final C06320mZ A02 = A00("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A03 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A04 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
    public static final C06320mZ A05 = A00("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256");
    public static final C06320mZ A06 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A07 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");
    public static final C06320mZ A08 = A00("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384");
    public static final C06320mZ A09 = A00("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA");
    public static final C06320mZ A0A = A00("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA");
    public static final C06320mZ A0B = A00("SSL_DHE_DSS_WITH_DES_CBC_SHA");
    public static final C06320mZ A0C = A00("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final C06320mZ A0D = A00("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A0E = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A0F = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final C06320mZ A0G = A00("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final C06320mZ A0H = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A0I = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
    public static final C06320mZ A0J = A00("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final C06320mZ A0K = A00("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final C06320mZ A0L = A00("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final C06320mZ A0M = A00("SSL_DHE_RSA_WITH_DES_CBC_SHA");
    public static final C06320mZ A0N = A00("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA");
    public static final C06320mZ A0O = A00("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");
    public static final C06320mZ A0P = A00("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A0Q = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A0R = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA256");
    public static final C06320mZ A0S = A00("TLS_DH_anon_WITH_AES_128_GCM_SHA256");
    public static final C06320mZ A0T = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A0U = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA256");
    public static final C06320mZ A0V = A00("TLS_DH_anon_WITH_AES_256_GCM_SHA384");
    public static final C06320mZ A0W = A00("SSL_DH_anon_WITH_DES_CBC_SHA");
    public static final C06320mZ A0X = A00("SSL_DH_anon_WITH_RC4_128_MD5");
    public static final C06320mZ A0Y = A00("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A0Z = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A0a = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final C06320mZ A0b = A00("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final C06320mZ A0c = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A0d = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final C06320mZ A0e = A00("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final C06320mZ A0f = A00("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final C06320mZ A0g = A00("TLS_ECDHE_ECDSA_WITH_NULL_SHA");
    public static final C06320mZ A0h = A00("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");
    public static final C06320mZ A0i = A00("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A0j = A00("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A0k = A00("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A0l = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A0m = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final C06320mZ A0n = A00("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final C06320mZ A0o = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A0p = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
    public static final C06320mZ A0q = A00("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final C06320mZ A0r = A00("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final C06320mZ A0s = A00("TLS_ECDHE_RSA_WITH_NULL_SHA");
    public static final C06320mZ A0t = A00("TLS_ECDHE_RSA_WITH_RC4_128_SHA");
    public static final C06320mZ A0u = A00("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A0v = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A0w = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final C06320mZ A0x = A00("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final C06320mZ A0y = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A0z = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final C06320mZ A10 = A00("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final C06320mZ A11 = A00("TLS_ECDH_ECDSA_WITH_NULL_SHA");
    public static final C06320mZ A12 = A00("TLS_ECDH_ECDSA_WITH_RC4_128_SHA");
    public static final C06320mZ A13 = A00("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A14 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A15 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");
    public static final C06320mZ A16 = A00("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256");
    public static final C06320mZ A17 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A18 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");
    public static final C06320mZ A19 = A00("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384");
    public static final C06320mZ A1A = A00("TLS_ECDH_RSA_WITH_NULL_SHA");
    public static final C06320mZ A1B = A00("TLS_ECDH_RSA_WITH_RC4_128_SHA");
    public static final C06320mZ A1C = A00("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A1D = A00("TLS_ECDH_anon_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A1E = A00("TLS_ECDH_anon_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A1F = A00("TLS_ECDH_anon_WITH_NULL_SHA");
    public static final C06320mZ A1G = A00("TLS_ECDH_anon_WITH_RC4_128_SHA");
    public static final C06320mZ A1H = A00("TLS_EMPTY_RENEGOTIATION_INFO_SCSV");
    public static final C06320mZ A1I = A00("TLS_FALLBACK_SCSV");
    public static final C06320mZ A1J = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5");
    public static final C06320mZ A1K = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA");
    public static final C06320mZ A1L = A00("TLS_KRB5_EXPORT_WITH_RC4_40_MD5");
    public static final C06320mZ A1M = A00("TLS_KRB5_EXPORT_WITH_RC4_40_SHA");
    public static final C06320mZ A1N = A00("TLS_KRB5_WITH_3DES_EDE_CBC_MD5");
    public static final C06320mZ A1O = A00("TLS_KRB5_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A1P = A00("TLS_KRB5_WITH_DES_CBC_MD5");
    public static final C06320mZ A1Q = A00("TLS_KRB5_WITH_DES_CBC_SHA");
    public static final C06320mZ A1R = A00("TLS_KRB5_WITH_RC4_128_MD5");
    public static final C06320mZ A1S = A00("TLS_KRB5_WITH_RC4_128_SHA");
    public static final C06320mZ A1T = A00("TLS_PSK_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A1U = A00("TLS_PSK_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A1V = A00("TLS_PSK_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A1W = A00("TLS_PSK_WITH_RC4_128_SHA");
    public static final C06320mZ A1X = A00("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final C06320mZ A1Y = A00("SSL_RSA_EXPORT_WITH_RC4_40_MD5");
    public static final C06320mZ A1Z = A00("SSL_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C06320mZ A1a = A00("TLS_RSA_WITH_AES_128_CBC_SHA");
    public static final C06320mZ A1b = A00("TLS_RSA_WITH_AES_128_CBC_SHA256");
    public static final C06320mZ A1c = A00("TLS_RSA_WITH_AES_128_GCM_SHA256");
    public static final C06320mZ A1d = A00("TLS_RSA_WITH_AES_256_CBC_SHA");
    public static final C06320mZ A1e = A00("TLS_RSA_WITH_AES_256_CBC_SHA256");
    public static final C06320mZ A1f = A00("TLS_RSA_WITH_AES_256_GCM_SHA384");
    public static final C06320mZ A1g = A00("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final C06320mZ A1h = A00("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final C06320mZ A1i = A00("SSL_RSA_WITH_DES_CBC_SHA");
    public static final C06320mZ A1j = A00("SSL_RSA_WITH_NULL_MD5");
    public static final C06320mZ A1k = A00("SSL_RSA_WITH_NULL_SHA");
    public static final C06320mZ A1l = A00("TLS_RSA_WITH_NULL_SHA256");
    public static final C06320mZ A1m = A00("SSL_RSA_WITH_RC4_128_MD5");
    public static final C06320mZ A1n = A00("SSL_RSA_WITH_RC4_128_SHA");
    public static final C06320mZ A1o = A00("TLS_RSA_WITH_SEED_CBC_SHA");
    public static final ConcurrentMap<String, C06320mZ> A1p = new ConcurrentHashMap();
    public final String A00;

    public static C06320mZ A00(String str) {
        C06320mZ putIfAbsent;
        ConcurrentMap<String, C06320mZ> concurrentMap = A1p;
        C06320mZ r1 = concurrentMap.get(str);
        if (r1 != null || (putIfAbsent = concurrentMap.putIfAbsent(str, (r1 = new C06320mZ(str)))) == null) {
            return r1;
        }
        return putIfAbsent;
    }

    public final String toString() {
        return this.A00;
    }

    public C06320mZ(String str) {
        if (str != null) {
            this.A00 = str;
            return;
        }
        throw null;
    }
}
