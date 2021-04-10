package org.chromium.components.offline_items_collection.bridges;

import android.net.Uri;
import android.text.TextUtils;
import org.chromium.components.offline_items_collection.OfflineItemShareInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OfflineItemShareInfoBridge {
    public static OfflineItemShareInfo createOfflineItemShareInfo(String str) {
        OfflineItemShareInfo offlineItemShareInfo = new OfflineItemShareInfo();
        if (!TextUtils.isEmpty(str)) {
            Uri.parse(str);
        }
        return offlineItemShareInfo;
    }
}
