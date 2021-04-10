package oculus.internal.license.store;

import android.content.ContentValues;
import android.provider.BaseColumns;
import com.oculus.license.License;
import com.oculus.license.PackageFilter;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import oculus.internal.functional.Pair;
import oculus.internal.functional.Try;

public final class LicenseStoreSchema {
    public static final int DATABASE_SCHEMA_VERSION = 2;

    public static class LicenseTableSchema implements BaseColumns {
        public static final String COLUMN_NAME_BLOB = "blob";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_COMMENT = "comment";
        public static final String COLUMN_NAME_EXPIRES = "expires";
        public static final String COLUMN_NAME_FBID = "fbid";
        public static final String COLUMN_NAME_ISSUED = "issued";
        public static final String COLUMN_NAME_ISSUER = "issuer";
        public static final String COLUMN_NAME_REQUESTER = "requester";
        public static final String TABLE_NAME = "licenses";
        public static final String _ID = "fbid";
    }

    public static class PackageFilterTableSchema implements BaseColumns {
        public static final String COLUMN_NAME_FBID = "fbid";
        public static final String COLUMN_NAME_IDENTIFIER_PATTERN = "identifier_pattern";
        public static final String COLUMN_NAME_SIGNER_FINGERPRINT = "signer_fingerprint";
        public static final String TABLE_NAME = "package_filters";
    }

    public static class RevokedLicenseTableSchema implements BaseColumns {
        public static final String COLUMN_NAME_FBID = "fbid";
        public static final String TABLE_NAME = "revoked_licenses";
        public static final String _ID = "fbid";
    }

    private LicenseStoreSchema() {
    }

    public static ContentValues formatLicenseTableRecord(License license, ByteBuffer blob) {
        ContentValues values = new ContentValues();
        values.put("fbid", Long.valueOf(license.id));
        values.put(LicenseTableSchema.COLUMN_NAME_BLOB, blob.array());
        values.put(LicenseTableSchema.COLUMN_NAME_CATEGORY, license.category.getValue());
        values.put(LicenseTableSchema.COLUMN_NAME_COMMENT, license.comment);
        values.put("issuer", license.issuer);
        values.put(LicenseTableSchema.COLUMN_NAME_ISSUED, Long.valueOf(license.issued));
        values.put(LicenseTableSchema.COLUMN_NAME_EXPIRES, Long.valueOf(license.expires));
        values.put(LicenseTableSchema.COLUMN_NAME_REQUESTER, license.requester);
        return values;
    }

    public static Collection<ContentValues> formatPackageFilterTableRecords(License license) {
        return (Collection) ((Try) license.packageFilters.stream().map(new Function(license) {
            /* class oculus.internal.license.store.$$Lambda$LicenseStoreSchema$qFeBHcA67pFyNJx26ThboM0YOc */
            private final /* synthetic */ License f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LicenseStoreSchema.lambda$formatPackageFilterTableRecords$0(this.f$0, (PackageFilter) obj);
            }
        }).flatMap($$Lambda$LicenseStoreSchema$vLVpQvyYkHMuh_XH78e_rgeE.INSTANCE).collect(Try.fold(Collectors.toList()))).orElse((Object) null);
    }

    static /* synthetic */ Pair lambda$formatPackageFilterTableRecords$0(License license, PackageFilter pf) {
        ContentValues prototype = new ContentValues();
        prototype.put("fbid", Long.valueOf(license.id));
        prototype.put(PackageFilterTableSchema.COLUMN_NAME_IDENTIFIER_PATTERN, pf.identifierPattern);
        return new Pair(prototype, pf.signers);
    }

    static /* synthetic */ ContentValues lambda$formatPackageFilterTableRecords$4(Pair p, byte[] d) throws Exception {
        ContentValues values = new ContentValues((ContentValues) p.left);
        values.put(PackageFilterTableSchema.COLUMN_NAME_SIGNER_FINGERPRINT, d);
        return values;
    }

    public static ContentValues formatRevokedLicenseTableRecord(long id) {
        ContentValues values = new ContentValues();
        values.put("fbid", Long.valueOf(id));
        return values;
    }
}
