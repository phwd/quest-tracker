package java.text;

import libcore.icu.CollationKeyICU;

public class RuleBasedCollator extends Collator {
    RuleBasedCollator(android.icu.text.RuleBasedCollator wrapper) {
        super(wrapper);
    }

    public RuleBasedCollator(String rules) throws ParseException {
        if (rules != null) {
            try {
                this.icuColl = new android.icu.text.RuleBasedCollator(rules);
            } catch (Exception e) {
                if (e instanceof ParseException) {
                    throw ((ParseException) e);
                }
                throw new ParseException(e.getMessage(), -1);
            }
        } else {
            throw new NullPointerException("rules == null");
        }
    }

    public String getRules() {
        return collAsICU().getRules();
    }

    public CollationElementIterator getCollationElementIterator(String source) {
        if (source != null) {
            return new CollationElementIterator(collAsICU().getCollationElementIterator(source));
        }
        throw new NullPointerException("source == null");
    }

    public CollationElementIterator getCollationElementIterator(CharacterIterator source) {
        if (source != null) {
            return new CollationElementIterator(collAsICU().getCollationElementIterator(source));
        }
        throw new NullPointerException("source == null");
    }

    @Override // java.text.Collator
    public synchronized int compare(String source, String target) {
        if (source == null || target == null) {
            throw new NullPointerException();
        }
        return this.icuColl.compare(source, target);
    }

    @Override // java.text.Collator
    public synchronized CollationKey getCollationKey(String source) {
        if (source == null) {
            return null;
        }
        return new CollationKeyICU(source, this.icuColl.getCollationKey(source));
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

    @Override // java.text.Collator
    public int hashCode() {
        return this.icuColl.hashCode();
    }

    private android.icu.text.RuleBasedCollator collAsICU() {
        return (android.icu.text.RuleBasedCollator) this.icuColl;
    }
}
