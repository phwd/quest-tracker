package com.facebook.secure.trustedapp;

import android.annotation.SuppressLint;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllFamilyTrustedSignatures {
    public static final Set<AppSignatureHash> ALL_FAMILY_DEBUG = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.facebook.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass6 */

        {
            addAll(AllFamilyTrustedSignatures.FB_DEBUG);
            addAll(AllFamilyTrustedSignatures.MILAN_DEBUG);
            addAll(AllFamilyTrustedSignatures.OCULUS_DEBUG);
            addAll(AllFamilyTrustedSignatures.OXYGEN_DEBUG);
            addAll(AllFamilyTrustedSignatures.PORTAL_DEBUG);
            addAll(AllFamilyTrustedSignatures.WHATSAPP_DEBUG);
            addAll(AllFamilyTrustedSignatures.NOVI_WALLET_DEBUG);
        }
    });
    public static final Set<AppSignatureHash> ALL_FAMILY_PROD = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.facebook.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass7 */

        {
            addAll(AllFamilyTrustedSignatures.FB_PROD);
            addAll(AllFamilyTrustedSignatures.FB_FAMILY_PROD);
            addAll(AllFamilyTrustedSignatures.INSTAGRAM_PROD);
            addAll(AllFamilyTrustedSignatures.OCULUS_PLATFORM_PROD);
            addAll(AllFamilyTrustedSignatures.OCULUS_SYSTEM_PLATFORM_PROD);
            addAll(AllFamilyTrustedSignatures.OXYGEN_PROD);
            addAll(AllFamilyTrustedSignatures.WHATSAPP_PROD);
            addAll(AllFamilyTrustedSignatures.PORTAL_PROD);
            addAll(AllFamilyTrustedSignatures.NOVI_WALLET_PROD);
        }
    });
    public static final Set<AppSignatureHash> ALL_FB = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.facebook.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass4 */

        {
            addAll(AllFamilyTrustedSignatures.FB_INHOUSE);
            addAll(AllFamilyTrustedSignatures.FB_PROD);
            addAll(AllFamilyTrustedSignatures.FB_FAMILY_PROD);
        }
    });
    public static final Set<AppSignatureHash> ALL_INHOUSE = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.facebook.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass5 */

        {
            addAll(AllFamilyTrustedSignatures.FB_INHOUSE);
        }
    });
    public static final AppSignatureHash FBANDROID_FAMILY_SIGNATURE_HASH_RELEASE = new AppSignatureHash("fbandroid_family_release", "JDi84d23vQJtX_ifWYs7Xlu4JLM", "gHUKrFoWceNwTt-LAmskjOi6tbjZz0g1PCk6sS5ZNZo");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_DEBUG = new AppSignatureHash("fbandroid_debug", "Xo8WBi6jzSxKDVR4drqm84yr9iU", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_IN_HOUSE_V1 = new AppSignatureHash("fbandroid_inhouse_v1", "pLdFLi7Y9fGRBYynu_0msNMhS_w", "Ep3FNIDivxtVz27sIF_g9Z0rn4Hn7k-2fOy77iWgsIA");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_IN_HOUSE_V2 = new AppSignatureHash("fbandroid_inhouse_v2", "RkHFCTArxterQ7h9g2sQjVr4Ej0", "wDby32gn_uCqMVAmAc62_hOfNu_VSqMa5uyB5sNI4dk");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_IN_OCULUS_ASSISTANT = new AppSignatureHash("fbandroid_oculus_assistant", "eBLxbTAHR6nuEIur96W48dDc7Io", "rO1i0-x-Hhu8_RdBiQjfrXe1WnMMVVkuOoFfs8ri2eE");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_RELEASE = new AppSignatureHash("fbandroid_release", "ijxLJi1yGs1JpL-X1SExmchvork", "4_nh4M-Z0OVqBVumXiQbM5n3zqUkMmsM3W7BMn7Q_cE");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_SAMPLES = new AppSignatureHash("fbandroid_samples", "BcapvdaWLq6ZfAglJbxXazMNBFU", "jW-jYWbZ4GQZ28iGykpgUFoIDlGPXHb2sIWpDljhlYw");
    public static final Set<AppSignatureHash> FB_DEBUG = Collections.unmodifiableSet(new HashSet(Collections.singletonList(FBANDROID_SIGNATURE_HASH_DEBUG)));
    public static final Set<AppSignatureHash> FB_FAMILY_PROD = Collections.unmodifiableSet(new HashSet(Arrays.asList(FBANDROID_FAMILY_SIGNATURE_HASH_RELEASE, GAMES_SIGNATURE_HASH_RELEASE)));
    public static final Set<AppSignatureHash> FB_INHOUSE = Collections.unmodifiableSet(new HashSet(Arrays.asList(FBANDROID_SIGNATURE_HASH_IN_HOUSE_V1, FBANDROID_SIGNATURE_HASH_IN_HOUSE_V2, FBANDROID_SIGNATURE_HASH_SAMPLES)));
    public static final Set<AppSignatureHash> FB_INHOUSE_DEBUG = Collections.unmodifiableSet(new HashSet(Arrays.asList(PORTAL_SIGNATURE_HASH_FB_IN_HOUSE_V2_DEBUG, PORTAL_SIGNATURE_HASH_FB_SAMPLES_DEBUG)));
    public static final Set<AppSignatureHash> FB_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(FBANDROID_SIGNATURE_HASH_RELEASE)));
    public static final AppSignatureHash GAMES_SIGNATURE_HASH_RELEASE = new AppSignatureHash("games_release", "nFGQkG3LpFTag1g8R3ppoA0_NjA", "cUlH0jQYllHE5u0OaOFfOj_2ZPBtYk1dZtKmV4QmWOY");
    public static final Set<AppSignatureHash> INSTAGRAM_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(INSTAGRAM_SIGNATURE_HASH_RELEASE)));
    public static final AppSignatureHash INSTAGRAM_SIGNATURE_HASH_RELEASE = new AppSignatureHash("instagram_release", "xW-31ZG6ZwTfBH_Zj1NTcv6gAhE", "Xz5Q9DVYPJrmJjAqcfc0AEQIen4sYK2s_CVCBamT4wU");
    public static final AppSignatureHash MILAN_1P_APPS_SIGNATURE_HASH_DEBUG = new AppSignatureHash("milan_1p_apps_debug", "BWpXl1bYKD6UbUpHmnVCYzNKahQ", "5lv0pjBMtKPzukwULDKBfs89rl-zr-HlzxueI_JPrwM");
    public static final AppSignatureHash MILAN_1P_APPS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("milan_1p_apps_release", "IbLe4s4vmD9fTAOkRpKbhqq5uo8", "i1R0ZwdXk2ev6WLsW1iXdNyytsuVi570wNd9O6D5wkA");
    public static final AppSignatureHash MILAN_1P_NON_PRIV_APPS_SIGNATURE_HASH_DEBUG = new AppSignatureHash("milan_1p_nonpriv_apps_debug", "ImH0QTASejtEcTMC1lnLKDYl8E0", "AuYCk4ZRoWy5MJTr4GmbZSKv7vsGVtVR2oLiOKKp3qs");
    public static final AppSignatureHash MILAN_1P_NON_PRIV_APPS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("milan_1p_nonpriv_apps_release", "FbZBdbtMq7Tbx7vSAeVboB_fujk", "tUeTbfUVkQ4kAyXHrrDqNU9CGPX_KznP-HjvMEDCN1I");
    public static final AppSignatureHash MILAN_1P_PRIV_APPS_SIGNATURE_HASH_DEBUG = new AppSignatureHash("milan_1p_priv_apps_debug", "56Okh3GNc5c7nKYtifJU6wAaMW8", "BhbXF71VGdruXI2K92clfscrAA8xTQxV0mTVQSyTzJM");
    public static final AppSignatureHash MILAN_1P_PRIV_APPS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("milan_1p_priv_apps_release", "sUakPG-ALPFrRRLsehhiEDo5Ko4", "A9A-oXiTMLDip81kTvgXrtXtypfecxU3vmuNPlCfkOM");
    public static final AppSignatureHash MILAN_2P_APPS_SIGNATURE_HASH_DEBUG = new AppSignatureHash("milan_2p_apps_debug", "zOQ8PKut0us9IIk389BIVdyiyVE", "F5OoLdx6B8GGOezxJY0QifKgn3FjXCyp54J8bPv3yfI");
    public static final AppSignatureHash MILAN_2P_APPS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("milan_2p_apps_release", "UVwOviJa-1xIHO7IY6Em4xbg2Fo", "1sAWMT6a6V1REH9thlTTQffc-bEC_Gd1kx1OASk5E8w");
    public static final AppSignatureHash MILAN_COMPANION_APP_SIGNATURE_HASH_DEBUG = new AppSignatureHash("milan_companion_apps_debug", "VKEg191XEt-E4PtbqIZyECrT3Ao", "OaxNl9DzmpbAu1HcjBRq8oUlJBWeTEWmnftIpuLE0dY");
    public static final AppSignatureHash MILAN_COMPANION_APP_SIGNATURE_HASH_RELEASE = new AppSignatureHash("milan_companion_apps_release", "FtWD8cHxwbOmofyrSb92W1lfhuw", "HCybH_tpKpB-OxHiTeHi10hWinUASIoN77-QK_bv5Jk");
    public static final Set<AppSignatureHash> MILAN_DEBUG = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.facebook.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass2 */

        {
            addAll(Arrays.asList(AllFamilyTrustedSignatures.MILAN_1P_APPS_SIGNATURE_HASH_DEBUG, AllFamilyTrustedSignatures.MILAN_1P_NON_PRIV_APPS_SIGNATURE_HASH_DEBUG, AllFamilyTrustedSignatures.MILAN_1P_PRIV_APPS_SIGNATURE_HASH_DEBUG, AllFamilyTrustedSignatures.MILAN_2P_APPS_SIGNATURE_HASH_DEBUG, AllFamilyTrustedSignatures.MILAN_COMPANION_APP_SIGNATURE_HASH_DEBUG));
        }
    });
    public static final Set<AppSignatureHash> MILAN_PROD = Collections.unmodifiableSet(new HashSet(Arrays.asList(MILAN_1P_APPS_SIGNATURE_HASH_RELEASE, MILAN_1P_NON_PRIV_APPS_SIGNATURE_HASH_RELEASE, MILAN_1P_PRIV_APPS_SIGNATURE_HASH_RELEASE, MILAN_2P_APPS_SIGNATURE_HASH_RELEASE, MILAN_COMPANION_APP_SIGNATURE_HASH_RELEASE)));
    public static final Set<AppSignatureHash> NOVI_WALLET_DEBUG = Collections.unmodifiableSet(new HashSet(Collections.singletonList(NOVI_WALLET_SIGNATURE_HASH_DEBUG)));
    public static final Set<AppSignatureHash> NOVI_WALLET_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(NOVI_WALLET_SIGNATURE_HASH)));
    public static final AppSignatureHash NOVI_WALLET_SIGNATURE_HASH = new AppSignatureHash("novi_wallet_release", "Oyj_ueKRyiouZ9S5m8ZJVmDaY2g", "f7gJRqqNdFy6c-h6YDccpOlV9siIvTfWhjFKXhnNpJ0");
    public static final AppSignatureHash NOVI_WALLET_SIGNATURE_HASH_DEBUG = new AppSignatureHash("novi_wallet_debug", "Xo8WBi6jzSxKDVR4drqm84yr9iU", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
    public static final AppSignatureHash OCULUS_APPS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oculus_apps_release", "MxZgtt071YLz39PLrkVGckZooCE", "3C-DTjwZQjeLFPB2yCzRq1nqZWa_9HMLIEfpjvyfna8");
    public static final AppSignatureHash OCULUS_CORE_APPS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oculus_core_apps_release", "mKGAnzw-eRcOXfgTW1AOz6Od8b4", "AS-BZcGR7-j7DW1GBqpKusFNd9HZfVNAhgyfgQRaoVc");
    public static final Set<AppSignatureHash> OCULUS_DEBUG = Collections.unmodifiableSet(new HashSet(Arrays.asList(FBANDROID_SIGNATURE_HASH_DEBUG, OCULUS_PLATFORM_SIGNATURE_HASH_DEBUG)));
    public static final Set<AppSignatureHash> OCULUS_PLATFORM_PROD = Collections.unmodifiableSet(new HashSet(Arrays.asList(OCULUS_PROD_SIGNATURE_HASH_RELEASE, OCULUS_CORE_APPS_SIGNATURE_HASH_RELEASE, OCULUS_APPS_SIGNATURE_HASH_RELEASE, OCULUS_PRIV_APPS_SIGNATURE_HASH_RELEASE)));
    public static final AppSignatureHash OCULUS_PLATFORM_SIGNATURE_HASH_DEBUG = new AppSignatureHash("oculus_system_debug", "JxluOGuHXnat9wDn6oTkxu7jPfo", "yKLpvM9ZfC-23Ga-4pP8E_L8R-x3vGsrDVLBH1EZKrg");
    public static final AppSignatureHash OCULUS_PRIV_APPS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oculus_priv_apps_release", "eBLxbTAHR6nuEIur96W48dDc7Io", "rO1i0-x-Hhu8_RdBiQjfrXe1WnMMVVkuOoFfs8ri2eE");
    public static final AppSignatureHash OCULUS_PROD_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oculus_prod_release", "Sr9mhPKOEwo6NysnYn803dZ3UiY", "ZSEpCPBvG4p97Oj7FYJ_pqcOLvWpiwfdZ4BcNkPF08M");
    public static final AppSignatureHash OCULUS_QUEST2_PLATFORM_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oculus_system_release", "j7CX6aQ1hv171mwrubptrNdpqZs", "Vbp2NjfUrxP3oENKQRG8ePxWU-Z-ShV2n0jLNhU21Vc");
    public static final AppSignatureHash OCULUS_QUEST_PLATFORM_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oculus_system_release", "2KUshwWT2olt01Y9V_BeTOrMcrg", "6PDrONEnh3P7htSccijrhAA8B9sXJeGvGHy-ZuMff34");
    public static final Set<AppSignatureHash> OCULUS_SYSTEM_PLATFORM_PROD = Collections.unmodifiableSet(new HashSet(Arrays.asList(OCULUS_QUEST_PLATFORM_SIGNATURE_HASH_RELEASE, OCULUS_QUEST2_PLATFORM_SIGNATURE_HASH_RELEASE)));
    public static final Set<AppSignatureHash> OXYGEN_DEBUG = Collections.unmodifiableSet(new HashSet(Collections.singletonList(OXYGEN_PRELOADS_SIGNATURE_HASH_DEBUG)));
    public static final AppSignatureHash OXYGEN_PRELOADS_SIGNATURE_HASH_DEBUG = new AppSignatureHash("oxygen_debug", "7XE60X540nq3JXIiFpcVSgM8diY", "Jm4bl26QMphvIVgzVUeQb6f37Ys3IKRmCw0LBgLJBzs");
    public static final AppSignatureHash OXYGEN_PRELOADS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oxygen_release", "e6fv6XFRr-tXEDJmsSANhagF19Y", "uSEJtZCVlVzKr17Lzw8VPslkCkZYwQFmetlrfkmaQJI");
    public static final Set<AppSignatureHash> OXYGEN_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(OXYGEN_PRELOADS_SIGNATURE_HASH_RELEASE)));
    public static final Set<AppSignatureHash> PORTAL_DEBUG = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.facebook.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass1 */

        {
            addAll(Arrays.asList(AllFamilyTrustedSignatures.FBANDROID_SIGNATURE_HASH_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_FB_IN_HOUSE_V2_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_FB_SAMPLES_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_PRIVAPPS_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_NONPRIVAPPS_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_2P_APPS_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_FRAMEWORKS_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_APPMANAGER_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_WHATSAPP_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_DF_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_WORK_DEBUG));
            addAll(AllFamilyTrustedSignatures.PORTAL_PLATFORM_DEBUG);
        }
    });
    public static final Set<AppSignatureHash> PORTAL_INHOUSE_PROD = Collections.unmodifiableSet(new HashSet(Arrays.asList(PORTAL_SIGNATURE_HASH_1P_APPS, PORTAL_SIGNATURE_HASH_WHATSAPP)));
    public static final Set<AppSignatureHash> PORTAL_PLATFORM_DEBUG = Collections.unmodifiableSet(new HashSet(Arrays.asList(PORTAL_SIGNATURE_HASH_PLATFORM_ALOHA_DEBUG, PORTAL_SIGNATURE_HASH_PLATFORM_CATALINA_DEBUG, PORTAL_SIGNATURE_HASH_PLATFORM_OMNI_DEBUG)));
    public static final Set<AppSignatureHash> PORTAL_PLATFORM_PROD = Collections.unmodifiableSet(new HashSet(Arrays.asList(PORTAL_SIGNATURE_HASH_PLATFORM_ALOHA, PORTAL_SIGNATURE_HASH_PLATFORM_CATALINA, PORTAL_SIGNATURE_HASH_PLATFORM_OMNI)));
    public static final Set<AppSignatureHash> PORTAL_PROD = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.facebook.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass3 */

        {
            addAll(Arrays.asList(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_APPS, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_PRIVAPPS, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_NONPRIVAPPS, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_2P_APPS, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_FRAMEWORKS, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_WHATSAPP, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_APPMANAGER, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_COMPANION_APPS, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_DF, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_WORK));
            addAll(AllFamilyTrustedSignatures.PORTAL_PLATFORM_PROD);
        }
    });
    public static final AppSignatureHash PORTAL_SIGNATURE_FRAMEWORKS = new AppSignatureHash("portal_frameworks", "OO66WgNh5QhN2YJvbm7WsMFraIk", "ZiIZ7fdUdXcbGMNL6M656x4mTCC9kdSoSNBOifLrBAA");
    public static final AppSignatureHash PORTAL_SIGNATURE_FRAMEWORKS_DEBUG = new AppSignatureHash("portal_frameworks_debug", "qvsjgntAXl2NeUeJG7WcMOO67_U", "_7txYJOR7-3x7Od7kFQdFwsbKaGzch2f0hsTE8UtSZo");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_1P_APPS = new AppSignatureHash("aloha_prod", "5IMQjt1hTC_xqzPM0QypzwhjD9g", "T4Q-4dJKibgNvwschNsyH9YBK3Hwl5zTIkQlBgZu10E");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_1P_NONPRIVAPPS = new AppSignatureHash("portal_1p_nonprivapps", "d9u8HZP0Sr8IRZcknkT90o1dA4I", "JnN9N-awG-f7YQ7ROKJoQilHd7EWI2fcuf7cVk8IwbE");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_1P_NONPRIVAPPS_DEBUG = new AppSignatureHash("portal_1p_nonprivapps_debug", "aIIMIXiHjw__OMc2ophMdya2vHQ", "qadsRSPy8q2oOtZucRAyNlbBCyrbDQYWnD6ZESwR0vs");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_1P_PRIVAPPS = new AppSignatureHash("portal_1p_privapps", "ayxL3GZmow1e3H96rZTKomVeN6k", "2IY_S9EZqWwT_9dcezZrynlWkcjV0xIOItAdj_LCCzc");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_1P_PRIVAPPS_DEBUG = new AppSignatureHash("portal_1p_privapps_debug", "FRNYKa3Xwpy4PBUuvctQLZyChQw", "ztXcjgEmmxMKYWXyXR1OtAW6codwAh6kiOzYzpxMCM4");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_2P_APPS = new AppSignatureHash("portal_2p", "wcJiR08sDZHVuZ_UeU3M5Lfj1lU", "VKYCWsqDaYYL11gG_pE8Boqff1awXJofnT5IhJbw6uk");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_2P_APPS_DEBUG = new AppSignatureHash("portal_2p_debug", "RAVUNRBo-sqseoeNZBd_uekYbJM", "8HU_MHdtxR7HUwGeKgfv4kXzi2-XbN5whjV851JZJGU");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_APPMANAGER = new AppSignatureHash("portal_appmanager", "jxVEf9-ocDhrDkkMAwlVmGuOugc", "gtg55g1XfaUz6E-QxdmYF3r07MuWN7xqo-Pau3WtV2A");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_APPMANAGER_DEBUG = new AppSignatureHash("portal_appmanager_debug", "szpKVLjLIa_o9Cq03JS6cPeMSmw", "Y5Hqye7Bbux7I1qFFmbE6EqILj2ssTFQB9Ss6LwpmGE");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_COMPANION_APPS = new AppSignatureHash("portal_companionapps", "X64eihZAou6lkJdulh7SBnnTnDU", "2eC54WVokOBCMfraLI5w5AkPzV4OhG2rUnfhWHKBM0M");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_DF = new AppSignatureHash("portal_df", "AbX42B4J9OEFPkJ2iesKntcZmdU", "oSjSY8pqhXpum64U6nRyis9rV9XfVU3BgyBK6ru6RS8");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_DF_DEBUG = new AppSignatureHash("portal_df_debug", "KIinUUA_jQBvJqp_vHv5OA-fM30", "nrLY2buLK5TINVGKW_QqkWIpddkVCGkpUdvmsyG4vlc");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_FB_IN_HOUSE_V2_DEBUG = new AppSignatureHash("portal_fb_inhousev2_debug", "pGLr1wNX3vElqWNF4QYKaubskS4", "jtZAS_lKBE3WXzNRjrPIcqOuRU4Dh_MGihoNLNw3CKA");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_FB_SAMPLES_DEBUG = new AppSignatureHash("portal_fb_samples_debug", "aIIMIXiHjw__OMc2ophMdya2vHQ", "qadsRSPy8q2oOtZucRAyNlbBCyrbDQYWnD6ZESwR0vs");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_PLATFORM_ALOHA = new AppSignatureHash("portal_platform_aloha", "n0_Ssi2N8d9-5-hJV0WQ1rt5nlk", "0qONa7PbO-l9JLKlJjkkf_VDfDI7jJKEQCKJX1nzxus");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_PLATFORM_ALOHA_DEBUG = new AppSignatureHash("portal_platform_aloha_debug", "Aj-bcwbSjGsTOUNEkMaqXHyTQeY", "5MCO54QyiJ31mua72pgMV7lET8XxQmxVGsxMmN3dAkA");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_PLATFORM_CATALINA = new AppSignatureHash("portal_platform_catalina", "gZ2hI80bGDGB_XVfiJOSCktxXwI", "-HdCA-5jUGjnsyAYKdXRg4w3tVAHYZTU3EzUOWRVTX0");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_PLATFORM_CATALINA_DEBUG = PORTAL_SIGNATURE_HASH_PLATFORM_ALOHA_DEBUG;
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_PLATFORM_OMNI = new AppSignatureHash("portal_platform_omni", "HyZQnz_A_xFUJNx9-uRxfLglhH0", "W1BzWTPVNZBtrA45RvTcbkVphwqyUdZwQEL7X-RqPpM");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_PLATFORM_OMNI_DEBUG = new AppSignatureHash("portal_platform_omni_debug", "a_U1bAb7cZcQ1uOM0JdyiPdHWq8", "b2sRSFyeAdgq4NbTDsF6EuDfHreyS9x2Pp7oKe8QclI");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_WHATSAPP = new AppSignatureHash("portal_whatsapp", "FKzVxsqJpYsJuFdQqTE7IEgYC08", "7oVvh3Fck1xX0J5u42DHceCqMyIqewU2TWaJlChTsZA");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_WHATSAPP_DEBUG = new AppSignatureHash("portal_whatsapp_debug", "AMOxgVfUuJgIn3TkLNUWQ2QWkeY", "y-7IuVPL3L_a06a23Nl8rcsi51i83grZLyXD-OMtQO0");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_WORK = new AppSignatureHash("portal_work", "jNzSyAsLWIABbRrSOiutGvlokAA", "HC4rwCH0AxqzQcvYDrHg5ikHBl2GnUuRnJLwuJJyt8o");
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_WORK_DEBUG = new AppSignatureHash("portal_work_debug", "mVFabvwIZ6M8VQPvGgdcTrwZhXI", "ovbQAw7LTrM4PSNadgRBwp4vfR5ma3mkb1x-g0cLqhk");
    public static final AppSignatureHash STELLA_SIGNATURE_HASH = new AppSignatureHash("stella_release", "_H-OYUFZvtFrvtzR6NdYRD0eaTA", "gbBIh-jriiCfLl5AQq3PaWv3Uemavb2hMrZhZ_3KlDE");
    public static final AppSignatureHash STUDY_SIGNATURE_HASH_RELEASE = new AppSignatureHash("study_release", "r9BCZbqOeyVExd0xAOMmz1UyHz4", "GVQYWvhotR_1RBv0Am3VA2bLMifS4uOCNDeaKSVc7CU");
    public static final AppSignatureHash VIEWPOINTS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("viewpoints_release", "XuWTzVfchfTAN8XtLJfChtm7034", "XdkQEeiVeIyDkvBEAHtGJKKHfdsrOFf9te68UpyJVhA");
    public static final Set<AppSignatureHash> WHATSAPP_DEBUG = Collections.unmodifiableSet(new HashSet(Collections.singletonList(WHATSAPP_SIGNATURE_HASH_DEBUG)));
    public static final Set<AppSignatureHash> WHATSAPP_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(WHATSAPP_SIGNATURE_HASH_RELEASE)));
    public static final AppSignatureHash WHATSAPP_SIGNATURE_HASH_DEBUG = new AppSignatureHash("whatsapp_debug", "HfqsFpVx2hvmL2FpTQgY5bCSyHo", "HEIr5yy3eXd1ynm5SueBb-3LsOp9aEYClzuRNXxJzFc");
    public static final AppSignatureHash WHATSAPP_SIGNATURE_HASH_RELEASE = new AppSignatureHash("whatsapp_release", "OKD31QX-GP7GT780Psqq8xDb15k", "OYfQQ9EK769ahxCzZxQY_lfg4ZtlPJ34JVj-tf_OXUQ");
    @SuppressLint({"NotAccessedPrivateField"})
    private static final Map<AppSignatureHash, Set<AppSignatureHash>> mDebugSignaturesMap = Collections.unmodifiableMap(new HashMap<AppSignatureHash, Set<AppSignatureHash>>() {
        /* class com.facebook.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass8 */

        {
            put(AllFamilyTrustedSignatures.FBANDROID_SIGNATURE_HASH_IN_HOUSE_V2, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.FBANDROID_SIGNATURE_HASH_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_FB_IN_HOUSE_V2_DEBUG));
            put(AllFamilyTrustedSignatures.FBANDROID_SIGNATURE_HASH_SAMPLES, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.FBANDROID_SIGNATURE_HASH_DEBUG, AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_FB_SAMPLES_DEBUG));
            put(AllFamilyTrustedSignatures.MILAN_1P_APPS_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.MILAN_1P_APPS_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.MILAN_1P_PRIV_APPS_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.MILAN_1P_PRIV_APPS_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.MILAN_1P_NON_PRIV_APPS_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.MILAN_1P_NON_PRIV_APPS_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.MILAN_2P_APPS_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.MILAN_2P_APPS_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.MILAN_COMPANION_APP_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.MILAN_COMPANION_APP_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_PRIVAPPS, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_PRIVAPPS_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_NONPRIVAPPS, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_1P_NONPRIVAPPS_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_2P_APPS, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_2P_APPS_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_FRAMEWORKS, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_FRAMEWORKS_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_WHATSAPP, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_WHATSAPP_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_DF, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_DF_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_WORK, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_WORK_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_PLATFORM_ALOHA, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_PLATFORM_ALOHA_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_PLATFORM_CATALINA, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_PLATFORM_CATALINA_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_PLATFORM_OMNI, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_PLATFORM_OMNI_DEBUG));
            put(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_APPMANAGER, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.PORTAL_SIGNATURE_HASH_APPMANAGER_DEBUG));
            put(AllFamilyTrustedSignatures.OXYGEN_PRELOADS_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.OXYGEN_PRELOADS_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.WHATSAPP_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.WHATSAPP_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.OCULUS_QUEST_PLATFORM_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.OCULUS_PLATFORM_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.OCULUS_QUEST2_PLATFORM_SIGNATURE_HASH_RELEASE, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.OCULUS_PLATFORM_SIGNATURE_HASH_DEBUG));
            put(AllFamilyTrustedSignatures.NOVI_WALLET_SIGNATURE_HASH, AllFamilyTrustedSignatures.createUnmodifiableSet(AllFamilyTrustedSignatures.NOVI_WALLET_SIGNATURE_HASH_DEBUG));
        }
    });

    protected static final <T> Set<T> createUnmodifiableSet(T... signatureHashes) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(signatureHashes)));
    }
}
