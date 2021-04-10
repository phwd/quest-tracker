package X;

import android.os.Bundle;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookServiceException;
import com.facebook.internal.Utility;
import com.facebook.internal.WebDialog;
import com.facebook.login.LoginClient;
import com.facebook.login.LoginMethodHandler;
import com.facebook.login.WebViewLoginMethodHandler;
import java.util.Locale;

/* renamed from: X.1ez  reason: invalid class name and case insensitive filesystem */
public class C09441ez implements WebDialog.OnCompleteListener {
    public final /* synthetic */ LoginClient.Request A00;
    public final /* synthetic */ WebViewLoginMethodHandler A01;

    public C09441ez(WebViewLoginMethodHandler webViewLoginMethodHandler, LoginClient.Request request) {
        this.A01 = webViewLoginMethodHandler;
        this.A00 = request;
    }

    @Override // com.facebook.internal.WebDialog.OnCompleteListener
    public final void onComplete(Bundle bundle, FacebookException facebookException) {
        LoginClient.Result result;
        String str;
        WebViewLoginMethodHandler webViewLoginMethodHandler = this.A01;
        LoginClient.Request request = this.A00;
        if (bundle != null) {
            if (bundle.containsKey("e2e")) {
                webViewLoginMethodHandler.A01 = bundle.getString("e2e");
            }
            try {
                AccessToken A002 = LoginMethodHandler.A00(request.A00, bundle, AccessTokenSource.WEB_VIEW, request.A04);
                LoginClient loginClient = ((LoginMethodHandler) webViewLoginMethodHandler).A01;
                result = LoginClient.Result.A00(loginClient.A04, A002);
                CookieSyncManager.createInstance(loginClient.A03.getActivity()).sync();
                ((LoginMethodHandler) webViewLoginMethodHandler).A01.A03.getActivity().getSharedPreferences("com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY", 0).edit().putString("TOKEN", A002.token).apply();
            } catch (FacebookException e) {
                result = LoginClient.Result.A02(((LoginMethodHandler) webViewLoginMethodHandler).A01.A04, null, e.getMessage(), null);
            }
        } else if (facebookException instanceof FacebookOperationCanceledException) {
            result = LoginClient.Result.A01(((LoginMethodHandler) webViewLoginMethodHandler).A01.A04, "User canceled log in.");
        } else {
            webViewLoginMethodHandler.A01 = null;
            String message = facebookException.getMessage();
            if (facebookException instanceof FacebookServiceException) {
                FacebookRequestError facebookRequestError = ((FacebookServiceException) facebookException).error;
                str = String.format(Locale.ROOT, "%d", Integer.valueOf(facebookRequestError.errorCode));
                message = facebookRequestError.toString();
            } else {
                str = null;
            }
            result = LoginClient.Result.A02(((LoginMethodHandler) webViewLoginMethodHandler).A01.A04, null, message, str);
        }
        String str2 = webViewLoginMethodHandler.A01;
        if (!Utility.isNullOrEmpty(str2)) {
            webViewLoginMethodHandler.A02(str2);
        }
        ((LoginMethodHandler) webViewLoginMethodHandler).A01.A06(result);
    }
}
