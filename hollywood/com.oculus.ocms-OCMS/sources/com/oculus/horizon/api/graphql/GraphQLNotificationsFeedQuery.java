package com.oculus.horizon.api.graphql;

public class GraphQLNotificationsFeedQuery {
    public static final String MARK_ALL_SEEN_MUTATION = "Mutation MarkSeenMutation  {  notifications_mark_all_seen(<input>) {    client_mutation_id  }}";
    public static final String MARK_NOTIFICATION_READ = "Mutation SeenStateMutation : NotificationPayload {  set_seen_state_notification(<input>) {    notification {id,uuid,is_read,notification_type,notification_preference_type,rendered_notification {title,message,extra_data,},seen_state,send_time,sender {alias,name,},    }  }}";
    public static final String NOTIFICATIONS_FEED_QUERY_V2 = "viewer() {  user {    notifications        .supported_by_caller(true)        .included_in_feed(true) {      nodes {id,uuid,is_read,notification_type,notification_preference_type,rendered_notification {title,message,extra_data,},seen_state,send_time,sender {alias,name,},      },    },    notification_opt_out_preferences_list {key,name,description,opt_out_value    },    dev_notif_opt_out_preferences_list {app_group_id,name,opt_out_value    },  },}";
    public static final String NOTIFICATIONS_FEED_UNSEEN_COUNT_QUERY = "viewer() {  user {    notifications        .application_platform(android)        .included_in_feed(true) {      unseen_count,    },  },}";
    private static final String NOTIFICATION_BUCKET_SETTINGS_QUERY = "key,name,description,opt_out_value";
    private static final String NOTIFICATION_DEV_SETTINGS_QUERY = "app_group_id,name,opt_out_value";
    private static final String NOTIFICATION_QUERY_V2 = "id,uuid,is_read,notification_type,notification_preference_type,rendered_notification {title,message,extra_data,},seen_state,send_time,sender {alias,name,},";
    private static final String RENDERED_NOTIFICATION_FIELDS = "title,message,extra_data,";
    private static final String SENDER_FIELDS = "alias,name,";
}
