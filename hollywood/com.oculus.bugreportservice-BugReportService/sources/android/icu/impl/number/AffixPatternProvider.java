package android.icu.impl.number;

public interface AffixPatternProvider {
    char charAt(int i, int i2);

    boolean containsSymbolType(int i);

    String getString(int i);

    boolean hasBody();

    boolean hasCurrencySign();

    boolean hasNegativeSubpattern();

    int length(int i);

    boolean negativeHasMinusSign();

    boolean positiveHasPlusSign();
}
