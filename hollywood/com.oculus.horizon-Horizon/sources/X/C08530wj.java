package X;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: X.0wj  reason: invalid class name and case insensitive filesystem */
public final class C08530wj {
    public static final C08530wj A01 = A00("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");
    public static final C08530wj A02 = A00("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A03 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
    public static final C08530wj A04 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
    public static final C08530wj A05 = A00("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256");
    public static final C08530wj A06 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
    public static final C08530wj A07 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");
    public static final C08530wj A08 = A00("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384");
    public static final C08530wj A09 = A00("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA");
    public static final C08530wj A0A = A00("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA");
    public static final C08530wj A0B = A00("SSL_DHE_DSS_WITH_DES_CBC_SHA");
    public static final C08530wj A0C = A00("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final C08530wj A0D = A00("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A0E = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
    public static final C08530wj A0F = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final C08530wj A0G = A00("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final C08530wj A0H = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
    public static final C08530wj A0I = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
    public static final C08530wj A0J = A00("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final C08530wj A0K = A00("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final C08530wj A0L = A00("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final C08530wj A0M = A00("SSL_DHE_RSA_WITH_DES_CBC_SHA");
    public static final C08530wj A0N = A00("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA");
    public static final C08530wj A0O = A00("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");
    public static final C08530wj A0P = A00("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A0Q = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA");
    public static final C08530wj A0R = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA256");
    public static final C08530wj A0S = A00("TLS_DH_anon_WITH_AES_128_GCM_SHA256");
    public static final C08530wj A0T = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA");
    public static final C08530wj A0U = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA256");
    public static final C08530wj A0V = A00("TLS_DH_anon_WITH_AES_256_GCM_SHA384");
    public static final C08530wj A0W = A00("SSL_DH_anon_WITH_DES_CBC_SHA");
    public static final C08530wj A0X = A00("SSL_DH_anon_WITH_RC4_128_MD5");
    public static final C08530wj A0Y = A00("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A0Z = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
    public static final C08530wj A0a = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final C08530wj A0b = A00("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final C08530wj A0c = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
    public static final C08530wj A0d = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final C08530wj A0e = A00("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final C08530wj A0f = A00("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final C08530wj A0g = A00("TLS_ECDHE_ECDSA_WITH_NULL_SHA");
    public static final C08530wj A0h = A00("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");
    public static final C08530wj A0i = A00("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA");
    public static final C08530wj A0j = A00("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA");
    public static final C08530wj A0k = A00("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A0l = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
    public static final C08530wj A0m = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final C08530wj A0n = A00("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final C08530wj A0o = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
    public static final C08530wj A0p = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
    public static final C08530wj A0q = A00("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final C08530wj A0r = A00("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final C08530wj A0s = A00("TLS_ECDHE_RSA_WITH_NULL_SHA");
    public static final C08530wj A0t = A00("TLS_ECDHE_RSA_WITH_RC4_128_SHA");
    public static final C08530wj A0u = A00("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A0v = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");
    public static final C08530wj A0w = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final C08530wj A0x = A00("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final C08530wj A0y = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");
    public static final C08530wj A0z = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final C08530wj A10 = A00("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final C08530wj A11 = A00("TLS_ECDH_ECDSA_WITH_NULL_SHA");
    public static final C08530wj A12 = A00("TLS_ECDH_ECDSA_WITH_RC4_128_SHA");
    public static final C08530wj A13 = A00("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A14 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");
    public static final C08530wj A15 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");
    public static final C08530wj A16 = A00("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256");
    public static final C08530wj A17 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");
    public static final C08530wj A18 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");
    public static final C08530wj A19 = A00("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384");
    public static final C08530wj A1A = A00("TLS_ECDH_RSA_WITH_NULL_SHA");
    public static final C08530wj A1B = A00("TLS_ECDH_RSA_WITH_RC4_128_SHA");
    public static final C08530wj A1C = A00("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A1D = A00("TLS_ECDH_anon_WITH_AES_128_CBC_SHA");
    public static final C08530wj A1E = A00("TLS_ECDH_anon_WITH_AES_256_CBC_SHA");
    public static final C08530wj A1F = A00("TLS_ECDH_anon_WITH_NULL_SHA");
    public static final C08530wj A1G = A00("TLS_ECDH_anon_WITH_RC4_128_SHA");
    public static final C08530wj A1H = A00("TLS_EMPTY_RENEGOTIATION_INFO_SCSV");
    public static final C08530wj A1I = A00("TLS_FALLBACK_SCSV");
    public static final C08530wj A1J = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5");
    public static final C08530wj A1K = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA");
    public static final C08530wj A1L = A00("TLS_KRB5_EXPORT_WITH_RC4_40_MD5");
    public static final C08530wj A1M = A00("TLS_KRB5_EXPORT_WITH_RC4_40_SHA");
    public static final C08530wj A1N = A00("TLS_KRB5_WITH_3DES_EDE_CBC_MD5");
    public static final C08530wj A1O = A00("TLS_KRB5_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A1P = A00("TLS_KRB5_WITH_DES_CBC_MD5");
    public static final C08530wj A1Q = A00("TLS_KRB5_WITH_DES_CBC_SHA");
    public static final C08530wj A1R = A00("TLS_KRB5_WITH_RC4_128_MD5");
    public static final C08530wj A1S = A00("TLS_KRB5_WITH_RC4_128_SHA");
    public static final C08530wj A1T = A00("TLS_PSK_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A1U = A00("TLS_PSK_WITH_AES_128_CBC_SHA");
    public static final C08530wj A1V = A00("TLS_PSK_WITH_AES_256_CBC_SHA");
    public static final C08530wj A1W = A00("TLS_PSK_WITH_RC4_128_SHA");
    public static final C08530wj A1X = A00("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final C08530wj A1Y = A00("SSL_RSA_EXPORT_WITH_RC4_40_MD5");
    public static final C08530wj A1Z = A00("SSL_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C08530wj A1a = A00("TLS_RSA_WITH_AES_128_CBC_SHA");
    public static final C08530wj A1b = A00("TLS_RSA_WITH_AES_128_CBC_SHA256");
    public static final C08530wj A1c = A00("TLS_RSA_WITH_AES_128_GCM_SHA256");
    public static final C08530wj A1d = A00("TLS_RSA_WITH_AES_256_CBC_SHA");
    public static final C08530wj A1e = A00("TLS_RSA_WITH_AES_256_CBC_SHA256");
    public static final C08530wj A1f = A00("TLS_RSA_WITH_AES_256_GCM_SHA384");
    public static final C08530wj A1g = A00("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final C08530wj A1h = A00("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final C08530wj A1i = A00("SSL_RSA_WITH_DES_CBC_SHA");
    public static final C08530wj A1j = A00("SSL_RSA_WITH_NULL_MD5");
    public static final C08530wj A1k = A00("SSL_RSA_WITH_NULL_SHA");
    public static final C08530wj A1l = A00("TLS_RSA_WITH_NULL_SHA256");
    public static final C08530wj A1m = A00("SSL_RSA_WITH_RC4_128_MD5");
    public static final C08530wj A1n = A00("SSL_RSA_WITH_RC4_128_SHA");
    public static final C08530wj A1o = A00("TLS_RSA_WITH_SEED_CBC_SHA");
    public static final ConcurrentMap<String, C08530wj> A1p = new ConcurrentHashMap();
    public final String A00;

    public static C08530wj A00(String str) {
        C08530wj putIfAbsent;
        ConcurrentMap<String, C08530wj> concurrentMap = A1p;
        C08530wj r1 = concurrentMap.get(str);
        if (r1 != null || (putIfAbsent = concurrentMap.putIfAbsent(str, (r1 = new C08530wj(str)))) == null) {
            return r1;
        }
        return putIfAbsent;
    }

    public C08530wj(String str) {
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
