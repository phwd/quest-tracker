package X;

import com.facebook.annotations.OkToExtend;
import com.facebook.graphql.query.GraphQlQueryParamSet;
import java.util.ArrayList;

@OkToExtend
/* renamed from: X.0bg  reason: invalid class name */
public class AnonymousClass0bg<T> implements AnonymousClass0P7<T> {
    public C03960dj<String, String> A00 = new C03960dj<>();
    public ArrayList<String> A01 = new ArrayList<>();
    public final C03100bZ<T> A02;

    public static <T> AnonymousClass0bg<T> A00(C03100bZ<T> r4) {
        if (!(r4 instanceof C01790Lq)) {
            return new AnonymousClass0bg<>(r4);
        }
        throw new IllegalArgumentException(AnonymousClass006.A09("Trying to create a ", "BaseGraphQLRequest", " from a ", "TypedGraphQLMutationString", ". Use createMutationRequest() instead."));
    }

    /* renamed from: A01 */
    public C03100bZ<T> A4J() {
        return this.A02;
    }

    public AnonymousClass0bg(C03100bZ<T> r2) {
        if (r2 != null) {
            this.A02 = r2;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass0P7
    public final GraphQlQueryParamSet A4K() {
        return A4J().A00;
    }
}
