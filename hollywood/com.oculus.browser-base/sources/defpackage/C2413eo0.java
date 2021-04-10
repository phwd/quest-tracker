package defpackage;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.chromium.chrome.browser.video_tutorials.Tutorial;

/* renamed from: eo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2413eo0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2584fo0 f9881a;
    public final List b;

    public C2413eo0(C2584fo0 fo0, List list) {
        this.f9881a = fo0;
        this.b = list;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        String str;
        C2584fo0 fo0 = this.f9881a;
        List<Tutorial> list = this.b;
        Objects.requireNonNull(fo0);
        if (((Boolean) obj).booleanValue()) {
            for (Tutorial tutorial : list) {
                int i = tutorial.f10797a;
                boolean z = true;
                String str2 = i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? null : "IPH_VideoTutorial_NTP_VoiceSearch" : "IPH_VideoTutorial_NTP_Search" : "IPH_VideoTutorial_NTP_Download" : "IPH_VideoTutorial_NTP_ChromeIntro" : "IPH_VideoTutorial_NTP_Summary";
                if (str2 != null && fo0.b.shouldTriggerHelpUI(str2)) {
                    Mt1.a(tutorial.f10797a, 8);
                    Ft1 ft1 = fo0.c;
                    ft1.b.j(Gt1.f8119a, true);
                    ft1.b.m(Gt1.b, tutorial.b);
                    UH0 uh0 = ft1.b;
                    TH0 th0 = Gt1.c;
                    int i2 = tutorial.f;
                    int i3 = i2 / 3600;
                    int i4 = (i2 / 60) % 60;
                    int i5 = i2 % 60;
                    if (i3 > 0) {
                        str = String.format(Locale.US, "%d:%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
                    } else {
                        str = String.format(Locale.US, "%d:%02d", Integer.valueOf(i4), Integer.valueOf(i5));
                    }
                    uh0.m(th0, str);
                    UH0 uh02 = ft1.b;
                    QH0 qh0 = Gt1.d;
                    if (tutorial.f == 0) {
                        z = false;
                    }
                    uh02.j(qh0, z);
                    ft1.b.m(Gt1.f, new RunnableC6010zt1(ft1, tutorial));
                    ft1.b.m(Gt1.g, new At1(ft1, tutorial));
                    ft1.b.m(Gt1.e, new Bt1(ft1, tutorial));
                    fo0.b.dismissed(str2);
                    return;
                }
            }
        }
    }
}
