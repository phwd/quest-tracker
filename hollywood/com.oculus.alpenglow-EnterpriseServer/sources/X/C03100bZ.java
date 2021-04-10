package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.graphql.query.GraphQlQueryParamSet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0bZ  reason: invalid class name and case insensitive filesystem */
public class C03100bZ<T> {
    public GraphQlQueryParamSet A00 = new GraphQlQueryParamSet();
    public final int A01;
    public final long A02;
    @Nullable
    public final Class<?> A03;
    public volatile long A04;
    public volatile boolean A05;
    public volatile boolean A06;

    public boolean A01() {
        return false;
    }

    @VisibleForTesting
    @Deprecated
    public final void A00(GraphQlQueryParamSet graphQlQueryParamSet) {
        if (graphQlQueryParamSet == null) {
            graphQlQueryParamSet = new GraphQlQueryParamSet();
        }
        this.A00 = graphQlQueryParamSet;
    }

    public C03100bZ(@Nullable Class cls, @Nullable int i, long j, long j2) {
        this.A04 = j2;
        this.A03 = cls;
        this.A01 = i;
        this.A02 = j;
    }
}
