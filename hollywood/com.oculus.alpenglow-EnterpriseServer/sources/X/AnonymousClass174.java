package X;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.annotation.RequiresApi;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: X.174  reason: invalid class name */
public class AnonymousClass174 extends Resources {
    public final Resources A00;

    @Override // android.content.res.Resources
    public final XmlResourceParser getAnimation(int i) throws Resources.NotFoundException {
        return this.A00.getAnimation(i);
    }

    @Override // android.content.res.Resources
    public final boolean getBoolean(int i) throws Resources.NotFoundException {
        return this.A00.getBoolean(i);
    }

    @Override // android.content.res.Resources
    public final int getColor(int i) throws Resources.NotFoundException {
        return this.A00.getColor(i);
    }

    @Override // android.content.res.Resources
    public final ColorStateList getColorStateList(int i) throws Resources.NotFoundException {
        return this.A00.getColorStateList(i);
    }

    public final Configuration getConfiguration() {
        return this.A00.getConfiguration();
    }

    @Override // android.content.res.Resources
    public final float getDimension(int i) throws Resources.NotFoundException {
        return this.A00.getDimension(i);
    }

    @Override // android.content.res.Resources
    public final int getDimensionPixelOffset(int i) throws Resources.NotFoundException {
        return this.A00.getDimensionPixelOffset(i);
    }

    @Override // android.content.res.Resources
    public final int getDimensionPixelSize(int i) throws Resources.NotFoundException {
        return this.A00.getDimensionPixelSize(i);
    }

    public final DisplayMetrics getDisplayMetrics() {
        return this.A00.getDisplayMetrics();
    }

    public final float getFraction(int i, int i2, int i3) {
        return this.A00.getFraction(i, i2, i3);
    }

    public final int getIdentifier(String str, String str2, String str3) {
        return this.A00.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public final int[] getIntArray(int i) throws Resources.NotFoundException {
        return this.A00.getIntArray(i);
    }

    @Override // android.content.res.Resources
    public final int getInteger(int i) throws Resources.NotFoundException {
        return this.A00.getInteger(i);
    }

    @Override // android.content.res.Resources
    public final XmlResourceParser getLayout(int i) throws Resources.NotFoundException {
        return this.A00.getLayout(i);
    }

    @Override // android.content.res.Resources
    public final Movie getMovie(int i) throws Resources.NotFoundException {
        return this.A00.getMovie(i);
    }

    @Override // android.content.res.Resources
    public final CharSequence getQuantityText(int i, int i2) throws Resources.NotFoundException {
        return this.A00.getQuantityText(i, i2);
    }

    @Override // android.content.res.Resources
    public final String getResourceEntryName(int i) throws Resources.NotFoundException {
        return this.A00.getResourceEntryName(i);
    }

    @Override // android.content.res.Resources
    public final String getResourceName(int i) throws Resources.NotFoundException {
        return this.A00.getResourceName(i);
    }

    @Override // android.content.res.Resources
    public final String getResourcePackageName(int i) throws Resources.NotFoundException {
        return this.A00.getResourcePackageName(i);
    }

    @Override // android.content.res.Resources
    public final String getResourceTypeName(int i) throws Resources.NotFoundException {
        return this.A00.getResourceTypeName(i);
    }

    @Override // android.content.res.Resources
    public final String[] getStringArray(int i) throws Resources.NotFoundException {
        return this.A00.getStringArray(i);
    }

    @Override // android.content.res.Resources
    public final CharSequence[] getTextArray(int i) throws Resources.NotFoundException {
        return this.A00.getTextArray(i);
    }

    @Override // android.content.res.Resources
    @RequiresApi(Hpack.PREFIX_4_BITS)
    public final void getValueForDensity(int i, int i2, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        this.A00.getValueForDensity(i, i2, typedValue, z);
    }

    @Override // android.content.res.Resources
    public final XmlResourceParser getXml(int i) throws Resources.NotFoundException {
        return this.A00.getXml(i);
    }

    public final TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.A00.obtainAttributes(attributeSet, iArr);
    }

    @Override // android.content.res.Resources
    public final TypedArray obtainTypedArray(int i) throws Resources.NotFoundException {
        return this.A00.obtainTypedArray(i);
    }

    @Override // android.content.res.Resources
    public final AssetFileDescriptor openRawResourceFd(int i) throws Resources.NotFoundException {
        return this.A00.openRawResourceFd(i);
    }

    @Override // android.content.res.Resources
    public final void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) throws XmlPullParserException {
        this.A00.parseBundleExtra(str, attributeSet, bundle);
    }

    @Override // android.content.res.Resources
    public final void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) throws XmlPullParserException, IOException {
        this.A00.parseBundleExtras(xmlResourceParser, bundle);
    }

    public final void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.A00;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        return this.A00.getDrawable(i);
    }

    @Override // android.content.res.Resources
    @RequiresApi(21)
    public final Drawable getDrawable(int i, Resources.Theme theme) throws Resources.NotFoundException {
        return this.A00.getDrawable(i, theme);
    }

    @Override // android.content.res.Resources
    @RequiresApi(Hpack.PREFIX_4_BITS)
    public final Drawable getDrawableForDensity(int i, int i2) throws Resources.NotFoundException {
        return this.A00.getDrawableForDensity(i, i2);
    }

    @RequiresApi(21)
    public final Drawable getDrawableForDensity(int i, int i2, Resources.Theme theme) {
        return this.A00.getDrawableForDensity(i, i2, theme);
    }

    @Override // android.content.res.Resources
    public final String getQuantityString(int i, int i2) throws Resources.NotFoundException {
        return this.A00.getQuantityString(i, i2);
    }

    @Override // android.content.res.Resources
    public final String getQuantityString(int i, int i2, Object... objArr) throws Resources.NotFoundException {
        return this.A00.getQuantityString(i, i2, objArr);
    }

    @Override // android.content.res.Resources
    public final String getString(int i) throws Resources.NotFoundException {
        return this.A00.getString(i);
    }

    @Override // android.content.res.Resources
    public final String getString(int i, Object... objArr) throws Resources.NotFoundException {
        return this.A00.getString(i, objArr);
    }

    @Override // android.content.res.Resources
    public final CharSequence getText(int i) throws Resources.NotFoundException {
        return this.A00.getText(i);
    }

    public final CharSequence getText(int i, CharSequence charSequence) {
        return this.A00.getText(i, charSequence);
    }

    @Override // android.content.res.Resources
    public final void getValue(int i, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        this.A00.getValue(i, typedValue, z);
    }

    @Override // android.content.res.Resources
    public final void getValue(String str, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        this.A00.getValue(str, typedValue, z);
    }

    @Override // android.content.res.Resources
    public final InputStream openRawResource(int i) throws Resources.NotFoundException {
        return this.A00.openRawResource(i);
    }

    @Override // android.content.res.Resources
    public final InputStream openRawResource(int i, TypedValue typedValue) throws Resources.NotFoundException {
        return this.A00.openRawResource(i, typedValue);
    }
}
