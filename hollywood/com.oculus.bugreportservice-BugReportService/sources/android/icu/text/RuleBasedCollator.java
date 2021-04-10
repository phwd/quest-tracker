package android.icu.text;

import android.icu.impl.ClassLoaderUtil;
import android.icu.impl.Normalizer2Impl;
import android.icu.impl.coll.CollationCompare;
import android.icu.impl.coll.CollationData;
import android.icu.impl.coll.CollationFastLatin;
import android.icu.impl.coll.CollationRoot;
import android.icu.impl.coll.CollationSettings;
import android.icu.impl.coll.CollationTailoring;
import android.icu.impl.coll.FCDUTF16CollationIterator;
import android.icu.impl.coll.SharedObject;
import android.icu.impl.coll.TailoredSet;
import android.icu.impl.coll.UTF16CollationIterator;
import android.icu.util.ULocale;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;

public final class RuleBasedCollator extends Collator {
    private boolean actualLocaleIsSameAsValid;
    private CollationBuffer collationBuffer;
    CollationData data;
    private Lock frozenLock;
    SharedObject.Reference settings;
    CollationTailoring tailoring;
    private ULocale validLocale;

    public RuleBasedCollator(String str) {
        if (str != null) {
            this.validLocale = ULocale.ROOT;
            internalBuildTailoring(str);
            return;
        }
        throw new IllegalArgumentException("Collation rules can not be null");
    }

    private final void internalBuildTailoring(String str) {
        CollationTailoring root = CollationRoot.getRoot();
        try {
            Class loadClass = ClassLoaderUtil.getClassLoader(RuleBasedCollator.class).loadClass("android.icu.impl.coll.CollationBuilder");
            Object newInstance = loadClass.getConstructor(CollationTailoring.class).newInstance(root);
            CollationTailoring collationTailoring = (CollationTailoring) loadClass.getMethod("parseAndBuild", String.class).invoke(newInstance, str);
            collationTailoring.actualLocale = null;
            adoptTailoring(collationTailoring);
        } catch (InvocationTargetException e) {
            throw ((Exception) e.getTargetException());
        }
    }

    @Override // android.icu.text.Collator
    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    private final void initMaxExpansions() {
        synchronized (this.tailoring) {
            if (this.tailoring.maxExpansions == null) {
                this.tailoring.maxExpansions = CollationElementIterator.computeMaxExpansions(this.tailoring.data);
            }
        }
    }

    public CollationElementIterator getCollationElementIterator(String str) {
        initMaxExpansions();
        return new CollationElementIterator(str, this);
    }

    @Override // android.icu.text.Collator
    public boolean isFrozen() {
        return this.frozenLock != null;
    }

    public RuleBasedCollator cloneAsThawed() {
        try {
            RuleBasedCollator ruleBasedCollator = (RuleBasedCollator) super.clone();
            ruleBasedCollator.settings = this.settings.clone();
            ruleBasedCollator.collationBuffer = null;
            ruleBasedCollator.frozenLock = null;
            return ruleBasedCollator;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    private void checkNotFrozen() {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify frozen RuleBasedCollator");
        }
    }

    private final CollationSettings getOwnedSettings() {
        return (CollationSettings) this.settings.copyOnWrite();
    }

    private final CollationSettings getDefaultSettings() {
        return (CollationSettings) this.tailoring.settings.readOnly();
    }

    public void setUpperCaseFirst(boolean z) {
        checkNotFrozen();
        if (z != isUpperCaseFirst()) {
            CollationSettings ownedSettings = getOwnedSettings();
            ownedSettings.setCaseFirst(z ? 768 : 0);
            setFastLatinOptions(ownedSettings);
        }
    }

    public void setLowerCaseFirst(boolean z) {
        checkNotFrozen();
        if (z != isLowerCaseFirst()) {
            CollationSettings ownedSettings = getOwnedSettings();
            ownedSettings.setCaseFirst(z ? 512 : 0);
            setFastLatinOptions(ownedSettings);
        }
    }

    public void setFrenchCollation(boolean z) {
        checkNotFrozen();
        if (z != isFrenchCollation()) {
            CollationSettings ownedSettings = getOwnedSettings();
            ownedSettings.setFlag(2048, z);
            setFastLatinOptions(ownedSettings);
        }
    }

    public void setAlternateHandlingShifted(boolean z) {
        checkNotFrozen();
        if (z != isAlternateHandlingShifted()) {
            CollationSettings ownedSettings = getOwnedSettings();
            ownedSettings.setAlternateHandlingShifted(z);
            setFastLatinOptions(ownedSettings);
        }
    }

    public void setCaseLevel(boolean z) {
        checkNotFrozen();
        if (z != isCaseLevel()) {
            CollationSettings ownedSettings = getOwnedSettings();
            ownedSettings.setFlag(1024, z);
            setFastLatinOptions(ownedSettings);
        }
    }

    @Override // android.icu.text.Collator
    public void setDecomposition(int i) {
        boolean z;
        checkNotFrozen();
        if (i == 16) {
            z = false;
        } else if (i == 17) {
            z = true;
        } else {
            throw new IllegalArgumentException("Wrong decomposition mode.");
        }
        if (z != ((CollationSettings) this.settings.readOnly()).getFlag(1)) {
            CollationSettings ownedSettings = getOwnedSettings();
            ownedSettings.setFlag(1, z);
            setFastLatinOptions(ownedSettings);
        }
    }

    @Override // android.icu.text.Collator
    public void setStrength(int i) {
        checkNotFrozen();
        if (i != getStrength()) {
            CollationSettings ownedSettings = getOwnedSettings();
            ownedSettings.setStrength(i);
            setFastLatinOptions(ownedSettings);
        }
    }

    @Override // android.icu.text.Collator
    public RuleBasedCollator setMaxVariable(int i) {
        int i2;
        if (i == -1) {
            i2 = -1;
        } else if (4096 > i || i > 4099) {
            throw new IllegalArgumentException("illegal max variable group " + i);
        } else {
            i2 = i - 4096;
        }
        if (i2 == ((CollationSettings) this.settings.readOnly()).getMaxVariable()) {
            return this;
        }
        CollationSettings defaultSettings = getDefaultSettings();
        if (this.settings.readOnly() == defaultSettings && i2 < 0) {
            return this;
        }
        CollationSettings ownedSettings = getOwnedSettings();
        if (i == -1) {
            i = defaultSettings.getMaxVariable() + 4096;
        }
        long lastPrimaryForGroup = this.data.getLastPrimaryForGroup(i);
        ownedSettings.setMaxVariable(i2, defaultSettings.options);
        ownedSettings.variableTop = lastPrimaryForGroup;
        setFastLatinOptions(ownedSettings);
        return this;
    }

    public void setNumericCollation(boolean z) {
        checkNotFrozen();
        if (z != getNumericCollation()) {
            CollationSettings ownedSettings = getOwnedSettings();
            ownedSettings.setFlag(2, z);
            setFastLatinOptions(ownedSettings);
        }
    }

    @Override // android.icu.text.Collator
    public void setReorderCodes(int... iArr) {
        checkNotFrozen();
        int length = iArr != null ? iArr.length : 0;
        if (length == 1 && iArr[0] == 103) {
            length = 0;
        }
        if (length == 0) {
            if (((CollationSettings) this.settings.readOnly()).reorderCodes.length == 0) {
                return;
            }
        } else if (Arrays.equals(iArr, ((CollationSettings) this.settings.readOnly()).reorderCodes)) {
            return;
        }
        CollationSettings defaultSettings = getDefaultSettings();
        if (length != 1 || iArr[0] != -1) {
            CollationSettings ownedSettings = getOwnedSettings();
            if (length == 0) {
                ownedSettings.resetReordering();
            } else {
                ownedSettings.setReordering(this.data, (int[]) iArr.clone());
            }
            setFastLatinOptions(ownedSettings);
        } else if (this.settings.readOnly() != defaultSettings) {
            CollationSettings ownedSettings2 = getOwnedSettings();
            ownedSettings2.copyReorderingFrom(defaultSettings);
            setFastLatinOptions(ownedSettings2);
        }
    }

    private void setFastLatinOptions(CollationSettings collationSettings) {
        collationSettings.fastLatinOptions = CollationFastLatin.getOptions(this.data, collationSettings, collationSettings.fastLatinPrimaries);
    }

    public String getRules() {
        return this.tailoring.getRules();
    }

    public UnicodeSet getTailoredSet() {
        UnicodeSet unicodeSet = new UnicodeSet();
        if (this.data.base != null) {
            new TailoredSet(unicodeSet).forData(this.data);
        }
        return unicodeSet;
    }

    public int getStrength() {
        return ((CollationSettings) this.settings.readOnly()).getStrength();
    }

    public boolean isUpperCaseFirst() {
        return ((CollationSettings) this.settings.readOnly()).getCaseFirst() == 768;
    }

    public boolean isLowerCaseFirst() {
        return ((CollationSettings) this.settings.readOnly()).getCaseFirst() == 512;
    }

    public boolean isAlternateHandlingShifted() {
        return ((CollationSettings) this.settings.readOnly()).getAlternateHandling();
    }

    public boolean isCaseLevel() {
        return (((CollationSettings) this.settings.readOnly()).options & 1024) != 0;
    }

    public boolean isFrenchCollation() {
        return (((CollationSettings) this.settings.readOnly()).options & 2048) != 0;
    }

    public boolean getNumericCollation() {
        return (((CollationSettings) this.settings.readOnly()).options & 2) != 0;
    }

    @Override // java.util.Comparator, android.icu.text.Collator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        RuleBasedCollator ruleBasedCollator = (RuleBasedCollator) obj;
        if (!((CollationSettings) this.settings.readOnly()).equals(ruleBasedCollator.settings.readOnly())) {
            return false;
        }
        CollationData collationData = this.data;
        if (collationData == ruleBasedCollator.data) {
            return true;
        }
        boolean z = collationData.base == null;
        boolean z2 = ruleBasedCollator.data.base == null;
        if (z != z2) {
            return false;
        }
        String rules = this.tailoring.getRules();
        String rules2 = ruleBasedCollator.tailoring.getRules();
        return ((z || rules.length() != 0) && ((z2 || rules2.length() != 0) && rules.equals(rules2))) || getTailoredSet().equals(ruleBasedCollator.getTailoredSet());
    }

    @Override // android.icu.text.Collator
    public int hashCode() {
        int i;
        int hashCode = ((CollationSettings) this.settings.readOnly()).hashCode();
        if (this.data.base == null) {
            return hashCode;
        }
        UnicodeSetIterator unicodeSetIterator = new UnicodeSetIterator(getTailoredSet());
        while (unicodeSetIterator.next() && (i = unicodeSetIterator.codepoint) != UnicodeSetIterator.IS_STRING) {
            hashCode ^= this.data.getCE32(i);
        }
        return hashCode;
    }

    @Override // android.icu.text.Collator
    public int compare(String str, String str2) {
        return doCompare(str, str2);
    }

    /* access modifiers changed from: private */
    public static abstract class NFDIterator {
        private String decomp;
        private int index;

        /* access modifiers changed from: protected */
        public abstract int nextRawCodePoint();

        NFDIterator() {
        }

        /* access modifiers changed from: package-private */
        public final void reset() {
            this.index = -1;
        }

        /* access modifiers changed from: package-private */
        public final int nextCodePoint() {
            int i = this.index;
            if (i >= 0) {
                if (i == this.decomp.length()) {
                    this.index = -1;
                } else {
                    int codePointAt = Character.codePointAt(this.decomp, this.index);
                    this.index += Character.charCount(codePointAt);
                    return codePointAt;
                }
            }
            return nextRawCodePoint();
        }

        /* access modifiers changed from: package-private */
        public final int nextDecomposedCodePoint(Normalizer2Impl normalizer2Impl, int i) {
            if (this.index >= 0) {
                return i;
            }
            this.decomp = normalizer2Impl.getDecomposition(i);
            String str = this.decomp;
            if (str == null) {
                return i;
            }
            int codePointAt = Character.codePointAt(str, 0);
            this.index = Character.charCount(codePointAt);
            return codePointAt;
        }
    }

    /* access modifiers changed from: private */
    public static class UTF16NFDIterator extends NFDIterator {
        protected int pos;
        protected CharSequence s;

        UTF16NFDIterator() {
        }

        /* access modifiers changed from: package-private */
        public void setText(CharSequence charSequence, int i) {
            reset();
            this.s = charSequence;
            this.pos = i;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.RuleBasedCollator.NFDIterator
        public int nextRawCodePoint() {
            if (this.pos == this.s.length()) {
                return -1;
            }
            int codePointAt = Character.codePointAt(this.s, this.pos);
            this.pos += Character.charCount(codePointAt);
            return codePointAt;
        }
    }

    /* access modifiers changed from: private */
    public static final class FCDUTF16NFDIterator extends UTF16NFDIterator {
        private StringBuilder str;

        FCDUTF16NFDIterator() {
        }

        /* access modifiers changed from: package-private */
        public void setText(Normalizer2Impl normalizer2Impl, CharSequence charSequence, int i) {
            reset();
            int makeFCD = normalizer2Impl.makeFCD(charSequence, i, charSequence.length(), null);
            if (makeFCD == charSequence.length()) {
                this.s = charSequence;
                this.pos = i;
                return;
            }
            StringBuilder sb = this.str;
            if (sb == null) {
                this.str = new StringBuilder();
            } else {
                sb.setLength(0);
            }
            this.str.append(charSequence, i, makeFCD);
            normalizer2Impl.makeFCD(charSequence, makeFCD, charSequence.length(), new Normalizer2Impl.ReorderingBuffer(normalizer2Impl, this.str, charSequence.length() - i));
            this.s = this.str;
            this.pos = 0;
        }
    }

    private static final int compareNFDIter(Normalizer2Impl normalizer2Impl, NFDIterator nFDIterator, NFDIterator nFDIterator2) {
        while (true) {
            int nextCodePoint = nFDIterator.nextCodePoint();
            int nextCodePoint2 = nFDIterator2.nextCodePoint();
            if (nextCodePoint != nextCodePoint2) {
                int i = -2;
                int nextDecomposedCodePoint = nextCodePoint < 0 ? -2 : nextCodePoint == 65534 ? -1 : nFDIterator.nextDecomposedCodePoint(normalizer2Impl, nextCodePoint);
                if (nextCodePoint2 >= 0) {
                    i = nextCodePoint2 == 65534 ? -1 : nFDIterator2.nextDecomposedCodePoint(normalizer2Impl, nextCodePoint2);
                }
                if (nextDecomposedCodePoint < i) {
                    return -1;
                }
                if (nextDecomposedCodePoint > i) {
                    return 1;
                }
            } else if (nextCodePoint < 0) {
                return 0;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Collator
    public int doCompare(CharSequence charSequence, CharSequence charSequence2) {
        Throwable th;
        CollationBuffer collationBuffer2;
        int i;
        if (charSequence == charSequence2) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            if (i2 != charSequence.length()) {
                if (i2 == charSequence2.length() || charSequence.charAt(i2) != charSequence2.charAt(i2)) {
                    break;
                }
                i2++;
            } else if (i2 == charSequence2.length()) {
                return 0;
            }
        }
        CollationSettings collationSettings = (CollationSettings) this.settings.readOnly();
        boolean isNumeric = collationSettings.isNumeric();
        if (i2 > 0 && ((i2 != charSequence.length() && this.data.isUnsafeBackward(charSequence.charAt(i2), isNumeric)) || (i2 != charSequence2.length() && this.data.isUnsafeBackward(charSequence2.charAt(i2), isNumeric)))) {
            do {
                i2--;
                if (i2 <= 0) {
                    break;
                }
            } while (this.data.isUnsafeBackward(charSequence.charAt(i2), isNumeric));
        }
        int i3 = collationSettings.fastLatinOptions;
        int compareUTF16 = (i3 < 0 || (i2 != charSequence.length() && charSequence.charAt(i2) > 383) || (i2 != charSequence2.length() && charSequence2.charAt(i2) > 383)) ? -2 : CollationFastLatin.compareUTF16(this.data.fastLatinTable, collationSettings.fastLatinPrimaries, i3, charSequence, charSequence2, i2);
        CollationBuffer collationBuffer3 = null;
        if (compareUTF16 == -2) {
            try {
                collationBuffer2 = getCollationBuffer();
                try {
                    if (collationSettings.dontCheckFCD()) {
                        collationBuffer2.leftUTF16CollIter.setText(isNumeric, charSequence, i2);
                        collationBuffer2.rightUTF16CollIter.setText(isNumeric, charSequence2, i2);
                        i = CollationCompare.compareUpToQuaternary(collationBuffer2.leftUTF16CollIter, collationBuffer2.rightUTF16CollIter, collationSettings);
                    } else {
                        collationBuffer2.leftFCDUTF16Iter.setText(isNumeric, charSequence, i2);
                        collationBuffer2.rightFCDUTF16Iter.setText(isNumeric, charSequence2, i2);
                        i = CollationCompare.compareUpToQuaternary(collationBuffer2.leftFCDUTF16Iter, collationBuffer2.rightFCDUTF16Iter, collationSettings);
                    }
                    compareUTF16 = i;
                    releaseCollationBuffer(collationBuffer2);
                } catch (Throwable th2) {
                    th = th2;
                    releaseCollationBuffer(collationBuffer2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                collationBuffer2 = collationBuffer3;
                releaseCollationBuffer(collationBuffer2);
                throw th;
            }
        }
        if (compareUTF16 != 0 || collationSettings.getStrength() < 15) {
            return compareUTF16;
        }
        try {
            collationBuffer3 = getCollationBuffer();
            Normalizer2Impl normalizer2Impl = this.data.nfcImpl;
            if (collationSettings.dontCheckFCD()) {
                collationBuffer3.leftUTF16NFDIter.setText(charSequence, i2);
                collationBuffer3.rightUTF16NFDIter.setText(charSequence2, i2);
                return compareNFDIter(normalizer2Impl, collationBuffer3.leftUTF16NFDIter, collationBuffer3.rightUTF16NFDIter);
            }
            collationBuffer3.leftFCDUTF16NFDIter.setText(normalizer2Impl, charSequence, i2);
            collationBuffer3.rightFCDUTF16NFDIter.setText(normalizer2Impl, charSequence2, i2);
            int compareNFDIter = compareNFDIter(normalizer2Impl, collationBuffer3.leftFCDUTF16NFDIter, collationBuffer3.rightFCDUTF16NFDIter);
            releaseCollationBuffer(collationBuffer3);
            return compareNFDIter;
        } finally {
            releaseCollationBuffer(collationBuffer3);
        }
    }

    RuleBasedCollator(CollationTailoring collationTailoring, ULocale uLocale) {
        this.data = collationTailoring.data;
        this.settings = collationTailoring.settings.clone();
        this.tailoring = collationTailoring;
        this.validLocale = uLocale;
        this.actualLocaleIsSameAsValid = false;
    }

    private void adoptTailoring(CollationTailoring collationTailoring) {
        this.data = collationTailoring.data;
        this.settings = collationTailoring.settings.clone();
        this.tailoring = collationTailoring;
        this.validLocale = collationTailoring.actualLocale;
        this.actualLocaleIsSameAsValid = false;
    }

    /* access modifiers changed from: private */
    public static final class CollationBuffer {
        FCDUTF16CollationIterator leftFCDUTF16Iter;
        FCDUTF16NFDIterator leftFCDUTF16NFDIter;
        UTF16CollationIterator leftUTF16CollIter;
        UTF16NFDIterator leftUTF16NFDIter;
        FCDUTF16CollationIterator rightFCDUTF16Iter;
        FCDUTF16NFDIterator rightFCDUTF16NFDIter;
        UTF16CollationIterator rightUTF16CollIter;
        UTF16NFDIterator rightUTF16NFDIter;

        private CollationBuffer(CollationData collationData) {
            this.leftUTF16CollIter = new UTF16CollationIterator(collationData);
            this.rightUTF16CollIter = new UTF16CollationIterator(collationData);
            this.leftFCDUTF16Iter = new FCDUTF16CollationIterator(collationData);
            this.rightFCDUTF16Iter = new FCDUTF16CollationIterator(collationData);
            this.leftUTF16NFDIter = new UTF16NFDIterator();
            this.rightUTF16NFDIter = new UTF16NFDIterator();
            this.leftFCDUTF16NFDIter = new FCDUTF16NFDIterator();
            this.rightFCDUTF16NFDIter = new FCDUTF16NFDIterator();
        }
    }

    private final CollationBuffer getCollationBuffer() {
        if (isFrozen()) {
            this.frozenLock.lock();
        } else if (this.collationBuffer == null) {
            this.collationBuffer = new CollationBuffer(this.data);
        }
        return this.collationBuffer;
    }

    private final void releaseCollationBuffer(CollationBuffer collationBuffer2) {
        if (isFrozen()) {
            this.frozenLock.unlock();
        }
    }
}
