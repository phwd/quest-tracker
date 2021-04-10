package com.oculus.horizon.api.graphql;

public class GraphQLSettingsNotificationsQuery {
    public static final String CHANGE_NOTIFICATIONS_PREFERENCES = "Mutation ChangeNotificationsPreferences : NotificationPreferenceChangeResponsePayload {  notification_preference_change(<input>) {user {  notification_opt_out_preferences_list {    key,    name,    description,    opt_out_value,  },},  }}";
    public static final String CHANGE_NOTIFICATIONS_PREFERENCES_BY_NOTIF_ID = "Mutation ChangeNotificationsPreferences : NotificationPreferenceChangeResponsePayload {  user_notif_preference_change(<input>) {user {  notification_opt_out_preferences_list {    key,    name,    description,    opt_out_value,  },},  }}";
    private static final String USER_NOTIFICATIONS_OPT_OUT_PREFERENCES = "user {  notification_opt_out_preferences_list {    key,    name,    description,    opt_out_value,  },},";
    public static final String USER_NOTIFICATIONS_PREFERENCES_QUERY = "viewer() {user {  notification_opt_out_preferences_list {    key,    name,    description,    opt_out_value,  },},}";
}
