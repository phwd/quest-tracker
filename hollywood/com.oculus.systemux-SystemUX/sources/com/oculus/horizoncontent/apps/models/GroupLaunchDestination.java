package com.oculus.horizoncontent.apps.models;

public class GroupLaunchDestination {
    private Application mApplication;
    private String mDisplayName;
    private String mId;
    private String mImageUrl;

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String str) {
        this.mImageUrl = str;
    }

    public Application getApplication() {
        return this.mApplication;
    }

    public void setApplication(Application application) {
        this.mApplication = application;
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

    public static class Application {
        private final String mDisplayName;
        private final String mId;
        private final String mImageUrl;

        public Application(String str, String str2, String str3) {
            this.mId = str;
            this.mDisplayName = str2;
            this.mImageUrl = str3;
        }

        public String getId() {
            return this.mId;
        }

        public String getDisplayName() {
            return this.mDisplayName;
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
    }
}
