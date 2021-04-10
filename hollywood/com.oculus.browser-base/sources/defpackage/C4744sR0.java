package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.ntp.snippets.SectionHeaderView;

/* renamed from: sR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4744sR0 extends ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 {
    public final /* synthetic */ SectionHeaderView M;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4744sR0(SectionHeaderView sectionHeaderView, View view) {
        super(view);
        this.M = sectionHeaderView;
    }

    @Override // defpackage.ViewTreeObserver$OnGlobalLayoutListenerC2606fv1
    public boolean onPreDraw() {
        super.onPreDraw();
        if (this.F.bottom >= (this.M.H.getHeight() / 2) + this.M.getResources().getDimensionPixelSize(R.dimen.f26330_resource_name_obfuscated_RES_2131166252)) {
            return true;
        }
        a();
        return true;
    }
}
