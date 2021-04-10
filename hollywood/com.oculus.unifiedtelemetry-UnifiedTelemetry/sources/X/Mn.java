package X;

import javax.annotation.Nullable;

public enum Mn {
    FBINTERNAL("FBINTERNAL_URL_SCHEME"),
    FB("FB_URL_SCHEME"),
    FB_SERVICE("FB_SERVICE_URL_SCHEME"),
    GAMES("GAMES_URL_SCHEME"),
    INSTAGRAM("INSTAGRAM_URL_SCHEME"),
    MESSENGER("MESSENGER_URL_SCHEME"),
    MESSENGER_SAMETASK("MESSENGER_SAMETASK_URL_SCHEME"),
    MESSENGER_SECURE("MESSENGER_SECURE_URL_SCHEME"),
    MLITE("MLITE_URL_SCHEME"),
    MLITE_SECURE("MLITE_SECURE_URL_SCHEME"),
    PORTAL("PORTAL_URL_SCHEME"),
    TALK("TALK_URL_SCHEME"),
    CRS("CRS_URL_SCHEMA"),
    BIZAPP_INTERNAL("BIZAPP_INTERNAL_URL_SCHEME"),
    CSMOBILE("CSMOBILE_URL_SCHEME"),
    HTTP("HTTP_SCHEME"),
    HTTPS("HTTPS_SCHEME"),
    DIALTONE("DIALTONE_URL_SCHEME"),
    NONE(null);
    
    @Nullable
    public final String buildConstantName;

    public static Mn decode(String str) {
        if (str.startsWith("$")) {
            return valueOf(str.substring(1));
        }
        return NONE;
    }

    public String encode() {
        return AnonymousClass06.A04("$", super.toString());
    }

    public String getBuildConstant() {
        String str = this.buildConstantName;
        if (str != null) {
            try {
                return (String) IK.class.getField(str).get(this);
            } catch (Exception e) {
                throw new RuntimeException(String.format("Could not access %s.%s", "BuildConstants", this.buildConstantName), e);
            }
        } else {
            throw new IllegalStateException(String.format("Scheme %s does not have a buildConstantName", this));
        }
    }

    public boolean hasBuildConstant() {
        if (this.buildConstantName != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    Mn(@Nullable String str) {
        this.buildConstantName = str;
    }
}
