package org.example;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class DataBaseSaverWithDates {
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    public void saveToDBWithDates(String url) { //save currency not current day
        entityManagerFactory = Persistence.createEntityManagerFactory("SaveUseDate");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(getUSDFromDate(url));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private USD getUSDFromDate(String url) {
        String userDat=null;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Date in format: dd.mm.yyyy");
            userDat = sc.nextLine();
            if(userDat.length()==10){
                break;
            }else {
                System.out.println("Wrong Entering");
            }
        }
        StringBuilder line = new StringBuilder();
        String readed = null;
        try {
            URL url1 = new URL(url + userDat);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                while ((readed = bufferedReader.readLine()) != null) {
                        line.append(readed);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject(line.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("exchangeRate");
        JSONObject jsonObject1 = new JSONObject();
        for (int i=0;i<jsonArray.length();i++){
            if(jsonArray.getJSONObject(i).getString("currency").equals("USD")){
                jsonObject1 = jsonArray.getJSONObject(i);
                jsonObject1.put("date", userDat);
                break;
            }
        }

        return new Gson().fromJson(jsonObject1.toString(), USD.class);
    }
}