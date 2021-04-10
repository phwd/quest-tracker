package X;

public final class AG {
    public static final AF A02 = new AF();
    public final String A00;
    public final String A01;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AG)) {
            return false;
        }
        AG ag = (AG) obj;
        return C0514bB.A05(this.A01, ag.A01) && C0514bB.A05(this.A00, ag.A00);
    }

    public final int hashCode() {
        String str = this.A01;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.A00;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        return AnonymousClass08.A07("AssistantTtsVoiceSelection(personaId=", this.A01, ", locale=", this.A00, ")");
    }

    public AG(String str, String str2) {
        C0514bB.A02(str, "personaId");
        C0514bB.A02(str2, "locale");
        this.A01 = str;
        this.A00 = str2;
    }
}
