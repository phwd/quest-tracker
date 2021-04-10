package android.icu.text;

import android.icu.impl.Assert;
import android.icu.impl.CharacterIteration;
import android.icu.text.DictionaryBreakEngine;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/* access modifiers changed from: package-private */
public class CjkBreakEngine extends DictionaryBreakEngine {
    private static final UnicodeSet fHanWordSet = new UnicodeSet();
    private static final UnicodeSet fHangulWordSet = new UnicodeSet();
    private static final UnicodeSet fHiraganaWordSet = new UnicodeSet();
    private static final UnicodeSet fKatakanaWordSet = new UnicodeSet();
    private static final int kMaxKatakanaGroupLength = 20;
    private static final int kMaxKatakanaLength = 8;
    private static final int kint32max = Integer.MAX_VALUE;
    private static final int maxSnlp = 255;
    private DictionaryMatcher fDictionary;

    static {
        fHangulWordSet.applyPattern("[\\uac00-\\ud7a3]");
        fHanWordSet.applyPattern("[:Han:]");
        fKatakanaWordSet.applyPattern("[[:Katakana:]\\uff9e\\uff9f]");
        fHiraganaWordSet.applyPattern("[:Hiragana:]");
        fHangulWordSet.freeze();
        fHanWordSet.freeze();
        fKatakanaWordSet.freeze();
        fHiraganaWordSet.freeze();
    }

    public CjkBreakEngine(boolean korean) throws IOException {
        this.fDictionary = null;
        this.fDictionary = DictionaryData.loadDictionaryFor("Hira");
        if (korean) {
            setCharacters(fHangulWordSet);
            return;
        }
        UnicodeSet cjSet = new UnicodeSet();
        cjSet.addAll(fHanWordSet);
        cjSet.addAll(fKatakanaWordSet);
        cjSet.addAll(fHiraganaWordSet);
        cjSet.add(65392);
        cjSet.add(12540);
        setCharacters(cjSet);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CjkBreakEngine) {
            return this.fSet.equals(((CjkBreakEngine) obj).fSet);
        }
        return false;
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    private static int getKatakanaCost(int wordlength) {
        int[] katakanaCost = {8192, 984, HttpURLConnection.HTTP_CLIENT_TIMEOUT, 240, 204, 252, 300, 372, 480};
        if (wordlength > 8) {
            return 8192;
        }
        return katakanaCost[wordlength];
    }

    private static boolean isKatakana(int value) {
        return (value >= 12449 && value <= 12542 && value != 12539) || (value >= 65382 && value <= 65439);
    }

    @Override // android.icu.text.DictionaryBreakEngine
    public int divideUpDictionaryRange(CharacterIterator inText, int startPos, int endPos, DictionaryBreakEngine.DequeI foundBreaks) {
        int[] charPositions;
        CharacterIterator text;
        int i;
        int[] bestSnlp;
        int[] prev;
        int[] values;
        int[] lengths;
        StringBuffer s;
        int newSnlp;
        if (startPos >= endPos) {
            return 0;
        }
        inText.setIndex(startPos);
        int inputLength = endPos - startPos;
        int[] charPositions2 = new int[(inputLength + 1)];
        StringBuffer s2 = new StringBuffer("");
        inText.setIndex(startPos);
        while (inText.getIndex() < endPos) {
            s2.append(inText.current());
            inText.next();
        }
        String prenormstr = s2.toString();
        int numCodePts = 0;
        if (Normalizer.quickCheck(prenormstr, Normalizer.NFKC) == Normalizer.YES || Normalizer.isNormalized(prenormstr, Normalizer.NFKC, 0)) {
            CharacterIterator text2 = new StringCharacterIterator(prenormstr);
            int index = 0;
            charPositions2[0] = 0;
            while (index < prenormstr.length()) {
                index += Character.charCount(prenormstr.codePointAt(index));
                numCodePts++;
                charPositions2[numCodePts] = index;
            }
            charPositions = charPositions2;
            text = text2;
        } else {
            String normStr = Normalizer.normalize(prenormstr, Normalizer.NFKC);
            CharacterIterator text3 = new StringCharacterIterator(normStr);
            int[] charPositions3 = new int[(normStr.length() + 1)];
            Normalizer normalizer = new Normalizer(prenormstr, Normalizer.NFKC, 0);
            int index2 = 0;
            charPositions3[0] = 0;
            while (index2 < normalizer.endIndex()) {
                normalizer.next();
                numCodePts++;
                index2 = normalizer.getIndex();
                charPositions3[numCodePts] = index2;
            }
            charPositions = charPositions3;
            text = text3;
        }
        int[] bestSnlp2 = new int[(numCodePts + 1)];
        bestSnlp2[0] = 0;
        int i2 = 1;
        while (true) {
            i = Integer.MAX_VALUE;
            if (i2 > numCodePts) {
                break;
            }
            bestSnlp2[i2] = Integer.MAX_VALUE;
            i2++;
        }
        int[] prev2 = new int[(numCodePts + 1)];
        for (int i3 = 0; i3 <= numCodePts; i3++) {
            prev2[i3] = -1;
        }
        int[] values2 = new int[numCodePts];
        int[] lengths2 = new int[numCodePts];
        text.setIndex(0);
        boolean is_prev_katakana = false;
        int i4 = 0;
        while (i4 < numCodePts) {
            int ix = text.getIndex();
            if (bestSnlp2[i4] == i) {
                s = s2;
                values = values2;
                prev = prev2;
                lengths = lengths2;
                bestSnlp = bestSnlp2;
            } else {
                s = s2;
                int maxSearchLength = i4 + 20 < numCodePts ? 20 : numCodePts - i4;
                lengths = lengths2;
                int[] count_ = new int[1];
                values = values2;
                prev = prev2;
                bestSnlp = bestSnlp2;
                this.fDictionary.matches(text, maxSearchLength, lengths, count_, maxSearchLength, values);
                int count = count_[0];
                text.setIndex(ix);
                if ((count == 0 || lengths[0] != 1) && CharacterIteration.current32(text) != Integer.MAX_VALUE && !fHangulWordSet.contains(CharacterIteration.current32(text))) {
                    values[count] = 255;
                    lengths[count] = 1;
                    count++;
                }
                for (int j = 0; j < count; j++) {
                    int newSnlp2 = bestSnlp[i4] + values[j];
                    if (newSnlp2 < bestSnlp[lengths[j] + i4]) {
                        bestSnlp[lengths[j] + i4] = newSnlp2;
                        prev[lengths[j] + i4] = i4;
                    }
                }
                boolean is_katakana = isKatakana(CharacterIteration.current32(text));
                if (!is_prev_katakana && is_katakana) {
                    int j2 = i4 + 1;
                    CharacterIteration.next32(text);
                    while (j2 < numCodePts && j2 - i4 < 20 && isKatakana(CharacterIteration.current32(text))) {
                        CharacterIteration.next32(text);
                        j2++;
                    }
                    if (j2 - i4 < 20 && (newSnlp = bestSnlp[i4] + getKatakanaCost(j2 - i4)) < bestSnlp[j2]) {
                        bestSnlp[j2] = newSnlp;
                        prev[j2] = i4;
                    }
                }
                is_prev_katakana = is_katakana;
            }
            i4++;
            text.setIndex(ix);
            CharacterIteration.next32(text);
            inputLength = inputLength;
            s2 = s;
            lengths2 = lengths;
            values2 = values;
            prev2 = prev;
            bestSnlp2 = bestSnlp;
            i = Integer.MAX_VALUE;
        }
        int[] t_boundary = new int[(numCodePts + 1)];
        int numBreaks = 0;
        if (bestSnlp2[numCodePts] == Integer.MAX_VALUE) {
            t_boundary[0] = numCodePts;
            numBreaks = 0 + 1;
        } else {
            boolean z = true;
            for (int i5 = numCodePts; i5 > 0; i5 = prev2[i5]) {
                t_boundary[numBreaks] = i5;
                numBreaks++;
            }
            if (prev2[t_boundary[numBreaks - 1]] != 0) {
                z = false;
            }
            Assert.assrt(z);
        }
        if (foundBreaks.size() == 0 || foundBreaks.peek() < startPos) {
            t_boundary[numBreaks] = 0;
            numBreaks++;
        }
        int correctedNumBreaks = 0;
        for (int i6 = numBreaks - 1; i6 >= 0; i6--) {
            int pos = charPositions[t_boundary[i6]] + startPos;
            if (!foundBreaks.contains(pos) && pos != startPos) {
                foundBreaks.push(charPositions[t_boundary[i6]] + startPos);
                correctedNumBreaks++;
            }
        }
        if (!foundBreaks.isEmpty() && foundBreaks.peek() == endPos) {
            foundBreaks.pop();
            correctedNumBreaks--;
        }
        if (!foundBreaks.isEmpty()) {
            inText.setIndex(foundBreaks.peek());
        }
        return correctedNumBreaks;
    }
}
