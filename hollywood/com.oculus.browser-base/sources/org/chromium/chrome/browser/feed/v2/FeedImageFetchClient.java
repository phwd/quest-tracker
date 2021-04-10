package org.chromium.chrome.browser.feed.v2;

import org.chromium.chrome.browser.xsurface.ImageFetchClient$HttpResponseConsumer;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FeedImageFetchClient extends IZ {
    public static void onHttpResponse(ImageFetchClient$HttpResponseConsumer imageFetchClient$HttpResponseConsumer, int i, byte[] bArr) {
        imageFetchClient$HttpResponseConsumer.a(new C5077uO(i, bArr));
    }
}
