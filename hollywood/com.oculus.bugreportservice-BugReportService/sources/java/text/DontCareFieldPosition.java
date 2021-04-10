package java.text;

import java.text.Format;

/* access modifiers changed from: package-private */
public class DontCareFieldPosition extends FieldPosition {
    static final FieldPosition INSTANCE = new DontCareFieldPosition();
    private final Format.FieldDelegate noDelegate = new Format.FieldDelegate() {
        /* class java.text.DontCareFieldPosition.AnonymousClass1 */

        @Override // java.text.Format.FieldDelegate
        public void formatted(int i, Format.Field field, Object obj, int i2, int i3, StringBuffer stringBuffer) {
        }
    };

    private DontCareFieldPosition() {
        super(0);
    }

    /* access modifiers changed from: package-private */
    @Override // java.text.FieldPosition
    public Format.FieldDelegate getFieldDelegate() {
        return this.noDelegate;
    }
}
