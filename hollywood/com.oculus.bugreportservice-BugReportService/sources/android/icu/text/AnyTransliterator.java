package android.icu.text;

import android.icu.lang.UScript;
import android.icu.text.Transliterator;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class AnyTransliterator extends Transliterator {
    private ConcurrentHashMap cache;
    private String target;
    private int targetScript;
    private Transliterator widthFix = Transliterator.getInstance("[[:dt=Nar:][:dt=Wide:]] nfkd");

    private boolean isWide(int i) {
        return i == 5 || i == 17 || i == 18 || i == 20 || i == 22;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        int i = position.start;
        int i2 = position.limit;
        ScriptRunIterator scriptRunIterator = new ScriptRunIterator(replaceable, position.contextStart, position.contextLimit);
        while (scriptRunIterator.next()) {
            if (scriptRunIterator.limit > i) {
                Transliterator transliterator = getTransliterator(scriptRunIterator.scriptCode);
                if (transliterator == null) {
                    position.start = scriptRunIterator.limit;
                } else {
                    boolean z2 = z && scriptRunIterator.limit >= i2;
                    position.start = Math.max(i, scriptRunIterator.start);
                    position.limit = Math.min(i2, scriptRunIterator.limit);
                    int i3 = position.limit;
                    transliterator.filteredTransliterate(replaceable, position, z2);
                    int i4 = position.limit - i3;
                    i2 += i4;
                    scriptRunIterator.adjustLimit(i4);
                    if (scriptRunIterator.limit >= i2) {
                        break;
                    }
                }
            }
        }
        position.limit = i2;
    }

    private AnyTransliterator(String str, String str2, String str3, int i) {
        super(str, null);
        this.targetScript = i;
        this.cache = new ConcurrentHashMap();
        this.target = str2;
        if (str3.length() > 0) {
            this.target = str2 + '/' + str3;
        }
    }

    public AnyTransliterator(String str, UnicodeFilter unicodeFilter, String str2, int i, Transliterator transliterator, ConcurrentHashMap concurrentHashMap) {
        super(str, unicodeFilter);
        this.targetScript = i;
        this.cache = concurrentHashMap;
        this.target = str2;
    }

    private Transliterator getTransliterator(int i) {
        if (i != this.targetScript && i != -1) {
            Integer valueOf = Integer.valueOf(i);
            Transliterator transliterator = (Transliterator) this.cache.get(valueOf);
            if (transliterator != null) {
                return transliterator;
            }
            String name = UScript.getName(i);
            try {
                transliterator = Transliterator.getInstance(name + '-' + this.target, 0);
            } catch (RuntimeException unused) {
            }
            if (transliterator == null) {
                try {
                    transliterator = Transliterator.getInstance(name + "-Latin;Latin-" + this.target, 0);
                } catch (RuntimeException unused2) {
                }
            }
            if (transliterator != null) {
                if (!isWide(this.targetScript)) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(this.widthFix);
                    arrayList.add(transliterator);
                    transliterator = new CompoundTransliterator(arrayList);
                }
                Transliterator transliterator2 = (Transliterator) this.cache.putIfAbsent(valueOf, transliterator);
                return transliterator2 != null ? transliterator2 : transliterator;
            } else if (!isWide(this.targetScript)) {
                return this.widthFix;
            } else {
                return transliterator;
            }
        } else if (isWide(this.targetScript)) {
            return null;
        } else {
            return this.widthFix;
        }
    }

    static void register() {
        HashMap hashMap = new HashMap();
        Enumeration availableSources = Transliterator.getAvailableSources();
        while (availableSources.hasMoreElements()) {
            String str = (String) availableSources.nextElement();
            if (!str.equalsIgnoreCase("Any")) {
                Enumeration availableTargets = Transliterator.getAvailableTargets(str);
                while (availableTargets.hasMoreElements()) {
                    String str2 = (String) availableTargets.nextElement();
                    int scriptNameToCode = scriptNameToCode(str2);
                    if (scriptNameToCode != -1) {
                        Set set = (Set) hashMap.get(str2);
                        if (set == null) {
                            set = new HashSet();
                            hashMap.put(str2, set);
                        }
                        Enumeration availableVariants = Transliterator.getAvailableVariants(str, str2);
                        while (availableVariants.hasMoreElements()) {
                            String str3 = (String) availableVariants.nextElement();
                            if (!set.contains(str3)) {
                                set.add(str3);
                                Transliterator.registerInstance(new AnyTransliterator(TransliteratorIDParser.STVtoID("Any", str2, str3), str2, str3, scriptNameToCode));
                                Transliterator.registerSpecialInverse(str2, "Null", false);
                            }
                        }
                    }
                }
            }
        }
    }

    private static int scriptNameToCode(String str) {
        try {
            int[] code = UScript.getCode(str);
            if (code != null) {
                return code[0];
            }
            return -1;
        } catch (MissingResourceException unused) {
            return -1;
        }
    }

    private static class ScriptRunIterator {
        public int limit;
        public int scriptCode;
        public int start;
        private Replaceable text;
        private int textLimit;
        private int textStart;

        public ScriptRunIterator(Replaceable replaceable, int i, int i2) {
            this.text = replaceable;
            this.textStart = i;
            this.textLimit = i2;
            this.limit = i;
        }

        public boolean next() {
            int script;
            this.scriptCode = -1;
            this.start = this.limit;
            if (this.start == this.textLimit) {
                return false;
            }
            while (true) {
                int i = this.start;
                if (i > this.textStart && ((script = UScript.getScript(this.text.char32At(i - 1))) == 0 || script == 1)) {
                    this.start--;
                }
            }
            while (true) {
                int i2 = this.limit;
                if (i2 >= this.textLimit) {
                    break;
                }
                int script2 = UScript.getScript(this.text.char32At(i2));
                if (!(script2 == 0 || script2 == 1)) {
                    int i3 = this.scriptCode;
                    if (i3 == -1) {
                        this.scriptCode = script2;
                    } else if (script2 != i3) {
                        break;
                    }
                }
                this.limit++;
            }
            return true;
        }

        public void adjustLimit(int i) {
            this.limit += i;
            this.textLimit += i;
        }
    }

    public Transliterator safeClone() {
        UnicodeFilter filter = getFilter();
        return new AnyTransliterator(getID(), (filter == null || !(filter instanceof UnicodeSet)) ? filter : new UnicodeSet((UnicodeSet) filter), this.target, this.targetScript, this.widthFix, this.cache);
    }
}
