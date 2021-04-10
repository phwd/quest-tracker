package com.oculus.localmedia.metadata;

public class FilenameMetadata {
    private String mFormat;
    private float mFovX;
    private boolean mIsCardboard;
    private String mPresentationFormat;
    private String mTitle;

    public String getTitle() {
        return this.mTitle;
    }

    public String getFormat() {
        return this.mFormat;
    }

    public String getPresentationFormat() {
        return this.mPresentationFormat;
    }

    public float getFovX() {
        return this.mFovX;
    }

    public boolean isCardboard() {
        return this.mIsCardboard;
    }

    private FilenameMetadata(String title, String format, String presentationFormat, float fovX, boolean isCardboard) {
        this.mTitle = title;
        this.mFormat = format;
        this.mPresentationFormat = presentationFormat;
        this.mFovX = fovX;
        this.mIsCardboard = isCardboard;
    }

    public String toString() {
        return "FilenameMetadata{mTitle='" + this.mTitle + '\'' + ", mFormat='" + this.mFormat + '\'' + ", mPresentationFormat='" + this.mPresentationFormat + '\'' + ", mFovX=" + this.mFovX + ", mIsCardboard=" + this.mIsCardboard + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String mFormat;
        private float mFovX;
        private boolean mIsCardboard;
        private String mPresentationFormat;
        private String mTitle;

        public void setTitle(String mTitle2) {
            this.mTitle = mTitle2;
        }

        public void setFormat(String mFormat2) {
            this.mFormat = mFormat2;
        }

        public void setPresentationFormat(String mPresentationFormat2) {
            this.mPresentationFormat = mPresentationFormat2;
        }

        public void setFovX(float mFovX2) {
            this.mFovX = mFovX2;
        }

        public void setIsCardboard(boolean isCardboard) {
            this.mIsCardboard = isCardboard;
        }

        public FilenameMetadata build() {
            return new FilenameMetadata(this.mTitle, this.mFormat, this.mPresentationFormat, this.mFovX, this.mIsCardboard);
        }
    }
}
