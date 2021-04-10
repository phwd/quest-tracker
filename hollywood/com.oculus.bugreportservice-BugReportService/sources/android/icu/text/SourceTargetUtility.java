package android.icu.text;

class SourceTargetUtility {
    static Normalizer2 NFC = Normalizer2.getNFCInstance();
    static final UnicodeSet NON_STARTERS;

    static {
        UnicodeSet unicodeSet = new UnicodeSet("[:^ccc=0:]");
        unicodeSet.freeze();
        NON_STARTERS = unicodeSet;
    }
}
