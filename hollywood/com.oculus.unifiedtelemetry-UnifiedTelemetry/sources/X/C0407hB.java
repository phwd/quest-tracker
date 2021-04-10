package X;

import android.annotation.SuppressLint;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: X.hB  reason: case insensitive filesystem */
public final class C0407hB {
    public static final C0409hD A00 = new C0409hD("fbandroid_family_release", "JDi84d23vQJtX_ifWYs7Xlu4JLM", "gHUKrFoWceNwTt-LAmskjOi6tbjZz0g1PCk6sS5ZNZo");
    public static final C0409hD A01 = new C0409hD("fbandroid_debug", "Xo8WBi6jzSxKDVR4drqm84yr9iU", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
    public static final C0409hD A02 = new C0409hD("fbandroid_inhouse_v1", "pLdFLi7Y9fGRBYynu_0msNMhS_w", "Ep3FNIDivxtVz27sIF_g9Z0rn4Hn7k-2fOy77iWgsIA");
    public static final C0409hD A03 = new C0409hD("fbandroid_inhouse_v2", "RkHFCTArxterQ7h9g2sQjVr4Ej0", "wDby32gn_uCqMVAmAc62_hOfNu_VSqMa5uyB5sNI4dk");
    public static final C0409hD A04 = new C0409hD("fbandroid_oculus_assistant", "eBLxbTAHR6nuEIur96W48dDc7Io", "rO1i0-x-Hhu8_RdBiQjfrXe1WnMMVVkuOoFfs8ri2eE");
    public static final C0409hD A05 = new C0409hD("fbandroid_release", "ijxLJi1yGs1JpL-X1SExmchvork", "4_nh4M-Z0OVqBVumXiQbM5n3zqUkMmsM3W7BMn7Q_cE");
    public static final C0409hD A06 = new C0409hD("fbandroid_samples", "BcapvdaWLq6ZfAglJbxXazMNBFU", "jW-jYWbZ4GQZ28iGykpgUFoIDlGPXHb2sIWpDljhlYw");
    public static final C0409hD A07 = new C0409hD("games_release", "nFGQkG3LpFTag1g8R3ppoA0_NjA", "cUlH0jQYllHE5u0OaOFfOj_2ZPBtYk1dZtKmV4QmWOY");
    public static final C0409hD A08 = new C0409hD("instagram_release", "xW-31ZG6ZwTfBH_Zj1NTcv6gAhE", "Xz5Q9DVYPJrmJjAqcfc0AEQIen4sYK2s_CVCBamT4wU");
    public static final C0409hD A09 = new C0409hD("milan_1p_apps_debug", "BWpXl1bYKD6UbUpHmnVCYzNKahQ", "5lv0pjBMtKPzukwULDKBfs89rl-zr-HlzxueI_JPrwM");
    public static final C0409hD A0A = new C0409hD("milan_1p_apps_release", "IbLe4s4vmD9fTAOkRpKbhqq5uo8", "i1R0ZwdXk2ev6WLsW1iXdNyytsuVi570wNd9O6D5wkA");
    public static final C0409hD A0B = new C0409hD("milan_1p_nonpriv_apps_debug", "ImH0QTASejtEcTMC1lnLKDYl8E0", "AuYCk4ZRoWy5MJTr4GmbZSKv7vsGVtVR2oLiOKKp3qs");
    public static final C0409hD A0C = new C0409hD("milan_1p_nonpriv_apps_release", "FbZBdbtMq7Tbx7vSAeVboB_fujk", "tUeTbfUVkQ4kAyXHrrDqNU9CGPX_KznP-HjvMEDCN1I");
    public static final C0409hD A0D = new C0409hD("milan_1p_priv_apps_debug", "56Okh3GNc5c7nKYtifJU6wAaMW8", "BhbXF71VGdruXI2K92clfscrAA8xTQxV0mTVQSyTzJM");
    public static final C0409hD A0E = new C0409hD("milan_1p_priv_apps_release", "sUakPG-ALPFrRRLsehhiEDo5Ko4", "A9A-oXiTMLDip81kTvgXrtXtypfecxU3vmuNPlCfkOM");
    public static final C0409hD A0F = new C0409hD("milan_2p_apps_debug", "zOQ8PKut0us9IIk389BIVdyiyVE", "F5OoLdx6B8GGOezxJY0QifKgn3FjXCyp54J8bPv3yfI");
    public static final C0409hD A0G = new C0409hD("milan_2p_apps_release", "UVwOviJa-1xIHO7IY6Em4xbg2Fo", "1sAWMT6a6V1REH9thlTTQffc-bEC_Gd1kx1OASk5E8w");
    public static final C0409hD A0H = new C0409hD("milan_companion_apps_debug", "VKEg191XEt-E4PtbqIZyECrT3Ao", "OaxNl9DzmpbAu1HcjBRq8oUlJBWeTEWmnftIpuLE0dY");
    public static final C0409hD A0I = new C0409hD("milan_companion_apps_release", "FtWD8cHxwbOmofyrSb92W1lfhuw", "HCybH_tpKpB-OxHiTeHi10hWinUASIoN77-QK_bv5Jk");
    public static final C0409hD A0J = new C0409hD("novi_wallet_release", "Oyj_ueKRyiouZ9S5m8ZJVmDaY2g", "f7gJRqqNdFy6c-h6YDccpOlV9siIvTfWhjFKXhnNpJ0");
    public static final C0409hD A0K = new C0409hD("novi_wallet_debug", "Xo8WBi6jzSxKDVR4drqm84yr9iU", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
    public static final C0409hD A0L = new C0409hD("oculus_apps_release", "MxZgtt071YLz39PLrkVGckZooCE", "3C-DTjwZQjeLFPB2yCzRq1nqZWa_9HMLIEfpjvyfna8");
    public static final C0409hD A0M = new C0409hD("oculus_core_apps_release", "mKGAnzw-eRcOXfgTW1AOz6Od8b4", "AS-BZcGR7-j7DW1GBqpKusFNd9HZfVNAhgyfgQRaoVc");
    public static final C0409hD A0N = new C0409hD("oculus_system_debug", "JxluOGuHXnat9wDn6oTkxu7jPfo", "yKLpvM9ZfC-23Ga-4pP8E_L8R-x3vGsrDVLBH1EZKrg");
    public static final C0409hD A0O = new C0409hD("oculus_priv_apps_release", "eBLxbTAHR6nuEIur96W48dDc7Io", "rO1i0-x-Hhu8_RdBiQjfrXe1WnMMVVkuOoFfs8ri2eE");
    public static final C0409hD A0P = new C0409hD("oculus_prod_release", "Sr9mhPKOEwo6NysnYn803dZ3UiY", "ZSEpCPBvG4p97Oj7FYJ_pqcOLvWpiwfdZ4BcNkPF08M");
    public static final C0409hD A0Q = new C0409hD("oculus_system_release", "j7CX6aQ1hv171mwrubptrNdpqZs", "Vbp2NjfUrxP3oENKQRG8ePxWU-Z-ShV2n0jLNhU21Vc");
    public static final C0409hD A0R = new C0409hD("oculus_system_release", "2KUshwWT2olt01Y9V_BeTOrMcrg", "6PDrONEnh3P7htSccijrhAA8B9sXJeGvGHy-ZuMff34");
    public static final C0409hD A0S = new C0409hD("oxygen_debug", "7XE60X540nq3JXIiFpcVSgM8diY", "Jm4bl26QMphvIVgzVUeQb6f37Ys3IKRmCw0LBgLJBzs");
    public static final C0409hD A0T = new C0409hD("oxygen_release", "e6fv6XFRr-tXEDJmsSANhagF19Y", "uSEJtZCVlVzKr17Lzw8VPslkCkZYwQFmetlrfkmaQJI");
    public static final C0409hD A0U = new C0409hD("portal_frameworks", "OO66WgNh5QhN2YJvbm7WsMFraIk", "ZiIZ7fdUdXcbGMNL6M656x4mTCC9kdSoSNBOifLrBAA");
    public static final C0409hD A0V = new C0409hD("portal_frameworks_debug", "qvsjgntAXl2NeUeJG7WcMOO67_U", "_7txYJOR7-3x7Od7kFQdFwsbKaGzch2f0hsTE8UtSZo");
    public static final C0409hD A0W = new C0409hD("aloha_prod", "5IMQjt1hTC_xqzPM0QypzwhjD9g", "T4Q-4dJKibgNvwschNsyH9YBK3Hwl5zTIkQlBgZu10E");
    public static final C0409hD A0X = new C0409hD("portal_1p_nonprivapps", "d9u8HZP0Sr8IRZcknkT90o1dA4I", "JnN9N-awG-f7YQ7ROKJoQilHd7EWI2fcuf7cVk8IwbE");
    public static final C0409hD A0Y = new C0409hD("portal_1p_nonprivapps_debug", "aIIMIXiHjw__OMc2ophMdya2vHQ", "qadsRSPy8q2oOtZucRAyNlbBCyrbDQYWnD6ZESwR0vs");
    public static final C0409hD A0Z = new C0409hD("portal_1p_privapps", "ayxL3GZmow1e3H96rZTKomVeN6k", "2IY_S9EZqWwT_9dcezZrynlWkcjV0xIOItAdj_LCCzc");
    public static final C0409hD A0a = new C0409hD("portal_1p_privapps_debug", "FRNYKa3Xwpy4PBUuvctQLZyChQw", "ztXcjgEmmxMKYWXyXR1OtAW6codwAh6kiOzYzpxMCM4");
    public static final C0409hD A0b = new C0409hD("portal_2p", "wcJiR08sDZHVuZ_UeU3M5Lfj1lU", "VKYCWsqDaYYL11gG_pE8Boqff1awXJofnT5IhJbw6uk");
    public static final C0409hD A0c = new C0409hD("portal_2p_debug", "RAVUNRBo-sqseoeNZBd_uekYbJM", "8HU_MHdtxR7HUwGeKgfv4kXzi2-XbN5whjV851JZJGU");
    public static final C0409hD A0d = new C0409hD("portal_appmanager", "jxVEf9-ocDhrDkkMAwlVmGuOugc", "gtg55g1XfaUz6E-QxdmYF3r07MuWN7xqo-Pau3WtV2A");
    public static final C0409hD A0e = new C0409hD("portal_appmanager_debug", "szpKVLjLIa_o9Cq03JS6cPeMSmw", "Y5Hqye7Bbux7I1qFFmbE6EqILj2ssTFQB9Ss6LwpmGE");
    public static final C0409hD A0f = new C0409hD("portal_companionapps", "X64eihZAou6lkJdulh7SBnnTnDU", "2eC54WVokOBCMfraLI5w5AkPzV4OhG2rUnfhWHKBM0M");
    public static final C0409hD A0g = new C0409hD("portal_df", "AbX42B4J9OEFPkJ2iesKntcZmdU", "oSjSY8pqhXpum64U6nRyis9rV9XfVU3BgyBK6ru6RS8");
    public static final C0409hD A0h = new C0409hD("portal_df_debug", "KIinUUA_jQBvJqp_vHv5OA-fM30", "nrLY2buLK5TINVGKW_QqkWIpddkVCGkpUdvmsyG4vlc");
    public static final C0409hD A0i = new C0409hD("portal_fb_inhousev2_debug", "pGLr1wNX3vElqWNF4QYKaubskS4", "jtZAS_lKBE3WXzNRjrPIcqOuRU4Dh_MGihoNLNw3CKA");
    public static final C0409hD A0j = new C0409hD("portal_fb_samples_debug", "aIIMIXiHjw__OMc2ophMdya2vHQ", "qadsRSPy8q2oOtZucRAyNlbBCyrbDQYWnD6ZESwR0vs");
    public static final C0409hD A0k = new C0409hD("portal_platform_aloha", "n0_Ssi2N8d9-5-hJV0WQ1rt5nlk", "0qONa7PbO-l9JLKlJjkkf_VDfDI7jJKEQCKJX1nzxus");
    public static final C0409hD A0l;
    public static final C0409hD A0m = new C0409hD("portal_platform_catalina", "gZ2hI80bGDGB_XVfiJOSCktxXwI", "-HdCA-5jUGjnsyAYKdXRg4w3tVAHYZTU3EzUOWRVTX0");
    public static final C0409hD A0n;
    public static final C0409hD A0o = new C0409hD("portal_platform_omni", "HyZQnz_A_xFUJNx9-uRxfLglhH0", "W1BzWTPVNZBtrA45RvTcbkVphwqyUdZwQEL7X-RqPpM");
    public static final C0409hD A0p = new C0409hD("portal_platform_omni_debug", "a_U1bAb7cZcQ1uOM0JdyiPdHWq8", "b2sRSFyeAdgq4NbTDsF6EuDfHreyS9x2Pp7oKe8QclI");
    public static final C0409hD A0q = new C0409hD("portal_whatsapp", "FKzVxsqJpYsJuFdQqTE7IEgYC08", "7oVvh3Fck1xX0J5u42DHceCqMyIqewU2TWaJlChTsZA");
    public static final C0409hD A0r = new C0409hD("portal_whatsapp_debug", "AMOxgVfUuJgIn3TkLNUWQ2QWkeY", "y-7IuVPL3L_a06a23Nl8rcsi51i83grZLyXD-OMtQO0");
    public static final C0409hD A0s = new C0409hD("portal_work", "jNzSyAsLWIABbRrSOiutGvlokAA", "HC4rwCH0AxqzQcvYDrHg5ikHBl2GnUuRnJLwuJJyt8o");
    public static final C0409hD A0t = new C0409hD("portal_work_debug", "mVFabvwIZ6M8VQPvGgdcTrwZhXI", "ovbQAw7LTrM4PSNadgRBwp4vfR5ma3mkb1x-g0cLqhk");
    public static final C0409hD A0u = new C0409hD("stella_release", "_H-OYUFZvtFrvtzR6NdYRD0eaTA", "gbBIh-jriiCfLl5AQq3PaWv3Uemavb2hMrZhZ_3KlDE");
    public static final C0409hD A0v = new C0409hD("study_release", "r9BCZbqOeyVExd0xAOMmz1UyHz4", "GVQYWvhotR_1RBv0Am3VA2bLMifS4uOCNDeaKSVc7CU");
    public static final C0409hD A0w = new C0409hD("viewpoints_release", "XuWTzVfchfTAN8XtLJfChtm7034", "XdkQEeiVeIyDkvBEAHtGJKKHfdsrOFf9te68UpyJVhA");
    public static final C0409hD A0x = new C0409hD("whatsapp_debug", "HfqsFpVx2hvmL2FpTQgY5bCSyHo", "HEIr5yy3eXd1ynm5SueBb-3LsOp9aEYClzuRNXxJzFc");
    public static final C0409hD A0y = new C0409hD("whatsapp_release", "OKD31QX-GP7GT780Psqq8xDb15k", "OYfQQ9EK769ahxCzZxQY_lfg4ZtlPJ34JVj-tf_OXUQ");
    @SuppressLint({"NotAccessedPrivateField"})
    public static final Map<C0409hD, Set<C0409hD>> A0z = Collections.unmodifiableMap(new C0406hA());
    public static final Set<C0409hD> A10 = Collections.unmodifiableSet(new C0404h8());
    public static final Set<C0409hD> A11 = Collections.unmodifiableSet(new C0405h9());
    public static final Set<C0409hD> A12 = Collections.unmodifiableSet(new C0402h6());
    public static final Set<C0409hD> A13 = Collections.unmodifiableSet(new C0403h7());
    public static final Set<C0409hD> A14 = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A01)));
    public static final Set<C0409hD> A15 = Collections.unmodifiableSet(new HashSet(Arrays.asList(A00, A07)));
    public static final Set<C0409hD> A16 = Collections.unmodifiableSet(new HashSet(Arrays.asList(A02, A03, A06)));
    public static final Set<C0409hD> A17 = Collections.unmodifiableSet(new HashSet(Arrays.asList(A0i, A0j)));
    public static final Set<C0409hD> A18 = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A05)));
    public static final Set<C0409hD> A19 = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A08)));
    public static final Set<C0409hD> A1A = Collections.unmodifiableSet(new h4());
    public static final Set<C0409hD> A1B = Collections.unmodifiableSet(new HashSet(Arrays.asList(A0A, A0C, A0E, A0G, A0I)));
    public static final Set<C0409hD> A1C = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A0K)));
    public static final Set<C0409hD> A1D = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A0J)));
    public static final Set<C0409hD> A1E = Collections.unmodifiableSet(new HashSet(Arrays.asList(A01, A0N)));
    public static final Set<C0409hD> A1F = Collections.unmodifiableSet(new HashSet(Arrays.asList(A0P, A0M, A0L, A0O)));
    public static final Set<C0409hD> A1G = Collections.unmodifiableSet(new HashSet(Arrays.asList(A0R, A0Q)));
    public static final Set<C0409hD> A1H = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A0S)));
    public static final Set<C0409hD> A1I = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A0T)));
    public static final Set<C0409hD> A1J = Collections.unmodifiableSet(new C0400h3());
    public static final Set<C0409hD> A1K = Collections.unmodifiableSet(new HashSet(Arrays.asList(A0W, A0q)));
    public static final Set<C0409hD> A1L = Collections.unmodifiableSet(new HashSet(Arrays.asList(A0l, A0n, A0p)));
    public static final Set<C0409hD> A1M = Collections.unmodifiableSet(new HashSet(Arrays.asList(A0k, A0m, A0o)));
    public static final Set<C0409hD> A1N = Collections.unmodifiableSet(new C0401h5());
    public static final Set<C0409hD> A1O = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A0x)));
    public static final Set<C0409hD> A1P = Collections.unmodifiableSet(new HashSet(Collections.singletonList(A0y)));

    static {
        C0409hD hDVar = new C0409hD("portal_platform_aloha_debug", "Aj-bcwbSjGsTOUNEkMaqXHyTQeY", "5MCO54QyiJ31mua72pgMV7lET8XxQmxVGsxMmN3dAkA");
        A0l = hDVar;
        A0n = hDVar;
    }

    public static final <T> Set<T> A00(T... tArr) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(tArr)));
    }
}
