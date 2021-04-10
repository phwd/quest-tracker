package defpackage;

import android.content.Context;
import android.database.Cursor;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* renamed from: Qw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1029Qw0 extends AbstractC2032cb {
    public boolean i;
    public final /* synthetic */ Context j;
    public final /* synthetic */ PartnerBrowserCustomizations k;

    public C1029Qw0(PartnerBrowserCustomizations partnerBrowserCustomizations, Context context) {
        this.k = partnerBrowserCustomizations;
        this.j = context;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            if (!((this.j.getApplicationInfo().flags & 1) == 1)) {
                return null;
            }
            if (h()) {
                return null;
            }
            Objects.requireNonNull(AppHooks.get());
            C1090Rw0 rw0 = new C1090Rw0();
            if (h()) {
                return null;
            }
            p(rw0);
            if (h()) {
                return null;
            }
            n(rw0);
            if (h()) {
                return null;
            }
            o(rw0);
            return null;
        } catch (Exception e) {
            AbstractC1220Ua0.f("PartnerCustomize", "Fetching partner customizations failed", e);
            return null;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void j(Object obj) {
        Void r1 = (Void) obj;
        m();
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r1 = (Void) obj;
        m();
    }

    public final void m() {
        QX qx;
        PartnerBrowserCustomizations partnerBrowserCustomizations = this.k;
        partnerBrowserCustomizations.e = true;
        for (Runnable runnable : partnerBrowserCustomizations.f) {
            runnable.run();
        }
        this.k.f.clear();
        if (this.i && (qx = this.k.g) != null) {
            qx.h();
        }
    }

    public final void n(C1090Rw0 rw0) {
        boolean z;
        try {
            PartnerBrowserCustomizations partnerBrowserCustomizations = this.k;
            if (!rw0.a()) {
                z = false;
            } else {
                Cursor query = ContextUtils.getApplicationContext().getContentResolver().query(PartnerBrowserCustomizations.a("disablebookmarksediting"), null, null, null, null);
                z = query != null && query.moveToFirst() && query.getColumnCount() == 1 && query.getInt(0) == 1;
                if (query != null) {
                    query.close();
                }
            }
            partnerBrowserCustomizations.d = z;
        } catch (Exception e) {
            AbstractC1220Ua0.f("PartnerCustomize", "Partner disable bookmarks editing read failed : ", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006b A[Catch:{ Exception -> 0x007e }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006c A[Catch:{ Exception -> 0x007e }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0077 A[Catch:{ Exception -> 0x007e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void o(defpackage.C1090Rw0 r11) {
        /*
        // Method dump skipped, instructions count: 137
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1029Qw0.o(Rw0):void");
    }

    public final void p(C1090Rw0 rw0) {
        boolean z;
        try {
            PartnerBrowserCustomizations partnerBrowserCustomizations = this.k;
            if (!rw0.a()) {
                z = false;
            } else {
                Cursor query = ContextUtils.getApplicationContext().getContentResolver().query(PartnerBrowserCustomizations.a("disableincognitomode"), null, null, null, null);
                z = query != null && query.moveToFirst() && query.getColumnCount() == 1 && query.getInt(0) == 1;
                if (query != null) {
                    query.close();
                }
            }
            partnerBrowserCustomizations.c = z;
        } catch (Exception e) {
            AbstractC1220Ua0.f("PartnerCustomize", "Partner disable incognito mode read failed : ", e);
        }
    }
}
