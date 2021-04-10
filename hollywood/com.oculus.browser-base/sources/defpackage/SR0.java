package defpackage;

import J.N;
import android.content.Context;
import android.net.Uri;
import org.chromium.base.ContentUriUtils;
import org.chromium.ui.base.SelectFileDialog;

/* renamed from: SR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SR0 extends AbstractC2032cb {
    public String[] i;
    public final Context j;
    public final boolean k;
    public final Uri[] l;
    public final /* synthetic */ SelectFileDialog m;

    public SR0(SelectFileDialog selectFileDialog, Context context, boolean z, Uri[] uriArr) {
        this.m = selectFileDialog;
        this.j = context;
        this.k = z;
        this.l = uriArr;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Uri[] uriArr = this.l;
        this.i = new String[uriArr.length];
        String[] strArr = new String[uriArr.length];
        int i2 = 0;
        while (true) {
            try {
                Uri[] uriArr2 = this.l;
                if (i2 >= uriArr2.length) {
                    return strArr;
                }
                if ("file".equals(uriArr2[i2].getScheme())) {
                    this.i[i2] = this.l[i2].getSchemeSpecificPart();
                } else {
                    this.i[i2] = this.l[i2].toString();
                }
                strArr[i2] = ContentUriUtils.c(this.l[i2], this.j, "_display_name");
                i2++;
            } catch (SecurityException unused) {
                AbstractC1220Ua0.f("SelectFileDialog", "Unable to extract results from the content provider", new Object[0]);
                return null;
            }
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        String[] strArr = (String[]) obj;
        if (strArr == null) {
            SelectFileDialog selectFileDialog = this.m;
            String[] strArr2 = SelectFileDialog.b;
            selectFileDialog.m();
        } else if (this.k) {
            SelectFileDialog selectFileDialog2 = this.m;
            long j2 = selectFileDialog2.f;
            String[] strArr3 = this.i;
            if (selectFileDialog2.i()) {
                AbstractC3364kK0.c("Android.SelectFileDialogImgCount", strArr3.length);
            }
            N.Mx1807vz(j2, selectFileDialog2, strArr3, strArr);
        } else {
            SelectFileDialog selectFileDialog3 = this.m;
            long j3 = selectFileDialog3.f;
            String str = this.i[0];
            String str2 = strArr[0];
            if (selectFileDialog3.i()) {
                AbstractC3364kK0.c("Android.SelectFileDialogImgCount", 1);
            }
            N.MBeWYy2V(j3, selectFileDialog3, str, str2);
        }
    }
}
