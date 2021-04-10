package defpackage;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import java.io.File;
import org.chromium.base.PathUtils;
import org.chromium.ui.base.SelectFileDialog;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: QR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class QR0 extends AbstractC2032cb {
    public final String i;
    public final WindowAndroid j;
    public final /* synthetic */ SelectFileDialog k;

    public QR0(SelectFileDialog selectFileDialog, Context context, String str, WindowAndroid windowAndroid) {
        this.k = selectFileDialog;
        this.i = str;
        this.j = windowAndroid;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            if (!new File(this.i).getCanonicalPath().startsWith(new File(PathUtils.getDataDirectory()).getCanonicalPath())) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            AbstractC1220Ua0.f("SelectFileDialog", "Unable to get canonical file path", e);
        }
        return Boolean.FALSE;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            SelectFileDialog selectFileDialog = this.k;
            long j2 = selectFileDialog.f;
            String str = this.i;
            if (selectFileDialog.i()) {
                AbstractC3364kK0.c("Android.SelectFileDialogImgCount", 1);
            }
            N.MBeWYy2V(j2, selectFileDialog, str, "");
            this.j.G0(R.string.f56770_resource_name_obfuscated_RES_2131952994);
            return;
        }
        SelectFileDialog selectFileDialog2 = this.k;
        String[] strArr = SelectFileDialog.b;
        selectFileDialog2.m();
    }
}
