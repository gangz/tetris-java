package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shape{
    protected List<Cell> shape =new ArrayList<>();
    public Shape(){
    }

    public void rotate() {
        throw new UnsupportedOperationException("Not support rotation yet.");
    }

    public List<Cell> getCells() {
        return shape;
    }

    public void add(Cell cell) {
        shape.add(cell);
    }

    public int eliminate(int rowWidth) {
        List<Integer> rowsToBeRemoved = findAllWholeRows(rowWidth);
        shape.removeIf(cell->rowsToBeRemoved.contains(cell.y));
        shape.forEach(cell->{cell.y = cell.y+countHighRows(rowsToBeRemoved,cell.y);});
        return rowsToBeRemoved.size();
    }

    private int countHighRows(List<Integer> rowsToBeRemoved, int y) {
        return (int) rowsToBeRemoved.stream().filter(rowIndex->(y<rowIndex)).count();
    }

    private List<Integer> findAllWholeRows(int rowWidth) {
        ArrayList<Integer> wholeRowYIndex = new ArrayList<>();
        Map<Integer, Integer> rowCounts = countAllRows();
        for (Integer row:rowCounts.keySet()){
            if (rowCounts.get(row)==rowWidth){
                wholeRowYIndex.add(row);
            }
        }
        return wholeRowYIndex;
    }

    private Map<Integer, Integer> countAllRows() {
        HashMap<Integer,Integer> rowCounts = new HashMap<Integer,Integer>();
        for (Cell cell:getCells()) {
            int y = cell.getY();
            Integer count = rowCounts.get(y);
            if (count==null)
                count = new Integer(0);
            count++;
            rowCounts.put(y, count);
        }
        return rowCounts;
    }

}
