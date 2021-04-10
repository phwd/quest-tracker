package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;
import org.apache.commons.cli.HelpFormatter;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.235  reason: invalid class name */
public final class AnonymousClass235 extends C143522x {
    public AnonymousClass235(AnonymousClass23P r2, C145023o r3) {
        super(r2, r3, null);
    }

    @Override // X.C143522x
    public final /* bridge */ /* synthetic */ Object A01() {
        return super.A01();
    }

    public final C145023o A02() {
        return (C145023o) super.A01();
    }

    @Override // X.C143522x
    public final String toString() {
        return AnonymousClass006.A09(super.toString(), HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, super.A01().toString());
    }
}
