package defpackage;

import android.content.Context;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.chromium.base.ContextUtils;

/* renamed from: ir  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3104ir {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10167a = {R.id.contextmenu_open_in_new_chrome_tab, R.id.contextmenu_open_in_chrome_incognito_tab, R.id.contextmenu_open_in_browser_id, R.id.contextmenu_open_in_new_tab, R.id.contextmenu_open_in_incognito_tab, R.id.contextmenu_open_in_other_window, R.id.contextmenu_open_in_ephemeral_tab, R.id.contextmenu_copy_link_address, R.id.contextmenu_copy_link_text, R.id.contextmenu_save_link_as, R.id.contextmenu_share_link, R.id.contextmenu_direct_share_link, R.id.contextmenu_read_later, R.id.contextmenu_load_original_image, R.id.contextmenu_save_image, R.id.contextmenu_open_image, R.id.contextmenu_open_image_in_new_tab, R.id.contextmenu_open_image_in_ephemeral_tab, R.id.contextmenu_copy_image, R.id.contextmenu_search_by_image, R.id.contextmenu_search_with_google_lens, R.id.contextmenu_shop_similar_products, R.id.contextmenu_shop_image_with_google_lens, R.id.contextmenu_search_similar_products, R.id.contextmenu_share_image, R.id.contextmenu_direct_share_image, R.id.contextmenu_call, R.id.contextmenu_send_message, R.id.contextmenu_add_to_contacts, R.id.contextmenu_copy, R.id.contextmenu_save_video, R.id.contextmenu_open_in_chrome};
    public static final int[] b = {R.string.f49750_resource_name_obfuscated_RES_2131952292, R.string.f49720_resource_name_obfuscated_RES_2131952289, 0, R.string.f49760_resource_name_obfuscated_RES_2131952293, R.string.f49740_resource_name_obfuscated_RES_2131952291, R.string.f49780_resource_name_obfuscated_RES_2131952295, R.string.f49730_resource_name_obfuscated_RES_2131952290, R.string.f49640_resource_name_obfuscated_RES_2131952281, R.string.f49650_resource_name_obfuscated_RES_2131952282, R.string.f49820_resource_name_obfuscated_RES_2131952299, R.string.f49890_resource_name_obfuscated_RES_2131952306, 0, R.string.f49800_resource_name_obfuscated_RES_2131952297, R.string.f49680_resource_name_obfuscated_RES_2131952285, R.string.f49810_resource_name_obfuscated_RES_2131952298, R.string.f49690_resource_name_obfuscated_RES_2131952286, R.string.f49710_resource_name_obfuscated_RES_2131952288, R.string.f49700_resource_name_obfuscated_RES_2131952287, R.string.f49630_resource_name_obfuscated_RES_2131952280, R.string.f49850_resource_name_obfuscated_RES_2131952302, R.string.f49860_resource_name_obfuscated_RES_2131952303, R.string.f49910_resource_name_obfuscated_RES_2131952308, R.string.f49900_resource_name_obfuscated_RES_2131952307, R.string.f49840_resource_name_obfuscated_RES_2131952301, R.string.f49880_resource_name_obfuscated_RES_2131952305, 0, R.string.f49610_resource_name_obfuscated_RES_2131952278, R.string.f49870_resource_name_obfuscated_RES_2131952304, R.string.f49600_resource_name_obfuscated_RES_2131952277, R.string.f49620_resource_name_obfuscated_RES_2131952279, R.string.f49830_resource_name_obfuscated_RES_2131952300, R.string.f54700_resource_name_obfuscated_RES_2131952787};

    public static CharSequence a(Context context, int i, String str, boolean z) {
        String string = context.getString(b(i));
        if (!z || (str != null && NU0.f8549a.d(str, false))) {
            return FY0.b(string, new EY0("<new>", "</new>", new Object[0]));
        }
        return FY0.a(string, new EY0("<new>", "</new>", new SuperscriptSpan(), new RelativeSizeSpan(0.75f), new ForegroundColorSpan(context.getResources().getColor(R.color.f11460_resource_name_obfuscated_RES_2131099836))));
    }

    public static int b(int i) {
        if (!AbstractC4772sd1.g() || i != 3) {
            return b[i];
        }
        return R.string.f49770_resource_name_obfuscated_RES_2131952294;
    }

    public static CharSequence c(Context context, int i, boolean z) {
        if (i == 2) {
            if (HD.b == null) {
                HD.b();
            }
            try {
                return (String) ((ArrayList) HD.b.g()).get(1);
            } catch (InterruptedException | ExecutionException unused) {
                return ContextUtils.getApplicationContext().getString(R.string.f54730_resource_name_obfuscated_RES_2131952790);
            }
        } else if (i == 6) {
            return a(context, i, "Chrome.Contextmenu.OpenInEphemeralTabClicked", z);
        } else {
            if (i == 12) {
                return a(context, i, null, z);
            }
            if (i == 17) {
                return a(context, i, "Chrome.Contextmenu.OpenImageInEphemeralTabClicked", z);
            }
            switch (i) {
                case Version.VERSION_19:
                    return context.getString(b(i), AbstractC0444Hf1.a().a().d());
                case Version.VERSION_20:
                    return a(context, i, "Chrome.ContextMenu.SearchWithGoogleLensClicked", z);
                case Version.VERSION_21:
                    return a(context, i, "Chrome.ContextMenu.ShopSimilarProductsClicked", z);
                case Version.VERSION_22:
                    return a(context, i, "Chrome.ContextMenu.ShopImageWithGoogleLensClicked", z);
                case Version.VERSION_23:
                    return a(context, i, "Chrome.ContextMenu.SearchSimilarProductsClicked", z);
                default:
                    return context.getString(b(i));
            }
        }
    }
}
