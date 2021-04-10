package com.oculus.panelapp.keyboardv2.japanese;

import java.util.List;

public class PredictionResult {
    private String predictionCompositionString;
    private List<Prediction> predictions;

    public PredictionResult(String str, List<Prediction> list) {
        this.predictionCompositionString = str;
        this.predictions = list;
    }

    public String getPredictionCompositionString() {
        return this.predictionCompositionString;
    }

    public List<Prediction> getPredictions() {
        return this.predictions;
    }

    public void setPredictionCompositionString(String str) {
        if (str != null) {
            this.predictionCompositionString = str;
        }
    }

    public void setPredictions(List<Prediction> list) {
        if (list != null) {
            this.predictions = list;
        }
    }
}
