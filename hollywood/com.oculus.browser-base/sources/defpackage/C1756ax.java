package defpackage;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: ax  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1756ax extends AbstractC2032cb {
    public ContentResolver i;
    public Set j;
    public HashMap k = new HashMap();
    public List l;
    public AbstractC1577Zw m;

    public C1756ax(ContentResolver contentResolver, FC0 fc0, List list, AbstractC1577Zw zw) {
        this.i = contentResolver;
        this.j = fc0.b;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            HashMap hashMap = this.k;
            String str = ((C3638ly) it.next()).F;
            hashMap.put(str, fc0.a(str));
        }
        this.l = list;
        this.m = zw;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        for (C3638ly lyVar : this.l) {
            if (!this.j.contains(lyVar.F)) {
                Bitmap bitmap = (Bitmap) this.k.get(lyVar.F);
                if (bitmap == null) {
                    boolean z = lyVar.L;
                    Drawable drawable = z ? lyVar.M : null;
                    if (drawable != null && (drawable instanceof BitmapDrawable)) {
                        bitmap = ((BitmapDrawable) drawable).getBitmap();
                    } else if (!z) {
                        bitmap = new C5589xP(lyVar.F, this.i, null).c();
                    }
                }
                if (bitmap != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    C4833sy syVar = new C4833sy();
                    syVar.e = byteArrayOutputStream.toByteArray();
                    syVar.d = "image/png";
                    lyVar.K.add(syVar);
                }
            }
        }
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r2 = (Void) obj;
        if (!h()) {
            ((IC0) this.m).f(this.l);
        }
    }
}
