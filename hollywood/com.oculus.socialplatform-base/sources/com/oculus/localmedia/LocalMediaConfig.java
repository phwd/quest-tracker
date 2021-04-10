package com.oculus.localmedia;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocalMediaConfig {
    public List<String> mExcludeFolders;
    public List<String> mIncludeFolders;
    public MediaUriManager mMediaUriManager;
    public boolean mTestMode;

    public static class Builder {
        public List<String> mExcludeFolders;
        public List<String> mIncludeFolders;
        public MediaUriManager mMediaUriManager;
        public boolean mTestMode;

        public Builder enableTestMode() throws Exception {
            this.mTestMode = true;
            return this;
        }

        public LocalMediaConfig build() {
            return new LocalMediaConfig(this.mMediaUriManager, this.mIncludeFolders, this.mExcludeFolders, this.mTestMode);
        }

        public Builder excludeFolder(String str) throws Exception {
            if (this.mIncludeFolders.size() > 0) {
                Log.e(LocalMediaManager.TAG, "Can't use both includeFolder and excludeFolder at the same time.");
                return this;
            }
            this.mExcludeFolders.add(str.toLowerCase());
            return this;
        }

        public Builder includeFolder(String str) throws Exception {
            if (this.mExcludeFolders.size() > 0) {
                Log.e(LocalMediaManager.TAG, "Can't use both includeFolder and excludeFolder at the same time.");
                return this;
            }
            this.mIncludeFolders.add(str.toLowerCase());
            return this;
        }

        public Builder setMediaUriManager(MediaUriManager mediaUriManager) {
            this.mMediaUriManager = mediaUriManager;
            return this;
        }

        public Builder() {
            this.mIncludeFolders = new ArrayList();
            this.mExcludeFolders = new ArrayList();
            this.mTestMode = false;
        }
    }

    public boolean isSupportedPath(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            Iterator<String> it = this.mExcludeFolders.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (lowerCase.startsWith(it.next())) {
                        break;
                    }
                } else {
                    if (this.mIncludeFolders.size() > 0) {
                        for (String str2 : this.mIncludeFolders) {
                            if (lowerCase.startsWith(str2)) {
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static Builder builder() {
        return new Builder();
    }

    public LocalMediaConfig(MediaUriManager mediaUriManager, List<String> list, List<String> list2, boolean z) {
        this.mMediaUriManager = mediaUriManager;
        this.mIncludeFolders = list;
        this.mExcludeFolders = list2;
        this.mTestMode = z;
    }

    public List<String> getIncludeFolders() {
        return this.mIncludeFolders;
    }

    public MediaUriManager getMediaUriManager() {
        return this.mMediaUriManager;
    }

    public boolean isTestMode() {
        return this.mTestMode;
    }
}
