package defpackage;

import android.database.AbstractCursor;
import android.database.Cursor;
import com.oculus.browser.R;
import com.oculus.os.Version;

/* renamed from: ar  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1738ar extends AbstractCursor {
    public static final String[] F = {"_id", "suggest_intent_action", "suggest_intent_data", "suggest_text_1", "suggest_text_2", "suggest_text_2_url", "suggest_icon_1", "suggest_last_access_hint"};
    public final Cursor G;

    public C1738ar(Cursor cursor) {
        this.G = cursor;
    }

    public String[] getColumnNames() {
        return F;
    }

    public int getCount() {
        return this.G.getCount();
    }

    public double getDouble(int i) {
        throw new UnsupportedOperationException();
    }

    public float getFloat(int i) {
        throw new UnsupportedOperationException();
    }

    public int getInt(int i) {
        throw new UnsupportedOperationException();
    }

    public long getLong(int i) {
        if (i == 7) {
            Cursor cursor = this.G;
            long j = cursor.getLong(cursor.getColumnIndex("date"));
            if (j < 0) {
                return 0;
            }
            return j;
        }
        throw new UnsupportedOperationException();
    }

    public short getShort(int i) {
        throw new UnsupportedOperationException();
    }

    public String getString(int i) {
        switch (i) {
            case 0:
                Cursor cursor = this.G;
                return cursor.getString(cursor.getColumnIndex("_id"));
            case 1:
                return "android.intent.action.VIEW";
            case 2:
                Cursor cursor2 = this.G;
                return cursor2.getString(cursor2.getColumnIndex("url"));
            case 3:
                Cursor cursor3 = this.G;
                return cursor3.getString(cursor3.getColumnIndex("title"));
            case 4:
            case 5:
                Cursor cursor4 = this.G;
                return cursor4.getString(cursor4.getColumnIndex("url"));
            case 6:
                return Integer.toString(R.mipmap.app_icon);
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                Cursor cursor5 = this.G;
                long j = cursor5.getLong(cursor5.getColumnIndex("date"));
                if (j < 0) {
                    return "0";
                }
                return "" + j;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public boolean isNull(int i) {
        return this.G.isNull(i);
    }

    public boolean onMove(int i, int i2) {
        return this.G.moveToPosition(i2);
    }
}
