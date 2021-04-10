package defpackage;

import android.graphics.RectF;
import java.util.Objects;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: oQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4059oQ implements Comparable {
    public float F;
    public float G;
    public final /* synthetic */ C4230pQ H;

    public C4059oQ(C4230pQ pQVar, float f, float f2) {
        this.H = pQVar;
        this.F = f;
        this.G = f2;
    }

    public float a() {
        return (this.F + this.G) * 0.5f;
    }

    public RectF b() {
        C4230pQ pQVar = this.H;
        Objects.requireNonNull(pQVar);
        int width = LocalizationUtils.isLayoutRtl() ? 0 : pQVar.getWidth() - pQVar.N;
        RectF rectF = new RectF((float) width, this.F, (float) (width + this.H.N), this.G);
        float f = 0.5f;
        rectF.inset(2.0f, 0.5f);
        if (LocalizationUtils.isLayoutRtl()) {
            f = -0.5f;
        }
        rectF.offset(f, 0.0f);
        return rectF;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return Float.compare(a(), ((C4059oQ) obj).a());
    }
}
