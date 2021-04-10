package com.oculus.horizoncontent.social;

import java.util.Objects;

public class SocialDeeplinkPresence {
    private String mAppID;
    private String mDeepLinkLaunchParams;
    private String mPackageName;

    public SocialDeeplinkPresence(String str, String str2, String str3) {
        this.mAppID = str;
        this.mPackageName = str2;
        this.mDeepLinkLaunchParams = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SocialDeeplinkPresence socialDeeplinkPresence = (SocialDeeplinkPresence) obj;
        return Objects.equals(this.mAppID, socialDeeplinkPresence.mAppID) && Objects.equals(this.mPackageName, socialDeeplinkPresence.mPackageName) && Objects.equals(this.mDeepLinkLaunchParams, socialDeeplinkPresence.mDeepLinkLaunchParams);
    }

    public int hashCode() {
        return Objects.hash(this.mAppID, this.mPackageName, this.mDeepLinkLaunchParams);
    }

    public String getAppID() {
        return this.mAppID;
    }

    public String getDeepLinkLaunchParams() {
        return this.mDeepLinkLaunchParams;
    }

    public String getPackageName() {
        return this.mPackageName;
    }
}
