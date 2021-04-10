package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.content.browser.ContactsDialogHost;

/* renamed from: vy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5343vy implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final ContactsDialogHost f11507a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final String h;

    public C5343vy(ContactsDialogHost contactsDialogHost, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str) {
        this.f11507a = contactsDialogHost;
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = z4;
        this.f = z5;
        this.g = z6;
        this.h = str;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        ContactsDialogHost contactsDialogHost = this.f11507a;
        boolean z = this.b;
        boolean z2 = this.c;
        boolean z3 = this.d;
        boolean z4 = this.e;
        boolean z5 = this.f;
        boolean z6 = this.g;
        String str = this.h;
        Objects.requireNonNull(contactsDialogHost);
        if (strArr.length != 1 || iArr.length != 1 || !TextUtils.equals(strArr[0], "android.permission.READ_CONTACTS") || iArr[0] != 0) {
            N.MOM50EIZ(contactsDialogHost.f10911a);
        } else if (!AbstractC5853yy.a(contactsDialogHost.b, contactsDialogHost, z, z2, z3, z4, z5, z6, str)) {
            N.MOM50EIZ(contactsDialogHost.f10911a);
        }
    }
}
