package com.oculus.appsafety;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public final class PackageIntegrityProvider extends ContentProvider {
    private static final String PARAM_NAME = "name";
    private static final String PATH_PACKAGE = "package";
    private static final String PROJECTION_IS_TRUSTED = "is_trusted";
    private static final String TAG = PackageIntegrityProvider.class.getSimpleName();

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (!uri.getLastPathSegment().equals(PATH_PACKAGE)) {
            return null;
        }
        uri.getQueryParameter(PARAM_NAME);
        MatrixCursor cursor = new MatrixCursor(projection);
        Object[] row = new Object[projection.length];
        for (int i = 0; i < projection.length; i++) {
            if (projection[i].equals(PROJECTION_IS_TRUSTED)) {
                row[i] = new Integer(1);
            }
        }
        cursor.addRow(row);
        return cursor;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
