package com.oculus.panelapp.keyboardv2.japanese;

/* access modifiers changed from: package-private */
public class HiraganaTransformDag extends CodePointTransformDag {
    private static final String[][] graph = {new String[]{"あ", "ぁ"}, new String[]{"い", "ぃ"}, new String[]{"う", "ぅ"}, new String[]{"え", "ぇ"}, new String[]{"お", "ぉ"}, new String[]{"か", "が"}, new String[]{"き", "ぎ"}, new String[]{"く", "ぐ"}, new String[]{"け", "げ"}, new String[]{"こ", "ご"}, new String[]{"さ", "ざ"}, new String[]{"し", "じ"}, new String[]{"す", "ず"}, new String[]{"せ", "ぜ"}, new String[]{"そ", "ぞ"}, new String[]{"た", "だ"}, new String[]{"ち", "ぢ"}, new String[]{"つ", "っ", "づ"}, new String[]{"て", "で"}, new String[]{"と", "ど"}, new String[]{"は", "ば", "ぱ"}, new String[]{"ひ", "び", "ぴ"}, new String[]{"ふ", "ぶ", "ぷ"}, new String[]{"へ", "べ", "ぺ"}, new String[]{"ほ", "ぼ", "ぽ"}, new String[]{"や", "ゃ"}, new String[]{"ゆ", "ゅ"}, new String[]{"よ", "ょ"}, new String[]{"わ", "ゎ"}};

    public HiraganaTransformDag() {
        super(graph);
    }
}
