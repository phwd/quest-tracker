package com.oculus.os.vrlockscreen;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import com.android.internal.widget.LockPatternUtils;
import com.oculus.os.SettingsManager;

public class LockscreenActivity extends Activity {
    public static final String FLOW_ADD_USER = "add_user";
    public static final String FLOW_CLEAR_PATTERN = "clear_pattern";
    public static final String FLOW_PARAM = "flow_param";
    public static final String FLOW_SECONDARY_USER_NUX = "secondary_user_nux";
    public static final String FLOW_SET_PATTERN = "set_pattern";
    public static final String RETURN_URI_PARAM = "return_uri_param";
    private SettingsManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.lockscreen_window_background);
        setContentView(R.layout.lock_screen);
        this.mManager = new SettingsManager(getApplicationContext());
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        Fragment fragment;
        super.onStart();
        Intent i = getIntent();
        if (i != null) {
            if (new LockPatternUtils(this).isLockPatternEnabled(ActivityManager.getCurrentUser())) {
                fragment = new VerifyLockPatternFragment();
            } else {
                fragment = new SetNewLockPatternFragment();
            }
            Bundle bundle = new Bundle(2);
            bundle.putString(FLOW_PARAM, getExtraString(i, "flow"));
            bundle.putString(RETURN_URI_PARAM, getExtraString(i, "return_uri"));
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }

    private String getExtraString(Intent i, String key) {
        String value = i.getStringExtra(key);
        if (value == null) {
            return "";
        }
        return value;
    }

    private boolean isNuxComplete() {
        return this.mManager.getBoolean("first_time_nux_complete", false, ActivityManager.getCurrentUser());
    }

    public void onBackPressed() {
        Fragment f = getFragmentManager().findFragmentById(R.id.fragment_container);
        if ((f == null || !(f instanceof SetNewLockPatternFragment) || !((SetNewLockPatternFragment) f).onBackPressed()) && isNuxComplete()) {
            super.onBackPressed();
        }
    }
}
