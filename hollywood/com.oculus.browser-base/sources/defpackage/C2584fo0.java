package defpackage;

import J.N;
import android.content.Context;
import android.view.ViewStub;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.video_tutorials.bridges.VideoTutorialServiceBridge;

/* renamed from: fo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2584fo0 {

    /* renamed from: a  reason: collision with root package name */
    public Context f9954a;
    public Tm1 b;
    public Ft1 c;
    public VideoTutorialServiceBridge d;

    public C2584fo0(ViewStub viewStub, Profile profile) {
        if (N.M09VlOh_("VideoTutorials")) {
            this.f9954a = viewStub.getContext();
            this.b = Um1.a(profile);
            this.c = new Ft1(viewStub, QZ.a(3, PZ.a(profile), CV.f7814a, 20971520), new C1560Zn0(this), new C1730ao0(this));
            VideoTutorialServiceBridge videoTutorialServiceBridge = (VideoTutorialServiceBridge) N.MBuXqyoS(profile);
            this.d = videoTutorialServiceBridge;
            C1901bo0 bo0 = new C1901bo0(this);
            long j = videoTutorialServiceBridge.f10798a;
            if (j != 0) {
                N.MscHdp7R(j, videoTutorialServiceBridge, bo0);
            }
        }
    }

    public final void a(List list) {
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list);
            VideoTutorialServiceBridge videoTutorialServiceBridge = this.d;
            C2072co0 co0 = new C2072co0(this, arrayList);
            long j = videoTutorialServiceBridge.f10798a;
            if (j != 0) {
                N.MSP6HvY8(j, videoTutorialServiceBridge, 1, co0);
            }
        }
    }
}
