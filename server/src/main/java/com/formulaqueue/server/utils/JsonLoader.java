package com.formulaqueue.server.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader {

    public static List<String> getAllowedCars(String path){
        List<String> allowedCars = new ArrayList<>();

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject jsonObject = new JSONObject(jsonContent);

            if (jsonObject.has("batteryInspection")) {
                JSONArray array = jsonObject.getJSONArray("batteryInspection");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject inspectionObject = array.getJSONObject(i);

                    String carNumber = inspectionObject.getString("carNumber");
                    allowedCars.add(carNumber);
                }
            }
            else if (jsonObject.has("mechanicalInspection")) {
                JSONArray array = jsonObject.getJSONArray("mechanicalInspection");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject inspectionObject = array.getJSONObject(i);

                    String carNumber = inspectionObject.getString("carNumber");
                    allowedCars.add(carNumber);
                }
            }
            else if (jsonObject.has("HVLVInspection")) {
                JSONArray array = jsonObject.getJSONArray("HVLVInspection");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject inspectionObject = array.getJSONObject(i);

                    String carNumber = inspectionObject.getString("carNumber");
                    allowedCars.add(carNumber);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return allowedCars;
    }


}
