/*
MIT License

Copyright (c) 2022 ZHANG Gang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.github.gangz.emergentdesign.demo.tetris.shape;

import com.github.gangz.emergentdesign.demo.tetris.controller.Cell;

import java.rmi.UnexpectedException;
import java.util.*;

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

    public Shape deepClone() {
        Shape clone = new Shape();
        for (Cell cell:shape){
            clone.shape.add(new Cell(cell.x,cell.y));
        }
        return clone;
    }

    public int height() {
        if (shape.size()==0) return 0;
        Optional<Cell> max = shape.stream().max(Comparator.comparingInt(Cell::getY));
        Optional<Cell> min = shape.stream().min(Comparator.comparingInt(Cell::getY));
        return max.get().y-min.get().y+1;
    }

    public int width() {
        if (shape.size()==0) return 0;
        Optional<Cell> max = shape.stream().max(Comparator.comparingInt(Cell::getX));
        Optional<Cell> min = shape.stream().min(Comparator.comparingInt(Cell::getX));
        return max.get().x-min.get().x+1;
    }
}
