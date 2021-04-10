package java.text;

import android.icu.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Locale;

public abstract class Collator implements Comparator, Cloneable {
    android.icu.text.Collator icuColl;

    public abstract int compare(String str, String str2);

    public static synchronized Collator getInstance() {
        Collator instance;
        synchronized (Collator.class) {
            instance = getInstance(Locale.getDefault());
        }
        return instance;
    }

    public static synchronized Collator getInstance(Locale locale) {
        RuleBasedCollator ruleBasedCollator;
        synchronized (Collator.class) {
            if (locale != null) {
                ruleBasedCollator = new RuleBasedCollator((RuleBasedCollator) android.icu.text.Collator.getInstance(locale));
            } else {
                throw new NullPointerException("locale == null");
            }
        }
        return ruleBasedCollator;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return compare((String) obj, (String) obj2);
    }

    public Object clone() {
        try {
            Collator collator = (Collator) super.clone();
            collator.icuColl = (android.icu.text.Collator) this.icuColl.clone();
            return collator;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Collator collator = (Collator) obj;
        android.icu.text.Collator collator2 = this.icuColl;
        if (collator2 == null) {
            return collator.icuColl == null;
        }
        return collator2.equals(collator.icuColl);
    }

    protected Collator() {
        this.icuColl = android.icu.text.Collator.getInstance(Locale.getDefault());
    }

    Collator(android.icu.text.Collator collator) {
        this.icuColl = collator;
    }
}
