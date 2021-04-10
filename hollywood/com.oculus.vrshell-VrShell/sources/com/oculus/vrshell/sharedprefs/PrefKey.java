package com.oculus.vrshell.sharedprefs;

public class PrefKey {
    private String mFullKey;
    private String mKey;
    private PrefKey mParent;

    PrefKey(String str) {
        this.mParent = null;
        this.mKey = str;
    }

    PrefKey(PrefKey prefKey, String str) {
        this.mParent = prefKey;
        this.mKey = str;
    }

    public String getFullKey() {
        String str;
        if (this.mFullKey == null) {
            if (this.mParent == null) {
                str = this.mKey;
            } else {
                str = this.mParent.getFullKey() + this.mKey;
            }
            this.mFullKey = str;
        }
        return this.mFullKey;
    }

    public PrefKey extend(String str) {
        return new PrefKey(this, str);
    }

    public boolean startsWith(PrefKey prefKey) {
        if (prefKey == null) {
            return false;
        }
        return getFullKey().startsWith(prefKey.getFullKey());
    }
}
