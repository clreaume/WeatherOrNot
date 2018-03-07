package com.gc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gc.model.DAOItemImpl;
import com.gc.model.DAOUserImpl;
import com.gc.model.Item;
import com.gc.model.User;

/**
 * 
 * author: WeatherOrNot
 *
 * 
 */
// wunderground api key: be4423cb67742fcc

@Controller
@SessionAttributes("user1")
public class HomeController {
	
	//DAO USER IMPLEMENTATION OBJECT
	DAOUserImpl usr = new DAOUserImpl();
	//DAO ITEM IMPLEMENTATION OBJECT
	DAOItemImpl itm = new DAOItemImpl();

	@RequestMapping("/") //THE STUFF BELOW SHOULD MOVE OUT OF THIS CONTROLLER
	public ModelAndView index(Model model) {
		String prodCenter = "";
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

			prodCenter = json.get("current_observation").toString();
			String test = json.getJSONObject("current_observation").getString("weather");
			String test2 = json.getJSONObject("current_observation").getJSONObject("image").getString("url");
			String test3 = json.getJSONObject("current_observation").getString("icon_url");

			System.out.println(test);
			System.out.println(test2);
			System.out.println(test3);

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

		return new ModelAndView("index", "centerData", prodCenter);
	}
	
	
	@RequestMapping("getsignup")
	public String giveSignUp() {
		return "signupform";
	}
	

	
	@RequestMapping ("createuser")
	public ModelAndView createUser(HttpSession session, @RequestParam("fname") String firstName, @RequestParam("lname") String lastName, 
			@RequestParam("email") String email, @RequestParam("password") String password) {
		
		
	
		User tempUser = new User(firstName, lastName, email, password);
		session.setAttribute("user1", tempUser);
		
		usr.createUser(tempUser);
		
		return new ModelAndView("fillCloset", "name", firstName);
		//TODO Add ${name} EL tag to fillCloset - "Welcome x! Let's fill your closet"
		
	}
	
	@RequestMapping("getItemInputForm")
	public String getItemInputForm(@RequestParam("itemOfClothing") String itemChosen) {
		String formToReturn;
		
		switch (itemChosen) {
		case "top":
			formToReturn = "topForm";
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
	
	@RequestMapping("addItem")
	public String addItem(@ModelAttribute("user1") User user1, @RequestParam("imageURL") String url, @RequestParam("type") String type, @RequestParam("description") String desc) {
		
		Item tempItem = new Item(false, user1.getUserId(), type, desc, url);
		itm.addItem(tempItem);

		return "itemAdded";
	}
	
	@RequestMapping("addAnother")
	public String giveClosetFormAgain() {
		return "fillCloset";
	}
	
	@RequestMapping("viewCloset")
	public ModelAndView viewCloset() {
		
		ArrayList<Item> userCloset = itm.getAllItems();
		
		return new ModelAndView("closet", "clothes", userCloset);
		//TODO ADD ${clothes} EL tag in closet.jsp, with weird 'core' tags to loop through...
	}
	
	//this mapping works with each item having their own delete button. not ideal but...
	@RequestMapping("deleteItem") 
	//THIS 'ID' PARAM SNEAKILY PASSED IN FROM FORM USING ~URL ENCODING~
	public ModelAndView deleteItem(@RequestParam("id") String id) {
		
		Item tempItem = new Item();
		tempItem.setItemId(id);
		
		itm.deleteItem(tempItem);
		
		return new ModelAndView("closet", "msg", "Your " + tempItem.getType() + "has been deleted. Your updated closet: ");
		//TODO add ${msg} EL tag in closet.jsp - at top, above printing out of pictures
	}
	
	@RequestMapping("putInHamp")
	//THIS 'ID' PARAM SNEAKILY PASSED IN FROM FORM USING ~URL ENCODING~
	public ModelAndView putInHamp(@RequestParam("id") String id) {
		Item tempItem = new Item();
		tempItem.setItemId(id);
		
		itm.changeHampStatus(tempItem);
		
		return new ModelAndView("hamper", "hamperItems", itm.getHamperItems());
	}
	
	@RequestMapping("viewHamp")
	public ModelAndView viewHamper() {
				
		return new ModelAndView("hamper", "hamperItems", itm.getHamperItems());
		//TODO add ${hamperItems} EL tag to hamper page & use weird core thing to print each out...
	}
	
	@RequestMapping("putInCloset")
	//THIS 'ID' PARAM SNEAKILY PASSED IN FROM FORM USING ~URL ENCODING~
	public ModelAndView putInCloset(@RequestParam("id") String id) {
		
		Item tempItem = new Item();
		tempItem.setItemId(id);
		
		itm.changeHampStatus(tempItem);
	
		return new ModelAndView("closet", "msg", "Your item added to closet!");
		//TODO add ${msg} EL tag in closet.jsp - at top, above printing out of pictures
	}
	
	boolean layerNeeded;
	boolean outerwearNeeded;
	boolean accessoryNeeded;
	
	@RequestMapping("home")
	public ModelAndView getFashionCast() {
		
		ArrayList<Item> predictedOutfit = null;
		
		//SOME ALGORITHM HERE
		//ADD ITEMS RETRIEVED TO predictedOutfit ARRAY LIST?
		
		//set layerNeeded, outerwearNeeded, accessoryNeeded
		
		return new ModelAndView("fashionCast", "", predictedOutfit);
	}
	
	@RequestMapping("baseOutfitSelected")
	public ModelAndView baseOutfitChosen() {

		String nextPage;
		
		if (layerNeeded) {
			nextPage = "addLayer";
		}
	
		else if (outerwearNeeded) {
			nextPage = "addOuterwear";
		}
		
		else if (accessoryNeeded) {
			nextPage = "addAccessory";
		}
		
		else {
			nextPage = "readyToGo";
		}
		
		return new ModelAndView(nextPage, "", "something here");
	}
	
	
	
	
	

	

}