package com.facebook.quicklog.identifiers;

public class FbliteColdStartup {
    public static final int ACTIVITY_DELEGATE_ONCREATE = 19922971;
    public static final int APPCONTROLLER_ONCREATE = 19922970;
    public static final int ASYNC_PREPARE_LOGIN_PROPERTIES = 19922968;
    public static final int CONNECTING_RUNNABLE = 19922964;
    public static final int CONNECTION_STATE_CONNECTED = 19922947;
    public static final int CONNECTION_STATE_CONNECTING = 19922946;
    public static final int CONNECTION_STATE_DISCONNECTED = 19922945;
    public static final int CONNECTION_STATE_DROPPED = 19922949;
    public static final int CONNECTION_STATE_SHUTTING_DOWN = 19922950;
    public static final int CONNECTION_STATE_STANDBY = 19922948;
    public static final int CONNECTION_STATE_TERMINATED = 19922951;
    public static final int CREATE_CLIENTSESSION = 19922972;
    public static final int CREATE_LOGIN_MESSAGE = 19922967;
    public static final int CREATE_SOCKET = 19922953;
    public static final int DNS_LOOKUP = 19922955;
    public static final int ENABLE_TLS = 19922954;
    public static final int ESTABLISH_SOCKET = 19922952;
    public static final int INITIALIZE_SESSION_OBJECTS = 19922973;
    public static final int INIT_BACKGROUND_TABLE_CACHE_ANDROID = 19922986;
    public static final int INIT_LAYOUT = 19922974;
    public static final short MODULE_ID = 304;
    public static final int PERFORM_LAYOUT_FIRST_SCREEN = 19922976;
    public static final int QPL_CREATE_RECTANGULAR_BACKGROUND_ON_LOAD = 19922987;
    public static final int QPL_LOAD_BACKGROOUND_TABLE_CACHE = 19922981;
    public static final int QPL_LOAD_RECTANGULAR_BACKGROUND = 19922984;
    public static final int QPL_STORE_BACKGROOUND_TABLE_CACHE = 19922982;
    public static final int QPL_STORE_RECTANGULAR_BACKGROUND = 19922983;
    public static final int RECEIVE_SCREEN_PARTS = 19922977;
    public static final int SEND_LOGIN_MESSAGE = 19922966;
    public static final int SESSION_TO_FIRST_SCREEN = 19922975;
    public static final int START_CLIENT_SESSION = 19922962;
    public static final int START_SEND_AND_RECEIVE_THREADS = 19922965;
    public static final int TRIGGER_CONNECTING_RUNNABLE = 19922963;
    public static final int TRIGGER_EARLY_CONNECTION = 19922959;
    public static final int TRIGGER_START_CLIENT_SESSION = 19922961;
    public static final int TRY_CONNECTING = 19922956;
    public static final int TRY_GET_EARLY_CONNECTION = 19922960;
    public static final int VERIFY_SSL = 19922958;
    public static final int WAIT_ASYNC_FONTS = 19922978;
    public static final int WAIT_ASYNC_LOGIN_PROPERTIES = 19922969;
    public static final int WAIT_FOR_HANDSHAKE = 19922957;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "FBLITE_COLD_STARTUP_CONNECTION_STATE_DISCONNECTED";
            case 2:
                return "FBLITE_COLD_STARTUP_CONNECTION_STATE_CONNECTING";
            case 3:
                return "FBLITE_COLD_STARTUP_CONNECTION_STATE_CONNECTED";
            case 4:
                return "FBLITE_COLD_STARTUP_CONNECTION_STATE_STANDBY";
            case 5:
                return "FBLITE_COLD_STARTUP_CONNECTION_STATE_DROPPED";
            case 6:
                return "FBLITE_COLD_STARTUP_CONNECTION_STATE_SHUTTING_DOWN";
            case 7:
                return "FBLITE_COLD_STARTUP_CONNECTION_STATE_TERMINATED";
            case 8:
                return "FBLITE_COLD_STARTUP_ESTABLISH_SOCKET";
            case 9:
                return "FBLITE_COLD_STARTUP_CREATE_SOCKET";
            case 10:
                return "FBLITE_COLD_STARTUP_ENABLE_TLS";
            case 11:
                return "FBLITE_COLD_STARTUP_DNS_LOOKUP";
            case 12:
                return "FBLITE_COLD_STARTUP_TRY_CONNECTING";
            case 13:
                return "FBLITE_COLD_STARTUP_WAIT_FOR_HANDSHAKE";
            case 14:
                return "FBLITE_COLD_STARTUP_VERIFY_SSL";
            case 15:
                return "FBLITE_COLD_STARTUP_TRIGGER_EARLY_CONNECTION";
            case 16:
                return "FBLITE_COLD_STARTUP_TRY_GET_EARLY_CONNECTION";
            case 17:
                return "FBLITE_COLD_STARTUP_TRIGGER_START_CLIENT_SESSION";
            case 18:
                return "FBLITE_COLD_STARTUP_START_CLIENT_SESSION";
            case 19:
                return "FBLITE_COLD_STARTUP_TRIGGER_CONNECTING_RUNNABLE";
            case 20:
                return "FBLITE_COLD_STARTUP_CONNECTING_RUNNABLE";
            case 21:
                return "FBLITE_COLD_STARTUP_START_SEND_AND_RECEIVE_THREADS";
            case 22:
                return "FBLITE_COLD_STARTUP_SEND_LOGIN_MESSAGE";
            case 23:
                return "FBLITE_COLD_STARTUP_CREATE_LOGIN_MESSAGE";
            case 24:
                return "FBLITE_COLD_STARTUP_ASYNC_PREPARE_LOGIN_PROPERTIES";
            case 25:
                return "FBLITE_COLD_STARTUP_WAIT_ASYNC_LOGIN_PROPERTIES";
            case 26:
                return "FBLITE_COLD_STARTUP_APPCONTROLLER_ONCREATE";
            case 27:
                return "FBLITE_COLD_STARTUP_ACTIVITY_DELEGATE_ONCREATE";
            case 28:
                return "FBLITE_COLD_STARTUP_CREATE_CLIENTSESSION";
            case 29:
                return "FBLITE_COLD_STARTUP_INITIALIZE_SESSION_OBJECTS";
            case 30:
                return "FBLITE_COLD_STARTUP_INIT_LAYOUT";
            case 31:
                return "FBLITE_COLD_STARTUP_SESSION_TO_FIRST_SCREEN";
            case 32:
                return "FBLITE_COLD_STARTUP_PERFORM_LAYOUT_FIRST_SCREEN";
            case 33:
                return "FBLITE_COLD_STARTUP_RECEIVE_SCREEN_PARTS";
            case 34:
                return "FBLITE_COLD_STARTUP_WAIT_ASYNC_FONTS";
            case 35:
            case 36:
            case 41:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 37:
                return "FBLITE_COLD_STARTUP_QPL_LOAD_BACKGROOUND_TABLE_CACHE";
            case 38:
                return "FBLITE_COLD_STARTUP_QPL_STORE_BACKGROOUND_TABLE_CACHE";
            case 39:
                return "FBLITE_COLD_STARTUP_QPL_STORE_RECTANGULAR_BACKGROUND";
            case 40:
                return "FBLITE_COLD_STARTUP_QPL_LOAD_RECTANGULAR_BACKGROUND";
            case 42:
                return "FBLITE_COLD_STARTUP_INIT_BACKGROUND_TABLE_CACHE_ANDROID";
            case 43:
                return "FBLITE_COLD_STARTUP_QPL_CREATE_RECTANGULAR_BACKGROUND_ON_LOAD";
        }
    }
}
