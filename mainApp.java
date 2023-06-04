/*
* OMADA 069
* 
* MATSAS MARIO			p3220120
* PAPADHMAS BALISLEIOS p3220150
* TOMARAS NIKOLAOS 	p3220202
*/

import java.io.File;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

public class mainApp {

	public Collection<AdAgency> AdAgencies;
	public Collection<Product> Products;
	public Collection<AdType> AdTypes;
	public Collection<Ad> Ads;

	public static Scanner sc;

	public static int randomInteger(int min, int max) {
		if (min > max) {
			System.out.println("Error: Minimum bigger than maximum returning 0"); // TODO: Consider printing all error
																					// messages to STDERR
			return 0;
		}
		// math.random = 0.0 to 0.999
		//
		// if max = 10 and min = 3
		// max - min + 1 = 8
		// thus we get a random number from 0.0 to 0.99999 and
		// get it to the range 0.0 to 7.999
		// then add minimum = 3
		// our final number is in range 3.0 to 10.999
		// in the end we floor the value and we get a value from min = 3.0 to max = 10.0
		return (int) (Math.random() * (max + 1 - min) + min);
	}

	public static int randomInteger(int max) {
		return randomInteger(0, max);
	}

	public static int randomInteger() {
		return randomInteger(0, 1);
	}

	// Reads an integer with a prompt text
	// if a non integer is given then we retry getting input again with the same
	// prompt

	public static int readNextIntegerWithPrompt(String prompt, int checkAuto) {

		while (true) {
			try {
				// Check if overall input is valid by catching the exception
				System.out.print("\n" + prompt + ": ");

				int x = Integer.parseInt(sc.nextLine());
				// Check if the AutoShow input is valid
				if (checkAuto == 1) {
					if (x == 0 || x == 1) {
						return x;
					}
					continue;
				}
				return x;
			} catch (NumberFormatException e) {
				continue;
			}

		}
	}

	// Get a string list of inputs we also pass isNumerical boolean list
	// to indicate what each of the inputs should be
	// if an element of isNumerical is true then that means we need to get an
	// Integer value
	// thus we call function readNextInteger which ensures we get an integer for
	// input and convert it to String
	// else it gets a string for input

	String[] getManyInputs(String[] prompts, Boolean[] isNumerical, int TINcheck, int AutoCheck) {
		String[] results = new String[prompts.length];
		for (int i = 0; i < prompts.length; i++) {
			if (isNumerical[i]) {

				results[i] = Integer.toString(readNextIntegerWithPrompt(prompts[i], AutoCheck));
				continue;
			} else {
				/*
				 * The TIN is neither a number nor a String. It contains numerical values,
				 * however it can also represent
				 * something like '000123456' which while numerical, isn't a number and thus
				 * requires a special check
				 */

				if (TINcheck == 1 && i == 0) {
					while (true) {
						System.out.print("\n" + prompts[i] + ": ");
						String choice = sc.nextLine();
						// Create a list that contains all the agancy TINs
						ArrayList<String> tins = new ArrayList<>();
						for (int j = 0; j < AdAgencies.getLength(); j++) {
							tins.add(AdAgencies.get(j).getUniqueIdentifier());
						}
						// Check if the input contains only numerical values
						boolean isValid = choice.matches("[0-9]+") &&
								choice.length() == 9 && !tins.contains(choice);
						if (isValid) {
							results[i] = choice;
							break;
						}
					}
				} else {
					System.out.print("\n" + prompts[i] + ": ");
					results[i] = sc.nextLine();
				}
			}
		}
		return results;
	}

	// Choose one item from a collecion eg an agent from agent collections or a
	// product from products collection
	// Returns -1 if options is empty or null
	int chooseOne(Collection<?> options) {

		ArrayList<String> tins = new ArrayList<>();
		for (int i = 0; i < options.getLength(); i++) {
			tins.add(options.get(i).getUniqueIdentifier());
			tins.sort(null);
		}

		for (int i = 0; i < options.getLength(); i++) {
			for (int j = 0; j < options.getLength(); j++) {
				if (options.get(j).getUniqueIdentifier().equals(tins.get(i))) {
					System.out.println("[" + options.get(j).getUniqueIdentifier() + "]" + options.get(j).getName());
				} else {
					continue;
				}
			}
		}

		if (options == null || options.getLength() == 0) {
			System.out.println("Collection is empty or null.");
			return -1;
		}

		while (true) {
			System.out.print("\nWhich one? ");
			int indexFound = -1;
			try {
				// Check if the passed value is a number
				int givenCode = Integer.parseInt(sc.nextLine());
				// Go through all the options in the Collection in order to find which option
				// actually has this code
				for (int i = 0; i < options.getLength(); i++) {
					String codeToCheck = options.get(i).getUniqueIdentifier();

					// Check if the tin matches
					if (Integer.valueOf(codeToCheck) == givenCode) {
						indexFound = i;
						break;
					}
				}

				if (indexFound != -1) {
					return indexFound;
				}
			} catch (NumberFormatException e) {
				continue;
			}
		}
	}

	// Prints a menu prompt and asks the user to select one of the many options

	int menuPrompt(String[] prompts) {
		System.out.println("Choose an option!");

		for (int i = 0; i < prompts.length; i++) {
			System.out.printf("[%d] %s\n", i, prompts[i]);
		}

		int choiceIndex = -1;

		do {
			try {
				System.out.print("Which one? ");
				choiceIndex = Integer.parseInt(sc.nextLine());

			} catch (NumberFormatException e) {
				continue;
			}
		} while (choiceIndex < 0 || choiceIndex > prompts.length - 1);

		return choiceIndex;
	}

	boolean isTypeChoiceValid(int typeChoice) {
		return typeChoice == AdType.ONLINE || typeChoice == AdType.PRINTED || typeChoice == AdType.RADIO_TV;
	}

	void repl() {
		String[] choices = null;
		int index = 0;
		String[] details = null;
		String[] prompts = null;

		boolean caseExit = false;

		int adTypeChoiceIndex = 0;
		int agentChoiceIndex = 0;
		int productChoiceIndex = 0;

		String adTypeChoiceCode = "";
		String agentChoiceCode = "";
		String productChoiceCode = "";

		int typeChoice = 0;

		while (true) {
			System.out.print("Options:\n"
					+ "[1] Enter new advertising agency\n"
					+ "[2] Enter new advertisement type\n"
					+ "[3] Enter new advertisement\n"
					+ "[4] Display all advertisements\n"
					+ "[5] Display all advertisements of a given advertising agency\n"
					+ "[6] Calculate the costs of a given advertising agency's advertisements\n"
					+ "[7] Display amount of advertisements per product\n"
					+ "[8] Calculate a given product's advertising cost\n"
					+ "[9] Display cost of advertising per product\n"
					+ "[0] Exit\n");
			choice: while (true) {
				switch (readNextIntegerWithPrompt("Your choice", 0)) {
					case 0:					
					Collection.saveInfo("Ads.txt", Ads, AdTypes, AdAgencies, Products);
					Collection.saveInfo("AdTypes.txt", Ads, AdTypes, AdAgencies, Products);
					Collection.saveInfo("AdAgencies.txt", Ads, AdTypes, AdAgencies, Products);
					Collection.saveInfo("Products.txt", Ads, AdTypes, AdAgencies, Products);
						System.exit(0);
					case 1:
						choices = getManyInputs(new String[] { "TIN", "Brand name" }, new Boolean[] { false, false }, 1,
								0);
						this.AdAgencies.push(new AdAgency(choices[0], choices[1]));
						break choice;
					case 2:
						do {
							System.out.print("Enter advertisement type Details:\n\n");

							// Select agent for this ad type
							agentChoiceIndex = chooseOne(AdAgencies);

							if (agentChoiceIndex == -1) {
								System.out.println("There are no ad agencies to connect this AdType to...");
								caseExit = true;
								break;
							}

							agentChoiceCode = AdAgencies.get(agentChoiceIndex).getUniqueIdentifier();

							// Select description for this ad type
							choices = getManyInputs(new String[] { "Description" }, new Boolean[] { false }, 0, 0);

							// Select ad type to create
							typeChoice = menuPrompt(new String[] { "Online", "Printed", "RadioTV" });
						} while (!isTypeChoiceValid(typeChoice));

						if (caseExit) {
							break choice;
						}

						switch (typeChoice) {
							case AdType.ONLINE:
								details = getManyInputs(new String[] { "Price per day", "Automatic display cost",
										"Price per extra page" }, new Boolean[] { true, true, true }, 0, 0);
								this.AdTypes.push(new OnlineAdType(this.AdTypes.getSequenceNumber(), choices[0],
										agentChoiceCode, Integer.parseInt(details[0]), Integer.parseInt(details[1]),
										Integer.parseInt(details[2])));
								break;
							case AdType.PRINTED:
								details = getManyInputs(
										new String[] { "Price per word on the first page",
												"Price per word in the middle of newspaper",
												"Price per word on the last page" },
										new Boolean[] { true, true, true }, 0, 0);
								this.AdTypes.push(new PrintedAdType(this.AdTypes.getSequenceNumber(), choices[0],
										agentChoiceCode, Integer.parseInt(details[0]), Integer.parseInt(details[1]),
										Integer.parseInt(details[2])));
								break;
							case AdType.RADIO_TV:
								details = getManyInputs(
										new String[] { "Price per second in the morning", "Price per second at noon",
												"Price per second in the afternoon",
												"Price per second in the evening" },
										new Boolean[] { true, true, true, true }, 0, 0);
								this.AdTypes.push(new RadioTVAdType(this.AdTypes.getSequenceNumber(), choices[0],
										agentChoiceCode, Integer.parseInt(details[0]), Integer.parseInt(details[1]),
										Integer.parseInt(details[2]), Integer.parseInt(details[3])));
								break;
						}
						break choice;
					case 3:
						do {
							// Select the AdType of this ad
							adTypeChoiceIndex = chooseOne(AdTypes);

							if (adTypeChoiceIndex == -1) {
								System.out.println("There are no AdTypes to connect this Ad to...");
								caseExit = true;
								break;
							}

							adTypeChoiceCode = AdTypes.get(adTypeChoiceIndex).getUniqueIdentifier();
							// Select Product for this ad
							productChoiceIndex = chooseOne(Products);

							if (productChoiceIndex == -1) {
								System.out.println("There are no Products to connect this Ad to...");
								caseExit = true;
								break;
							}

							productChoiceCode = Products.get(productChoiceIndex).getUniqueIdentifier();

							// Select properties of this ad
							choices = getManyInputs(new String[] { "Duration in days" }, new Boolean[] { true }, 0, 0);
							// Select ad type to create
							typeChoice = AdTypes.get(adTypeChoiceIndex).getTYPE();

						} while (!isTypeChoiceValid(typeChoice));

						if (caseExit) {
							break choice;
						}

						switch (typeChoice) {
							case AdType.ONLINE:
								// Get extra pages

								String[] extraPages = getManyInputs(new String[] { "Extra pages" },
										new Boolean[] { true }, 0, 0);

								this.Ads.push(new OnlineAd(adTypeChoiceCode, productChoiceCode,
										Integer.parseInt(choices[0]), extraPages[0],
										readNextIntegerWithPrompt("Autoshow ( [0] No [1] Yes )", 1)));
								break;
							case AdType.PRINTED:
								// Get Page Showcase

								prompts = new String[] { PrintedAdType.FIRST_PAGE,
										PrintedAdType.MIDDLE_PAGE,
										PrintedAdType.LAST_PAGE };

								index = menuPrompt(prompts);

								this.Ads.push(
										new PrintedAd(adTypeChoiceCode, productChoiceCode, Integer.parseInt(choices[0]),
												prompts[index], readNextIntegerWithPrompt("Words", 0)));

								break;
							case AdType.RADIO_TV:
								// Get Day Time

								prompts = new String[] { RadioTVAdType.MORNING,
										RadioTVAdType.NOON,
										RadioTVAdType.AFTERNOON,
										RadioTVAdType.EVENING };

								index = menuPrompt(prompts);

								this.Ads.push(
										new RadioTVAd(adTypeChoiceCode, productChoiceCode, Integer.parseInt(choices[0]),
												prompts[index], readNextIntegerWithPrompt("Duration in seconds", 0)));

								break;
						}
						break choice;
					case 4:
						for (int i = 0; i < this.AdAgencies.getLength(); i++) {
							CollectionManager.printAdsOf(this.AdAgencies.get(i));
						}
						break choice;
					case 5:
						CollectionManager.printAdsOf(this.AdAgencies.get(chooseOne(this.AdAgencies)));
						break choice;
					case 6:
						System.out.println("The total cost will be the sum of all the above.\nSo the total cost is: "
								+ CollectionManager.printAdCostFor(this.AdAgencies.get(chooseOne(this.AdAgencies)))
								+ " EURO");
						break choice;
					case 7:
						CollectionManager.printNumberOfAdsPerProduct();
						break choice;
					case 8:
						System.out.println("The total cost will be the sum of all the above.\nSo the total cost is: "
								+ CollectionManager.printAdCostFor(this.Products.get(chooseOne(this.Products)))
								+ " EURO");
						break choice;
					case 9:
						CollectionManager.printCostPerProduct();
						break choice;
				}
			}
			System.out.println();
		}
	}

	mainApp() {
		this.AdAgencies = new Collection<AdAgency>(AdAgency.class);
		this.Products = new Collection<Product>(Product.class);
		this.AdTypes = new Collection<AdType>(AdType.class);
		this.Ads = new Collection<Ad>(Ad.class);

		CollectionManager.bind(Ads, AdTypes, AdAgencies, Products);

		try{
			File adFile = new File("Ads.txt");
			File adTypeFile = new File("AdTypes.txt");
			File agencyFile = new File("AdAgencies.txt");
			File productFile = new File("Products.txt");

			if(!adFile.exists()){ //If the file doesnt exist, create it
				adFile.createNewFile();
			}
			else{
				this.Ads.populateFromFile("Ads.txt");
			}

			if(!adTypeFile.exists()){ //If the file doesnt exist, create it
				adTypeFile.createNewFile();
			}
			else{
				this.AdTypes.populateFromFile("AdTypes.txt");
			}

			if(!agencyFile.exists()){ //If the file doesnt exist, create it
				agencyFile.createNewFile();
			}
			else{
				this.AdAgencies.populateFromFile("AdAgencies.txt");
			}

			if(!productFile.exists()){ //If the file doesnt exist, create it
				productFile.createNewFile();
			}
			else{
				this.Products.populateFromFile("Products.txt");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		/*for (int i = 0; i < this.Products.getLength(); i++) {

			// 3 online ads for product i
			for (String typecode : new String[] { "0", "1", "2" }) {
				System.out.println("ADV\n"
				+"{\n"
				+"TYPE web\n"
				+"ADVTYPE_CODE " +typecode
				+"\nITEM_CODE "+this.Products.get(i).getProductCode()
				+"\nDURATION "+randomInteger(100)
				+"\nJustification “"+Integer.toString(randomInteger(10))+"”\n"
				+"c "+randomInteger(1)
				+"\n}");
			}

			// 3 printed ads for product i
			for (String typecode : new String[] { "3", "4", "5" }) {
				System.out.println("ADV\n"
				+"{\n"
				+"TYPE Print\n"
				+"ADVTYPE_CODE " +typecode
				+"\nITEM_CODE "+this.Products.get(i).getProductCode()
				+"\nDURATION "+randomInteger(100)
				+"\nJustification “"+(new String[] { PrintedAdType.FIRST_PAGE,
								PrintedAdType.MIDDLE_PAGE,
								PrintedAdType.LAST_PAGE })[randomInteger(2)]+"”\n"
				+"c "+randomInteger(60)
				+"\n}");
			}

			// 3 radio tv ads for product i
			for (String typecode : new String[] { "6", "7", "8" }) {
				System.out.println("ADV\n"
				+"{\n"
				+"TYPE Media\n"
				+"ADVTYPE_CODE " +typecode
				+"\nITEM_CODE "+this.Products.get(i).getProductCode()
				+"\nDURATION "+randomInteger(100)
				+"\nJustification “"+(new String[] { RadioTVAdType.MORNING,
								RadioTVAdType.NOON,
								RadioTVAdType.AFTERNOON,
								RadioTVAdType.EVENING })[randomInteger(3)]+"”\n"
				+"c "+randomInteger(60)
				+"\n}");
			}
		}*/
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		mainApp app = new mainApp();
		app.repl();

		sc.close();
		
	}
}
