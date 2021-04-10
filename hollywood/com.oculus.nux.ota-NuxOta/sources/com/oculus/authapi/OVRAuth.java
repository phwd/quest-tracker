package com.oculus.authapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import com.oculus.common.build.BuildConstants;

public class OVRAuth {
    private final CallerInfoProvider mCallerInfoProvider;
    private final Context mContext;
    private boolean mIsUnderTest;

    /* renamed from: com.oculus.authapi.OVRAuth$10  reason: invalid class name */
    class AnonymousClass10 extends AuthTaskReceiver<Void, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public Void createResultFromBundle(Bundle bundle) {
            return null;
        }
    }

    /* renamed from: com.oculus.authapi.OVRAuth$11  reason: invalid class name */
    class AnonymousClass11 extends AuthTaskReceiver<Void, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public Void createResultFromBundle(Bundle bundle) {
            return null;
        }
    }

    /* renamed from: com.oculus.authapi.OVRAuth$9  reason: invalid class name */
    class AnonymousClass9 extends AuthTaskReceiver<Void, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public Void createResultFromBundle(Bundle bundle) {
            return null;
        }
    }

    public interface CallerInfoProvider {
        Intent attachCallerInfo(Intent intent);
    }

    private ResultReceiver getReceiverForSending(ResultReceiver resultReceiver) {
        if (this.mIsUnderTest) {
            return new TestReceiverForSending(resultReceiver);
        }
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }

    /* access modifiers changed from: package-private */
    public static final class TestReceiverForSending extends ResultReceiver {
        final ResultReceiver mActualReceiver;

        TestReceiverForSending(ResultReceiver resultReceiver) {
            super(null);
            this.mActualReceiver = resultReceiver;
        }

        public void send(int i, Bundle bundle) {
            this.mActualReceiver.send(i, bundle);
        }
    }

    public OVRAuth(Context context, CallerInfoProvider callerInfoProvider) {
        this.mContext = context;
        this.mCallerInfoProvider = callerInfoProvider;
    }

    /* access modifiers changed from: package-private */
    public void loginWithEmailAndPassword(String str, String str2, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent("com.oculus.auth.ACTION_LOGIN_API");
        newHorizonIntent.putExtra("email", str);
        newHorizonIntent.putExtra("password", str2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        this.mContext.startService(newHorizonIntent);
    }

    private Intent attachCallerInfo(Intent intent) {
        CallerInfoProvider callerInfoProvider = this.mCallerInfoProvider;
        if (callerInfoProvider != null) {
            callerInfoProvider.attachCallerInfo(intent);
        }
        return intent;
    }

    public void loginWithEmailAndPassword(String str, String str2, AuthResultCallback<Void, AuthLoginError> authResultCallback) {
        loginWithEmailAndPassword(str, str2, new AuthTaskReceiver<Void, AuthLoginError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginError(bundle);
            }
        });
    }

    /* renamed from: com.oculus.authapi.OVRAuth$2  reason: invalid class name */
    class AnonymousClass2 extends AuthTaskReceiver<Void, AuthLoginError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public Void createResultFromBundle(Bundle bundle) {
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public AuthLoginError createErrorFromBundle(Bundle bundle) {
            return new AuthLoginError(bundle);
        }
    }

    /* renamed from: com.oculus.authapi.OVRAuth$3  reason: invalid class name */
    class AnonymousClass3 extends AuthTaskReceiver<Void, AuthLoginError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public Void createResultFromBundle(Bundle bundle) {
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public AuthLoginError createErrorFromBundle(Bundle bundle) {
            return new AuthLoginError(bundle);
        }
    }

    public void loginWithFbAuth(String str, AuthResultCallback<Void, AuthLoginWithFbAuthError> authResultCallback) {
        AnonymousClass4 r0 = new AuthTaskReceiver<Void, AuthLoginWithFbAuthError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass4 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public Void createResultFromBundle(Bundle bundle) {
                return null;
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthLoginWithFbAuthError createErrorFromBundle(Bundle bundle) {
                return new AuthLoginWithFbAuthError(bundle);
            }
        };
        Intent newHorizonIntent = newHorizonIntent("com.oculus.auth.ACTION_LOGIN_WITH_FB_AUTH");
        newHorizonIntent.putExtra("fb_access_token", str);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(r0));
        startAuthService(newHorizonIntent);
    }

    /* renamed from: com.oculus.authapi.OVRAuth$5  reason: invalid class name */
    class AnonymousClass5 extends ResultReceiver {
        final /* synthetic */ ResultReceiver val$receiver;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            this.val$receiver.send(i, bundle);
        }
    }

    /* renamed from: com.oculus.authapi.OVRAuth$6  reason: invalid class name */
    class AnonymousClass6 extends AuthTaskReceiver<AuthCredentials, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public AuthCredentials createResultFromBundle(Bundle bundle) {
            return new AuthCredentials(bundle);
        }
    }

    /* renamed from: com.oculus.authapi.OVRAuth$7  reason: invalid class name */
    class AnonymousClass7 extends AuthTaskReceiver<AuthCredentials, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public AuthCredentials createResultFromBundle(Bundle bundle) {
            return new AuthCredentials(bundle);
        }
    }

    /* renamed from: com.oculus.authapi.OVRAuth$8  reason: invalid class name */
    class AnonymousClass8 extends ResultReceiver {
        final /* synthetic */ ResultReceiver val$receiver;

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            this.val$receiver.send(i, bundle);
        }
    }

    /* renamed from: com.oculus.authapi.OVRAuth$12  reason: invalid class name */
    class AnonymousClass12 extends AuthTaskReceiver<AuthFetchTwoFactorMethodsResult, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public AuthFetchTwoFactorMethodsResult createResultFromBundle(Bundle bundle) {
            return new AuthFetchTwoFactorMethodsResult(bundle);
        }
    }

    public void loginToFb(String str, String str2, AuthResultCallback<AuthFbLoginResult, AuthFbLoginError> authResultCallback) {
        loginToFb(str, str2, null, null, new AuthTaskReceiver<AuthFbLoginResult, AuthFbLoginError>(authResultCallback) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass13 */

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFbLoginResult createResultFromBundle(Bundle bundle) throws AuthFbLoginError {
                try {
                    return new AuthFbLoginResult(bundle);
                } catch (AuthError e) {
                    throw new AuthFbLoginError(e);
                }
            }

            /* access modifiers changed from: protected */
            @Override // com.oculus.authapi.AuthTaskReceiver
            public AuthFbLoginError createErrorFromBundle(Bundle bundle) {
                return new AuthFbLoginError(bundle);
            }
        });
    }

    /* renamed from: com.oculus.authapi.OVRAuth$14  reason: invalid class name */
    class AnonymousClass14 extends AuthTaskReceiver<Void, AuthFbLoginError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public Void createResultFromBundle(Bundle bundle) {
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public AuthFbLoginError createErrorFromBundle(Bundle bundle) {
            return new AuthFbLoginError(bundle);
        }
    }

    private void loginToFb(String str, String str2, String str3, String str4, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent("com.oculus.auth.ACTION_FB_LOGIN");
        newHorizonIntent.putExtra("email", str);
        newHorizonIntent.putExtra("password", str2);
        newHorizonIntent.putExtra("fb_link_logging_json", str3);
        putExtraIfNonnull(newHorizonIntent, "follow_up", str4);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        startAuthService(newHorizonIntent);
    }

    /* renamed from: com.oculus.authapi.OVRAuth$15  reason: invalid class name */
    class AnonymousClass15 extends AuthTaskReceiver<Boolean, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public Boolean createResultFromBundle(Bundle bundle) {
            return Boolean.valueOf(bundle.getBoolean("machine_approved"));
        }
    }

    public void completeFbLoginWithTwoFactorCode(String str, String str2, String str3, String str4, String str5, AuthResultCallback<AuthFbLoginResult, AuthError> authResultCallback) {
        completeFbLoginWithTwoFactorCode(str, str2, str3, str4, str5, null, null, new AuthFbLoginReceiver(authResultCallback));
    }

    private void completeFbLoginWithTwoFactorCode(String str, String str2, String str3, String str4, String str5, String str6, String str7, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent("com.oculus.auth.ACTION_FB_VERIFY_LOGIN_APPROVALS_CODE");
        newHorizonIntent.putExtra("email", str);
        newHorizonIntent.putExtra("uid", str2);
        newHorizonIntent.putExtra("machine_id", str3);
        newHorizonIntent.putExtra("first_factor", str4);
        newHorizonIntent.putExtra("pin", str5);
        putExtraIfNonnull(newHorizonIntent, "fb_link_logging_json", str6);
        putExtraIfNonnull(newHorizonIntent, "follow_up", str7);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        startAuthService(newHorizonIntent);
    }

    public void startAuthService(Intent intent) {
        this.mContext.startService(intent);
    }

    private Intent newHorizonIntent(String str) {
        Intent intent = new Intent(str);
        intent.setPackage(BuildConstants.PACKAGE_NAME_HORIZON);
        attachCallerInfo(intent);
        return intent;
    }

    private static void putExtraIfNonnull(Intent intent, String str, String str2) {
        if (str2 != null) {
            intent.putExtra(str, str2);
        }
    }
}
