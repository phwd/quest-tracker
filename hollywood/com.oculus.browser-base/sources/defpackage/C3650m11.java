package defpackage;

import J.N;
import android.os.Handler;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: m11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3650m11 extends AbstractC0601Jv0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2454f11 f10393a;

    public C3650m11(C2454f11 f11) {
        this.f10393a = f11;
    }

    @Override // defpackage.AbstractC0601Jv0
    public void b(WebContents webContents, long j, long j2, long j3) {
        C2454f11 f11 = this.f10393a;
        Tab tab = f11.f9892a;
        if (tab != null && tab.l() == webContents) {
            f11.i = true;
            AbstractC3100ip1.f10165a.a("Browser.PaintPreview.TabbedPlayer.FirstPaintBeforeTabLoad", f11.b.c);
            if (f11.h == 1) {
                new Handler().postDelayed(new RunnableC1942c11(f11), (long) N.M37SqSAy("PaintPreviewShowOnStartup", "initial_remove_delay_ms", 0));
            }
        }
    }
}
