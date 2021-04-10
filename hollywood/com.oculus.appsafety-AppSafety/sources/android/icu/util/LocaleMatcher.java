package android.icu.util;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.Relation;
import android.icu.impl.Row;
import android.icu.impl.locale.XLocaleDistance;
import android.icu.impl.locale.XLocaleMatcher;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.util.locale.LanguageTag;

public class LocaleMatcher {
    @Deprecated
    public static final boolean DEBUG = false;
    private static final double DEFAULT_THRESHOLD = 0.5d;
    private static final ULocale UNKNOWN_LOCALE = new ULocale(LanguageTag.UNDETERMINED);
    private static HashMap<String, String> canonicalMap = new HashMap<>();
    private static final LanguageMatcherData defaultWritten = new LanguageMatcherData();
    private final ULocale defaultLanguage;
    Map<String, Set<Row.R3<ULocale, ULocale, Double>>> desiredLanguageToPossibleLocalesToMaxLocaleToData;
    LocalePriorityList languagePriorityList;
    Set<Row.R3<ULocale, ULocale, Double>> localeToMaxLocaleAndWeight;
    LanguageMatcherData matcherData;
    private final double threshold;
    transient ULocale xDefaultLanguage;
    transient boolean xFavorScript;
    transient XLocaleMatcher xLocaleMatcher;

    static {
        canonicalMap.put("iw", "he");
        canonicalMap.put("mo", "ro");
        canonicalMap.put("tl", "fil");
        UResourceBundleIterator iter = ((ICUResourceBundle) getICUSupplementalData().findTopLevel("languageMatching").get("written")).getIterator();
        while (iter.hasNext()) {
            ICUResourceBundle item = (ICUResourceBundle) iter.next();
            defaultWritten.addDistance(item.getString(0), item.getString(1), Integer.parseInt(item.getString(2)), item.getSize() > 3 && "1".equals(item.getString(3)));
        }
        defaultWritten.freeze();
    }

    public LocaleMatcher(LocalePriorityList languagePriorityList2) {
        this(languagePriorityList2, defaultWritten);
    }

    public LocaleMatcher(String languagePriorityListString) {
        this(LocalePriorityList.add(languagePriorityListString).build());
    }

    @Deprecated
    public LocaleMatcher(LocalePriorityList languagePriorityList2, LanguageMatcherData matcherData2) {
        this(languagePriorityList2, matcherData2, DEFAULT_THRESHOLD);
    }

    @Deprecated
    public LocaleMatcher(LocalePriorityList languagePriorityList2, LanguageMatcherData matcherData2, double threshold2) {
        this.localeToMaxLocaleAndWeight = new LinkedHashSet();
        this.desiredLanguageToPossibleLocalesToMaxLocaleToData = new LinkedHashMap();
        ULocale uLocale = null;
        this.xLocaleMatcher = null;
        this.xDefaultLanguage = null;
        this.xFavorScript = false;
        this.matcherData = matcherData2 == null ? defaultWritten : matcherData2.freeze();
        this.languagePriorityList = languagePriorityList2;
        Iterator<ULocale> it = languagePriorityList2.iterator();
        while (it.hasNext()) {
            ULocale language = it.next();
            add(language, languagePriorityList2.getWeight(language));
        }
        processMapping();
        Iterator<ULocale> it2 = languagePriorityList2.iterator();
        this.defaultLanguage = it2.hasNext() ? it2.next() : uLocale;
        this.threshold = threshold2;
    }

    public double match(ULocale desired, ULocale desiredMax, ULocale supported, ULocale supportedMax) {
        return this.matcherData.match(desired, desiredMax, supported, supportedMax);
    }

    public ULocale canonicalize(ULocale ulocale) {
        String lang = ulocale.getLanguage();
        String lang2 = canonicalMap.get(lang);
        String script = ulocale.getScript();
        String script2 = canonicalMap.get(script);
        String region = ulocale.getCountry();
        String region2 = canonicalMap.get(region);
        if (lang2 == null && script2 == null && region2 == null) {
            return ulocale;
        }
        return new ULocale(lang2 == null ? lang : lang2, script2 == null ? script : script2, region2 == null ? region : region2);
    }

    public ULocale getBestMatch(LocalePriorityList languageList) {
        double bestWeight = 0.0d;
        ULocale bestTableMatch = null;
        double penalty = 0.0d;
        OutputDouble matchWeight = new OutputDouble(null);
        Iterator<ULocale> it = languageList.iterator();
        while (it.hasNext()) {
            ULocale language = it.next();
            ULocale matchLocale = getBestMatchInternal(language, matchWeight);
            double weight = (matchWeight.value * languageList.getWeight(language).doubleValue()) - penalty;
            if (weight > bestWeight) {
                bestWeight = weight;
                bestTableMatch = matchLocale;
            }
            penalty += 0.07000001d;
        }
        if (bestWeight < this.threshold) {
            return this.defaultLanguage;
        }
        return bestTableMatch;
    }

    public ULocale getBestMatch(String languageList) {
        return getBestMatch(LocalePriorityList.add(languageList).build());
    }

    public ULocale getBestMatch(ULocale ulocale) {
        return getBestMatchInternal(ulocale, null);
    }

    @Deprecated
    public ULocale getBestMatch(ULocale... ulocales) {
        return getBestMatch(LocalePriorityList.add(ulocales).build());
    }

    public String toString() {
        return "{" + ((Object) this.defaultLanguage) + ", " + ((Object) this.localeToMaxLocaleAndWeight) + "}";
    }

    private ULocale getBestMatchInternal(ULocale languageCode, OutputDouble outputWeight) {
        ULocale languageCode2 = canonicalize(languageCode);
        ULocale maximized = addLikelySubtags(languageCode2);
        double bestWeight = 0.0d;
        ULocale bestTableMatch = null;
        Set<Row.R3<ULocale, ULocale, Double>> searchTable = this.desiredLanguageToPossibleLocalesToMaxLocaleToData.get(maximized.getLanguage());
        if (searchTable != null) {
            for (Row.R3<ULocale, ULocale, Double> tableKeyValue : searchTable) {
                ULocale tableKey = tableKeyValue.get0();
                double weight = tableKeyValue.get2().doubleValue() * match(languageCode2, maximized, tableKey, tableKeyValue.get1());
                if (weight > bestWeight) {
                    bestWeight = weight;
                    bestTableMatch = tableKey;
                    if (weight > 0.999d) {
                        break;
                    }
                }
            }
        }
        if (bestWeight < this.threshold) {
            bestTableMatch = this.defaultLanguage;
        }
        if (outputWeight != null) {
            outputWeight.value = bestWeight;
        }
        return bestTableMatch;
    }

    /* access modifiers changed from: private */
    @Deprecated
    public static class OutputDouble {
        double value;

        private OutputDouble() {
        }

        /* synthetic */ OutputDouble(AnonymousClass1 x0) {
            this();
        }
    }

    private void add(ULocale language, Double weight) {
        ULocale language2 = canonicalize(language);
        Row.R3<ULocale, ULocale, Double> row = Row.of(language2, addLikelySubtags(language2), weight);
        row.freeze();
        this.localeToMaxLocaleAndWeight.add(row);
    }

    private void processMapping() {
        for (Map.Entry<String, Set<String>> desiredToMatchingLanguages : this.matcherData.matchingLanguages().keyValuesSet()) {
            String desired = desiredToMatchingLanguages.getKey();
            Set<String> supported = desiredToMatchingLanguages.getValue();
            for (Row.R3<ULocale, ULocale, Double> localeToMaxAndWeight : this.localeToMaxLocaleAndWeight) {
                if (supported.contains(localeToMaxAndWeight.get0().getLanguage())) {
                    addFiltered(desired, localeToMaxAndWeight);
                }
            }
        }
        for (Row.R3<ULocale, ULocale, Double> localeToMaxAndWeight2 : this.localeToMaxLocaleAndWeight) {
            addFiltered(localeToMaxAndWeight2.get0().getLanguage(), localeToMaxAndWeight2);
        }
    }

    private void addFiltered(String desired, Row.R3<ULocale, ULocale, Double> localeToMaxAndWeight) {
        Set<Row.R3<ULocale, ULocale, Double>> map = this.desiredLanguageToPossibleLocalesToMaxLocaleToData.get(desired);
        if (map == null) {
            Map<String, Set<Row.R3<ULocale, ULocale, Double>>> map2 = this.desiredLanguageToPossibleLocalesToMaxLocaleToData;
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            map = linkedHashSet;
            map2.put(desired, linkedHashSet);
        }
        map.add(localeToMaxAndWeight);
    }

    private ULocale addLikelySubtags(ULocale languageCode) {
        String str;
        if (languageCode.equals(UNKNOWN_LOCALE)) {
            return UNKNOWN_LOCALE;
        }
        ULocale result = ULocale.addLikelySubtags(languageCode);
        if (result != null && !result.equals(languageCode)) {
            return result;
        }
        String language = languageCode.getLanguage();
        String script = languageCode.getScript();
        String region = languageCode.getCountry();
        StringBuilder sb = new StringBuilder();
        if (language.length() == 0) {
            str = LanguageTag.UNDETERMINED;
        } else {
            str = language;
        }
        sb.append(str);
        sb.append("_");
        sb.append(script.length() == 0 ? "Zzzz" : script);
        sb.append("_");
        sb.append(region.length() == 0 ? "ZZ" : region);
        return new ULocale(sb.toString());
    }

    /* access modifiers changed from: private */
    public static class LocalePatternMatcher {
        static Pattern pattern = Pattern.compile("([a-z]{1,8}|\\*)(?:[_-]([A-Z][a-z]{3}|\\*))?(?:[_-]([A-Z]{2}|[0-9]{3}|\\*))?");
        private String lang;
        private Level level;
        private String region;
        private String script;

        public LocalePatternMatcher(String toMatch) {
            Matcher matcher = pattern.matcher(toMatch);
            if (matcher.matches()) {
                this.lang = matcher.group(1);
                this.script = matcher.group(2);
                this.region = matcher.group(3);
                this.level = this.region != null ? Level.region : this.script != null ? Level.script : Level.language;
                if (this.lang.equals("*")) {
                    this.lang = null;
                }
                String str = this.script;
                if (str != null && str.equals("*")) {
                    this.script = null;
                }
                String str2 = this.region;
                if (str2 != null && str2.equals("*")) {
                    this.region = null;
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Bad pattern: " + toMatch);
        }

        /* access modifiers changed from: package-private */
        public boolean matches(ULocale ulocale) {
            String str = this.lang;
            if (str != null && !str.equals(ulocale.getLanguage())) {
                return false;
            }
            String str2 = this.script;
            if (str2 != null && !str2.equals(ulocale.getScript())) {
                return false;
            }
            String str3 = this.region;
            if (str3 == null || str3.equals(ulocale.getCountry())) {
                return true;
            }
            return false;
        }

        public Level getLevel() {
            return this.level;
        }

        public String getLanguage() {
            String str = this.lang;
            return str == null ? "*" : str;
        }

        public String getScript() {
            String str = this.script;
            return str == null ? "*" : str;
        }

        public String getRegion() {
            String str = this.region;
            return str == null ? "*" : str;
        }

        public String toString() {
            String result = getLanguage();
            if (this.level == Level.language) {
                return result;
            }
            String result2 = result + "-" + getScript();
            if (this.level == Level.script) {
                return result2;
            }
            return result2 + "-" + getRegion();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !(obj instanceof LocalePatternMatcher)) {
                return false;
            }
            LocalePatternMatcher other = (LocalePatternMatcher) obj;
            if (!Objects.equals(this.level, other.level) || !Objects.equals(this.lang, other.lang) || !Objects.equals(this.script, other.script) || !Objects.equals(this.region, other.region)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int ordinal = this.level.ordinal();
            String str = this.lang;
            int i = 0;
            int hashCode = ordinal ^ (str == null ? 0 : str.hashCode());
            String str2 = this.script;
            int hashCode2 = hashCode ^ (str2 == null ? 0 : str2.hashCode());
            String str3 = this.region;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return hashCode2 ^ i;
        }
    }

    /* access modifiers changed from: package-private */
    public enum Level {
        language(0.99d),
        script(0.2d),
        region(0.04d);
        
        final double worst;

        private Level(double d) {
            this.worst = d;
        }
    }

    /* access modifiers changed from: private */
    public static class ScoreData implements Freezable<ScoreData> {
        private static final double maxUnequal_changeD_sameS = 0.5d;
        private static final double maxUnequal_changeEqual = 0.75d;
        private volatile boolean frozen = false;
        final Level level;
        LinkedHashSet<Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double>> scores = new LinkedHashSet<>();

        public ScoreData(Level level2) {
            this.level = level2;
        }

        /* access modifiers changed from: package-private */
        public void addDataToScores(String desired, String supported, Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double> data) {
            if (!this.scores.add(data)) {
                throw new ICUException("trying to add duplicate data: " + ((Object) data));
            }
        }

        /* access modifiers changed from: package-private */
        public double getScore(ULocale dMax, String desiredRaw, String desiredMax, ULocale sMax, String supportedRaw, String supportedMax) {
            if (!desiredMax.equals(supportedMax)) {
                return getRawScore(dMax, sMax);
            }
            if (!desiredRaw.equals(supportedRaw)) {
                return 0.0d + 0.001d;
            }
            return 0.0d;
        }

        private double getRawScore(ULocale desiredLocale, ULocale supportedLocale) {
            Iterator<Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double>> it = this.scores.iterator();
            while (it.hasNext()) {
                Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double> datum = it.next();
                if (datum.get0().matches(desiredLocale) && datum.get1().matches(supportedLocale)) {
                    return datum.get2().doubleValue();
                }
            }
            return this.level.worst;
        }

        public String toString() {
            StringBuilder result = new StringBuilder().append((Object) this.level);
            Iterator<Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double>> it = this.scores.iterator();
            while (it.hasNext()) {
                result.append("\n\t\t");
                result.append((Object) it.next());
            }
            return result.toString();
        }

        @Override // android.icu.util.Freezable
        public ScoreData cloneAsThawed() {
            try {
                ScoreData result = (ScoreData) clone();
                result.scores = (LinkedHashSet) result.scores.clone();
                result.frozen = false;
                return result;
            } catch (CloneNotSupportedException e) {
                throw new ICUCloneNotSupportedException(e);
            }
        }

        @Override // android.icu.util.Freezable
        public ScoreData freeze() {
            return this;
        }

        @Override // android.icu.util.Freezable
        public boolean isFrozen() {
            return this.frozen;
        }

        public Relation<String, String> getMatchingLanguages() {
            Relation<String, String> desiredToSupported = Relation.of(new LinkedHashMap(), HashSet.class);
            Iterator<Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double>> it = this.scores.iterator();
            while (it.hasNext()) {
                Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double> item = it.next();
                LocalePatternMatcher desired = item.get0();
                LocalePatternMatcher supported = item.get1();
                if (!(desired.lang == null || supported.lang == null)) {
                    desiredToSupported.put(desired.lang, supported.lang);
                }
            }
            desiredToSupported.freeze();
            return desiredToSupported;
        }
    }

    @Deprecated
    public static class LanguageMatcherData implements Freezable<LanguageMatcherData> {
        private volatile boolean frozen = false;
        private ScoreData languageScores = new ScoreData(Level.language);
        private Relation<String, String> matchingLanguages;
        private ScoreData regionScores = new ScoreData(Level.region);
        private ScoreData scriptScores = new ScoreData(Level.script);

        @Deprecated
        public Relation<String, String> matchingLanguages() {
            return this.matchingLanguages;
        }

        @Deprecated
        public String toString() {
            return ((Object) this.languageScores) + "\n\t" + ((Object) this.scriptScores) + "\n\t" + ((Object) this.regionScores);
        }

        @Deprecated
        public double match(ULocale a, ULocale aMax, ULocale b, ULocale bMax) {
            double diff = 0.0d + this.languageScores.getScore(aMax, a.getLanguage(), aMax.getLanguage(), bMax, b.getLanguage(), bMax.getLanguage());
            if (diff > 0.999d) {
                return 0.0d;
            }
            double diff2 = diff + this.scriptScores.getScore(aMax, a.getScript(), aMax.getScript(), bMax, b.getScript(), bMax.getScript()) + this.regionScores.getScore(aMax, a.getCountry(), aMax.getCountry(), bMax, b.getCountry(), bMax.getCountry());
            if (!a.getVariant().equals(b.getVariant())) {
                diff2 += 0.01d;
            }
            if (diff2 < 0.0d) {
                diff2 = 0.0d;
            } else if (diff2 > 1.0d) {
                diff2 = 1.0d;
            }
            return 1.0d - diff2;
        }

        @Deprecated
        public LanguageMatcherData addDistance(String desired, String supported, int percent, String comment) {
            return addDistance(desired, supported, percent, false, comment);
        }

        @Deprecated
        public LanguageMatcherData addDistance(String desired, String supported, int percent, boolean oneway) {
            return addDistance(desired, supported, percent, oneway, null);
        }

        private LanguageMatcherData addDistance(String desired, String supported, int percent, boolean oneway, String comment) {
            double score = 1.0d - (((double) percent) / 100.0d);
            LocalePatternMatcher desiredMatcher = new LocalePatternMatcher(desired);
            Level desiredLen = desiredMatcher.getLevel();
            LocalePatternMatcher supportedMatcher = new LocalePatternMatcher(supported);
            if (desiredLen == supportedMatcher.getLevel()) {
                Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double> data = Row.of(desiredMatcher, supportedMatcher, Double.valueOf(score));
                Row.R3<LocalePatternMatcher, LocalePatternMatcher, Double> data2 = oneway ? null : Row.of(supportedMatcher, desiredMatcher, Double.valueOf(score));
                boolean desiredEqualsSupported = desiredMatcher.equals(supportedMatcher);
                int i = AnonymousClass1.$SwitchMap$android$icu$util$LocaleMatcher$Level[desiredLen.ordinal()];
                if (i == 1) {
                    String dlanguage = desiredMatcher.getLanguage();
                    String slanguage = supportedMatcher.getLanguage();
                    this.languageScores.addDataToScores(dlanguage, slanguage, data);
                    if (!oneway && !desiredEqualsSupported) {
                        this.languageScores.addDataToScores(slanguage, dlanguage, data2);
                    }
                } else if (i == 2) {
                    String dscript = desiredMatcher.getScript();
                    String sscript = supportedMatcher.getScript();
                    this.scriptScores.addDataToScores(dscript, sscript, data);
                    if (!oneway && !desiredEqualsSupported) {
                        this.scriptScores.addDataToScores(sscript, dscript, data2);
                    }
                } else if (i == 3) {
                    String dregion = desiredMatcher.getRegion();
                    String sregion = supportedMatcher.getRegion();
                    this.regionScores.addDataToScores(dregion, sregion, data);
                    if (!oneway && !desiredEqualsSupported) {
                        this.regionScores.addDataToScores(sregion, dregion, data2);
                    }
                }
                return this;
            }
            throw new IllegalArgumentException("Lengths unequal: " + desired + ", " + supported);
        }

        @Override // android.icu.util.Freezable
        @Deprecated
        public LanguageMatcherData cloneAsThawed() {
            try {
                LanguageMatcherData result = (LanguageMatcherData) clone();
                result.languageScores = this.languageScores.cloneAsThawed();
                result.scriptScores = this.scriptScores.cloneAsThawed();
                result.regionScores = this.regionScores.cloneAsThawed();
                result.frozen = false;
                return result;
            } catch (CloneNotSupportedException e) {
                throw new ICUCloneNotSupportedException(e);
            }
        }

        @Override // android.icu.util.Freezable
        @Deprecated
        public LanguageMatcherData freeze() {
            this.languageScores.freeze();
            this.regionScores.freeze();
            this.scriptScores.freeze();
            this.matchingLanguages = this.languageScores.getMatchingLanguages();
            this.frozen = true;
            return this;
        }

        @Override // android.icu.util.Freezable
        @Deprecated
        public boolean isFrozen() {
            return this.frozen;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.LocaleMatcher$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$LocaleMatcher$Level = new int[Level.values().length];

        static {
            try {
                $SwitchMap$android$icu$util$LocaleMatcher$Level[Level.language.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$util$LocaleMatcher$Level[Level.script.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$util$LocaleMatcher$Level[Level.region.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @Deprecated
    public static ICUResourceBundle getICUSupplementalData() {
        return (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
    }

    @Deprecated
    public static double match(ULocale a, ULocale b) {
        LocaleMatcher matcher = new LocaleMatcher("");
        return matcher.match(a, matcher.addLikelySubtags(a), b, matcher.addLikelySubtags(b));
    }

    @Deprecated
    public int distance(ULocale desired, ULocale supported) {
        return getLocaleMatcher().distance(desired, supported);
    }

    private synchronized XLocaleMatcher getLocaleMatcher() {
        if (this.xLocaleMatcher == null) {
            XLocaleMatcher.Builder builder = XLocaleMatcher.builder();
            builder.setSupportedLocales(this.languagePriorityList);
            if (this.xDefaultLanguage != null) {
                builder.setDefaultLanguage(this.xDefaultLanguage);
            }
            if (this.xFavorScript) {
                builder.setDistanceOption(XLocaleDistance.DistanceOption.SCRIPT_FIRST);
            }
            this.xLocaleMatcher = builder.build();
        }
        return this.xLocaleMatcher;
    }

    @Deprecated
    public ULocale getBestMatch(LinkedHashSet<ULocale> desiredLanguages, Output<ULocale> outputBestDesired) {
        return getLocaleMatcher().getBestMatch(desiredLanguages, outputBestDesired);
    }

    @Deprecated
    public synchronized LocaleMatcher setDefaultLanguage(ULocale defaultLanguage2) {
        this.xDefaultLanguage = defaultLanguage2;
        this.xLocaleMatcher = null;
        return this;
    }

    @Deprecated
    public synchronized LocaleMatcher setFavorScript(boolean favorScript) {
        this.xFavorScript = favorScript;
        this.xLocaleMatcher = null;
        return this;
    }
}
