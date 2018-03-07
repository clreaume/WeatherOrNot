package com.gc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 * author: Weather Or Not
 *
 */
//wunderground api key: be4423cb67742fcc

@Controller
public class HomeController {

	@RequestMapping("/welcome")
	public ModelAndView index(Model model) {

		String prodCenter = "";

		try {
			// the HttpClient Interface represents the contract for the HTTP request
			// execution
			HttpClient http = HttpClientBuilder.create().build();

			// HttpHost holds the variables needed for the connections
			// default port for http is 80
			// default port for https is 443
			HttpHost host = new HttpHost("api.wunderground.com", 443, "https");

			// HttpGet retrieves the info identified by the request url (returns as an
			// entity)
			HttpGet getPage = new HttpGet("/api/be4423cb67742fcc/conditions/q/MI/Detroit.json");

			HttpResponse resp = http.execute(host, getPage);

			// casting the entity returned to a string
			String jsonString = EntityUtils.toString(resp.getEntity());

			System.out.println(jsonString);
			
			// assign the returned result to a json object
			JSONObject json = new JSONObject(jsonString);
			
			prodCenter = json.get("current_observation").toString();
			String test = json.getJSONObject("current_observation").getString("weather");
			String test2 = json.getJSONObject("current_observation").getJSONObject("image").getString("url");
			String test3 = json.getJSONObject("current_observation").getString( "icon_url");
			
			
			
			System.out.println(test);
			System.out.println(test2);
			System.out.println(test3);

			// this is a test print to our console to make sure we are communicating with
			// the API (response code should be 200)
			System.out.println("Response code: " + resp.getStatusLine().getStatusCode());

			String text = "";
			JSONArray arr = json.getJSONObject("data").getJSONArray("text");

			for (int i = 0; i < arr.length(); i++) {
				text += ("<h6>" + arr.getString(i) + "</h6>");
			}

			model.addAttribute("jsonData", text);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("index", "centerData", prodCenter);
	}

	// this is an alternative way to pull in json data
	@RequestMapping("/nasadata")
	public ModelAndView nasaData() {

		String center = "";
		String city = "";
		String contact = "";
		String forPrint = "";

		try {
			// this is how we create the url code in order to call the JSON response with
			// info we request
			URL url = new URL("https://data.nasa.gov/resource/9g7e-7hzz.json");

			// the openstream() allows us to open and read the url that was given -- we will
			// need to loop through this
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			String line = reader.readLine();
			String jsonString = "";

			while (line != null) {
				jsonString += line;
				line = reader.readLine();
			}

			JSONArray json = new JSONArray(jsonString);

			for (int i = 0; i < json.length(); i++) {
				center = json.getJSONObject(i).getString("center");
				city = json.getJSONObject(i).getString("city");
				contact = json.getJSONObject(i).getString("contact");
				forPrint += ("<h6>" + center + ", " + city + ", " + contact + "</h6>");
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("welcome", "message", forPrint);
	}
	
	
	
	
	
}