package oculus.internal.license.store;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.PatternMatcher;
import com.oculus.license.Category;
import com.oculus.license.License;
import com.oculus.os.PackageMetadata;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import oculus.internal.functional.Try;
import oculus.internal.license.store.LicenseStoreSchema;

public class DatabaseLicenseStore extends DbWrapper {
    LicenseInstaller installer;
    LicenseLoader loader;
    Collection<LicenseStoreObserver> observers = new HashSet();
    LicensePackageQueryCache packageQueryCache;

    public DatabaseLicenseStore(SQLiteDatabase db, LicenseInstaller installer2, LicenseLoader loader2, LicensePackageQueryCache packageQueryCache2) {
        super(db);
        this.installer = installer2;
        this.loader = loader2;
        this.packageQueryCache = packageQueryCache2;
        registerObserver(packageQueryCache2);
    }

    public void registerObserver(LicenseStoreObserver observer) {
        this.observers.add(observer);
    }

    public void unregisterObserver(LicenseStoreObserver observer) {
        this.observers.remove(observer);
    }

    public License install(byte[] blob) throws LicenseStoreException {
        return install(Arrays.asList(ByteBuffer.wrap(blob))).stream().findFirst().get();
    }

    public Collection<License> install(Collection<ByteBuffer> blobCollection) throws LicenseStoreException {
        return install(blobCollection, 0);
    }

    public Collection<License> install(Collection<ByteBuffer> blobCollection, int flags) throws LicenseStoreException {
        Collection<License> installed;
        notifyBeginChange();
        this.observers.stream().forEach(new Consumer() {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$MQFqS5ReOsIXomLig1WUjfaBHfk */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((LicenseStoreObserver) obj).onBeginInstall(Collection.this);
            }
        });
        if ((flags & 1) != 0) {
            try {
                installed = this.installer.install(this, blobCollection);
            } catch (Throwable th) {
                notifyEndChange(false);
                throw th;
            }
        } else {
            installed = this.installer.installOrThrow(this, blobCollection);
        }
        this.observers.stream().forEach(new Consumer() {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$scKCO3rs7CKNBloZ6hVEpIu5auU */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((LicenseStoreObserver) obj).onInstalled(Collection.this);
            }
        });
        notifyEndChange(true);
        return installed;
    }

    public Collection<License> queryLicenses(String selection, String[] selectionArgs, String sortOrder) {
        return licensesFromRowStream(streamFromCursor(query(LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, null, selection, selectionArgs, null, null, sortOrder)));
    }

    public Collection<License> queryLicensesByPackage(String packageIdentifier, Collection<PackageMetadata.Signature> signatures) {
        Collection<License> licenses;
        if (signatures.isEmpty() && (licenses = this.packageQueryCache.get(packageIdentifier)) != null) {
            return licenses;
        }
        Collection<License> licenses2 = licensesFromRowStream(queryByPackage(packageIdentifier, signatures));
        if (signatures.isEmpty()) {
            this.packageQueryCache.put(packageIdentifier, licenses2);
        }
        return licenses2;
    }

    public Stream<ContentValues> queryByPackage(String packageIdentifier, Collection<PackageMetadata.Signature> signatures) {
        String identifierPatternCondition;
        String str;
        String fingerprintQueryCondition = signatures.size() > 0 ? formatPackageSignerClause(signatures) : null;
        if (packageIdentifier != null) {
            identifierPatternCondition = String.format(" (%s IS NULL OR  %s='%s' OR SUBSTR(%s, -1)='*')", "package_filters.identifier_pattern", "package_filters.identifier_pattern", packageIdentifier, "package_filters.identifier_pattern");
        } else {
            identifierPatternCondition = null;
        }
        String whereClause = "";
        if (fingerprintQueryCondition != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(" WHERE ");
            sb.append(fingerprintQueryCondition);
            if (identifierPatternCondition != null) {
                str = " AND " + identifierPatternCondition;
            } else {
                str = "";
            }
            sb.append(str);
            whereClause = sb.toString();
        } else if (identifierPatternCondition != null) {
            whereClause = " WHERE " + identifierPatternCondition;
        }
        Stream<ContentValues> stream = streamFromCursor(rawQuery(String.format("SELECT licenses.*, package_filters.identifier_pattern FROM licenses LEFT OUTER JOIN package_filters ON licenses.fbid=package_filters.fbid" + whereClause, new Object[0]), null));
        if (packageIdentifier != null) {
            stream = stream.filter(new Predicate() {
                /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$cCCdKku_8G9tN4JR9YSGVAeSyU8 */

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return DatabaseLicenseStore.rowMatchesPackageIdentifier((ContentValues) obj, String.this);
                }
            });
        }
        return stream.filter(distinctById());
    }

    public Collection<License> queryLicensesByExpiry(long expiresBy) {
        return licensesFromRowStream(queryByExpiry(expiresBy));
    }

    public Stream<ContentValues> queryByExpiry(long expiresBy) {
        return streamFromCursor(query(LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, null, "expires > 0 AND expires <= " + expiresBy, null, null, null, null));
    }

    public Collection<License> queryLicensesByCategory(Category category) {
        return licensesFromRowStream(queryByCategory(category));
    }

    public Stream<License> queryLicensesStreamByCategory(Category category) {
        return rowStreamToLicenseStream(queryByCategory(category));
    }

    public Stream<ContentValues> queryByCategory(Category category) {
        return streamFromCursor(query(LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, null, String.format("%s = '%s'", LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_CATEGORY, category.getValue()), null, null, null, null));
    }

    public void deleteLicenses(Collection<Long> fbids) {
        if (fbids.size() != 0) {
            String fbidConstraint = "fbid" + formatFbidConstraint(fbids);
            boolean successful = false;
            notifyBeginChange();
            this.observers.forEach(new Consumer() {
                /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$oYZ9svqBAKQlNmAvQmwAfx3MJ8 */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((LicenseStoreObserver) obj).onBeginDelete(Collection.this);
                }
            });
            beginTransaction();
            try {
                delete(LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, fbidConstraint, null);
                setTransactionSuccessful();
                this.observers.forEach(new Consumer() {
                    /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$XvSWHO6EIY7WZTGBCUH4xJlurA */

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((LicenseStoreObserver) obj).onDeleted(Collection.this);
                    }
                });
                successful = true;
            } finally {
                endTransaction();
                notifyEndChange(successful);
            }
        }
    }

    public boolean revokeLicenses(Collection<Long> fbids) {
        if (fbids.size() == 0) {
            return true;
        }
        String fbidConstraint = "fbid" + formatFbidConstraint(fbids);
        boolean successful = false;
        notifyBeginChange();
        this.observers.forEach(new Consumer() {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$7MN6gMjfWw4ctI4TGfuks198qY4 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((LicenseStoreObserver) obj).onBeginRevoke(Collection.this);
            }
        });
        this.observers.forEach(new Consumer() {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$LQjtJL7DZobPIdvanj7fMigdoGo */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((LicenseStoreObserver) obj).onBeginDelete(Collection.this);
            }
        });
        beginTransaction();
        try {
            delete(LicenseStoreSchema.LicenseTableSchema.TABLE_NAME, fbidConstraint, null);
            if (fbids.stream().distinct().map($$Lambda$DatabaseLicenseStore$2Pee_kvHoNVxQRTqr7dBz5uyOW0.INSTANCE).map(new Function() {
                /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$Jg8GcgUYMszRKI3Wg2JzKuQMV8A */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DatabaseLicenseStore.this.lambda$revokeLicenses$8$DatabaseLicenseStore((ContentValues) obj);
                }
            }).filter($$Lambda$DatabaseLicenseStore$oG64SJJ3g0OoYW5FQ0JoeiGz_k4.INSTANCE).count() == 0) {
                setTransactionSuccessful();
                this.observers.forEach(new Consumer() {
                    /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$AXYp5yyf9_ux4rLN39zIDsQXwE */

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((LicenseStoreObserver) obj).onDeleted(Collection.this);
                    }
                });
                this.observers.forEach(new Consumer() {
                    /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$lagXPYspGiAoRTS9phAflWXVTGU */

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((LicenseStoreObserver) obj).onRevoked(Collection.this);
                    }
                });
                successful = true;
            }
            return successful;
        } finally {
            endTransaction();
            notifyEndChange(successful);
        }
    }

    public /* synthetic */ Long lambda$revokeLicenses$8$DatabaseLicenseStore(ContentValues cv) {
        return Long.valueOf(this.db.insertWithOnConflict(LicenseStoreSchema.RevokedLicenseTableSchema.TABLE_NAME, null, cv, 5));
    }

    static /* synthetic */ boolean lambda$revokeLicenses$9(Long r) {
        return r.longValue() == -1;
    }

    public boolean unrevokeLicenses(Collection<Long> fbids) {
        if (fbids.size() == 0) {
            return true;
        }
        String fbidConstraint = "fbid" + formatFbidConstraint(fbids);
        boolean successful = false;
        notifyBeginChange();
        this.observers.forEach(new Consumer() {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$qsryZEJB4Gu1WOWhunT_bZRmm2s */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((LicenseStoreObserver) obj).onBeginUnrevoke(Collection.this);
            }
        });
        beginTransaction();
        try {
            delete(LicenseStoreSchema.RevokedLicenseTableSchema.TABLE_NAME, fbidConstraint, null);
            setTransactionSuccessful();
            this.observers.forEach(new Consumer() {
                /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$0o284tLTgbhvNxCEqyBChSP_AE */

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((LicenseStoreObserver) obj).onUnrevoked(Collection.this);
                }
            });
            successful = true;
            return successful;
        } finally {
            endTransaction();
            notifyEndChange(successful);
        }
    }

    public Collection<License> licensesFromRowStream(Stream<ContentValues> rows) {
        return (Collection) rowStreamToLicenseStream(rows).collect(Collectors.toList());
    }

    public /* synthetic */ License lambda$rowStreamToLicenseStream$14$DatabaseLicenseStore(ContentValues r) throws Exception {
        return this.loader.load(r);
    }

    public /* synthetic */ License lambda$rowStreamToLicenseStream$15$DatabaseLicenseStore(ContentValues r) {
        return (License) Try.Try(new Try.F0(r) {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$xeQXwV3jTiUkUH30Ee1aC0qYA6g */
            private final /* synthetic */ ContentValues f$1;

            {
                this.f$1 = r2;
            }

            public final Object get() {
                return DatabaseLicenseStore.this.lambda$rowStreamToLicenseStream$14$DatabaseLicenseStore(this.f$1);
            }
        }).orElse((Object) null);
    }

    public Stream<License> rowStreamToLicenseStream(Stream<ContentValues> rows) {
        return rows.map(new Function() {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$iJNKCfGsPJc8B5qtKe9qMdktZr0 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DatabaseLicenseStore.this.lambda$rowStreamToLicenseStream$15$DatabaseLicenseStore((ContentValues) obj);
            }
        }).filter($$Lambda$SddSBZHi6vAVVICJqv16KPm_TfA.INSTANCE);
    }

    public static String formatFbidConstraint(Collection<Long> fbids) {
        return " IN (" + ((String) fbids.stream().map($$Lambda$yPICcPHcLq6ptOeaCrb6uDYWEIA.INSTANCE).collect(Collectors.joining(","))) + ")";
    }

    /* access modifiers changed from: package-private */
    public String formatPackageSignerClause(Collection<PackageMetadata.Signature> signatures) {
        if (signatures.size() == 0) {
            return "";
        }
        return "package_filters.signer_fingerprint IN " + ("(" + ((String) signatures.stream().map($$Lambda$DatabaseLicenseStore$BQ4_GqhLq4g1DGpd0AWoc9m45m0.INSTANCE).map($$Lambda$4MOUGDbqIhUurwcLBLp9j5VASrI.INSTANCE).map($$Lambda$DcjOpslNXivKahO4QPhY2mMPmok.INSTANCE).map($$Lambda$DatabaseLicenseStore$kEhv814OnYPgYbWuSNIK8ZRN1ww.INSTANCE).filter($$Lambda$DatabaseLicenseStore$v4PCTAMuns7iyKyZIEYsqCLPxk.INSTANCE).map($$Lambda$3Q9D5ethWmHHaKd02rM4coTfHUw.INSTANCE).map($$Lambda$DatabaseLicenseStore$bDu1b525vLfTJ9kgvy92mh5F1KU.INSTANCE).collect(Collectors.joining(","))) + ")");
    }

    static /* synthetic */ boolean lambda$formatPackageSignerClause$17(byte[] d) {
        return d != null;
    }

    private static Predicate<ContentValues> distinctById() {
        return new Predicate() {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$cEUIW1UZ9joelAADQ_evzcxmuMo */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Set.this.add(((ContentValues) obj).getAsLong("fbid"));
            }
        };
    }

    /* access modifiers changed from: private */
    public static byte[] md5(byte[] buffer) {
        try {
            return MessageDigest.getInstance("MD5").digest(buffer);
        } catch (Exception e) {
            return null;
        }
    }

    public static Stream<ContentValues> streamFromCursor(Cursor cursor) {
        CursorIterator<ContentValues> iterator = new CursorIterator<>(cursor, $$Lambda$DatabaseLicenseStore$Yu2zbPx0fPpuKPikIkYPFkP61sg.INSTANCE);
        return (Stream) StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 16), false).onClose(new Runnable() {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$5WCEdZngjBLgs7Pg7QZ5RECJFN0 */

            @Override // java.lang.Runnable
            public final void run() {
                CursorIterator.this.close();
            }
        });
    }

    private static byte[] getBlobFromCursor(Cursor cursor) {
        return cursor.getBlob(cursor.getColumnIndexOrThrow(LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_BLOB));
    }

    /* access modifiers changed from: private */
    public static ContentValues cursorRowToContentValues(Cursor cursor) {
        ContentValues values = new ContentValues();
        DatabaseUtils.cursorRowToContentValues(cursor, values);
        return values;
    }

    /* access modifiers changed from: private */
    public static boolean rowMatchesPackageIdentifier(ContentValues values, String packageIdentifier) {
        String pattern = values.getAsString(LicenseStoreSchema.PackageFilterTableSchema.COLUMN_NAME_IDENTIFIER_PATTERN);
        if (pattern == null || pattern.equals("*")) {
            return true;
        }
        int algorithm = 0;
        if (pattern.endsWith("*")) {
            pattern = pattern.substring(0, pattern.length() - 1);
            algorithm = 1;
        }
        return new PatternMatcher(pattern, algorithm).match(packageIdentifier);
    }

    private void notifyBeginChange() {
        this.observers.stream().forEach($$Lambda$DatabaseLicenseStore$QMi_QrTUZHA3JpKR6hKDoQ6ts.INSTANCE);
    }

    private void notifyEndChange(boolean successful) {
        this.observers.stream().forEach(new Consumer(successful) {
            /* class oculus.internal.license.store.$$Lambda$DatabaseLicenseStore$jZ3ofgwwho9jiwgbCjLiSWOlFz4 */
            private final /* synthetic */ boolean f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((LicenseStoreObserver) obj).onChanged(this.f$0);
            }
        });
    }
}
