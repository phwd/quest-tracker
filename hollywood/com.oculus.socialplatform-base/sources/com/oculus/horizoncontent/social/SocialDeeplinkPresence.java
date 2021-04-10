package com.oculus.horizoncontent.social;

import java.util.Objects;

public class SocialDeeplinkPresence {
    public String mAppID;
    public String mDeepLinkLaunchParams;
    public String mPackageName;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SocialDeeplinkPresence socialDeeplinkPresence = (SocialDeeplinkPresence) obj;
            if (!Objects.equals(this.mAppID, socialDeeplinkPresence.mAppID) || !Objects.equals(this.mPackageName, socialDeeplinkPresence.mPackageName) || !Objects.equals(this.mDeepLinkLaunchParams, socialDeeplinkPresence.mDeepLinkLaunchParams)) {
                return false;
            }
        }
        return true;
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

    public int hashCode() {
        return Objects.hash(this.mAppID, this.mPackageName, this.mDeepLinkLaunchParams);
    }

    public SocialDeeplinkPresence(String str, String str2, String str3) {
        this.mAppID = str;
        this.mPackageName = str2;
        this.mDeepLinkLaunchParams = str3;
    }
}
