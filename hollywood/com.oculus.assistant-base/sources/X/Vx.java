package X;

import android.database.Cursor;
import java.util.ArrayList;

public final class Vx extends AbstractC1403yh implements AbstractC0496aj {
    public final /* synthetic */ Cursor $cursor$inlined;
    public final /* synthetic */ KC this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Vx(KC kc, Cursor cursor) {
        super(0);
        this.this$0 = kc;
        this.$cursor$inlined = cursor;
    }

    @Override // X.AbstractC0496aj
    public final Object A3M() {
        KC kc = this.this$0;
        Cursor cursor = this.$cursor$inlined;
        C0962pe peVar = kc.A01;
        C0514bB.A02(cursor, "cursor");
        ArrayList arrayList = new ArrayList(cursor.getCount());
        while (cursor.moveToNext()) {
            arrayList.add(peVar.A00.A3N(cursor));
        }
        return arrayList;
    }
}
