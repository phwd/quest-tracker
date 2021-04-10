package com.oculus.os.vrlockscreen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ForgotPatternInstructionsFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forgot_pattern_instructions, container, false);
        view.findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.os.vrlockscreen.$$Lambda$ForgotPatternInstructionsFragment$Y_GYiVhjUmIBjOeqV01LaFnWIRY */

            public final void onClick(View view) {
                ForgotPatternInstructionsFragment.this.lambda$onCreateView$0$ForgotPatternInstructionsFragment(view);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$ForgotPatternInstructionsFragment(View v) {
        getFragmentManager().popBackStack("VerifyLockPatternFragment", 1);
    }
}
