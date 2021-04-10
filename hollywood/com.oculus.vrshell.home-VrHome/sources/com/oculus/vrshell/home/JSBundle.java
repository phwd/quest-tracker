package com.oculus.vrshell.home;

public class JSBundle {
    private final String mChecksum;
    private final String mFileName;
    private final String mUrl;

    public JSBundle(String fileName, String url, String checksum) {
        this.mFileName = fileName;
        this.mUrl = url;
        this.mChecksum = checksum;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getChecksum() {
        return this.mChecksum;
    }

    public String toString() {
        return String.format("JSBundle{fileName: %s, url: %s, checksum: %s}", this.mFileName, this.mUrl, this.mChecksum);
    }
}
