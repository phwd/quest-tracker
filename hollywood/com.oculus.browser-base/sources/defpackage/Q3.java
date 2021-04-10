package defpackage;

import android.app.ProgressDialog;
import android.util.Pair;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: Q3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q3 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ S3 f8735a;

    public Q3(S3 s3) {
        this.f8735a = s3;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Pair pair = (Pair) obj;
        this.f8735a.u.b.clear();
        S3 s3 = this.f8735a;
        Objects.requireNonNull(s3);
        ProgressDialog progressDialog = new ProgressDialog(s3.b);
        s3.v = progressDialog;
        progressDialog.setMessage(s3.b.getText(R.string.f58660_resource_name_obfuscated_RES_2131953183));
        s3.v.show();
        S3 s32 = this.f8735a;
        String str = (String) pair.first;
        s32.r = str;
        s32.i.G = str;
        s32.j.f8807a = str;
        s32.s = (Runnable) pair.second;
        s32.g(str);
    }
}
