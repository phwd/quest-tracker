package com.oculus.panelapp.people.views;

import X.AnonymousClass1uc;
import com.oculus.common.logutilities.LoggingUtil;

public class PeopleSearchViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(PeopleSearchViewModel.class);
    public boolean mIsLoading = false;

    public boolean getLoading() {
        return this.mIsLoading;
    }

    public void setLoading(boolean z) {
        this.mIsLoading = z;
    }
}
