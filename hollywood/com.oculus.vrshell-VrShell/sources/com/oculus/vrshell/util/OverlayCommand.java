package com.oculus.vrshell.util;

public class OverlayCommand {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String[] stringArgs;

    public OverlayCommand(String str, String... strArr) {
        int i = 2;
        if (str != null) {
            this.stringArgs = new String[(strArr.length + 2)];
            String[] strArr2 = this.stringArgs;
            strArr2[0] = "command";
            strArr2[1] = str;
        } else {
            this.stringArgs = new String[strArr.length];
            i = 0;
        }
        for (String str2 : strArr) {
            this.stringArgs[i] = str2;
            i++;
        }
    }

    public String[] toStringArray() {
        return this.stringArgs;
    }
}
