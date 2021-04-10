package defpackage;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.oculus.browser.R;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.SelectFileDialog;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: RR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RR0 extends AbstractC2032cb {
    public Boolean i;
    public WindowAndroid j;
    public Ky1 k;
    public final /* synthetic */ SelectFileDialog l;

    public RR0(SelectFileDialog selectFileDialog, Boolean bool, WindowAndroid windowAndroid, Ky1 ky1) {
        this.l = selectFileDialog;
        this.i = bool;
        this.j = windowAndroid;
        this.k = ky1;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            SelectFileDialog selectFileDialog = this.l;
            String[] strArr = SelectFileDialog.b;
            Objects.requireNonNull(selectFileDialog);
            return ContentUriUtils.b(File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", AbstractC2417ep1.d(applicationContext)));
        } catch (IOException e) {
            AbstractC1220Ua0.a("SelectFileDialog", "Cannot retrieve content uri from file", e);
            return null;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Uri uri = (Uri) obj;
        SelectFileDialog selectFileDialog = this.l;
        selectFileDialog.j = uri;
        if (uri != null) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.setFlags(3);
            intent.putExtra("output", this.l.j);
            P21 f0 = P21.f0();
            try {
                intent.setClipData(ClipData.newUri(ContextUtils.getApplicationContext().getContentResolver(), "images", this.l.j));
                f0.close();
                if (this.i.booleanValue()) {
                    this.j.F0(intent, this.k, Integer.valueOf((int) R.string.f54270_resource_name_obfuscated_RES_2131952744));
                    return;
                } else {
                    this.l.l(intent);
                    return;
                }
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else if (selectFileDialog.f() || this.i.booleanValue()) {
            this.l.m();
            return;
        } else {
            this.l.l(null);
            return;
        }
        throw th;
    }
}
