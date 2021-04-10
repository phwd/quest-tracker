package com.oculus.horizoncontent.apps.models;

public class GroupLaunchDestination {
    public Application mApplication;
    public String mDisplayName;
    public String mId;
    public String mImageUrl;

    public static class Application {
        public final String mDisplayName;
        public final String mId;
        public final String mImageUrl;

        public String getDisplayName() {
            return this.mDisplayName;
        }

        public String getId() {
            return this.mId;
        }

        public String getImageUrl() {
            return this.mImageUrl;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("Application{");
            stringBuffer.append("mId='");
            stringBuffer.append(this.mId);
            stringBuffer.append('\'');
            stringBuffer.append(", mDisplayName='");
            stringBuffer.append(this.mDisplayName);
            stringBuffer.append('\'');
            stringBuffer.append(", mImageUrl='");
            stringBuffer.append(this.mImageUrl);
            stringBuffer.append('\'');
            stringBuffer.append('}');
            return stringBuffer.toString();
        }

        public Application(String str, String str2, String str3) {
            this.mId = str;
            this.mDisplayName = str2;
            this.mImageUrl = str3;
        }
    }

    public Application getApplication() {
        return this.mApplication;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getId() {
        return this.mId;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GroupLaunchDestination{");
        stringBuffer.append("mId='");
        stringBuffer.append(this.mId);
        stringBuffer.append('\'');
        stringBuffer.append(", mDisplayName='");
        stringBuffer.append(this.mDisplayName);
        stringBuffer.append('\'');
        stringBuffer.append(", mImageUrl='");
        stringBuffer.append(this.mImageUrl);
        stringBuffer.append('\'');
        stringBuffer.append(", mApplication='");
        stringBuffer.append(this.mApplication);
        stringBuffer.append('\'');
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public void setApplication(Application application) {
        this.mApplication = application;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }
}
