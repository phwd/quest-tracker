package org.chromium.chrome.browser.thumbnail.generator;

import android.graphics.Bitmap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ThumbnailGenerator {
    public void onThumbnailRetrieved(String str, int i, Bitmap bitmap, ThumbnailGeneratorCallback thumbnailGeneratorCallback) {
        thumbnailGeneratorCallback.a(str, bitmap, i);
    }
}
