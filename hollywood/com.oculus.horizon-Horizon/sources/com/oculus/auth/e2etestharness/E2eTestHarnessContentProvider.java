package com.oculus.auth.e2etestharness;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.facebook.infer.annotation.Initializer;
import com.facebook.secure.content.PublicContentProvider;
import com.google.common.util.concurrent.SettableFuture;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.authapi.AuthCredentials;
import com.oculus.authapi.AuthError;
import com.oculus.authapi.AuthFbLoginError;
import com.oculus.authapi.AuthFbLoginResult;
import com.oculus.authapi.AuthLoginError;
import com.oculus.authapi.AuthLoginWithFbAuthError;
import com.oculus.authapi.AuthTaskReceiver;
import com.oculus.authapi.OVRAuth;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;

public class E2eTestHarnessContentProvider extends PublicContentProvider {
    public static final String[] REQ_EX_AUTH_TOKEN_USER_ID = {Constants.EXTRA_KEY_AUTH_TOKEN, "userId"};
    public static final String[] REQ_EX_EMAIL_PASSWORD = {"email", "password"};
    public static final int SYSTEM_UID = 0;
    public static final String TAG = "E2eTestHarnessContentProvider";
    public OVRAuth mOVRAuth;

    private Bundle testLoginWithFbAuth(Bundle bundle, @Nullable Bundle bundle2) {
        if (!requiredExtrasProvided(bundle, bundle2, new String[]{Constants.EXTRA_KEY_AUTH_TOKEN}) || bundle2 == null) {
            bundle.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, false);
            return bundle;
        }
        String string = bundle2.getString(Constants.EXTRA_KEY_AUTH_TOKEN, "");
        SettableFuture create = SettableFuture.create();
        TestAuthCallback testAuthCallback = new TestAuthCallback(bundle, create);
        OVRAuth oVRAuth = this.mOVRAuth;
        OVRAuth.AnonymousClass6 r1 = new AuthTaskReceiver<Void, AuthLoginWithFbAuthError>(testAuthCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass6 */

            /* Return type fixed from 'com.oculus.authapi.AuthError' to match base method */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public final AuthLoginWithFbAuthError A01(Bundle bundle) {
                return new AuthLoginWithFbAuthError(bundle);
            }

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public final Void A02(Bundle bundle) throws AuthError {
                return null;
            }
        };
        Intent A00 = OVRAuth.A00(oVRAuth, ServiceContract.ACTION_LOGIN_WITH_FB_AUTH);
        A00.putExtra(ServiceContract.EXTRA_FB_ACCESS_TOKEN, string);
        A00.putExtra("receiver", OVRAuth.A01(oVRAuth, r1));
        A00.putExtra("is_relogin", false);
        OVRAuth.A02(oVRAuth, A00);
        return waitForTestResult(create, bundle);
    }

    @Override // X.AbstractC09361bk
    @Initializer
    public void onInitialize() {
        AnonymousClass0NO.A00(4);
        Context context = getContext();
        if (context != null) {
            this.mOVRAuth = new OVRAuth(context, new CallerInfoProviderImpl(context));
            return;
        }
        throw null;
    }

    public static void failTestWithError(Bundle bundle, Exception exc) {
        AnonymousClass0NO.A0H(TAG, exc, "Test failed");
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        bundle.putString(Constants.EXTRA_KEY_ERROR, stringWriter.toString());
        bundle.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, false);
    }

    public static boolean requiredExtrasProvided(Bundle bundle, @Nullable Bundle bundle2, String[] strArr) {
        String A07;
        if (bundle2 == null) {
            A07 = "Extras are required but were not provided";
        } else {
            for (String str : strArr) {
                if (bundle2.getString(str) == null) {
                    A07 = AnonymousClass006.A07("Required extra ", str, " was not provided");
                }
            }
            return true;
        }
        bundle.putString(Constants.EXTRA_KEY_ERROR, A07);
        return false;
    }

    private Bundle testFacebookEmailAndPasswordAuth(Bundle bundle, @Nullable Bundle bundle2) {
        if (!requiredExtrasProvided(bundle, bundle2, REQ_EX_EMAIL_PASSWORD) || bundle2 == null) {
            bundle.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, false);
            return bundle;
        }
        String string = bundle2.getString("email", "");
        String string2 = bundle2.getString("password", "");
        SettableFuture create = SettableFuture.create();
        TestFbAuthCallback testFbAuthCallback = new TestFbAuthCallback(bundle, create);
        OVRAuth oVRAuth = this.mOVRAuth;
        OVRAuth.AnonymousClass16 r3 = new AuthTaskReceiver<AuthFbLoginResult, AuthFbLoginError>(testFbAuthCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass16 */

            /* Return type fixed from 'com.oculus.authapi.AuthError' to match base method */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public final AuthFbLoginError A01(Bundle bundle) {
                return new AuthFbLoginError(bundle);
            }

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public final AuthFbLoginResult A02(Bundle bundle) throws AuthError {
                try {
                    return new AuthFbLoginResult(bundle);
                } catch (AuthError e) {
                    throw new AuthFbLoginError(e);
                }
            }
        };
        Intent A00 = OVRAuth.A00(oVRAuth, ServiceContract.ACTION_FB_LOGIN);
        A00.putExtra("email", string);
        A00.putExtra("password", string2);
        A00.putExtra(ServiceContract.EXTRA_FB_LINK_TOS_VERSION, (String) null);
        A00.putExtra(ServiceContract.EXTRA_FB_LINK_LOGGING_JSON, (String) null);
        A00.putExtra("receiver", OVRAuth.A01(oVRAuth, r3));
        OVRAuth.A02(oVRAuth, A00);
        return waitForTestResult(create, bundle);
    }

    private Bundle testOculusAuthTokenAuth(Bundle bundle, @Nullable Bundle bundle2) {
        if (!requiredExtrasProvided(bundle, bundle2, REQ_EX_AUTH_TOKEN_USER_ID) || bundle2 == null) {
            bundle.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, false);
            return bundle;
        }
        String string = bundle2.getString(Constants.EXTRA_KEY_AUTH_TOKEN, "");
        String string2 = bundle2.getString("userId", "");
        SettableFuture create = SettableFuture.create();
        TestResultReceiver testResultReceiver = new TestResultReceiver(new TestAuthCallback(bundle, create), bundle);
        OVRAuth oVRAuth = this.mOVRAuth;
        Intent A00 = OVRAuth.A00(oVRAuth, ServiceContract.ACTION_LOGIN_API);
        A00.putExtra("user_id", string2);
        A00.putExtra("access_token", string);
        A00.putExtra("receiver", OVRAuth.A01(oVRAuth, testResultReceiver));
        OVRAuth.A02(oVRAuth, A00);
        return waitForTestResult(create, bundle);
    }

    private Bundle testOculusEmailAndPasswordAuth(Bundle bundle, @Nullable Bundle bundle2) {
        if (!requiredExtrasProvided(bundle, bundle2, REQ_EX_EMAIL_PASSWORD) || bundle2 == null) {
            bundle.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, false);
            return bundle;
        }
        String string = bundle2.getString("email", "");
        String string2 = bundle2.getString("password", "");
        SettableFuture create = SettableFuture.create();
        TestAuthCallback testAuthCallback = new TestAuthCallback(bundle, create);
        OVRAuth oVRAuth = this.mOVRAuth;
        OVRAuth.AnonymousClass1 r1 = new AuthTaskReceiver<Void, AuthLoginError>(testAuthCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass1 */

            /* Return type fixed from 'com.oculus.authapi.AuthError' to match base method */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public final AuthLoginError A01(Bundle bundle) {
                return new AuthLoginError(bundle);
            }

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public final Void A02(Bundle bundle) throws AuthError {
                return null;
            }
        };
        Intent A00 = OVRAuth.A00(oVRAuth, ServiceContract.ACTION_LOGIN_API);
        A00.putExtra("email", string);
        A00.putExtra("password", string2);
        A00.putExtra("receiver", OVRAuth.A01(oVRAuth, r1));
        A00.putExtra("is_relogin", false);
        OVRAuth.A02(oVRAuth, A00);
        return waitForTestResult(create, bundle);
    }

    @Override // X.AbstractC09361bk
    public Bundle doCall(String str, @Nullable String str2, @Nullable Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(Constants.EXTRA_KEY_METHOD, str);
        switch (str.hashCode()) {
            case -2074885109:
                if (str.equals(Constants.REQUEST_AUTH_STATUS)) {
                    return getStatus(bundle2);
                }
                break;
            case -1933425180:
                if (str.equals(Constants.FETCH_HORIZON_DEVICE_SCOPED_CREDENTIALS)) {
                    return testFetchHorizonCredentials(bundle2, bundle);
                }
                break;
            case -1258469802:
                if (str.equals(Constants.LOGIN_WITH_FB_AUTH)) {
                    return testLoginWithFbAuth(bundle2, bundle);
                }
                break;
            case -718142583:
                if (str.equals(Constants.FETCH_DEVICE_SCOPED_ACCESS_TOKEN)) {
                    return testFetchDsatToken(bundle2, bundle);
                }
                break;
            case -670439294:
                if (str.equals(Constants.OCULUS_AUTH_TOKEN_AUTH)) {
                    return testOculusAuthTokenAuth(bundle2, bundle);
                }
                break;
            case 1705607386:
                if (str.equals(Constants.OCULUS_EMAIL_PASSWORD_AUTH)) {
                    return testOculusEmailAndPasswordAuth(bundle2, bundle);
                }
                break;
            case 1733049341:
                if (str.equals(Constants.FACEBOOK_EMAIL_PASSWORD_AUTH)) {
                    return testFacebookEmailAndPasswordAuth(bundle2, bundle);
                }
                break;
        }
        bundle2.putString(Constants.EXTRA_KEY_ERROR, Constants.ERROR_METHOD_NOT_IMPLEMENTED);
        bundle2.putBoolean(Constants.EXTRA_KEY_TEST_PASSED, false);
        return bundle2;
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override // X.AbstractC09361bk
    public String doGetType(Uri uri) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override // X.AbstractC09361bk
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override // X.AbstractC09361bk
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not supported");
    }

    private Bundle getStatus(Bundle bundle) {
        SettableFuture create = SettableFuture.create();
        TestResultReceiver testResultReceiver = new TestResultReceiver(new TestAuthCallback(bundle, create), bundle);
        OVRAuth oVRAuth = this.mOVRAuth;
        Intent A00 = OVRAuth.A00(oVRAuth, ServiceContract.ACTION_STATUS);
        A00.putExtra("receiver", OVRAuth.A01(oVRAuth, new ResultReceiver(testResultReceiver) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass8 */
            public final /* synthetic */ ResultReceiver val$receiver;

            {
                this.val$receiver = r3;
            }

            public final void onReceiveResult(int i, Bundle bundle) {
                this.val$receiver.send(i, bundle);
            }
        }));
        OVRAuth.A02(oVRAuth, A00);
        return waitForTestResult(create, bundle);
    }

    private Bundle testFetchDsatToken(Bundle bundle, @Nullable Bundle bundle2) {
        SettableFuture create = SettableFuture.create();
        TestOculusAuthCallback testOculusAuthCallback = new TestOculusAuthCallback(bundle, create);
        OVRAuth oVRAuth = this.mOVRAuth;
        OVRAuth.AnonymousClass9 r1 = new AuthTaskReceiver<AuthCredentials, AuthError>(testOculusAuthCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass9 */

            /* Return type fixed from 'java.lang.Object' to match base method */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public final AuthCredentials A02(Bundle bundle) throws AuthError {
                return new AuthCredentials(bundle);
            }
        };
        Intent A00 = OVRAuth.A00(oVRAuth, ServiceContract.ACTION_FETCH_DEVICE_SCOPED_CREDENTIALS);
        A00.putExtra("receiver", OVRAuth.A01(oVRAuth, r1));
        OVRAuth.A02(oVRAuth, A00);
        return waitForTestResult(create, bundle);
    }

    private Bundle testFetchHorizonCredentials(Bundle bundle, @Nullable Bundle bundle2) {
        SettableFuture create = SettableFuture.create();
        this.mOVRAuth.A03(new TestOculusAuthCallback(bundle, create));
        return waitForTestResult(create, bundle);
    }

    public static Bundle waitForTestResult(SettableFuture<Bundle> settableFuture, Bundle bundle) {
        try {
            return settableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            failTestWithError(bundle, e);
            return bundle;
        }
    }

    @Override // com.facebook.secure.content.PublicContentProvider, X.AbstractC09361bk
    public final boolean onCheckPermissions() {
        if (Binder.getCallingUid() == 0) {
            return true;
        }
        return false;
    }

    public E2eTestHarnessContentProvider() {
    }

    public E2eTestHarnessContentProvider(OVRAuth oVRAuth) {
        this.mOVRAuth = oVRAuth;
    }
}
