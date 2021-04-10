package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1uc;
import android.content.res.Resources;
import androidx.databinding.Bindable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.socialplatform.R;
import com.oculus.tablet.view.ViewModelLifecycle;
import java.util.List;

public class GroupThreadBlockedViewModel extends AnonymousClass1uc implements ViewModelLifecycle {
    public static final String TAG = LoggingUtil.tag(GroupThreadBlockedViewModel.class);
    public MessengerAPIType mApiType;
    public List<MessengerParticipant> mBlockedParticipants;
    public final Resources mResources;

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
    }

    @Bindable
    public String getGroupThreadBlockedViewBodyText() {
        int i;
        int i2;
        int i3;
        int i4;
        MessengerAPIType messengerAPIType = this.mApiType;
        if (messengerAPIType == MessengerAPIType.OC_CHATS) {
            i = R.string.anytime_tablet_oc_group_thread_containing_one_blocked_body;
            i2 = R.string.anytime_tablet_oc_group_thread_containing_two_blocked_body;
            i3 = R.string.anytime_tablet_oc_group_thread_containing_three_blocked_body;
            i4 = R.string.anytime_tablet_oc_group_thread_containing_more_than_three_blocked_body;
        } else if (messengerAPIType != MessengerAPIType.FB_MSYS) {
            return "";
        } else {
            i = R.string.anytime_tablet_messenger_group_thread_containing_one_blocked_body;
            i2 = R.string.anytime_tablet_messenger_group_thread_containing_two_blocked_body;
            i3 = R.string.anytime_tablet_messenger_group_thread_containing_three_blocked_body;
            i4 = R.string.anytime_tablet_messenger_group_thread_containing_more_than_three_blocked_body;
        }
        return getGroupThreadBodyText(i, i2, i3, i4);
    }

    @Bindable
    public String getGroupThreadBlockedViewManageBlockButtonText() {
        Resources resources;
        int i;
        if (this.mBlockedParticipants.size() == 1) {
            resources = this.mResources;
            i = R.string.anytime_tablet_messenger_group_thread_containing_one_blocked_manage_block_btn;
        } else {
            resources = this.mResources;
            i = R.string.anytime_tablet_messenger_group_thread_containing_multiple_blocked_manage_block_btn;
        }
        return resources.getString(i);
    }

    @Bindable
    public int getGroupThreadBlockedViewManageBlockButtonVisibility() {
        if (this.mApiType == MessengerAPIType.FB_MSYS || this.mBlockedParticipants.size() == 1) {
            return 0;
        }
        return 8;
    }

    @Bindable
    public String getGroupThreadBlockedViewTitleText() {
        if (this.mApiType == MessengerAPIType.OC_CHATS) {
            return this.mResources.getString(R.string.anytime_tablet_oc_group_thread_containing_blocked_title);
        }
        if (this.mBlockedParticipants.size() != 1) {
            return this.mResources.getQuantityString(R.plurals.number_of_people_blocked_in_group, this.mBlockedParticipants.size(), Integer.valueOf(this.mBlockedParticipants.size()));
        }
        return this.mResources.getString(R.string.anytime_tablet_messenger_group_thread_containing_one_blocked_title, this.mBlockedParticipants.get(0).getName());
    }

    public String getGroupThreadBodyText(int i, int i2, int i3, int i4) {
        if (this.mBlockedParticipants.size() == 1) {
            String name = this.mBlockedParticipants.get(0).getName();
            return this.mResources.getString(i, name, name);
        } else if (this.mBlockedParticipants.size() == 2) {
            return this.mResources.getString(i2, this.mBlockedParticipants.get(0).getName(), this.mBlockedParticipants.get(1).getName());
        } else {
            if (this.mBlockedParticipants.size() == 3) {
                return this.mResources.getString(i3, this.mBlockedParticipants.get(0).getName(), this.mBlockedParticipants.get(1).getName(), this.mBlockedParticipants.get(2).getName());
            }
            if (this.mBlockedParticipants.size() > 3) {
                return this.mResources.getString(i4, this.mBlockedParticipants.get(0).getName(), this.mBlockedParticipants.get(1).getName(), this.mResources.getQuantityString(R.plurals.number_of_other_people_blocked_in_group, this.mBlockedParticipants.size() - 2, Integer.valueOf(this.mBlockedParticipants.size() - 2)));
            }
            return "";
        }
    }

    public void setApiType(MessengerAPIType messengerAPIType) {
        this.mApiType = messengerAPIType;
        notifyPropertyChanged(148);
        notifyPropertyChanged(115);
        notifyPropertyChanged(143);
    }

    public void setBlockedParticipants(List<MessengerParticipant> list) {
        this.mBlockedParticipants = list;
        notifyPropertyChanged(113);
        notifyPropertyChanged(143);
        notifyPropertyChanged(115);
        notifyPropertyChanged(148);
    }

    public GroupThreadBlockedViewModel(Resources resources) {
        this.mResources = resources;
    }
}
