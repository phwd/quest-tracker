package X;

import android.content.Context;
import android.net.Uri;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.0w5  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07830w5 implements AnonymousClass0vW {
    @Nullable
    public AnonymousClass0LT A00;
    public final Context A01;
    public final C07710vp A02;
    public final AnonymousClass0v4 A03;
    public final Integer A04;
    public volatile String A05;
    public volatile String A06;

    public final boolean A01(String str) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        Set<String> unmodifiableSet;
        if (str != null) {
            if (!str.startsWith("https://")) {
                str = AnonymousClass006.A05("https://", str);
            }
            try {
                URI create = URI.create(str);
                Uri build = new Uri.Builder().scheme(create.getScheme()).encodedAuthority(create.getRawAuthority()).encodedPath(create.getRawPath()).encodedQuery(create.getRawQuery()).encodedFragment(create.getRawFragment()).build();
                String scheme = create.getScheme();
                String scheme2 = build.getScheme();
                if (scheme == null) {
                    z = false;
                    if (scheme2 == null) {
                        z = true;
                    }
                } else {
                    z = scheme.equals(scheme2);
                }
                String authority = create.getAuthority();
                String authority2 = build.getAuthority();
                if (authority == null) {
                    z2 = false;
                    if (authority2 == null) {
                        z2 = true;
                    }
                } else {
                    z2 = authority.equals(authority2);
                }
                String path = create.getPath();
                String path2 = build.getPath();
                if (path == null) {
                    z3 = false;
                    if (path2 == null) {
                        z3 = true;
                    }
                } else {
                    z3 = path.equals(path2);
                }
                String query = create.getQuery();
                String query2 = build.getQuery();
                if (query == null) {
                    z4 = false;
                    if (query2 == null) {
                        z4 = true;
                    }
                } else {
                    z4 = query.equals(query2);
                }
                String fragment = create.getFragment();
                String fragment2 = build.getFragment();
                if (fragment == null) {
                    z5 = false;
                    if (fragment2 == null) {
                        z5 = true;
                    }
                } else {
                    z5 = fragment.equals(fragment2);
                }
                String str2 = "";
                if (!z) {
                    str2 = AnonymousClass006.A05(str2, String.format(Locale.US, "javaUri scheme: \"%s\". androidUri scheme: \"%s\".", create.getScheme(), build.getScheme()));
                }
                if (!z2) {
                    str2 = AnonymousClass006.A05(str2, String.format(Locale.US, "javaUri authority: \"%s\". androidUri authority: \"%s\".", create.getAuthority(), build.getAuthority()));
                }
                if (!z3) {
                    str2 = AnonymousClass006.A05(str2, String.format(Locale.US, "javaUri path: \"%s\". androidUri path: \"%s\".", create.getPath(), build.getPath()));
                }
                if (!z4) {
                    str2 = AnonymousClass006.A05(str2, String.format(Locale.US, "javaUri query: \"%s\". androidUri query: \"%s\".", create.getQuery(), build.getQuery()));
                }
                if (!z5) {
                    str2 = AnonymousClass006.A05(str2, String.format(Locale.US, "javaUri fragment: \"%s\". androidUri fragment: \"%s\".", create.getFragment(), build.getFragment()));
                }
                if (!z || !z2 || !z3 || !z4 || !z5) {
                    throw new SecurityException(String.format(Locale.US, "java uri \"%s\" not equal to android uri \"%s\". Debug info: %s", create.toString(), build.toString(), str2));
                }
                if (!(this instanceof C07810w3)) {
                    unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("facebook.com", "workplace.com", "pushnotifs.com")));
                } else {
                    unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("facebook.com", "workplace.com")));
                }
                for (String str3 : unmodifiableSet) {
                    String host = build.getHost();
                    if (host != null) {
                        if (!host.equalsIgnoreCase(str3)) {
                            if (host.endsWith(AnonymousClass006.A05(".", str3))) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            } catch (SecurityException unused) {
                return false;
            }
        }
        return true;
    }

    public final void A00() {
        String str;
        AnonymousClass0LT r1 = this.A00;
        if (r1 != null) {
            try {
                this.A01.unregisterReceiver(r1);
            } catch (IllegalArgumentException e) {
                if (!(this instanceof C07740vv)) {
                    str = "WorkConnectionConfigOverrides";
                } else {
                    str = "ZeroRatingConnectionConfigOverrides";
                }
                AnonymousClass0NK.A0A(str, e, "Failed to unregister broadcast receiver");
            }
            this.A00 = null;
        }
    }

    public AbstractC07830w5(Context context, AnonymousClass0v4 r2, Integer num, @Nullable C07710vp r4) {
        this.A01 = context;
        this.A03 = r2;
        this.A04 = num;
        this.A02 = r4;
    }

    @Override // X.AnonymousClass0vW
    public final String A2v() {
        return this.A05;
    }

    @Override // X.AnonymousClass0vW
    public final String A45() {
        return this.A06;
    }
}
