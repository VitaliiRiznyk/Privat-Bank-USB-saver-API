package org.example;

import com.google.gson.Gson;
import org.json.JSONArray;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DatabaseSaver {
    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;

    public void saveToDBConvertedUSD(String url) {

        entityManagerFactory = Persistence.createEntityManagerFactory("SimpleJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(getCourseUSD(url));
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    private Converted getCourseUSD(String url) {
        StringBuilder result = new StringBuilder();
        try {
            URL urlConn = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConn.openConnection();
            httpURLConnection.setRequestMethod("GET");
            String line;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray(result.toString());
        return new Gson().fromJson(jsonArray.get(0).toString(), Converted.class);
    }
}
