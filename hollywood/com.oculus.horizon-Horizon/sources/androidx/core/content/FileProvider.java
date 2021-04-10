package androidx.core.content;

import X.AnonymousClass006;
import X.AnonymousClass04K;
import X.AnonymousClass0sY;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.FacebookSdk;
import com.oculus.appmanager.downloader.OculusFileDownloader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

public class FileProvider extends ContentProvider {
    public static final String ATTR_NAME = "name";
    public static final String ATTR_PATH = "path";
    public static final String[] COLUMNS = {"_display_name", "_size"};
    public static final File DEVICE_ROOT = new File("/");
    public static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
    public static final String TAG_CACHE_PATH = "cache-path";
    public static final String TAG_EXTERNAL = "external-path";
    public static final String TAG_EXTERNAL_CACHE = "external-cache-path";
    public static final String TAG_EXTERNAL_FILES = "external-files-path";
    public static final String TAG_EXTERNAL_MEDIA = "external-media-path";
    public static final String TAG_FILES_PATH = "files-path";
    public static final String TAG_ROOT_PATH = "root-path";
    @GuardedBy("sCache")
    public static HashMap<String, AnonymousClass04K> sCache = new HashMap<>();
    public AnonymousClass04K mStrategy;

    public static File buildPath(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    public boolean onCreate() {
        return true;
    }

    public static AnonymousClass04K getPathStrategy(Context context, String str) {
        AnonymousClass04K r1;
        synchronized (sCache) {
            r1 = sCache.get(str);
            if (r1 == null) {
                try {
                    r1 = parsePathStrategy(context, str);
                    sCache.put(str, r1);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);
                } catch (XmlPullParserException e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                }
            }
        }
        return r1;
    }

    public static int modeToMode(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException(AnonymousClass006.A05("Invalid mode: ", str));
    }

    public static AnonymousClass04K parsePathStrategy(Context context, String str) throws IOException, XmlPullParserException {
        String A05;
        File[] externalMediaDirs;
        File file;
        AnonymousClass0sY r4 = new AnonymousClass0sY(str);
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(str, FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
        if (resolveContentProvider != null) {
            XmlResourceParser loadXmlMetaData = resolveContentProvider.loadXmlMetaData(context.getPackageManager(), META_DATA_FILE_PROVIDER_PATHS);
            if (loadXmlMetaData == null) {
                A05 = "Missing android.support.FILE_PROVIDER_PATHS meta-data";
            } else {
                while (true) {
                    int next = loadXmlMetaData.next();
                    if (next == 1) {
                        return r4;
                    }
                    if (next == 2) {
                        String name = loadXmlMetaData.getName();
                        String attributeValue = loadXmlMetaData.getAttributeValue(null, "name");
                        String attributeValue2 = loadXmlMetaData.getAttributeValue(null, "path");
                        if (TAG_ROOT_PATH.equals(name)) {
                            file = DEVICE_ROOT;
                        } else if (TAG_FILES_PATH.equals(name)) {
                            file = context.getFilesDir();
                        } else if (TAG_CACHE_PATH.equals(name)) {
                            file = context.getCacheDir();
                        } else if (TAG_EXTERNAL.equals(name)) {
                            file = Environment.getExternalStorageDirectory();
                        } else {
                            if (TAG_EXTERNAL_FILES.equals(name)) {
                                externalMediaDirs = context.getExternalFilesDirs(null);
                            } else if (TAG_EXTERNAL_CACHE.equals(name)) {
                                externalMediaDirs = context.getExternalCacheDirs();
                            } else if (TAG_EXTERNAL_MEDIA.equals(name)) {
                                externalMediaDirs = context.getExternalMediaDirs();
                            } else {
                                continue;
                            }
                            if (externalMediaDirs.length > 0) {
                                file = externalMediaDirs[0];
                            } else {
                                continue;
                            }
                        }
                        if (file != null) {
                            File buildPath = buildPath(file, attributeValue2);
                            if (TextUtils.isEmpty(attributeValue)) {
                                A05 = "Name must not be empty";
                                break;
                            }
                            try {
                                r4.A00.put(attributeValue, buildPath.getCanonicalFile());
                            } catch (IOException e) {
                                StringBuilder sb = new StringBuilder("Failed to resolve canonical path for ");
                                sb.append(buildPath);
                                throw new IllegalArgumentException(sb.toString(), e);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
            A05 = AnonymousClass006.A05("Couldn't find meta-data for provider with authority ", str);
        }
        throw new IllegalArgumentException(A05);
    }

    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return this.mStrategy.A3Q(uri).delete() ? 1 : 0;
    }

    public String getType(@NonNull Uri uri) {
        String mimeTypeFromExtension;
        File A3Q = this.mStrategy.A3Q(uri);
        int lastIndexOf = A3Q.getName().lastIndexOf(46);
        if (lastIndexOf < 0 || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(A3Q.getName().substring(lastIndexOf + 1))) == null) {
            return OculusFileDownloader.ACCEPT_BINARY_STREAM;
        }
        return mimeTypeFromExtension;
    }

    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String str) throws FileNotFoundException {
        return ParcelFileDescriptor.open(this.mStrategy.A3Q(uri), modeToMode(str));
    }

    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        int i;
        File A3Q = this.mStrategy.A3Q(uri);
        if (strArr == null) {
            strArr = COLUMNS;
        }
        int length = strArr.length;
        String[] strArr3 = new String[length];
        Object[] objArr = new Object[length];
        int i2 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = A3Q.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(A3Q.length());
            }
            i2 = i;
        }
        String[] copyOf = copyOf(strArr3, i2);
        Object[] copyOf2 = copyOf(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(copyOf, 1);
        matrixCursor.addRow(copyOf2);
        return matrixCursor;
    }

    public int update(@NonNull Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    public static Uri getUriForFile(@NonNull Context context, @NonNull String str, @NonNull File file) {
        return getPathStrategy(context, str).A4b(file);
    }

    public void attachInfo(@NonNull Context context, @NonNull ProviderInfo providerInfo) {
        String str;
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            str = "Provider must not be exported";
        } else if (providerInfo.grantUriPermissions) {
            this.mStrategy = getPathStrategy(context, providerInfo.authority);
            return;
        } else {
            str = "Provider must grant uri permissions";
        }
        throw new SecurityException(str);
    }

    public static Object[] copyOf(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    public static String[] copyOf(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }
}
