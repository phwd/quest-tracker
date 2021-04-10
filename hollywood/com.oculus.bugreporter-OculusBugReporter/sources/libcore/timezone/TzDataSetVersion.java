package libcore.timezone;

import android.icu.impl.PatternTokenizer;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TzDataSetVersion {
    public static final int CURRENT_FORMAT_MAJOR_VERSION = 3;
    public static final int CURRENT_FORMAT_MINOR_VERSION = 1;
    public static final String DEFAULT_FILE_NAME = "tz_version";
    private static final Pattern FORMAT_VERSION_PATTERN = Pattern.compile("(\\d{3})\\.(\\d{3})");
    private static final int FORMAT_VERSION_STRING_LENGTH = FULL_CURRENT_FORMAT_VERSION_STRING.length();
    private static final String FULL_CURRENT_FORMAT_VERSION_STRING = toFormatVersionString(3, 1);
    private static final int REVISION_LENGTH = 3;
    private static final Pattern REVISION_PATTERN = Pattern.compile("(\\d{3})");
    private static final int RULES_VERSION_LENGTH = 5;
    private static final Pattern RULES_VERSION_PATTERN = Pattern.compile("(\\d{4}\\w)");
    private static final int TZ_DATA_VERSION_FILE_LENGTH = ((((FORMAT_VERSION_STRING_LENGTH + 1) + 5) + 1) + 3);
    private static final Pattern TZ_DATA_VERSION_FILE_PATTERN = Pattern.compile(FORMAT_VERSION_PATTERN.pattern() + "\\|" + RULES_VERSION_PATTERN.pattern() + "\\|" + REVISION_PATTERN.pattern() + ".*");
    public final int formatMajorVersion;
    public final int formatMinorVersion;
    public final int revision;
    public final String rulesVersion;

    public static int currentFormatMajorVersion() {
        return 3;
    }

    public static int currentFormatMinorVersion() {
        return 1;
    }

    public TzDataSetVersion(int formatMajorVersion2, int formatMinorVersion2, String rulesVersion2, int revision2) throws TzDataSetException {
        this.formatMajorVersion = validate3DigitVersion(formatMajorVersion2);
        this.formatMinorVersion = validate3DigitVersion(formatMinorVersion2);
        if (RULES_VERSION_PATTERN.matcher(rulesVersion2).matches()) {
            this.rulesVersion = rulesVersion2;
            this.revision = validate3DigitVersion(revision2);
            return;
        }
        throw new TzDataSetException("Invalid rulesVersion: " + rulesVersion2);
    }

    public static TzDataSetVersion fromBytes(byte[] bytes) throws TzDataSetException {
        String tzDataVersion = new String(bytes, StandardCharsets.US_ASCII);
        try {
            Matcher matcher = TZ_DATA_VERSION_FILE_PATTERN.matcher(tzDataVersion);
            if (matcher.matches()) {
                String formatMajorVersion2 = matcher.group(1);
                String formatMinorVersion2 = matcher.group(2);
                return new TzDataSetVersion(from3DigitVersionString(formatMajorVersion2), from3DigitVersionString(formatMinorVersion2), matcher.group(3), from3DigitVersionString(matcher.group(4)));
            }
            throw new TzDataSetException("Invalid tz data version string: \"" + tzDataVersion + "\"");
        } catch (IndexOutOfBoundsException e) {
            throw new TzDataSetException("tz data version string too short: \"" + tzDataVersion + "\"");
        }
    }

    public static TzDataSetVersion readFromFile(File file) throws IOException, TzDataSetException {
        return fromBytes(readBytes(file, TZ_DATA_VERSION_FILE_LENGTH));
    }

    public byte[] toBytes() {
        return toBytes(this.formatMajorVersion, this.formatMinorVersion, this.rulesVersion, this.revision);
    }

    private static byte[] toBytes(int majorFormatVersion, int minorFormatVerison, String rulesVersion2, int revision2) {
        return (toFormatVersionString(majorFormatVersion, minorFormatVerison) + "|" + rulesVersion2 + "|" + to3DigitVersionString(revision2)).getBytes(StandardCharsets.US_ASCII);
    }

    public static boolean isCompatibleWithThisDevice(TzDataSetVersion tzDataVersion) {
        return 3 == tzDataVersion.formatMajorVersion && 1 <= tzDataVersion.formatMinorVersion;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TzDataSetVersion that = (TzDataSetVersion) o;
        if (this.formatMajorVersion == that.formatMajorVersion && this.formatMinorVersion == that.formatMinorVersion && this.revision == that.revision) {
            return this.rulesVersion.equals(that.rulesVersion);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.formatMajorVersion * 31) + this.formatMinorVersion) * 31) + this.rulesVersion.hashCode()) * 31) + this.revision;
    }

    public String toString() {
        return "TzDataSetVersion{formatMajorVersion=" + this.formatMajorVersion + ", formatMinorVersion=" + this.formatMinorVersion + ", rulesVersion='" + this.rulesVersion + PatternTokenizer.SINGLE_QUOTE + ", revision=" + this.revision + '}';
    }

    private static String to3DigitVersionString(int version) {
        try {
            return String.format(Locale.ROOT, "%03d", Integer.valueOf(validate3DigitVersion(version)));
        } catch (TzDataSetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static int from3DigitVersionString(String versionString) throws TzDataSetException {
        if (versionString.length() == 3) {
            try {
                return validate3DigitVersion(Integer.parseInt(versionString));
            } catch (NumberFormatException e) {
                throw new TzDataSetException("versionString must be a zero padded, 3 digit, positive decimal integer", e);
            }
        } else {
            throw new TzDataSetException("versionString must be a zero padded, 3 digit, positive decimal integer");
        }
    }

    private static int validate3DigitVersion(int value) throws TzDataSetException {
        if (value >= 0 && value <= 999) {
            return value;
        }
        throw new TzDataSetException("Expected 0 <= value <= 999, was " + value);
    }

    private static String toFormatVersionString(int majorFormatVersion, int minorFormatVersion) {
        return to3DigitVersionString(majorFormatVersion) + "." + to3DigitVersionString(minorFormatVersion);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] readBytes(java.io.File r5, int r6) throws java.io.IOException {
        /*
            if (r6 <= 0) goto L_0x0024
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r5)
            byte[] r1 = new byte[r6]     // Catch:{ all -> 0x0018 }
            r2 = 0
            int r3 = r0.read(r1, r2, r6)     // Catch:{ all -> 0x0018 }
            byte[] r4 = new byte[r3]     // Catch:{ all -> 0x0018 }
            java.lang.System.arraycopy(r1, r2, r4, r2, r3)     // Catch:{ all -> 0x0018 }
            r0.close()
            return r4
        L_0x0018:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001a }
        L_0x001a:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0023:
            throw r2
        L_0x0024:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "maxBytes =="
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.timezone.TzDataSetVersion.readBytes(java.io.File, int):byte[]");
    }

    public static class TzDataSetException extends Exception {
        public TzDataSetException(String message) {
            super(message);
        }

        public TzDataSetException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
