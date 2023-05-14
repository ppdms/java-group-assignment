import Advertisements.*;
import AdvertisementTypes.*;
import Collections.*;
import ComplimentaryClasses.*;
import java.lang.Math;
import java.util.Scanner;

public class App {
	public AdvertisingAgencyCollection AdAgencies;
	public ProductCollection Products;
	public AdvertisementTypeCollection AdvertisementTypes;
	public AdvertisementCollection Advertisements;

	String[] getManyInputs(String[] prompts) {
		Scanner sc = new Scanner(System.in);
		String[] results = new String[prompts.length];
		for (int i=0; i<prompts.length; i++) {
			System.out.print("\n"+prompts[i]+" : ");
			results[i] = sc.nextLine();
		}
		return results;
	}

	int chooseOne(Collection options) {
		Scanner sc = new Scanner(System.in);
		
		for (int i=0; i < options.getLength(); i++) {
						System.out.println(i+ ": " + options.get(i).getUniqueIdentifier() + ": " + options.get(i).getName());
					}
					while (true) {
						System.out.print("Which one? ");
						int input = sc.nextInt();
						if (0 <= input & input < options.getLength()) return input;						
					}
	}

	void repl() {
		Scanner sc = new Scanner(System.in);
		String choice;
		String[] choices, details;
		repl:
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
							+ "Your choice? ");
			switch (sc.nextInt()) {
				case 1:
					choices = getManyInputs(new String[]{"TIN", "Brand name"});
					this.AdAgencies.push(new AdvertisingAgency(choices[0], choices[1]));
					break;
				case 2:
					do {
						choices = getManyInputs(new String[]{"Code", "Description", "Agency TIN", "Type (Online/Printed/RadioTV)"});
					} while (!(choices[3].equals("Online") | choices[3].equals("Printed") | choices[3].equals("RadioTV")) || !(this.AdAgencies.containsTIN(choices[2])));
					switch (choices[3]) {
						case "Online":
							details = getManyInputs(new String[]{"Price per day", "Automatic display cost", "Price per extra page"});
							this.AdvertisementTypes.push(new OnlineTypeAd(choices[0], choices[1], choices[2], Integer.parseInt(details[0]), Integer.parseInt(details[1]), Integer.parseInt(details[2]))); // TODO: rename OnlineTypeAd to OnlineAdType (etc.)
							break;
						case "Printed":
							details = getManyInputs(new String[]{"Price per word on the first page", "Price per word in the middle of newspaper", "Price per word on the last page"});
							this.AdvertisementTypes.push(new PrintedTypeAd(choices[0], choices[1], choices[2], Integer.parseInt(details[0]), Integer.parseInt(details[1]), Integer.parseInt(details[2])));
							break;
						case "RadioTV":
							details = getManyInputs(new String[]{"Price per second in the morning", "Price per second at noon", "Price per second in the afternoon", "Price per second in the evening"});
							this.AdvertisementTypes.push(new RadioTVTypeAd(choices[0], choices[1], choices[2], Integer.parseInt(details[0]), Integer.parseInt(details[1]), Integer.parseInt(details[2]), Integer.parseInt(details[3])));
							break;
					}
					break;
				case 3:
					do {
						choices = getManyInputs(new String[]{"Ad Code", "Product Code", "Duration in days", "Details", "Type (Online/Printed/RadioTV)"});
					} while (!(choices[4].equals("Online") | choices[4].equals("Printed") | choices[4].equals("RadioTV")) || !(this.AdvertisementTypes.containsTypeCode(choices[0]) & this.Products.containsProductCode(choices[1])) || !(this.AdvertisementTypes.getAdvertisementTypeByTypeCode(choices[0]).getClass().getSimpleName().equals(choices[4]+"TypeAd")));
					switch (choices[4]) {
						case "Online":
							details = getManyInputs(new String[]{"Autoshow"});
							this.Advertisements.push(new OnlineAd(choices[0], choices[1], Integer.parseInt(choices[2]), choices[3], Integer.parseInt(details[0])));
							break;
						case "Printed":
							details = getManyInputs(new String[]{"Words"});
							this.Advertisements.push(new PrintedAd(choices[0], choices[1], Integer.parseInt(choices[2]), choices[3], Integer.parseInt(details[0])));
							break;
						case "RadioTV":
							details = getManyInputs(new String[]{"Duration in seconds"});
							this.Advertisements.push(new RadioTvAd(choices[0], choices[1], Integer.parseInt(choices[2]), choices[3], Integer.parseInt(details[0])));
							break;
					}
					break;
				case 4:
					for (int i=0; i < this.AdAgencies.getLength(); i++) {
						CollectionManager.printAdvertisementsOf(this.AdAgencies.get(i), this.AdvertisementTypes, this.Advertisements);
					}
					break;
				case 5:
					CollectionManager.printAdvertisementsOf(this.AdAgencies.get(chooseOne(this.AdAgencies)), this.AdvertisementTypes, this.Advertisements);
					break;
				case 6:
					for (int i=0; i < this.AdAgencies.getLength(); i++) {
						System.out.println(i+ ": " + this.AdAgencies.get(i).getUniqueIdentifier() + ": " + this.AdAgencies.get(i).getName());
					}
					while (true) {
						System.out.print("Which one? ");
						int input = sc.nextInt();
						if (0 <= input & input < this.AdAgencies.getLength()) {
							System.out.println("Cost is: " + CollectionManager.printAdvertisementCostFor(this.AdAgencies.get(input), this.AdvertisementTypes, this.Advertisements) + "€");
							break;
						}
					}
					break;
				case 7:
					for (int i=0; i < this.Products.getLength(); i++) {
						System.out.println(i+ ": " + this.AdAgencies.get(i).getUniqueIdentifier() + ": " + this.AdAgencies.get(i).getName());
					}
					while (true) {
						System.out.print("Which one? ");
						int input = sc.nextInt();
						if (0 <= input & input < this.AdAgencies.getLength()) {
							System.out.println("Cost is: " + CollectionManager.printAdvertisementCostFor(this.AdAgencies.get(input), this.AdvertisementTypes, this.Advertisements) + "€");
							break;
						}
					}
					break;
				case 8:
					break;
				case 9:
					break;
				default:
					continue repl;
				
			}
		}
	}
	
	App() {
		this.AdAgencies = new AdvertisingAgencyCollection();
		this.Products = new ProductCollection();
		this.AdvertisementTypes = new AdvertisementTypeCollection();
		this.Advertisements = new AdvertisementCollection();

		this.AdAgencies.push(new AdvertisingAgency("WGFR5A8A", "McCann"));
		this.AdAgencies.push(new AdvertisingAgency("HOXLK9ZB", "Wunderman Thompson"));
		this.AdAgencies.push(new AdvertisingAgency("VYC28ZZO", "Ogilvy"));
		this.AdAgencies.push(new AdvertisingAgency("BUQPVZJL", "Sterling Cooper"));

		this.Products.push(new Product("UBOO6R30", "GeForce RTX 3060 Ti", "Q12J0SS4"));
		this.Products.push(new Product("LPNE6GOD", "Delonghi Dedica Pump", "0IHLDONQ"));
		this.Products.push(new Product("4IKX92ZL", "AMD Ryzen 7 5700G", "UZP71ZMA"));
		this.Products.push(new Product("9EP2ZTWP", "Osprey Rook 50", "YQ40SMX6"));

		this.AdvertisementTypes.push(new OnlineTypeAd("KPA3CO2G", "Banner", "WGFR5A8A", 25, 50, 5));
		this.AdvertisementTypes.push(new OnlineTypeAd("Y7LG6JPZ", "Pop-up", "HOXLK9ZB", 50, 70, 30));
		this.AdvertisementTypes.push(new OnlineTypeAd("KHY4HM8I", "YouTube Unskippable", "VYC28ZZO", 40, 0, 90));
		
		this.AdvertisementTypes.push(new PrintedTypeAd("9JDKMAV5", "Full Page", "BUQPVZJL", 100, 20, 80));
		this.AdvertisementTypes.push(new PrintedTypeAd("QYIESEYR", "Quarter Page", "HOXLK9ZB", 25, 5, 20));
		AdvertisementTypes.push(new PrintedTypeAd("H50PQI23", "Tenth page", "HOXLK9ZB", 10, 2, 8));

		this.AdvertisementTypes.push(new RadioTVTypeAd("P1G8TAB8", "Product placement", "VYC28ZZO", 3, 7, 3, 5));
		this.AdvertisementTypes.push(new RadioTVTypeAd("O6EIHDNV", "Live endorsement", "WGFR5A8A", 12, 7, 10, 5));
		this.AdvertisementTypes.push(new RadioTVTypeAd("4SCIB0J3", "First ad in queue", "BUQPVZJL", 15, 9, 20, 5));

		//TODO: align these
		for (int i=0; i < this.Products.getLength(); i++) {
			for (String typecode : new String[]{"KPA3CO2G", "Y7LG6JPZ", "KHY4HM8I"}) {
				this.Advertisements.push(new OnlineAd(typecode, this.Products.get(i).getProductCode(), (int) (100*Math.random()), Integer.toString((int)(10*Math.random())), (int) (Math.random())));
			}

			for (String typecode : new String[]{"9JDKMAV5", "QYIESEYR", "H50PQI23"}) {
				this.Advertisements.push(new PrintedAd(typecode, this.Products.get(i).getProductCode(), (int) (100*Math.random()), (new String[]{"First", "Middle", "Last"})[(int) (2*Math.random())], (int) (Math.random())));
			}

			for (String typecode : new String[]{"P1G8TAB8", "O6EIHDNV", "4SCIB0J3"}) {
				this.Advertisements.push(new RadioTvAd(typecode, this.Products.get(i).getProductCode(), (int) (100*Math.random()), (new String[]{"Morning", "Noon", "Afternoon", "Evening"})[(int) (3*Math.random())], (int) (60*Math.random())));
			}
		}
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.repl();
	}
}