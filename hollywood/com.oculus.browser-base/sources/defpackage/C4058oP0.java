package defpackage;

import android.graphics.Bitmap;
import com.oculus.browser.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: oP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4058oP0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4400qP0 f10547a;

    public C4058oP0(C4400qP0 qp0) {
        this.f10547a = qp0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4400qP0 qp0 = this.f10547a;
        Objects.requireNonNull(qp0);
        int intValue = ((Integer) obj).intValue();
        if (1 == intValue) {
            AbstractC4570rP0.a(1);
            if (qp0.g.isInitialized()) {
                WindowAndroid i = qp0.g.i();
                String format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                String string = qp0.b.getString(R.string.f60960_resource_name_obfuscated_RES_2131953413, format);
                AbstractC1667aU0.d(string, new PT0(), new TT0(new C4229pP0(qp0, i, string)), new QT0((Bitmap) qp0.f11139a.g(AbstractC5590xP0.b)), true, ".jpg");
                qp0.d.run();
            }
        } else if (2 == intValue) {
            AbstractC4570rP0.a(2);
            qp0.c.run();
        } else if (3 == intValue) {
            AbstractC4570rP0.a(3);
            qp0.d.run();
        } else if (4 == intValue) {
            AbstractC4570rP0.a(0);
            qp0.e.onResult(qp0.d);
        }
    }
}
