package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: pP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4229pP0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4400qP0 f11063a;
    public final WindowAndroid b;
    public final String c;

    public C4229pP0(C4400qP0 qp0, WindowAndroid windowAndroid, String str) {
        this.f11063a = qp0;
        this.b = windowAndroid;
        this.c = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4400qP0 qp0 = this.f11063a;
        WindowAndroid windowAndroid = this.b;
        String str = this.c;
        Uri uri = (Uri) obj;
        Objects.requireNonNull(qp0);
        ArrayList arrayList = new ArrayList(Collections.singletonList(uri));
        Objects.requireNonNull(windowAndroid);
        String type = ContextUtils.getApplicationContext().getContentResolver().getType(uri);
        String str2 = "";
        if (!TextUtils.isEmpty(str2)) {
            str2 = HG.a(str2);
        }
        C2189dU0 du0 = new C2189dU0(windowAndroid, str, null, str2, type, arrayList, null, null, null, null);
        View$OnLayoutChangeListenerC5940zU0 zu0 = qp0.f;
        C1915bt btVar = new C1915bt(false, false, false, null, false, false, null);
        long currentTimeMillis = System.currentTimeMillis();
        zu0.N = true;
        zu0.h(du0, btVar, currentTimeMillis);
    }
}
