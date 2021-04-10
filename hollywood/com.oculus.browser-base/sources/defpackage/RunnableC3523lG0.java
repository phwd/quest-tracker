package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.video_tutorials.bridges.VideoTutorialServiceBridge;

/* renamed from: lG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3523lG0 implements Runnable {
    public void run() {
        if (!N.M09VlOh_("VideoTutorials")) {
            NU0.f8549a.l("Chrome.VideoTutorials.ShareUrls");
            return;
        }
        VideoTutorialServiceBridge videoTutorialServiceBridge = (VideoTutorialServiceBridge) N.MBuXqyoS(Profile.b());
        Ot1 ot1 = new Ot1();
        long j = videoTutorialServiceBridge.f10798a;
        if (j != 0) {
            N.MscHdp7R(j, videoTutorialServiceBridge, ot1);
        }
    }
}
