package com.facebook.stetho.inspector.protocol.module;

import android.annotation.SuppressLint;
import com.facebook.stetho.common.ListUtil;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.ComputedStyleAccumulator;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.Origin;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.elements.StyleRuleNameAccumulator;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

public class CSS implements ChromeDevtoolsDomain {
    private final Document mDocument;
    private final ObjectMapper mObjectMapper = new ObjectMapper();
    private final ChromePeerManager mPeerManager = new ChromePeerManager();

    private static class PseudoIdMatches {
        @JsonProperty(required = true)
        public List<RuleMatch> matches = new ArrayList();
        @JsonProperty(required = true)
        public int pseudoId;
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
    }

    public CSS(Document document) {
        this.mDocument = (Document) Util.throwIfNull(document);
        this.mPeerManager.setListener(new PeerManagerListener());
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getComputedStyleForNode(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        final GetComputedStyleForNodeRequest getComputedStyleForNodeRequest = (GetComputedStyleForNodeRequest) this.mObjectMapper.convertValue(jSONObject, GetComputedStyleForNodeRequest.class);
        final GetComputedStyleForNodeResult getComputedStyleForNodeResult = new GetComputedStyleForNodeResult();
        getComputedStyleForNodeResult.computedStyle = new ArrayList();
        this.mDocument.postAndWait(new Runnable() {
            /* class com.facebook.stetho.inspector.protocol.module.CSS.AnonymousClass1 */

            public void run() {
                Object elementForNodeId = CSS.this.mDocument.getElementForNodeId(getComputedStyleForNodeRequest.nodeId);
                if (elementForNodeId == null) {
                    LogUtil.e("Tried to get the style of an element that does not exist, using nodeid=" + getComputedStyleForNodeRequest.nodeId);
                    return;
                }
                CSS.this.mDocument.getElementComputedStyles(elementForNodeId, new ComputedStyleAccumulator() {
                    /* class com.facebook.stetho.inspector.protocol.module.CSS.AnonymousClass1.AnonymousClass1 */

                    @Override // com.facebook.stetho.inspector.elements.ComputedStyleAccumulator
                    public void store(String str, String str2) {
                        CSSComputedStyleProperty cSSComputedStyleProperty = new CSSComputedStyleProperty();
                        cSSComputedStyleProperty.name = str;
                        cSSComputedStyleProperty.value = str2;
                        getComputedStyleForNodeResult.computedStyle.add(cSSComputedStyleProperty);
                    }
                });
            }
        });
        return getComputedStyleForNodeResult;
    }

    @ChromeDevtoolsMethod
    @SuppressLint({"DefaultLocale"})
    public JsonRpcResult getMatchedStylesForNode(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        final GetMatchedStylesForNodeRequest getMatchedStylesForNodeRequest = (GetMatchedStylesForNodeRequest) this.mObjectMapper.convertValue(jSONObject, GetMatchedStylesForNodeRequest.class);
        final GetMatchedStylesForNodeResult getMatchedStylesForNodeResult = new GetMatchedStylesForNodeResult();
        getMatchedStylesForNodeResult.matchedCSSRules = new ArrayList();
        getMatchedStylesForNodeResult.inherited = Collections.emptyList();
        getMatchedStylesForNodeResult.pseudoElements = Collections.emptyList();
        this.mDocument.postAndWait(new Runnable() {
            /* class com.facebook.stetho.inspector.protocol.module.CSS.AnonymousClass2 */

            public void run() {
                final Object elementForNodeId = CSS.this.mDocument.getElementForNodeId(getMatchedStylesForNodeRequest.nodeId);
                if (elementForNodeId == null) {
                    LogUtil.w("Failed to get style of an element that does not exist, nodeid=" + getMatchedStylesForNodeRequest.nodeId);
                    return;
                }
                CSS.this.mDocument.getElementStyleRuleNames(elementForNodeId, new StyleRuleNameAccumulator() {
                    /* class com.facebook.stetho.inspector.protocol.module.CSS.AnonymousClass2.AnonymousClass1 */

                    @Override // com.facebook.stetho.inspector.elements.StyleRuleNameAccumulator
                    public void store(String str, boolean z) {
                        final ArrayList arrayList = new ArrayList();
                        RuleMatch ruleMatch = new RuleMatch();
                        ruleMatch.matchingSelectors = ListUtil.newImmutableList(0);
                        Selector selector = new Selector();
                        selector.value = str;
                        CSSRule cSSRule = new CSSRule();
                        cSSRule.origin = Origin.REGULAR;
                        cSSRule.selectorList = new SelectorList();
                        cSSRule.selectorList.selectors = ListUtil.newImmutableList(selector);
                        cSSRule.style = new CSSStyle();
                        cSSRule.style.cssProperties = arrayList;
                        cSSRule.style.shorthandEntries = Collections.emptyList();
                        if (z) {
                            cSSRule.style.styleSheetId = String.format("%s.%s", Integer.toString(getMatchedStylesForNodeRequest.nodeId), selector.value);
                        }
                        CSS.this.mDocument.getElementStyles(elementForNodeId, str, new StyleAccumulator() {
                            /* class com.facebook.stetho.inspector.protocol.module.CSS.AnonymousClass2.AnonymousClass1.AnonymousClass1 */

                            @Override // com.facebook.stetho.inspector.elements.StyleAccumulator
                            public void store(String str, String str2, boolean z) {
                                CSSProperty cSSProperty = new CSSProperty();
                                cSSProperty.name = str;
                                cSSProperty.value = str2;
                                arrayList.add(cSSProperty);
                            }
                        });
                        ruleMatch.rule = cSSRule;
                        getMatchedStylesForNodeResult.matchedCSSRules.add(ruleMatch);
                    }
                });
            }
        });
        return getMatchedStylesForNodeResult;
    }

    @ChromeDevtoolsMethod
    public SetPropertyTextResult setPropertyText(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        final String str;
        final String str2;
        SetPropertyTextRequest setPropertyTextRequest = (SetPropertyTextRequest) this.mObjectMapper.convertValue(jSONObject, SetPropertyTextRequest.class);
        String[] split = setPropertyTextRequest.styleSheetId.split("\\.", 2);
        final int parseInt = Integer.parseInt(split[0]);
        final String str3 = split[1];
        if (setPropertyTextRequest.text == null || !setPropertyTextRequest.text.contains(":")) {
            str2 = null;
            str = null;
        } else {
            String[] split2 = setPropertyTextRequest.text.split(":", 2);
            String trim = split2[0].trim();
            str = StringUtil.removeAll(split2[1], ';').trim();
            str2 = trim;
        }
        final SetPropertyTextResult setPropertyTextResult = new SetPropertyTextResult();
        setPropertyTextResult.style = new CSSStyle();
        setPropertyTextResult.style.styleSheetId = setPropertyTextRequest.styleSheetId;
        setPropertyTextResult.style.cssProperties = new ArrayList();
        setPropertyTextResult.style.shorthandEntries = Collections.emptyList();
        this.mDocument.postAndWait(new Runnable() {
            /* class com.facebook.stetho.inspector.protocol.module.CSS.AnonymousClass3 */

            public void run() {
                Object elementForNodeId = CSS.this.mDocument.getElementForNodeId(parseInt);
                if (elementForNodeId == null) {
                    LogUtil.w("Failed to get style of an element that does not exist, nodeid=" + parseInt);
                    return;
                }
                if (str2 != null) {
                    CSS.this.mDocument.setElementStyle(elementForNodeId, str3, str2, str);
                }
                CSS.this.mDocument.getElementStyles(elementForNodeId, str3, new StyleAccumulator() {
                    /* class com.facebook.stetho.inspector.protocol.module.CSS.AnonymousClass3.AnonymousClass1 */

                    @Override // com.facebook.stetho.inspector.elements.StyleAccumulator
                    public void store(String str, String str2, boolean z) {
                        CSSProperty cSSProperty = new CSSProperty();
                        cSSProperty.name = str;
                        cSSProperty.value = str2;
                        setPropertyTextResult.style.cssProperties.add(cSSProperty);
                    }
                });
            }
        });
        return setPropertyTextResult;
    }

    private final class PeerManagerListener extends PeersRegisteredListener {
        private PeerManagerListener() {
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.stetho.inspector.helper.PeersRegisteredListener
        public synchronized void onFirstPeerRegistered() {
            CSS.this.mDocument.addRef();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.stetho.inspector.helper.PeersRegisteredListener
        public synchronized void onLastPeerUnregistered() {
            CSS.this.mDocument.release();
        }
    }

    private static class CSSComputedStyleProperty {
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String value;

        private CSSComputedStyleProperty() {
        }
    }

    private static class RuleMatch {
        @JsonProperty
        public List<Integer> matchingSelectors;
        @JsonProperty
        public CSSRule rule;

        private RuleMatch() {
        }
    }

    private static class SelectorList {
        @JsonProperty
        public List<Selector> selectors;
        @JsonProperty
        public String text;

        private SelectorList() {
        }
    }

    private static class SourceRange {
        @JsonProperty(required = true)
        public int endColumn;
        @JsonProperty(required = true)
        public int endLine;
        @JsonProperty(required = true)
        public int startColumn;
        @JsonProperty(required = true)
        public int startLine;

        private SourceRange() {
        }
    }

    private static class Selector {
        @JsonProperty
        public SourceRange range;
        @JsonProperty(required = true)
        public String value;

        private Selector() {
        }
    }

    private static class CSSRule {
        @JsonProperty
        public Origin origin;
        @JsonProperty(required = true)
        public SelectorList selectorList;
        @JsonProperty
        public CSSStyle style;
        @JsonProperty
        public String styleSheetId;

        private CSSRule() {
        }
    }

    private static class CSSStyle {
        @JsonProperty(required = true)
        public List<CSSProperty> cssProperties;
        @JsonProperty
        public String cssText;
        @JsonProperty
        public SourceRange range;
        @JsonProperty
        public List<ShorthandEntry> shorthandEntries;
        @JsonProperty
        public String styleSheetId;

        private CSSStyle() {
        }
    }

    private static class ShorthandEntry {
        @JsonProperty
        public Boolean imporant;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public String value;

        private ShorthandEntry() {
        }
    }

    private static class CSSProperty {
        @JsonProperty
        public Boolean disabled;
        @JsonProperty
        public Boolean implicit;
        @JsonProperty
        public Boolean important;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty
        public Boolean parsedOk;
        @JsonProperty
        public SourceRange range;
        @JsonProperty
        public String text;
        @JsonProperty(required = true)
        public String value;

        private CSSProperty() {
        }
    }

    private static class GetComputedStyleForNodeRequest {
        @JsonProperty(required = true)
        public int nodeId;

        private GetComputedStyleForNodeRequest() {
        }
    }

    private static class InheritedStyleEntry {
        @JsonProperty(required = true)
        public CSSStyle inlineStyle;
        @JsonProperty(required = true)
        public List<RuleMatch> matchedCSSRules;

        private InheritedStyleEntry() {
        }
    }

    private static class GetComputedStyleForNodeResult implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<CSSComputedStyleProperty> computedStyle;

        private GetComputedStyleForNodeResult() {
        }
    }

    private static class GetMatchedStylesForNodeRequest implements JsonRpcResult {
        @JsonProperty
        public Boolean excludeInherited;
        @JsonProperty
        public Boolean excludePseudo;
        @JsonProperty(required = true)
        public int nodeId;

        private GetMatchedStylesForNodeRequest() {
        }
    }

    private static class GetMatchedStylesForNodeResult implements JsonRpcResult {
        @JsonProperty
        public List<InheritedStyleEntry> inherited;
        @JsonProperty
        public List<RuleMatch> matchedCSSRules;
        @JsonProperty
        public List<PseudoIdMatches> pseudoElements;

        private GetMatchedStylesForNodeResult() {
        }
    }

    private static class SetPropertyTextRequest implements JsonRpcResult {
        @JsonProperty(required = true)
        public String styleSheetId;
        @JsonProperty(required = true)
        public String text;

        private SetPropertyTextRequest() {
        }
    }

    private static class SetPropertyTextResult implements JsonRpcResult {
        @JsonProperty(required = true)
        public CSSStyle style;

        private SetPropertyTextResult() {
        }
    }
}
