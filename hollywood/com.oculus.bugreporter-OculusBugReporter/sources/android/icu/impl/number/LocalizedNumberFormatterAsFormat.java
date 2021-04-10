package android.icu.impl.number;

import android.icu.number.FormattedNumber;
import android.icu.number.LocalizedNumberFormatter;
import android.icu.number.NumberFormatter;
import android.icu.util.ULocale;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class LocalizedNumberFormatterAsFormat extends Format {
    private static final long serialVersionUID = 1;
    private final transient LocalizedNumberFormatter formatter;
    private final transient ULocale locale;

    public LocalizedNumberFormatterAsFormat(LocalizedNumberFormatter formatter2, ULocale locale2) {
        this.formatter = formatter2;
        this.locale = locale2;
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj instanceof Number) {
            FormattedNumber result = this.formatter.format((Number) obj);
            pos.setBeginIndex(0);
            pos.setEndIndex(0);
            if (result.nextFieldPosition(pos) && toAppendTo.length() != 0) {
                pos.setBeginIndex(pos.getBeginIndex() + toAppendTo.length());
                pos.setEndIndex(pos.getEndIndex() + toAppendTo.length());
            }
            result.appendTo(toAppendTo);
            return toAppendTo;
        }
        throw new IllegalArgumentException();
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        if (obj instanceof Number) {
            return this.formatter.format((Number) obj).toCharacterIterator();
        }
        throw new IllegalArgumentException();
    }

    @Override // java.text.Format
    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    public LocalizedNumberFormatter getNumberFormatter() {
        return this.formatter;
    }

    public int hashCode() {
        return this.formatter.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof LocalizedNumberFormatterAsFormat)) {
            return this.formatter.equals(((LocalizedNumberFormatterAsFormat) other).getNumberFormatter());
        }
        return false;
    }

    private Object writeReplace() throws ObjectStreamException {
        Proxy proxy = new Proxy();
        proxy.languageTag = this.locale.toLanguageTag();
        proxy.skeleton = this.formatter.toSkeleton();
        return proxy;
    }

    static class Proxy implements Externalizable {
        private static final long serialVersionUID = 1;
        String languageTag;
        String skeleton;

        @Override // java.io.Externalizable
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeByte(0);
            out.writeUTF(this.languageTag);
            out.writeUTF(this.skeleton);
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            in.readByte();
            this.languageTag = in.readUTF();
            this.skeleton = in.readUTF();
        }

        private Object readResolve() throws ObjectStreamException {
            return NumberFormatter.forSkeleton(this.skeleton).locale(ULocale.forLanguageTag(this.languageTag)).toFormat();
        }
    }
}
