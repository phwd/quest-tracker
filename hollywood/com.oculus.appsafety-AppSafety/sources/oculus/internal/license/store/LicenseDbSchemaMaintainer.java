package oculus.internal.license.store;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.ToLongFunction;
import oculus.internal.license.store.LicenseStoreSchema;

public final class LicenseDbSchemaMaintainer {
    private static final String LICENSE_TABLE_CREATE_SQL = "CREATE TABLE licenses (fbid INTEGER PRIMARY KEY,blob BLOB,category TEXT,comment TEXT,issuer TEXT,issued INTEGER,expires INTEGER,requester TEXT);";
    private static final String LICENSE_TABLE_DROP_SQL = "DROP TABLE IF EXISTS licenses";
    private static final String PACKAGE_FILTER_TABLE_CREATE_SQL = ("CREATE TABLE package_filters (fbid INTEGER,identifier_pattern TEXT,signer_fingerprint BLOB," + String.format(" FOREIGN KEY(%s) REFERENCES %s(%s) ON DELETE CASCADE", "fbid", LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, "fbid") + ");" + String.format(" CREATE INDEX fbid_index ON %s(%s);", LicenseStoreSchema.PackageFilterTableSchema.TABLE_NAME, "fbid"));
    private static final String PACKAGE_FILTER_TABLE_DROP_SQL = "DROP TABLE IF EXISTS package_filters";
    private static final String REVOKED_LICENSE_TABLE_CREATE_SQL = "CREATE TABLE revoked_licenses (fbid INTEGER PRIMARY KEY);";
    private static final String REVOKED_LICENSE_TABLE_DROP_SQL = "DROP TABLE IF EXISTS revoked_licenses";
    public static final String TAG = "LicenseDbSchemaMaintainer";
    private final LicenseInstaller licenseInstaller;

    public LicenseDbSchemaMaintainer(LicenseInstaller licenseInstaller2) {
        this.licenseInstaller = licenseInstaller2;
    }

    public void initialize(DbWrapper db) throws SQLiteException {
        db.execSQL(LICENSE_TABLE_CREATE_SQL);
        db.execSQL(PACKAGE_FILTER_TABLE_CREATE_SQL);
        db.execSQL(REVOKED_LICENSE_TABLE_CREATE_SQL);
    }

    public void open(DbWrapper db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        if (r1 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004b, code lost:
        $closeResource(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004e, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int migrateDbSchema(oculus.internal.license.store.DbWrapper r8) {
        /*
            r7 = this;
            java.lang.String r0 = "LicenseDbSchemaMaintainer"
            r8.beginTransaction()
            android.database.Cursor r1 = r7.queryAllLicenseBlobs(r8)     // Catch:{ Exception -> 0x0051 }
            r2 = 0
            java.util.Collection r3 = r7.copyBlobs(r1)     // Catch:{ all -> 0x0046 }
            java.util.Collection r4 = r7.readAllRevokedFbids(r8)     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "DROP TABLE IF EXISTS licenses"
            r8.execSQL(r5)     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "DROP TABLE IF EXISTS package_filters"
            r8.execSQL(r5)     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "DROP TABLE IF EXISTS revoked_licenses"
            r8.execSQL(r5)     // Catch:{ all -> 0x0046 }
            r7.initialize(r8)     // Catch:{ all -> 0x0046 }
            boolean r5 = r7.insertRevokedFbids(r8, r4)     // Catch:{ all -> 0x0046 }
            if (r5 == 0) goto L_0x002f
            java.lang.String r6 = "ignoring error migrating revoked license fbids"
            android.util.Log.w(r0, r6)     // Catch:{ all -> 0x0046 }
        L_0x002f:
            oculus.internal.license.store.LicenseInstaller r6 = r7.licenseInstaller     // Catch:{ all -> 0x0046 }
            java.util.Collection r6 = r6.install(r8, r3)     // Catch:{ all -> 0x0046 }
            int r6 = r6.size()     // Catch:{ all -> 0x0046 }
            r8.setTransactionSuccessful()     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0042
            $closeResource(r2, r1)
        L_0x0042:
            r8.endTransaction()
            return r6
        L_0x0046:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r3 = move-exception
            if (r1 == 0) goto L_0x004e
            $closeResource(r2, r1)
        L_0x004e:
            throw r3
        L_0x004f:
            r0 = move-exception
            goto L_0x005c
        L_0x0051:
            r1 = move-exception
            java.lang.String r2 = "error re-installing licenses"
            android.util.Log.e(r0, r2, r1)     // Catch:{ all -> 0x004f }
            r0 = 0
            r8.endTransaction()
            return r0
        L_0x005c:
            r8.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.license.store.LicenseDbSchemaMaintainer.migrateDbSchema(oculus.internal.license.store.DbWrapper):int");
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 != null) {
            try {
                x1.close();
            } catch (Throwable th) {
                x0.addSuppressed(th);
            }
        } else {
            x1.close();
        }
    }

    /* access modifiers changed from: package-private */
    public Cursor queryAllLicenseBlobs(DbWrapper db) {
        return db.query(LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, new String[]{LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_BLOB}, null, null, null, null, null);
    }

    /* access modifiers changed from: package-private */
    public Collection<ByteBuffer> copyBlobs(Cursor cursor) {
        Collection<ByteBuffer> licenses = new LinkedList<>();
        while (cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_BLOB));
            licenses.add(ByteBuffer.wrap(Arrays.copyOf(blob, blob.length)));
        }
        return licenses;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        if (r1 != null) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        $closeResource(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<java.lang.Long> readAllRevokedFbids(oculus.internal.license.store.DbWrapper r11) {
        /*
            r10 = this;
            java.lang.String r0 = "fbid"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r9 = r1
            java.lang.String r2 = "revoked_licenses"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r11
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)
        L_0x001a:
            boolean r2 = r1.moveToNext()     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x0031
            int r2 = r1.getColumnIndex(r0)     // Catch:{ all -> 0x0036 }
            long r4 = r1.getLong(r2)     // Catch:{ all -> 0x0036 }
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0036 }
            r9.add(r2)     // Catch:{ all -> 0x0036 }
            goto L_0x001a
        L_0x0031:
            r0 = 0
            $closeResource(r0, r1)
            return r9
        L_0x0036:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r2 = move-exception
            if (r1 == 0) goto L_0x003e
            $closeResource(r0, r1)
        L_0x003e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.license.store.LicenseDbSchemaMaintainer.readAllRevokedFbids(oculus.internal.license.store.DbWrapper):java.util.Collection");
    }

    private boolean insertRevokedFbids(DbWrapper db, Collection<Long> fbids) {
        return 0 != fbids.stream().map($$Lambda$LicenseDbSchemaMaintainer$LMtt6La1t8WFt5qZM386d5DH8.INSTANCE).mapToLong(new ToLongFunction() {
            /* class oculus.internal.license.store.$$Lambda$LicenseDbSchemaMaintainer$M3pRc5DTIhG4a00nKKH2vti_gv0 */

            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return DbWrapper.this.insert(LicenseStoreSchema.RevokedLicenseTableSchema.TABLE_NAME, null, (ContentValues) obj);
            }
        }).filter($$Lambda$LicenseDbSchemaMaintainer$3S7ON3iR2F_SLRjGFGfmsLNO04.INSTANCE).count();
    }

    static /* synthetic */ boolean lambda$insertRevokedFbids$2(long r) {
        return r == -1;
    }
}
