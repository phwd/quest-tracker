package libcore.timezone;

import android.icu.util.TimeZone;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import libcore.timezone.CountryTimeZones;
import libcore.timezone.TimeZoneFinder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class TimeZoneFinder {
    private static final String COUNTRY_CODE_ATTRIBUTE = "code";
    private static final String COUNTRY_ELEMENT = "country";
    private static final String COUNTRY_ZONES_ELEMENT = "countryzones";
    private static final String DEFAULT_TIME_ZONE_ID_ATTRIBUTE = "default";
    private static final String EVER_USES_UTC_ATTRIBUTE = "everutc";
    private static final String FALSE_ATTRIBUTE_VALUE = "n";
    private static final String IANA_VERSION_ATTRIBUTE = "ianaversion";
    private static final String TIMEZONES_ELEMENT = "timezones";
    private static final String TRUE_ATTRIBUTE_VALUE = "y";
    private static final String TZLOOKUP_FILE_NAME = "tzlookup.xml";
    private static final String ZONE_ID_ELEMENT = "id";
    private static final String ZONE_NOT_USED_AFTER_ATTRIBUTE = "notafter";
    private static final String ZONE_SHOW_IN_PICKER_ATTRIBUTE = "picker";
    private static TimeZoneFinder instance;
    private CountryTimeZones lastCountryTimeZones;
    private final ReaderSupplier xmlSource;

    private TimeZoneFinder(ReaderSupplier xmlSource2) {
        this.xmlSource = xmlSource2;
    }

    public static TimeZoneFinder getInstance() {
        synchronized (TimeZoneFinder.class) {
            if (instance == null) {
                instance = createInstanceWithFallback(TimeZoneDataFiles.getTimeZoneFilePaths(TZLOOKUP_FILE_NAME));
            }
        }
        return instance;
    }

    public static TimeZoneFinder createInstanceWithFallback(String... tzLookupFilePaths) {
        IOException lastException = null;
        int length = tzLookupFilePaths.length;
        for (int i = 0; i < length; i++) {
            try {
                return createInstance(tzLookupFilePaths[i]);
            } catch (IOException e) {
                if (lastException != null) {
                    e.addSuppressed(lastException);
                }
                lastException = e;
            }
        }
        System.logE("No valid file found in set: " + Arrays.toString(tzLookupFilePaths) + " Printing exceptions and falling back to empty map.", lastException);
        return createInstanceForTests("<timezones><countryzones /></timezones>");
    }

    public static TimeZoneFinder createInstance(String path) throws IOException {
        return new TimeZoneFinder(ReaderSupplier.forFile(path, StandardCharsets.UTF_8));
    }

    public static TimeZoneFinder createInstanceForTests(String xml) {
        return new TimeZoneFinder(ReaderSupplier.forString(xml));
    }

    public void validate() throws IOException {
        try {
            processXml(new TimeZonesValidator());
        } catch (XmlPullParserException e) {
            throw new IOException("Parsing error", e);
        }
    }

    public String getIanaVersion() {
        IanaVersionExtractor ianaVersionExtractor = new IanaVersionExtractor();
        try {
            processXml(ianaVersionExtractor);
            return ianaVersionExtractor.getIanaVersion();
        } catch (IOException | XmlPullParserException e) {
            return null;
        }
    }

    public CountryZonesFinder getCountryZonesFinder() {
        CountryZonesLookupExtractor extractor = new CountryZonesLookupExtractor();
        try {
            processXml(extractor);
            return extractor.getCountryZonesLookup();
        } catch (IOException | XmlPullParserException e) {
            System.logW("Error reading country zones ", e);
            return null;
        }
    }

    public TimeZone lookupTimeZoneByCountryAndOffset(String countryIso, int offsetMillis, boolean isDst, long whenMillis, TimeZone bias) {
        CountryTimeZones.OffsetResult offsetResult;
        CountryTimeZones countryTimeZones = lookupCountryTimeZones(countryIso);
        if (countryTimeZones == null || (offsetResult = countryTimeZones.lookupByOffsetWithBias(offsetMillis, isDst, whenMillis, bias)) == null) {
            return null;
        }
        return offsetResult.mTimeZone;
    }

    public String lookupDefaultTimeZoneIdByCountry(String countryIso) {
        CountryTimeZones countryTimeZones = lookupCountryTimeZones(countryIso);
        if (countryTimeZones == null) {
            return null;
        }
        return countryTimeZones.getDefaultTimeZoneId();
    }

    public List<TimeZone> lookupTimeZonesByCountry(String countryIso) {
        CountryTimeZones countryTimeZones = lookupCountryTimeZones(countryIso);
        if (countryTimeZones == null) {
            return null;
        }
        return countryTimeZones.getIcuTimeZones();
    }

    public List<String> lookupTimeZoneIdsByCountry(String countryIso) {
        CountryTimeZones countryTimeZones = lookupCountryTimeZones(countryIso);
        if (countryTimeZones == null) {
            return null;
        }
        return extractTimeZoneIds(countryTimeZones.getTimeZoneMappings());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        r0 = new libcore.timezone.TimeZoneFinder.SelectiveCountryTimeZonesExtractor(r5, null);
        processXml(r0);
        r2 = r0.getValidatedCountryTimeZones();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r2 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r4.lastCountryTimeZones = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0026, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002b, code lost:
        java.lang.System.logW("Error reading country zones ", r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0030, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public libcore.timezone.CountryTimeZones lookupCountryTimeZones(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            libcore.timezone.CountryTimeZones r0 = r4.lastCountryTimeZones     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0011
            libcore.timezone.CountryTimeZones r0 = r4.lastCountryTimeZones     // Catch:{ all -> 0x0031 }
            boolean r0 = r0.isForCountryCode(r5)     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0011
            libcore.timezone.CountryTimeZones r0 = r4.lastCountryTimeZones     // Catch:{ all -> 0x0031 }
            monitor-exit(r4)     // Catch:{ all -> 0x0031 }
            return r0
        L_0x0011:
            monitor-exit(r4)     // Catch:{ all -> 0x0031 }
            libcore.timezone.TimeZoneFinder$SelectiveCountryTimeZonesExtractor r0 = new libcore.timezone.TimeZoneFinder$SelectiveCountryTimeZonesExtractor
            r1 = 0
            r0.<init>(r5)
            r4.processXml(r0)
            libcore.timezone.CountryTimeZones r2 = r0.getValidatedCountryTimeZones()
            if (r2 != 0) goto L_0x0022
            return r1
        L_0x0022:
            monitor-enter(r4)
            r4.lastCountryTimeZones = r2     // Catch:{ all -> 0x0027 }
            monitor-exit(r4)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x0027:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0027 }
            throw r3     // Catch:{ IOException | XmlPullParserException -> 0x002a }
        L_0x002a:
            r2 = move-exception
            java.lang.String r3 = "Error reading country zones "
            java.lang.System.logW(r3, r2)
            return r1
        L_0x0031:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.timezone.TimeZoneFinder.lookupCountryTimeZones(java.lang.String):libcore.timezone.CountryTimeZones");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        if (r2 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005a, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processXml(libcore.timezone.TimeZoneFinder.TimeZonesProcessor r8) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "countryzones"
            java.lang.String r1 = "timezones"
            libcore.timezone.TimeZoneFinder$ReaderSupplier r2 = r7.xmlSource
            java.io.Reader r2 = r2.get()
            org.xmlpull.v1.XmlPullParserFactory r3 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ all -> 0x0050 }
            r4 = 0
            r3.setNamespaceAware(r4)     // Catch:{ all -> 0x0050 }
            org.xmlpull.v1.XmlPullParser r4 = r3.newPullParser()     // Catch:{ all -> 0x0050 }
            r4.setInput(r2)     // Catch:{ all -> 0x0050 }
            findRequiredStartTag(r4, r1)     // Catch:{ all -> 0x0050 }
            r5 = 0
            java.lang.String r6 = "ianaversion"
            java.lang.String r5 = r4.getAttributeValue(r5, r6)     // Catch:{ all -> 0x0050 }
            boolean r6 = r8.processHeader(r5)     // Catch:{ all -> 0x0050 }
            if (r6 != 0) goto L_0x002f
            if (r2 == 0) goto L_0x002e
            r2.close()
        L_0x002e:
            return
        L_0x002f:
            findRequiredStartTag(r4, r0)
            boolean r6 = processCountryZones(r4, r8)
            if (r6 != 0) goto L_0x003e
            if (r2 == 0) goto L_0x003d
            r2.close()
        L_0x003d:
            return
        L_0x003e:
            checkOnEndTag(r4, r0)
            r4.next()
            consumeUntilEndTag(r4, r1)
            checkOnEndTag(r4, r1)
            if (r2 == 0) goto L_0x004f
            r2.close()
        L_0x004f:
            return
        L_0x0050:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0052 }
        L_0x0052:
            r1 = move-exception
            if (r2 == 0) goto L_0x005d
            r2.close()     // Catch:{ all -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r3 = move-exception
            r0.addSuppressed(r3)
        L_0x005d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.timezone.TimeZoneFinder.processXml(libcore.timezone.TimeZoneFinder$TimeZonesProcessor):void");
    }

    private static boolean processCountryZones(XmlPullParser parser, TimeZonesProcessor processor) throws IOException, XmlPullParserException {
        while (findOptionalStartTag(parser, COUNTRY_ELEMENT)) {
            if (processor == null) {
                consumeUntilEndTag(parser, COUNTRY_ELEMENT);
            } else {
                String code = parser.getAttributeValue(null, COUNTRY_CODE_ATTRIBUTE);
                if (code == null || code.isEmpty()) {
                    throw new XmlPullParserException("Unable to find country code: " + parser.getPositionDescription());
                }
                String defaultTimeZoneId = parser.getAttributeValue(null, DEFAULT_TIME_ZONE_ID_ATTRIBUTE);
                if (defaultTimeZoneId == null || defaultTimeZoneId.isEmpty()) {
                    throw new XmlPullParserException("Unable to find default time zone ID: " + parser.getPositionDescription());
                }
                Boolean everUsesUtc = parseBooleanAttribute(parser, EVER_USES_UTC_ATTRIBUTE, null);
                if (everUsesUtc != null) {
                    String debugInfo = parser.getPositionDescription();
                    if (!processor.processCountryZones(code, defaultTimeZoneId, everUsesUtc.booleanValue(), parseTimeZoneMappings(parser), debugInfo)) {
                        return false;
                    }
                } else {
                    throw new XmlPullParserException("Unable to find UTC hint attribute (everutc): " + parser.getPositionDescription());
                }
            }
            checkOnEndTag(parser, COUNTRY_ELEMENT);
        }
        return true;
    }

    private static List<CountryTimeZones.TimeZoneMapping> parseTimeZoneMappings(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<CountryTimeZones.TimeZoneMapping> timeZoneMappings = new ArrayList<>();
        while (findOptionalStartTag(parser, "id")) {
            boolean showInPicker = parseBooleanAttribute(parser, ZONE_SHOW_IN_PICKER_ATTRIBUTE, true).booleanValue();
            Long notUsedAfter = parseLongAttribute(parser, ZONE_NOT_USED_AFTER_ATTRIBUTE, null);
            String zoneIdString = consumeText(parser);
            checkOnEndTag(parser, "id");
            if (zoneIdString == null || zoneIdString.length() == 0) {
                throw new XmlPullParserException("Missing text for id): " + parser.getPositionDescription());
            }
            timeZoneMappings.add(new CountryTimeZones.TimeZoneMapping(zoneIdString, showInPicker, notUsedAfter));
        }
        return Collections.unmodifiableList(timeZoneMappings);
    }

    private static Long parseLongAttribute(XmlPullParser parser, String attributeName, Long defaultValue) throws XmlPullParserException {
        String attributeValueString = parser.getAttributeValue(null, attributeName);
        if (attributeValueString == null) {
            return defaultValue;
        }
        try {
            return Long.valueOf(Long.parseLong(attributeValueString));
        } catch (NumberFormatException e) {
            throw new XmlPullParserException("Attribute \"" + attributeName + "\" is not a long value: " + parser.getPositionDescription());
        }
    }

    private static Boolean parseBooleanAttribute(XmlPullParser parser, String attributeName, Boolean defaultValue) throws XmlPullParserException {
        String attributeValueString = parser.getAttributeValue(null, attributeName);
        if (attributeValueString == null) {
            return defaultValue;
        }
        boolean isTrue = "y".equals(attributeValueString);
        if (isTrue || FALSE_ATTRIBUTE_VALUE.equals(attributeValueString)) {
            return Boolean.valueOf(isTrue);
        }
        throw new XmlPullParserException("Attribute \"" + attributeName + "\" is not \"y\" or \"n\": " + parser.getPositionDescription());
    }

    private static void findRequiredStartTag(XmlPullParser parser, String elementName) throws IOException, XmlPullParserException {
        findStartTag(parser, elementName, true);
    }

    private static boolean findOptionalStartTag(XmlPullParser parser, String elementName) throws IOException, XmlPullParserException {
        return findStartTag(parser, elementName, false);
    }

    private static boolean findStartTag(XmlPullParser parser, String elementName, boolean elementRequired) throws IOException, XmlPullParserException {
        while (true) {
            int type = parser.next();
            if (type == 1) {
                throw new XmlPullParserException("Unexpected end of document while looking for " + elementName);
            } else if (type == 2) {
                String currentElementName = parser.getName();
                if (elementName.equals(currentElementName)) {
                    return true;
                }
                parser.next();
                consumeUntilEndTag(parser, currentElementName);
            } else if (type == 3) {
                if (!elementRequired) {
                    return false;
                }
                throw new XmlPullParserException("No child element found with name " + elementName);
            }
        }
    }

    private static void consumeUntilEndTag(XmlPullParser parser, String elementName) throws IOException, XmlPullParserException {
        if (parser.getEventType() != 3 || !elementName.equals(parser.getName())) {
            int requiredDepth = parser.getDepth();
            if (parser.getEventType() == 2) {
                requiredDepth--;
            }
            while (parser.getEventType() != 1) {
                int type = parser.next();
                int currentDepth = parser.getDepth();
                if (currentDepth < requiredDepth) {
                    throw new XmlPullParserException("Unexpected depth while looking for end tag: " + parser.getPositionDescription());
                } else if (currentDepth == requiredDepth && type == 3) {
                    if (!elementName.equals(parser.getName())) {
                        throw new XmlPullParserException("Unexpected eng tag: " + parser.getPositionDescription());
                    }
                    return;
                }
            }
            throw new XmlPullParserException("Unexpected end of document");
        }
    }

    private static String consumeText(XmlPullParser parser) throws IOException, XmlPullParserException {
        int type = parser.next();
        if (type == 4) {
            String text = parser.getText();
            int type2 = parser.next();
            if (type2 == 3) {
                return text;
            }
            throw new XmlPullParserException("Unexpected nested tag or end of document when expecting text: type=" + type2 + " at " + parser.getPositionDescription());
        }
        throw new XmlPullParserException("Text not found. Found type=" + type + " at " + parser.getPositionDescription());
    }

    private static void checkOnEndTag(XmlPullParser parser, String elementName) throws XmlPullParserException {
        if (parser.getEventType() != 3 || !parser.getName().equals(elementName)) {
            throw new XmlPullParserException("Unexpected tag encountered: " + parser.getPositionDescription());
        }
    }

    /* access modifiers changed from: private */
    public interface TimeZonesProcessor {
        public static final boolean CONTINUE = true;
        public static final boolean HALT = false;

        default boolean processHeader(String ianaVersion) throws XmlPullParserException {
            return true;
        }

        default boolean processCountryZones(String countryIso, String defaultTimeZoneId, boolean everUsesUtc, List<CountryTimeZones.TimeZoneMapping> list, String debugInfo) throws XmlPullParserException {
            return true;
        }
    }

    private static class TimeZonesValidator implements TimeZonesProcessor {
        private final Set<String> knownCountryCodes;

        private TimeZonesValidator() {
            this.knownCountryCodes = new HashSet();
        }

        @Override // libcore.timezone.TimeZoneFinder.TimeZonesProcessor
        public boolean processCountryZones(String countryIso, String defaultTimeZoneId, boolean everUsesUtc, List<CountryTimeZones.TimeZoneMapping> timeZoneMappings, String debugInfo) throws XmlPullParserException {
            if (!TimeZoneFinder.normalizeCountryIso(countryIso).equals(countryIso)) {
                throw new XmlPullParserException("Country code: " + countryIso + " is not normalized at " + debugInfo);
            } else if (this.knownCountryCodes.contains(countryIso)) {
                throw new XmlPullParserException("Second entry for country code: " + countryIso + " at " + debugInfo);
            } else if (timeZoneMappings.isEmpty()) {
                throw new XmlPullParserException("No time zone IDs for country code: " + countryIso + " at " + debugInfo);
            } else if (CountryTimeZones.TimeZoneMapping.containsTimeZoneId(timeZoneMappings, defaultTimeZoneId)) {
                this.knownCountryCodes.add(countryIso);
                return true;
            } else {
                throw new XmlPullParserException("defaultTimeZoneId for country code: " + countryIso + " is not one of the zones " + ((Object) timeZoneMappings) + " at " + debugInfo);
            }
        }
    }

    private static class IanaVersionExtractor implements TimeZonesProcessor {
        private String ianaVersion;

        private IanaVersionExtractor() {
        }

        @Override // libcore.timezone.TimeZoneFinder.TimeZonesProcessor
        public boolean processHeader(String ianaVersion2) throws XmlPullParserException {
            this.ianaVersion = ianaVersion2;
            return false;
        }

        public String getIanaVersion() {
            return this.ianaVersion;
        }
    }

    private static class CountryZonesLookupExtractor implements TimeZonesProcessor {
        private List<CountryTimeZones> countryTimeZonesList;

        private CountryZonesLookupExtractor() {
            this.countryTimeZonesList = new ArrayList(250);
        }

        @Override // libcore.timezone.TimeZoneFinder.TimeZonesProcessor
        public boolean processCountryZones(String countryIso, String defaultTimeZoneId, boolean everUsesUtc, List<CountryTimeZones.TimeZoneMapping> timeZoneMappings, String debugInfo) throws XmlPullParserException {
            this.countryTimeZonesList.add(CountryTimeZones.createValidated(countryIso, defaultTimeZoneId, everUsesUtc, timeZoneMappings, debugInfo));
            return true;
        }

        /* access modifiers changed from: package-private */
        public CountryZonesFinder getCountryZonesLookup() {
            return new CountryZonesFinder(this.countryTimeZonesList);
        }
    }

    /* access modifiers changed from: private */
    public static class SelectiveCountryTimeZonesExtractor implements TimeZonesProcessor {
        private final String countryCodeToMatch;
        private CountryTimeZones validatedCountryTimeZones;

        private SelectiveCountryTimeZonesExtractor(String countryCodeToMatch2) {
            this.countryCodeToMatch = TimeZoneFinder.normalizeCountryIso(countryCodeToMatch2);
        }

        @Override // libcore.timezone.TimeZoneFinder.TimeZonesProcessor
        public boolean processCountryZones(String countryIso, String defaultTimeZoneId, boolean everUsesUtc, List<CountryTimeZones.TimeZoneMapping> timeZoneMappings, String debugInfo) {
            String countryIso2 = TimeZoneFinder.normalizeCountryIso(countryIso);
            if (!this.countryCodeToMatch.equals(countryIso2)) {
                return true;
            }
            this.validatedCountryTimeZones = CountryTimeZones.createValidated(countryIso2, defaultTimeZoneId, everUsesUtc, timeZoneMappings, debugInfo);
            return false;
        }

        /* access modifiers changed from: package-private */
        public CountryTimeZones getValidatedCountryTimeZones() {
            return this.validatedCountryTimeZones;
        }
    }

    /* access modifiers changed from: private */
    public interface ReaderSupplier {
        Reader get() throws IOException;

        static default ReaderSupplier forFile(String fileName, Charset charSet) throws IOException {
            Path file = Paths.get(fileName, new String[0]);
            if (!Files.exists(file, new LinkOption[0])) {
                throw new FileNotFoundException(fileName + " does not exist");
            } else if (Files.isRegularFile(file, new LinkOption[0]) || !Files.isReadable(file)) {
                return new ReaderSupplier(charSet) {
                    /* class libcore.timezone.$$Lambda$TimeZoneFinder$ReaderSupplier$gDAfECMWS_ohJ_Rfk1HN7JyDSJA */
                    private final /* synthetic */ Charset f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // libcore.timezone.TimeZoneFinder.ReaderSupplier
                    public final Reader get() {
                        return Files.newBufferedReader(Path.this, this.f$1);
                    }
                };
            } else {
                throw new IOException(fileName + " must be a regular readable file.");
            }
        }

        static default ReaderSupplier forString(String xml) {
            return new ReaderSupplier() {
                /* class libcore.timezone.$$Lambda$TimeZoneFinder$ReaderSupplier$Q2dnwJWibh29nQ77BtDmdnZnbdI */

                @Override // libcore.timezone.TimeZoneFinder.ReaderSupplier
                public final Reader get() {
                    return TimeZoneFinder.ReaderSupplier.lambda$forString$1(String.this);
                }
            };
        }

        static /* synthetic */ default Reader lambda$forString$1(String xml) throws IOException {
            return new StringReader(xml);
        }
    }

    private static List<String> extractTimeZoneIds(List<CountryTimeZones.TimeZoneMapping> timeZoneMappings) {
        List<String> zoneIds = new ArrayList<>(timeZoneMappings.size());
        for (CountryTimeZones.TimeZoneMapping timeZoneMapping : timeZoneMappings) {
            zoneIds.add(timeZoneMapping.timeZoneId);
        }
        return Collections.unmodifiableList(zoneIds);
    }

    static String normalizeCountryIso(String countryIso) {
        return countryIso.toLowerCase(Locale.US);
    }
}
