package oculus.internal.license.store;

import android.content.ContentValues;
import com.oculus.license.License;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import oculus.internal.license.LicenseFormatException;
import oculus.internal.license.SignerNotFoundException;
import oculus.internal.license.parsing.LicenseBlobParser;
import oculus.internal.license.parsing.LicenseParser;
import oculus.internal.license.parsing.LicenseParsingException;
import oculus.internal.license.store.LicenseStoreSchema;

public class BlobLoader implements LicenseLoader {
    private final LicenseBlobParser blobParser;
    private final LicenseParser licenseParser;

    public BlobLoader(LicenseBlobParser blobParser2, LicenseParser licenseParser2) {
        this.blobParser = blobParser2;
        this.licenseParser = licenseParser2;
    }

    @Override // oculus.internal.license.store.LicenseLoader
    public License load(ContentValues row) throws LicenseFormatException, SignatureException, SignerNotFoundException, LicenseParsingException {
        return this.licenseParser.parse(StandardCharsets.UTF_8.decode(this.blobParser.parseLicense(row.getAsByteArray(LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_BLOB))).toString());
    }
}
