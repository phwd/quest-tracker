package com.apple.dnssd;

public abstract class DNSSDException extends Exception {
    public static final int ALREADY_REGISTERED = -65547;
    public static final int BADKEY = -65561;
    public static final int BADSIG = -65560;
    public static final int BADTIME = -65559;
    public static final int BAD_FLAGS = -65543;
    public static final int BAD_INTERFACE_INDEX = -65552;
    public static final int BAD_PARAM = -65540;
    public static final int BAD_REFERENCE = -65541;
    public static final int BAD_STATE = -65542;
    public static final int DOUBLENAT = -65558;
    public static final int FIREWALL = -65550;
    public static final int INCOMPATIBLE = -65551;
    public static final int INVALID = -65549;
    public static final int NAME_CONFLICT = -65548;
    public static final int NATPORTMAPPINGDISABLED = -65565;
    public static final int NATPORTMAPPINGUNSUPPORTED = -65564;
    public static final int NATTRAVERSAL = -65557;
    public static final int NOAUTH = -65555;
    public static final int NOSUCHKEY = -65556;
    public static final int NOSUCHRECORD = -65554;
    public static final int NOT_INITIALIZED = -65545;
    public static final int NO_CACHE = -65546;
    public static final int NO_ERROR = 0;
    public static final int NO_MEMORY = -65539;
    public static final int NO_SUCH_NAME = -65538;
    public static final int REFUSED = -65553;
    public static final int SERVICENOTRUNNING = -65563;
    public static final int TRANSIENT = -65562;
    public static final int UNKNOWN = -65537;
    public static final int UNSUPPORTED = -65544;

    public abstract int getErrorCode();
}
