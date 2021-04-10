package defpackage;

import android.graphics.Bitmap;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.url.GURL;

/* renamed from: uL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5071uL extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5241vL f11407a;
    public final GURL b;

    public C5071uL(C5241vL vLVar, GURL gurl) {
        this.f11407a = vLVar;
        this.b = gurl;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5241vL vLVar = this.f11407a;
        GURL gurl = this.b;
        Bitmap bitmap = (Bitmap) obj;
        Objects.requireNonNull(vLVar);
        Object obj2 = ThreadUtils.f10596a;
        List list = (List) vLVar.g.remove(gurl);
        if (!(list == null || bitmap == null)) {
            for (int i = 0; i < list.size(); i++) {
                C5191v31 b2 = C5191v31.b(vLVar.f8494a, bitmap);
                b2.c = true;
                b2.d = true;
                ((UH0) list.get(i)).m(AbstractC0871Og.f8641a, b2.a());
            }
        }
    }
}
