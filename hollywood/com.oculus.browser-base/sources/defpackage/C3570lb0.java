package defpackage;

import J.N;
import android.app.Activity;
import android.graphics.Rect;
import java.util.LinkedList;
import org.chromium.chrome.browser.share.long_screenshots.LongScreenshotsTabService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* renamed from: lb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3570lb0 extends C2691gP0 {
    public C3570lb0(Activity activity, Tab tab, View$OnLayoutChangeListenerC5940zU0 zu0, AbstractC4448qj qjVar, HZ hz) {
        super(activity, tab, zu0, qjVar, hz);
        N.MJ3oAy5s();
    }

    @Override // defpackage.C2691gP0
    public void a() {
        Activity activity = this.b;
        Tab tab = this.c;
        LinkedList linkedList = new LinkedList();
        int i = YF.b(activity).d.y;
        TL0 tl0 = ((WebContentsImpl) tab.l()).M;
        C3912nb0 nb0 = new C3912nb0(activity, tab, tl0.e() / ((int) Math.floor((double) tl0.g)), i);
        if (nb0.d == null) {
            nb0.d = new C0387Gh(activity, tab, nb0.b, new C3741mb0(nb0));
        }
        C0387Gh gh = nb0.d;
        LongScreenshotsTabService longScreenshotsTabService = gh.b;
        Tab tab2 = gh.c;
        Rect rect = gh.f8101a;
        if (longScreenshotsTabService.b == 0) {
            longScreenshotsTabService.processCaptureTabStatus(9);
        }
        if (tab2.l() == null) {
            longScreenshotsTabService.processCaptureTabStatus(5);
        }
        N.ML_nbaMX(longScreenshotsTabService.b, tab2.getId(), tab2.l(), rect.left, rect.top, rect.right, rect.bottom);
        linkedList.add(nb0);
        nb0.g = new C3399kb0(this, nb0);
    }
}
