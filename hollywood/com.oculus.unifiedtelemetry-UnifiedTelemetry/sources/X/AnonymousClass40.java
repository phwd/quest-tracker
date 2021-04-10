package X;

import android.app.Person;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;

/* renamed from: X.40  reason: invalid class name */
public final class AnonymousClass40 {
    @Nullable
    public IconCompat A00;
    @Nullable
    public CharSequence A01;
    @Nullable
    public String A02;
    @Nullable
    public String A03;
    public boolean A04;
    public boolean A05;

    @NonNull
    @RequiresApi(28)
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public static AnonymousClass40 A00(@NonNull Person person) {
        IconCompat iconCompat;
        AnonymousClass3z r1 = new AnonymousClass3z();
        r1.A01 = person.getName();
        if (person.getIcon() != null) {
            iconCompat = IconCompat.A04(person.getIcon());
        } else {
            iconCompat = null;
        }
        r1.A00 = iconCompat;
        r1.A03 = person.getUri();
        r1.A02 = person.getKey();
        r1.A04 = person.isBot();
        r1.A05 = person.isImportant();
        return new AnonymousClass40(r1);
    }

    @NonNull
    public static AnonymousClass40 A01(@NonNull Bundle bundle) {
        IconCompat iconCompat;
        Bundle bundle2 = bundle.getBundle("icon");
        AnonymousClass3z r2 = new AnonymousClass3z();
        r2.A01 = bundle.getCharSequence("name");
        if (bundle2 != null) {
            int i = bundle2.getInt("type");
            iconCompat = new IconCompat(i);
            iconCompat.A00 = bundle2.getInt("int1");
            iconCompat.A01 = bundle2.getInt("int2");
            if (bundle2.containsKey("tint_list")) {
                iconCompat.A03 = (ColorStateList) bundle2.getParcelable("tint_list");
            }
            if (bundle2.containsKey("tint_mode")) {
                iconCompat.A04 = PorterDuff.Mode.valueOf(bundle2.getString("tint_mode"));
            }
            switch (i) {
                case -1:
                case 1:
                case 5:
                    iconCompat.A06 = bundle2.getParcelable("obj");
                    break;
                case 0:
                default:
                    Log.w("IconCompat", AnonymousClass06.A01("Unknown type ", i));
                    iconCompat = null;
                    break;
                case 2:
                case 4:
                case 6:
                    iconCompat.A06 = bundle2.getString("obj");
                    break;
                case 3:
                    iconCompat.A06 = bundle2.getByteArray("obj");
                    break;
            }
        } else {
            iconCompat = null;
        }
        r2.A00 = iconCompat;
        r2.A03 = bundle.getString("uri");
        r2.A02 = bundle.getString("key");
        r2.A04 = bundle.getBoolean("isBot");
        r2.A05 = bundle.getBoolean("isImportant");
        return new AnonymousClass40(r2);
    }

    @NonNull
    @RequiresApi(28)
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public final Person A02() {
        Icon icon;
        Person.Builder name = new Person.Builder().setName(this.A01);
        IconCompat iconCompat = this.A00;
        if (iconCompat != null) {
            icon = IconCompat.A02(iconCompat);
        } else {
            icon = null;
        }
        return name.setIcon(icon).setUri(this.A03).setKey(this.A02).setBot(this.A04).setImportant(this.A05).build();
    }

    @NonNull
    public final Bundle A03() {
        Bundle bundle;
        Parcelable parcelable;
        Bundle bundle2 = new Bundle();
        bundle2.putCharSequence("name", this.A01);
        IconCompat iconCompat = this.A00;
        if (iconCompat != null) {
            bundle = new Bundle();
            switch (iconCompat.A02) {
                case -1:
                    parcelable = (Parcelable) iconCompat.A06;
                    bundle.putParcelable("obj", parcelable);
                    break;
                case 0:
                default:
                    throw new IllegalArgumentException("Invalid icon");
                case 1:
                case 5:
                    parcelable = (Bitmap) iconCompat.A06;
                    bundle.putParcelable("obj", parcelable);
                    break;
                case 2:
                case 4:
                case 6:
                    bundle.putString("obj", (String) iconCompat.A06);
                    break;
                case 3:
                    bundle.putByteArray("obj", (byte[]) iconCompat.A06);
                    break;
            }
            bundle.putInt("type", iconCompat.A02);
            bundle.putInt("int1", iconCompat.A00);
            bundle.putInt("int2", iconCompat.A01);
            ColorStateList colorStateList = iconCompat.A03;
            if (colorStateList != null) {
                bundle.putParcelable("tint_list", colorStateList);
            }
            PorterDuff.Mode mode = iconCompat.A04;
            if (mode != IconCompat.A09) {
                bundle.putString("tint_mode", mode.name());
            }
        } else {
            bundle = null;
        }
        bundle2.putBundle("icon", bundle);
        bundle2.putString("uri", this.A03);
        bundle2.putString("key", this.A02);
        bundle2.putBoolean("isBot", this.A04);
        bundle2.putBoolean("isImportant", this.A05);
        return bundle2;
    }

    public AnonymousClass40(AnonymousClass3z r2) {
        this.A01 = r2.A01;
        this.A00 = r2.A00;
        this.A03 = r2.A03;
        this.A02 = r2.A02;
        this.A04 = r2.A04;
        this.A05 = r2.A05;
    }
}
