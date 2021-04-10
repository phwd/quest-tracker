package com.facebook.stetho.inspector.helper;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.ViewDebug;
import androidx.annotation.Nullable;

public class IntegerFormatter {
    private static IntegerFormatter cachedFormatter;

    public static IntegerFormatter getInstance() {
        if (cachedFormatter == null) {
            synchronized (IntegerFormatter.class) {
                if (cachedFormatter == null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        cachedFormatter = new IntegerFormatterWithHex();
                    } else {
                        cachedFormatter = new IntegerFormatter();
                    }
                }
            }
        }
        return cachedFormatter;
    }

    private IntegerFormatter() {
    }

    public String format(Integer num, @Nullable ViewDebug.ExportedProperty exportedProperty) {
        return String.valueOf(num);
    }

    /* access modifiers changed from: private */
    public static class IntegerFormatterWithHex extends IntegerFormatter {
        private IntegerFormatterWithHex() {
            super();
        }

        @Override // com.facebook.stetho.inspector.helper.IntegerFormatter
        @TargetApi(21)
        public String format(Integer num, @Nullable ViewDebug.ExportedProperty exportedProperty) {
            if (exportedProperty == null || !exportedProperty.formatToHexString()) {
                return IntegerFormatter.super.format(num, exportedProperty);
            }
            return "0x" + Integer.toHexString(num.intValue());
        }
    }
}
