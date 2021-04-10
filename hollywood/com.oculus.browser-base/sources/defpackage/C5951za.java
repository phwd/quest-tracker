package defpackage;

import android.database.Cursor;
import android.net.Uri;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: za  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5951za extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ C0122Ca j;

    public C5951za(C0122Ca ca, String str) {
        this.j = ca;
        this.i = str;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        boolean z;
        C5259vU vUVar = this.j.K;
        Objects.requireNonNull(vUVar);
        Object obj = ThreadUtils.f10596a;
        Cursor cursor = null;
        try {
            cursor = vUVar.d.getContentResolver().query(Uri.parse(C5259vU.b), null, null, null, null);
            z = vUVar.e(cursor);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            AbstractC1220Ua0.a("GSAState", "Failed due to unexpected exception.", e);
            if (cursor != null) {
                cursor.close();
            }
            z = false;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return Boolean.valueOf(z);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Boolean bool = (Boolean) obj;
        C0122Ca.F = bool;
        this.j.L.m("Chrome.Assistant.Supported", bool.booleanValue());
        this.j.L.p("Chrome.Assistant.LastVersion", this.i);
        this.j.d();
    }
}
