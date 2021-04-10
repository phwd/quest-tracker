package org.chromium.components.omnibox;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SuggestionAnswer {

    /* renamed from: a  reason: collision with root package name */
    public final int f10864a;
    public final ImageLine b;
    public final ImageLine c;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ImageLine {

        /* renamed from: a  reason: collision with root package name */
        public final List f10865a;
        public final TextField b;
        public final TextField c;
        public final String d;

        public ImageLine(List list, TextField textField, TextField textField2, String str) {
            this.f10865a = list;
            this.b = textField;
            this.c = textField2;
            this.d = str;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ImageLine)) {
                return false;
            }
            ImageLine imageLine = (ImageLine) obj;
            if (this.f10865a.size() != imageLine.f10865a.size()) {
                return false;
            }
            for (int i = 0; i < this.f10865a.size(); i++) {
                if (!Objects.equals(this.f10865a.get(i), imageLine.f10865a.get(i))) {
                    return false;
                }
            }
            if (!TextUtils.equals(this.d, imageLine.d) || !Objects.equals(this.b, imageLine.b) || !Objects.equals(this.c, imageLine.c)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Arrays.deepHashCode(new Object[]{this.f10865a.toArray(), this.b, this.c, this.d});
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class TextField {

        /* renamed from: a  reason: collision with root package name */
        public final int f10866a;
        public final String b;
        public final int c;
        public final int d;

        public TextField(int i, String str, int i2, int i3) {
            this.f10866a = i;
            this.b = str;
            this.c = i2;
            this.d = i3;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TextField)) {
                return false;
            }
            TextField textField = (TextField) obj;
            if (this.f10866a == textField.f10866a && TextUtils.equals(this.b, textField.b) && this.c == textField.c && this.d == textField.d) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.f10866a), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d)});
        }
    }

    public SuggestionAnswer(int i, ImageLine imageLine, ImageLine imageLine2) {
        this.f10864a = i;
        this.b = imageLine;
        this.c = imageLine2;
    }

    public static void addTextFieldToList(List list, TextField textField) {
        list.add(textField);
    }

    public static ImageLine createImageLine(List list, TextField textField, TextField textField2, String str) {
        return new ImageLine(list, textField, textField2, str);
    }

    public static SuggestionAnswer createSuggestionAnswer(int i, ImageLine imageLine, ImageLine imageLine2) {
        return new SuggestionAnswer(i, imageLine, imageLine2);
    }

    public static TextField createTextField(int i, String str, int i2, int i3) {
        return new TextField(i, str, i2, i3);
    }

    public static List createTextFieldList() {
        return new ArrayList();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SuggestionAnswer)) {
            return false;
        }
        SuggestionAnswer suggestionAnswer = (SuggestionAnswer) obj;
        if (this.f10864a != suggestionAnswer.f10864a || !Objects.equals(this.b, suggestionAnswer.b) || !Objects.equals(this.c, suggestionAnswer.c)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f10864a), this.b, this.c});
    }
}
