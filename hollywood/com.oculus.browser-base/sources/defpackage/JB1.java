package defpackage;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.signin.internal.zab;
import com.google.android.gms.signin.internal.zak;

/* renamed from: JB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class JB1 extends DA1 implements AbstractC5215vB1 {
    public JB1() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override // defpackage.DA1
    public boolean y0(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 3) {
            ConnectionResult connectionResult = (ConnectionResult) HB1.a(parcel, ConnectionResult.CREATOR);
            zab zab = (zab) HB1.a(parcel, zab.CREATOR);
        } else if (i == 4) {
            Status status = (Status) HB1.a(parcel, Status.CREATOR);
        } else if (i == 6) {
            Status status2 = (Status) HB1.a(parcel, Status.CREATOR);
        } else if (i == 7) {
            Status status3 = (Status) HB1.a(parcel, Status.CREATOR);
            GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) HB1.a(parcel, GoogleSignInAccount.CREATOR);
        } else if (i != 8) {
            return false;
        } else {
            L((zak) HB1.a(parcel, zak.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
