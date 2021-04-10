package defpackage;

import android.provider.MediaStore;
import java.io.File;
import java.io.FileNotFoundException;
import org.chromium.base.ContextUtils;

/* renamed from: Zh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1546Zh0 extends AbstractC2032cb {
    public final /* synthetic */ String i;

    public C1546Zh0(String str) {
        this.i = str;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            MediaStore.Images.Media.insertImage(ContextUtils.getApplicationContext().getContentResolver(), this.i, new File(this.i).getName(), (String) null);
        } catch (FileNotFoundException e) {
            AbstractC1220Ua0.a("MediaStoreHelper", "Cannot find image file to add to gallery.", e);
        }
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r1 = (Void) obj;
    }
}
