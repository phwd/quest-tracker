package com.oculus.panelapp.keyboardv2;

public enum KeyboardLocale {
    UNKNOWN,
    DE_DE,
    EN_US,
    ES_ES,
    FR_FR,
    KO_KR,
    IT_IT,
    JA_JP,
    JA_JP_ROMAJI,
    NL_NL,
    PT_BR,
    RU_RU,
    TR_TR;

    public static KeyboardLocale getKnownLanguages(String str) {
        if (str.toUpperCase().startsWith("EN")) {
            return EN_US;
        }
        if (str.toUpperCase().startsWith("KO")) {
            return KO_KR;
        }
        if (str.toUpperCase().startsWith("DE")) {
            return DE_DE;
        }
        if (str.toUpperCase().startsWith("FR")) {
            return FR_FR;
        }
        if (str.toUpperCase().startsWith("IT")) {
            return IT_IT;
        }
        if (str.toUpperCase().startsWith("ES")) {
            return ES_ES;
        }
        if (str.toUpperCase().startsWith("PT")) {
            return PT_BR;
        }
        if (str.toUpperCase().startsWith("NL")) {
            return NL_NL;
        }
        if (str.toUpperCase().startsWith("TR")) {
            return TR_TR;
        }
        if (str.toUpperCase().startsWith("RU")) {
            return RU_RU;
        }
        if (str.equalsIgnoreCase("JA_JP_ROMAJI")) {
            return JA_JP_ROMAJI;
        }
        if (str.toUpperCase().startsWith("JA")) {
            return JA_JP;
        }
        return UNKNOWN;
    }
}
