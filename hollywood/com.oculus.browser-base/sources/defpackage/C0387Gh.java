package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Rect;
import org.chromium.chrome.browser.share.long_screenshots.LongScreenshotsTabService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: Gh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0387Gh {

    /* renamed from: a  reason: collision with root package name */
    public Rect f8101a;
    public LongScreenshotsTabService b;
    public Tab c;
    public C3741mb0 d;

    public C0387Gh(Context context, Tab tab, Rect rect, C3741mb0 mb0) {
        this.c = tab;
        this.d = mb0;
        this.f8101a = rect;
        LongScreenshotsTabService longScreenshotsTabService = (LongScreenshotsTabService) N.M_JqOytn();
        this.b = longScreenshotsTabService;
        longScreenshotsTabService.f10757a = this;
    }

    public void a(C1149Sv0 sv0, int i) {
        if (i != 1) {
            C3741mb0 mb0 = this.d;
            if (i == 7) {
                C3912nb0.a(mb0.f10434a, 1);
            } else {
                C3912nb0.a(mb0.f10434a, 2);
            }
        } else {
            C1088Rv0 rv0 = sv0.h;
            if (rv0 == null) {
                C1088Rv0 rv02 = C1088Rv0.e;
                rv0 = C1088Rv0.e;
            }
            GURL gurl = new GURL(rv0.h);
            LongScreenshotsTabService longScreenshotsTabService = this.b;
            Rect rect = this.f8101a;
            C3741mb0 mb02 = this.d;
            mb02.getClass();
            C0265Eh eh = new C0265Eh(mb02);
            C3741mb0 mb03 = this.d;
            mb03.getClass();
            new C3228jb0(gurl, longScreenshotsTabService, "long_screenshots_dir", sv0, rect, eh, new C0326Fh(mb03));
        }
    }
}
