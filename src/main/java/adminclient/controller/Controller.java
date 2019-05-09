package adminclient.controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Controller {
    public static void main(String[] args) {
        Controller c = new Controller();
        try {
            //c.getStatistics();
            c.attemptLogin("s175129","Kode2405");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //private HttpURLConnection connection;

    public boolean attemptLogin(String username, String password) throws IOException {
        //TODO Lav HTTPREQUEST TIL SERVER

        String line;
        StringBuffer responseContent = new StringBuffer();
        BufferedReader bufferedReader;

        JSONObject requestJson;// = new JsonObject();
        String url = "http://localhost:8080/Homely-ws/admin";

        //method call for generating json

        requestJson = generateJSON(username, password);
        URL myurl = new URL(url);
        HttpURLConnection con = (HttpURLConnection)myurl.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);

        con.setRequestProperty("Content-Type", "application/json;charset=utf8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Method", "POST");
        OutputStream os = con.getOutputStream();
        os.write(requestJson.toString().getBytes("UTF-8"));
        os.close();


        //StringBuilder sb = new StringBuilder();
        int HttpResult =con.getResponseCode();
        if(HttpResult ==HttpURLConnection.HTTP_OK)//{
            return true;
            /*BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            System.out.println(""+sb.toString());*/

        /*}else{
            System.out.println(con.getResponseCode());
            System.out.println(con.getResponseMessage());
        }*/
        return false;

    }
    private static JSONObject generateJSON (String email, String password) //throws MalformedURLException

    {

        JSONObject reqparam=new JSONObject();
        reqparam.put("email", email);
        reqparam.put("password", password);
        return reqparam;

    }



    public String getStatistics() throws IOException {
        String line;
        StringBuffer responseContent = new StringBuffer();
        BufferedReader bufferedReader;
        URL url = new URL("http://localhost:8080/Homely-ws/admin");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //Request setup
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        if(status>299) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while ((line = bufferedReader.readLine()) != null) {
                responseContent.append(line);
            }
            bufferedReader.close();
        }
        else{
            bufferedReader =  new BufferedReader((new InputStreamReader(connection.getInputStream())));
            while((line = bufferedReader.readLine()) != null) {
                responseContent.append(line);
            }
        }
        String returnValue = ReadJsonStatisticsResponse(responseContent.toString()); //TODO RETURNER String til VIEW
        connection.disconnect();
        return returnValue;
    }

    private static String ReadJsonStatisticsResponse(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        int categoriesCount = jsonObject.getInt("categoriesCount");
        int adsCount = jsonObject.getInt("adsCount");
        int usersCount = jsonObject.getInt("usersCount");
        JSONArray stats = jsonObject.getJSONArray("adByCategoriesList");

        String returnValue = "Antal brugere : " + usersCount +
                "\nAntal kategorier: " + categoriesCount +
                "\nAntal salgsopslag: " + adsCount +
                "\nAntal salgsopslag fordelt på kategorier: \n";



        for (int i = 0; i < stats.length(); i++) {
            JSONObject js = stats.getJSONObject(i);
            String categoryName = js.getString("categoryName");
            int numbers = js.getInt("numbers");
            returnValue+= categoryName + " : " + numbers +"\n";

        }
        return returnValue;
    }

    public String fetchAllCategories() {
        String line;
        StringBuffer responseContent = new StringBuffer();
        BufferedReader bufferedReader;

        try {
            URL url = new URL("http://localhost:8080/Homely-ws/category");
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            if(status>299) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    responseContent.append(line);
                }
                bufferedReader.close();
            }
            else{
                bufferedReader =  new BufferedReader((new InputStreamReader(connection.getInputStream())));
                while((line = bufferedReader.readLine()) != null) {
                    responseContent.append(line);
                }
            }

            String returnValue = ReadJsonCategoriesResponse(responseContent.toString());
            connection.disconnect();
            return returnValue;

        } catch (IOException e) {
            return "Noget gik galt. Kunne ikke oprette forbindelse til server. \nPrøv igen senere.";
        }

    }

    private String ReadJsonCategoriesResponse(String responseBody) {
        JSONArray jsonArray = new JSONArray(responseBody);

        String returnValue = "Kategorier: \n";
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject js = jsonArray.getJSONObject(i);
            String categoryName = js.getString("categoryName");
            int categoryId = js.getInt("categoryId");

            //System.out.println(" Kategori: "+ categoryName + " : id " + categoryId);
            returnValue+=categoryName + " : id " + categoryId +"\n";

        }
        return returnValue;
    }

    public String updateCategory(int id, String newName) {
        String line;
        StringBuffer responseContent = new StringBuffer();
        BufferedReader bufferedReader;
        try {
        JSONObject requestJson;// = new JsonObject();
        String url = "http://localhost:8080/Homely-ws/category/" + id;

        //method call for generating json

        requestJson = generateJSONForCategoryUpdate(newName);
        URL myurl;

            myurl = new URL(url);
            HttpURLConnection con = (HttpURLConnection)myurl.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("PUT");

            con.setRequestProperty("Content-Type", "application/json;charset=utf8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Method", "PUT");
            OutputStream os = con.getOutputStream();
            os.write(requestJson.toString().getBytes("UTF-8"));
            os.close();


            //StringBuilder sb = new StringBuilder();
            int HttpResult =con.getResponseCode();

            if(HttpResult !=HttpURLConnection.HTTP_OK){
                bufferedReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    responseContent.append(line);
                }
                bufferedReader.close();
            }
            else{
                bufferedReader =  new BufferedReader((new InputStreamReader(con.getInputStream())));
                while((line = bufferedReader.readLine()) != null) {
                    responseContent.append(line);
                }
            }

            String returnValue;
            if(HttpResult ==HttpURLConnection.HTTP_OK)
                returnValue = ReadSingleJsonCategoryResponse(responseContent.toString());
            else
                returnValue = ReadJsonCategoryErrorMessage(responseContent.toString());
            con.disconnect();

            return returnValue;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Noget gik galt. Prøv igen senere";

    }

    private String ReadJsonCategoryErrorMessage(String errorResponse) {
        String returnValue = "";
        JSONObject jsonObject = new JSONObject(errorResponse);
        returnValue += jsonObject.getString("message") + "\n";
        returnValue += jsonObject.getString("fix") + "\n";
        return returnValue;
    }

    private String ReadSingleJsonCategoryResponse(String responseBody) {
        String returnValue = "";
        JSONObject jsonObject = new JSONObject(responseBody);
        int id = jsonObject.getInt("categoryId");
        String categoryName = jsonObject.getString("categoryName");
        returnValue+="Kategprien med id: " + id + " blev opdateret til " + categoryName + "\n";

        return returnValue;
    }

    private JSONObject generateJSONForCategoryUpdate(String newName) {
        JSONObject returnValue = new JSONObject();
        returnValue.put("categoryName", newName);
        return returnValue;

    }



    public String deleteCategory() {
        return null;
    }
}
