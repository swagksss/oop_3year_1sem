package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Medicine {
    private List<Drug> drugList;

    // Getter method for retrieving the list of drugs
    public List<Drug> getDrugList() {
        // If the drugList is null, initialize it as a new ArrayList
        if (drugList == null) {
            drugList = new ArrayList<>();
        }
        // Return the drugList
        return drugList;
    }
}

