package libraries.marauder.analytics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnalyticsSession {
    public String mAppId;
    public String mAppVersion;
    public String mBuildNumber;
    public List<IAnalyticsEvent> mCurrentBatch = new ArrayList(50);
    public String mCustomUserId;
    public String mDeviceId;
    public String mFacebookUserId;
    public int mSequence = 0;
    public UUID mSessionId;
    public long mTime;

    public synchronized UUID getSessionId() {
        UUID uuid;
        uuid = this.mSessionId;
        if (uuid == null) {
            uuid = UUID.randomUUID();
            this.mSessionId = uuid;
        }
        return uuid;
    }

    public void addEvent(IAnalyticsEvent iAnalyticsEvent) {
        this.mCurrentBatch.add(iAnalyticsEvent);
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public String getBuildNumber() {
        return this.mBuildNumber;
    }

    public List<IAnalyticsEvent> getCurrentBatch() {
        return this.mCurrentBatch;
    }

    public String getCustomUserId() {
        return this.mCustomUserId;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public String getFacebookUserId() {
        return this.mFacebookUserId;
    }

    public int getSequence() {
        return this.mSequence;
    }

    public long getTime() {
        return this.mTime;
    }

    public void startNewBatch() {
        this.mCurrentBatch.clear();
        this.mSequence++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ID: ");
        sb.append(getSessionId());
        sb.append(" Sequence: ");
        sb.append(this.mSequence);
        sb.append(" (");
        sb.append(this.mCurrentBatch.size());
        sb.append(" events)\n");
        sb.append("Device ID: ");
        sb.append(this.mDeviceId);
        sb.append(" FB: ");
        sb.append(this.mFacebookUserId);
        sb.append(" Custom ID: ");
        sb.append(this.mCustomUserId);
        sb.append(" Version: ");
        sb.append(this.mAppVersion);
        sb.append(" Build Number: ");
        sb.append(this.mBuildNumber);
        return sb.toString();
    }

    public void setAppId(String str) {
        this.mAppId = str;
    }

    public void setAppVersion(String str) {
        this.mAppVersion = str;
    }

    public void setBuildNumber(String str) {
        this.mBuildNumber = str;
    }

    public void setCustomUserId(String str) {
        this.mCustomUserId = str;
    }

    public void setDeviceId(String str) {
        this.mDeviceId = str;
    }

    public void setFacebookUserId(String str) {
        this.mFacebookUserId = str;
    }

    public void setTime(long j) {
        this.mTime = j;
    }
}
