package com.oculus.systemdialog;

public final class DialogIcon {
    private static final String CHECK_ALT_IPC_STRING = "check_alt";
    private static final String INFO_IPC_STRING = "info";
    private static final String SETTINGS_IPC_STRING = "settings";
    private static final String SPINNER_IPC_STRING = "spinner";

    public enum IconButton {
        INFO(DialogIcon.INFO_IPC_STRING),
        SETTINGS(DialogIcon.SETTINGS_IPC_STRING);
        
        private final String mIPCString;

        private IconButton(String str) {
            this.mIPCString = str;
        }

        public String getIPCString() {
            return this.mIPCString;
        }
    }

    public enum InformationBox {
        CHECK_ALT(DialogIcon.CHECK_ALT_IPC_STRING),
        INFO(DialogIcon.INFO_IPC_STRING),
        SPINNER(DialogIcon.SPINNER_IPC_STRING);
        
        private final String mIPCString;

        private InformationBox(String str) {
            this.mIPCString = str;
        }

        public String getIPCString() {
            return this.mIPCString;
        }
    }

    private DialogIcon() {
    }
}
