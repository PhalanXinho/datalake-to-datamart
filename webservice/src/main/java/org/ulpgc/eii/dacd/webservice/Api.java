package org.ulpgc.eii.dacd.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.util.List;

import static spark.Spark.get;

public class Api {
    public void run() {
        get("/v1/places/with-max-temperature", (req, res) -> {
            String queryParamFrom = req.queryParams("from");
            String queryParamsTo = req.queryParams("to");
            if (queryParamFrom == null && queryParamsTo == null) {
                List<Response> responseList = new ResponseMaxTemperatureBuilder().build();
                return jsonFormatter(new Gson().toJson(responseList));
            }
            if (queryParamFrom == null) {
                List<Response> responseList = new ResponseMaxTemperatureBuilder().buildWithTo(queryParamsTo);
                return jsonFormatter(new Gson().toJson(responseList));
            }
            if (queryParamsTo == null) {
                List<Response> responseList = new ResponseMaxTemperatureBuilder().buildWithFrom(queryParamFrom);
                return jsonFormatter(new Gson().toJson(responseList));
            }
            List<Response> responseList = new ResponseMaxTemperatureBuilder().buildWithBoth(queryParamFrom, queryParamsTo);
            return jsonFormatter(new Gson().toJson(responseList));
        });

        get("/v1/places/with-min-temperature", (req, res) -> {
            String queryParamFrom = req.queryParams("from");
            String queryParamsTo = req.queryParams("to");

            if (queryParamFrom == null && queryParamsTo == null) {
                List<Response> responseList = new ResponseMinTemperatureBuilder().build();
                return jsonFormatter(new Gson().toJson(responseList));
            }

            if (queryParamFrom == null) {
                List<Response> responseList = new ResponseMinTemperatureBuilder().buildWithTo(queryParamsTo);
                return jsonFormatter(new Gson().toJson(responseList));
            }

            if (queryParamsTo == null) {
                List<Response> responseList = new ResponseMinTemperatureBuilder().buildWithFrom(queryParamFrom);
                return jsonFormatter(new Gson().toJson(responseList));
            }

            List<Response> responseList = new ResponseMinTemperatureBuilder().buildWithBoth(queryParamFrom, queryParamsTo);
            return jsonFormatter(new Gson().toJson(responseList));
        });
    }

    private String jsonFormatter(String jsonString) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(jsonString));
    }
}
