package defpackage;

import J.N;
import android.graphics.Bitmap;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;

/* renamed from: c70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1959c70 implements FaviconHelper$FaviconImageCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Tab f9584a;
    public final /* synthetic */ LayerTitleCache b;

    public C1959c70(LayerTitleCache layerTitleCache, Tab tab) {
        this.b = layerTitleCache;
        this.f9584a = tab;
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback
    public void onFaviconAvailable(Bitmap bitmap, String str) {
        LayerTitleCache layerTitleCache = this.b;
        Tab tab = this.f9584a;
        Objects.requireNonNull(layerTitleCache);
        if (tab.isInitialized()) {
            int id = tab.getId();
            C2130d70 d70 = (C2130d70) layerTitleCache.d.get(id);
            if (d70 != null) {
                boolean z = false;
                if (d70.c) {
                    d70.f9749a.f(bitmap);
                    d70.c = false;
                    z = true;
                }
                if (z) {
                    long j = layerTitleCache.f;
                    if (j != 0) {
                        N.MJ3tyP3k(j, layerTitleCache, id, d70.f9749a.F);
                    }
                }
            }
        }
    }
}
