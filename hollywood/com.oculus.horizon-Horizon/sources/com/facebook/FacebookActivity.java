package com.facebook;

import X.AbstractC003209a;
import X.AnonymousClass0sD;
import X.AnonymousClass1f1;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.internal.NativeProtocol;
import com.oculus.horizon.R;

public class FacebookActivity extends FragmentActivity {
    public static final String FRAGMENT_TAG = "SingleFragment";
    public static final String PASS_THROUGH_CANCEL_ACTION = "PassThrough";
    public Fragment singleFragment;

    private void handlePassThroughError() {
        Intent intent = getIntent();
        setResult(0, NativeProtocol.createProtocolResultIntent(intent, null, NativeProtocol.getExceptionFromErrorData(NativeProtocol.getMethodArgumentsFromIntent(intent))));
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Fragment fragment = this.singleFragment;
        if (fragment != null) {
            fragment.onConfigurationChanged(configuration);
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.com_facebook_activity_layout);
        Intent intent = getIntent();
        if (PASS_THROUGH_CANCEL_ACTION.equals(intent.getAction())) {
            handlePassThroughError();
            return;
        }
        AbstractC003209a supportFragmentManager = getSupportFragmentManager();
        Fragment A0J = supportFragmentManager.A0J(FRAGMENT_TAG);
        FacebookDialogFragment facebookDialogFragment = A0J;
        if (A0J == null) {
            if (FacebookDialogFragment.TAG.equals(intent.getAction())) {
                FacebookDialogFragment facebookDialogFragment2 = new FacebookDialogFragment();
                facebookDialogFragment2.setRetainInstance(true);
                facebookDialogFragment2.show(supportFragmentManager, FRAGMENT_TAG);
                facebookDialogFragment = facebookDialogFragment2;
            } else {
                AnonymousClass1f1 r3 = new AnonymousClass1f1();
                r3.setRetainInstance(true);
                AnonymousClass0sD r1 = new AnonymousClass0sD(supportFragmentManager);
                r1.A08(R.id.com_facebook_fragment_container, r3, FRAGMENT_TAG, 1);
                r1.A03();
                facebookDialogFragment = r3;
            }
        }
        this.singleFragment = facebookDialogFragment;
    }
}
