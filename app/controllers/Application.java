package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import check.PriceCheck;

import models.Flight;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	ArrayList<String> checks = new ArrayList<String>();
    	File dataDir = new File("data/");
    	
    	for (String file: dataDir.list())
    		checks.add(file);
    	
        return ok(index.render(checks));
    }
    
    public static Result show(String file) {
    	ArrayList<String> lines = new ArrayList<String>();
    	
    	try {
	    	BufferedReader in = new BufferedReader(new FileReader("data/" + file));
	    	String line;
	    	
	    	while((line = in.readLine()) != null)
	    		lines.add(line);
	    	
	    	in.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return ok(show.render(file, lines));
    }
    
    public static Result update() {
    	ArrayList<Flight> flights = new ArrayList<Flight>();
    	
    	flights.add(new Flight("MUC", "SOF", "201404172000", "201404220600", "0.00"));
    	flights.add(new Flight("MUC", "SOF", "201408142000", "201408312000", "0.00"));
    	
    	for (Flight f: flights) {
    		PriceCheck pc = new PriceCheck(f.fromAirport, f.toAirport, f.fromDateTime, f.toDateTime);
    		f.price = pc.checkPrice();
    		
    		saveToFile(f.getFilename(), f.price);
    	}
    	
    	return ok(updated.render(flights));    	
    }
    
    private static void saveToFile(String filename, String price) {
    	try {
			PrintWriter out = new PrintWriter(new FileOutputStream("data/" + filename, true));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String tmp = sdf.format(new Date()).toString();
			out.println(tmp + "\t" + price);
			
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

}
