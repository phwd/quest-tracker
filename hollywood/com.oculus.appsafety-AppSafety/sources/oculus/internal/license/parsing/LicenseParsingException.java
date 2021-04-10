package oculus.internal.license.parsing;

public abstract class LicenseParsingException extends Exception {
    public LicenseParsingException(Throwable cause) {
        super(cause);
    }

    public LicenseParsingException(String message) {
        super(message);
    }

    public static class IllegalOperation extends LicenseParsingException {
        public IllegalOperation(Throwable cause) {
            super(cause);
        }
    }

    public static class IllegalParseState extends LicenseParsingException {
        public IllegalParseState(Throwable cause) {
            super(cause);
        }

        public IllegalParseState(String message) {
            super(message);
        }
    }

    public static class IllegalInput extends LicenseParsingException {
        public IllegalInput(Throwable cause) {
            super(cause);
        }

        public IllegalInput(String message) {
            super(message);
        }
    }

    public static class InvalidLicense extends LicenseParsingException {
        public final long licenseId;

        public InvalidLicense(long licenseId2, Throwable cause) {
            super(cause);
            this.licenseId = licenseId2;
        }

        public InvalidLicense(long licenseId2, String message) {
            super(message);
            this.licenseId = licenseId2;
        }
    }
}
