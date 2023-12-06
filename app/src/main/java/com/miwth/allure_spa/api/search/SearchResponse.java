package com.miwth.allure_spa.api.search;

import com.miwth.allure_spa.model.Cosmetics;
import com.miwth.allure_spa.model.Search;
import com.miwth.allure_spa.model.Treatments;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    private boolean success;
    private String message;
//    private List<Treatments> treatments;
//    private List<Cosmetics> cosmetics;
    private List<Search> results;
    private Search search;
//
//    public SearchResponse(boolean success, String message, List<Treatments> treatments, List<Cosmetics> cosmetics, List<Search> results) {
//        this.success = success;
//        this.message = message;
//        this.treatments = treatments;
//        this.cosmetics = cosmetics;
//        this.results = results;
//    }

    public SearchResponse(boolean success, String message, List<Search> results, Search search) {
        this.success = success;
        this.message = message;
        this.results = results;
        this.search = search;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Search> getResults() {
//        List<Search> results = new ArrayList<>();
//        for (Treatments treatment : treatments) {
//            results.add(new Search(treatment.getTreatmentName(), treatment.getDescription(), treatment.getImage()));
//        }
//        for (Cosmetics cosmetic : cosmetics) {
//            results.add(new Search(cosmetic.getCosmeticsName(), cosmetic.getDescription(), cosmetic.getImage()));
//        }

        return results;
    }

    public Search getSearch() {
        return search;
    }
}
