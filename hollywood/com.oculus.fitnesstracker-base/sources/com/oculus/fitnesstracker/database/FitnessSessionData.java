package com.oculus.fitnesstracker.database;

public class FitnessSessionData {
    private String mActivity;
    private String mPackageName;
    private long mStartDate;

    public FitnessSessionData(String str, String str2, long j) {
        this.mActivity = str;
        this.mPackageName = str2;
        this.mStartDate = j;
    }

    public String getActivity() {
        return this.mActivity;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public long getStartDate() {
        return this.mStartDate;
    }

    public String toString() {
        return "Session{mPackageName=" + this.mPackageName + ", mStartDate=" + this.mStartDate + ", mActivity=" + this.mActivity + '}';
    }
}
