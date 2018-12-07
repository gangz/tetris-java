package com.github.gangz.tetris.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shape {
	protected ArrayList<Cell> shape;
	public Shape(){
		shape = new ArrayList<Cell>();
	}
	public int size() {
		return shape.size();
	}

	public Cell getAt(int index) {
		return shape.get(index);
	}
	public void add(Cell cell) {
		shape.add(cell);
	}
	
	public int eleminate(int width_in_y) {
		HashMap<Integer, Integer> rowCounts;
		rowCounts = countAllRows(shape);
        int removedRowsCount = 0;
        for (Map.Entry<Integer, Integer> mapEntry:rowCounts.entrySet())
    	{
        	if (mapEntry.getValue()==width_in_y)
        	{
        		erase(mapEntry.getKey());
        		compress(mapEntry.getKey());
        		removedRowsCount++;
        	}
    	}
    	

        return removedRowsCount;

	}
	

	private void compress(int rowIndex) {
		for (int i=0;i<shape.size();i++)
		{
			if (shape.get(i).x>rowIndex){
				shape.get(i).x--;
			}
		}
	}
	public void erase(int rowIndex) {
		for (int i=0;i<shape.size();i++)
		{
			if (shape.get(i).x==rowIndex){
				shape.remove(i);
				i--;
			}
		}
	}
	
	public HashMap<Integer, Integer>  countAllRows(ArrayList<Cell> cellList) {
		HashMap<Integer, Integer>  rowCounts = new HashMap<Integer, Integer> ();
		Integer count;
		for (int i = 0; i < cellList.size(); i++) {
			int rowIndex = cellList.get(i).x;
			count = rowCounts.get(rowIndex);
			if (count == null)
				rowCounts.put(rowIndex, 1);
			else
				rowCounts.put(rowIndex, ++count);
			}
		return rowCounts;
	}


}
