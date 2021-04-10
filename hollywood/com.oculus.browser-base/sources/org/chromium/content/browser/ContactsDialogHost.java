package org.chromium.content.browser;

import J.N;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContactsDialogHost {

    /* renamed from: a  reason: collision with root package name */
    public long f10911a;
    public final WindowAndroid b;

    public ContactsDialogHost(WindowAndroid windowAndroid, long j) {
        this.f10911a = j;
        this.b = windowAndroid;
    }

    public static ContactsDialogHost create(WindowAndroid windowAndroid, long j) {
        return new ContactsDialogHost(windowAndroid, j);
    }

    public void a(int i, List list, int i2, int i3) {
        long j = this.f10911a;
        if (j != 0) {
            if (i == 0) {
                N.MwwTaBAE(j, 0, i3);
            } else if (i == 1) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    C0057Ay ay = (C0057Ay) it.next();
                    long j2 = this.f10911a;
                    List list2 = ay.f7708a;
                    String[] strArr = list2 != null ? (String[]) list2.toArray(new String[list2.size()]) : null;
                    List list3 = ay.b;
                    String[] strArr2 = list3 != null ? (String[]) list3.toArray(new String[list3.size()]) : null;
                    List list4 = ay.c;
                    String[] strArr3 = list4 != null ? (String[]) list4.toArray(new String[list4.size()]) : null;
                    List list5 = ay.d;
                    ByteBuffer[] byteBufferArr = list5 != null ? (ByteBuffer[]) list5.toArray(new ByteBuffer[list5.size()]) : null;
                    List list6 = ay.e;
                    N.MS9BZrW0(j2, strArr, strArr2, strArr3, byteBufferArr, list6 != null ? (ByteBuffer[]) list6.toArray(new ByteBuffer[list6.size()]) : null);
                }
                N.MwwTaBAE(this.f10911a, i2, i3);
            }
        }
    }

    public void destroy() {
        this.f10911a = 0;
    }

    public final void showDialog(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str) {
        if (this.b.s0().get() == null) {
            N.MOM50EIZ(this.f10911a);
        } else if (this.b.hasPermission("android.permission.READ_CONTACTS")) {
            if (!AbstractC5853yy.a(this.b, this, z, z2, z3, z4, z5, z6, str)) {
                N.MOM50EIZ(this.f10911a);
            }
        } else if (!this.b.canRequestPermission("android.permission.READ_CONTACTS")) {
            N.MOM50EIZ(this.f10911a);
        } else {
            this.b.i(new String[]{"android.permission.READ_CONTACTS"}, new C5343vy(this, z, z2, z3, z4, z5, z6, str));
        }
    }
}
