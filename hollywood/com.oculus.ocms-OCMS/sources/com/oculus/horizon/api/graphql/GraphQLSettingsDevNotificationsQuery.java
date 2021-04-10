package com.oculus.horizon.api.graphql;

public class GraphQLSettingsDevNotificationsQuery {
    public static final String CHANGE_DEV_NOTIFICATIONS_PREFERENCES = "Mutation ChangeDevNotificationsPreferences : DevNotifPreferenceChangeResponsePayload {  dev_notif_preference_change(<input>) {user {  dev_notif_opt_out_preferences_list {    app_group_id,    name,    opt_out_value,  },},  }}";
    private static final String USER_DEV_NOTIFICATIONS_OPT_OUT_PREFERENCES = "user {  dev_notif_opt_out_preferences_list {    app_group_id,    name,    opt_out_value,  },},";
    public static final String USER_DEV_NOTIFICATIONS_PREFERENCES_QUERY = "viewer() {user {  dev_notif_opt_out_preferences_list {    app_group_id,    name,    opt_out_value,  },},}";
}
