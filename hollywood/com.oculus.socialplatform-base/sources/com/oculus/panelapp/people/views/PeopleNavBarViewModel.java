package com.oculus.panelapp.people.views;

import X.AnonymousClass1uc;
import android.content.Context;
import android.content.res.Resources;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.people.PeopleTabletPanelApp;
import com.oculus.panelapp.people.PeopleTabletType;
import com.oculus.socialplatform.R;

public class PeopleNavBarViewModel extends AnonymousClass1uc {
    public static final String TAG = LoggingUtil.tag(PeopleNavBarViewModel.class);
    public String friendButtonText;

    @Bindable
    public String getFriendsButtonText() {
        return this.friendButtonText;
    }

    public PeopleNavBarViewModel(PeopleTabletPanelApp peopleTabletPanelApp, Context context) {
        Resources resources;
        int i;
        if (peopleTabletPanelApp.getPeopleTabletType() == PeopleTabletType.FACEBOOK) {
            resources = context.getResources();
            i = R.string.people_tablet_fb_friends_button_title;
        } else {
            resources = context.getResources();
            i = R.string.people_tablet_oc_friends_button_title;
        }
        this.friendButtonText = resources.getString(i);
    }
}
