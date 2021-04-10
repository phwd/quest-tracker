package jp.co.omronsoft.iwnnime.ml.iwnn;

/* access modifiers changed from: package-private */
public class IWnnNative {
    public static final native int WriteOutDictionary(long j, int i);

    public static final native int addWord(long j, String str, String str2, int i, int i2, int i3, int i4);

    public static final native int clearPreviousCandidates(long j);

    public static final native int conv(long j, int i);

    public static final native int deleteDictionary(long j, int i, int i2, int i3);

    public static final native int deleteDictionaryFile(String str);

    public static final native int deleteSearchWord(long j, int i);

    public static final native int deleteWord(long j, int i);

    public static final native void destroy(long j);

    public static final native int forecast(long j, int i, int i2, int i3, int i4);

    public static final native int getCorrectDiff(long j, int i);

    public static final native long getInfo();

    public static final native int getLengthInputCharacters(long j, int i);

    public static final native short getMorphemeHinsi(long j, int i);

    public static final native void getMorphemeWord(long j, int i, String[] strArr);

    public static final native void getMorphemeYomi(long j, int i, String[] strArr);

    public static final native String getSegmentString(long j, int i);

    public static final native String getSegmentStroke(long j, int i);

    public static final native int getState(long j);

    public static final native String getWord(long j, int i, int i2);

    public static final native int getWordCandidateType(long j, int i, int i2);

    public static final native int getWordCharacterType(long j, int i, int i2);

    public static final native int getWordFrequency(long j, int i, int i2);

    public static final native String getWordString(long j, int i, int i2);

    public static final native String getWordStroke(long j, int i, int i2);

    public static final native int getgijistr(long j, int i, int i2);

    public static final native int init(long j, String str);

    public static final native int isGijiResult(long j, int i);

    public static final native int isLearnDictionary(long j, int i);

    public static final native int noconv(long j);

    public static final native int searchWord(long j, int i, int i2);

    public static final native int select(long j, int i, int i2, int i3);

    public static final native int setActiveLang(long j, int i, int i2);

    public static final native int setBookshelf(long j, int i);

    public static final native int setCorrectionOptions(long j, int i, String str, Object obj);

    public static final native int setFlexibleCharset(long j, int i, int i2);

    public static final native int setInput(long j, String str);

    public static final native int setState(long j);

    public static final native void setStateSystem(long j, int i, int i2);

    public static final native int setdicByConf(long j, String str, int i, int i2, String str2, int i3, Object obj);

    public static final native void splitWord(long j, String str, int[] iArr);

    public static final native int undo(long j, int i);

    public static final native int unmountDics(long j, boolean z);

    IWnnNative() {
    }
}
