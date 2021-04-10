package defpackage;

import J.N;
import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Bundle;
import android.os.Handler;
import java.io.IOException;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.net.HttpNegotiateAuthenticator;

/* renamed from: XX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XX implements AccountManagerCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C1674aY f9213a;
    public final /* synthetic */ HttpNegotiateAuthenticator b;

    public XX(HttpNegotiateAuthenticator httpNegotiateAuthenticator, C1674aY aYVar) {
        this.b = httpNegotiateAuthenticator;
        this.f9213a = aYVar;
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture accountManagerFuture) {
        try {
            Account[] accountArr = (Account[]) accountManagerFuture.getResult();
            if (accountArr.length == 0) {
                AbstractC1220Ua0.f("net_auth", "ERR_MISSING_AUTH_CREDENTIALS: No account provided for the kerberos authentication. Please verify the configuration policies and that the CONTACTS runtime permission is granted. ", new Object[0]);
                N.M0s8NeYn(this.f9213a.f9435a, this.b, -341, null);
            } else if (accountArr.length > 1) {
                AbstractC1220Ua0.f("net_auth", "ERR_MISSING_AUTH_CREDENTIALS: Found %d accounts eligible for the kerberos authentication. Please fix the configuration by providing a single account.", Integer.valueOf(accountArr.length));
                N.M0s8NeYn(this.f9213a.f9435a, this.b, -341, null);
            } else if (this.b.a(ContextUtils.getApplicationContext(), "android.permission.USE_CREDENTIALS", true)) {
                AbstractC1220Ua0.a("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: USE_CREDENTIALS permission not granted. Aborting authentication.", new Object[0]);
                N.M0s8NeYn(this.f9213a.f9435a, this.b, -343, null);
            } else {
                C1674aY aYVar = this.f9213a;
                Account account = accountArr[0];
                aYVar.e = account;
                aYVar.b.getAuthToken(account, aYVar.d, aYVar.c, true, (AccountManagerCallback<Bundle>) new ZX(this.b, aYVar), new Handler(ThreadUtils.c()));
            }
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            AbstractC1220Ua0.f("net_auth", "ERR_UNEXPECTED: Error while attempting to retrieve accounts.", e);
            N.M0s8NeYn(this.f9213a.f9435a, this.b, -9, null);
        }
    }
}
