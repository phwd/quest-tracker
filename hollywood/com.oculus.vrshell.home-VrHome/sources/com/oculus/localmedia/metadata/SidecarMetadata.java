package com.oculus.localmedia.metadata;

public class SidecarMetadata {
    private String mFormat;
    private String mTitle;

    public String getTitle() {
        return this.mTitle;
    }

    public String getFormat() {
        return this.mFormat;
    }

    private SidecarMetadata(String title, String format) {
        this.mTitle = title;
        this.mFormat = format;
    }

    public String toString() {
        return "SidecarMetadata{mTitle='" + this.mTitle + '\'' + ", mFormat='" + this.mFormat + '\'' + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String mFormat;
        private String mTitle;

        public void setTitle(String title) {
            this.mTitle = title;
        }

        public void setFormat(String format) {
            this.mFormat = format;
        }

        public SidecarMetadata build() {
            return new SidecarMetadata(this.mTitle, this.mFormat);
        }
    }
}
