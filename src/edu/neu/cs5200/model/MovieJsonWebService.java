package edu.neu.cs5200.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MovieJsonWebService {


	

	private String urlApi="http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=7m9cdz4jsrj6gp5stfth3mf3&q={{Movie}}&page_limit=1";
	public Movie getMovieforName(String movie) throws IOException{
		
		
		
		String urlStr=urlApi.replace("{{Movie}}", movie);
		String newUrlStr=urlStr.replaceAll(" ", "+");

		
		try {
			URL url = new URL(newUrlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

			connection.setRequestMethod("GET");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line;
			String json="";
			while((line = buffer.readLine()) !=null) {
				json += line;
			}
			
			JSONParser parser = new JSONParser();
			
			try {
				JSONObject root = (JSONObject) parser.parse(json);
				JSONArray movies= (JSONArray) root.get("movies");
				JSONObject firstelementinmovie= (JSONObject) movies.get(0);
				JSONObject posters= (JSONObject)firstelementinmovie.get("posters");
				JSONObject ratings= (JSONObject)firstelementinmovie.get("ratings");

				
				
				Movie currentelement = new Movie();
				currentelement.title = firstelementinmovie.get("title").toString();
				currentelement.year = firstelementinmovie.get("year").toString();

				currentelement.runtime = firstelementinmovie.get("runtime").toString();


				currentelement.synopsis = firstelementinmovie.get("synopsis").toString();
				currentelement.rating = ratings.get("audience_score").toString(); //rating
				 
				
/*				JSONObject cast= (JSONObject)firstelementinmovie.get("abridged_cast");//actors
				JSONObject cast1= (JSONObject)cast.get("0");
				String actor1=cast1.get("name").toString();
				JSONObject cast2= (JSONObject)cast.get("1");
				String actor2=cast2.get("name").toString();
				JSONObject cast3= (JSONObject)cast.get("2");
				String actor3=cast3.get("name").toString();
				currentelement.actor1=actor1;
				currentelement.actor2=actor2;
				currentelement.actor3=actor3;
*/				
				
				String thumbnail=posters.get("thumbnail").toString();
				String thumbnailId =thumbnail.substring(99);
//				String thumbnailId2=thumbnailId.replaceAll("ori", "pro");
				String thumbnailId3="http://content6.flixster.com/movie/"+thumbnailId;
                thumbnail=thumbnailId3;
				currentelement.thumbnail = thumbnail;
				
                Movie movieObject = new Movie(currentelement.title, currentelement.year, currentelement.thumbnail , currentelement.synopsis, currentelement.rating, currentelement.runtime);
                
                return movieObject;
                
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
