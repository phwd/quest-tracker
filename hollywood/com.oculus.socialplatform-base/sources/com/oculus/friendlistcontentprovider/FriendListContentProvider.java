package com.oculus.friendlistcontentprovider;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import bolts.Task;
import bolts.TaskCompletionSource;
import com.facebook.ultralight.Eager;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.oculus.horizon.api.common.PageInfo;
import com.oculus.horizon.api.common.user.ProfilePhoto;
import com.oculus.horizon.api.common.user.User;
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
import com.oculus.horizon.api.social.RosterSizeResponse;
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
import com.oculus.horizon.social.request.FriendRequestSource;
import com.oculus.horizon.social.request.PartyMicrophoneChannel;
import com.oculus.horizon.social.request.SocialActivityParams;
import com.oculus.horizon.social.request.UserSearchParams;
import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import com.oculus.partycontroller.PartyController;
import com.oculus.platforminitexception.PlatformInitException;
import com.oculus.provider.OculusContent;
import com.oculus.util.constants.MicrophoneButtonState;
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
    public static final ImmutableSet<String> WHITELISTED_PACKAGES;
    public AnonymousClass0RE _UL_mInjectionContext;
    public String mFbPermissionName;
    public boolean mIsMicSwitchingEnabled;
    @Inject
    @Eager
    public SocialMethods mSocialMethods;

    static {
        Preconditions.checkArgument(true, "the total number of elements must fit in an int");
        Object[] objArr = {"com.oculus.vrshell", "com.oculus.vrshell.home", "com.oculus.socialplatform", "com.oculus.livingroom", "com.oculus.tv", "com.oculus.systemux"};
        System.arraycopy(new String[0], 0, objArr, 6, 0);
        WHITELISTED_PACKAGES = ImmutableSet.A03(6, objArr);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, FriendListContentProvider friendListContentProvider) {
        friendListContentProvider._UL_mInjectionContext = new AnonymousClass0RE(2, r2);
        friendListContentProvider.mSocialMethods = SocialMethods._UL__ULSEP_com_oculus_horizon_social_SocialMethods_ULSEP_ACCESS_METHOD(r2);
    }

    @Nullable
    public static Cursor convertPartyInfoToCursor(PartyInviteInfoResponse partyInviteInfoResponse) {
        PartyInviteInfoResponse.Party node;
        String str;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"row_type", "party_id", "id", "alias", "image_url"}, 0);
        if (partyInviteInfoResponse == null || (node = partyInviteInfoResponse.getNode()) == null) {
            return null;
        }
        PartyInviteInfoResponse.ViewerParty viewerParty = partyInviteInfoResponse.viewer.user.current_party;
        matrixCursor.addRow(new Object[]{0, node.id, null, node.party_type, null});
        if (viewerParty == null) {
            str = null;
        } else {
            str = viewerParty.id;
        }
        matrixCursor.addRow(new Object[]{9, str, null, null, null});
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
            String str2 = node.id;
            PartyInviteInfoResponse.Application application = groupLaunch.destination.application;
            matrixCursor.addRow(new Object[]{5, str2, application.id, application.display_name, application.icon_image.uri});
            String str3 = node.id;
            PartyInviteInfoResponse.GroupLaunchDestination groupLaunchDestination = groupLaunch.destination;
            matrixCursor.addRow(new Object[]{6, str3, groupLaunchDestination.id, groupLaunchDestination.display_name, groupLaunchDestination.image});
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
            GroupLaunchAppDestinationsResponse fetchGroupLaunchAppDestinationsSync = this.mSocialMethods.fetchGroupLaunchAppDestinationsSync(str, str2, num);
            matrixCursor = new MatrixCursor(new String[]{"deeplink_message_for_deeplink_target", "destination_display_name", "destination_id", "destination_image", "destination_max_capacity", "destination_is_external_deeplinkable", "cursor", "has_next"}, 0);
            if (!(fetchGroupLaunchAppDestinationsSync == null || (node = fetchGroupLaunchAppDestinationsSync.node) == null || (grouping = node.grouping) == null || (destinations = grouping.destinations) == null || (list = destinations.edges) == null || (pageInfo = destinations.page_info) == null)) {
                boolean z = pageInfo.has_next_page;
                int size = list.size() - 1;
                int i = 0;
                for (GroupLaunchAppDestinationsResponse.Edge edge : fetchGroupLaunchAppDestinationsSync.node.grouping.destinations.edges) {
                    GroupLaunchAppDestinationsResponse.Destination destination = edge.node;
                    String str4 = destination.deeplink_message_for_deeplink_target;
                    String str5 = destination.display_name;
                    String str6 = destination.id;
                    String str7 = destination.image;
                    Integer valueOf = Integer.valueOf(destination.max_group_launch_capacity);
                    String str8 = destination.is_external_deeplinkable;
                    String str9 = edge.cursor;
                    boolean z2 = z;
                    if (i < size) {
                        z2 = true;
                    }
                    int i2 = z2 ? 1 : 0;
                    int i3 = z2 ? 1 : 0;
                    int i4 = z2 ? 1 : 0;
                    matrixCursor.addRow(new Object[]{str4, str5, str6, str7, valueOf, str8, str9, Integer.valueOf(i2)});
                    i++;
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

    private Cursor getPartyMicrophoneButtonState() {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_mic_state"}, 0);
        matrixCursor.addRow(new Object[]{getPartyInterface().getPartyMicrophoneState()});
        return matrixCursor;
    }

    private Cursor getPartyMicrophoneInputLocation() {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_microphone_input_location"}, 0);
        matrixCursor.addRow(new Object[]{Integer.valueOf(getPartyInterface().getPartyIsUsingMicrophone() ? 1 : 0)});
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
            Task<UserSearchResponse> userSearchAsync = getUserSearchAsync(str, str2);
            userSearchAsync.waitForCompletion();
            if (userSearchAsync.getError() != null) {
                return null;
            }
            matrixCursor = new MatrixCursor(new String[]{"user_id", "name", "avatar", "friend_status", "alias", "can_viewer_message"}, 1);
            try {
                for (SearchResultUser searchResultUser : userSearchAsync.getResult().results.nodes) {
                    String userSearchAvatarUrl = getUserSearchAvatarUrl(searchResultUser);
                    String userSearchName = getUserSearchName(searchResultUser);
                    String str3 = searchResultUser.user_id;
                    String str4 = searchResultUser.friend_status;
                    String str5 = searchResultUser.alias;
                    boolean z = searchResultUser.can_viewer_message;
                    int i = 0;
                    if (z) {
                        i = 1;
                    }
                    matrixCursor.addRow(new Object[]{str3, userSearchName, userSearchAvatarUrl, str4, str5, Integer.valueOf(i)});
                }
                return matrixCursor;
            } catch (Exception e2) {
                e = e2;
                AnonymousClass0MD.A07(TAG, "User search was interrupted.", e);
                return matrixCursor;
            }
        } catch (Exception e3) {
            e = e3;
            matrixCursor = null;
            AnonymousClass0MD.A07(TAG, "User search was interrupted.", e);
            return matrixCursor;
        }
    }

    @Nullable
    @SuppressLint({"CatchGeneralException"})
    private Cursor joinPartyQuery(String str) {
        Exception e;
        MatrixCursor matrixCursor = null;
        try {
            Task<String> joinPartyAsync = joinPartyAsync(str);
            joinPartyAsync.waitForCompletion();
            if (joinPartyAsync.getError() == null) {
                return null;
            }
            MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{OculusContent.FriendList.PARTY_JOIN_ERROR_COLUMN}, 1);
            try {
                matrixCursor2.addRow(new Object[]{joinPartyAsync.getError().getMessage()});
                return matrixCursor2;
            } catch (Exception e2) {
                e = e2;
                matrixCursor = matrixCursor2;
                AnonymousClass0MD.A07(TAG, "Party join was interrupted.", e);
                return matrixCursor;
            }
        } catch (Exception e3) {
            e = e3;
            AnonymousClass0MD.A07(TAG, "Party join was interrupted.", e);
            return matrixCursor;
        }
    }

    @Override // X.AnonymousClass0jF
    public int doDelete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AnonymousClass0jF
    @Nullable
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // X.AnonymousClass0jF
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
                return createParty(uri.getQueryParameter("invite_activity_id"), uri.getQueryParameter("party_type"), uri.getQueryParameter("destination_id"), uri.getQueryParameter("message_thread_key"));
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
                String queryParameter = uri.getQueryParameter("activity_id");
                String queryParameter2 = uri.getQueryParameter("role");
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
                    return getPartyInviteInfoWithNonce(uri.getQueryParameter("deeplink_target_id"), uri.getQueryParameter("link_nonce"));
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
                    String queryParameter5 = uri.getQueryParameter("ignore_rich_presence");
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
                        return getFbFriendPrimaryProfile(uri.getQueryParameter("fb_friend_id"));
                    } else {
                        if (uri.toString().contains(OculusContent.FriendList.GET_VR_PROFILE_CONTENT.toString())) {
                            return getVRProfileContent(uri.getQueryParameter("id"));
                        }
                        if (uri.toString().contains(OculusContent.FriendList.USER_SEARCH.toString())) {
                            return getUserSearch(uri.getQueryParameter("query_string"), uri.getQueryParameter("search_mode"));
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
                        } else if (uri.compareTo(OculusContent.FriendList.PARTY_GET_MICROPHONE_INPUT_LOCATION) == 0) {
                            return getPartyMicrophoneInputLocation();
                        } else {
                            if (uri.compareTo(OculusContent.FriendList.PARTY_GET_MICROPHONE_STATE) == 0) {
                                return getPartyMicrophoneButtonState();
                            }
                            if (uri.toString().contains(OculusContent.FriendList.GET_ROSTER_SIZE.toString())) {
                                return getRosterSize();
                            }
                            return null;
                        }
                    }
                }
            }
        }
        AnonymousClass0MD.A04(str3, str4);
        return null;
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public static void addBlockedUserRowForCurrentParty(MatrixCursor matrixCursor, PartyInfoResponse.BlockedUser blockedUser) {
        Object[] objArr = new Object[43];
        System.arraycopy(new Object[]{blockedUser.id, null, blockedUser.alias, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}, 0, objArr, 0, 27);
        System.arraycopy(new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null, null}, 0, objArr, 27, 16);
        matrixCursor.addRow(objArr);
    }

    public static void addNewResultTypeIndicatorRowForCurrentParty(MatrixCursor matrixCursor, PartyListResponse.ListType listType) {
        Object[] objArr = new Object[43];
        System.arraycopy(new Object[]{null, null, null, null, null, null, null, null, null, null, null, listType.toString(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}, 0, objArr, 0, 27);
        System.arraycopy(new Object[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}, 0, objArr, 27, 16);
        matrixCursor.addRow(objArr);
    }

    private Cursor createParty(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        PartyCreateResponse partyCreateSync = this.mSocialMethods.partyCreateSync(str, str2, str3, str4);
        String str5 = partyCreateSync.party.id;
        if (partyCreateSync.did_create_party) {
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
        AppScoreboardsInfoResponse fetchAppScoreboardsInfoSync = this.mSocialMethods.fetchAppScoreboardsInfoSync(str);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"appId", "hasAchievements", "hasLeaderboards"}, 0);
        if (!(fetchAppScoreboardsInfoSync == null || (node = fetchAppScoreboardsInfoSync.node) == null || (grouping = node.grouping) == null || (achievementDefinitions = grouping.achievement_definitions) == null || (leaderboards = grouping.leaderboards) == null)) {
            boolean z = !achievementDefinitions.is_empty;
            boolean z2 = !leaderboards.is_empty;
            String str2 = "true";
            String str3 = "false";
            if (z) {
                str3 = str2;
            }
            if (!z2) {
                str2 = "false";
            }
            matrixCursor.addRow(new Object[]{str, str3, str2});
        }
        return matrixCursor;
    }

    private Cursor getAppsScoreboardsInfo(String[] strArr) {
        ArrayList<AppsScoreboardsInfoResponse.Node> arrayList;
        AppsScoreboardsInfoResponse.Grouping grouping;
        AppsScoreboardsInfoResponse.AchievementDefinitions achievementDefinitions;
        AppsScoreboardsInfoResponse.Leaderboards leaderboards;
        AppsScoreboardsInfoResponse fetchAppsScoreboardsInfoSync = this.mSocialMethods.fetchAppsScoreboardsInfoSync(strArr);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"appId", "hasAchievements", "hasLeaderboards"}, 0);
        if (!(fetchAppsScoreboardsInfoSync == null || (arrayList = fetchAppsScoreboardsInfoSync.nodes) == null)) {
            Iterator<AppsScoreboardsInfoResponse.Node> it = arrayList.iterator();
            while (it.hasNext()) {
                AppsScoreboardsInfoResponse.Node next = it.next();
                String str = next.id;
                if (!(str == null || (grouping = next.grouping) == null || (achievementDefinitions = grouping.achievement_definitions) == null || (leaderboards = grouping.leaderboards) == null)) {
                    boolean z = !achievementDefinitions.is_empty;
                    boolean z2 = !leaderboards.is_empty;
                    String str2 = "true";
                    String str3 = "false";
                    if (z) {
                        str3 = str2;
                    }
                    if (!z2) {
                        str2 = "false";
                    }
                    matrixCursor.addRow(new Object[]{str, str3, str2});
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
        AuiProfileContentResponse fetchAuiProfileContent = this.mSocialMethods.fetchAuiProfileContent();
        int i = 0;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"friend_count", "biography", "presence", "is_current", "name", "alias", "profile_image_url", "avatar_image_url"}, 0);
        if (!(fetchAuiProfileContent == null || (user = fetchAuiProfileContent.user) == null || (friends = user.friends) == null)) {
            AuiProfileContentResponse.ProfilePhoto profilePhoto = user.profile_photo;
            AuiProfileContentResponse.AvatarImage avatarImage = user.avatar_image;
            Integer valueOf = Integer.valueOf(friends.count);
            String str3 = user.biography;
            String str4 = user.presence;
            String str5 = "";
            if (str4 == null) {
                str4 = str5;
            }
            AuiProfileContentResponse.MostRecentPresence mostRecentPresence = user.most_recent_presence;
            if (mostRecentPresence != null && mostRecentPresence.is_current) {
                i = 1;
            }
            Integer valueOf2 = Integer.valueOf(i);
            String str6 = user.name;
            if (str6 == null) {
                str6 = str5;
            }
            String str7 = user.alias;
            if (str7 == null) {
                str7 = str5;
            }
            if (profilePhoto == null || (str = profilePhoto.uri) == null) {
                str = str5;
            }
            if (!(avatarImage == null || (str2 = avatarImage.uri) == null)) {
                str5 = str2;
            }
            matrixCursor.addRow(new Object[]{valueOf, str3, str4, valueOf2, str6, str7, str, str5});
        }
        return matrixCursor;
    }

    private Cursor getBlockedUserId(String str) {
        BlockedUsersResponse.BlockedUsers blockedUsers;
        ArrayList<BlockedUser> arrayList;
        BlockedUsersResponse fetchBlockedUsersSync = this.mSocialMethods.fetchBlockedUsersSync();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"blocked_user_id"}, 0);
        if (fetchBlockedUsersSync != null && (blockedUsers = fetchBlockedUsersSync.blocked_users) != null && (arrayList = blockedUsers.nodes) != null) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0090, code lost:
        if (r7.does_activity_support_group_launch == false) goto L_0x0092;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x021a A[LOOP:0: B:67:0x0214->B:69:0x021a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x02a1  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x02a9  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02ad  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x02b9  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x02c5  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x02cd  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02d1  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02d5  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02dd  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x02e1  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02e9  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02ed  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0336 A[LOOP:1: B:96:0x0330->B:98:0x0336, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.Cursor getCurrentParty() {
        /*
        // Method dump skipped, instructions count: 906
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.friendlistcontentprovider.FriendListContentProvider.getCurrentParty():android.database.Cursor");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0094, code lost:
        if (r7.does_activity_support_group_launch == false) goto L_0x0096;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0373 A[LOOP:2: B:100:0x036d->B:102:0x0373, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0392 A[LOOP:3: B:104:0x038c->B:106:0x0392, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01d8 A[LOOP:0: B:67:0x01d2->B:69:0x01d8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x026f  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0273  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0277  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0283  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x028b  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0293  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x029b  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x029f  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02a7  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02b3  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02bb  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02bf  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02e9 A[LOOP:1: B:96:0x02e3->B:98:0x02e9, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.Cursor getCurrentPartyV2() {
        /*
        // Method dump skipped, instructions count: 925
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.friendlistcontentprovider.FriendListContentProvider.getCurrentPartyV2():android.database.Cursor");
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
                jSONObject.put("alias", socialUser.alias);
                jSONArray.put(jSONObject);
                jSONObject2.put("type", DeeplinkIntentUtils.INTENT_KEY_TYPE_DEEPLINK);
                jSONObject2.put(DeeplinkIntentUtils.INTENT_KEY_LAUNCH_SOURCE, "AUIv2_PARTY_UI");
                jSONObject2.put("users", jSONArray);
                jSONObject2.put("destination_api_name", socialUser.most_recent_presence.destination_api_name);
                jSONObject2.put("deeplink_message", socialUser.most_recent_presence.deeplink_message_for_deeplink_target);
                jSONObject3.put(DeeplinkIntentUtils.INTENT_KEY_SOCIAL_LAUNCH, jSONObject2);
                return jSONObject3.toString();
            } catch (JSONException e) {
                AnonymousClass0MD.A07(TAG, "Error parsing deep link information for party user.", e);
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
            partyInvitableUsersResponse = this.mSocialMethods.fetchPartyInvitableUsersSync(loggedInUserId);
            i = partyInvitableUsersResponse.friends.count;
        } else {
            partyInvitableUsersResponse = null;
            i = 0;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", "presence", "presence_status", "profile_image_url", "invite_state", "current_party_id"}, i);
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
                    matrixCursor.addRow(new Object[]{next.id, userDisplayName, next.presence, str, Integer.valueOf(ordinal), null});
                }
                ordinal = userInviteState.ordinal();
                matrixCursor.addRow(new Object[]{next.id, userDisplayName, next.presence, str, Integer.valueOf(ordinal), null});
            }
        }
        return matrixCursor;
    }

    private Cursor getFriendRequests() {
        SocialFriendRequestsResponse.FriendRequests friendRequests;
        ArrayList<SocialFriendRequestsResponse.FriendRequests.Edge> arrayList;
        SocialFriendRequestsResponse fetchFriendRequestsSync = this.mSocialMethods.fetchFriendRequestsSync();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", "alias", "presence", "presence_status", "profile_image_url", "friendship_status"}, 0);
        if (!(fetchFriendRequestsSync == null || (friendRequests = fetchFriendRequestsSync.friend_requests_received_2) == null || (arrayList = friendRequests.edges) == null)) {
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
        GroupLaunchSupportedApplicationsResponse fetchGroupLaunchSupportedApplicationsSync = this.mSocialMethods.fetchGroupLaunchSupportedApplicationsSync();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"application_id", "application_display_short_description", "application_max_group_launch_capacity", "application_party_user_entitlement_count", "party_current_users"}, 0);
        if (!(fetchGroupLaunchSupportedApplicationsSync == null || (viewer = fetchGroupLaunchSupportedApplicationsSync.viewer) == null || (user = viewer.user) == null || (appEntitlements = user.app_entitlements) == null || appEntitlements.nodes == null)) {
            GroupLaunchSupportedApplicationsResponse.CurrentParty currentParty = user.current_party;
            if (currentParty == null || (partyUsers = currentParty.party_users) == null || (list = partyUsers.nodes) == null) {
                hashMap = new HashMap<>();
                i = 0;
            } else {
                hashMap = getPartyMemberAppEntitlementCounts(list);
                i = currentParty.party_users.nodes.size();
            }
            for (GroupLaunchSupportedApplicationsResponse.AppEntitlement appEntitlement : fetchGroupLaunchSupportedApplicationsSync.viewer.user.app_entitlements.nodes) {
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
        JoinablePartiesResponse.JoinableParty next;
        ArrayList<SocialUser> arrayList;
        JoinablePartiesResponse fetchJoinablePartiesSync = this.mSocialMethods.fetchJoinablePartiesSync();
        String[] strArr = {"party_id", OculusContent.FriendList.PARTY_BLOCKED_USERS_COUNT_COLUMN, "id", "name", "alias", "friendship_status", "profile_image_url", "party_max_size", "can_viewer_message"};
        JoinablePartiesResponse.JoinableParties joinableParties = fetchJoinablePartiesSync.joinable_parties_from_friends;
        if (joinableParties == null || (i = joinableParties.count) <= 0) {
            return new MatrixCursor(strArr, 0);
        }
        Iterator<JoinablePartiesResponse.JoinableParty> it = joinableParties.nodes.iterator();
        while (it.hasNext()) {
            i += it.next().party_users.count.intValue();
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, i);
        Iterator<JoinablePartiesResponse.JoinableParty> it2 = fetchJoinablePartiesSync.joinable_parties_from_friends.nodes.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            next = it2.next();
            matrixCursor.addRow(new Object[]{next.id, next.party_blocked_users.count, null, null, null, null, null, Integer.valueOf(next.max_size), null});
            JoinablePartiesResponse.PartyUsers partyUsers = next.party_users;
            if (partyUsers == null || (arrayList = partyUsers.nodes) == null) {
                AnonymousClass0MD.A09(TAG, "Party id=%s query party_users return null", next.id);
            } else {
                Iterator<SocialUser> it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    SocialUser next2 = it3.next();
                    matrixCursor.addRow(new Object[]{null, null, next2.id, next2.name, next2.alias, getSocialUserFriendshipStatus(next2), getSocialUserPhotoUrl(next2), null, Integer.valueOf(next2.can_viewer_message ? 1 : 0)});
                }
            }
        }
        AnonymousClass0MD.A09(TAG, "Party id=%s query party_users return null", next.id);
        return matrixCursor;
    }

    @Nullable
    private Cursor getLinkedAccountsInfo() {
        FbLinkedStatusResponse fetchFbLinkedStatusSync = this.mSocialMethods.fetchFbLinkedStatusSync();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"fb_linked_status"}, 1);
        if (fetchFbLinkedStatusSync != null) {
            String str = fetchFbLinkedStatusSync.viewer.fb_linked_status;
            matrixCursor.addRow(new Object[]{str});
            ((FriendListContentProviderLogger) AnonymousClass0VF.A03(1, 66, this._UL_mInjectionContext)).reportLinkedAccountsQuery(this, str.equals(LinkedAccountsInfo.FbLinkedStatus.LINKED));
        }
        return matrixCursor;
    }

    public static int getMicrophoneMuteStateFromPartyStateJson(String str) {
        try {
            return MicrophoneMuteState.valueOf(new JSONObject(str).getString("microphone_muted"));
        } catch (JSONException e) {
            AnonymousClass0MD.A07(TAG, "I don't like your json", e);
            return 0;
        }
    }

    private Cursor getPYMKs() {
        PYMKResponse.PeopleYouMayKnow peopleYouMayKnow;
        ArrayList<SocialUser> arrayList;
        PYMKResponse fetchPYMKsSync = this.mSocialMethods.fetchPYMKsSync();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", "alias", "presence", "presence_status", "profile_image_url", "mutual_context_string", "can_viewer_message", "friendship_status"}, 0);
        if (!(fetchPYMKsSync == null || (peopleYouMayKnow = fetchPYMKsSync.people_you_may_know) == null || (arrayList = peopleYouMayKnow.nodes) == null)) {
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
        GetPartyInfoWithActivityResponse partyInfoWithActivitySync = this.mSocialMethods.getPartyInfoWithActivitySync(str);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_id", "party_blocked_invited_users", "party_invited_users", "party_blocked_current_users", "party_current_users", "party_activity_id", "party_activity_title", "party_activity_subtitle", "party_activity_deeplink", "party_activity_image_uri", "party_activity_package_name", "party_activity_app_id"}, 1);
        String str8 = partyInfoWithActivitySync.id;
        Integer valueOf = Integer.valueOf(partyInfoWithActivitySync.party_blocked_invited_users.count);
        Integer valueOf2 = Integer.valueOf(partyInfoWithActivitySync.party_invited_users.count);
        Integer valueOf3 = Integer.valueOf(partyInfoWithActivitySync.party_blocked_users.count);
        Integer valueOf4 = Integer.valueOf(partyInfoWithActivitySync.party_users.count);
        GetPartyInfoWithActivityResponse.PartyInviteActivity partyInviteActivity = partyInfoWithActivitySync.party_invite_activity;
        String str9 = null;
        if (partyInviteActivity != null) {
            str2 = partyInviteActivity.id;
        } else {
            str2 = null;
        }
        if (partyInviteActivity != null) {
            str3 = partyInviteActivity.title;
        } else {
            str3 = null;
        }
        if (partyInviteActivity != null) {
            str4 = partyInviteActivity.subtitle;
        } else {
            str4 = null;
        }
        if (partyInviteActivity != null) {
            str5 = partyInviteActivity.deeplink;
        } else {
            str5 = null;
        }
        if (partyInviteActivity == null || (activityImage = partyInviteActivity.image) == null) {
            str6 = null;
        } else {
            str6 = activityImage.uri;
        }
        if (partyInviteActivity == null || (application2 = partyInviteActivity.application) == null) {
            str7 = null;
        } else {
            str7 = application2.package_name;
        }
        if (!(partyInviteActivity == null || (application = partyInviteActivity.application) == null)) {
            str9 = application.id;
        }
        matrixCursor.addRow(new Object[]{str8, valueOf, valueOf2, valueOf3, valueOf4, str2, str3, str4, str5, str6, str7, str9});
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
        GetPartyInfoWithMembersResponse partyInfoWithMembersSync = this.mSocialMethods.getPartyInfoWithMembersSync(str);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", "alias", "presence", "profile_image_url", "current_party_id", "party_max_size", OculusContent.FriendList.PARTY_USERS_COLUMN, "party_blocked_current_users", "party_activity_id", "party_activity_title", "party_activity_subtitle", "party_activity_deeplink", "party_activity_image_uri", "party_activity_package_name", "party_activity_app_id"}, 0);
        if (partyInfoWithMembersSync != null) {
            SocialUser socialUser = partyInfoWithMembersSync.party_leader;
            String str9 = socialUser.id;
            String str10 = socialUser.name;
            String str11 = socialUser.alias;
            String str12 = socialUser.presence;
            String str13 = socialUser.presence_status;
            String socialUserPhotoUrl = getSocialUserPhotoUrl(socialUser);
            String str14 = partyInfoWithMembersSync.id;
            Integer valueOf = Integer.valueOf(partyInfoWithMembersSync.max_size);
            Integer num = partyInfoWithMembersSync.party_users.count;
            Integer num2 = partyInfoWithMembersSync.party_blocked_users.count;
            GetPartyInfoWithMembersResponse.PartyInviteActivity partyInviteActivity = partyInfoWithMembersSync.party_invite_activity;
            if (partyInviteActivity != null) {
                str2 = partyInviteActivity.id;
            } else {
                str2 = null;
            }
            if (partyInviteActivity != null) {
                str3 = partyInviteActivity.title;
            } else {
                str3 = null;
            }
            if (partyInviteActivity != null) {
                str4 = partyInviteActivity.subtitle;
            } else {
                str4 = null;
            }
            if (partyInviteActivity != null) {
                str5 = partyInviteActivity.deeplink;
            } else {
                str5 = null;
            }
            if (partyInviteActivity == null || (activityImage = partyInviteActivity.image) == null) {
                str6 = null;
            } else {
                str6 = activityImage.uri;
            }
            if (partyInviteActivity == null || (application2 = partyInviteActivity.application) == null) {
                str7 = null;
            } else {
                str7 = application2.package_name;
            }
            if (partyInviteActivity == null || (application = partyInviteActivity.application) == null) {
                str8 = null;
            } else {
                str8 = application.id;
            }
            matrixCursor.addRow(new Object[]{str9, str10, str11, str12, str13, socialUserPhotoUrl, str14, valueOf, num, num2, str2, str3, str4, str5, str6, str7, str8});
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
        return convertPartyInfoToCursor(this.mSocialMethods.fetchPartyInviteInfo(str));
    }

    @Nullable
    private Cursor getPartyInviteInfoWithNonce(String str, String str2) {
        return convertPartyInfoToCursor(this.mSocialMethods.fetchPartyInviteInfoWithNonce(str, str2));
    }

    private Cursor getPartyInvites() {
        PartyInvitesResponse.InvitedParties invitedParties;
        PartyInvitesResponse fetchPartyInvites = this.mSocialMethods.fetchPartyInvites();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", "alias", "friendship_status", "presence", "presence_status", "profile_image_url", "current_party_id", "join_policy", OculusContent.FriendList.PARTY_BLOCKED_USERS_COUNT_COLUMN, "party_max_size"}, 0);
        if (!(fetchPartyInvites == null || (invitedParties = fetchPartyInvites.invited_parties) == null)) {
            Iterator<PartyInvitesResponse.Party> it = invitedParties.nodes.iterator();
            while (it.hasNext()) {
                PartyInvitesResponse.Party next = it.next();
                SocialUser socialUser = next.invited_by;
                matrixCursor.addRow(new Object[]{socialUser.id, socialUser.name, socialUser.alias, getSocialUserFriendshipStatus(socialUser), socialUser.presence, socialUser.presence_status, getSocialUserPhotoUrl(socialUser), next.id, next.join_policy, next.party_blocked_users.count, next.max_size});
                Iterator<SocialUser> it2 = next.party_users.nodes.iterator();
                while (it2.hasNext()) {
                    SocialUser next2 = it2.next();
                    matrixCursor.addRow(new Object[]{next2.id, next2.name, next2.alias, getSocialUserFriendshipStatus(next2), next2.presence, next2.presence_status, getSocialUserPhotoUrl(next2), null, null, null, null});
                }
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
        PartyPrivacyInfoResponse fetchPartyPrivacyInfo = this.mSocialMethods.fetchPartyPrivacyInfo(str);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_id", "party_type", "party_has_link_invite", "party_url"}, 1);
        if (!(fetchPartyPrivacyInfo == null || (party = fetchPartyPrivacyInfo.node) == null)) {
            matrixCursor.addRow(new Object[]{party.id, party.party_type, Integer.valueOf(party.has_active_link_sharing ? 1 : 0), party.url_for_deeplink_target_web_page});
        }
        return matrixCursor;
    }

    private Cursor getPeopleNearby(@Nullable Integer num) {
        SocialUser socialUser;
        ArrayList<SocialUser> arrayList;
        PeopleNearbyResponse fetchPeopleNearbySync = this.mSocialMethods.fetchPeopleNearbySync(num);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", "alias", "presence", "presence_status", "profile_image_url", "can_viewer_message", "mutual_context_string", "friendship_status", "is_in_room"}, 0);
        if (!(fetchPeopleNearbySync == null || (socialUser = fetchPeopleNearbySync.user) == null || socialUser.id == null || (arrayList = socialUser.people_nearby) == null)) {
            arrayList.add(0, socialUser);
            Iterator<SocialUser> it = arrayList.iterator();
            while (it.hasNext()) {
                SocialUser next = it.next();
                matrixCursor.addRow(new Object[]{next.id, next.name, next.alias, next.presence, next.presence_status, getSocialUserPhotoUrl(next), Integer.valueOf(next.can_viewer_message ? 1 : 0), next.mutual_context_string, getSocialUserFriendshipStatus(next), Integer.valueOf(fetchPeopleNearbySync.user.is_in_room ? 1 : 0)});
            }
        }
        return matrixCursor;
    }

    private Cursor getRosterSize() {
        RosterSizeResponse.Viewer viewer;
        RosterSizeResponse fetchRosterSize = this.mSocialMethods.fetchRosterSize();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"row_type", "value"}, 0);
        if (!(fetchRosterSize == null || (viewer = fetchRosterSize.viewer) == null)) {
            RosterSizeResponse.User user = viewer.user;
            matrixCursor.addRow(new Object[]{0, Integer.valueOf(user.lobby_roster.count)});
            matrixCursor.addRow(new Object[]{1, Integer.valueOf(user.application_invites.count)});
        }
        return matrixCursor;
    }

    private Cursor getSharePartyInfo() {
        SharePartyInfoResponse.CurrentParty currentParty;
        SharePartyInfoResponse fetchSharePartyInfo = this.mSocialMethods.fetchSharePartyInfo();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_fbid"}, 0);
        if (!(fetchSharePartyInfo == null || (currentParty = fetchSharePartyInfo.current_party) == null)) {
            matrixCursor.addRow(new String[]{currentParty.fb_shareable_ent_id});
        }
        return matrixCursor;
    }

    private Cursor getSocialFriends(String[] strArr, @Nullable Integer num, @Nullable boolean z) {
        SocialFriendsResponse.Viewer viewer;
        SocialFriendsResponse.User user;
        SocialFriendsResponse.Friends friends;
        ArrayList<SocialUser> arrayList;
        String str;
        String str2;
        String str3;
        String str4;
        SocialUser.PartyUsers partyUsers;
        SocialFriendsResponse fetchSocialFriends = this.mSocialMethods.fetchSocialFriends(strArr, num, z);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"id", "name", "alias", "presence", "presence_status", "profile_image_url", "last_active_description", "current_party_id", "join_policy", "party_max_size", "party_users_count", "can_viewer_message", "friendship_status"}, 0);
        if (!(fetchSocialFriends == null || (viewer = fetchSocialFriends.viewer) == null || (user = viewer.user) == null || (friends = user.friends) == null || (arrayList = friends.nodes) == null)) {
            Iterator<SocialUser> it = arrayList.iterator();
            while (it.hasNext()) {
                SocialUser next = it.next();
                String socialUserPhotoUrl = getSocialUserPhotoUrl(next);
                String str5 = next.id;
                String str6 = next.name;
                String str7 = next.alias;
                String str8 = next.presence;
                String str9 = next.presence_status;
                SocialUser.Presence presence = next.most_recent_presence;
                String str10 = null;
                if (presence == null) {
                    str = null;
                } else {
                    str = presence.last_active_description;
                }
                SocialUser.CurrentParty currentParty = next.current_party;
                if (currentParty == null) {
                    str2 = null;
                } else {
                    str2 = currentParty.id;
                }
                if (currentParty == null) {
                    str3 = null;
                } else {
                    str3 = currentParty.join_policy;
                }
                if (currentParty == null) {
                    str4 = null;
                } else {
                    str4 = currentParty.max_size;
                }
                if (!(currentParty == null || (partyUsers = currentParty.party_users) == null)) {
                    str10 = partyUsers.count;
                }
                matrixCursor.addRow(new Object[]{str5, str6, str7, str8, str9, socialUserPhotoUrl, str, str2, str3, str4, str10, Integer.valueOf(next.can_viewer_message ? 1 : 0), getSocialUserFriendshipStatus(next)});
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
        SocialViewerInfoResponse fetchSocialViewerInfo = this.mSocialMethods.fetchSocialViewerInfo();
        int i = 0;
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"alias", "profile_image_url", "avatar_image_url", "has_seen_vr_invite_profile_nux"}, 0);
        if (!(fetchSocialViewerInfo == null || (user = fetchSocialViewerInfo.user) == null)) {
            String str2 = user.alias;
            SocialViewerInfoResponse.ProfilePhoto profilePhoto = user.profile_photo;
            String str3 = "";
            if (profilePhoto == null) {
                str = str3;
            } else {
                str = profilePhoto.uri;
            }
            SocialViewerInfoResponse.AvatarImage avatarImage = user.avatar_image;
            if (avatarImage != null) {
                str3 = avatarImage.uri;
            }
            SocialViewerInfoResponse.UserNuxFlags userNuxFlags = user.user_nux_flags;
            if (userNuxFlags != null && userNuxFlags.has_seen_vr_invite_profile_nux) {
                i = 1;
            }
            matrixCursor.addRow(new Object[]{str2, str, str3, Integer.valueOf(i)});
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
    private Task<UserSearchResponse> getUserSearchAsync(String str, String str2) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            this.mSocialMethods.userSearch(str, UserSearchParams.SearchType.valueOf(str2), null, new ApiCallback<UserSearchResponse>() {
                /* class com.oculus.friendlistcontentprovider.FriendListContentProvider.AnonymousClass1 */

                @Override // com.oculus.http.core.base.ApiCallback
                public void onError(ApiError apiError) {
                    taskCompletionSource.setError(new Exception(apiError.getFBErrorMessage("")));
                }

                public void onResponse(UserSearchResponse userSearchResponse) {
                    try {
                        taskCompletionSource.setResult(userSearchResponse);
                    } catch (Exception e) {
                        AnonymousClass0MD.A07(FriendListContentProvider.TAG, "There was an exception while user search: ", e);
                        taskCompletionSource.setError(new Exception(e.getMessage()));
                    }
                }
            });
        } catch (Exception e) {
            AnonymousClass0MD.A07(TAG, "User search failed with an exception: ", e);
        }
        return taskCompletionSource.task;
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

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0079, code lost:
        if (r8.id.equals(r3) == false) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007c, code lost:
        if (r3 != null) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007e, code lost:
        r1 = r4.invited_parties;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0080, code lost:
        if (r1 == null) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0082, code lost:
        r1 = r1.nodes;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0084, code lost:
        if (r1 == null) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0086, code lost:
        r2 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008e, code lost:
        if (r2.hasNext() == false) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009c, code lost:
        if (r3.equals(r2.next().id) == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009e, code lost:
        r29 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a0, code lost:
        r1 = r9.current_party;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a2, code lost:
        if (r1 == null) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a4, code lost:
        r2 = r1.join_policy;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a6, code lost:
        if (r2 == null) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ae, code lost:
        if ("DIRECT_JOIN".equals(r2) == false) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b0, code lost:
        r29 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b2, code lost:
        r13 = r9.alias;
        r6 = r9.biography;
        r25 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b8, code lost:
        if (r6 != null) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ba, code lost:
        r6 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00bc, code lost:
        r12 = r9.friend_status;
        r17 = java.lang.Integer.valueOf(r9.is_blocked_by_viewer.booleanValue() ? 1 : 0);
        r1 = r9.profile_photo;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ca, code lost:
        if (r1 != null) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cc, code lost:
        r11 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ce, code lost:
        r1 = r9.avatar_image;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d0, code lost:
        if (r1 != null) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d2, code lost:
        r5 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d4, code lost:
        r4 = r9.presence;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d6, code lost:
        if (r4 != null) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d8, code lost:
        r4 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00da, code lost:
        r3 = r9.presence_status;
        r10 = r9.most_recent_presence;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00de, code lost:
        if (r10 == null) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e0, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00e3, code lost:
        if (r10.is_current != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00e5, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e6, code lost:
        r22 = java.lang.Integer.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ea, code lost:
        if (r10 != null) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00ec, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00ee, code lost:
        r23 = java.lang.Long.valueOf(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00f2, code lost:
        if (r10 == null) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00f4, code lost:
        r1 = r10.presence;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00f6, code lost:
        if (r1 != null) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00f8, code lost:
        r1 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00fa, code lost:
        r2 = r9.name;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00fc, code lost:
        if (r2 == null) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00fe, code lost:
        r25 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0100, code lost:
        if (r8 != null) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0102, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0103, code lost:
        r7.addRow(new java.lang.Object[]{r13, r6, r12, r17, r11, r5, r4, r3, r22, r23, r1, r25, r2, java.lang.Integer.valueOf(r16), r0.viewer.fb_linked_status, r29});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0125, code lost:
        r2 = r8.id;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0127, code lost:
        if (r14 != false) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0129, code lost:
        if (r15 == false) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x012b, code lost:
        r16 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x012e, code lost:
        r1 = r10.vr_last_active_time;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0131, code lost:
        r5 = r1.uri;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0134, code lost:
        r11 = r1.uri;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0137, code lost:
        r29 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0139, code lost:
        if (r3 == null) goto L_0x00b2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.database.Cursor getVRProfileContent(java.lang.String r34) {
        /*
        // Method dump skipped, instructions count: 324
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.friendlistcontentprovider.FriendListContentProvider.getVRProfileContent(java.lang.String):android.database.Cursor");
    }

    @SuppressLint({"CatchGeneralException", "ThrowException"})
    private Task<String> joinPartyAsync(final String str) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            this.mSocialMethods.partyJoinSyncWithCallback(str, new ApiCallback<Void>() {
                /* class com.oculus.friendlistcontentprovider.FriendListContentProvider.AnonymousClass2 */

                @Override // com.oculus.http.core.base.ApiCallback
                public void onError(ApiError apiError) {
                    taskCompletionSource.setError(new Exception(apiError.getFBErrorMessage("")));
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                @Override // com.oculus.http.core.base.ApiCallback
                public /* bridge */ /* synthetic */ void onResponse(Void r2) {
                    onResponse((Void) null);
                }

                public void onResponse(Void r5) {
                    try {
                        taskCompletionSource.setResult(str);
                    } catch (Exception e) {
                        AnonymousClass0MD.A07(FriendListContentProvider.TAG, "There was an exception while starting party chat: ", e);
                        taskCompletionSource.setError(new Exception(e.getMessage()));
                    }
                    FriendListContentProvider.this.startPartyChat(str);
                }
            });
        } catch (Exception e) {
            AnonymousClass0MD.A07(TAG, "Party join failed with an exception: ", e);
        }
        return taskCompletionSource.task;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startPartyChat(String str) {
        Long valueOf;
        if (str != null && (valueOf = Long.valueOf(Long.parseLong(str))) != null) {
            getPartyInterface().startPartyChat(valueOf.longValue());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0098, code lost:
        if (r4 == null) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a0, code lost:
        if (r4.equals(r6.id) == false) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a2, code lost:
        r17 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c6, code lost:
        if (r5 == null) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ce, code lost:
        if ("DIRECT_JOIN".equals(r5) == false) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d0, code lost:
        r27 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d2, code lost:
        if (r14 == false) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d4, code lost:
        r3 = r0.fb_friend_primary_profile;
        r14 = r3.is_currently_active;
        r4 = r3.last_active_time;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00da, code lost:
        r3 = r0.fb_friend_primary_profile;
        r11 = r3.name;
        r16 = 0;
        r10 = r3.profile_picture_uri;
        r3 = r3.bio;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e4, code lost:
        if (r14 == false) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e6, code lost:
        r16 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e8, code lost:
        r18 = java.lang.Integer.valueOf(r16);
        r19 = java.lang.Long.valueOf(r4);
        r12 = r12 ? 1 : 0;
        r12 = r12 ? 1 : 0;
        r12 = r12 ? 1 : 0;
        r23 = java.lang.Integer.valueOf(r12);
        r25 = java.lang.Long.valueOf(r1);
        r26 = java.lang.Integer.valueOf(r17);
        r0 = r0.viewer.viewer_fb_id;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0100, code lost:
        if (r6 == null) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0102, code lost:
        r9 = r6.id;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0104, code lost:
        r8.addRow(new java.lang.Object[]{r11, r10, r3, r18, r19, r7, r15, r13, r23, r24, r25, r26, r27, r0, r9});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x011b, code lost:
        r4 = 0;
        r14 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0121, code lost:
        if (r4 != null) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0124, code lost:
        if (r11 == false) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0129, code lost:
        if (r6 != null) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0047, code lost:
        if (r10.fb_presence_sharing == false) goto L_0x0049;
     */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor getFbFriendPrimaryProfile(java.lang.String r31) {
        /*
        // Method dump skipped, instructions count: 328
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.friendlistcontentprovider.FriendListContentProvider.getFbFriendPrimaryProfile(java.lang.String):android.database.Cursor");
    }

    @Override // com.facebook.secure.content.FbPermissionsContentProvider
    public String getFbPermission() {
        return this.mFbPermissionName;
    }

    @VisibleForTesting
    public PartyController getPartyInterface() {
        return (PartyController) AnonymousClass0VF.A03(0, 22, this._UL_mInjectionContext);
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

    public static final void _UL_injectMe(Context context, FriendListContentProvider friendListContentProvider) {
        _UL_staticInjectMe((AnonymousClass0lg) AnonymousClass0VF.get(context), friendListContentProvider);
    }

    public static void addPartyUser(MatrixCursor matrixCursor, PartyInviteInfoResponse.Party party, SocialUser socialUser, int i) {
        matrixCursor.addRow(new Object[]{Integer.valueOf(i), party.id, socialUser.id, socialUser.alias, getSocialUserPhotoUrl(socialUser)});
    }

    @Nullable
    private Cursor getHasParticipantRecentlySpoken(String str) {
        boolean hasParticipantRecentlySpoken = getPartyInterface().getHasParticipantRecentlySpoken(Long.parseLong(str));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_has_participant_recently_spoken"}, 1);
        matrixCursor.addRow(new Object[]{Integer.valueOf(hasParticipantRecentlySpoken ? 1 : 0)});
        return matrixCursor;
    }

    @Nullable
    private Cursor getPartyChatVolume() {
        try {
            float partyChatVolumeX = getPartyInterface().getPartyChatVolumeX();
            MatrixCursor matrixCursor = new MatrixCursor(new String[]{"party_volume"}, 1);
            matrixCursor.addRow(new Object[]{Float.valueOf(partyChatVolumeX)});
            return matrixCursor;
        } catch (PlatformInitException e) {
            AnonymousClass0MD.A08(TAG, "Failed to get party volume", e);
            return null;
        }
    }

    @Nullable
    private Cursor getSocialActivity(String str, String str2) {
        SocialActivityResponse socialActivitySync = this.mSocialMethods.getSocialActivitySync(str, SocialActivityParams.Role.fromString(str2));
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"title", "subtitle", "image_uri", "package_name", "deeplink"}, 5);
        SocialActivityResponse.SocialActivity socialActivity = socialActivitySync.social_activity;
        matrixCursor.addRow(new Object[]{socialActivity.title, socialActivity.subtitle, socialActivity.image.uri, socialActivity.application.package_name, socialActivity.deeplink});
        return matrixCursor;
    }

    @Override // com.oculus.content.OculusFbPermissionsContentProvider, com.oculus.friendlistcontentprovider.OculusLoggedInUserFbPermissionsContentProvider
    public void doInitialization() {
        Context context = getContext();
        if (context != null) {
            this.mFbPermissionName = AnonymousClass006.A07(context.getPackageName(), ".fbpermission.FRIEND_LIST_CONTENT_PROVIDER");
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                this.mIsMicSwitchingEnabled = packageManager.hasSystemFeature("oculus.software.mic_sharing");
            }
        }
        super.doInitialization();
        _UL_injectMe(getContext(), this);
    }

    @Override // X.AnonymousClass0jF
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Throwable e;
        String str2;
        String str3;
        PartyMicrophoneChannel partyMicrophoneChannel;
        GenericErrorResponse.Error error;
        if (uri == null) {
            AnonymousClass0MD.A04(TAG, "Cannot resolve query with null URI");
            return 0;
        }
        if (uri.compareTo(OculusContent.FriendList.PARTY_INVITE_USERS_CONTENT_URI) == 0) {
            this.mSocialMethods.partyInviteUsersSync(contentValues.getAsString("party_id"), contentValues.getAsString("user_ids").split(":"));
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_LEAVE) == 0) {
            this.mSocialMethods.partyLeaveSync((String) contentValues.get("party_id"));
            getPartyInterface().stopPartyChat();
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_JOIN) == 0) {
            String str4 = (String) contentValues.get("party_id");
            try {
                this.mSocialMethods.partyJoinSync(str4, (String) contentValues.get("link_nonce"), (String) contentValues.get("application_id"));
                startPartyChat(str4);
                return 1;
            } catch (RetrofitError e2) {
                GenericErrorResponse genericErrorResponse = (GenericErrorResponse) e2.getBodyAs(GenericErrorResponse.class);
                if (genericErrorResponse != null && (error = genericErrorResponse.error) != null) {
                    return error.error_subcode;
                }
                throw e2;
            }
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_CANCEL_INVITE) == 0) {
            this.mSocialMethods.partyCancelInvite((String) contentValues.get("user_to_uninvite"), (String) contentValues.get("party_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.USER_BLOCK) == 0) {
            this.mSocialMethods.blockSync((String) contentValues.get("user_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.USER_UNBLOCK) == 0) {
            this.mSocialMethods.unblockSync((String) contentValues.get("blocked_user_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_ACCEPT_REQUEST) == 0) {
            this.mSocialMethods.acceptFriendRequestSync((String) contentValues.get("user_id"), FriendRequestSource.Origin.valueOf((String) contentValues.get("source")));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_SEND_REQUEST) == 0) {
            this.mSocialMethods.sendFriendRequestSync((String) contentValues.get("user_id"), FriendRequestSource.Origin.valueOf((String) contentValues.get("source")));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_REJECT_REQUEST) == 0) {
            this.mSocialMethods.rejectFriendRequestSync((String) contentValues.get("user_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_CANCEL_REQUEST) == 0) {
            this.mSocialMethods.cancelFriendRequestSync((String) contentValues.get("user_id"), FriendRequestSource.Origin.valueOf((String) contentValues.get("source")));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.FRIEND_REMOVE) == 0) {
            this.mSocialMethods.unfriendSync((String) contentValues.get("friend"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PYMK_HIDE_USER) == 0) {
            this.mSocialMethods.pymkHideUserSync((String) contentValues.get("user"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.MESSAGE_SEND_TO_THREAD) == 0) {
            this.mSocialMethods.messageSendToThreadSync((String) contentValues.get("thread_id"), (String) contentValues.get("body"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.SET_NUX_FLAG_FOR_USER) == 0) {
            this.mSocialMethods.setNuxFlagForUserSync((String) contentValues.get("nux_flag"), (String) contentValues.get("nux_value"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PROFILE_SET_BIO) == 0) {
            this.mSocialMethods.profileSetBioSync((String) contentValues.get("user_id"), (String) contentValues.get("biography"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_SET_TYPE) == 0) {
            this.mSocialMethods.partySetTypeSync((String) contentValues.get("party_id"), (String) contentValues.get("party_type"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_ACTIVATE_LINK_INVITE) == 0) {
            this.mSocialMethods.partyActivateLinkInviteSync((String) contentValues.get("party_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_DEACTIVATE_LINK_INVITE) == 0) {
            this.mSocialMethods.partyDeactivateLinkInviteSync((String) contentValues.get("party_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_KICK) == 0) {
            this.mSocialMethods.partyKickSync((String) contentValues.get("party_id"), new String[]{(String) contentValues.get("user_id")});
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE) == 0) {
            this.mSocialMethods.partyProposedGroupLaunchDestinationCreate((String) contentValues.get("destination_id"), (String) contentValues.get("party_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE) == 0) {
            this.mSocialMethods.partyProposedGroupLaunchDestinationRemove((String) contentValues.get("party_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.GROUP_LAUNCH_SET_STATE) == 0) {
            this.mSocialMethods.groupLaunchSetState((String) contentValues.get("group_launch_id"), (String) contentValues.get("group_launch_state"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.GROUP_LAUNCH_SET_USER_RESPONSE) == 0) {
            this.mSocialMethods.groupLaunchSetUserResponse((String) contentValues.get("response"), (String) contentValues.get("group_launch_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.GROUP_LAUNCH_LAUNCH_HANDLE) == 0) {
            this.mSocialMethods.groupLaunchLaunchHandle((String) contentValues.get("group_launch_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.GROUP_LAUNCH_SOLO_LAUNCH_HANDLE) == 0) {
            this.mSocialMethods.groupLaunchSoloLaunchHandle((String) contentValues.get("group_launch_id"));
            return 1;
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_SET_VOLUME) == 0) {
            try {
                getPartyInterface().setPartyChatVolumeX(((Number) contentValues.get("party_volume")).floatValue());
                return 1;
            } catch (PlatformInitException e3) {
                AnonymousClass0MD.A08(TAG, "Failed to set party volume", e3);
            }
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_SET_SELF_MUTE) == 0) {
            try {
                return getMicrophoneMuteStateFromPartyStateJson(getPartyInterface().setSystemVoipMicrophoneMutedX(((Number) contentValues.get("party_self_mute")).intValue()));
            } catch (PlatformInitException | IllegalArgumentException e4) {
                e = e4;
                str2 = TAG;
                str3 = "Failed to set local party mute";
                AnonymousClass0MD.A08(str2, str3, e);
                return 0;
            }
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_GET_SELF_MUTE) == 0) {
            try {
                return getMicrophoneMuteStateFromPartyStateJson(getPartyInterface().getSystemVoipStateX());
            } catch (PlatformInitException | IllegalArgumentException e5) {
                e = e5;
                str2 = TAG;
                str3 = "Failed to get local party mute";
                AnonymousClass0MD.A08(str2, str3, e);
                return 0;
            }
        } else if (uri.compareTo(OculusContent.FriendList.PARTY_SET_PER_PERSON_MUTE_STATUS) == 0) {
            try {
                return getPartyInterface().setPerPersonMuteAction(Long.parseLong((String) contentValues.get("user_id")), ((Integer) contentValues.get("party_per_person_mute_value")).intValue());
            } catch (PlatformInitException | ClassCastException | NumberFormatException e6) {
                e = e6;
                str2 = TAG;
                str3 = "Failed to set per person mute status";
                AnonymousClass0MD.A08(str2, str3, e);
                return 0;
            }
        } else {
            if (uri.compareTo(OculusContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_APP) == 0) {
                if (this.mIsMicSwitchingEnabled) {
                    getPartyInterface().setPartyIsUsingMicrophone(false);
                    this.mSocialMethods.partySetMicrophoneChannel(String.valueOf(getPartyInterface().getCurrentPartyId()), PartyMicrophoneChannel.APP);
                    return 1;
                }
            } else if (uri.compareTo(OculusContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_PARTY) != 0) {
                if (uri.compareTo(OculusContent.FriendList.PARTY_SET_MICROPHONE_STATE) == 0) {
                    if (this.mIsMicSwitchingEnabled) {
                        try {
                            String str5 = (String) contentValues.get("party_mic_state");
                            getPartyInterface().setPartyMicrophoneState(str5);
                            if (MicrophoneButtonState.PARTY.name().equals(str5)) {
                                partyMicrophoneChannel = PartyMicrophoneChannel.PARTY;
                            } else if (MicrophoneButtonState.MUTE.name().equals(str5)) {
                                partyMicrophoneChannel = PartyMicrophoneChannel.MUTE;
                            } else if (MicrophoneButtonState.APP.name().equals(str5)) {
                                partyMicrophoneChannel = PartyMicrophoneChannel.APP;
                            }
                            if (partyMicrophoneChannel != null) {
                                this.mSocialMethods.partySetMicrophoneChannel(String.valueOf(getPartyInterface().getCurrentPartyId()), partyMicrophoneChannel);
                                return 1;
                            }
                        } catch (PlatformInitException e7) {
                            AnonymousClass0MD.A08(TAG, "Failed to update microphone channel", e7);
                            return 0;
                        }
                    }
                }
                return 0;
            } else if (this.mIsMicSwitchingEnabled) {
                getPartyInterface().setPartyIsUsingMicrophone(true);
                this.mSocialMethods.partySetMicrophoneChannel(String.valueOf(getPartyInterface().getCurrentPartyId()), PartyMicrophoneChannel.PARTY);
                return 1;
            }
            AnonymousClass0MD.A05(TAG, "The current OS version does not support mic switching. no-op.");
            return 0;
        }
        return 1;
    }
}
