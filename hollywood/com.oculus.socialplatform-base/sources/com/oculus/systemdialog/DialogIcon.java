package com.oculus.systemdialog;

public final class DialogIcon {
    public static final String CHECK_ALT_IPC_STRING = "check_alt";
    public static final String INFO_IPC_STRING = "info";
    public static final String SETTINGS_IPC_STRING = "settings";
    public static final String SPINNER_IPC_STRING = "spinner";

    public enum IconButton {
        INFO(DialogIcon.INFO_IPC_STRING),
        SETTINGS("settings");
        
        public final String mIPCString;

        public String getIPCString() {
            return this.mIPCString;
        }

        /* access modifiers changed from: public */
        IconButton(String str) {
            this.mIPCString = str;
        }
    }

    public enum InformationBox {
        CHECK_ALT(DialogIcon.CHECK_ALT_IPC_STRING),
        INFO(DialogIcon.INFO_IPC_STRING),
        SPINNER(DialogIcon.SPINNER_IPC_STRING);
        
        public final String mIPCString;

        public String getIPCString() {
            return this.mIPCString;
        }

        /* access modifiers changed from: public */
        InformationBox(String str) {
            this.mIPCString = str;
        }
    }
}
