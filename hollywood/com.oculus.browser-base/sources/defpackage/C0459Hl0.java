package defpackage;

import android.graphics.Bitmap;
import java.util.Objects;
import org.chromium.chrome.browser.suggestions.tile.SuggestionsTileView;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: Hl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0459Hl0 implements LargeIconBridge$LargeIconCallback {
    public final C0642Kl0 F;
    public final C0815Nh1 G;

    public C0459Hl0(C0642Kl0 kl0, C0815Nh1 nh1) {
        this.F = kl0;
        this.G = nh1;
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        C0642Kl0 kl0 = this.F;
        C0815Nh1 nh1 = this.G;
        Objects.requireNonNull(kl0);
        if (nh1 != null) {
            nh1.d = i2;
            if (bitmap == null) {
                kl0.f8385J.b(nh1, i, z);
            } else {
                kl0.f8385J.a(nh1, bitmap);
            }
            for (int i3 = 0; i3 < kl0.G.getChildCount(); i3++) {
                if (((SuggestionsTileView) kl0.G.getChildAt(i3)).f10769J.b.equals(nh1.f8567a.b)) {
                    SuggestionsTileView suggestionsTileView = (SuggestionsTileView) kl0.G.getChildAt(i3);
                    Objects.requireNonNull(suggestionsTileView);
                    suggestionsTileView.I.setImageDrawable(nh1.e);
                    suggestionsTileView.a(nh1);
                }
            }
        }
    }
}
