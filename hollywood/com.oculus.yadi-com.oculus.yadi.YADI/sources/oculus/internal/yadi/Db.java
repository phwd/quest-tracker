package oculus.internal.yadi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import com.oculus.os.yadi.RemoteApp;
import com.oculus.os.yadi.RemoteResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import oculus.internal.functional.Pair;

public class Db extends SQLiteOpenHelper {
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public enum ResourceTable {
        local,
        temporary;

        public String tableName() {
            return toString() + "_resources";
        }
    }

    public Db(Context context) {
        super(context, "yadi.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + "deployed_apps(" + "fbid TEXT NOT NULL UNIQUE,lastUpdated INTEGER NOT NULL,packageName TEXT NOT NULL,PRIMARY KEY (fbid)" + ");");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + "local_resources(" + "fbid TEXT NOT NULL,appid TEXT NOT NULL,lastUpdated INTEGER NOT NULL,digest TEXT NOT NULL,filename NOT NULL,httpLastModified TEXT,httpETag TEXT,active BOOLEAN,PRIMARY KEY (fbid, digest)" + ");");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + "temporary_resources(" + "fbid TEXT NOT NULL,appid TEXT NOT NULL,lastUpdated INTEGER NOT NULL,digest TEXT NOT NULL,filename NOT NULL,httpLastModified TEXT,httpETag TEXT,active BOOLEAN,PRIMARY KEY (fbid, digest)" + ");");
    }

    public long deployApp(RemoteApp remoteApp, String str, boolean z) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fbid", remoteApp.appId);
        contentValues.put("lastUpdated", Long.valueOf(z ? remoteApp.lastUpdatedTime : 0));
        contentValues.put("packageName", str);
        return getWritableDatabase().replaceOrThrow("deployed_apps", null, contentValues);
    }

    public void purgeApp(RemoteApp remoteApp) {
        for (ResourceTable resourceTable : ResourceTable.values()) {
            getWritableDatabase().delete(resourceTable.tableName(), "appid = ?", new String[]{remoteApp.appId});
        }
        getWritableDatabase().delete("deployed_apps", "fbid = ?", new String[]{remoteApp.appId});
    }

    public long deployResource(ResourceTable resourceTable, RemoteResource remoteResource, Bundle bundle, boolean z) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("fbid", remoteResource.resourceId);
        contentValues.put("appid", remoteResource.appId);
        contentValues.put("lastUpdated", Long.valueOf(remoteResource.lastUpdatedTime));
        contentValues.put("digest", Utils.hexString(remoteResource.digest));
        contentValues.put("filename", remoteResource.filename.toString());
        contentValues.put("active", Boolean.valueOf(z));
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        String string = bundle.getString("Last-Modified", "");
        if (!Utils.emptyOrNull(string)) {
            contentValues.put("httpLastModified", string);
        }
        String string2 = bundle.getString("ETag", "");
        if (!Utils.emptyOrNull(string2)) {
            contentValues.put("httpETag", string2);
        }
        return getWritableDatabase().replaceOrThrow(resourceTable.tableName(), null, contentValues);
    }

    public int removeResource(ResourceTable resourceTable, String str, String str2) {
        return getWritableDatabase().delete(resourceTable.tableName(), "fbid = ? AND digest = ?", new String[]{str, str2});
    }

    public int removeResource(ResourceTable resourceTable, RemoteResource remoteResource) {
        return removeResource(resourceTable, remoteResource.resourceId, Utils.hexString(remoteResource.digest));
    }

    public Optional<String> getPackageName(RemoteApp remoteApp) {
        Cursor query = getReadableDatabase().query("deployed_apps", new String[]{"packageName"}, "fbid = ?", new String[]{remoteApp.appId}, null, null, null, "1");
        try {
            if (!query.moveToFirst()) {
                return Optional.empty();
            }
            Optional<String> of = Optional.of(query.getString(0));
            query.close();
            return of;
        } finally {
            query.close();
        }
    }

    public Optional<Uri> getPreviousFilename(String str, String str2) {
        Cursor query = getReadableDatabase().query("local_resources", new String[]{"filename"}, "fbid = ? AND digest = ?", new String[]{str, str2}, null, null, null, "1");
        try {
            if (!query.moveToFirst()) {
                return Optional.empty();
            }
            Optional<Uri> of = Optional.of(Uri.parse(query.getString(0)));
            query.close();
            return of;
        } finally {
            query.close();
        }
    }

    public Optional<Uri> getPreviousFilename(RemoteResource remoteResource) {
        return getPreviousFilename(remoteResource.resourceId, Utils.hexString(remoteResource.digest));
    }

    public Optional<Bundle> getResumeInfo(RemoteResource remoteResource) {
        Cursor query = getReadableDatabase().query("temporary_resources", new String[]{"httpLastModified", "httpETag"}, "fbid = ? AND digest = ?", new String[]{remoteResource.resourceId, Utils.hexString(remoteResource.digest)}, null, null, null, "1");
        try {
            if (!query.moveToFirst()) {
                return Optional.empty();
            }
            Bundle bundle = new Bundle();
            String string = query.getString(0);
            if (!Utils.emptyOrNull(string)) {
                bundle.putString("Last-Modified", string);
            }
            String string2 = query.getString(1);
            if (!Utils.emptyOrNull(string2)) {
                bundle.putString("ETag", string2);
            }
            Optional<Bundle> of = Optional.of(bundle);
            query.close();
            return of;
        } finally {
            query.close();
        }
    }

    public List<Pair<String, String>> knownResourcesForApp(ResourceTable resourceTable, RemoteApp remoteApp) {
        Cursor query = getReadableDatabase().query(resourceTable.tableName(), new String[]{"fbid", "digest"}, "appid = ?", new String[]{remoteApp.appId}, null, null, null, null);
        try {
            if (!query.moveToFirst()) {
                return Collections.EMPTY_LIST;
            }
            ArrayList arrayList = new ArrayList();
            for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
                arrayList.add(Pair.Pair(query.getString(0), query.getString(1)));
            }
            query.close();
            return arrayList;
        } finally {
            query.close();
        }
    }

    public Optional<RemoteResource> asInactiveResource(RemoteResource remoteResource) {
        if (isResourceActive(remoteResource)) {
            return Optional.empty();
        }
        return Optional.of(remoteResource);
    }

    public boolean isResourceActive(RemoteResource remoteResource) {
        return isResourceActive(remoteResource.resourceId, Utils.hexString(remoteResource.digest));
    }

    public boolean isResourceActive(String str, String str2) {
        boolean z = false;
        Cursor query = getReadableDatabase().query("local_resources", new String[]{"active"}, "fbid = ? AND digest = ?", new String[]{str, str2}, null, null, null, "1");
        try {
            if (!query.moveToFirst()) {
                return false;
            }
            if (query.getInt(0) != 0) {
                z = true;
            }
            query.close();
            return z;
        } finally {
            query.close();
        }
    }

    public boolean needsUpdate(RemoteApp remoteApp) {
        boolean z = true;
        Cursor query = getReadableDatabase().query("deployed_apps", new String[]{"lastUpdated"}, "fbid = ?", new String[]{remoteApp.appId}, null, null, null, "1");
        try {
            if (!query.moveToFirst()) {
                return true;
            }
            if (query.getLong(0) == remoteApp.lastUpdatedTime) {
                z = false;
            }
            query.close();
            return z;
        } finally {
            query.close();
        }
    }

    public boolean didContentChange(RemoteResource remoteResource) {
        String hexString = Utils.hexString(remoteResource.digest);
        boolean z = true;
        Cursor query = getReadableDatabase().query("local_resources", new String[]{"digest"}, "fbid = ? AND digest = ?", new String[]{remoteResource.resourceId, hexString}, null, null, null, "1");
        try {
            if (!query.moveToFirst()) {
                return true;
            }
            if (!"".equals(hexString) && hexString.equalsIgnoreCase(query.getString(0))) {
                z = false;
            }
            query.close();
            return z;
        } finally {
            query.close();
        }
    }
}
