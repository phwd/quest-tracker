package defpackage;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import java.io.IOException;
import org.chromium.base.Callback;

/* renamed from: u51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5027u51 implements AccountManagerCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f11385a;

    public C5027u51(Callback callback) {
        this.f11385a = callback;
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture accountManagerFuture) {
        Callback callback = this.f11385a;
        try {
            callback.onResult((Intent) ((Bundle) accountManagerFuture.getResult()).getParcelable("intent"));
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            AbstractC1220Ua0.a("Auth", "Error while creating an intent to add an account: ", e);
            callback.onResult(null);
        }
    }
}
