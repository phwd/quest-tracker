package X;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zat;
import com.google.android.gms.signin.internal.zae;
import com.google.android.gms.signin.internal.zag;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.internal.zak;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.dj  reason: case insensitive filesystem */
public final class C0643dj extends G3 implements E2 {
    public final Bundle A00;
    public final RQ A01;
    public final Integer A02;
    public final boolean A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0643dj(Context context, Looper looper, RQ rq, AbstractC1086sA sAVar, AbstractC1087sB sBVar) {
        super(context, looper, 44, rq, sAVar, sBVar);
        C0644dk dkVar = rq.A01;
        Integer num = rq.A00;
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", null);
        if (num != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
        }
        if (dkVar != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        }
        this.A03 = true;
        this.A01 = rq;
        this.A00 = bundle;
        this.A02 = rq.A00;
    }

    /* JADX INFO: finally extract failed */
    @Override // X.E2
    public final void A69(zae zae) {
        RZ.A02(zae, "Expecting a valid ISignInCallbacks");
        try {
            Account account = new Account("<<default account>>", "com.google");
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(account.name)) {
                Context context = this.A09;
                RZ.A01(context);
                Lock lock = QM.A03;
                lock.lock();
                try {
                    QM qm = QM.A02;
                    if (qm == null) {
                        qm = new QM(context.getApplicationContext());
                        QM.A02 = qm;
                    }
                    lock.unlock();
                    Lock lock2 = qm.A01;
                    lock2.lock();
                    try {
                        SharedPreferences sharedPreferences = qm.A00;
                        googleSignInAccount = null;
                        String string = sharedPreferences.getString("defaultGoogleSignInAccount", null);
                        lock2.unlock();
                        if (!TextUtils.isEmpty(string)) {
                            StringBuilder sb = new StringBuilder(String.valueOf("googleSignInAccount").length() + 1 + String.valueOf(string).length());
                            sb.append("googleSignInAccount");
                            sb.append(":");
                            sb.append(string);
                            String obj = sb.toString();
                            lock2.lock();
                            String str = null;
                            try {
                                String string2 = sharedPreferences.getString(obj, null);
                                if (string2 != null) {
                                    try {
                                        if (!TextUtils.isEmpty(string2)) {
                                            JSONObject jSONObject = new JSONObject(string2);
                                            String optString = jSONObject.optString("photoUrl");
                                            Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
                                            long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
                                            HashSet hashSet = new HashSet();
                                            JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
                                            int length = jSONArray.length();
                                            for (int i = 0; i < length; i++) {
                                                hashSet.add(new Scope(1, jSONArray.getString(i)));
                                            }
                                            String optString2 = jSONObject.optString("id");
                                            String optString3 = jSONObject.has("tokenId") ? jSONObject.optString("tokenId") : null;
                                            String optString4 = jSONObject.has("email") ? jSONObject.optString("email") : null;
                                            String optString5 = jSONObject.has("displayName") ? jSONObject.optString("displayName") : null;
                                            String optString6 = jSONObject.has("givenName") ? jSONObject.optString("givenName") : null;
                                            String optString7 = jSONObject.has("familyName") ? jSONObject.optString("familyName") : null;
                                            Long valueOf = Long.valueOf(parseLong);
                                            String string3 = jSONObject.getString("obfuscatedIdentifier");
                                            if (valueOf == null) {
                                                valueOf = Long.valueOf(GoogleSignInAccount.A0D.A1a() / 1000);
                                            }
                                            long longValue = valueOf.longValue();
                                            if (!TextUtils.isEmpty(string3)) {
                                                GoogleSignInAccount googleSignInAccount2 = new GoogleSignInAccount(3, optString2, optString3, optString4, optString5, parse, null, longValue, string3, new ArrayList(hashSet), optString6, optString7);
                                                if (jSONObject.has("serverAuthCode")) {
                                                    str = jSONObject.optString("serverAuthCode");
                                                }
                                                googleSignInAccount2.A05 = str;
                                                googleSignInAccount = googleSignInAccount2;
                                            } else {
                                                throw new IllegalArgumentException("Given String is empty or null");
                                            }
                                        }
                                    } catch (JSONException unused) {
                                    }
                                }
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } catch (Throwable th) {
                        lock2.unlock();
                        throw th;
                    }
                } catch (Throwable th2) {
                    lock.unlock();
                    throw th2;
                }
            }
            Integer num = this.A02;
            RZ.A01(num);
            ((zag) A02()).A6B(new zaj(1, new zat(2, account, num.intValue(), googleSignInAccount)), zae);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zae.A6C(new zak(1, new ConnectionResult(8, null), null));
            } catch (RemoteException unused2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    @Override // X.E2
    public final void A6D() {
        A1T(new C1096sM(this));
    }

    @Override // X.AbstractC1084s8, X.RO
    public final boolean A4r() {
        return this.A03;
    }
}
