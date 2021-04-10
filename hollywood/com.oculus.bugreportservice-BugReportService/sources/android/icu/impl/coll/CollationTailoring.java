package android.icu.impl.coll;

import android.icu.impl.Norm2AllModes;
import android.icu.impl.Trie2_32;
import android.icu.impl.coll.SharedObject;
import android.icu.text.UnicodeSet;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.Map;

public final class CollationTailoring {
    public ULocale actualLocale = ULocale.ROOT;
    public CollationData data;
    public Map maxExpansions;
    CollationData ownedData;
    private String rules;
    private UResourceBundle rulesResource;
    public SharedObject.Reference settings;
    Trie2_32 trie;
    UnicodeSet unsafeBackwardSet;
    public int version = 0;

    CollationTailoring(SharedObject.Reference reference) {
        if (reference != null) {
            this.settings = reference.clone();
        } else {
            this.settings = new SharedObject.Reference(new CollationSettings());
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureOwnedData() {
        if (this.ownedData == null) {
            this.ownedData = new CollationData(Norm2AllModes.getNFCInstance().impl);
        }
        this.data = this.ownedData;
    }

    /* access modifiers changed from: package-private */
    public void setRulesResource(UResourceBundle uResourceBundle) {
        this.rulesResource = uResourceBundle;
    }

    public String getRules() {
        String str = this.rules;
        if (str != null) {
            return str;
        }
        UResourceBundle uResourceBundle = this.rulesResource;
        return uResourceBundle != null ? uResourceBundle.getString() : "";
    }

    /* access modifiers changed from: package-private */
    public int getUCAVersion() {
        int i = this.version;
        return ((i >> 14) & 3) | ((i >> 12) & 4080);
    }
}
