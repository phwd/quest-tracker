package com.oculus.panelapp.people.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.content.res.Resources;
import androidx.databinding.Bindable;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.PeopleTabletType;
import com.oculus.socialplatform.R;

public class PeopleTabletTopBarViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(PeopleTabletTopBarViewModel.class);
    public int mSearchIconVisibility;
    public String mTitleText;

    @Bindable
    public int getSearchIconVisibility() {
        return this.mSearchIconVisibility;
    }

    @Bindable
    public String getTitleText() {
        return this.mTitleText;
    }

    public PeopleTabletTopBarViewModel(PeopleTabletPanelApp peopleTabletPanelApp, Context context) {
        int i;
        int i2 = 0;
        boolean z = peopleTabletPanelApp.getPeopleTabletType() == PeopleTabletType.FACEBOOK;
        boolean z2 = DeviceConfigHelper.getBoolean(context, DeviceConfigSocialPlatformMC.AUI_PEOPLE_TAB_SHOW_FB_SEARCH);
        Resources resources = context.getResources();
        if (z) {
            i = R.string.tablet_title_fb;
        } else {
            i = R.string.tablet_title_oc;
        }
        this.mTitleText = resources.getString(i);
        if (z && !z2) {
            i2 = 8;
        }
        this.mSearchIconVisibility = i2;
    }
}
