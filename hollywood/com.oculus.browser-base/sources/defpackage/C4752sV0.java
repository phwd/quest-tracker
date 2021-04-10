package defpackage;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.signin.internal.zai;
import com.google.android.gms.signin.internal.zak;

/* renamed from: sV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4752sV0 extends KV implements AbstractC5045uB1 {
    public final boolean D;
    public final C3800mv E;
    public final Bundle F;
    public Integer G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4752sV0(Context context, Looper looper, C3800mv mvVar, WV wv, XV xv) {
        super(context, looper, 44, mvVar, wv, xv);
        C5092uV0 uv0 = mvVar.g;
        Integer num = mvVar.i;
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", mvVar.f10459a);
        if (num != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
        }
        if (uv0 != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        }
        this.D = true;
        this.E = mvVar;
        this.F = bundle;
        this.G = mvVar.i;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public /* synthetic */ IInterface e(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (queryLocalInterface instanceof OB1) {
            return (OB1) queryLocalInterface;
        }
        return new OB1(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.AbstractC2129d7
    public int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public Bundle i() {
        if (!this.h.getPackageName().equals(this.E.e)) {
            this.F.putString("com.google.android.gms.signin.internal.realClientPackageName", this.E.e);
        }
        return this.F;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String m() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String n() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.AbstractC2129d7
    public boolean requiresSignIn() {
        return this.D;
    }

    public final void z(AbstractC5215vB1 vb1) {
        SE0.i(vb1, "Expecting a valid ISignInCallbacks");
        try {
            Account account = this.E.f10459a;
            if (account == null) {
                account = new Account("<<default account>>", "com.google");
            }
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(account.name)) {
                googleSignInAccount = H21.a(this.h).b();
            }
            ResolveAccountRequest resolveAccountRequest = new ResolveAccountRequest(account, this.G.intValue(), googleSignInAccount);
            OB1 ob1 = (OB1) l();
            zai zai = new zai(resolveAccountRequest);
            Parcel c = ob1.c();
            int i = HB1.f8141a;
            c.writeInt(1);
            zai.writeToParcel(c, 0);
            c.writeStrongBinder((DA1) vb1);
            ob1.d(12, c);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                vb1.L(new zak());
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }
}
