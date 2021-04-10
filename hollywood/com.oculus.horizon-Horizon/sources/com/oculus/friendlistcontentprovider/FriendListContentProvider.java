package com.oculus.friendlistcontentprovider;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass0DC;
import X.AnonymousClass0DD;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.facebook.ultralight.Eager;
import com.google.common.collect.ImmutableSet;
import com.oculus.horizon.api.common.PageInfo;
import com.oculus.horizon.api.common.user.ProfilePhoto;
import com.oculus.horizon.api.common.user.User;
import com.oculus.horizon.api.graphql.GraphQLSocialQuery;
import com.oculus.horizon.api.social.AppScoreboardsInfoResponse;
import com.oculus.horizon.api.social.AppsScoreboardsInfoResponse;
import com.oculus.horizon.api.social.GenericErrorResponse;
import com.oculus.horizon.api.social.GetPartyInfoWithMembersResponse;
import com.oculus.horizon.api.social.GroupLaunchAppDestinationsResponse;
import com.oculus.horizon.api.social.GroupLaunchInfo;
import com.oculus.horizon.api.social.GroupLaunchSupportedApplicationsResponse;
import com.oculus.horizon.api.social.JoinablePartiesResponse;
import com.oculus.horizon.api.social.PYMKResponse;
import com.oculus.horizon.api.social.PartyInfoResponse;
import com.oculus.horizon.api.social.PartyInviteInfoResponse;
import com.oculus.horizon.api.social.PartyInvitesResponse;
import com.oculus.horizon.api.social.PartyListResponse;
import com.oculus.horizon.api.social.PartyPrivacyInfoResponse;
import com.oculus.horizon.api.social.PeopleNearbyResponse;
import com.oculus.horizon.api.social.SharePartyInfoResponse;
import com.oculus.horizon.api.social.SocialDeeplinkTarget;
import com.oculus.horizon.api.social.SocialFriendRequestsResponse;
import com.oculus.horizon.api.social.SocialFriendsResponse;
import com.oculus.horizon.api.social.SocialUser;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizon.social.SocialMethods;
import com.oculus.horizon.social.api.AuiProfileContentResponse;
import com.oculus.horizon.social.api.BlockedUser;
import com.oculus.horizon.social.api.BlockedUsersResponse;
import com.oculus.horizon.social.api.FbLinkedStatusResponse;
import com.oculus.horizon.social.api.GetPartyInfoWithActivityResponse;
import com.oculus.horizon.social.api.PartyCreateResponse;
import com.oculus.horizon.social.api.PartyInvitableUsersResponse;
import com.oculus.horizon.social.api.SearchResultUser;
import com.oculus.horizon.social.api.SocialActivityResponse;
import com.oculus.horizon.social.api.SocialViewerInfoResponse;
import com.oculus.horizon.social.api.UserSearchResponse;
import com.oculus.horizon.social.request.ApplicationListParams;
import com.oculus.horizon.social.request.ApplicationParams;
import com.oculus.horizon.social.request.BiographySetParams;
import com.oculus.horizon.social.request.ChatMutationParams;
import com.oculus.horizon.social.request.DeeplinkNonceParam;
import com.oculus.horizon.social.request.FriendListParams;
import com.oculus.horizon.social.request.FriendRequestSource;
import com.oculus.horizon.social.request.GroupLaunchLaunchHandleParams;
import com.oculus.horizon.social.request.GroupLaunchSetStateParams;
import com.oculus.horizon.social.request.GroupLaunchSetUserResponseParams;
import com.oculus.horizon.social.request.NuxFlagForUserMutationParams;
import com.oculus.horizon.social.request.PartyCancelInviteParams;
import com.oculus.horizon.social.request.PartyCreateParams;
import com.oculus.horizon.social.request.PartyIDMutationParam;
import com.oculus.horizon.social.request.PartyIDParam;
import com.oculus.horizon.social.request.PartyInviteUsersParams;
import com.oculus.horizon.social.request.PartyJoinParams;
import com.oculus.horizon.social.request.PartyKickParams;
import com.oculus.horizon.social.request.PartyLeaveParams;
import com.oculus.horizon.social.request.PartyProposedGroupLaunchDestinationCreateParams;
import com.oculus.horizon.social.request.PartyProposedGroupLaunchDestinationRemoveParams;
import com.oculus.horizon.social.request.PartySetTypeParams;
import com.oculus.horizon.social.request.PeopleNearbyParams;
import com.oculus.horizon.social.request.SingleUserParams;
import com.oculus.horizon.social.request.SocialActivityParams;
import com.oculus.horizon.social.request.UserMutationParams;
import com.oculus.horizon.social.request.UserSearchParams;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.partycontroller.PartyController;
import com.oculus.platforminitexception.PlatformInitException;
import com.oculus.provider.OculusContent;
import com.oculus.util.constants.MicrophoneMuteState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit.RetrofitError;

public class FriendListContentProvider extends OculusLoggedInUserFbPermissionsContentProvider {
    public static final String[] DEFAULT_FRIENDS_ORDERBY = {"self_last", "are_friends", "status", "last_online", "name_or_alias"};
    public static final String ID_LIST_DELIMITER = ":";
    public static final String LIVING_ROOM_PACKAGE_NAME = "com.oculus.livingroom";
    public static final String OCULUS_TV_PACKAGE_NAME = "com.oculus.tv";
    public static final String STRING_LIST_DELIMITER = ",";
    public static final String TAG = "FriendListContentProvider";
    public static final ImmutableSet<String> WHITELISTED_PACKAGES = ImmutableSet.A07("com.oculus.vrshell", "com.oculus.vrshell.home", "com.oculus.socialplatform", "com.oculus.livingroom", "com.oculus.tv", "com.oculus.systemux", new String[0]);
    public AnonymousClass0QC _UL_mInjectionContext;
    public String mFbPermissionName;
    public boolean mIsMicSwitchingEnabled;
    @Inject
    @Eager
    public SocialMethods mSocialMethods;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, FriendListContentProvider friendListContentProvider) {
        friendListContentProvider._UL_mInjectionContext = new AnonymousClass0QC(2, r2);
        friendListContentProvider.mSocialMethods = (SocialMethods) AnonymousClass117.A00(500, r2);
    }

    public static void addPartyUser(MatrixCursor matrixCursor, PartyInviteInfoResponse.Party party, SocialUser socialUser, int i) {
        matrixCursor.addRow(new Object[]{Integer.valueOf(i), party.id, socialUser.id, socialUser.alias, getSocialUserPhotoUrl(socialUser)});
    }

    @Nullable
    public static Cursor convertPartyInfoToCursor(PartyInviteInfoResponse partyInviteInfoResponse) {
        PartyInviteInfoResponse.Party node;
        String str;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{OculusContent.FriendList.ROW_TYPE_COLUMN, "party_id", "id", OculusContent.FriendList.ALIAS_COLUMN, "image_url"}, 0);
        if (partyInviteInfoResponse == null || (node = partyInviteInfoResponse.getNode()) == null) {
            return null;
        }
        PartyInviteInfoResponse.ViewerParty viewerParty = partyInviteInfoResponse.viewer.user.current_party;
        matrixCursor.addRow(new Object[]{0, node.id, null, node.party_type, null});
        Object[] objArr = new Object[5];
        objArr[0] = 9;
        if (viewerParty == null) {
            str = null;
        } else {
            str = viewerParty.id;
        }
        objArr[1] = str;
        objArr[2] = null;
        objArr[3] = null;
        objArr[4] = null;
        matrixCursor.addRow(objArr);
        SocialUser socialUser = node.party_leader;
        if (socialUser != null) {
            addPartyUser(matrixCursor, node, socialUser, 1);
        }
        SocialUser socialUser2 = node.invited_by;
        if (socialUser2 != null) {
            addPartyUser(matrixCursor, node, socialUser2, 2);
        }
        Iterator<SocialUser> it = node.party_users.nodes.iterator();
        while (it.hasNext()) {
            addPartyUser(matrixCursor, node, it.next(), 4);
        }
        Iterator<SocialUser> it2 = node.invited_users.nodes.iterator();
        while (it2.hasNext()) {
            addPartyUser(matrixCursor, node, it2.next(), 3);
        }
        Iterator<SocialUser> it3 = node.party_blocked_users.nodes.iterator();
        while (it3.hasNext()) {
            addPartyUser(matrixCursor, node, it3.next(), 7);
        }
        Iterator<SocialUser> it4 = node.party_blocked_invited_users.nodes.iterator();
        while (it4.hasNext()) {
            addPartyUser(matrixCursor, node, it4.next(), 8);
        }
        PartyInviteInfoResponse.GroupLaunch groupLaunch = node.party_group_launch;
        if (groupLaunch != null) {
            PartyInviteInfoResponse.Application application = groupLaunch.destination.application;
            matrixCursor.addRow(new Object[]{5, node.id, application.id, application.display_name, application.icon_image.uri});
            PartyInviteInfoResponse.GroupLaunchDestination groupLaunchDestination = groupLaunch.destination;
            matrixCursor.addRow(new Object[]{6, node.id, groupLaunchDestination.id, groupLaunchDestination.display_name, groupLaunchDestination.image});
        }
        return matrixCursor;
    }

    @Nullable
    private Cursor getGroupLaunchAppDestinations(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        GroupLaunchAppDestinationsResponse.Node node;
        GroupLaunchAppDestinationsResponse.Grouping grouping;
        GroupLaunchAppDestinationsResponse.Destinations destinations;
        List<GroupLaunchAppDestinationsResponse.Edge> list;
        PageInfo pageInfo;
        MatrixCursor matrixCursor = null;
        Integer num = null;
        if (str != null) {
            if (str3 != null) {
                num = Integer.valueOf(Integer.parseInt(str3));
            }
            GroupLaunchAppDestinationsResponse groupLaunchAppDestinationsSync = this.mSocialMethods.mMethods.getGroupLaunchAppDestinationsSync(GraphQLSocialQuery.GROUP_LAUNCH_APP_DESTINATIONS_QUERY, new ApplicationParams(str, str2, num));
            int i = 8;
            matrixCursor = new MatrixCursor(new String[]{OculusContent.FriendList.DESTINATION_DEEPLINK_MESSAGE_FOR_DEEPLINK_TARGET_COLUMN, OculusContent.FriendList.DESTINATION_DISPLAY_NAME, "destination_id", OculusContent.FriendList.DESTINATION_IMAGE, OculusContent.FriendList.DESTINATION_MAX_CAPACITY, OculusContent.FriendList.DESTINATION_IS_EXTERNAL_DEEPLINKABLE, "cursor", OculusContent.Paging.HAS_NEXT}, 0);
            if (!(groupLaunchAppDestinationsSync == null || (node = groupLaunchAppDestinationsSync.node) == null || (grouping = node.grouping) == null || (destinations = grouping.destinations) == null || (list = destinations.edges) == null || (pageInfo = destinations.page_info) == null)) {
                boolean z = pageInfo.has_next_page;
                int size = list.size() - 1;
                int i2 = 0;
                for (GroupLaunchAppDestinationsResponse.Edge edge : groupLaunchAppDestinationsSync.node.grouping.destinations.edges) {
                    GroupLaunchAppDestinationsResponse.Destination destination = edge.node;
                    Object[] objArr = new Object[i];
                    objArr[0] = destination.deeplink_message_for_deeplink_target;
                    objArr[1] = destination.display_name;
                    objArr[2] = destination.id;
                    objArr[3] = destination.image;
                    objArr[4] = Integer.valueOf(destination.max_group_launch_capacity);
                    objArr[5] = destination.is_external_deeplinkable;
                    objArr[6] = edge.cursor;
                    boolean z2 = z;
                    if (i2 < size) {
                        z2 = true;
                    }
                    int i3 = z2 ? 1 : 0;
                    int i4 = z2 ? 1 : 0;
                    int i5 = z2 ? 1 : 0;
                    objArr[7] = Integer.valueOf(i3);
                    matrixCursor.addRow(objArr);
                    i2++;
                    i = 8;
                }
            }
        }
        return matrixCursor;
    }

    @Nullable
    public static String getGroupLaunchResponse(GroupLaunchInfo.GroupLaunchUsers groupLaunchUsers, SocialUser socialUser) {
        if (groupLaunchUsers == null) {
            return null;
        }
        Iterator<GroupLaunchInfo.GroupLaunchUsersEdge> it = groupLaunchUsers.edges.iterator();
        while (it.hasNext()) {
            GroupLaunchInfo.GroupLaunchUsersEdge next = it.next();
            if (next.node.id.equals(socialUser.id)) {
                return next.proposal_response;
            }
        }
        return null;
    }

    private Cursor getPartyMicrophoneInputLocation() {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{OculusContent.FriendList.PARTY_MICROPHONE_INPUT_LOCATION}, 0);
        matrixCursor.addRow(new Object[]{Integer.valueOf(getPartyInterface().A47() ? 1 : 0)});
        return matrixCursor;
    }

    @Nullable
    @SuppressLint({"CatchGeneralException"})
    private Cursor getUserSearch(@Nullable String str, String str2) {
        MatrixCursor matrixCursor;
        Exception e;
        if (str == null) {
            return null;
        }
        try {
            AnonymousClass0DC<UserSearchResponse> userSearchAsync = getUserSearchAsync(str, str2);
            userSearchAsync.A0H();
            if (userSearchAsync.A0F() != null) {
                return null;
            }
            matrixCursor = new MatrixCursor(new String[]{"user_id", "name", OculusContent.FriendList.USER_SEARCH_AVATAR_URL, OculusContent.FriendList.USER_SEARCH_FRIEND_STATUS, OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.CAN_VIEWER_MESSAGE_COLUMN}, 1);
            try {
                for (SearchResultUser searchResultUser : userSearchAsync.A0G().results.nodes) {
                    String userSearchAvatarUrl = getUserSearchAvatarUrl(searchResultUser);
                    String userSearchName = getUserSearchName(searchResultUser);
                    Object[] objArr = new Object[6];
                    objArr[0] = searchResultUser.user_id;
                    objArr[1] = userSearchName;
                    objArr[2] = userSearchAvatarUrl;
                    objArr[3] = searchResultUser.friend_status;
                    objArr[4] = searchResultUser.alias;
                    int i = 0;
                    if (searchResultUser.can_viewer_message) {
                        i = 1;
                    }
                    objArr[5] = Integer.valueOf(i);
                    matrixCursor.addRow(objArr);
                }
            } catch (Exception e2) {
                e = e2;
                AnonymousClass0NO.A0B(TAG, "User search was interrupted.", e);
                return matrixCursor;
            }
            return matrixCursor;
        } catch (Exception e3) {
            e = e3;
            matrixCursor = null;
            AnonymousClass0NO.A0B(TAG, "User search was interrupted.", e);
            return matrixCursor;
        }
    }

    @Nullable
    @SuppressLint({"CatchGeneralException"})
    private Cursor joinPartyQuery(String str) {
        Exception e;
        MatrixCursor matrixCursor = null;
        try {
            AnonymousClass0DC<String> joinPartyAsync = joinPartyAsync(str);
            joinPartyAsync.A0H();
            if (joinPartyAsync.A0F() != null) {
                MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{OculusContent.FriendList.PARTY_JOIN_ERROR_COLUMN}, 1);
                try {
                    matrixCursor2.addRow(new Object[]{joinPartyAsync.A0F().getMessage()});
                    return matrixCursor2;
                } catch (Exception e2) {
                    e = e2;
                    matrixCursor = matrixCursor2;
                    AnonymousClass0NO.A0B(TAG, "Party join was interrupted.", e);
                    return matrixCursor;
                }
            }
        } catch (Exception e3) {
            e = e3;
            AnonymousClass0NO.A0B(TAG, "Party join was interrupted.", e);
            return matrixCursor;
        }
        return matrixCursor;
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public static void addBlockedUserRowForCurrentParty(MatrixCursor matrixCursor, PartyInfoResponse.BlockedUser blockedUser) {
        matrixCursor.addRow(new Object[]{blockedUser.id, null, blockedUser.alias, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null});
    }

    public static void addNewResultTypeIndicatorRowForCurrentParty(MatrixCursor matrixCursor, PartyListResponse.ListType listType) {
        matrixCursor.addRow(new Object[]{null, null, null, null, null, null, null, null, null, null, null, listType.toString(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null});
    }

    private Cursor createParty(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        PartyCreateResponse partyCreate = this.mSocialMethods.mMethods.partyCreate(GraphQLSocialQuery.PARTY_CREATE, new PartyCreateParams(str, str2, str3, str4), "");
        String str5 = partyCreate.party.id;
        if (partyCreate.did_create_party) {
            startPartyChat(str5);
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"current_party_id"}, 1);
        matrixCursor.addRow(new Object[]{str5});
        return matrixCursor;
    }

    private Cursor getAppScoreboardsInfo(String str) {
        AppScoreboardsInfoResponse.Node node;
        AppScoreboardsInfoResponse.Grouping grouping;
        AppScoreboardsInfoResponse.AchievementDefinitions achievementDefinitions;
        AppScoreboardsInfoResponse.Leaderboards leaderboards;
        AppScoreboardsInfoResponse appScoreboardsInfoSync = this.mSocialMethods.mMethods.getAppScoreboardsInfoSync(GraphQLSocialQuery.APP_SCOREBOARDS_QUERY, new ApplicationParams(str));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"appId", "hasAchievements", "hasLeaderboards"}, 0);
        if (!(appScoreboardsInfoSync == null || (node = appScoreboardsInfoSync.node) == null || (grouping = node.grouping) == null || (achievementDefinitions = grouping.achievement_definitions) == null || (leaderboards = grouping.leaderboards) == null)) {
            boolean z = !achievementDefinitions.is_empty;
            boolean z2 = !leaderboards.is_empty;
            Object[] objArr = new Object[3];
            objArr[0] = str;
            String str2 = "true";
            String str3 = "false";
            if (z) {
                str3 = str2;
            }
            objArr[1] = str3;
            if (!z2) {
                str2 = "false";
            }
            objArr[2] = str2;
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }

    private Cursor getAppsScoreboardsInfo(String[] strArr) {
        ArrayList<AppsScoreboardsInfoResponse.Node> arrayList;
        AppsScoreboardsInfoResponse.Grouping grouping;
        AppsScoreboardsInfoResponse.AchievementDefinitions achievementDefinitions;
        AppsScoreboardsInfoResponse.Leaderboards leaderboards;
        AppsScoreboardsInfoResponse appsScoreboardsInfoSync = this.mSocialMethods.mMethods.getAppsScoreboardsInfoSync(GraphQLSocialQuery.APPS_SCOREBOARDS_QUERY, new ApplicationListParams(strArr));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"appId", "hasAchievements", "hasLeaderboards"}, 0);
        if (!(appsScoreboardsInfoSync == null || (arrayList = appsScoreboardsInfoSync.nodes) == null)) {
            Iterator<AppsScoreboardsInfoResponse.Node> it = arrayList.iterator();
            while (it.hasNext()) {
                AppsScoreboardsInfoResponse.Node next = it.next();
                String str = next.id;
                if (!(str == null || (grouping = next.grouping) == null || (achievementDefinitions = grouping.achievement_definitions) == null || (leaderboards = grouping.leaderboards) == null)) {
                    boolean z = !achievementDefinitions.is_empty;
                    boolean z2 = !leaderboards.is_empty;
                    Object[] objArr = new Object[3];
                    objArr[0] = str;
                    String str2 = "true";
                    String str3 = "false";
                    if (z) {
                        str3 = str2;
                    }
                    objArr[1] = str3;
                    if (!z2) {
                        str2 = "false";
                    }
                    objArr[2] = str2;
                    matrixCursor.addRow(objArr);
                }
            }
        }
        return matrixCursor;
    }

    private Cursor getAuiProfileContent() {
        AuiProfileContentResponse.User user;
        AuiProfileContentResponse.Friends friends;
        String str;
        String str2;
        AuiProfileContentResponse auiProfileContentSync = this.mSocialMethods.mMethods.getAuiProfileContentSync(GraphQLSocialQuery.AUI_VIEWER_PROFILE);
        int i = 0;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{OculusContent.FriendList.FRIEND_COUNT, OculusContent.FriendList.BIOGRAPHY_STRING, OculusContent.FriendList.PRESENCE_COLUMN, OculusContent.FriendList.ACTIVE_COLUMN, "name", OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, OculusContent.FriendList.AVATAR_IMAGE_URL}, 0);
        if (!(auiProfileContentSync == null || (user = auiProfileContentSync.user) == null || (friends = user.friends) == null)) {
            AuiProfileContentResponse.ProfilePhoto profilePhoto = user.profile_photo;
            AuiProfileContentResponse.AvatarImage avatarImage = user.avatar_image;
            Object[] objArr = new Object[8];
            objArr[0] = Integer.valueOf(friends.count);
            objArr[1] = user.biography;
            String str3 = user.presence;
            String str4 = "";
            if (str3 == null) {
                str3 = str4;
            }
            objArr[2] = str3;
            AuiProfileContentResponse.MostRecentPresence mostRecentPresence = user.most_recent_presence;
            if (mostRecentPresence != null && mostRecentPresence.is_current) {
                i = 1;
            }
            objArr[3] = Integer.valueOf(i);
            String str5 = user.name;
            if (str5 == null) {
                str5 = str4;
            }
            objArr[4] = str5;
            String str6 = user.alias;
            if (str6 == null) {
                str6 = str4;
            }
            objArr[5] = str6;
            if (profilePhoto == null || (str = profilePhoto.uri) == null) {
                str = str4;
            }
            objArr[6] = str;
            if (!(avatarImage == null || (str2 = avatarImage.uri) == null)) {
                str4 = str2;
            }
            objArr[7] = str4;
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }

    private Cursor getBlockedUserId(String str) {
        BlockedUsersResponse.BlockedUsers blockedUsers;
        ArrayList<BlockedUser> arrayList;
        BlockedUsersResponse blockedUsersSync = this.mSocialMethods.mMethods.getBlockedUsersSync(GraphQLSocialQuery.BLOCKED_USERS_QUERY);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"blocked_user_id"}, 0);
        if (blockedUsersSync != null && (blockedUsers = blockedUsersSync.blocked_users) != null && (arrayList = blockedUsers.nodes) != null) {
            Iterator<BlockedUser> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BlockedUser next = it.next();
                if (str.equals(next.possibly_blocked_real_user_id)) {
                    matrixCursor.addRow(new Object[]{next.id});
                    break;
                }
            }
        }
        return matrixCursor;
    }

    @Nullable
    public static String getDeepLinkLaunchParamString(SocialUser socialUser) {
        SocialUser.Presence presence = socialUser.most_recent_presence;
        if (!(presence == null || presence.scoped_user_id == null || presence.deeplink_message_for_deeplink_target == null)) {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject.put("id", socialUser.most_recent_presence.scoped_user_id);
                jSONObject.put(OculusContent.FriendList.ALIAS_COLUMN, socialUser.alias);
                jSONArray.put(jSONObject);
                jSONObject2.put("type", DeeplinkIntentUtils.INTENT_KEY_TYPE_DEEPLINK);
                jSONObject2.put(DeeplinkIntentUtils.INTENT_KEY_LAUNCH_SOURCE, "AUIv2_PARTY_UI");
                jSONObject2.put("users", jSONArray);
                jSONObject2.put("destination_api_name", socialUser.most_recent_presence.destination_api_name);
                jSONObject2.put("deeplink_message", socialUser.most_recent_presence.deeplink_message_for_deeplink_target);
                jSONObject3.put("ovr_social_launch", jSONObject2);
                return jSONObject3.toString();
            } catch (JSONException e) {
                AnonymousClass0NO.A0B(TAG, "Error parsing deep link information for party user.", e);
            }
        }
        return null;
    }

    public static SocialDeeplinkTarget getDeeplinkTarget(SocialUser socialUser) {
        SocialUser.Presence presence;
        ArrayList<SocialDeeplinkTarget> arrayList;
        SocialDeeplinkTarget socialDeeplinkTarget = new SocialDeeplinkTarget();
        socialDeeplinkTarget.id = "id";
        socialDeeplinkTarget.vr_platform = "vr_platform";
        socialDeeplinkTarget.display_name = "display_name";
        if (!(socialUser == null || (presence = socialUser.most_recent_presence) == null || (arrayList = presence.vr_apps_for_deeplink_target) == null)) {
            Iterator<SocialDeeplinkTarget> it = arrayList.iterator();
            while (it.hasNext()) {
                SocialDeeplinkTarget next = it.next();
                if (next.vr_platform.equals("ANDROID_6DOF")) {
                    return next;
                }
            }
        }
        return socialDeeplinkTarget;
    }

    private Cursor getFriendList() {
        PartyInvitableUsersResponse partyInvitableUsersResponse;
        int i;
        String str;
        OculusContent.FriendList.UserInviteState userInviteState;
        String loggedInUserId = getLoggedInUserId();
        if (loggedInUserId != null) {
            partyInvitableUsersResponse = this.mSocialMethods.mMethods.getPartyInvitableUsersSync(GraphQLSocialQuery.PARTY_INVITABLE_USERS, new SingleUserParams(loggedInUserId));
            i = partyInvitableUsersResponse.friends.count;
        } else {
            partyInvitableUsersResponse = null;
            i = 0;
        }
        int i2 = 6;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", OculusContent.FriendList.PRESENCE_COLUMN, OculusContent.FriendList.PRESENCE_STATUS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, OculusContent.FriendList.INVITE_STATE_COLUMN, "current_party_id"}, i);
        if (partyInvitableUsersResponse != null) {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            PartyInvitableUsersResponse.Party party = partyInvitableUsersResponse.current_party;
            if (party != null) {
                matrixCursor.addRow(new Object[]{null, null, null, null, 0, party.id});
                Iterator<User> it = partyInvitableUsersResponse.current_party.party_invited_users.nodes.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().id);
                }
                Iterator<User> it2 = partyInvitableUsersResponse.current_party.party_users.nodes.iterator();
                while (it2.hasNext()) {
                    hashSet2.add(it2.next().id);
                }
            }
            Iterator<User> it3 = partyInvitableUsersResponse.friends.nodes.iterator();
            while (it3.hasNext()) {
                User next = it3.next();
                String userDisplayName = getUserDisplayName(next);
                ProfilePhoto profilePhoto = next.profile_photo;
                if (profilePhoto == null || (str = profilePhoto.uri) == null) {
                    str = "";
                }
                int ordinal = OculusContent.FriendList.UserInviteState.DEFAULT_UNKNOWN.ordinal();
                if (hashSet2.contains(next.id)) {
                    userInviteState = OculusContent.FriendList.UserInviteState.JOINED;
                } else if (hashSet.contains(next.id)) {
                    userInviteState = OculusContent.FriendList.UserInviteState.INVITED;
                } else {
                    Object[] objArr = new Object[i2];
                    objArr[0] = next.id;
                    objArr[1] = userDisplayName;
                    objArr[2] = next.presence;
                    objArr[3] = str;
                    objArr[4] = Integer.valueOf(ordinal);
                    objArr[5] = null;
                    matrixCursor.addRow(objArr);
                    i2 = 6;
                }
                ordinal = userInviteState.ordinal();
                Object[] objArr2 = new Object[i2];
                objArr2[0] = next.id;
                objArr2[1] = userDisplayName;
                objArr2[2] = next.presence;
                objArr2[3] = str;
                objArr2[4] = Integer.valueOf(ordinal);
                objArr2[5] = null;
                matrixCursor.addRow(objArr2);
                i2 = 6;
            }
        }
        return matrixCursor;
    }

    private Cursor getFriendRequests() {
        SocialFriendRequestsResponse.FriendRequests friendRequests;
        ArrayList<SocialFriendRequestsResponse.FriendRequests.Edge> arrayList;
        SocialFriendRequestsResponse friendRequestsSync = this.mSocialMethods.mMethods.getFriendRequestsSync(GraphQLSocialQuery.FRIEND_REQUESTS_QUERY);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.PRESENCE_COLUMN, OculusContent.FriendList.PRESENCE_STATUS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, OculusContent.FriendList.FRIENDSHIP_STATUS_COLUMN}, 0);
        if (!(friendRequestsSync == null || (friendRequests = friendRequestsSync.friend_requests_received_2) == null || (arrayList = friendRequests.edges) == null)) {
            Iterator<SocialFriendRequestsResponse.FriendRequests.Edge> it = arrayList.iterator();
            while (it.hasNext()) {
                SocialUser socialUser = it.next().node;
                if (socialUser != null) {
                    matrixCursor.addRow(new Object[]{socialUser.user_id, socialUser.name, socialUser.alias, socialUser.presence, socialUser.presence_status, getSocialUserPhotoUrl(socialUser), getSocialUserFriendshipStatus(socialUser)});
                }
            }
        }
        return matrixCursor;
    }

    @Nullable
    private Cursor getGroupLaunchSupportedApplications() {
        GroupLaunchSupportedApplicationsResponse.Viewer viewer;
        GroupLaunchSupportedApplicationsResponse.User user;
        GroupLaunchSupportedApplicationsResponse.AppEntitlements appEntitlements;
        Map<String, Integer> hashMap;
        int i;
        GroupLaunchSupportedApplicationsResponse.PartyUsers partyUsers;
        List<GroupLaunchSupportedApplicationsResponse.PartyUser> list;
        GroupLaunchSupportedApplicationsResponse groupLaunchSupportedApplicationsSync = this.mSocialMethods.mMethods.getGroupLaunchSupportedApplicationsSync(GraphQLSocialQuery.GROUP_LAUNCH_SUPPORTED_APPLICATIONS_QUERY);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"application_id", OculusContent.FriendList.APPLICATION_DISPLAY_SHORT_DESCRIPTION_COLUMN, OculusContent.FriendList.APPLICATION_MAX_GROUP_LAUNCH_CAPACITY_COLUMN, OculusContent.FriendList.APPLICATION_PARTY_USER_ENTITLEMENT_COUNT_COLUMN, OculusContent.FriendList.PARTY_CURRENT_USERS_COLUMN}, 0);
        if (!(groupLaunchSupportedApplicationsSync == null || (viewer = groupLaunchSupportedApplicationsSync.viewer) == null || (user = viewer.user) == null || (appEntitlements = user.app_entitlements) == null || appEntitlements.nodes == null)) {
            GroupLaunchSupportedApplicationsResponse.CurrentParty currentParty = user.current_party;
            if (currentParty == null || (partyUsers = currentParty.party_users) == null || (list = partyUsers.nodes) == null) {
                hashMap = new HashMap<>();
                i = 0;
            } else {
                hashMap = getPartyMemberAppEntitlementCounts(list);
                i = currentParty.party_users.nodes.size();
            }
            for (GroupLaunchSupportedApplicationsResponse.AppEntitlement appEntitlement : groupLaunchSupportedApplicationsSync.viewer.user.app_entitlements.nodes) {
                GroupLaunchSupportedApplicationsResponse.Application application = appEntitlement.item;
                String str = application.id;
                if (str != null) {
                    matrixCursor.addRow(new Object[]{application.id, application.display_short_description, Integer.valueOf(application.max_group_launch_capacity), Integer.valueOf(hashMap.getOrDefault(str, 0).intValue()), Integer.valueOf(i)});
                }
            }
        }
        return matrixCursor;
    }

    private Cursor getJoinableParties() {
        int i;
        JoinablePartiesResponse joinablePartiesSync = this.mSocialMethods.mMethods.getJoinablePartiesSync(GraphQLSocialQuery.JOINABLE_PARTIES_QUERY);
        String[] strArr = {"party_id", OculusContent.FriendList.PARTY_BLOCKED_USERS_COUNT_COLUMN, "id", "name", OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.FRIENDSHIP_STATUS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, OculusContent.FriendList.PARTY_MAX_SIZE, OculusContent.FriendList.CAN_VIEWER_MESSAGE_COLUMN};
        JoinablePartiesResponse.JoinableParties joinableParties = joinablePartiesSync.joinable_parties_from_friends;
        if (joinableParties == null || (i = joinableParties.count) <= 0) {
            return new MatrixCursor(strArr, 0);
        }
        Iterator<JoinablePartiesResponse.JoinableParty> it = joinableParties.nodes.iterator();
        while (it.hasNext()) {
            i += it.next().party_users.count.intValue();
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, i);
        Iterator<JoinablePartiesResponse.JoinableParty> it2 = joinablePartiesSync.joinable_parties_from_friends.nodes.iterator();
        while (it2.hasNext()) {
            JoinablePartiesResponse.JoinableParty next = it2.next();
            matrixCursor.addRow(new Object[]{next.id, next.party_blocked_users.count, null, null, null, null, null, Integer.valueOf(next.max_size), null});
            Iterator<SocialUser> it3 = next.party_users.nodes.iterator();
            while (it3.hasNext()) {
                SocialUser next2 = it3.next();
                matrixCursor.addRow(new Object[]{null, null, next2.id, next2.name, next2.alias, getSocialUserFriendshipStatus(next2), getSocialUserPhotoUrl(next2), null, null, Integer.valueOf(next2.can_viewer_message ? 1 : 0)});
            }
        }
        return matrixCursor;
    }

    @Nullable
    private Cursor getLinkedAccountsInfo() {
        FbLinkedStatusResponse fbLinkedStatusSync = this.mSocialMethods.mMethods.getFbLinkedStatusSync(GraphQLSocialQuery.GET_FB_LINKED_STATUS);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{OculusContent.FriendList.FB_LINKED_STATUS_COLUMN}, 1);
        if (fbLinkedStatusSync != null) {
            String str = fbLinkedStatusSync.viewer.fb_linked_status;
            matrixCursor.addRow(new Object[]{str});
            boolean equals = str.equals("LINKED");
            Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, ((FriendListContentProviderLogger) AnonymousClass0J2.A03(1, 360, this._UL_mInjectionContext))._UL_mInjectionContext)).A22(FriendListContentProviderLogger.EVENT_LINKED_ACCOUNTS_QUERY);
            A22.A15("requesting_package", getCallingPackage());
            A22.A16("result", equals);
            A22.A5L();
        }
        return matrixCursor;
    }

    public static int getMicrophoneMuteStateFromPartyStateJson(String str) {
        try {
            return MicrophoneMuteState.A00(new JSONObject(str).getString("microphone_muted"));
        } catch (JSONException e) {
            AnonymousClass0NO.A0B(TAG, "I don't like your json", e);
            return 0;
        }
    }

    private Cursor getPYMKs() {
        PYMKResponse.PeopleYouMayKnow peopleYouMayKnow;
        ArrayList<SocialUser> arrayList;
        PYMKResponse pYMKsSync = this.mSocialMethods.mMethods.getPYMKsSync(GraphQLSocialQuery.PYMK_QUERY);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.PRESENCE_COLUMN, OculusContent.FriendList.PRESENCE_STATUS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, OculusContent.FriendList.MUTUAL_CONTEXT_STRING_COLUMN, OculusContent.FriendList.CAN_VIEWER_MESSAGE_COLUMN, OculusContent.FriendList.FRIENDSHIP_STATUS_COLUMN}, 0);
        if (!(pYMKsSync == null || (peopleYouMayKnow = pYMKsSync.people_you_may_know) == null || (arrayList = peopleYouMayKnow.nodes) == null)) {
            Iterator<SocialUser> it = arrayList.iterator();
            while (it.hasNext()) {
                SocialUser next = it.next();
                matrixCursor.addRow(new Object[]{next.id, next.name, next.alias, next.presence, next.presence_status, getSocialUserPhotoUrl(next), next.mutual_context_string, Integer.valueOf(next.can_viewer_message ? 1 : 0), getSocialUserFriendshipStatus(next)});
            }
        }
        return matrixCursor;
    }

    @Nullable
    private Cursor getPartyInfoWithActivity(String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        GetPartyInfoWithActivityResponse.Application application;
        GetPartyInfoWithActivityResponse.Application application2;
        GetPartyInfoWithActivityResponse.ActivityImage activityImage;
        GetPartyInfoWithActivityResponse partyInfoWithActivitySync = this.mSocialMethods.mMethods.getPartyInfoWithActivitySync(GraphQLSocialQuery.GET_PARTY_INFO_WITH_ACTIVITY, new PartyIDParam(str));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_id", OculusContent.FriendList.PARTY_BLOCKED_INVITED_USERS_COLUMN, OculusContent.FriendList.PARTY_INVITED_USERS_COLUMN, OculusContent.FriendList.PARTY_BLOCKED_USERS_COLUMN, OculusContent.FriendList.PARTY_CURRENT_USERS_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_ID_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_TITLE_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_SUBTITLE_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_DEEPLINK_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_IMAGE_URI_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_PACKAGE_NAME_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_APP_ID_COLUMN}, 1);
        Object[] objArr = new Object[12];
        objArr[0] = partyInfoWithActivitySync.id;
        objArr[1] = Integer.valueOf(partyInfoWithActivitySync.party_blocked_invited_users.count);
        objArr[2] = Integer.valueOf(partyInfoWithActivitySync.party_invited_users.count);
        objArr[3] = Integer.valueOf(partyInfoWithActivitySync.party_blocked_users.count);
        objArr[4] = Integer.valueOf(partyInfoWithActivitySync.party_users.count);
        GetPartyInfoWithActivityResponse.PartyInviteActivity partyInviteActivity = partyInfoWithActivitySync.party_invite_activity;
        String str8 = null;
        if (partyInviteActivity != null) {
            str2 = partyInviteActivity.id;
        } else {
            str2 = null;
        }
        objArr[5] = str2;
        if (partyInviteActivity != null) {
            str3 = partyInviteActivity.title;
        } else {
            str3 = null;
        }
        objArr[6] = str3;
        if (partyInviteActivity != null) {
            str4 = partyInviteActivity.subtitle;
        } else {
            str4 = null;
        }
        objArr[7] = str4;
        if (partyInviteActivity != null) {
            str5 = partyInviteActivity.deeplink;
        } else {
            str5 = null;
        }
        objArr[8] = str5;
        if (partyInviteActivity == null || (activityImage = partyInviteActivity.image) == null) {
            str6 = null;
        } else {
            str6 = activityImage.uri;
        }
        objArr[9] = str6;
        if (partyInviteActivity == null || (application2 = partyInviteActivity.application) == null) {
            str7 = null;
        } else {
            str7 = application2.package_name;
        }
        objArr[10] = str7;
        if (!(partyInviteActivity == null || (application = partyInviteActivity.application) == null)) {
            str8 = application.id;
        }
        objArr[11] = str8;
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    @Nullable
    private Cursor getPartyInfoWithMembers(String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        GetPartyInfoWithMembersResponse.Application application;
        GetPartyInfoWithMembersResponse.Application application2;
        GetPartyInfoWithMembersResponse.ActivityImage activityImage;
        GetPartyInfoWithMembersResponse partyInfoWithMembersSync = this.mSocialMethods.mMethods.getPartyInfoWithMembersSync(GraphQLSocialQuery.GET_PARTY_INFO_WITH_MEMBERS, new PartyIDParam(str));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.PRESENCE_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, "current_party_id", OculusContent.FriendList.PARTY_MAX_SIZE, OculusContent.FriendList.PARTY_USERS_COLUMN, OculusContent.FriendList.PARTY_BLOCKED_USERS_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_ID_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_TITLE_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_SUBTITLE_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_DEEPLINK_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_IMAGE_URI_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_PACKAGE_NAME_COLUMN, OculusContent.FriendList.PARTY_ACTIVITY_APP_ID_COLUMN}, 0);
        if (partyInfoWithMembersSync != null) {
            Object[] objArr = new Object[17];
            SocialUser socialUser = partyInfoWithMembersSync.party_leader;
            objArr[0] = socialUser.id;
            objArr[1] = socialUser.name;
            objArr[2] = socialUser.alias;
            objArr[3] = socialUser.presence;
            objArr[4] = socialUser.presence_status;
            objArr[5] = getSocialUserPhotoUrl(socialUser);
            objArr[6] = partyInfoWithMembersSync.id;
            objArr[7] = Integer.valueOf(partyInfoWithMembersSync.max_size);
            objArr[8] = partyInfoWithMembersSync.party_users.count;
            objArr[9] = partyInfoWithMembersSync.party_blocked_users.count;
            GetPartyInfoWithMembersResponse.PartyInviteActivity partyInviteActivity = partyInfoWithMembersSync.party_invite_activity;
            if (partyInviteActivity != null) {
                str2 = partyInviteActivity.id;
            } else {
                str2 = null;
            }
            objArr[10] = str2;
            if (partyInviteActivity != null) {
                str3 = partyInviteActivity.title;
            } else {
                str3 = null;
            }
            objArr[11] = str3;
            if (partyInviteActivity != null) {
                str4 = partyInviteActivity.subtitle;
            } else {
                str4 = null;
            }
            objArr[12] = str4;
            if (partyInviteActivity != null) {
                str5 = partyInviteActivity.deeplink;
            } else {
                str5 = null;
            }
            objArr[13] = str5;
            if (partyInviteActivity == null || (activityImage = partyInviteActivity.image) == null) {
                str6 = null;
            } else {
                str6 = activityImage.uri;
            }
            objArr[14] = str6;
            if (partyInviteActivity == null || (application2 = partyInviteActivity.application) == null) {
                str7 = null;
            } else {
                str7 = application2.package_name;
            }
            objArr[15] = str7;
            if (partyInviteActivity == null || (application = partyInviteActivity.application) == null) {
                str8 = null;
            } else {
                str8 = application.id;
            }
            objArr[16] = str8;
            matrixCursor.addRow(objArr);
            matrixCursor.addRow(new Object[]{null, null, null, null, null, PartyListResponse.ListType.MEMBERS.toString(), null, null, null, null, null, null, null, null, null, null});
            Iterator<SocialUser> it = partyInfoWithMembersSync.party_users.nodes.iterator();
            while (it.hasNext()) {
                SocialUser next = it.next();
                matrixCursor.addRow(new Object[]{next.id, next.name, next.alias, next.presence, next.presence_status, getSocialUserPhotoUrl(next), null, null, null, null, null, null, null, null, null, null, null});
            }
        }
        return matrixCursor;
    }

    @Nullable
    private Cursor getPartyInviteInfo(String str) {
        return convertPartyInfoToCursor(this.mSocialMethods.mMethods.getPartyInviteInfoSync(GraphQLSocialQuery.GET_PARTY_INVITE_INFO, new PartyIDParam(str)));
    }

    @Nullable
    private Cursor getPartyInviteInfoWithNonce(String str, String str2) {
        return convertPartyInfoToCursor(this.mSocialMethods.mMethods.getPartyInviteInfoWithNonceSync(GraphQLSocialQuery.GET_PARTY_INVITE_INFO_WITH_NONCE, new DeeplinkNonceParam(str, str2)));
    }

    private Cursor getPartyInvites() {
        PartyInvitesResponse.InvitedParties invitedParties;
        PartyInvitesResponse partyInvitesSync = this.mSocialMethods.mMethods.getPartyInvitesSync(GraphQLSocialQuery.GET_PARTY_INVITES);
        int i = 11;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.FRIENDSHIP_STATUS_COLUMN, OculusContent.FriendList.PRESENCE_COLUMN, OculusContent.FriendList.PRESENCE_STATUS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, "current_party_id", OculusContent.FriendList.PARTY_JOIN_POLICY, OculusContent.FriendList.PARTY_BLOCKED_USERS_COUNT_COLUMN, OculusContent.FriendList.PARTY_MAX_SIZE}, 0);
        if (!(partyInvitesSync == null || (invitedParties = partyInvitesSync.invited_parties) == null)) {
            Iterator<PartyInvitesResponse.Party> it = invitedParties.nodes.iterator();
            while (it.hasNext()) {
                PartyInvitesResponse.Party next = it.next();
                SocialUser socialUser = next.invited_by;
                String socialUserPhotoUrl = getSocialUserPhotoUrl(socialUser);
                Object[] objArr = new Object[i];
                objArr[0] = socialUser.id;
                objArr[1] = socialUser.name;
                objArr[2] = socialUser.alias;
                objArr[3] = getSocialUserFriendshipStatus(socialUser);
                objArr[4] = socialUser.presence;
                objArr[5] = socialUser.presence_status;
                objArr[6] = socialUserPhotoUrl;
                objArr[7] = next.id;
                objArr[8] = next.join_policy;
                objArr[9] = next.party_blocked_users.count;
                objArr[10] = next.max_size;
                matrixCursor.addRow(objArr);
                Iterator<SocialUser> it2 = next.party_users.nodes.iterator();
                while (it2.hasNext()) {
                    SocialUser next2 = it2.next();
                    matrixCursor.addRow(new Object[]{next2.id, next2.name, next2.alias, getSocialUserFriendshipStatus(next2), next2.presence, next2.presence_status, getSocialUserPhotoUrl(next2), null, null, null, null});
                }
                i = 11;
            }
        }
        return matrixCursor;
    }

    private Map<String, Integer> getPartyMemberAppEntitlementCounts(List<GroupLaunchSupportedApplicationsResponse.PartyUser> list) {
        List<GroupLaunchSupportedApplicationsResponse.AppEntitlement> list2;
        HashMap hashMap = new HashMap();
        for (GroupLaunchSupportedApplicationsResponse.PartyUser partyUser : list) {
            GroupLaunchSupportedApplicationsResponse.AppEntitlements appEntitlements = partyUser.app_entitlements;
            if (!(appEntitlements == null || (list2 = appEntitlements.nodes) == null)) {
                for (GroupLaunchSupportedApplicationsResponse.AppEntitlement appEntitlement : list2) {
                    GroupLaunchSupportedApplicationsResponse.Application application = appEntitlement.item;
                    String str = application.id;
                    if (str != null) {
                        hashMap.put(application.id, Integer.valueOf(((Number) hashMap.getOrDefault(str, 0)).intValue() + 1));
                    }
                }
            }
        }
        return hashMap;
    }

    private Cursor getPartyPrivacyInfo(String str) {
        PartyPrivacyInfoResponse.Party party;
        PartyPrivacyInfoResponse partyPrivacyInfoSync = this.mSocialMethods.mMethods.getPartyPrivacyInfoSync(GraphQLSocialQuery.GET_PARTY_PRIVACY_INFO, new PartyIDParam(str));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_id", "party_type", OculusContent.FriendList.PARTY_HAS_LINK_INVITE, OculusContent.FriendList.PARTY_URL}, 1);
        if (!(partyPrivacyInfoSync == null || (party = partyPrivacyInfoSync.node) == null)) {
            matrixCursor.addRow(new Object[]{party.id, party.party_type, Integer.valueOf(party.has_active_link_sharing ? 1 : 0), party.url_for_deeplink_target_web_page});
        }
        return matrixCursor;
    }

    private Cursor getPeopleNearby(@Nullable Integer num) {
        SocialUser socialUser;
        ArrayList<SocialUser> arrayList;
        PeopleNearbyResponse peopleNearbySync = this.mSocialMethods.mMethods.getPeopleNearbySync(GraphQLSocialQuery.PEOPLE_NEARBY_QUERY, new PeopleNearbyParams(num));
        int i = 10;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.PRESENCE_COLUMN, OculusContent.FriendList.PRESENCE_STATUS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, OculusContent.FriendList.CAN_VIEWER_MESSAGE_COLUMN, OculusContent.FriendList.MUTUAL_CONTEXT_STRING_COLUMN, OculusContent.FriendList.FRIENDSHIP_STATUS_COLUMN, OculusContent.FriendList.IS_IN_ROOM_COLUMN}, 0);
        if (!(peopleNearbySync == null || (socialUser = peopleNearbySync.user) == null || socialUser.id == null || (arrayList = socialUser.people_nearby) == null)) {
            arrayList.add(0, socialUser);
            Iterator<SocialUser> it = arrayList.iterator();
            while (it.hasNext()) {
                SocialUser next = it.next();
                String socialUserPhotoUrl = getSocialUserPhotoUrl(next);
                Object[] objArr = new Object[i];
                objArr[0] = next.id;
                objArr[1] = next.name;
                objArr[2] = next.alias;
                objArr[3] = next.presence;
                objArr[4] = next.presence_status;
                objArr[5] = socialUserPhotoUrl;
                objArr[6] = Integer.valueOf(next.can_viewer_message ? 1 : 0);
                objArr[7] = next.mutual_context_string;
                objArr[8] = getSocialUserFriendshipStatus(next);
                objArr[9] = Integer.valueOf(peopleNearbySync.user.is_in_room ? 1 : 0);
                matrixCursor.addRow(objArr);
                i = 10;
            }
        }
        return matrixCursor;
    }

    private Cursor getSharePartyInfo() {
        SharePartyInfoResponse.CurrentParty currentParty;
        SharePartyInfoResponse sharePartyInfo = this.mSocialMethods.mMethods.getSharePartyInfo(GraphQLSocialQuery.GET_SHARE_PARTY_INFO);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{OculusContent.FriendList.PARTY_FBID_COLUMN}, 0);
        if (!(sharePartyInfo == null || (currentParty = sharePartyInfo.current_party) == null)) {
            matrixCursor.addRow(new String[]{currentParty.fb_shareable_ent_id});
        }
        return matrixCursor;
    }

    private Cursor getSocialFriends(String[] strArr, @Nullable Integer num, @Nullable boolean z) {
        String str;
        SocialFriendsResponse.Viewer viewer;
        SocialFriendsResponse.User user;
        SocialFriendsResponse.Friends friends;
        ArrayList<SocialUser> arrayList;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        SocialUser.PartyUsers partyUsers;
        SocialMethods socialMethods = this.mSocialMethods;
        if (z) {
            str = GraphQLSocialQuery.GET_FRIENDS_NO_RICH_PRESENCE;
        } else {
            str = GraphQLSocialQuery.GET_FRIENDS;
        }
        SocialFriendsResponse socialFriendsSync = socialMethods.mMethods.getSocialFriendsSync(str, new FriendListParams(strArr, num));
        int i = 13;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.PRESENCE_COLUMN, OculusContent.FriendList.PRESENCE_STATUS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, OculusContent.FriendList.LAST_ACTIVE_COLUMN, "current_party_id", OculusContent.FriendList.PARTY_JOIN_POLICY, OculusContent.FriendList.PARTY_MAX_SIZE, OculusContent.FriendList.PARTY_USERS_COUNT_COLUMN, OculusContent.FriendList.CAN_VIEWER_MESSAGE_COLUMN, OculusContent.FriendList.FRIENDSHIP_STATUS_COLUMN}, 0);
        if (!(socialFriendsSync == null || (viewer = socialFriendsSync.viewer) == null || (user = viewer.user) == null || (friends = user.friends) == null || (arrayList = friends.nodes) == null)) {
            Iterator<SocialUser> it = arrayList.iterator();
            while (it.hasNext()) {
                SocialUser next = it.next();
                Object socialUserPhotoUrl = getSocialUserPhotoUrl(next);
                Object[] objArr = new Object[i];
                objArr[0] = next.id;
                objArr[1] = next.name;
                objArr[2] = next.alias;
                objArr[3] = next.presence;
                objArr[4] = next.presence_status;
                objArr[5] = socialUserPhotoUrl;
                SocialUser.Presence presence = next.most_recent_presence;
                Object obj5 = null;
                if (presence == null) {
                    obj = null;
                } else {
                    obj = presence.last_active_description;
                }
                objArr[6] = obj;
                SocialUser.CurrentParty currentParty = next.current_party;
                if (currentParty == null) {
                    obj2 = null;
                } else {
                    obj2 = currentParty.id;
                }
                objArr[7] = obj2;
                if (currentParty == null) {
                    obj3 = null;
                } else {
                    obj3 = currentParty.join_policy;
                }
                objArr[8] = obj3;
                if (currentParty == null) {
                    obj4 = null;
                } else {
                    obj4 = currentParty.max_size;
                }
                objArr[9] = obj4;
                if (!(currentParty == null || (partyUsers = currentParty.party_users) == null)) {
                    obj5 = partyUsers.count;
                }
                objArr[10] = obj5;
                objArr[11] = Integer.valueOf(next.can_viewer_message ? 1 : 0);
                objArr[12] = getSocialUserFriendshipStatus(next);
                matrixCursor.addRow(objArr);
                i = 13;
            }
        }
        return matrixCursor;
    }

    public static String getSocialUserFriendshipStatus(SocialUser socialUser) {
        if (socialUser.is_blocked_by_viewer) {
            return "BLOCKING";
        }
        return socialUser.friend_status;
    }

    public static String getSocialUserPhotoUrl(SocialUser socialUser) {
        String str;
        ProfilePhoto profilePhoto = socialUser.profile_photo;
        if (profilePhoto == null || (str = profilePhoto.uri) == null) {
            return "";
        }
        return str;
    }

    private Cursor getSocialViewerInfo() {
        SocialViewerInfoResponse.User user;
        String str;
        SocialViewerInfoResponse socialViewerInfoSync = this.mSocialMethods.mMethods.getSocialViewerInfoSync(GraphQLSocialQuery.GET_SOCIAL_VIEWER);
        int i = 0;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{OculusContent.FriendList.ALIAS_COLUMN, OculusContent.FriendList.PROFILE_IMAGE_URL, OculusContent.FriendList.AVATAR_IMAGE_URL, OculusContent.FriendList.HAS_SEEN_VR_INVITE_PROFILE_NUX_COLUMN}, 0);
        if (!(socialViewerInfoSync == null || (user = socialViewerInfoSync.user) == null)) {
            Object[] objArr = new Object[4];
            objArr[0] = user.alias;
            SocialViewerInfoResponse.ProfilePhoto profilePhoto = user.profile_photo;
            String str2 = "";
            if (profilePhoto == null) {
                str = str2;
            } else {
                str = profilePhoto.uri;
            }
            objArr[1] = str;
            SocialViewerInfoResponse.AvatarImage avatarImage = user.avatar_image;
            if (avatarImage != null) {
                str2 = avatarImage.uri;
            }
            objArr[2] = str2;
            SocialViewerInfoResponse.UserNuxFlags userNuxFlags = user.user_nux_flags;
            if (userNuxFlags != null && userNuxFlags.has_seen_vr_invite_profile_nux) {
                i = 1;
            }
            objArr[3] = Integer.valueOf(i);
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }

    public static String getUserDisplayName(User user) {
        String str = user.name;
        if (str == null || str.isEmpty()) {
            return user.alias;
        }
        return str;
    }

    @SuppressLint({"CatchGeneralException", "ThrowException"})
    private AnonymousClass0DC<UserSearchResponse> getUserSearchAsync(String str, String str2) {
        final AnonymousClass0DD r5 = new AnonymousClass0DD();
        try {
            SocialMethods socialMethods = this.mSocialMethods;
            UserSearchParams.SearchType valueOf = UserSearchParams.SearchType.valueOf(str2);
            socialMethods.mMethods.userSearch(GraphQLSocialQuery.USER_SEARCH, new UserSearchParams(str, valueOf), "", new ApiCallback<UserSearchResponse>() {
                /* class com.oculus.friendlistcontentprovider.FriendListContentProvider.AnonymousClass1 */

                @Override // com.oculus.http.core.base.ApiCallback
                public final void onError(ApiError apiError) {
                    r5.A01(new Exception(apiError.A00("")));
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                @Override // com.oculus.http.core.base.ApiCallback
                public final void onResponse(UserSearchResponse userSearchResponse) {
                    try {
                        r5.A02(userSearchResponse);
                    } catch (Exception e) {
                        AnonymousClass0NO.A0B(FriendListContentProvider.TAG, "There was an exception while user search: ", e);
                        r5.A01(new Exception(e.getMessage()));
                    }
                }
            });
        } catch (Exception e) {
            AnonymousClass0NO.A0B(TAG, "User search failed with an exception: ", e);
        }
        return r5.A00;
    }

    public static String getUserSearchAvatarUrl(SearchResultUser searchResultUser) {
        String str;
        ProfilePhoto profilePhoto = searchResultUser.avatar;
        if (profilePhoto == null || (str = profilePhoto.uri) == null) {
            return "";
        }
        return str;
    }

    public static String getUserSearchName(SearchResultUser searchResultUser) {
        String str = searchResultUser.name;
        if (str == null) {
            return "";
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ba, code lost:
        if (r5.id.equals(r2) == false) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00bd, code lost:
        if (r2 != null) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00bf, code lost:
        r0 = r1.invited_parties;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c1, code lost:
        if (r0 == null) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c3, code lost:
        r0 = r0.nodes;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c5, code lost:
        if (r0 == null) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c7, code lost:
        r1 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00cf, code lost:
        if (r1.hasNext() == false) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00dd, code lost:
        if (r2.equals(r1.next().id) == false) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00df, code lost:
        r9 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e0, code lost:
        r0 = r7.current_party;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e2, code lost:
        if (r0 == null) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e4, code lost:
        r1 = r0.join_policy;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e6, code lost:
        if (r1 == null) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ee, code lost:
        if ("DIRECT_JOIN".equals(r1) == false) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f0, code lost:
        r9 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f1, code lost:
        r2 = new java.lang.Object[16];
        r2[0] = r7.alias;
        r0 = r7.biography;
        r8 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00fb, code lost:
        if (r0 != null) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00fd, code lost:
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00fe, code lost:
        r2[1] = r0;
        r2[2] = r7.friend_status;
        r2[3] = java.lang.Integer.valueOf(r7.is_blocked_by_viewer.booleanValue() ? 1 : 0);
        r0 = r7.profile_photo;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0112, code lost:
        if (r0 != null) goto L_0x0188;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0114, code lost:
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0115, code lost:
        r2[4] = r0;
        r0 = r7.avatar_image;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0119, code lost:
        if (r0 != null) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011b, code lost:
        r1 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011c, code lost:
        r2[5] = r1;
        r1 = r7.presence;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0121, code lost:
        if (r1 != null) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0123, code lost:
        r1 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0124, code lost:
        r2[6] = r1;
        r2[7] = r7.presence_status;
        r6 = r7.most_recent_presence;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x012e, code lost:
        if (r6 == null) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0130, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0133, code lost:
        if (r6.is_current != false) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0135, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0136, code lost:
        r2[8] = java.lang.Integer.valueOf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x013e, code lost:
        if (r6 != null) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0140, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0142, code lost:
        r2[9] = java.lang.Long.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x014a, code lost:
        if (r6 == null) goto L_0x0150;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x014c, code lost:
        r0 = r6.presence;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x014e, code lost:
        if (r0 != null) goto L_0x0151;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0150, code lost:
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0151, code lost:
        r2[10] = r0;
        r0 = r7.name;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0155, code lost:
        if (r0 == null) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0157, code lost:
        r8 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0158, code lost:
        r2[11] = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x015c, code lost:
        if (r5 != null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x015e, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x015f, code lost:
        r2[12] = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0163, code lost:
        if (r5 == null) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0165, code lost:
        if (r10 != false) goto L_0x0169;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0167, code lost:
        if (r11 == false) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0169, code lost:
        r18 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x016b, code lost:
        r2[13] = java.lang.Integer.valueOf(r18);
        r2[14] = r4.viewer.fb_linked_status;
        r2[15] = r9;
        r3.addRow(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x017f, code lost:
        r0 = r5.id;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0182, code lost:
        r0 = r6.vr_last_active_time;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0185, code lost:
        r1 = r0.uri;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0188, code lost:
        r0 = r0.uri;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x018b, code lost:
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x018c, code lost:
        if (r2 == null) goto L_0x00f1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.Cursor getVRProfileContent(java.lang.String r20) {
        /*
        // Method dump skipped, instructions count: 407
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.friendlistcontentprovider.FriendListContentProvider.getVRProfileContent(java.lang.String):android.database.Cursor");
    }

    @SuppressLint({"CatchGeneralException", "ThrowException"})
    private AnonymousClass0DC<String> joinPartyAsync(final String str) {
        final AnonymousClass0DD r5 = new AnonymousClass0DD();
        try {
            SocialMethods socialMethods = this.mSocialMethods;
            socialMethods.mMethods.partyJoinWithCallback(GraphQLSocialQuery.PARTY_JOIN, new PartyJoinParams(str, null, null), "", new ApiCallback<Void>() {
                /* class com.oculus.friendlistcontentprovider.FriendListContentProvider.AnonymousClass2 */

                @Override // com.oculus.http.core.base.ApiCallback
                public final void onError(ApiError apiError) {
                    r5.A01(new Exception(apiError.A00("")));
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                @Override // com.oculus.http.core.base.ApiCallback
                public final /* bridge */ /* synthetic */ void onResponse(Void r5) {
                    try {
                        r5.A02(str);
                    } catch (Exception e) {
                        AnonymousClass0NO.A0B(FriendListContentProvider.TAG, "There was an exception while starting party chat: ", e);
                        r5.A01(new Exception(e.getMessage()));
                    }
                    FriendListContentProvider.this.startPartyChat(str);
                }
            });
        } catch (Exception e) {
            AnonymousClass0NO.A0B(TAG, "Party join failed with an exception: ", e);
        }
        return r5.A00;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startPartyChat(String str) {
        Long valueOf;
        if (str != null && (valueOf = Long.valueOf(Long.parseLong(str))) != null) {
            getPartyInterface().A9J(valueOf.longValue());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d6, code lost:
        if (r1 == null) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00de, code lost:
        if (r1.equals(r3.id) == false) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e0, code lost:
        r19 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0104, code lost:
        if (r13 == null) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x010c, code lost:
        if ("DIRECT_JOIN".equals(r13) == false) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x010e, code lost:
        r18 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0110, code lost:
        if (r16 == false) goto L_0x017a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0112, code lost:
        r0 = r8.fb_friend_primary_profile;
        r15 = r0.is_currently_active;
        r0 = r0.last_active_time;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0118, code lost:
        r2 = new java.lang.Object[15];
        r14 = r8.fb_friend_primary_profile;
        r17 = 0;
        r2[0] = r14.name;
        r2[1] = r14.profile_picture_uri;
        r2[2] = r14.bio;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x012d, code lost:
        if (r15 == false) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x012f, code lost:
        r17 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0131, code lost:
        r2[3] = java.lang.Integer.valueOf(r17);
        r2[4] = java.lang.Long.valueOf(r0);
        r2[5] = r12;
        r2[6] = r11;
        r2[7] = r10;
        r9 = r9 ? 1 : 0;
        r9 = r9 ? 1 : 0;
        r9 = r9 ? 1 : 0;
        r2[8] = java.lang.Integer.valueOf(r9);
        r2[9] = r20;
        r2[10] = java.lang.Long.valueOf(r4);
        r2[11] = java.lang.Integer.valueOf(r19);
        r2[12] = r18;
        r2[13] = r8.viewer.viewer_fb_id;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0170, code lost:
        if (r3 == null) goto L_0x0174;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0172, code lost:
        r6 = r3.id;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0174, code lost:
        r2[14] = r6;
        r7.addRow(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x017a, code lost:
        r0 = 0;
        r15 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0180, code lost:
        if (r1 != null) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0183, code lost:
        if (r15 == false) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0188, code lost:
        if (r3 != null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0084, code lost:
        if (r14.fb_presence_sharing == false) goto L_0x0086;
     */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor getFbFriendPrimaryProfile(java.lang.String r23) {
        /*
        // Method dump skipped, instructions count: 423
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.friendlistcontentprovider.FriendListContentProvider.getFbFriendPrimaryProfile(java.lang.String):android.database.Cursor");
    }

    @VisibleForTesting
    public PartyController getPartyInterface() {
        return (PartyController) AnonymousClass0J2.A03(0, 15, this._UL_mInjectionContext);
    }

    public static final void _UL_injectMe(Context context, FriendListContentProvider friendListContentProvider) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), friendListContentProvider);
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    @Nullable
    private Cursor getHasParticipantRecentlySpoken(String str) {
        boolean A3U = getPartyInterface().A3U(Long.parseLong(str));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{OculusContent.FriendList.PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN}, 1);
        matrixCursor.addRow(new Object[]{Integer.valueOf(A3U ? 1 : 0)});
        return matrixCursor;
    }

    @Nullable
    private Cursor getPartyChatVolume() {
        try {
            float A46 = getPartyInterface().A46();
            MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_volume"}, 1);
            matrixCursor.addRow(new Object[]{Float.valueOf(A46)});
            return matrixCursor;
        } catch (PlatformInitException e) {
            AnonymousClass0NO.A0C(TAG, "Failed to get party volume", e);
            return null;
        }
    }

    @Nullable
    private Cursor getSocialActivity(String str, String str2) {
        SocialActivityResponse socialActivity = this.mSocialMethods.mMethods.getSocialActivity(GraphQLSocialQuery.GET_SOCIAL_ACTIVITY, new SocialActivityParams(str, SocialActivityParams.Role.fromString(str2)));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"title", OculusContent.FriendList.ACTIVITY_SUBTITLE_COLUMN, OculusContent.FriendList.ACTIVITY_IMAGE_URI_COLUMN, "package_name", "deeplink"}, 5);
        SocialActivityResponse.SocialActivity socialActivity2 = socialActivity.social_activity;
        matrixCursor.addRow(new Object[]{socialActivity2.title, socialActivity2.subtitle, socialActivity2.image.uri, socialActivity2.application.package_name, socialActivity2.deeplink});
        return matrixCursor;
    }

    @Override // com.oculus.content.OculusFbPermissionsContentProvider, com.oculus.friendlistcontentprovider.OculusLoggedInUserFbPermissionsContentProvider
    public void doInitialization() {
        Context context = getContext();
        if (context != null) {
            this.mFbPermissionName = AnonymousClass006.A05(context.getPackageName(), ".fbpermission.FRIEND_LIST_CONTENT_PROVIDER");
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                this.mIsMicSwitchingEnabled = packageManager.hasSystemFeature("oculus.software.mic_sharing");
            }
        }
        super.doInitialization();
        _UL_injectMe(getContext(), this);
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getFbPermission() {
        return this.mFbPermissionName;
    }

    @VisibleForTesting
    public SocialMethods getSocialMethods() {
        return this.mSocialMethods;
    }

    @Override // com.oculus.friendlistcontentprovider.OculusLoggedInUserFbPermissionsContentProvider
    public ImmutableSet<String> getWhitelistedPackages() {
        return WHITELISTED_PACKAGES;
    }

    @VisibleForTesting
    public boolean isMicSwitchingEnabled() {
        return this.mIsMicSwitchingEnabled;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x011f, code lost:
        if (r10.does_activity_support_group_launch == false) goto L_0x0121;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x043b  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x043e  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0441  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0444  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0447  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x044a  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x044d  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0450  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0453  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0456  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0515 A[LOOP:1: B:114:0x050f->B:116:0x0515, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x01c4  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01e8  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0210  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x022e  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0238  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0240  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0248  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x026c  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0343 A[LOOP:0: B:85:0x033d->B:87:0x0343, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0416  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x041a  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x041d  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0420  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0423  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0426  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0429  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x042c  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x042f  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0432  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0438  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.Cursor getCurrentParty() {
        /*
        // Method dump skipped, instructions count: 1489
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.friendlistcontentprovider.FriendListContentProvider.getCurrentParty():android.database.Cursor");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0122, code lost:
        if (r11.does_activity_support_group_launch == false) goto L_0x0124;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x03b4  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x03b8  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x03bc  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x03c0  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x03c4  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x03c8  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x03cc  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x03d0  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x03d4  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x03d8  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x03fe A[LOOP:1: B:114:0x03f8->B:116:0x03fe, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x04d4 A[LOOP:2: B:118:0x04ce->B:120:0x04d4, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x04f3 A[LOOP:3: B:122:0x04ed->B:124:0x04f3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x01d9  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x01f1  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x021b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0225  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x022f  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0251  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0259  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x026d  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0293  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02ad A[LOOP:0: B:85:0x02a7->B:87:0x02ad, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0384  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x038c  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0390  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0394  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0398  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x039c  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x03a0  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x03a8  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x03ac  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x03b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.Cursor getCurrentPartyV2() {
        /*
        // Method dump skipped, instructions count: 1278
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.friendlistcontentprovider.FriendListContentProvider.getCurrentPartyV2():android.database.Cursor");
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Cursor doQuery(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        String str4;
        String[] split;
        boolean parseBoolean;
        Integer num = null;
        if (uri == null) {
            str3 = TAG;
            str4 = "Cannot resolve query with null URI";
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_LIST_CONTENT_URI) == 0) {
            return getFriendList();
        } else {
            if (uri.toString().contains(OculusContent.FriendList.PARTY_CREATE.toString())) {
                return createParty(uri.getQueryParameter(OculusContent.FriendList.INVITE_ACTIVITY_ID_VALUE), uri.getQueryParameter("party_type"), uri.getQueryParameter("destination_id"), uri.getQueryParameter(OculusContent.FriendList.MESSAGE_THREAD_KEY_PARAM));
            }
            if (uri.compareTo(OculusContent.FriendList.PARTY_GET_VOLUME) == 0) {
                return getPartyChatVolume();
            }
            if (uri.toString().contains(OculusContent.FriendList.PARTY_GET_INFO_WITH_ACTIVITY.toString())) {
                return getPartyInfoWithActivity(uri.getQueryParameter("party_id"));
            }
            if (uri.toString().contains(OculusContent.FriendList.PARTY_GET_INFO_WITH_MEMBERS.toString())) {
                return getPartyInfoWithMembers(uri.getQueryParameter("party_id"));
            }
            if (uri.toString().contains(OculusContent.FriendList.GET_SOCIAL_ACTIVITY.toString())) {
                String queryParameter = uri.getQueryParameter(OculusContent.FriendList.ACTIVITY_ID_PARAM);
                String queryParameter2 = uri.getQueryParameter(OculusContent.FriendList.ROLE_PARAM);
                if (queryParameter2 == null) {
                    queryParameter2 = "SENDER";
                }
                return getSocialActivity(queryParameter, queryParameter2);
            } else if (uri.compareTo(OculusContent.FriendList.GET_LINKED_ACCOUNTS_INFO) == 0) {
                return getLinkedAccountsInfo();
            } else {
                if (uri.toString().contains(OculusContent.FriendList.PARTY_JOIN_WITH_ERROR_CALLBACK.toString())) {
                    return joinPartyQuery(uri.getQueryParameter("party_id"));
                }
                if (uri.compareTo(OculusContent.FriendList.FRIEND_REQUESTS_CONTENT_URI) == 0) {
                    return getFriendRequests();
                }
                if (uri.compareTo(OculusContent.FriendList.PYMK_CONTENT_URI) == 0) {
                    return getPYMKs();
                }
                if (uri.compareTo(OculusContent.FriendList.PARTY_GET_CURRENT_URI) == 0) {
                    return getCurrentParty();
                }
                if (uri.compareTo(OculusContent.FriendList.PARTY_GET_CURRENT_URI_V2) == 0) {
                    return getCurrentPartyV2();
                }
                if (uri.compareTo(OculusContent.FriendList.GET_PARTY_FBID) == 0) {
                    return getSharePartyInfo();
                }
                if (uri.toString().contains(OculusContent.FriendList.GET_PARTY_INVITE_INFO_WITH_NONCE.toString())) {
                    return getPartyInviteInfoWithNonce(uri.getQueryParameter(OculusContent.FriendList.DEEP_LINK_TARGET_ID_VALUE), uri.getQueryParameter(OculusContent.FriendList.LINK_NONCE_VALUE));
                }
                if (uri.toString().contains(OculusContent.FriendList.GET_PARTY_INVITE_INFO.toString())) {
                    return getPartyInviteInfo(uri.getQueryParameter("party_id"));
                }
                if (uri.toString().contains(OculusContent.FriendList.GET_PARTY_PRIVACY_INFO.toString())) {
                    return getPartyPrivacyInfo(uri.getQueryParameter("party_id"));
                }
                if (uri.compareTo(OculusContent.FriendList.GET_PARTY_INVITES) == 0) {
                    return getPartyInvites();
                }
                if (uri.compareTo(OculusContent.FriendList.PARTY_GET_JOINABLE_PARTIES_URI) == 0) {
                    return getJoinableParties();
                }
                if (uri.toString().contains(OculusContent.FriendList.GET_FRIENDS_URI.toString())) {
                    String queryParameter3 = uri.getQueryParameter("orderby");
                    if (queryParameter3 == null) {
                        split = DEFAULT_FRIENDS_ORDERBY;
                    } else {
                        split = queryParameter3.split(",");
                    }
                    String queryParameter4 = uri.getQueryParameter("first");
                    if (queryParameter4 != null) {
                        num = Integer.valueOf(Integer.parseInt(queryParameter4));
                    }
                    String queryParameter5 = uri.getQueryParameter(OculusContent.FriendList.IGNORE_RICH_PRESENCE_PARAM);
                    if (queryParameter5 == null) {
                        parseBoolean = false;
                    } else {
                        parseBoolean = Boolean.parseBoolean(queryParameter5);
                    }
                    return getSocialFriends(split, num, parseBoolean);
                } else if (uri.compareTo(OculusContent.FriendList.GET_SOCIAL_VIEWER) == 0) {
                    return getSocialViewerInfo();
                } else {
                    if (uri.compareTo(OculusContent.FriendList.GET_GROUP_LAUNCH_SUPPORTED_APPLICATIONS_URI) == 0) {
                        return getGroupLaunchSupportedApplications();
                    }
                    if (uri.toString().contains(OculusContent.FriendList.GET_PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN.toString())) {
                        return getHasParticipantRecentlySpoken(uri.getQueryParameter("user_id"));
                    }
                    if (uri.toString().contains(OculusContent.FriendList.GET_GROUP_LAUNCH_APPLICATION_DESTINATIONS_URI.toString())) {
                        return getGroupLaunchAppDestinations(uri.getQueryParameter("application_id"), uri.getQueryParameter("after"), uri.getQueryParameter("first"));
                    }
                    if (uri.compareTo(OculusContent.FriendList.GET_PROFILE_CONTENT) == 0) {
                        return getAuiProfileContent();
                    }
                    if (uri.toString().contains(OculusContent.FriendList.GET_APP_SCOREBOARDS_INFO_URI.toString())) {
                        return getAppScoreboardsInfo(uri.getQueryParameter("application_id"));
                    }
                    if (uri.toString().contains(OculusContent.FriendList.GET_APPS_SCOREBOARDS_INFO_URI.toString())) {
                        String queryParameter6 = uri.getQueryParameter("application_ids");
                        if (queryParameter6 != null) {
                            return getAppsScoreboardsInfo(queryParameter6.split(":"));
                        }
                        str3 = TAG;
                        str4 = "Must specify APP_IDS_PARAM";
                    } else if (uri.toString().contains(OculusContent.FriendList.GET_FB_FRIEND_PRIMARY_PROFILE.toString())) {
                        return getFbFriendPrimaryProfile(uri.getQueryParameter(OculusContent.FriendList.FB_FRIEND_ID_PARAM));
                    } else {
                        if (uri.toString().contains(OculusContent.FriendList.GET_VR_PROFILE_CONTENT.toString())) {
                            return getVRProfileContent(uri.getQueryParameter("id"));
                        }
                        if (uri.toString().contains(OculusContent.FriendList.USER_SEARCH.toString())) {
                            return getUserSearch(uri.getQueryParameter(OculusContent.FriendList.USER_SEARCH_STRING_PARAM), uri.getQueryParameter(OculusContent.FriendList.USER_SEARCH_TYPE_PARAM));
                        }
                        if (uri.toString().contains(OculusContent.FriendList.GET_BLOCKED_USER_ID.toString())) {
                            return getBlockedUserId(uri.getQueryParameter("user_id"));
                        }
                        if (uri.toString().contains(OculusContent.FriendList.PEOPLE_NEARBY_CONTENT_URI.toString())) {
                            String queryParameter7 = uri.getQueryParameter("first");
                            if (queryParameter7 != null) {
                                num = Integer.valueOf(Integer.parseInt(queryParameter7));
                            }
                            return getPeopleNearby(num);
                        }
                        if (uri.compareTo(OculusContent.FriendList.PARTY_GET_MICROPHONE_INPUT_LOCATION) == 0) {
                            return getPartyMicrophoneInputLocation();
                        }
                        return null;
                    }
                }
            }
        }
        AnonymousClass0NO.A08(str3, str4);
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v6, resolved type: java.lang.String[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Throwable e;
        String str2;
        String str3;
        SocialMethods.Methods methods;
        GroupLaunchLaunchHandleParams groupLaunchLaunchHandleParams;
        String str4;
        GenericErrorResponse.Error error;
        if (uri == null) {
            AnonymousClass0NO.A08(TAG, "Cannot resolve query with null URI");
            return 0;
        }
        if (uri.compareTo(OculusContent.FriendList.PARTY_INVITE_USERS_CONTENT_URI) == 0) {
            this.mSocialMethods.mMethods.partyInviteUsers(GraphQLSocialQuery.PARTY_INVITE_USERS, new PartyInviteUsersParams(contentValues.getAsString("party_id"), contentValues.getAsString(OculusContent.FriendList.USER_IDS_VALUE).split(":")), "");
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_LEAVE) == 0) {
            this.mSocialMethods.mMethods.partyLeave(GraphQLSocialQuery.PARTY_LEAVE, new PartyLeaveParams((String) contentValues.get("party_id")), "");
            getPartyInterface().A9S();
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_JOIN) == 0) {
            String str5 = (String) contentValues.get("party_id");
            try {
                this.mSocialMethods.mMethods.partyJoin(GraphQLSocialQuery.PARTY_JOIN, new PartyJoinParams(str5, (String) contentValues.get(OculusContent.FriendList.LINK_NONCE_VALUE), (String) contentValues.get("application_id")), "");
                startPartyChat(str5);
                return 1;
            } catch (RetrofitError e2) {
                GenericErrorResponse genericErrorResponse = (GenericErrorResponse) e2.getBodyAs(GenericErrorResponse.class);
                if (genericErrorResponse != null && (error = genericErrorResponse.error) != null) {
                    return error.error_subcode;
                }
                throw e2;
            }
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_CANCEL_INVITE) == 0) {
            this.mSocialMethods.mMethods.partyCancelInvite(GraphQLSocialQuery.CANCEL_PARTY_INVITE, new PartyCancelInviteParams((String) contentValues.get(OculusContent.FriendList.USER_TO_UNINVITE_VALUE), (String) contentValues.get("party_id")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.USER_BLOCK) == 0) {
            this.mSocialMethods.mMethods.blockSync(GraphQLSocialQuery.BLOCK, new UserMutationParams((String) contentValues.get("user_id"), "user_id"), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.USER_UNBLOCK) == 0) {
            this.mSocialMethods.mMethods.unblockSync(GraphQLSocialQuery.UNBLOCK, new UserMutationParams((String) contentValues.get("blocked_user_id"), "blocked_user_id"), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_ACCEPT_REQUEST) == 0) {
            this.mSocialMethods.mMethods.acceptFriendRequestSync(GraphQLSocialQuery.ACCEPT_FRIEND_REQUEST, new UserMutationParams((String) contentValues.get("user_id"), UserMutationParams.PARAM_FRIEND_REQUESTER, FriendRequestSource.Origin.valueOf((String) contentValues.get("source"))), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_SEND_REQUEST) == 0) {
            this.mSocialMethods.mMethods.sendFriendRequestSync(GraphQLSocialQuery.SEND_FRIEND_REQUEST, new UserMutationParams((String) contentValues.get("user_id"), UserMutationParams.PARAM_FRIEND_REQUESTEE, FriendRequestSource.Origin.valueOf((String) contentValues.get("source"))), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_REJECT_REQUEST) == 0) {
            this.mSocialMethods.mMethods.rejectFriendRequestSync(GraphQLSocialQuery.REJECT_FRIEND_REQUEST, new UserMutationParams((String) contentValues.get("user_id"), UserMutationParams.PARAM_FRIEND_REQUESTER), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_CANCEL_REQUEST) == 0) {
            this.mSocialMethods.mMethods.cancelFriendRequestSync(GraphQLSocialQuery.CANCEL_FRIEND_REQUEST, new UserMutationParams((String) contentValues.get("user_id"), UserMutationParams.PARAM_FRIEND_REQUESTEE, FriendRequestSource.Origin.valueOf((String) contentValues.get("source"))), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_REMOVE) == 0) {
            this.mSocialMethods.mMethods.unfriendSync(GraphQLSocialQuery.UNFRIEND, new UserMutationParams((String) contentValues.get("friend"), "friend"), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PYMK_HIDE_USER) == 0) {
            this.mSocialMethods.mMethods.pymkHideUserSync(GraphQLSocialQuery.PYMK_HIDE_USER, new UserMutationParams((String) contentValues.get("user"), "user"), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.MESSAGE_SEND_TO_THREAD) == 0) {
            this.mSocialMethods.mMethods.messageSendToThreadSync(GraphQLSocialQuery.MESSAGE_SEND_TO_THREAD, new ChatMutationParams((String) contentValues.get("thread_id"), (String) contentValues.get("body")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.SET_NUX_FLAG_FOR_USER) == 0) {
            this.mSocialMethods.mMethods.setNuxFlagForUserSync(GraphQLSocialQuery.SET_NUX_FLAG_FOR_USER, new NuxFlagForUserMutationParams((String) contentValues.get("nux_flag"), (String) contentValues.get("nux_value")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PROFILE_SET_BIO) == 0) {
            this.mSocialMethods.mMethods.profileSetBioSync(GraphQLSocialQuery.PROFILE_SET_BIO, new BiographySetParams((String) contentValues.get("user_id"), (String) contentValues.get(OculusContent.FriendList.BIOGRAPHY_STRING)), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_SET_TYPE) == 0) {
            this.mSocialMethods.mMethods.partySetType(GraphQLSocialQuery.PARTY_SET_TYPE, new PartySetTypeParams((String) contentValues.get("party_id"), (String) contentValues.get("party_type")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_ACTIVATE_LINK_INVITE) == 0) {
            this.mSocialMethods.mMethods.partyActivateLinkInvite(GraphQLSocialQuery.PARTY_ACTIVATE_LINK_INVITE, new PartyIDMutationParam((String) contentValues.get("party_id")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_DEACTIVATE_LINK_INVITE) == 0) {
            this.mSocialMethods.mMethods.partyDeactivateLinkInvite(GraphQLSocialQuery.PARTY_DEACTIVATE_LINK_INVITE, new PartyIDMutationParam((String) contentValues.get("party_id")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_KICK) == 0) {
            this.mSocialMethods.mMethods.partyKick(GraphQLSocialQuery.PARTY_KICK, new PartyKickParams((String) contentValues.get("party_id"), new String[]{contentValues.get("user_id")}), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE) == 0) {
            this.mSocialMethods.mMethods.partyProposedGroupLaunchDestinationCreate(GraphQLSocialQuery.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE, new PartyProposedGroupLaunchDestinationCreateParams((String) contentValues.get("destination_id"), (String) contentValues.get("party_id")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE) == 0) {
            this.mSocialMethods.mMethods.partyProposedGroupLaunchDestinationRemove(GraphQLSocialQuery.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE, new PartyProposedGroupLaunchDestinationRemoveParams((String) contentValues.get("party_id")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.GROUP_LAUNCH_SET_STATE) == 0) {
            this.mSocialMethods.mMethods.groupLaunchSetState(GraphQLSocialQuery.GROUP_LAUNCH_SET_STATE, new GroupLaunchSetStateParams((String) contentValues.get("group_launch_state"), (String) contentValues.get("group_launch_id")), "");
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.GROUP_LAUNCH_SET_USER_RESPONSE) == 0) {
            this.mSocialMethods.mMethods.groupLaunchSetUserResponse(GraphQLSocialQuery.GROUP_LAUNCH_SET_USER_RESPONSE, new GroupLaunchSetUserResponseParams((String) contentValues.get("response"), (String) contentValues.get("group_launch_id")), "");
            return 1;
        } else {
            if (uri.compareTo(OculusContent.FriendList.GROUP_LAUNCH_LAUNCH_HANDLE) == 0) {
                methods = this.mSocialMethods.mMethods;
                groupLaunchLaunchHandleParams = new GroupLaunchLaunchHandleParams((String) contentValues.get("group_launch_id"));
                str4 = GraphQLSocialQuery.GROUP_LAUNCH_HANDLE_LAUNCH;
            } else if (uri.compareTo(OculusContent.FriendList.GROUP_LAUNCH_SOLO_LAUNCH_HANDLE) == 0) {
                methods = this.mSocialMethods.mMethods;
                groupLaunchLaunchHandleParams = new GroupLaunchLaunchHandleParams((String) contentValues.get("group_launch_id"));
                str4 = GraphQLSocialQuery.GROUP_LAUNCH_HANDLE_SOLO_LAUNCH;
            } else if (uri.compareTo(OculusContent.FriendList.PARTY_SET_VOLUME) == 0) {
                try {
                    getPartyInterface().A8i(((Number) contentValues.get("party_volume")).floatValue());
                    return 1;
                } catch (PlatformInitException e3) {
                    AnonymousClass0NO.A0C(TAG, "Failed to set party volume", e3);
                    return 0;
                }
            } else if (uri.compareTo(OculusContent.FriendList.PARTY_SET_SELF_MUTE) == 0) {
                try {
                    return getMicrophoneMuteStateFromPartyStateJson(getPartyInterface().A8p(((Number) contentValues.get(OculusContent.FriendList.PARTY_SELF_MUTE_VALUE)).intValue()));
                } catch (PlatformInitException | IllegalArgumentException e4) {
                    e = e4;
                    str2 = TAG;
                    str3 = "Failed to set local party mute";
                    AnonymousClass0NO.A0C(str2, str3, e);
                    return 0;
                }
            } else if (uri.compareTo(OculusContent.FriendList.PARTY_GET_SELF_MUTE) == 0) {
                try {
                    return getMicrophoneMuteStateFromPartyStateJson(getPartyInterface().A4V());
                } catch (PlatformInitException | IllegalArgumentException e5) {
                    e = e5;
                    str2 = TAG;
                    str3 = "Failed to get local party mute";
                    AnonymousClass0NO.A0C(str2, str3, e);
                    return 0;
                }
            } else if (uri.compareTo(OculusContent.FriendList.PARTY_SET_PER_PERSON_MUTE_STATUS) == 0) {
                try {
                    return getPartyInterface().A8j(Long.parseLong((String) contentValues.get("user_id")), ((Integer) contentValues.get(OculusContent.FriendList.PARTY_PER_PERSON_MUTE_VALUE)).intValue());
                } catch (PlatformInitException | ClassCastException | NumberFormatException e6) {
                    e = e6;
                    str2 = TAG;
                    str3 = "Failed to set per person mute status";
                    AnonymousClass0NO.A0C(str2, str3, e);
                    return 0;
                }
            } else if (uri.compareTo(OculusContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_APP) != 0 && uri.compareTo(OculusContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_PARTY) != 0) {
                return 0;
            } else {
                if (!this.mIsMicSwitchingEnabled) {
                    AnonymousClass0NO.A09(TAG, "The current OS version does not support mic switching. no-op.");
                    return 0;
                }
            }
            methods.groupLaunchLaunchHandle(str4, groupLaunchLaunchHandleParams, "");
            return 1;
        }
        return 1;
    }
}
