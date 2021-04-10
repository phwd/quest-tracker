package com.oculus.devicelocale;

import com.oculus.common.devicelocale.R;

public enum DeviceLocale {
    CS_CZ("cs_CZ", R.string.device_language_cs_CZ),
    DA_DK("da_DK", R.string.device_language_da_DK),
    DE_DE("de_DE", R.string.device_language_de_DE),
    EL_GR("el_GR", R.string.device_language_el_GR),
    EN_GB("en_GB", R.string.device_language_en_GB),
    EN_US("en_US", R.string.device_language_en_US),
    ES_ES("es_ES", R.string.device_language_es_ES),
    ES_LA("es_LA", R.string.device_language_es_LA),
    FI_FI("fi_FI", R.string.device_language_fi_FI),
    FR_FR("fr_FR", R.string.device_language_fr_FR),
    IT_IT("it_IT", R.string.device_language_it_IT),
    JA_JP("ja_JP", R.string.device_language_ja_JP),
    KO_KR("ko_KR", R.string.device_language_ko_KR),
    NB_NO("nb_NO", R.string.device_language_nb_NO),
    NL_NL("nl_NL", R.string.device_language_nl_NL),
    PL_PL("pl_PL", R.string.device_language_pl_PL),
    PT_BR("pt_BR", R.string.device_language_pt_BR),
    PT_PT("pt_PT", R.string.device_language_pt_PT),
    RO_RO("ro_RO", R.string.device_language_ro_RO),
    RU_RU("ru_RU", R.string.device_language_ru_RU),
    SV_SE("sv_SE", R.string.device_language_sv_SE),
    TR_TR("tr_TR", R.string.device_language_tr_TR),
    ZH_CN("zh_CN", R.string.device_language_zh_CN),
    ZH_HK("zh_HK", R.string.device_language_zh_HK),
    ZH_TW("zh_TW", R.string.device_language_zh_TW);
    
    private int mDisplayNameId;
    private String mLocale;

    private DeviceLocale(String str, int i) {
        this.mLocale = str;
        this.mDisplayNameId = i;
    }

    public String getLocaleName() {
        return this.mLocale;
    }

    public int getDisplayNameId() {
        return this.mDisplayNameId;
    }
}
