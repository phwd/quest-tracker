package defpackage;

import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: oH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4038oH extends AbstractC2032cb {
    public C4209pH i;
    public final /* synthetic */ C4721sH j;

    public C4038oH(C4721sH sHVar, C4209pH pHVar) {
        this.j = sHVar;
        this.i = pHVar;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        ArrayList arrayList = new ArrayList();
        File a2 = this.i.a();
        if (a2 == null) {
            arrayList.add(new LF(null, 0, 0, 2));
        } else {
            boolean z = false;
            arrayList.add(m(a2, 0));
            Objects.requireNonNull(this.j);
            AbstractC3364kK0.g("MobileDownload.Location.DirectoryType", 0, 3);
            this.j.f = Environment.getExternalStorageDirectory().getAbsolutePath();
            ArrayList arrayList2 = (ArrayList) this.i.b();
            if (!arrayList2.isEmpty()) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    File file = (File) it.next();
                    if (file != null) {
                        arrayList.add(m(file, 1));
                        z = true;
                    }
                }
                if (z) {
                    Objects.requireNonNull(this.j);
                    AbstractC3364kK0.g("MobileDownload.Location.DirectoryType", 1, 3);
                }
            }
        }
        return arrayList;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        C4721sH sHVar = this.j;
        sHVar.e = (ArrayList) obj;
        sHVar.b = true;
        sHVar.c = false;
        Iterator it = sHVar.g.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onResult(this.j.e);
        }
        this.j.g.clear();
        this.j.d = null;
    }

    public final LF m(File file, int i2) {
        return new LF(file.getAbsolutePath(), file.getUsableSpace(), file.getTotalSpace(), i2);
    }
}
