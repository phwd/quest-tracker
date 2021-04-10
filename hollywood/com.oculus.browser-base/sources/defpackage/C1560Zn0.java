package defpackage;

import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* renamed from: Zn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1560Zn0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2584fo0 f9372a;

    public C1560Zn0(C2584fo0 fo0) {
        this.f9372a = fo0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2584fo0 fo0 = this.f9372a;
        Tutorial tutorial = (Tutorial) obj;
        Tm1 tm1 = fo0.b;
        int i = tutorial.f10797a;
        tm1.notifyEvent(i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? null : "video_tutorial_iph_clicked_voice_search" : "video_tutorial_iph_clicked_search" : "video_tutorial_iph_clicked_download" : "video_tutorial_iph_clicked_chrome_intro" : "video_tutorial_iph_clicked_summary");
        Mt1.a(tutorial.f10797a, 9);
        int i2 = tutorial.f10797a;
        if (i2 == 1) {
            Intent intent = new Intent();
            intent.setClass(fo0.f9954a, Lt1.class);
            fo0.f9954a.startActivity(intent);
            return;
        }
        Context context = fo0.f9954a;
        int i3 = Kt1.W;
        Intent intent2 = new Intent();
        intent2.setClass(context, Kt1.class);
        intent2.setFlags(268435456);
        intent2.putExtra("extra_video_tutorial", i2);
        context.startActivity(intent2);
    }
}
