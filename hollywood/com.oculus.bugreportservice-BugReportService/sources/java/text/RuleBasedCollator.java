package java.text;

public class RuleBasedCollator extends Collator {
    RuleBasedCollator(android.icu.text.RuleBasedCollator ruleBasedCollator) {
        super(ruleBasedCollator);
    }

    @Override // java.text.Collator
    public synchronized int compare(String str, String str2) {
        if (str == null || str2 == null) {
            throw new NullPointerException();
        }
        return this.icuColl.compare(str, str2);
    }

    @Override // java.text.Collator
    public Object clone() {
        return super.clone();
    }

    @Override // java.util.Comparator, java.text.Collator
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.icuColl.hashCode();
    }
}
