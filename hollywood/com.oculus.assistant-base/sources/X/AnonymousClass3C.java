package X;

/* renamed from: X.3C  reason: invalid class name */
public class AnonymousClass3C extends AnonymousClass1e implements AbstractC0513bA, bI {
    public final int arity;
    public final int flags = 0;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof AnonymousClass3C)) {
                return false;
            }
            AnonymousClass3C r4 = (AnonymousClass3C) obj;
            if (!C0514bB.A05(A00(), r4.A00()) || !this.name.equals(r4.name) || !this.signature.equals(r4.signature) || this.flags != r4.flags || this.arity != r4.arity || !C0514bB.A05(this.receiver, r4.receiver)) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        bI bIVar = this.A00;
        if (bIVar == null) {
            bIVar = this;
            this.A00 = this;
        }
        if (bIVar != this) {
            return bIVar.toString();
        }
        String str = this.name;
        if ("<init>".equals(str)) {
            return "constructor (Kotlin reflection is not available)";
        }
        return AnonymousClass08.A05("function ", str, " (Kotlin reflection is not available)");
    }

    public AnonymousClass3C(int i, Object obj, Class cls, String str, String str2) {
        super(obj, cls, str, str2);
        this.arity = i;
    }

    public final int hashCode() {
        int hashCode;
        if (A00() == null) {
            hashCode = 0;
        } else {
            hashCode = A00().hashCode() * 31;
        }
        return ((hashCode + this.name.hashCode()) * 31) + this.signature.hashCode();
    }
}
