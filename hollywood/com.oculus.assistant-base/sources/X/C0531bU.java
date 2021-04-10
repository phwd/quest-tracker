package X;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: X.bU  reason: case insensitive filesystem */
public final class C0531bU {
    public static final C0531bU A01 = A00("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");
    public static final C0531bU A02 = A00("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A03 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
    public static final C0531bU A04 = A00("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
    public static final C0531bU A05 = A00("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256");
    public static final C0531bU A06 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
    public static final C0531bU A07 = A00("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");
    public static final C0531bU A08 = A00("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384");
    public static final C0531bU A09 = A00("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA");
    public static final C0531bU A0A = A00("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA");
    public static final C0531bU A0B = A00("SSL_DHE_DSS_WITH_DES_CBC_SHA");
    public static final C0531bU A0C = A00("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final C0531bU A0D = A00("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A0E = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
    public static final C0531bU A0F = A00("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final C0531bU A0G = A00("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final C0531bU A0H = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
    public static final C0531bU A0I = A00("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
    public static final C0531bU A0J = A00("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final C0531bU A0K = A00("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final C0531bU A0L = A00("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final C0531bU A0M = A00("SSL_DHE_RSA_WITH_DES_CBC_SHA");
    public static final C0531bU A0N = A00("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA");
    public static final C0531bU A0O = A00("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");
    public static final C0531bU A0P = A00("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A0Q = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA");
    public static final C0531bU A0R = A00("TLS_DH_anon_WITH_AES_128_CBC_SHA256");
    public static final C0531bU A0S = A00("TLS_DH_anon_WITH_AES_128_GCM_SHA256");
    public static final C0531bU A0T = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA");
    public static final C0531bU A0U = A00("TLS_DH_anon_WITH_AES_256_CBC_SHA256");
    public static final C0531bU A0V = A00("TLS_DH_anon_WITH_AES_256_GCM_SHA384");
    public static final C0531bU A0W = A00("SSL_DH_anon_WITH_DES_CBC_SHA");
    public static final C0531bU A0X = A00("SSL_DH_anon_WITH_RC4_128_MD5");
    public static final C0531bU A0Y = A00("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A0Z = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
    public static final C0531bU A0a = A00("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final C0531bU A0b = A00("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final C0531bU A0c = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
    public static final C0531bU A0d = A00("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final C0531bU A0e = A00("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final C0531bU A0f = A00("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final C0531bU A0g = A00("TLS_ECDHE_ECDSA_WITH_NULL_SHA");
    public static final C0531bU A0h = A00("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");
    public static final C0531bU A0i = A00("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA");
    public static final C0531bU A0j = A00("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA");
    public static final C0531bU A0k = A00("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A0l = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
    public static final C0531bU A0m = A00("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
    public static final C0531bU A0n = A00("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
    public static final C0531bU A0o = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
    public static final C0531bU A0p = A00("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
    public static final C0531bU A0q = A00("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
    public static final C0531bU A0r = A00("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
    public static final C0531bU A0s = A00("TLS_ECDHE_RSA_WITH_NULL_SHA");
    public static final C0531bU A0t = A00("TLS_ECDHE_RSA_WITH_RC4_128_SHA");
    public static final C0531bU A0u = A00("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A0v = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");
    public static final C0531bU A0w = A00("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");
    public static final C0531bU A0x = A00("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256");
    public static final C0531bU A0y = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");
    public static final C0531bU A0z = A00("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");
    public static final C0531bU A10 = A00("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384");
    public static final C0531bU A11 = A00("TLS_ECDH_ECDSA_WITH_NULL_SHA");
    public static final C0531bU A12 = A00("TLS_ECDH_ECDSA_WITH_RC4_128_SHA");
    public static final C0531bU A13 = A00("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A14 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");
    public static final C0531bU A15 = A00("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");
    public static final C0531bU A16 = A00("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256");
    public static final C0531bU A17 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");
    public static final C0531bU A18 = A00("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");
    public static final C0531bU A19 = A00("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384");
    public static final C0531bU A1A = A00("TLS_ECDH_RSA_WITH_NULL_SHA");
    public static final C0531bU A1B = A00("TLS_ECDH_RSA_WITH_RC4_128_SHA");
    public static final C0531bU A1C = A00("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A1D = A00("TLS_ECDH_anon_WITH_AES_128_CBC_SHA");
    public static final C0531bU A1E = A00("TLS_ECDH_anon_WITH_AES_256_CBC_SHA");
    public static final C0531bU A1F = A00("TLS_ECDH_anon_WITH_NULL_SHA");
    public static final C0531bU A1G = A00("TLS_ECDH_anon_WITH_RC4_128_SHA");
    public static final C0531bU A1H = A00("TLS_EMPTY_RENEGOTIATION_INFO_SCSV");
    public static final C0531bU A1I = A00("TLS_FALLBACK_SCSV");
    public static final C0531bU A1J = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5");
    public static final C0531bU A1K = A00("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA");
    public static final C0531bU A1L = A00("TLS_KRB5_EXPORT_WITH_RC4_40_MD5");
    public static final C0531bU A1M = A00("TLS_KRB5_EXPORT_WITH_RC4_40_SHA");
    public static final C0531bU A1N = A00("TLS_KRB5_WITH_3DES_EDE_CBC_MD5");
    public static final C0531bU A1O = A00("TLS_KRB5_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A1P = A00("TLS_KRB5_WITH_DES_CBC_MD5");
    public static final C0531bU A1Q = A00("TLS_KRB5_WITH_DES_CBC_SHA");
    public static final C0531bU A1R = A00("TLS_KRB5_WITH_RC4_128_MD5");
    public static final C0531bU A1S = A00("TLS_KRB5_WITH_RC4_128_SHA");
    public static final C0531bU A1T = A00("TLS_PSK_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A1U = A00("TLS_PSK_WITH_AES_128_CBC_SHA");
    public static final C0531bU A1V = A00("TLS_PSK_WITH_AES_256_CBC_SHA");
    public static final C0531bU A1W = A00("TLS_PSK_WITH_RC4_128_SHA");
    public static final C0531bU A1X = A00("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA");
    public static final C0531bU A1Y = A00("SSL_RSA_EXPORT_WITH_RC4_40_MD5");
    public static final C0531bU A1Z = A00("SSL_RSA_WITH_3DES_EDE_CBC_SHA");
    public static final C0531bU A1a = A00("TLS_RSA_WITH_AES_128_CBC_SHA");
    public static final C0531bU A1b = A00("TLS_RSA_WITH_AES_128_CBC_SHA256");
    public static final C0531bU A1c = A00("TLS_RSA_WITH_AES_128_GCM_SHA256");
    public static final C0531bU A1d = A00("TLS_RSA_WITH_AES_256_CBC_SHA");
    public static final C0531bU A1e = A00("TLS_RSA_WITH_AES_256_CBC_SHA256");
    public static final C0531bU A1f = A00("TLS_RSA_WITH_AES_256_GCM_SHA384");
    public static final C0531bU A1g = A00("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA");
    public static final C0531bU A1h = A00("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA");
    public static final C0531bU A1i = A00("SSL_RSA_WITH_DES_CBC_SHA");
    public static final C0531bU A1j = A00("SSL_RSA_WITH_NULL_MD5");
    public static final C0531bU A1k = A00("SSL_RSA_WITH_NULL_SHA");
    public static final C0531bU A1l = A00("TLS_RSA_WITH_NULL_SHA256");
    public static final C0531bU A1m = A00("SSL_RSA_WITH_RC4_128_MD5");
    public static final C0531bU A1n = A00("SSL_RSA_WITH_RC4_128_SHA");
    public static final C0531bU A1o = A00("TLS_RSA_WITH_SEED_CBC_SHA");
    public static final ConcurrentMap A1p = new ConcurrentHashMap();
    public final String A00;

    public static C0531bU A00(String str) {
        ConcurrentMap concurrentMap = A1p;
        C0531bU bUVar = (C0531bU) concurrentMap.get(str);
        if (bUVar == null) {
            bUVar = new C0531bU(str);
            C0531bU bUVar2 = (C0531bU) concurrentMap.putIfAbsent(str, bUVar);
            if (bUVar2 != null) {
                return bUVar2;
            }
        }
        return bUVar;
    }

    public C0531bU(String str) {
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
