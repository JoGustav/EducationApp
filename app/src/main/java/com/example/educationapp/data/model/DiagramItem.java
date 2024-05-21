package com.example.educationapp.data.model;

public class DiagramItem {

    private String label;
    private int value;

    public DiagramItem(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }
}