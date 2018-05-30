package com.example.olga.kalkulator;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorActivity extends AppCompatActivity {

    String maintext = ""; //main string saving numbers and actions in sequence
    TextView txt1; //main display
    TextView txt2; //additional display
    int counter = 0; //count number of dots in given number
    String success ="1"; //if numbers for newton sum are correct

    public void clearDisplay2(){
        txt2.setText("");
    }  //clear additional display


    public void updateDisplay(){  //update display

        if(maintext.isEmpty()) {
            txt1.setText("0");
            return;
        }

        String tekst1 = maintext.replaceAll(" ", "");

        int length_s = tekst1.length();

        if(length_s>12) { //if string doesn't fit on screen, move display
            String tekst2 = tekst1.substring(length_s - 12, length_s) ;
            txt1.setText(tekst2);
        }
        else
            txt1.setText(tekst1);

        if(txt2.getText()!="")
            txt2.setText("");

    }

    public void isInteger() {  //check if number is integer
        Double a = Double.parseDouble(maintext);

        if(Math.floor(a) == a){
            long b = Math.round(a);
            maintext = String.valueOf(b); //show integer without dot
        }
    }

    public void resultDisplay() { //update display

        String text1 = maintext;

        if (txt2.getText() != "")
            txt2.setText("");

        if (text1.length() > 12){

            int positionE = text1.lastIndexOf("E");
            if( positionE > 0) {
                int howmanyafterE = text1.length() - positionE - 1;
                String cutted = text1.substring(0, 12 - howmanyafterE - 1) + text1.substring(positionE);
                txt1.setText(cutted);
            }


            else
            {
                txt1.setText(maintext.substring(0,12));
            }
        }
        else txt1.setText(text1);


    }

    public void ifZero(){ //checks if there is a zero on a screen

        if(maintext.endsWith("0")){
            int index = maintext.indexOf("0");
            String character = String.valueOf(maintext.charAt(index -1));
            if(character.equals(" ")){
                maintext = maintext.substring(0, maintext.length()-1);
            }
        }



    }

    public static ArrayList<String> changeSinh(ArrayList<String> part){  //change "sinh" in string to result of function sinh

        for(int i=0; i<= part.size()-1; i++){
            if(part.get(i).contains("sinh"))
            {
                double a = Double.parseDouble(part.get(i).substring(4));
                part.set(i, String.valueOf(Math.sinh(a)));
            }

        }

        return part;

    }

    public void nik(){  //checks if number k is suitable in sum of newton symbol

        int doStop =1;

        String[] parts = maintext.split(" ");

        ArrayList<String> part = new ArrayList<String>(Arrays.asList(parts));


        for(int i=0; i<= part.size()-1 && doStop==1; i++) {

            if(part.get(i).contains("nkSum"))
            {
                double n = Double.parseDouble(part.get(i-1));
                double k = Double.parseDouble(part.get(i+1));

                if(k>n) {
                    txt2.setText("k can't be greater than n");
                    success = "0";
                    doStop = 0;
                }
                else if(Math.floor(k) != k){
                    txt2.setText("k must be integer");
                    success = "0";
                    doStop = 0;
                }

                else if(k<0){
                    txt2.setText("k must by positive");
                    success = "0";
                    doStop = 0;
                }
                else {
                    success = "1";
                    doStop = 0;
                }
            }

        }

    }

    public static long Newton( int n, int k )  //newton symbol
    {
        long  Wynik = 1;
        int i;

        for(i = 1; i <= k; i++)
        {
            Wynik = Wynik * ( n - i + 1 ) / i;
        }

        return Wynik;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_kalkulator);


        txt1 = (TextView) findViewById(R.id.textView);
        txt2 = (TextView) findViewById(R.id.textView2);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btn0 = (Button) findViewById(R.id.btn0);


        Button btnplus = (Button) findViewById(R.id.btnplus);
        Button btnminus = (Button) findViewById(R.id.btnminus);
        Button btnmultiply = (Button) findViewById(R.id.btnrazy);
        Button btndivide = (Button) findViewById(R.id.btnpodziel);
        Button btnresult = (Button) findViewById(R.id.btnwynik);

        Button btnc = (Button) findViewById(R.id.btnc);
        Button btnce = (Button) findViewById(R.id.btnce);
        ImageButton btncorrect = (ImageButton) findViewById(R.id.btnpopraw);
        Button btncomma = (Button) findViewById(R.id.btnprzecinek);
        Button btnchar = (Button) findViewById(R.id.btnznak);

        Button btnsinhx = (Button) findViewById(R.id.btnsinhx);

        ImageButton btnsecondoperator = (ImageButton) findViewById(R.id.btnmoje2);





        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //przycisk "1"

                clearDisplay2();
                ifZero();


                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)"); //you can't type in number longer, than it can be fitted on the screen

                else {
                    maintext += "1";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //button "2"
                clearDisplay2();
                ifZero();


                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {
                    maintext += "2";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //przycisk "3"
                clearDisplay2();
                ifZero();


                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {
                    maintext += "3";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //button "4"
                clearDisplay2();
                ifZero();


                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {
                    maintext += "4";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //button "5"
                clearDisplay2();
                ifZero();

                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {
                    maintext += "5";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //button "6"
                clearDisplay2();
                ifZero();

                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {
                    maintext += "6";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //button "7"
                clearDisplay2();
                ifZero();

                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {
                    maintext += "7";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //button "8"
                clearDisplay2();
                ifZero();

                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {
                    maintext += "8";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //button "9"
                clearDisplay2();
                ifZero();

                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {
                    maintext += "9";
                    counter++;
                    updateDisplay();
                }

            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //button "0"
                clearDisplay2();
                if(maintext.equals("0")) return;
                ifZero();


                if(counter >=12) txt2.setText("Exceed maximal number of digits (12)");

                else {

                    maintext += "0";
                    counter++;
                    updateDisplay();
                }



            }
        });




        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //button "+"
                clearDisplay2();
                if(maintext.isEmpty()) {
                    txt1.setText("0");
                    return;
                }

                if(maintext.endsWith(" ")){  //substitue sign, if there already exists one
                    maintext = maintext.substring(0, maintext.length()-2);
                }

                nik();

                if(success == "1") { //if there exist sum newton and it's correctly typed in, add plus sign to operation
                    maintext += " + ";
                    counter = 0;
                    updateDisplay();
                }
                else if(success == "0") return;




            }
        });

        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //button "-"
                clearDisplay2();
                if(maintext.isEmpty()) {
                    txt1.setText("0");
                    return;
                }

                if(maintext.endsWith(" ")){
                    int index = maintext.lastIndexOf(" ");
                    Character s = maintext.charAt(index-1);
                    if (s.equals('m')){     //one can add minus sign before second number in newton sum
                        maintext = maintext.substring(0);
                        maintext += "-";
                        counter = 0;
                        updateDisplay();
                    }
                    else {
                        maintext = maintext.substring(0, maintext.length() - 2);
                        maintext += " - ";
                        counter = 0;
                        updateDisplay();
                    }
                }

                nik();

                if(success == "1") {
                    maintext += " - ";
                    counter = 0;
                    updateDisplay();
                }
                else if(success == "0") return;
            }
        });

        btnmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {     //button "*"
                clearDisplay2();
                if(maintext.isEmpty()) {
                    txt1.setText("0");
                    return;
                }

                if(maintext.endsWith(" ")){
                    maintext = maintext.substring(0, maintext.length()-2);
                }
                nik();

                if(success == "1") {
                    maintext += " * ";
                    counter = 0;
                    updateDisplay();
                }
                else if(success == "0") return;
            }
        });

        btndivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //button "/"
                clearDisplay2();
                if(maintext.isEmpty()) {
                    txt1.setText("0");
                    return;
                }

                if(maintext.endsWith(" ")){
                    maintext = maintext.substring(0, maintext.length()-2);
                }
                nik();

                if(success == "1") {
                    maintext += " / ";
                    counter = 0;
                    updateDisplay();
                }
                else if(success == "0") return;
            }
        });

        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //button - clear whole display
                clearDisplay2();
                success ="1";

                if(txt2.getText()!="")
                    txt2.setText("");

                maintext = "";
                counter =0;
                updateDisplay();
            }
        });

        btnce.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {   //clear last action
                clearDisplay2();

                success ="1";

                if(txt2.getText()!="")
                    txt2.setText("");

                if (maintext.isEmpty()) {
                    txt1.setText("0");
                    return;
                }

                boolean doStop = false;

                String[] parts = maintext.split(" ");

                ArrayList<String> part = new ArrayList<String>(Arrays.asList(parts));



                for(int i=part.size()-1; i>=0 && !doStop; i--){

                    if (part.get(i).contains("nkSum")) { //clear newton sum if it's last

                        int j=part.size()-1;
                        while(j!=i-1){
                            part.remove(j);
                            j--;
                        }

                        doStop = true;
                        break;
                    }

                    if(part.get(i).equals("*") || part.get(i).equals("/") || part.get(i).equals("+") || part.get(i).equals("-")){  //clear one of those operations, if it's last in string

                        int j=part.size()-1;
                        while(j!=i-1){
                            part.remove(j);
                            j--;
                        }
                        doStop = true;
                        break;
                    }
                }


                maintext = String.join(" ", part);
                counter =0;

                updateDisplay();
                txt1.setText(maintext.replaceAll(" ",""));
            }
        });


        btncorrect.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {  //correct last sign
                clearDisplay2();
                success ="1";

                System.out.println(maintext);

                if(txt2.getText()!="")
                    txt2.setText("");

                if (maintext.isEmpty()) {
                    txt1.setText("0");
                    return;
                }

                if(maintext.endsWith(" ") ) {

                    Character m = maintext.charAt(maintext.length() - 2);
                    if (m.equals('m'))
                        maintext = maintext.substring(0, maintext.length() - 7);

                    else
                        maintext = maintext.substring(0, maintext.length() - 3);
                }
                else {
                    maintext = maintext.substring(0, maintext.length() - 1);
                    counter--;
                    if (maintext.isEmpty()) {
                        txt1.setText("0");
                        counter =0;
                        return;
                    }
                }
                updateDisplay();
                //          txt1.setText(maintext.replaceAll(" ",""));
            }
        });


        btncomma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //add dot
                clearDisplay2();


                if (maintext.isEmpty()) {
                    maintext +="0.";
                    counter =2; //counter, which checks how many dots are in given number
                    updateDisplay();
                    return;
                }

                if(maintext.indexOf(".")>=0){

                    int indeks = maintext.lastIndexOf(" ");
                    int gdziekropka = maintext.lastIndexOf(".");

                    if(gdziekropka > indeks)
                        return;
                }


                if(Character.isDigit(maintext.charAt(maintext.length()-1))){
                    maintext += ".";
                    counter++;
                    updateDisplay();
                }

                if(maintext.endsWith(" ")){
                    return;
                }

                updateDisplay();

            }
        });

        btnchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //change of sign "+" to "-" and vice versa
                clearDisplay2();

                if ( maintext.isEmpty() || maintext.equals("0") || maintext.endsWith(" ")) return;


                int n = maintext.lastIndexOf(' ');
                int m = maintext.lastIndexOf('m');
                String l1 = maintext.substring(n + 1);
                String l2 = maintext.substring(m+1);


                if(n>=m) { //if last number is second number in newton sum

                    if (n > 0) {


                        if (l1.equals("0")) return;

                        maintext = maintext.substring(0, n + 1) + (-Double.parseDouble(l1));

                    }

                    else {
                        maintext = "" + (-Double.parseDouble(l1));
                    }

                }

                else if (m>n){  //if last number isn't in newton sum

                    if(l2.equals("0")) return;

                    maintext = maintext.substring(0,m+1) + ((-Double.parseDouble(l2)));

                }


                updateDisplay();




            }

        });



        btnsinhx.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {  //add to string "sinh" from last entered number
                clearDisplay2();

                if(maintext.endsWith(" "))
                    return;

                if(maintext.endsWith("."))
                    maintext = maintext.substring(0, maintext.length()-1);

                if(Character.isDigit(maintext.charAt(maintext.length()-1))){

                    String[] parts = maintext.split(" ");
                    ArrayList<String> part = new ArrayList<String>(Arrays.asList(parts));

                    String s = part.get(part.size()-1);

                    part.set(part.size()-1,"sinh"+s);

                    maintext = String.join(" ", part);

                    updateDisplay();

                    //resultDisplay();
                }
            }

        });


        btnsecondoperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //add to string "nkSum" after last entered number
                clearDisplay2();


                if(maintext.endsWith(" ") || maintext.isEmpty()) //if string is empty, do nothing
                    return;

                if(maintext.endsWith(".")) //if string is ended with dot, remove that dot
                    maintext = maintext.substring(0, maintext.length() - 1);



                if(Character.isDigit(maintext.charAt(maintext.length()-1))) {


                    String a;

                    if(maintext.indexOf(" ")<0)
                        a= maintext;

                    else
                        a= maintext.substring(maintext.lastIndexOf(" "));

                    double n = Double.parseDouble(a);

                    if(Math.floor(n) != n){ //checks if entered number is correct
                        txt2.setText("n must be integer");
                        return;
                    }
                    if(n<0){
                        txt2.setText("n must be not negative");
                        return;
                    }
                    else{
                        maintext +=" nkSum ";
                        updateDisplay();
                    }



                }



            }});



        btnresult.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {  //return the result
                clearDisplay2();

                if(maintext.endsWith(" ")){
                    txt2.setText("Incorrect format");
                    return;
                }


                if (maintext.isEmpty()) {
                    txt1.setText("0");
                    return;
                }

                String[] parts = maintext.split(" ");

                ArrayList<String> part = new ArrayList<String>(Arrays.asList(parts));

                part = changeSinh(part);

                for ( String s : part)
                {
                    if (s.charAt(0)==' ') s = s.substring(1);
                }

                // System.out.println(part.toString());

                for (int i = part.size()-1; i >= 0; i--) {  //first compute newton sum

                    if(part.get(i).equals("nkSum")){

                        int n = Integer.parseInt(part.get(i-1));
                        int k = Integer.parseInt(part.get(i+1));

                        int wynik = 0;

                        for(int j = 0; j<=k; j++){
                            wynik += Newton(n,j);
                        }
                        part.set(i-1,String.valueOf(wynik));
                        part.remove(i);
                        part.remove(i);
                    }

                }


                for (int i = part.size()-1; i >= 0; i--) { //then compute multiplying and dividing operations

                    if (part.get(i).equals("*")){

                        Double num1 = Double.parseDouble(part.get(i-1));
                        Double num2 = Double.parseDouble(part.get(i+1));


                        Double number = num1*num2;

                        String result = String.valueOf(number);
                        part.set(i-1, result);

                        part.remove(i);
                        part.remove(i);
                    }
                    else if (part.get(i).equals("/")) {




                        Double num1 = Double.parseDouble(part.get(i-1));
                        Double num2 = Double.parseDouble(part.get(i+1));
                        Double number = num1/num2;
                        String result = String.valueOf(number);

                        if(part.get(i+1).equals("0")) {
                            txt2.setText("Nie można dzielić przez 0");  //don't divide by 0
                            maintext ="";
                            return;
                        }


                        part.set(i-1, result);


                        part.remove(i);
                        part.remove(i);
                    }


                }

                for (int i = part.size()-1; i >= 0; i--) { //addition and substraction
                    if (part.get(i).equals("+")) {

                        Double num1 = Double.parseDouble(part.get(i - 1));
                        Double num2 = Double.parseDouble(part.get(i + 1));
                        Double number = num1+num2;
                        String result = String.valueOf(number);
                        part.set(i - 1, result);


                        part.remove(i+1);
                        part.remove(i);
                    } else if (part.get(i).equals("-")) {

                        Double num1 = Double.parseDouble(part.get(i - 1));
                        Double num2 = Double.parseDouble(part.get(i + 1));
                        Double number = num1-num2;
                        String result = String.valueOf(number);
                        part.set(i - 1, result);


                        part.remove(i+1);
                        part.remove(i);
                    }
                }

                maintext =part.get(0);

                counter =0;
                isInteger();
                resultDisplay(); //return result on display

            }});}}










