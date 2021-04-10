package defpackage;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: Nw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Nw1 extends Pw1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC5827yp0 f8586a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ int d;
    public final /* synthetic */ Qw1 e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Nw1(Qw1 qw1, AbstractC5827yp0 yp0, String str, String str2, int i) {
        super(null);
        this.e = qw1;
        this.f8586a = yp0;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    @Override // defpackage.Pw1
    public void b(AbstractC5099uZ uZVar) {
        Bitmap bitmap;
        Qw1 qw1 = this.e;
        AbstractC5827yp0 yp0 = this.f8586a;
        String str = this.b;
        C4759sZ sZVar = (C4759sZ) uZVar;
        int d2 = sZVar.d();
        Objects.requireNonNull(qw1);
        String str2 = null;
        try {
            bitmap = BitmapFactory.decodeResource(ContextUtils.getApplicationContext().getPackageManager().getResourcesForApplication(str), d2);
        } catch (PackageManager.NameNotFoundException unused) {
            bitmap = null;
        }
        if (!yp0.g()) {
            yp0.k(bitmap);
        }
        if (!yp0.h()) {
            yp0.l(bitmap);
        }
        boolean f = sZVar.f();
        if (f) {
            Qw1 qw12 = this.e;
            String str3 = this.b;
            Objects.requireNonNull(qw12);
            boolean z = false;
            try {
                if (ContextUtils.getApplicationContext().getPackageManager().getApplicationInfo(str3, 0).targetSdkVersion >= 26) {
                    z = true;
                }
            } catch (PackageManager.NameNotFoundException unused2) {
            }
            if (z) {
                this.f8586a.g = "default_channel_id";
                str2 = ContextUtils.getApplicationContext().getString(R.string.f64640_resource_name_obfuscated_RES_2131953781);
            }
            String str4 = this.c;
            int i = this.d;
            sZVar.e0(str4, i, this.f8586a.d(new C0832Np0(9, str4, i)).f10306a, str2);
        }
        AbstractC3100ip1.f10165a.a("WebApk.Notification.Permission.Status", f);
    }
}
