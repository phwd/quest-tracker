package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.2a8  reason: invalid class name */
public class AnonymousClass2a8 extends ViewGroup.MarginLayoutParams {
    public float A00;
    public float A01 = 0.5f;
    public int A02 = 1;
    public int A03 = -1;
    public int A04 = -1;
    public int A05;
    public int A06;
    public int A07 = -1;
    public int A08 = -1;
    public int A09 = -1;
    public int A0A = -1;
    public boolean A0B = true;
    public boolean A0C = false;
    public boolean A0D = false;
    public boolean A0E = false;
    public boolean A0F = true;
    public float A0G = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A0H = -1.0f;
    public float A0I = 0.5f;
    public float A0J = -1.0f;
    public float A0K = 1.0f;
    public float A0L = 1.0f;
    public float A0M = 0.5f;
    public float A0N = -1.0f;
    public int A0O = -1;
    public int A0P = -1;
    public int A0Q = -1;
    public int A0R = -1;
    public int A0S = 0;
    public int A0T = -1;
    public int A0U = -1;
    public int A0V = -1;
    public int A0W = -1;
    public int A0X = -1;
    public int A0Y = -1;
    public int A0Z = -1;
    public int A0a = -1;
    public int A0b = -1;
    public int A0c = -1;
    public int A0d = -1;
    public int A0e = -1;
    public int A0f = 0;
    public int A0g = -1;
    public int A0h = -1;
    public int A0i = 0;
    public int A0j = 0;
    public int A0k = 0;
    public int A0l = 0;
    public int A0m = 0;
    public int A0n = 0;
    public int A0o = -1;
    public int A0p = -1;
    public int A0q = -1;
    public int A0r = -1;
    public int A0s = -1;
    public int A0t = -1;
    public int A0u = -1;
    public int A0v = 0;
    public AnonymousClass2ac A0w = new AnonymousClass2ac();
    public String A0x = null;
    public String A0y = null;
    public boolean A0z = false;
    public boolean A10 = false;

    public final void A00() {
        this.A0C = false;
        this.A0B = true;
        this.A0F = true;
        int i = this.width;
        if (i == -2 && this.A10) {
            this.A0B = false;
            if (this.A0j == 0) {
                this.A0j = 1;
            }
        }
        int i2 = this.height;
        if (i2 == -2 && this.A0z) {
            this.A0F = false;
            if (this.A0i == 0) {
                this.A0i = 1;
            }
        }
        if (i == 0 || i == -1) {
            this.A0B = false;
            if (i == 0 && this.A0j == 1) {
                this.width = -2;
                this.A10 = true;
            }
        }
        if (i2 == 0 || i2 == -1) {
            this.A0F = false;
            if (i2 == 0 && this.A0i == 1) {
                this.height = -2;
                this.A0z = true;
            }
        }
        if (this.A0H != -1.0f || this.A0d != -1 || this.A0e != -1) {
            this.A0C = true;
            this.A0B = true;
            this.A0F = true;
            AnonymousClass2ac r1 = this.A0w;
            if (!(r1 instanceof C15002ak)) {
                r1 = new C15002ak();
                this.A0w = r1;
            }
            ((C15002ak) r1).A0V(this.A0o);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ab  */
    @android.annotation.TargetApi(17)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void resolveLayoutDirection(int r15) {
        /*
        // Method dump skipped, instructions count: 232
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2a8.resolveLayoutDirection(int):void");
    }

    public AnonymousClass2a8(int i, int i2) {
        super(i, i2);
    }

    public AnonymousClass2a8(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i;
        String str;
        String str2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass2aI.A01);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (AnonymousClass2aM.A00.get(index)) {
                case 1:
                    this.A0o = obtainStyledAttributes.getInt(index, this.A0o);
                    continue;
                case 2:
                    int resourceId = obtainStyledAttributes.getResourceId(index, this.A0R);
                    this.A0R = resourceId;
                    if (resourceId == -1) {
                        this.A0R = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 3:
                    this.A0S = obtainStyledAttributes.getDimensionPixelSize(index, this.A0S);
                    continue;
                case 4:
                    float f = obtainStyledAttributes.getFloat(index, this.A0G) % 360.0f;
                    this.A0G = f;
                    if (f < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                        this.A0G = (360.0f - f) % 360.0f;
                    } else {
                        continue;
                    }
                case 5:
                    this.A0d = obtainStyledAttributes.getDimensionPixelOffset(index, this.A0d);
                    continue;
                case 6:
                    this.A0e = obtainStyledAttributes.getDimensionPixelOffset(index, this.A0e);
                    continue;
                case 7:
                    this.A0H = obtainStyledAttributes.getFloat(index, this.A0H);
                    continue;
                case 8:
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, this.A0g);
                    this.A0g = resourceId2;
                    if (resourceId2 == -1) {
                        this.A0g = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 9:
                    int resourceId3 = obtainStyledAttributes.getResourceId(index, this.A0h);
                    this.A0h = resourceId3;
                    if (resourceId3 == -1) {
                        this.A0h = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 10:
                    int resourceId4 = obtainStyledAttributes.getResourceId(index, this.A0p);
                    this.A0p = resourceId4;
                    if (resourceId4 == -1) {
                        this.A0p = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 11:
                    int resourceId5 = obtainStyledAttributes.getResourceId(index, this.A0q);
                    this.A0q = resourceId5;
                    if (resourceId5 == -1) {
                        this.A0q = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 12:
                    int resourceId6 = obtainStyledAttributes.getResourceId(index, this.A0u);
                    this.A0u = resourceId6;
                    if (resourceId6 == -1) {
                        this.A0u = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 13:
                    int resourceId7 = obtainStyledAttributes.getResourceId(index, this.A0t);
                    this.A0t = resourceId7;
                    if (resourceId7 == -1) {
                        this.A0t = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 14:
                    int resourceId8 = obtainStyledAttributes.getResourceId(index, this.A0Q);
                    this.A0Q = resourceId8;
                    if (resourceId8 == -1) {
                        this.A0Q = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 15:
                    int resourceId9 = obtainStyledAttributes.getResourceId(index, this.A0P);
                    this.A0P = resourceId9;
                    if (resourceId9 == -1) {
                        this.A0P = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 16:
                    int resourceId10 = obtainStyledAttributes.getResourceId(index, this.A0O);
                    this.A0O = resourceId10;
                    if (resourceId10 == -1) {
                        this.A0O = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 17:
                    int resourceId11 = obtainStyledAttributes.getResourceId(index, this.A0r);
                    this.A0r = resourceId11;
                    if (resourceId11 == -1) {
                        this.A0r = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 18:
                    int resourceId12 = obtainStyledAttributes.getResourceId(index, this.A0s);
                    this.A0s = resourceId12;
                    if (resourceId12 == -1) {
                        this.A0s = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 19:
                    int resourceId13 = obtainStyledAttributes.getResourceId(index, this.A0W);
                    this.A0W = resourceId13;
                    if (resourceId13 == -1) {
                        this.A0W = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 20:
                    int resourceId14 = obtainStyledAttributes.getResourceId(index, this.A0V);
                    this.A0V = resourceId14;
                    if (resourceId14 == -1) {
                        this.A0V = obtainStyledAttributes.getInt(index, -1);
                    } else {
                        continue;
                    }
                case 21:
                    this.A0Z = obtainStyledAttributes.getDimensionPixelSize(index, this.A0Z);
                    continue;
                case 22:
                    this.A0c = obtainStyledAttributes.getDimensionPixelSize(index, this.A0c);
                    continue;
                case 23:
                    this.A0a = obtainStyledAttributes.getDimensionPixelSize(index, this.A0a);
                    continue;
                case 24:
                    this.A0X = obtainStyledAttributes.getDimensionPixelSize(index, this.A0X);
                    continue;
                case 25:
                    this.A0b = obtainStyledAttributes.getDimensionPixelSize(index, this.A0b);
                    continue;
                case 26:
                    this.A0Y = obtainStyledAttributes.getDimensionPixelSize(index, this.A0Y);
                    continue;
                case 27:
                    this.A10 = obtainStyledAttributes.getBoolean(index, this.A10);
                    continue;
                case 28:
                    this.A0z = obtainStyledAttributes.getBoolean(index, this.A0z);
                    continue;
                case 29:
                    this.A0I = obtainStyledAttributes.getFloat(index, this.A0I);
                    continue;
                case 30:
                    this.A0M = obtainStyledAttributes.getFloat(index, this.A0M);
                    continue;
                case 31:
                    int i3 = obtainStyledAttributes.getInt(index, 0);
                    this.A0j = i3;
                    if (i3 == 1) {
                        str2 = "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.";
                        break;
                    } else {
                        continue;
                    }
                case 32:
                    int i4 = obtainStyledAttributes.getInt(index, 0);
                    this.A0i = i4;
                    if (i4 == 1) {
                        str2 = "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.";
                        break;
                    } else {
                        continue;
                    }
                case 33:
                    try {
                        this.A0n = obtainStyledAttributes.getDimensionPixelSize(index, this.A0n);
                        continue;
                    } catch (Exception unused) {
                        if (obtainStyledAttributes.getInt(index, this.A0n) == -2) {
                            this.A0n = -2;
                        }
                    }
                case 34:
                    try {
                        this.A0l = obtainStyledAttributes.getDimensionPixelSize(index, this.A0l);
                        continue;
                    } catch (Exception unused2) {
                        if (obtainStyledAttributes.getInt(index, this.A0l) == -2) {
                            this.A0l = -2;
                        }
                    }
                case 35:
                    this.A0L = Math.max((float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, obtainStyledAttributes.getFloat(index, this.A0L));
                    this.A0j = 2;
                    continue;
                case 36:
                    try {
                        this.A0m = obtainStyledAttributes.getDimensionPixelSize(index, this.A0m);
                        continue;
                    } catch (Exception unused3) {
                        if (obtainStyledAttributes.getInt(index, this.A0m) == -2) {
                            this.A0m = -2;
                        }
                    }
                case 37:
                    try {
                        this.A0k = obtainStyledAttributes.getDimensionPixelSize(index, this.A0k);
                        continue;
                    } catch (Exception unused4) {
                        if (obtainStyledAttributes.getInt(index, this.A0k) == -2) {
                            this.A0k = -2;
                        }
                    }
                case 38:
                    this.A0K = Math.max((float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, obtainStyledAttributes.getFloat(index, this.A0K));
                    this.A0i = 2;
                    continue;
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                default:
                case 44:
                    String string = obtainStyledAttributes.getString(index);
                    this.A0y = string;
                    this.A02 = -1;
                    if (string != null) {
                        int length = string.length();
                        int indexOf = string.indexOf(44);
                        if (indexOf <= 0 || indexOf >= length - 1) {
                            i = 0;
                        } else {
                            String substring = string.substring(0, indexOf);
                            if (substring.equalsIgnoreCase("W")) {
                                this.A02 = 0;
                            } else if (substring.equalsIgnoreCase("H")) {
                                this.A02 = 1;
                            }
                            i = indexOf + 1;
                        }
                        String str3 = this.A0y;
                        int indexOf2 = str3.indexOf(58);
                        if (indexOf2 < 0 || indexOf2 >= length - 1) {
                            str = str3.substring(i);
                            if (str.length() <= 0) {
                            }
                        } else {
                            String substring2 = str3.substring(i, indexOf2);
                            str = this.A0y.substring(indexOf2 + 1);
                            if (substring2.length() > 0 && str.length() > 0) {
                                try {
                                    Float.parseFloat(substring2);
                                } catch (NumberFormatException unused5) {
                                }
                            }
                        }
                        Float.parseFloat(str);
                    } else {
                        continue;
                    }
                    break;
                case 45:
                    this.A0J = obtainStyledAttributes.getFloat(index, this.A0J);
                    continue;
                case 46:
                    this.A0N = obtainStyledAttributes.getFloat(index, this.A0N);
                    continue;
                case 47:
                    this.A0f = obtainStyledAttributes.getInt(index, 0);
                    continue;
                case 48:
                    this.A0v = obtainStyledAttributes.getInt(index, 0);
                    continue;
                case 49:
                    this.A0T = obtainStyledAttributes.getDimensionPixelOffset(index, this.A0T);
                    continue;
                case 50:
                    this.A0U = obtainStyledAttributes.getDimensionPixelOffset(index, this.A0U);
                    continue;
                case 51:
                    this.A0x = obtainStyledAttributes.getString(index);
                    continue;
            }
            Log.e(ConstraintLayout.TAG, str2);
        }
        obtainStyledAttributes.recycle();
        A00();
    }

    public AnonymousClass2a8(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }
}
