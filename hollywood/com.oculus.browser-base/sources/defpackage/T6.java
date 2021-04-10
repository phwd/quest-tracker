package defpackage;

import android.graphics.Bitmap;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: T6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T6 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final U6 f8938a;
    public final String b;

    public T6(U6 u6, String str) {
        this.f8938a = u6;
        this.b = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        U6 u6 = this.f8938a;
        String str = this.b;
        Bitmap bitmap = (Bitmap) obj;
        Objects.requireNonNull(u6);
        Object obj2 = ThreadUtils.f10596a;
        List list = (List) u6.g.remove(str);
        if (!(list == null || bitmap == null)) {
            for (int i = 0; i < list.size(); i++) {
                C5191v31 b2 = C5191v31.b(u6.f8494a, bitmap);
                b2.d = true;
                ((UH0) list.get(i)).m(AbstractC0871Og.f8641a, b2.a());
            }
        }
    }
}
