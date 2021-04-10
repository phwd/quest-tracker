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
import com.oculus.assistant.api.OculusAssistantMessageTypes;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.apps.models.AppScoreboardsInfo;
import com.oculus.horizoncontent.apps.models.PartyInviteInfo;
import com.oculus.horizoncontent.horizon.HorizonContent;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.horizoncontent.profile.FbFriendPrimaryProfile;
import com.oculus.horizoncontent.profile.SelfVRProfileContent;
import com.oculus.horizoncontent.profile.VRProfileContent;
import com.oculus.horizoncontent.social.PartyMicrophoneInputLocation;
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
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HorizonContentProviderHelper {
    private static final int COLUMN_DOES_NOT_EXIST = -1;
    private static final String ID_LIST_DELIMITER = ":";
    private static final String STRING_LIST_DELIMITER = ",";
    private static final String TAG = LoggingUtil.tag(HorizonContentProviderHelper.class);

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

    static /* synthetic */ App lambda$getGroupLaunchSupportedApplicationsFromCursor$7(App app) {
        return app;
    }

    /* access modifiers changed from: private */
    public static final class CreateOrUpdateCustomPartyAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public CreateOrUpdateCustomPartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(SingleIDCallback singleIDCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("current_party_id"));
                        if (string == null) {
                            singleIDCallback.onError();
                            return;
                        } else {
                            singleIDCallback.onSuccess(string);
                            return;
                        }
                    }
                } catch (Exception unused) {
                    singleIDCallback.onError();
                    return;
                }
            }
            singleIDCallback.onError();
        }
    }

    public static AsyncQueryHandle createOrUpdateCustomParty(Context context, @Nullable Map<String, String> map, SingleIDCallback singleIDCallback) {
        CreateOrUpdateCustomPartyAsyncQueryHandler createOrUpdateCustomPartyAsyncQueryHandler = new CreateOrUpdateCustomPartyAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(map, createOrUpdateCustomPartyAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$iqXbN9ANu_yESJPG34zVlsxhaZU */
            private final /* synthetic */ Map f$0;
            private final /* synthetic */ HorizonContentProviderHelper.CreateOrUpdateCustomPartyAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$createOrUpdateCustomParty$3(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(createOrUpdateCustomPartyAsyncQueryHandler, singleIDCallback);
    }

    static /* synthetic */ void lambda$createOrUpdateCustomParty$3(@Nullable Map map, CreateOrUpdateCustomPartyAsyncQueryHandler createOrUpdateCustomPartyAsyncQueryHandler) {
        Uri uri = HorizonContent.FriendList.PARTY_CREATE;
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                uri = uri.buildUpon().appendQueryParameter((String) entry.getKey(), (String) entry.getValue()).build();
            }
        }
        String str = TAG;
        Log.d(str, "CREATE_CUSTOM_PARTY: " + uri.toString());
        createOrUpdateCustomPartyAsyncQueryHandler.startQuery("", uri, null, null, null, null);
    }

    /* access modifiers changed from: private */
    public static final class CreatePartyAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public CreatePartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(SingleIDCallback singleIDCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("current_party_id"));
                        if (string == null) {
                            singleIDCallback.onError();
                            return;
                        } else {
                            singleIDCallback.onSuccess(string);
                            return;
                        }
                    }
                } catch (Exception unused) {
                    singleIDCallback.onError();
                    return;
                }
            }
            singleIDCallback.onError();
        }
    }

    public static AsyncQueryHandle createParty(Context context, @Nullable String str, SingleIDCallback singleIDCallback) {
        CreatePartyAsyncQueryHandler createPartyAsyncQueryHandler = new CreatePartyAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, createPartyAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$q0IEcX957qzfE9HnRkPGpvK_dEg */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ HorizonContentProviderHelper.CreatePartyAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$createParty$4(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(createPartyAsyncQueryHandler, singleIDCallback);
    }

    static /* synthetic */ void lambda$createParty$4(@Nullable String str, CreatePartyAsyncQueryHandler createPartyAsyncQueryHandler) {
        Uri uri;
        if (str == null) {
            uri = HorizonContent.FriendList.PARTY_CREATE;
        } else {
            uri = HorizonContent.FriendList.PARTY_CREATE.buildUpon().appendQueryParameter("invite_activity_id", str).build();
        }
        createPartyAsyncQueryHandler.startQuery("", uri, null, null, null, null);
    }

    /* access modifiers changed from: private */
    public static final class CreatePartyAsyncQueryHandlerDEPRECATED extends AsyncQueryHandler {
        private final SingleIDCallback mCallback;

        public CreatePartyAsyncQueryHandlerDEPRECATED(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver);
            this.mCallback = singleIDCallback;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't wrap try/catch for region: R(3:16|17|(1:29)) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            r0.mCallback.onError();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
            if (r3 != null) goto L_0x0042;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0035 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onQueryComplete(int r1, java.lang.Object r2, android.database.Cursor r3) {
            /*
                r0 = this;
                if (r3 == 0) goto L_0x0028
                boolean r1 = r3.moveToNext()     // Catch:{ Exception -> 0x0035 }
                if (r1 != 0) goto L_0x0009
                goto L_0x0028
            L_0x0009:
                java.lang.String r1 = "current_party_id"
                int r1 = r3.getColumnIndexOrThrow(r1)     // Catch:{ Exception -> 0x0035 }
                java.lang.String r1 = r3.getString(r1)     // Catch:{ Exception -> 0x0035 }
                if (r1 != 0) goto L_0x0020
                com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$SingleIDCallback r1 = r0.mCallback     // Catch:{ Exception -> 0x0035 }
                r1.onError()     // Catch:{ Exception -> 0x0035 }
                if (r3 == 0) goto L_0x001f
                r3.close()
            L_0x001f:
                return
            L_0x0020:
                com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$SingleIDCallback r2 = r0.mCallback
                r2.onSuccess(r1)
                if (r3 == 0) goto L_0x003f
                goto L_0x003c
            L_0x0028:
                com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$SingleIDCallback r1 = r0.mCallback
                r1.onError()
                if (r3 == 0) goto L_0x0032
                r3.close()
            L_0x0032:
                return
            L_0x0033:
                r1 = move-exception
                goto L_0x0040
            L_0x0035:
                com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$SingleIDCallback r1 = r0.mCallback     // Catch:{ all -> 0x0033 }
                r1.onError()     // Catch:{ all -> 0x0033 }
                if (r3 == 0) goto L_0x003f
            L_0x003c:
                r3.close()
            L_0x003f:
                return
            L_0x0040:
                if (r3 == 0) goto L_0x0045
                r3.close()
            L_0x0045:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.CreatePartyAsyncQueryHandlerDEPRECATED.onQueryComplete(int, java.lang.Object, android.database.Cursor):void");
        }
    }

    public static void createPartyDEPRECATED(Context context, @Nullable String str, SingleIDCallback singleIDCallback) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, new CreatePartyAsyncQueryHandlerDEPRECATED(context.getContentResolver(), singleIDCallback)) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$v2MkwraLgd56Qkyf0JapMiBHWos */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ HorizonContentProviderHelper.CreatePartyAsyncQueryHandlerDEPRECATED f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$createPartyDEPRECATED$5(this.f$0, this.f$1);
            }
        });
    }

    static /* synthetic */ void lambda$createPartyDEPRECATED$5(@Nullable String str, CreatePartyAsyncQueryHandlerDEPRECATED createPartyAsyncQueryHandlerDEPRECATED) {
        Uri uri;
        if (str == null) {
            uri = HorizonContent.FriendList.PARTY_CREATE;
        } else {
            uri = HorizonContent.FriendList.PARTY_CREATE.buildUpon().appendQueryParameter("invite_activity_id", str).build();
        }
        createPartyAsyncQueryHandlerDEPRECATED.startQuery(1, "", uri, null, null, null, null);
    }

    private static final class InviteUsersToPartyAsyncQueryHandler extends AsyncQueryHandlerBase<MultipleIDCallback> {
        public InviteUsersToPartyAsyncQueryHandler(ContentResolver contentResolver, MultipleIDCallback multipleIDCallback) {
            super(contentResolver, multipleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(MultipleIDCallback multipleIDCallback, Object obj, int i) {
            if (i == 1) {
                multipleIDCallback.onSuccess((List) obj);
            } else {
                multipleIDCallback.onError();
            }
        }
    }

    private static final class SetNuxFlagForUserAsyncQueryHandler extends AsyncQueryHandlerBase<SetNuxFlagForUserCallback> {
        public SetNuxFlagForUserAsyncQueryHandler(ContentResolver contentResolver, SetNuxFlagForUserCallback setNuxFlagForUserCallback) {
            super(contentResolver, setNuxFlagForUserCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SetNuxFlagForUserCallback setNuxFlagForUserCallback, Object obj, int i) {
            if (i == 1) {
                setNuxFlagForUserCallback.onSuccess();
            } else {
                setNuxFlagForUserCallback.onError();
            }
        }
    }

    public static AsyncQueryHandle setNuxFlagForUser(Context context, String str, boolean z, SetNuxFlagForUserCallback setNuxFlagForUserCallback) {
        SetNuxFlagForUserAsyncQueryHandler setNuxFlagForUserAsyncQueryHandler = new SetNuxFlagForUserAsyncQueryHandler(context.getContentResolver(), setNuxFlagForUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("nux_flag", str);
        contentValues.put("nux_value", z ? SocialBundleConstants.FB_UPSELL_MUST_INTERACT : "false");
        setNuxFlagForUserAsyncQueryHandler.startUpdate(1, HorizonContent.FriendList.SET_NUX_FLAG_FOR_USER, contentValues, null, null);
        return new AsyncQueryHandle(setNuxFlagForUserAsyncQueryHandler, setNuxFlagForUserCallback);
    }

    public static AsyncQueryHandle inviteUsersToParty(Context context, List<String> list, String str, MultipleIDCallback multipleIDCallback) {
        InviteUsersToPartyAsyncQueryHandler inviteUsersToPartyAsyncQueryHandler = new InviteUsersToPartyAsyncQueryHandler(context.getContentResolver(), multipleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("user_ids", TextUtils.join(ID_LIST_DELIMITER, list));
        inviteUsersToPartyAsyncQueryHandler.startUpdate(1, list, HorizonContent.FriendList.PARTY_INVITE_USERS_CONTENT_URI, contentValues, null, null);
        return new AsyncQueryHandle(inviteUsersToPartyAsyncQueryHandler, multipleIDCallback);
    }

    private static final class CancelPartyInviteAsyncQueryHandler extends AsyncQueryHandler {
        private final SingleIDCallback mCallback;

        public CancelPartyInviteAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver);
            this.mCallback = singleIDCallback;
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 == 1) {
                this.mCallback.onSuccess((String) obj);
            } else {
                this.mCallback.onError();
            }
        }
    }

    public static void cancelPartyInvite(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        CancelPartyInviteAsyncQueryHandler cancelPartyInviteAsyncQueryHandler = new CancelPartyInviteAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_to_uninvite", str);
        contentValues.put("party_id", str2);
        cancelPartyInviteAsyncQueryHandler.startUpdate(1, str2, HorizonContent.FriendList.PARTY_CANCEL_INVITE, contentValues, null, null);
    }

    public static String getStringFromIndex(Cursor cursor, int i, String str) {
        return i < 0 ? str : cursor.getString(i);
    }

    public static boolean getBoolFromIntIndex(Cursor cursor, int i) {
        return i >= 0 && cursor.getInt(i) != 0;
    }

    public static long getLongFromIndex(Cursor cursor, int i, long j) {
        return i == -1 ? j : cursor.getLong(i);
    }

    /* access modifiers changed from: private */
    public static ArrayList<SocialUser> getPeopleNearbyDataFromCursor(Cursor cursor) {
        ArrayList<SocialUser> arrayList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arrayList.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.PEOPLE_NEARBY));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static ArrayList<SocialUser> getPYMKDataFromCursor(Cursor cursor) {
        ArrayList<SocialUser> arrayList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arrayList.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.SUGGESTED_FRIEND));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static ArrayList<SocialUser> getFriendRequestDataFromCursor(Cursor cursor) {
        ArrayList<SocialUser> arrayList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arrayList.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.INCOMING_FRIEND_REQUEST));
        }
        return arrayList;
    }

    private static SocialUser getSocialUserFromCursor(Cursor cursor, SocialUser.UserRowType userRowType) {
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
        int columnIndex11 = cursor.getColumnIndex(HorizonContent.FriendList.PRESENCE_STATUS_COLUMN);
        SocialUser socialUser = new SocialUser(getStringFromIndex(cursor, columnIndexOrThrow, ""), getStringFromIndex(cursor, columnIndexOrThrow2, ""), getStringFromIndex(cursor, columnIndexOrThrow3, ""), getStringFromIndex(cursor, columnIndexOrThrow4, ""), getStringFromIndex(cursor, columnIndexOrThrow5, ""), getBoolFromIntIndex(cursor, columnIndex8), userRowType);
        String stringFromIndex = getStringFromIndex(cursor, columnIndex11, null);
        if (stringFromIndex != null && !stringFromIndex.isEmpty()) {
            socialUser.setPresenceStatus(stringFromIndex);
        }
        String stringFromIndex2 = getStringFromIndex(cursor, columnIndex, "");
        if (stringFromIndex2 != null && !stringFromIndex2.isEmpty()) {
            socialUser.setFriendship(SocialUserFriendshipStatus.get(stringFromIndex2));
        }
        String stringFromIndex3 = getStringFromIndex(cursor, columnIndex2, "");
        if (stringFromIndex3 != null && !stringFromIndex3.isEmpty()) {
            socialUser.setMutualContext(stringFromIndex3);
        }
        socialUser.setIsInRoom(getBoolFromIntIndex(cursor, columnIndex10));
        String stringFromIndex4 = getStringFromIndex(cursor, columnIndex3, "");
        if (stringFromIndex4 != null && !stringFromIndex4.isEmpty()) {
            socialUser.setLastActive(stringFromIndex4);
        }
        String stringFromIndex5 = getStringFromIndex(cursor, columnIndex4, "");
        if (stringFromIndex5 != null && !stringFromIndex5.isEmpty()) {
            socialUser.setCurrentPartyID(stringFromIndex5);
        }
        String stringFromIndex6 = getStringFromIndex(cursor, columnIndex5, "");
        if (stringFromIndex6 != null && !stringFromIndex6.isEmpty()) {
            socialUser.setCurrentPartyJoinPolicy(stringFromIndex6);
        }
        String stringFromIndex7 = getStringFromIndex(cursor, columnIndex6, "");
        String stringFromIndex8 = getStringFromIndex(cursor, columnIndex7, "");
        boolean z = false;
        if (stringFromIndex7 == null || stringFromIndex7.isEmpty() || stringFromIndex8 == null || stringFromIndex8.isEmpty()) {
            socialUser.setIsPartyFull(false);
        } else {
            if (Integer.parseInt(stringFromIndex8) >= Integer.parseInt(stringFromIndex7)) {
                z = true;
            }
            socialUser.setIsPartyFull(z);
        }
        String stringFromIndex9 = getStringFromIndex(cursor, columnIndex9, "");
        if (stringFromIndex9 != null && !stringFromIndex9.isEmpty()) {
            socialUser.setInvitedToLocalPartyBySenderID(stringFromIndex9);
        }
        return socialUser;
    }

    /* access modifiers changed from: private */
    public static List<SocialParty> getJoinablePartiesDataFromCursor(Cursor cursor) {
        Cursor cursor2 = cursor;
        ArrayList arrayList = new ArrayList();
        int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("party_id");
        int columnIndexOrThrow2 = cursor2.getColumnIndexOrThrow("id");
        int columnIndexOrThrow3 = cursor2.getColumnIndexOrThrow("name");
        int columnIndexOrThrow4 = cursor2.getColumnIndexOrThrow("alias");
        int columnIndexOrThrow5 = cursor2.getColumnIndexOrThrow("profile_image_url");
        int columnIndexOrThrow6 = cursor2.getColumnIndexOrThrow("party_max_size");
        int columnIndex = cursor2.getColumnIndex("can_viewer_message");
        cursor2.moveToPosition(-1);
        HashMap hashMap = new HashMap();
        String str = null;
        while (cursor.moveToNext()) {
            String string = cursor2.getString(columnIndexOrThrow);
            int i = cursor2.getInt(columnIndexOrThrow6);
            if (string != null) {
                hashMap.put(string, new SocialParty(string, new ArrayList(), SocialPartyType.JOINABLE_BY_FRIENDS, false, null, null, null, null, null, null, i, null, false, null, null, false, null, null, null, null, null));
                str = string;
            } else {
                SocialParty socialParty = (SocialParty) hashMap.get(str);
                List<SocialUser> partyMembers = socialParty.getPartyMembers();
                SocialUser socialUser = new SocialUser(cursor2.getString(columnIndexOrThrow2), cursor2.getString(columnIndexOrThrow3), cursor2.getString(columnIndexOrThrow4), null, cursor2.getString(columnIndexOrThrow5), getBoolFromIntIndex(cursor2, columnIndex), SocialUser.UserRowType.PARTY_MEMBER);
                socialUser.setCurrentPartyID(str);
                partyMembers.add(socialUser);
                socialParty.setPartyMembers(partyMembers);
                hashMap.put(string, socialParty);
                cursor2 = cursor;
            }
        }
        for (String str2 : hashMap.keySet()) {
            arrayList.add((SocialParty) hashMap.get(str2));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
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
            arrayList.add(new SocialGroupLaunchAppDestination("", null, "", "", true, getStringFromIndex(cursor, columnIndexOrThrow, ""), getStringFromIndex(cursor, columnIndexOrThrow2, ""), getStringFromIndex(cursor, columnIndexOrThrow3, ""), getStringFromIndex(cursor, columnIndexOrThrow4, ""), cursor.getInt(columnIndexOrThrow5), null, false, getStringFromIndex(cursor, columnIndexOrThrow6, ""), getStringFromIndex(cursor, columnIndexOrThrow7, ""), getBoolFromIntIndex(cursor, columnIndexOrThrow8)));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
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
                arrayList.add(new FriendListRowData(getStringFromIndex(cursor, columnIndexOrThrow, ""), getStringFromIndex(cursor, columnIndexOrThrow2, ""), getStringFromIndex(cursor, columnIndexOrThrow3, ""), getStringFromIndex(cursor, columnIndexOrThrow4, ""), HorizonContent.FriendList.UserInviteState.values()[cursor.getInt(columnIndexOrThrow5)]));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
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

    @Nullable
    private static SocialDeeplinkPresence getDeepLinkFromCursor(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("presence_app_package_name");
        int columnIndex2 = cursor.getColumnIndex("presence_app_package_app_id");
        int columnIndex3 = cursor.getColumnIndex("presence_deep_link_launch_params");
        String stringFromIndex = getStringFromIndex(cursor, columnIndex, "");
        String stringFromIndex2 = getStringFromIndex(cursor, columnIndex2, "");
        String stringFromIndex3 = getStringFromIndex(cursor, columnIndex3, "");
        if (stringFromIndex == null || stringFromIndex.isEmpty() || stringFromIndex2 == null || stringFromIndex2.isEmpty() || stringFromIndex3 == null || stringFromIndex3.isEmpty()) {
            return null;
        }
        return new SocialDeeplinkPresence(stringFromIndex2, stringFromIndex, stringFromIndex3);
    }

    /* access modifiers changed from: private */
    public static List<SocialUser> getSocialUsersFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            SocialUser socialUserFromCursor = getSocialUserFromCursor(cursor, SocialUser.UserRowType.FRIEND);
            socialUserFromCursor.setFriendship(SocialUserFriendshipStatus.ARE_FRIENDS);
            arrayList.add(socialUserFromCursor);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static String getPartyFBIDFromCursor(Cursor cursor) {
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        return getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("party_fbid"), "");
    }

    /* access modifiers changed from: private */
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
                    socialParty.setPartyMembers(arrayList2);
                    arrayList.add(socialParty);
                }
                ArrayList arrayList3 = new ArrayList();
                SocialParty socialParty2 = new SocialParty(null, arrayList3, SocialPartyType.CLOSED, false, null, null, null, null, null, null, cursor.getInt(columnIndex2), null, false, null, null, false, null, null, null, null, null);
                socialParty2.setID(cursor.getString(columnIndex));
                socialParty2.setInviter(getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER));
                arrayList2 = arrayList3;
                socialParty = socialParty2;
            } else {
                arrayList2.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER));
            }
        }
        if (socialParty != null) {
            socialParty.setPartyMembers(arrayList2);
            arrayList.add(socialParty);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
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
        int i = columnIndex;
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
        SocialPartyType fromPartyType = SocialPartyType.fromPartyType(getStringFromIndex(cursor, cursor.getColumnIndex("party_type"), ""));
        boolean boolFromIntIndex = getBoolFromIntIndex(cursor, columnIndexOrThrow2);
        SocialUser socialUserFromCursor = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
        String string = cursor.getString(columnIndexOrThrow);
        boolean z = cursor.getInt(columnIndex3) == 1;
        String stringFromIndex = getStringFromIndex(cursor, columnIndexOrThrow3, "");
        String stringFromIndex2 = getStringFromIndex(cursor, columnIndexOrThrow4, "");
        String stringFromIndex3 = getStringFromIndex(cursor, columnIndex6, "");
        boolean z2 = cursor.getInt(columnIndex7) == 1;
        String stringFromIndex4 = getStringFromIndex(cursor, columnIndexOrThrow5, "");
        String stringFromIndex5 = getStringFromIndex(cursor, columnIndexOrThrow6, "");
        String stringFromIndex6 = getStringFromIndex(cursor, columnIndexOrThrow7, "");
        String stringFromIndex7 = getStringFromIndex(cursor, columnIndexOrThrow8, "");
        int i2 = cursor.getInt(columnIndex2);
        String stringFromIndex8 = getStringFromIndex(cursor, columnIndex14, "");
        if (stringFromIndex8 == null || stringFromIndex8 == "") {
            socialGroupLaunchAppDestination = null;
        } else {
            socialGroupLaunchAppDestination = new SocialGroupLaunchAppDestination(getStringFromIndex(cursor, columnIndex4, ""), SocialGroupLaunchState.fromString(getStringFromIndex(cursor, columnIndex8, "")), getStringFromIndex(cursor, columnIndex9, ""), getStringFromIndex(cursor, columnIndex10, ""), cursor.getInt(columnIndex11) == 1, getStringFromIndex(cursor, columnIndex12, ""), getStringFromIndex(cursor, columnIndex13, ""), getStringFromIndex(cursor, columnIndex14, ""), getStringFromIndex(cursor, columnIndex15, ""), cursor.getInt(columnIndex16), null, cursor.getInt(columnIndex5) == 1, getStringFromIndex(cursor, columnIndex17, ""), "", false);
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        cursor.moveToNext();
        HashMap hashMap = new HashMap();
        while (cursor.moveToNext() && (cursor.getString(i) == null || !cursor.getString(i).equals("INVITED"))) {
            SocialUser socialUserFromCursor2 = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
            hashMap.put(socialUserFromCursor2.getID(), SocialGroupLaunchResponse.fromString(getStringFromIndex(cursor, columnIndex18, "")));
            socialUserFromCursor2.setDeepLink(getDeepLinkFromCursor(cursor));
            if (getBoolFromIntIndex(cursor, columnIndex19)) {
                hashSet.add(socialUserFromCursor2.getID());
            }
            arrayList.add(socialUserFromCursor2);
            i = i;
            columnIndex18 = columnIndex18;
            columnIndex19 = columnIndex19;
        }
        if (socialGroupLaunchAppDestination != null) {
            socialGroupLaunchAppDestination.setUserResponse(hashMap);
        }
        ArrayList arrayList2 = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList2.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.INVITED));
        }
        return new SocialParty(string, arrayList, fromPartyType, boolFromIntIndex, socialUserFromCursor, arrayList2, null, null, hashSet, null, i2, socialGroupLaunchAppDestination, z, stringFromIndex, stringFromIndex2, z2, stringFromIndex4, stringFromIndex5, stringFromIndex6, stringFromIndex7, stringFromIndex3);
    }

    /* access modifiers changed from: private */
    public static SocialParty getCurrentPartyDataFromCursorV2(Cursor cursor) {
        SocialGroupLaunchAppDestination socialGroupLaunchAppDestination;
        int i;
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
        int i2 = columnIndex;
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
        SocialPartyType fromPartyType = SocialPartyType.fromPartyType(getStringFromIndex(cursor, cursor.getColumnIndex("party_type"), ""));
        boolean boolFromIntIndex = getBoolFromIntIndex(cursor, columnIndexOrThrow2);
        SocialUser socialUserFromCursor = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
        String string = cursor.getString(columnIndexOrThrow);
        boolean z = cursor.getInt(columnIndex3) == 1;
        String stringFromIndex = getStringFromIndex(cursor, columnIndexOrThrow3, "");
        String stringFromIndex2 = getStringFromIndex(cursor, columnIndexOrThrow4, "");
        String stringFromIndex3 = getStringFromIndex(cursor, columnIndex6, "");
        boolean z2 = cursor.getInt(columnIndex7) == 1;
        String stringFromIndex4 = getStringFromIndex(cursor, columnIndexOrThrow5, "");
        String stringFromIndex5 = getStringFromIndex(cursor, columnIndexOrThrow6, "");
        String stringFromIndex6 = getStringFromIndex(cursor, columnIndexOrThrow7, "");
        String stringFromIndex7 = getStringFromIndex(cursor, columnIndexOrThrow8, "");
        int i3 = cursor.getInt(columnIndex2);
        String stringFromIndex8 = getStringFromIndex(cursor, columnIndex14, "");
        if (stringFromIndex8 == null || stringFromIndex8 == "") {
            socialGroupLaunchAppDestination = null;
        } else {
            socialGroupLaunchAppDestination = new SocialGroupLaunchAppDestination(getStringFromIndex(cursor, columnIndex4, ""), SocialGroupLaunchState.fromString(getStringFromIndex(cursor, columnIndex8, "")), getStringFromIndex(cursor, columnIndex9, ""), getStringFromIndex(cursor, columnIndex10, ""), cursor.getInt(columnIndex11) == 1, getStringFromIndex(cursor, columnIndex12, ""), getStringFromIndex(cursor, columnIndex13, ""), getStringFromIndex(cursor, columnIndex14, ""), getStringFromIndex(cursor, columnIndex15, ""), cursor.getInt(columnIndex16), null, cursor.getInt(columnIndex5) == 1, getStringFromIndex(cursor, columnIndex17, ""), "", false);
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        cursor.moveToNext();
        HashMap hashMap = new HashMap();
        while (true) {
            if (!cursor.moveToNext()) {
                i = i2;
                break;
            }
            i = i2;
            if (getIsCurrentRowNextResultTypeIndicator(cursor, i)) {
                break;
            }
            SocialUser socialUserFromCursor2 = getSocialUserFromCursor(cursor, SocialUser.UserRowType.PARTY_MEMBER);
            hashMap.put(socialUserFromCursor2.getID(), SocialGroupLaunchResponse.fromString(getStringFromIndex(cursor, columnIndex18, "")));
            socialUserFromCursor2.setDeepLink(getDeepLinkFromCursor(cursor));
            if (getBoolFromIntIndex(cursor, columnIndex19)) {
                hashSet.add(socialUserFromCursor2.getID());
            }
            arrayList.add(socialUserFromCursor2);
            i2 = i;
            columnIndex18 = columnIndex18;
            columnIndex19 = columnIndex19;
        }
        if (socialGroupLaunchAppDestination != null) {
            socialGroupLaunchAppDestination.setUserResponse(hashMap);
        }
        ArrayList arrayList2 = new ArrayList();
        while (cursor.moveToNext() && !getIsCurrentRowNextResultTypeIndicator(cursor, i)) {
            arrayList2.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.INVITED));
        }
        ArrayList arrayList3 = new ArrayList();
        while (cursor.moveToNext() && !getIsCurrentRowNextResultTypeIndicator(cursor, i)) {
            arrayList3.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.BLOCKED_USER));
        }
        ArrayList arrayList4 = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList4.add(getSocialUserFromCursor(cursor, SocialUser.UserRowType.BLOCKED_INVITED));
        }
        return new SocialParty(string, arrayList, fromPartyType, boolFromIntIndex, socialUserFromCursor, arrayList2, arrayList3, arrayList4, hashSet, null, i3, socialGroupLaunchAppDestination, z, stringFromIndex, stringFromIndex2, z2, stringFromIndex4, stringFromIndex5, stringFromIndex6, stringFromIndex7, stringFromIndex3);
    }

    private static boolean getIsCurrentRowNextResultTypeIndicator(Cursor cursor, int i) {
        return cursor.getString(i) != null && (cursor.getString(i).equals("INVITED") || cursor.getString(i).equals("BLOCKED_MEMBERS") || cursor.getString(i).equals("INVITED_BLOCKED_USERS"));
    }

    private static final class FetchLinkedAccountsInfoForViewerAsyncQueryHandler extends AsyncQueryHandlerBase<FetchLinkedAccountsInfoForViewerCallback> {
        public FetchLinkedAccountsInfoForViewerAsyncQueryHandler(ContentResolver contentResolver, FetchLinkedAccountsInfoForViewerCallback fetchLinkedAccountsInfoForViewerCallback) {
            super(contentResolver, fetchLinkedAccountsInfoForViewerCallback);
        }

        /* access modifiers changed from: protected */
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

    public static final AsyncQueryHandle fetchLinkedAccountsInfoForViewer(Context context, FetchLinkedAccountsInfoForViewerCallback fetchLinkedAccountsInfoForViewerCallback) {
        FetchLinkedAccountsInfoForViewerAsyncQueryHandler fetchLinkedAccountsInfoForViewerAsyncQueryHandler = new FetchLinkedAccountsInfoForViewerAsyncQueryHandler(context.getContentResolver(), fetchLinkedAccountsInfoForViewerCallback);
        fetchLinkedAccountsInfoForViewerAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_LINKED_ACCOUNTS_INFO.buildUpon().build(), null, null, null, null);
        return new AsyncQueryHandle(fetchLinkedAccountsInfoForViewerAsyncQueryHandler, fetchLinkedAccountsInfoForViewerCallback);
    }

    /* access modifiers changed from: private */
    public static LinkedAccountsInfo getLinkedAccountsInfoForViewerFromCursor(Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return null;
        }
        return new LinkedAccountsInfo(getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("fb_linked_status"), ""));
    }

    private static final class FetchPartyInfoWithActivityAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyInfoWithActivityCallback> {
        public FetchPartyInfoWithActivityAsyncQueryHandler(ContentResolver contentResolver, FetchPartyInfoWithActivityCallback fetchPartyInfoWithActivityCallback) {
            super(contentResolver, fetchPartyInfoWithActivityCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchPartyInfoWithActivityCallback fetchPartyInfoWithActivityCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchPartyInfoWithActivityCallback.onSuccess(HorizonContentProviderHelper.getPartyInfoWithActivityFromCursor(cursor));
                } catch (Exception unused) {
                    fetchPartyInfoWithActivityCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchPartyInfoWithActivity(Context context, String str, FetchPartyInfoWithActivityCallback fetchPartyInfoWithActivityCallback) {
        FetchPartyInfoWithActivityAsyncQueryHandler fetchPartyInfoWithActivityAsyncQueryHandler = new FetchPartyInfoWithActivityAsyncQueryHandler(context.getContentResolver(), fetchPartyInfoWithActivityCallback);
        fetchPartyInfoWithActivityAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_INFO_WITH_ACTIVITY.buildUpon().appendQueryParameter("party_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPartyInfoWithActivityAsyncQueryHandler, fetchPartyInfoWithActivityCallback);
    }

    /* access modifiers changed from: private */
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
        return new PartyInfo(getStringFromIndex(cursor, columnIndexOrThrow, ""), Integer.parseInt(getStringFromIndex(cursor, columnIndexOrThrow2, "")), Integer.parseInt(getStringFromIndex(cursor, columnIndexOrThrow3, "")), Integer.parseInt(getStringFromIndex(cursor, columnIndexOrThrow4, "")), Integer.parseInt(getStringFromIndex(cursor, columnIndexOrThrow5, "")), getStringFromIndex(cursor, columnIndexOrThrow6, ""), getStringFromIndex(cursor, columnIndexOrThrow7, ""), getStringFromIndex(cursor, columnIndexOrThrow8, ""), getStringFromIndex(cursor, columnIndexOrThrow9, ""), getStringFromIndex(cursor, columnIndexOrThrow10, ""), getStringFromIndex(cursor, columnIndexOrThrow11, ""), getStringFromIndex(cursor, columnIndexOrThrow12, ""));
    }

    private static final class FetchPartyInviteInfoAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyInviteInfoCallback> {
        public FetchPartyInviteInfoAsyncQueryHandler(ContentResolver contentResolver, FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
            super(contentResolver, fetchPartyInviteInfoCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchPartyInviteInfoCallback.onSuccess(PartyInviteInfo.fromCursor(cursor));
                } catch (Exception e) {
                    Log.d(HorizonContentProviderHelper.TAG, "onQueryComplete: ", e);
                    fetchPartyInviteInfoCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchPartyInviteInfo(Context context, String str, FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
        FetchPartyInviteInfoAsyncQueryHandler fetchPartyInviteInfoAsyncQueryHandler = new FetchPartyInviteInfoAsyncQueryHandler(context.getContentResolver(), fetchPartyInviteInfoCallback);
        fetchPartyInviteInfoAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_INVITE_INFO.buildUpon().appendQueryParameter("party_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPartyInviteInfoAsyncQueryHandler, fetchPartyInviteInfoCallback);
    }

    private static final class FetchPartyInviteInfoNonceAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyInviteInfoCallback> {
        public FetchPartyInviteInfoNonceAsyncQueryHandler(ContentResolver contentResolver, FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
            super(contentResolver, fetchPartyInviteInfoCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchPartyInviteInfoCallback.onSuccess(PartyInviteInfo.fromCursor(cursor));
                } catch (Exception e) {
                    Log.d(HorizonContentProviderHelper.TAG, "onQueryComplete: ", e);
                    fetchPartyInviteInfoCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static AsyncQueryHandle fetchPartyInviteInfoWithNonce(Context context, String str, String str2, FetchPartyInviteInfoCallback fetchPartyInviteInfoCallback) {
        FetchPartyInviteInfoNonceAsyncQueryHandler fetchPartyInviteInfoNonceAsyncQueryHandler = new FetchPartyInviteInfoNonceAsyncQueryHandler(context.getContentResolver(), fetchPartyInviteInfoCallback);
        fetchPartyInviteInfoNonceAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_INVITE_INFO_WITH_NONCE.buildUpon().appendQueryParameter("deeplink_target_id", str).appendQueryParameter("link_nonce", str2).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPartyInviteInfoNonceAsyncQueryHandler, fetchPartyInviteInfoCallback);
    }

    private static final class FetchPartyPrivacyInfoAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyPrivacyInfoCallback> {
        public FetchPartyPrivacyInfoAsyncQueryHandler(ContentResolver contentResolver, FetchPartyPrivacyInfoCallback fetchPartyPrivacyInfoCallback) {
            super(contentResolver, fetchPartyPrivacyInfoCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchPartyPrivacyInfoCallback fetchPartyPrivacyInfoCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchPartyPrivacyInfoCallback.onSuccess(SocialPartyPrivacyInfo.fromCursor(cursor));
                } catch (Exception e) {
                    Log.d(HorizonContentProviderHelper.TAG, "onQueryComplete: ", e);
                    fetchPartyPrivacyInfoCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchPartyPrivacyInfo(Context context, String str, FetchPartyPrivacyInfoCallback fetchPartyPrivacyInfoCallback) {
        FetchPartyPrivacyInfoAsyncQueryHandler fetchPartyPrivacyInfoAsyncQueryHandler = new FetchPartyPrivacyInfoAsyncQueryHandler(context.getContentResolver(), fetchPartyPrivacyInfoCallback);
        fetchPartyPrivacyInfoAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_PRIVACY_INFO.buildUpon().appendQueryParameter("party_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPartyPrivacyInfoAsyncQueryHandler, fetchPartyPrivacyInfoCallback);
    }

    private static final class FetchPeopleNearbyAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPeopleNearbyCallback> {
        public FetchPeopleNearbyAsyncQueryHandler(ContentResolver contentResolver, FetchPeopleNearbyCallback fetchPeopleNearbyCallback) {
            super(contentResolver, fetchPeopleNearbyCallback);
        }

        /* access modifiers changed from: protected */
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

    private static final class FetchPYMKsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPYMKsCallback> {
        public FetchPYMKsAsyncQueryHandler(ContentResolver contentResolver, FetchPYMKsCallback fetchPYMKsCallback) {
            super(contentResolver, fetchPYMKsCallback);
        }

        /* access modifiers changed from: protected */
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

    public static final AsyncQueryHandle fetchPeopleNearby(Context context, @Nullable Integer num, FetchPeopleNearbyCallback fetchPeopleNearbyCallback) {
        FetchPeopleNearbyAsyncQueryHandler fetchPeopleNearbyAsyncQueryHandler = new FetchPeopleNearbyAsyncQueryHandler(context.getContentResolver(), fetchPeopleNearbyCallback);
        Uri.Builder buildUpon = HorizonContent.FriendList.PEOPLE_NEARBY_CONTENT_URI.buildUpon();
        if (num != null) {
            buildUpon.appendQueryParameter("first", num.toString());
        }
        fetchPeopleNearbyAsyncQueryHandler.startQuery("", buildUpon.build(), null, null, null, null);
        return new AsyncQueryHandle(fetchPeopleNearbyAsyncQueryHandler, fetchPeopleNearbyCallback);
    }

    public static final AsyncQueryHandle fetchPYMKs(Context context, FetchPYMKsCallback fetchPYMKsCallback) {
        FetchPYMKsAsyncQueryHandler fetchPYMKsAsyncQueryHandler = new FetchPYMKsAsyncQueryHandler(context.getContentResolver(), fetchPYMKsCallback);
        fetchPYMKsAsyncQueryHandler.startQuery("", HorizonContent.FriendList.PYMK_CONTENT_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchPYMKsAsyncQueryHandler, fetchPYMKsCallback);
    }

    private static final class HidePYMKUserAsyncQueryHandler extends AsyncQueryHandler {
        private final HidePYMKUserCallback mCallback;

        public HidePYMKUserAsyncQueryHandler(ContentResolver contentResolver, HidePYMKUserCallback hidePYMKUserCallback) {
            super(contentResolver);
            this.mCallback = hidePYMKUserCallback;
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 == 1) {
                this.mCallback.onSuccess((String) obj);
            } else {
                this.mCallback.onError();
            }
        }
    }

    public static final void hidePYMKUser(Context context, String str, HidePYMKUserCallback hidePYMKUserCallback) {
        HidePYMKUserAsyncQueryHandler hidePYMKUserAsyncQueryHandler = new HidePYMKUserAsyncQueryHandler(context.getContentResolver(), hidePYMKUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", str);
        hidePYMKUserAsyncQueryHandler.startUpdate(1, str, HorizonContent.FriendList.PYMK_HIDE_USER, contentValues, null, null);
    }

    private static final class MessageSendToThreadAsyncQueryHandler extends AsyncQueryHandlerBase<MessageSendToThreadCallback> {
        public MessageSendToThreadAsyncQueryHandler(ContentResolver contentResolver, MessageSendToThreadCallback messageSendToThreadCallback) {
            super(contentResolver, messageSendToThreadCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(MessageSendToThreadCallback messageSendToThreadCallback, Object obj, int i) {
            if (i == 1) {
                messageSendToThreadCallback.onSuccess((String) obj);
            } else {
                messageSendToThreadCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle messageSendToThread(Context context, String str, String str2, MessageSendToThreadCallback messageSendToThreadCallback) {
        MessageSendToThreadAsyncQueryHandler messageSendToThreadAsyncQueryHandler = new MessageSendToThreadAsyncQueryHandler(context.getContentResolver(), messageSendToThreadCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("thread_id", str);
        contentValues.put("body", str2);
        messageSendToThreadAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.MESSAGE_SEND_TO_THREAD, contentValues, null, null);
        return new AsyncQueryHandle(messageSendToThreadAsyncQueryHandler, messageSendToThreadCallback);
    }

    private static final class FetchCurrentPartyAsyncQueryHandlerDEPRECATED extends AsyncQueryHandlerBase<FetchCurrentPartyCallBack> {
        public FetchCurrentPartyAsyncQueryHandlerDEPRECATED(ContentResolver contentResolver, FetchCurrentPartyCallBack fetchCurrentPartyCallBack) {
            super(contentResolver, fetchCurrentPartyCallBack);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchCurrentPartyCallBack fetchCurrentPartyCallBack, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchCurrentPartyCallBack.onSuccess(HorizonContentProviderHelper.getCurrentPartyDataFromCursorDEPRECATED(cursor));
                } catch (Exception e) {
                    Log.d(HorizonContentProviderHelper.TAG, e.toString());
                    fetchCurrentPartyCallBack.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchCurrentPartyDEPRECATED(Context context, FetchCurrentPartyCallBack fetchCurrentPartyCallBack) {
        FetchCurrentPartyAsyncQueryHandlerDEPRECATED fetchCurrentPartyAsyncQueryHandlerDEPRECATED = new FetchCurrentPartyAsyncQueryHandlerDEPRECATED(context.getContentResolver(), fetchCurrentPartyCallBack);
        fetchCurrentPartyAsyncQueryHandlerDEPRECATED.startQuery("", HorizonContent.FriendList.PARTY_GET_CURRENT, null, null, null, null);
        return new AsyncQueryHandle(fetchCurrentPartyAsyncQueryHandlerDEPRECATED, fetchCurrentPartyCallBack);
    }

    private static final class FetchCurrentPartyAsyncQueryHandlerV2 extends AsyncQueryHandlerBase<FetchCurrentPartyCallBack> {
        public FetchCurrentPartyAsyncQueryHandlerV2(ContentResolver contentResolver, FetchCurrentPartyCallBack fetchCurrentPartyCallBack) {
            super(contentResolver, fetchCurrentPartyCallBack);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchCurrentPartyCallBack fetchCurrentPartyCallBack, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchCurrentPartyCallBack.onSuccess(HorizonContentProviderHelper.getCurrentPartyDataFromCursorV2(cursor));
                } catch (Exception e) {
                    Log.d(HorizonContentProviderHelper.TAG, e.toString());
                    fetchCurrentPartyCallBack.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchCurrentPartyV2(Context context, FetchCurrentPartyCallBack fetchCurrentPartyCallBack) {
        FetchCurrentPartyAsyncQueryHandlerV2 fetchCurrentPartyAsyncQueryHandlerV2 = new FetchCurrentPartyAsyncQueryHandlerV2(context.getContentResolver(), fetchCurrentPartyCallBack);
        fetchCurrentPartyAsyncQueryHandlerV2.startQuery("", HorizonContent.FriendList.PARTY_GET_CURRENT_V2, null, null, null, null);
        return new AsyncQueryHandle(fetchCurrentPartyAsyncQueryHandlerV2, fetchCurrentPartyCallBack);
    }

    private static final class FetchPartyInvitesAsyncQueryHandler extends AsyncQueryHandlerBase<FetchPartyInvitesCallBack> {
        public FetchPartyInvitesAsyncQueryHandler(ContentResolver contentResolver, FetchPartyInvitesCallBack fetchPartyInvitesCallBack) {
            super(contentResolver, fetchPartyInvitesCallBack);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchPartyInvitesCallBack fetchPartyInvitesCallBack, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchPartyInvitesCallBack.onSuccess(HorizonContentProviderHelper.getPartyInvitesDataFromCursor(cursor));
                } catch (Exception e) {
                    Log.d(HorizonContentProviderHelper.TAG, e.toString());
                    fetchPartyInvitesCallBack.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchPartyInvites(Context context, FetchPartyInvitesCallBack fetchPartyInvitesCallBack) {
        FetchPartyInvitesAsyncQueryHandler fetchPartyInvitesAsyncQueryHandler = new FetchPartyInvitesAsyncQueryHandler(context.getContentResolver(), fetchPartyInvitesCallBack);
        fetchPartyInvitesAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_INVITES, null, null, null, null);
        return new AsyncQueryHandle(fetchPartyInvitesAsyncQueryHandler, fetchPartyInvitesCallBack);
    }

    private static final class FetchJoinablePartiesAsyncQueryHandler extends AsyncQueryHandlerBase<FetchJoinablePartiesCallback> {
        public FetchJoinablePartiesAsyncQueryHandler(ContentResolver contentResolver, FetchJoinablePartiesCallback fetchJoinablePartiesCallback) {
            super(contentResolver, fetchJoinablePartiesCallback);
        }

        /* access modifiers changed from: protected */
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

    public static final AsyncQueryHandle fetchJoinableParties(Context context, FetchJoinablePartiesCallback fetchJoinablePartiesCallback) {
        FetchJoinablePartiesAsyncQueryHandler fetchJoinablePartiesAsyncQueryHandler = new FetchJoinablePartiesAsyncQueryHandler(context.getContentResolver(), fetchJoinablePartiesCallback);
        fetchJoinablePartiesAsyncQueryHandler.startQuery("", HorizonContent.FriendList.PARTY_GET_JOINABLE_PARTIES_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchJoinablePartiesAsyncQueryHandler, fetchJoinablePartiesCallback);
    }

    private static final class FetchAppsScoreboardsInfoAsyncQueryHandler extends AsyncQueryHandlerBase<FetchAppsScoreboardsInfoCallback> {
        public FetchAppsScoreboardsInfoAsyncQueryHandler(ContentResolver contentResolver, FetchAppsScoreboardsInfoCallback fetchAppsScoreboardsInfoCallback) {
            super(contentResolver, fetchAppsScoreboardsInfoCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchAppsScoreboardsInfoCallback fetchAppsScoreboardsInfoCallback, Cursor cursor) {
            try {
                Log.d(HorizonContentProviderHelper.TAG, "fetchAppsScoreboardsInfo - complete");
                if (cursor != null) {
                    ArrayList arrayList = new ArrayList();
                    int columnIndexOrThrow = cursor.getColumnIndexOrThrow(OculusAssistantMessageTypes.VoiceSdk.APP_ID);
                    int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("hasAchievements");
                    int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("hasLeaderboards");
                    cursor.moveToPosition(-1);
                    while (cursor.moveToNext()) {
                        arrayList.add(new AppScoreboardsInfo(HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow, ""), SocialBundleConstants.FB_UPSELL_MUST_INTERACT.equals(HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow2, "")), SocialBundleConstants.FB_UPSELL_MUST_INTERACT.equals(HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow3, ""))));
                    }
                    fetchAppsScoreboardsInfoCallback.onSuccess(arrayList);
                    return;
                }
                throw new RuntimeException();
            } catch (Exception unused) {
                fetchAppsScoreboardsInfoCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle fetchAppsScoreboardsInfo(Context context, String[] strArr, FetchAppsScoreboardsInfoCallback fetchAppsScoreboardsInfoCallback) {
        String str = TAG;
        Log.d(str, "fetchAppsScoreboardsInfo - appIds.length: " + strArr.length);
        FetchAppsScoreboardsInfoAsyncQueryHandler fetchAppsScoreboardsInfoAsyncQueryHandler = new FetchAppsScoreboardsInfoAsyncQueryHandler(context.getContentResolver(), fetchAppsScoreboardsInfoCallback);
        fetchAppsScoreboardsInfoAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_APPS_SCOREBOARDS_INFO_URI.buildUpon().appendQueryParameter("application_ids", TextUtils.join(ID_LIST_DELIMITER, strArr)).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchAppsScoreboardsInfoAsyncQueryHandler, fetchAppsScoreboardsInfoCallback);
    }

    private static final class FetchSocialFriendsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchSocialFriendsCallback> {
        public FetchSocialFriendsAsyncQueryHandler(ContentResolver contentResolver, FetchSocialFriendsCallback fetchSocialFriendsCallback) {
            super(contentResolver, fetchSocialFriendsCallback);
        }

        /* access modifiers changed from: protected */
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

    /* access modifiers changed from: private */
    public static List<SocialGroupLaunchApp> getGroupLaunchSupportedApplicationsFromCursor(Context context, Cursor cursor) {
        Map map = (Map) HorizonUtil.getApplications(context).stream().collect(Collectors.toMap($$Lambda$HorizonContentProviderHelper$l1bvDLESN8bAiEm4MxLPPIn5yq4.INSTANCE, $$Lambda$HorizonContentProviderHelper$7QGIobHvmIyrLrjZCsgFL3bHU2c.INSTANCE));
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

    private static final class FetchGroupLaunchAppAsyncQueryHandler extends AsyncQueryHandlerBase<FetchGroupLaunchSupportedApplicationsCallback> {
        private Context mContext;

        public FetchGroupLaunchAppAsyncQueryHandler(Context context, FetchGroupLaunchSupportedApplicationsCallback fetchGroupLaunchSupportedApplicationsCallback) {
            super(context.getContentResolver(), fetchGroupLaunchSupportedApplicationsCallback);
            this.mContext = context;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
            r2.onError();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
            if (r3 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
            if (r3 != null) goto L_0x0021;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
            throw r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0016 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onQueryComplete(com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchGroupLaunchSupportedApplicationsCallback r2, android.database.Cursor r3) {
            /*
                r1 = this;
                if (r3 == 0) goto L_0x0010
                android.content.Context r0 = r1.mContext     // Catch:{ Exception -> 0x0016 }
                java.util.List r0 = com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.access$1000(r0, r3)     // Catch:{ Exception -> 0x0016 }
                r2.onSuccess(r0)     // Catch:{ Exception -> 0x0016 }
                if (r3 == 0) goto L_0x001e
                goto L_0x001b
            L_0x000e:
                r2 = move-exception
                goto L_0x001f
            L_0x0010:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0016 }
                r0.<init>()     // Catch:{ Exception -> 0x0016 }
                throw r0     // Catch:{ Exception -> 0x0016 }
            L_0x0016:
                r2.onError()     // Catch:{ all -> 0x000e }
                if (r3 == 0) goto L_0x001e
            L_0x001b:
                r3.close()
            L_0x001e:
                return
            L_0x001f:
                if (r3 == 0) goto L_0x0024
                r3.close()
            L_0x0024:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizoncontent.horizon.HorizonContentProviderHelper.FetchGroupLaunchAppAsyncQueryHandler.onQueryComplete(com.oculus.horizoncontent.horizon.HorizonContentProviderHelper$FetchGroupLaunchSupportedApplicationsCallback, android.database.Cursor):void");
        }
    }

    public static final AsyncQueryHandle fetchGroupLaunchSupportedApps(Context context, FetchGroupLaunchSupportedApplicationsCallback fetchGroupLaunchSupportedApplicationsCallback) {
        FetchGroupLaunchAppAsyncQueryHandler fetchGroupLaunchAppAsyncQueryHandler = new FetchGroupLaunchAppAsyncQueryHandler(context, fetchGroupLaunchSupportedApplicationsCallback);
        fetchGroupLaunchAppAsyncQueryHandler.startQuery(1, "", HorizonContent.FriendList.GET_GROUP_LAUNCH_SUPPORTED_APPLICATIONS_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchGroupLaunchAppAsyncQueryHandler, fetchGroupLaunchSupportedApplicationsCallback);
    }

    private static final class FetchGroupLaunchAppDestinationsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchGroupLaunchAppDestinationsCallback> {
        public FetchGroupLaunchAppDestinationsAsyncQueryHandler(ContentResolver contentResolver, FetchGroupLaunchAppDestinationsCallback fetchGroupLaunchAppDestinationsCallback) {
            super(contentResolver, fetchGroupLaunchAppDestinationsCallback);
        }

        /* access modifiers changed from: protected */
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
        buildUpon.appendQueryParameter("ignore_rich_presence", SocialBundleConstants.FB_UPSELL_MUST_INTERACT);
        fetchSocialFriendsAsyncQueryHandler.startQuery("", buildUpon.build(), null, null, null, null);
        return new AsyncQueryHandle(fetchSocialFriendsAsyncQueryHandler, fetchSocialFriendsCallback);
    }

    private static final class SearchUsersAsyncQueryHandler extends AsyncQueryHandlerBase<UserSearchCallback> {
        public SearchUsersAsyncQueryHandler(ContentResolver contentResolver, UserSearchCallback userSearchCallback) {
            super(contentResolver, userSearchCallback);
        }

        /* access modifiers changed from: protected */
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
                        SocialUser socialUser = new SocialUser(HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow, ""), HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow2, ""), HorizonContentProviderHelper.getStringFromIndex(cursor, cursor.getColumnIndexOrThrow("alias"), ""), "", HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow3, ""), HorizonContentProviderHelper.getBoolFromIntIndex(cursor, cursor.getColumnIndex("can_viewer_message")), SocialUser.UserRowType.FRIEND);
                        socialUser.setFriendship(SocialUserFriendshipStatus.valueOf(HorizonContentProviderHelper.getStringFromIndex(cursor, columnIndexOrThrow4, "")));
                        arrayList.add(socialUser);
                    }
                    try {
                        userSearchCallback.onSuccess(arrayList);
                    } catch (Exception unused) {
                        userSearchCallback.onError();
                    }
                } catch (Exception unused2) {
                    userSearchCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle searchUsers(String str, String str2, Context context, UserSearchCallback userSearchCallback) {
        SearchUsersAsyncQueryHandler searchUsersAsyncQueryHandler = new SearchUsersAsyncQueryHandler(context.getContentResolver(), userSearchCallback);
        searchUsersAsyncQueryHandler.startQuery("", HorizonContent.FriendList.USER_SEARCH.buildUpon().appendQueryParameter("query_string", str).appendQueryParameter("search_mode", str2).build(), null, null, null, null);
        return new AsyncQueryHandle(searchUsersAsyncQueryHandler, userSearchCallback);
    }

    private static final class FetchInvitableFriendsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchInvitableFriendsCallback> {
        public FetchInvitableFriendsAsyncQueryHandler(ContentResolver contentResolver, FetchInvitableFriendsCallback fetchInvitableFriendsCallback) {
            super(contentResolver, fetchInvitableFriendsCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchInvitableFriendsCallback fetchInvitableFriendsCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchInvitableFriendsCallback.onSuccess(HorizonContentProviderHelper.getFriendDataFromCursor(cursor), HorizonContentProviderHelper.getPartyIDFromCursor(cursor));
                } catch (Exception unused) {
                    fetchInvitableFriendsCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchInvitableFriends(Context context, FetchInvitableFriendsCallback fetchInvitableFriendsCallback) {
        FetchInvitableFriendsAsyncQueryHandler fetchInvitableFriendsAsyncQueryHandler = new FetchInvitableFriendsAsyncQueryHandler(context.getContentResolver(), fetchInvitableFriendsCallback);
        fetchInvitableFriendsAsyncQueryHandler.startQuery("", HorizonContent.FriendList.FRIEND_LIST_CONTENT_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchInvitableFriendsAsyncQueryHandler, fetchInvitableFriendsCallback);
    }

    private static final class BlockUserAsyncQueryHandler extends AsyncQueryHandlerBase<BlockUserCallback> {
        public BlockUserAsyncQueryHandler(ContentResolver contentResolver, BlockUserCallback blockUserCallback) {
            super(contentResolver, blockUserCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(BlockUserCallback blockUserCallback, Object obj, int i) {
            if (i == 1) {
                blockUserCallback.onSuccess((String) obj);
            } else {
                blockUserCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle blockUser(Context context, String str, BlockUserCallback blockUserCallback) {
        BlockUserAsyncQueryHandler blockUserAsyncQueryHandler = new BlockUserAsyncQueryHandler(context.getContentResolver(), blockUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        blockUserAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.USER_BLOCK, contentValues, null, null);
        return new AsyncQueryHandle(blockUserAsyncQueryHandler, blockUserCallback);
    }

    private static final class UnblockUserAsyncQueryHandler extends AsyncQueryHandlerBase<UnblockUserCallback> {
        public UnblockUserAsyncQueryHandler(ContentResolver contentResolver, UnblockUserCallback unblockUserCallback) {
            super(contentResolver, unblockUserCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(UnblockUserCallback unblockUserCallback, Object obj, int i) {
            if (i == 1) {
                unblockUserCallback.onSuccess((String) obj);
            } else {
                unblockUserCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle unblockUser(Context context, String str, UnblockUserCallback unblockUserCallback) {
        UnblockUserAsyncQueryHandler unblockUserAsyncQueryHandler = new UnblockUserAsyncQueryHandler(context.getContentResolver(), unblockUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("blocked_user_id", str);
        unblockUserAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.USER_UNBLOCK, contentValues, null, null);
        return new AsyncQueryHandle(unblockUserAsyncQueryHandler, unblockUserCallback);
    }

    private static final class AcceptFriendRequestAsyncQueryHandler extends AsyncQueryHandlerBase<AcceptFriendRequestCallback> {
        public AcceptFriendRequestAsyncQueryHandler(ContentResolver contentResolver, AcceptFriendRequestCallback acceptFriendRequestCallback) {
            super(contentResolver, acceptFriendRequestCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(AcceptFriendRequestCallback acceptFriendRequestCallback, Object obj, int i) {
            if (i == 1) {
                acceptFriendRequestCallback.onSuccess((String) obj);
            } else {
                acceptFriendRequestCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle acceptFriendRequest(Context context, String str, String str2, AcceptFriendRequestCallback acceptFriendRequestCallback) {
        AcceptFriendRequestAsyncQueryHandler acceptFriendRequestAsyncQueryHandler = new AcceptFriendRequestAsyncQueryHandler(context.getContentResolver(), acceptFriendRequestCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("source", str2);
        acceptFriendRequestAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_ACCEPT_REQUEST, contentValues, null, null);
        return new AsyncQueryHandle(acceptFriendRequestAsyncQueryHandler, acceptFriendRequestCallback);
    }

    private static final class RejectFriendRequestAsyncQueryHandler extends AsyncQueryHandlerBase<RejectFriendRequestCallback> {
        public RejectFriendRequestAsyncQueryHandler(ContentResolver contentResolver, RejectFriendRequestCallback rejectFriendRequestCallback) {
            super(contentResolver, rejectFriendRequestCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(RejectFriendRequestCallback rejectFriendRequestCallback, Object obj, int i) {
            if (i == 1) {
                rejectFriendRequestCallback.onSuccess((String) obj);
            } else {
                rejectFriendRequestCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle rejectFriendRequest(Context context, String str, RejectFriendRequestCallback rejectFriendRequestCallback) {
        RejectFriendRequestAsyncQueryHandler rejectFriendRequestAsyncQueryHandler = new RejectFriendRequestAsyncQueryHandler(context.getContentResolver(), rejectFriendRequestCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        rejectFriendRequestAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_REJECT_REQUEST, contentValues, null, null);
        return new AsyncQueryHandle(rejectFriendRequestAsyncQueryHandler, rejectFriendRequestCallback);
    }

    private static final class SendFriendRequestAsyncQueryHandler extends AsyncQueryHandlerBase<SendFriendRequestCallback> {
        public SendFriendRequestAsyncQueryHandler(ContentResolver contentResolver, SendFriendRequestCallback sendFriendRequestCallback) {
            super(contentResolver, sendFriendRequestCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SendFriendRequestCallback sendFriendRequestCallback, Object obj, int i) {
            if (i == 1) {
                sendFriendRequestCallback.onSuccess((String) obj);
            } else {
                sendFriendRequestCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle sendFriendRequest(Context context, String str, String str2, SendFriendRequestCallback sendFriendRequestCallback) {
        SendFriendRequestAsyncQueryHandler sendFriendRequestAsyncQueryHandler = new SendFriendRequestAsyncQueryHandler(context.getContentResolver(), sendFriendRequestCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("source", str2);
        sendFriendRequestAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_SEND_REQUEST, contentValues, null, null);
        return new AsyncQueryHandle(sendFriendRequestAsyncQueryHandler, sendFriendRequestCallback);
    }

    private static final class CancelFriendRequestAsyncQueryHandler extends AsyncQueryHandlerBase<CancelFriendRequestCallback> {
        public CancelFriendRequestAsyncQueryHandler(ContentResolver contentResolver, CancelFriendRequestCallback cancelFriendRequestCallback) {
            super(contentResolver, cancelFriendRequestCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(CancelFriendRequestCallback cancelFriendRequestCallback, Object obj, int i) {
            if (i == 1) {
                cancelFriendRequestCallback.onSuccess((String) obj);
            } else {
                cancelFriendRequestCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle cancelFriendRequest(Context context, String str, String str2, CancelFriendRequestCallback cancelFriendRequestCallback) {
        CancelFriendRequestAsyncQueryHandler cancelFriendRequestAsyncQueryHandler = new CancelFriendRequestAsyncQueryHandler(context.getContentResolver(), cancelFriendRequestCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("source", str2);
        cancelFriendRequestAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_CANCEL_REQUEST, contentValues, null, null);
        return new AsyncQueryHandle(cancelFriendRequestAsyncQueryHandler, cancelFriendRequestCallback);
    }

    private static final class FetchFriendRequestsAsyncQueryHandler extends AsyncQueryHandlerBase<FetchFriendRequestsCallback> {
        public FetchFriendRequestsAsyncQueryHandler(ContentResolver contentResolver, FetchFriendRequestsCallback fetchFriendRequestsCallback) {
            super(contentResolver, fetchFriendRequestsCallback);
        }

        /* access modifiers changed from: protected */
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

    public static final AsyncQueryHandle fetchFriendRequests(Context context, FetchFriendRequestsCallback fetchFriendRequestsCallback) {
        FetchFriendRequestsAsyncQueryHandler fetchFriendRequestsAsyncQueryHandler = new FetchFriendRequestsAsyncQueryHandler(context.getContentResolver(), fetchFriendRequestsCallback);
        fetchFriendRequestsAsyncQueryHandler.startQuery("", HorizonContent.FriendList.FRIEND_REQUESTS_CONTENT_URI, null, null, null, null);
        return new AsyncQueryHandle(fetchFriendRequestsAsyncQueryHandler, fetchFriendRequestsCallback);
    }

    private static final class UnfriendUserAsyncQueryHandler extends AsyncQueryHandlerBase<UnfriendUserCallback> {
        public UnfriendUserAsyncQueryHandler(ContentResolver contentResolver, UnfriendUserCallback unfriendUserCallback) {
            super(contentResolver, unfriendUserCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(UnfriendUserCallback unfriendUserCallback, Object obj, int i) {
            if (i == 1) {
                unfriendUserCallback.onSuccess((String) obj);
            } else {
                unfriendUserCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle unfriendUser(Context context, String str, UnfriendUserCallback unfriendUserCallback) {
        UnfriendUserAsyncQueryHandler unfriendUserAsyncQueryHandler = new UnfriendUserAsyncQueryHandler(context.getContentResolver(), unfriendUserCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("friend", str);
        unfriendUserAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.FRIEND_REMOVE, contentValues, null, null);
        return new AsyncQueryHandle(unfriendUserAsyncQueryHandler, unfriendUserCallback);
    }

    /* access modifiers changed from: private */
    public static final class JoinPartyAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallbackWithErrorCode> {
        public JoinPartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode) {
            super(contentResolver, singleIDCallbackWithErrorCode);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode, Object obj, int i) {
            if (i == 1) {
                singleIDCallbackWithErrorCode.onSuccess((String) obj);
            } else {
                singleIDCallbackWithErrorCode.onError(i);
            }
        }
    }

    public static final AsyncQueryHandle joinParty(Context context, String str, SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode) {
        return joinParty(context, str, null, null, singleIDCallbackWithErrorCode);
    }

    public static final AsyncQueryHandle joinParty(Context context, String str, String str2, String str3, SingleIDCallbackWithErrorCode singleIDCallbackWithErrorCode) {
        JoinPartyAsyncQueryHandler joinPartyAsyncQueryHandler = new JoinPartyAsyncQueryHandler(context.getContentResolver(), singleIDCallbackWithErrorCode);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, str2, str3, joinPartyAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$7tmrDvAous1e5w4MZxeyclA8 */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ HorizonContentProviderHelper.JoinPartyAsyncQueryHandler f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$joinParty$8(this.f$0, this.f$1, this.f$2, this.f$3);
            }
        });
        return new AsyncQueryHandle(joinPartyAsyncQueryHandler, singleIDCallbackWithErrorCode);
    }

    static /* synthetic */ void lambda$joinParty$8(String str, String str2, String str3, JoinPartyAsyncQueryHandler joinPartyAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("link_nonce", str2);
        contentValues.put("application_id", str3);
        joinPartyAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_JOIN, contentValues, null, null);
    }

    /* access modifiers changed from: private */
    public static final class LeavePartyAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public LeavePartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle leaveParty(Context context, String str, SingleIDCallback singleIDCallback) {
        LeavePartyAsyncQueryHandler leavePartyAsyncQueryHandler = new LeavePartyAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, leavePartyAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$a_7kQRsO0rF1jJBmMNHCs5urFE */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ HorizonContentProviderHelper.LeavePartyAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$leaveParty$9(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(leavePartyAsyncQueryHandler, singleIDCallback);
    }

    static /* synthetic */ void lambda$leaveParty$9(String str, LeavePartyAsyncQueryHandler leavePartyAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        leavePartyAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_LEAVE, contentValues, null, null);
    }

    private static final class SharePartyAsyncQueryHandler extends AsyncQueryHandlerBase<SharePartyCallBack> {
        public SharePartyAsyncQueryHandler(ContentResolver contentResolver, SharePartyCallBack sharePartyCallBack) {
            super(contentResolver, sharePartyCallBack);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(SharePartyCallBack sharePartyCallBack, Cursor cursor) {
            if (cursor != null) {
                try {
                    sharePartyCallBack.onSuccess(HorizonContentProviderHelper.getPartyFBIDFromCursor(cursor));
                } catch (Exception e) {
                    Log.d(HorizonContentProviderHelper.TAG, e.toString());
                    sharePartyCallBack.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle shareParty(Context context, SharePartyCallBack sharePartyCallBack) {
        SharePartyAsyncQueryHandler sharePartyAsyncQueryHandler = new SharePartyAsyncQueryHandler(context.getContentResolver(), sharePartyCallBack);
        sharePartyAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PARTY_FBID, null, null, null, null);
        return new AsyncQueryHandle(sharePartyAsyncQueryHandler, sharePartyCallBack);
    }

    /* access modifiers changed from: private */
    public static final class SetPartyTypeAsyncQueryHandlerDEPRECATED extends AsyncQueryHandler {
        private final SingleIDCallback mCallback;

        public SetPartyTypeAsyncQueryHandlerDEPRECATED(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver);
            this.mCallback = singleIDCallback;
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 == 1) {
                this.mCallback.onSuccess((String) obj);
            } else {
                this.mCallback.onError();
            }
        }
    }

    public static final void setPartyTypeDEPRECATED(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, str2, new SetPartyTypeAsyncQueryHandlerDEPRECATED(context.getContentResolver(), singleIDCallback)) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$2ix6qIgapoJxSzhlWwCeuayAxF4 */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ HorizonContentProviderHelper.SetPartyTypeAsyncQueryHandlerDEPRECATED f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyTypeDEPRECATED$10(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ void lambda$setPartyTypeDEPRECATED$10(String str, String str2, SetPartyTypeAsyncQueryHandlerDEPRECATED setPartyTypeAsyncQueryHandlerDEPRECATED) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("party_type", str2);
        setPartyTypeAsyncQueryHandlerDEPRECATED.startUpdate(1, str, HorizonContent.FriendList.PARTY_SET_TYPE, contentValues, null, null);
    }

    /* access modifiers changed from: private */
    public static final class SetPartyTypeAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetPartyTypeAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle setPartyType(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        SetPartyTypeAsyncQueryHandler setPartyTypeAsyncQueryHandler = new SetPartyTypeAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, str2, setPartyTypeAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$o9_d7QQvXP07CV0gVjG_uPHI3Qk */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ HorizonContentProviderHelper.SetPartyTypeAsyncQueryHandler f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyType$11(this.f$0, this.f$1, this.f$2);
            }
        });
        return new AsyncQueryHandle(setPartyTypeAsyncQueryHandler, singleIDCallback);
    }

    static /* synthetic */ void lambda$setPartyType$11(String str, String str2, SetPartyTypeAsyncQueryHandler setPartyTypeAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("party_type", str2);
        setPartyTypeAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_SET_TYPE, contentValues, null, null);
    }

    /* access modifiers changed from: private */
    public static final class ActivatePartyLinkInviteAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public ActivatePartyLinkInviteAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle activatePartyLinkInvite(Context context, String str, SingleIDCallback singleIDCallback) {
        ActivatePartyLinkInviteAsyncQueryHandler activatePartyLinkInviteAsyncQueryHandler = new ActivatePartyLinkInviteAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, activatePartyLinkInviteAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$AwWfgeBqP2270HmmWSpRmsCDYA0 */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ HorizonContentProviderHelper.ActivatePartyLinkInviteAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$activatePartyLinkInvite$12(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(activatePartyLinkInviteAsyncQueryHandler, singleIDCallback);
    }

    static /* synthetic */ void lambda$activatePartyLinkInvite$12(String str, ActivatePartyLinkInviteAsyncQueryHandler activatePartyLinkInviteAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        activatePartyLinkInviteAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_ACTIVATE_LINK_INVITE, contentValues, null, null);
    }

    /* access modifiers changed from: private */
    public static final class DeactivatePartyLinkInviteAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public DeactivatePartyLinkInviteAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle deactivatePartyLinkInvite(Context context, String str, SingleIDCallback singleIDCallback) {
        DeactivatePartyLinkInviteAsyncQueryHandler deactivatePartyLinkInviteAsyncQueryHandler = new DeactivatePartyLinkInviteAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, deactivatePartyLinkInviteAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$RWCH1LeD6mfsB3LOU6PV4c5suvQ */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ HorizonContentProviderHelper.DeactivatePartyLinkInviteAsyncQueryHandler f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$deactivatePartyLinkInvite$13(this.f$0, this.f$1);
            }
        });
        return new AsyncQueryHandle(deactivatePartyLinkInviteAsyncQueryHandler, singleIDCallback);
    }

    static /* synthetic */ void lambda$deactivatePartyLinkInvite$13(String str, DeactivatePartyLinkInviteAsyncQueryHandler deactivatePartyLinkInviteAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        deactivatePartyLinkInviteAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_DEACTIVATE_LINK_INVITE, contentValues, null, null);
    }

    /* access modifiers changed from: private */
    public static final class KickUserFromPartyAsyncQueryHandler extends AsyncQueryHandler {
        private final SingleIDCallback mCallback;

        public KickUserFromPartyAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver);
            this.mCallback = singleIDCallback;
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(int i, Object obj, int i2) {
            if (i2 == 1) {
                this.mCallback.onSuccess((String) obj);
            } else {
                this.mCallback.onError();
            }
        }
    }

    public static final void kickUserFromParty(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str2, str, new KickUserFromPartyAsyncQueryHandler(context.getContentResolver(), singleIDCallback)) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$0kLiFEDLDD3K6NJyPY1O9jwuKIw */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ HorizonContentProviderHelper.KickUserFromPartyAsyncQueryHandler f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$kickUserFromParty$14(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ void lambda$kickUserFromParty$14(String str, String str2, KickUserFromPartyAsyncQueryHandler kickUserFromPartyAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("user_id", str2);
        kickUserFromPartyAsyncQueryHandler.startUpdate(1, str, HorizonContent.FriendList.PARTY_KICK, contentValues, null, null);
    }

    private static final class SetPartyGroupLaunchDestinationAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetPartyGroupLaunchDestinationAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle setPartyGroupLaunchDestination(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        SetPartyGroupLaunchDestinationAsyncQueryHandler setPartyGroupLaunchDestinationAsyncQueryHandler = new SetPartyGroupLaunchDestinationAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        contentValues.put("destination_id", str2);
        setPartyGroupLaunchDestinationAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE, contentValues, null, null);
        return new AsyncQueryHandle(setPartyGroupLaunchDestinationAsyncQueryHandler, singleIDCallback);
    }

    private static final class SetGroupLaunchResponseAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetGroupLaunchResponseAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle setGroupLaunchResponse(Context context, String str, SocialGroupLaunchResponse socialGroupLaunchResponse, SingleIDCallback singleIDCallback) {
        SetGroupLaunchResponseAsyncQueryHandler setGroupLaunchResponseAsyncQueryHandler = new SetGroupLaunchResponseAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_launch_id", str);
        contentValues.put("response", socialGroupLaunchResponse.toString());
        setGroupLaunchResponseAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.GROUP_LAUNCH_SET_USER_RESPONSE, contentValues, null, null);
        return new AsyncQueryHandle(setGroupLaunchResponseAsyncQueryHandler, singleIDCallback);
    }

    private static final class PrepareGroupLaunchAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public PrepareGroupLaunchAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle prepareGroupLaunch(Context context, String str, SingleIDCallback singleIDCallback) {
        PrepareGroupLaunchAsyncQueryHandler prepareGroupLaunchAsyncQueryHandler = new PrepareGroupLaunchAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_launch_id", str);
        contentValues.put("group_launch_state", SocialGroupLaunchState.PREPARING.toString());
        prepareGroupLaunchAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.GROUP_LAUNCH_SET_STATE, contentValues, null, null);
        return new AsyncQueryHandle(prepareGroupLaunchAsyncQueryHandler, singleIDCallback);
    }

    private static final class LaunchGroupLaunchAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public LaunchGroupLaunchAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
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

    private static final class RemovePartyGroupLaunchDestinationAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public RemovePartyGroupLaunchDestinationAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle removePartyGroupLaunchDestination(Context context, String str, SingleIDCallback singleIDCallback) {
        RemovePartyGroupLaunchDestinationAsyncQueryHandler removePartyGroupLaunchDestinationAsyncQueryHandler = new RemovePartyGroupLaunchDestinationAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_id", str);
        removePartyGroupLaunchDestinationAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE, contentValues, null, null);
        return new AsyncQueryHandle(removePartyGroupLaunchDestinationAsyncQueryHandler, singleIDCallback);
    }

    public static final void setPartyVolume(Context context, float f) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(f, context) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$hsqjvoVrEh3jGSij35rM7vbf7LY */
            private final /* synthetic */ float f$0;
            private final /* synthetic */ Context f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyVolume$15(this.f$0, this.f$1);
            }
        });
    }

    static /* synthetic */ void lambda$setPartyVolume$15(float f, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_volume", Float.valueOf(f));
        if (context.getContentResolver().update(HorizonContent.FriendList.PARTY_SET_VOLUME, contentValues, null, null) != 1) {
            Log.e(TAG, "Error setting party volume");
        }
    }

    public static final float getPartyVolume(Context context) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(HorizonContent.FriendList.PARTY_GET_VOLUME, null, null, null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    float f = query.getFloat(query.getColumnIndexOrThrow("party_volume"));
                    if (query != null) {
                        query.close();
                    }
                    return f;
                }
            }
            Log.i(TAG, "getPartyVolume null cursor");
            if (query != null) {
                query.close();
            }
            return 0.0f;
        } catch (Exception e) {
            Log.e(TAG, "Error getting party volume", e);
            if (0 != 0) {
                cursor.close();
            }
            return 0.0f;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    public static final void getHasParticipantRecentlySpokenDEPRECATED(Context context, String str, FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback) {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, context, fetchParticipantSpeakerStatusCallback) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$QDBh9lEeGIHIie0Re_p3lgMOEw */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ HorizonContentProviderHelper.FetchParticipantSpeakerStatusCallback f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$getHasParticipantRecentlySpokenDEPRECATED$16(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ void lambda$getHasParticipantRecentlySpokenDEPRECATED$16(String str, Context context, FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback) {
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
                    if (cursor != null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
            }
            Log.i(TAG, "getHasParticipantRecentlySpoken null cursor");
            if (cursor != null) {
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

    public static final AsyncQueryHandle getHasParticipantRecentlySpoken(Context context, String str, FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback) {
        GetHasParticipantRecentlySpokenAsyncQueryHandler getHasParticipantRecentlySpokenAsyncQueryHandler = new GetHasParticipantRecentlySpokenAsyncQueryHandler(context.getContentResolver(), fetchParticipantSpeakerStatusCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, getHasParticipantRecentlySpokenAsyncQueryHandler) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$TX47hjTYJbh3PR22k71B1wCaTk */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ HorizonContentProviderHelper.GetHasParticipantRecentlySpokenAsyncQueryHandler f$1;

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

    /* access modifiers changed from: private */
    public static final class GetHasParticipantRecentlySpokenAsyncQueryHandler extends AsyncQueryHandlerBase<FetchParticipantSpeakerStatusCallback> {
        public GetHasParticipantRecentlySpokenAsyncQueryHandler(ContentResolver contentResolver, FetchParticipantSpeakerStatusCallback fetchParticipantSpeakerStatusCallback) {
            super(contentResolver, fetchParticipantSpeakerStatusCallback);
        }

        /* access modifiers changed from: protected */
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

    /* access modifiers changed from: private */
    public static final class SetPartyLocalMuteAsyncQueryHandler extends AsyncQueryHandler {
        private final MuteStateCallback mCallback;

        public SetPartyLocalMuteAsyncQueryHandler(ContentResolver contentResolver, MuteStateCallback muteStateCallback) {
            super(contentResolver);
            this.mCallback = muteStateCallback;
        }

        /* access modifiers changed from: protected */
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

    public static final void setPartyLocalMuteAsync(Context context, int i, MuteStateCallback muteStateCallback) throws IllegalArgumentException {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(i, context, muteStateCallback) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$qctooCfCv8qFSQVos_pXnjO9s0 */
            private final /* synthetic */ int f$0;
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ HorizonContentProviderHelper.MuteStateCallback f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyLocalMuteAsync$18(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ void lambda$setPartyLocalMuteAsync$18(int i, Context context, MuteStateCallback muteStateCallback) {
        HorizonContent.PlatformPluginManager.MicrophoneMuteState.validate(i);
        SetPartyLocalMuteAsyncQueryHandler setPartyLocalMuteAsyncQueryHandler = new SetPartyLocalMuteAsyncQueryHandler(context.getContentResolver(), muteStateCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("party_self_mute", Integer.valueOf(i));
        setPartyLocalMuteAsyncQueryHandler.startUpdate(1, "", HorizonContent.FriendList.PARTY_SET_SELF_MUTE, contentValues, null, null);
    }

    /* access modifiers changed from: private */
    public static final class SetPartyPerPersonMuteAsyncQueryHandler extends AsyncQueryHandler {
        private final PerPersonMuteStateCallback mCallback;

        public SetPartyPerPersonMuteAsyncQueryHandler(ContentResolver contentResolver, PerPersonMuteStateCallback perPersonMuteStateCallback) {
            super(contentResolver);
            this.mCallback = perPersonMuteStateCallback;
        }

        /* access modifiers changed from: protected */
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
    }

    public static final void setPartyPerPersonMuteStatusAsync(Context context, String str, int i, PerPersonMuteStateCallback perPersonMuteStateCallback) throws IllegalArgumentException {
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback(str, i, new SetPartyPerPersonMuteAsyncQueryHandler(context.getContentResolver(), perPersonMuteStateCallback)) {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$4JEbq4VpHlbBPzNQrXd5eiC2MY */
            private final /* synthetic */ String f$0;
            private final /* synthetic */ int f$1;
            private final /* synthetic */ HorizonContentProviderHelper.SetPartyPerPersonMuteAsyncQueryHandler f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.lambda$setPartyPerPersonMuteStatusAsync$19(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    static /* synthetic */ void lambda$setPartyPerPersonMuteStatusAsync$19(String str, int i, SetPartyPerPersonMuteAsyncQueryHandler setPartyPerPersonMuteAsyncQueryHandler) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("party_per_person_mute_value", Integer.valueOf(i));
        setPartyPerPersonMuteAsyncQueryHandler.startUpdate(1, "", HorizonContent.FriendList.PARTY_SET_PER_PERSON_MUTE_STATUS, contentValues, null, null);
    }

    public static final int getPartyLocalMute(Context context) {
        return context.getContentResolver().update(HorizonContent.FriendList.PARTY_GET_SELF_MUTE, new ContentValues(), null, null);
    }

    private static final class FetchProfileContentAsyncQueryHandler extends AsyncQueryHandlerBase<FetchProfileContentCallback> {
        public FetchProfileContentAsyncQueryHandler(ContentResolver contentResolver, FetchProfileContentCallback fetchProfileContentCallback) {
            super(contentResolver, fetchProfileContentCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchProfileContentCallback fetchProfileContentCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchProfileContentCallback.onSuccess(HorizonContentProviderHelper.getProfileContentFromCursor(cursor));
                } catch (Exception unused) {
                    fetchProfileContentCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class SwitchMicrophoneInputAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        private final SingleIDCallback mCallback;

        public SwitchMicrophoneInputAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
            this.mCallback = singleIDCallback;
        }

        /* access modifiers changed from: protected */
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

    public static final AsyncQueryHandle sendMicrophoneInputToParty(Context context, SingleIDCallback singleIDCallback) throws IllegalArgumentException {
        SwitchMicrophoneInputAsyncQueryHandler switchMicrophoneInputAsyncQueryHandler = new SwitchMicrophoneInputAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback() {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$CxKxw68Rcdw1mB_iH_H_B2X3a_g */

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.SwitchMicrophoneInputAsyncQueryHandler.this.startUpdate(1, "", HorizonContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_PARTY, new ContentValues(), null, null);
            }
        });
        return new AsyncQueryHandle(switchMicrophoneInputAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle sendMicrophoneInputToApp(Context context, SingleIDCallback singleIDCallback) throws IllegalArgumentException {
        SwitchMicrophoneInputAsyncQueryHandler switchMicrophoneInputAsyncQueryHandler = new SwitchMicrophoneInputAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback() {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$8oeVd86KSwl7HpdoQpEq9B8ddw4 */

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.SwitchMicrophoneInputAsyncQueryHandler.this.startUpdate(1, "", HorizonContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_APP, new ContentValues(), null, null);
            }
        });
        return new AsyncQueryHandle(switchMicrophoneInputAsyncQueryHandler, singleIDCallback);
    }

    public static final AsyncQueryHandle getPartyMicrophoneInputLocation(Context context, GetPartyMicrophoneInputLocationCallback getPartyMicrophoneInputLocationCallback) {
        GetPartyMicrophoneInputLocationAsyncQueryHandler getPartyMicrophoneInputLocationAsyncQueryHandler = new GetPartyMicrophoneInputLocationAsyncQueryHandler(context.getContentResolver(), getPartyMicrophoneInputLocationCallback);
        HorizonContent.runOncePartyInfraGKFetched(context, new HorizonContent.PartyInfraGKCallback() {
            /* class com.oculus.horizoncontent.horizon.$$Lambda$HorizonContentProviderHelper$xmqpe3ZBUe4Fa8DHxK1AKXj_ow */

            @Override // com.oculus.horizoncontent.horizon.HorizonContent.PartyInfraGKCallback
            public final void call() {
                HorizonContentProviderHelper.GetPartyMicrophoneInputLocationAsyncQueryHandler.this.startQuery("", HorizonContent.FriendList.PARTY_GET_MICROPHONE_INPUT_LOCATION.buildUpon().build(), null, null, null, null);
            }
        });
        return new AsyncQueryHandle(getPartyMicrophoneInputLocationAsyncQueryHandler, getPartyMicrophoneInputLocationCallback);
    }

    /* access modifiers changed from: private */
    public static final class GetPartyMicrophoneInputLocationAsyncQueryHandler extends AsyncQueryHandlerBase<GetPartyMicrophoneInputLocationCallback> {
        public GetPartyMicrophoneInputLocationAsyncQueryHandler(ContentResolver contentResolver, GetPartyMicrophoneInputLocationCallback getPartyMicrophoneInputLocationCallback) {
            super(contentResolver, getPartyMicrophoneInputLocationCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(GetPartyMicrophoneInputLocationCallback getPartyMicrophoneInputLocationCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    cursor.moveToPosition(-1);
                    if (cursor.moveToNext()) {
                        getPartyMicrophoneInputLocationCallback.onSuccess(PartyMicrophoneInputLocation.get(Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(HorizonContent.FriendList.PARTY_MICROPHONE_INPUT_LOCATION)))));
                    }
                } catch (Exception unused) {
                    getPartyMicrophoneInputLocationCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchProfileContent(Context context, FetchProfileContentCallback fetchProfileContentCallback) {
        FetchProfileContentAsyncQueryHandler fetchProfileContentAsyncQueryHandler = new FetchProfileContentAsyncQueryHandler(context.getContentResolver(), fetchProfileContentCallback);
        fetchProfileContentAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_PROFILE_CONTENT, null, null, null, null);
        return new AsyncQueryHandle(fetchProfileContentAsyncQueryHandler, fetchProfileContentCallback);
    }

    /* access modifiers changed from: private */
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
        return new SelfVRProfileContent(cursor.getInt(columnIndex), getStringFromIndex(cursor, columnIndex2, ""), Boolean.valueOf(getBoolFromIntIndex(cursor, columnIndex3)), getStringFromIndex(cursor, columnIndex4, ""), getStringFromIndex(cursor, columnIndex5, ""), getStringFromIndex(cursor, columnIndex6, ""), getStringFromIndex(cursor, columnIndex7, ""), getStringFromIndex(cursor, columnIndex8, ""));
    }

    private static final class SetUserBioAsyncQueryHandler extends AsyncQueryHandlerBase<SingleIDCallback> {
        public SetUserBioAsyncQueryHandler(ContentResolver contentResolver, SingleIDCallback singleIDCallback) {
            super(contentResolver, singleIDCallback);
        }

        /* access modifiers changed from: protected */
        public void onUpdateComplete(SingleIDCallback singleIDCallback, Object obj, int i) {
            if (i == 1) {
                singleIDCallback.onSuccess((String) obj);
            } else {
                singleIDCallback.onError();
            }
        }
    }

    public static final AsyncQueryHandle setUserBio(Context context, String str, String str2, SingleIDCallback singleIDCallback) {
        SetUserBioAsyncQueryHandler setUserBioAsyncQueryHandler = new SetUserBioAsyncQueryHandler(context.getContentResolver(), singleIDCallback);
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", str);
        contentValues.put("biography", str2);
        setUserBioAsyncQueryHandler.startUpdate(str, HorizonContent.FriendList.PROFILE_SET_BIO, contentValues, null, null);
        return new AsyncQueryHandle(setUserBioAsyncQueryHandler, singleIDCallback);
    }

    private static final class FetchSocialViewerAsyncQueryHandler extends AsyncQueryHandlerBase<FetchSocialViewerInfoCallback> {
        public FetchSocialViewerAsyncQueryHandler(ContentResolver contentResolver, FetchSocialViewerInfoCallback fetchSocialViewerInfoCallback) {
            super(contentResolver, fetchSocialViewerInfoCallback);
        }

        /* access modifiers changed from: protected */
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

    public static final AsyncQueryHandle fetchSocialViewer(Context context, FetchSocialViewerInfoCallback fetchSocialViewerInfoCallback) {
        FetchSocialViewerAsyncQueryHandler fetchSocialViewerAsyncQueryHandler = new FetchSocialViewerAsyncQueryHandler(context.getContentResolver(), fetchSocialViewerInfoCallback);
        fetchSocialViewerAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_SOCIAL_VIEWER, null, null, null, null);
        return new AsyncQueryHandle(fetchSocialViewerAsyncQueryHandler, fetchSocialViewerInfoCallback);
    }

    /* access modifiers changed from: private */
    public static SocialViewerInfo getSocialViewerInfoFromCursor(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("alias");
        int columnIndex2 = cursor.getColumnIndex("profile_image_url");
        int columnIndex3 = cursor.getColumnIndex("avatar_image_url");
        int columnIndex4 = cursor.getColumnIndex("has_seen_vr_invite_profile_nux");
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        return new SocialViewerInfo(getStringFromIndex(cursor, columnIndex, ""), getStringFromIndex(cursor, columnIndex2, ""), getStringFromIndex(cursor, columnIndex3, ""), getBoolFromIntIndex(cursor, columnIndex4));
    }

    private static final class FetchVRProfileContentAsyncQueryHandler extends AsyncQueryHandlerBase<FetchVRProfileContentCallback> {
        public FetchVRProfileContentAsyncQueryHandler(ContentResolver contentResolver, FetchVRProfileContentCallback fetchVRProfileContentCallback) {
            super(contentResolver, fetchVRProfileContentCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(FetchVRProfileContentCallback fetchVRProfileContentCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    fetchVRProfileContentCallback.onSuccess(HorizonContentProviderHelper.getVRProfileContentFromCursor(cursor));
                } catch (Exception unused) {
                    fetchVRProfileContentCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle fetchVRProfileContent(Context context, String str, FetchVRProfileContentCallback fetchVRProfileContentCallback) {
        FetchVRProfileContentAsyncQueryHandler fetchVRProfileContentAsyncQueryHandler = new FetchVRProfileContentAsyncQueryHandler(context.getContentResolver(), fetchVRProfileContentCallback);
        fetchVRProfileContentAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_VR_PROFILE_CONTENT.buildUpon().appendQueryParameter("id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(fetchVRProfileContentAsyncQueryHandler, fetchVRProfileContentCallback);
    }

    /* access modifiers changed from: private */
    public static VRProfileContent getVRProfileContentFromCursor(Cursor cursor) {
        SocialUserPresenceStatus socialUserPresenceStatus;
        int columnIndex = cursor.getColumnIndex("alias");
        int columnIndex2 = cursor.getColumnIndex("biography");
        int columnIndex3 = cursor.getColumnIndex("blocked_by_viewer");
        int columnIndex4 = cursor.getColumnIndex("profile_image_url");
        int columnIndex5 = cursor.getColumnIndex("avatar_image_url");
        int columnIndex6 = cursor.getColumnIndex("friendship_status");
        int columnIndex7 = cursor.getColumnIndex("is_current");
        int columnIndex8 = cursor.getColumnIndex(HorizonContent.FriendList.LAST_ACTIVE_TIME_COLUMN);
        if (columnIndex8 == -1) {
            Log.d(TAG, "Last active time column does not exist");
        }
        int columnIndex9 = cursor.getColumnIndex(HorizonContent.FriendList.LAST_PRESENCE_COLUMN);
        int columnIndex10 = cursor.getColumnIndex("presence");
        int columnIndex11 = cursor.getColumnIndex(HorizonContent.FriendList.PRESENCE_STATUS_COLUMN);
        int columnIndex12 = cursor.getColumnIndex("name");
        int columnIndex13 = cursor.getColumnIndex("current_party_id");
        int columnIndex14 = cursor.getColumnIndex("party_can_invite_user");
        int columnIndex15 = cursor.getColumnIndex("fb_linked_status");
        int columnIndex16 = cursor.getColumnIndex("joinable_party_id");
        cursor.moveToPosition(-1);
        if (!cursor.moveToNext()) {
            return null;
        }
        String stringFromIndex = getStringFromIndex(cursor, columnIndex11, null);
        String stringFromIndex2 = getStringFromIndex(cursor, columnIndex, "");
        String stringFromIndex3 = getStringFromIndex(cursor, columnIndex2, "");
        SocialUserFriendshipStatus socialUserFriendshipStatus = SocialUserFriendshipStatus.get(getStringFromIndex(cursor, columnIndex6, ""));
        Boolean valueOf = Boolean.valueOf(getBoolFromIntIndex(cursor, columnIndex3));
        String stringFromIndex4 = getStringFromIndex(cursor, columnIndex4, "");
        String stringFromIndex5 = getStringFromIndex(cursor, columnIndex5, "");
        Boolean valueOf2 = Boolean.valueOf(getBoolFromIntIndex(cursor, columnIndex7));
        long longFromIndex = getLongFromIndex(cursor, columnIndex8, 0);
        String stringFromIndex6 = getStringFromIndex(cursor, columnIndex9, "");
        String stringFromIndex7 = getStringFromIndex(cursor, columnIndex10, "");
        if (stringFromIndex == null) {
            socialUserPresenceStatus = SocialUserPresenceStatus.OFFLINE;
        } else {
            socialUserPresenceStatus = SocialUserPresenceStatus.valueOf(stringFromIndex);
        }
        return new VRProfileContent(stringFromIndex2, stringFromIndex3, socialUserFriendshipStatus, valueOf, stringFromIndex4, stringFromIndex5, valueOf2, longFromIndex, stringFromIndex6, stringFromIndex7, socialUserPresenceStatus, getStringFromIndex(cursor, columnIndex12, ""), getStringFromIndex(cursor, columnIndex13, null), Boolean.valueOf(getBoolFromIntIndex(cursor, columnIndex14)), new LinkedAccountsInfo(getStringFromIndex(cursor, columnIndex15, "")), getStringFromIndex(cursor, columnIndex16, null));
    }

    private static final class GetFbFriendPrimaryProfileAsyncQueryHandler extends AsyncQueryHandlerBase<GetFbFriendPrimaryProfileCallback> {
        private Context mContext;

        public GetFbFriendPrimaryProfileAsyncQueryHandler(Context context, ContentResolver contentResolver, GetFbFriendPrimaryProfileCallback getFbFriendPrimaryProfileCallback) {
            super(contentResolver, getFbFriendPrimaryProfileCallback);
            this.mContext = context;
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(GetFbFriendPrimaryProfileCallback getFbFriendPrimaryProfileCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    getFbFriendPrimaryProfileCallback.onSuccess(HorizonContentProviderHelper.getFbFriendPrimaryProfileFromCursor(this.mContext, cursor));
                } catch (Exception unused) {
                    getFbFriendPrimaryProfileCallback.onError();
                }
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static final AsyncQueryHandle getFbFriendPrimaryProfile(Context context, String str, GetFbFriendPrimaryProfileCallback getFbFriendPrimaryProfileCallback) {
        GetFbFriendPrimaryProfileAsyncQueryHandler getFbFriendPrimaryProfileAsyncQueryHandler = new GetFbFriendPrimaryProfileAsyncQueryHandler(context, context.getContentResolver(), getFbFriendPrimaryProfileCallback);
        getFbFriendPrimaryProfileAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_FB_FRIEND_PRIMARY_PROFILE.buildUpon().appendQueryParameter("fb_friend_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(getFbFriendPrimaryProfileAsyncQueryHandler, getFbFriendPrimaryProfileCallback);
    }

    /* access modifiers changed from: private */
    public static FbFriendPrimaryProfile getFbFriendPrimaryProfileFromCursor(Context context, Cursor cursor) {
        SocialUserFriendshipStatus socialUserFriendshipStatus;
        SocialUserPresenceStatus socialUserPresenceStatus;
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
        boolean boolFromIntIndex = getBoolFromIntIndex(cursor, columnIndexOrThrow4);
        Long valueOf = Long.valueOf(cursor.getLong(columnIndexOrThrow5));
        String string6 = cursor.getString(columnIndexOrThrow6);
        if (string == null) {
            socialUserFriendshipStatus = SocialUserFriendshipStatus.UNKNOWN;
        } else {
            socialUserFriendshipStatus = SocialUserFriendshipStatus.valueOf(string);
        }
        if (string2 == null) {
            socialUserPresenceStatus = SocialUserPresenceStatus.OFFLINE;
        } else {
            socialUserPresenceStatus = SocialUserPresenceStatus.valueOf(string2);
        }
        return new FbFriendPrimaryProfile(string3, string4, string5, boolFromIntIndex, valueOf, string6, socialUserFriendshipStatus, socialUserPresenceStatus, getBoolFromIntIndex(cursor, columnIndexOrThrow9), cursor.getString(columnIndexOrThrow10), Long.valueOf(cursor.getLong(columnIndexOrThrow11)), getBoolFromIntIndex(cursor, columnIndexOrThrow12), cursor.getString(columnIndexOrThrow13), cursor.getString(columnIndexOrThrow14), cursor.getString(columnIndexOrThrow15));
    }

    private static final class GetBlockedUserIdAsyncQueryHandler extends AsyncQueryHandlerBase<GetBlockedUserIdCallback> {
        public GetBlockedUserIdAsyncQueryHandler(ContentResolver contentResolver, GetBlockedUserIdCallback getBlockedUserIdCallback) {
            super(contentResolver, getBlockedUserIdCallback);
        }

        /* access modifiers changed from: protected */
        public void onQueryComplete(GetBlockedUserIdCallback getBlockedUserIdCallback, Cursor cursor) {
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        getBlockedUserIdCallback.onSuccess(cursor.getString(cursor.getColumnIndex("blocked_user_id")));
                        return;
                    }
                } catch (Exception unused) {
                    getBlockedUserIdCallback.onError();
                    return;
                }
            }
            throw new RuntimeException();
        }
    }

    public static final AsyncQueryHandle getBlockedUserId(Context context, String str, GetBlockedUserIdCallback getBlockedUserIdCallback) {
        GetBlockedUserIdAsyncQueryHandler getBlockedUserIdAsyncQueryHandler = new GetBlockedUserIdAsyncQueryHandler(context.getContentResolver(), getBlockedUserIdCallback);
        getBlockedUserIdAsyncQueryHandler.startQuery("", HorizonContent.FriendList.GET_BLOCKED_USER_ID.buildUpon().appendQueryParameter("user_id", str).build(), null, null, null, null);
        return new AsyncQueryHandle(getBlockedUserIdAsyncQueryHandler, getBlockedUserIdCallback);
    }
}
