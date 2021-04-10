package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import org.chromium.chrome.browser.tasks.tab_management.TabGridIphDialogView;

/* renamed from: s71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4693s71 extends D6 {
    public final /* synthetic */ TabGridIphDialogView b;

    public C4693s71(TabGridIphDialogView tabGridIphDialogView) {
        this.b = tabGridIphDialogView;
    }

    @Override // defpackage.D6
    public void a(Drawable drawable) {
        Handler handler = new Handler();
        Animatable animatable = this.b.N;
        animatable.getClass();
        handler.postDelayed(new RunnableC4522r71(animatable), 1500);
    }
}
