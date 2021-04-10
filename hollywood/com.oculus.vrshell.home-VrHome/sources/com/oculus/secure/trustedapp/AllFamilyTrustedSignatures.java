package com.oculus.secure.trustedapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AllFamilyTrustedSignatures {
    public static final Set<AppSignatureHash> ALL_FAMILY = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.oculus.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass5 */

        {
            addAll(AllFamilyTrustedSignatures.ALL_FAMILY_DEBUG);
            addAll(AllFamilyTrustedSignatures.ALL_FAMILY_PROD);
        }
    });
    public static final Set<AppSignatureHash> ALL_FAMILY_DEBUG = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.oculus.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass3 */

        {
            addAll(AllFamilyTrustedSignatures.FB_DEBUG);
            addAll(AllFamilyTrustedSignatures.OXYGEN_DEBUG);
            addAll(AllFamilyTrustedSignatures.WHATSAPP_DEBUG);
        }
    });
    public static final Set<AppSignatureHash> ALL_FAMILY_PROD = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.oculus.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass4 */

        {
            addAll(AllFamilyTrustedSignatures.FB_PROD);
            addAll(AllFamilyTrustedSignatures.FB_FAMILY_PROD);
            addAll(AllFamilyTrustedSignatures.INSTAGRAM_PROD);
            addAll(AllFamilyTrustedSignatures.OCULUS_PROD);
            addAll(AllFamilyTrustedSignatures.OXYGEN_PROD);
            addAll(AllFamilyTrustedSignatures.WHATSAPP_PROD);
        }
    });
    public static final Set<AppSignatureHash> ALL_FB = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.oculus.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass1 */

        {
            addAll(AllFamilyTrustedSignatures.FB_DEBUG);
            addAll(AllFamilyTrustedSignatures.FB_INHOUSE);
            addAll(AllFamilyTrustedSignatures.FB_PROD);
            addAll(AllFamilyTrustedSignatures.FB_FAMILY_PROD);
        }
    });
    public static final Set<AppSignatureHash> ALL_INHOUSE = Collections.unmodifiableSet(new HashSet<AppSignatureHash>() {
        /* class com.oculus.secure.trustedapp.AllFamilyTrustedSignatures.AnonymousClass2 */

        {
            addAll(AllFamilyTrustedSignatures.FB_INHOUSE);
        }
    });
    public static final AppSignatureHash FBANDROID_FAMILY_SIGNATURE_HASH_RELEASE = new AppSignatureHash("fbandroid_family_release", "JDi84d23vQJtX_ifWYs7Xlu4JLM", "gHUKrFoWceNwTt.LAmskjOi6tbjZz0g1PCk6sS5ZNZo");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_DEBUG = new AppSignatureHash("fbandroid_debug", "Xo8WBi6jzSxKDVR4drqm84yr9iU", "-sYXRdwJA3hvue3mKpYrOZ9zSPC7b4mbgzJmdZEDO5w");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_IN_HOUSE_V1 = new AppSignatureHash("fbandroid_in_house_v1", "pLdFLi7Y9fGRBYynu_0msNMhS_w", "Ep3FNIDivxtVz27sIF_g9Z0rn4Hn7k-2fOy77iWgsIA");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_IN_HOUSE_V2 = new AppSignatureHash("fbandroid_in_house_v2", "RkHFCTArxterQ7h9g2sQjVr4Ej0", "wDby32gn_uCqMVAmAc62_hOfNu_VSqMa5uyB5sNI4dk");
    public static final AppSignatureHash FBANDROID_SIGNATURE_HASH_RELEASE = new AppSignatureHash("fbandroid_release", "ijxLJi1yGs1JpL-X1SExmchvork", "4_nh4M-Z0OVqBVumXiQbM5n3zqUkMmsM3W7BMn7Q_cE");
    public static final Set<AppSignatureHash> FB_DEBUG = Collections.unmodifiableSet(new HashSet(Collections.singletonList(FBANDROID_SIGNATURE_HASH_DEBUG)));
    public static final Set<AppSignatureHash> FB_FAMILY_PROD = Collections.unmodifiableSet(new HashSet(Arrays.asList(FBANDROID_FAMILY_SIGNATURE_HASH_RELEASE, GAMES_SIGNATURE_HASH_RELEASE, KOTOTORO_SIGNATURE_HASH_RELEASE)));
    public static final Set<AppSignatureHash> FB_INHOUSE = Collections.unmodifiableSet(new HashSet(Arrays.asList(FBANDROID_SIGNATURE_HASH_IN_HOUSE_V1, FBANDROID_SIGNATURE_HASH_IN_HOUSE_V2)));
    public static final Set<AppSignatureHash> FB_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(FBANDROID_SIGNATURE_HASH_RELEASE)));
    public static final AppSignatureHash GAMES_SIGNATURE_HASH_RELEASE = new AppSignatureHash("games_release", "nFGQkG3LpFTag1g8R3ppoA0_NjA", "cUlH0jQYllHE5u0OaOFfOj_2ZPBtYk1dZtKmV4QmWOY");
    public static final Set<AppSignatureHash> INSTAGRAM_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(INSTAGRAM_SIGNATURE_HASH_RELEASE)));
    public static final AppSignatureHash INSTAGRAM_SIGNATURE_HASH_RELEASE = new AppSignatureHash("instagram_release", "xW-31ZG6ZwTfBH_Zj1NTcv6gAhE", "Xz5Q9DVYPJrmJjAqcfc0AEQIen4sYK2s_CVCBamT4wU");
    public static final AppSignatureHash KOTOTORO_SIGNATURE_HASH_RELEASE = new AppSignatureHash("kototoro_release", "GoRS3a2OGuGeDTeVe6y0xFgcNbo", "wkocCWOvIvFGAvqCNK7NFD1RWuuKWGrar7D70A1eTlQ");
    public static final AppSignatureHash OCULUS_APPS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oculus_apps_release", "MxZgtt071YLz39PLrkVGckZooCE", "3C-DTjwZQjeLFPB2yCzRq1nqZWa_9HMLIEfpjvyfna8");
    public static final AppSignatureHash OCULUS_CORE_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oculus_core_release", "Sr9mhPKOEwo6NysnYn803dZ3UiY", "ZSEpCPBvG4p97Oj7FYJ_pqcOLvWpiwfdZ4BcNkPF08M");
    public static final Set<AppSignatureHash> OCULUS_PROD = Collections.unmodifiableSet(new HashSet(Arrays.asList(OCULUS_CORE_SIGNATURE_HASH_RELEASE, OCULUS_APPS_SIGNATURE_HASH_RELEASE)));
    public static final Set<AppSignatureHash> ONAVO_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(ONAVO_SIGNATURE_HASH_RELEASE)));
    public static final AppSignatureHash ONAVO_SIGNATURE_HASH_RELEASE = new AppSignatureHash("onavo_release", "J3-CNxaE5CjNOqEst7k0V7rDh8M", "pokdenfIDGuRjq7cDhhxQQNaSWZxpBmMsoCP9_YX73o");
    public static final Set<AppSignatureHash> OXYGEN_DEBUG = Collections.unmodifiableSet(new HashSet(Collections.singletonList(OXYGEN_PRELOADS_SIGNATURE_HASH_DEBUG)));
    public static final AppSignatureHash OXYGEN_PRELOADS_SIGNATURE_HASH_DEBUG = new AppSignatureHash("oxygen_debug", "7XE60X540nq3JXIiFpcVSgM8diY", "Jm4bl26QMphvIVgzVUeQb6f37Ys3IKRmCw0LBgLJBzs");
    public static final AppSignatureHash OXYGEN_PRELOADS_SIGNATURE_HASH_RELEASE = new AppSignatureHash("oxygen_release", "e6fv6XFRr-tXEDJmsSANhagF19Y", "uSEJtZCVlVzKr17Lzw8VPslkCkZYwQFmetlrfkmaQJI");
    public static final Set<AppSignatureHash> OXYGEN_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(OXYGEN_PRELOADS_SIGNATURE_HASH_RELEASE)));
    public static final Set<AppSignatureHash> PORTAL_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(PORTAL_SIGNATURE_HASH_COMPANION_APPS)));
    public static final AppSignatureHash PORTAL_SIGNATURE_HASH_COMPANION_APPS = new AppSignatureHash("portal_companionapps", "X64eihZAou6lkJdulh7SBnnTnDU", "2eC54WVokOBCMfraLI5w5AkPzV4OhG2rUnfhWHKBM0M");
    public static final Set<AppSignatureHash> WHATSAPP_DEBUG = Collections.unmodifiableSet(new HashSet(Collections.singletonList(WHATSAPP_SIGNATURE_HASH_DEBUG)));
    public static final Set<AppSignatureHash> WHATSAPP_PROD = Collections.unmodifiableSet(new HashSet(Collections.singletonList(WHATSAPP_SIGNATURE_HASH_RELEASE)));
    public static final AppSignatureHash WHATSAPP_SIGNATURE_HASH_DEBUG = new AppSignatureHash("whatsapp_debug", "HfqsFpVx2hvmL2FpTQgY5bCSyHo", "HEIr5yy3eXd1ynm5SueBb+3LsOp9aEYClzuRNXxJzFc");
    public static final AppSignatureHash WHATSAPP_SIGNATURE_HASH_RELEASE = new AppSignatureHash("whatsapp_release", "OKD31QX-GP7GT780Psqq8xDb15k", "OYfQQ9EK769ahxCzZxQY_lfg4ZtlPJ34JVj-tf_OXUQ");
}
