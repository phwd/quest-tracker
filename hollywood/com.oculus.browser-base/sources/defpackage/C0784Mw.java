package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorView;

/* renamed from: Mw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0784Mw extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CompositorView f8511a;

    public C0784Mw(CompositorView compositorView) {
        this.f8511a = compositorView;
        compositorView.getContext().getApplicationContext().registerReceiver(this, new IntentFilter("android.intent.action.SCREEN_OFF"));
    }

    public void onReceive(Context context, Intent intent) {
        CompositorView compositorView;
        AbstractC0419Gw gw;
        if (intent.getAction().equals("android.intent.action.SCREEN_OFF") && (gw = (compositorView = this.f8511a).G) != null && compositorView.K != 0) {
            ((SurfaceHolder$Callback2C0723Lw) gw).g();
            CompositorView compositorView2 = this.f8511a;
            Objects.requireNonNull(compositorView2);
            SurfaceHolder$Callback2C0723Lw lw = new SurfaceHolder$Callback2C0723Lw(compositorView2, compositorView2);
            compositorView2.G = lw;
            lw.f(compositorView2.b());
            N.M_Nkznfe(compositorView2.K, compositorView2);
            AbstractC0419Gw gw2 = compositorView2.G;
            int visibility = compositorView2.getVisibility();
            SurfaceHolder$Callback2C0723Lw lw2 = (SurfaceHolder$Callback2C0723Lw) gw2;
            lw2.F.f8395a.setVisibility(visibility);
            lw2.G.f8395a.setVisibility(visibility);
        }
    }
}
