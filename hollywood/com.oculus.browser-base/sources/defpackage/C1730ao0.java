package defpackage;

import J.N;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.chrome.browser.video_tutorials.bridges.VideoTutorialServiceBridge;

/* renamed from: ao0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1730ao0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2584fo0 f9489a;

    public C1730ao0(C2584fo0 fo0) {
        this.f9489a = fo0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2584fo0 fo0 = this.f9489a;
        Tutorial tutorial = (Tutorial) obj;
        Tm1 tm1 = fo0.b;
        int i = tutorial.f10797a;
        tm1.notifyEvent(i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? null : "video_tutorial_iph_dismissed_voice_search" : "video_tutorial_iph_dismissed_search" : "video_tutorial_iph_dismissed_download" : "video_tutorial_iph_dismissed_chrome_intro" : "video_tutorial_iph_dismissed_summary");
        Mt1.a(tutorial.f10797a, 10);
        VideoTutorialServiceBridge videoTutorialServiceBridge = fo0.d;
        C2242do0 do0 = new C2242do0(fo0);
        long j = videoTutorialServiceBridge.f10798a;
        if (j != 0) {
            N.MscHdp7R(j, videoTutorialServiceBridge, do0);
        }
    }
}
