package com.facebook.acra;

import androidx.core.app.NotificationCompat$MessagingStyle;
import androidx.core.app.NotificationCompat$WearableExtender;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.oculus.userserver.managerservice.IOculusUserManager;
import com.squareup.okhttp.internal.framed.Hpack;

public class ActionId {
    public static final short ABORTED = 105;
    public static final short ACTION_BAR_COMPLETE = 104;
    public static final short ACTIVITY_CREATED = 7;
    public static final short ACTIVITY_LAUNCHED = 74;
    public static final short ACTIVITY_ON_CREATE = 80;
    public static final short ACTIVITY_PAUSED = 75;
    public static final short ACTIVITY_RESUME = 143;
    public static final short ACTIVITY_RESUMED = 77;
    public static final short ACTIVITY_START = 144;
    public static final short ACTIVITY_STARTED = 76;
    public static final short ANIMATION_END = 93;
    public static final short APPLY_FINISHED_LIST = 153;
    public static final short APPLY_FINISHED_LIST_AGAIN = 154;
    public static final short APPLY_OPTIMISTICS = 152;
    public static final short APP_DID_BECOME_ACTIVE = 131;
    public static final short APP_DID_ENTER_BACKGROUND = 133;
    public static final short APP_DID_FINISH_LAUNCHING = 130;
    public static final short APP_FIRST_VIEW_CONTROLLER = 139;
    public static final short APP_MAIN = 134;
    public static final short APP_WILL_ENTER_FOREGROUND = 132;
    public static final short ASNYC_FAILED = 161;
    public static final short ASYNC_ACTION_FAIL = 168;
    public static final short ASYNC_ACTION_SUCCESS = 167;
    public static final short ASYNC_BEGIN = 37;
    public static final short ASYNC_END = 38;
    public static final short ASYNC_FAIL = 162;
    public static final short BEGIN_START_ACTIVITY = 145;
    public static final short BROADCAST_DONE = 41;
    public static final short CACHE_FETCH = 34;
    public static final short CACHE_UPDATED = 29;
    public static final short CANCEL = 4;
    public static final short CARD_DATA_LOADED = 122;
    public static final short COMPONENTS_DATA_SOURCE_DID_END_UPDATES = 126;
    public static final short COMPONENTS_DATA_SOURCE_WILL_BEGIN_UPDATES = 125;
    public static final short CONNECTIVITY_CHANGED = 169;
    public static final short CONSISTENCY_MODEL_UPDATER = 8;
    public static final short CONSISTENCY_UPDATE = 19;
    public static final short CONTROLLER_INITIATED = 114;
    public static final short COUNTER = 61;
    public static final short DATA_EMPTY = 33;
    public static final short DATA_LOAD_START = 102;
    public static final short DATA_RECEIVED = 31;
    public static final short DB_FETCH = 23;
    public static final short DB_UPDATED = 30;
    public static final short DEQUEUE = 15;
    public static final short DISK_CACHE_VISIT = 18;
    public static final short DISPLAYED = 165;
    public static final short DISPLAYED_ON_SCREEN = 166;
    public static final short DRAW_COMPLETE = 5;
    public static final short DRAW_VIEW = 32;
    public static final short EDGE_PROCESSING_BEGIN = 99;
    public static final short END_START_ACTIVITY = 146;
    public static final short ERROR = 87;
    public static final short FAIL = 3;
    public static final short FAIL_FILE_TOO_LARGE = 159;
    public static final short FILE_SYSTEM_FAIL = 147;
    public static final short FINALLY = 89;
    public static final short FORMAT_ERROR = 148;
    public static final short FRAGMENT_CREATED = 78;
    public static final short FRAGMENT_INSTANCE_CREATED = 85;
    public static final short FRAGMENT_NEW_INSTANCE = 83;
    public static final short FRAGMENT_ON_CREATE = 82;
    public static final short FRAGMENT_RESUMED = 79;
    public static final short FUTURE_LISTENERS_COMPLETE = 155;
    public static final short HEADER_DATA_LOADED = 121;
    public static final short INIT = 50;
    public static final short INTENT_MAPPED = 73;
    public static final short INTENT_MAPPING_BEGIN = 81;
    public static final short INTERACTION_LOAD_EVENT_PERMALINK = 63;
    public static final short INTERACTION_LOAD_GROUPS_FEED = 64;
    public static final short INTERACTION_LOAD_PAGE_HEADER = 65;
    public static final short INTERACTION_LOAD_PAGE_HEADER_ADMIN = 66;
    public static final short INTERACTION_LOAD_PERMALINK = 67;
    public static final short INTERACTION_LOAD_TIMELINE_HEADER = 62;
    public static final short INTERACTION_LOAD_WEB_VIEW = 72;
    public static final short INTERACTION_OPEN_CHECK_IN = 71;
    public static final short INTERACTION_OPEN_COMPOSER = 68;
    public static final short INTERACTION_OPEN_MEDIA_PICKER = 69;
    public static final short INTERACTION_OPEN_PHOTO_GALLERY = 70;
    public static final short INTERRUPTED = 96;
    public static final short IN_PROGRESS = 49;
    public static final short LEGACY_MARKER = 103;
    public static final short LOAD_VIEW_BEGIN = 127;
    public static final short MAIN_COMPLETE = 95;
    public static final short MARKER_SWAPPED = 84;
    public static final short MEDIA_PREVIEW_VISIBLE = 60;
    public static final short MEMORY_CACHE_VISIT = 17;
    public static final short MESSAGE_UPDATE_END = 58;
    public static final short MESSAGE_UPDATE_START = 57;
    public static final short MESSENGER_DELTA_REQUEST = 138;
    public static final short MESSENGER_QUEUE_CREATION = 129;
    public static final short MESSENGER_THREAD_LIST_DISPLAYED = 141;
    public static final short MESSENGER_THREAD_LIST_LOADED = 140;
    public static final short METHOD_INVOKE = 88;
    public static final short MINIPREVIEW_COMPLETE = 91;
    public static final short MISSED_EVENT = 112;
    public static final short MQTT_CONNECTED = 136;
    public static final short MQTT_CONNECTING = 135;
    public static final short MQTT_DISCONNECTED = 137;
    public static final short NETWORK_COMPLETE = 16;
    public static final short NETWORK_FAILED = 97;
    public static final short NETWORK_RESPONSE = 98;
    public static final short NETWORK_RESPONSE_INITIAL_SCAN = 150;
    public static final short NEWSFEED_PROCESS_RESPONSE = 100;
    public static final short NEW_START_FOUND = 111;
    public static final short NOTIFY_SUBSCRIBERS = 158;
    public static final short OFFLINE = 160;
    public static final short ON_ACTIVITY_CREATED_END = 46;
    public static final short ON_ATTACH_END = 43;
    public static final short ON_ATTACH_FRAGMENT = 163;
    public static final short ON_CREATE_VIEW_END = 45;
    public static final short ON_FRAGMENT_CREATE_END = 44;
    public static final short ON_RESUME = 6;
    public static final short ON_RESUME_END = 42;
    public static final short ON_START_END = 47;
    public static final short ON_VIEW_CREATED_END = 101;
    public static final short PHASE_ONE = 13;
    public static final short PHASE_TWO = 14;
    public static final short PHOTO_CAPTURED = 59;
    public static final short PHOTO_DOWNLOAD_COMPLETE = 90;
    public static final short PHOTO_UPLOAD_COMPLETE = 21;
    public static final short POPULATE_CONSISTENCY_MEMORY_CACHE = 151;
    public static final short PREPARE_BEGIN = 35;
    public static final short PREPARE_END = 36;
    public static final short PREV_ACTIVITY_PAUSE = 142;
    public static final short PREV_ACTIVITY_PAUSED = 86;
    public static final short PRIVACY_VIOLATION = 149;
    public static final short QUERY_READY = 106;
    public static final short QUEUED = 48;
    public static final short QUEUEING_BEGIN = 54;
    public static final short QUEUEING_FAIL = 56;
    public static final short QUEUEING_SUCCESS = 55;
    public static final short REMOVE_BEGIN = 39;
    public static final short REMOVE_END = 40;
    public static final short REQUESTED_PLAYING = 108;
    public static final short RETRY_AFTER_FAILURE = 52;
    public static final short RETRY_AFTER_RECONNECT = 53;
    public static final short RETURN_TO_CALLER = 20;
    public static final short RTMP_CONNECTION_CONNECTED = 117;
    public static final short RTMP_CONNECTION_FAILED = 118;
    public static final short RTMP_CONNECTION_INTERCEPTED = 119;
    public static final short RTMP_CONNECTION_RELEASE = 110;
    public static final short RTMP_CONNECTION_REQUESTED = 109;
    public static final short RTMP_FIRST_KEY_FRAME_RECEIVED = 128;
    public static final short RTMP_PACKET_RECEIVED = 107;
    public static final short RTMP_STREAM_PREPARED = 115;
    public static final short SEARCH_TYPEAHEAD = 92;
    public static final short SEND_MESSAGE = 9;
    public static final short SERVER_FETCH = 24;
    public static final short SERVICE_ON_START_COMMAND = 156;
    public static final short START = 1;
    public static final short SUCCESS = 2;
    public static final short SUCCESS_CACHE = 25;
    public static final short SUCCESS_COLD = 10;
    public static final short SUCCESS_DB = 26;
    public static final short SUCCESS_LOCAL_UNSPECIFIED = 28;
    public static final short SUCCESS_NETWORK = 27;
    public static final short SUCCESS_WARM = 11;
    public static final short TIMEOUT = 113;
    public static final short UDP_REQUEST_SEND = 94;
    public static final short UI_IDLE = 12;
    public static final short UNKNOWN = 51;
    public static final short USER_NAVIGATION_CANCELLATION = 22;
    public static final short VIDEO_DISPLAYED = 170;
    public static final short VIDEO_PLAYING = 116;
    public static final short VIDEO_REQUESTED_PLAYING = 171;
    public static final short VIDEO_SET_RENDERER_CONTEXT = 120;
    public static final short VIEW_DID_APPEAR_BEGIN = 164;
    public static final short VIEW_DID_LOAD_BEGIN = 124;
    public static final short VIEW_WILL_APPEAR_BEGIN = 123;
    public static final short WAIT_FOR_BLOCKERS = 157;

    public static String getActionName(int i) {
        switch (i) {
            case 1:
                return "START";
            case 2:
                return "SUCCESS";
            case 3:
                return "FAIL";
            case 4:
                return "CANCEL";
            case 5:
                return "DRAW_COMPLETE";
            case 6:
                return "ON_RESUME";
            case 7:
                return "ACTIVITY_CREATED";
            case 8:
                return "CONSISTENCY_MODEL_UPDATER";
            case 9:
                return "SEND_MESSAGE";
            case 10:
                return "SUCCESS_COLD";
            case IOculusUserManager.Stub.TRANSACTION_refreshUsers /*{ENCODED_INT: 11}*/:
                return "SUCCESS_WARM";
            case IOculusUserManager.Stub.TRANSACTION_getSelf /*{ENCODED_INT: 12}*/:
                return "UI_IDLE";
            case IOculusUserManager.Stub.TRANSACTION_removeUnclaimedUser /*{ENCODED_INT: 13}*/:
                return "PHASE_ONE";
            case 14:
                return "PHASE_TWO";
            case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
                return "DEQUEUE";
            case 16:
                return "NETWORK_COMPLETE";
            case 17:
                return "MEMORY_CACHE_VISIT";
            case 18:
                return "DISK_CACHE_VISIT";
            case 19:
                return "CONSISTENCY_UPDATE";
            case 20:
                return "RETURN_TO_CALLER";
            case 21:
                return "PHOTO_UPLOAD_COMPLETE";
            case 22:
                return "USER_NAVIGATION_CANCELLATION";
            case 23:
                return "DB_FETCH";
            case MinidumpReader.MODULE_LIST_OFFSET /*{ENCODED_INT: 24}*/:
                return "SERVER_FETCH";
            case NotificationCompat$MessagingStyle.MAXIMUM_RETAINED_MESSAGES:
                return "SUCCESS_CACHE";
            case 26:
                return "SUCCESS_DB";
            case 27:
                return "SUCCESS_NETWORK";
            case 28:
                return "SUCCESS_LOCAL_UNSPECIFIED";
            case 29:
                return "CACHE_UPDATED";
            case 30:
                return "DB_UPDATED";
            case Hpack.PREFIX_5_BITS /*{ENCODED_INT: 31}*/:
                return "DATA_RECEIVED";
            case NotificationCompat$WearableExtender.FLAG_BIG_PICTURE_AMBIENT:
                return "DRAW_VIEW";
            case 33:
                return "DATA_EMPTY";
            case 34:
                return "CACHE_FETCH";
            case 35:
                return "PREPARE_BEGIN";
            case 36:
                return "PREPARE_END";
            case 37:
                return "ASYNC_BEGIN";
            case 38:
                return "ASYNC_END";
            case 39:
                return "REMOVE_BEGIN";
            case 40:
                return "REMOVE_END";
            case 41:
                return "BROADCAST_DONE";
            case 42:
                return "ON_RESUME_END";
            case 43:
                return "ON_ATTACH_END";
            case 44:
                return "ON_FRAGMENT_CREATE_END";
            case 45:
                return "ON_CREATE_VIEW_END";
            case 46:
                return "ON_ACTIVITY_CREATED_END";
            case 47:
                return "ON_START_END";
            case 48:
                return "QUEUED";
            case 49:
                return "IN_PROGRESS";
            case 50:
                return "INIT";
            case 51:
                return CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
            case 52:
                return "RETRY_AFTER_FAILURE";
            case 53:
                return "RETRY_AFTER_RECONNECT";
            case 54:
                return "QUEUEING_BEGIN";
            case 55:
                return "QUEUEING_SUCCESS";
            case 56:
                return "QUEUEING_FAIL";
            case 57:
                return "MESSAGE_UPDATE_START";
            case 58:
                return "MESSAGE_UPDATE_END";
            case 59:
                return "PHOTO_CAPTURED";
            case 60:
                return "MEDIA_PREVIEW_VISIBLE";
            case 61:
                return "COUNTER";
            case 62:
                return "INTERACTION_LOAD_TIMELINE_HEADER";
            case Hpack.PREFIX_6_BITS /*{ENCODED_INT: 63}*/:
                return "INTERACTION_LOAD_EVENT_PERMALINK";
            case NotificationCompat$WearableExtender.FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY:
                return "INTERACTION_LOAD_GROUPS_FEED";
            case 65:
                return "INTERACTION_LOAD_PAGE_HEADER";
            case 66:
                return "INTERACTION_LOAD_PAGE_HEADER_ADMIN";
            case 67:
                return "INTERACTION_LOAD_PERMALINK";
            case 68:
                return "INTERACTION_OPEN_COMPOSER";
            case 69:
                return "INTERACTION_OPEN_MEDIA_PICKER";
            case 70:
                return "INTERACTION_OPEN_PHOTO_GALLERY";
            case 71:
                return "INTERACTION_OPEN_CHECK_IN";
            case 72:
                return "INTERACTION_LOAD_WEB_VIEW";
            case 73:
                return "INTENT_MAPPED";
            case 74:
                return "ACTIVITY_LAUNCHED";
            case 75:
                return "ACTIVITY_PAUSED";
            case 76:
                return "ACTIVITY_STARTED";
            case 77:
                return "ACTIVITY_RESUMED";
            case 78:
                return "FRAGMENT_CREATED";
            case 79:
                return "FRAGMENT_RESUMED";
            case NotificationCompat$WearableExtender.DEFAULT_GRAVITY:
                return "ACTIVITY_ON_CREATE";
            case 81:
                return "INTENT_MAPPING_BEGIN";
            case 82:
                return "FRAGMENT_ON_CREATE";
            case 83:
                return "FRAGMENT_NEW_INSTANCE";
            case 84:
                return "MARKER_SWAPPED";
            case 85:
                return "FRAGMENT_INSTANCE_CREATED";
            case 86:
                return "PREV_ACTIVITY_PAUSED";
            case 87:
                return "ERROR";
            case 88:
                return "METHOD_INVOKE";
            case 89:
                return "FINALLY";
            case 90:
                return "PHOTO_DOWNLOAD_COMPLETE";
            case 91:
                return "MINIPREVIEW_COMPLETE";
            case 92:
                return "SEARCH_TYPEAHEAD";
            case 93:
                return "ANIMATION_END";
            case 94:
                return "UDP_REQUEST_SEND";
            case 95:
                return "MAIN_COMPLETE";
            case 96:
                return "INTERRUPTED";
            case 97:
                return "NETWORK_FAILED";
            case 98:
                return "NETWORK_RESPONSE";
            case 99:
                return "EDGE_PROCESSING_BEGIN";
            case 100:
                return "NEWSFEED_PROCESS_RESPONSE";
            case 101:
                return "ON_VIEW_CREATED_END";
            case 102:
                return "DATA_LOAD_START";
            case 103:
                return "LEGACY_MARKER";
            case 104:
                return "ACTION_BAR_COMPLETE";
            case 105:
                return "ABORTED";
            case 106:
                return "QUERY_READY";
            case 107:
                return "RTMP_PACKET_RECEIVED";
            case MinidumpReader.MODULE_FULL_SIZE /*{ENCODED_INT: 108}*/:
                return "REQUESTED_PLAYING";
            case 109:
                return "RTMP_CONNECTION_REQUESTED";
            case 110:
                return "RTMP_CONNECTION_RELEASE";
            case 111:
                return "NEW_START_FOUND";
            case 112:
                return "MISSED_EVENT";
            case 113:
                return "TIMEOUT";
            case 114:
                return "CONTROLLER_INITIATED";
            case 115:
                return "RTMP_STREAM_PREPARED";
            case 116:
                return "VIDEO_PLAYING";
            case 117:
                return "RTMP_CONNECTION_CONNECTED";
            case 118:
                return "RTMP_CONNECTION_FAILED";
            case 119:
                return "RTMP_CONNECTION_INTERCEPTED";
            case 120:
                return "VIDEO_SET_RENDERER_CONTEXT";
            case 121:
                return "HEADER_DATA_LOADED";
            case 122:
                return "CARD_DATA_LOADED";
            case 123:
                return "VIEW_WILL_APPEAR_BEGIN";
            case 124:
                return "VIEW_DID_LOAD_BEGIN";
            case 125:
                return "COMPONENTS_DATA_SOURCE_WILL_BEGIN_UPDATES";
            case 126:
                return "COMPONENTS_DATA_SOURCE_DID_END_UPDATES";
            case Hpack.PREFIX_7_BITS /*{ENCODED_INT: 127}*/:
                return "LOAD_VIEW_BEGIN";
            case 128:
                return "RTMP_FIRST_KEY_FRAME_RECEIVED";
            case 129:
                return "MESSENGER_QUEUE_CREATION";
            case 130:
                return "APP_DID_FINISH_LAUNCHING";
            case 131:
                return "APP_DID_BECOME_ACTIVE";
            case 132:
                return "APP_WILL_ENTER_FOREGROUND";
            case 133:
                return "APP_DID_ENTER_BACKGROUND";
            case 134:
                return "APP_MAIN";
            case 135:
                return "MQTT_CONNECTING";
            case 136:
                return "MQTT_CONNECTED";
            case 137:
                return "MQTT_DISCONNECTED";
            case 138:
                return "MESSENGER_DELTA_REQUEST";
            case 139:
                return "APP_FIRST_VIEW_CONTROLLER";
            case 140:
                return "MESSENGER_THREAD_LIST_LOADED";
            case 141:
                return "MESSENGER_THREAD_LIST_DISPLAYED";
            case 142:
                return "PREV_ACTIVITY_PAUSE";
            case 143:
                return "ACTIVITY_RESUME";
            case 144:
                return "ACTIVITY_START";
            case 145:
                return "BEGIN_START_ACTIVITY";
            case 146:
                return "END_START_ACTIVITY";
            case 147:
                return "FILE_SYSTEM_FAIL";
            case 148:
                return "FORMAT_ERROR";
            case 149:
                return "PRIVACY_VIOLATION";
            case 150:
                return "NETWORK_RESPONSE_INITIAL_SCAN";
            case 151:
                return "POPULATE_CONSISTENCY_MEMORY_CACHE";
            case 152:
                return "APPLY_OPTIMISTICS";
            case 153:
                return "APPLY_FINISHED_LIST";
            case 154:
                return "APPLY_FINISHED_LIST_AGAIN";
            case 155:
                return "FUTURE_LISTENERS_COMPLETE";
            case 156:
                return "SERVICE_ON_START_COMMAND";
            case 157:
                return "WAIT_FOR_BLOCKERS";
            case 158:
                return "NOTIFY_SUBSCRIBERS";
            case 159:
                return "FAIL_FILE_TOO_LARGE";
            case 160:
                return "OFFLINE";
            case 161:
                return "ASNYC_FAILED";
            case 162:
                return "ASYNC_FAIL";
            case 163:
                return "ON_ATTACH_FRAGMENT";
            case 164:
                return "VIEW_DID_APPEAR_BEGIN";
            case 165:
                return "DISPLAYED";
            case 166:
                return "DISPLAYED_ON_SCREEN";
            case 167:
                return "ASYNC_ACTION_SUCCESS";
            case 168:
                return "ASYNC_ACTION_FAIL";
            case 169:
                return "CONNECTIVITY_CHANGED";
            case 170:
                return "VIDEO_DISPLAYED";
            case 171:
                return "VIDEO_REQUESTED_PLAYING";
            default:
                return "UNDEFINED_QPL_ACTION";
        }
    }
}
