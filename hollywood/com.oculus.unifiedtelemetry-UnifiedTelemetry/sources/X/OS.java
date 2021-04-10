package X;

public enum OS {
    DEFAULT {
        @Override // X.OS
        public M4 serialize(Long l) {
            return new U1(l);
        }
    },
    STRING {
        @Override // X.OS
        public M4 serialize(Long l) {
            return new U1(String.valueOf(l));
        }
    };

    public abstract M4 serialize(Long l);
}
