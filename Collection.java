import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class Collection<T extends Identifiable> {
    protected ArrayList<T> contents;

    private int sequenceNumber;
    private String T;
    private Class<T> itemsClassType;

    public Collection(Class<T> itemsClassType) {
        contents = new ArrayList<T>();
        this.itemsClassType = itemsClassType;
        this.T = itemsClassType.getName();
        this.sequenceNumber = 0;
    }
    
    private boolean isNumeric(String candidate) {
		try {
			int x = Integer.parseInt(candidate);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

    public String getSequenceNumber() {
        String current = Integer.toString(sequenceNumber);

        return current;
    }

    public boolean containsUniqueIdentifier(String uniqueIdentifier) {
        for (T t : contents) {
            if (t.getUniqueIdentifier().equals(uniqueIdentifier))
                return true;
        }
        return false;
    }

    class TokenStream {
        private BufferedReader br;
        private StringTokenizer tokens;
        private String line;

        TokenStream(String filename) {
            try { 
                this.br = new BufferedReader(new FileReader(filename));
                do {
                    line = br.readLine();
                } while (line.trim().equals(""));
                tokens = new StringTokenizer(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        boolean ready() {
            try {
                return br.ready();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
                System.out.println(line);
                return false;
            }
        }

        public String nextToken() {
            if (!ready()) return "";
            String token;
            while (true) {
                try {
                    token = tokens.nextToken();
                    break;
                } catch (java.util.NoSuchElementException e) {
                    try {
                        do {
                            line = br.readLine();
                        } while (line.trim().equals(""));
                    } catch (Exception E) {
                        return "";
                    }
                    tokens = new StringTokenizer(line);
                }
            }
            if (!(token.charAt(0)=='“')) return token;
            boolean state = false;
            for (char c : line.trim().toCharArray()) {
                if (state) {
                    if (c == '”') break;
                    token = ((token == null) ? "" : token) + c;
                } else if (c == '“') {
                    state = true;
                    token = null;
                }
            }
            return token;
        }

        public String line() {
            return line;
        }
    }

    public void populateFromFile(String filename) {
        try {
            TokenStream ts = new TokenStream(filename);
            String token;
            String line;

            switch (T) {
                case "AdAgency":
                    while (ts.ready()) {
                        if (!ts.nextToken().equalsIgnoreCase("COMPANY_LIST")) continue;
                        if (!ts.nextToken().equals("{")) continue;
                        token = ts.nextToken();
                        do {
                            if (!token.equalsIgnoreCase("COMPANY")) continue;
                            token = ts.nextToken();
                            if (!token.equals("{")) continue;
                            String TIN = null;
                            String name = null;
                            Boolean invalid = false;
                            do {
                                token = ts.nextToken();
                                if (token.equalsIgnoreCase("AFM")) {
                                    if (TIN != null) invalid = true;
                                    TIN = ts.nextToken();
                                } else if (token.equalsIgnoreCase("Name")) {
                                    if (name != null) invalid = true;
                                    name = ts.nextToken();
                                }
                            } while (!token.equals("}"));
                            if (!(TIN==null || name==null || invalid) && isNumeric(TIN)) {
                                this.push(this.itemsClassType.getDeclaredConstructor(new Class[]{String.class, String.class}).newInstance(TIN, name));
                            }
                            else System.out.println("data corruption!+agency");
                            token = ts.nextToken();
                        } while (!token.equals("}"));
                    }
                    break;
                
                case "Product":
                    while (ts.ready()) {
                        if (!ts.nextToken().equalsIgnoreCase("ITEM_LIST")) continue;
                        if (!ts.nextToken().equals("{")) continue;
                        token = ts.nextToken();
                        do {
                            if (!token.equalsIgnoreCase("ITEM")) continue;
                            token = ts.nextToken();
                            if (!token.equals("{")) continue;
                            String TIN = null;
                            String desc = null;
                            String code = null;
                            Boolean invalid = false;
                            do {
                                token = ts.nextToken();
                                if (token.equalsIgnoreCase("AFM")) {
                                    if (TIN != null) invalid = true;
                                    TIN = ts.nextToken();
                                } else if (token.equalsIgnoreCase("CODE")) {
                                    if (code != null) invalid = true;
                                    code = ts.nextToken();
                                } else if (token.equalsIgnoreCase("DESCR")) {
                                    if (desc != null) invalid = true;
                                    desc = ts.nextToken();
                                }
                            } while (!token.equals("}"));
                            if (!(TIN==null || desc==null || code == null || invalid) && isNumeric(TIN) && isNumeric(code)) {
                                this.push(this.itemsClassType.getDeclaredConstructor(new Class[]{String.class, String.class, String.class}).newInstance(code, desc, TIN));
                            }
                            else System.out.println("data corruption!+prouct");
                            token = ts.nextToken();
                        } while (!token.equals("}"));
                    }
                    break;
                
                case "AdType":
                    while (ts.ready()) {
                        if (!ts.nextToken().equalsIgnoreCase("ADVTYPE_LIST")) continue;                        
                        if (!ts.nextToken().equals("{")) continue;
                        token = ts.nextToken();
                        do {
                            if (!token.equalsIgnoreCase("ADVTYPE")) continue;
                            token = ts.nextToken();
                            if (!token.equals("{")) continue;
                            String type = null;
                            String code = null;
                            String desc = null;
                            String TIN = null;
                            //values/class: online               printed      radio
                            Integer c1 = null;//pricePerDay          euroPwFirst  euroPsMorning
                            Integer c2 = null;//automaticDisplayCost euroPwMiddle euroPsNoon
                            Integer c3 = null;//pricePerExtraPage    euroPwFLast  euroPsAfternoon
                            Integer c4 = null;//THIS IS ONLY FOR RADIO (euroPsEvening)
                            Boolean invalid = false;
                            do {
                                token = ts.nextToken();
                                if (token.equalsIgnoreCase("AFM")) {
                                    if (TIN != null) invalid = true;
                                    TIN = ts.nextToken();
                                } else if (token.equalsIgnoreCase("type")) {
                                    if (type != null) invalid = true;
                                    type = ts.nextToken();
                                } else if (token.equalsIgnoreCase("code")) {
                                    if (code != null) invalid = true;
                                    code = ts.nextToken();
                                    //c1
                                } else if (token.equalsIgnoreCase("PRICE_PER_DAY") || token.equalsIgnoreCase("EURO_PW_FIRST") || token.equalsIgnoreCase("EURO_PW_MORNING")) {
                                    if (c1 != null) invalid = true;
                                    token = ts.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c1 = Integer.parseInt(token);
                                    //c2
                                } else if (token.equalsIgnoreCase("AUTOMATIC_DISPLAY_COST") || token.equalsIgnoreCase("EURO_PW_MIDDLE") || token.equalsIgnoreCase("EURO_PW_NOON")) {
                                    if (c2 != null) invalid = true;
                                    token = ts.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c2 = Integer.parseInt(token);
                                    //c3
                                } else if (token.equalsIgnoreCase("PRICE_PER_EXTRA_PAGE") || token.equalsIgnoreCase("EURO_PW_LAST") || token.equalsIgnoreCase("EURO_PW_AFTERNOON")) {
                                    if (c3 != null) invalid = true;
                                    token = ts.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c3 = Integer.parseInt(token);
                                } else if (token.equalsIgnoreCase("EURO_PW_EVENING")) {
                                    if (c4 != null) invalid = true;
                                    token = ts.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c4 = Integer.parseInt(token);
                                }
                                else if (token.equalsIgnoreCase("descr")) {
                                    if (desc != null) invalid = true;
                                    desc = ts.nextToken();
                                }
                            } while (!token.equals("}"));
                            if (!(TIN==null || type==null || desc==null || code == null || invalid) && isNumeric(TIN) && isNumeric(code) && (type.equalsIgnoreCase("Print") || (type.equalsIgnoreCase("Media") && c4 != null) || type.equalsIgnoreCase("web"))) {
                                if (type.equalsIgnoreCase("Print")) this.push((T) new PrintedAdType(code, desc, TIN, c1, c2, c3)); else if (type.equalsIgnoreCase("Media")) this.push((T) new RadioTVAdType(code, desc, TIN, c1, c2, c3, c4)); else if (type.equalsIgnoreCase("web")) this.push((T) new OnlineAdType(code, desc, TIN, c1, c2, c3));
                            } else System.out.println("data corruption!"+code+desc+TIN+type+c1+c2+c3+c4);
                            token = ts.nextToken();
                        } while (!token.equals("}"));
                    }
                    break;
                
                case "Ad":
                    while (ts.ready()) {
                        if (!ts.nextToken().equalsIgnoreCase("ADV_LIST")) continue;
                        if (!ts.nextToken().equals("{")) continue;
                        token = ts.nextToken();
                        do {
                            if (!token.equalsIgnoreCase("ADV")) continue;
                            token = ts.nextToken();
                            if (!token.equals("{")) continue;
                            String type = null;
                            String advtype_code = null;
                            String item_code = null;
                            String justification = null;
                            Integer duration = null;
                            //values/class:    online      printed  radio
                            Integer c = null;//autoShow    words    durationSeconds
                            Boolean invalid = false;
                            do {
                                token = ts.nextToken();
                                if (token.equalsIgnoreCase("advtype_code")) {
                                    if (advtype_code != null) invalid = true;
                                    advtype_code = ts.nextToken();
                                } else if (token.equalsIgnoreCase("type")) {
                                    if (type != null) invalid = true;
                                    type = ts.nextToken();
                                } else if (token.equalsIgnoreCase("item_code")) {
                                    if (item_code != null) invalid = true;
                                    item_code = ts.nextToken();
                                } else if (token.equalsIgnoreCase("duration")) {
                                    if (duration != null) invalid = true;
                                    token = ts.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    duration = Integer.parseInt(token);
                                    //c
                                } else if (token.equalsIgnoreCase("AUTO_SHOW") || token.equalsIgnoreCase("WORDS") || token.equalsIgnoreCase("DURATION_SECONDS")) {
                                    if (c != null) invalid = true;
                                    token = ts.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c = Integer.parseInt(token);
                                } else if (token.equalsIgnoreCase("justification")) {
                                    if (justification != null) invalid = true;
                                    justification = ts.nextToken();
                                }
                            } while (!token.equals("}"));
                            if (!(type == null || advtype_code==null || item_code==null || duration==null || justification == null || c == null || invalid) && isNumeric(advtype_code) && isNumeric(item_code) && (type.equalsIgnoreCase("Print") || type.equalsIgnoreCase("Media") || type.equalsIgnoreCase("wEB"))) {
                                if (type.equalsIgnoreCase("Print")) this.push((T) new PrintedAd(advtype_code, item_code, duration, justification, c)); else if (type.equalsIgnoreCase("Media")) this.push((T) new RadioTVAd(advtype_code, item_code, duration, justification, c)); else if (type.equalsIgnoreCase("WEB")) this.push((T) new OnlineAd(advtype_code, item_code, duration, justification, c));
                            } else System.out.println("data corruption!+bbbb");
                            token = ts.nextToken();
                        } while (!token.equals("}"));
                    }
                break;
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static void saveInfo(String fileName, Collection<Ad> ads, Collection<AdType> adTypes, Collection<AdAgency> adAgencies, Collection<Product> products){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            if (fileName.equals("Ads.txt")){
                writer.write("ADV_LIST\n");
                writer.write("{\n");
                for (int i=0; i<ads.getLength(); i++){
                    String avdType ="";
                    writer.write("\tADV\n");
                    writer.write("\t{\n");
                    String adTypeCode = ads.get(i).getAdTypeCode();
                    String characteristic="";
                    //Get the type of the specific Ad
                    for (int j=0; j<adTypes.getLength(); j++){
                        if (adTypes.get(j).getAdCode().equals(adTypeCode)){
                            switch(adTypes.get(j).getTYPE()){
                                case 0:
                                    avdType = "Web";
                                    characteristic = "AUTO_SHOW";
                                    break;
                                case 1:
                                    avdType = "Print";
                                    characteristic = "WORDS";
                                    break;
                                case 2:
                                    avdType = "Media";
                                    characteristic = "DURATION_SECONDS";
                                    break;
                            }
                        }
                    }
                    writer.write("\t\tTYPE "+avdType+"\n");
                    writer.write("\t\tADVTYPE_CODE "+ads.get(i).getAdTypeCode()+"\n");
                    writer.write("\t\tITEM_CODE "+ads.get(i).getProductCode()+"\n");
                    writer.write("\t\tDURATION "+ads.get(i).getDurationInDays()+"\n");
                    writer.write("\t\tJUSTIFICATION “"+ads.get(i).getDetails()+"”\n");
                    writer.write("\t\t"+characteristic+" "+ads.get(i).getExtraCharacteristic()+"\n");
                    writer.write("\t}\n");
                }
                writer.write("}");
                writer.close();
            }
            else if (fileName.equals("AdTypes.txt")){
                writer.write("ADVTYPE_LIST\n\n");
                writer.write("{\n");
                String type = null;
                String code = null;
                String desc = null;
                String TIN = null;
                //values/class: online               printed      radio
                Integer c1 = null;//pricePerDay          euroPwFirst  euroPsMorning
                Integer c2 = null;//automaticDisplayCost euroPwMiddle euroPsNoon
                Integer c3 = null;//pricePerExtraPage    euroPwFLast  euroPsAfternoon
                Integer c4 = null;//THIS IS ONLY FOR RADIO (euroPsEvening)


                //Get the needed info depending on the adType
                for (int i=0; i<adTypes.getLength(); i++){
                    String char1 = "";
                    String char2 = "";
                    String char3 = "";
                    //These are the characteristics shared among all the AdTypes
                    code = adTypes.get(i).getAdCode();
                    desc = adTypes.get(i).getDescription();
                    TIN = adTypes.get(i).getAgencyTIN();
                    if (adTypes.get(i) instanceof OnlineAdType){
                        //These are the unique characteristics
                        type = "Web";
                        c1 = ((OnlineAdType)adTypes.get(i)).getPricePerDay();
                        c2 = ((OnlineAdType)adTypes.get(i)).getAutomaticDisplayCost();
                        c3 = ((OnlineAdType)adTypes.get(i)).getPricePerExtraPage(); 
                        char1 = "PRICE_PER_DAY";
                        char2 = "AUTOMATIC_DISPLAY_COST";
                        char3 = "PRICE_PER_EXTRA_PAGE";                       
                    }
                    else if (adTypes.get(i) instanceof PrintedAdType){
                        //These are the unique characteristics
                        type = "Print";
                        c1 = ((PrintedAdType)adTypes.get(i)).getEuroPwFirst();
                        c2 = ((PrintedAdType)adTypes.get(i)).getEuroPwMiddle();
                        c3 = ((PrintedAdType)adTypes.get(i)).getEuroPwLast();
                        char1 = "EURO_PW_FIRST";
                        char2 = "EURO_PW_MIDDLE";
                        char3 = "EURO_PW_LAST";
                    }
                    else{
                        //These are the unique characteristics
                        type = "Media";
                        c1 = ((RadioTVAdType)adTypes.get(i)).getEuroPsMorning();
                        c2 = ((RadioTVAdType)adTypes.get(i)).getEuroPsNoon();
                        c3 = ((RadioTVAdType)adTypes.get(i)).getEuroPsAfternoon();
                        c4 = ((RadioTVAdType)adTypes.get(i)).getEuroPsEvening();
                        char1 = "EURO_PW_MORNING";
                        char2 = "EURO_PW_NOON";
                        char3 = "EURO_PW_AFTERNOON";
                    }

                    writer.write("\tADVTYPE\n");
                    writer.write("\t{\n");
                    writer.write("\t\tTYPE "+type+"\n");
                    writer.write("\t\tCODE "+code+"\n");
                    writer.write("\t\tDESCR “"+desc+"”\n");
                    writer.write("\t\tAFM "+TIN+"\n");
                    writer.write("\t\t"+char1+" "+c1+"\n");
                    writer.write("\t\t"+char2+" "+c2+"\n");
                    writer.write("\t\t"+char3+" "+c3+"\n");
                    if (c4 != null){
                        writer.write("\t\tEURO_PW_EVENING "+c4+"\n");
                    }
                    writer.write("\t}\n");                    
                }                
                writer.write("}");
                writer.close();
            }
            else if (fileName.equals("AdAgencies.txt")){
                writer.write("COMPANY_LIST\n");
                writer.write("{\n");
                for (int i=0; i<adAgencies.getLength(); i++){
                    writer.write("\tCOMPANY\n");
                    writer.write("\t{\n");
                    writer.write("\t\tAFM "+adAgencies.get(i).getTIN()+"\n");
                    writer.write("\t\tNAME “"+adAgencies.get(i).getBrandName()+"”\n");
                    writer.write("\t}\n");
                }
                writer.write("}");
                writer.close();
            } 
            else{
                writer.write("ITEM_LIST\n");
                writer.write("{\n");
                for (int i=0; i<products.getLength(); i++){
                    writer.write("\tITEM\n");
                    writer.write("\t{\n");
                    writer.write("\t\tCODE "+products.get(i).getProductCode()+"\n");
                    writer.write("\t\tDESCR “"+products.get(i).getDescription()+"”\n");
                    writer.write("\t\tAFM "+products.get(i).getSupplierTIN()+"\n");
                    writer.write("\t}\n");
                }
                writer.write("}");
                writer.close();
            }       
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }

    // Add the Object to the Collection
    public T push(T data) {
        sequenceNumber++;
        this.contents.add(data);
        return null;
    }

    // Get index of Object
    public T get(int index) {
        if (index < 0 || index > this.getLength() - 1)
            return null;

        return (T) contents.get(index);
    }

    public int getLength() {
        return contents.size();
    }

    public String toString() {
        String output = itemsClassType.getName() + "(s):\n";

        for (T item : contents) {
            output += String.format("   %s,%n", item.toString());
        }

        return output;
    }
}
