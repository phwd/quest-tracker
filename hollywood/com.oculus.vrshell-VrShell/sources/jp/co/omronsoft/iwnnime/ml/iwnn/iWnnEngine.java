package jp.co.omronsoft.iwnnime.ml.iwnn;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;
import jp.co.omronsoft.iwnnime.ml.ComposingText;
import jp.co.omronsoft.iwnnime.ml.StrSegment;
import jp.co.omronsoft.iwnnime.ml.WnnEngine;
import jp.co.omronsoft.iwnnime.ml.WnnWord;
import jp.co.omronsoft.iwnnime.ml.jajp.KanaRomajiConverter;

public class iWnnEngine implements WnnEngine {
    public static final int ADD_WORD_DEFAULT_MODE = 0;
    public static final int CANDIDATE_MAX = 350;
    private static final char CHARACTER_CODE_JAPANESE_NN = 12435;
    private static final String[] CONF_TABLE = {"@iwnnime@/lib_dic_ja_JP.conf.so", "@iwnnime@/lib_dic_en_USUK.conf.so"};
    public static final int CONVERT_TYPE_HANKATA = 4;
    public static final int CONVERT_TYPE_HAN_EIJI_CAP = 5;
    public static final int CONVERT_TYPE_HAN_EIJI_LOWER = 9;
    public static final int CONVERT_TYPE_HAN_EIJI_UPPER = 7;
    public static final int CONVERT_TYPE_HAN_INPUT_KEY = 11;
    public static final int CONVERT_TYPE_HIRAGANA = 2;
    public static final int CONVERT_TYPE_KATAKANA = 3;
    private static final int[] CONVERT_TYPE_LIST = {11, 12, 9, 10, 7, 8, 5, 6};
    private static final int[] CONVERT_TYPE_LIST_HALF = {9, 7, 5};
    public static final int CONVERT_TYPE_NONE = 1;
    public static final int CONVERT_TYPE_ZEN_EIJI_CAP = 6;
    public static final int CONVERT_TYPE_ZEN_EIJI_LOWER = 10;
    public static final int CONVERT_TYPE_ZEN_EIJI_UPPER = 8;
    public static final int CONVERT_TYPE_ZEN_INPUT_KEY = 12;
    private static final boolean DEBUG = false;
    public static final int DICTIONARY_DELETE_FAILURE = -1;
    private static final Pattern GIJI_PATTERN = Pattern.compile("^[a-zA-Z0-9０-９-]+$");
    private static final int INITIAL_PERMISSION_STATE = 1;
    private static final String IWNNIME_KEY = "@iwnnime@/";
    private static final String IWNN_VERSION = "2.4";
    public static final int LEARN_DICTIONARY_DELETE = 1;
    private static final int OFFSET_FULL_WIDTH = 65248;
    public static final int PREDICT_WORD_LENGTH_UNSPECIFIED = -1;
    private static final char SINGLE_CHAR_N = 'n';
    private static final String TAG = "iWnn";
    public static final int USER_DICTIONARY_DELETE = 2;
    public static final int WNNWORD_ATTRIBUTE_CONNECTED = 8192;
    public static final int WNNWORD_ATTRIBUTE_CORRECTION_WORD = 268435456;
    public static final int WNNWORD_ATTRIBUTE_DELETABLE = 2;
    public static final int WNNWORD_ATTRIBUTE_HISTORY = 1;
    public static final int WNNWORD_ATTRIBUTE_JAPANESE_QWERTY_GIJI = 128;
    public static final int WNNWORD_ATTRIBUTE_LATIN_GIJI = 32;
    public static final int WNNWORD_ATTRIBUTE_MUHENKAN = 4;
    public static final int WNNWORD_ATTRIBUTE_MUHENKAN_LOWERCASE = 256;
    public static final int WNNWORD_ATTRIBUTE_NOT_DISPLAY_RUBY = 134217728;
    public static final int WNNWORD_ATTRIBUTE_NO_CANDIDATE = 2048;
    public static final int WNNWORD_ATTRIBUTE_NO_DICTIONARY = 64;
    public static final int WNNWORD_ATTRIBUTE_SYMBOL = 8;
    public static final int WNNWORD_ATTRIBUTE_SYMBOLLIST = 16;
    public static final int WNNWORD_ATTRIBUTE_TARGET_LEARN = 32768;
    private static ArrayList<ConfTypeInfo> mConfTypeList = new ArrayList<>();
    private static iWnnEngine mEngine = new iWnnEngine();
    private Pattern mAllowDuplicationCharPattern;
    private HashMap<String, WnnWord> mCandTable;
    private String[] mCaseGijiList;
    private int mCaseGijiListIndex;
    private int mConfType;
    private IWnnCore mCore;
    private int mDictionarySet;
    private boolean mEnableConvertedCandidate;
    private boolean mEnableHeadConv;
    private String mFilesDirPath;
    private String mForecastKey;
    private boolean mHasBroke;
    private boolean mHasSearchWords;
    private boolean mIsConverting;
    private boolean mIsForbidDuplication;
    private boolean mIsRequestGiji;
    private int mKeyboardType;
    private HashMap<Integer, String> mLastConfFile;
    private int mLastPermissionState;
    private LatinFilter mLatinFilter;
    private int mMaxLength;
    private int mMinLength;
    private int mOutputNum;
    private int mSearchCnt;
    private ComposingText mSearchComposingText;
    private String mSearchKey;
    private int mSegment;
    private int mSegmentCount;
    private HashMap<Integer, Boolean> mStatusDicMount;

    public static final class AddWordDictionaryType {
        public static final int ADD_WORD_DICTIONARY_TYPE_LEARNING = 1;
        public static final int ADD_WORD_DICTIONARY_TYPE_USER = 0;
    }

    public static final class ConfType {
        public static final int JAPANESE = 0;
        private static final int NONE = -1;
    }

    public static final class CorrectType {
        public static final int CORRECT_DEFAULT_ON = 20480;
        public static final int CORRECT_HEAD_CONV_ON = 32768;
        public static final int CORRECT_OFF = 0;
        public static final int CORRECT_RENBUN_ON = 8192;
        public static final int CORRECT_YOSOKU_ON = 4096;
        public static final int CORRECT_ZEN_ON = 16384;
    }

    public static final class FlexibleSearchType {
        public static final int FLEXIBLE_SEARCH_OFF = 0;
        public static final int FLEXIBLE_SEARCH_ON = 1;
    }

    public static final class KeyboardType {
        public static final int KEY_TYPE_KEYPAD12 = 0;
        public static final int KEY_TYPE_NONE = 255;
        public static final int KEY_TYPE_QWERTY = 1;
    }

    public static final class SearchMethod {
        public static final int SEARCH_ORIGINAL_PULL_FRONT = 1;
        public static final int SEARCH_ORIGINAL_PULL_PERFECTION = 0;
        public static final int SEARCH_REVERSE_PULL_FRONT = 4;
        public static final int SEARCH_REVERSE_PULL_PERFECTION = 3;
    }

    public static final class SearchOrder {
        public static final int ORDER_FREQUENCY = 0;
        public static final int ORDER_READING = 1;
        public static final int ORDER_REGISTRATION = 2;
    }

    public static final class SetType {
        private static final int DICTIONARY_TYPE_MAX = 57;
        public static final int EISUKANA = 1;
        public static final int JINMEI = 3;
        public static final int KAOMOJI = 2;
        public static final int LEARNDIC = 11;
        private static final int NONE = -1;
        public static final int NORMAL = 0;
        public static final int POSTAL_ADDRESS = 4;
        public static final int USERDIC = 10;
    }

    private boolean isAlphabet(char c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    private boolean isHiragana(char c) {
        return 12353 <= c && c <= 12438;
    }

    private boolean isLowercaseStrokeInLearning() {
        return false;
    }

    static {
        resetConfTypeList();
    }

    /* access modifiers changed from: private */
    public static class ConfTypeInfo {
        int inputMethodType;
        boolean isMultipleInputMethod;
        int language;
        int type;

        private ConfTypeInfo(int i, int i2, boolean z, int i3) {
            this.type = i;
            this.language = i2;
            this.isMultipleInputMethod = z;
            this.inputMethodType = i3;
        }

        public int type() {
            return this.type;
        }

        public int language() {
            return this.language;
        }

        public boolean isMultipleInputMethod() {
            return this.isMultipleInputMethod;
        }

        public int inputMethodType() {
            return this.inputMethodType;
        }
    }

    public static void resetConfTypeList() {
        mConfTypeList.clear();
    }

    /* access modifiers changed from: private */
    public class LatinFilter {
        private static final int CASE_HEAD_UPPER = 3;
        private static final int CASE_LOWER = 0;
        private static final int CASE_UPPER = 1;
        private HashMap<String, WnnWord> mCandEnglishTable = new HashMap<>();
        private int mCandidateCase;
        private String mInputString;

        public LatinFilter() {
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void clearLatinFilter() {
            this.mCandEnglishTable.clear();
            this.mCandidateCase = 0;
        }

        private void clearCandEnglishTable() {
            this.mCandEnglishTable.clear();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setSearchKey(String str) {
            this.mInputString = str;
            if (str.length() != 0) {
                if (!Character.isUpperCase(str.charAt(0))) {
                    this.mCandidateCase = 0;
                } else if (str.length() <= 1 || !Character.isUpperCase(str.charAt(1))) {
                    this.mCandidateCase = 3;
                } else {
                    this.mCandidateCase = 1;
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private String candidateConversion(String str) {
            if (str == null) {
                return str;
            }
            iWnnEngine iwnnengine = iWnnEngine.this;
            if (str.equals(iwnnengine.toLowerCase(iwnnengine.mSearchKey))) {
                return iWnnEngine.this.mSearchKey;
            }
            int i = this.mCandidateCase;
            if (i == 1) {
                return iWnnEngine.this.toUpperCase(str).length() == str.length() ? iWnnEngine.this.toUpperCase(str) : str;
            }
            if (i != 3 || !Character.isLowerCase(str.charAt(0))) {
                return str;
            }
            String upperCase = iWnnEngine.this.toUpperCase(str);
            if (upperCase.length() != str.length()) {
                return str;
            }
            char charAt = upperCase.charAt(0);
            return Character.toString(charAt) + str.substring(1);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean putCandidate(WnnWord wnnWord) {
            if (this.mInputString == null || wnnWord == null || wnnWord.candidate == null) {
                return false;
            }
            if (this.mInputString.length() <= 1) {
                if (this.mCandEnglishTable.containsKey(wnnWord.candidate)) {
                    return false;
                }
                this.mCandEnglishTable.put(wnnWord.candidate, wnnWord);
                return true;
            } else if (this.mCandEnglishTable.containsKey(iWnnEngine.this.toLowerCase(wnnWord.candidate))) {
                return false;
            } else {
                this.mCandEnglishTable.put(iWnnEngine.this.toLowerCase(wnnWord.candidate), wnnWord);
                return true;
            }
        }
    }

    private iWnnEngine() {
        this.mKeyboardType = 255;
        this.mSearchKey = null;
        this.mForecastKey = null;
        this.mOutputNum = 0;
        this.mCore = null;
        this.mSegment = 0;
        this.mSegmentCount = 0;
        this.mSearchCnt = 0;
        this.mCandTable = null;
        this.mSearchComposingText = null;
        this.mCaseGijiList = null;
        this.mIsRequestGiji = true;
        this.mAllowDuplicationCharPattern = Pattern.compile(".*[ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん].*");
        this.mIsForbidDuplication = false;
        this.mIsConverting = false;
        this.mLatinFilter = new LatinFilter();
        this.mEnableConvertedCandidate = false;
        this.mDictionarySet = -1;
        this.mHasBroke = true;
        this.mEnableHeadConv = false;
        this.mFilesDirPath = null;
        this.mLastPermissionState = 1;
        this.mStatusDicMount = new HashMap<>();
        this.mMinLength = -1;
        this.mMaxLength = -1;
        this.mLastConfFile = new HashMap<>();
        this.mConfType = -1;
        this.mCore = new IWnnCore();
        this.mCandTable = new HashMap<>();
    }

    public static iWnnEngine getEngine() {
        return mEngine;
    }

    public boolean setDictionary(int i, int i2, Context context) {
        return setDictionary(i, i2, null, null, context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean setDictionary(int r17, int r18, java.lang.String r19, java.lang.String r20, android.content.Context r21) {
        /*
        // Method dump skipped, instructions count: 187
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.co.omronsoft.iwnnime.ml.iwnn.iWnnEngine.setDictionary(int, int, java.lang.String, java.lang.String, android.content.Context):boolean");
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public void breakSequence() {
        this.mHasBroke = true;
    }

    private String getSegmentString(int i) {
        String segmentString = this.mCore.getSegmentString(i);
        if (segmentString == null) {
            return null;
        }
        return segmentString;
    }

    private String getSegmentStroke(int i) {
        String segmentStroke = this.mCore.getSegmentStroke(i);
        if (segmentStroke == null) {
            return null;
        }
        return segmentStroke;
    }

    private WnnWord getCandidate(int i) {
        String resultString = this.mCore.getResultString(this.mSegment, i);
        String resultStroke = this.mCore.getResultStroke(this.mSegment, i);
        int lengthInputCharacters = this.mCore.getLengthInputCharacters(i);
        int resultFrequency = this.mCore.getResultFrequency(this.mSegment, i);
        int resultCandidateType = this.mCore.getResultCandidateType(this.mSegment, i);
        int resultCharacterType = this.mCore.getResultCharacterType(this.mSegment, i);
        if (resultString == null || resultStroke == null) {
            return null;
        }
        int i2 = 0;
        boolean isLearnDictionary = this.mCore.isLearnDictionary(i);
        boolean isGijiDic = this.mCore.isGijiDic(i);
        if (isLearnDictionary) {
            i2 = 2;
        } else if (isGijiDic) {
            i2 = WNNWORD_ATTRIBUTE_NOT_DISPLAY_RUBY;
        }
        if (this.mCore.getCorrectDiff(i) != 0) {
            i2 |= WNNWORD_ATTRIBUTE_CORRECTION_WORD;
        }
        if (this.mEnableConvertedCandidate) {
            resultString = this.mLatinFilter.candidateConversion(resultString);
        }
        return new WnnWord(i, lengthInputCharacters, resultString, resultStroke, i2, resultFrequency, resultCandidateType, resultCharacterType);
    }

    private void clearCandidates() {
        this.mOutputNum = 0;
        this.mSearchKey = null;
        this.mForecastKey = null;
        this.mIsForbidDuplication = false;
        this.mCandTable.clear();
        this.mLatinFilter.clearLatinFilter();
    }

    public String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase(Locale.JAPANESE);
    }

    public String toLowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(Locale.JAPANESE);
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public void init(String str) {
        if (str != null) {
            this.mFilesDirPath = str;
        }
        this.mCore.init(this.mFilesDirPath);
        clearCandidates();
        this.mIsConverting = false;
        this.mMinLength = -1;
        this.mMaxLength = -1;
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public void close() {
        close(true);
    }

    public void close(boolean z) {
        this.mDictionarySet = -1;
        this.mConfType = -1;
        this.mCore.unmountDictionary(z);
        clearCandidates();
        this.mStatusDicMount.clear();
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public int predict(ComposingText composingText, int i, int i2) {
        this.mSearchComposingText = composingText;
        this.mCaseGijiList = null;
        clearCandidates();
        this.mSegment = 0;
        this.mSegmentCount = 0;
        this.mMinLength = i;
        this.mMaxLength = i2;
        this.mIsRequestGiji = i == 0;
        this.mIsConverting = false;
        this.mHasSearchWords = false;
        if (composingText == null) {
            return 0;
        }
        if (this.mEnableConvertedCandidate) {
            this.mLatinFilter.setSearchKey(composingText.toString(1));
        }
        String composingText2 = composingText.toString(1);
        if (composingText2 == null) {
            return 0;
        }
        if (i2 >= 0 && i2 < composingText2.length()) {
            composingText2 = composingText2.substring(0, composingText.getCursor(1));
        }
        this.mSearchKey = composingText2;
        if (!this.mAllowDuplicationCharPattern.matcher(composingText2).matches()) {
            this.mIsForbidDuplication = true;
        }
        String stripAlphabetsIfJP = stripAlphabetsIfJP(composingText2);
        this.mForecastKey = stripAlphabetsIfJP;
        return this.mCore.forecast(stripAlphabetsIfJP, i, i2, getEnableHeadConversion(), 1);
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public int convert(ComposingText composingText) {
        this.mSearchComposingText = composingText;
        this.mCaseGijiList = null;
        clearCandidates();
        this.mSegment = 0;
        this.mSegmentCount = 0;
        this.mIsRequestGiji = false;
        this.mIsConverting = true;
        this.mHasSearchWords = false;
        this.mMinLength = -1;
        this.mMaxLength = -1;
        if (composingText == null) {
            return 0;
        }
        if (this.mEnableConvertedCandidate) {
            this.mLatinFilter.setSearchKey(composingText.toString(1));
        }
        String composingText2 = composingText.toString(1);
        if (composingText2 == null) {
            return 0;
        }
        this.mSearchKey = composingText2;
        if (!this.mAllowDuplicationCharPattern.matcher(composingText2).matches()) {
            this.mIsForbidDuplication = true;
        }
        int length = composingText2.length();
        if (length > 0) {
            int i = length - 1;
            char charAt = composingText2.charAt(i);
            StringBuilder sb = new StringBuilder();
            if (charAt == 'n') {
                sb.append(composingText2.substring(0, i));
                sb.append(CHARACTER_CODE_JAPANESE_NN);
                composingText2 = sb.toString();
            }
        }
        int conv = this.mCore.conv(composingText2, 0);
        if (conv <= 0) {
            return 0;
        }
        StrSegment[] strSegmentArr = new StrSegment[conv];
        int i2 = 0;
        int i3 = 0;
        while (i2 < conv) {
            String segmentString = getSegmentString(i2);
            String segmentStroke = getSegmentStroke(i2);
            if (segmentString == null || segmentStroke == null) {
                return 0;
            }
            int length2 = segmentStroke.length() + i3;
            strSegmentArr[i2] = new StrSegment(segmentString, i3, length2 - 1);
            i2++;
            i3 = length2;
        }
        composingText.setCursor(2, composingText.size(2));
        composingText.replaceStrSegment(2, strSegmentArr, composingText.getCursor(2));
        this.mSegmentCount = conv;
        return conv;
    }

    public int searchWords(String str, int i, int i2) {
        this.mSearchCnt = 0;
        int searchWord = this.mCore.searchWord(i, i2, str);
        if (searchWord < 0) {
            Log.e(TAG, "iWnnEngine::searchWord() error. ret=" + searchWord);
        }
        this.mHasSearchWords = true;
        return searchWord;
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public int searchWords(String str) {
        int i = 1;
        int i2 = 0;
        if ("".equals(str)) {
            i2 = 1;
        } else {
            i = 0;
        }
        return searchWords(str, i, i2);
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public int clearPreviousCandidates() {
        int clearPreviousCandidates = this.mCore.clearPreviousCandidates();
        clearCandidates();
        this.mIsConverting = false;
        this.mMinLength = -1;
        this.mMaxLength = -1;
        return clearPreviousCandidates;
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public WnnWord getNextCandidate() {
        WnnWord nextCandidateInternal = getNextCandidateInternal();
        if (nextCandidateInternal != null) {
            this.mCandTable.put(nextCandidateInternal.candidate, nextCandidateInternal);
        }
        return nextCandidateInternal;
    }

    public WnnWord getNextCandidateInternal() {
        if (this.mHasSearchWords) {
            WnnWord word = getWord(this.mSearchCnt);
            if (word == null) {
                return word;
            }
            this.mSearchCnt++;
            return word;
        } else if (this.mSearchKey == null) {
            return null;
        } else {
            if (this.mCaseGijiList != null) {
                return createCaseGiji(null, false, false);
            }
            WnnWord wnnWord = null;
            for (int i = 0; i < 350 && (wnnWord = getCandidate(this.mOutputNum)) != null; i++) {
                boolean isGijiDic = this.mCore.isGijiDic(this.mOutputNum);
                this.mOutputNum++;
                if (!this.mIsForbidDuplication && !isGijiDic) {
                    if (!this.mEnableConvertedCandidate || this.mLatinFilter.putCandidate(wnnWord)) {
                        break;
                    }
                } else if (!this.mCandTable.containsKey(wnnWord.candidate)) {
                    break;
                } else {
                    WnnWord wnnWord2 = this.mCandTable.get(wnnWord.candidate);
                    if ((wnnWord2.attribute & 2) == 0) {
                        wnnWord2.attribute |= WNNWORD_ATTRIBUTE_NOT_DISPLAY_RUBY;
                    }
                }
            }
            if (wnnWord == null) {
                if (this.mEnableConvertedCandidate) {
                    if (this.mSearchKey.length() < 1) {
                        return null;
                    }
                    return createCaseGiji(this.mSearchKey, true, false);
                } else if (!this.mIsConverting || this.mKeyboardType != 1) {
                    String convertKanaRomaji = KanaRomajiConverter.convertKanaRomaji(this.mSearchComposingText.toString(0, 0, this.mSearchComposingText.getCursor(0) - 1));
                    if (GIJI_PATTERN.matcher(convertKanaRomaji).matches()) {
                        return createCaseGiji(convertKanaRomaji, true, true);
                    }
                }
            }
            return wnnWord;
        }
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public boolean learn(WnnWord wnnWord) {
        boolean z;
        boolean z2;
        int i = -1;
        boolean z3 = true;
        if (wnnWord != null) {
            try {
                int i2 = wnnWord.id;
                z2 = (wnnWord.attribute & 64) != 0;
                if (Pattern.compile(".*[0-9０-９].*").matcher(wnnWord.candidate).matches() && (this.mDictionarySet == 1 || (this.mCore.isGijiDic(wnnWord.id) && !wnnWord.candidate.equals(this.mSearchKey) && (wnnWord.attribute & 128) == 0))) {
                    z2 = true;
                }
                if ((wnnWord.attribute & 4) == 0) {
                    i = i2;
                } else if (isLowercaseStrokeInLearning()) {
                    wnnWord.attribute |= 256;
                } else if (!this.mCore.noConv(wnnWord.stroke)) {
                    return false;
                }
                if ((wnnWord.attribute & 128) == 0 && (wnnWord.attribute & 32) == 0) {
                    if ((wnnWord.attribute & 256) == 0) {
                        z = z2;
                    }
                }
                if (z2) {
                    this.mCore.init(this.mFilesDirPath);
                    this.mHasBroke = true;
                    return true;
                }
                if (this.mCore.addWord(wnnWord.stroke, wnnWord.candidate, wnnWord.lexicalCategory, 1, this.mHasBroke ? 0 : 1, 0) < 0 || this.mCore.forecast(wnnWord.stroke, 0, -1, getEnableHeadConversion(), 1) == 0) {
                    return false;
                }
                String resultString = this.mCore.getResultString(0, 0);
                int i3 = 0;
                while (true) {
                    if (resultString == null) {
                        break;
                    } else if (resultString.equals(wnnWord.candidate)) {
                        break;
                    } else {
                        i3++;
                        resultString = this.mCore.getResultString(0, i3);
                    }
                }
                z = true;
                i = i3;
            } catch (Exception e) {
                Log.e("IWnnIME", "iWnnEngine:learn " + e);
                return false;
            }
        } else {
            z = false;
            z2 = false;
        }
        if (this.mCore.select(this.mSegment, i, !z, this.mHasBroke) < 0) {
            z3 = false;
        }
        this.mHasBroke = z2;
        return z3;
    }

    public boolean learn(WnnWord wnnWord, boolean z) {
        if (!z) {
            this.mHasBroke = true;
        }
        return learn(wnnWord);
    }

    public boolean learn(boolean z, WnnWord wnnWord) {
        boolean z2;
        Exception e;
        if (wnnWord == null) {
            try {
                int select = this.mCore.select(this.mSegment, -1, z, this.mHasBroke);
                if (select < 0) {
                    Log.e(TAG, "iWnnEngine::learn(" + z + ") = " + select + "failure");
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (!z) {
                    try {
                        this.mHasBroke = true;
                        return z2;
                    } catch (Exception e2) {
                        e = e2;
                        Log.e("IWnnIME", "iWnnEngine::learn " + e);
                        return z2;
                    }
                } else {
                    this.mHasBroke = false;
                    return z2;
                }
            } catch (Exception e3) {
                e = e3;
                z2 = false;
                Log.e("IWnnIME", "iWnnEngine::learn " + e);
                return z2;
            }
        } else {
            this.mCore.select(this.mSegment, 0, false, true);
            this.mHasBroke = true;
            return true;
        }
    }

    private WnnWord getWord(int i) {
        String word = this.mCore.getWord(i, 0);
        String word2 = this.mCore.getWord(i, 1);
        if (word == null || word2 == null) {
            return null;
        }
        return new WnnWord(i, word2, word);
    }

    public int addWord(WnnWord wnnWord, int i, int i2, int i3) {
        if (wnnWord == null) {
            Log.e(TAG, "iWnnEngine::addWord() END parameter error. return = false");
            return -1;
        }
        int addWord = this.mCore.addWord(wnnWord.stroke, wnnWord.candidate, i, i2, i3, 0);
        if (addWord < 0) {
            Log.e(TAG, "iWnnEngine::addWord() error. ret=" + addWord);
        }
        return addWord;
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public int addWord(WnnWord wnnWord) {
        if (wnnWord == null) {
            Log.e(TAG, "iWnnEngine::addWord() END parameter error. return = false");
            return -1;
        }
        int i = 1;
        int i2 = (wnnWord.attribute & 32768) != 0 ? 1 : 0;
        if ((wnnWord.attribute & 8192) == 0) {
            i = 0;
        }
        return addWord(wnnWord, 0, i2, i);
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public boolean deleteWord(WnnWord wnnWord) {
        int i;
        if (wnnWord == null) {
            Log.e(TAG, "iWnnEngine::deleteWord() END parameter error. return = false");
            return false;
        }
        if ((wnnWord.attribute & 2) != 0) {
            i = this.mCore.deleteWord(wnnWord.id);
        } else {
            i = this.mCore.deleteSearchWord(wnnWord.id);
        }
        if (i < 0) {
            return false;
        }
        return true;
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public int makeCandidateListOf(int i) {
        WnnWord candidate;
        this.mSegment = i;
        this.mOutputNum = 0;
        this.mIsForbidDuplication = false;
        this.mCandTable.clear();
        this.mLatinFilter.clearLatinFilter();
        if (this.mSearchKey == null || (candidate = getCandidate(0)) == null) {
            return 0;
        }
        if (!this.mAllowDuplicationCharPattern.matcher(candidate.stroke).matches()) {
            this.mIsForbidDuplication = true;
        }
        return 1;
    }

    public boolean writeoutDictionary(int i, int i2, Context context) {
        int i3;
        int i4 = this.mConfType;
        int i5 = this.mDictionarySet;
        if (i2 == 10) {
            i3 = 3;
        } else if (i2 != 11) {
            return false;
        } else {
            i3 = 2;
        }
        setDictionary(i, 0, context);
        boolean writeoutDictionary = this.mCore.writeoutDictionary(i3);
        if (!writeoutDictionary) {
            Log.e(TAG, "iWnnEngine::writeoutDictionary() END failed error. return = false");
        }
        setDictionary(i4, i5, context);
        return writeoutDictionary;
    }

    public int setFlexibleCharset(int i, int i2) {
        if (i != 0 && 1 != i) {
            return 0;
        }
        if (i2 != 0 && 1 != i2) {
            return 0;
        }
        this.mKeyboardType = i2;
        int flexibleCharset = this.mCore.setFlexibleCharset(i, i2);
        this.mCore.init(this.mFilesDirPath);
        return flexibleCharset;
    }

    private String convertHalftoFull(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if ('!' <= charArray[i] && charArray[i] <= '~') {
                charArray[i] = (char) (charArray[i] + OFFSET_FULL_WIDTH);
            }
        }
        return new String(charArray);
    }

    private String convertFulltoHalf(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if (65281 <= charArray[i] && charArray[i] <= 65374) {
                charArray[i] = (char) (charArray[i] - OFFSET_FULL_WIDTH);
            }
        }
        return new String(charArray);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private jp.co.omronsoft.iwnnime.ml.WnnWord createCaseGiji(java.lang.String r8, boolean r9, boolean r10) {
        /*
        // Method dump skipped, instructions count: 150
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.co.omronsoft.iwnnime.ml.iwnn.iWnnEngine.createCaseGiji(java.lang.String, boolean, boolean):jp.co.omronsoft.iwnnime.ml.WnnWord");
    }

    public boolean initializeUserDictionary(int i, int i2) {
        return i2 == 10 && this.mCore.runInitialize(2, i, i2) != -1;
    }

    public boolean initializeLearnDictionary(int i) {
        return this.mCore.runInitialize(1, i, -1) != -1;
    }

    public boolean undo(int i) {
        return this.mCore.undo(i);
    }

    public boolean isGijiDic(int i) {
        return this.mCore.isGijiDic(i);
    }

    public void setConvertedCandidateEnabled(boolean z) {
        this.mEnableConvertedCandidate = z;
    }

    public int getDictionary() {
        return this.mDictionarySet;
    }

    public int getConfType() {
        return this.mConfType;
    }

    private String stripAlphabetsIfJP(String str) {
        return !Pattern.compile("^[a-zA-Z]*$").matcher(str).matches() ? Pattern.compile("[a-zA-Z]+$").matcher(str).replaceAll("") : str;
    }

    public String[] getConfTable() {
        return (String[]) CONF_TABLE.clone();
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public boolean isConverting() {
        return this.mIsConverting;
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public int convertGijiStr(ComposingText composingText, int i) {
        if (composingText == null) {
            return 0;
        }
        String composingText2 = composingText.toString(1);
        String composingText3 = composingText.toString(0);
        if (composingText2 == null || composingText3 == null) {
            return 0;
        }
        StrSegment[] strSegmentArr = {new StrSegment(getgijistr(composingText2, composingText3, i), 0, composingText2.length() - 1)};
        composingText.setCursor(2, composingText.size(2));
        composingText.replaceStrSegment(2, strSegmentArr, composingText.getCursor(2));
        this.mSegmentCount = 1;
        return this.mSegmentCount;
    }

    private String getgijistr(String str, String str2, int i) {
        switch (i) {
            case 2:
            case 3:
            case 4:
                return getGijiKanaStr(str, str2, i);
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
                return getGijiEijiStr(str2, i);
            case 11:
                return str2;
            default:
                return null;
        }
    }

    private String getGijiKanaStr(String str, String str2, int i) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int length2 = str2.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i2 + 1;
            String substring = str.substring(i2, i4);
            char charAt = substring.charAt(0);
            if ((isHiragana(charAt) ? this.mCore.getgijistr(substring, substring.length(), i) : 0) > 0) {
                substring = getSegmentString(0);
            } else if (isAlphabet(charAt)) {
                if (4 != i) {
                    substring = convertHalftoFull(substring);
                }
            } else if (4 == i) {
                while (true) {
                    if (i3 >= length2) {
                        break;
                    } else if (!isAlphabet(str2.charAt(i3))) {
                        substring = convertFulltoHalfKanaSymbol(charAt);
                        if (substring == null) {
                            substring = str2.substring(i3, i3 + 1);
                        }
                        i3++;
                    } else {
                        i3++;
                    }
                }
            }
            sb.append(substring);
            i2 = i4;
        }
        return sb.toString();
    }

    private String getGijiEijiStr(String str, int i) {
        if (str == null) {
            return null;
        }
        switch (i) {
            case 5:
                return convertFulltoHalf(Character.toString(toUpperCase(str).charAt(0)) + toLowerCase(str).substring(1));
            case 6:
                return convertHalftoFull(Character.toString(toUpperCase(str).charAt(0)) + toLowerCase(str).substring(1));
            case 7:
                return convertFulltoHalf(toUpperCase(str));
            case 8:
                return convertHalftoFull(toUpperCase(str));
            case 9:
                return convertFulltoHalf(toLowerCase(str));
            case 10:
                return convertHalftoFull(toLowerCase(str));
            case 11:
            default:
                return null;
            case 12:
                return convertHalftoFull(str);
        }
    }

    private String convertFulltoHalfKanaSymbol(char c) {
        if (c == 12289) {
            return Character.toString(65380);
        }
        if (c == 12290) {
            return Character.toString(65377);
        }
        if (c == 12300) {
            return Character.toString(65378);
        }
        if (c == 12301) {
            return Character.toString(65379);
        }
        if (c != 12539) {
            return null;
        }
        return Character.toString(65381);
    }

    public void setEnableHeadConversion(boolean z) {
        this.mEnableHeadConv = z;
    }

    private int getEnableHeadConversion() {
        return this.mEnableHeadConv ? 1 : 0;
    }

    public void setFilesDirPath(String str) {
        this.mFilesDirPath = str;
    }

    public boolean deleteDictionaryFile(String str) {
        return this.mCore.deleteDictionaryFile(str);
    }

    public String getForecastKey() {
        return this.mForecastKey;
    }

    @Override // jp.co.omronsoft.iwnnime.ml.WnnEngine
    public boolean hasCandidate() {
        if (this.mSearchKey == null || getCandidate(0) == null) {
            return false;
        }
        return true;
    }

    public int setCorrectionOptions(int i, String str, Context context) {
        if (context == null) {
            return 0;
        }
        AssetManager assetManager = null;
        if (str != null && str.startsWith(IWNNIME_KEY)) {
            str = str.substring(10);
            assetManager = context.getAssets();
        }
        return this.mCore.setCorrectionOptions(i, str, assetManager);
    }

    public int getCurrentMinimumLength() {
        return this.mMinLength;
    }

    public int getCurrentMaximunLength() {
        return this.mMaxLength;
    }
}
