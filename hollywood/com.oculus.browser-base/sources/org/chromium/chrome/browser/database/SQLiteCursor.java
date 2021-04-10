package org.chromium.chrome.browser.database;

import J.N;
import android.database.AbstractCursor;
import android.database.CursorWindow;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SQLiteCursor extends AbstractCursor {
    public long F;
    public int G = -1;
    public int[] H;
    public final Object I = new Object();

    /* renamed from: J  reason: collision with root package name */
    public final Object f10649J = new Object();
    public final Object K = new Object();
    public final Object L = new Object();

    public SQLiteCursor(long j) {
        this.F = j;
    }

    public static SQLiteCursor create(long j) {
        return new SQLiteCursor(j);
    }

    public final boolean Y(CursorWindow cursorWindow, Object obj, int i, int i2) {
        boolean z;
        if (obj == null) {
            z = cursorWindow.putNull(i, i2);
        } else if (obj instanceof Long) {
            z = cursorWindow.putLong(((Long) obj).longValue(), i, i2);
        } else if (obj instanceof String) {
            z = cursorWindow.putString((String) obj, i, i2);
        } else {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (bArr.length > 0) {
                    z = cursorWindow.putBlob(bArr, i, i2);
                }
            }
            if (obj instanceof Double) {
                z = cursorWindow.putDouble(((Double) obj).doubleValue(), i, i2);
            } else {
                z = cursorWindow.putNull(i, i2);
            }
        }
        if (z) {
            return true;
        }
        cursorWindow.freeLastRow();
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        synchronized (this.f10649J) {
            long j = this.F;
            if (j != 0) {
                N.MMXVRMHD(j, this);
                this.F = 0;
            }
        }
    }

    public final int f0(int i) {
        synchronized (this.I) {
            if (this.H == null) {
                int columnCount = getColumnCount();
                this.H = new int[columnCount];
                for (int i2 = 0; i2 < columnCount; i2++) {
                    this.H[i2] = N.Mj53Fcpg(this.F, this, i2);
                }
            }
        }
        return this.H[i];
    }

    public void fillWindow(int i, CursorWindow cursorWindow) {
        boolean z;
        if (i >= 0 && i <= getCount()) {
            cursorWindow.acquireReference();
            try {
                int position = getPosition();
                moveToPosition(i - 1);
                cursorWindow.clear();
                cursorWindow.setStartPosition(i);
                int columnCount = getColumnCount();
                cursorWindow.setNumColumns(columnCount);
                while (moveToNext() && cursorWindow.allocRow()) {
                    int position2 = getPosition();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= columnCount) {
                            break;
                        }
                        int f0 = f0(i2);
                        if (f0 == -1) {
                            z = Y(cursorWindow, getString(i2), position2, i2);
                        } else if (f0 == 0) {
                            z = Y(cursorWindow, null, position2, i2);
                        } else if (f0 == 2) {
                            z = Y(cursorWindow, Long.valueOf(getLong(i2)), position2, i2);
                        } else if (f0 == 8) {
                            z = Y(cursorWindow, Double.valueOf(getDouble(i2)), position2, i2);
                        } else if (f0 != 2004) {
                            z = true;
                        } else {
                            z = Y(cursorWindow, getBlob(i2), position2, i2);
                        }
                        if (!z) {
                            break;
                        }
                        i2++;
                    }
                }
                moveToPosition(position);
            } catch (IllegalStateException unused) {
            } catch (Throwable th) {
                cursorWindow.releaseReference();
                throw th;
            }
            cursorWindow.releaseReference();
        }
    }

    public byte[] getBlob(int i) {
        byte[] M$y5OMoQ;
        synchronized (this.L) {
            M$y5OMoQ = N.M$y5OMoQ(this.F, this, i);
        }
        return M$y5OMoQ;
    }

    public String[] getColumnNames() {
        return N.MzRUX52l(this.F, this);
    }

    public int getCount() {
        synchronized (this.K) {
            if (this.G == -1) {
                this.G = N.MYg7mvxo(this.F, this);
            }
        }
        return this.G;
    }

    public double getDouble(int i) {
        return N.MwhZ6Q97(this.F, this, i);
    }

    public float getFloat(int i) {
        return (float) N.MwhZ6Q97(this.F, this, i);
    }

    public int getInt(int i) {
        return N.MlsHIYMp(this.F, this, i);
    }

    public long getLong(int i) {
        return N.MvGp3edK(this.F, this, i);
    }

    public short getShort(int i) {
        return (short) N.MlsHIYMp(this.F, this, i);
    }

    public String getString(int i) {
        return N.M0zQNAFQ(this.F, this, i);
    }

    public boolean isNull(int i) {
        return N.M4VrFfY5(this.F, this, i);
    }

    public boolean onMove(int i, int i2) {
        synchronized (this.K) {
            N.MiTQIBC9(this.F, this, i2);
        }
        return super.onMove(i, i2);
    }
}
