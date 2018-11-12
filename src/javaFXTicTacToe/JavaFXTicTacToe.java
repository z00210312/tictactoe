/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFXTicTacToe;

import APIFunctions.Ulit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Tom
 */
public class JavaFXTicTacToe extends Application {
    private int userControl;
    private List<List<Button>> btnlist;
    private ButtonIterator btnit;
    private Ulit ult;
    private int stepCounter = 0;
    @Override
    public void start(Stage primaryStage) {
        
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 400, 500);
        
        ult = new Ulit("X", "O", "-");
        
        userControl = 0;
        Button gamebtn = new Button();
        gamebtn.setText("Player vs Player");
        gamebtn.setPrefSize(200,50);
        gamebtn.relocate(100, 300);
        gamebtn.setOnAction((ActionEvent event) -> {
            setTicTacToe(root,100,100,300,300,3);
            userControl = 1;
            //System.out.println("Hello World!");
        });
        root.getChildren().add(gamebtn);
        
        Button aibtn = new Button();
        aibtn.setText("Play vs AI");
        aibtn.setPrefSize(200,50);
        aibtn.relocate(100, 350);
        aibtn.setOnAction((ActionEvent event) -> {
            setAITicTacToe(root,100,100,300,300,3);
            userControl = 1;
        });
        root.getChildren().add(aibtn);
        
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    boolean Checkwinner(String user){
        
        for(int row = 0; row<btnlist.size();row++){
            if(ult.StringsAreSame(btnit.IteratorToListStr(btnit.rowIterator(row)), user))
                return true;
        }
        for(int col = 0; col<btnlist.size();col++){
            if(ult.StringsAreSame(btnit.IteratorToListStr(btnit.colIterator(col)), user))
                return true;
        }
        if(ult.StringsAreSame(btnit.IteratorToListStr(btnit.rDemIterator()), user))
            return true;
        if(ult.StringsAreSame(btnit.IteratorToListStr(btnit.lDemIterator()), user))
            return true;
        return false;
    }
    
    public void setTicTacToe(AnchorPane root, int start_x, int start_y, int end_x, int end_y, int size){
        int tempX = (end_x - start_x) / (size);
        int tempY = (end_y - start_y) / (size);
        stepCounter = 0;
        btnlist = new ArrayList<>();
        for(int i = 0; i<size ; i++){
            btnlist.add(new ArrayList<>());
            for(int j = 0; j<size; j++){
                Button newbtn = new Button();
                newbtn.setPrefSize(tempX, tempY);
                newbtn.relocate(start_x+tempX*i, start_y+tempY*j);
                newbtn.setText(ult.getDefaultDesc());
                btnlist.get(i).add(newbtn);
                root.getChildren().add(newbtn);
                newbtn.setOnAction((ActionEvent event) -> {
                    if(userControl == 1){
                        if(newbtn.getText().equals(ult.getDefaultDesc())){
                            newbtn.setText(ult.getPlayer1());
                            userControl = 2;
                            stepCounter++;
                        }
                    }
                    else if(userControl == 2){
                        if(newbtn.getText().equals(ult.getDefaultDesc())){
                            newbtn.setText(ult.getPlayer2());
                            userControl = 1;
                            stepCounter++;
                        }
                    }
                    else if (userControl == 0){
                        return;
                    }
                    
                    if(Checkwinner(ult.getPlayer1())){
                        userControl = 0;
                        new Alert(Alert.AlertType.INFORMATION, ult.getPlayer1() + " is winner").showAndWait();
                    }
                        
                    else if(Checkwinner(ult.getPlayer2())){
                        userControl = 0;
                        new Alert(Alert.AlertType.INFORMATION, ult.getPlayer2() + " is winner").showAndWait();
                    }
                    else if (stepCounter>=size*size){
                        userControl = 0;
                        new Alert(Alert.AlertType.INFORMATION, "Tie").showAndWait();
                    }
                    
                });
            }
        };
        btnit = new ButtonIterator(btnlist);
    }
    
    public void setAITicTacToe(AnchorPane root, int start_x, int start_y, int end_x, int end_y, int size){
        int tempX = (end_x - start_x) / (size);
        int tempY = (end_y - start_y) / (size);
        stepCounter = 0;
        Random random = new Random() ;
        
        btnlist = new ArrayList<>();
        for(int i = 0; i<size ; i++){
            btnlist.add(new ArrayList<>());
            for(int j = 0; j<size; j++){
                Button newbtn = new Button();
                newbtn.setPrefSize(tempX, tempY);
                newbtn.relocate(start_x+tempX*i, start_y+tempY*j);
                newbtn.setText(ult.getDefaultDesc());
                btnlist.get(i).add(newbtn);
                root.getChildren().add(newbtn);
                newbtn.setOnAction((ActionEvent event) -> {
                    if(userControl == 1){
                        if(newbtn.getText().equals(ult.getDefaultDesc())){
                            newbtn.setText(ult.getPlayer1());
                            userControl = 2;
                            stepCounter++;
                        }
                    }
                    if(userControl == 2 && stepCounter<size*size){
                        int randomNumberRow = random.nextInt(size);
                        int randomNumberCol = random.nextInt(size);
                        while(btnlist.get(randomNumberRow).get(randomNumberCol).getText().equals(ult.getPlayer1()) || btnlist.get(randomNumberRow).get(randomNumberCol).getText().equals(ult.getPlayer2())){
                            randomNumberRow = random.nextInt(size);
                            randomNumberCol = random.nextInt(size);
                        }
                        stepCounter++;
                        btnlist.get(randomNumberRow).get(randomNumberCol).setText(ult.getPlayer2());
                        userControl = 1;
                    }
                    else if (userControl == 0){
                        return;
                    }
                    
                    if(Checkwinner(ult.getPlayer1())){
                        userControl = 0;
                        new Alert(Alert.AlertType.INFORMATION, ult.getPlayer1() + " is winner").showAndWait();
                    }
                    else if(Checkwinner(ult.getPlayer2())){
                        userControl = 0;
                        new Alert(Alert.AlertType.INFORMATION, ult.getPlayer2() + " is winner").showAndWait();
                    }
                    else if (stepCounter>=size*size){
                        userControl = 0;
                        new Alert(Alert.AlertType.INFORMATION, "Tie").showAndWait();
                    }
                });
            }
        }
        btnit = new ButtonIterator(btnlist);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
