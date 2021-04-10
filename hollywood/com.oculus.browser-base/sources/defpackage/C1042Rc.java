package defpackage;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/* renamed from: Rc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1042Rc extends InputConnectionWrapper {

    /* renamed from: a  reason: collision with root package name */
    public final char[] f8843a = new char[1];
    public final /* synthetic */ C1164Tc b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1042Rc(C1164Tc tc, InputConnection inputConnection, boolean z) {
        super(null, z);
        this.b = tc;
    }

    public boolean beginBatchEdit() {
        C1164Tc tc = this.b;
        int i = tc.c + 1;
        tc.c = i;
        if (i != 1) {
            return super.beginBatchEdit();
        }
        tc.d = tc.f8968a.getText().getSpanStart(this.b.b);
        C1164Tc tc2 = this.b;
        tc2.e = tc2.f8968a.getText().toString();
        boolean beginBatchEdit = super.beginBatchEdit();
        this.b.g = false;
        return beginBatchEdit;
    }

    public boolean commitText(CharSequence charSequence, int i) {
        Editable text = this.b.f8968a.getText();
        if (text == null) {
            return super.commitText(charSequence, i);
        }
        int selectionStart = Selection.getSelectionStart(text);
        int selectionEnd = Selection.getSelectionEnd(text);
        int spanStart = text.getSpanStart(this.b.b);
        if (i == 1 && selectionStart > 0 && selectionStart != selectionEnd && selectionEnd >= text.length() && spanStart == selectionStart && charSequence.length() == 1) {
            int i2 = selectionStart + 1;
            text.getChars(selectionStart, i2, this.f8843a, 0);
            if (this.f8843a[0] == charSequence.charAt(0)) {
                AccessibilityManager accessibilityManager = ((AbstractC0981Qc) this.b.f8968a).f8772J;
                if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain(16);
                    obtain.setBeforeText(text.toString().substring(0, selectionStart));
                    obtain.setFromIndex(selectionStart);
                    obtain.setRemovedCount(0);
                    obtain.setAddedCount(1);
                    this.b.f8968a.sendAccessibilityEventUnchecked(obtain);
                }
                this.b.c(text.subSequence(0, i2), text.subSequence(i2, selectionEnd));
                C1164Tc tc = this.b;
                if (tc.c == 0) {
                    tc.m(false, false);
                }
                return true;
            }
        }
        boolean commitText = super.commitText(charSequence, i);
        if (text.getSpanStart(this.b.b) >= 0) {
            this.b.l();
        }
        return commitText;
    }

    public boolean endBatchEdit() {
        String str;
        C1164Tc tc = this.b;
        tc.c = Math.max(tc.c - 1, 0);
        if (this.b.c != 0) {
            return super.endBatchEdit();
        }
        boolean endBatchEdit = super.endBatchEdit();
        C1164Tc tc2 = this.b;
        if (tc2.f) {
            tc2.o(tc2.f8968a.getSelectionStart(), tc2.f8968a.getSelectionEnd());
            tc2.f = false;
        }
        String obj = tc2.f8968a.getText().toString();
        if (!TextUtils.equals(tc2.e, obj) || tc2.f8968a.getText().getSpanStart(tc2.b) != tc2.d) {
            if (tc2.h() && tc2.d != -1 && (str = tc2.e) != null && str.startsWith(obj) && !tc2.g && obj.length() - tc2.d == 1) {
                tc2.c(obj, tc2.e.substring(obj.length()));
            }
            tc2.m(tc2.g, true);
        }
        tc2.g = false;
        tc2.d = -1;
        tc2.e = null;
        tc2.n();
        return endBatchEdit;
    }

    public boolean setComposingText(CharSequence charSequence, int i) {
        Editable text = this.b.f8968a.getText();
        int spanStart = text.getSpanStart(this.b.b);
        if (spanStart >= 0) {
            if (BaseInputConnection.getComposingSpanEnd(text) == text.length() && spanStart >= charSequence.length() && TextUtils.equals(text.subSequence(spanStart - charSequence.length(), spanStart), charSequence)) {
                setComposingRegion(spanStart - charSequence.length(), spanStart);
            }
            this.b.b.a();
            Selection.setSelection(text, spanStart);
            text.delete(spanStart, text.length());
        }
        return super.setComposingText(charSequence, i);
    }
}
