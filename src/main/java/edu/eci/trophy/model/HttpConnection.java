/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.trophy.model;

import edu.eci.trophy.service.TrophyException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Jonathan Prieto
 */
public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static String getUrlData(String urlParam) throws IOException, TrophyException {

        URL objUrl = new URL(urlParam);
        HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        //System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            throw new TrophyException("Nombre de usuario no valido.");
        } else {
            throw new TrophyException("Error en los parametros de la peticion.");
        }
        //System.out.println("GET DONE");
    }
}
