package com.gc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.web.servlet.ModelAndView;

import com.gc.model.DAOItemImpl;
import com.gc.model.Item;
import com.gc.model.User;

public class Apparel {
	
	public static HashMap<String, ArrayList<Item>> getClosetMap(DAOItemImpl itm, User currentUser) {
		ArrayList<Item> userCloset = itm.getClosetItems(currentUser);
		ArrayList<Item> userTops = new ArrayList<Item>();
		ArrayList<Item> userSweaters = new ArrayList<Item>();	
		ArrayList<Item> userBottoms = new ArrayList<Item>();	
		ArrayList<Item> userOuterwear = new ArrayList<Item>();	
		ArrayList<Item> userShoes = new ArrayList<Item>();	
		ArrayList<Item> userAccessories = new ArrayList<Item>();
				
		for (Item item : userCloset) {
			if (item.getCategory().equals("TOP")) {
				userTops.add(item);
			}
			if (item.getCategory().equals("SWEATER")) {
				userSweaters.add(item);
			}
			if (item.getCategory().equals("BOTTOM")) {
				userBottoms.add(item);
			}
			if (item.getCategory().equals("OUTERWEAR")) {
				userOuterwear.add(item);
			}
			if (item.getCategory().equals("SHOE")) {
				userShoes.add(item);
			}
			if (item.getCategory().equals("ACCESSORY")) {
				userAccessories.add(item);
			}
		}
		
		HashMap<String, ArrayList<Item>> clothesInCloset = new HashMap<String, ArrayList<Item>>();
		clothesInCloset.put("Tops", userTops);
		clothesInCloset.put("Bottoms", userBottoms);
		clothesInCloset.put("Sweaters", userSweaters);
		clothesInCloset.put("Outerwear", userOuterwear);
		clothesInCloset.put("Shoes", userShoes);
		clothesInCloset.put("Accessories", userAccessories);
		
		return clothesInCloset;
	}
	
	public static HashMap<String, ArrayList<Item>> getHamperMap(DAOItemImpl itm, User currentUser) {
		ArrayList<Item> userHamper = itm.getHamperItems(currentUser);
		ArrayList<Item> userTops = new ArrayList<Item>();
		ArrayList<Item> userSweaters = new ArrayList<Item>();	
		ArrayList<Item> userBottoms = new ArrayList<Item>();	
		ArrayList<Item> userOuterwear = new ArrayList<Item>();	
		ArrayList<Item> userShoes = new ArrayList<Item>();	
		ArrayList<Item> userAccessories = new ArrayList<Item>();
				
		for (Item item : userHamper) {
			if (item.getCategory().equals("TOP")) {
				userTops.add(item);
			}
			if (item.getCategory().equals("SWEATER")) {
				userSweaters.add(item);
			}
			if (item.getCategory().equals("BOTTOM")) {
				userBottoms.add(item);
			}
			if (item.getCategory().equals("OUTERWEAR")) {
				userOuterwear.add(item);
			}
			if (item.getCategory().equals("SHOE")) {
				userShoes.add(item);
			}
			if (item.getCategory().equals("ACCESSORY")) {
				userAccessories.add(item);
			}
		}
		
		HashMap<String, ArrayList<Item>> clothesInHamper = new HashMap<String, ArrayList<Item>>();
		clothesInHamper.put("Tops", userTops);
		clothesInHamper.put("Bottoms", userBottoms);
		clothesInHamper.put("Sweaters", userSweaters);
		clothesInHamper.put("Outerwear", userOuterwear);
		clothesInHamper.put("Shoes", userShoes);
		clothesInHamper.put("Accessories", userAccessories);
		
		return clothesInHamper;
	}
	
	
	//TO GENERATE SUGGESTED OUTFIT
	public static ArrayList<Item> generateOutfit(DAOItemImpl itm, User currentUser, API ourAPI){

		ArrayList<Item> allItems = itm.getClosetItems(currentUser);
		ArrayList<Item> predictedOutfit = new ArrayList<Item>();

		Random randomGenerator;
		randomGenerator = new Random();
		
		// ACCESSORIES
		// IF PRECIPITATION
		try {
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
				
				//System.out.println("size of freezing outerwear arraylist: " + freezingOuterwear.size());
				index = randomGenerator.nextInt(freezingOuterwear.size());
				//System.out.println("index" + index);
				Item freezingOuterwearPick = freezingOuterwear.get(index);
				predictedOutfit.add(freezingOuterwearPick);

			}
		} catch (NullPointerException e) {
			System.out.println("Sorry all your clothes got infested with lice");
			e.printStackTrace();
		} catch(IllegalArgumentException i) {
			System.out.println("error");
			//return new ModelAndView("error", "errorMsg", "Your closet does not contain items appropriate for the current weather conditions. Please add more items or stay inside!");
		}
		
		return predictedOutfit;
	}

}
