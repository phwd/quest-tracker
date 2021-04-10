package defpackage;

import J.N;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import com.oculus.os.Version;
import java.io.IOException;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.net.HttpNegotiateAuthenticator;

/* renamed from: ZX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZX implements AccountManagerCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C1674aY f9348a;
    public final /* synthetic */ HttpNegotiateAuthenticator b;

    public ZX(HttpNegotiateAuthenticator httpNegotiateAuthenticator, C1674aY aYVar) {
        this.b = httpNegotiateAuthenticator;
        this.f9348a = aYVar;
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture accountManagerFuture) {
        int i = -9;
        try {
            Bundle bundle = (Bundle) accountManagerFuture.getResult();
            if (bundle.containsKey("intent")) {
                Context applicationContext = ContextUtils.getApplicationContext();
                applicationContext.registerReceiver(new YX(this, applicationContext), new IntentFilter("android.accounts.LOGIN_ACCOUNTS_CHANGED"));
                return;
            }
            HttpNegotiateAuthenticator httpNegotiateAuthenticator = this.b;
            C1674aY aYVar = this.f9348a;
            Objects.requireNonNull(httpNegotiateAuthenticator);
            httpNegotiateAuthenticator.f11002a = bundle.getBundle("spnegoContext");
            int i2 = bundle.getInt("spnegoResult", 1);
            if (i2 != 0) {
                switch (i2) {
                    case 2:
                        i = -3;
                        break;
                    case 3:
                        i = -342;
                        break;
                    case 4:
                        i = -320;
                        break;
                    case 5:
                        i = -338;
                        break;
                    case 6:
                        i = -339;
                        break;
                    case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                        i = -341;
                        break;
                    case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                        i = -344;
                        break;
                    case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                        i = -329;
                        break;
                }
            } else {
                i = 0;
            }
            N.M0s8NeYn(aYVar.f9435a, httpNegotiateAuthenticator, i, bundle.getString("authtoken"));
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            AbstractC1220Ua0.f("net_auth", "ERR_UNEXPECTED: Error while attempting to obtain a token.", e);
            N.M0s8NeYn(this.f9348a.f9435a, this.b, -9, null);
        }
    }
}
