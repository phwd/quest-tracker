package androidx.core.view;

public final class DisplayCutoutCompat {
    private final Object mDisplayCutout;

    private DisplayCutoutCompat(Object obj) {
        this.mDisplayCutout = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DisplayCutoutCompat displayCutoutCompat = (DisplayCutoutCompat) obj;
        Object obj2 = this.mDisplayCutout;
        if (obj2 != null) {
            return obj2.equals(displayCutoutCompat.mDisplayCutout);
        }
        if (displayCutoutCompat.mDisplayCutout == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.mDisplayCutout;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }

    static DisplayCutoutCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new DisplayCutoutCompat(obj);
    }
}
