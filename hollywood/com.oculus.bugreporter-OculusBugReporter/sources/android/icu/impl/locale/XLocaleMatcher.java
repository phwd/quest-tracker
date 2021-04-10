package android.icu.impl.locale;

import android.icu.impl.locale.XCldrStub;
import android.icu.impl.locale.XLikelySubtags;
import android.icu.impl.locale.XLocaleDistance;
import android.icu.util.LocalePriorityList;
import android.icu.util.Output;
import android.icu.util.ULocale;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import sun.util.locale.LanguageTag;

public class XLocaleMatcher {
    private static final boolean TRACE_MATCHER = false;
    private static final XLikelySubtags.LSR UND = new XLikelySubtags.LSR(LanguageTag.UNDETERMINED, "", "");
    private static final ULocale UND_LOCALE = new ULocale(LanguageTag.UNDETERMINED);
    private final ULocale defaultLanguage;
    private final int demotionPerAdditionalDesiredLocale;
    private final XLocaleDistance.DistanceOption distanceOption;
    private final Set<ULocale> exactSupportedLocales;
    private final XLocaleDistance localeDistance;
    private final Map<XLikelySubtags.LSR, Set<ULocale>> supportedLanguages;
    private final int thresholdDistance;

    public static class Builder {
        private ULocale defaultLanguage;
        private int demotionPerAdditionalDesiredLocale = -1;
        private XLocaleDistance.DistanceOption distanceOption;
        private XLocaleDistance localeDistance;
        private Set<ULocale> supportedLanguagesList;
        private int thresholdDistance = -1;

        public Builder setSupportedLocales(String languagePriorityList) {
            this.supportedLanguagesList = XLocaleMatcher.asSet(LocalePriorityList.add(languagePriorityList).build());
            return this;
        }

        public Builder setSupportedLocales(LocalePriorityList languagePriorityList) {
            this.supportedLanguagesList = XLocaleMatcher.asSet(languagePriorityList);
            return this;
        }

        public Builder setSupportedLocales(Set<ULocale> languagePriorityList) {
            Set<ULocale> temp = new LinkedHashSet<>();
            temp.addAll(languagePriorityList);
            this.supportedLanguagesList = temp;
            return this;
        }

        public Builder setThresholdDistance(int thresholdDistance2) {
            this.thresholdDistance = thresholdDistance2;
            return this;
        }

        public Builder setDemotionPerAdditionalDesiredLocale(int demotionPerAdditionalDesiredLocale2) {
            this.demotionPerAdditionalDesiredLocale = demotionPerAdditionalDesiredLocale2;
            return this;
        }

        public Builder setLocaleDistance(XLocaleDistance localeDistance2) {
            this.localeDistance = localeDistance2;
            return this;
        }

        public Builder setDefaultLanguage(ULocale defaultLanguage2) {
            this.defaultLanguage = defaultLanguage2;
            return this;
        }

        public Builder setDistanceOption(XLocaleDistance.DistanceOption distanceOption2) {
            this.distanceOption = distanceOption2;
            return this;
        }

        public XLocaleMatcher build() {
            return new XLocaleMatcher(this);
        }

        public String toString() {
            StringBuilder s = new StringBuilder().append("{XLocaleMatcher.Builder");
            if (!this.supportedLanguagesList.isEmpty()) {
                s.append(" supported={");
                s.append(this.supportedLanguagesList.toString());
                s.append("}");
            }
            if (this.defaultLanguage != null) {
                s.append(" default=");
                s.append(this.defaultLanguage.toString());
            }
            int i = this.thresholdDistance;
            if (i >= 0) {
                s.append(String.format(" thresholdDistance=%d", Integer.valueOf(i)));
            }
            s.append(" preference=");
            s.append(this.distanceOption.name());
            s.append("}");
            return s.toString();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public XLocaleMatcher(String supportedLocales) {
        this(builder().setSupportedLocales(supportedLocales));
    }

    public XLocaleMatcher(LocalePriorityList supportedLocales) {
        this(builder().setSupportedLocales(supportedLocales));
    }

    public XLocaleMatcher(Set<ULocale> supportedLocales) {
        this(builder().setSupportedLocales(supportedLocales));
    }

    private XLocaleMatcher(Builder builder) {
        XLocaleDistance xLocaleDistance;
        int i;
        ULocale uLocale;
        int i2;
        if (builder.localeDistance == null) {
            xLocaleDistance = XLocaleDistance.getDefault();
        } else {
            xLocaleDistance = builder.localeDistance;
        }
        this.localeDistance = xLocaleDistance;
        if (builder.thresholdDistance < 0) {
            i = this.localeDistance.getDefaultScriptDistance();
        } else {
            i = builder.thresholdDistance;
        }
        this.thresholdDistance = i;
        XCldrStub.Multimap<XLikelySubtags.LSR, ULocale> temp2 = extractLsrMap(builder.supportedLanguagesList, extractLsrSet(this.localeDistance.getParadigms()));
        this.supportedLanguages = temp2.asMap();
        this.exactSupportedLocales = XCldrStub.ImmutableSet.copyOf(temp2.values());
        if (builder.defaultLanguage != null) {
            uLocale = builder.defaultLanguage;
        } else if (this.supportedLanguages.isEmpty()) {
            uLocale = null;
        } else {
            uLocale = this.supportedLanguages.entrySet().iterator().next().getValue().iterator().next();
        }
        this.defaultLanguage = uLocale;
        if (builder.demotionPerAdditionalDesiredLocale < 0) {
            i2 = this.localeDistance.getDefaultRegionDistance() + 1;
        } else {
            i2 = builder.demotionPerAdditionalDesiredLocale;
        }
        this.demotionPerAdditionalDesiredLocale = i2;
        this.distanceOption = builder.distanceOption;
    }

    private Set<XLikelySubtags.LSR> extractLsrSet(Set<ULocale> languagePriorityList) {
        Set<XLikelySubtags.LSR> result = new LinkedHashSet<>();
        for (ULocale item : languagePriorityList) {
            result.add(item.equals(UND_LOCALE) ? UND : XLikelySubtags.LSR.fromMaximalized(item));
        }
        return result;
    }

    private XCldrStub.Multimap<XLikelySubtags.LSR, ULocale> extractLsrMap(Set<ULocale> languagePriorityList, Set<XLikelySubtags.LSR> priorities) {
        XLikelySubtags.LSR max;
        XCldrStub.Multimap<XLikelySubtags.LSR, ULocale> builder = XCldrStub.LinkedHashMultimap.create();
        for (ULocale item : languagePriorityList) {
            if (item.equals(UND_LOCALE)) {
                max = UND;
            } else {
                max = XLikelySubtags.LSR.fromMaximalized(item);
            }
            builder.put(max, item);
        }
        if (builder.size() > 1 && priorities != null) {
            XCldrStub.Multimap<XLikelySubtags.LSR, ULocale> builder2 = XCldrStub.LinkedHashMultimap.create();
            boolean first = true;
            for (Map.Entry<XLikelySubtags.LSR, Set<ULocale>> entry : builder.asMap().entrySet()) {
                XLikelySubtags.LSR key = entry.getKey();
                if (first || priorities.contains(key)) {
                    builder2.putAll(key, entry.getValue());
                    first = false;
                }
            }
            builder2.putAll(builder);
            if (builder2.equals(builder)) {
                builder = builder2;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return XCldrStub.ImmutableMultimap.copyOf(builder);
    }

    public ULocale getBestMatch(ULocale ulocale) {
        return getBestMatch(ulocale, (Output<ULocale>) null);
    }

    public ULocale getBestMatch(String languageList) {
        return getBestMatch(LocalePriorityList.add(languageList).build(), (Output<ULocale>) null);
    }

    public ULocale getBestMatch(ULocale... locales) {
        return getBestMatch(new LinkedHashSet(Arrays.asList(locales)), (Output<ULocale>) null);
    }

    public ULocale getBestMatch(Set<ULocale> desiredLanguages) {
        return getBestMatch(desiredLanguages, (Output<ULocale>) null);
    }

    public ULocale getBestMatch(LocalePriorityList desiredLanguages) {
        return getBestMatch(desiredLanguages, (Output<ULocale>) null);
    }

    public ULocale getBestMatch(LocalePriorityList desiredLanguages, Output<ULocale> outputBestDesired) {
        return getBestMatch(asSet(desiredLanguages), outputBestDesired);
    }

    /* access modifiers changed from: private */
    public static Set<ULocale> asSet(LocalePriorityList languageList) {
        Set<ULocale> temp = new LinkedHashSet<>();
        Iterator<ULocale> it = languageList.iterator();
        while (it.hasNext()) {
            temp.add(it.next());
        }
        return temp;
    }

    public ULocale getBestMatch(Set<ULocale> desiredLanguages, Output<ULocale> outputBestDesired) {
        if (desiredLanguages.size() == 1) {
            return getBestMatch(desiredLanguages.iterator().next(), outputBestDesired);
        }
        XCldrStub.Multimap<XLikelySubtags.LSR, ULocale> desiredLSRs = extractLsrMap(desiredLanguages, null);
        int bestDistance = Integer.MAX_VALUE;
        T t = null;
        Collection<ULocale> bestSupportedLocales = null;
        int delta = 0;
        Iterator<Map.Entry<XLikelySubtags.LSR, Set<ULocale>>> it = desiredLSRs.asMap().entrySet().iterator();
        loop0:
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<XLikelySubtags.LSR, Set<ULocale>> desiredLsrAndLocales = it.next();
            XLikelySubtags.LSR desiredLSR = desiredLsrAndLocales.getKey();
            Iterator<ULocale> it2 = desiredLsrAndLocales.getValue().iterator();
            while (it2.hasNext()) {
                T t2 = (T) it2.next();
                if (delta < bestDistance) {
                    if (this.exactSupportedLocales.contains(t2)) {
                        if (outputBestDesired != null) {
                            outputBestDesired.value = t2;
                        }
                        return t2;
                    }
                    Collection<ULocale> found = this.supportedLanguages.get(desiredLSR);
                    if (found != null) {
                        if (outputBestDesired != null) {
                            outputBestDesired.value = t2;
                        }
                        return found.iterator().next();
                    }
                }
                for (Map.Entry<XLikelySubtags.LSR, Set<ULocale>> supportedLsrAndLocale : this.supportedLanguages.entrySet()) {
                    int distance = this.localeDistance.distanceRaw(desiredLSR, supportedLsrAndLocale.getKey(), this.thresholdDistance, this.distanceOption) + delta;
                    if (distance < bestDistance) {
                        bestDistance = distance;
                        t = t2;
                        bestSupportedLocales = supportedLsrAndLocale.getValue();
                        if (distance == 0) {
                            break loop0;
                        }
                    } else {
                        t = t;
                    }
                    desiredLSRs = desiredLSRs;
                }
                delta += this.demotionPerAdditionalDesiredLocale;
            }
        }
        if (bestDistance >= this.thresholdDistance) {
            if (outputBestDesired != null) {
                outputBestDesired.value = null;
            }
            return this.defaultLanguage;
        }
        if (outputBestDesired != null) {
            outputBestDesired.value = t;
        }
        if (bestSupportedLocales.contains(t)) {
            return t;
        }
        return bestSupportedLocales.iterator().next();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: android.icu.util.ULocale */
    /* JADX WARN: Multi-variable type inference failed */
    public ULocale getBestMatch(ULocale desiredLocale, Output<ULocale> outputBestDesired) {
        Collection<ULocale> found;
        int bestDistance = Integer.MAX_VALUE;
        T t = null;
        Collection<ULocale> bestSupportedLocales = null;
        XLikelySubtags.LSR desiredLSR = desiredLocale.equals(UND_LOCALE) ? UND : XLikelySubtags.LSR.fromMaximalized(desiredLocale);
        if (this.exactSupportedLocales.contains(desiredLocale)) {
            if (outputBestDesired != null) {
                outputBestDesired.value = desiredLocale;
            }
            return desiredLocale;
        } else if (this.distanceOption != XLocaleDistance.DistanceOption.REGION_FIRST || (found = this.supportedLanguages.get(desiredLSR)) == null) {
            for (Map.Entry<XLikelySubtags.LSR, Set<ULocale>> supportedLsrAndLocale : this.supportedLanguages.entrySet()) {
                int distance = this.localeDistance.distanceRaw(desiredLSR, supportedLsrAndLocale.getKey(), this.thresholdDistance, this.distanceOption);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    t = desiredLocale;
                    bestSupportedLocales = supportedLsrAndLocale.getValue();
                    if (distance == 0) {
                        break;
                    }
                }
            }
            if (bestDistance >= this.thresholdDistance) {
                if (outputBestDesired != null) {
                    outputBestDesired.value = null;
                }
                return this.defaultLanguage;
            }
            if (outputBestDesired != null) {
                outputBestDesired.value = t;
            }
            if (bestSupportedLocales.contains(t)) {
                return t;
            }
            return bestSupportedLocales.iterator().next();
        } else {
            if (outputBestDesired != null) {
                outputBestDesired.value = desiredLocale;
            }
            return found.iterator().next();
        }
    }

    public static ULocale combine(ULocale bestSupported, ULocale bestDesired) {
        if (bestSupported.equals(bestDesired) || bestDesired == null) {
            return bestSupported;
        }
        ULocale.Builder b = new ULocale.Builder().setLocale(bestSupported);
        String region = bestDesired.getCountry();
        if (!region.isEmpty()) {
            b.setRegion(region);
        }
        String variants = bestDesired.getVariant();
        if (!variants.isEmpty()) {
            b.setVariant(variants);
        }
        for (Character ch : bestDesired.getExtensionKeys()) {
            char extensionKey = ch.charValue();
            b.setExtension(extensionKey, bestDesired.getExtension(extensionKey));
        }
        return b.build();
    }

    public int distance(ULocale desired, ULocale supported) {
        return this.localeDistance.distanceRaw(XLikelySubtags.LSR.fromMaximalized(desired), XLikelySubtags.LSR.fromMaximalized(supported), this.thresholdDistance, this.distanceOption);
    }

    public int distance(String desiredLanguage, String supportedLanguage) {
        return this.localeDistance.distanceRaw(XLikelySubtags.LSR.fromMaximalized(new ULocale(desiredLanguage)), XLikelySubtags.LSR.fromMaximalized(new ULocale(supportedLanguage)), this.thresholdDistance, this.distanceOption);
    }

    public String toString() {
        return this.exactSupportedLocales.toString();
    }

    public double match(ULocale desired, ULocale supported) {
        return ((double) (100 - distance(desired, supported))) / 100.0d;
    }

    @Deprecated
    public double match(ULocale desired, ULocale desiredMax, ULocale supported, ULocale supportedMax) {
        return match(desired, supported);
    }

    public ULocale canonicalize(ULocale ulocale) {
        return null;
    }

    public int getThresholdDistance() {
        return this.thresholdDistance;
    }
}
