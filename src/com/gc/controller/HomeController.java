package com.gc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.gc.model.DAOItemImpl;
import com.gc.model.DAOUserImpl;
import com.gc.model.Item;
import com.gc.model.User;
import com.gc.util.API;
import com.gc.util.ApiCredentials;
import com.gc.util.Apparel;

/**
 * 
 * author: WeatherOrNot
 *
 * 
 */
// wunderground api key: be4423cb67742fcc

//generateoutfit
//getclosetmap

@Controller
//@SessionAttributes("user1")
public class HomeController {

	private static final String PIC_PATH = "resources/";
	// DAO USER IMPLEMENTATION OBJECT
	DAOUserImpl usr = new DAOUserImpl();
	// DAO ITEM IMPLEMENTATION OBJECT
	DAOItemImpl itm = new DAOItemImpl();

	// API OBJECT - VALUES ING API CALL
	API ourAPI = new API();

	@RequestMapping("getLoginPage")
	public String getLogin() {

		try {
			// the HttpClient Interface represents the contract for the HTTP request
			// execution
			HttpClient http = HttpClientBuilder.create().build();

			// HttpHost holds the variables needed for the connections
			// default port for http is 80
			// default port for https is 443

			HttpHost host = new HttpHost("api.wunderground.com", 80, "http");

			// HttpGet retrieves the info identified by the request url (returns as an
			// entity)
			HttpGet getPage = new HttpGet("/api.wunderground.com/api/83ee1eafa5306085/conditions/q/MI/Detroit.json");

			HttpResponse resp = http.execute(host, getPage);

			// casting the entity returned to a string
			String jsonString = EntityUtils.toString(resp.getEntity());

			System.out.println(jsonString);

			// assign the returned result to a json object
			JSONObject json = new JSONObject(jsonString);
			JSONObject currentObs = json.getJSONObject("current_observation");

			// SETTING OURAPI VARIABLES TO RETRIEVE LATER WITH GETTERS
			ourAPI.setWeather(currentObs.getString("weather"));
			ourAPI.setIcon_URL(currentObs.getString("icon_url"));
			ourAPI.setTemp_f(currentObs.getDouble("temp_f"));
			ourAPI.setFeelslike_f(currentObs.getString("feelslike_f"));
			ourAPI.setPrecip_today_in(currentObs.getString("precip_today_in"));
			ourAPI.setIcon(currentObs.getString("icon"));
			ourAPI.setRelative_humidity(currentObs.getString("relative_humidity"));
			ourAPI.setWindchill_f(currentObs.getString("windchill_f"));
			ourAPI.setWind_gust_mph(currentObs.getString("wind_gust_mph"));
			ourAPI.setCityState(currentObs.getJSONObject("display_location").getString("full"));

			// this is a test print to our console to make sure we are communicating with
			// the API (response code should be 200)
			System.out.println("Response code: " + resp.getStatusLine().getStatusCode());

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Login";
	}
  
	User currentUser;
	
	
	@RequestMapping("existingUserLogin")
	public ModelAndView loginUser( @RequestParam("email") String email,
			@RequestParam("password") String password) {
		
		System.out.println("existingUserLogin called");
		
		String pageToReturn = "Login";
		String msg;

		try {
			User userTryingToLogIn = usr.getUser(email);
			System.out.println(userTryingToLogIn);

			if (userTryingToLogIn.getPassword().equals(password)) {
				pageToReturn = "welcome";
				msg = userTryingToLogIn.getFirstName();
				//session.setAttribute("user1", currentUser);
				currentUser = userTryingToLogIn;
			} else {
				pageToReturn = "Login";
				msg = "Incorrect password.";
			}
		}

		catch (Exception e) {
			msg = "Account with that email address not found.";
		}

		return new ModelAndView(pageToReturn, "msg", msg);
	}

	@RequestMapping("getsignup")
	public String giveSignUp() {
		return "signupform";
	}

	@RequestMapping("createUser")
	public ModelAndView createUser(@RequestParam("fname") String firstName,
			@RequestParam("lname") String lastName, @RequestParam("email") String email,
			@RequestParam("password") String password) {

		User tempUser = new User(firstName, lastName, email, password);
		//session.setAttribute("user1", tempUser);

		usr.createUser(tempUser);
		
		currentUser = tempUser;

		return new ModelAndView("fillCloset", "name", firstName);

	}
	
	@RequestMapping("addToCloset")
	public ModelAndView giveFillClosetPage() {
		return new ModelAndView("fillCloset", "name", currentUser.getFirstName());
	}

	@RequestMapping("getItemInputForm")
	public String getItemInputForm(@RequestParam("itemOfClothing") String itemChosen) {
		String formToReturn;

		switch (itemChosen) {
		case "top":
			formToReturn = "topform";
			break;
		case "sweater":
			formToReturn = "sweaterForm";
			break;
		case "outerwear":
			formToReturn = "outerwearForm";
			break;
		case "bottom":
			formToReturn = "bottomForm";
			break;
		case "dress":
			formToReturn = "dressForm";
			break;
		case "shoe":
			formToReturn = "shoeForm";
			break;
		case "accessory":
			formToReturn = "accessoryForm";
			break;
		default:
			formToReturn = "fillCloset";

		}

		return formToReturn;
	}

	@RequestMapping(value="dressForm", method=RequestMethod.GET)
	public String dressForm() {
		return "dressForm";
	}
	
	@RequestMapping(value="addItem", method=RequestMethod.POST)
	public String addItem( @RequestParam("imageURL") MultipartFile file,
			@RequestParam("type") String type, @RequestParam("description") String desc, @RequestParam("category") String cat) {

		String fileName = file.getOriginalFilename();
		//encodedFileName = Base64.getEncoder().encodeToString(fileName.getBytes());
		String url = null;

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", ApiCredentials.CLOUDNAME,
				  "api_key", ApiCredentials.APIKEY,
				  "api_secret", ApiCredentials.APISECRET));
		
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
			url = (String) uploadResult.get("url");
			System.out.println(uploadResult.get("url"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Item tempItem = new Item("F", currentUser.getUserId(), type, desc, url, cat);
		System.out.println(tempItem.getCategory());

		itm.addItem(tempItem);
		

		return "itemAdded";
	}

	@RequestMapping("addAnother")
	public String giveClosetFormAgain() {
		return "fillCloset";
	}

	@RequestMapping("viewCloset")
	public ModelAndView viewCloset(Model model) {

		model.addAttribute("clothesMap", Apparel.getClosetMap(itm, currentUser));
		
		return new ModelAndView("closet", "name", currentUser.getFirstName());
	}

	
	// this mapping works with each item having their own delete button. not ideal
	// but...
	@RequestMapping("deleteItem")
	// THIS 'ID' PARAM SNEAKILY PASSED IN FROM FORM USING ~URL ENCODING~
	public ModelAndView deleteItem(@RequestParam("id") String id, Model model) {

		Item tempItem = new Item();
		tempItem.setItemId(id);
		itm.deleteItem(tempItem);
			
		model.addAttribute("clothesMap", Apparel.getClosetMap(itm, currentUser));

		return new ModelAndView("closet", "name", currentUser.getFirstName());
	}

	@RequestMapping("putInHamp")
	// THIS 'ID' PARAM SNEAKILY PASSED IN FROM FORM USING ~URL ENCODING~
	public ModelAndView putInHamp(@RequestParam("id") int id) {
		System.out.println(id);
		
		Item itemToModify;
		itemToModify = itm.getItem(id);
		
		System.out.println(itemToModify);
		System.out.println(itemToModify.getInHamp());
		
		itm.changeHampStatus(itemToModify);
		System.out.println(itemToModify.getInHamp());


		return new ModelAndView("hamper", "hamperItems", itm.getHamperItems(currentUser));
	}

	@RequestMapping("viewHamp")
	public ModelAndView viewHamper() {

		return new ModelAndView("hamper", "hamperItems", itm.getHamperItems(currentUser));

	}
	
	//WE CAN CREATE NEW USER, LOGIN EXISTING USER, ADD ITEM, DELETE ITEM, VIEW CLOSET
	//NOT GET OUTFIT

	@RequestMapping("putInCloset")
	// THIS 'ID' PARAM SNE AKILY PASSED IN FROM FORM USING ~URL ENCODING~
	public ModelAndView putInCloset(@RequestParam("id") String id) {

		Item tempItem = new Item();
		tempItem.setItemId(id);

		itm.changeHampStatus(tempItem);

		return new ModelAndView("closet", "msg", "Your item added to closet!");
	}

	ArrayList<Item> predictedOutfit = null;

	@RequestMapping("home")
	public ModelAndView getFashionCast(Model model) {

		model.addAttribute("cityState", ourAPI.getCityState());
		model.addAttribute("temp", ourAPI.getTemp_f());
		model.addAttribute("wind", ourAPI.getWind_gust_mph());
		model.addAttribute("precip", ourAPI.getPrecip_today_in());
		model.addAttribute("weather", ourAPI.getWeather());
		model.addAttribute("icon_url", ourAPI.getIcon_URL());
		model.addAttribute("humidity", ourAPI.getRelative_humidity());
		
		ArrayList<Item> predictedOutfit = Apparel.generateOutfit(itm, currentUser, ourAPI);

		return new ModelAndView("fashionCast", "outfitItems", predictedOutfit);

		// TODO ADD ${outfitItems} EL tag in fashionCast file and weird core tags to
		// loop through/display all photos
	}


}