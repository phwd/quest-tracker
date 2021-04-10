package com.oculus.localmedia.metadata;

public class SidecarMetadata {
    public String mFormat;
    public String mTitle;

    public static class Builder {
        public String mFormat;
        public String mTitle;

        public SidecarMetadata build() {
            return new SidecarMetadata(this.mTitle, this.mFormat);
        }

        public void setFormat(String str) {
            this.mFormat = str;
        }

        public void setTitle(String str) {
            this.mTitle = str;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SidecarMetadata{mTitle='");
        sb.append(this.mTitle);
        sb.append('\'');
        sb.append(", mFormat='");
        sb.append(this.mFormat);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getFormat() {
        return this.mFormat;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public SidecarMetadata(String str, String str2) {
        this.mTitle = str;
        this.mFormat = str2;
    }
}
