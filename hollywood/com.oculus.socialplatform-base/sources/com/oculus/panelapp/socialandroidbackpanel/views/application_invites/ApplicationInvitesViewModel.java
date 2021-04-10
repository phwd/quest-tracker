package com.oculus.panelapp.socialandroidbackpanel.views.application_invites;

import X.AnonymousClass1uc;
import android.os.Handler;
import android.os.Looper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.tablet.view.ViewModelLifecycle;

public class ApplicationInvitesViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final String TAG = LoggingUtil.tag(ApplicationInvitesViewModel.class);
    public String mSource;
    public final Handler mainHandler = new Handler(Looper.getMainLooper());

    @FunctionalInterface
    public interface Callback {
        void execute();
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    public String getSource() {
        return this.mSource;
    }

    public void setSource(String str) {
        this.mSource = str;
    }
}
