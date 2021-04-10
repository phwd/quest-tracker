package android.support.v4.print;

import android.content.Context;
import android.print.PrintAttributes;

class PrintHelperApi23 extends PrintHelperApi20 {
    /* access modifiers changed from: protected */
    @Override // android.support.v4.print.PrintHelperKitkat
    public PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
        PrintAttributes.Builder copyAttributes = super.copyAttributes(printAttributes);
        if (printAttributes.getDuplexMode() != 0) {
            copyAttributes.setDuplexMode(printAttributes.getDuplexMode());
        }
        return copyAttributes;
    }

    PrintHelperApi23(Context context) {
        super(context);
        this.mIsMinMarginsHandlingCorrect = false;
    }
}
