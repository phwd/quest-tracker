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

    public void startAuthService(Intent intent) {
        throw null;
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

    private Intent attachCallerInfo(Intent intent) {
        CallerInfoProvider callerInfoProvider = this.mCallerInfoProvider;
        if (callerInfoProvider != null) {
            callerInfoProvider.attachCallerInfo(intent);
        }
        return intent;
    }

    /* renamed from: com.oculus.authapi.OVRAuth$1  reason: invalid class name */
    class AnonymousClass1 extends AuthTaskReceiver<Void, AuthLoginError> {
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

    public void loginWithCredentials(String str, String str2, ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent("com.oculus.auth.ACTION_LOGIN_API");
        newHorizonIntent.putExtra("user_id", str);
        newHorizonIntent.putExtra("access_token", str2);
        newHorizonIntent.putExtra("receiver", getReceiverForSending(resultReceiver));
        startAuthService(newHorizonIntent);
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

    /* renamed from: com.oculus.authapi.OVRAuth$4  reason: invalid class name */
    class AnonymousClass4 extends AuthTaskReceiver<Void, AuthLoginWithFbAuthError> {
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
    }

    public void status(final ResultReceiver resultReceiver) {
        Intent newHorizonIntent = newHorizonIntent("com.oculus.auth.ACTION_STATUS");
        newHorizonIntent.putExtra("receiver", getReceiverForSending(new ResultReceiver(null) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass5 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                resultReceiver.send(i, bundle);
            }
        }));
        startAuthService(newHorizonIntent);
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

    public void logout(ResultReceiver resultReceiver) {
        logout(resultReceiver, false);
    }

    public void logout(final ResultReceiver resultReceiver, boolean z) {
        Intent newHorizonIntent = newHorizonIntent("com.oculus.auth.ACTION_LOGOUT");
        newHorizonIntent.putExtra("receiver", getReceiverForSending(new ResultReceiver(null) {
            /* class com.oculus.authapi.OVRAuth.AnonymousClass8 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                resultReceiver.send(i, bundle);
            }
        }));
        newHorizonIntent.putExtra("logout_no_server", z);
        startAuthService(newHorizonIntent);
    }

    /* renamed from: com.oculus.authapi.OVRAuth$12  reason: invalid class name */
    class AnonymousClass12 extends AuthTaskReceiver<AuthFetchTwoFactorMethodsResult, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public AuthFetchTwoFactorMethodsResult createResultFromBundle(Bundle bundle) {
            return new AuthFetchTwoFactorMethodsResult(bundle);
        }
    }

    /* renamed from: com.oculus.authapi.OVRAuth$13  reason: invalid class name */
    class AnonymousClass13 extends AuthTaskReceiver<AuthFbLoginResult, AuthFbLoginError> {
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

    /* renamed from: com.oculus.authapi.OVRAuth$15  reason: invalid class name */
    class AnonymousClass15 extends AuthTaskReceiver<Boolean, AuthError> {
        /* access modifiers changed from: protected */
        @Override // com.oculus.authapi.AuthTaskReceiver
        public Boolean createResultFromBundle(Bundle bundle) {
            return Boolean.valueOf(bundle.getBoolean("machine_approved"));
        }
    }

    private Intent newHorizonIntent(String str) {
        Intent intent = new Intent(str);
        intent.setPackage(BuildConstants.PACKAGE_NAME_HORIZON);
        attachCallerInfo(intent);
        return intent;
    }
}
