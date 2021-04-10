package android.icu.impl;

import android.support.v4.os.EnvironmentCompat;

public enum CalType {
    GREGORIAN("gregorian"),
    ISO8601("iso8601"),
    BUDDHIST("buddhist"),
    CHINESE("chinese"),
    COPTIC("coptic"),
    DANGI("dangi"),
    ETHIOPIC("ethiopic"),
    ETHIOPIC_AMETE_ALEM("ethiopic-amete-alem"),
    HEBREW("hebrew"),
    INDIAN("indian"),
    ISLAMIC("islamic"),
    ISLAMIC_CIVIL("islamic-civil"),
    ISLAMIC_RGSA("islamic-rgsa"),
    ISLAMIC_TBLA("islamic-tbla"),
    ISLAMIC_UMALQURA("islamic-umalqura"),
    JAPANESE("japanese"),
    PERSIAN("persian"),
    ROC("roc"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);
    
    String id;

    private CalType(String id2) {
        this.id = id2;
    }

    public String getId() {
        return this.id;
    }
}
