package jp.co.omronsoft.iwnnime.ml;

public interface WnnEngine {
    int addWord(WnnWord wnnWord);

    void breakSequence();

    int clearPreviousCandidates();

    void close();

    int convert(ComposingText composingText);

    int convertGijiStr(ComposingText composingText, int i);

    boolean deleteWord(WnnWord wnnWord);

    WnnWord getNextCandidate();

    boolean hasCandidate();

    void init(String str);

    boolean isConverting();

    boolean learn(WnnWord wnnWord);

    int makeCandidateListOf(int i);

    int predict(ComposingText composingText, int i, int i2);

    int searchWords(String str);
}
