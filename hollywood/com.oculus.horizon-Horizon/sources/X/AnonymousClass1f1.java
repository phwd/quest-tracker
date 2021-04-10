package X;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.AccessTokenManager;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.WebDialog;
import com.facebook.login.GetTokenLoginMethodHandler;
import com.facebook.login.KatanaProxyLoginMethodHandler;
import com.facebook.login.LoginClient;
import com.facebook.login.LoginMethodHandler;
import com.facebook.login.WebViewLoginMethodHandler;
import com.oculus.horizon.R;
import java.util.ArrayList;

/* renamed from: X.1f1  reason: invalid class name */
public final class AnonymousClass1f1 extends Fragment {
    public static final String __redex_internal_original_name = "com.facebook.login.LoginFragment";
    public LoginClient.Request A00;
    public String A01;
    public LoginClient A02;

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        GetTokenLoginMethodHandler getTokenLoginMethodHandler;
        C09391dV r1;
        LoginClient loginClient = this.A02;
        int i = loginClient.A02;
        if (i >= 0) {
            LoginMethodHandler loginMethodHandler = loginClient.A07[i];
            if (loginMethodHandler instanceof WebViewLoginMethodHandler) {
                WebViewLoginMethodHandler webViewLoginMethodHandler = (WebViewLoginMethodHandler) loginMethodHandler;
                WebDialog webDialog = webViewLoginMethodHandler.A00;
                if (webDialog != null) {
                    webDialog.cancel();
                    webViewLoginMethodHandler.A00 = null;
                }
            } else if ((loginMethodHandler instanceof GetTokenLoginMethodHandler) && (r1 = (getTokenLoginMethodHandler = (GetTokenLoginMethodHandler) loginMethodHandler).A00) != null) {
                r1.running = false;
                r1.listener = null;
                getTokenLoginMethodHandler.A00 = null;
            }
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        LoginMethodHandler loginMethodHandler;
        LoginClient.Result A022;
        super.onActivityResult(i, i2, intent);
        LoginClient loginClient = this.A02;
        if (loginClient.A04 != null) {
            int i3 = loginClient.A02;
            if (i3 >= 0) {
                loginMethodHandler = loginClient.A07[i3];
            } else {
                loginMethodHandler = null;
            }
            if (loginMethodHandler instanceof KatanaProxyLoginMethodHandler) {
                LoginClient.Request request = loginMethodHandler.A01.A04;
                if (intent == null) {
                    A022 = LoginClient.Result.A01(request, "Operation canceled");
                } else if (i2 == 0) {
                    Bundle extras = intent.getExtras();
                    String string = extras.getString("error");
                    if (string == null) {
                        string = extras.getString("error_type");
                    }
                    String string2 = extras.getString("error_code");
                    if (ServerProtocol.errorConnectionFailure.equals(string2)) {
                        String string3 = extras.getString("error_message");
                        if (string3 == null) {
                            string3 = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
                        }
                        A022 = LoginClient.Result.A02(request, string, string3, string2);
                    } else {
                        A022 = LoginClient.Result.A01(request, string);
                    }
                } else if (i2 != -1) {
                    A022 = LoginClient.Result.A02(request, "Unexpected resultCode from authorization.", null, null);
                } else {
                    Bundle extras2 = intent.getExtras();
                    String string4 = extras2.getString("error");
                    if (string4 == null) {
                        string4 = extras2.getString("error_type");
                    }
                    String string5 = extras2.getString("error_code");
                    String string6 = extras2.getString("error_message");
                    if (string6 == null) {
                        string6 = extras2.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
                    }
                    String string7 = extras2.getString("e2e");
                    if (!Utility.isNullOrEmpty(string7)) {
                        loginMethodHandler.A02(string7);
                    }
                    if (string4 == null && string5 == null && string6 == null) {
                        try {
                            A022 = LoginClient.Result.A00(request, LoginMethodHandler.A00(request.A00, extras2, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.A04));
                        } catch (FacebookException e) {
                            A022 = LoginClient.Result.A02(request, null, e.getMessage(), null);
                        }
                    } else {
                        if (!ServerProtocol.errorsProxyAuthDisabled.contains(string4)) {
                            if (ServerProtocol.errorsUserCanceled.contains(string4)) {
                                A022 = LoginClient.Result.A01(request, null);
                            } else {
                                A022 = LoginClient.Result.A02(request, string4, string6, string5);
                            }
                        }
                        loginMethodHandler.A01.A04();
                    }
                }
                if (A022 != null) {
                    loginMethodHandler.A01.A06(A022);
                    return;
                }
                loginMethodHandler.A01.A04();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        LoginClient loginClient;
        super.onCreate(bundle);
        if (bundle != null) {
            loginClient = (LoginClient) bundle.getParcelable("loginClient");
            this.A02 = loginClient;
            if (loginClient.A03 == null) {
                loginClient.A03 = this;
            } else {
                throw new FacebookException("Can't set fragment once it is already set.");
            }
        } else {
            loginClient = new LoginClient(this);
            this.A02 = loginClient;
        }
        loginClient.A05 = new AnonymousClass1h1(this);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ComponentName callingActivity = activity.getCallingActivity();
            if (callingActivity != null) {
                this.A01 = callingActivity.getPackageName();
            }
            if (activity.getIntent() != null) {
                this.A00 = (LoginClient.Request) activity.getIntent().getParcelableExtra("request");
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.com_facebook_login_fragment, viewGroup, false);
        this.A02.A06 = new AnonymousClass1gR(this, inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onPause() {
        super.onPause();
        getActivity().findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        if (this.A01 == null) {
            Log.e("LoginFragment", "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
            getActivity().finish();
            return;
        }
        LoginClient loginClient = this.A02;
        LoginClient.Request request = this.A00;
        LoginClient.Request request2 = loginClient.A04;
        if ((request2 != null && loginClient.A02 >= 0) || request == null) {
            return;
        }
        if (request2 != null) {
            throw new FacebookException("Attempted to authorize while a request is pending.");
        } else if (AccessTokenManager.getInstance().currentAccessToken == null || LoginClient.A03(loginClient)) {
            loginClient.A04 = request;
            ArrayList arrayList = new ArrayList();
            AnonymousClass1gC r1 = request.A03;
            if (r1.allowsKatanaAuth()) {
                arrayList.add(new GetTokenLoginMethodHandler(loginClient));
                arrayList.add(new KatanaProxyLoginMethodHandler(loginClient));
            }
            if (r1.allowsWebViewAuth()) {
                arrayList.add(new WebViewLoginMethodHandler(loginClient));
            }
            LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[arrayList.size()];
            arrayList.toArray(loginMethodHandlerArr);
            loginClient.A07 = loginMethodHandlerArr;
            loginClient.A04();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("loginClient", this.A02);
    }
}
