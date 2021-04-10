package oculus.internal.license.parsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.oculus.license.License;
import oculus.internal.license.Validator;
import oculus.internal.license.parsing.CodableLicense;
import oculus.internal.license.parsing.LicenseParsingException;
import org.json.JSONObject;

public final class LicenseParserImpl implements LicenseParser {
    private final Gson gson;

    public static Gson gson() {
        return new GsonBuilder().registerTypeAdapter(CodableLicense.CodablePackageFilter.CodableSigner.CodableDigest.class, new CodableDigestTypeAdapter()).registerTypeAdapter(JSONObject.class, new JSONObjectAdapter()).create();
    }

    public static LicenseParserImpl create() {
        return new LicenseParserImpl(gson());
    }

    LicenseParserImpl(Gson gson2) {
        this.gson = gson2;
    }

    @Override // oculus.internal.license.parsing.LicenseParser
    public License parse(String jsonString) throws LicenseParsingException {
        try {
            CodableLicense parsed = (CodableLicense) this.gson.fromJson(jsonString, CodableLicense.class);
            License license = parsed == null ? null : parsed.toLicense();
            if (license != null) {
                try {
                    Validator.validate(license);
                    return license;
                } catch (IllegalArgumentException e) {
                    throw new LicenseParsingException.InvalidLicense(license.id, e);
                }
            } else {
                throw new LicenseParsingException.InvalidLicense(0, "failed to parse license blob");
            }
        } catch (IllegalArgumentException | IllegalStateException e2) {
            throw new LicenseParsingException.IllegalOperation(e2);
        } catch (JsonParseException e3) {
            throw new LicenseParsingException.IllegalParseState(e3);
        }
    }
}
