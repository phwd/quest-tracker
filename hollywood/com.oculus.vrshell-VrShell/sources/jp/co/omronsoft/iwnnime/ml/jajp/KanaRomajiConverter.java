package jp.co.omronsoft.iwnnime.ml.jajp;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class KanaRomajiConverter {
    private static final Pattern HIRAGANA_PATTERN = Pattern.compile("^[ぁ-ゔヴー]+$");
    private static final int IWNN_ENGINE_MAX_LEARNING_LEN = 50;
    private static final HashMap<String, String> KANA_ROMAJI_TABLE = new HashMap<String, String>() {
        /* class jp.co.omronsoft.iwnnime.ml.jajp.KanaRomajiConverter.AnonymousClass1 */

        {
            put("あ", "a");
            put("い", "i");
            put("う", "u");
            put("え", "e");
            put("お", "o");
            put("か", "ka");
            put("き", "ki");
            put("く", "ku");
            put("け", "ke");
            put("こ", "ko");
            put("さ", "sa");
            put("し", "shi");
            put("す", "su");
            put("せ", "se");
            put("そ", "so");
            put("た", "ta");
            put("ち", "chi");
            put("つ", "tsu");
            put("て", "te");
            put("と", "to");
            put("な", "na");
            put("に", "ni");
            put("ぬ", "nu");
            put("ね", "ne");
            put("の", "no");
            put("は", "ha");
            put("ひ", "hi");
            put("ふ", "fu");
            put("へ", "he");
            put("ほ", "ho");
            put("ま", "ma");
            put("み", "mi");
            put("む", "mu");
            put("め", "me");
            put("も", "mo");
            put("や", "ya");
            put("ゆ", "yu");
            put("よ", "yo");
            put("ら", "ra");
            put("り", "ri");
            put("る", "ru");
            put("れ", "re");
            put("ろ", "ro");
            put("わ", "wa");
            put("を", "o");
            put("が", "ga");
            put("ぎ", "gi");
            put("ぐ", "gu");
            put("げ", "ge");
            put("ご", "go");
            put("ざ", "za");
            put("じ", "ji");
            put("ず", "zu");
            put("ぜ", "ze");
            put("ぞ", "zo");
            put("だ", "da");
            put("ぢ", "ji");
            put("づ", "zu");
            put("で", "de");
            put("ど", "do");
            put("ば", "ba");
            put("び", "bi");
            put("ぶ", "bu");
            put("べ", "be");
            put("ぼ", "bo");
            put("ぱ", "pa");
            put("ぴ", "pi");
            put("ぷ", "pu");
            put("ぺ", "pe");
            put("ぽ", "po");
            put("きゃ", "kya");
            put("きゅ", "kyu");
            put("きょ", "kyo");
            put("しゃ", "sha");
            put("しゅ", "shu");
            put("しょ", "sho");
            put("ちゃ", "cha");
            put("ちゅ", "chu");
            put("ちょ", "cho");
            put("にゃ", "nya");
            put("にゅ", "nyu");
            put("にょ", "nyo");
            put("ひゃ", "hya");
            put("ひゅ", "hyu");
            put("ひょ", "hyo");
            put("みゃ", "mya");
            put("みゅ", "myu");
            put("みょ", "myo");
            put("りゃ", "rya");
            put("りゅ", "ryu");
            put("りょ", "ryo");
            put("ぎゃ", "gya");
            put("ぎゅ", "gyu");
            put("ぎょ", "gyo");
            put("じゃ", "ja");
            put("じゅ", "ju");
            put("じょ", "jo");
            put("びゃ", "bya");
            put("びゅ", "byu");
            put("びょ", "byo");
            put("ぴゃ", "pya");
            put("ぴゅ", "pyu");
            put("ぴょ", "pyo");
            put("ん", "n");
            put("っか", "kka");
            put("っき", "kki");
            put("っく", "kku");
            put("っけ", "kke");
            put("っこ", "kko");
            put("っさ", "ssa");
            put("っし", "sshi");
            put("っす", "ssu");
            put("っせ", "sse");
            put("っそ", "sso");
            put("った", "tta");
            put("っち", "tchi");
            put("っつ", "ttsu");
            put("って", "tte");
            put("っと", "tto");
            put("っな", "nna");
            put("っに", "nni");
            put("っぬ", "nnu");
            put("っね", "nne");
            put("っの", "nno");
            put("っは", "hha");
            put("っひ", "hhi");
            put("っふ", "ffu");
            put("っへ", "hhe");
            put("っほ", "hho");
            put("っま", "mma");
            put("っみ", "mmi");
            put("っむ", "mmu");
            put("っめ", "mme");
            put("っも", "mmo");
            put("っや", "yya");
            put("っゆ", "yyu");
            put("っよ", "yyo");
            put("っら", "rra");
            put("っり", "rri");
            put("っる", "rru");
            put("っれ", "rre");
            put("っろ", "rro");
            put("っわ", "wwa");
            put("っが", "gga");
            put("っぎ", "ggi");
            put("っぐ", "ggu");
            put("っげ", "gge");
            put("っご", "ggo");
            put("っざ", "zza");
            put("っじ", "jji");
            put("っず", "zzu");
            put("っぜ", "zze");
            put("っぞ", "zzo");
            put("っだ", "dda");
            put("っぢ", "jji");
            put("っづ", "zzu");
            put("っで", "dde");
            put("っど", "ddo");
            put("っば", "bba");
            put("っび", "bbi");
            put("っぶ", "bbu");
            put("っべ", "bbe");
            put("っぼ", "bbo");
            put("っぱ", "ppa");
            put("っぴ", "ppi");
            put("っぷ", "ppu");
            put("っぺ", "ppe");
            put("っぽ", "ppo");
            put("っきゃ", "kkya");
            put("っきゅ", "kkyu");
            put("っきょ", "kkyo");
            put("っしゃ", "ssha");
            put("っしゅ", "sshu");
            put("っしょ", "ssho");
            put("っちゃ", "tcha");
            put("っちゅ", "tchu");
            put("っちょ", "tcho");
            put("っにゃ", "nnya");
            put("っにゅ", "nnyu");
            put("っにょ", "nnyo");
            put("っひゃ", "hhya");
            put("っひゅ", "hhyu");
            put("っひょ", "hhyo");
            put("っみゃ", "mmya");
            put("っみゅ", "mmyu");
            put("っみょ", "mmyo");
            put("っりゃ", "rrya");
            put("っりゅ", "rryu");
            put("っりょ", "rryo");
            put("っぎゃ", "ggya");
            put("っぎゅ", "ggyu");
            put("っぎょ", "ggyo");
            put("っじゃ", "jja");
            put("っじゅ", "jju");
            put("っじょ", "jjo");
            put("っびゃ", "bbya");
            put("っびゅ", "bbyu");
            put("っびょ", "bbyo");
            put("っぴゃ", "ppya");
            put("っぴゅ", "ppyu");
            put("っぴょ", "ppyo");
            put("ー", "-");
            put("ヴぁ", "va");
            put("ヴぃ", "vi");
            put("ヴ", "vu");
            put("ヴぇ", "ve");
            put("ヴぉ", "vo");
        }
    };
    private static final int MAX_LENGTH = 3;
    private static final LinkedHashMap<String, String> SPECIAL_CONVERSION_RULES = new LinkedHashMap<String, String>() {
        /* class jp.co.omronsoft.iwnnime.ml.jajp.KanaRomajiConverter.AnonymousClass2 */

        {
            put("nb", "mb");
            put("nm", "mm");
            put("np", "mp");
            put("oo", "o");
            put("ou", "o");
            put("uu", "u");
        }
    };

    public static String convertKanaRomaji(String str) {
        if (!HIRAGANA_PATTERN.matcher(str).matches()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < length; i += str3.length()) {
            int min = Math.min(length - i, 3);
            while (true) {
                if (min <= 0) {
                    break;
                }
                str3 = str.substring(i, i + min);
                str2 = KANA_ROMAJI_TABLE.get(str3);
                if (str2 != null) {
                    sb.append(str2);
                    break;
                }
                min--;
            }
            if (str2 == null || sb.length() > IWNN_ENGINE_MAX_LEARNING_LEN) {
                return str;
            }
        }
        String sb2 = sb.toString();
        for (String str4 : SPECIAL_CONVERSION_RULES.keySet()) {
            if (sb2.contains(str4)) {
                sb2 = sb2.replace(str4, SPECIAL_CONVERSION_RULES.get(str4));
            }
        }
        return sb2;
    }
}
