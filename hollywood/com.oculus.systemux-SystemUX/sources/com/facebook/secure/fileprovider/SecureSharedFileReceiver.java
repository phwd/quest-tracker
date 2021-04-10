package com.facebook.secure.fileprovider;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.annotation.Nullable;

public class SecureSharedFileReceiver {
    public static final String DEFAULT_INBOUND_PREFIX = "inbound";

    public static File receiveExternalFileAsCopy(Context context, Uri uri) throws IOException {
        return receiveExternalFileAsCopy(context, uri, null, true);
    }

    public static File receiveExternalFileAsCopy(Context context, Uri uri, boolean z) throws IOException {
        return receiveExternalFileAsCopy(context, uri, null, z);
    }

    public static File receiveExternalFileAsCopy(Context context, Uri uri, StoragePath storagePath) throws IOException {
        return receiveExternalFileAsCopy(context, uri, storagePath, true);
    }

    /* JADX INFO: finally extract failed */
    private static File receiveExternalFileAsCopy(Context context, Uri uri, @Nullable StoragePath storagePath, boolean z) throws IOException {
        AssetFileDescriptor openExternalContentDescriptor = openExternalContentDescriptor(context, uri);
        try {
            StoragePath appropriateStoragePath = getAppropriateStoragePath(openExternalContentDescriptor.getParcelFileDescriptor(), z);
            if (storagePath == null) {
                storagePath = appropriateStoragePath;
            } else if (!storagePath.isPrivate()) {
                if (appropriateStoragePath.isPrivate()) {
                    throw new SecurityException("Attempted to save a file from internal storage onto sd card.");
                }
            }
            String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
            TempFileDirectoryManager shareableDirectoryManager = SecurePathStrategy.getStrategyForContext(context).getShareableDirectoryManager(storagePath);
            if (shareableDirectoryManager != null) {
                File newTemporaryFile = shareableDirectoryManager.getNewTemporaryFile(DEFAULT_INBOUND_PREFIX, extensionFromMimeType);
                FileOutputStream fileOutputStream = new FileOutputStream(newTemporaryFile);
                try {
                    FileUtil.copy(openExternalContentDescriptor.createInputStream(), fileOutputStream);
                    fileOutputStream.close();
                    return newTemporaryFile;
                } catch (Throwable th) {
                    fileOutputStream.close();
                    throw th;
                }
            } else {
                throw new IOException("No shareable directory manager for " + storagePath.tagName());
            }
        } finally {
            openExternalContentDescriptor.close();
        }
    }

    /* JADX INFO: finally extract failed */
    public static void receiveExternalFileAsCopy(Context context, Uri uri, File file) throws IOException {
        AssetFileDescriptor openExternalContentDescriptor = openExternalContentDescriptor(context, uri);
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 805306368);
            if (FileUtil.isFileOnSdCard(open)) {
                if (!FileUtil.isFileOnSdCard(openExternalContentDescriptor.getParcelFileDescriptor())) {
                    throw new SecurityException("Attempted to save a file from internal storage onto sd card.");
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(open.getFileDescriptor());
            try {
                FileUtil.copy(openExternalContentDescriptor.createInputStream(), fileOutputStream);
                fileOutputStream.close();
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } finally {
            openExternalContentDescriptor.close();
        }
    }

    public static StoragePath getAppropriateStoragePath(ParcelFileDescriptor parcelFileDescriptor, boolean z) throws IOException {
        return FileUtil.isFileOnSdCard(parcelFileDescriptor) ? z ? StoragePath.EXTERNAL_CACHE_PATH : StoragePath.EXTERNAL_FILES_PATH : z ? StoragePath.CACHE_PATH : StoragePath.FILES_PATH;
    }

    public static AssetFileDescriptor openExternalContentDescriptor(Context context, Uri uri) throws IOException {
        AssetFileDescriptor openAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
        if (!FileUtil.isFileOwnedByRunningApplication(openAssetFileDescriptor.getParcelFileDescriptor())) {
            return openAssetFileDescriptor;
        }
        throw new SecurityException("Attempted to retrieve internal file.");
    }
}
