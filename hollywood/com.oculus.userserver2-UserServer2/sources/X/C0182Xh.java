package X;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: X.Xh  reason: case insensitive filesystem */
public final class C0182Xh {
    public static final C0182Xh A01 = A00("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");
    public static final C0182Xh A02 = A00("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A03 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A04 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
    public static final C0182Xh A05 = A00("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256");
    public static final C0182Xh A06 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A07 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");
    public static final C0182Xh A08 = A00("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384");
    public static final C0182Xh A09 = A00("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA");
    public static final C0182Xh A0A = A00("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA");
    public static final C0182Xh A0B = A00("SSL_DHE_DSS_WITH_DES_CBC_SHA");
    public static final C0182Xh A0C = A00("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final C0182Xh A0D = A00("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A0E = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A0F = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final C0182Xh A0G = A00("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final C0182Xh A0H = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A0I = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
    public static final C0182Xh A0J = A00("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final C0182Xh A0K = A00("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final C0182Xh A0L = A00("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final C0182Xh A0M = A00("SSL_DHE_RSA_WITH_DES_CBC_SHA");
    public static final C0182Xh A0N = A00("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA");
    public static final C0182Xh A0O = A00("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");
    public static final C0182Xh A0P = A00("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A0Q = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A0R = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA256");
    public static final C0182Xh A0S = A00("TLS_DH_anon_WITH_AES_128_GCM_SHA256");
    public static final C0182Xh A0T = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A0U = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA256");
    public static final C0182Xh A0V = A00("TLS_DH_anon_WITH_AES_256_GCM_SHA384");
    public static final C0182Xh A0W = A00("SSL_DH_anon_WITH_DES_CBC_SHA");
    public static final C0182Xh A0X = A00("SSL_DH_anon_WITH_RC4_128_MD5");
    public static final C0182Xh A0Y = A00("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A0Z = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A0a = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final C0182Xh A0b = A00("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final C0182Xh A0c = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A0d = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final C0182Xh A0e = A00("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final C0182Xh A0f = A00("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final C0182Xh A0g = A00("TLS_ECDHE_ECDSA_WITH_NULL_SHA");
    public static final C0182Xh A0h = A00("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");
    public static final C0182Xh A0i = A00("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A0j = A00("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A0k = A00("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A0l = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A0m = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final C0182Xh A0n = A00("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final C0182Xh A0o = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A0p = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
    public static final C0182Xh A0q = A00("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final C0182Xh A0r = A00("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final C0182Xh A0s = A00("TLS_ECDHE_RSA_WITH_NULL_SHA");
    public static final C0182Xh A0t = A00("TLS_ECDHE_RSA_WITH_RC4_128_SHA");
    public static final C0182Xh A0u = A00("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A0v = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A0w = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final C0182Xh A0x = A00("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final C0182Xh A0y = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A0z = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final C0182Xh A10 = A00("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final C0182Xh A11 = A00("TLS_ECDH_ECDSA_WITH_NULL_SHA");
    public static final C0182Xh A12 = A00("TLS_ECDH_ECDSA_WITH_RC4_128_SHA");
    public static final C0182Xh A13 = A00("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A14 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A15 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");
    public static final C0182Xh A16 = A00("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256");
    public static final C0182Xh A17 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A18 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");
    public static final C0182Xh A19 = A00("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384");
    public static final C0182Xh A1A = A00("TLS_ECDH_RSA_WITH_NULL_SHA");
    public static final C0182Xh A1B = A00("TLS_ECDH_RSA_WITH_RC4_128_SHA");
    public static final C0182Xh A1C = A00("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A1D = A00("TLS_ECDH_anon_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A1E = A00("TLS_ECDH_anon_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A1F = A00("TLS_ECDH_anon_WITH_NULL_SHA");
    public static final C0182Xh A1G = A00("TLS_ECDH_anon_WITH_RC4_128_SHA");
    public static final C0182Xh A1H = A00("TLS_EMPTY_RENEGOTIATION_INFO_SCSV");
    public static final C0182Xh A1I = A00("TLS_FALLBACK_SCSV");
    public static final C0182Xh A1J = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5");
    public static final C0182Xh A1K = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA");
    public static final C0182Xh A1L = A00("TLS_KRB5_EXPORT_WITH_RC4_40_MD5");
    public static final C0182Xh A1M = A00("TLS_KRB5_EXPORT_WITH_RC4_40_SHA");
    public static final C0182Xh A1N = A00("TLS_KRB5_WITH_3DES_EDE_CBC_MD5");
    public static final C0182Xh A1O = A00("TLS_KRB5_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A1P = A00("TLS_KRB5_WITH_DES_CBC_MD5");
    public static final C0182Xh A1Q = A00("TLS_KRB5_WITH_DES_CBC_SHA");
    public static final C0182Xh A1R = A00("TLS_KRB5_WITH_RC4_128_MD5");
    public static final C0182Xh A1S = A00("TLS_KRB5_WITH_RC4_128_SHA");
    public static final C0182Xh A1T = A00("TLS_PSK_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A1U = A00("TLS_PSK_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A1V = A00("TLS_PSK_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A1W = A00("TLS_PSK_WITH_RC4_128_SHA");
    public static final C0182Xh A1X = A00("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final C0182Xh A1Y = A00("SSL_RSA_EXPORT_WITH_RC4_40_MD5");
    public static final C0182Xh A1Z = A00("SSL_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0182Xh A1a = A00("TLS_RSA_WITH_AES_128_CBC_SHA");
    public static final C0182Xh A1b = A00("TLS_RSA_WITH_AES_128_CBC_SHA256");
    public static final C0182Xh A1c = A00("TLS_RSA_WITH_AES_128_GCM_SHA256");
    public static final C0182Xh A1d = A00("TLS_RSA_WITH_AES_256_CBC_SHA");
    public static final C0182Xh A1e = A00("TLS_RSA_WITH_AES_256_CBC_SHA256");
    public static final C0182Xh A1f = A00("TLS_RSA_WITH_AES_256_GCM_SHA384");
    public static final C0182Xh A1g = A00("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final C0182Xh A1h = A00("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final C0182Xh A1i = A00("SSL_RSA_WITH_DES_CBC_SHA");
    public static final C0182Xh A1j = A00("SSL_RSA_WITH_NULL_MD5");
    public static final C0182Xh A1k = A00("SSL_RSA_WITH_NULL_SHA");
    public static final C0182Xh A1l = A00("TLS_RSA_WITH_NULL_SHA256");
    public static final C0182Xh A1m = A00("SSL_RSA_WITH_RC4_128_MD5");
    public static final C0182Xh A1n = A00("SSL_RSA_WITH_RC4_128_SHA");
    public static final C0182Xh A1o = A00("TLS_RSA_WITH_SEED_CBC_SHA");
    public static final ConcurrentMap<String, C0182Xh> A1p = new ConcurrentHashMap();
    public final String A00;

    public static C0182Xh A00(String str) {
        C0182Xh putIfAbsent;
        ConcurrentMap<String, C0182Xh> concurrentMap = A1p;
        C0182Xh xh = concurrentMap.get(str);
        if (xh != null || (putIfAbsent = concurrentMap.putIfAbsent(str, (xh = new C0182Xh(str)))) == null) {
            return xh;
        }
        return putIfAbsent;
    }

    public C0182Xh(String str) {
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
