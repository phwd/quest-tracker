package defpackage;

import android.content.ContentResolver;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: lr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3617lr extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5492wr f10378a;

    public C3617lr(C5492wr wrVar) {
        this.f10378a = wrVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        String str;
        C5492wr wrVar = this.f10378a;
        Uri uri = (Uri) obj;
        if (!((GT0) wrVar.d.get()).a()) {
            LT0.h(wrVar.j(), null, uri);
            return;
        }
        ContentResolver contentResolver = ContextUtils.getApplicationContext().getContentResolver();
        WindowAndroid j = wrVar.j();
        String a2 = AbstractC3470kz.a(wrVar.f);
        ArrayList arrayList = new ArrayList(Collections.singletonList(uri));
        String type = contentResolver.getType(uri);
        if (!TextUtils.isEmpty("")) {
            str = HG.a("");
        } else {
            str = "";
        }
        ((GT0) wrVar.d.get()).b(new C2189dU0(j, a2, null, str, type, arrayList, null, null, null, null), new C1915bt(true, false, false, wrVar.f.g, false, false, null), 2);
    }
}
