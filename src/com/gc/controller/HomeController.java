package com.gc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.gc.model.Category;
import com.gc.model.DAOItemImpl;
import com.gc.model.DAOUserImpl;
import com.gc.model.InHamp;
import com.gc.model.Item;
import com.gc.model.User;
import com.gc.util.API;
import com.gc.util.ApiCredentials;

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

	@RequestMapping("existingUserLogin")
	public ModelAndView loginUser(HttpSession session, @RequestParam("email") String email,
			@RequestParam("password") String password) {
		
		System.out.println("existingUserLogin called");
		
		String pageToReturn = "Login";
		String msg;

		try {
			User currentUser = usr.getUser(email);
<<<<<<< HEAD
			
=======
>>>>>>> 12998df4950d67855b9b195236221ff1710590c7
			System.out.println(currentUser);

			if (currentUser.getPassword().equals(password)) {
				pageToReturn = "welcome";
				msg = currentUser.getFirstName();
				session.setAttribute("user1", currentUser);
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
	public ModelAndView createUser(HttpSession session, @RequestParam("fname") String firstName,
			@RequestParam("lname") String lastName, @RequestParam("email") String email,
			@RequestParam("password") String password) {

		User tempUser = new User(firstName, lastName, email, password);
		session.setAttribute("user1", tempUser);

		usr.createUser(tempUser);

		return new ModelAndView("fillCloset", "name", firstName);

	}
	
	@RequestMapping("addToCloset")
	public String giveFillClosetPage() {
		return "fillCloset";
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
	//public String addItem(@ModelAttribute("user1") User user1, @RequestParam("imageURL")MultipartFile file,
	public String addItem(@ModelAttribute("user1") User user1, @RequestParam("imageURL") MultipartFile file,
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
		
		Item tempItem = new Item("F", user1.getUserId(), type, desc, url, cat);
		System.out.println(tempItem.getCategory());

		itm.addItem(tempItem);
		

		return "itemAdded";
	}

	@RequestMapping("addAnother")
	public String giveClosetFormAgain() {
		return "fillCloset";
	}

	@RequestMapping("viewCloset")
	public ModelAndView viewCloset(@ModelAttribute("user1") User user1) {

		ArrayList<Item> userCloset = itm.getAllItems(user1);

		return new ModelAndView("closet", "clothes", userCloset);
		// TODO ADD ${clothes} EL tag in closet.jsp, with weird 'core' tags to loop
		// through...
	}

	// this mapping works with each item having their own delete button. not ideal
	// but...
	@RequestMapping("deleteItem")
	// THIS 'ID' PARAM SNEAKILY PASSED IN FROM FORM USING ~URL ENCODING~
	public ModelAndView deleteItem(@RequestParam("id") String id, @ModelAttribute("user1") User user1) {

		Item tempItem = new Item();
		tempItem.setItemId(id);

		itm.deleteItem(tempItem);
		
		ArrayList<Item> userCloset = itm.getAllItems(user1);

		return new ModelAndView("closet", "clothes", userCloset);
	}

	@RequestMapping("putInHamp")
	// THIS 'ID' PARAM SNEAKILY PASSED IN FROM FORM USING ~URL ENCODING~
	public ModelAndView putInHamp(@RequestParam("id") String id, @ModelAttribute("user1") User user1) {
		Item tempItem = new Item();
		tempItem.setItemId(id);

		itm.changeHampStatus(tempItem);

		return new ModelAndView("hamper", "hamperItems", itm.getHamperItems(user1));
	}

	@RequestMapping("viewHamp")
	public ModelAndView viewHamper(@ModelAttribute("user1") User user1) {

		return new ModelAndView("hamper", "hamperItems", itm.getHamperItems(user1));

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

	ArrayList<Item> predictedOutfit = new ArrayList<Item>();

	@RequestMapping("home")
	public ModelAndView getFashionCast(@ModelAttribute("user1") User user1) {
		ArrayList<Item> allItems = itm.getAllItems(user1);

		Random randomGenerator;
		randomGenerator = new Random();

		// ACCESSORIES
		// IF PRECIPITATION
		if (Double.parseDouble(ourAPI.getPrecip_today_in()) > 0.0) {
			// GRAB UMBRELLA
			for (Item item : allItems) {
				if (item.getType().equals("umbrella")) {
					predictedOutfit.add(item);
					break;
				}
			}
		}

		// HOT
		if (ourAPI.getTemp_f() >= 80.0) {
			ArrayList<Item> hotTops = new ArrayList<Item>();
			ArrayList<Item> hotBottoms = new ArrayList<Item>();
			ArrayList<Item> hotShoes = new ArrayList<Item>();

			// SET SHOES
			// IF PRECIPITATION
			if (Double.parseDouble(ourAPI.getPrecip_today_in()) > 0.0) {
				// SHOES MUST BE WATERPROOF
				for (Item item : allItems) {
					if (item.getType().equals("waterproofBoots")) {
						hotShoes.add(item);
					}
				}
			}
			// ELSE IF NO PRECIPITATION
			else {
				// SHOES MUST BE SANDALS, SNEAKERS, FLATS
				for (Item item : allItems) {
					if (item.getType().equals("sandals")) {
						hotShoes.add(item);
					} else if (item.getType().equals("sneakers")) {
						hotShoes.add(item);
					} else if (item.getType().equals("flats")) {
						hotShoes.add(item);
					}
				}
			}
			// SET OUTFIT
			for (Item item : allItems) {
				// TOP MUST BE TANK TOP OR T-SHIRT
				if (item.getType().equals("tankTop")) {
					hotTops.add(item);
				} else if (item.getType().equals("tshirt")) {
					hotTops.add(item);
				}
				// BOTTOMS MUST BE SHORTS OR SKIRT
				else if (item.getType().equals("shorts")) {
					hotBottoms.add(item);
				} else if (item.getType().equals("skirt")) {
					hotBottoms.add(item);
				}

			}

			int index = randomGenerator.nextInt(hotTops.size());
			Item hotTopPick = hotTops.get(index);
			predictedOutfit.add(hotTopPick);

			index = randomGenerator.nextInt(hotBottoms.size());
			Item hotBottomsPick = hotBottoms.get(index);
			predictedOutfit.add(hotBottomsPick);

			index = randomGenerator.nextInt(hotShoes.size());
			Item hotShoesPick = hotShoes.get(index);
			predictedOutfit.add(hotShoesPick);

		}

		// WARM
		else if (ourAPI.getTemp_f() >= 70.0) {
			ArrayList<Item> warmTops = new ArrayList<Item>();
			ArrayList<Item> warmBottoms = new ArrayList<Item>();
			ArrayList<Item> warmShoes = new ArrayList<Item>();
			// SET SHOES
			// IF PRECIPITATION
			if (Double.parseDouble(ourAPI.getPrecip_today_in()) > 0.0) {
				// SHOES MUST BE WATERPROOF
				for (Item item : allItems) {
					if (item.getType().equals("waterproofBoots")) {
						warmShoes.add(item);
					}
				}
			}
			// ELSE IF NO PRECIPITATION
			else {
				// SHOES MUST BE SANDALS, SNEAKERS, FLATS
				for (Item item : allItems) {
					if (item.getType().equals("sandals")) {
						warmShoes.add(item);
					} else if (item.getType().equals("sneakers")) {
						warmShoes.add(item);
					} else if (item.getType().equals("flats")) {
						warmShoes.add(item);
					}
				}
			}
			// SET OUTFIT
			for (Item item : allItems) {
				// TOP MUST BE TSHIRT
				if (item.getType().equals("tshirt")) {
					warmTops.add(item);

				}
				// BOTTOMS MUST BE SHORTS, SKIRT OR CAPRIS
				else if (item.getType().equals("shorts")) {
					warmBottoms.add(item);
				} else if (item.getType().equals("skirt")) {
					warmBottoms.add(item);
				} else if (item.getType().equals("capris")) {
					warmBottoms.add(item);
				}
			}

			int index = randomGenerator.nextInt(warmTops.size());
			Item warmTopPick = warmTops.get(index);
			predictedOutfit.add(warmTopPick);

			index = randomGenerator.nextInt(warmBottoms.size());
			Item warmBottomsPick = warmBottoms.get(index);
			predictedOutfit.add(warmBottomsPick);

			index = randomGenerator.nextInt(warmShoes.size());
			Item warmShoesPick = warmShoes.get(index);
			predictedOutfit.add(warmShoesPick);

		}

		// MILD
		else if (ourAPI.getTemp_f() >= 60.0) {
			ArrayList<Item> mildTops = new ArrayList<Item>();
			ArrayList<Item> mildBottoms = new ArrayList<Item>();
			ArrayList<Item> mildShoes = new ArrayList<Item>();
			ArrayList<Item> mildLayers = new ArrayList<Item>();

			// SET SHOES
			// IF PRECIPITATION
			if (Double.parseDouble(ourAPI.getPrecip_today_in()) > 0.0) {
				// SHOES MUST BE WATERPROOF
				for (Item item : allItems) {
					if (item.getType().equals("waterproofBoots")) {
						mildShoes.add(item);
					}
				}
			}
			// ELSE IF NO PRECIPITATION
			else {
				// SHOES MUST BE BOOTS OR SNEAKERS
				for (Item item : allItems) {
					if (item.getType().equals("boots")) {
						mildShoes.add(item);
					} else if (item.getType().equals("sneakers")) {
						mildShoes.add(item);
					}
				}
			}
			// SET OUTFIT
			for (Item item : allItems) {
				// TOP MUST BE TSHIRT (WITH LAYER)
				if (item.getType().equals("tshirt")) {
					mildTops.add(item);
				}
				// BOTTOM MUST BE CAPRIS, PANTS, JEANS OR LEGGINGS
				else if (item.getType().equals("capris")) {
					mildBottoms.add(item);
				} else if (item.getType().equals("pants")) {
					mildBottoms.add(item);
				} else if (item.getType().equals("jeans")) {
					mildBottoms.add(item);
				} else if (item.getType().equals("leggings")) {
					mildBottoms.add(item);
				}

				// LAYER MUST BE ZIP UP OR CARDIGAN (?)
				else if (item.getType().equals("zipUp")) {
					mildLayers.add(item);
				} else if (item.getType().equals("cardigan")) {
					mildLayers.add(item);
				}

			}

			int index = randomGenerator.nextInt(mildTops.size());
			Item mildTopPick = mildTops.get(index);
			predictedOutfit.add(mildTopPick);

			index = randomGenerator.nextInt(mildBottoms.size());
			Item mildBottomsPick = mildBottoms.get(index);
			predictedOutfit.add(mildBottomsPick);

			index = randomGenerator.nextInt(mildShoes.size());
			Item mildShoesPick = mildShoes.get(index);
			predictedOutfit.add(mildShoesPick);

			index = randomGenerator.nextInt(mildLayers.size());
			Item mildLayerPick = mildLayers.get(index);
			predictedOutfit.add(mildLayerPick);

		}

		// CRISP
		else if (ourAPI.getTemp_f() >= 50.0) {
			ArrayList<Item> crispTops = new ArrayList<Item>();
			ArrayList<Item> crispBottoms = new ArrayList<Item>();
			ArrayList<Item> crispShoes = new ArrayList<Item>();
			ArrayList<Item> crispOuterwear = new ArrayList<>();

			// SET SHOES
			// IF PRECIPITATION
			if (Double.parseDouble(ourAPI.getPrecip_today_in()) > 0.0) {
				// SHOES MUST BE WATERPROOF
				for (Item item : allItems) {
					if (item.getType().equals("waterproofBoots")) {
						crispShoes.add(item);
					}
				}
			}
			// ELSE IF NO PRECIPITATION
			else {
				// SHOES MUST BE BOOTS OR SNEAKERS
				for (Item item : allItems) {
					if (item.getType().equals("boots")) {
						crispShoes.add(item);
					} else if (item.getType().equals("sneakers")) {
						crispShoes.add(item);
					}
				}
			}
			// SET OUTFIT
			for (Item item : allItems) {
				// TOP MUST BE TSHIRT (WILL HAVE OUTERWEAR)
				// ANY OTHER TOPS HERE?
				if (item.getType().equals("tshirt")) {
					crispTops.add(item);
				}
				// BOTTOMS MUST BE PANTS, JEANS OR LEGGINGS
				else if (item.getType().equals("pants")) {
					crispBottoms.add(item);
				} else if (item.getType().equals("jeans")) {
					crispBottoms.add(item);
				} else if (item.getType().equals("leggings")) {
					crispBottoms.add(item);
				}

				// OUTERWEAR MUST BE JACKET, PEA COAT, LEATHER JACKET, OR FLEECE JACKET
				else if (item.getType().equals("jacket")) {
					crispOuterwear.add(item);
				} else if (item.getType().equals("peaCoat")) {
					crispOuterwear.add(item);
				} else if (item.getType().equals("leatherJacket")) {
					crispOuterwear.add(item);
				} else if (item.getType().equals("fleeceJacket")) {
					crispOuterwear.add(item);
				}
			}

			int index = randomGenerator.nextInt(crispTops.size());
			Item crispTopPick = crispTops.get(index);
			predictedOutfit.add(crispTopPick);

			index = randomGenerator.nextInt(crispBottoms.size());
			Item crispBottomsPick = crispBottoms.get(index);
			predictedOutfit.add(crispBottomsPick);

			index = randomGenerator.nextInt(crispShoes.size());
			Item crispShoesPick = crispShoes.get(index);
			predictedOutfit.add(crispShoesPick);

			index = randomGenerator.nextInt(crispOuterwear.size());
			Item crispOuterwearPick = crispOuterwear.get(index);
			predictedOutfit.add(crispOuterwearPick);

			// NO LAYER AT THIS TEMP?

		}

		// COOL
		else if (ourAPI.getTemp_f() >= 40.0) {
			ArrayList<Item> coolTops = new ArrayList<Item>();
			ArrayList<Item> coolBottoms = new ArrayList<Item>();
			ArrayList<Item> coolShoes = new ArrayList<Item>();
			ArrayList<Item> coolLayer = new ArrayList<Item>();
			ArrayList<Item> coolOuterwear = new ArrayList<Item>();
			// SET SHOES
			// IF PRECIPITATION
			if (Double.parseDouble(ourAPI.getPrecip_today_in()) > 0.0) {
				// SHOES MUST BE WATERPROOF
				for (Item item : allItems) {
					if (item.getType().equals("waterproofBoots")) {
						coolShoes.add(item);
					}
				}
			}
			// ELSE IF NO PRECIPITATION
			else {
				// SHOES MUST BE BOOTS
				for (Item item : allItems) {
					if (item.getType().equals("boots")) {
						coolShoes.add(item);
					}
				}
			}
			// SET OUTFIT
			for (Item item : allItems) {
				// TOP MUST BE TSHIRT (WITH LAYER)
				// ANY OTHER TOPS HERE?
				if (item.getType().equals("tshirt")) {
					coolTops.add(item);
				}

				// BOTTOMS MUST BE PANTS OR JEANS
				else if (item.getType().equals("pants")) {
					coolBottoms.add(item);
				} else if (item.getType().equals("jeans")) {
					coolBottoms.add(item);
				}

				// LAYER MUST BE ZIP UP OR CARDIGAN
				else if (item.getType().equals("zipUp")) {
					coolLayer.add(item);
				} else if (item.getType().equals("cardigan")) {
					coolLayer.add(item);
				}
				// OUTERWEAR MUST BE WINTER COAT
				else if (item.getType().equals("winterCoat")) {
					coolOuterwear.add(item);
				}

			}
			int index = randomGenerator.nextInt(coolTops.size());
			Item coolTopPick = coolTops.get(index);
			predictedOutfit.add(coolTopPick);

			index = randomGenerator.nextInt(coolBottoms.size());
			Item coolBottomsPick = coolBottoms.get(index);
			predictedOutfit.add(coolBottomsPick);

			index = randomGenerator.nextInt(coolShoes.size());
			Item coolShoesPick = coolShoes.get(index);
			predictedOutfit.add(coolShoesPick);

			index = randomGenerator.nextInt(coolLayer.size());
			Item coolLayerPick = coolLayer.get(index);
			predictedOutfit.add(coolLayerPick);

			index = randomGenerator.nextInt(coolOuterwear.size());
			Item coolOuterwearPick = coolOuterwear.get(index);
			predictedOutfit.add(coolOuterwearPick);

		}

		// COLD
		else if (ourAPI.getTemp_f() >= 30.0) {
			ArrayList<Item> coldTops = new ArrayList<Item>();
			ArrayList<Item> coldBottoms = new ArrayList<Item>();
			ArrayList<Item> coldShoes = new ArrayList<Item>();
			ArrayList<Item> coldLayer = new ArrayList<Item>();
			ArrayList<Item> coldOuterwear = new ArrayList<Item>();
			// GET SHOES
			// IF PRECIPITATION
			if (Double.parseDouble(ourAPI.getPrecip_today_in()) > 0.0) {
				// SHOES MUST BE WATERPROOF
				for (Item item : allItems) {
					if (item.getType().equals("waterproofBoots")) {
						coldShoes.add(item);
					}
				}
			}
			// ELSE IF NO PRECIPITATION
			else {
				// SHOES MUST BE BOOTS
				for (Item item : allItems) {
					if (item.getType().equals("boots")) {
						coldShoes.add(item);
					}
				}
			}
			// GET OUTFIT
			for (Item item : allItems) {
				// TOP MUST BE TSHIRT (WITH LAYER)
				// WHAT OTHER SHIRTS SHOULD GO HERE?
				if (item.getType().equals("tshirt")) {
					coldTops.add(item);
				}
				// BOTTOMS MUST BE PANTS OR JEANS
				else if (item.getType().equals("pants")) {
					coldBottoms.add(item);
				} else if (item.getType().equals("jeans")) {
					coldBottoms.add(item);
				}

				// LAYER CAN BE OF ANY TYPE
				else if (item.getType().equals("zipUp")) {
					coldLayer.add(item);
				} else if (item.getType().equals("cardigan")) {
					coldLayer.add(item);
				} else if (item.getType().equals("crewneck")) {
					coldLayer.add(item);
				} else if (item.getType().equals("hoody")) {
					coldLayer.add(item);
				} else if (item.getType().equals("sweatshirt")) {
					coldLayer.add(item);
				}

				// OUTERWEAR MUST BE WINTER COAT OR PARKA
				else if (item.getType().equals("winterCoat")) {
					coldOuterwear.add(item);
				} else if (item.getType().equals("parka")) {
					coldOuterwear.add(item);
				}

			}
			int index = randomGenerator.nextInt(coldTops.size());
			Item coldTopPick = coldTops.get(index);
			predictedOutfit.add(coldTopPick);

			index = randomGenerator.nextInt(coldBottoms.size());
			Item coldBottomsPick = coldBottoms.get(index);
			predictedOutfit.add(coldBottomsPick);

			index = randomGenerator.nextInt(coldShoes.size());
			Item coldShoesPick = coldShoes.get(index);
			predictedOutfit.add(coldShoesPick);

			index = randomGenerator.nextInt(coldLayer.size());
			Item coldLayerPick = coldLayer.get(index);
			predictedOutfit.add(coldLayerPick);

			index = randomGenerator.nextInt(coldOuterwear.size());
			Item coldOuterwearPick = coldOuterwear.get(index);
			predictedOutfit.add(coldOuterwearPick);

		}

		// FREEZING AND BEYOND
		else if (ourAPI.getTemp_f() < 30.0) {
			ArrayList<Item> freezingTops = new ArrayList<Item>();
			ArrayList<Item> freezingBottoms = new ArrayList<Item>();
			ArrayList<Item> freezingShoes = new ArrayList<Item>();
			ArrayList<Item> freezingLayer = new ArrayList<Item>();
			ArrayList<Item> freezingOuterwear = new ArrayList<Item>();
			// GET SHOES
			// IF PRECIPITATION
			if (Double.parseDouble(ourAPI.getPrecip_today_in()) > 0.0) {
				// SHOES MUST BE WATERPROOF
				for (Item item : allItems) {
					if (item.getType().equals("waterproofBoots")) {
						freezingShoes.add(item);
					}
				}
			}
			// ELSE IF NO PRECIPITATION
			else {
				// SHOES MUST BE BOOTS
				for (Item item : allItems) {
					if (item.getType().equals("boots")) {
						freezingShoes.add(item);
					}
				}
			}
			// GET OUTFIT
			for (Item item : allItems) {
				// TOP MUST BE TSHIRT (WITH LAYER)
				// WHAT OTHER SHIRTS SHOULD GO HERE?
				if (item.getType().equals("tshirt")) {
					freezingTops.add(item);
				}
				// BOTTOMS MUST BE PANTS OR JEANS
				else if (item.getType().equals("pants")) {
					freezingBottoms.add(item);
				} else if (item.getType().equals("jeans")) {
					freezingBottoms.add(item);
				}

				// LAYER CAN BE OF ANY TYPE
				else if (item.getType().equals("zipUp")) {
					freezingLayer.add(item);
				} else if (item.getType().equals("cardigan")) {
					freezingLayer.add(item);
				} else if (item.getType().equals("crewneck")) {
					freezingLayer.add(item);
				} else if (item.getType().equals("hoody")) {
					freezingLayer.add(item);
				} else if (item.getType().equals("sweatshirt")) {
					freezingLayer.add(item);
				}

				// OUTERWEAR MUST BE PARKA
				else if (item.getType().equals("parka")) {
					freezingOuterwear.add(item);
				}
			}

			int index = randomGenerator.nextInt(freezingTops.size());
			Item freezingTopPick = freezingTops.get(index);
			predictedOutfit.add(freezingTopPick);

			index = randomGenerator.nextInt(freezingBottoms.size());
			Item freezingBottomsPick = freezingBottoms.get(index);
			predictedOutfit.add(freezingBottomsPick);

			index = randomGenerator.nextInt(freezingShoes.size());
			Item freezingShoesPick = freezingShoes.get(index);
			predictedOutfit.add(freezingShoesPick);

			index = randomGenerator.nextInt(freezingLayer.size());
			Item freezingLayerPick = freezingLayer.get(index);
			predictedOutfit.add(freezingLayerPick);

			index = randomGenerator.nextInt(freezingOuterwear.size());
			Item freezingOuterwearPick = freezingOuterwear.get(index);
			predictedOutfit.add(freezingOuterwearPick);

		}

		return new ModelAndView("fashionCast", "outfitItems", predictedOutfit);

		// TODO ADD ${outfitItems} EL tag in fashionCast file and weird core tags to
		// loop through/display all photos
	}

	@RequestMapping("baseOutfitSelected")
	public ModelAndView baseOutfitChosen() {
		boolean hasLayer = false;
		boolean hasOuterwear = false;
		boolean hasAccessory = false;

		String nextPage;

		for (Item item : predictedOutfit) {
			if (item.getCategory().equals("SWEATER")){
				hasLayer = true;
			}
			if(item.getCategory().equals("OUTERWEAR")) {
				hasOuterwear = true;
			}
			if(item.getCategory().equals("ACCESSORY")) {
				hasAccessory = true;
			}
		}

		if (hasLayer) {
			nextPage = "addLayer";
		} else if (hasOuterwear) {
			nextPage = "addOuterwear";
		} else if (hasAccessory) {
			nextPage = "addAccessory";
		} else {
			nextPage = "readyToGo";
		}

		return new ModelAndView(nextPage, "", "something here");
	}

}