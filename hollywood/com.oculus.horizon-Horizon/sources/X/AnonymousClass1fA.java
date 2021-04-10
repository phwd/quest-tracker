package X;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.GetTokenLoginMethodHandler;
import com.facebook.login.LoginClient;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1fA  reason: invalid class name */
public class AnonymousClass1fA implements Utility.GraphMeRequestWithCacheCallback {
    public final /* synthetic */ Bundle A00;
    public final /* synthetic */ GetTokenLoginMethodHandler A01;
    public final /* synthetic */ LoginClient.Request A02;

    public AnonymousClass1fA(GetTokenLoginMethodHandler getTokenLoginMethodHandler, Bundle bundle, LoginClient.Request request) {
        this.A01 = getTokenLoginMethodHandler;
        this.A00 = bundle;
        this.A02 = request;
    }

    @Override // com.facebook.internal.Utility.GraphMeRequestWithCacheCallback
    public final void onFailure(FacebookException facebookException) {
        LoginClient loginClient = this.A01.A01;
        loginClient.A05(LoginClient.Result.A02(loginClient.A04, "Caught exception", facebookException.getMessage(), null));
    }

    @Override // com.facebook.internal.Utility.GraphMeRequestWithCacheCallback
    public final void onSuccess(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("id");
            Bundle bundle = this.A00;
            bundle.putString(NativeProtocol.EXTRA_USER_ID, string);
            this.A01.A04(this.A02, bundle);
        } catch (JSONException e) {
            LoginClient loginClient = this.A01.A01;
            loginClient.A05(LoginClient.Result.A02(loginClient.A04, "Caught exception", e.getMessage(), null));
        }
    }
}
