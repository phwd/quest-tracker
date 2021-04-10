package X;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class LV extends hh<Date> {
    public static final AbstractC0237hg A01 = new LW();
    public final List<DateFormat> A00;

    public LV() {
        ArrayList arrayList = new ArrayList();
        this.A00 = arrayList;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            this.A00.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (hU.A00 >= 9) {
            this.A00.add(new SimpleDateFormat(AnonymousClass06.A04("MMM d, yyyy", " ", "h:mm:ss a"), Locale.US));
        }
    }
}
