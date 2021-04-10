package org.chromium.components.offline_items_collection.bridges;

import android.graphics.Bitmap;
import org.chromium.components.offline_items_collection.OfflineItemVisuals;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OfflineItemVisualsBridge {
    public static OfflineItemVisuals createOfflineItemVisuals(Bitmap bitmap) {
        OfflineItemVisuals offlineItemVisuals = new OfflineItemVisuals();
        offlineItemVisuals.f10859a = bitmap;
        return offlineItemVisuals;
    }
}
