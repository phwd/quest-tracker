package com.oculus.panelapp.dogfood.tabs;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dogfood.DogfoodPanelApp;
import com.oculus.panelapp.dogfood.GraphQLRequester;
import com.oculus.panelapp.dogfood.R;
import com.oculus.panelapp.dogfood.tabs.DogfoodTabHost;
import com.oculus.vrshell.SystemUXRoute;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DogfoodAssignmentsTab extends LinearLayout implements DogfoodTabHost.DogfoodTab, GraphQLRequester {
    private static final String TAG = LoggingUtil.tag(DogfoodAssignmentsTab.class);
    private List<Assignment> mAssignments;
    private TableLayout mAssignmentsTable;
    private Button mCheckButton;
    private TextView mNoAssignmentsText;
    private DogfoodPanelApp mPanelApp;

    public DogfoodAssignmentsTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static class Assignment {
        private String mDescription;
        private String mName;
        private String mSurveyUrl;

        public Assignment(String str, String str2, String str3) {
            this.mName = str;
            this.mDescription = str2;
            this.mSurveyUrl = str3;
        }

        public String getName() {
            return this.mName;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public String getSurveyUrl() {
            return this.mSurveyUrl;
        }
    }

    @Override // com.oculus.panelapp.dogfood.tabs.DogfoodTabHost.DogfoodTab
    public void initialize(DogfoodPanelApp dogfoodPanelApp, DogfoodTabHost dogfoodTabHost) {
        this.mPanelApp = dogfoodPanelApp;
        dogfoodTabHost.addTab("assignments", "Assignments", R.id.dogfood_assignments_tab);
        this.mCheckButton = (Button) findViewById(R.id.dogfood_assignments_check_for_new);
        this.mCheckButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodAssignmentsTab.AnonymousClass1 */

            public void onClick(View view) {
                DogfoodAssignmentsTab.this.mCheckButton.setEnabled(false);
                DogfoodAssignmentsTab.this.checkForAssignments();
            }
        });
        this.mNoAssignmentsText = (TextView) findViewById(R.id.dogfood_assignments_no_assignments_text);
        this.mNoAssignmentsText.setVisibility(8);
        this.mAssignmentsTable = (TableLayout) findViewById(R.id.dogfood_assignments_table);
        checkForAssignments();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkForAssignments() {
        this.mPanelApp.makeGraphQLRequest(this, String.format(GraphQLRequester.GRAPHQL_ASSIGNMENTS, Build.SERIAL, Build.VERSION.INCREMENTAL));
    }

    @Override // com.oculus.panelapp.dogfood.GraphQLRequester
    public void handleGraphQlResponse(JSONObject jSONObject) {
        try {
            Log.d(TAG, jSONObject.toString());
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("assignments");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                arrayList.add(new Assignment(jSONObject2.getString(ServiceContract.EXTRA_NAME), jSONObject2.getString("description"), jSONObject2.getString("survey_url")));
            }
            setAssignments(arrayList);
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "Received Invalid JSON response: " + e.getMessage());
        }
        this.mCheckButton.setEnabled(true);
    }

    @Override // com.oculus.panelapp.dogfood.GraphQLRequester
    public void handleFailedGraphQlResponse(String str) {
        this.mCheckButton.setEnabled(true);
        this.mAssignmentsTable.setVisibility(8);
        this.mNoAssignmentsText.setVisibility(0);
        this.mNoAssignmentsText.setText("There was an error loading the list of Dogfood Assignments.");
        String str2 = TAG;
        Log.e(str2, "GraphQL Request Failed, response: " + str);
    }

    public void setAssignments(List<Assignment> list) {
        this.mAssignments = list;
        TableLayout tableLayout = (TableLayout) findViewById(R.id.dogfood_assignments_table);
        if (this.mAssignments.isEmpty()) {
            this.mAssignmentsTable.setVisibility(8);
            this.mNoAssignmentsText.setVisibility(0);
            this.mNoAssignmentsText.setText("There are currently no assignments available. Check back later for more dogfood assignments!");
            return;
        }
        this.mAssignmentsTable.setVisibility(0);
        this.mNoAssignmentsText.setVisibility(8);
        for (int childCount = this.mAssignmentsTable.getChildCount() - 1; childCount >= 1; childCount--) {
            View childAt = this.mAssignmentsTable.getChildAt(childCount);
            if (childAt instanceof TableRow) {
                this.mAssignmentsTable.removeView(childAt);
            }
        }
        for (int i = 0; i < this.mAssignments.size(); i++) {
            final Assignment assignment = this.mAssignments.get(i);
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.assignment_item, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.dogfood_assignment_row_name)).setText(assignment.getName());
            ((TextView) inflate.findViewById(R.id.dogfood_assignment_row_description)).setText(assignment.getDescription());
            ((ImageButton) inflate.findViewById(R.id.dogfood_assignment_survey_button)).setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.dogfood.tabs.DogfoodAssignmentsTab.AnonymousClass2 */

                public void onClick(View view) {
                    DogfoodAssignmentsTab.this.mPanelApp.actionNavigate(SystemUXRoute.DEFAULT_BROWSER.path(), assignment.getSurveyUrl());
                }
            });
            this.mAssignmentsTable.addView(inflate);
        }
    }
}
