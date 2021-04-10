package com.oculus.panelapp.people.views;

import X.AnonymousClass1uc;
import com.oculus.common.logutilities.LoggingUtil;

public class PeopleViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(PeopleViewModel.class);
    public boolean mIsLoading = false;
    public boolean mShowSearchTopBar = false;

    public boolean getLoading() {
        return this.mIsLoading;
    }

    public void setLoading(boolean z) {
        this.mIsLoading = z;
    }
}
