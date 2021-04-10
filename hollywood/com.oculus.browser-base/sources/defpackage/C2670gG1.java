package defpackage;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.auth.TokenData;
import java.io.IOException;

/* renamed from: gG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2670gG1 implements AbstractC4040oH1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Account f9987a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Bundle c;

    public C2670gG1(Account account, String str, Bundle bundle) {
        this.f9987a = account;
        this.b = str;
        this.c = bundle;
    }

    @Override // defpackage.AbstractC4040oH1
    public final Object a(IBinder iBinder) {
        TokenData tokenData;
        AbstractC2328eG1 c2 = AbstractBinderC4379qG1.c(iBinder);
        Account account = this.f9987a;
        String str = this.b;
        Bundle bundle = this.c;
        FG1 fg1 = (FG1) c2;
        Parcel c3 = fg1.c();
        AbstractC1984cF1.b(c3, account);
        c3.writeString(str);
        AbstractC1984cF1.b(c3, bundle);
        Parcel d = fg1.d(5, c3);
        Bundle bundle2 = (Bundle) AbstractC1984cF1.a(d, Bundle.CREATOR);
        d.recycle();
        EF1.d(bundle2);
        bundle2.setClassLoader(TokenData.class.getClassLoader());
        Bundle bundle3 = bundle2.getBundle("tokenDetails");
        EnumC3006iE1 ie1 = null;
        if (bundle3 == null) {
            tokenData = null;
        } else {
            bundle3.setClassLoader(TokenData.class.getClassLoader());
            tokenData = (TokenData) bundle3.getParcelable("TokenData");
        }
        if (tokenData != null) {
            return tokenData;
        }
        String string = bundle2.getString("Error");
        Intent intent = (Intent) bundle2.getParcelable("userRecoveryIntent");
        EnumC3006iE1[] values = EnumC3006iE1.values();
        boolean z = false;
        for (EnumC3006iE1 ie12 : values) {
            if (ie12.H0.equals(string)) {
                ie1 = ie12;
            }
        }
        if (!(EnumC3006iE1.BAD_AUTHENTICATION.equals(ie1) || EnumC3006iE1.CAPTCHA.equals(ie1) || EnumC3006iE1.NEED_PERMISSION.equals(ie1) || EnumC3006iE1.NEED_REMOTE_CONSENT.equals(ie1) || EnumC3006iE1.NEEDS_BROWSER.equals(ie1) || EnumC3006iE1.USER_CANCEL.equals(ie1) || EnumC3006iE1.DEVICE_MANAGEMENT_REQUIRED.equals(ie1) || EnumC3006iE1.DM_INTERNAL_ERROR.equals(ie1) || EnumC3006iE1.DM_SYNC_DISABLED.equals(ie1) || EnumC3006iE1.DM_ADMIN_BLOCKED.equals(ie1) || EnumC3006iE1.DM_ADMIN_PENDING_APPROVAL.equals(ie1) || EnumC3006iE1.DM_STALE_SYNC_REQUIRED.equals(ie1) || EnumC3006iE1.DM_DEACTIVATED.equals(ie1) || EnumC3006iE1.DM_REQUIRED.equals(ie1) || EnumC3006iE1.THIRD_PARTY_DEVICE_MANAGEMENT_REQUIRED.equals(ie1) || EnumC3006iE1.DM_SCREENLOCK_REQUIRED.equals(ie1))) {
            if (EnumC3006iE1.NETWORK_ERROR.equals(ie1) || EnumC3006iE1.SERVICE_UNAVAILABLE.equals(ie1) || EnumC3006iE1.INTNERNAL_ERROR.equals(ie1)) {
                z = true;
            }
            if (z) {
                throw new IOException(string);
            }
            throw new C2192dW(string);
        }
        C1464Ya0 ya0 = EF1.c;
        String valueOf = String.valueOf(ie1);
        StringBuilder sb = new StringBuilder(valueOf.length() + 31);
        sb.append("isUserRecoverableError status: ");
        sb.append(valueOf);
        ya0.a("GoogleAuthUtil", sb.toString());
        throw new Xr1(string, intent);
    }
}
