package oculus.internal.license.parsing;

import com.oculus.license.License;

public interface LicenseParser {
    License parse(String str) throws LicenseParsingException;
}
