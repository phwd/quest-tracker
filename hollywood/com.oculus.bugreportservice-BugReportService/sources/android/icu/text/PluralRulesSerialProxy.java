package android.icu.text;

import java.io.Serializable;

class PluralRulesSerialProxy implements Serializable {
    private static final long serialVersionUID = 42;
    private final String data;

    PluralRulesSerialProxy(String str) {
        this.data = str;
    }

    private Object readResolve() {
        return PluralRules.createRules(this.data);
    }
}
