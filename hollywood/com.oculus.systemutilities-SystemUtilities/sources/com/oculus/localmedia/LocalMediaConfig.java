package com.oculus.localmedia;

import java.util.List;

public class LocalMediaConfig {
    private List<String> mExcludeFolders;
    private List<String> mIncludeFolders;
    private MediaUriManager mMediaUriManager;
    private boolean mTestMode;

    public boolean isTestMode() {
        return this.mTestMode;
    }

    public List<String> getIncludeFolders() {
        return this.mIncludeFolders;
    }

    public boolean isSupportedPath(String filePath) {
        if (filePath == null) {
            return false;
        }
        String filePath2 = filePath.toLowerCase();
        for (String path : this.mExcludeFolders) {
            if (filePath2.startsWith(path)) {
                return false;
            }
        }
        if (this.mIncludeFolders.size() <= 0) {
            return true;
        }
        for (String path2 : this.mIncludeFolders) {
            if (filePath2.startsWith(path2)) {
                return true;
            }
        }
        return false;
    }

    public MediaUriManager getMediaUriManager() {
        return this.mMediaUriManager;
    }
}
