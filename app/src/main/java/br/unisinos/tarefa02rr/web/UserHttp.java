package br.unisinos.tarefa02rr.web;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import br.unisinos.tarefa02rr.models.User;

/**
 * Created by dennerevaldtmachado on 06/07/16.
 */
public class UserHttp {
    private static String URL_API = "http://jsonplaceholder.typicode.com/users";

    public ArrayList<User> getDataApi() throws Exception {
        HttpURLConnection httpURLConnection = ConnectionUtil.connect(URL_API, "GET", true, false, null);
        ArrayList<User> userList = new ArrayList<>();

        int responseServer = httpURLConnection.getResponseCode();
        if (responseServer == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpURLConnection.getInputStream();
            JSONArray jsonUsers = new JSONArray(ConnectionUtil.bytesForString(inputStream));

            for (int i = 0; i < jsonUsers.length(); i++){
                JSONObject jsonUser = jsonUsers.getJSONObject(i);
                User user = new User(
                        jsonUser.getString("id"),
                        jsonUser.getString("name"),
                        jsonUser.getString("username"),
                        jsonUser.getString("email")
                );
                userList.add(user);
            }
            return userList;
        }
        return null;
    }

}
