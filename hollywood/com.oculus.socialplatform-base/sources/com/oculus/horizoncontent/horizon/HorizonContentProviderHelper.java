package com.oculus.horizoncontent.horizon;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.apps.models.AppScoreboardsInfo;
import com.oculus.horizoncontent.apps.models.PartyInviteInfo;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.profile.FbFriendPrimaryProfile;
import com.oculus.horizoncontent.profile.SelfVRProfileContent;
import com.oculus.horizoncontent.profile.VRProfileContent;
import com.oculus.horizoncontent.social.PartyMicrophoneInputLocation;
import com.oculus.horizoncontent.social.PartyMicrophoneState;
import com.oculus.horizoncontent.social.RosterSize;
import com.oculus.horizoncontent.social.SocialDeeplinkPresence;
import com.oculus.horizoncontent.social.SocialGroupLaunchApp;
import com.oculus.horizoncontent.social.SocialGroupLaunchAppDestination;
import com.oculus.horizoncontent.social.SocialGroupLaunchResponse;
import com.oculus.horizoncontent.social.SocialGroupLaunchState;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialPartyPrivacyInfo;
import com.oculus.horizoncontent.social.SocialPartyType;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.horizoncontent.social.SocialUserFriendshipStatus;
import com.oculus.horizoncontent.social.SocialUserPresenceStatus;
import com.oculus.horizoncontent.social.SocialViewerInfo;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.horizoncontent.utils.AsyncQueryHandle;
import com.oculus.horizoncontent.utils.AsyncQueryHandlerBase;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HorizonContentProviderHelper {
    public static final int COLUMN_DOES_NOT_EXIST = -1;
    public static final String ID_LIST_DELIMITER = ":";
    public static final String STRING_LIST_DELIMITER = ",";
    public static final String TAG = LoggingUtil.tag(HorizonContentProviderHelper.class);

    public static abstract class AcceptFriendRequestCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class BaseCallback {
        public abstract void onError();
    }

    public static abstract class BlockUserCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class CancelFriendRequestCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static final class CreatePartyAsyncQueryHandlerDEPRECATED extends AsyncQueryHandler {
        public final SingleIDCallback mCallback;

        /* JADX WARNING: Can't wrap try/catch for region: R(2:9|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
            r2.mCallback.onError();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
            if (r5 != null) goto L_0x0034;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
            r5.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
            throw r0;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0026 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onQueryComplete(int r3, java.lang.Object r4, android.database.Cursor r5) {
            /*
                r2 = this;
                if (r5 == 0) goto L_0x0020
                boolean r0 = r5.moveToNext()     // Catch:{ Exception -> 0x0026 }
                if (r0 == 0) goto L_0x0020
                java.lang.String r0 = "current_party_id"
                int r0 = r5.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x0026 }
                java.lang.String r1 = r5.getString(r0)     // Catch:{ Exception -> 0x0026 }
                if (r1 != 0) goto L_0x001a
                com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$SingleIDCallback r0 = r2.mCallback     // Catch:{ Exception -> 0x0026 }
                r0.onError()     // Catch:{ Exception -> 0x0026 }
                goto L_0x002d
            L_0x001a:
                com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$SingleIDCallback r0 = r2.mCallback     // Catch:{ Exception -> 0x0026 }
                r0.onSuccess(r1)     // Catch:{ Exception -> 0x0026 }
                goto L_0x002d
            L_0x0020:
                com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$SingleIDCallback r0 = r2.mCallback     // Catch:{ Exception -> 0x0026 }
                r0.onError()     // Catch:{ Exception -> 0x0026 }
                goto L_0x002b
            L_0x0026:
                com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$SingleIDCallback r0 = r2.mCallback     // Catch:{ all -> 0x0031 }
                r0.onError()     // Catch:{ all -> 0x0031 }
            L_0x002b:
                if (r5 == 0) goto L_0x0030
            L_0x002d:
                r5.close()
            L_0x0030:
                return
            L_0x0031:
                r0 = move-exception
                if (r5 == 0) goto L_0x0037
                r5.close()
            L_0x0037:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.CreatePartyAsyncQueryHandlerDEPRECATED.onQueryComplete(int, java.lang.Object, android.database.Cursor):void");
        }

        public CreatePartyAsyncQueryHandlerDEPRECATED(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver);
            this.mCallback = singleIDCallback;
        }
    }

    public static abstract class FetchAppsScoreboardsInfoCallback extends BaseCallback {
        public abstract void onSuccess(List<AppScoreboardsInfo> list);
    }

    public static abstract class FetchCurrentPartyCallBack extends BaseCallback {
        public abstract void onSuccess(SocialParty socialParty);
    }

    public static abstract class FetchFriendRequestsCallback extends BaseCallback {
        public abstract void onSuccess(List<SocialUser> list);
    }

    public static abstract class FetchGroupLaunchAppDestinationsCallback extends BaseCallback {
        public abstract void onSuccess(List<SocialGroupLaunchAppDestination> list);
    }

    public static abstract class FetchGroupLaunchSupportedApplicationsCallback extends BaseCallback {
        public abstract void onSuccess(List<SocialGroupLaunchApp> list);
    }

    public static abstract class FetchInvitableFriendsCallback extends BaseCallback {
        public abstract void onSuccess(ArrayList<FriendListRowData> arrayList, String str);
    }

    public static abstract class FetchJoinablePartiesCallback extends BaseCallback {
        public abstract void onSuccess(List<SocialParty> list);
    }

    public static abstract class FetchLinkedAccountsInfoForViewerCallback extends BaseCallback {
        public abstract void onSuccess(LinkedAccountsInfo linkedAccountsInfo);
    }

    public static abstract class FetchPYMKsCallback extends BaseCallback {
        public abstract void onSuccess(ArrayList<SocialUser> arrayList);
    }

    public static abstract class FetchParticipantSpeakerStatusCallback extends BaseCallback {
        public abstract void onSuccess(boolean z);
    }

    public static abstract class FetchPartyInfoWithActivityCallback extends BaseCallback {
        public abstract void onSuccess(PartyInfo partyInfo);
    }

    public static abstract class FetchPartyInviteInfoCallback extends BaseCallback {
        public abstract void onSuccess(PartyInviteInfo partyInviteInfo);
    }

    public static abstract class FetchPartyInvitesCallBack extends BaseCallback {
        public abstract void onSuccess(List<SocialParty> list);
    }

    public static abstract class FetchPartyPrivacyInfoCallback extends BaseCallback {
        public abstract void onSuccess(SocialPartyPrivacyInfo socialPartyPrivacyInfo);
    }

    public static abstract class FetchPeopleNearbyCallback extends BaseCallback {
        public abstract void onSuccess(ArrayList<SocialUser> arrayList);
    }

    public static abstract class FetchProfileContentCallback extends BaseCallback {
        public abstract void onSuccess(SelfVRProfileContent selfVRProfileContent);
    }

    public static abstract class FetchRosterSizeCallback extends BaseCallback {
        public abstract void onSuccess(RosterSize rosterSize);
    }

    public static abstract class FetchSocialFriendsCallback {
        public abstract void onError(Exception exc);

        public abstract void onSuccess(List<SocialUser> list);
    }

    public static abstract class FetchSocialViewerInfoCallback extends BaseCallback {
        public abstract void onSuccess(SocialViewerInfo socialViewerInfo);
    }

    public static abstract class FetchVRProfileContentCallback extends BaseCallback {
        public abstract void onSuccess(VRProfileContent vRProfileContent);
    }

    public static abstract class GetBlockedUserIdCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class GetFbFriendPrimaryProfileCallback extends BaseCallback {
        public abstract void onSuccess(FbFriendPrimaryProfile fbFriendPrimaryProfile);
    }

    public static abstract class GetPartyMicrophoneInputLocationCallback extends BaseCallback {
        public abstract void onSuccess(PartyMicrophoneInputLocation partyMicrophoneInputLocation);
    }

    public static abstract class GetPartyMicrophoneStateCallback extends BaseCallback {
        public abstract void onSuccess(PartyMicrophoneState partyMicrophoneState);
    }

    public static abstract class HidePYMKUserCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class MessageSendToThreadCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class MultipleIDCallback extends BaseCallback {
        public abstract void onSuccess(List<String> list);
    }

    public static abstract class MuteStateCallback extends BaseCallback {
        public abstract void onSuccess(int i);
    }

    public static abstract class PerPersonMuteStateCallback extends BaseCallback {
        public abstract void onSuccess(int i);
    }

    public static abstract class RejectFriendRequestCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class SendFriendRequestCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class SetNuxFlagForUserCallback extends BaseCallback {
        public abstract void onSuccess();
    }

    public static final class SetPartyPerPersonMuteAsyncQueryHandler extends AsyncQueryHandler {
        public final PerPersonMuteStateCallback mCallback;

        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 != 0) {
                try {
                    this.mCallback.onSuccess(i2);
                } catch (IllegalArgumentException unused) {
                    this.mCallback.onError();
                    return;
                }
            }
            this.mCallback.onError();
        }

        public SetPartyPerPersonMuteAsyncQueryHandler(ContentResolver contentResolver, PerPersonMuteStateCallback perPersonMuteStateCallback) {
            super(contentResolver);
            this.mCallback = perPersonMuteStateCallback;
        }
    }

    public static abstract class SharePartyCallBack extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class SingleIDCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class SingleIDCallbackWithErrorCode {
        public abstract void onError(int i);

        public abstract void onSuccess(String str);
    }

    public static abstract class SwitchMicrophoneInputCallback extends BaseCallback {
        @Override // com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.BaseCallback
        public abstract void onError();

        public abstract void onSuccess();
    }

    public static abstract class UnblockUserCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class UnfriendUserCallback extends BaseCallback {
        public abstract void onSuccess(String str);
    }

    public static abstract class UserSearchCallback extends BaseCallback {
        public abstract void onSuccess(List<SocialUser> list);
    }

    public static SocialParty getCurrentPartyDataFromCursorDEPRECATED(Cursor cursor) {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination;
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("current_party_id");
        cursor.getColumnIndexOrThrow("join_policy");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("party_has_link_invite");
        int columnIndex = cursor.getColumnIndex("current_party_id");
        int columnIndex2 = cursor.getColumnIndex("party_max_size");
        int columnIndex3 = cursor.getColumnIndex("party_only_quest_users");
        int columnIndex4 = cursor.getColumnIndex("group_launch_id");
        int columnIndex5 = cursor.getColumnIndex("group_launhc_only_quest_users");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("party_social_activity_icon_url");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("party_social_activity_application_id");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("party_social_activity_application_package_name");
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow("party_social_activity_application_display_name");
        int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow("party_social_activity_deeplink");
        int columnIndexOrThrow8 = cursor.getColumnIndexOrThrow("party_social_activity_title");
        int columnIndex6 = cursor.getColumnIndex("party_social_activity_id");
        int columnIndex7 = cursor.getColumnIndex("party_social_activity_does_support_group_launch");
        int columnIndex8 = cursor.getColumnIndex("group_launch_state");
        int columnIndex9 = cursor.getColumnIndex("application_id");
        int columnIndex10 = cursor.getColumnIndex("application_display_name");
        int columnIndex11 = cursor.getColumnIndex("application_is_viewer_entitled");
        int columnIndex12 = cursor.getColumnIndex("deeplink_message_for_deeplink_target");
        int columnIndex13 = cursor.getColumnIndex("destination_display_name");
        int columnIndex14 = cursor.getColumnIndex("destination_id");
        int columnIndex15 = cursor.getColumnIndex("destination_image");
        int columnIndex16 = cursor.getColumnIndex("destination_max_capacity");
        int columnIndex17 = cursor.getColumnIndex("destination_is_external_deeplinkable");
        int columnIndex18 = cursor.getColumnIndex("group_launch_user_response");
        int columnIndex19 = cursor.getColumnIndex("party_is_user_muted");
        SocialPartyType fromPartyType = SocialPartyType.fromPartyType(CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndex("party_type"), ""));
        boolean boolFromIntIndex = CursorHelper.getBoolFromIntIndex(cursor, columnIndexOrThrow2);
        SocialUser.UserRowType userRowType = SocialUser.UserRowType.PARTY_MEMBER;
        SocialUser socialUserFromCursor = getSocialUserFromCursor(cursor, userRowType);
        String string = cursor.getString(columnIndexOrThrow);
        boolean z = false;
        if (cursor.getInt(columnIndex3) == 1) {
            z = true;
        }
        String stringFromIndex = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, "");
        String stringFromIndex2 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow4, "");
        String stringFromIndex3 = CursorHelper.getStringFromIndex(cursor, columnIndex6, "");
        boolean z2 = false;
        if (cursor.getInt(columnIndex7) == 1) {
            z2 = true;
        }
        String stringFromIndex4 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow5, "");
        String stringFromIndex5 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow6, "");
        String stringFromIndex6 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow7, "");
        String stringFromIndex7 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow8, "");
        int i = cursor.getInt(columnIndex2);
        String stringFromIndex8 = CursorHelper.getStringFromIndex(cursor, columnIndex14, "");
        if (stringFromIndex8 == null || stringFromIndex8 == "") {
            socialGroupLaunchAppDestination = null;
        } else {
            String stringFromIndex9 = CursorHelper.getStringFromIndex(cursor, columnIndex4, "");
            SocialGroupLaunchState fromString = SocialGroupLaunchState.fromString(CursorHelper.getStringFromIndex(cursor, columnIndex8, ""));
            String stringFromIndex10 = CursorHelper.getStringFromIndex(cursor, columnIndex9, "");
            String stringFromIndex11 = CursorHelper.getStringFromIndex(cursor, columnIndex10, "");
            boolean z3 = false;
            if (cursor.getInt(columnIndex11) == 1) {
                z3 = true;
            }
            String stringFromIndex12 = CursorHelper.getStringFromIndex(cursor, columnIndex12, "");
            String stringFromIndex13 = CursorHelper.getStringFromIndex(cursor, columnIndex13, "");
            String stringFromIndex14 = CursorHelper.getStringFromIndex(cursor, columnIndex14, "");
            String stringFromIndex15 = CursorHelper.getStringFromIndex(cursor, columnIndex15, "");
            int i2 = cursor.getInt(columnIndex16);
            boolean z4 = false;
            if (cursor.getInt(columnIndex5) == 1) {
                z4 = true;
            }
            socialGroupLaunchAppDestination = new SocialGroupLaunchAppDestination(stringFromIndex9, fromString, stringFromIndex10, stringFromIndex11, z3, stringFromIndex12, stringFromIndex13, stringFromIndex14, stringFromIndex15, i2, null, z4, CursorHelper.getStringFromIndex(cursor, columnIndex17, ""), "", false);
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        cursor.moveToNext();
        HashMap hashMap = new HashMap();
        while (cursor.moveToNext() && (cursor.getString(columnIndex) == null || !cursor.getString(columnIndex).equals("INVITED"))) {
            SocialUser socialUserFromCursor2 = getSocialUserFromCursor(cursor, userRowType);
            hashMap.put(socialUserFromCursor2.mID, SocialGroupLaunchResponse.fromString(CursorHelper.getStringFromIndex(cursor, columnIndex18, "")));
            socialUserFromCursor2.mDeepLink = getDeepLinkFromCursor(cursor);
            if (CursorHelper.getBoolFromIntIndex(cursor, columnIndex19)) {
                hashSet.add(socialUserFromCursor2.mID);
            }
            arrayList.add(socialUserFromCursor2);
        }
        if (socialGroupLaunchAppDestination != null) {
            socialGroupLaunchAppDestination.mUserResponses = hashMap;
        }
        ArrayList arrayList2 = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList2.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.INVITED));
        }
        return new SocialParty(string, arrayList, fromPartyType, boolFromIntIndex, socialUserFromCursor, arrayList2, null, null, hashSet, null, i, socialGroupLaunchAppDestination, z, stringFromIndex, stringFromIndex2, z2, stringFromIndex4, stringFromIndex5, stringFromIndex6, stringFromIndex7, stringFromIndex3);
    }

    public static SocialParty getCurrentPartyDataFromCursorV2(Cursor cursor) {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination;
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("current_party_id");
        cursor.getColumnIndexOrThrow("join_policy");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("party_has_link_invite");
        int columnIndex = cursor.getColumnIndex("current_party_id");
        int columnIndex2 = cursor.getColumnIndex("party_max_size");
        int columnIndex3 = cursor.getColumnIndex("party_only_quest_users");
        int columnIndex4 = cursor.getColumnIndex("group_launch_id");
        int columnIndex5 = cursor.getColumnIndex("group_launhc_only_quest_users");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("party_social_activity_icon_url");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("party_social_activity_application_id");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("party_social_activity_application_package_name");
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow("party_social_activity_application_display_name");
        int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow("party_social_activity_deeplink");
        int columnIndexOrThrow8 = cursor.getColumnIndexOrThrow("party_social_activity_title");
        int columnIndex6 = cursor.getColumnIndex("party_social_activity_id");
        int columnIndex7 = cursor.getColumnIndex("party_social_activity_does_support_group_launch");
        int columnIndex8 = cursor.getColumnIndex("group_launch_state");
        int columnIndex9 = cursor.getColumnIndex("application_id");
        int columnIndex10 = cursor.getColumnIndex("application_display_name");
        int columnIndex11 = cursor.getColumnIndex("application_is_viewer_entitled");
        int columnIndex12 = cursor.getColumnIndex("deeplink_message_for_deeplink_target");
        int columnIndex13 = cursor.getColumnIndex("destination_display_name");
        int columnIndex14 = cursor.getColumnIndex("destination_id");
        int columnIndex15 = cursor.getColumnIndex("destination_image");
        int columnIndex16 = cursor.getColumnIndex("destination_max_capacity");
        int columnIndex17 = cursor.getColumnIndex("destination_is_external_deeplinkable");
        int columnIndex18 = cursor.getColumnIndex("group_launch_user_response");
        int columnIndex19 = cursor.getColumnIndex("party_is_user_muted");
        SocialPartyType fromPartyType = SocialPartyType.fromPartyType(CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndex("party_type"), ""));
        boolean boolFromIntIndex = CursorHelper.getBoolFromIntIndex(cursor, columnIndexOrThrow2);
        SocialUser socialUserFromCursor = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
        String string = cursor.getString(columnIndexOrThrow);
        boolean z = false;
        if (cursor.getInt(columnIndex3) == 1) {
            z = true;
        }
        String stringFromIndex = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, "");
        String stringFromIndex2 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow4, "");
        String stringFromIndex3 = CursorHelper.getStringFromIndex(cursor, columnIndex6, "");
        boolean z2 = false;
        if (cursor.getInt(columnIndex7) == 1) {
            z2 = true;
        }
        String stringFromIndex4 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow5, "");
        String stringFromIndex5 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow6, "");
        String stringFromIndex6 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow7, "");
        String stringFromIndex7 = CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow8, "");
        int i = cursor.getInt(columnIndex2);
        String stringFromIndex8 = CursorHelper.getStringFromIndex(cursor, columnIndex14, "");
        if (stringFromIndex8 == null || stringFromIndex8 == "") {
            socialGroupLaunchAppDestination = null;
        } else {
            String stringFromIndex9 = CursorHelper.getStringFromIndex(cursor, columnIndex4, "");
            SocialGroupLaunchState fromString = SocialGroupLaunchState.fromString(CursorHelper.getStringFromIndex(cursor, columnIndex8, ""));
            String stringFromIndex10 = CursorHelper.getStringFromIndex(cursor, columnIndex9, "");
            String stringFromIndex11 = CursorHelper.getStringFromIndex(cursor, columnIndex10, "");
            boolean z3 = false;
            if (cursor.getInt(columnIndex11) == 1) {
                z3 = true;
            }
            String stringFromIndex12 = CursorHelper.getStringFromIndex(cursor, columnIndex12, "");
            String stringFromIndex13 = CursorHelper.getStringFromIndex(cursor, columnIndex13, "");
            String stringFromIndex14 = CursorHelper.getStringFromIndex(cursor, columnIndex14, "");
            String stringFromIndex15 = CursorHelper.getStringFromIndex(cursor, columnIndex15, "");
            int i2 = cursor.getInt(columnIndex16);
            boolean z4 = false;
            if (cursor.getInt(columnIndex5) == 1) {
                z4 = true;
            }
            socialGroupLaunchAppDestination = new SocialGroupLaunchAppDestination(stringFromIndex9, fromString, stringFromIndex10, stringFromIndex11, z3, stringFromIndex12, stringFromIndex13, stringFromIndex14, stringFromIndex15, i2, null, z4, CursorHelper.getStringFromIndex(cursor, columnIndex17, ""), "", false);
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        cursor.moveToNext();
        HashMap hashMap = new HashMap();
        while (cursor.moveToNext() && !getIsCurrentRowNextResultTypeIndicator(cursor, columnIndex)) {
            SocialUser socialUserFromCursor2 = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
            hashMap.put(socialUserFromCursor2.mID, SocialGroupLaunchResponse.fromString(CursorHelper.getStringFromIndex(cursor, columnIndex18, "")));
            socialUserFromCursor2.mDeepLink = getDeepLinkFromCursor(cursor);
            if (CursorHelper.getBoolFromIntIndex(cursor, columnIndex19)) {
                hashSet.add(socialUserFromCursor2.mID);
            }
            arrayList.add(socialUserFromCursor2);
            if (socialUserFromCursor2.mID.equals(socialUserFromCursor.mID)) {
                socialUserFromCursor.mMicrophoneChannel = socialUserFromCursor2.mMicrophoneChannel;
            }
        }
        if (socialGroupLaunchAppDestination != null) {
            socialGroupLaunchAppDestination.mUserResponses = hashMap;
        }
        ArrayList arrayList2 = new ArrayList();
        while (cursor.moveToNext() && !getIsCurrentRowNextResultTypeIndicator(cursor, columnIndex)) {
            arrayList2.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.INVITED));
        }
        ArrayList arrayList3 = new ArrayList();
        while (cursor.moveToNext() && !getIsCurrentRowNextResultTypeIndicator(cursor, columnIndex)) {
            arrayList3.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.BLOCKED_USER));
        }
        ArrayList arrayList4 = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList4.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.BLOCKED_INVITED));
        }
        return new SocialParty(string, arrayList, fromPartyType, boolFromIntIndex, socialUserFromCursor, arrayList2, arrayList3, arrayList4, hashSet, null, i, socialGroupLaunchAppDestination, z, stringFromIndex, stringFromIndex2, z2, stringFromIndex4, stringFromIndex5, stringFromIndex6, stringFromIndex7, stringFromIndex3);
    }

    public static long getLongFromIndex(Cursor cursor, int i, long j) {
        if (i == -1) {
            return j;
        }
        return cursor.getLong(i);
    }

    public static String getPartyFBIDFromCursor(Cursor cursor) {
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        return CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("party_fbid"), "");
    }

    public static List<SocialParty> getPartyInvitesDataFromCursor(Cursor cursor) {
        cursor.moveToPosition(-1);
        int columnIndex = cursor.getColumnIndex("current_party_id");
        int columnIndex2 = cursor.getColumnIndex("party_max_size");
        ArrayList arrayList = new ArrayList();
        SocialParty socialParty = null;
        ArrayList arrayList2 = null;
        while (cursor.moveToNext()) {
            if (cursor.getString(columnIndex) != null) {
                if (socialParty != null) {
                    socialParty.mPartyMembers = arrayList2;
                    arrayList.add(socialParty);
                }
                arrayList2 = new ArrayList();
                socialParty = new SocialParty(null, arrayList2, SocialPartyType.CLOSED, false, null, null, null, null, null, null, cursor.getInt(columnIndex2), null, false, null, null, false, null, null, null, null, null);
                socialParty.mID = cursor.getString(columnIndex);
                socialParty.mInviter = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
            } else {
                arrayList2.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER));
            }
        }
        if (socialParty != null) {
            socialParty.mPartyMembers = arrayList2;
            arrayList.add(socialParty);
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (0 == 0) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final float getPartyVolume(android.content.Context r9) {
        /*
            r3 = 0
            r6 = 0
            android.content.ContentResolver r4 = r9.getContentResolver()     // Catch:{ Exception -> 0x0025 }
            android.net.Uri r5 = com.oculus.horizoncontent.horizon.HorizonContent.FriendList.PARTY_GET_VOLUME     // Catch:{ Exception -> 0x0025 }
            r7 = r6
            r8 = r6
            r9 = r6
            android.database.Cursor r6 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0025 }
            if (r6 == 0) goto L_0x0032
            boolean r0 = r6.moveToNext()     // Catch:{ Exception -> 0x0025 }
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = "party_volume"
            int r0 = r6.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x0025 }
            float r0 = r6.getFloat(r0)     // Catch:{ Exception -> 0x0025 }
            r6.close()
            return r0
        L_0x0025:
            r2 = move-exception
            java.lang.String r1 = com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.TAG     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = "Error getting party volume"
            android.util.Log.e(r1, r0, r2)     // Catch:{ all -> 0x0033 }
            if (r6 == 0) goto L_0x0032
        L_0x002f:
            r6.close()
        L_0x0032:
            return r3
        L_0x0033:
            r0 = move-exception
            if (r6 == 0) goto L_0x0039
            r6.close()
        L_0x0039:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.getPartyVolume(android.content.Context):float");
    }

    public static /* synthetic */ App lambda$getGroupLaunchSupportedApplicationsFromCursor$4(App app) {
        return app;
    }

    public static /* synthetic */ void lambda$getHasParticipantRecentlySpokenDEPRECATED$13(String str, Context context, FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(HorizonContent.FriendList.GET_PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN.buildUpon().appendQueryParameter("user_id", str).build(), null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    boolean z = true;
                    if (cursor.getInt(cursor.getColumnIndexOrThrow("party_has_participant_recently_spoken")) != 1) {
                        z = false;
                    }
                    fetchParticipantSpeakerStatusCallback.onSuccess(z);
                }
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting party participant's speaking status", e);
            if (0 == 0) {
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public static final class CancelPartyInviteAsyncQueryHandler extends AsyncQueryHandler {
        public final SingleIDCallback mCallback;

        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 == 1) {
                this.mCallback.onSuccess((String) obj);
            } else {
                this.mCallback.onError();
            }
        }

        public CancelPartyInviteAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver);
            this.mCallback = singleIDCallback;
        }
    }

    public static final class FetchGroupLaunchAppAsyncQueryHandler extends AsyncQueryHandlerBase<FetchGroupLaunchSupportedApplicationsCallback> {
        public Context mContext;

        public FetchGroupLaunchAppAsyncQueryHandler(Context context, FetchGroupLaunchSupportedApplicationsCallback fetchGroupLaunchSupportedApplicationsCallback) {
            super(context.getContentResolver(), fetchGroupLaunchSupportedApplicationsCallback);
            this.mContext = context;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
            if (r3 != null) goto L_0x001e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
            r2.onError();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0012 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onQueryComplete(com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchGroupLaunchSupportedApplicationsCallback r2, android.database.Cursor r3) {
            /*
                r1 = this;
                if (r3 == 0) goto L_0x000c
                android.content.Context r0 = r1.mContext     // Catch:{ Exception -> 0x0012 }
                java.util.List r0 = com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.getGroupLaunchSupportedApplicationsFromCursor(r0, r3)     // Catch:{ Exception -> 0x0012 }
                r2.onSuccess(r0)     // Catch:{ Exception -> 0x0012 }
                goto L_0x0017
            L_0x000c:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0012 }
                r0.<init>()     // Catch:{ Exception -> 0x0012 }
                throw r0     // Catch:{ Exception -> 0x0012 }
            L_0x0012:
                r2.onError()     // Catch:{ all -> 0x001b }
                if (r3 == 0) goto L_0x001a
            L_0x0017:
                r3.close()
            L_0x001a:
                return
            L_0x001b:
                r0 = move-exception
                if (r3 == 0) goto L_0x0021
                r3.close()
            L_0x0021:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchGroupLaunchAppAsyncQueryHandler.onQueryComplete(com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$FetchGroupLaunchSupportedApplicationsCallback, android.database.Cursor):void");
        }
    }

    public static final class GetFbFriendPrimaryProfileAsyncQueryHandler extends AsyncQueryHandlerBase<GetFbFriendPrimaryProfileCallback> {
        public Context mContext;

        public GetFbFriendPrimaryProfileAsyncQueryHandler(Context context, ContentResolver contentResolver, GetFbFriendPrimaryProfileCallback getFbFriendPrimaryProfileCallback) {
            super(contentResolver, getFbFriendPrimaryProfileCallback);
            this.mContext = context;
        }

        public void onQueryComplete(GetFbFriendPrimaryProfileCallback getFbFriendPrimaryProfileCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    HorizonContentProviderHelper.getFbFriendPrimaryProfileFromCursor(this.mContext, cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    getFbFriendPrimaryProfileCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class HidePYMKUserAsyncQueryHandler extends AsyncQueryHandler {
        public final HidePYMKUserCallback mCallback;

        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 == 1) {
                this.mCallback.onSuccess((String) obj);
            } else {
                this.mCallback.onError();
            }
        }

        public HidePYMKUserAsyncQueryHandler(ContentResolver contentResolver, HidePYMKUserCallback hidePYMKUserCallback) {
            super(contentResolver);
            this.mCallback = hidePYMKUserCallback;
        }
    }

    public static final class KickUserFromPartyAsyncQueryHandler extends AsyncQueryHandler {
        public final SingleIDCallback mCallback;

        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 == 1) {
                this.mCallback.onSuccess((String) obj);
            } else {
                this.mCallback.onError();
            }
        }

        public KickUserFromPartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver);
            this.mCallback = singleIDCallback;
        }
    }

    public static final class SetPartyLocalMuteAsyncQueryHandler extends AsyncQueryHandler {
        public final MuteStateCallback mCallback;

        public SetPartyLocalMuteAsyncQueryHandler(ContentResolver contentResolver, MuteStateCallback muteStateCallback) {
            super(contentResolver);
            this.mCallback = muteStateCallback;
        }

        public void onUpdateComplete(int i, Object obj, int i2) {
            HorizonContent.PlatformPluginManager.MicrophoneMuteState.validate(i2);
            if (i2 != 0) {
                try {
                    this.mCallback.onSuccess(i2);
                } catch (IllegalArgumentException unused) {
                    this.mCallback.onError();
                }
            } else {
                this.mCallback.onError();
            }
        }
    }

    public static final class SetPartyTypeAsyncQueryHandlerDEPRECATED extends AsyncQueryHandler {
        public final SingleIDCallback mCallback;

        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 == 1) {
                this.mCallback.onSuccess((String) obj);
            } else {
                this.mCallback.onError();
            }
        }

        public SetPartyTypeAsyncQueryHandlerDEPRECATED(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver);
            this.mCallback = singleIDCallback;
        }
    }

    public static final class SwitchMicrophoneInputAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public final SingleIDCallback mCallback;

        public SwitchMicrophoneInputAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
            this.mCallback = singleIDCallback;
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                try {
                    singleIDCallback.onSuccess(Integer.toString(i));
                } catch (Exception unused) {
                    singleIDCallback.onError();
                }
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle fetchGroupLaunchSupportedApps(Context context, FetchGroupLaunchSupportedApplicationsCallback fetchGroupLaunchSupportedApplicationsCallback) {
        FetchGroupLaunchAppAsyncQueryHandler fetchGroupLaunchAppAsyncQueryHandler = new FetchGroupLaunchAppAsyncQueryHandler(context, fetchGroupLaunchSupportedApplicationsCallback);
        fetchGroupLaunchAppAsyncQueryHandler.startQuery(1, "", HorizonContent.FriendList.GET_GROUP_LAUNCH_SUPPORTED_APPLICATIONS_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchGroupLaunchAppAsyncQueryHandler, fetchGroupLaunchSupportedApplicationsCallback);
    }

    @Nullable
    public static SocialDeeplinkPresence getDeepLinkFromCursor(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("presence_app_package_name");
        int columnIndex2 = cursor.getColumnIndex("presence_app_package_app_id");
        int columnIndex3 = cursor.getColumnIndex("presence_deep_link_launch_params");
        String stringFromIndex = CursorHelper.getStringFromIndex(cursor, columnIndex, "");
        String stringFromIndex2 = CursorHelper.getStringFromIndex(cursor, columnIndex2, "");
        String stringFromIndex3 = CursorHelper.getStringFromIndex(cursor, columnIndex3, "");
        if (stringFromIndex == null || stringFromIndex.isEmpty() || stringFromIndex2 == null || stringFromIndex2.isEmpty() || stringFromIndex3 == null || stringFromIndex3.isEmpty()) {
            return null;
        }
        return new SocialDeeplinkPresence(stringFromIndex2, stringFromIndex, stringFromIndex3);
    }

    public static FbFriendPrimaryProfile getFbFriendPrimaryProfileFromCursor(Context context, Cursor cursor) {
        SocialUserFriendshipStatus valueOf;
        SocialUserPresenceStatus valueOf2;
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("fb_friend_name");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("fb_friend_profile_picture_uri");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("fb_friend_bio");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("fb_friend_is_currently_active");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("fb_friend_last_active_time");
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow("fb_friend_oc_user_id");
        int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow("fb_friend_oc_user_friend_status");
        int columnIndexOrThrow8 = cursor.getColumnIndexOrThrow("fb_friend_oc_user_presence_status");
        int columnIndexOrThrow9 = cursor.getColumnIndexOrThrow("fb_friend_oc_user_is_current");
        int columnIndexOrThrow10 = cursor.getColumnIndexOrThrow("fb_friend_oc_user_presence");
        int columnIndexOrThrow11 = cursor.getColumnIndexOrThrow("fb_friend_oc_user_last_active_time");
        int columnIndexOrThrow12 = cursor.getColumnIndexOrThrow("fb_friend_oc_user_party_can_be_invited_to_party");
        int columnIndexOrThrow13 = cursor.getColumnIndexOrThrow("fb_friend_oc_user_joinable_party_id");
        int columnIndexOrThrow14 = cursor.getColumnIndexOrThrow("viewer_fb_id");
        int columnIndexOrThrow15 = cursor.getColumnIndexOrThrow("viewer_current_party_id");
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        String string = cursor.getString(columnIndexOrThrow7);
        String string2 = cursor.getString(columnIndexOrThrow8);
        String string3 = cursor.getString(columnIndexOrThrow);
        String string4 = cursor.getString(columnIndexOrThrow2);
        String string5 = cursor.getString(columnIndexOrThrow3);
        boolean boolFromIntIndex = CursorHelper.getBoolFromIntIndex(cursor, columnIndexOrThrow4);
        Long valueOf3 = Long.valueOf(cursor.getLong(columnIndexOrThrow5));
        String string6 = cursor.getString(columnIndexOrThrow6);
        if (string == null) {
            valueOf = SocialUserFriendshipStatus.UNKNOWN;
        } else {
            valueOf = SocialUserFriendshipStatus.valueOf(string);
        }
        if (string2 == null) {
            valueOf2 = SocialUserPresenceStatus.OFFLINE;
        } else {
            valueOf2 = SocialUserPresenceStatus.valueOf(string2);
        }
        return new FbFriendPrimaryProfile(string3, string4, string5, boolFromIntIndex, valueOf3, string6, valueOf, valueOf2, CursorHelper.getBoolFromIntIndex(cursor, columnIndexOrThrow9), cursor.getString(columnIndexOrThrow10), Long.valueOf(cursor.getLong(columnIndexOrThrow11)), CursorHelper.getBoolFromIntIndex(cursor, columnIndexOrThrow12), cursor.getString(columnIndexOrThrow13), cursor.getString(columnIndexOrThrow14), cursor.getString(columnIndexOrThrow15));
    }

    public static ArrayList<FriendListRowData> getFriendDataFromCursor(Cursor cursor) {
        ArrayList<FriendListRowData> arrayList = new ArrayList<>();
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("name");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("presence");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("profile_image_url");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("invite_state");
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow("current_party_id");
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            if (cursor.getString(columnIndexOrThrow6) == null) {
                arrayList.add(new FriendListRowData(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow4, ""), HorizonContent.FriendList.UserInviteState.values()[cursor.getInt(columnIndexOrThrow5)]));
            }
        }
        return arrayList;
    }

    public static ArrayList<SocialUser> getFriendRequestDataFromCursor(Cursor cursor) {
        ArrayList<SocialUser> arrayList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arrayList.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.INCOMING_FRIEND_REQUEST));
        }
        return arrayList;
    }

    public static List<SocialGroupLaunchAppDestination> getGroupLaunchAppDestinationsFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("deeplink_message_for_deeplink_target");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("destination_display_name");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("destination_id");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("destination_image");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("destination_max_capacity");
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow("destination_is_external_deeplinkable");
        int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow("cursor");
        int columnIndexOrThrow8 = cursor.getColumnIndexOrThrow("has_next");
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arrayList.add(new SocialGroupLaunchAppDestination("", null, "", "", true, CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow4, ""), cursor.getInt(columnIndexOrThrow5), null, false, CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow6, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow7, ""), CursorHelper.getBoolFromIntIndex(cursor, columnIndexOrThrow8)));
        }
        return arrayList;
    }

    public static final void getHasParticipantRecentlySpokenDEPRECATED(Context context, String str, FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, context, fetchParticipantSpeakerStatusCallback) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$9oJVrEUoycsH6y2PeyOD52lY1CE2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ Context f$1;
            public final /* synthetic */ HorizonContentProviderHelper.FetchParticipantSpeakerStatusCallback f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$getHasParticipantRecentlySpokenDEPRECATED$13(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    public static List<SocialParty> getJoinablePartiesDataFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("party_id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("id");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("name");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("alias");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("profile_image_url");
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow("party_max_size");
        int columnIndex = cursor.getColumnIndex("can_viewer_message");
        cursor.moveToPosition(-1);
        HashMap hashMap = new HashMap();
        String str = null;
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndexOrThrow);
            int i = cursor.getInt(columnIndexOrThrow6);
            if (string != null) {
                hashMap.put(string, new SocialParty(string, new ArrayList(), SocialPartyType.JOINABLE_BY_FRIENDS, false, null, null, null, null, null, null, i, null, false, null, null, false, null, null, null, null, null));
                str = string;
            } else {
                SocialParty socialParty = (SocialParty) hashMap.get(str);
                List<SocialUser> list = socialParty.mPartyMembers;
                SocialUser socialUser = new SocialUser(cursor.getString(columnIndexOrThrow2), cursor.getString(columnIndexOrThrow3), cursor.getString(columnIndexOrThrow4), null, cursor.getString(columnIndexOrThrow5), CursorHelper.getBoolFromIntIndex(cursor, columnIndex), SocialUser.UserRowType.PARTY_MEMBER);
                socialUser.mCurrentPartyID = str;
                list.add(socialUser);
                socialParty.mPartyMembers = list;
                hashMap.put(string, socialParty);
            }
        }
        for (Object obj : hashMap.keySet()) {
            arrayList.add(hashMap.get(obj));
        }
        return arrayList;
    }

    public static ArrayList<SocialUser> getPYMKDataFromCursor(Cursor cursor) {
        ArrayList<SocialUser> arrayList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arrayList.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.SUGGESTED_FRIEND));
        }
        return arrayList;
    }

    public static String getPartyIDFromCursor(Cursor cursor) {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("current_party_id");
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            if (cursor.getString(columnIndexOrThrow) != null) {
                return cursor.getString(columnIndexOrThrow);
            }
        }
        return "";
    }

    public static PartyInfo getPartyInfoWithActivityFromCursor(Cursor cursor) {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("party_id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("party_blocked_invited_users");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("party_invited_users");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("party_blocked_current_users");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("party_current_users");
        int columnIndexOrThrow6 = cursor.getColumnIndexOrThrow("party_activity_id");
        int columnIndexOrThrow7 = cursor.getColumnIndexOrThrow("party_activity_title");
        int columnIndexOrThrow8 = cursor.getColumnIndexOrThrow("party_activity_subtitle");
        int columnIndexOrThrow9 = cursor.getColumnIndexOrThrow("party_activity_deeplink");
        int columnIndexOrThrow10 = cursor.getColumnIndexOrThrow("party_activity_image_uri");
        int columnIndexOrThrow11 = cursor.getColumnIndexOrThrow("party_activity_package_name");
        int columnIndexOrThrow12 = cursor.getColumnIndexOrThrow("party_activity_app_id");
        if (!cursor.moveToFirst()) {
            return null;
        }
        return new PartyInfo(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow, ""), Integer.parseInt(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, "")), Integer.parseInt(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, "")), Integer.parseInt(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow4, "")), Integer.parseInt(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow5, "")), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow6, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow7, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow8, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow9, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow10, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow11, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow12, ""));
    }

    public static final int getPartyLocalMute(Context context) {
        return context.getContentResolver().update(HorizonContent.FriendList.PARTY_GET_SELF_MUTE, new ContentValues(), null, null);
    }

    public static ArrayList<SocialUser> getPeopleNearbyDataFromCursor(Cursor cursor) {
        ArrayList<SocialUser> arrayList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arrayList.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.PEOPLE_NEARBY));
        }
        return arrayList;
    }

    public static SelfVRProfileContent getProfileContentFromCursor(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("friend_count");
        int columnIndex2 = cursor.getColumnIndex("biography");
        int columnIndex3 = cursor.getColumnIndex("is_current");
        int columnIndex4 = cursor.getColumnIndex("presence");
        int columnIndex5 = cursor.getColumnIndex("name");
        int columnIndex6 = cursor.getColumnIndex("alias");
        int columnIndex7 = cursor.getColumnIndex("profile_image_url");
        int columnIndex8 = cursor.getColumnIndex("avatar_image_url");
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        return new SelfVRProfileContent(cursor.getInt(columnIndex), CursorHelper.getStringFromIndex(cursor, columnIndex2, ""), Boolean.valueOf(CursorHelper.getBoolFromIntIndex(cursor, columnIndex3)), CursorHelper.getStringFromIndex(cursor, columnIndex4, ""), CursorHelper.getStringFromIndex(cursor, columnIndex5, ""), CursorHelper.getStringFromIndex(cursor, columnIndex6, ""), CursorHelper.getStringFromIndex(cursor, columnIndex7, ""), CursorHelper.getStringFromIndex(cursor, columnIndex8, ""));
    }

    public static SocialUser getSocialUserFromCursor(Cursor cursor, SocialUser.UserRowType userRowType) {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("name");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("alias");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("presence");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("profile_image_url");
        int columnIndex = cursor.getColumnIndex("friendship_status");
        int columnIndex2 = cursor.getColumnIndex("mutual_context_string");
        int columnIndex3 = cursor.getColumnIndex("last_active_description");
        int columnIndex4 = cursor.getColumnIndex("current_party_id");
        int columnIndex5 = cursor.getColumnIndex("join_policy");
        int columnIndex6 = cursor.getColumnIndex("party_max_size");
        int columnIndex7 = cursor.getColumnIndex("party_users_count");
        int columnIndex8 = cursor.getColumnIndex("can_viewer_message");
        int columnIndex9 = cursor.getColumnIndex("party_invite_sender_id");
        int columnIndex10 = cursor.getColumnIndex("is_in_room");
        int columnIndex11 = cursor.getColumnIndex("presence_status");
        int columnIndex12 = cursor.getColumnIndex("party_microphone_input_location");
        SocialUser socialUser = new SocialUser(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow4, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow5, ""), CursorHelper.getBoolFromIntIndex(cursor, columnIndex8), userRowType);
        String stringFromIndex = CursorHelper.getStringFromIndex(cursor, columnIndex11, null);
        if (stringFromIndex != null && !stringFromIndex.isEmpty()) {
            socialUser.setPresenceStatus(stringFromIndex);
        }
        String stringFromIndex2 = CursorHelper.getStringFromIndex(cursor, columnIndex, "");
        if (stringFromIndex2 != null && !stringFromIndex2.isEmpty()) {
            socialUser.mFriendship = SocialUserFriendshipStatus.get(stringFromIndex2);
        }
        String stringFromIndex3 = CursorHelper.getStringFromIndex(cursor, columnIndex2, "");
        if (stringFromIndex3 != null && !stringFromIndex3.isEmpty()) {
            socialUser.mMutualContext = stringFromIndex3;
        }
        socialUser.mIsInRoom = CursorHelper.getBoolFromIntIndex(cursor, columnIndex10);
        String stringFromIndex4 = CursorHelper.getStringFromIndex(cursor, columnIndex3, "");
        if (stringFromIndex4 != null && !stringFromIndex4.isEmpty()) {
            socialUser.mLastActive = stringFromIndex4;
        }
        String stringFromIndex5 = CursorHelper.getStringFromIndex(cursor, columnIndex4, "");
        if (stringFromIndex5 != null && !stringFromIndex5.isEmpty()) {
            socialUser.mCurrentPartyID = stringFromIndex5;
        }
        String stringFromIndex6 = CursorHelper.getStringFromIndex(cursor, columnIndex5, "");
        if (stringFromIndex6 != null && !stringFromIndex6.isEmpty()) {
            socialUser.mCurrentPartyJoinPolicy = stringFromIndex6;
        }
        String stringFromIndex7 = CursorHelper.getStringFromIndex(cursor, columnIndex6, "");
        String stringFromIndex8 = CursorHelper.getStringFromIndex(cursor, columnIndex7, "");
        boolean z = false;
        if (stringFromIndex7 != null && !stringFromIndex7.isEmpty() && stringFromIndex8 != null && !stringFromIndex8.isEmpty() && Integer.parseInt(stringFromIndex8) >= Integer.parseInt(stringFromIndex7)) {
            z = true;
        }
        socialUser.mIsPartyFull = z;
        String stringFromIndex9 = CursorHelper.getStringFromIndex(cursor, columnIndex9, "");
        if (stringFromIndex9 != null && !stringFromIndex9.isEmpty()) {
            socialUser.mInvitedToLocalPartyBySenderID = stringFromIndex9;
        }
        String stringFromIndex10 = CursorHelper.getStringFromIndex(cursor, columnIndex12, "");
        if (stringFromIndex10 == null) {
            socialUser.mMicrophoneChannel = PartyMicrophoneState.UNKNOWN;
            return socialUser;
        }
        try {
            socialUser.mMicrophoneChannel = PartyMicrophoneState.valueOf(stringFromIndex10);
            return socialUser;
        } catch (IllegalArgumentException e) {
            e.toString();
            socialUser.mMicrophoneChannel = PartyMicrophoneState.UNKNOWN;
            return socialUser;
        }
    }

    public static List<SocialUser> getSocialUsersFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            SocialUser socialUserFromCursor = getSocialUserFromCursor(cursor, SocialUser.UserRowType.FRIEND);
            socialUserFromCursor.mFriendship = SocialUserFriendshipStatus.ARE_FRIENDS;
            arrayList.add(socialUserFromCursor);
        }
        return arrayList;
    }

    public static SocialViewerInfo getSocialViewerInfoFromCursor(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("alias");
        int columnIndex2 = cursor.getColumnIndex("profile_image_url");
        int columnIndex3 = cursor.getColumnIndex("avatar_image_url");
        int columnIndex4 = cursor.getColumnIndex("has_seen_vr_invite_profile_nux");
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        return new SocialViewerInfo(CursorHelper.getStringFromIndex(cursor, columnIndex, ""), CursorHelper.getStringFromIndex(cursor, columnIndex2, ""), CursorHelper.getStringFromIndex(cursor, columnIndex3, ""), CursorHelper.getBoolFromIntIndex(cursor, columnIndex4));
    }

    public static VRProfileContent getVRProfileContentFromCursor(Cursor cursor) {
        SocialUserPresenceStatus valueOf;
        int columnIndex = cursor.getColumnIndex("alias");
        int columnIndex2 = cursor.getColumnIndex("biography");
        int columnIndex3 = cursor.getColumnIndex("blocked_by_viewer");
        int columnIndex4 = cursor.getColumnIndex("profile_image_url");
        int columnIndex5 = cursor.getColumnIndex("avatar_image_url");
        int columnIndex6 = cursor.getColumnIndex("friendship_status");
        int columnIndex7 = cursor.getColumnIndex("is_current");
        int columnIndex8 = cursor.getColumnIndex("last_active_time");
        int columnIndex9 = cursor.getColumnIndex("last_presence");
        int columnIndex10 = cursor.getColumnIndex("presence");
        int columnIndex11 = cursor.getColumnIndex("presence_status");
        int columnIndex12 = cursor.getColumnIndex("name");
        int columnIndex13 = cursor.getColumnIndex("current_party_id");
        int columnIndex14 = cursor.getColumnIndex("party_can_invite_user");
        int columnIndex15 = cursor.getColumnIndex("fb_linked_status");
        int columnIndex16 = cursor.getColumnIndex("joinable_party_id");
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        String stringFromIndex = CursorHelper.getStringFromIndex(cursor, columnIndex11, null);
        String stringFromIndex2 = CursorHelper.getStringFromIndex(cursor, columnIndex, "");
        String stringFromIndex3 = CursorHelper.getStringFromIndex(cursor, columnIndex2, "");
        SocialUserFriendshipStatus socialUserFriendshipStatus = SocialUserFriendshipStatus.get(CursorHelper.getStringFromIndex(cursor, columnIndex6, ""));
        Boolean valueOf2 = Boolean.valueOf(CursorHelper.getBoolFromIntIndex(cursor, columnIndex3));
        String stringFromIndex4 = CursorHelper.getStringFromIndex(cursor, columnIndex4, "");
        String stringFromIndex5 = CursorHelper.getStringFromIndex(cursor, columnIndex5, "");
        Boolean valueOf3 = Boolean.valueOf(CursorHelper.getBoolFromIntIndex(cursor, columnIndex7));
        long longFromIndex = getLongFromIndex(cursor, columnIndex8, 0);
        String stringFromIndex6 = CursorHelper.getStringFromIndex(cursor, columnIndex9, "");
        String stringFromIndex7 = CursorHelper.getStringFromIndex(cursor, columnIndex10, "");
        if (stringFromIndex == null) {
            valueOf = SocialUserPresenceStatus.OFFLINE;
        } else {
            valueOf = SocialUserPresenceStatus.valueOf(stringFromIndex);
        }
        return new VRProfileContent(stringFromIndex2, stringFromIndex3, socialUserFriendshipStatus, valueOf2, stringFromIndex4, stringFromIndex5, valueOf3, longFromIndex, stringFromIndex6, stringFromIndex7, valueOf, CursorHelper.getStringFromIndex(cursor, columnIndex12, ""), CursorHelper.getStringFromIndex(cursor, columnIndex13, null), Boolean.valueOf(CursorHelper.getBoolFromIntIndex(cursor, columnIndex14)), new LinkedAccountsInfo(CursorHelper.getStringFromIndex(cursor, columnIndex15, "")), CursorHelper.getStringFromIndex(cursor, columnIndex16, null));
    }

    public static /* synthetic */ void lambda$activatePartyLinkInvite$9(String str, ActivatePartyLinkInviteAsyncQueryHandler activatePartyLinkInviteAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        activatePartyLinkInviteAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_ACTIVATE_LINK_INVITE, contentValues, null, null);
    }

    public static /* synthetic */ void lambda$createOrUpdateCustomParty$0(Map map, CreateOrUpdateCustomPartyAsyncQueryHandler createOrUpdateCustomPartyAsyncQueryHandler) {
        Uri uri = HorizonContent.FriendList.PARTY_CREATE;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                uri = uri.buildUpon().appendQueryParameter((String) entry.getKey(), (String) entry.getValue()).build();
            }
        }
        uri.toString();
        createOrUpdateCustomPartyAsyncQueryHandler.startQuery("", uri, null, null, null, null);
    }

    public static /* synthetic */ void lambda$createParty$1(String str, CreatePartyAsyncQueryHandler createPartyAsyncQueryHandler) {
        Uri build;
        if (str == null) {
            build = HorizonContent.FriendList.PARTY_CREATE;
        } else {
            build = HorizonContent.FriendList.PARTY_CREATE.buildUpon().appendQueryParameter("invite_activity_id", str).build();
        }
        createPartyAsyncQueryHandler.startQuery("", build, null, null, null, null);
    }

    public static /* synthetic */ void lambda$createPartyDEPRECATED$2(String str, CreatePartyAsyncQueryHandlerDEPRECATED createPartyAsyncQueryHandlerDEPRECATED) {
        Uri build;
        if (str == null) {
            build = HorizonContent.FriendList.PARTY_CREATE;
        } else {
            build = HorizonContent.FriendList.PARTY_CREATE.buildUpon().appendQueryParameter("invite_activity_id", str).build();
        }
        createPartyAsyncQueryHandlerDEPRECATED.startQuery(1, "", build, null, null, null, null);
    }

    public static /* synthetic */ void lambda$deactivatePartyLinkInvite$10(String str, DeactivatePartyLinkInviteAsyncQueryHandler deactivatePartyLinkInviteAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        deactivatePartyLinkInviteAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_DEACTIVATE_LINK_INVITE, contentValues, null, null);
    }

    public static /* synthetic */ void lambda$joinParty$5(String str, String str2, String str3, JoinPartyAsyncQueryHandler joinPartyAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("link_nonce", str2);
        contentValues.put("application_id", str3);
        joinPartyAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_JOIN, contentValues, null, null);
    }

    public static /* synthetic */ void lambda$kickUserFromParty$11(String str, String str2, KickUserFromPartyAsyncQueryHandler kickUserFromPartyAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("user_id", str2);
        kickUserFromPartyAsyncQueryHandler.startUpdate(1, str, HorizonContent.FriendList.PARTY_KICK, contentValues, null, null);
    }

    public static /* synthetic */ void lambda$leaveParty$6(String str, LeavePartyAsyncQueryHandler leavePartyAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        leavePartyAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_LEAVE, contentValues, null, null);
    }

    public static /* synthetic */ void lambda$setPartyPerPersonMuteStatusAsync$16(String str, int i, SetPartyPerPersonMuteAsyncQueryHandler setPartyPerPersonMuteAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("party_per_person_mute_value", Integer.valueOf(i));
        setPartyPerPersonMuteAsyncQueryHandler.startUpdate(1, "", HorizonContent.FriendList.PARTY_SET_PER_PERSON_MUTE_STATUS, contentValues, null, null);
    }

    public static /* synthetic */ void lambda$setPartyType$8(String str, String str2, SetPartyTypeAsyncQueryHandler setPartyTypeAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("party_type", str2);
        setPartyTypeAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_SET_TYPE, contentValues, null, null);
    }

    public static /* synthetic */ void lambda$setPartyTypeDEPRECATED$7(String str, String str2, SetPartyTypeAsyncQueryHandlerDEPRECATED setPartyTypeAsyncQueryHandlerDEPRECATED) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("party_type", str2);
        setPartyTypeAsyncQueryHandlerDEPRECATED.startUpdate(1, str, HorizonContent.FriendList.PARTY_SET_TYPE, contentValues, null, null);
    }

    public static /* synthetic */ void lambda$setPartyVolume$12(float f, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_volume", Float.valueOf(f));
        if (context.getContentResolver().update(HorizonContent.FriendList.PARTY_SET_VOLUME, contentValues, null, null) != 1) {
            Log.e(TAG, "Error setting party volume");
        }
    }

    public static final void setPartyLocalMuteAsync(Context context, int i, MuteStateCallback muteStateCallback) throws IllegalArgumentException {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(i, context, muteStateCallback) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$mmDyThwDIHSFp2VjyUmyv2pyIg2 */
            public final /* synthetic */ int f$0;
            public final /* synthetic */ Context f$1;
            public final /* synthetic */ HorizonContentProviderHelper.MuteStateCallback f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyLocalMuteAsync$15(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    public static final void setPartyVolume(Context context, float f) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(f, context) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$Lh6B1ends7gGxgo24mRMfBjTk2 */
            public final /* synthetic */ float f$0;
            public final /* synthetic */ Context f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyVolume$12(this.f$0, this.f$1);
            }
        });
    }

    public static final AsyncQueryHandle acceptFriendRequest(Context context, String str, String str2, AcceptFriendRequestCallback acceptFriendRequestCallback) {
        AcceptFriendRequestAsyncQueryHandler acceptFriendRequestAsyncQueryHandler = new AcceptFriendRequestAsyncQueryHandler(context.getContentResolver(), acceptFriendRequestCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("source", str2);
        acceptFriendRequestAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_ACCEPT_REQUEST, contentValues, null, null);
        return new AsyncQueryHandle(acceptFriendRequestAsyncQueryHandler, acceptFriendRequestCallback);
    }

    public static final AsyncQueryHandle activatePartyLinkInvite(Context context, String str, SingleIDCallback singleIDCallback) {
        ActivatePartyLinkInviteAsyncQueryHandler activatePartyLinkInviteAsyncQueryHandler = new ActivatePartyLinkInviteAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, activatePartyLinkInviteAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$QnhHt3aKJgLbDS3W8SwHxvv42QM2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ HorizonContentProviderHelper.ActivatePartyLinkInviteAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$activatePartyLinkInvite$9(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(activatePartyLinkInviteAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle blockUser(Context context, String str, BlockUserCallback blockUserCallback) {
        BlockUserAsyncQueryHandler blockUserAsyncQueryHandler = new BlockUserAsyncQueryHandler(context.getContentResolver(), blockUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        blockUserAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.USER_BLOCK, contentValues, null, null);
        return new AsyncQueryHandle(blockUserAsyncQueryHandler, blockUserCallback);
    }

    public static final AsyncQueryHandle cancelFriendRequest(Context context, String str, String str2, CancelFriendRequestCallback cancelFriendRequestCallback) {
        CancelFriendRequestAsyncQueryHandler cancelFriendRequestAsyncQueryHandler = new CancelFriendRequestAsyncQueryHandler(context.getContentResolver(), cancelFriendRequestCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("source", str2);
        cancelFriendRequestAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_CANCEL_REQUEST, contentValues, null, null);
        return new AsyncQueryHandle(cancelFriendRequestAsyncQueryHandler, cancelFriendRequestCallback);
    }

    public static void cancelPartyInvite(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        CancelPartyInviteAsyncQueryHandler cancelPartyInviteAsyncQueryHandler = new CancelPartyInviteAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_to_uninvite", str);
        contentValues.put("party_id", str2);
        cancelPartyInviteAsyncQueryHandler.startUpdate(1, str2, HorizonContent.FriendList.PARTY_CANCEL_INVITE, contentValues, null, null);
    }

    public static AsyncQueryHandle createOrUpdateCustomParty(Context context, @Nullable Map<String, String> map, SingleIDCallback singleIDCallback) {
        CreateOrUpdateCustomPartyAsyncQueryHandler createOrUpdateCustomPartyAsyncQueryHandler = new CreateOrUpdateCustomPartyAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(map, createOrUpdateCustomPartyAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$pbFILcGfGmXc9okKnOFelyHsbE2 */
            public final /* synthetic */ Map f$0;
            public final /* synthetic */ HorizonContentProviderHelper.CreateOrUpdateCustomPartyAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$createOrUpdateCustomParty$0(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(createOrUpdateCustomPartyAsyncQueryHandler, singleIDCallback);
    }

    public static AsyncQueryHandle createParty(Context context, @Nullable String str, SingleIDCallback singleIDCallback) {
        CreatePartyAsyncQueryHandler createPartyAsyncQueryHandler = new CreatePartyAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, createPartyAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$2wzzDx1gdQaAEMJwnBFqZBGTvRk2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ HorizonContentProviderHelper.CreatePartyAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$createParty$1(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(createPartyAsyncQueryHandler, singleIDCallback);
    }

    public static void createPartyDEPRECATED(Context context, @Nullable String str, SingleIDCallback singleIDCallback) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, new CreatePartyAsyncQueryHandlerDEPRECATED(context.getContentResolver(), singleIDCallback)) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$2Aer3oqLyYT9uPHRnwT06mpAUrw2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ HorizonContentProviderHelper.CreatePartyAsyncQueryHandlerDEPRECATED f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$createPartyDEPRECATED$2(this.f$0, this.f$1);
            }
        });
    }

    public static final AsyncQueryHandle deactivatePartyLinkInvite(Context context, String str, SingleIDCallback singleIDCallback) {
        DeactivatePartyLinkInviteAsyncQueryHandler deactivatePartyLinkInviteAsyncQueryHandler = new DeactivatePartyLinkInviteAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, deactivatePartyLinkInviteAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$851dQaBIntM1cFVW033qTuDNftI2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ HorizonContentProviderHelper.DeactivatePartyLinkInviteAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$deactivatePartyLinkInvite$10(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(deactivatePartyLinkInviteAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle fetchAppsScoreboardsInfo(Context context, String[] strArr, FetchAppsScoreboardsInfoCallback fetchAppsScoreboardsInfoCallback) {
        FetchAppsScoreboardsInfoAsyncQueryHandler fetchAppsScoreboardsInfoAsyncQueryHandler = new FetchAppsScoreboardsInfoAsyncQueryHandler(context.getContentResolver(), fetchAppsScoreboardsInfoCallback);
        fetchAppsScoreboardsInfoAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_APPS_SCOREBOARDS_INFO_URI.buildUpon().appendQueryParameter("application_ids", TextUtils.join(":", strArr)).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchAppsScoreboardsInfoAsyncQueryHandler, fetchAppsScoreboardsInfoCallback);
    }

    public static final AsyncQueryHandle fetchCurrentPartyDEPRECATED(Context context, FetchCurrentPartyCallBack fetchCurrentPartyCallBack) {
        FetchCurrentPartyAsyncQueryHandlerDEPRECATED fetchCurrentPartyAsyncQueryHandlerDEPRECATED = new FetchCurrentPartyAsyncQueryHandlerDEPRECATED(context.getContentResolver(), fetchCurrentPartyCallBack);
        fetchCurrentPartyAsyncQueryHandlerDEPRECATED.startQuery("", HorizonContent.FriendList.PARTY_GET_CURRENT, null, null, null, null);
        return new AsyncQueryHandle(fetchCurrentPartyAsyncQueryHandlerDEPRECATED, fetchCurrentPartyCallBack);
    }

    public static final AsyncQueryHandle fetchCurrentPartyV2(Context context, FetchCurrentPartyCallBack fetchCurrentPartyCallBack) {
        FetchCurrentPartyAsyncQueryHandlerV2 fetchCurrentPartyAsyncQueryHandlerV2 = new FetchCurrentPartyAsyncQueryHandlerV2(context.getContentResolver(), fetchCurrentPartyCallBack);
        fetchCurrentPartyAsyncQueryHandlerV2.startQuery("", HorizonContent.FriendList.PARTY_GET_CURRENT_V2, null, null, null, null);
        return new AsyncQueryHandle(fetchCurrentPartyAsyncQueryHandlerV2, fetchCurrentPartyCallBack);
    }

    public static final AsyncQueryHandle fetchFriendRequests(Context context, FetchFriendRequestsCallback fetchFriendRequestsCallback) {
        FetchFriendRequestsAsyncQueryHandler fetchFriendRequestsAsyncQueryHandler = new FetchFriendRequestsAsyncQueryHandler(context.getContentResolver(), fetchFriendRequestsCallback);
        fetchFriendRequestsAsyncQueryHandler.startQuery("", HorizonContent.FriendList.FRIEND_REQUESTS_CONTENT_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchFriendRequestsAsyncQueryHandler, fetchFriendRequestsCallback);
    }

    public static final AsyncQueryHandle fetchGroupLaunchAppDestinations(Context context, String str, @Nullable String str2, @Nullable Integer num, FetchGroupLaunchAppDestinationsCallback fetchGroupLaunchAppDestinationsCallback) {
        FetchGroupLaunchAppDestinationsAsyncQueryHandler fetchGroupLaunchAppDestinationsAsyncQueryHandler = new FetchGroupLaunchAppDestinationsAsyncQueryHandler(context.getContentResolver(), fetchGroupLaunchAppDestinationsCallback);
        Uri.Builder appendQueryParameter = HorizonContent.FriendList.GET_GROUP_LAUNCH_APPLICATION_DESTINATIONS_URI.buildUpon().appendQueryParameter("application_id", str);
        if (str2 != null) {
            appendQueryParameter.appendQueryParameter("after", str2);
        }
        if (num != null) {
            appendQueryParameter.appendQueryParameter("first", num.toString());
        }
        fetchGroupLaunchAppDestinationsAsyncQueryHandler.startQuery(1, "", appendQueryParameter.build(), null, null, null, null);
        return new AsyncQueryHandle(fetchGroupLaunchAppDestinationsAsyncQueryHandler, fetchGroupLaunchAppDestinationsCallback);
    }

    public static final AsyncQueryHandle fetchInvitableFriends(Context context, FetchInvitableFriendsCallback fetchInvitableFriendsCallback) {
        FetchInvitableFriendsAsyncQueryHandler fetchInvitableFriendsAsyncQueryHandler = new FetchInvitableFriendsAsyncQueryHandler(context.getContentResolver(), fetchInvitableFriendsCallback);
        fetchInvitableFriendsAsyncQueryHandler.startQuery("", HorizonContent.FriendList.FRIEND_LIST_CONTENT_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchInvitableFriendsAsyncQueryHandler, fetchInvitableFriendsCallback);
    }

    public static final AsyncQueryHandle fetchJoinableParties(Context context, FetchJoinablePartiesCallback fetchJoinablePartiesCallback) {
        FetchJoinablePartiesAsyncQueryHandler fetchJoinablePartiesAsyncQueryHandler = new FetchJoinablePartiesAsyncQueryHandler(context.getContentResolver(), fetchJoinablePartiesCallback);
        fetchJoinablePartiesAsyncQueryHandler.startQuery("", HorizonContent.FriendList.PARTY_GET_JOINABLE_PARTIES_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchJoinablePartiesAsyncQueryHandler, fetchJoinablePartiesCallback);
    }

    public static final AsyncQueryHandle fetchLinkedAccountsInfoForViewer(Context context, FetchLinkedAccountsInfoForViewerCallback fetchLinkedAccountsInfoForViewerCallback) {
        FetchLinkedAccountsInfoForViewerAsyncQueryHandler fetchLinkedAccountsInfoForViewerAsyncQueryHandler = new FetchLinkedAccountsInfoForViewerAsyncQueryHandler(context.getContentResolver(), fetchLinkedAccountsInfoForViewerCallback);
        fetchLinkedAccountsInfoForViewerAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_LINKED_ACCOUNTS_INFO.buildUpon().build(), null, null, null, null);
        return new AsyncQueryHandle(fetchLinkedAccountsInfoForViewerAsyncQueryHandler, fetchLinkedAccountsInfoForViewerCallback);
    }

    public static final AsyncQueryHandle fetchPYMKs(Context context, FetchPYMKsCallback fetchPYMKsCallback) {
        FetchPYMKsAsyncQueryHandler fetchPYMKsAsyncQueryHandler = new FetchPYMKsAsyncQueryHandler(context.getContentResolver(), fetchPYMKsCallback);
        fetchPYMKsAsyncQueryHandler.startQuery("", HorizonContent.FriendList.PYMK_CONTENT_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchPYMKsAsyncQueryHandler, fetchPYMKsCallback);
    }

    public static final AsyncQueryHandle fetchPartyInfoWithActivity(Context context, String str, FetchPartyInfoWithActivityCallback fetchPartyInfoWithActivityCallback) {
        FetchPartyInfoWithActivityAsyncQueryHandler fetchPartyInfoWithActivityAsyncQueryHandler = new FetchPartyInfoWithActivityAsyncQueryHandler(context.getContentResolver(), fetchPartyInfoWithActivityCallback);
        fetchPartyInfoWithActivityAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_INFO_WITH_ACTIVITY.buildUpon().appendQueryParameter("party_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPartyInfoWithActivityAsyncQueryHandler, fetchPartyInfoWithActivityCallback);
    }

    public static final AsyncQueryHandle fetchPartyInviteInfo(Context context, String str, FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
        FetchPartyInviteInfoAsyncQueryHandler fetchPartyInviteInfoAsyncQueryHandler = new FetchPartyInviteInfoAsyncQueryHandler(context.getContentResolver(), fetchPartyInviteInfoCallback);
        fetchPartyInviteInfoAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_INVITE_INFO.buildUpon().appendQueryParameter("party_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPartyInviteInfoAsyncQueryHandler, fetchPartyInviteInfoCallback);
    }

    public static AsyncQueryHandle fetchPartyInviteInfoWithNonce(Context context, String str, String str2, FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
        FetchPartyInviteInfoNonceAsyncQueryHandler fetchPartyInviteInfoNonceAsyncQueryHandler = new FetchPartyInviteInfoNonceAsyncQueryHandler(context.getContentResolver(), fetchPartyInviteInfoCallback);
        fetchPartyInviteInfoNonceAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_INVITE_INFO_WITH_NONCE.buildUpon().appendQueryParameter("deeplink_target_id", str).appendQueryParameter("link_nonce", str2).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPartyInviteInfoNonceAsyncQueryHandler, fetchPartyInviteInfoCallback);
    }

    public static final AsyncQueryHandle fetchPartyInvites(Context context, FetchPartyInvitesCallBack fetchPartyInvitesCallBack) {
        FetchPartyInvitesAsyncQueryHandler fetchPartyInvitesAsyncQueryHandler = new FetchPartyInvitesAsyncQueryHandler(context.getContentResolver(), fetchPartyInvitesCallBack);
        fetchPartyInvitesAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_INVITES, null, null, null, null);
        return new AsyncQueryHandle(fetchPartyInvitesAsyncQueryHandler, fetchPartyInvitesCallBack);
    }

    public static final AsyncQueryHandle fetchPartyPrivacyInfo(Context context, String str, FetchPartyPrivacyInfoCallback fetchPartyPrivacyInfoCallback) {
        FetchPartyPrivacyInfoAsyncQueryHandler fetchPartyPrivacyInfoAsyncQueryHandler = new FetchPartyPrivacyInfoAsyncQueryHandler(context.getContentResolver(), fetchPartyPrivacyInfoCallback);
        fetchPartyPrivacyInfoAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_PRIVACY_INFO.buildUpon().appendQueryParameter("party_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPartyPrivacyInfoAsyncQueryHandler, fetchPartyPrivacyInfoCallback);
    }

    public static final AsyncQueryHandle fetchPeopleNearby(Context context, @Nullable Integer num, FetchPeopleNearbyCallback fetchPeopleNearbyCallback) {
        FetchPeopleNearbyAsyncQueryHandler fetchPeopleNearbyAsyncQueryHandler = new FetchPeopleNearbyAsyncQueryHandler(context.getContentResolver(), fetchPeopleNearbyCallback);
        Uri.Builder buildUpon = HorizonContent.FriendList.PEOPLE_NEARBY_CONTENT_URI.buildUpon();
        if (num != null) {
            buildUpon.appendQueryParameter("first", num.toString());
        }
        fetchPeopleNearbyAsyncQueryHandler.startQuery("", buildUpon.build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPeopleNearbyAsyncQueryHandler, fetchPeopleNearbyCallback);
    }

    public static final AsyncQueryHandle fetchProfileContent(Context context, FetchProfileContentCallback fetchProfileContentCallback) {
        FetchProfileContentAsyncQueryHandler fetchProfileContentAsyncQueryHandler = new FetchProfileContentAsyncQueryHandler(context.getContentResolver(), fetchProfileContentCallback);
        fetchProfileContentAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PROFILE_CONTENT, null, null, null, null);
        return new AsyncQueryHandle(fetchProfileContentAsyncQueryHandler, fetchProfileContentCallback);
    }

    public static final AsyncQueryHandle fetchRosterSize(Context context, FetchRosterSizeCallback fetchRosterSizeCallback) {
        FetchRosterSizeAsyncQueryHandler fetchRosterSizeAsyncQueryHandler = new FetchRosterSizeAsyncQueryHandler(context.getContentResolver(), fetchRosterSizeCallback);
        fetchRosterSizeAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_ROSTER_SIZE, null, null, null, null);
        return new AsyncQueryHandle(fetchRosterSizeAsyncQueryHandler, fetchRosterSizeCallback);
    }

    public static final AsyncQueryHandle fetchSocialFriends(Context context, @Nullable String[] strArr, @Nullable Integer num, FetchSocialFriendsCallback fetchSocialFriendsCallback) {
        FetchSocialFriendsAsyncQueryHandler fetchSocialFriendsAsyncQueryHandler = new FetchSocialFriendsAsyncQueryHandler(context.getContentResolver(), fetchSocialFriendsCallback);
        Uri.Builder buildUpon = HorizonContent.FriendList.SOCIAL_FRIENDS_URI.buildUpon();
        if (strArr != null) {
            buildUpon.appendQueryParameter("orderby", TextUtils.join(",", strArr));
        }
        if (num != null) {
            buildUpon.appendQueryParameter("first", num.toString());
        }
        buildUpon.appendQueryParameter("ignore_rich_presence", "false");
        fetchSocialFriendsAsyncQueryHandler.startQuery("", buildUpon.build(), null, null, null, null);
        return new AsyncQueryHandle(fetchSocialFriendsAsyncQueryHandler, fetchSocialFriendsCallback);
    }

    public static final AsyncQueryHandle fetchSocialFriendsWithoutRichPresence(Context context, @Nullable String[] strArr, @Nullable Integer num, FetchSocialFriendsCallback fetchSocialFriendsCallback) {
        FetchSocialFriendsAsyncQueryHandler fetchSocialFriendsAsyncQueryHandler = new FetchSocialFriendsAsyncQueryHandler(context.getContentResolver(), fetchSocialFriendsCallback);
        Uri.Builder buildUpon = HorizonContent.FriendList.SOCIAL_FRIENDS_URI.buildUpon();
        if (strArr != null) {
            buildUpon.appendQueryParameter("orderby", TextUtils.join(",", strArr));
        }
        if (num != null) {
            buildUpon.appendQueryParameter("first", num.toString());
        }
        buildUpon.appendQueryParameter("ignore_rich_presence", "true");
        fetchSocialFriendsAsyncQueryHandler.startQuery("", buildUpon.build(), null, null, null, null);
        return new AsyncQueryHandle(fetchSocialFriendsAsyncQueryHandler, fetchSocialFriendsCallback);
    }

    public static final AsyncQueryHandle fetchSocialViewer(Context context, FetchSocialViewerInfoCallback fetchSocialViewerInfoCallback) {
        FetchSocialViewerAsyncQueryHandler fetchSocialViewerAsyncQueryHandler = new FetchSocialViewerAsyncQueryHandler(context.getContentResolver(), fetchSocialViewerInfoCallback);
        fetchSocialViewerAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_SOCIAL_VIEWER, null, null, null, null);
        return new AsyncQueryHandle(fetchSocialViewerAsyncQueryHandler, fetchSocialViewerInfoCallback);
    }

    public static final AsyncQueryHandle fetchVRProfileContent(Context context, String str, FetchVRProfileContentCallback fetchVRProfileContentCallback) {
        FetchVRProfileContentAsyncQueryHandler fetchVRProfileContentAsyncQueryHandler = new FetchVRProfileContentAsyncQueryHandler(context.getContentResolver(), fetchVRProfileContentCallback);
        fetchVRProfileContentAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_VR_PROFILE_CONTENT.buildUpon().appendQueryParameter("id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchVRProfileContentAsyncQueryHandler, fetchVRProfileContentCallback);
    }

    public static final AsyncQueryHandle getBlockedUserId(Context context, String str, GetBlockedUserIdCallback getBlockedUserIdCallback) {
        GetBlockedUserIdAsyncQueryHandler getBlockedUserIdAsyncQueryHandler = new GetBlockedUserIdAsyncQueryHandler(context.getContentResolver(), getBlockedUserIdCallback);
        getBlockedUserIdAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_BLOCKED_USER_ID.buildUpon().appendQueryParameter("user_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(getBlockedUserIdAsyncQueryHandler, getBlockedUserIdCallback);
    }

    public static final AsyncQueryHandle getFbFriendPrimaryProfile(Context context, String str, GetFbFriendPrimaryProfileCallback getFbFriendPrimaryProfileCallback) {
        GetFbFriendPrimaryProfileAsyncQueryHandler getFbFriendPrimaryProfileAsyncQueryHandler = new GetFbFriendPrimaryProfileAsyncQueryHandler(context, context.getContentResolver(), getFbFriendPrimaryProfileCallback);
        getFbFriendPrimaryProfileAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_FB_FRIEND_PRIMARY_PROFILE.buildUpon().appendQueryParameter("fb_friend_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(getFbFriendPrimaryProfileAsyncQueryHandler, getFbFriendPrimaryProfileCallback);
    }

    public static List<SocialGroupLaunchApp> getGroupLaunchSupportedApplicationsFromCursor(Context context, Cursor cursor) {
        Map map = (Map) HorizonUtil.getApplications(context).stream().collect(Collectors.toMap($$Lambda$HorizonContentProviderHelper$_5x3ACK4rKvFCVsGTVNmmGpZdNQ2.INSTANCE, $$Lambda$HorizonContentProviderHelper$YUY04L0xvTrjlkUWkc4vAPBxL642.INSTANCE));
        ArrayList arrayList = new ArrayList();
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("application_id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("application_display_short_description");
        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("application_max_group_launch_capacity");
        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("application_party_user_entitlement_count");
        int columnIndexOrThrow5 = cursor.getColumnIndexOrThrow("party_current_users");
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            String string = cursor.getString(columnIndexOrThrow);
            if (string != null && map.containsKey(string)) {
                String string2 = cursor.getString(columnIndexOrThrow2);
                int i = cursor.getInt(columnIndexOrThrow3);
                int i2 = cursor.getInt(columnIndexOrThrow4);
                int i3 = cursor.getInt(columnIndexOrThrow5);
                App app = (App) map.get(string);
                if (app != null) {
                    arrayList.add(new SocialGroupLaunchApp(app.id, app.displayName, string2, app.images.get(Image.ImageName.SOURCE_MAIN).uri, app.images.get(Image.ImageName.SOURCE_SQUARE).uri, i, i2, i3));
                }
            }
        }
        return arrayList;
    }

    public static final AsyncQueryHandle getHasParticipantRecentlySpoken(Context context, String str, FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback) {
        GetHasParticipantRecentlySpokenAsyncQueryHandler getHasParticipantRecentlySpokenAsyncQueryHandler = new GetHasParticipantRecentlySpokenAsyncQueryHandler(context.getContentResolver(), fetchParticipantSpeakerStatusCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, getHasParticipantRecentlySpokenAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$atTEfgzEqN4a5hTDJev2yM8qKgQ2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ HorizonContentProviderHelper.GetHasParticipantRecentlySpokenAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                this.f$1.startQuery("", HorizonContent.FriendList.GET_PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN.buildUpon().appendQueryParameter("user_id", this.f$0).build(), null, null, null, null);
            }
        });
        return new AsyncQueryHandle(getHasParticipantRecentlySpokenAsyncQueryHandler, fetchParticipantSpeakerStatusCallback);
    }

    public static boolean getIsCurrentRowNextResultTypeIndicator(Cursor cursor, int i) {
        if (cursor.getString(i) == null) {
            return false;
        }
        if (cursor.getString(i).equals("INVITED") || cursor.getString(i).equals("BLOCKED_MEMBERS") || cursor.getString(i).equals("INVITED_BLOCKED_USERS")) {
            return true;
        }
        return false;
    }

    public static LinkedAccountsInfo getLinkedAccountsInfoForViewerFromCursor(Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return null;
        }
        return new LinkedAccountsInfo(CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("fb_linked_status"), ""));
    }

    public static final AsyncQueryHandle getPartyMicrophoneInputLocation(Context context, GetPartyMicrophoneInputLocationCallback getPartyMicrophoneInputLocationCallback) {
        GetPartyMicrophoneInputLocationAsyncQueryHandler getPartyMicrophoneInputLocationAsyncQueryHandler = new GetPartyMicrophoneInputLocationAsyncQueryHandler(context.getContentResolver(), getPartyMicrophoneInputLocationCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback() {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$lx7Kn2F2tkVK2wByQ0qEg7edpfQ2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.GetPartyMicrophoneInputLocationAsyncQueryHandler.this.startQuery("", HorizonContent.FriendList.PARTY_GET_MICROPHONE_INPUT_LOCATION.buildUpon().build(), null, null, null, null);
            }
        });
        return new AsyncQueryHandle(getPartyMicrophoneInputLocationAsyncQueryHandler, getPartyMicrophoneInputLocationCallback);
    }

    public static final AsyncQueryHandle getPartyMicrophoneState(Context context, GetPartyMicrophoneStateCallback getPartyMicrophoneStateCallback) {
        GetPartyMicrophoneStateAsyncQueryHandler getPartyMicrophoneStateAsyncQueryHandler = new GetPartyMicrophoneStateAsyncQueryHandler(context.getContentResolver(), getPartyMicrophoneStateCallback);
        getPartyMicrophoneStateAsyncQueryHandler.startQuery("", HorizonContent.FriendList.PARTY_GET_MICROPHONE_STATE.buildUpon().build(), null, null, null, null);
        return new AsyncQueryHandle(getPartyMicrophoneStateAsyncQueryHandler, getPartyMicrophoneStateCallback);
    }

    public static final void hidePYMKUser(Context context, String str, HidePYMKUserCallback hidePYMKUserCallback) {
        HidePYMKUserAsyncQueryHandler hidePYMKUserAsyncQueryHandler = new HidePYMKUserAsyncQueryHandler(context.getContentResolver(), hidePYMKUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", str);
        hidePYMKUserAsyncQueryHandler.startUpdate(1, str, HorizonContent.FriendList.PYMK_HIDE_USER, contentValues, null, null);
    }

    public static AsyncQueryHandle inviteUsersToParty(Context context, List<String> list, String str, MultipleIDCallback multipleIDCallback) {
        InviteUsersToPartyAsyncQueryHandler inviteUsersToPartyAsyncQueryHandler = new InviteUsersToPartyAsyncQueryHandler(context.getContentResolver(), multipleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("user_ids", TextUtils.join(":", list));
        inviteUsersToPartyAsyncQueryHandler.startUpdate(1, list, HorizonContent.FriendList.PARTY_INVITE_USERS_CONTENT_URI, contentValues, null, null);
        return new AsyncQueryHandle(inviteUsersToPartyAsyncQueryHandler, multipleIDCallback);
    }

    public static final void kickUserFromParty(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str2, str, new KickUserFromPartyAsyncQueryHandler(context.getContentResolver(), singleIDCallback)) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$6yC0HeKHXI_ZCSv19YuBbbzNXI2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ String f$1;
            public final /* synthetic */ HorizonContentProviderHelper.KickUserFromPartyAsyncQueryHandler f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$kickUserFromParty$11(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ void lambda$setPartyLocalMuteAsync$15(int i, Context context, MuteStateCallback muteStateCallback) {
        HorizonContent.PlatformPluginManager.MicrophoneMuteState.validate(i);
        SetPartyLocalMuteAsyncQueryHandler setPartyLocalMuteAsyncQueryHandler = new SetPartyLocalMuteAsyncQueryHandler(context.getContentResolver(), muteStateCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_self_mute", Integer.valueOf(i));
        setPartyLocalMuteAsyncQueryHandler.startUpdate(1, "", HorizonContent.FriendList.PARTY_SET_SELF_MUTE, contentValues, null, null);
    }

    public static final AsyncQueryHandle launchGroupLaunch(Context context, String str, SingleIDCallback singleIDCallback) {
        LaunchGroupLaunchAsyncQueryHandler launchGroupLaunchAsyncQueryHandler = new LaunchGroupLaunchAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_launch_id", str);
        launchGroupLaunchAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.GROUP_LAUNCH_LAUNCH_HANDLE, contentValues, null, null);
        return new AsyncQueryHandle(launchGroupLaunchAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle launchGroupLaunchSolo(Context context, String str, SingleIDCallback singleIDCallback) {
        LaunchGroupLaunchAsyncQueryHandler launchGroupLaunchAsyncQueryHandler = new LaunchGroupLaunchAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_launch_id", str);
        launchGroupLaunchAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.GROUP_LAUNCH_SOLO_LAUNCH_HANDLE, contentValues, null, null);
        return new AsyncQueryHandle(launchGroupLaunchAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle leaveParty(Context context, String str, SingleIDCallback singleIDCallback) {
        LeavePartyAsyncQueryHandler leavePartyAsyncQueryHandler = new LeavePartyAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, leavePartyAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$qYzfC4xpA9icqhVC1wKoVTneceM2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ HorizonContentProviderHelper.LeavePartyAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$leaveParty$6(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(leavePartyAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle messageSendToThread(Context context, String str, String str2, MessageSendToThreadCallback messageSendToThreadCallback) {
        MessageSendToThreadAsyncQueryHandler messageSendToThreadAsyncQueryHandler = new MessageSendToThreadAsyncQueryHandler(context.getContentResolver(), messageSendToThreadCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("thread_id", str);
        contentValues.put("body", str2);
        messageSendToThreadAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.MESSAGE_SEND_TO_THREAD, contentValues, null, null);
        return new AsyncQueryHandle(messageSendToThreadAsyncQueryHandler, messageSendToThreadCallback);
    }

    public static final AsyncQueryHandle prepareGroupLaunch(Context context, String str, SingleIDCallback singleIDCallback) {
        PrepareGroupLaunchAsyncQueryHandler prepareGroupLaunchAsyncQueryHandler = new PrepareGroupLaunchAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_launch_id", str);
        contentValues.put("group_launch_state", SocialGroupLaunchState.PREPARING.toString());
        prepareGroupLaunchAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.GROUP_LAUNCH_SET_STATE, contentValues, null, null);
        return new AsyncQueryHandle(prepareGroupLaunchAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle rejectFriendRequest(Context context, String str, RejectFriendRequestCallback rejectFriendRequestCallback) {
        RejectFriendRequestAsyncQueryHandler rejectFriendRequestAsyncQueryHandler = new RejectFriendRequestAsyncQueryHandler(context.getContentResolver(), rejectFriendRequestCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        rejectFriendRequestAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_REJECT_REQUEST, contentValues, null, null);
        return new AsyncQueryHandle(rejectFriendRequestAsyncQueryHandler, rejectFriendRequestCallback);
    }

    public static final AsyncQueryHandle removePartyGroupLaunchDestination(Context context, String str, SingleIDCallback singleIDCallback) {
        RemovePartyGroupLaunchDestinationAsyncQueryHandler removePartyGroupLaunchDestinationAsyncQueryHandler = new RemovePartyGroupLaunchDestinationAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        removePartyGroupLaunchDestinationAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE, contentValues, null, null);
        return new AsyncQueryHandle(removePartyGroupLaunchDestinationAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle searchUsers(String str, String str2, Context context, UserSearchCallback userSearchCallback) {
        SearchUsersAsyncQueryHandler searchUsersAsyncQueryHandler = new SearchUsersAsyncQueryHandler(context.getContentResolver(), userSearchCallback);
        searchUsersAsyncQueryHandler.startQuery("", HorizonContent.FriendList.USER_SEARCH.buildUpon().appendQueryParameter("query_string", str).appendQueryParameter("search_mode", str2).build(), null, null, null, null);
        return new AsyncQueryHandle(searchUsersAsyncQueryHandler, userSearchCallback);
    }

    public static final AsyncQueryHandle sendFriendRequest(Context context, String str, String str2, SendFriendRequestCallback sendFriendRequestCallback) {
        SendFriendRequestAsyncQueryHandler sendFriendRequestAsyncQueryHandler = new SendFriendRequestAsyncQueryHandler(context.getContentResolver(), sendFriendRequestCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("source", str2);
        sendFriendRequestAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_SEND_REQUEST, contentValues, null, null);
        return new AsyncQueryHandle(sendFriendRequestAsyncQueryHandler, sendFriendRequestCallback);
    }

    public static final AsyncQueryHandle sendMicrophoneInputToApp(Context context, SingleIDCallback singleIDCallback) throws IllegalArgumentException {
        SwitchMicrophoneInputAsyncQueryHandler switchMicrophoneInputAsyncQueryHandler = new SwitchMicrophoneInputAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback() {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$xXD2R3a49lSb2RXln9SFd_B6ew2 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.SwitchMicrophoneInputAsyncQueryHandler.this.startUpdate(1, "", HorizonContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_APP, new ContentValues(), null, null);
            }
        });
        return new AsyncQueryHandle(switchMicrophoneInputAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle sendMicrophoneInputToParty(Context context, SingleIDCallback singleIDCallback) throws IllegalArgumentException {
        SwitchMicrophoneInputAsyncQueryHandler switchMicrophoneInputAsyncQueryHandler = new SwitchMicrophoneInputAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback() {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$MIUfDHlOLJEBnvPb4OVBMrg_l82 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.SwitchMicrophoneInputAsyncQueryHandler.this.startUpdate(1, "", HorizonContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_PARTY, new ContentValues(), null, null);
            }
        });
        return new AsyncQueryHandle(switchMicrophoneInputAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle setGroupLaunchResponse(Context context, String str, SocialGroupLaunchResponse socialGroupLaunchResponse, SingleIDCallback singleIDCallback) {
        SetGroupLaunchResponseAsyncQueryHandler setGroupLaunchResponseAsyncQueryHandler = new SetGroupLaunchResponseAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_launch_id", str);
        contentValues.put("response", socialGroupLaunchResponse.toString());
        setGroupLaunchResponseAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.GROUP_LAUNCH_SET_USER_RESPONSE, contentValues, null, null);
        return new AsyncQueryHandle(setGroupLaunchResponseAsyncQueryHandler, singleIDCallback);
    }

    public static AsyncQueryHandle setNuxFlagForUser(Context context, String str, boolean z, SetNuxFlagForUserCallback setNuxFlagForUserCallback) {
        String str2;
        SetNuxFlagForUserAsyncQueryHandler setNuxFlagForUserAsyncQueryHandler = new SetNuxFlagForUserAsyncQueryHandler(context.getContentResolver(), setNuxFlagForUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("nux_flag", str);
        if (z) {
            str2 = "true";
        } else {
            str2 = "false";
        }
        contentValues.put("nux_value", str2);
        setNuxFlagForUserAsyncQueryHandler.startUpdate(1, HorizonContent.FriendList.SET_NUX_FLAG_FOR_USER, contentValues, null, null);
        return new AsyncQueryHandle(setNuxFlagForUserAsyncQueryHandler, setNuxFlagForUserCallback);
    }

    public static final AsyncQueryHandle setPartyGroupLaunchDestination(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        SetPartyGroupLaunchDestinationAsyncQueryHandler setPartyGroupLaunchDestinationAsyncQueryHandler = new SetPartyGroupLaunchDestinationAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("destination_id", str2);
        setPartyGroupLaunchDestinationAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE, contentValues, null, null);
        return new AsyncQueryHandle(setPartyGroupLaunchDestinationAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle setPartyMicrophoneState(Context context, PartyMicrophoneState partyMicrophoneState, SingleIDCallback singleIDCallback) {
        String obj;
        SetPartyMicrophoneStateAsyncQueryHandler setPartyMicrophoneStateAsyncQueryHandler = new SetPartyMicrophoneStateAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        if (partyMicrophoneState == null) {
            obj = PartyMicrophoneState.UNKNOWN.toString();
        } else {
            obj = partyMicrophoneState.toString();
        }
        contentValues.put("party_mic_state", obj);
        setPartyMicrophoneStateAsyncQueryHandler.startUpdate(1, "", HorizonContent.FriendList.PARTY_SET_MICROPHONE_STATE, contentValues, null, null);
        return new AsyncQueryHandle(setPartyMicrophoneStateAsyncQueryHandler, singleIDCallback);
    }

    public static final void setPartyPerPersonMuteStatusAsync(Context context, String str, int i, PerPersonMuteStateCallback perPersonMuteStateCallback) throws IllegalArgumentException {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, i, new SetPartyPerPersonMuteAsyncQueryHandler(context.getContentResolver(), perPersonMuteStateCallback)) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$X0XrN8ofySvhevI9nI2m0N0Mk2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ int f$1;
            public final /* synthetic */ HorizonContentProviderHelper.SetPartyPerPersonMuteAsyncQueryHandler f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyPerPersonMuteStatusAsync$16(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    public static final AsyncQueryHandle setPartyType(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        SetPartyTypeAsyncQueryHandler setPartyTypeAsyncQueryHandler = new SetPartyTypeAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, str2, setPartyTypeAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$WqcZTKHIVuy_PfevfEmgUUzXwK42 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ String f$1;
            public final /* synthetic */ HorizonContentProviderHelper.SetPartyTypeAsyncQueryHandler f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyType$8(this.f$0, this.f$1, this.f$2);
            }
        });
        return new AsyncQueryHandle(setPartyTypeAsyncQueryHandler, singleIDCallback);
    }

    public static final void setPartyTypeDEPRECATED(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, str2, new SetPartyTypeAsyncQueryHandlerDEPRECATED(context.getContentResolver(), singleIDCallback)) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$RNlU39Jep4RE76SMm7Qi0OnbVA2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ String f$1;
            public final /* synthetic */ HorizonContentProviderHelper.SetPartyTypeAsyncQueryHandlerDEPRECATED f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyTypeDEPRECATED$7(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    public static final AsyncQueryHandle setUserBio(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        SetUserBioAsyncQueryHandler setUserBioAsyncQueryHandler = new SetUserBioAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("biography", str2);
        setUserBioAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PROFILE_SET_BIO, contentValues, null, null);
        return new AsyncQueryHandle(setUserBioAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle shareParty(Context context, SharePartyCallBack sharePartyCallBack) {
        SharePartyAsyncQueryHandler sharePartyAsyncQueryHandler = new SharePartyAsyncQueryHandler(context.getContentResolver(), sharePartyCallBack);
        sharePartyAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_FBID, null, null, null, null);
        return new AsyncQueryHandle(sharePartyAsyncQueryHandler, sharePartyCallBack);
    }

    public static final AsyncQueryHandle unblockUser(Context context, String str, UnblockUserCallback unblockUserCallback) {
        UnblockUserAsyncQueryHandler unblockUserAsyncQueryHandler = new UnblockUserAsyncQueryHandler(context.getContentResolver(), unblockUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("blocked_user_id", str);
        unblockUserAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.USER_UNBLOCK, contentValues, null, null);
        return new AsyncQueryHandle(unblockUserAsyncQueryHandler, unblockUserCallback);
    }

    public static final AsyncQueryHandle unfriendUser(Context context, String str, UnfriendUserCallback unfriendUserCallback) {
        UnfriendUserAsyncQueryHandler unfriendUserAsyncQueryHandler = new UnfriendUserAsyncQueryHandler(context.getContentResolver(), unfriendUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("friend", str);
        unfriendUserAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_REMOVE, contentValues, null, null);
        return new AsyncQueryHandle(unfriendUserAsyncQueryHandler, unfriendUserCallback);
    }

    public static final class AcceptFriendRequestAsyncQueryHandler extends AsyncQueryHandlerBase<AcceptFriendRequestCallback> {
        public AcceptFriendRequestAsyncQueryHandler(ContentResolver contentResolver, AcceptFriendRequestCallback acceptFriendRequestCallback) {
            super(contentResolver, acceptFriendRequestCallback);
        }

        public void onUpdateComplete(AcceptFriendRequestCallback acceptFriendRequestCallback, Object obj, int i) {
            if (i == 1) {
                acceptFriendRequestCallback.onSuccess((String) obj);
            } else {
                acceptFriendRequestCallback.onError();
            }
        }
    }

    public static final class ActivatePartyLinkInviteAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public ActivatePartyLinkInviteAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class BlockUserAsyncQueryHandler extends AsyncQueryHandlerBase<BlockUserCallback> {
        public BlockUserAsyncQueryHandler(ContentResolver contentResolver, BlockUserCallback blockUserCallback) {
            super(contentResolver, blockUserCallback);
        }

        public void onUpdateComplete(BlockUserCallback blockUserCallback, Object obj, int i) {
            if (i == 1) {
                blockUserCallback.onSuccess((String) obj);
            } else {
                blockUserCallback.onError();
            }
        }
    }

    public static final class CancelFriendRequestAsyncQueryHandler extends AsyncQueryHandlerBase<CancelFriendRequestCallback> {
        public CancelFriendRequestAsyncQueryHandler(ContentResolver contentResolver, CancelFriendRequestCallback cancelFriendRequestCallback) {
            super(contentResolver, cancelFriendRequestCallback);
        }

        public void onUpdateComplete(CancelFriendRequestCallback cancelFriendRequestCallback, Object obj, int i) {
            if (i == 1) {
                cancelFriendRequestCallback.onSuccess((String) obj);
            } else {
                cancelFriendRequestCallback.onError();
            }
        }
    }

    public static final class CreateOrUpdateCustomPartyAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public CreateOrUpdateCustomPartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onQueryComplete(SingleIDCallback singleIDCallback, Cursor cursor) {
            String string;
            if (cursor != null) {
                try {
                    if (cursor.moveToNext() && (string = cursor.getString(cursor.getColumnIndexOrThrow("current_party_id"))) != null) {
                        singleIDCallback.onSuccess(string);
                        return;
                    }
                } catch (Exception unused) {
                    singleIDCallback.onError();
                    return;
                }
            }
            singleIDCallback.onError();
        }
    }

    public static final class CreatePartyAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public CreatePartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onQueryComplete(SingleIDCallback singleIDCallback, Cursor cursor) {
            String string;
            if (cursor != null) {
                try {
                    if (cursor.moveToNext() && (string = cursor.getString(cursor.getColumnIndexOrThrow("current_party_id"))) != null) {
                        singleIDCallback.onSuccess(string);
                        return;
                    }
                } catch (Exception unused) {
                    singleIDCallback.onError();
                    return;
                }
            }
            singleIDCallback.onError();
        }
    }

    public static final class DeactivatePartyLinkInviteAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public DeactivatePartyLinkInviteAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class FetchAppsScoreboardsInfoAsyncQueryHandler extends AsyncQueryHandlerBase<FetchAppsScoreboardsInfoCallback> {
        public FetchAppsScoreboardsInfoAsyncQueryHandler(ContentResolver contentResolver, FetchAppsScoreboardsInfoCallback fetchAppsScoreboardsInfoCallback) {
            super(contentResolver, fetchAppsScoreboardsInfoCallback);
        }

        public void onQueryComplete(FetchAppsScoreboardsInfoCallback fetchAppsScoreboardsInfoCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    ArrayList arrayList = new ArrayList();
                    int columnIndexOrThrow = cursor.getColumnIndexOrThrow("appId");
                    int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("hasAchievements");
                    int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("hasLeaderboards");
                    cursor.moveToPosition(-1);
                    while (cursor.moveToNext()) {
                        arrayList.add(new AppScoreboardsInfo(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow, ""), "true".equals(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, "")), "true".equals(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, ""))));
                    }
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchAppsScoreboardsInfoCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchCurrentPartyAsyncQueryHandlerDEPRECATED extends AsyncQueryHandlerBase<FetchCurrentPartyCallBack> {
        public FetchCurrentPartyAsyncQueryHandlerDEPRECATED(ContentResolver contentResolver, FetchCurrentPartyCallBack fetchCurrentPartyCallBack) {
            super(contentResolver, fetchCurrentPartyCallBack);
        }

        public void onQueryComplete(FetchCurrentPartyCallBack fetchCurrentPartyCallBack, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchCurrentPartyCallBack.onSuccess(HorizonContentProviderHelper.getCurrentPartyDataFromCursorDEPRECATED(cursor));
                } catch (Exception e) {
                    e.toString();
                    fetchCurrentPartyCallBack.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchCurrentPartyAsyncQueryHandlerV2 extends AsyncQueryHandlerBase<FetchCurrentPartyCallBack> {
        public FetchCurrentPartyAsyncQueryHandlerV2(ContentResolver contentResolver, FetchCurrentPartyCallBack fetchCurrentPartyCallBack) {
            super(contentResolver, fetchCurrentPartyCallBack);
        }

        public void onQueryComplete(FetchCurrentPartyCallBack fetchCurrentPartyCallBack, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchCurrentPartyCallBack.onSuccess(HorizonContentProviderHelper.getCurrentPartyDataFromCursorV2(cursor));
                } catch (Exception e) {
                    e.toString();
                    fetchCurrentPartyCallBack.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchFriendRequestsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchFriendRequestsCallback> {
        public FetchFriendRequestsAsyncQueryHandler(ContentResolver contentResolver, FetchFriendRequestsCallback fetchFriendRequestsCallback) {
            super(contentResolver, fetchFriendRequestsCallback);
        }

        public void onQueryComplete(FetchFriendRequestsCallback fetchFriendRequestsCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchFriendRequestsCallback.onSuccess(HorizonContentProviderHelper.getFriendRequestDataFromCursor(cursor));
                } catch (Exception unused) {
                    fetchFriendRequestsCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchGroupLaunchAppDestinationsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchGroupLaunchAppDestinationsCallback> {
        public FetchGroupLaunchAppDestinationsAsyncQueryHandler(ContentResolver contentResolver, FetchGroupLaunchAppDestinationsCallback fetchGroupLaunchAppDestinationsCallback) {
            super(contentResolver, fetchGroupLaunchAppDestinationsCallback);
        }

        public void onQueryComplete(FetchGroupLaunchAppDestinationsCallback fetchGroupLaunchAppDestinationsCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchGroupLaunchAppDestinationsCallback.onSuccess(HorizonContentProviderHelper.getGroupLaunchAppDestinationsFromCursor(cursor));
                } catch (Exception unused) {
                    fetchGroupLaunchAppDestinationsCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchInvitableFriendsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchInvitableFriendsCallback> {
        public FetchInvitableFriendsAsyncQueryHandler(ContentResolver contentResolver, FetchInvitableFriendsCallback fetchInvitableFriendsCallback) {
            super(contentResolver, fetchInvitableFriendsCallback);
        }

        public void onQueryComplete(FetchInvitableFriendsCallback fetchInvitableFriendsCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    HorizonContentProviderHelper.getFriendDataFromCursor(cursor);
                    HorizonContentProviderHelper.getPartyIDFromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchInvitableFriendsCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchJoinablePartiesAsyncQueryHandler extends AsyncQueryHandlerBase<FetchJoinablePartiesCallback> {
        public FetchJoinablePartiesAsyncQueryHandler(ContentResolver contentResolver, FetchJoinablePartiesCallback fetchJoinablePartiesCallback) {
            super(contentResolver, fetchJoinablePartiesCallback);
        }

        public void onQueryComplete(FetchJoinablePartiesCallback fetchJoinablePartiesCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchJoinablePartiesCallback.onSuccess(HorizonContentProviderHelper.getJoinablePartiesDataFromCursor(cursor));
                } catch (Exception unused) {
                    fetchJoinablePartiesCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchLinkedAccountsInfoForViewerAsyncQueryHandler extends AsyncQueryHandlerBase<FetchLinkedAccountsInfoForViewerCallback> {
        public FetchLinkedAccountsInfoForViewerAsyncQueryHandler(ContentResolver contentResolver, FetchLinkedAccountsInfoForViewerCallback fetchLinkedAccountsInfoForViewerCallback) {
            super(contentResolver, fetchLinkedAccountsInfoForViewerCallback);
        }

        public void onQueryComplete(FetchLinkedAccountsInfoForViewerCallback fetchLinkedAccountsInfoForViewerCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchLinkedAccountsInfoForViewerCallback.onSuccess(HorizonContentProviderHelper.getLinkedAccountsInfoForViewerFromCursor(cursor));
                } catch (Exception unused) {
                    fetchLinkedAccountsInfoForViewerCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchPYMKsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPYMKsCallback> {
        public FetchPYMKsAsyncQueryHandler(ContentResolver contentResolver, FetchPYMKsCallback fetchPYMKsCallback) {
            super(contentResolver, fetchPYMKsCallback);
        }

        public void onQueryComplete(FetchPYMKsCallback fetchPYMKsCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchPYMKsCallback.onSuccess(HorizonContentProviderHelper.getPYMKDataFromCursor(cursor));
                } catch (Exception unused) {
                    fetchPYMKsCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchPartyInfoWithActivityAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyInfoWithActivityCallback> {
        public FetchPartyInfoWithActivityAsyncQueryHandler(ContentResolver contentResolver, FetchPartyInfoWithActivityCallback fetchPartyInfoWithActivityCallback) {
            super(contentResolver, fetchPartyInfoWithActivityCallback);
        }

        public void onQueryComplete(FetchPartyInfoWithActivityCallback fetchPartyInfoWithActivityCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    HorizonContentProviderHelper.getPartyInfoWithActivityFromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchPartyInfoWithActivityCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchPartyInviteInfoAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyInviteInfoCallback> {
        public FetchPartyInviteInfoAsyncQueryHandler(ContentResolver contentResolver, FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
            super(contentResolver, fetchPartyInviteInfoCallback);
        }

        public void onQueryComplete(FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    PartyInviteInfo.fromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchPartyInviteInfoCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchPartyInviteInfoNonceAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyInviteInfoCallback> {
        public FetchPartyInviteInfoNonceAsyncQueryHandler(ContentResolver contentResolver, FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
            super(contentResolver, fetchPartyInviteInfoCallback);
        }

        public void onQueryComplete(FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    PartyInviteInfo.fromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchPartyInviteInfoCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchPartyInvitesAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyInvitesCallBack> {
        public FetchPartyInvitesAsyncQueryHandler(ContentResolver contentResolver, FetchPartyInvitesCallBack fetchPartyInvitesCallBack) {
            super(contentResolver, fetchPartyInvitesCallBack);
        }

        public void onQueryComplete(FetchPartyInvitesCallBack fetchPartyInvitesCallBack, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchPartyInvitesCallBack.onSuccess(HorizonContentProviderHelper.getPartyInvitesDataFromCursor(cursor));
                } catch (Exception e) {
                    e.toString();
                    fetchPartyInvitesCallBack.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchPartyPrivacyInfoAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyPrivacyInfoCallback> {
        public FetchPartyPrivacyInfoAsyncQueryHandler(ContentResolver contentResolver, FetchPartyPrivacyInfoCallback fetchPartyPrivacyInfoCallback) {
            super(contentResolver, fetchPartyPrivacyInfoCallback);
        }

        public void onQueryComplete(FetchPartyPrivacyInfoCallback fetchPartyPrivacyInfoCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    SocialPartyPrivacyInfo.fromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchPartyPrivacyInfoCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchPeopleNearbyAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPeopleNearbyCallback> {
        public FetchPeopleNearbyAsyncQueryHandler(ContentResolver contentResolver, FetchPeopleNearbyCallback fetchPeopleNearbyCallback) {
            super(contentResolver, fetchPeopleNearbyCallback);
        }

        public void onQueryComplete(FetchPeopleNearbyCallback fetchPeopleNearbyCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchPeopleNearbyCallback.onSuccess(HorizonContentProviderHelper.getPeopleNearbyDataFromCursor(cursor));
                } catch (Exception unused) {
                    fetchPeopleNearbyCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchProfileContentAsyncQueryHandler extends AsyncQueryHandlerBase<FetchProfileContentCallback> {
        public FetchProfileContentAsyncQueryHandler(ContentResolver contentResolver, FetchProfileContentCallback fetchProfileContentCallback) {
            super(contentResolver, fetchProfileContentCallback);
        }

        public void onQueryComplete(FetchProfileContentCallback fetchProfileContentCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    HorizonContentProviderHelper.getProfileContentFromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchProfileContentCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchRosterSizeAsyncQueryHandler extends AsyncQueryHandlerBase<FetchRosterSizeCallback> {
        public FetchRosterSizeAsyncQueryHandler(ContentResolver contentResolver, FetchRosterSizeCallback fetchRosterSizeCallback) {
            super(contentResolver, fetchRosterSizeCallback);
        }

        public void onQueryComplete(FetchRosterSizeCallback fetchRosterSizeCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    RosterSize.fromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchRosterSizeCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchSocialFriendsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchSocialFriendsCallback> {
        public FetchSocialFriendsAsyncQueryHandler(ContentResolver contentResolver, FetchSocialFriendsCallback fetchSocialFriendsCallback) {
            super(contentResolver, fetchSocialFriendsCallback);
        }

        public void onQueryComplete(FetchSocialFriendsCallback fetchSocialFriendsCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchSocialFriendsCallback.onSuccess(HorizonContentProviderHelper.getSocialUsersFromCursor(cursor));
                } catch (Exception e) {
                    fetchSocialFriendsCallback.onError(e);
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchSocialViewerAsyncQueryHandler extends AsyncQueryHandlerBase<FetchSocialViewerInfoCallback> {
        public FetchSocialViewerAsyncQueryHandler(ContentResolver contentResolver, FetchSocialViewerInfoCallback fetchSocialViewerInfoCallback) {
            super(contentResolver, fetchSocialViewerInfoCallback);
        }

        public void onQueryComplete(FetchSocialViewerInfoCallback fetchSocialViewerInfoCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchSocialViewerInfoCallback.onSuccess(HorizonContentProviderHelper.getSocialViewerInfoFromCursor(cursor));
                } catch (Exception unused) {
                    fetchSocialViewerInfoCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class FetchVRProfileContentAsyncQueryHandler extends AsyncQueryHandlerBase<FetchVRProfileContentCallback> {
        public FetchVRProfileContentAsyncQueryHandler(ContentResolver contentResolver, FetchVRProfileContentCallback fetchVRProfileContentCallback) {
            super(contentResolver, fetchVRProfileContentCallback);
        }

        public void onQueryComplete(FetchVRProfileContentCallback fetchVRProfileContentCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    HorizonContentProviderHelper.getVRProfileContentFromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception unused) {
                    fetchVRProfileContentCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class GetBlockedUserIdAsyncQueryHandler extends AsyncQueryHandlerBase<GetBlockedUserIdCallback> {
        public GetBlockedUserIdAsyncQueryHandler(ContentResolver contentResolver, GetBlockedUserIdCallback getBlockedUserIdCallback) {
            super(contentResolver, getBlockedUserIdCallback);
        }

        public void onQueryComplete(GetBlockedUserIdCallback getBlockedUserIdCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        cursor.getString(cursor.getColumnIndex("blocked_user_id"));
                        throw new NullPointerException("onSuccess");
                    }
                } catch (Exception unused) {
                    getBlockedUserIdCallback.onError();
                    return;
                }
            }
            throw new RuntimeException();
        }
    }

    public static final class GetHasParticipantRecentlySpokenAsyncQueryHandler extends AsyncQueryHandlerBase<FetchParticipantSpeakerStatusCallback> {
        public GetHasParticipantRecentlySpokenAsyncQueryHandler(ContentResolver contentResolver, FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback) {
            super(contentResolver, fetchParticipantSpeakerStatusCallback);
        }

        public void onQueryComplete(FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    cursor.moveToPosition(-1);
                    if (cursor.moveToNext()) {
                        boolean z = true;
                        if (cursor.getInt(cursor.getColumnIndexOrThrow("party_has_participant_recently_spoken")) != 1) {
                            z = false;
                        }
                        fetchParticipantSpeakerStatusCallback.onSuccess(z);
                    }
                } catch (Exception unused) {
                    fetchParticipantSpeakerStatusCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class GetPartyMicrophoneInputLocationAsyncQueryHandler extends AsyncQueryHandlerBase<GetPartyMicrophoneInputLocationCallback> {
        public GetPartyMicrophoneInputLocationAsyncQueryHandler(ContentResolver contentResolver, GetPartyMicrophoneInputLocationCallback getPartyMicrophoneInputLocationCallback) {
            super(contentResolver, getPartyMicrophoneInputLocationCallback);
        }

        public void onQueryComplete(GetPartyMicrophoneInputLocationCallback getPartyMicrophoneInputLocationCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    cursor.moveToPosition(-1);
                    if (cursor.moveToNext()) {
                        getPartyMicrophoneInputLocationCallback.onSuccess(PartyMicrophoneInputLocation.get(Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("party_microphone_input_location")))));
                    }
                } catch (Exception unused) {
                    getPartyMicrophoneInputLocationCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class GetPartyMicrophoneStateAsyncQueryHandler extends AsyncQueryHandlerBase<GetPartyMicrophoneStateCallback> {
        public GetPartyMicrophoneStateAsyncQueryHandler(ContentResolver contentResolver, GetPartyMicrophoneStateCallback getPartyMicrophoneStateCallback) {
            super(contentResolver, getPartyMicrophoneStateCallback);
        }

        public void onQueryComplete(GetPartyMicrophoneStateCallback getPartyMicrophoneStateCallback, Cursor cursor) {
            String string;
            if (cursor == null) {
                getPartyMicrophoneStateCallback.onError();
                return;
            }
            try {
                cursor.moveToPosition(-1);
                if (!cursor.moveToNext() || (string = cursor.getString(cursor.getColumnIndexOrThrow("party_mic_state"))) == null) {
                    getPartyMicrophoneStateCallback.onError();
                } else {
                    getPartyMicrophoneStateCallback.onSuccess(PartyMicrophoneState.valueOf(string));
                }
            } catch (Exception unused) {
                getPartyMicrophoneStateCallback.onError();
            }
        }
    }

    public static final class InviteUsersToPartyAsyncQueryHandler extends AsyncQueryHandlerBase<MultipleIDCallback> {
        public InviteUsersToPartyAsyncQueryHandler(ContentResolver contentResolver, MultipleIDCallback multipleIDCallback) {
            super(contentResolver, multipleIDCallback);
        }

        public void onUpdateComplete(MultipleIDCallback multipleIDCallback, Object obj, int i) {
            if (i == 1) {
                multipleIDCallback.onSuccess((List) obj);
            } else {
                multipleIDCallback.onError();
            }
        }
    }

    public static final class JoinPartyAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallbackWithErrorCode> {
        public JoinPartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode) {
            super(contentResolver, singleIDCallbackWithErrorCode);
        }

        public void onUpdateComplete(SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode, Object obj, int i) {
            if (i == 1) {
                singleIDCallbackWithErrorCode.onSuccess((String) obj);
            } else {
                singleIDCallbackWithErrorCode.onError(i);
            }
        }
    }

    public static final class LaunchGroupLaunchAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public LaunchGroupLaunchAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class LeavePartyAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public LeavePartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class MessageSendToThreadAsyncQueryHandler extends AsyncQueryHandlerBase<MessageSendToThreadCallback> {
        public MessageSendToThreadAsyncQueryHandler(ContentResolver contentResolver, MessageSendToThreadCallback messageSendToThreadCallback) {
            super(contentResolver, messageSendToThreadCallback);
        }

        public void onUpdateComplete(MessageSendToThreadCallback messageSendToThreadCallback, Object obj, int i) {
            if (i == 1) {
                messageSendToThreadCallback.onSuccess((String) obj);
            } else {
                messageSendToThreadCallback.onError();
            }
        }
    }

    public static final class PrepareGroupLaunchAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public PrepareGroupLaunchAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class RejectFriendRequestAsyncQueryHandler extends AsyncQueryHandlerBase<RejectFriendRequestCallback> {
        public RejectFriendRequestAsyncQueryHandler(ContentResolver contentResolver, RejectFriendRequestCallback rejectFriendRequestCallback) {
            super(contentResolver, rejectFriendRequestCallback);
        }

        public void onUpdateComplete(RejectFriendRequestCallback rejectFriendRequestCallback, Object obj, int i) {
            if (i == 1) {
                rejectFriendRequestCallback.onSuccess((String) obj);
            } else {
                rejectFriendRequestCallback.onError();
            }
        }
    }

    public static final class RemovePartyGroupLaunchDestinationAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public RemovePartyGroupLaunchDestinationAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class SearchUsersAsyncQueryHandler extends AsyncQueryHandlerBase<UserSearchCallback> {
        public SearchUsersAsyncQueryHandler(ContentResolver contentResolver, UserSearchCallback userSearchCallback) {
            super(contentResolver, userSearchCallback);
        }

        public void onQueryComplete(UserSearchCallback userSearchCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    ArrayList arrayList = new ArrayList();
                    cursor.moveToPosition(-1);
                    while (cursor.moveToNext()) {
                        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("user_id");
                        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("name");
                        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("avatar");
                        int columnIndexOrThrow4 = cursor.getColumnIndexOrThrow("friend_status");
                        SocialUser socialUser = new SocialUser(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow, ""), CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow2, ""), CursorHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("alias"), ""), "", CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow3, ""), CursorHelper.getBoolFromIntIndex(cursor, cursor.getColumnIndex("can_viewer_message")), SocialUser.UserRowType.FRIEND);
                        socialUser.mFriendship = SocialUserFriendshipStatus.valueOf(CursorHelper.getStringFromIndex(cursor, columnIndexOrThrow4, ""));
                        arrayList.add(socialUser);
                    }
                    userSearchCallback.onSuccess(arrayList);
                } catch (Exception unused) {
                    userSearchCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class SendFriendRequestAsyncQueryHandler extends AsyncQueryHandlerBase<SendFriendRequestCallback> {
        public SendFriendRequestAsyncQueryHandler(ContentResolver contentResolver, SendFriendRequestCallback sendFriendRequestCallback) {
            super(contentResolver, sendFriendRequestCallback);
        }

        public void onUpdateComplete(SendFriendRequestCallback sendFriendRequestCallback, Object obj, int i) {
            if (i == 1) {
                sendFriendRequestCallback.onSuccess((String) obj);
            } else {
                sendFriendRequestCallback.onError();
            }
        }
    }

    public static final class SetGroupLaunchResponseAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetGroupLaunchResponseAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class SetNuxFlagForUserAsyncQueryHandler extends AsyncQueryHandlerBase<SetNuxFlagForUserCallback> {
        public SetNuxFlagForUserAsyncQueryHandler(ContentResolver contentResolver, SetNuxFlagForUserCallback setNuxFlagForUserCallback) {
            super(contentResolver, setNuxFlagForUserCallback);
        }

        public void onUpdateComplete(SetNuxFlagForUserCallback setNuxFlagForUserCallback, Object obj, int i) {
            if (i == 1) {
                setNuxFlagForUserCallback.onSuccess();
            } else {
                setNuxFlagForUserCallback.onError();
            }
        }
    }

    public static final class SetPartyGroupLaunchDestinationAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetPartyGroupLaunchDestinationAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class SetPartyMicrophoneStateAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetPartyMicrophoneStateAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                try {
                    singleIDCallback.onSuccess(Integer.toString(i));
                } catch (Exception unused) {
                    singleIDCallback.onError();
                }
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class SetPartyTypeAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetPartyTypeAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class SetUserBioAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetUserBioAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final class SharePartyAsyncQueryHandler extends AsyncQueryHandlerBase<SharePartyCallBack> {
        public SharePartyAsyncQueryHandler(ContentResolver contentResolver, SharePartyCallBack sharePartyCallBack) {
            super(contentResolver, sharePartyCallBack);
        }

        public void onQueryComplete(SharePartyCallBack sharePartyCallBack, Cursor cursor) {
            if (cursor != null) {
                try {
                    HorizonContentProviderHelper.getPartyFBIDFromCursor(cursor);
                    throw new NullPointerException("onSuccess");
                } catch (Exception e) {
                    e.toString();
                    sharePartyCallBack.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final class UnblockUserAsyncQueryHandler extends AsyncQueryHandlerBase<UnblockUserCallback> {
        public UnblockUserAsyncQueryHandler(ContentResolver contentResolver, UnblockUserCallback unblockUserCallback) {
            super(contentResolver, unblockUserCallback);
        }

        public void onUpdateComplete(UnblockUserCallback unblockUserCallback, Object obj, int i) {
            if (i == 1) {
                throw new NullPointerException("onSuccess");
            }
            unblockUserCallback.onError();
        }
    }

    public static final class UnfriendUserAsyncQueryHandler extends AsyncQueryHandlerBase<UnfriendUserCallback> {
        public UnfriendUserAsyncQueryHandler(ContentResolver contentResolver, UnfriendUserCallback unfriendUserCallback) {
            super(contentResolver, unfriendUserCallback);
        }

        public void onUpdateComplete(UnfriendUserCallback unfriendUserCallback, Object obj, int i) {
            if (i == 1) {
                unfriendUserCallback.onSuccess((String) obj);
            } else {
                unfriendUserCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle joinParty(Context context, String str, SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode) {
        return joinParty(context, str, null, null, singleIDCallbackWithErrorCode);
    }

    public static final AsyncQueryHandle joinParty(Context context, String str, String str2, String str3, SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode) {
        JoinPartyAsyncQueryHandler joinPartyAsyncQueryHandler = new JoinPartyAsyncQueryHandler(context.getContentResolver(), singleIDCallbackWithErrorCode);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, str2, str3, joinPartyAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$ra58wQ21OcJqu3QOOIkAYF0jFKs2 */
            public final /* synthetic */ String f$0;
            public final /* synthetic */ String f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ HorizonContentProviderHelper.JoinPartyAsyncQueryHandler f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$joinParty$5(this.f$0, this.f$1, this.f$2, this.f$3);
            }
        });
        return new AsyncQueryHandle(joinPartyAsyncQueryHandler, singleIDCallbackWithErrorCode);
    }
}
