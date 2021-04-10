package com.oculus.os.vrlockscreen;

import android.app.ActivityManager;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.oculus.os.vrlockscreen.SetNewLockPatternView;
import oculus.internal.LockPatternUtilsCompat;

public class SetNewLockPatternFragment extends Fragment {
    static final String OLD_PATTERN = "OLD_PATTERN";
    private ImageButton mBackButton;
    private String mCachedPattern;
    private Button mClearPatternButton;
    private String mLockPattern;
    private SetNewLockPatternView mSetNewLockPatternView;
    private Button mSetPatternButton;
    private Button mSkipButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_lock_pattern, container, false);
        this.mSetNewLockPatternView = (SetNewLockPatternView) view.findViewById(R.id.set_new_lock_pattern_view);
        this.mSetNewLockPatternView.setCallback(new SetNewLockPatternView.Callback() {
            /* class com.oculus.os.vrlockscreen.$$Lambda$SetNewLockPatternFragment$q3rwIQ1ZpaFqClOJdKe5XopWkFg */

            @Override // com.oculus.os.vrlockscreen.SetNewLockPatternView.Callback
            public final void onValidPatternDetected(String str) {
                SetNewLockPatternFragment.this.lambda$onCreateView$0$SetNewLockPatternFragment(str);
            }
        });
        this.mSetNewLockPatternView.disableClearingHelpOnPatternDetected();
        this.mClearPatternButton = (Button) view.findViewById(R.id.clear_pattern_btn);
        this.mClearPatternButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.os.vrlockscreen.$$Lambda$SetNewLockPatternFragment$yzTfXwLb3I0LOni43OoppYDExo */

            public final void onClick(View view) {
                SetNewLockPatternFragment.this.lambda$onCreateView$1$SetNewLockPatternFragment(view);
            }
        });
        this.mSetPatternButton = (Button) view.findViewById(R.id.set_pattern_btn);
        this.mSetPatternButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.os.vrlockscreen.$$Lambda$SetNewLockPatternFragment$5NtiI1j7BsTF1CXTbkBWgbKLhYc */

            public final void onClick(View view) {
                SetNewLockPatternFragment.this.lambda$onCreateView$2$SetNewLockPatternFragment(view);
            }
        });
        this.mSkipButton = (Button) view.findViewById(R.id.skip_button);
        this.mSkipButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.os.vrlockscreen.$$Lambda$SetNewLockPatternFragment$rHfeJMYp8CgNiVI1RubcaCQhw */

            public final void onClick(View view) {
                SetNewLockPatternFragment.this.lambda$onCreateView$3$SetNewLockPatternFragment(view);
            }
        });
        this.mBackButton = (ImageButton) view.findViewById(R.id.back_btn);
        this.mBackButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.os.vrlockscreen.$$Lambda$SetNewLockPatternFragment$Ifp9PN7JHyUWPgtFoA9V1XvYI3k */

            public final void onClick(View view) {
                SetNewLockPatternFragment.this.lambda$onCreateView$4$SetNewLockPatternFragment(view);
            }
        });
        resetView();
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$SetNewLockPatternFragment(String lockPattern) {
        this.mCachedPattern = lockPattern;
        setButtonsEnabled(true);
    }

    public /* synthetic */ void lambda$onCreateView$1$SetNewLockPatternFragment(View v) {
        resetView();
    }

    public /* synthetic */ void lambda$onCreateView$2$SetNewLockPatternFragment(View v) {
        onSetPatternClicked();
    }

    public /* synthetic */ void lambda$onCreateView$3$SetNewLockPatternFragment(View v) {
        endFlow();
    }

    public /* synthetic */ void lambda$onCreateView$4$SetNewLockPatternFragment(View v) {
        getActivity().onBackPressed();
    }

    private void resetView() {
        String help = "";
        Bundle args = getArguments();
        String flow = args != null ? args.getString(LockscreenActivity.FLOW_PARAM) : "";
        if (LockscreenActivity.FLOW_ADD_USER.equals(flow)) {
            this.mSkipButton.setText(R.string.skip);
            help = getString(R.string.lockscreen_secure_owner_account);
        } else if (LockscreenActivity.FLOW_SECONDARY_USER_NUX.equals(flow)) {
            this.mSkipButton.setText(R.string.skip);
            help = getString(R.string.lockscreen_secure_secondary_account);
        } else if (LockscreenActivity.FLOW_SET_PATTERN.equals(flow)) {
            this.mSkipButton.setText(R.string.cancel);
            help = getString(R.string.lockscreen_set_pattern_hint);
        }
        boolean patternEmpty = TextUtils.isEmpty(this.mLockPattern);
        this.mSetNewLockPatternView.setHelpText(help);
        this.mSetNewLockPatternView.reset();
        this.mSetNewLockPatternView.setTitle(patternEmpty ? R.string.lockscreen_set_pattern : R.string.lockscreen_confirm_lock);
        this.mSetPatternButton.setText(patternEmpty ? R.string.next : R.string.confirm);
        this.mBackButton.setVisibility(patternEmpty ? 8 : 0);
        setButtonsEnabled(false);
    }

    private void onSetPatternClicked() {
        if (TextUtils.isEmpty(this.mLockPattern)) {
            this.mLockPattern = this.mCachedPattern;
            resetView();
        } else if (this.mLockPattern.equals(this.mCachedPattern)) {
            setLockPattern();
            endFlow();
        } else {
            this.mCachedPattern = null;
            this.mLockPattern = null;
            resetView();
            this.mSetNewLockPatternView.setHelpText(R.string.lockscreen_patterm_mismatch);
        }
    }

    private void setLockPattern() {
        LockPatternUtilsCompat lockPatternUtils = new LockPatternUtilsCompat(getContext());
        Bundle bundle = getArguments();
        lockPatternUtils.saveLockPattern(LockPatternUtilsCompat.stringToPattern(this.mLockPattern), bundle != null ? bundle.getString(OLD_PATTERN) : null, ActivityManager.getCurrentUser());
        lockPatternUtils.setVisiblePatternEnabled(true, ActivityManager.getCurrentUser());
    }

    private void setButtonsEnabled(boolean enabled) {
        this.mClearPatternButton.setEnabled(enabled);
        this.mSetPatternButton.setEnabled(enabled);
    }

    public boolean onBackPressed() {
        if (this.mCachedPattern == null) {
            return false;
        }
        this.mCachedPattern = null;
        this.mLockPattern = null;
        resetView();
        return true;
    }

    private void endFlow() {
        checkAndMaybeLaunchReturnUri();
        getActivity().finish();
    }

    private void checkAndMaybeLaunchReturnUri() {
        Bundle args = getArguments();
        if (args != null) {
            String returnUri = args.getString(LockscreenActivity.RETURN_URI_PARAM);
            if (!TextUtils.isEmpty(returnUri)) {
                Log.d("LockscreenApp", "Launching return URI: " + returnUri);
                Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
                intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.ShellControlBroadcastReceiver"));
                intent.setData(Uri.parse(returnUri));
                getContext().sendBroadcastAsUser(intent, UserHandle.CURRENT);
            }
        }
    }
}
