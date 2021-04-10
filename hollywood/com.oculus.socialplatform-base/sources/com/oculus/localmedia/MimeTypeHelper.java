package com.oculus.localmedia;

import java.util.HashMap;
import java.util.Map;

public class MimeTypeHelper {
    public HashMap<String, String> mSupportedMimeTypeToExtension;

    public String getMimeTypeFromFileExtension(String str) {
        if (str != null && !str.isEmpty()) {
            String fileExtension = MediaProviderUtils.getFileExtension(str, false);
            for (Map.Entry<String, String> entry : this.mSupportedMimeTypeToExtension.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(fileExtension)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public boolean isSupportedMediaFile(String str) {
        if (str != null && !str.isEmpty()) {
            String fileExtension = MediaProviderUtils.getFileExtension(str, false);
            for (String str2 : this.mSupportedMimeTypeToExtension.values()) {
                if (str2.equals(fileExtension)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSupportedMimeType(String str) {
        if (str != null && !str.isEmpty()) {
            for (String str2 : this.mSupportedMimeTypeToExtension.keySet()) {
                if (str2.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void registerMimeType(String str, String str2) {
        if (!this.mSupportedMimeTypeToExtension.containsKey(str)) {
            this.mSupportedMimeTypeToExtension.put(str, str2);
        }
    }

    public MimeTypeHelper() {
        this.mSupportedMimeTypeToExtension = null;
        this.mSupportedMimeTypeToExtension = new HashMap<>();
    }
}
