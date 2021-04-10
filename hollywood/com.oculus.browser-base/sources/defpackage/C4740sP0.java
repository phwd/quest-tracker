package defpackage;

import J.N;
import com.oculus.browser.R;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

/* renamed from: sP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4740sP0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5250vP0 f11271a;

    public C4740sP0(C5250vP0 vp0) {
        this.f11271a = vp0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5250vP0 vp0 = this.f11271a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Objects.requireNonNull(vp0);
        if (booleanValue) {
            DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(2, 1);
            N.MTm9IWhH(vp0.b.getString(R.string.f60930_resource_name_obfuscated_RES_2131953410, dateTimeInstance.format(new Date(System.currentTimeMillis()))), vp0.e);
            vp0.d.run();
        }
    }
}
