package defpackage;

import org.chromium.components.omnibox.AutocompleteSchemeClassifier;

/* renamed from: bv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1922bv0 {

    /* renamed from: a  reason: collision with root package name */
    public final AutocompleteSchemeClassifier f9572a;
    public final AbstractC3292jw1 b;
    public final boolean c;
    public final boolean d;
    public int e = 1;
    public int f = 1;
    public boolean g = false;
    public String h = null;

    public AbstractC1922bv0(AutocompleteSchemeClassifier autocompleteSchemeClassifier, AbstractC3292jw1 jw1, boolean z, boolean z2) {
        this.f9572a = autocompleteSchemeClassifier;
        this.b = jw1;
        this.c = z;
        this.d = z2;
    }

    public abstract KS a();

    public abstract String b();

    public boolean c() {
        return this.f != 1 && !e();
    }

    public abstract boolean d();

    public boolean e() {
        return this.e != 1;
    }
}
