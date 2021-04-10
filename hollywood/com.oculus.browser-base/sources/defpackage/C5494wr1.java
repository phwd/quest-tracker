package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.Callback;

/* renamed from: wr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5494wr1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f11575a;

    public C5494wr1(Callback callback) {
        this.f11575a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f11575a.onResult(new ArrayList(Arrays.asList((String[]) obj)));
    }
}
