package android.icu.util;

import android.icu.impl.ICUCache;
import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SimpleCache;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

@Deprecated
public class GenderInfo {
    private static Cache genderInfoCache = new Cache(null);
    private static GenderInfo neutral = new GenderInfo(ListGenderStyle.NEUTRAL);
    private final ListGenderStyle style;

    @Deprecated
    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    @Deprecated
    public static GenderInfo getInstance(ULocale uLocale) {
        return genderInfoCache.get(uLocale);
    }

    @Deprecated
    public static GenderInfo getInstance(Locale locale) {
        return getInstance(ULocale.forLocale(locale));
    }

    @Deprecated
    public enum ListGenderStyle {
        NEUTRAL,
        MIXED_NEUTRAL,
        MALE_TAINTS;
        
        private static Map<String, ListGenderStyle> fromNameMap = new HashMap(3);

        static {
            fromNameMap.put("neutral", NEUTRAL);
            fromNameMap.put("maleTaints", MALE_TAINTS);
            fromNameMap.put("mixedNeutral", MIXED_NEUTRAL);
        }

        @Deprecated
        public static ListGenderStyle fromName(String name) {
            ListGenderStyle result = fromNameMap.get(name);
            if (result != null) {
                return result;
            }
            throw new IllegalArgumentException("Unknown gender style name: " + name);
        }
    }

    @Deprecated
    public Gender getListGender(Gender... genders) {
        return getListGender(Arrays.asList(genders));
    }

    @Deprecated
    public Gender getListGender(List<Gender> genders) {
        if (genders.size() == 0) {
            return Gender.OTHER;
        }
        if (genders.size() == 1) {
            return genders.get(0);
        }
        int i = AnonymousClass1.$SwitchMap$android$icu$util$GenderInfo$ListGenderStyle[this.style.ordinal()];
        if (i == 1) {
            return Gender.OTHER;
        }
        if (i == 2) {
            boolean hasFemale = false;
            boolean hasMale = false;
            for (Gender gender : genders) {
                int i2 = AnonymousClass1.$SwitchMap$android$icu$util$GenderInfo$Gender[gender.ordinal()];
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            return Gender.OTHER;
                        }
                    } else if (hasFemale) {
                        return Gender.OTHER;
                    } else {
                        hasMale = true;
                    }
                } else if (hasMale) {
                    return Gender.OTHER;
                } else {
                    hasFemale = true;
                }
            }
            return hasMale ? Gender.MALE : Gender.FEMALE;
        } else if (i != 3) {
            return Gender.OTHER;
        } else {
            for (Gender gender2 : genders) {
                if (gender2 != Gender.FEMALE) {
                    return Gender.MALE;
                }
            }
            return Gender.FEMALE;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.GenderInfo$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$GenderInfo$Gender = new int[Gender.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$GenderInfo$ListGenderStyle = new int[ListGenderStyle.values().length];

        static {
            try {
                $SwitchMap$android$icu$util$GenderInfo$ListGenderStyle[ListGenderStyle.NEUTRAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$util$GenderInfo$ListGenderStyle[ListGenderStyle.MIXED_NEUTRAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$util$GenderInfo$ListGenderStyle[ListGenderStyle.MALE_TAINTS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$util$GenderInfo$Gender[Gender.FEMALE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$icu$util$GenderInfo$Gender[Gender.MALE.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$icu$util$GenderInfo$Gender[Gender.OTHER.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    @Deprecated
    public GenderInfo(ListGenderStyle genderStyle) {
        this.style = genderStyle;
    }

    /* access modifiers changed from: private */
    public static class Cache {
        private final ICUCache<ULocale, GenderInfo> cache;

        private Cache() {
            this.cache = new SimpleCache();
        }

        /* synthetic */ Cache(AnonymousClass1 x0) {
            this();
        }

        public GenderInfo get(ULocale locale) {
            GenderInfo result = this.cache.get(locale);
            if (result == null) {
                result = load(locale);
                if (result == null) {
                    ULocale fallback = locale.getFallback();
                    result = fallback == null ? GenderInfo.neutral : get(fallback);
                }
                this.cache.put(locale, result);
            }
            return result;
        }

        private static GenderInfo load(ULocale ulocale) {
            try {
                return new GenderInfo(ListGenderStyle.fromName(UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "genderList", ICUResourceBundle.ICU_DATA_CLASS_LOADER, true).get("genderList").getString(ulocale.toString())));
            } catch (MissingResourceException e) {
                return null;
            }
        }
    }
}
