package X;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class e3 {
    public static final e3 A01 = A00("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");
    public static final e3 A02 = A00("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A03 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
    public static final e3 A04 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
    public static final e3 A05 = A00("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256");
    public static final e3 A06 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
    public static final e3 A07 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");
    public static final e3 A08 = A00("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384");
    public static final e3 A09 = A00("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA");
    public static final e3 A0A = A00("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA");
    public static final e3 A0B = A00("SSL_DHE_DSS_WITH_DES_CBC_SHA");
    public static final e3 A0C = A00("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final e3 A0D = A00("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A0E = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
    public static final e3 A0F = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final e3 A0G = A00("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final e3 A0H = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
    public static final e3 A0I = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
    public static final e3 A0J = A00("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final e3 A0K = A00("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final e3 A0L = A00("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final e3 A0M = A00("SSL_DHE_RSA_WITH_DES_CBC_SHA");
    public static final e3 A0N = A00("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA");
    public static final e3 A0O = A00("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");
    public static final e3 A0P = A00("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A0Q = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA");
    public static final e3 A0R = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA256");
    public static final e3 A0S = A00("TLS_DH_anon_WITH_AES_128_GCM_SHA256");
    public static final e3 A0T = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA");
    public static final e3 A0U = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA256");
    public static final e3 A0V = A00("TLS_DH_anon_WITH_AES_256_GCM_SHA384");
    public static final e3 A0W = A00("SSL_DH_anon_WITH_DES_CBC_SHA");
    public static final e3 A0X = A00("SSL_DH_anon_WITH_RC4_128_MD5");
    public static final e3 A0Y = A00("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A0Z = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
    public static final e3 A0a = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final e3 A0b = A00("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final e3 A0c = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
    public static final e3 A0d = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final e3 A0e = A00("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final e3 A0f = A00("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final e3 A0g = A00("TLS_ECDHE_ECDSA_WITH_NULL_SHA");
    public static final e3 A0h = A00("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");
    public static final e3 A0i = A00("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA");
    public static final e3 A0j = A00("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA");
    public static final e3 A0k = A00("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A0l = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
    public static final e3 A0m = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final e3 A0n = A00("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final e3 A0o = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
    public static final e3 A0p = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
    public static final e3 A0q = A00("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final e3 A0r = A00("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final e3 A0s = A00("TLS_ECDHE_RSA_WITH_NULL_SHA");
    public static final e3 A0t = A00("TLS_ECDHE_RSA_WITH_RC4_128_SHA");
    public static final e3 A0u = A00("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A0v = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");
    public static final e3 A0w = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final e3 A0x = A00("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final e3 A0y = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");
    public static final e3 A0z = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final e3 A10 = A00("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final e3 A11 = A00("TLS_ECDH_ECDSA_WITH_NULL_SHA");
    public static final e3 A12 = A00("TLS_ECDH_ECDSA_WITH_RC4_128_SHA");
    public static final e3 A13 = A00("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A14 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");
    public static final e3 A15 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");
    public static final e3 A16 = A00("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256");
    public static final e3 A17 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");
    public static final e3 A18 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");
    public static final e3 A19 = A00("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384");
    public static final e3 A1A = A00("TLS_ECDH_RSA_WITH_NULL_SHA");
    public static final e3 A1B = A00("TLS_ECDH_RSA_WITH_RC4_128_SHA");
    public static final e3 A1C = A00("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A1D = A00("TLS_ECDH_anon_WITH_AES_128_CBC_SHA");
    public static final e3 A1E = A00("TLS_ECDH_anon_WITH_AES_256_CBC_SHA");
    public static final e3 A1F = A00("TLS_ECDH_anon_WITH_NULL_SHA");
    public static final e3 A1G = A00("TLS_ECDH_anon_WITH_RC4_128_SHA");
    public static final e3 A1H = A00("TLS_EMPTY_RENEGOTIATION_INFO_SCSV");
    public static final e3 A1I = A00("TLS_FALLBACK_SCSV");
    public static final e3 A1J = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5");
    public static final e3 A1K = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA");
    public static final e3 A1L = A00("TLS_KRB5_EXPORT_WITH_RC4_40_MD5");
    public static final e3 A1M = A00("TLS_KRB5_EXPORT_WITH_RC4_40_SHA");
    public static final e3 A1N = A00("TLS_KRB5_WITH_3DES_EDE_CBC_MD5");
    public static final e3 A1O = A00("TLS_KRB5_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A1P = A00("TLS_KRB5_WITH_DES_CBC_MD5");
    public static final e3 A1Q = A00("TLS_KRB5_WITH_DES_CBC_SHA");
    public static final e3 A1R = A00("TLS_KRB5_WITH_RC4_128_MD5");
    public static final e3 A1S = A00("TLS_KRB5_WITH_RC4_128_SHA");
    public static final e3 A1T = A00("TLS_PSK_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A1U = A00("TLS_PSK_WITH_AES_128_CBC_SHA");
    public static final e3 A1V = A00("TLS_PSK_WITH_AES_256_CBC_SHA");
    public static final e3 A1W = A00("TLS_PSK_WITH_RC4_128_SHA");
    public static final e3 A1X = A00("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final e3 A1Y = A00("SSL_RSA_EXPORT_WITH_RC4_40_MD5");
    public static final e3 A1Z = A00("SSL_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final e3 A1a = A00("TLS_RSA_WITH_AES_128_CBC_SHA");
    public static final e3 A1b = A00("TLS_RSA_WITH_AES_128_CBC_SHA256");
    public static final e3 A1c = A00("TLS_RSA_WITH_AES_128_GCM_SHA256");
    public static final e3 A1d = A00("TLS_RSA_WITH_AES_256_CBC_SHA");
    public static final e3 A1e = A00("TLS_RSA_WITH_AES_256_CBC_SHA256");
    public static final e3 A1f = A00("TLS_RSA_WITH_AES_256_GCM_SHA384");
    public static final e3 A1g = A00("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final e3 A1h = A00("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final e3 A1i = A00("SSL_RSA_WITH_DES_CBC_SHA");
    public static final e3 A1j = A00("SSL_RSA_WITH_NULL_MD5");
    public static final e3 A1k = A00("SSL_RSA_WITH_NULL_SHA");
    public static final e3 A1l = A00("TLS_RSA_WITH_NULL_SHA256");
    public static final e3 A1m = A00("SSL_RSA_WITH_RC4_128_MD5");
    public static final e3 A1n = A00("SSL_RSA_WITH_RC4_128_SHA");
    public static final e3 A1o = A00("TLS_RSA_WITH_SEED_CBC_SHA");
    public static final ConcurrentMap<String, e3> A1p = new ConcurrentHashMap();
    public final String A00;

    public static e3 A00(String str) {
        e3 putIfAbsent;
        ConcurrentMap<String, e3> concurrentMap = A1p;
        e3 e3Var = concurrentMap.get(str);
        if (e3Var != null || (putIfAbsent = concurrentMap.putIfAbsent(str, (e3Var = new e3(str)))) == null) {
            return e3Var;
        }
        return putIfAbsent;
    }

    public e3(String str) {
        if (str != null) {
            this.A00 = str;
            return;
        }
        throw null;
    }

    public final String toString() {
        return this.A00;
    }
}
