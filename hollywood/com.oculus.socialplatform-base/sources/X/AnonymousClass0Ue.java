package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* renamed from: X.0Ue  reason: invalid class name */
public final class AnonymousClass0Ue {
    public static final List<AnonymousClass0Ug> A00;

    static {
        LinkedList linkedList = new LinkedList();
        for (AnonymousClass0UW r4 : new ArrayList(Arrays.asList(new AnonymousClass0UW("facebook.com", true, true), new AnonymousClass0UW("fbcdn.net", true, true), new AnonymousClass0UW("fbsbx.com", true, true), new AnonymousClass0UW("facebookcorewwwi.onion", true, true), new AnonymousClass0UW("fbcdn23dssr3jqnq.onion", true, true), new AnonymousClass0UW("fbsbx2q4mvcl63pw.onion", true, true), new AnonymousClass0UW("instagram.com", true, true), new AnonymousClass0UW("cdninstagram.com", true, true), new AnonymousClass0UW("workplace.com", true, true), new AnonymousClass0UW("oculus.com", true, true), new AnonymousClass0UW("facebook-program.com", true, true), new AnonymousClass0UW("viewpointsfromfacebook.com", true, true), new AnonymousClass0UW("facebookvirtualassistant.com", true, true), new AnonymousClass0UW("freebasics.com", true, true), new AnonymousClass0UW("internet.org", true, true), new AnonymousClass0UW("novi.com", true, true), new AnonymousClass0UW("h.facebook.com", false, false), new AnonymousClass0UW("l.facebook.com", false, false), new AnonymousClass0UW("lm.facebook.com", false, false), new AnonymousClass0UW("l.instagram.com", false, false), new AnonymousClass0UW("pinningtest.appspot.com", false, false), new AnonymousClass0UW("insecure.pinningtest.appspot.com", true, true), new AnonymousClass0UW("fbpinningtest.com", true, true)))) {
            AnonymousClass0Uf r3 = new AnonymousClass0Uf();
            r3.A00 = r4.A00.toLowerCase(Locale.US);
            r3.A02 = Boolean.valueOf(r4.A02).booleanValue();
            r3.A01 = Boolean.valueOf(r4.A01).booleanValue();
            linkedList.add(new AnonymousClass0Ug(r3));
        }
        A00 = linkedList;
    }
}
