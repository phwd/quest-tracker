package X;

/* renamed from: X.0BU  reason: invalid class name */
public enum AnonymousClass0BU {
    DEFAULT {
        @Override // X.AnonymousClass0BU
        public AnonymousClass0AU serialize(Long l) {
            return new AnonymousClass0XR(l);
        }
    },
    STRING {
        @Override // X.AnonymousClass0BU
        public AnonymousClass0AU serialize(Long l) {
            return new AnonymousClass0XR(String.valueOf(l));
        }
    };

    public abstract AnonymousClass0AU serialize(Long l);
}
