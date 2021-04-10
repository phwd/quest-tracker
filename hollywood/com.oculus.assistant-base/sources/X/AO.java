package X;

import com.facebook.assistant.oacr.OacrConstants;

public interface AO {
    public static final D6[] A00 = {new D6("manual_composite_key", "TEXT", null, false, true, false, false, false, null, null, null, null), new D6("user_id", "TEXT", null, false, false, false, false, false, null, null, null, null), new D6("persona_id", "TEXT", null, false, false, false, false, false, null, null, null, null), new D6("locale_id", "TEXT", null, false, false, false, false, false, null, null, null, null), new D6("manual_composite_foreign_key", "TEXT", null, false, false, false, false, false, "personas", "manual_composite_key", "NO ACTION", "CASCADE")};
    public static final D7[] A01 = {new D7(false, new String[]{"user_id"}, new String[]{OacrConstants.AUTO_SPEECH_DOMAIN}), new D7(false, new String[]{"user_id", "locale_id"}, new String[]{OacrConstants.AUTO_SPEECH_DOMAIN, OacrConstants.AUTO_SPEECH_DOMAIN}), new D7(false, new String[]{"manual_composite_foreign_key"}, new String[]{OacrConstants.AUTO_SPEECH_DOMAIN})};
}
