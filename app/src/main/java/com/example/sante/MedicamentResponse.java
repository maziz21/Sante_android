package com.example.sante;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedicamentResponse {

    @SerializedName("results")
    public List<Medicament> results;

    public static class Medicament {
        @SerializedName("openfda")
        public OpenFDA openFDA;

        @SerializedName("indications_and_usage")
        public List<String> indications;

        @SerializedName("adverse_reactions")
        public List<String> adverseReactions;

        @SerializedName("purpose")
        public List<String> purpose;

        public static class OpenFDA {
            @SerializedName("brand_name")
            public List<String> brandName;

            @SerializedName("generic_name")
            public List<String> genericName;
        }
    }
}

