package CalculatorSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

    //Declarations
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functioJButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD,30);

    double num1=0, num2=0, result=0;
    char operator;

    //Constructor of Calculator class
    Calculator(){

        //Setting up frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,550);
        frame.setLayout(null);

        //Setitng up text field
        textField = new JTextField();
        textField.setBounds(50,25,300,50);
        textField.setFont(myFont);
        textField.setEditable(false);

        //Giving buttons names
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("C");
        negButton = new JButton("(-)");

        //Adding buttons to function buttons array
        functioJButtons[0] = addButton;
        functioJButtons[1] = subButton;
        functioJButtons[2] = mulButton;
        functioJButtons[3] = divButton;
        functioJButtons[4] = decButton;
        functioJButtons[5] = equButton;
        functioJButtons[6] = delButton;
        functioJButtons[7] = clrButton;
        functioJButtons[8] = negButton;

        //Creating actual buttons for funtion buttons
        for(int i=0; i<9;i++){

            functioJButtons[i].addActionListener(this);
            functioJButtons[i].setFont(myFont);
            functioJButtons[i].setFocusable(false);

        }

        //Creating actual buttons for number buttons
        for(int i=0; i<10;i++){

            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);

        }

        //Setting up the position and dimensions of neg,del,clr
        negButton.setBounds(50,430,100,50);;
        delButton.setBounds(150,430,100,50);
        clrButton.setBounds(250,430,100,50);

        //Setting up panel
        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10));
    
        //Adding our buttons to panel by the order we want
            //There is one point really important as you can see we didnt add
            // neg,del,clr buttons cause we already declared their positions out
            // of our panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        //Adding everything to or frame and setting frame to visible
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        
        //Creating object
        Calculator calc = new Calculator();

    }

    //ActionListener to set results of ations
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Declaring a for loop for our number buttons array to avoid coding
        // every action one by one
        for(int i=0; i<10;i++){

            //getSource takes the action and checks if its one of our numbers
            if(e.getSource() == numberButtons[i]){

                //If it is on text field we set the value to our number buttons value
                textField.setText(textField.getText().concat(String.valueOf(i)));

            }

        }

        //If user hit decimal button this adds "." to make number decimal
        if(e.getSource() == decButton){
            textField.setText(textField.getText().concat("."));
        }

        //These for "if" part is basically checking if the aciton is 
        //one of our operators then clearing the text field for next number input
        if(e.getSource() == addButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if(e.getSource() == subButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }if(e.getSource() == mulButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if(e.getSource() == divButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        //We take the operator as a switch to do the calculating
        if(e.getSource() == equButton){

            num2=Double.parseDouble(textField.getText());

            switch(operator){

                case '+':
                    result=num1+num2;
                    break;
                case '-':
                    result=num1-num2;
                    break;
                case '*':
                    result=num1*num2;
                    break;
                case '/':
                    result=num1/num2;
                    break;

            }
            //then we set result to textField and hold result on number 1 in case user wants to keep
            //calculating over the first result
            textField.setText(String.valueOf(result));
            num1=result;
        }

        //checking if the action is clear button and setting text field to a blank
        if(e.getSource() == clrButton){

            textField.setText("");

        }
        //If its delete button things kinda change here
        if(e.getSource() == delButton){

            //So we get the last number on field as a string and set the field blank
            String string = textField.getText();
            textField.setText("");

            //Then we basically print our last number without last char of it
            //so we are deleting last digit
            for(int i=0; i<string.length()-1; i++){

                textField.setText(textField.getText()+string.charAt(i));

            }

        }
        //Just to be able to use negative numbers too we add neg button a funtion
        //to take the last number on field and multiply it with minus one
        //then set the result to text field. Now the last number is negative
        if(e.getSource() == negButton){

            double temp = Double.parseDouble(textField.getText());
            temp*=-1;
            textField.setText(String.valueOf(temp));

        }

    }

}