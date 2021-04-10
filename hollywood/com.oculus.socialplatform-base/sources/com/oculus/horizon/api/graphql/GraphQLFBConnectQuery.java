package com.oculus.horizon.api.graphql;

public class GraphQLFBConnectQuery {
    public static final String CORE_FB_ACCOUNT = "  facebook_account {    facebook_id,    friend_policy,    needs_password_to_unlink,  },";
    public static final String CORE_FB_ACCOUNT_WITH_TOKEN = "  facebook_account {    access_token,    facebook_id,    friend_policy,    needs_password_to_unlink,  },";
    public static final String DISCONNECT_LINKED_FB_ACCOUNT = "Mutation FBDisconnect : FacebookDisconnectResponsePayload {  facebook_disconnect(<input>) {    client_mutation_id  }}";
    public static final String FACEBOOK_IMPORT_PROFILE_PICTURE = "Mutation FBImportProfilePicture : FacebookImportProfilePictureResponsePayload {  facebook_import_profile_picture(<input>) {    client_mutation_id  }}";
    public static final String FACEBOOK_PROXY_INSTALL = "Mutation FBProxyInstall : FacebookProxyInstallResponsePayload {  facebook_proxy_install(<input>) {  facebook_account {    access_token,    facebook_id,    friend_policy,    needs_password_to_unlink,  },  }}";
    public static final String FACEBOOK_REAUTH = "Mutation FacebookReauth : FacebookReauthResponsePayload {  facebook_reauth(<input>) {  facebook_account {    facebook_id,    friend_policy,    needs_password_to_unlink,  },  }}";
    public static final String FACEBOOK_SYNC_FRIENDS = "Mutation FacebookSyncFriends : FacebookSyncFriendsResponsePayload {  facebook_sync_friends(<input>) {  facebook_account {    facebook_id,    friend_policy,    needs_password_to_unlink,  },  }}";
    public static final String FACEBOOK_UNSYNC_FRIENDS = "Mutation FacebookUnsyncFriends : FacebookUnsyncFriendsResponsePayload {  facebook_unsync_friends(<input>) {  facebook_account {    facebook_id,    friend_policy,    needs_password_to_unlink,  },  }}";
    public static final String FB_TOKEN_GENERATE = "Mutation FBTokenGenerate : FBTokenGenerateResponsePayload {  facebook_access_token_generate(<input>) {    access_token,     fb_user_id,     fb_app_id  }}";
    public static final String FETCH_FB_CONNECT_ACCOUNT = "viewer() {  linked_accounts_info {  facebook_account {    facebook_id,    friend_policy,    needs_password_to_unlink,  },  },}";
    public static final String GET_CONNECTED_FACEBOOK_FRIENDS = "viewer() {  linked_accounts_info {    facebook_friends_connected.access_token(<facebook_access_token>).first(<first>) {      count,      nodes {        profile_photo_uri      }    }  }}";
    public static final String LINKED_FB_CONNECT_ACCOUNT = "  linked_accounts_info {  facebook_account {    facebook_id,    friend_policy,    needs_password_to_unlink,  },  },";
    public static final String REGISTER_FB_CONNECT_ACCOUNT = "Mutation FBConnectSetToken : FacebookConnectResponsePayload {  facebook_connect(<input>) {  facebook_account {    access_token,    facebook_id,    friend_policy,    needs_password_to_unlink,  },  }}";
}
