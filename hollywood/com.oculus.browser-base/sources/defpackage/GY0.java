package defpackage;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

/* renamed from: GY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GY0 extends InputConnectionWrapper {

    /* renamed from: a  reason: collision with root package name */
    public final C2721gd f8095a;
    public final /* synthetic */ IY0 b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GY0(IY0 iy0) {
        super(null, true);
        this.b = iy0;
        this.f8095a = new C2721gd(iy0.c);
    }

    public void a() {
        if (this.b.c.c()) {
            C2721gd gdVar = this.b.c;
            String str = gdVar.b;
            gdVar.f10008a += gdVar.b;
            gdVar.b = "";
            IY0 iy0 = this.b;
            iy0.e.a(iy0.c);
            IY0 iy02 = this.b;
            iy02.h = false;
            if (iy02.j == 0) {
                c();
                HY0 hy0 = this.b.f;
                hy0.f8163a.getEditableText().removeSpan(hy0.b);
                hy0.c(true);
                b();
                return;
            }
            iy02.b.append(str);
        }
    }

    public final boolean b() {
        IY0 iy0 = this.b;
        iy0.j--;
        boolean endBatchEdit = super.endBatchEdit();
        IY0 iy02 = this.b;
        if (iy02.j == 0) {
            iy02.n();
        }
        return endBatchEdit;
    }

    public boolean beginBatchEdit() {
        d();
        boolean c = c();
        e();
        return c;
    }

    public final boolean c() {
        this.b.j++;
        return super.beginBatchEdit();
    }

    public boolean clearMetaKeyStates(int i) {
        d();
        boolean clearMetaKeyStates = super.clearMetaKeyStates(i);
        e();
        return clearMetaKeyStates;
    }

    public boolean commitCompletion(CompletionInfo completionInfo) {
        d();
        boolean commitCompletion = super.commitCompletion(completionInfo);
        e();
        return commitCompletion;
    }

    public boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        d();
        boolean commitContent = super.commitContent(inputContentInfo, i, bundle);
        e();
        return commitContent;
    }

    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        d();
        boolean commitCorrection = super.commitCorrection(correctionInfo);
        e();
        return commitCorrection;
    }

    public boolean commitText(CharSequence charSequence, int i) {
        d();
        boolean commitText = super.commitText(charSequence, i);
        e();
        return commitText;
    }

    public boolean d() {
        boolean c = c();
        IY0 iy0 = this.b;
        if (iy0.j == 1) {
            this.f8095a.a(iy0.c);
        } else if (iy0.k > 0) {
            int length = iy0.b.getText().length();
            this.b.b.getText().delete(length - this.b.k, length);
        }
        IY0 iy02 = this.b;
        iy02.k = 0;
        iy02.f.b();
        return c;
    }

    public boolean deleteSurroundingText(int i, int i2) {
        d();
        boolean deleteSurroundingText = super.deleteSurroundingText(i, i2);
        e();
        return deleteSurroundingText;
    }

    public boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        d();
        boolean deleteSurroundingTextInCodePoints = super.deleteSurroundingTextInCodePoints(i, i2);
        e();
        return deleteSurroundingTextInCodePoints;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0152, code lost:
        if (r9.b.c.e() != false) goto L_0x0154;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x012b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean e() {
        /*
        // Method dump skipped, instructions count: 350
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.GY0.e():boolean");
    }

    public boolean endBatchEdit() {
        d();
        boolean b2 = b();
        e();
        return b2;
    }

    public boolean finishComposingText() {
        d();
        boolean finishComposingText = super.finishComposingText();
        e();
        return finishComposingText;
    }

    public int getCursorCapsMode(int i) {
        d();
        int cursorCapsMode = super.getCursorCapsMode(i);
        e();
        return cursorCapsMode;
    }

    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        d();
        ExtractedText extractedText = super.getExtractedText(extractedTextRequest, i);
        e();
        return extractedText;
    }

    public CharSequence getSelectedText(int i) {
        d();
        CharSequence selectedText = super.getSelectedText(i);
        e();
        return selectedText;
    }

    public CharSequence getTextAfterCursor(int i, int i2) {
        d();
        CharSequence textAfterCursor = super.getTextAfterCursor(i, i2);
        e();
        return textAfterCursor;
    }

    public CharSequence getTextBeforeCursor(int i, int i2) {
        d();
        CharSequence textBeforeCursor = super.getTextBeforeCursor(i, i2);
        e();
        return textBeforeCursor;
    }

    public boolean performEditorAction(int i) {
        d();
        a();
        boolean performEditorAction = super.performEditorAction(i);
        e();
        return performEditorAction;
    }

    public boolean requestCursorUpdates(int i) {
        d();
        boolean requestCursorUpdates = super.requestCursorUpdates(i);
        e();
        return requestCursorUpdates;
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        d();
        boolean sendKeyEvent = super.sendKeyEvent(keyEvent);
        e();
        return sendKeyEvent;
    }

    public boolean setComposingRegion(int i, int i2) {
        d();
        boolean composingRegion = super.setComposingRegion(i, i2);
        e();
        return composingRegion;
    }

    public boolean setComposingText(CharSequence charSequence, int i) {
        d();
        boolean composingText = super.setComposingText(charSequence, i);
        e();
        return composingText;
    }

    public boolean setSelection(int i, int i2) {
        d();
        boolean selection = super.setSelection(i, i2);
        e();
        return selection;
    }
}
