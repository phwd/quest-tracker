package com.oculus.horizon.app;

import X.AnonymousClass006;
import X.AnonymousClass04R;
import X.AnonymousClass0J2;
import X.AnonymousClass0Q3;
import X.AnonymousClass0QA;
import X.AnonymousClass117;
import X.C01160Kw;
import X.C03030bw;
import android.app.Application;
import android.net.Uri;
import com.facebook.inject.rootmodule.defaultmodule.___DEFAULT___ProcessRootModule;
import com.facebook.internal.NativeProtocol;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.oculus.base.app.ApplicationLike;
import com.oculus.common.init.AppInitLock;
import com.oculus.horizon.foreground.ApplicationForegroundTracker;
import com.oculus.horizon.logging.HorizonForegroundListener;
import com.oculus.provider.OculusContent;

public class MainProcessApplicationLike implements ApplicationLike, AnonymousClass0Q3, AnonymousClass0QA {
    @Inject
    @Eager
    public AppInitLock mAppInitLock;
    public Application mApplication;
    @Inject
    @Eager
    public ApplicationForegroundTracker mApplicationForegroundTracker;
    @Inject
    @Eager
    public ApplicationLike mApplicationLike;
    @Inject
    @Eager
    public HorizonForegroundListener mHorizonForegroundListener;
    public AnonymousClass0J2 mInjector;

    @Override // X.AnonymousClass0Q3
    public final synchronized AnonymousClass0J2 A3X() {
        AnonymousClass0J2 r0;
        while (true) {
            try {
                r0 = this.mInjector;
                if (r0 == null) {
                    wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return r0;
    }

    @Override // com.oculus.base.app.ApplicationLike
    public final void A5v(Application application) {
        this.mApplication = application;
        synchronized (this) {
            Application application2 = this.mApplication;
            C01160Kw.A00();
            ImmutableList A07 = ImmutableList.A07(new ___DEFAULT___ProcessRootModule());
            AnonymousClass0J2.A00 = application2.getApplicationContext();
            this.mInjector = new AnonymousClass04R(application2, A07);
            notifyAll();
        }
        AnonymousClass0J2 r1 = AnonymousClass0J2.get(this.mApplication.getApplicationContext());
        this.mApplicationLike = (ApplicationLike) AnonymousClass117.A00(292, r1);
        this.mAppInitLock = (AppInitLock) AnonymousClass117.A00(139, r1);
        this.mApplicationForegroundTracker = (ApplicationForegroundTracker) AnonymousClass117.A00(512, r1);
        this.mHorizonForegroundListener = (HorizonForegroundListener) AnonymousClass117.A00(76, r1);
        this.mApplicationLike.A5v(this.mApplication);
        ApplicationForegroundTracker applicationForegroundTracker = this.mApplicationForegroundTracker;
        applicationForegroundTracker.mListeners.add(this.mHorizonForegroundListener);
        this.mApplication.registerActivityLifecycleCallbacks(this.mApplicationForegroundTracker);
        String A05 = AnonymousClass006.A05("com.oculus.horizon", ".friendlist");
        OculusContent.FriendList.AUTHORITY = A05;
        OculusContent.FriendList.GET_FRIENDS_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, A05, "/get_friends"));
        OculusContent.FriendList.GET_PARTY_INVITES = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_party_invites"));
        OculusContent.FriendList.GET_PARTY_INVITE_INFO = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_party_invite_info"));
        OculusContent.FriendList.GET_PARTY_PRIVACY_INFO = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_party_privacy_info"));
        OculusContent.FriendList.GET_PARTY_INVITE_INFO_WITH_NONCE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_party_invite_info_with_nonce"));
        OculusContent.FriendList.PARTY_GET_JOINABLE_PARTIES_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_get_joinable"));
        OculusContent.FriendList.GET_PARTY_FBID = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_party_fbid"));
        OculusContent.FriendList.GET_GROUP_LAUNCH_SUPPORTED_APPLICATIONS_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_group_launch_supported_applications"));
        OculusContent.FriendList.GET_GROUP_LAUNCH_APPLICATION_DESTINATIONS_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_group_launch_application_destinations"));
        OculusContent.FriendList.GET_APP_SCOREBOARDS_INFO_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_app_scoreboards_info"));
        OculusContent.FriendList.GET_APPS_SCOREBOARDS_INFO_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_apps_scoreboards_info"));
        OculusContent.FriendList.PARTY_GET_CURRENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_get_current"));
        OculusContent.FriendList.PARTY_GET_CURRENT_URI_V2 = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_get_current_v2"));
        OculusContent.FriendList.PEOPLE_NEARBY_CONTENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/people_nearby"));
        OculusContent.FriendList.PYMK_CONTENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/pymks"));
        OculusContent.FriendList.PYMK_HIDE_USER = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/pymk_hide_user"));
        OculusContent.FriendList.MESSAGE_SEND_TO_THREAD = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/message_send_to_thread"));
        OculusContent.FriendList.FRIEND_REQUESTS_CONTENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/friend_requests"));
        OculusContent.FriendList.FRIEND_LIST_CONTENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_invitable"));
        OculusContent.FriendList.USER_BLOCK = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/user_block"));
        OculusContent.FriendList.USER_UNBLOCK = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/user_unblock"));
        OculusContent.FriendList.FRIEND_SEND_REQUEST = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/friend_request_send"));
        OculusContent.FriendList.FRIEND_ACCEPT_REQUEST = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/friend_request_accept"));
        OculusContent.FriendList.FRIEND_REJECT_REQUEST = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/friend_reject_request"));
        OculusContent.FriendList.FRIEND_CANCEL_REQUEST = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/friend_request_cancel"));
        OculusContent.FriendList.FRIEND_REMOVE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/friend_remove"));
        OculusContent.FriendList.GET_SOCIAL_ACTIVITY = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/social_activity"));
        OculusContent.FriendList.PARTY_INVITE_USERS_CONTENT_URI = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_invite_users"));
        OculusContent.FriendList.PARTY_CREATE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_create"));
        OculusContent.FriendList.PARTY_JOIN = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_join"));
        OculusContent.FriendList.PARTY_JOIN_WITH_ERROR_CALLBACK = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_join_with_error_callback"));
        OculusContent.FriendList.PARTY_LEAVE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_leave"));
        OculusContent.FriendList.PARTY_CANCEL_INVITE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_cancel_invite"));
        OculusContent.FriendList.PARTY_SET_TYPE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_set_type"));
        OculusContent.FriendList.PARTY_ACTIVATE_LINK_INVITE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_activate_link_invite"));
        OculusContent.FriendList.PARTY_DEACTIVATE_LINK_INVITE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_deactivate_link_invite"));
        OculusContent.FriendList.PARTY_KICK = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_kick"));
        OculusContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_CREATE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_proposed_group_launch_destination_create"));
        OculusContent.FriendList.PARTY_PROPOSED_GROUP_LAUNCH_DESTINATION_REMOVE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_proposed_group_launch_destination_remove"));
        OculusContent.FriendList.PARTY_SET_VOLUME = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_set_volume"));
        OculusContent.FriendList.PARTY_GET_VOLUME = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_get_volume"));
        OculusContent.FriendList.PARTY_SET_SELF_MUTE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_set_self_mute"));
        OculusContent.FriendList.PARTY_GET_SELF_MUTE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_get_self_mute"));
        OculusContent.FriendList.PARTY_SET_PER_PERSON_MUTE_STATUS = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_set_per_person_mute_status"));
        OculusContent.FriendList.PARTY_GET_INFO_WITH_ACTIVITY = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_get_info_with_activity"));
        OculusContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_APP = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_send_microphone_input_to_app"));
        OculusContent.FriendList.PARTY_SEND_MICROPHONE_INPUT_TO_PARTY = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_send_microphone_input_to_party"));
        OculusContent.FriendList.PARTY_GET_MICROPHONE_INPUT_LOCATION = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_get_microphone_input_location"));
        OculusContent.FriendList.GET_LINKED_ACCOUNTS_INFO = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_linked_accounts_info"));
        OculusContent.FriendList.GET_PARTY_HAS_PARTICIPANT_RECENTLY_SPOKEN = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_party_has_participant_recently_spoken"));
        OculusContent.FriendList.PARTY_GET_INFO_WITH_MEMBERS = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/party_get_info_with_members"));
        OculusContent.FriendList.GROUP_LAUNCH_SET_USER_RESPONSE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/group_launch_user_response"));
        OculusContent.FriendList.GROUP_LAUNCH_LAUNCH_HANDLE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/group_launch_launch_handle"));
        OculusContent.FriendList.GROUP_LAUNCH_SOLO_LAUNCH_HANDLE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/group_launch_solo_launch_handle"));
        OculusContent.FriendList.GET_PROFILE_CONTENT = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_profile_content"));
        OculusContent.FriendList.PROFILE_SET_BIO = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/profile_set_bio"));
        OculusContent.FriendList.GET_FB_FRIEND_PRIMARY_PROFILE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_fb_friend_primary_profile"));
        OculusContent.FriendList.GROUP_LAUNCH_SET_STATE = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/group_launch_set_state"));
        OculusContent.FriendList.GET_SOCIAL_VIEWER = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_social_viewer"));
        OculusContent.FriendList.GET_VR_PROFILE_CONTENT = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_vr_profile_content"));
        OculusContent.FriendList.USER_SEARCH = C03030bw.A00(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/user_search"));
        OculusContent.FriendList.GET_BLOCKED_USER_ID = Uri.parse(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/get_blocked_id"));
        OculusContent.FriendList.SET_NUX_FLAG_FOR_USER = C03030bw.A00(AnonymousClass006.A07(NativeProtocol.CONTENT_SCHEME, OculusContent.FriendList.AUTHORITY, "/set_nux_flag_for_user"));
        this.mAppInitLock.A00();
    }
}
