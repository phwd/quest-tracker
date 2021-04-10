package com.facebook.secure.fileprovider;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.webkit.MimeTypeMap;
import com.facebook.secure.logger.LocalReporter;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.providerinit.DeferredInitContentProvider;
import com.facebook.secure.providerinit.DeferredInitContentProviderDelegate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.annotation.Nullable;

public class SecureFileProvider extends DeferredInitContentProvider {
    private SecurePathStrategy mSecurePathStrategy;

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.providerinit.DeferredInitContentProvider
    public DeferredInitContentProviderDelegate getDelegateInstance() {
        return new Impl(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.secure.providerinit.DeferredInitContentProvider
    public void validateProviderInfo(Context context, ProviderInfo providerInfo) {
        if (!providerInfo.exported) {
            this.mSecurePathStrategy = SecurePathStrategy.getStrategyForContext(context, providerInfo);
            return;
        }
        throw new SecurityException("Provider must not be exported.");
    }

    /* access modifiers changed from: protected */
    public SecurePathStrategy getSecurePathStrategy() {
        return this.mSecurePathStrategy;
    }

    private static class Impl extends DeferredInitContentProviderDelegate {
        private static final String[] DEFAULT_QUERY_COLUMNS = {"_display_name", "_size"};
        private static final String TAG = "SecureFileProvider.Impl";
        private static final Reporter sReporter = new LocalReporter();
        private SecureFileProvider mParent;

        public Impl(DeferredInitContentProvider deferredInitContentProvider) {
            super(deferredInitContentProvider);
            this.mParent = (SecureFileProvider) deferredInitContentProvider;
        }

        private SecurePathStrategy getSecurePathStrategy() {
            return this.mParent.getSecurePathStrategy();
        }

        @Override // com.facebook.secure.providerinit.DeferredInitContentProviderDelegate
        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
            int i;
            try {
                File fileForUri = getSecurePathStrategy().getFileForUri(uri);
                if (strArr == null) {
                    strArr = DEFAULT_QUERY_COLUMNS;
                }
                String[] strArr3 = new String[strArr.length];
                Object[] objArr = new Object[strArr.length];
                int i2 = 0;
                for (String str3 : strArr) {
                    if ("_display_name".equals(str3)) {
                        strArr3[i2] = "_display_name";
                        i = i2 + 1;
                        objArr[i2] = fileForUri.getName();
                    } else if ("_size".equals(str3)) {
                        strArr3[i2] = "_size";
                        i = i2 + 1;
                        objArr[i2] = Long.valueOf(fileForUri.length());
                    }
                    i2 = i;
                }
                Object[] copyOf = Arrays.copyOf(objArr, i2);
                MatrixCursor matrixCursor = new MatrixCursor((String[]) Arrays.copyOf(strArr3, i2), 0);
                if (i2 > 0) {
                    matrixCursor.addRow(copyOf);
                }
                return matrixCursor;
            } catch (IOException e) {
                sReporter.report(TAG, "Query incurred an IOException", e);
                return new MatrixCursor(new String[strArr.length], 0);
            }
        }

        @Override // com.facebook.secure.providerinit.DeferredInitContentProviderDelegate
        public Uri insert(Uri uri, ContentValues contentValues) {
            throw new UnsupportedOperationException("No external inserts");
        }

        @Override // com.facebook.secure.providerinit.DeferredInitContentProviderDelegate
        public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
            throw new UnsupportedOperationException("No external updates");
        }

        @Override // com.facebook.secure.providerinit.DeferredInitContentProviderDelegate
        public int delete(Uri uri, String str, String[] strArr) {
            try {
                File fileForUri = getSecurePathStrategy().getFileForUri(uri);
                if (fileForUri == null || !getSecurePathStrategy().isFileUnderWriteableRootPath(fileForUri) || !fileForUri.delete()) {
                    return 0;
                }
                return 1;
            } catch (IOException unused) {
                return 0;
            }
        }

        @Override // com.facebook.secure.providerinit.DeferredInitContentProviderDelegate
        public String getType(Uri uri) {
            String mimeTypeFromExtension;
            try {
                String extension = FileUtil.getExtension(getSecurePathStrategy().getFileForUri(uri));
                return (extension.length() <= 0 || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)) == null) ? "application/octet-stream" : mimeTypeFromExtension;
            } catch (IOException e) {
                sReporter.report(TAG, "Could not resolve file type.", e);
                return "";
            }
        }

        @Override // com.facebook.secure.providerinit.DeferredInitContentProviderDelegate
        public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
            try {
                return ParcelFileDescriptor.open(getSecurePathStrategy().getFileForUri(uri), FileUtil.modeToMode(str));
            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e2) {
                sReporter.report(TAG, "IOException during file opening.", e2);
                throw new FileNotFoundException("Could not open file");
            }
        }
    }

    public static Uri getUriForFile(Context context, File file) throws IOException {
        return SecurePathStrategy.getStrategyForContext(context).getUriForFile(file);
    }

    public static Uri getTempUriForFile(Context context, File file, @Nullable String str, @Nullable StoragePath storagePath) throws IOException {
        SecurePathStrategy strategyForContext = SecurePathStrategy.getStrategyForContext(context, null);
        if (strategyForContext.isFileUnderReadableRootPath(file)) {
            return strategyForContext.getUriForFile(strategyForContext.getShareableDirectoryManager(storagePath).getNewTemporaryFileCopy(file, str));
        }
        throw new SecurityException(String.format("Attempted to generate temporary URI for %s not under readable root", file.getPath()));
    }

    public static Uri getUriForNewShareableFile(Context context, String str, String str2, @Nullable StoragePath storagePath) throws IOException {
        SecurePathStrategy strategyForContext = SecurePathStrategy.getStrategyForContext(context);
        return strategyForContext.getUriForFile(strategyForContext.getShareableDirectoryManager(storagePath).getNewTemporaryFile(str, str2));
    }

    public static File getNewShareableFile(Context context, String str, String str2, @Nullable StoragePath storagePath) throws IOException {
        return SecurePathStrategy.getStrategyForContext(context).getShareableDirectoryManager(storagePath).getNewTemporaryFile(str, str2);
    }

    public static boolean cleanupTemporaryFile(Context context, Uri uri) throws IOException {
        SecurePathStrategy strategyForContext = SecurePathStrategy.getStrategyForContext(context);
        return strategyForContext.cleanupTemporarySharedFile(strategyForContext.getFileForUri(uri));
    }

    public static boolean cleanupTemporaryFile(Context context, File file) throws IOException {
        return SecurePathStrategy.getStrategyForContext(context).cleanupTemporarySharedFile(file);
    }
}
