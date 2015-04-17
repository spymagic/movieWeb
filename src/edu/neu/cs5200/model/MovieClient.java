package edu.neu.cs5200.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MovieClient {

	public final String FIND_MOVIE_BY_TITLE = "http://www.myapifilms.com/imdb?title=MOVIE_TITLE&format=JSON&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=N&exactFilter=0&limit=1&forceYear=0&lang=en-us&actors=N&biography=0&trailer=0&uniqueName=0&filmography=0&bornDied=0&starSign=0&actorActress=0&actorTrivia=0&movieTrivia=0&awards=0&moviePhotos=N&movieVideos=N&similarMovies=0";
	
	public Movie findMovieByTitle(String title)
	{
		String urlStr = FIND_MOVIE_BY_TITLE.replace("MOVIE_TITLE", title);
		Movie movie = new Movie();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = getJsonStringForUrl(urlStr);
		
		try {
			movie = mapper.readValue(json, Movie.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print(getJsonStringForUrl(urlStr));
		return movie;	
	}

	private String getJsonStringForUrl(String urlStr) {
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String out;
			StringBuffer json = new StringBuffer(); 
			while((out = buffer.readLine()) !=null) {
				json.append(out);
			}
			return json.toString();
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args)
	{
		MovieClient client = new MovieClient();
		Movie movie = client.findMovieByTitle("Avatar");
		System.out.print(movie.getId());
	}
}
