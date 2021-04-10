package android.icu.impl.text;

import android.icu.impl.ICUDebug;
import android.icu.text.CollationElementIterator;
import android.icu.text.Collator;
import android.icu.text.RbnfLenientScanner;
import android.icu.text.RbnfLenientScannerProvider;
import android.icu.text.RuleBasedCollator;
import android.icu.util.ULocale;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class RbnfScannerProviderImpl implements RbnfLenientScannerProvider {
    private static final boolean DEBUG = ICUDebug.enabled("rbnf");
    private Map<String, RbnfLenientScanner> cache = new HashMap();

    @Override // android.icu.text.RbnfLenientScannerProvider
    @Deprecated
    public RbnfLenientScanner get(ULocale locale, String extras) {
        String key = locale.toString() + "/" + extras;
        synchronized (this.cache) {
            RbnfLenientScanner result = this.cache.get(key);
            if (result != null) {
                return result;
            }
            RbnfLenientScanner result2 = createScanner(locale, extras);
            synchronized (this.cache) {
                this.cache.put(key, result2);
            }
            return result2;
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public RbnfLenientScanner createScanner(ULocale locale, String extras) {
        RuleBasedCollator collator;
        try {
            collator = (RuleBasedCollator) Collator.getInstance(locale.toLocale());
            if (extras != null) {
                collator = new RuleBasedCollator(collator.getRules() + extras);
            }
            collator.setDecomposition(17);
        } catch (Exception e) {
            if (DEBUG) {
                e.printStackTrace();
                System.out.println("++++");
            }
            collator = null;
        }
        return new RbnfLenientScannerImpl(collator);
    }

    /* access modifiers changed from: private */
    public static class RbnfLenientScannerImpl implements RbnfLenientScanner {
        private final RuleBasedCollator collator;

        private RbnfLenientScannerImpl(RuleBasedCollator rbc) {
            this.collator = rbc;
        }

        @Override // android.icu.text.RbnfLenientScanner
        public boolean allIgnorable(String s) {
            CollationElementIterator iter = this.collator.getCollationElementIterator(s);
            int o = iter.next();
            while (o != -1 && CollationElementIterator.primaryOrder(o) == 0) {
                o = iter.next();
            }
            return o == -1;
        }

        @Override // android.icu.text.RbnfLenientScanner
        public int[] findText(String str, String key, int startingAt) {
            int keyLen = 0;
            for (int p = startingAt; p < str.length() && keyLen == 0; p++) {
                keyLen = prefixLength(str.substring(p), key);
                if (keyLen != 0) {
                    return new int[]{p, keyLen};
                }
            }
            return new int[]{-1, 0};
        }

        public int[] findText2(String str, String key, int startingAt) {
            CollationElementIterator strIter = this.collator.getCollationElementIterator(str);
            CollationElementIterator keyIter = this.collator.getCollationElementIterator(key);
            int keyStart = -1;
            strIter.setOffset(startingAt);
            int oStr = strIter.next();
            int oKey = keyIter.next();
            while (oKey != -1) {
                while (oStr != -1 && CollationElementIterator.primaryOrder(oStr) == 0) {
                    oStr = strIter.next();
                }
                while (oKey != -1 && CollationElementIterator.primaryOrder(oKey) == 0) {
                    oKey = keyIter.next();
                }
                if (oStr == -1) {
                    return new int[]{-1, 0};
                }
                if (oKey == -1) {
                    break;
                } else if (CollationElementIterator.primaryOrder(oStr) == CollationElementIterator.primaryOrder(oKey)) {
                    keyStart = strIter.getOffset();
                    oStr = strIter.next();
                    oKey = keyIter.next();
                } else if (keyStart != -1) {
                    keyStart = -1;
                    keyIter.reset();
                } else {
                    oStr = strIter.next();
                }
            }
            return new int[]{keyStart, strIter.getOffset() - keyStart};
        }

        @Override // android.icu.text.RbnfLenientScanner
        public int prefixLength(String str, String prefix) {
            CollationElementIterator strIter = this.collator.getCollationElementIterator(str);
            CollationElementIterator prefixIter = this.collator.getCollationElementIterator(prefix);
            int oStr = strIter.next();
            int oPrefix = prefixIter.next();
            while (oPrefix != -1) {
                while (CollationElementIterator.primaryOrder(oStr) == 0 && oStr != -1) {
                    oStr = strIter.next();
                }
                while (CollationElementIterator.primaryOrder(oPrefix) == 0 && oPrefix != -1) {
                    oPrefix = prefixIter.next();
                }
                if (oPrefix == -1) {
                    break;
                } else if (oStr == -1 || CollationElementIterator.primaryOrder(oStr) != CollationElementIterator.primaryOrder(oPrefix)) {
                    return 0;
                } else {
                    oStr = strIter.next();
                    oPrefix = prefixIter.next();
                }
            }
            int result = strIter.getOffset();
            if (oStr != -1) {
                return result - 1;
            }
            return result;
        }
    }
}
