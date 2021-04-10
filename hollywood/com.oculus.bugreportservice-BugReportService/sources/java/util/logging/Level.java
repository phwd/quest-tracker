package java.util.logging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Level implements Serializable {
    public static final Level ALL = new Level("ALL", Integer.MIN_VALUE, "sun.util.logging.resources.logging");
    public static final Level CONFIG = new Level("CONFIG", 700, "sun.util.logging.resources.logging");
    public static final Level FINE = new Level("FINE", 500, "sun.util.logging.resources.logging");
    public static final Level FINER = new Level("FINER", 400, "sun.util.logging.resources.logging");
    public static final Level FINEST = new Level("FINEST", 300, "sun.util.logging.resources.logging");
    public static final Level INFO = new Level("INFO", 800, "sun.util.logging.resources.logging");
    public static final Level OFF = new Level("OFF", Integer.MAX_VALUE, "sun.util.logging.resources.logging");
    public static final Level SEVERE = new Level("SEVERE", 1000, "sun.util.logging.resources.logging");
    public static final Level WARNING = new Level("WARNING", 900, "sun.util.logging.resources.logging");
    private static final long serialVersionUID = -8176160795706313070L;
    private transient Locale cachedLocale;
    private transient String localizedLevelName;
    private final String name;
    private final String resourceBundleName;
    private final int value;

    protected Level(String str, int i) {
        this(str, i, null);
    }

    protected Level(String str, int i, String str2) {
        this(str, i, str2, true);
    }

    private Level(String str, int i, String str2, boolean z) {
        if (str != null) {
            this.name = str;
            this.value = i;
            this.resourceBundleName = str2;
            this.localizedLevelName = str2 != null ? null : str;
            this.cachedLocale = null;
            if (z) {
                KnownLevel.add(this);
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    private String computeLocalizedLevelName(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(this.resourceBundleName, locale, Thread.currentThread().getContextClassLoader());
        String string = bundle.getString(this.name);
        if (!"sun.util.logging.resources.logging".equals(this.resourceBundleName)) {
            return string;
        }
        Locale locale2 = bundle.getLocale();
        if (Locale.ROOT.equals(locale2) || this.name.equals(string.toUpperCase(Locale.ROOT))) {
            locale2 = Locale.ROOT;
        }
        return Locale.ROOT.equals(locale2) ? this.name : string.toUpperCase(locale2);
    }

    /* access modifiers changed from: package-private */
    public final String getCachedLocalizedLevelName() {
        Locale locale;
        if (this.localizedLevelName != null && (locale = this.cachedLocale) != null && locale.equals(Locale.getDefault())) {
            return this.localizedLevelName;
        }
        if (this.resourceBundleName == null) {
            return this.name;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final synchronized String getLocalizedLevelName() {
        String cachedLocalizedLevelName = getCachedLocalizedLevelName();
        if (cachedLocalizedLevelName != null) {
            return cachedLocalizedLevelName;
        }
        Locale locale = Locale.getDefault();
        try {
            this.localizedLevelName = computeLocalizedLevelName(locale);
        } catch (Exception unused) {
            this.localizedLevelName = this.name;
        }
        this.cachedLocale = locale;
        return this.localizedLevelName;
    }

    static Level findLevel(String str) {
        if (str != null) {
            KnownLevel findByName = KnownLevel.findByName(str);
            if (findByName != null) {
                return findByName.mirroredLevel;
            }
            try {
                int parseInt = Integer.parseInt(str);
                KnownLevel findByValue = KnownLevel.findByValue(parseInt);
                if (findByValue == null) {
                    new Level(str, parseInt);
                    findByValue = KnownLevel.findByValue(parseInt);
                }
                return findByValue.mirroredLevel;
            } catch (NumberFormatException unused) {
                KnownLevel findByLocalizedLevelName = KnownLevel.findByLocalizedLevelName(str);
                if (findByLocalizedLevelName != null) {
                    return findByLocalizedLevelName.mirroredLevel;
                }
                return null;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public final String toString() {
        return this.name;
    }

    public final int intValue() {
        return this.value;
    }

    private Object readResolve() {
        KnownLevel matches = KnownLevel.matches(this);
        if (matches != null) {
            return matches.levelObject;
        }
        return new Level(this.name, this.value, this.resourceBundleName);
    }

    public boolean equals(Object obj) {
        try {
            if (((Level) obj).value == this.value) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public int hashCode() {
        return this.value;
    }

    static final class KnownLevel {
        private static Map intToLevels = new HashMap();
        private static Map nameToLevels = new HashMap();
        final Level levelObject;
        final Level mirroredLevel;

        KnownLevel(Level level) {
            this.levelObject = level;
            if (level.getClass() == Level.class) {
                this.mirroredLevel = level;
            } else {
                this.mirroredLevel = new Level(level.name, level.value, level.resourceBundleName, false);
            }
        }

        static synchronized void add(Level level) {
            synchronized (KnownLevel.class) {
                KnownLevel knownLevel = new KnownLevel(level);
                List list = (List) nameToLevels.get(level.name);
                if (list == null) {
                    list = new ArrayList();
                    nameToLevels.put(level.name, list);
                }
                list.add(knownLevel);
                List list2 = (List) intToLevels.get(Integer.valueOf(level.value));
                if (list2 == null) {
                    list2 = new ArrayList();
                    intToLevels.put(Integer.valueOf(level.value), list2);
                }
                list2.add(knownLevel);
            }
        }

        static synchronized KnownLevel findByName(String str) {
            synchronized (KnownLevel.class) {
                List list = (List) nameToLevels.get(str);
                if (list == null) {
                    return null;
                }
                return (KnownLevel) list.get(0);
            }
        }

        static synchronized KnownLevel findByValue(int i) {
            synchronized (KnownLevel.class) {
                List list = (List) intToLevels.get(Integer.valueOf(i));
                if (list == null) {
                    return null;
                }
                return (KnownLevel) list.get(0);
            }
        }

        static synchronized KnownLevel findByLocalizedLevelName(String str) {
            synchronized (KnownLevel.class) {
                for (List list : nameToLevels.values()) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            KnownLevel knownLevel = (KnownLevel) it.next();
                            if (str.equals(knownLevel.levelObject.getLocalizedLevelName())) {
                                return knownLevel;
                            }
                        }
                    }
                }
                return null;
            }
        }

        static synchronized KnownLevel matches(Level level) {
            synchronized (KnownLevel.class) {
                List<KnownLevel> list = (List) nameToLevels.get(level.name);
                if (list != null) {
                    for (KnownLevel knownLevel : list) {
                        Level level2 = knownLevel.mirroredLevel;
                        Class cls = knownLevel.levelObject.getClass();
                        if (level.value == level2.value && ((level.resourceBundleName == level2.resourceBundleName || (level.resourceBundleName != null && level.resourceBundleName.equals(level2.resourceBundleName))) && cls == level.getClass())) {
                            return knownLevel;
                        }
                    }
                }
                return null;
            }
        }
    }
}
