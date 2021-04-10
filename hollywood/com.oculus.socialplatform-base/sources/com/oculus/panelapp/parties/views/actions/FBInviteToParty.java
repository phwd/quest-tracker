package com.oculus.panelapp.parties.views.actions;

import X.AnonymousClass006;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.parties.PartyNotificationHelper;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.vrshell.SystemUXRoute;
import org.json.JSONException;
import org.json.JSONObject;

public class FBInviteToParty extends PartyAction {
    @VisibleForTesting(otherwise = 2)
    public static final String SHARE_SHEET_SOURCE = "parties_tablet";
    public static final String TAG = LoggingUtil.tag(FBInviteToParty.class);
    public ClickEventButtonId mButtonId;
    public PartiesTabletPanelApp mPanelApp;
    public SocialParty mParty;
    public PartyNotificationHelper mPartyNotificationHelper;
    public SurfaceType mSurfaceType;

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public boolean isRelevant() {
        return true;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public PartyActionType getType() {
        return PartyActionType.INVITE_TO_PARTY;
    }

    @Override // com.oculus.panelapp.parties.views.actions.PartyAction
    public void performAction(Context context, @Nullable PartyActionHandler partyActionHandler) {
        SocialParty socialParty = this.mParty;
        if (socialParty != null) {
            if (!socialParty.mHasLinkSharing) {
                this.mPartyNotificationHelper.sendLinkSharingOffToast(context, "fb_invite_to_party_link_sharing_off");
                if (partyActionHandler != null) {
                    partyActionHandler.onSuccess();
                    return;
                }
                return;
            }
            SocialLogger socialLogger = this.mPanelApp.mSocialLogger;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("deeplinkTargetId", this.mParty.mID);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("defaultDestination", "Messenger");
                Uri build = new Uri.Builder().appendQueryParameter("overrides", jSONObject2.toString()).appendQueryParameter("payload", jSONObject.toString()).appendQueryParameter("source", SHARE_SHEET_SOURCE).appendQueryParameter("type", "deeplink_target").build();
                if (socialLogger != null) {
                    socialLogger.logActionSuccess(ActionId.NAVIGATE_TO_SHARE_SHEET, this.mButtonId, this.mSurfaceType, "party_id", this.mParty.mID);
                }
                this.mPanelApp.actionNavigate(SystemUXRoute.SHARE_SHEET_V2, build.toString());
                if (partyActionHandler != null) {
                    partyActionHandler.onSuccess();
                    return;
                }
                return;
            } catch (JSONException e) {
                Log.e(TAG, AnonymousClass006.A07("Error building payload for share sheet: ", e.toString()));
                if (socialLogger != null) {
                    socialLogger.logActionFailure(ActionId.NAVIGATE_TO_SHARE_SHEET, this.mButtonId, this.mSurfaceType, e.toString(), "party_id", this.mParty.mID);
                } else {
                    return;
                }
            }
        }
        if (partyActionHandler != null) {
            partyActionHandler.onError();
        }
    }

    public FBInviteToParty(PartiesTabletPanelApp partiesTabletPanelApp, SocialParty socialParty, ClickEventButtonId clickEventButtonId, SurfaceType surfaceType, PartyNotificationHelper partyNotificationHelper) {
        this.mPanelApp = partiesTabletPanelApp;
        this.mParty = socialParty;
        this.mButtonId = clickEventButtonId;
        this.mSurfaceType = surfaceType;
        this.mPartyNotificationHelper = partyNotificationHelper;
    }
}
