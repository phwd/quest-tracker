package com.oculus.localmedia.metadata;

public class FilenameMetadata {
    public String mFormat;
    public float mFovX;
    public boolean mIsCardboard;
    public String mPresentationFormat;
    public String mTitle;

    public static class Builder {
        public String mFormat;
        public float mFovX;
        public boolean mIsCardboard;
        public String mPresentationFormat;
        public String mTitle;

        public FilenameMetadata build() {
            return new FilenameMetadata(this.mTitle, this.mFormat, this.mPresentationFormat, this.mFovX, this.mIsCardboard);
        }

        public void setFormat(String str) {
            this.mFormat = str;
        }

        public void setFovX(float f) {
            this.mFovX = f;
        }

        public void setIsCardboard(boolean z) {
            this.mIsCardboard = z;
        }

        public void setPresentationFormat(String str) {
            this.mPresentationFormat = str;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FilenameMetadata{mTitle='");
        sb.append(this.mTitle);
        sb.append('\'');
        sb.append(", mFormat='");
        sb.append(this.mFormat);
        sb.append('\'');
        sb.append(", mPresentationFormat='");
        sb.append(this.mPresentationFormat);
        sb.append('\'');
        sb.append(", mFovX=");
        sb.append(this.mFovX);
        sb.append(", mIsCardboard=");
        sb.append(this.mIsCardboard);
        sb.append('}');
        return sb.toString();
    }

    public String getFormat() {
        return this.mFormat;
    }

    public float getFovX() {
        return this.mFovX;
    }

    public String getPresentationFormat() {
        return this.mPresentationFormat;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isCardboard() {
        return this.mIsCardboard;
    }

    public FilenameMetadata(String str, String str2, String str3, float f, boolean z) {
        this.mTitle = str;
        this.mFormat = str2;
        this.mPresentationFormat = str3;
        this.mFovX = f;
        this.mIsCardboard = z;
    }
}
