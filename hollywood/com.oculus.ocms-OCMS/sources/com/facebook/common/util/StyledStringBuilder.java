package com.facebook.common.util;

import android.content.res.Resources;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StyledStringBuilder {
    private final Resources resources;
    private final SpannableStringBuilder sb;
    private final ArrayDeque<SpanStart> stack;

    /* access modifiers changed from: private */
    public static class SpanStart {
        final int flags;
        final int index;
        final Object span;

        public SpanStart(int i, Object obj, int i2) {
            this.index = i;
            this.span = obj;
            this.flags = i2;
        }
    }

    public StyledStringBuilder(Resources resources2) {
        this(new SpannableStringBuilder(), resources2);
    }

    public StyledStringBuilder(SpannableStringBuilder spannableStringBuilder, Resources resources2) {
        this.stack = new ArrayDeque<>();
        this.sb = spannableStringBuilder;
        this.resources = resources2;
    }

    public StyledStringBuilder append(CharSequence charSequence) {
        this.sb.append(charSequence);
        return this;
    }

    public StyledStringBuilder append(int i) {
        this.sb.append((CharSequence) this.resources.getString(i));
        return this;
    }

    public StyledStringBuilder startSpan(Object obj, int i) {
        this.stack.addFirst(new SpanStart(this.sb.length(), obj, i));
        return this;
    }

    public StyledStringBuilder endSpan() {
        Preconditions.checkState(!this.stack.isEmpty());
        SpanStart removeFirst = this.stack.removeFirst();
        this.sb.setSpan(removeFirst.span, removeFirst.index, this.sb.length(), removeFirst.flags);
        return this;
    }

    public StyledStringBuilder replaceToken(String str, String str2, Object obj, int i) {
        return replaceToken(str, str2, i, obj);
    }

    public StyledStringBuilder replaceToken(String str, CharSequence charSequence, int i, Object... objArr) {
        Preconditions.checkState(this.stack.isEmpty());
        Matcher matcher = Pattern.compile(Pattern.quote(str)).matcher(this.sb);
        if (matcher.find()) {
            int start = matcher.start();
            this.sb.replace(start, matcher.end(), charSequence);
            for (Object obj : objArr) {
                this.sb.setSpan(obj, start, charSequence.length() + start, i);
            }
        }
        return this;
    }

    public StyledStringBuilder replaceToken(String str, CharSequence charSequence) {
        return replaceToken(str, charSequence, 0, Collections.emptyList());
    }

    public SpannableString toSpannableString() {
        return new SpannableString(this.sb);
    }

    public int length() {
        return this.sb.length();
    }
}
