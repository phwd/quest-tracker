package defpackage;

import J.N;
import android.content.Context;
import org.chromium.url.GURL;

/* renamed from: a80  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1611a80 {
    public static boolean a(boolean z) {
        if (!N.M09VlOh_("ContextMenuGoogleLensChip") || (z && N.M6bsIDpc("ContextMenuGoogleLensChip", "disableOnIncognito", true))) {
            return false;
        }
        return true;
    }

    public static String b(Context context) {
        String a2;
        if (Boolean.TRUE.equals(Boolean.FALSE)) {
            return N.M09VlOh_("ContextMenuShopWithGoogleLens") ? "11.16" : "10.65";
        }
        if (context == null || (a2 = C5259vU.b(context).a()) == null) {
            return "";
        }
        return a2;
    }

    public static boolean c(boolean z) {
        if (!N.M09VlOh_("ContextMenuSearchWithGoogleLens") || (z && N.M6bsIDpc("ContextMenuSearchWithGoogleLens", "disableOnIncognito", true))) {
            return false;
        }
        return true;
    }

    public static boolean d(boolean z) {
        if ((N.MMltG$kc("ContextMenuShopWithGoogleLens", "lensShopVariation").equals("ShopSimilarProducts") || N.MMltG$kc("ContextMenuShopWithGoogleLens", "lensShopVariation").equals("ShopImageWithGoogleLens") || N.MMltG$kc("ContextMenuShopWithGoogleLens", "lensShopVariation").equals("SearchSimilarProducts")) && ((!z || !N.M6bsIDpc("ContextMenuShopWithGoogleLens", "disableOnIncognito", true)) && !N.M09VlOh_("ContextMenuGoogleLensChip"))) {
            return true;
        }
        return false;
    }

    public static boolean e(GURL gurl) {
        boolean z;
        if (!N.M09VlOh_("ContextMenuEnableLensShoppingAllowlist") || GURL.k(gurl)) {
            return false;
        }
        String MMltG$kc = N.MMltG$kc("ContextMenuEnableLensShoppingAllowlist", "shoppingUrlPatterns");
        if (MMltG$kc == null || MMltG$kc.isEmpty()) {
            MMltG$kc = "^https://www.google.com/shopping/.*|^https://www.google.com/.*tbm=shop.*";
        }
        if (!gurl.h().matches(MMltG$kc)) {
            String[] split = N.MMltG$kc("ContextMenuEnableLensShoppingAllowlist", "allowlistEntries").split(",");
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                String str = split[i];
                if (str.length() > 0 && gurl.h().contains(str)) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public static boolean f(boolean z) {
        if (a(z)) {
            return N.M6bsIDpc("ContextMenuGoogleLensChip", "logUkm", true);
        }
        if (d(z)) {
            return N.M6bsIDpc("ContextMenuShopWithGoogleLens", "logUkm", true);
        }
        if (c(z)) {
            return N.M6bsIDpc("ContextMenuSearchWithGoogleLens", "logUkm", true);
        }
        return false;
    }
}
