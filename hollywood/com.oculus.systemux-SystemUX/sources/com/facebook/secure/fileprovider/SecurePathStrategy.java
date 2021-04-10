package com.facebook.secure.fileprovider;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.DeadObjectException;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.util.UriUtil;
import com.facebook.secure.logger.LocalReporter;
import com.facebook.secure.logger.Reporter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.Nullable;
import org.xmlpull.v1.XmlPullParserException;

public class SecurePathStrategy {
    @VisibleForTesting
    static final String ATTR_NAME = "name";
    @VisibleForTesting
    static final String ATTR_PATH = "path";
    public static final String AUTHORITY_SUFFIX = ".securefileprovider";
    private static final HashSet<StoragePath> MANAGED_STORAGE_PATHS = new HashSet<>(Arrays.asList(StoragePath.FILES_PATH, StoragePath.CACHE_PATH, StoragePath.EXTERNAL_FILES_PATH, StoragePath.EXTERNAL_CACHE_PATH));
    public static final String META_DATA_SECURE_FILE_PROVIDER_PATHS = "com.facebook.secure.fileprovider.SECURE_FILE_PROVIDER_PATHS";
    private static final String PATHS_TAG_NAME = "paths";
    public static final String SECURE_SHARED_SUBDIRECTORY = "secure_shared";
    private static final String TAG = "SecurePathStrategy";
    private static final HashMap<String, SecurePathStrategy> sCache = new HashMap<>();
    private final boolean mAllowDirectAccessToReadableRoots;
    private final String mAuthority;
    private final Context mContext;
    private final HashSet<ReadableRoot> mReadableRootDirectories;
    private final HashMap<String, File> mReadableRoots = new HashMap<>();
    private boolean mReadableRootsCached = false;
    private final Reporter mReporter;
    private final HashMap<StoragePath, TempFileDirectoryManager> mShareableDirectoryManagers = new HashMap<>();
    private boolean mShareableDirectoryManagersCached = false;

    private SecurePathStrategy(Context context, @Nullable ProviderInfo providerInfo, Reporter reporter) throws IOException, XmlPullParserException {
        this.mReporter = reporter;
        this.mContext = context;
        if (providerInfo == null || providerInfo.metaData == null) {
            this.mAuthority = getDefaultAuthorityForContext(context);
            try {
                providerInfo = context.getPackageManager().resolveContentProvider(this.mAuthority, 2176);
            } catch (RuntimeException e) {
                if (e.getCause() instanceof DeadObjectException) {
                    this.mReporter.report(TAG, "DeadObjectException", e);
                } else {
                    throw e;
                }
            }
        } else {
            this.mAuthority = providerInfo.authority;
        }
        if (providerInfo == null) {
            this.mReporter.report(TAG, String.format("Could not retrieve provider info for %s", this.mAuthority), null);
            this.mAllowDirectAccessToReadableRoots = false;
            this.mReadableRootDirectories = new HashSet<>();
            return;
        }
        this.mAllowDirectAccessToReadableRoots = providerInfo.grantUriPermissions;
        XmlResourceParser loadXmlMetaData = providerInfo.loadXmlMetaData(context.getPackageManager(), META_DATA_SECURE_FILE_PROVIDER_PATHS);
        if (loadXmlMetaData == null) {
            this.mReporter.report(TAG, String.format("Could not read %s meta-data", META_DATA_SECURE_FILE_PROVIDER_PATHS), null);
            this.mReadableRootDirectories = new HashSet<>();
            return;
        }
        LinkedList linkedList = new LinkedList();
        while (true) {
            int next = loadXmlMetaData.next();
            if (next == 1) {
                this.mReadableRootDirectories = new HashSet<>(linkedList);
                return;
            } else if (next == 2) {
                String name = loadXmlMetaData.getName();
                if (!PATHS_TAG_NAME.equals(name)) {
                    StoragePath pathForTagName = StoragePath.getPathForTagName(name);
                    if (pathForTagName != null) {
                        linkedList.add(new ReadableRoot(loadXmlMetaData.getAttributeValue(null, "name"), pathForTagName, loadXmlMetaData.getAttributeValue(null, "path")));
                    } else {
                        throw new IllegalArgumentException("Unrecognized storage root " + name);
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public static SecurePathStrategy getStrategyForContext(Context context) {
        return getStrategyForContext(context, null, new LocalReporter());
    }

    public static SecurePathStrategy getStrategyForContext(Context context, ProviderInfo providerInfo) {
        return getStrategyForContext(context, providerInfo, new LocalReporter());
    }

    public static SecurePathStrategy getStrategyForContext(Context context, @Nullable ProviderInfo providerInfo, Reporter reporter) {
        String defaultAuthorityForContext = providerInfo == null ? getDefaultAuthorityForContext(context) : providerInfo.authority;
        synchronized (sCache) {
            SecurePathStrategy securePathStrategy = sCache.get(defaultAuthorityForContext);
            if (securePathStrategy != null) {
                return securePathStrategy;
            }
            try {
                SecurePathStrategy securePathStrategy2 = new SecurePathStrategy(context, providerInfo, reporter);
                sCache.put(defaultAuthorityForContext, securePathStrategy2);
                return securePathStrategy2;
            } catch (IOException | XmlPullParserException e) {
                String format = String.format("Failed to parse %s meta-data.", META_DATA_SECURE_FILE_PROVIDER_PATHS);
                reporter.report(TAG, format, e);
                throw new IllegalArgumentException(format);
            }
        }
    }

    public static String getDefaultAuthorityForContext(Context context) {
        return context.getApplicationContext().getPackageName() + AUTHORITY_SUFFIX;
    }

    public Uri getUriForFile(File file) throws IOException {
        Map.Entry<StoragePath, TempFileDirectoryManager> writeableRootManager = getWriteableRootManager(file);
        if (writeableRootManager == null) {
            return getDirectUriForFile(file);
        }
        File managedDirectory = writeableRootManager.getValue().getManagedDirectory();
        return encodeUri(managedDirectory.getPath(), writeableRootManager.getKey().tagName(), file.getCanonicalPath(), true);
    }

    private Uri getDirectUriForFile(File file) throws IOException {
        if (this.mAllowDirectAccessToReadableRoots) {
            Map.Entry<String, File> readableRoot = getReadableRoot(file);
            if (readableRoot == null) {
                StringBuilder sb = new StringBuilder(file.getCanonicalPath());
                for (Map.Entry<String, File> entry : this.mReadableRoots.entrySet()) {
                    sb.append(", ");
                    sb.append(entry.getValue().getCanonicalPath());
                }
                throw new SecurityException("Resolved path jumped beyond configured direct roots: " + sb.toString());
            }
            return encodeUri(readableRoot.getValue().getPath(), readableRoot.getKey(), file.getCanonicalPath(), false);
        }
        throw new SecurityException("Resolved path jumped beyond configured temporary roots: " + file.getPath());
    }

    private Uri encodeUri(String str, String str2, String str3, boolean z) {
        String str4;
        if (str.endsWith("/")) {
            str4 = str3.substring(str.length());
        } else {
            str4 = str3.substring(str.length() + 1);
        }
        if (z) {
            str2 = "secure_shared_" + str2;
        }
        return new Uri.Builder().scheme(UriUtil.LOCAL_CONTENT_SCHEME).authority(this.mAuthority).encodedPath(Uri.encode(str2) + '/' + Uri.encode(str4, "/")).build();
    }

    public File getFileForUri(Uri uri) throws IOException {
        String encodedPath = uri.getEncodedPath();
        int indexOf = encodedPath.indexOf(47, 1);
        String decode = Uri.decode(encodedPath.substring(1, indexOf));
        if (!decode.startsWith(SECURE_SHARED_SUBDIRECTORY)) {
            return getFileForDirectUri(uri);
        }
        TempFileDirectoryManager orInitManagedDirectoryManager = getOrInitManagedDirectoryManager(StoragePath.getPathForTagName(decode.substring(14)));
        if (orInitManagedDirectoryManager != null) {
            File managedDirectory = orInitManagedDirectoryManager.getManagedDirectory();
            File canonicalFile = new File(managedDirectory, Uri.decode(encodedPath.substring(indexOf + 1))).getCanonicalFile();
            if (!canonicalFile.getPath().startsWith(managedDirectory.getPath())) {
                throw new SecurityException("Resolved path jumped beyond configured roots");
            } else if (canonicalFile.exists()) {
                return canonicalFile;
            } else {
                throw new FileNotFoundException(String.format("File %s not found", canonicalFile.getPath()));
            }
        } else {
            throw new SecurityException("Resolved path jumped beyond configured roots");
        }
    }

    private File getFileForDirectUri(Uri uri) throws IOException {
        if (this.mAllowDirectAccessToReadableRoots) {
            ensureInitAllReadableRoots();
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.mReadableRoots.get(decode);
            if (file != null) {
                File canonicalFile = new File(file, decode2).getCanonicalFile();
                if (!canonicalFile.getPath().startsWith(file.getPath())) {
                    throw new SecurityException("Resolved path jumped beyond configured roots");
                } else if (canonicalFile.exists()) {
                    return canonicalFile;
                } else {
                    throw new FileNotFoundException(String.format("File %s not found", canonicalFile.getPath()));
                }
            } else {
                throw new SecurityException("Resolved path jumped beyond configured roots");
            }
        } else {
            throw new SecurityException("Direct access to shared files is not enabled.");
        }
    }

    public void addReadableRoot(String str, File file) throws IOException {
        if (str == null || str.trim().length() == 0) {
            this.mReporter.report(TAG, "Path names may not be empty", null);
        } else {
            this.mReadableRoots.put(str, file.getCanonicalFile());
        }
    }

    public boolean isFileUnderWriteableRootPath(File file) throws IOException {
        return getWriteableRootManager(file) != null;
    }

    @Nullable
    public Map.Entry<StoragePath, TempFileDirectoryManager> getWriteableRootManager(File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        ensureInitAllManagedTempDirectories();
        for (Map.Entry<StoragePath, TempFileDirectoryManager> entry : this.mShareableDirectoryManagers.entrySet()) {
            if (canonicalPath.startsWith(entry.getValue().getManagedDirectory().getPath())) {
                return entry;
            }
        }
        return null;
    }

    public boolean isFileUnderReadableRootPath(File file) throws IOException {
        ensureInitAllReadableRoots();
        String canonicalPath = file.getCanonicalPath();
        if (!file.exists()) {
            return false;
        }
        for (File file2 : this.mReadableRoots.values()) {
            if (canonicalPath.startsWith(file2.getPath())) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Map.Entry<String, File> getReadableRoot(File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        ensureInitAllReadableRoots();
        Map.Entry<String, File> entry = null;
        int i = 0;
        for (Map.Entry<String, File> entry2 : this.mReadableRoots.entrySet()) {
            String canonicalPath2 = entry2.getValue().getCanonicalPath();
            if (canonicalPath.startsWith(canonicalPath2) && canonicalPath2.length() > i) {
                i = canonicalPath2.length();
                entry = entry2;
            }
        }
        return entry;
    }

    public TempFileDirectoryManager getShareableDirectoryManager(@Nullable StoragePath storagePath) throws IOException {
        if (storagePath == null) {
            return getOrInitManagedDirectoryManager(StoragePath.CACHE_PATH);
        }
        return getOrInitManagedDirectoryManager(storagePath);
    }

    private TempFileDirectoryManager getOrInitManagedDirectoryManager(StoragePath storagePath) throws IOException {
        TempFileDirectoryManager tempFileDirectoryManager;
        synchronized (this.mShareableDirectoryManagers) {
            tempFileDirectoryManager = this.mShareableDirectoryManagers.get(storagePath);
            if (tempFileDirectoryManager == null) {
                if (MANAGED_STORAGE_PATHS.contains(storagePath)) {
                    TempFileDirectoryManager tempFileDirectoryManager2 = new TempFileDirectoryManager(new File(storagePath.getDirectoryForContext(this.mContext), SECURE_SHARED_SUBDIRECTORY));
                    this.mShareableDirectoryManagers.put(storagePath, tempFileDirectoryManager2);
                    tempFileDirectoryManager = tempFileDirectoryManager2;
                } else {
                    throw new IllegalArgumentException("No directory manager defined for " + storagePath);
                }
            }
        }
        return tempFileDirectoryManager;
    }

    private void ensureInitAllManagedTempDirectories() throws IOException {
        if (!this.mShareableDirectoryManagersCached) {
            synchronized (this.mShareableDirectoryManagers) {
                if (!this.mShareableDirectoryManagersCached) {
                    Iterator<StoragePath> it = MANAGED_STORAGE_PATHS.iterator();
                    while (it.hasNext()) {
                        getOrInitManagedDirectoryManager(it.next());
                    }
                    this.mShareableDirectoryManagersCached = true;
                }
            }
        }
    }

    private void ensureInitAllReadableRoots() throws IOException {
        if (!this.mReadableRootsCached) {
            synchronized (this.mReadableRoots) {
                if (!this.mReadableRootsCached) {
                    Iterator<ReadableRoot> it = this.mReadableRootDirectories.iterator();
                    while (it.hasNext()) {
                        ReadableRoot next = it.next();
                        addReadableRoot(next.getName(), next.resolveFile(this.mContext));
                    }
                    this.mReadableRootsCached = true;
                }
            }
        }
    }

    public boolean cleanupTemporarySharedFile(File file) throws IOException {
        Map.Entry<StoragePath, TempFileDirectoryManager> writeableRootManager = getWriteableRootManager(file);
        if (writeableRootManager != null) {
            return writeableRootManager.getValue().deleteTemporaryFile(file);
        }
        return false;
    }

    @VisibleForTesting
    static void uncacheStrategy(Context context) {
        synchronized (sCache) {
            sCache.remove(getDefaultAuthorityForContext(context));
        }
    }
}
