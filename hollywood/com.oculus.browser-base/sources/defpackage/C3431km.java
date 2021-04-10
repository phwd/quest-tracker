package defpackage;

import android.view.accessibility.CaptioningManager;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: km  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3431km extends CaptioningManager.CaptioningChangeListener implements AbstractC5707y51 {

    /* renamed from: a  reason: collision with root package name */
    public static C3431km f10301a;
    public final C3602lm b = new C3602lm();
    public final CaptioningManager c = ((CaptioningManager) ContextUtils.getApplicationContext().getSystemService("captioning"));

    public final C3773mm a(CaptioningManager.CaptionStyle captionStyle) {
        if (captionStyle == null) {
            return new C3773mm(null, null, null, null, null, null);
        }
        Integer num = null;
        Integer valueOf = captionStyle.hasBackgroundColor() ? Integer.valueOf(captionStyle.backgroundColor) : null;
        Integer valueOf2 = captionStyle.hasEdgeColor() ? Integer.valueOf(captionStyle.edgeColor) : null;
        Integer valueOf3 = captionStyle.hasEdgeType() ? Integer.valueOf(captionStyle.edgeType) : null;
        Integer valueOf4 = captionStyle.hasForegroundColor() ? Integer.valueOf(captionStyle.foregroundColor) : null;
        if (captionStyle.hasWindowColor()) {
            num = Integer.valueOf(captionStyle.windowColor);
        }
        return new C3773mm(valueOf, valueOf2, valueOf3, valueOf4, num, captionStyle.getTypeface());
    }

    public final void b() {
        C3602lm lmVar = this.b;
        lmVar.f10371a = this.c.isEnabled();
        lmVar.d();
        this.b.e(this.c.getFontScale());
        C3602lm lmVar2 = this.b;
        this.c.getLocale();
        Objects.requireNonNull(lmVar2);
        this.b.f(a(this.c.getUserStyle()));
    }

    public void onEnabledChanged(boolean z) {
        C3602lm lmVar = this.b;
        lmVar.f10371a = z;
        lmVar.d();
    }

    public void onFontScaleChanged(float f) {
        this.b.e(f);
    }

    public void onLocaleChanged(Locale locale) {
        Objects.requireNonNull(this.b);
    }

    public void onUserStyleChanged(CaptioningManager.CaptionStyle captionStyle) {
        this.b.f(a(captionStyle));
    }
}
