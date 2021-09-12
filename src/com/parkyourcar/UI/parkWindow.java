package com.parkyourcar.UI;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.parkyourcar.model.RegisteredCar;
import com.parkyourcar.model.RegisteredCarTableModel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.awt.event.ActionEvent;


public class parkWindow {

	public static JFrame frame;
	public static JTable table;
	public static JScrollPane scrollPane;
	public static int id = 0;
	public static RegisteredCarTableModel model;
	public static List<String> zoneList = new ArrayList<String>();
	public static List<String> zoneListChecker = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public parkWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 466, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
        //build the list
        List<RegisteredCar> regCarList = new ArrayList<RegisteredCar>();
      
         
        //create the model
        RegisteredCarTableModel model = new RegisteredCarTableModel(regCarList);
        //create the table
        table = new JTable(model);
      
        //add the table to the frame
        scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        
        JSplitPane splitPane = new JSplitPane();
        scrollPane.setRowHeaderView(splitPane);
        
        JButton btnInput = new JButton("Input");
        btnInput.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//JSON parser object to parse read file
                JSONParser jsonParser = new JSONParser();
                 
                try (FileReader reader = new FileReader("In.json"))
                {
                    //Read JSON file
                    Object obj = jsonParser.parse(reader);
         
                    JSONArray regcarJSList = (JSONArray) obj;
                    System.out.println(regcarJSList);
                     
                    
                    regcarJSList.forEach( rcr -> parseRegCarObject( (JSONObject) rcr,regCarList ) );
                    //create the model
                    RegisteredCarTableModel model = new RegisteredCarTableModel(regCarList);
                    //create the table
                    table = new JTable(model);
                  
                    frame.setBounds(100, 100, 600, 475);
                  
         
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (org.json.simple.parser.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
         
            private static void parseRegCarObject(JSONObject regCar, List<RegisteredCar> regCarList) 
            {
            	id++;
                RegisteredCar regCarTableElem = new RegisteredCar();
                
                regCarTableElem.setId(id);
                
                JSONObject regCarObject = (JSONObject) regCar.get("car");
                 
                
                String carBrand = (String) regCarObject.get("carBrand");    
                System.out.println(carBrand);
                regCarTableElem.setCarBrand(carBrand); 
                
                String carPlate = (String) regCarObject.get("carPlate");  
                System.out.println(carPlate);
                regCarTableElem.setCarPlate(carPlate); 
               
                String carDriver = (String) regCarObject.get("carDriver");    
                System.out.println(carDriver);
                regCarTableElem.setCarDriver(carDriver);
              
                String carColor = (String) regCarObject.get("carColor");    
                System.out.println(carColor);
                regCarTableElem.setCarColor(carColor);
              
                String parkZone = (String) regCarObject.get("parkZone");    
                System.out.println(parkZone);
                regCarTableElem.setCarParkZone(parkZone);
                
                if(!zoneList.contains(parkZone)) {
                	zoneList.add(parkZone);
                	zoneListChecker.add(parkZone);
                }
               
        
                regCarList.add(regCarTableElem);
               
                 
               
            }
        	
        });
        splitPane.setLeftComponent(btnInput);
        
        JButton btnOutput = new JButton("Output");
        btnOutput.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		for(RegisteredCar regCar : regCarList) {               
                    if(!zoneListChecker.contains(regCar.getCarParkZone())) {
                    	zoneListChecker.add(regCar.getCarParkZone());
                    }
        		}
        		JSONArray repartization = new JSONArray();
        		
        		JSONObject repObject = new JSONObject();
        		
        		for(String zones : zoneList) {
        			JSONObject carObject = new JSONObject();
        			JSONArray cars = new JSONArray();
        			boolean colorFound = false;
        			String remindColor="";
        			boolean parkFound = false;
        			String remindPark = "";
        			
        			for(RegisteredCar regCar : regCarList ) {
        				if(!parkFound ) {
        					parkFound = true;
        					if(zoneListChecker.contains(regCar.getCarParkZone())) {
        					remindPark = regCar.getCarParkZone();
        					carObject.put("ParkZone",remindPark);
        					}else {
        						remindPark = null;
        						parkFound = false;
        						continue;
        					}
        				}
        				
        				if(!colorFound) {
    						colorFound = true;
    						remindColor = regCar.getCarColor();
    						carObject.put("Color", remindColor);
    					}
        				
        				
        				if(regCar.getCarColor().contains(remindColor)) {
        					cars.add(regCar.getCarPlate());
        					
        				}
        				
        				
        			}
        			if(remindPark != null) {
        				carObject.put("Cars", cars);
        				zoneListChecker.remove(remindPark);
        			}
        			repartization.add(carObject);
        		  
        	}
        		  repObject.put("Repartization", repartization);
        		  try {
						Files.write(Paths.get("Output.JSON"), repObject.toJSONString().getBytes());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        });
        splitPane.setRightComponent(btnOutput);
        
        JButton button = new JButton("New button");
        scrollPane.setColumnHeaderView(button);
         
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frame.pack();
        frame.setVisible(true);
	}

}
