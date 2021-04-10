package android.icu.text;

class FunctionReplacer implements UnicodeReplacer {
    private UnicodeReplacer replacer;
    private Transliterator translit;

    public FunctionReplacer(Transliterator transliterator, UnicodeReplacer unicodeReplacer) {
        this.translit = transliterator;
        this.replacer = unicodeReplacer;
    }

    @Override // android.icu.text.UnicodeReplacer
    public int replace(Replaceable replaceable, int i, int i2, int[] iArr) {
        return this.translit.transliterate(replaceable, i, this.replacer.replace(replaceable, i, i2, iArr) + i) - i;
    }

    @Override // android.icu.text.UnicodeReplacer
    public String toReplacerPattern(boolean z) {
        return "&" + this.translit.getID() + "( " + this.replacer.toReplacerPattern(z) + " )";
    }
}
