package jp.co.omronsoft.iwnnime.ml.iwnn;

import android.content.res.AssetManager;
import android.util.Log;

public class IWnnCore {
    private static final boolean DEBUG = false;
    public static final int HEAD_CONVERSION_OFF = 0;
    public static final int HEAD_CONVERSION_ON = 1;
    public static final int LEARN_CONNECT = 128;
    public static final int LEARN_ENABLE = 1;
    public static final int MULTI_SEG_CONVERSION_OFF = 0;
    public static final int MULTI_SEG_CONVERSION_ON = 1;
    public static final int RELATIONAL_LEARNING_OFF = 0;
    public static final int RELATIONAL_LEARNING_ON = 1;
    public static final int SITUATION_ADDRESS = 4;
    public static final int SITUATION_EMAIL = 8;
    public static final int SITUATION_HEAD = 2;
    public static final int SITUATION_NONE = 0;
    public static final int SITUATION_PERSON = 1;
    private static final String TAG = "iWnn";
    private static int mSituationBiasStatus = 0;
    private static boolean sHasLibrary = false;
    private long mIwnnInfo;
    private IWnnSituationManager mSituationManager = null;

    public static final class DictionaryType {
        public static final int DICTIONARY_TYPE_LEARNING = 2;
        public static final int DICTIONARY_TYPE_USER = 3;
    }

    public static final class Hinshi {
        public static final int CHIMEI = 2;
        public static final int JINMEI = 1;
        public static final int KIGOU = 3;
        public static final int MEISI = 0;
        public static final int MEISI_NO_CONJ = 2;
    }

    static {
        try {
            System.loadLibrary("iwnn");
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    public IWnnCore() {
        try {
            this.mIwnnInfo = IWnnNative.getInfo();
            this.mSituationManager = new IWnnSituationManager(this);
        } catch (Exception e) {
            Log.e(TAG, "WARNING: " + e.toString());
        } catch (UnsatisfiedLinkError unused) {
            this.mIwnnInfo = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        long j = this.mIwnnInfo;
        if (j != 0) {
            IWnnNative.destroy(j);
            this.mIwnnInfo = 0;
        }
    }

    public void destroyWnnInfo() {
        long j = this.mIwnnInfo;
        if (j != 0) {
            IWnnNative.destroy(j);
            this.mIwnnInfo = 0;
        }
    }

    public boolean setDictionary(int i, int i2, String str, boolean z, String str2, String str3, int i3, AssetManager assetManager) {
        return setDictionaryApp(i, i2, str, z, str2, str3, i3, assetManager, null);
    }

    public boolean setDictionaryApp(int i, int i2, String str, boolean z, String str2, String str3, int i3, AssetManager assetManager, AssetManager assetManager2) {
        boolean z2;
        if (z) {
            z2 = (assetManager2 == null ? IWnnNative.setdicByConf(this.mIwnnInfo, str, i, 0, str3, i3, assetManager) : 0) > 0;
            IWnnNative.setActiveLang(this.mIwnnInfo, i, i == 0 ? 0 : 1);
        } else {
            z2 = true;
        }
        IWnnNative.setBookshelf(this.mIwnnInfo, i2);
        if (i2 == 3) {
            mSituationBiasStatus = 1;
        } else if (i2 != 4) {
            mSituationBiasStatus = 0;
        } else {
            mSituationBiasStatus = 4;
        }
        if (z) {
            init(str2);
        }
        return z2;
    }

    public int unmountDictionary() {
        return unmountDictionary(true);
    }

    public int unmountDictionary(boolean z) {
        return IWnnNative.unmountDics(this.mIwnnInfo, z);
    }

    public int clearPreviousCandidates() {
        return IWnnNative.clearPreviousCandidates(this.mIwnnInfo);
    }

    public int init(String str) {
        int init = IWnnNative.init(this.mIwnnInfo, str);
        this.mSituationManager.updateState();
        return init;
    }

    public boolean pullSituationState() {
        return IWnnNative.getState(this.mIwnnInfo) >= 0;
    }

    public boolean pushSituationState() {
        return IWnnNative.setState(this.mIwnnInfo) >= 0;
    }

    public void setSituationBiasValue(int i, int i2) {
        IWnnNative.setStateSystem(this.mIwnnInfo, i, i2);
    }

    public int getSituationBiasStatus() {
        return mSituationBiasStatus;
    }

    public int forecast(String str, int i, int i2, int i3, int i4) {
        if (IWnnNative.setInput(this.mIwnnInfo, str) < 0) {
            return 0;
        }
        return IWnnNative.forecast(this.mIwnnInfo, i, i2, i3, i4);
    }

    public int conv(String str, int i) {
        if (IWnnNative.setInput(this.mIwnnInfo, str) < 0) {
            return 0;
        }
        return IWnnNative.conv(this.mIwnnInfo, i);
    }

    public boolean noConv(String str) {
        if (IWnnNative.setInput(this.mIwnnInfo, str) >= 0 && IWnnNative.noconv(this.mIwnnInfo) != 0) {
            return true;
        }
        return false;
    }

    public int select(int i, int i2, boolean z, boolean z2) {
        return IWnnNative.select(this.mIwnnInfo, i, i2, (z ? 1 : 0) | (z2 ? 0 : 128));
    }

    public int searchWord(int i, int i2, String str) {
        if (IWnnNative.setInput(this.mIwnnInfo, str) < 0) {
            return 0;
        }
        return IWnnNative.searchWord(this.mIwnnInfo, i, i2);
    }

    public String getWord(int i, int i2) {
        return IWnnNative.getWord(this.mIwnnInfo, i, i2);
    }

    public int addWord(String str, String str2, int i, int i2, int i3, int i4) {
        return IWnnNative.addWord(this.mIwnnInfo, str, str2, i, i2, i3, i4);
    }

    public int deleteSearchWord(int i) {
        return IWnnNative.deleteSearchWord(this.mIwnnInfo, i);
    }

    public int deleteWord(int i) {
        return IWnnNative.deleteWord(this.mIwnnInfo, i);
    }

    public String getResultString(int i, int i2) {
        return IWnnNative.getWordString(this.mIwnnInfo, i, i2);
    }

    public String getResultStroke(int i, int i2) {
        return IWnnNative.getWordStroke(this.mIwnnInfo, i, i2);
    }

    public String getSegmentStroke(int i) {
        return IWnnNative.getSegmentStroke(this.mIwnnInfo, i);
    }

    public String getSegmentString(int i) {
        return IWnnNative.getSegmentString(this.mIwnnInfo, i);
    }

    public boolean writeoutDictionary(int i) {
        return (i == 2 || i == 3) && IWnnNative.WriteOutDictionary(this.mIwnnInfo, i) >= 0;
    }

    public int runInitialize(int i, int i2, int i3) {
        if (i == 1 || i == 2) {
            return IWnnNative.deleteDictionary(this.mIwnnInfo, i, i2, i3);
        }
        return -1;
    }

    public int setFlexibleCharset(int i, int i2) {
        return IWnnNative.setFlexibleCharset(this.mIwnnInfo, i, i2);
    }

    public boolean isLearnDictionary(int i) {
        return IWnnNative.isLearnDictionary(this.mIwnnInfo, i) != 0;
    }

    public boolean isGijiDic(int i) {
        return IWnnNative.isGijiResult(this.mIwnnInfo, i) > 0;
    }

    public int getCorrectDiff(int i) {
        return IWnnNative.getCorrectDiff(this.mIwnnInfo, i);
    }

    public int getLengthInputCharacters(int i) {
        return IWnnNative.getLengthInputCharacters(this.mIwnnInfo, i);
    }

    public boolean undo(int i) {
        return IWnnNative.undo(this.mIwnnInfo, i) >= 0;
    }

    public void splitWord(String str, int[] iArr) {
        if (iArr.length >= 2) {
            iArr[0] = 0;
            iArr[1] = 0;
            IWnnNative.splitWord(this.mIwnnInfo, str, iArr);
        }
    }

    public void getMorphemeText(int i, String[][] strArr) {
        if (i >= 0 && strArr.length >= 1 && strArr[0].length == 2) {
            strArr[0][0] = null;
            strArr[0][1] = null;
            IWnnNative.getMorphemeWord(this.mIwnnInfo, i, strArr[0]);
            int i2 = i;
            for (int i3 = 1; i3 < strArr.length; i3++) {
                strArr[i3][0] = null;
                strArr[i3][1] = null;
                IWnnNative.getMorphemeYomi(this.mIwnnInfo, i2, strArr[i3]);
                i2 = -1;
            }
        }
    }

    public short getMorphemePartOfSpeech(int i) {
        return IWnnNative.getMorphemeHinsi(this.mIwnnInfo, i);
    }

    public int getgijistr(String str, int i, int i2) {
        if (IWnnNative.setInput(this.mIwnnInfo, str) < 0) {
            return 0;
        }
        return IWnnNative.getgijistr(this.mIwnnInfo, i, i2);
    }

    public boolean deleteDictionaryFile(String str) {
        return IWnnNative.deleteDictionaryFile(str) == 1;
    }

    public static boolean hasLibrary() {
        return sHasLibrary;
    }

    public int setCorrectionOptions(int i, String str, AssetManager assetManager) {
        return IWnnNative.setCorrectionOptions(this.mIwnnInfo, i, str, assetManager);
    }

    public int getResultFrequency(int i, int i2) {
        return IWnnNative.getWordFrequency(this.mIwnnInfo, i, i2);
    }

    public int getResultCandidateType(int i, int i2) {
        return IWnnNative.getWordCandidateType(this.mIwnnInfo, i, i2);
    }

    public int getResultCharacterType(int i, int i2) {
        return IWnnNative.getWordCharacterType(this.mIwnnInfo, i, i2);
    }
}
