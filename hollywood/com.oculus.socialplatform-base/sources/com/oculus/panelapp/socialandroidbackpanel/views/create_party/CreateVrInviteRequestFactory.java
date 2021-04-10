package com.oculus.panelapp.socialandroidbackpanel.views.create_party;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import java.util.Map;

public class CreateVrInviteRequestFactory {
    public final Context mContext;

    public AsyncQueryHandle createOrUpdateCustomParty(@Nullable Map<String, String> map, HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        return HorizonContentProviderHelper.createOrUpdateCustomParty(this.mContext, map, singleIDCallback);
    }

    public AsyncQueryHandle fetchGroupLaunchAppDestinations(String str, @Nullable String str2, @Nullable Integer num, HorizonContentProviderHelper.FetchGroupLaunchAppDestinationsCallback fetchGroupLaunchAppDestinationsCallback) {
        return HorizonContentProviderHelper.fetchGroupLaunchAppDestinations(this.mContext, str, str2, num, fetchGroupLaunchAppDestinationsCallback);
    }

    public AsyncQueryHandle fetchGroupLaunchSupportedApps(HorizonContentProviderHelper.FetchGroupLaunchSupportedApplicationsCallback fetchGroupLaunchSupportedApplicationsCallback) {
        return HorizonContentProviderHelper.fetchGroupLaunchSupportedApps(this.mContext, fetchGroupLaunchSupportedApplicationsCallback);
    }

    public AsyncQueryHandle setNuxFlagForUser(String str, boolean z, HorizonContentProviderHelper.SetNuxFlagForUserCallback setNuxFlagForUserCallback) {
        return HorizonContentProviderHelper.setNuxFlagForUser(this.mContext, str, z, setNuxFlagForUserCallback);
    }

    public AsyncQueryHandle setPartyGroupLaunchDestination(String str, String str2, HorizonContentProviderHelper.SingleIDCallback singleIDCallback) {
        return HorizonContentProviderHelper.setPartyGroupLaunchDestination(this.mContext, str, str2, singleIDCallback);
    }

    public CreateVrInviteRequestFactory(Context context) {
        this.mContext = context;
    }
}
