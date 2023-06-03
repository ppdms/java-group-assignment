import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
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

    public void populateFromFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);    
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer tokens;
            String token;
            String line;

            switch (T) {
                case "AdAgency":
                    while (br.ready()) {
                        line = br.readLine();
                        if (!line.trim().equalsIgnoreCase("COMPANY_LIST")) continue;
                        line = br.readLine();
                        if (!line.trim().equals("{")) continue;
                        line = br.readLine();
                        do {
                            if (!line.trim().equalsIgnoreCase("COMPANY")) continue;
                            line = br.readLine();
                            if (!line.trim().equals("{")) continue;
                            String TIN = null;
                            String name = null;
                            Boolean invalid = false;
                            do {
                                line = br.readLine();
                                tokens = new StringTokenizer(line);
                                token = tokens.nextToken();
                                if (token.equalsIgnoreCase("AFM")) {
                                    if (TIN != null) invalid = true;
                                    TIN = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("Name")) {
                                    if (name != null) invalid = true;
                                    name = tokens.nextToken();
                                    if (!(name.charAt(0)=='“')) continue;
                                    name = null;
                                    boolean state = false;
                                    for (char c : line.trim().toCharArray()) {
                                        if (state) {
                                            if (c == '”') break;
                                            name = ((name == null) ? "" : name) + c;
                                        } else if (c == '“') state = true;
                                    }
                                }
                            } while (!(line.trim().equals("}")));
                            if (!(TIN==null || name==null || invalid) && isNumeric(TIN)) {
                                this.push(this.itemsClassType.getDeclaredConstructor(new Class[]{String.class, String.class}).newInstance(TIN, name));
                            }
                            line = br.readLine();
                        } while (!(line.trim().equals("}")));
                    }
                    break;
                case "Product":
                    while (br.ready()) {
                        line = br.readLine();
                        if (!line.trim().equalsIgnoreCase("ITEM_LIST")) continue;
                        line = br.readLine();
                        if (!line.trim().equals("{")) continue;
                        line = br.readLine();
                        do {
                            if (!line.trim().equalsIgnoreCase("ITEM")) continue;
                            line = br.readLine();
                            if (!line.trim().equals("{")) continue;
                            String TIN = null;
                            String desc = null;
                            String code = null;
                            Boolean invalid = false;
                            do {
                                line = br.readLine();
                                tokens = new StringTokenizer(line);
                                token = tokens.nextToken();
                                if (token.equalsIgnoreCase("AFM")) {
                                    if (TIN != null) invalid = true;
                                    TIN = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("code")) {
                                    if (code != null) invalid = true;
                                    code = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("descr")) {
                                    if (desc != null) invalid = true;
                                    desc = tokens.nextToken();
                                    if (!(desc.charAt(0)=='“')) continue;
                                    desc = null;
                                    boolean state = false;
                                    for (char c : line.trim().toCharArray()) {
                                        if (state) {
                                            if (c == '”') break;
                                            desc = ((desc == null) ? "" : desc) + c;
                                        } else if (c == '“') state = true;
                                    }
                                }
                            } while (!(line.trim().equals("}")));
                            
                            if (!(TIN==null || desc==null || code == null || invalid) && isNumeric(TIN) && isNumeric(code)) {
                                this.push(this.itemsClassType.getDeclaredConstructor(new Class[]{String.class, String.class, String.class}).newInstance(code, desc, TIN));
                            }
                            line = br.readLine();
                        } while (!(line.trim().equals("}")));
                    }
                    break;
                case "AdType":
                    while (br.ready()) {
                        line = br.readLine();
                        if (!line.trim().equalsIgnoreCase("ADVTYPE_LIST")) continue;
                        line = br.readLine();
                        if (!line.trim().equals("{")) continue;
                        line = br.readLine();
                        do {
                            if (!line.trim().equalsIgnoreCase("ADVTYPE")) continue;
                            line = br.readLine();
                            if (!line.trim().equals("{")) continue;
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
                                line = br.readLine();
                                tokens = new StringTokenizer(line);
                                token = tokens.nextToken();
                                if (token.equalsIgnoreCase("AFM")) {
                                    if (TIN != null) invalid = true;
                                    TIN = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("type")) {
                                    if (type != null) invalid = true;
                                    type = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("code")) {
                                    if (code != null) invalid = true;
                                    code = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("c1")) {
                                    if (c1 != null) invalid = true;
                                    token = tokens.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c1 = Integer.parseInt(token);
                                } else if (token.equalsIgnoreCase("c2")) {
                                    if (c2 != null) invalid = true;
                                    token = tokens.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c2 = Integer.parseInt(token);
                                } else if (token.equalsIgnoreCase("c3")) {
                                    if (c3 != null) invalid = true;
                                    token = tokens.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c3 = Integer.parseInt(token);
                                } else if (token.equalsIgnoreCase("c4")) {
                                    if (c4 != null) invalid = true;
                                    token = tokens.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c4 = Integer.parseInt(token);
                                }
                                else if (token.equalsIgnoreCase("descr")) {
                                    if (desc != null) invalid = true;
                                    desc = tokens.nextToken();
                                    if (!(desc.charAt(0)=='“')) continue;
                                    desc = null;
                                    boolean state = false;
                                    for (char c : line.trim().toCharArray()) {
                                        if (state) {
                                            if (c == '”') break;
                                            desc = ((desc == null) ? "" : desc) + c;
                                        } else if (c == '“') state = true;
                                    }
                                }
                            } while (!(line.trim().equals("}")));
                            if (!(TIN==null || type==null || desc==null || code == null || invalid) && isNumeric(TIN) && isNumeric(code) && (type.equalsIgnoreCase("Print") || (type.equalsIgnoreCase("Media") && c4 != null) || type.equalsIgnoreCase("web"))) {
                                if (type.equalsIgnoreCase("Print")) this.push((T) new PrintedAdType(code, desc, TIN, c1, c2, c3)); else if (type.equalsIgnoreCase("Media")) this.push((T) new RadioTVAdType(code, desc, TIN, c1, c2, c3, c4)); else if (type.equalsIgnoreCase("web")) this.push((T) new OnlineAdType(code, desc, TIN, c1, c2, c3));
                            } else System.out.println("data corruption!"+code+desc+TIN+type+c1+c2+c3);
                            line = br.readLine();
                        } while (!(line.trim().equals("}")));
                    }
                    break;
                case "Ad":
                    while (br.ready()) {
                        line = br.readLine();
                        if (!line.trim().equalsIgnoreCase("ADV_LIST")) continue;
                        line = br.readLine();
                        if (!line.trim().equals("{")) continue;
                        line = br.readLine();
                        do {
                            if (!line.trim().equalsIgnoreCase("ADV")) continue;
                            line = br.readLine();
                            if (!line.trim().equals("{")) continue;
                            String type = null;
                            String advtype_code = null;
                            String item_code = null;
                            String justification = null;
                            Integer duration = null;
                            //values/class:    online      printed  radio
                            Integer c = null;//autoShow    words    durationSeconds
                            Boolean invalid = false;
                            do {
                                line = br.readLine();
                                tokens = new StringTokenizer(line);
                                token = tokens.nextToken();
                                if (token.equalsIgnoreCase("advtype_code")) {
                                    if (advtype_code != null) invalid = true;
                                    advtype_code = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("type")) {
                                    if (type != null) invalid = true;
                                    type = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("item_code")) {
                                    if (item_code != null) invalid = true;
                                    item_code = tokens.nextToken();
                                } else if (token.equalsIgnoreCase("duration")) {
                                    if (duration != null) invalid = true;
                                    token = tokens.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    duration = Integer.parseInt(token);
                                } else if (token.equalsIgnoreCase("c")) {
                                    if (c != null) invalid = true;
                                    token = tokens.nextToken();
                                    if (!isNumeric(token)) {
                                        invalid = true;
                                        continue;
                                    }
                                    c = Integer.parseInt(token);
                                } else if (token.equalsIgnoreCase("justification")) {
                                    if (justification != null) invalid = true;
                                    justification = tokens.nextToken();
                                    if (!(justification.charAt(0)=='“')) continue;
                                    justification = null;
                                    boolean state = false;
                                    for (char ch : line.trim().toCharArray()) {
                                        if (state) {
                                            if (ch == '”') break;
                                            justification = ((justification == null) ? "" : justification) + ch;
                                        } else if (ch == '“') state = true;
                                    }
                                }
                            } while (!(line.trim().equals("}")));
                            if (!(type == null || advtype_code==null || item_code==null || duration==null || justification == null || c == null || invalid) && isNumeric(advtype_code) && isNumeric(item_code) && (type.equalsIgnoreCase("Print") || type.equalsIgnoreCase("Media") || type.equalsIgnoreCase("wEB"))) {
                                if (type.equalsIgnoreCase("Print")) this.push((T) new PrintedAd(advtype_code, item_code, duration, justification, c)); else if (type.equalsIgnoreCase("Media")) this.push((T) new RadioTVAd(advtype_code, item_code, duration, justification, c)); else if (type.equalsIgnoreCase("WEB")) this.push((T) new OnlineAd(advtype_code, item_code, duration, justification, c));
                            } else System.out.println("data corruption!");
                            line = br.readLine();
                        } while (!(line.trim().equals("}")));
                    }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
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
