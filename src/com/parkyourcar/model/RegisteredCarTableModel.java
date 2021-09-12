package com.parkyourcar.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RegisteredCarTableModel  extends AbstractTableModel{
	
	 private final List<RegisteredCar> regCarList;
     
	    private final String[] columnNames = new String[] {
	            "Id", "Car", "Driver", "Plate","Color","Parking Zone"
	    };
	    private final Class[] columnClass = new Class[] {
	        Integer.class, String.class, String.class, String.class , String.class, String.class
	    };
	 
	    public RegisteredCarTableModel(List<RegisteredCar> regCarList)
	    {
	        this.regCarList = regCarList;
	    }
	     
	    @Override
	    public String getColumnName(int column)
	    {
	        return columnNames[column];
	    }
	 
	    @Override
	    public Class<?> getColumnClass(int columnIndex)
	    {
	        return columnClass[columnIndex];
	    }
	 
	    @Override
	    public int getColumnCount()
	    {
	        return columnNames.length;
	    }
	 
	    @Override
	    public int getRowCount()
	    {
	        return regCarList.size();
	    }
	 
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex)
	    {
	        RegisteredCar row = regCarList.get(rowIndex);
	        if(0 == columnIndex) {
	            return row.getId();
	        }
	        else if(1 == columnIndex) {
	            return row.getCarBrand();
	        }
	        else if(2 == columnIndex) {
	            return row.getCarDriver();
	        }
	        else if(3 == columnIndex) {
	            return row.getCarPlate();
	        }else if(4 == columnIndex) {
	        	return row.getCarColor();	        	
	        }else if(5 == columnIndex) {
	        	return row.getCarParkZone();
	        }
	        return null;
	    }
	    
	 
	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex)
	    {
	        return true;
	    }
	 
	    @Override
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	    {
	        RegisteredCar row = regCarList.get(rowIndex);
	        if(0 == columnIndex) {
	            row.setId((Integer) aValue);
	        }
	        else if(1 == columnIndex) {
	            row.setCarBrand((String) aValue);
	        }
	        else if(2 == columnIndex) {
	        	 row.setCarPlate((String) aValue);
	        }
	        else if(3 == columnIndex) {
	           
	            row.setCarDriver((String) aValue);
	        }else if (4 == columnIndex) {
	        	row.setCarColor((String) aValue);
	        }else if(5 == columnIndex) {
	        	row.setCarParkZone((String) aValue);
	        }
	    }
	 

}
