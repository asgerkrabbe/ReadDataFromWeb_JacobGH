package com.kea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Type the desired 3 digit currency code, for today's exchange rate");
        Scanner findCurrency = new Scanner(System.in);
        String matchCurrency = findCurrency.next();

        //Instantiating the URL class
        URL url = new URL("https://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=da");
        //Retrieving the contents of the specified page
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputline;
        //opretter en arraylist og gemmer hver linje fra URL som nyt objekt i arraylisten
        List<String> list = new ArrayList<String>();
        while ((inputline = in.readLine()) != null)
        {
            list.add(inputline);
        }
        for (String s : list) {
            // if sætning for at holde brugerns input op imod listen.
            if (s.contains(matchCurrency)) {
                String j = s;
                j = j.replaceAll("[^0-9,]", "");
                System.out.println("Today's exchange rate for DKK to " + matchCurrency + " is " + j);

                String jd = j.replaceAll(",", ".");
                double jdd = 0;
                try {
                    jdd = Double.parseDouble(jd);
                } catch (Exception ignored) {

                }

                System.out.println("Type amount in DKK you want to convert");
                Scanner amount = new Scanner(System.in);
                double currencyConverter = amount.nextDouble();
                System.out.println("" + currencyConverter + " DKK = " + (currencyConverter / jdd) * 100 + " " + matchCurrency + "");
            }
        }
        }
    }


