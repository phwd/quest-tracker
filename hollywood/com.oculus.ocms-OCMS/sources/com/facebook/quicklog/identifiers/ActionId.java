package com.facebook.quicklog.identifiers;

import com.facebook.common.fragmentconstants.FragmentConstants;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.tigon.iface.TigonRequest;
import com.facebook.ultralight.UL;

public class ActionId {
    public static final short ABANDONED = 477;
    public static final short ABORTED = 105;
    public static final short ACCESSIBILITY_ACTIVATE = 303;
    public static final short ACCESSIBILITY_CUSTOM = 304;
    public static final short ACCESSIBILITY_MAGIC_TAP = 305;
    public static final short ACTION_BAR_COMPLETE = 104;
    public static final short ACTIVITY_CREATED = 7;
    public static final short ACTIVITY_LAUNCHED = 74;
    public static final short ACTIVITY_NEW_INTENT = 434;
    public static final short ACTIVITY_ON_CREATE = 80;
    public static final short ACTIVITY_PAUSED = 75;
    public static final short ACTIVITY_RESUME = 143;
    public static final short ACTIVITY_RESUMED = 77;
    public static final short ACTIVITY_START = 144;
    public static final short ACTIVITY_STARTED = 76;
    public static final short ADD_STORY_TO_UI = 535;
    public static final short ADS_SELECT_AUDIENCE_VIEW = 650;
    public static final short ADS_SELECT_BUDGET_VIEW = 649;
    public static final short ADS_SELECT_CREATIVE_VIEW = 651;
    public static final short ADS_SELECT_IMAGE_VIEW = 648;
    public static final short ADS_SELECT_POST_VIEW = 647;
    public static final short AGGREGATED = 704;
    public static final short ALREADY_SEEN = 209;
    public static final short ANIMATION_END = 93;
    public static final short ANR_ENQUEUE = 405;
    public static final short ANR_START_DATA_CAPTURE = 404;
    public static final short APPLY_FACE_DETECTION_EFFECT = 644;
    public static final short APPLY_FINISHED_LIST = 153;
    public static final short APPLY_FINISHED_LIST_AGAIN = 154;
    public static final short APPLY_OPTIMISTICS = 152;
    public static final short APP_BACKGROUND = 310;
    public static final short APP_CRASH = 722;
    public static final short APP_CREATED = 396;
    public static final short APP_CREATED_MAIN_PROCESS = 446;
    public static final short APP_DID_BECOME_ACTIVE = 131;
    public static final short APP_DID_ENTER_BACKGROUND = 133;
    public static final short APP_DID_FINISH_LAUNCHING = 130;
    public static final short APP_DID_FINISH_LAUNCHING_ENDED = 412;
    public static final short APP_EXIT = 2001;
    public static final short APP_FIRST_VIEW_CONTROLLER = 139;
    public static final short APP_FOREGROUND = 311;
    public static final short APP_MAIN = 134;
    public static final short APP_ONCREATE = 459;
    public static final short APP_WILL_ENTER_FOREGROUND = 132;
    public static final short ASNYC_FAILED = 161;
    public static final short ASYNC_ACTION_FAIL = 168;
    public static final short ASYNC_ACTION_SUCCESS = 167;
    public static final short ASYNC_BEGIN = 37;
    public static final short ASYNC_BEGIN_DB = 413;
    public static final short ASYNC_BEGIN_SERVER = 417;
    public static final short ASYNC_END = 38;
    public static final short ASYNC_FAIL = 162;
    public static final short ASYNC_FAIL_DB = 416;
    public static final short ASYNC_FAIL_SERVER = 419;
    public static final short ASYNC_FAIL_SERVER_NO_DATA = 430;
    public static final short ASYNC_SUCCESS_DB = 415;
    public static final short ASYNC_SUCCESS_DB_NO_DATA = 429;
    public static final short ASYNC_SUCCESS_SERVER = 418;
    public static final short BACKGROUND_THREAD = 692;
    public static final short BB_TRACE_REQUESTED = 702;
    public static final short BEGIN_HANDLE_EVENT = 626;
    public static final short BEGIN_PROCESS_EVENT = 628;
    public static final short BEGIN_START_ACTIVITY = 145;
    public static final short BEGIN_TRANSACTION = 202;
    public static final short BLOCKING_RESOURCES_LOADED = 600;
    public static final short BRIDGE_STARTUP_DID_FINISH = 512;
    public static final short BRIDGE_STARTUP_WILL_START = 511;
    public static final short BROADCAST_DONE = 41;
    public static final short BROWSER_OPEN = 541;
    public static final short BUG_BASH_ACTION = 725;
    public static final short BUG_BASH_TEST = 726;
    public static final short BWE_ESTIMATE_COMPLETE = 694;
    public static final short CACHE_FETCH = 34;
    public static final short CACHE_INITIALIZED = 577;
    public static final short CACHE_UPDATED = 29;
    public static final short CACHE_WRITE_FAIL = 547;
    public static final short CACHE_WRITE_START = 545;
    public static final short CACHE_WRITE_SUCCESS = 546;
    public static final short CALLBACKS_COMPLETE = 191;
    public static final short CALLBACKS_DISPATCHED = 192;
    public static final short CALL_TO_ACTION_COMPLETE = 180;
    public static final short CAMERA_COMPONENT_MOUNTED = 313;
    public static final short CAMERA_FIRST_FRAME = 465;
    public static final short CAMERA_INITIALIZED = 507;
    public static final short CAMERA_PREVIEW_FROZEN = 567;
    public static final short CAMERA_START_READY = 528;
    public static final short CAMERA_VIDEO_OUTPUT_SWITCHED = 262;
    public static final short CAMERA_VIEW_READY = 464;
    public static final short CANCEL = 4;
    public static final short CANCEL_BACKGROUND = 630;
    public static final short CANCEL_NAVIGATION = 615;
    public static final short CANCEL_PSP = 703;
    public static final short CANCEL_UNLOAD = 706;
    public static final short CARD_ASYNC_BEGIN_SERVER = 699;
    public static final short CARD_ASYNC_SUCCESS_SERVER = 700;
    public static final short CARD_DATA_LOADED = 122;
    public static final short CHANGESET_ENQUEUED = 258;
    public static final short COLD_START_APP_SHELL_COMPONENT_DID_MOUNT = 673;
    public static final short COLD_START_BEGIN = 669;
    public static final short COLD_START_END = 670;
    public static final short COLD_START_LOAD_APP_JS = 671;
    public static final short COLD_START_QUERY_SEND = 672;
    public static final short COMMENTS_LOAD_COMPLETE = 441;
    public static final short COMMENTS_LOAD_START = 440;
    public static final short COMPONENTS_DATA_SOURCE_DID_END_UPDATES = 126;
    public static final short COMPONENTS_DATA_SOURCE_WILL_BEGIN_UPDATES = 125;
    public static final short COMPONENT_DATA_MODEL_UPDATE_COMPLETE = 487;
    public static final short COMPONENT_DATA_MODEL_UPDATE_START = 486;
    public static final short COMPONENT_DID_CREATE = 184;
    public static final short COMPONENT_DID_LAYOUT = 186;
    public static final short COMPONENT_DID_MOUNT = 188;
    public static final short COMPONENT_WILL_CREATE = 183;
    public static final short COMPONENT_WILL_LAYOUT = 185;
    public static final short COMPONENT_WILL_MOUNT = 187;
    public static final short COMPUTE_CHUNKS = 219;
    public static final short CONDITION_NOT_MET = 719;
    public static final short CONFIGURE_END = 540;
    public static final short CONFIGURE_START = 539;
    public static final short CONFIG_TABLE_MAGIC_MISMATCH = 296;
    public static final short CONFIG_TABLE_SCHEMA_ABSENT = 295;
    public static final short CONFIG_TABLE_SCHEMA_HASH_MISMATCH = 294;
    public static final short CONNECTIVITY_CHANGED = 169;
    public static final short CONSISTENCY_MODEL_UPDATER = 8;
    public static final short CONSISTENCY_UPDATE = 19;
    public static final short CONTEXT_ITEMS = 517;
    public static final short CONTROLLER_INITIATED = 114;
    public static final short COUNTER = 61;
    public static final short COVER_PHOTO_COMPLETE = 182;
    public static final short COVER_PHOTO_HIGH_RES = 514;
    public static final short COVER_PHOTO_LOW_RES = 513;
    public static final short CREATED_INTENT = 211;
    public static final short CREATED_MODEL_FILE = 200;
    public static final short CREATE_UI_MANAGER_MODULE_END = 717;
    public static final short CREATE_UI_MANAGER_MODULE_START = 716;
    public static final short DATA_EMPTY = 33;
    public static final short DATA_LOAD_END = 328;
    public static final short DATA_LOAD_START = 102;
    public static final short DATA_RECEIVED = 31;
    public static final short DB_FETCH = 23;
    public static final short DB_SUPPLIER_GET = 201;
    public static final short DB_UPDATED = 30;
    public static final short DB_WRITE_START = 533;
    public static final short DB_WRITE_STOP = 534;
    public static final short DELAY_START = 529;
    public static final short DELAY_STOP = 530;
    public static final short DELTAS_RECEIVED_AFTER_CONNECT = 326;
    public static final short DELTA_APPLICATION_COMPLETED = 325;
    public static final short DELTA_APPLICATION_INTERRUPTED = 330;
    public static final short DELTA_APPLICATION_STARTED = 414;
    public static final short DELTA_BATCH_APPLICATION_COMPLETED = 331;
    public static final short DEQUEUE = 15;
    public static final short DID_START_CAMERA_SESSION = 502;
    public static final short DISABLED = 524;
    public static final short DISK_CACHE_VISIT = 18;
    public static final short DISPLAYED = 165;
    public static final short DISPLAYED_ON_SCREEN = 166;
    public static final short DI_DONE = 244;
    public static final short DOWNLOAD_FAILED = 707;
    public static final short DRAW_COMPLETE = 5;
    public static final short DRAW_VIEW = 32;
    public static final short DROPPED = 216;
    public static final short DUMMY = 12524;
    public static final short EDGE_PROCESSING_BEGIN = 99;
    public static final short EMPTY_REQUEST = 521;
    public static final short END = 467;
    public static final short END_HANDLE_EVENT = 627;
    public static final short END_PROCESS_EVENT = 629;
    public static final short END_START_ACTIVITY = 146;
    public static final short ENTERED = 478;
    public static final short ERROR = 87;
    public static final short EXIT_VIEW_CONTROLLER = 228;
    public static final short FAIL = 3;
    public static final short FAIL_FALSE_NEGATIVE = 721;
    public static final short FAIL_FALSE_POSITIVE = 720;
    public static final short FAIL_FETCH_IMAGE = 215;
    public static final short FAIL_FILE_TOO_LARGE = 159;
    public static final short FAIL_GET_MODEL = 208;
    public static final short FAIL_NO_DATA = 433;
    public static final short FBLITE_INCOMPLETE = 691;
    public static final short FBLITE_INSTANT_TRANSITION_FAILED = 701;
    public static final short FBLITE_SCREEN_RECEIVED = 688;
    public static final short FBLITE_SERVER_END = 690;
    public static final short FBLITE_SERVER_START = 689;
    public static final short FBLITE_UNEXPECTED = 696;
    public static final short FEED_LOAD_FROM_DISK_FINISHED = 345;
    public static final short FEED_REQUEST_FAILED = 343;
    public static final short FEED_REQUEST_STARTED = 342;
    public static final short FEED_REQUEST_SUCCEEDED = 344;
    public static final short FEED_RESPONSE_PROCESSED = 410;
    public static final short FEED_TOOLBOX_SETUP_BEGIN = 635;
    public static final short FEED_TOOLBOX_SETUP_END = 636;
    public static final short FETCH_BEGIN = 578;
    public static final short FETCH_FINISHED = 579;
    public static final short FETCH_INBOX_FAILED = 495;
    public static final short FETCH_INBOX_STARTED = 493;
    public static final short FETCH_INBOX_SUCCEEDED = 494;
    public static final short FETCH_THREAD_FAILED = 474;
    public static final short FETCH_THREAD_STARTED = 472;
    public static final short FETCH_THREAD_SUCCEEDED = 473;
    public static final short FILE_FLUSHED = 203;
    public static final short FILE_NOT_FOUND = 178;
    public static final short FILE_SYSTEM_FAIL = 147;
    public static final short FINALLY = 89;
    public static final short FINAL_IMAGE_SET = 306;
    public static final short FINISH_CALLING_JS_FUNCTION = 199;
    public static final short FINISH_EXECUTING_JS_BUNDLE = 197;
    public static final short FINISH_INITIALIZING_JS_BRIDGE = 449;
    public static final short FINISH_INJECTING_JS_HOOKS = 450;
    public static final short FINISH_LOADING_JS_BUNDLE = 195;
    public static final short FINISH_REGISTERING_JS_NATIVE_MODULES = 447;
    public static final short FINISH_RUNNING_JS_INITIALIZER = 451;
    public static final short FIRST_CACHED_MEDIA_RENDERED = 506;
    public static final short FIRST_DATA_RECEIVED = 542;
    public static final short FIRST_HARDWARE_FRAME = 503;
    public static final short FIRST_MEDIA_RENDERED = 505;
    public static final short FIRST_UPDATE_FAILURE = 268;
    public static final short FIRST_UPDATE_SUCCESS = 267;
    public static final short FLATBUFFER_SCHEMA_ABSENT = 245;
    public static final short FOLLOWED_SHOWS_FETCHED = 476;
    public static final short FORMAT_ERROR = 148;
    public static final short FOUND_IMAGES = 281;
    public static final short FRAGMENT_CREATED = 78;
    public static final short FRAGMENT_INSTANCE_CREATED = 85;
    public static final short FRAGMENT_NEW_INSTANCE = 83;
    public static final short FRAGMENT_ON_CREATE = 82;
    public static final short FRAGMENT_RESUMED = 79;
    public static final short FRAGMENT_VISIBLE = 205;
    public static final short FRAME_RENDERED = 468;
    public static final short FRX_FLOW_END = 6646;
    public static final short FUTURE_LISTENERS_COMPLETE = 155;
    public static final short GINANDI_TEST = 32339;
    public static final short GO_AD_ACTIVITY_MODE = 302;
    public static final short GO_TO_AD_ACTIVITY = 298;
    public static final short GRAPHQL_CACHE_FETCH_END = 289;
    public static final short GRAPHQL_CACHE_FETCH_START = 288;
    public static final short GRID_MEDIA_LOADED = 435;
    public static final short HAS_VALID_HTML = 280;
    public static final short HAVE_EXACTLY_ONE_GMAIL = 13063;
    public static final short HAVE_ONE_GMAIL_MATCHED_CP = 13065;
    public static final short HEAD = 400;
    public static final short HEADER_CACHE_FETCH_STARTED = 253;
    public static final short HEADER_DATA_LOADED = 121;
    public static final short HEADER_DRAW_COMPLETE = 181;
    public static final short HIGH_RES_PHOTO_FILE_READY = 488;
    public static final short HTTP_TRANSACTION_STARTED = 508;
    public static final short INCOMPLETE_METADATA = 526;
    public static final short INIT = 50;
    public static final short INITIAL = 402;
    public static final short INITIAL_LOAD = 3921;
    public static final short INIT_MOBILE_CONFIG = 727;
    public static final short INIT_QE_END = 498;
    public static final short INIT_QE_START = 497;
    public static final short INIT_TO_NETWORK_CONTENT = 398;
    public static final short INIT_TO_USABLE = 397;
    public static final short INTENT_MAPPED = 73;
    public static final short INTENT_MAPPING_BEGIN = 81;
    public static final short INTERACTION_CLICK = 666;
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
    public static final short INTERACTION_OPEN_QRCODE_SCANNER = 437;
    public static final short INTERACTION_SWIPE_UP = 645;
    public static final short INTERMEDIATE_IMAGE_FAIL = 308;
    public static final short INTERMEDIATE_IMAGE_GOOD_ENOUGH_SET = 309;
    public static final short INTERMEDIATE_IMAGE_SET = 307;
    public static final short INTERRUPTED = 96;
    public static final short INVALID_INTENT = 212;
    public static final short IN_PROGRESS = 49;
    public static final short ITEM_SELECTED = 232;
    public static final short JSON_PARSE = 177;
    public static final short JS_SETUP = 604;
    public static final short JS_TEARDOWN = 603;
    public static final short LEAVE = 420;
    public static final short LEGACY_MARKER = 103;
    public static final short LIVE_RTMP_DVR_HANDLED_FRAME = 276;
    public static final short LIVE_RTMP_STREAMING_HANDLED_FRAME = 275;
    public static final short LOADED_AUDIO_SESSION = 172;
    public static final short LOADED_CAMERA_SESSION = 173;
    public static final short LOAD_MODULES = 613;
    public static final short LOAD_QUERY_STRING = 293;
    public static final short LOAD_URL_BEGIN = 582;
    public static final short LOAD_URL_END = 583;
    public static final short LOAD_VIEW_BEGIN = 127;
    public static final short LOAD_VIEW_END = 581;
    public static final short LOCATION_FETCH_BEGIN = 264;
    public static final short LOCATION_FETCH_FAILED = 266;
    public static final short LOCATION_FETCH_SUCCESS = 265;
    public static final short LOGGED_OUT = 461;
    public static final short LOGIN_FLOW_COMPLETED = 684;
    public static final short LOGIN_FLOW_STARTED = 683;
    public static final short LOG_COMPACTED = 225;
    public static final short LOG_READ = 224;
    public static final short LOG_WRITER_OPENED = 226;
    public static final short LOOM_MMAP_TRACE_NOT_RECOVERED = 710;
    public static final short LOOM_PROVIDER_FAILURE = 646;
    public static final short LS_BODY_LOAD = 458;
    public static final short LS_BODY_START = 457;
    public static final short LS_FILTER_LOAD = 480;
    public static final short LS_FILTER_START = 479;
    public static final short LS_FILTER_SUCCESS = 481;
    public static final short LS_HEADER_LAYOUT_SUCCESS = 462;
    public static final short LS_HEADER_LOAD = 456;
    public static final short LS_HEADER_START = 455;
    public static final short LS_MAP_LIST_LOADED = 470;
    public static final short LS_MAP_LIST_START = 469;
    public static final short LS_MAP_LIST_SUCCESS = 471;
    public static final short LS_SEARCH_RESULT_LOAD = 483;
    public static final short LS_SEARCH_RESULT_START = 482;
    public static final short MAIN_COMPLETE = 95;
    public static final short MAIN_THREAD = 693;
    public static final short MARKER_SWAPPED = 84;
    public static final short MEASURE_IMAGE = 679;
    public static final short MEDIA_EDIT = 235;
    public static final short MEDIA_EDIT_COMPLETE = 236;
    public static final short MEDIA_LOADED = 518;
    public static final short MEDIA_LOAD_CACHE = 438;
    public static final short MEDIA_LOAD_NETWORK = 439;
    public static final short MEDIA_LOAD_START = 442;
    public static final short MEDIA_PREVIEW_VISIBLE = 60;
    public static final short MEDIA_TOO_SMALL = 231;
    public static final short MEMORY_CACHE_VISIT = 17;
    public static final short MERGE_LOCAL_FIELDS = 292;
    public static final short MESSAGE_COMPRESSED = 563;
    public static final short MESSAGE_DECOMPRESSED = 565;
    public static final short MESSAGE_LIST_DID_UPDATE = 332;
    public static final short MESSAGE_LIST_WILL_UPDATE = 333;
    public static final short MESSAGE_UPDATE_END = 58;
    public static final short MESSAGE_UPDATE_START = 57;
    public static final short MESSENGER_DAY_UNIT_DISPLAYED = 327;
    public static final short MESSENGER_DELTA_REQUEST = 138;
    public static final short MESSENGER_DELTA_REQUEST_FAILURE = 249;
    public static final short MESSENGER_DELTA_REQUEST_INIT = 341;
    public static final short MESSENGER_QUEUE_CREATION = 129;
    public static final short MESSENGER_QUEUE_CREATION_FAILURE = 248;
    public static final short MESSENGER_THREAD_LIST_DISPLAYED = 141;
    public static final short MESSENGER_THREAD_LIST_LOADED = 140;
    public static final short METABOX_COMPLETE = 179;
    public static final short METERED_CONNECTION = 522;
    public static final short METHOD_INVOKE = 88;
    public static final short MINIPREVIEW_COMPLETE = 91;
    public static final short MISSED_EVENT = 112;
    public static final short MODEL_ENQUEUED = 337;
    public static final short MQTT_CONNECTED = 136;
    public static final short MQTT_CONNECTING = 135;
    public static final short MQTT_CONNECTION_ATTEMPTED = 314;
    public static final short MQTT_DISCONNECTED = 137;
    public static final short MULTIDEX_INSTALLED = 499;
    public static final short NATIVE_PHOTO_BITMAP_READY = 489;
    public static final short NAVIGATION = 3922;
    public static final short NETWORK_COMPLETE = 16;
    public static final short NETWORK_FAILED = 97;
    public static final short NETWORK_PARSE_COMPLETE = 193;
    public static final short NETWORK_PARSE_START = 484;
    public static final short NETWORK_REQUEST_SENT = 485;
    public static final short NETWORK_RESPONSE = 98;
    public static final short NETWORK_RESPONSE_INITIAL_SCAN = 150;
    public static final short NEWSFEED_PROCESS_RESPONSE = 100;
    public static final short NEWS_FEED_FRAGMENT = 252;
    public static final short NEW_START_FOUND = 111;
    public static final short NON_ANR = 448;
    public static final short NOTIFY_SUBSCRIBERS = 158;
    public static final short NOTIF_BUZZED = 409;
    public static final short NOTIF_DUPLICATE = 407;
    public static final short NOTIF_MUTED = 408;
    public static final short NOTIF_NOT_ALERTED = 406;
    public static final short NOT_READY = 176;
    public static final short NO_ACTIVE_TRACE = 709;
    public static final short NO_EMAIL_FETCHED = 13062;
    public static final short NO_GMAIL_MATCHED = 13066;
    public static final short NO_METADATA = 525;
    public static final short NUMBER_OF_GMAILS = 13064;
    public static final short OAUTH_DIALOG_ACCEPT = 13068;
    public static final short OAUTH_DIALOG_DENY = 13069;
    public static final short OAUTH_DIALOG_SHOWN = 13067;
    public static final short OAUTH_FAIL_OTHER = 13074;
    public static final short OAUTH_FAIL_TIMEOUT = 13073;
    public static final short OAUTH_SUCCESS = 13072;
    public static final short OAUTH_TOKEN_FAILURE = 13071;
    public static final short OAUTH_TOKEN_FETCHED = 13070;
    public static final short OBJSEL_FETCH = 297;
    public static final short OFFLINE = 160;
    public static final short OFFSCREEN = 223;
    public static final short ON_ACTIVITY_CREATED_END = 46;
    public static final short ON_ATTACH_END = 43;
    public static final short ON_ATTACH_FRAGMENT = 163;
    public static final short ON_CREATE_VIEW_END = 45;
    public static final short ON_FRAGMENT_CREATE_END = 44;
    public static final short ON_RESUME = 6;
    public static final short ON_RESUME_END = 42;
    public static final short ON_SHOW_LOGIN = 520;
    public static final short ON_START_END = 47;
    public static final short ON_VIDEO_RECORDING_FINISHED = 678;
    public static final short ON_VIEW_CREATED_END = 101;
    public static final short OPTIMISTIC_UPDATES_APPLIED = 204;
    public static final short OTHER = 403;
    public static final short OUT_OF_ORDER = 175;
    public static final short OVERRIDES_EXIST = 243;
    public static final short PARSING_FINISHED = 580;
    public static final short PASS_AD_CHECK = 278;
    public static final short PASS_QE_CHECK = 277;
    public static final short PASS_SAMPLE_RATE_CHECK = 279;
    public static final short PDP_RENDER_FETCHED = 681;
    public static final short PDP_RENDER_LOADING = 680;
    public static final short PERMISSION_DIALOG_SHOWN = 13061;
    public static final short PHASE_ONE = 13;
    public static final short PHASE_ONE_COMPLETE = 453;
    public static final short PHASE_TWO = 14;
    public static final short PHASE_TWO_COMPLETE = 454;
    public static final short PHOTO_BITMAP_READY = 466;
    public static final short PHOTO_CAPTURED = 59;
    public static final short PHOTO_CAPTURE_READY = 527;
    public static final short PHOTO_DOWNLOAD_COMPLETE = 90;
    public static final short PHOTO_UPLOAD_COMPLETE = 21;
    public static final short PHOTO_UPLOAD_START = 234;
    public static final short PLUGIN_LOAD_END = 711;
    public static final short PLUGIN_LOAD_START = 713;
    public static final short POPULATE_CONSISTENCY_MEMORY_CACHE = 151;
    public static final short PRECALCULATE_EDGES = 189;
    public static final short PREPARE_BEGIN = 35;
    public static final short PREPARE_CAMERA_SESSION = 500;
    public static final short PREPARE_END = 36;
    public static final short PREP_FORMS = 606;
    public static final short PREP_STATE = 605;
    public static final short PRESENTED = 339;
    public static final short PREV_ACTIVITY_PAUSE = 142;
    public static final short PREV_ACTIVITY_PAUSED = 86;
    public static final short PRE_REQUEST_SEND_CALLED = 510;
    public static final short PRIVACY_VIOLATION = 149;
    public static final short PROFILE_PIC_HIGH_RES = 516;
    public static final short PROFILE_PIC_LOW_RES = 515;
    public static final short PROFILE_TOOLBOX_SETUP_BEGIN = 633;
    public static final short PROFILE_TOOLBOX_SETUP_END = 634;
    public static final short QPL_END_TO_END = 11445;
    public static final short QRCODE_SCANNER_SCAN_FAILURE = 445;
    public static final short QRCODE_SCANNER_SCAN_SUCCESS = 444;
    public static final short QUERY_CHUNKS = 217;
    public static final short QUERY_READY = 106;
    public static final short QUERY_ROWS = 218;
    public static final short QUEUED = 48;
    public static final short QUEUEING_BEGIN = 54;
    public static final short QUEUEING_FAIL = 56;
    public static final short QUEUEING_SUCCESS = 55;
    public static final short RANK_START = 531;
    public static final short RANK_STOP = 532;
    public static final short RECEIVED_HARDWARE_FRAME = 312;
    public static final short REEL_JSON_RECEIVED = 490;
    public static final short REEL_MEDIA_RECEIVED = 491;
    public static final short REMOTE_PROCESS = 509;
    public static final short REMOVE_BEGIN = 39;
    public static final short REMOVE_END = 40;
    public static final short REPOSITIONED = 233;
    public static final short REQUESTED_PLAYING = 108;
    public static final short REQUEST_ADDED = 496;
    public static final short RETRY_AFTER_FAILURE = 52;
    public static final short RETRY_AFTER_RECONNECT = 53;
    public static final short RETURN_EXCEPTION_TO_CALLER = 250;
    public static final short RETURN_TO_CALLER = 20;
    public static final short ROOT_QUERY_FAIL = 576;
    public static final short ROOT_QUERY_START = 574;
    public static final short ROOT_QUERY_SUCCESS = 575;
    public static final short RTC_BROADCAST_INITIALIZED = 259;
    public static final short RTC_STREAMING_INITIALIZED = 260;
    public static final short RTC_STREAMING_STARTED = 261;
    public static final short RTMP_CONNECTION_CONNECTED = 117;
    public static final short RTMP_CONNECTION_FAILED = 118;
    public static final short RTMP_CONNECTION_INTERCEPTED = 119;
    public static final short RTMP_CONNECTION_RELEASE = 110;
    public static final short RTMP_CONNECTION_REQUESTED = 109;
    public static final short RTMP_DVR_HANDLED_FRAME = 274;
    public static final short RTMP_FIRST_KEY_FRAME_RECEIVED = 128;
    public static final short RTMP_PACKET_RECEIVED = 107;
    public static final short RTMP_STREAMING_HANDLED_FRAME = 269;
    public static final short RTMP_STREAM_PREPARED = 115;
    public static final short RTMP_STREAM_STOPPED = 263;
    public static final short RUN_FUNCTION = 610;
    public static final short RVP_DID_CREATE = 557;
    public static final short RVP_DID_FAIL_AUTOPLAY = 638;
    public static final short RVP_DID_FINISH_INFLATE = 559;
    public static final short RVP_DID_LOAD = 544;
    public static final short RVP_DID_MOUNT = 561;
    public static final short RVP_DID_PAUSE = 549;
    public static final short RVP_DID_PLAY = 551;
    public static final short RVP_DID_PREPARE = 553;
    public static final short RVP_DID_RELOAD = 555;
    public static final short RVP_WILL_CREATE = 556;
    public static final short RVP_WILL_FINISH_INFLATE = 558;
    public static final short RVP_WILL_LOAD = 543;
    public static final short RVP_WILL_MOUNT = 560;
    public static final short RVP_WILL_PAUSE = 548;
    public static final short RVP_WILL_PLAY = 550;
    public static final short RVP_WILL_PREPARE = 552;
    public static final short RVP_WILL_RELOAD = 554;
    public static final short SAVE_AD = 299;
    public static final short SAVE_MODE = 301;
    public static final short SCHEMA_HASH_MISMATCH = 242;
    public static final short SCREEN_PART_RECEIVED = 637;
    public static final short SCROLL_COMPLETE = 220;
    public static final short SCROLL_START = 443;
    public static final short SC_TRACKER_SETUP_BEGIN = 631;
    public static final short SC_TRACKER_SETUP_END = 632;
    public static final short SEARCH_TYPEAHEAD = 92;
    public static final short SELECT_PHOTOS_FAILED_SCORE = 664;
    public static final short SELECT_PHOTOS_FAILED_TIMESTAMP = 665;
    public static final short SEND_MESSAGE = 9;
    public static final short SERVER_FETCH = 24;
    public static final short SERVICE_ON_START_COMMAND = 156;
    public static final short SESSION_OPEN = 241;
    public static final short SHARE_FLOW_LOADED = 642;
    public static final short SHOULD_LOAD_URL_BEGIN = 598;
    public static final short SHOULD_LOAD_URL_END = 599;
    public static final short SHOW_NOTIFICATION = 213;
    public static final short SPINNER_APPEARED = 336;
    public static final short STALE = 206;
    public static final short START = 1;
    public static final short STARTED_CAPTURE_SESSION = 463;
    public static final short START_CALLING_JS_FUNCTION = 198;
    public static final short START_CAMERA_SESSION = 501;
    public static final short START_COMPRESSING_MESSAGE = 562;
    public static final short START_DECOMPRESSING_MESSAGE = 564;
    public static final short START_DOWNLOAD_FACE_DETECTION_EFFECT = 643;
    public static final short START_EXECUTING_JS_BUNDLE = 196;
    public static final short START_FETCH_IMAGE = 460;
    public static final short START_LOADING_JS_BUNDLE = 194;
    public static final short START_RENDERING_FIRST_USER_FRAME = 504;
    public static final short START_SENDING_MESSAGE = 566;
    public static final short STATE_UPDATE = 519;
    public static final short STORIES_LOAD_FROM_DISK_FINISHED = 394;
    public static final short STORIES_REQUEST_FAILED = 392;
    public static final short STORIES_REQUEST_STARTED = 346;
    public static final short STORIES_REQUEST_SUCCEEDED = 393;
    public static final short STORIES_RESPONSE_PROCESSED = 411;
    public static final short STORY_TRAY_MEDIA_LOADED = 436;
    public static final short STORY_VIEWER_APPEAR = 492;
    public static final short STRUCTURE_FETCH_COMPLETE = 475;
    public static final short SUCCESS = 2;
    public static final short SUCCESS_CACHE = 25;
    public static final short SUCCESS_CACHE_NOT_MODIFIED = 11947;
    public static final short SUCCESS_COLD = 10;
    public static final short SUCCESS_DB = 26;
    public static final short SUCCESS_FETCH_IMAGE = 214;
    public static final short SUCCESS_GET_MODEL = 207;
    public static final short SUCCESS_LOCAL_UNSPECIFIED = 28;
    public static final short SUCCESS_MEMORY = 431;
    public static final short SUCCESS_NETWORK = 27;
    public static final short SUCCESS_NONEXISTENCE = 246;
    public static final short SUCCESS_NOTPRESENTED = 13170;
    public static final short SUCCESS_OPTIMISTIC = 174;
    public static final short SUCCESS_PYTORCH = 3653;
    public static final short SUCCESS_SERVER = 432;
    public static final short SUCCESS_WARM = 11;
    public static final short SUCCESS_ZERO_WAIT_TIME = 334;
    public static final short SURFACE_TEXTURE_AVAILABLE = 251;
    public static final short TAB_SWITCH = 227;
    public static final short TAGS_PREPARED = 229;
    public static final short TAIL = 401;
    public static final short TEST_ACTION_FOR_CALLER_CONTEXT_TWO = 29998;
    public static final short TEST_GINDI = 723;
    public static final short TEST_NUBBEL = 718;
    public static final short THREAD_ASYNC_BEGIN_SERVER = 697;
    public static final short THREAD_ASYNC_SUCCESS_SERVER = 698;
    public static final short TIGON_PARSE_BEGIN = 286;
    public static final short TIGON_PARSE_END = 287;
    public static final short TIGON_REQUEST_BEGIN = 282;
    public static final short TIGON_REQUEST_END = 283;
    public static final short TIGON_RESPONSE_BEGIN = 284;
    public static final short TIGON_RESPONSE_END = 285;
    public static final short TIMEOUT = 113;
    public static final short TOTAL = 399;
    public static final short TTTT = 724;
    public static final short UDP_REQUEST_SEND = 94;
    public static final short UI_IDLE = 12;
    public static final short UI_RESPONSIVE = 452;
    public static final short UI_THREAD_DEQUEUE = 190;
    public static final short UNINSTRUMENTED = 705;
    public static final short UNINTERRUPTED = 537;
    public static final short UNKNOWN = 51;
    public static final short UNKNOWN_SEEN_STATE = 210;
    public static final short UNPACKER_CHECK_END = 714;
    public static final short UNPACKER_CHECK_START = 712;
    public static final short UNPACKING_END = 715;
    public static final short UNSAVE_AD = 300;
    public static final short UNZIP_FAILED = 708;
    public static final short USER_INFO_LOADED = 395;
    public static final short USER_NAVIGATION_CANCELLATION = 22;
    public static final short USER_SCROLLED = 335;
    public static final short USE_ARGUMENTS = 222;
    public static final short USE_INSTANCE_STATE = 221;
    public static final short VC_INIT_BEGIN = 570;
    public static final short VC_INIT_END = 571;
    public static final short VC_INIT_START = 569;
    public static final short VC_VIEW_DID_LOAD_BEGIN = 572;
    public static final short VC_VIEW_DID_LOAD_END = 573;
    public static final short VIDEO_CANCELLED = 247;
    public static final short VIDEO_COMPLETE = 238;
    public static final short VIDEO_DISPLAYED = 170;
    public static final short VIDEO_DOWNLOAD_FAILED = 687;
    public static final short VIDEO_DOWNLOAD_READY_TO_PLAY = 686;
    public static final short VIDEO_DOWNLOAD_STARTED = 685;
    public static final short VIDEO_DOWNSTREAM_FORMAT_CHANGED = 523;
    public static final short VIDEO_END_STALL = 291;
    public static final short VIDEO_FETCH_REQUEST_CACHE_CHECK_END = 662;
    public static final short VIDEO_FETCH_REQUEST_CACHE_CHECK_START = 661;
    public static final short VIDEO_FETCH_REQUEST_COMPLETE = 660;
    public static final short VIDEO_FETCH_REQUEST_DID_ATTACH_TO_NETWORK_REQUEST = 663;
    public static final short VIDEO_FETCH_REQUEST_ENTER_NETWORK_QUEUE = 654;
    public static final short VIDEO_FETCH_REQUEST_FAILED = 682;
    public static final short VIDEO_FETCH_REQUEST_NETWORK_FIRST_BYTE_ARRIVED = 657;
    public static final short VIDEO_FETCH_REQUEST_NETWORK_REQUEST_START = 655;
    public static final short VIDEO_FETCH_REQUEST_NETWORK_RESPONSE_RECEIVED = 656;
    public static final short VIDEO_FETCH_REQUEST_NETWORK_TRANSFER_COMPLETE = 658;
    public static final short VIDEO_FETCH_REQUEST_RECEIVED = 653;
    public static final short VIDEO_FETCH_REQUEST_SATISFIED_BY_CACHE = 659;
    public static final short VIDEO_FETCH_REQUEST_START = 652;
    public static final short VIDEO_PAUSE = 237;
    public static final short VIDEO_PLAYING = 116;
    public static final short VIDEO_PLAYING_QPL_TIMEOUT = 240;
    public static final short VIDEO_PLAYING_TIMEOUT = 239;
    public static final short VIDEO_READY_TO_PLAY = 614;
    public static final short VIDEO_RECORDING_START_CALLED = 674;
    public static final short VIDEO_RECORDING_STOP_CALLED = 675;
    public static final short VIDEO_RENDERED = 640;
    public static final short VIDEO_REQUESTED_PLAYING = 171;
    public static final short VIDEO_SCRUBBER_FIRST_THUMBNAIL_SHOWN = 667;
    public static final short VIDEO_SCRUBBER_THUMBNAIL_SHOWN = 668;
    public static final short VIDEO_SET_RENDERER_CONTEXT = 120;
    public static final short VIDEO_START_STALL = 290;
    public static final short VIDEO_TOGGLE_FULL_SCREEN = 538;
    public static final short VIEW_DID_APPEAR = 230;
    public static final short VIEW_DID_APPEAR_BEGIN = 164;
    public static final short VIEW_DID_APPEAR_END = 641;
    public static final short VIEW_DID_BECOME_VISIBLE = 329;
    public static final short VIEW_DID_BECOME_VISIBLE_END = 340;
    public static final short VIEW_DID_LOAD_BEGIN = 124;
    public static final short VIEW_MODEL_APPLIED = 338;
    public static final short VIEW_WILL_APPEAR_BEGIN = 123;
    public static final short VIEW_WILL_APPEAR_END = 639;
    public static final short WAIT_FOR_BLOCKERS = 157;
    public static final short WEBVIEW_URI_REDIRECTOR_CONSTRUCTION = 536;
    public static final short WEB_PAGE_LOADED = 601;
    public static final short WIKTORK_TEST = 676;
    public static final short WIKTORK_TEST_THREE = 695;
    public static final short WIKTORK_TEST_TWO = 677;

    public static String getActionName(int i) {
        if (i == 1) {
            return "START";
        }
        if (i == 2) {
            return "SUCCESS";
        }
        if (i == 3) {
            return "FAIL";
        }
        if (i == 4) {
            return "CANCEL";
        }
        if (i == 5) {
            return "DRAW_COMPLETE";
        }
        if (i == 6) {
            return "ON_RESUME";
        }
        if (i == 7) {
            return "ACTIVITY_CREATED";
        }
        switch (i) {
            case 7:
                return "ACTIVITY_CREATED";
            case 8:
                return "CONSISTENCY_MODEL_UPDATER";
            case 9:
                return "SEND_MESSAGE";
            case 10:
                return "SUCCESS_COLD";
            case 11:
                return "SUCCESS_WARM";
            case 12:
                return "UI_IDLE";
            case 13:
                return "PHASE_ONE";
            case 14:
                return "PHASE_TWO";
            case 15:
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
            case 24:
                return "SERVER_FETCH";
            case 25:
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
            case 31:
                return "DATA_RECEIVED";
            case 32:
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
            case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                return "IN_PROGRESS";
            case 50:
                return "INIT";
            case 51:
                return "UNKNOWN";
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
            case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                return "COUNTER";
            case 62:
                return "INTERACTION_LOAD_TIMELINE_HEADER";
            case 63:
                return "INTERACTION_LOAD_EVENT_PERMALINK";
            case 64:
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
            case 80:
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
            case 108:
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
            case UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID /*{ENCODED_INT: 117}*/:
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
            case 127:
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
            case 172:
                return "LOADED_AUDIO_SESSION";
            case 173:
                return "LOADED_CAMERA_SESSION";
            case 174:
                return "SUCCESS_OPTIMISTIC";
            case 175:
                return "OUT_OF_ORDER";
            case 176:
                return "NOT_READY";
            case 177:
                return "JSON_PARSE";
            case 178:
                return "FILE_NOT_FOUND";
            case 179:
                return "METABOX_COMPLETE";
            case 180:
                return "CALL_TO_ACTION_COMPLETE";
            case 181:
                return "HEADER_DRAW_COMPLETE";
            case 182:
                return "COVER_PHOTO_COMPLETE";
            case 183:
                return "COMPONENT_WILL_CREATE";
            case 184:
                return "COMPONENT_DID_CREATE";
            case 185:
                return "COMPONENT_WILL_LAYOUT";
            case 186:
                return "COMPONENT_DID_LAYOUT";
            case 187:
                return "COMPONENT_WILL_MOUNT";
            case 188:
                return "COMPONENT_DID_MOUNT";
            case 189:
                return "PRECALCULATE_EDGES";
            case 190:
                return "UI_THREAD_DEQUEUE";
            case 191:
                return "CALLBACKS_COMPLETE";
            case 192:
                return "CALLBACKS_DISPATCHED";
            case 193:
                return "NETWORK_PARSE_COMPLETE";
            case 194:
                return "START_LOADING_JS_BUNDLE";
            case 195:
                return "FINISH_LOADING_JS_BUNDLE";
            case 196:
                return "START_EXECUTING_JS_BUNDLE";
            case 197:
                return "FINISH_EXECUTING_JS_BUNDLE";
            case 198:
                return "START_CALLING_JS_FUNCTION";
            case 199:
                return "FINISH_CALLING_JS_FUNCTION";
            case 200:
                return "CREATED_MODEL_FILE";
            case 201:
                return "DB_SUPPLIER_GET";
            case 202:
                return "BEGIN_TRANSACTION";
            case 203:
                return "FILE_FLUSHED";
            case 204:
                return "OPTIMISTIC_UPDATES_APPLIED";
            case 205:
                return "FRAGMENT_VISIBLE";
            case 206:
                return "STALE";
            case 207:
                return "SUCCESS_GET_MODEL";
            case 208:
                return "FAIL_GET_MODEL";
            case 209:
                return "ALREADY_SEEN";
            case 210:
                return "UNKNOWN_SEEN_STATE";
            case 211:
                return "CREATED_INTENT";
            case 212:
                return "INVALID_INTENT";
            case 213:
                return "SHOW_NOTIFICATION";
            case 214:
                return "SUCCESS_FETCH_IMAGE";
            case 215:
                return "FAIL_FETCH_IMAGE";
            case 216:
                return "DROPPED";
            case 217:
                return "QUERY_CHUNKS";
            case 218:
                return "QUERY_ROWS";
            case 219:
                return "COMPUTE_CHUNKS";
            case 220:
                return "SCROLL_COMPLETE";
            case 221:
                return "USE_INSTANCE_STATE";
            case 222:
                return "USE_ARGUMENTS";
            case 223:
                return "OFFSCREEN";
            case 224:
                return "LOG_READ";
            case 225:
                return "LOG_COMPACTED";
            case 226:
                return "LOG_WRITER_OPENED";
            case 227:
                return "TAB_SWITCH";
            case 228:
                return "EXIT_VIEW_CONTROLLER";
            case 229:
                return "TAGS_PREPARED";
            case 230:
                return "VIEW_DID_APPEAR";
            case 231:
                return "MEDIA_TOO_SMALL";
            case 232:
                return "ITEM_SELECTED";
            case 233:
                return "REPOSITIONED";
            case 234:
                return "PHOTO_UPLOAD_START";
            case 235:
                return "MEDIA_EDIT";
            case 236:
                return "MEDIA_EDIT_COMPLETE";
            case 237:
                return "VIDEO_PAUSE";
            case 238:
                return "VIDEO_COMPLETE";
            case 239:
                return "VIDEO_PLAYING_TIMEOUT";
            case 240:
                return "VIDEO_PLAYING_QPL_TIMEOUT";
            case 241:
                return "SESSION_OPEN";
            case 242:
                return "SCHEMA_HASH_MISMATCH";
            case 243:
                return "OVERRIDES_EXIST";
            case 244:
                return "DI_DONE";
            case 245:
                return "FLATBUFFER_SCHEMA_ABSENT";
            case 246:
                return "SUCCESS_NONEXISTENCE";
            case 247:
                return "VIDEO_CANCELLED";
            case 248:
                return "MESSENGER_QUEUE_CREATION_FAILURE";
            case 249:
                return "MESSENGER_DELTA_REQUEST_FAILURE";
            case 250:
                return "RETURN_EXCEPTION_TO_CALLER";
            case 251:
                return "SURFACE_TEXTURE_AVAILABLE";
            case 252:
                return "NEWS_FEED_FRAGMENT";
            case FragmentConstants.ContentFragmentType.LIVE_MAP_FRAGMENT:
                return "HEADER_CACHE_FETCH_STARTED";
            case FragmentConstants.ContentFragmentType.LOCATION_SETTINGS_XPLAT_FRAGMENT:
                return "RUN_FUNCTION";
            case FragmentConstants.ContentFragmentType.WATCH_EXPERIMENT_VIEWER_FRAGMENT:
                return "BEGIN_HANDLE_EVENT";
            case FragmentConstants.ContentFragmentType.REQUESTED_APPOINTMENT_TAB_FRAGMENT:
                return "END_HANDLE_EVENT";
            case FragmentConstants.ContentFragmentType.PAGE_CLIENT_LIST_FRAGMENT:
                return "BEGIN_PROCESS_EVENT";
            case FragmentConstants.ContentFragmentType.PAGE_CLIENT_LIST_SEARCH_FRAGMENT:
                return "END_PROCESS_EVENT";
            case FragmentConstants.ContentFragmentType.GROUP_SAFETY_HUB_COMPONENT_FRAGMENT:
                return "CANCEL_BACKGROUND";
            case FragmentConstants.ContentFragmentType.ATHENS_SURFACE_FRAGMENT:
                return "SC_TRACKER_SETUP_BEGIN";
            case FragmentConstants.ContentFragmentType.PAGE_UNIVERSAL_DISTRIBUTION_FRAGMENT:
                return "SC_TRACKER_SETUP_END";
            case FragmentConstants.ContentFragmentType.SEARCH_VOYAGER_ENDPOINT_FRAGMENT:
                return "PROFILE_TOOLBOX_SETUP_BEGIN";
            case FragmentConstants.ContentFragmentType.PAGE_CREATE_OFFER_NT_FRAGMENT:
                return "PROFILE_TOOLBOX_SETUP_END";
            case FragmentConstants.ContentFragmentType.LOCAL_SERVICE_APPOINTMENT_DASHBOARD:
                return "FEED_TOOLBOX_SETUP_BEGIN";
            case FragmentConstants.ContentFragmentType.PAGE_CLIENT_REENGAGEMENT_CREATION_FRAGMENT:
                return "FEED_TOOLBOX_SETUP_END";
            case FragmentConstants.ContentFragmentType.XMA_LABELS_SETTINGS_FRAGMENT:
                return "SCREEN_PART_RECEIVED";
            case FragmentConstants.ContentFragmentType.BUY_SELL_GROUP_DISCUSSIONS_FRAGMENT:
                return "RVP_DID_FAIL_AUTOPLAY";
            case FragmentConstants.ContentFragmentType.GROUP_SELL_PENDING_REPLIES_FRAGMENT:
                return "VIEW_WILL_APPEAR_END";
            case FragmentConstants.ContentFragmentType.PAGE_CLIENT_IMPORT_VIEW_NOTES_FRAGMENT:
                return "VIDEO_RENDERED";
            case FragmentConstants.ContentFragmentType.EVENT_SHARE_STORY_LANDING_FRAGMENT:
                return "VIEW_DID_APPEAR_END";
            case FragmentConstants.ContentFragmentType.PAGE_INSPIRATION_HUB_FRAGMENT:
                return "SHARE_FLOW_LOADED";
            case FragmentConstants.ContentFragmentType.PMA_REDIRECT_FRAGMENT:
                return "START_DOWNLOAD_FACE_DETECTION_EFFECT";
            case 644:
                return "APPLY_FACE_DETECTION_EFFECT";
            case 645:
                return "INTERACTION_SWIPE_UP";
            case FragmentConstants.ContentFragmentType.BIRTHDAY_STORY_SETTINGS_FRAGMENT:
                return "LOOM_PROVIDER_FAILURE";
            case FragmentConstants.ContentFragmentType.PAGE_MANUAL_APPOINTMENT_CREATION_FRAGMENT:
                return "ADS_SELECT_POST_VIEW";
            case FragmentConstants.ContentFragmentType.STORY_HIGHLIGHTS_SETTINGS_FRAGMENT:
                return "ADS_SELECT_IMAGE_VIEW";
            case FragmentConstants.ContentFragmentType.GROUPS_NEW_EDIT_PRIVACY_FRAGMENT:
                return "ADS_SELECT_BUDGET_VIEW";
            case FragmentConstants.ContentFragmentType.GROUPS_LINKED_FOLDERS_FRAGMENT:
                return "ADS_SELECT_AUDIENCE_VIEW";
            case FragmentConstants.ContentFragmentType.GROUPS_LEARNING_UNIT_FRAGMENT:
                return "ADS_SELECT_CREATIVE_VIEW";
            case FragmentConstants.ContentFragmentType.GROUPS_RECOMMENDATIONS_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_START";
            case 653:
                return "VIDEO_FETCH_REQUEST_RECEIVED";
            case FragmentConstants.ContentFragmentType.GROUPS_MENTORSHIP_APPLICATION_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_ENTER_NETWORK_QUEUE";
            case FragmentConstants.ContentFragmentType.GROUPS_NATIVE_TEMPLATES_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_NETWORK_REQUEST_START";
            case FragmentConstants.ContentFragmentType.PAGE_ADMIN_CREATE_PERSONAL_EVENT_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_NETWORK_RESPONSE_RECEIVED";
            case FragmentConstants.ContentFragmentType.GROUPS_TAB_DISCOVER_INVITES_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_NETWORK_FIRST_BYTE_ARRIVED";
            case FragmentConstants.ContentFragmentType.MAPS_FRAGMENT_VECTOR:
                return "VIDEO_FETCH_REQUEST_NETWORK_TRANSFER_COMPLETE";
            case FragmentConstants.ContentFragmentType.INSTANT_EXPERIENCE_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_SATISFIED_BY_CACHE";
            case FragmentConstants.ContentFragmentType.SHARESHEET_POLL_END_TIME_PICKER_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_COMPLETE";
            case FragmentConstants.ContentFragmentType.GROUPS_INTEREST_WIZARD_PICKER_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_CACHE_CHECK_START";
            case FragmentConstants.ContentFragmentType.WORK_PENDING_INVITES_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_CACHE_CHECK_END";
            case FragmentConstants.ContentFragmentType.GROUP_KEYWORD_ALERTS_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_DID_ATTACH_TO_NETWORK_REQUEST";
            case FragmentConstants.ContentFragmentType.GROUPS_INTEREST_WIZARD_SGBI_FRAGMENT:
                return "SELECT_PHOTOS_FAILED_SCORE";
            case FragmentConstants.ContentFragmentType.CONVERSATION_FRAGMENT:
                return "SELECT_PHOTOS_FAILED_TIMESTAMP";
            case FragmentConstants.ContentFragmentType.GROUPS_MEMBERSHIP_TAB_FRAGMENT:
                return "INTERACTION_CLICK";
            case FragmentConstants.ContentFragmentType.EVENT_TICKET_ORDER_LIST_NT_FRAGMENT:
                return "VIDEO_SCRUBBER_FIRST_THUMBNAIL_SHOWN";
            case FragmentConstants.ContentFragmentType.EVENT_TICKET_ORDER_DETAIL_NT_FRAGMENT:
                return "VIDEO_SCRUBBER_THUMBNAIL_SHOWN";
            case FragmentConstants.ContentFragmentType.COMPOSER_PHOTO3D_PREVIEW_FRAGMENT:
                return "COLD_START_BEGIN";
            case FragmentConstants.ContentFragmentType.GROUPS_CREATE_CHAT_FRAGMENT:
                return "COLD_START_END";
            case FragmentConstants.ContentFragmentType.SOCAL_LOCATION_PICKER_MAP_FRAGMENT:
                return "COLD_START_LOAD_APP_JS";
            case FragmentConstants.ContentFragmentType.SOCAL_LOCATION_PICKER_TYPEAHEAD_FRAGMENT:
                return "COLD_START_QUERY_SEND";
            case FragmentConstants.ContentFragmentType.GROUPS_RULE_ENFORCEMENT_ADMIN_VIEW_FRAGMENT:
                return "COLD_START_APP_SHELL_COMPONENT_DID_MOUNT";
            case FragmentConstants.ContentFragmentType.GROUPS_RULE_ENFORCEMENT_ADMIN_VIEW_LEARN_MORE_FRAGMENT:
                return "VIDEO_RECORDING_START_CALLED";
            case FragmentConstants.ContentFragmentType.SOCAL_SEARCH_TYPEAHEAD_FRAGMENT:
                return "VIDEO_RECORDING_STOP_CALLED";
            case FragmentConstants.ContentFragmentType.SOCAL_EVENTS_FEED_FRAGMENT:
                return "WIKTORK_TEST";
            case FragmentConstants.ContentFragmentType.GROUPS_ADMIN_PEOPLE_PICKER_FRAGMENT:
                return "WIKTORK_TEST_TWO";
            case FragmentConstants.ContentFragmentType.GROUPS_TAB_SETTINGS_TAB_FRAGMENT:
                return "ON_VIDEO_RECORDING_FINISHED";
            case FragmentConstants.ContentFragmentType.NATIVE_TEMPLATES_CPP_FRAGMENT:
                return "MEASURE_IMAGE";
            case FragmentConstants.ContentFragmentType.GROUPS_TAB_SETTINGS_TAB_COMMON_FRAGMENT:
                return "PDP_RENDER_LOADING";
            case FragmentConstants.ContentFragmentType.PAGE_ADMIN_SAVE_PAGE_COLLECTIONS_FRAGMENT:
                return "PDP_RENDER_FETCHED";
            case FragmentConstants.ContentFragmentType.PAGE_MANAGER_INSTAGRAM_DIRECT_DETAILS_FRAGMENT:
                return "VIDEO_FETCH_REQUEST_FAILED";
            case FragmentConstants.ContentFragmentType.NATIVE_GEMSTONE_HOME_FRAGMENT:
                return "LOGIN_FLOW_STARTED";
            case FragmentConstants.ContentFragmentType.PAGE_CONTENT_LIST_VIEW_STANDALONE_FRAGMENT:
                return "LOGIN_FLOW_COMPLETED";
            case 685:
                return "VIDEO_DOWNLOAD_STARTED";
            case FragmentConstants.ContentFragmentType.PAGE_MANAGER_INSTAGRAM_DIRECT_GROUP_DETAILS_FRAGMENT:
                return "VIDEO_DOWNLOAD_READY_TO_PLAY";
            case FragmentConstants.ContentFragmentType.TIMELINE_HOBBIES_FRAGMENT:
                return "VIDEO_DOWNLOAD_FAILED";
            case FragmentConstants.ContentFragmentType.PERMANET_FRAGMENT:
                return "FBLITE_SCREEN_RECEIVED";
            case FragmentConstants.ContentFragmentType.GROUP_VIOLATIONS_SURFACE:
                return "FBLITE_SERVER_START";
            case FragmentConstants.ContentFragmentType.JOBS_TAB_FRAGMENT:
                return "FBLITE_SERVER_END";
            case FragmentConstants.ContentFragmentType.GROUPS_BADGE_MEMBER_LIST_FRAGMENT:
                return "FBLITE_INCOMPLETE";
            case FragmentConstants.ContentFragmentType.COMPASS_SURFACE_FRAGMENT:
                return "BACKGROUND_THREAD";
            case FragmentConstants.ContentFragmentType.PAGES_FEED_SCREEN_FRAGMENT:
                return "MAIN_THREAD";
            case FragmentConstants.ContentFragmentType.MODULARITY_BACKGROUND_DOWNLOAD_FRAGMENT:
                return "BWE_ESTIMATE_COMPLETE";
            case FragmentConstants.ContentFragmentType.MODULARITY_BIG_DOWNLOAD_FRAGMENT:
                return "WIKTORK_TEST_THREE";
            case FragmentConstants.ContentFragmentType.MODULARITY_BUILTIN_FRAGMENT:
                return "FBLITE_UNEXPECTED";
            case FragmentConstants.ContentFragmentType.MODULARITY_INTERACTIVE_DOWNLOAD_FRAGMENT:
                return "THREAD_ASYNC_BEGIN_SERVER";
            case FragmentConstants.ContentFragmentType.LOCAL_GROUP_EDIT_LOCATION_FRAGMENT:
                return "THREAD_ASYNC_SUCCESS_SERVER";
            case FragmentConstants.ContentFragmentType.FRIENDS_HOME_SECONDARY_FRAGMENT:
                return "CARD_ASYNC_BEGIN_SERVER";
            case FragmentConstants.ContentFragmentType.PMA_XMA_INBOX_SEARCH_EXPAND_LIST_FRAGMENT:
                return "CARD_ASYNC_SUCCESS_SERVER";
            case FragmentConstants.ContentFragmentType.FRIENDS_HOME_FRAGMENT:
                return "FBLITE_INSTANT_TRANSITION_FAILED";
            case FragmentConstants.ContentFragmentType.GROUPS_RULE_BASED_AUTO_APPROVE_SETUP_FRAGMENT:
                return "BB_TRACE_REQUESTED";
            case FragmentConstants.ContentFragmentType.PMA_SAVED_REPLIES_MANAGEMENT_FRAGMENT:
                return "CANCEL_PSP";
            case FragmentConstants.ContentFragmentType.GROUPS_ACTIVE_LIVING_ROOMS_FRAGMENT:
                return "AGGREGATED";
            case FragmentConstants.ContentFragmentType.WATCH_UPDATES_SURFACE_FRAGMENT:
                return "UNINSTRUMENTED";
            case FragmentConstants.ContentFragmentType.PMA_MESSAGING_COMPOSER_PERSONALIZION_FRAGMENT:
                return "CANCEL_UNLOAD";
            case FragmentConstants.ContentFragmentType.GROUPS_AUTO_APPROVED_MEMBERS_FRAGMENT:
                return "DOWNLOAD_FAILED";
            case FragmentConstants.ContentFragmentType.WORKSPEED_INBOX_FRAGMENT:
                return "UNZIP_FAILED";
            case FragmentConstants.ContentFragmentType.PMA_WEC_GROUP_THREAD_MEMBER_LIST_FRAGMENT:
                return "NO_ACTIVE_TRACE";
            case FragmentConstants.ContentFragmentType.CARRIER_WIFI_PROFILE_SETUP_FRAGMENT:
                return "LOOM_MMAP_TRACE_NOT_RECOVERED";
            case FragmentConstants.ContentFragmentType.PERMANET_PREFERRED_NETWORKS_SETUP_FRAGMENT:
                return "PLUGIN_LOAD_END";
            case FragmentConstants.ContentFragmentType.GROUPS_TAB_DISCOVER_FRAGMENT:
                return "UNPACKER_CHECK_START";
            case FragmentConstants.ContentFragmentType.PAGE_CLIENT_IMPORT_VIEW_CUSTOM_FIELDS_FRAGMENT:
                return "PLUGIN_LOAD_START";
            case FragmentConstants.ContentFragmentType.PHOTO_3D_LAUNCHER_FRAGMENT:
                return "UNPACKER_CHECK_END";
            case FragmentConstants.ContentFragmentType.NATIVE_TEMPLATES_VIEW_MODEL_FRAGMENT:
                return "UNPACKING_END";
            case FragmentConstants.ContentFragmentType.GROUPS_TAB_EDIT_GROUP_LIST_FRAGMENT:
                return "CREATE_UI_MANAGER_MODULE_START";
            case FragmentConstants.ContentFragmentType.PAGE_CREATION_AND_UPDATION_FRAGMENT:
                return "CREATE_UI_MANAGER_MODULE_END";
            case 718:
                return "TEST_NUBBEL";
            case FragmentConstants.ContentFragmentType.MODULARITY_ROOT_FRAGMENT:
                return "CONDITION_NOT_MET";
            case FragmentConstants.ContentFragmentType.GROUP_MEMBERS_LIST_FRAGMENT:
                return "FAIL_FALSE_POSITIVE";
            case FragmentConstants.ContentFragmentType.GROUP_INSIGHTS_MODERATOR_RECOMMENDATION_FRAGMENT:
                return "FAIL_FALSE_NEGATIVE";
            case FragmentConstants.ContentFragmentType.GOODWILL_MEMORIES_PERMALINK_FRAGMENT:
                return "APP_CRASH";
            case FragmentConstants.ContentFragmentType.CARRIER_WIFI_LEARN_MORE_FRAGMENT:
                return "TEST_GINDI";
            case FragmentConstants.ContentFragmentType.GROUPS_TAB_COLLECTION_CREATE_EDIT_FRAGMENT:
                return "TTTT";
            case FragmentConstants.ContentFragmentType.GROUPS_SCHEDULE_POST_FRAGMENT:
                return "BUG_BASH_ACTION";
            case FragmentConstants.ContentFragmentType.BIZ_APP_TABS_FRAGMENT:
                return "BUG_BASH_TEST";
            case FragmentConstants.ContentFragmentType.PLACE_PREVIEW_MOVIE_THEATER_MODAL_FRAGMENT:
                return "INIT_MOBILE_CONFIG";
            case 2001:
                return "APP_EXIT";
            case 3653:
                return "SUCCESS_PYTORCH";
            case 3921:
                return "INITIAL_LOAD";
            case 3922:
                return "NAVIGATION";
            case 6646:
                return "FRX_FLOW_END";
            case 11445:
                return "QPL_END_TO_END";
            case 11947:
                return "SUCCESS_CACHE_NOT_MODIFIED";
            case 12524:
                return "DUMMY";
            case 13061:
                return "PERMISSION_DIALOG_SHOWN";
            case 13062:
                return "NO_EMAIL_FETCHED";
            case 13063:
                return "HAVE_EXACTLY_ONE_GMAIL";
            case 13064:
                return "NUMBER_OF_GMAILS";
            case 13065:
                return "HAVE_ONE_GMAIL_MATCHED_CP";
            case 13066:
                return "NO_GMAIL_MATCHED";
            case 13067:
                return "OAUTH_DIALOG_SHOWN";
            case 13068:
                return "OAUTH_DIALOG_ACCEPT";
            case 13069:
                return "OAUTH_DIALOG_DENY";
            case 13070:
                return "OAUTH_TOKEN_FETCHED";
            case 13071:
                return "OAUTH_TOKEN_FAILURE";
            case 13072:
                return "OAUTH_SUCCESS";
            case 13073:
                return "OAUTH_FAIL_TIMEOUT";
            case 13074:
                return "OAUTH_FAIL_OTHER";
            case 13170:
                return "SUCCESS_NOTPRESENTED";
            case 29998:
                return "TEST_ACTION_FOR_CALLER_CONTEXT_TWO";
            case 32339:
                return "GINANDI_TEST";
            default:
                switch (i) {
                    case FragmentConstants.ContentFragmentType.CREATE_NEW_PAGE_FRAGMENT:
                        return "CHANGESET_ENQUEUED";
                    case FragmentConstants.ContentFragmentType.GROUP_EDIT_TAGS_FRAGMENT:
                        return "RTC_BROADCAST_INITIALIZED";
                    case FragmentConstants.ContentFragmentType.PAGE_PRESENCE_TAB_FRAGMENT:
                        return "RTC_STREAMING_INITIALIZED";
                    case FragmentConstants.ContentFragmentType.WORK_GROUPS_TAB:
                        return "RTC_STREAMING_STARTED";
                    case FragmentConstants.ContentFragmentType.OFFER_BARCODE_FULLSCREEN_FRAGMENT:
                        return "CAMERA_VIDEO_OUTPUT_SWITCHED";
                    case FragmentConstants.ContentFragmentType.SEARCH_RESULTS_EXPLORE_HOST_FRAGMENT:
                        return "RTMP_STREAM_STOPPED";
                    case FragmentConstants.ContentFragmentType.FUNDRAISER_SINGLE_CLICK_INVITE_FRAGMENT:
                        return "LOCATION_FETCH_BEGIN";
                    case FragmentConstants.ContentFragmentType.REACT_FRAGMENT_WITH_MARKETPLACE_SEARCH:
                        return "LOCATION_FETCH_SUCCESS";
                    case FragmentConstants.ContentFragmentType.GROUP_SCHOOL_EMAIL_VERIFICATION_FRAGMENT:
                        return "LOCATION_FETCH_FAILED";
                    case FragmentConstants.ContentFragmentType.GROUP_SCHOOL_CODE_CONFIRMATION_FRAGMENT:
                        return "FIRST_UPDATE_SUCCESS";
                    case FragmentConstants.ContentFragmentType.GROUPS_ADMIN_ACTIVITY_FRAGMENT:
                        return "FIRST_UPDATE_FAILURE";
                    case FragmentConstants.ContentFragmentType.CITY_COMMUNITY_FRAGMENT:
                        return "RTMP_STREAMING_HANDLED_FRAME";
                    default:
                        switch (i) {
                            case FragmentConstants.ContentFragmentType.OLD_SHARESHEET_FRAGMENT:
                                return "RTMP_DVR_HANDLED_FRAME";
                            case FragmentConstants.ContentFragmentType.STORY_PRIVACY_SETTING_FRAGMENT:
                                return "LIVE_RTMP_STREAMING_HANDLED_FRAME";
                            case FragmentConstants.ContentFragmentType.STORIES_ALLOWLIST_BLOCKLIST_SELECTION_FRAGMENT:
                                return "LIVE_RTMP_DVR_HANDLED_FRAME";
                            case FragmentConstants.ContentFragmentType.FACECAST_SHARESHEET_FRAGMENT:
                                return "PASS_QE_CHECK";
                            case FragmentConstants.ContentFragmentType.TEENS_SHARESHEET_FRAGMENT:
                                return "PASS_AD_CHECK";
                            case FragmentConstants.ContentFragmentType.TARGET_AUDIENCE_SHARESHEET_FRAGMENT:
                                return "PASS_SAMPLE_RATE_CHECK";
                            case FragmentConstants.ContentFragmentType.SEARCH_RESULTS_ELECTION_DETAILS_FRAGMENT:
                                return "HAS_VALID_HTML";
                            case FragmentConstants.ContentFragmentType.FUNDRAISER_CREATION_FRAGMENT:
                                return "FOUND_IMAGES";
                            case FragmentConstants.ContentFragmentType.SEARCH_RESULTS_EXPLORE_IMMERSIVE_HOST_FRAGMENT:
                                return "TIGON_REQUEST_BEGIN";
                            case FragmentConstants.ContentFragmentType.COMMUNITY_MEMBERS_LIST_FRAGMENT:
                                return "TIGON_REQUEST_END";
                            case FragmentConstants.ContentFragmentType.EVENT_TICKET_ORDER_DETAIL_FRAGMENT:
                                return "TIGON_RESPONSE_BEGIN";
                            case FragmentConstants.ContentFragmentType.CREATE_BOOKING_APPOINTMENT_FRAGMENT:
                                return "TIGON_RESPONSE_END";
                            case FragmentConstants.ContentFragmentType.PAGE_SERVICE_SELECTOR_FRAGMENT:
                                return "TIGON_PARSE_BEGIN";
                            case FragmentConstants.ContentFragmentType.FUNDRAISER_CREATION_SUGGESTED_COVER_PHOTO_FRAGMENT:
                                return "TIGON_PARSE_END";
                            case FragmentConstants.ContentFragmentType.PAGE_DEEPLINK_TAB_FRAGMENT:
                                return "GRAPHQL_CACHE_FETCH_START";
                            case FragmentConstants.ContentFragmentType.APPOINTMENT_CALENDAR_FRAGMENT:
                                return "GRAPHQL_CACHE_FETCH_END";
                            case FragmentConstants.ContentFragmentType.GROUPS_CHATS_FRAGMENT:
                                return "VIDEO_START_STALL";
                            case FragmentConstants.ContentFragmentType.PAGE_OFFERS_FRAGMENT:
                                return "VIDEO_END_STALL";
                            case FragmentConstants.ContentFragmentType.PAGE_JOBS_FRAGMENT:
                                return "MERGE_LOCAL_FIELDS";
                            case FragmentConstants.ContentFragmentType.FUNDRAISER_CURRENCY_SELECTOR_FRAGMENT:
                                return "LOAD_QUERY_STRING";
                            case FragmentConstants.ContentFragmentType.GETQUOTE_FORM_BUILDER:
                                return "CONFIG_TABLE_SCHEMA_HASH_MISMATCH";
                            case FragmentConstants.ContentFragmentType.GROUPS_DISCUSSION_TOPICS_ASSIGN_FRAGMENT:
                                return "CONFIG_TABLE_SCHEMA_ABSENT";
                            case FragmentConstants.ContentFragmentType.COMMUNITY_GROUPS_FRAGMENT:
                                return "CONFIG_TABLE_MAGIC_MISMATCH";
                            case FragmentConstants.ContentFragmentType.COMMUNITY_SEARCH_FRAGMENT:
                                return "OBJSEL_FETCH";
                            case FragmentConstants.ContentFragmentType.COMMUNITY_TRENDING_STORIES_FRAGMENT:
                                return "GO_TO_AD_ACTIVITY";
                            case FragmentConstants.ContentFragmentType.COMMUNITY_JOIN_GROUPS_NUX_FRAGMENT:
                                return "SAVE_AD";
                            case FragmentConstants.ContentFragmentType.PAGE_EDIT_TABS_REORDER_FRAGMENT:
                                return "UNSAVE_AD";
                            case FragmentConstants.ContentFragmentType.PAGE_TEMPLATES_FRAGMENT:
                                return "SAVE_MODE";
                            case FragmentConstants.ContentFragmentType.PAGES_SUBSCRIPTION_SETTINGS_FRAGMENT:
                                return "GO_AD_ACTIVITY_MODE";
                            case FragmentConstants.ContentFragmentType.PAGES_USER_NOTIFICATION_SETTINGS_FRAGMENT:
                                return "ACCESSIBILITY_ACTIVATE";
                            case FragmentConstants.ContentFragmentType.GROUP_MEMBER_BIO_FRAGMENT:
                                return "ACCESSIBILITY_CUSTOM";
                            case FragmentConstants.ContentFragmentType.NATIVE_TEMPLATES_FRAGMENT:
                                return "ACCESSIBILITY_MAGIC_TAP";
                            case FragmentConstants.ContentFragmentType.PAGES_CONFIGURE_ACTION_FRAGMENT:
                                return "FINAL_IMAGE_SET";
                            case 307:
                                return "INTERMEDIATE_IMAGE_SET";
                            case 308:
                                return "INTERMEDIATE_IMAGE_FAIL";
                            case FragmentConstants.ContentFragmentType.EVENTS_DASHBOARD_HOSTING_FRAGMENT:
                                return "INTERMEDIATE_IMAGE_GOOD_ENOUGH_SET";
                            case FragmentConstants.ContentFragmentType.GROUPS_CUSTOM_INVITE_FRAGMENT:
                                return "APP_BACKGROUND";
                            case FragmentConstants.ContentFragmentType.GROUP_SHARE_LINK_FRAGMENT:
                                return "APP_FOREGROUND";
                            case FragmentConstants.ContentFragmentType.LINK_GROUP_TO_PAGE_FRAGMENT:
                                return "RECEIVED_HARDWARE_FRAME";
                            case FragmentConstants.ContentFragmentType.REACTION_PHOTO_GRID:
                                return "CAMERA_COMPONENT_MOUNTED";
                            case FragmentConstants.ContentFragmentType.PAGE_EDIT_PAGE_ADD_TAB_FRAGMENT:
                                return "MQTT_CONNECTION_ATTEMPTED";
                            default:
                                switch (i) {
                                    case FragmentConstants.ContentFragmentType.LOYALTY_VIEW_FRAGMENT:
                                        return "DELTA_APPLICATION_COMPLETED";
                                    case FragmentConstants.ContentFragmentType.OFFERS_FEED_FRAGMENT:
                                        return "DELTAS_RECEIVED_AFTER_CONNECT";
                                    case FragmentConstants.ContentFragmentType.OFFERS_FEED_VIEW_FRAGMENT:
                                        return "MESSENGER_DAY_UNIT_DISPLAYED";
                                    case FragmentConstants.ContentFragmentType.FUNDRAISER_CATEGORY_SELECTOR_FRAGMENT:
                                        return "DATA_LOAD_END";
                                    case FragmentConstants.ContentFragmentType.PAGE_SERVICE_ADD_EDIT_FRAGMENT:
                                        return "VIEW_DID_BECOME_VISIBLE";
                                    case FragmentConstants.ContentFragmentType.GROUP_COMMERCE_BOOKMARK_FRAGMENT:
                                        return "DELTA_APPLICATION_INTERRUPTED";
                                    case FragmentConstants.ContentFragmentType.REACT_FRAGMENT_WITH_JOBS_KEYWORD_QUERY_SEARCH:
                                        return "DELTA_BATCH_APPLICATION_COMPLETED";
                                    case FragmentConstants.ContentFragmentType.SAVED_LISTS_CREATION_FRAGMENT:
                                        return "MESSAGE_LIST_DID_UPDATE";
                                    case FragmentConstants.ContentFragmentType.FIND_WIFI_FRAGMENT:
                                        return "MESSAGE_LIST_WILL_UPDATE";
                                    case FragmentConstants.ContentFragmentType.FIND_WIFI_SETTINGS_FRAGMENT:
                                        return "SUCCESS_ZERO_WAIT_TIME";
                                    case FragmentConstants.ContentFragmentType.FIND_WIFI_NUX_FRAGMENT:
                                        return "USER_SCROLLED";
                                    case FragmentConstants.ContentFragmentType.TOPIC_INFO_FRAGMENT:
                                        return "SPINNER_APPEARED";
                                    case FragmentConstants.ContentFragmentType.TIP_ADMIN_FRAGMENT:
                                        return "MODEL_ENQUEUED";
                                    case FragmentConstants.ContentFragmentType.GROUPS_INSIGHTS_FRAGMENT:
                                        return "VIEW_MODEL_APPLIED";
                                    case FragmentConstants.ContentFragmentType.GROUPS_INSIGHTS_GROUP_ACTIVITY_FRAGMENT:
                                        return "PRESENTED";
                                    case FragmentConstants.ContentFragmentType.GROUPS_INSIGHTS_TOP_CONTRIBUTOR_FRAGMENT:
                                        return "VIEW_DID_BECOME_VISIBLE_END";
                                    case FragmentConstants.ContentFragmentType.GROUPS_INSIGHTS_GROUP_MEMBER_FRAGMENT:
                                        return "MESSENGER_DELTA_REQUEST_INIT";
                                    case FragmentConstants.ContentFragmentType.GROUPS_INSIGHTS_GROUP_PEOPLE_FRAGMENT:
                                        return "FEED_REQUEST_STARTED";
                                    case FragmentConstants.ContentFragmentType.EVENTS_CREATE_NUX_FRAGMENT:
                                        return "FEED_REQUEST_FAILED";
                                    case FragmentConstants.ContentFragmentType.EVENTS_PAGE_CALENDAR_ALL_EVENTS:
                                        return "FEED_REQUEST_SUCCEEDED";
                                    case FragmentConstants.ContentFragmentType.NOTIFICATION_SETTINGS_EMAIL_FRAGMENT:
                                        return "FEED_LOAD_FROM_DISK_FINISHED";
                                    case FragmentConstants.ContentFragmentType.GROUP_NATIVE_REPORTED_POST_FRAGMENT:
                                        return "STORIES_REQUEST_STARTED";
                                    default:
                                        switch (i) {
                                            case 392:
                                                return "STORIES_REQUEST_FAILED";
                                            case FragmentConstants.ContentFragmentType.INSTAGRAM_SIGN_UP_WELCOME_FRAGMENT:
                                                return "STORIES_REQUEST_SUCCEEDED";
                                            case FragmentConstants.ContentFragmentType.INSTANT_BOOKING_SETTINGS_FRAGMENT:
                                                return "STORIES_LOAD_FROM_DISK_FINISHED";
                                            case 395:
                                                return "USER_INFO_LOADED";
                                            case FragmentConstants.ContentFragmentType.LOCAL_SURFACE_LOCATION_PICKER:
                                                return "APP_CREATED";
                                            case FragmentConstants.ContentFragmentType.B2C_COMMERCE_TAB_FRAGMENT_DELETED:
                                                return "INIT_TO_USABLE";
                                            case FragmentConstants.ContentFragmentType.INSTAGRAM_CONTACT_IMPORTER_FRAGMENT:
                                                return "INIT_TO_NETWORK_CONTENT";
                                            case FragmentConstants.ContentFragmentType.FUNDRAISER_CHARITY_SEARCH_INFO:
                                                return "TOTAL";
                                            case 400:
                                                return TigonRequest.HEAD;
                                            case FragmentConstants.ContentFragmentType.PAGE_EDIT_COVER_FRAGMENT:
                                                return "TAIL";
                                            case FragmentConstants.ContentFragmentType.GOOD_ADDS_UP_FRAGMENT:
                                                return "INITIAL";
                                            case FragmentConstants.ContentFragmentType.GROUP_MEMBER_QUESTION_SETTING_FRAGMENT:
                                                return "OTHER";
                                            case 404:
                                                return "ANR_START_DATA_CAPTURE";
                                            case FragmentConstants.ContentFragmentType.EVENTS_DASHBOARD_NEARBY_FRAGMENT:
                                                return "ANR_ENQUEUE";
                                            case FragmentConstants.ContentFragmentType.INSPIRATION_SETTINGS_FRAGMENT:
                                                return "NOTIF_NOT_ALERTED";
                                            case FragmentConstants.ContentFragmentType.FUNDRAISER_CREATION_OUTRO_FRAGMENT:
                                                return "NOTIF_DUPLICATE";
                                            case FragmentConstants.ContentFragmentType.INTERN_TEST_USERS_FRAGMENT:
                                                return "NOTIF_MUTED";
                                            case FragmentConstants.ContentFragmentType.FOOD_DRINK_SEE_ALL_FRAGMENT:
                                                return "NOTIF_BUZZED";
                                            case FragmentConstants.ContentFragmentType.COMPOSER_SHARESHEET_CREATE_CASUAL_GROUP_FRAGMENT:
                                                return "FEED_RESPONSE_PROCESSED";
                                            case FragmentConstants.ContentFragmentType.GROUP_LINKED_OR_LINKABLE_PAGE_SEE_ALL_FRAGMENT:
                                                return "STORIES_RESPONSE_PROCESSED";
                                            case FragmentConstants.ContentFragmentType.GROUPS_ANNOUNCEMENTS_FRAGMENT:
                                                return "APP_DID_FINISH_LAUNCHING_ENDED";
                                            case 413:
                                                return "ASYNC_BEGIN_DB";
                                            case FragmentConstants.ContentFragmentType.PAGES_DEEP_EDIT_FRAGMENT:
                                                return "DELTA_APPLICATION_STARTED";
                                            case FragmentConstants.ContentFragmentType.GROUP_COMMERCE_COMPOSER_AUDIENCE_VIEW_FRAGMENT:
                                                return "ASYNC_SUCCESS_DB";
                                            case 416:
                                                return "ASYNC_FAIL_DB";
                                            case FragmentConstants.ContentFragmentType.PAGES_NOTE_DRAFTS_FRAGMENT:
                                                return "ASYNC_BEGIN_SERVER";
                                            case FragmentConstants.ContentFragmentType.VISUAL_SEARCH_FRAGMENT:
                                                return "ASYNC_SUCCESS_SERVER";
                                            case FragmentConstants.ContentFragmentType.SEEN_CONTENT_FEED_FRAGMENT:
                                                return "ASYNC_FAIL_SERVER";
                                            case FragmentConstants.ContentFragmentType.PAGES_EDIT_BUTTONS_FRAGMENT:
                                                return "LEAVE";
                                            default:
                                                switch (i) {
                                                    case FragmentConstants.ContentFragmentType.PAGES_EDIT_TABS_FRAGMENT:
                                                        return "ASYNC_SUCCESS_DB_NO_DATA";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_SAVED_TAB_FRAGMENT:
                                                        return "ASYNC_FAIL_SERVER_NO_DATA";
                                                    case FragmentConstants.ContentFragmentType.COMPONENT_SCRIPT_PLAYGROUND:
                                                        return "SUCCESS_MEMORY";
                                                    case FragmentConstants.ContentFragmentType.INSTANT_BOOKING_CONFIRMATION_DIALOG_FRAGMENT:
                                                        return "SUCCESS_SERVER";
                                                    case FragmentConstants.ContentFragmentType.LOCATION_TIMELINE_NUX_FRAGMENT:
                                                        return "FAIL_NO_DATA";
                                                    case FragmentConstants.ContentFragmentType.CONFIGURE_BOOK_NOW_FRAGMENT_HOST:
                                                        return "ACTIVITY_NEW_INTENT";
                                                    case FragmentConstants.ContentFragmentType.CONSUMER_GET_QUOTE_FRAGMENT:
                                                        return "GRID_MEDIA_LOADED";
                                                    case FragmentConstants.ContentFragmentType.BOOK_NOW_CALENDAR_SETTINGS_HOST_FRAGMENT:
                                                        return "STORY_TRAY_MEDIA_LOADED";
                                                    case FragmentConstants.ContentFragmentType.FAQ_REORDER_QUESTIONS_FRAGMENT:
                                                        return "INTERACTION_OPEN_QRCODE_SCANNER";
                                                    case 438:
                                                        return "MEDIA_LOAD_CACHE";
                                                    case FragmentConstants.ContentFragmentType.GROUP_SUGGEST_TAGS_FRAGMENT:
                                                        return "MEDIA_LOAD_NETWORK";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_INSIGHTS_ADD_MODERATORS_FRAGMENT:
                                                        return "COMMENTS_LOAD_START";
                                                    case FragmentConstants.ContentFragmentType.FOOD_DRINK_TAG_FRAGMENT:
                                                        return "COMMENTS_LOAD_COMPLETE";
                                                    case FragmentConstants.ContentFragmentType.FAQ_VISITOR_VOTE_FRAGMENT:
                                                        return "MEDIA_LOAD_START";
                                                    case FragmentConstants.ContentFragmentType.COMMERCE_LIVE_VIDEO_FEED_FRAGMENT:
                                                        return "SCROLL_START";
                                                    case FragmentConstants.ContentFragmentType.CASUAL_GROUP_TAB_FRAGMENT:
                                                        return "QRCODE_SCANNER_SCAN_SUCCESS";
                                                    case FragmentConstants.ContentFragmentType.CONSUMER_GET_QUOTE_CONFIRMATION_FRAGMENT:
                                                        return "QRCODE_SCANNER_SCAN_FAILURE";
                                                    case FragmentConstants.ContentFragmentType.WORK_CONTACTS_FRAGMENT:
                                                        return "APP_CREATED_MAIN_PROCESS";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_TARGETED_TAB_SAVED_FRAGMENT:
                                                        return "FINISH_REGISTERING_JS_NATIVE_MODULES";
                                                    case FragmentConstants.ContentFragmentType.TOP_LIVE_LOADING_FRAGMENT:
                                                        return "NON_ANR";
                                                    case FragmentConstants.ContentFragmentType.INSTAGRAM_SIGN_UP_CONFIRMATION_FRAGMENT:
                                                        return "FINISH_INITIALIZING_JS_BRIDGE";
                                                    case FragmentConstants.ContentFragmentType.NEO_FRIEND_SEARCH_FRAGMENT:
                                                        return "FINISH_INJECTING_JS_HOOKS";
                                                    case FragmentConstants.ContentFragmentType.LOCATION_HISTORY_UPSELL_FRAGMENT:
                                                        return "FINISH_RUNNING_JS_INITIALIZER";
                                                    case FragmentConstants.ContentFragmentType.TOPIC_DISCOVERY_TABBED_FRAGMENT:
                                                        return "UI_RESPONSIVE";
                                                    case FragmentConstants.ContentFragmentType.CASUAL_GROUP_EDIT_FAVORITES_FRAGMENT:
                                                        return "PHASE_ONE_COMPLETE";
                                                    case FragmentConstants.ContentFragmentType.SHARING_SPACES_SHARESHEET_FRAGMENT:
                                                        return "PHASE_TWO_COMPLETE";
                                                    case FragmentConstants.ContentFragmentType.STORIES_ADMIN_TOOL_FRAGMENT:
                                                        return "LS_HEADER_START";
                                                    case FragmentConstants.ContentFragmentType.SPACE_CREATE_AND_EDIT_FRAGMENT:
                                                        return "LS_HEADER_LOAD";
                                                    case FragmentConstants.ContentFragmentType.SEARCH_RESULTS_DISCOVERY_FRAGMENT:
                                                        return "LS_BODY_START";
                                                    case FragmentConstants.ContentFragmentType.LEARNING_UNIT_FRAGMENT:
                                                        return "LS_BODY_LOAD";
                                                    case FragmentConstants.ContentFragmentType.VISUAL_POLL_TABBED_VOTES_FRAGMENT:
                                                        return "APP_ONCREATE";
                                                    case FragmentConstants.ContentFragmentType.ONAVO_PROTECT_OLD_BOOKMARK_INTERSTITIAL_FRAGMENT:
                                                        return "START_FETCH_IMAGE";
                                                    case FragmentConstants.ContentFragmentType.PAGES_MANAGER_NOTIFICATION_SETTINGS_FRAGMENT:
                                                        return "LOGGED_OUT";
                                                    case FragmentConstants.ContentFragmentType.STORIES_MUTED_OWNERS_LIST_FRAGMENT:
                                                        return "LS_HEADER_LAYOUT_SUCCESS";
                                                    case FragmentConstants.ContentFragmentType.SPACE_LIST_FRAGMENT:
                                                        return "STARTED_CAPTURE_SESSION";
                                                    case FragmentConstants.ContentFragmentType.PAGES_EDIT_COVER_AREA_FRAGMENT:
                                                        return "CAMERA_VIEW_READY";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_RULES_EDIT_GROUP_RULES_FRAGMENT:
                                                        return "CAMERA_FIRST_FRAME";
                                                    case FragmentConstants.ContentFragmentType.MULTI_EVENTS_INSTANCE_FRAGMENT:
                                                        return "PHOTO_BITMAP_READY";
                                                    case FragmentConstants.ContentFragmentType.OLD_PAGES_ADMIN_LAUNCHPOINT_FRAGMENT:
                                                        return ParamsMapEntry.END_MARKER;
                                                    case FragmentConstants.ContentFragmentType.FUNDRAISER_COUNTRY_SELECTOR_FRAGMENT:
                                                        return "FRAME_RENDERED";
                                                    case FragmentConstants.ContentFragmentType.MULTI_EVENTS_CALENDAR_FRAGMENT:
                                                        return "LS_MAP_LIST_START";
                                                    case FragmentConstants.ContentFragmentType.CONTENT_CHAINING_FRAGMENT:
                                                        return "LS_MAP_LIST_LOADED";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_CUSTOM_BADGE_MANAGEMENT_FRAGMENT:
                                                        return "LS_MAP_LIST_SUCCESS";
                                                    case FragmentConstants.ContentFragmentType.FB_REACT_FRAGMENT_WITH_SEARCH:
                                                        return "FETCH_THREAD_STARTED";
                                                    case FragmentConstants.ContentFragmentType.PAGE_ADMIN_APPOINTMENT_REQUEST_DETAIL_FRAGMENT:
                                                        return "FETCH_THREAD_SUCCEEDED";
                                                    case FragmentConstants.ContentFragmentType.DITTO_TIMELINE_FRAGMENT:
                                                        return "FETCH_THREAD_FAILED";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_MEMBERSHIP_ONE_SECTION_FULL_LIST_FRAGMENT:
                                                        return "STRUCTURE_FETCH_COMPLETE";
                                                    case FragmentConstants.ContentFragmentType.GLTF_FULLSCREEN_FRAGMENT:
                                                        return "FOLLOWED_SHOWS_FETCHED";
                                                    case FragmentConstants.ContentFragmentType.RESHARESHEET_GROUP_LIST_FRAGMENT:
                                                        return "ABANDONED";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_AUTO_APPROVAL_FRAGMENT:
                                                        return "ENTERED";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_TARGETED_TAB_GROUP_LIST_FRAGMENT:
                                                        return "LS_FILTER_START";
                                                    case FragmentConstants.ContentFragmentType.PAGES_PHOTO_PICKER_FRAGMENT:
                                                        return "LS_FILTER_LOAD";
                                                    case FragmentConstants.ContentFragmentType.FACECAST_INTEGRATED_SHARESHEET:
                                                        return "LS_FILTER_SUCCESS";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_INSIGHTS_TIPS_CENTER_FRAGMENT:
                                                        return "LS_SEARCH_RESULT_START";
                                                    case FragmentConstants.ContentFragmentType.PAGE_ADMIN_JOURNEY_CONTAINER_FRAGMENT:
                                                        return "LS_SEARCH_RESULT_LOAD";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_ADMIN_TAB_SETTINGS_FRAGMENT:
                                                        return "NETWORK_PARSE_START";
                                                    case FragmentConstants.ContentFragmentType.MOVIES_THEATER_SHOWTIME_PICKER_FRAGMENT:
                                                        return "NETWORK_REQUEST_SENT";
                                                    case FragmentConstants.ContentFragmentType.CONFIGURE_REQUEST_TIME_FRAGMENT_HOST:
                                                        return "COMPONENT_DATA_MODEL_UPDATE_START";
                                                    case FragmentConstants.ContentFragmentType.PAGES_TAB_FRAGMENT:
                                                        return "COMPONENT_DATA_MODEL_UPDATE_COMPLETE";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_REPORTED_POSTS_FRAGMENT:
                                                        return "HIGH_RES_PHOTO_FILE_READY";
                                                    case FragmentConstants.ContentFragmentType.ALBUM_PEOPLE_FRAGMENT:
                                                        return "NATIVE_PHOTO_BITMAP_READY";
                                                    case FragmentConstants.ContentFragmentType.CONTEXTUAL_PROFILE_FRAGMENT:
                                                        return "REEL_JSON_RECEIVED";
                                                    case FragmentConstants.ContentFragmentType.CASUAL_TAB_INACTIVE_GROUPS_FRAGMENT:
                                                        return "REEL_MEDIA_RECEIVED";
                                                    case FragmentConstants.ContentFragmentType.CREATOR_APP_HOME_FOLLOWING_FRAGMENT:
                                                        return "STORY_VIEWER_APPEAR";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_ADMIN_FRAGMENT:
                                                        return "FETCH_INBOX_STARTED";
                                                    case FragmentConstants.ContentFragmentType.LOCAL_SURFACE_TYPEAHEAD_FRAGMENT:
                                                        return "FETCH_INBOX_SUCCEEDED";
                                                    case FragmentConstants.ContentFragmentType.BOOKMARKS_EDIT_SHORTCUTS_FRAGMENT:
                                                        return "FETCH_INBOX_FAILED";
                                                    case FragmentConstants.ContentFragmentType.CASUAL_GROUP_INFO_FRAGMENT:
                                                        return "REQUEST_ADDED";
                                                    case FragmentConstants.ContentFragmentType.AGORA_SURFACE_FRAGMENT:
                                                        return "INIT_QE_START";
                                                    case FragmentConstants.ContentFragmentType.FOLLOWERS_FRAGMENT:
                                                        return "INIT_QE_END";
                                                    case FragmentConstants.ContentFragmentType.STANDALONE_CREATE_CASUAL_GROUP_FRAGMENT:
                                                        return "MULTIDEX_INSTALLED";
                                                    case 500:
                                                        return "PREPARE_CAMERA_SESSION";
                                                    case 501:
                                                        return "START_CAMERA_SESSION";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_RULES_VIEW_MODE_FRAGMENT:
                                                        return "DID_START_CAMERA_SESSION";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_RULES_EDIT_RULE_FRAGMENT:
                                                        return "FIRST_HARDWARE_FRAME";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_POST_TAG_CONSUMPTION_FRAGMENT:
                                                        return "START_RENDERING_FIRST_USER_FRAME";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_RULES_SUGGESTED_RULES_FRAGMENT:
                                                        return "FIRST_MEDIA_RENDERED";
                                                    case FragmentConstants.ContentFragmentType.WATCH_FOLLOW_INTERSTITIAL_FRAGMENT:
                                                        return "FIRST_CACHED_MEDIA_RENDERED";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_RULES_EDIT_MODE_FRAGMENT:
                                                        return "CAMERA_INITIALIZED";
                                                    case FragmentConstants.ContentFragmentType.FOX_AUDIENCE_SELECTOR:
                                                        return "HTTP_TRANSACTION_STARTED";
                                                    case FragmentConstants.ContentFragmentType.TOUR_PERMALINK_FRAGMENT:
                                                        return "REMOTE_PROCESS";
                                                    case 510:
                                                        return "PRE_REQUEST_SEND_CALLED";
                                                    case 511:
                                                        return "BRIDGE_STARTUP_WILL_START";
                                                    case 512:
                                                        return "BRIDGE_STARTUP_DID_FINISH";
                                                    case 513:
                                                        return "COVER_PHOTO_LOW_RES";
                                                    case FragmentConstants.ContentFragmentType.TYPEAHEAD_NEW:
                                                        return "COVER_PHOTO_HIGH_RES";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_TARGETED_TAB_SWIPEABLE_MALL_FRAGMENT:
                                                        return "PROFILE_PIC_LOW_RES";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_ALL_PHOTOS_FRAGMENT:
                                                        return "PROFILE_PIC_HIGH_RES";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_MODERN_CREATION_MEMBER_PICKER_FRAGMENT:
                                                        return "CONTEXT_ITEMS";
                                                    case FragmentConstants.ContentFragmentType.APPOINTMENT_CALENDAR_BLOCK_TIME_SLOT_FRAGMENT:
                                                        return "MEDIA_LOADED";
                                                    case 519:
                                                        return "STATE_UPDATE";
                                                    case FragmentConstants.ContentFragmentType.GROUP_CREATION_FRAGMENT:
                                                        return "ON_SHOW_LOGIN";
                                                    case FragmentConstants.ContentFragmentType.FOX_PROFILE:
                                                        return "EMPTY_REQUEST";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_REQUEST_GROUP_APPEAL_FRAGMENT:
                                                        return "METERED_CONNECTION";
                                                    case FragmentConstants.ContentFragmentType.FOX_EDIT_PROFILE:
                                                        return "VIDEO_DOWNSTREAM_FORMAT_CHANGED";
                                                    case FragmentConstants.ContentFragmentType.SAVED_LISTS_ADD_TO_COLLECTION_FRAGMENT:
                                                        return "DISABLED";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_ACTIVE_MEMBER_SUMMARY:
                                                        return "NO_METADATA";
                                                    case FragmentConstants.ContentFragmentType.GROUP_LINK_FOLDER_INTEGRATION_SELECTION:
                                                        return "INCOMPLETE_METADATA";
                                                    case FragmentConstants.ContentFragmentType.NEW_APPOINTMENT_DETAIL_FRAGMENT:
                                                        return "PHOTO_CAPTURE_READY";
                                                    case FragmentConstants.ContentFragmentType.PAGES_ADMIN_SURFACE_FRAGMENT_WRAPPER:
                                                        return "CAMERA_START_READY";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_NEW_REPORTED_POSTS_FRAGMENT:
                                                        return "DELAY_START";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_FLAGGED_MEMBER_POSTS_FRAGMENT:
                                                        return "DELAY_STOP";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_TARGETED_TAB_PENDING_REQUESTS_FRAGMENT:
                                                        return "RANK_START";
                                                    case FragmentConstants.ContentFragmentType.MOVIES_HOME_SEE_MORE_FRAGMENT:
                                                        return "RANK_STOP";
                                                    case FragmentConstants.ContentFragmentType.CREATOR_APP_SETTINGS_FRAGMENT:
                                                        return "DB_WRITE_START";
                                                    case FragmentConstants.ContentFragmentType.TIMELINE_OBSESSION_EDIT_FRAGMENT:
                                                        return "DB_WRITE_STOP";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_SUPPORT_THREAD_FRAGMENT:
                                                        return "ADD_STORY_TO_UI";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_TOPICS_EDIT_TOPICS_FRAGMENT:
                                                        return "WEBVIEW_URI_REDIRECTOR_CONSTRUCTION";
                                                    case 537:
                                                        return "UNINTERRUPTED";
                                                    case FragmentConstants.ContentFragmentType.FOX_TAGGING:
                                                        return "VIDEO_TOGGLE_FULL_SCREEN";
                                                    case FragmentConstants.ContentFragmentType.ONAVO_PROTECT_BOOKMARK_INTERSTITIAL_FRAGMENT:
                                                        return "CONFIGURE_START";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_SUPPORT_THREADS_LIST_FRAGMENT:
                                                        return "CONFIGURE_END";
                                                    case FragmentConstants.ContentFragmentType.PAGES_VOICE_SWITCHER_LIST_FRAGMENT:
                                                        return "BROWSER_OPEN";
                                                    case 542:
                                                        return "FIRST_DATA_RECEIVED";
                                                    case FragmentConstants.ContentFragmentType.AWESOMIZER_FACTCHECKERS_FRAGMENT:
                                                        return "RVP_WILL_LOAD";
                                                    case FragmentConstants.ContentFragmentType.DITTO_GROUPS_NT_TAB:
                                                        return "RVP_DID_LOAD";
                                                    case FragmentConstants.ContentFragmentType.PAGES_QR_CODE_CONNECT_OFFLINE_WIFI_FRAGMENT:
                                                        return "CACHE_WRITE_START";
                                                    case FragmentConstants.ContentFragmentType.TIMELINE_OBSESSION_PICK_FRAGMENT:
                                                        return "CACHE_WRITE_SUCCESS";
                                                    case FragmentConstants.ContentFragmentType.GROUP_INVITE_PAGE_FAN_FRAGMENT:
                                                        return "CACHE_WRITE_FAIL";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_DELETE_POST_WITH_FEEDBACK_FRAGMENT:
                                                        return "RVP_WILL_PAUSE";
                                                    case FragmentConstants.ContentFragmentType.EDIT_PIN_ORDER_GROUPS_FRAGMENT:
                                                        return "RVP_DID_PAUSE";
                                                    case FragmentConstants.ContentFragmentType.STORIES_ARCHIVE_SETTINGS_FRAGMENT:
                                                        return "RVP_WILL_PLAY";
                                                    case FragmentConstants.ContentFragmentType.NEARBY_FRIENDS_MAP_LIST_FRAGMENT:
                                                        return "RVP_DID_PLAY";
                                                    case FragmentConstants.ContentFragmentType.SAVE_DASHBOARD_RN_FRAGMENT:
                                                        return "RVP_WILL_PREPARE";
                                                    case FragmentConstants.ContentFragmentType.GROUP_SAFETY_HUB_FRAGMENT:
                                                        return "RVP_DID_PREPARE";
                                                    case FragmentConstants.ContentFragmentType.FOX_FEED_FRAGMENT:
                                                        return "RVP_WILL_RELOAD";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_POST_TAG_FEED_FRAGMENT:
                                                        return "RVP_DID_RELOAD";
                                                    case FragmentConstants.ContentFragmentType.MULTIMEDIA_VIEW_COMPOSER_FRAGMENT:
                                                        return "RVP_WILL_CREATE";
                                                    case FragmentConstants.ContentFragmentType.NOTIF_CONNECTION_CONTROLLER_FRAGMENT:
                                                        return "RVP_DID_CREATE";
                                                    case FragmentConstants.ContentFragmentType.PAGE_BROADCASTS_FRAGMENT:
                                                        return "RVP_WILL_FINISH_INFLATE";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_ALL_POST_TAGS_FRAGMENT:
                                                        return "RVP_DID_FINISH_INFLATE";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_CROSS_POST_RETRY_FRAGMENT:
                                                        return "RVP_WILL_MOUNT";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_INPERSON_CREATE_FRAGMENT:
                                                        return "RVP_DID_MOUNT";
                                                    case FragmentConstants.ContentFragmentType.VIDEOHOME_WATCH_TOPIC_FEED_FRAGMENT:
                                                        return "START_COMPRESSING_MESSAGE";
                                                    case FragmentConstants.ContentFragmentType.LOCAL_SERVICE_APPOINTMENT_CALENDAR:
                                                        return "MESSAGE_COMPRESSED";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_TOURNAMENT_CUSTOM_BADGE_FRAGMENT:
                                                        return "START_DECOMPRESSING_MESSAGE";
                                                    case FragmentConstants.ContentFragmentType.XNETWORK_POSTING_SHARESHEET_FRAGMENT:
                                                        return "MESSAGE_DECOMPRESSED";
                                                    case FragmentConstants.ContentFragmentType.GROUPS_INVITE_MEMBERS_WITH_NOTE_FRAGMENT:
                                                        return "START_SENDING_MESSAGE";
                                                    case FragmentConstants.ContentFragmentType.FOX_ONBOARDING_PROMPTS:
                                                        return "CAMERA_PREVIEW_FROZEN";
                                                    default:
                                                        switch (i) {
                                                            case 569:
                                                                return "VC_INIT_START";
                                                            case FragmentConstants.ContentFragmentType.PAGES_BROADCAST_COMPOSER_FRAGMENT:
                                                                return "VC_INIT_BEGIN";
                                                            case FragmentConstants.ContentFragmentType.GROUPS_TAB_DISCOVER_CATEGORIES_FRAGMENT:
                                                                return "VC_INIT_END";
                                                            case FragmentConstants.ContentFragmentType.PAGES_TRANSPARENCY_FRAGMENT:
                                                                return "VC_VIEW_DID_LOAD_BEGIN";
                                                            case FragmentConstants.ContentFragmentType.GET_QUOTE_QUESTIONNAIRE_SETUP_FRAGMENT:
                                                                return "VC_VIEW_DID_LOAD_END";
                                                            case FragmentConstants.ContentFragmentType.GROUPS_MEMBER_PROFILE_FRAGMENT:
                                                                return "ROOT_QUERY_START";
                                                            case FragmentConstants.ContentFragmentType.GROUPS_TAB_DISCOVER_LANDING_FRAGMENT:
                                                                return "ROOT_QUERY_SUCCESS";
                                                            case FragmentConstants.ContentFragmentType.ADS_TRANSPARENCY_FRAGMENT:
                                                                return "ROOT_QUERY_FAIL";
                                                            case FragmentConstants.ContentFragmentType.GROUPS_TAB_DISCOVER_CATEGORY_FRAGMENT:
                                                                return "CACHE_INITIALIZED";
                                                            case FragmentConstants.ContentFragmentType.MOVIES_HOME_SEARCH_FRAGMENT:
                                                                return "FETCH_BEGIN";
                                                            case FragmentConstants.ContentFragmentType.GROUPS_ADMIN_EDUCATION_CENTER_FRAGMENT:
                                                                return "FETCH_FINISHED";
                                                            case FragmentConstants.ContentFragmentType.GROUPS_TAB_HOME_FRAGMENT:
                                                                return "PARSING_FINISHED";
                                                            case FragmentConstants.ContentFragmentType.VIDEO_CREATOR_PAGES_GROUPS_CREATE_FRAGMENT:
                                                                return "LOAD_VIEW_END";
                                                            case FragmentConstants.ContentFragmentType.GROUPS_COVERPHOTO_REPOSITION_FRAGMENT:
                                                                return "LOAD_URL_BEGIN";
                                                            case FragmentConstants.ContentFragmentType.LOL_FEED_FRAGMENT:
                                                                return "LOAD_URL_END";
                                                            default:
                                                                switch (i) {
                                                                    case FragmentConstants.ContentFragmentType.GROUPS_TAB_ROOT_FRAGMENT:
                                                                        return "SHOULD_LOAD_URL_BEGIN";
                                                                    case FragmentConstants.ContentFragmentType.DAILY_LAUGH_CALL_FRAGMENT:
                                                                        return "SHOULD_LOAD_URL_END";
                                                                    case FragmentConstants.ContentFragmentType.NATIVE_FULLSCREEN_FEED_FRAGMENT:
                                                                        return "BLOCKING_RESOURCES_LOADED";
                                                                    case FragmentConstants.ContentFragmentType.TOPIC_TAG_MANAGEMENT_CENTER_EDIT_TOPIC_FRAGMENT:
                                                                        return "WEB_PAGE_LOADED";
                                                                    default:
                                                                        switch (i) {
                                                                            case FragmentConstants.ContentFragmentType.PAGES_ADMIN_SURFACE_NT_FRAGMENT:
                                                                                return "JS_TEARDOWN";
                                                                            case FragmentConstants.ContentFragmentType.PAGE_CREATION_NT_FRAGMENT:
                                                                                return "JS_SETUP";
                                                                            case FragmentConstants.ContentFragmentType.DAILY_LAUGH_PLAYER_ROOT_FRAGMENT:
                                                                                return "PREP_STATE";
                                                                            case FragmentConstants.ContentFragmentType.GROUPS_TAB_DISCOVER_SPOTLIGHT_FRAGMENT:
                                                                                return "PREP_FORMS";
                                                                            default:
                                                                                switch (i) {
                                                                                    case FragmentConstants.ContentFragmentType.PAGE_CREATION_NAME_FRAGMENT:
                                                                                        return "LOAD_MODULES";
                                                                                    case FragmentConstants.ContentFragmentType.EVENT_CUSTOM_SCHEDULE_DATE_FRAGMENT:
                                                                                        return "VIDEO_READY_TO_PLAY";
                                                                                    case FragmentConstants.ContentFragmentType.PAGE_CLIENT_IMPORT_NUX_FRAGMENT:
                                                                                        return "CANCEL_NAVIGATION";
                                                                                    default:
                                                                                        return "UNDEFINED_QPL_ACTION";
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
