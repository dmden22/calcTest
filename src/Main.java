import java.util.Arrays;
import java.util.Scanner;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    /*enum Rim{
        I(1),II(2);
        Rim (int value){
            this.value=value;
        }
    }*/
    public static int getCalcResult (int a, int b, char operation){
        int result = 0;
        switch (operation) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '/':
                result = a / b;
                break;
            case '*':
                result = a * b;
                break;
        }
        return result;
    }
    public static boolean isRim(String input){
        for (int i = 0; i<input.length(); i++){
            //System.out.println(input.charAt(i));
            if (!(input.charAt(i)=='I' || input.charAt(i)=='V' || input.charAt(i)=='X')) {
                return false;
            }
        }
        return true;
    }
    public static int rimToArab(String input){
        int arabResult = 0;
        for (int i = 0; i<input.length(); i++){
            if (input.charAt(i)=='X') arabResult+=10;
            if (input.charAt(i)=='V') arabResult+=5;
            if (input.charAt(i)=='I') {
                if (i==input.length()-1){
                    arabResult+=1;
                    return arabResult;
                } else if (i==input.length()-2) {
                    if (input.charAt(i+1)=='X') {
                        arabResult+=9;
                        return arabResult;
                    }
                    if (input.charAt(i+1)=='V'){
                        arabResult+=4;
                        return arabResult;
                    }
                    if (input.charAt(i+1)=='I'){
                        arabResult+=2;
                        return arabResult;
                    }
                } else if (i==input.length()-3) {
                    arabResult+=3;
                    return arabResult;
                } else if (i==input.length()-4) {
                    System.out.println("Неправильно написана римская цифра! ЗАВЕРШЕНИЕ РАБОТЫ.");
                    System.exit(-1);
                }
            }
        }
        return arabResult;
    }
    public static String rimL1(int input){
        switch (input){
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            default: return "";
        }
    }
    public static String rimL10(int input){
        switch (input){
            case 1: return "X";
            case 2: return "XX";
            case 3: return "XXX";
            case 4: return "XL";
            case 5: return "L";
            case 6: return "LX";
            case 7: return "LXX";
            case 8: return "LXXX";
            case 9: return "XC";
            default: return "";
        }
    }
    public static String rimL100(int input){
        switch (input){
            case 1: return "C";
            case 2: return "CC";
            case 3: return "CCC";
            case 4: return "CD";
            case 5: return "D";
            case 6: return "DC";
            case 7: return "DCC";
            case 8: return "DCCC";
            case 9: return "CM";
            default: return "";
        }
    }
    public static String arabToRim(int a){
        //if (a<1) return "Результат операция над Римскими цифрами меньше 1. Не соответствует ТЗ";
        if (a<1) throw new ArithmeticException("Пункт 10 ТЗ");
        String result = "";

        result+=rimL100(a/100);
        result+=rimL10((a%100)/10);
        result+=rimL1((a%10)/1);

        return result;
    }
    public static String calc(String input){
        char[] mass = input.toCharArray();
        char operation = '0'; //храним какая будет операция +-/*
        int numOperation;

        String aMass = "";
        String bMass = "";

        int a = 0;
        int b = 0;
        int result = 0;

        for (int i = 0; i<mass.length; i++){
            if (mass[i]=='+' || mass[i]=='-' || mass[i]=='/' || mass[i]=='*'){
                    operation = mass[i];
                    numOperation = i;
                    //System.out.print("Операция: " + operation+ "\n" + "Номер операции в массиве: " + i + "\n");
                    for (int j = i+1; j<mass.length; j++){
                        bMass = bMass + mass[j];
                    }
                    break;
            }
            aMass = aMass + mass[i];
        }
        if (operation=='0') {
            //return "Арифметической операции +-/* НЕ найдено \n";
            throw new ArithmeticException("Арифметической операции +-/* НЕ найдено");
        }

        if (Main.isRim(aMass)&&Main.isRim(bMass)){
            if (rimToArab(aMass)>10 || rimToArab(bMass)>10) throw new ArithmeticException("Пункт 7 ТЗ");
            return arabToRim(getCalcResult(Main.rimToArab(aMass),Main.rimToArab(bMass),operation));
        }
        if (Main.isRim(aMass)^Main.isRim(bMass)){
            //return "Написаны арабские и римские - НЕДОПУСТИМО по ТЗ";
            throw new ArithmeticException("Написаны арабские и римские.");
        }

        try {
            a = Integer.parseInt(aMass);
            b = Integer.parseInt(bMass);
            if (a<1 || a>10 || b<1 || b>10) throw new ArithmeticException("Пункт 7 ТЗ");
            result = getCalcResult(a,b,operation);
        } catch (NumberFormatException  e){
            //return "Недопустимые значения!";
            System.out.println("Ошибка: " + e.getMessage());
        }

        return Integer.toString(result);
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.print("Введите задачу: \n");
        Scanner in = new Scanner(System.in);
        String prim = in.nextLine();

        System.out.print(calc(prim));
    }
}