package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.facebook.acra.CrashTimeDataCollector;
import com.facebook.acra.util.ProcFileReader;
import java.lang.reflect.InvocationTargetException;

public class IconCompat extends CustomVersionedParcelable {
    public static final PorterDuff.Mode A09 = PorterDuff.Mode.SRC_IN;
    public int A00;
    public int A01;
    public int A02;
    public ColorStateList A03;
    public PorterDuff.Mode A04;
    public Parcelable A05;
    public Object A06;
    public String A07;
    public byte[] A08;

    private final String A00() {
        int i = this.A02;
        if (i == -1) {
            Icon icon = (Icon) this.A06;
            if (Build.VERSION.SDK_INT >= 28) {
                return icon.getResPackage();
            }
            try {
                return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e("IconCompat", "Unable to get icon package", e);
                return null;
            }
        } else if (i == 2) {
            return ((String) this.A06).split(":", -1)[0];
        } else {
            StringBuilder sb = new StringBuilder("called getResPackage() on ");
            sb.append(this);
            throw new IllegalStateException(sb.toString());
        }
    }

    public final int A01() {
        int i = this.A02;
        if (i == -1) {
            Icon icon = (Icon) this.A06;
            if (Build.VERSION.SDK_INT >= 28) {
                return icon.getResId();
            }
            try {
                return ((Number) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e("IconCompat", "Unable to get icon resource", e);
                return 0;
            }
        } else if (i == 2) {
            return this.A00;
        } else {
            StringBuilder sb = new StringBuilder("called getResId() on ");
            sb.append(this);
            throw new IllegalStateException(sb.toString());
        }
    }

    public final int A02() {
        int i = this.A02;
        if (i != -1) {
            return i;
        }
        Icon icon = (Icon) this.A06;
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Number) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to get icon type ");
            sb.append(icon);
            Log.e("IconCompat", sb.toString(), e);
            return -1;
        }
    }

    public final Icon A03() {
        Icon icon;
        Object parse;
        int i = this.A02;
        switch (i) {
            case ProcFileReader.CANNOT_DETERMINE_OPEN_FDS /*{ENCODED_INT: -1}*/:
                return (Icon) this.A06;
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                icon = Icon.createWithBitmap((Bitmap) this.A06);
                break;
            case 2:
                icon = Icon.createWithResource(A00(), this.A00);
                break;
            case 3:
                icon = Icon.createWithData((byte[]) this.A06, this.A00, this.A01);
                break;
            case 4:
                icon = Icon.createWithContentUri((String) this.A06);
                break;
            case 5:
                if (Build.VERSION.SDK_INT < 26) {
                    Bitmap bitmap = (Bitmap) this.A06;
                    int min = (int) (((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) * 0.6666667f);
                    Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(createBitmap);
                    Paint paint = new Paint(3);
                    float f = 0.5f * ((float) min);
                    paint.setColor(-16777216);
                    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                    BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
                    Matrix matrix = new Matrix();
                    matrix.setTranslate((float) ((-(bitmap.getWidth() - min)) >> 1), (float) ((-(bitmap.getHeight() - min)) >> 1));
                    bitmapShader.setLocalMatrix(matrix);
                    paint.setShader(bitmapShader);
                    canvas.drawCircle(f, f, 0.9166667f * f, paint);
                    canvas.setBitmap(null);
                    icon = Icon.createWithBitmap(createBitmap);
                    break;
                } else {
                    icon = Icon.createWithAdaptiveBitmap((Bitmap) this.A06);
                    break;
                }
            case 6:
                StringBuilder sb = new StringBuilder("Context is required to resolve the file uri of the icon: ");
                if (i == -1) {
                    Icon icon2 = (Icon) this.A06;
                    if (Build.VERSION.SDK_INT >= 28) {
                        parse = icon2.getUri();
                    } else {
                        parse = null;
                        try {
                            parse = icon2.getClass().getMethod("getUri", new Class[0]).invoke(icon2, new Object[0]);
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            Log.e("IconCompat", "Unable to get icon uri", e);
                        }
                    }
                } else if (i == 4 || i == 6) {
                    parse = Uri.parse((String) this.A06);
                } else {
                    StringBuilder sb2 = new StringBuilder("called getUri() on ");
                    sb2.append(this);
                    throw new IllegalStateException(sb2.toString());
                }
                sb.append(parse);
                throw new IllegalArgumentException(sb.toString());
        }
        ColorStateList colorStateList = this.A03;
        if (colorStateList != null) {
            icon.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.A04;
        if (mode != A09) {
            icon.setTintMode(mode);
        }
        return icon;
    }

    public final String toString() {
        String str;
        int i = this.A02;
        if (i == -1) {
            return String.valueOf(this.A06);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (i) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
                break;
        }
        sb.append(str);
        switch (i) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.A06).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.A06).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(A00());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(A01())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.A00);
                int i2 = this.A01;
                if (i2 != 0) {
                    sb.append(" off=");
                    sb.append(i2);
                    break;
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.A06);
                break;
        }
        ColorStateList colorStateList = this.A03;
        if (colorStateList != null) {
            sb.append(" tint=");
            sb.append(colorStateList);
        }
        PorterDuff.Mode mode = this.A04;
        if (mode != A09) {
            sb.append(" mode=");
            sb.append(mode);
        }
        sb.append(")");
        return sb.toString();
    }

    public IconCompat() {
        this.A02 = -1;
        this.A08 = null;
        this.A05 = null;
        this.A00 = 0;
        this.A01 = 0;
        this.A03 = null;
        this.A04 = A09;
        this.A07 = null;
    }

    public IconCompat(int i) {
        this.A02 = -1;
        this.A08 = null;
        this.A05 = null;
        this.A00 = 0;
        this.A01 = 0;
        this.A03 = null;
        this.A04 = A09;
        this.A07 = null;
        this.A02 = 2;
    }
}
