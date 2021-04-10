package org.webrtc;

public class SessionDescription {
    public final String description;
    public final Type type;

    public enum Type {
        OFFER,
        PRANSWER,
        ANSWER;

        public static Type fromCanonicalForm(String str) {
            return (Type) valueOf(Type.class, str.toUpperCase());
        }

        public String canonicalForm() {
            return name().toLowerCase();
        }
    }

    public SessionDescription(Type type2, String str) {
        this.type = type2;
        this.description = str;
    }
}
