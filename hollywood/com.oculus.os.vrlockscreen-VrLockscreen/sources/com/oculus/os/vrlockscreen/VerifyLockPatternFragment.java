package com.oculus.os.vrlockscreen;

import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import oculus.internal.LockPatternUtilsCompat;
import oculus.internal.widget.VerifyLockPatternView;

public class VerifyLockPatternFragment extends Fragment {
    static final String TAG = "VerifyLockPatternFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.verify_lock_pattern, container, false);
        Bundle args = getArguments();
        final String flow = args != null ? args.getString(LockscreenActivity.FLOW_PARAM) : "";
        VerifyNewLockPatternView verifyLockPatternView = (VerifyNewLockPatternView) view.findViewById(R.id.verify_lock_pattern_view);
        verifyLockPatternView.setCallback(new VerifyLockPatternView.Callback() {
            /* class com.oculus.os.vrlockscreen.VerifyLockPatternFragment.AnonymousClass1 */

            public void onCorrectPattern(String pattern) {
                if (LockscreenActivity.FLOW_CLEAR_PATTERN.equals(flow)) {
                    int userId = ActivityManager.getCurrentUser();
                    LockPatternUtilsCompat lockPatternUtilsCompat = new LockPatternUtilsCompat(VerifyLockPatternFragment.this.getContext());
                    lockPatternUtilsCompat.clearPattern(userId, pattern);
                    lockPatternUtilsCompat.setLockScreenDisabled(true, userId);
                    VerifyLockPatternFragment.this.getActivity().finish();
                    return;
                }
                Fragment fragment = new SetNewLockPatternFragment();
                Bundle bundle = new Bundle(2);
                bundle.putString("OLD_PATTERN", pattern);
                bundle.putString(LockscreenActivity.FLOW_PARAM, flow);
                fragment.setArguments(bundle);
                VerifyLockPatternFragment.this.loadFragment(fragment, null);
            }

            public void onIncorrectPattern() {
            }
        });
        Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        Button forgotButton = (Button) view.findViewById(R.id.forgot_pattern_btn);
        if (LockscreenActivity.FLOW_CLEAR_PATTERN.equals(flow)) {
            verifyLockPatternView.setTitle(R.string.lockscreen_remove_lock);
            verifyLockPatternView.setHelpText(R.string.lockscreen_confirm_for_removal);
            forgotButton.setVisibility(8);
            cancelButton.setVisibility(0);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.os.vrlockscreen.$$Lambda$VerifyLockPatternFragment$fCA18JrVnTtFrSoCWnRO2Y9IIHs */

                public final void onClick(View view) {
                    VerifyLockPatternFragment.this.lambda$onCreateView$0$VerifyLockPatternFragment(view);
                }
            });
        } else {
            verifyLockPatternView.setHelpText(R.string.lockscreen_confirm_help);
            cancelButton.setVisibility(8);
            forgotButton.setVisibility(0);
            forgotButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.os.vrlockscreen.$$Lambda$VerifyLockPatternFragment$7FXBRHiYoPUHdFsaoOkJKZpaufk */

                public final void onClick(View view) {
                    VerifyLockPatternFragment.this.lambda$onCreateView$1$VerifyLockPatternFragment(view);
                }
            });
        }
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$VerifyLockPatternFragment(View v) {
        getActivity().finish();
    }

    public /* synthetic */ void lambda$onCreateView$1$VerifyLockPatternFragment(View v) {
        loadFragment(new ForgotPatternInstructionsFragment(), TAG);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void loadFragment(Fragment fragment, String name) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (name != null) {
            fragmentTransaction.addToBackStack(name);
        }
        fragmentTransaction.commit();
    }
}
