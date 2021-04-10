package com.oculus.vrshell.sharedprefs;

import X.AnonymousClass006;

public class PrefKey {
    public String mFullKey;
    public String mKey;
    public PrefKey mParent;

    public PrefKey extend(String str) {
        return new PrefKey(this, str);
    }

    public String getFullKey() {
        String str = this.mFullKey;
        if (str == null) {
            PrefKey prefKey = this.mParent;
            if (prefKey == null) {
                str = this.mKey;
            } else {
                str = AnonymousClass006.A07(prefKey.getFullKey(), this.mKey);
            }
            this.mFullKey = str;
        }
        return str;
    }

    public boolean startsWith(PrefKey prefKey) {
        if (prefKey == null) {
            return false;
        }
        return getFullKey().startsWith(prefKey.getFullKey());
    }

    public PrefKey(PrefKey prefKey, String str) {
        this.mParent = prefKey;
        this.mKey = str;
    }

    public PrefKey(String str) {
        this.mParent = null;
        this.mKey = str;
    }
}
