package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppNetSessionId {
    private long mAppBackgroundCount;
    private long mAppForegroundCount;
    private final String mConnectionId;
    private String mExtraFields = "";
    private final String mLocationLoggingId;
    private long mNetworkChangeCount;
    private final String mNetworkLoggingId;
    private final String mProcessId;
    private final String mTransactionId;
    private final String mUserSessionId;

    public AppNetSessionId(String str, String str2, String str3, String str4, String str5, String str6, long j, long j2, long j3) {
        this.mNetworkLoggingId = str;
        this.mLocationLoggingId = str2;
        this.mUserSessionId = str3;
        this.mProcessId = str4;
        this.mConnectionId = str5;
        this.mTransactionId = str6;
        this.mNetworkChangeCount = j;
        this.mAppForegroundCount = j2;
        this.mAppBackgroundCount = j3;
    }

    public AppNetSessionId(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, long j2, long j3) {
        this.mNetworkLoggingId = str;
        this.mLocationLoggingId = str2;
        this.mUserSessionId = str3;
        this.mProcessId = str4;
        this.mConnectionId = str5;
        this.mTransactionId = str6;
        this.mExtraFields = str7;
        this.mNetworkChangeCount = j;
        this.mAppForegroundCount = j2;
        this.mAppBackgroundCount = j3;
    }

    public String networkLoggingId() {
        return this.mNetworkLoggingId;
    }

    public String locationLoggingId() {
        return this.mLocationLoggingId;
    }

    public String userSessionId() {
        return this.mUserSessionId;
    }

    public String processId() {
        return this.mProcessId;
    }

    public String connectionId() {
        return this.mConnectionId;
    }

    public String transactionId() {
        return this.mTransactionId;
    }

    public String extraFields() {
        return this.mExtraFields;
    }

    public long networkChangeCount() {
        return this.mNetworkChangeCount;
    }

    public long appForegroundCount() {
        return this.mAppForegroundCount;
    }

    public long appBackgroundCount() {
        return this.mAppBackgroundCount;
    }

    public void extraFields(String str) {
        this.mExtraFields = str;
    }

    public void networkChangeCount(long j) {
        this.mNetworkChangeCount = j;
    }

    public void appForegroundCount(long j) {
        this.mAppForegroundCount = j;
    }

    public void appBackgroundCount(long j) {
        this.mAppBackgroundCount = j;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof AppNetSessionId)) {
            return false;
        }
        AppNetSessionId appNetSessionId = (AppNetSessionId) obj;
        if (!this.mNetworkLoggingId.equals(appNetSessionId.networkLoggingId()) || !this.mLocationLoggingId.equals(appNetSessionId.locationLoggingId()) || !this.mUserSessionId.equals(appNetSessionId.userSessionId()) || !this.mProcessId.equals(appNetSessionId.processId()) || !this.mConnectionId.equals(appNetSessionId.connectionId()) || !this.mTransactionId.equals(appNetSessionId.transactionId()) || !this.mExtraFields.equals(appNetSessionId.extraFields()) || this.mNetworkChangeCount != appNetSessionId.networkChangeCount() || this.mAppForegroundCount != appNetSessionId.appForegroundCount() || this.mAppBackgroundCount != appNetSessionId.appBackgroundCount()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mNetworkLoggingId.hashCode() + this.mLocationLoggingId.hashCode() + this.mUserSessionId.hashCode() + this.mProcessId.hashCode() + this.mConnectionId.hashCode() + this.mTransactionId.hashCode() + this.mExtraFields.hashCode() + Long.valueOf(this.mNetworkChangeCount).hashCode() + Long.valueOf(this.mAppForegroundCount).hashCode() + Long.valueOf(this.mAppBackgroundCount).hashCode();
    }

    public String toStringPrivacyUnaware() {
        String str = (((((((((((((("nid=" + this.mNetworkLoggingId + ";") + "uid=") + this.mUserSessionId + ";") + "pid=") + this.mProcessId + ";") + "tid=") + this.mTransactionId + ";") + "nc=") + this.mNetworkChangeCount + ";") + "fc=") + this.mAppForegroundCount + ";") + "bc=") + this.mAppBackgroundCount + ";") + "cid=") + this.mConnectionId;
        if (this.mExtraFields.isEmpty()) {
            return str;
        }
        return str + this.mExtraFields;
    }
}
