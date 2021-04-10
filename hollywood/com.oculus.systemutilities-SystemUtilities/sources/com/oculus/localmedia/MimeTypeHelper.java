package com.oculus.localmedia;

import java.util.HashMap;
import java.util.Map;

public class MimeTypeHelper {
    private HashMap<String, String> mSupportedMimeTypeToExtension;

    public MimeTypeHelper() {
        this.mSupportedMimeTypeToExtension = null;
        this.mSupportedMimeTypeToExtension = new HashMap<>();
    }

    public void registerMimeType(String mimeType, String extension) {
        if (!this.mSupportedMimeTypeToExtension.containsKey(mimeType)) {
            this.mSupportedMimeTypeToExtension.put(mimeType, extension);
        }
    }

    public boolean isSupportedMimeType(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            return false;
        }
        for (String type : this.mSupportedMimeTypeToExtension.keySet()) {
            if (type.equalsIgnoreCase(mimeType)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSupportedMediaFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }
        String extension = MediaProviderUtils.getFileExtension(filePath, false);
        for (String ext : this.mSupportedMimeTypeToExtension.values()) {
            if (ext.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    public String getMimeTypeFromFileExtension(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return null;
        }
        String extension = MediaProviderUtils.getFileExtension(filePath, false);
        for (Map.Entry<String, String> entry : this.mSupportedMimeTypeToExtension.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(extension)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
