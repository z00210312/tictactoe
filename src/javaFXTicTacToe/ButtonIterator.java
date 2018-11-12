/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFXTicTacToe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.Button;

/**
 *
 * @author Tom
 */
public class ButtonIterator {
    private List<List<Button>> btnCollection;
    public ButtonIterator(List<List<Button>> _btnCollection){
        btnCollection = _btnCollection;
    }
    public void setBtnCollection(List<List<Button>> _btnCollection){
        btnCollection = _btnCollection;
    }
    public RowIterator rowIterator(int _row){
        return new RowIterator(_row);
    }
    public ColIterator colIterator(int _col){
        return new ColIterator(_col);
    }
    public RDemIterator rDemIterator(){
        return new RDemIterator();
    }
    public LDemIterator lDemIterator(){
            return new LDemIterator();
        }
    public class RowIterator implements Iterator<Button>{
        private int count;
        private int row;
        public RowIterator(int _row){
            count = 0;
            row = _row;
        }

        @Override
        public boolean hasNext() {
            return count < btnCollection.size();
        }

        @Override
        public Button next() {
            return (btnCollection.get(row).get(count++));
        }
    }
    public class ColIterator implements Iterator<Button>{
        private int count;
        private int col;
        ColIterator(int _col){
            count = 0;
            col = _col;
        }

        @Override
        public boolean hasNext() {
            return count < btnCollection.size();
        }

        @Override
        public Button next() {
            return (btnCollection.get(count++).get(col));
        }
    }
    public class RDemIterator implements Iterator<Button>{
        private int count;
        private int reverse;
        RDemIterator(){
            count = 0;
            reverse = btnCollection.size();
        }

        @Override
        public boolean hasNext() {
            return count < btnCollection.size();
        }
        @Override
        public Button next() {
            return (btnCollection.get(count++).get(reverse - count));
        }
    }
    public class LDemIterator implements Iterator<Button>{
            private int count;
            public String user;
            LDemIterator(){
                count = 0;
            }

            @Override
            public boolean hasNext() {
                return count < btnCollection.size();
            }
            @Override
            public Button next() {
                return (btnCollection.get(count).get(count++));
            }
        }
    public List<String> IteratorToListStr(RowIterator It){
        List<String> tempStr = new ArrayList<>();
        while(It.hasNext()){
            tempStr.add(It.next().getText());
        }
        return tempStr;
    }
    public List<String> IteratorToListStr(ColIterator It){
        List<String> tempStr = new ArrayList<>();
        while(It.hasNext()){
            tempStr.add(It.next().getText());
        }
        return tempStr;
    }
    public List<String> IteratorToListStr(RDemIterator It){
        List<String> tempStr = new ArrayList<>();
        while(It.hasNext()){
            tempStr.add(It.next().getText());
        }
        return tempStr;
    }
    public List<String> IteratorToListStr(LDemIterator It){
        List<String> tempStr = new ArrayList<>();
        while(It.hasNext()){
            tempStr.add(It.next().getText());
        }
        return tempStr;
    }
}
