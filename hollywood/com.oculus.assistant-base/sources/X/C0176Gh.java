package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* renamed from: X.Gh  reason: case insensitive filesystem */
public final class C0176Gh {
    public static final List A00;

    static {
        LinkedList linkedList = new LinkedList();
        for (C0170Ga ga : new ArrayList(Arrays.asList(new C0170Ga("facebook.com", true, true), new C0170Ga("fbcdn.net", true, true), new C0170Ga("fbsbx.com", true, true), new C0170Ga("facebookcorewwwi.onion", true, true), new C0170Ga("fbcdn23dssr3jqnq.onion", true, true), new C0170Ga("fbsbx2q4mvcl63pw.onion", true, true), new C0170Ga("instagram.com", true, true), new C0170Ga("cdninstagram.com", true, true), new C0170Ga("workplace.com", true, true), new C0170Ga("oculus.com", true, true), new C0170Ga("facebook-program.com", true, true), new C0170Ga("viewpointsfromfacebook.com", true, true), new C0170Ga("facebookvirtualassistant.com", true, true), new C0170Ga("freebasics.com", true, true), new C0170Ga("internet.org", true, true), new C0170Ga("novi.com", true, true), new C0170Ga("h.facebook.com", false, false), new C0170Ga("l.facebook.com", false, false), new C0170Ga("lm.facebook.com", false, false), new C0170Ga("l.instagram.com", false, false), new C0170Ga("pinningtest.appspot.com", false, false), new C0170Ga("insecure.pinningtest.appspot.com", true, true), new C0170Ga("fbpinningtest.com", true, true)))) {
            C0177Gi gi = new C0177Gi();
            gi.A00 = ga.A00.toLowerCase(Locale.US);
            gi.A02 = Boolean.valueOf(ga.A02).booleanValue();
            gi.A01 = Boolean.valueOf(ga.A01).booleanValue();
            linkedList.add(new C0178Gj(gi));
        }
        A00 = linkedList;
    }
}
