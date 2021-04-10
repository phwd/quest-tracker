package org.chromium.chrome.browser.read_later;

import J.N;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.components.bookmarks.BookmarkId;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ReadingListBridge {
    public static String getNotificationText(int i) {
        return ContextUtils.getApplicationContext().getResources().getQuantityString(R.plurals.f42900_resource_name_obfuscated_RES_2131820582, i, Integer.valueOf(i));
    }

    public static String getNotificationTitle() {
        return ContextUtils.getApplicationContext().getResources().getString(R.string.f59950_resource_name_obfuscated_RES_2131953312);
    }

    public static void openReadingListPage() {
        if (N.M09VlOh_("ReadLater")) {
            AbstractC1243Ui.f(null, new BookmarkId(0, 2));
        }
    }
}
