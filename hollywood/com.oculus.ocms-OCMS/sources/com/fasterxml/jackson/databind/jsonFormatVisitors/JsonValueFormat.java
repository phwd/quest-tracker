package com.fasterxml.jackson.databind.jsonFormatVisitors;

public enum JsonValueFormat {
    DATE_TIME {
        public String toString() {
            return "date-time";
        }
    },
    DATE {
        public String toString() {
            return "date";
        }
    },
    TIME {
        public String toString() {
            return "time";
        }
    },
    UTC_MILLISEC {
        public String toString() {
            return "utc-millisec";
        }
    },
    REGEX {
        public String toString() {
            return "regex";
        }
    },
    COLOR {
        public String toString() {
            return "color";
        }
    },
    STYLE {
        public String toString() {
            return "style";
        }
    },
    PHONE {
        public String toString() {
            return "phone";
        }
    },
    URI {
        public String toString() {
            return "uri";
        }
    },
    EMAIL {
        public String toString() {
            return "email";
        }
    },
    IP_ADDRESS {
        public String toString() {
            return "ip-address";
        }
    },
    IPV6 {
        public String toString() {
            return "ipv6";
        }
    },
    HOST_NAME {
        public String toString() {
            return "host-name";
        }
    }
}
