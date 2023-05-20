/*
 * OMADA 069
 * 
 * MATSAS MARIO			p3220120
 * PAPADHMAS BALISLEIOS p3220150
 * TOMARAS NIKOLAOS 	p3220202
 */

import Ads.*;
import AdTypes.*;
import Collections.Collection;
import ComplimentaryClasses.*;
import java.lang.Math;
import java.util.Scanner;

public class mainApp {

	private static final int ONLINE = 0;
	private static final int PRINTED = 1;
	private static final int RADIO_TV = 2;


	public Collection<AdAgency> AdAgencies;
	public Collection<Product> Products;
	public Collection<AdType> AdTypes;
	public Collection<Ad> Ads;

	public static Scanner sc;

	public static int randomInteger(int min, int max)
	{
        if(min > max)
        {
            System.out.println("Error: Minimum bigger than maximum returning 0");
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
		return (int)(Math.random() * (max + 1 - min) + min);
	}

	public static int randomInteger(int max)
	{
		return randomInteger(0, max);
	}

	public static int randomInteger()
	{
		return randomInteger(0, 1);
	}

	// Reads an integer with a prompt text
	// if a non integer is given then we retry getting input again with the same prompt

	public static int readNextIntegerWithPrompt(String prompt) {

		while (true) {
			try {
				System.out.print("\n" + prompt + ": ");
    			return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				continue;
			}
		}
	}

	// Get a string list of inputs we also pass isNumerical boolean list
	// to indicate what each of the inputs should be
	// if an element of isNumerical is true then that means we need to get an Integer value 
	// thus we call function readNextInteger which ensures we get an integer for input and convert it to String
	// else it gets a string for input

	String[] getManyInputs(String[] prompts, Boolean[] isNumerical) {
		String[] results = new String[prompts.length];
		for (int i=0; i<prompts.length; i++) {
			if (isNumerical[i]) {

				results[i] = Integer.toString(readNextIntegerWithPrompt(prompts[i]));
				continue;
			} else {
				System.out.print("\n" + prompts[i]+": ");
				results[i] = sc.nextLine();
			}
		}
		return results;
	}

	
	// Choose one item from a collecion eg an agent from agent collections or a product from products collection
	// Returns -1 if options is empty or null
	int chooseOne(Collection<?> options) {
		
		for (int i=0; i < options.getLength(); i++) {
			System.out.println(options.get(i).getUniqueIdentifier() + ": " + options.get(i).getName());
		}

		if(options == null || options.getLength() == 0) {
			System.out.println("Collection is empty or null.");
			return -1;
		}

		while (true) {
			System.out.print("Which one? ");
			int indexFound = -1;
			//Get the chosen code
			int givenCode = sc.nextInt();

			//Go through all the options in the Collection in order to find which option actually has this code
			for (int i=0; i<options.getLength(); i++){
				String codeToCheck = options.get(i).getUniqueIdentifier();
				
				//Check if the tin matches
				if (Integer.valueOf(codeToCheck) == givenCode){
					indexFound = i;
					break; 
				}
			}

			//if (0 <= input & input < options.getLength()) return input;	
			if (indexFound!=-1){
				return indexFound;
			}					
		}
	}

	// Prints a menu prompt and asks the user to select one of the many options

	int menuPrompt(String[] prompts)
	{
		System.out.println("Choose an option!");

		for (int i=0; i<prompts.length; i++) {
			System.out.printf("[%d] %s", i, prompts[i]);
		}

		int choiceIndex = -1;

		do {
			try {
				System.out.print("Which one? ");
				choiceIndex = Integer.parseInt(sc.nextLine());

			} catch (NumberFormatException e) {
				continue;
			}
		} while(choiceIndex < 0 || choiceIndex > prompts.length - 1);

		return choiceIndex;
	}

	boolean isTypeChoiceValid(int typeChoice)
	{
		return typeChoice == ONLINE || typeChoice == PRINTED || typeChoice == RADIO_TV;
	}

	void repl() {
		String[] choices = null;
		String[] details = null;

		boolean caseExit = false;

		int adChoiceIndex = 0;
		int adTypeChoiceIndex = 0;
		int agentChoiceIndex = 0;
		int productChoiceIndex = 0;

		String adChoiceCode = "";
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
							+ "[9] Display cost of advertising per product\n");
			choice:
			while (true) {
				switch (readNextIntegerWithPrompt("Your choice")) {
					case 1:
						choices = getManyInputs(new String[]{"Brand name"}, new Boolean[]{false});
						this.AdAgencies.push(new AdAgency(this.AdAgencies.getSequenceNumber(), choices[0]));
						break choice;
					case 2:
						
						do {
							System.out.print("Enter advertisement type Details:\n\n");

							//Select ad for this ad type
							adChoiceIndex = chooseOne(Ads);

							if(adChoiceIndex == -1)
							{
								System.out.println("There are no ads to connect this AdType to...");
								caseExit = true;
								break;
							}	
							
							adChoiceCode = Ads.get(adChoiceIndex).getUniqueIdentifier();


							// Select agent for this ad type
							adTypeChoiceIndex = chooseOne(AdAgencies);

							if(adTypeChoiceIndex == -1)
							{
								System.out.println("There are no ad agencies to connect this AdType to...");
								caseExit = true;
								break;
							}

							agentChoiceCode = AdAgencies.get(adTypeChoiceIndex).getUniqueIdentifier();

							// Select description for this ad type
							sc.nextLine();
							choices = getManyInputs(new String[]{"Description"}, new Boolean[]{false});
							
							// Select ad type to create
							typeChoice = menuPrompt(new String[]{"Online", "Printed", "RadioTV"});
						} while (!isTypeChoiceValid(typeChoice));
						
						if(caseExit)
						{
							break choice;
						}

						switch (typeChoice) {
							case ONLINE:
								details = getManyInputs(new String[]{"Price per day", "Automatic display cost", "Price per extra page"}, new Boolean[]{true, true, true});
								this.AdTypes.push(new OnlineAdType(adChoiceCode, choices[0], agentChoiceCode, Integer.parseInt(details[0]), Integer.parseInt(details[1]), Integer.parseInt(details[2])));
								break;
							case PRINTED:
								details = getManyInputs(new String[]{"Price per word on the first page", "Price per word in the middle of newspaper", "Price per word on the last page"}, new Boolean[]{true, true, true});
								this.AdTypes.push(new PrintedAdType(adChoiceCode, choices[0], agentChoiceCode, Integer.parseInt(details[0]), Integer.parseInt(details[1]), Integer.parseInt(details[2])));
								break;
							case RADIO_TV:
								details = getManyInputs(new String[]{"Price per second in the morning", "Price per second at noon", "Price per second in the afternoon", "Price per second in the evening"}, new Boolean[]{true, true, true, true});
								this.AdTypes.push(new RadioTVAdType(adChoiceCode, choices[0], agentChoiceCode, Integer.parseInt(details[0]), Integer.parseInt(details[1]), Integer.parseInt(details[2]), Integer.parseInt(details[3])));
								break;
						}
						break choice;
					case 3:
						do {
							//Select the AdType of this ad
							adTypeChoiceIndex = chooseOne(AdTypes);

							if(adTypeChoiceIndex == -1)
							{
								System.out.println("There are no AdTypes to connect this Ad to...");
								caseExit = true;
								break;
							}

							adTypeChoiceCode = AdTypes.get(adTypeChoiceIndex).getUniqueIdentifier();
							// Select Product for this ad
							productChoiceIndex = chooseOne(Products);

							if(productChoiceIndex == -1)
							{
								System.out.println("There are no Products to connect this Ad to...");
								caseExit = true;
								break;
							}

							productChoiceCode = Products.get(productChoiceIndex).getProductCode();

							// Select properties of this ad
							sc.nextLine();
							choices = getManyInputs(new String[]{"Duration in days", "Details"}, new Boolean[]{true, false});
							
							// Select ad type to create
							typeChoice = menuPrompt(new String[]{"Online", "Printed", "RadioTV"});

						} while (!isTypeChoiceValid(typeChoice));

						if(caseExit)
						{
							break choice;
						}

						switch (typeChoice) {
							case ONLINE:
								this.Ads.push(new OnlineAd(adTypeChoiceCode, productChoiceCode, Integer.parseInt(choices[0]), choices[1], readNextIntegerWithPrompt("Autoshow (1. Yes - 2. No)")));
								break;
							case PRINTED:
								this.Ads.push(new PrintedAd(adTypeChoiceCode, productChoiceCode, Integer.parseInt(choices[0]), choices[1], readNextIntegerWithPrompt("Words")));
								break;
							case RADIO_TV:
							//Ads.getSequenceNumber()
								this.Ads.push(new RadioTVAd(adTypeChoiceCode, productChoiceCode, Integer.parseInt(choices[0]), choices[1], readNextIntegerWithPrompt("Duration in seconds")));
								break;
						}
						break choice;
					case 4:
						for (int i=0; i < this.AdAgencies.getLength(); i++) {
							CollectionManager.printAdsOf(this.AdAgencies.get(i));
						}
						break choice;
					case 5:
						CollectionManager.printAdsOf(this.AdAgencies.get(chooseOne(this.AdAgencies)));
						break choice;
					case 6:
						System.out.println("Cost is: " + CollectionManager.printAdCostFor(this.AdAgencies.get(chooseOne(this.AdAgencies))) + "€");
						break choice;
					case 7:
						CollectionManager.printNumberOfAdsPerProduct();
						break choice;
					case 8:
						System.out.println("Cost is: " + CollectionManager.printAdCostFor(this.Products.get(chooseOne(this.Products))) + "€");
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

		this.AdAgencies.push(new AdAgency("10000001", "McCann"));
		this.AdAgencies.push(new AdAgency("10000002", "Wunderman Thompson"));
		this.AdAgencies.push(new AdAgency("10000003", "Ogilvy"));
		this.AdAgencies.push(new AdAgency("10000004", "Sterling Cooper"));

		this.Products.push(new Product("10000011", "GeForce RTX 3060 Ti", "Q12J0SS4"));
		this.Products.push(new Product("10000012", "Delonghi Dedica Pump", "0IHLDONQ"));
		this.Products.push(new Product("10000013", "AMD Ryzen 7 5700G", "UZP71ZMA"));
		this.Products.push(new Product("10000014", "Osprey Rook 50", "YQ40SMX6"));

		this.AdTypes.push(new OnlineAdType("10000021", "Banner", "10000001", 25, 50, 5));
		this.AdTypes.push(new OnlineAdType("10000022", "Pop-up", "10000002", 50, 70, 30));
		this.AdTypes.push(new OnlineAdType("10000023", "YouTube Unskippable", "10000003", 40, 0, 90));
		
		this.AdTypes.push(new PrintedAdType("10000031", "Full Page", "10000004", 100, 20, 80));
		this.AdTypes.push(new PrintedAdType("10000032", "Quarter Page", "10000002", 25, 5, 20));
		this.AdTypes.push(new PrintedAdType("10000033", "Tenth page", "10000002", 10, 2, 8));

		this.AdTypes.push(new RadioTVAdType("10000041", "Product placement", "10000003", 3, 7, 3, 5));
		this.AdTypes.push(new RadioTVAdType("10000042", "Live endorsement", "10000001", 12, 7, 10, 5));
		this.AdTypes.push(new RadioTVAdType("10000043", "First ad in queue", "10000004", 15, 9, 20, 5));

		for (int i=0; i < this.Products.getLength(); i++) {
			for (String typecode : new String[]{"10000021", "10000022", "10000023"}) {
				this.Ads.push(new OnlineAd(typecode, 
										   this.Products.get(i).getProductCode(), 
										   randomInteger(100), 
										   Integer.toString(randomInteger(10)), 
										   randomInteger(1)));
			}

			for (String typecode : new String[]{"10000031", "10000032", "10000033"}) {
				this.Ads.push(new PrintedAd(typecode, 
											this.Products.get(i).getProductCode(), 
											randomInteger(100), 
											(new String[]{PrintedAdType.FIRST_PAGE, 
														  PrintedAdType.MIDDLE_PAGE, 
														  PrintedAdType.LAST_PAGE})[randomInteger(2)], 
											randomInteger(60)));
			}

			for (String typecode : new String[]{"10000041", "10000042", "10000043"}) {
				this.Ads.push(new RadioTVAd(typecode, 
											this.Products.get(i).getProductCode(), 
											randomInteger(100),
											(new String[]{RadioTVAdType.MORNING, 
														  RadioTVAdType.NOON, 
														  RadioTVAdType.AFTERNOON, 
														  RadioTVAdType.EVENING})[randomInteger( 3)], 
											randomInteger(60)));
			}
		}
	}
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);

		mainApp app = new mainApp();
		app.repl();

		sc.close();
	}
}
