package android.icu.text;

class FunctionReplacer implements UnicodeReplacer {
    private UnicodeReplacer replacer;
    private Transliterator translit;

    public FunctionReplacer(Transliterator theTranslit, UnicodeReplacer theReplacer) {
        this.translit = theTranslit;
        this.replacer = theReplacer;
    }

    @Override // android.icu.text.UnicodeReplacer
    public int replace(Replaceable text, int start, int limit, int[] cursor) {
        return this.translit.transliterate(text, start, start + this.replacer.replace(text, start, limit, cursor)) - start;
    }

    @Override // android.icu.text.UnicodeReplacer
    public String toReplacerPattern(boolean escapeUnprintable) {
        return "&" + this.translit.getID() + "( " + this.replacer.toReplacerPattern(escapeUnprintable) + " )";
    }

    @Override // android.icu.text.UnicodeReplacer
    public void addReplacementSetTo(UnicodeSet toUnionTo) {
        toUnionTo.addAll(this.translit.getTargetSet());
    }
}
