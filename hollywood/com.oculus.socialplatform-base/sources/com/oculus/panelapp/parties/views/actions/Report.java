package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.modules.AbuseReportModule;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.views.PartyUserCardAdapterItem;
import com.oculus.vrshell.SystemUXRoute;

public class Report extends PartyAction {
    public static final String TAG = LoggingUtil.tag(Report.class);
    public PartiesTabletPanelApp mPanelApp;
    public SocialUser mUser;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        return PartyActionType.REPORT;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        SocialUser.UserRowType userRowType;
        SocialUser socialUser = this.mUser;
        if (socialUser == null || (userRowType = socialUser.mUserType) == SocialUser.UserRowType.PARTY_CONTROLS || userRowType == SocialUser.UserRowType.BLOCKED_USER || userRowType == SocialUser.UserRowType.BLOCKED_INVITED) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, PartyActionHandler partyActionHandler) {
        if (this.mUser != null) {
            this.mPanelApp.actionNavigate(SystemUXRoute.BLOCKANDREPORT, new Uri.Builder().encodedPath("com.oculus.vrshell").appendQueryParameter(AbuseReportModule.KEY_SDK_USER_ID, this.mUser.mID).build().toString());
        }
    }

    public Report(@NonNull PartyUserCardAdapterItem partyUserCardAdapterItem, PartiesTabletPanelApp partiesTabletPanelApp) {
        this.mUser = partyUserCardAdapterItem.mUser;
        this.mPanelApp = partiesTabletPanelApp;
    }
}
