package us.kpatrick;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;

/**
 * Main program to tie together the Countries, number of cities and number of stuff in those Countries
 * @author kpatr
 * @version 1.0.0
 */
public class Main {

    private final static FileInput places = new FileInput("places.csv");
    private final static FileInput stuff = new FileInput("stuff.csv");
    private static ArrayList<String> Countries = new ArrayList<>(); //PARALLEL ARRAYLIST
    private static ArrayList<Integer> Cities = new ArrayList<>(); //PARALLEL ARRAYLIST
    private static ArrayList<Integer> Stuff = new ArrayList<>(); //PARALLEL ARRAYLIST

    /**
     * Main starting point of the program
     * @param args Initial variables passed into the program when it starts
     */
    public static void main(String[] args) {
	// write your code here

        SetUniqueCountries();
        GetStuffCount();
        String line;
        String[] fields;
        System.out.format("%-20s %6s %6s\n","Country", "Cities", "Stuff");
        System.out.format("%-20s %6s %6s\n","===================", "======", "======");
        for (int i = 0; i < Countries.size(); i++) {

            System.out.format("%-20s %6s %6s\n",Countries.get(i),Cities.get(i), Stuff.get(i));
            //System.out.println(Countries.get(i) + " - " + Cities.get(i) + " - " + Stuff.get(i));
        }


    }


    /**
     * Updates the stuff arrayList in Parallel with the Countries ArrayList to hold to quantity of Stuff
     */
    public static void GetStuffCount() {
        ArrayList<String> StuffList = new ArrayList<>();
        ArrayList<String> CountryStuff = new ArrayList<>();
        String tCountry = "";
        String line;
        String[] fields;

        try {
            while (((line = stuff.fileReadLine()) != null)) {
                fields = line.split(",");

                if ( !tCountry.equals(fields[0]) ) {
                    CountryStuff.clear();
                }
                tCountry = fields[0].replace("\uFEFF","");

                if (!CountryStuff.contains(fields[1])) {
                    StuffList.add(tCountry.replace("\uFEFF",""));
                    CountryStuff.add(fields[1]);
                }

            }


            for (String c: Countries ) {
                Integer count = Collections.frequency(StuffList,c);
                Stuff.add(count);
            }

            stuff.fileClose();
        } catch (Exception e) {
            System.out.println("There was an error Counting the stuff.");
        }

    }

    /**
     * Sets the Countries Array list with the unique Countries.  Also sets the count of cities in a parallel array with the Number of Cities
     */
    public static void SetUniqueCountries() {
        ArrayList<String> c = new ArrayList<String>();
        String tCountry;
        String line;
        String[] fields;
        try {
            while (((line = places.fileReadLine()) != null)) {
                fields = line.split(",");
                tCountry = fields[0];
                c.add(tCountry);
            }

            Collections.sort(c);

            String oldCountry = "";

            for (String uniqueCountries: c) {
                if (!uniqueCountries.equals(oldCountry)) {

                    Countries.add(uniqueCountries);
                    Integer u = Collections.frequency(c,uniqueCountries);
                    Cities.add(u);

                    oldCountry = uniqueCountries;
                }
            }
            places.fileClose();
        } catch (Exception e) {
            System.out.println("There was an error setting the Unique Countries/Cities");
        }

    }
}
