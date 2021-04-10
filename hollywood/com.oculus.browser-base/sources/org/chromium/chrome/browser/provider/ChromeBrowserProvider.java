package org.chromium.chrome.browser.provider;

import J.N;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.oculus.os.Version;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeBrowserProvider extends ContentProvider {
    public final Object F = new Object();
    public VY0 G;
    public String H = "org.chromium.chrome.browser.provider.ChromeBrowserProviderImpl";

    public ChromeBrowserProvider() {
        C5271va vaVar = AbstractC2369eZ0.f9859a;
    }

    public final VY0 a() {
        VY0 vy0;
        synchronized (this.F) {
            if (this.G == null) {
                VY0 vy02 = (VY0) AbstractC2369eZ0.b(AbstractC2369eZ0.a(getContext()), this.H);
                this.G = vy02;
                vy02.f9090a = this;
            }
            vy0 = this.G;
        }
        return vy0;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int i;
        ChromeBrowserProviderImpl chromeBrowserProviderImpl = (ChromeBrowserProviderImpl) a();
        int i2 = 0;
        if (chromeBrowserProviderImpl.i() && chromeBrowserProviderImpl.n()) {
            long l = ChromeBrowserProviderImpl.l(uri);
            if (l != 0) {
                switch (chromeBrowserProviderImpl.g.match(uri)) {
                    case 1:
                        i = N.MVeHPHvq(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, l);
                        break;
                    case 2:
                        i = N.MOmmRjdm(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, str, strArr);
                        break;
                    case 3:
                        i = chromeBrowserProviderImpl.s(ChromeBrowserProviderImpl.h(l, str), strArr);
                        break;
                    case 4:
                        i = N.MS0nlt_B(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, str, strArr);
                        break;
                    case 5:
                        i = N.MS0nlt_B(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, ChromeBrowserProviderImpl.h(l, str), strArr);
                        break;
                    case 6:
                        i = N.M7MqdZwH(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, str, strArr);
                        break;
                    case Version.VERSION_7:
                        i = N.M7MqdZwH(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, ChromeBrowserProviderImpl.h(l, str), strArr);
                        break;
                    case Version.VERSION_8:
                        i = chromeBrowserProviderImpl.s(ChromeBrowserProviderImpl.e(str), strArr);
                        break;
                    case Version.VERSION_9:
                        i = chromeBrowserProviderImpl.s(ChromeBrowserProviderImpl.d(l, str), strArr);
                        break;
                    default:
                        throw new IllegalArgumentException(AbstractC2531fV.c("ChromeBrowserProvider: delete - unknown URL ", uri));
                }
                i2 = i;
                if (i2 != 0) {
                    chromeBrowserProviderImpl.o(uri);
                }
            }
        }
        return i2;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        a().a();
    }

    public String getType(Uri uri) {
        ChromeBrowserProviderImpl chromeBrowserProviderImpl = (ChromeBrowserProviderImpl) a();
        chromeBrowserProviderImpl.j();
        switch (chromeBrowserProviderImpl.g.match(uri)) {
            case 0:
            case 2:
                return "vnd.android.cursor.dir/bookmark";
            case 1:
            case 3:
                return "vnd.android.cursor.item/bookmark";
            case 4:
                return "vnd.android.cursor.dir/searches";
            case 5:
                return "vnd.android.cursor.item/searches";
            case 6:
                return "vnd.android.cursor.dir/browser-history";
            case Version.VERSION_7:
                return "vnd.android.cursor.item/browser-history";
            default:
                throw new IllegalArgumentException(AbstractC2531fV.c("ChromeBrowserProvider: getType - unknown URL ", uri));
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        long j;
        ChromeBrowserProviderImpl chromeBrowserProviderImpl = (ChromeBrowserProviderImpl) a();
        if (!chromeBrowserProviderImpl.i() || !chromeBrowserProviderImpl.n()) {
            return null;
        }
        int match = chromeBrowserProviderImpl.g.match(uri);
        if (match != 0) {
            if (match != 2) {
                if (match == 4) {
                    C1565Zq a2 = C1565Zq.a(contentValues);
                    String str = a2.f9375a;
                    if (str != null) {
                        j = N.MX2EuUHF(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, str, a2.b);
                        if (j == 0) {
                            return null;
                        }
                    } else {
                        throw new IllegalArgumentException("Must have a search term");
                    }
                } else if (match != 6) {
                    if (match == 8) {
                        contentValues.put("bookmark", (Integer) 1);
                    } else {
                        throw new IllegalArgumentException(AbstractC2531fV.c("ChromeBrowserProvider: insert - unknown URL ", uri));
                    }
                }
            }
            C1504Yq a3 = C1504Yq.a(contentValues);
            String str2 = a3.c;
            if (str2 != null) {
                j = N.MmfTg$eO(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, str2, a3.b, a3.f9299a, a3.d, a3.e, a3.f, a3.g, a3.h);
                if (j == 0) {
                    return null;
                }
            } else {
                throw new IllegalArgumentException("Must have a bookmark URL");
            }
        } else {
            String asString = contentValues.getAsString("url");
            String asString2 = contentValues.getAsString("title");
            boolean booleanValue = contentValues.containsKey("isFolder") ? contentValues.getAsBoolean("isFolder").booleanValue() : false;
            long longValue = contentValues.containsKey("parentId") ? contentValues.getAsLong("parentId").longValue() : -1;
            j = N.MLb_V7Gy(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, asString, asString2, booleanValue, longValue);
            int i = (j > -1 ? 1 : (j == -1 ? 0 : -1));
            if (i != 0) {
                if (booleanValue) {
                    chromeBrowserProviderImpl.u(j);
                } else {
                    chromeBrowserProviderImpl.u(longValue);
                }
            }
            if (i == 0) {
                return null;
            }
        }
        Uri withAppendedId = ContentUris.withAppendedId(uri, j);
        chromeBrowserProviderImpl.o(withAppendedId);
        return withAppendedId;
    }

    public /* bridge */ /* synthetic */ boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor cursor;
        ChromeBrowserProviderImpl chromeBrowserProviderImpl = (ChromeBrowserProviderImpl) a();
        if (!chromeBrowserProviderImpl.i()) {
            return null;
        }
        if (!chromeBrowserProviderImpl.m("READ_HISTORY_BOOKMARKS")) {
            return new MatrixCursor(ChromeBrowserProviderImpl.c, 0);
        }
        long l = ChromeBrowserProviderImpl.l(uri);
        if (l == 0) {
            return null;
        }
        switch (chromeBrowserProviderImpl.g.match(uri)) {
            case 2:
                cursor = chromeBrowserProviderImpl.p(strArr, str, strArr2, str2);
                break;
            case 3:
                cursor = chromeBrowserProviderImpl.p(strArr, ChromeBrowserProviderImpl.h(l, str), strArr2, str2);
                break;
            case 4:
                cursor = chromeBrowserProviderImpl.q(strArr, str, strArr2, str2);
                break;
            case 5:
                cursor = chromeBrowserProviderImpl.q(strArr, ChromeBrowserProviderImpl.h(l, str), strArr2, str2);
                break;
            case 6:
                cursor = chromeBrowserProviderImpl.p(strArr, ChromeBrowserProviderImpl.f(str, false), strArr2, str2);
                break;
            case Version.VERSION_7:
                cursor = chromeBrowserProviderImpl.p(strArr, ChromeBrowserProviderImpl.h(l, ChromeBrowserProviderImpl.f(str, false)), strArr2, str2);
                break;
            case Version.VERSION_8:
                cursor = chromeBrowserProviderImpl.p(strArr, ChromeBrowserProviderImpl.e(str), strArr2, str2);
                break;
            case Version.VERSION_9:
                cursor = chromeBrowserProviderImpl.p(strArr, ChromeBrowserProviderImpl.d(l, str), strArr2, str2);
                break;
            case Version.VERSION_10:
                cursor = chromeBrowserProviderImpl.k(str, strArr2, str2, true);
                break;
            case Version.VERSION_11:
                cursor = chromeBrowserProviderImpl.k(str, strArr2, str2, false);
                break;
            default:
                throw new IllegalArgumentException(AbstractC2531fV.c("ChromeBrowserProvider: query - unknown URL uri = ", uri));
        }
        if (cursor == null) {
            cursor = new MatrixCursor(new String[0]);
        }
        cursor.setNotificationUri(chromeBrowserProviderImpl.b().getContentResolver(), uri);
        return cursor;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int MOKmxj6h;
        ChromeBrowserProviderImpl chromeBrowserProviderImpl = (ChromeBrowserProviderImpl) a();
        int i = 0;
        if (chromeBrowserProviderImpl.i() && chromeBrowserProviderImpl.n()) {
            long l = ChromeBrowserProviderImpl.l(uri);
            if (l != 0) {
                switch (chromeBrowserProviderImpl.g.match(uri)) {
                    case 1:
                        String str2 = null;
                        if (contentValues.containsKey("url")) {
                            str2 = contentValues.getAsString("url");
                        }
                        String asString = contentValues.getAsString("title");
                        long j = -1;
                        if (contentValues.containsKey("parentId")) {
                            j = contentValues.getAsLong("parentId").longValue();
                        }
                        MOKmxj6h = N.MOKmxj6h(chromeBrowserProviderImpl.i, chromeBrowserProviderImpl, l, str2, asString, j);
                        chromeBrowserProviderImpl.u(j);
                        break;
                    case 2:
                        MOKmxj6h = chromeBrowserProviderImpl.t(contentValues, str, strArr);
                        break;
                    case 3:
                        MOKmxj6h = chromeBrowserProviderImpl.t(contentValues, ChromeBrowserProviderImpl.h(l, str), strArr);
                        break;
                    case 4:
                        MOKmxj6h = chromeBrowserProviderImpl.v(contentValues, str, strArr);
                        break;
                    case 5:
                        MOKmxj6h = chromeBrowserProviderImpl.v(contentValues, ChromeBrowserProviderImpl.h(l, str), strArr);
                        break;
                    case 6:
                        MOKmxj6h = chromeBrowserProviderImpl.t(contentValues, ChromeBrowserProviderImpl.f(str, false), strArr);
                        break;
                    case Version.VERSION_7:
                        MOKmxj6h = chromeBrowserProviderImpl.t(contentValues, ChromeBrowserProviderImpl.h(l, ChromeBrowserProviderImpl.f(str, false)), strArr);
                        break;
                    case Version.VERSION_8:
                        MOKmxj6h = chromeBrowserProviderImpl.t(contentValues, ChromeBrowserProviderImpl.e(str), strArr);
                        break;
                    case Version.VERSION_9:
                        MOKmxj6h = chromeBrowserProviderImpl.t(contentValues, ChromeBrowserProviderImpl.d(l, str), strArr);
                        break;
                    default:
                        throw new IllegalArgumentException(AbstractC2531fV.c("ChromeBrowserProvider: update - unknown URL ", uri));
                }
                i = MOKmxj6h;
                if (i != 0) {
                    chromeBrowserProviderImpl.o(uri);
                }
            }
        }
        return i;
    }
}
