package com.oculus.common.navigation;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NavigationRouterContentProvider extends ContentProvider {
    private static final String COL_PACKAGE_HAS_FOCUS = "hasFocus";
    private static final String COL_PACKAGE_IS_FOCUS_AWARE = "isFocusAware";
    private static final String COL_PACKAGE_IS_SHELL = "isShell";
    private static final String COL_PACKAGE_IS_SYSTEM_PACKAGE = "isSystemPackage";
    private static final String COL_PACKAGE_NAME = "packageName";
    private static final String PRIMARY_PACKAGE = "primaryPackage";
    private static final int PRIMARY_PACKAGE_STATUS = 2;
    private static NavigationRouter mNavigationRouter;
    private final UriMatcher uriMatcher = new UriMatcher(-1);

    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    public static void setNavigationRouter(NavigationRouter navigationRouter) {
        mNavigationRouter = navigationRouter;
    }

    public boolean onCreate() {
        this.uriMatcher.addURI("com.oculus.common.navigation", PRIMARY_PACKAGE, 2);
        return false;
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        if (this.uriMatcher.match(uri) != 2) {
            return null;
        }
        PrimaryPackage primaryPackage = mNavigationRouter.getPrimaryPackage();
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{COL_PACKAGE_NAME, COL_PACKAGE_IS_FOCUS_AWARE, COL_PACKAGE_HAS_FOCUS, COL_PACKAGE_IS_SHELL, COL_PACKAGE_IS_SYSTEM_PACKAGE});
        matrixCursor.newRow().add(COL_PACKAGE_NAME, primaryPackage.getPackageName()).add(COL_PACKAGE_IS_FOCUS_AWARE, Integer.valueOf(primaryPackage.isFocusAware() ? 1 : 0)).add(COL_PACKAGE_HAS_FOCUS, Integer.valueOf(mNavigationRouter.hasCurrentAppFocus() ? 1 : 0)).add(COL_PACKAGE_IS_SHELL, Integer.valueOf(primaryPackage.isShell() ? 1 : 0)).add(COL_PACKAGE_IS_SYSTEM_PACKAGE, Integer.valueOf(primaryPackage.isSystemPackage() ? 1 : 0));
        return matrixCursor;
    }
}
