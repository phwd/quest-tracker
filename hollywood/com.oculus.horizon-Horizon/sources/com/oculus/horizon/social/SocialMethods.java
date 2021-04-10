package com.oculus.horizon.social;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import com.facebook.ultralight.Dependencies;
import com.oculus.horizon.api.common.user.User;
import com.oculus.horizon.api.social.AppScoreboardsInfoResponse;
import com.oculus.horizon.api.social.AppsScoreboardsInfoResponse;
import com.oculus.horizon.api.social.FriendsResponse;
import com.oculus.horizon.api.social.GetPartyInfoWithMembersResponse;
import com.oculus.horizon.api.social.GroupLaunchAppDestinationsResponse;
import com.oculus.horizon.api.social.GroupLaunchSupportedApplicationsResponse;
import com.oculus.horizon.api.social.JoinablePartiesResponse;
import com.oculus.horizon.api.social.PYMKResponse;
import com.oculus.horizon.api.social.PartyInfoResponse;
import com.oculus.horizon.api.social.PartyInviteInfoResponse;
import com.oculus.horizon.api.social.PartyInvitesResponse;
import com.oculus.horizon.api.social.PartyPrivacyInfoResponse;
import com.oculus.horizon.api.social.PeopleNearbyResponse;
import com.oculus.horizon.api.social.SharePartyInfoResponse;
import com.oculus.horizon.api.social.SocialFriendRequestsResponse;
import com.oculus.horizon.api.social.SocialFriendsResponse;
import com.oculus.horizon.social.api.AchievementsResponse;
import com.oculus.horizon.social.api.AuiProfileContentResponse;
import com.oculus.horizon.social.api.BlockedUsersResponse;
import com.oculus.horizon.social.api.EntitlementsWithAchievementsResponse;
import com.oculus.horizon.social.api.FbFriendPrimaryProfileResponse;
import com.oculus.horizon.social.api.FbLinkedStatusResponse;
import com.oculus.horizon.social.api.FriendRemovedResponse;
import com.oculus.horizon.social.api.FriendRequestAcceptResponse;
import com.oculus.horizon.social.api.FriendRequestCancelResponse;
import com.oculus.horizon.social.api.FriendRequestRejectResponse;
import com.oculus.horizon.social.api.FriendRequestSendResponse;
import com.oculus.horizon.social.api.GetPartyInfoWithActivityResponse;
import com.oculus.horizon.social.api.GetQueuedRemoteLaunchesResponse;
import com.oculus.horizon.social.api.GroupLaunchSetStateResponse;
import com.oculus.horizon.social.api.GroupLaunchSetUserResponseResponse;
import com.oculus.horizon.social.api.HasSeenActivityPrivacyUpdateRoadblockResponse;
import com.oculus.horizon.social.api.HasSeenNuxResponse;
import com.oculus.horizon.social.api.MessageSendToThreadResponse;
import com.oculus.horizon.social.api.NotificationResponse;
import com.oculus.horizon.social.api.PYMKHideUserResponse;
import com.oculus.horizon.social.api.PartyCancelInviteResponse;
import com.oculus.horizon.social.api.PartyCreateResponse;
import com.oculus.horizon.social.api.PartyInvitableUsersResponse;
import com.oculus.horizon.social.api.PartyProposedGroupLaunchDestinationCreateResponse;
import com.oculus.horizon.social.api.PartyProposedGroupLaunchDestinationRemoveResponse;
import com.oculus.horizon.social.api.SetNuxFlagForUserResponse;
import com.oculus.horizon.social.api.SetPrivacyResponse;
import com.oculus.horizon.social.api.SocialActivityResponse;
import com.oculus.horizon.social.api.SocialViewerInfoResponse;
import com.oculus.horizon.social.api.UserBlockedResponse;
import com.oculus.horizon.social.api.UserGetCurrentPartyResponse;
import com.oculus.horizon.social.api.UserSearchResponse;
import com.oculus.horizon.social.api.UserUnblockedResponse;
import com.oculus.horizon.social.api.VRProfileContentResponse;
import com.oculus.horizon.social.request.AchievementsParams;
import com.oculus.horizon.social.request.ActivityPrivacyUpdateRoadblockMutationParams;
import com.oculus.horizon.social.request.ApplicationListParams;
import com.oculus.horizon.social.request.ApplicationParams;
import com.oculus.horizon.social.request.BiographySetParams;
import com.oculus.horizon.social.request.ChatMutationParams;
import com.oculus.horizon.social.request.DeeplinkNonceParam;
import com.oculus.horizon.social.request.FbFriendPrimaryProfileParams;
import com.oculus.horizon.social.request.FriendListParams;
import com.oculus.horizon.social.request.GameInviteParams;
import com.oculus.horizon.social.request.GetQueuedRemoteLaunchesParams;
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
import com.oculus.horizon.social.request.SetPrivacyParams;
import com.oculus.horizon.social.request.SingleIDParam;
import com.oculus.horizon.social.request.SingleUserParams;
import com.oculus.horizon.social.request.SocialActivityParams;
import com.oculus.horizon.social.request.SocialNuxMutationParams;
import com.oculus.horizon.social.request.UserMutationParams;
import com.oculus.horizon.social.request.UserSearchParams;
import com.oculus.http.common.graphql.GraphQLQueryConstants;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.common.DsatAuthorization;
import javax.inject.Inject;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

@Dependencies({"_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class SocialMethods {
    public AnonymousClass0QC _UL_mInjectionContext;
    public final Methods mMethods;

    public interface Methods {
        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void acceptFriendRequest(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2, Callback<FriendRequestAcceptResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FriendRequestAcceptResponse acceptFriendRequestSync(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void block(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2, Callback<UserBlockedResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        UserBlockedResponse blockSync(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void cancelFriendRequest(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2, Callback<FriendRequestCancelResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FriendRequestCancelResponse cancelFriendRequestSync(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void checkUserHasSeenActivityPrivacyUpdateRoadblock(@Query("q") String str, Callback<HasSeenActivityPrivacyUpdateRoadblockResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void checkUserHasSeenSocialNux(@Query("q") String str, Callback<HasSeenNuxResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getAchievements(@Query("q") String str, @Query("query_params") AchievementsParams achievementsParams, Callback<AchievementsResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getAllGameInvites(@Query("q") String str, @Query("query_params") GameInviteParams gameInviteParams, Callback<NotificationResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getAllPartyInvites(@Query("q") String str, Callback<NotificationResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        AppScoreboardsInfoResponse getAppScoreboardsInfoSync(@Query("doc") String str, @Query("variables") ApplicationParams applicationParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        AppsScoreboardsInfoResponse getAppsScoreboardsInfoSync(@Query("doc") String str, @Query("variables") ApplicationListParams applicationListParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        AuiProfileContentResponse getAuiProfileContentSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getBlockedUsers(@Query("q") String str, Callback<BlockedUsersResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        BlockedUsersResponse getBlockedUsersSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getEntitlementsWithAchievements(@Query("q") String str, @Query("query_params") SingleUserParams singleUserParams, Callback<EntitlementsWithAchievementsResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FbFriendPrimaryProfileResponse getFbFriendPrimaryProfile(@Query("doc") String str, @Query("variables") FbFriendPrimaryProfileParams fbFriendPrimaryProfileParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FbLinkedStatusResponse getFbLinkedStatusSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getFriendRequests(@Query("q") String str, Callback<SocialFriendRequestsResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        SocialFriendRequestsResponse getFriendRequestsSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getFriends(@Query("q") String str, @Query("query_params") SingleUserParams singleUserParams, Callback<FriendsResponse> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GroupLaunchAppDestinationsResponse getGroupLaunchAppDestinationsSync(@Query("doc") String str, @Query("variables") ApplicationParams applicationParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GroupLaunchSupportedApplicationsResponse getGroupLaunchSupportedApplicationsSync(@Query("doc") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        JoinablePartiesResponse getJoinablePartiesSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PYMKResponse getPYMKsSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyInfoResponse getPartyInfoSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GetPartyInfoWithActivityResponse getPartyInfoWithActivitySync(@Query("q") String str, @Query("query_params") PartyIDParam partyIDParam);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GetPartyInfoWithMembersResponse getPartyInfoWithMembersSync(@Query("q") String str, @Query("query_params") PartyIDParam partyIDParam);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyInvitableUsersResponse getPartyInvitableUsersSync(@Query("q") String str, @Query("query_params") SingleUserParams singleUserParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyInviteInfoResponse getPartyInviteInfoSync(@Query("doc") String str, @Query("variables") PartyIDParam partyIDParam);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyInviteInfoResponse getPartyInviteInfoWithNonceSync(@Query("doc") String str, @Query("variables") DeeplinkNonceParam deeplinkNonceParam);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyInvitesResponse getPartyInvitesSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyPrivacyInfoResponse getPartyPrivacyInfoSync(@Query("doc") String str, @Query("variables") PartyIDParam partyIDParam);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PeopleNearbyResponse getPeopleNearbySync(@Query("q") String str, @Query("query_params") PeopleNearbyParams peopleNearbyParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void getProfile(@Query("q") String str, @Query("query_params") SingleUserParams singleUserParams, Callback<User> callback);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GetQueuedRemoteLaunchesResponse getQueuedRemoteLaunchesSync(@Query("doc") String str, @Query("variables") GetQueuedRemoteLaunchesParams getQueuedRemoteLaunchesParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        SharePartyInfoResponse getSharePartyInfo(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        SocialActivityResponse getSocialActivity(@Query("doc") String str, @Query("variables") SocialActivityParams socialActivityParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        SocialFriendsResponse getSocialFriendsSync(@Query("doc") String str, @Query("variables") FriendListParams friendListParams);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        SocialViewerInfoResponse getSocialViewerInfoSync(@Query("q") String str);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        VRProfileContentResponse getVRProfileContentSync(@Query("doc") String str, @Query("variables") SingleIDParam singleIDParam);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Void groupLaunchLaunchHandle(@Query("q") String str, @Query("query_params") GroupLaunchLaunchHandleParams groupLaunchLaunchHandleParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GroupLaunchSetStateResponse groupLaunchSetState(@Query("q") String str, @Query("query_params") GroupLaunchSetStateParams groupLaunchSetStateParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        GroupLaunchSetUserResponseResponse groupLaunchSetUserResponse(@Query("q") String str, @Query("query_params") GroupLaunchSetUserResponseParams groupLaunchSetUserResponseParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        MessageSendToThreadResponse messageSendToThreadSync(@Query("q") String str, @Query("query_params") ChatMutationParams chatMutationParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Void partyActivateLinkInvite(@Query("q") String str, @Query("query_params") PartyIDMutationParam partyIDMutationParam, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyCancelInviteResponse partyCancelInvite(@Query("q") String str, @Query("query_params") PartyCancelInviteParams partyCancelInviteParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        @Headers({DsatAuthorization.ENABLE})
        PartyCreateResponse partyCreate(@Query("q") String str, @Query("query_params") PartyCreateParams partyCreateParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Void partyDeactivateLinkInvite(@Query("q") String str, @Query("query_params") PartyIDMutationParam partyIDMutationParam, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Void partyInviteUsers(@Query("q") String str, @Query("query_params") PartyInviteUsersParams partyInviteUsersParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        @Headers({DsatAuthorization.ENABLE})
        Void partyJoin(@Query("q") String str, @Query("query_params") PartyJoinParams partyJoinParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        @Headers({DsatAuthorization.ENABLE})
        void partyJoinWithCallback(@Query("q") String str, @Query("query_params") PartyJoinParams partyJoinParams, @Body String str2, Callback<Void> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Void partyKick(@Query("q") String str, @Query("query_params") PartyKickParams partyKickParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Void partyLeave(@Query("q") String str, @Query("query_params") PartyLeaveParams partyLeaveParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyProposedGroupLaunchDestinationCreateResponse partyProposedGroupLaunchDestinationCreate(@Query("q") String str, @Query("query_params") PartyProposedGroupLaunchDestinationCreateParams partyProposedGroupLaunchDestinationCreateParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PartyProposedGroupLaunchDestinationRemoveResponse partyProposedGroupLaunchDestinationRemove(@Query("q") String str, @Query("query_params") PartyProposedGroupLaunchDestinationRemoveParams partyProposedGroupLaunchDestinationRemoveParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Void partySetType(@Query("q") String str, @Query("query_params") PartySetTypeParams partySetTypeParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        Void profileSetBioSync(@Query("q") String str, @Query("query_params") BiographySetParams biographySetParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        PYMKHideUserResponse pymkHideUserSync(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void rejectFriendRequest(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2, Callback<FriendRequestRejectResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FriendRequestRejectResponse rejectFriendRequestSync(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void resetActivityPrivacyUpdateRoadblock(@Query("q") String str, @Query("query_params") ActivityPrivacyUpdateRoadblockMutationParams activityPrivacyUpdateRoadblockMutationParams, @Body String str2, Callback<Void> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void resetSocialNux(@Query("q") String str, @Query("query_params") SocialNuxMutationParams socialNuxMutationParams, @Body String str2, Callback<Void> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void sendFriendRequest(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2, Callback<FriendRequestSendResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FriendRequestSendResponse sendFriendRequestSync(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        SetNuxFlagForUserResponse setNuxFlagForUserSync(@Query("q") String str, @Query("query_params") NuxFlagForUserMutationParams nuxFlagForUserMutationParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void setPrivacy(@Query("q") String str, @Query("query_params") SetPrivacyParams setPrivacyParams, @Body String str2, Callback<SetPrivacyResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void setSeenActivityPrivacyUpdateRoadblock(@Query("q") String str, @Query("query_params") ActivityPrivacyUpdateRoadblockMutationParams activityPrivacyUpdateRoadblockMutationParams, @Body String str2, Callback<Void> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void setSeenSocialNux(@Query("q") String str, @Query("query_params") SocialNuxMutationParams socialNuxMutationParams, @Body String str2, Callback<Void> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void unblock(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2, Callback<UserUnblockedResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        UserUnblockedResponse unblockSync(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void unfriend(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2, Callback<FriendRemovedResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        FriendRemovedResponse unfriendSync(@Query("q") String str, @Query("query_params") UserMutationParams userMutationParams, @Body String str2);

        @GET(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void userGetCurrentParty(@Query("q") String str, Callback<UserGetCurrentPartyResponse> callback);

        @POST(GraphQLQueryConstants.GRAPHQL_ENDPOINT)
        void userSearch(@Query("q") String str, @Query("query_params") UserSearchParams userSearchParams, @Body String str2, Callback<UserSearchResponse> callback);
    }

    @Inject
    public SocialMethods(AbstractC06640p5 r3, @OculusRestAdapter RestAdapter restAdapter) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mMethods = (Methods) restAdapter.create(Methods.class);
    }
}
