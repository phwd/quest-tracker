package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import org.chromium.base.Callback;
import org.chromium.base.PathUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* renamed from: h61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2811h61 extends AbstractC2032cb {
    public final /* synthetic */ int i;
    public final /* synthetic */ Callback j;
    public final /* synthetic */ TabContentManager k;

    public C2811h61(TabContentManager tabContentManager, int i2, Callback callback) {
        this.k = tabContentManager;
        this.i = i2;
        this.j = callback;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        int i2 = this.i;
        String thumbnailCacheDirectory = PathUtils.getThumbnailCacheDirectory();
        File file = new File(thumbnailCacheDirectory, i2 + ".jpeg");
        if (!file.isFile()) {
            return null;
        }
        return BitmapFactory.decodeFile(file.getPath());
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        double d;
        Bitmap bitmap = (Bitmap) obj;
        TraceEvent.g0("GetTabThumbnailFromDisk", (long) this.i);
        TabContentManager tabContentManager = this.k;
        int i2 = tabContentManager.n - 1;
        tabContentManager.n = i2;
        if (i2 == 0 && !tabContentManager.l) {
            tabContentManager.l = true;
            tabContentManager.p = tabContentManager.o;
            tabContentManager.i();
        }
        if (bitmap != null) {
            if (TabContentManager.f10636a.c()) {
                if (bitmap.getHeight() == 0) {
                    d = 0.0d;
                } else {
                    d = (((double) bitmap.getWidth()) * 1.0d) / ((double) bitmap.getHeight());
                }
                if (!this.k.b.contains(Integer.valueOf(this.i)) && Math.abs(d - ((double) this.k.q)) >= 0.01d) {
                    TabContentManager.a(3);
                    TabContentManager tabContentManager2 = this.k;
                    if (tabContentManager2.g == 0) {
                        this.j.onResult(bitmap);
                        return;
                    } else if (tabContentManager2.i) {
                        tabContentManager2.b.add(Integer.valueOf(this.i));
                        TabContentManager tabContentManager3 = this.k;
                        N.MGNfqDdn(tabContentManager3.g, tabContentManager3, this.i, this.j);
                        return;
                    } else {
                        return;
                    }
                }
            }
            TabContentManager.a(0);
            this.j.onResult(bitmap);
            return;
        }
        TabContentManager tabContentManager4 = this.k;
        long j2 = tabContentManager4.g;
        if (j2 != 0 && tabContentManager4.i) {
            N.MGNfqDdn(j2, tabContentManager4, this.i, new C2640g61(this.j));
        }
    }
}
