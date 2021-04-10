package com.oculus.panelapp.parties.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.library.model.App;
import com.oculus.library.model.AppStatus;
import com.oculus.vrshell.util.HorizonUtil;

public class PartyTravelStateUtils {

    public enum PartyTravelState {
        UNKNOWN,
        PARTY_NULL,
        LEADER_NO_DESTINATION_OR_ACTIVITY_SELECTED,
        LEADER_ACTIVITY_SELECTED,
        LEADER_DESTINATION_SELECTED_ALONE_IN_PARTY,
        LEADER_DESTINATION_SELECTED_NO_OTHERS_ACCEPTED,
        LEADER_DESTINATION_SELECTED_SOME_OTHERS_ACCEPTED,
        LEADER_DESTINATION_SELECTED_MORE_THAN_MAX_OTHERS_ACCEPTED,
        NON_LEADER_NO_DESTINATION_OR_ACTIVITY_SELECTED,
        NON_LEADER_ACTIVITY_SELECTED,
        NON_LEADER_DESTINATION_SELECTED_RESPONSE_PENDING,
        NON_LEADER_DESTINATION_SELECTED_CAPACITY_REACHED,
        NON_LEADER_DESTINATION_SELECTED_RESPONSE_ACCEPTED,
        ASYNC_LEADER_DESTINATION_SELECTED,
        ASYNC_LEADER_DESTINATION_SELECTED_MORE_THAN_MAX_OTHERS_JOINED,
        ASYNC_NON_LEADER_DESTINATION_SELECTED_CAPACITY_REACHED,
        ASYNC_NON_LEADER_DESTINATION_SELECTED
    }

    public static boolean partyHasUninstalledAppSelected(SocialParty socialParty, @NonNull Context context) {
        String str;
        if (socialParty != null) {
            if (TextUtils.isEmpty(socialParty.mSocialActivityId) || TextUtils.isEmpty(socialParty.mSocialActivityApplicationId)) {
                SocialGroupLaunchAppDestination socialGroupLaunchAppDestination = socialParty.mSocialProposedGroupLaunchAppDestination;
                if (socialGroupLaunchAppDestination != null && !TextUtils.isEmpty(socialGroupLaunchAppDestination.mAppID)) {
                    str = socialParty.mSocialProposedGroupLaunchAppDestination.mAppID;
                }
            } else {
                str = socialParty.mSocialActivityApplicationId;
            }
            return !isAppInstalled(context, str);
        }
        return false;
    }

    public static PartyTravelState getAsyncFlowOnlyPartyTravelState(@Nullable SocialParty socialParty, @NonNull String str, @Nullable SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        if (str.equals(socialParty.mPartyLeader.mID)) {
            if (socialGroupLaunchAppDestination == null) {
                String str2 = socialParty.mSocialActivityId;
                if (str2 == null || str2.isEmpty()) {
                    return PartyTravelState.LEADER_NO_DESTINATION_OR_ACTIVITY_SELECTED;
                }
                return PartyTravelState.LEADER_ACTIVITY_SELECTED;
            } else if (socialParty.getSize() == 1) {
                return PartyTravelState.LEADER_DESTINATION_SELECTED_ALONE_IN_PARTY;
            } else {
                if (socialGroupLaunchAppDestination.mMaxCapacity <= socialParty.getSize()) {
                    return PartyTravelState.ASYNC_LEADER_DESTINATION_SELECTED_MORE_THAN_MAX_OTHERS_JOINED;
                }
                return PartyTravelState.ASYNC_LEADER_DESTINATION_SELECTED;
            }
        } else if (socialGroupLaunchAppDestination == null) {
            String str3 = socialParty.mSocialActivityId;
            if (str3 == null || str3.isEmpty()) {
                return PartyTravelState.NON_LEADER_NO_DESTINATION_OR_ACTIVITY_SELECTED;
            }
            return PartyTravelState.NON_LEADER_ACTIVITY_SELECTED;
        } else if (socialGroupLaunchAppDestination.mMaxCapacity <= socialParty.getSize()) {
            return PartyTravelState.ASYNC_NON_LEADER_DESTINATION_SELECTED_CAPACITY_REACHED;
        } else {
            return PartyTravelState.ASYNC_NON_LEADER_DESTINATION_SELECTED;
        }
    }

    public static PartyTravelState getPartyTravelState(@Nullable SocialParty socialParty, @NonNull String str) {
        if (socialParty == null) {
            return PartyTravelState.PARTY_NULL;
        }
        return getAsyncFlowOnlyPartyTravelState(socialParty, str, socialParty.mSocialProposedGroupLaunchAppDestination);
    }

    public static PartyTravelState getSyncAsyncFlowsPartyTravelState(@Nullable SocialParty socialParty, @NonNull String str, @Nullable SocialGroupLaunchAppDestination socialGroupLaunchAppDestination) {
        if (str.equals(socialParty.mPartyLeader.mID)) {
            if (socialGroupLaunchAppDestination == null) {
                String str2 = socialParty.mSocialActivityId;
                if (str2 == null || str2.isEmpty()) {
                    return PartyTravelState.LEADER_NO_DESTINATION_OR_ACTIVITY_SELECTED;
                }
                return PartyTravelState.LEADER_ACTIVITY_SELECTED;
            } else if (socialParty.getSize() == 1) {
                return PartyTravelState.LEADER_DESTINATION_SELECTED_ALONE_IN_PARTY;
            } else {
                if (socialGroupLaunchAppDestination.getNumAcceptedUsers() == 1) {
                    return PartyTravelState.LEADER_DESTINATION_SELECTED_NO_OTHERS_ACCEPTED;
                }
                if (socialGroupLaunchAppDestination.mMaxCapacity < socialGroupLaunchAppDestination.getNumAcceptedUsers()) {
                    return PartyTravelState.LEADER_DESTINATION_SELECTED_MORE_THAN_MAX_OTHERS_ACCEPTED;
                }
                return PartyTravelState.LEADER_DESTINATION_SELECTED_SOME_OTHERS_ACCEPTED;
            }
        } else if (socialGroupLaunchAppDestination == null) {
            String str3 = socialParty.mSocialActivityId;
            if (str3 == null || str3.isEmpty()) {
                return PartyTravelState.NON_LEADER_NO_DESTINATION_OR_ACTIVITY_SELECTED;
            }
            return PartyTravelState.NON_LEADER_ACTIVITY_SELECTED;
        } else if (socialGroupLaunchAppDestination.getUserResponse(str) == SocialGroupLaunchResponse.PENDING_RESPONSE) {
            if (socialGroupLaunchAppDestination.mMaxCapacity <= socialGroupLaunchAppDestination.getNumAcceptedUsers()) {
                return PartyTravelState.NON_LEADER_DESTINATION_SELECTED_CAPACITY_REACHED;
            }
            return PartyTravelState.NON_LEADER_DESTINATION_SELECTED_RESPONSE_PENDING;
        } else if (socialGroupLaunchAppDestination.getUserResponse(str) == SocialGroupLaunchResponse.ACCEPT) {
            return PartyTravelState.NON_LEADER_DESTINATION_SELECTED_RESPONSE_ACCEPTED;
        } else {
            return PartyTravelState.UNKNOWN;
        }
    }

    public static boolean isAppInstalled(@NonNull Context context, String str) {
        for (App app : HorizonUtil.getApplications(context)) {
            if (app.id.equals(str) && app.status.equals(AppStatus.INSTALLED)) {
                return true;
            }
        }
        return false;
    }
}
