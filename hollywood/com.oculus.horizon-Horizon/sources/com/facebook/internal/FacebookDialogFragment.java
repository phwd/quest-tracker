package com.facebook.internal;

import X.AnonymousClass0AH;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.WebDialog;

public class FacebookDialogFragment extends AnonymousClass0AH {
    public static final String TAG = "FacebookDialogFragment";
    public Dialog dialog;

    @Override // X.AnonymousClass0AH
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.dialog == null) {
            onCompleteWebDialog(null, null);
            this.mShowsDialog = false;
        }
        return this.dialog;
    }

    @Override // androidx.fragment.app.Fragment, X.AnonymousClass0AH
    public void onDestroyView() {
        Dialog dialog2 = this.mDialog;
        if (dialog2 != null && this.mRetainInstance) {
            dialog2.setDismissMessage(null);
        }
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onCompleteWebDialog(Bundle bundle, FacebookException facebookException) {
        FragmentActivity activity = getActivity();
        Intent createProtocolResultIntent = NativeProtocol.createProtocolResultIntent(activity.getIntent(), bundle, facebookException);
        int i = 0;
        if (facebookException == null) {
            i = -1;
        }
        activity.setResult(i, createProtocolResultIntent);
        activity.finish();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onCompleteWebFallbackDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        activity.setResult(-1, intent);
        activity.finish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Dialog dialog2 = this.dialog;
        if (dialog2 instanceof WebDialog) {
            ((WebDialog) dialog2).resize();
        }
    }

    @Override // androidx.fragment.app.Fragment, X.AnonymousClass0AH
    public void onCreate(Bundle bundle) {
        WebDialog facebookWebFallbackDialog;
        super.onCreate(bundle);
        if (this.dialog == null) {
            FragmentActivity activity = getActivity();
            Bundle methodArgumentsFromIntent = NativeProtocol.getMethodArgumentsFromIntent(activity.getIntent());
            if (!methodArgumentsFromIntent.getBoolean(NativeProtocol.WEB_DIALOG_IS_FALLBACK, false)) {
                String string = methodArgumentsFromIntent.getString("action");
                Bundle bundle2 = methodArgumentsFromIntent.getBundle("params");
                if (!Utility.isNullOrEmpty(string)) {
                    WebDialog.Builder builder = new WebDialog.Builder(activity, string, bundle2);
                    builder.listener = new WebDialog.OnCompleteListener() {
                        /* class com.facebook.internal.FacebookDialogFragment.AnonymousClass1 */

                        @Override // com.facebook.internal.WebDialog.OnCompleteListener
                        public void onComplete(Bundle bundle, FacebookException facebookException) {
                            FacebookDialogFragment.this.onCompleteWebDialog(bundle, facebookException);
                        }
                    };
                    facebookWebFallbackDialog = builder.build();
                }
                activity.finish();
                return;
            }
            String string2 = methodArgumentsFromIntent.getString("url");
            if (!Utility.isNullOrEmpty(string2)) {
                Validate.sdkInitialized();
                facebookWebFallbackDialog = new FacebookWebFallbackDialog(activity, string2, String.format("fb%s://bridge/", FacebookSdk.applicationId));
                facebookWebFallbackDialog.onCompleteListener = new WebDialog.OnCompleteListener() {
                    /* class com.facebook.internal.FacebookDialogFragment.AnonymousClass2 */

                    @Override // com.facebook.internal.WebDialog.OnCompleteListener
                    public void onComplete(Bundle bundle, FacebookException facebookException) {
                        FacebookDialogFragment.this.onCompleteWebFallbackDialog(bundle);
                    }
                };
            }
            activity.finish();
            return;
            this.dialog = facebookWebFallbackDialog;
        }
    }

    public void setDialog(Dialog dialog2) {
        this.dialog = dialog2;
    }
}
