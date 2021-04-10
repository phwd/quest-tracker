package defpackage;

import android.graphics.Bitmap;
import java.lang.ref.WeakReference;

/* renamed from: Bh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0082Bh extends C4595rb0 {
    public C0082Bh(int i, AbstractC0021Ah ah) {
        super(i);
    }

    @Override // defpackage.C4595rb0
    public Object a(Object obj) {
        WeakReference weakReference = (WeakReference) C0143Ch.f7829a.get((String) obj);
        if (weakReference == null) {
            return null;
        }
        return (Bitmap) weakReference.get();
    }

    @Override // defpackage.C4595rb0
    public int e(Object obj, Object obj2) {
        String str = (String) obj;
        return ((Bitmap) obj2).getByteCount();
    }
}
