package com.oculus.localmedia;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class LocalMediaConfig {
    private List<String> mExcludeFolders;
    private List<String> mIncludeFolders;
    private MediaUriManager mMediaUriManager;
    private boolean mTestMode;

    public static Builder builder() {
        return new Builder();
    }

    public LocalMediaConfig(MediaUriManager mediaUriManager, List<String> includeFolders, List<String> excludeFolders, boolean testMode) {
        this.mMediaUriManager = mediaUriManager;
        this.mIncludeFolders = includeFolders;
        this.mExcludeFolders = excludeFolders;
        this.mTestMode = testMode;
    }

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

    public static class Builder {
        private List<String> mExcludeFolders;
        private List<String> mIncludeFolders;
        private MediaUriManager mMediaUriManager;
        private boolean mTestMode;

        private Builder() {
            this.mIncludeFolders = new ArrayList();
            this.mExcludeFolders = new ArrayList();
            this.mTestMode = false;
        }

        public Builder setMediaUriManager(MediaUriManager mediaUriManager) {
            this.mMediaUriManager = mediaUriManager;
            return this;
        }

        public Builder includeFolder(String path) throws Exception {
            if (this.mExcludeFolders.size() > 0) {
                Log.e(LocalMediaManager.TAG, "Can't use both includeFolder and excludeFolder at the same time.");
            } else {
                this.mIncludeFolders.add(path.toLowerCase());
            }
            return this;
        }

        public Builder excludeFolder(String path) throws Exception {
            if (this.mIncludeFolders.size() > 0) {
                Log.e(LocalMediaManager.TAG, "Can't use both includeFolder and excludeFolder at the same time.");
            } else {
                this.mExcludeFolders.add(path.toLowerCase());
            }
            return this;
        }

        public Builder enableTestMode() throws Exception {
            this.mTestMode = true;
            return this;
        }

        public LocalMediaConfig build() {
            return new LocalMediaConfig(this.mMediaUriManager, this.mIncludeFolders, this.mExcludeFolders, this.mTestMode);
        }
    }
}
