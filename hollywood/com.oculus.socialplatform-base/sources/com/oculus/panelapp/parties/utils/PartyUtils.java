package com.oculus.panelapp.parties.utils;

import android.content.Context;
import android.content.Intent;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.panelapp.parties.PartiesTabletPanelApp;
import com.oculus.panelapp.parties.views.actions.PartyActionHandler;
import com.oculus.socialplatform.R;
import com.oculus.tablet.utils.PartiesBroadcastActions;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;

public class PartyUtils {
    public static final String TAG = LoggingUtil.tag(PartyUtils.class);

    public static void blockUser(final Context context, String str, final ClickEventButtonId clickEventButtonId, final SurfaceType surfaceType, final PartiesTabletPanelApp partiesTabletPanelApp, final PartyActionHandler partyActionHandler) {
        HorizonContentProviderHelper.blockUser(context, str, new HorizonContentProviderHelper.BlockUserCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyUtils.AnonymousClass4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                partyActionHandler.onError();
                partiesTabletPanelApp.mSocialLogger.logActionFailure(ActionId.BLOCK_USER, clickEventButtonId, surfaceType, null);
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_social_block_error", context.getResources().getString(R.string.parties_tablet_block_failed), PartyUtils.TAG);
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BlockUserCallback
            public void onSuccess(String str) {
                partiesTabletPanelApp.mSocialLogger.logActionSuccess(ActionId.BLOCK_USER, clickEventButtonId, surfaceType);
                partyActionHandler.onSuccess();
            }
        });
    }

    public static void cancelPartyInvite(final Context context, final String str, final String str2, final PartiesTabletPanelApp partiesTabletPanelApp, final ClickEventButtonId clickEventButtonId, final SurfaceType surfaceType, final PartyActionHandler partyActionHandler) {
        HorizonContentProviderHelper.cancelPartyInvite(context, str, str2, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyUtils.AnonymousClass2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                partiesTabletPanelApp.mSocialLogger.logActionFailure(ActionId.PARTY_CANCEL_INVITE, clickEventButtonId, surfaceType, (String) null, "party_id", str2);
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_social_party_cancel_invite_error", context.getResources().getString(R.string.parties_tablet_party_uninvite_failed), PartyUtils.TAG);
                partyActionHandler.onError();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                partiesTabletPanelApp.mSocialLogger.logActionSuccess(ActionId.PARTY_CANCEL_INVITE, clickEventButtonId, surfaceType, "party_id", str);
                partiesTabletPanelApp.mPartiesViewModel.removeInvitedUserOptimistically(str);
                partyActionHandler.onSuccess();
            }
        });
    }

    public static void kickUserFromParty(final Context context, final String str, final String str2, final PartiesTabletPanelApp partiesTabletPanelApp, final ClickEventButtonId clickEventButtonId, final SurfaceType surfaceType, final PartyActionHandler partyActionHandler) {
        HorizonContentProviderHelper.kickUserFromParty(context, str, str2, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyUtils.AnonymousClass3 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                partiesTabletPanelApp.mSocialLogger.logActionFailure(ActionId.PARTY_KICK_USER, clickEventButtonId, surfaceType, (String) null, "party_id", str2);
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_social_party_kick_user_error", context.getResources().getString(R.string.parties_tablet_party_kick_user_failed), PartyUtils.TAG);
                partyActionHandler.onError();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                partiesTabletPanelApp.mSocialLogger.logActionSuccess(ActionId.PARTY_KICK_USER, clickEventButtonId, surfaceType, "party_id", str);
                partiesTabletPanelApp.mPartiesViewModel.removePartyMemberOptimistically(str);
                partyActionHandler.onSuccess();
            }
        });
    }

    public static AsyncQueryHandle leaveParty(final Context context, final String str, final ClickEventButtonId clickEventButtonId, final SurfaceType surfaceType, final PartiesTabletPanelApp partiesTabletPanelApp, final PartyActionHandler partyActionHandler) {
        return HorizonContentProviderHelper.leaveParty(context, str, new HorizonContentProviderHelper.SingleIDCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyUtils.AnonymousClass5 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                partiesTabletPanelApp.mSocialLogger.logActionFailure(ActionId.PARTY_LEAVE, clickEventButtonId, surfaceType, (String) null, "party_id", str);
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_social_party_leave_error", context.getResources().getString(R.string.parties_tablet_party_leave_failed), PartyUtils.TAG);
                partyActionHandler.onError();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.SingleIDCallback
            public void onSuccess(String str) {
                partiesTabletPanelApp.mSocialLogger.logActionSuccess(ActionId.PARTY_LEAVE, clickEventButtonId, surfaceType, "party_id", str);
                context.sendBroadcast(new Intent(PartiesBroadcastActions.ACTION_REMOVE_PARTY_OPTIMISTICALLY));
                partyActionHandler.onSuccess();
            }
        });
    }

    public static void inviteUsersToParty(final Context context, PartiesTabletPanelApp partiesTabletPanelApp, List<String> list, String str, final PartyActionHandler partyActionHandler) {
        HorizonContentProviderHelper.inviteUsersToParty(context, list, str, new HorizonContentProviderHelper.MultipleIDCallback() {
            /* class com.oculus.panelapp.parties.utils.PartyUtils.AnonymousClass1 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
            public void onError() {
                Context context = context;
                PartiesTabletWarningToaster.showErrorToast(context, "oculus_mobile_social_party_invite_error", context.getResources().getString(R.string.parties_tablet_party_invite_failed), PartyUtils.TAG);
                partyActionHandler.onError();
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.MultipleIDCallback
            public void onSuccess(List<String> list) {
                partyActionHandler.onSuccess();
            }
        });
    }

    public static void navigateToLastOpenedSocialTablet(PartiesTabletPanelApp partiesTabletPanelApp) {
        partiesTabletPanelApp.actionNavigate(SystemUXRoute.AUI_SOCIAL_V2, "");
    }

    public static int getLocalUserMuteState(Context context) {
        return HorizonContentProviderHelper.getPartyLocalMute(context);
    }
}
