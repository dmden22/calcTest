import java.util.Arrays;
import java.util.Scanner;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
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
    public static String arabToRim(int a){
        if (a<1) return "Результат операция над Римскими цифрами меньше 1. Не соответствует ТЗ";
        String result = "";
        for (int i = 0; i<a/100; i++){
            result += "C";
        }
        for (int i = 0; i<(a%100)/10; i++) {
            result += "X";
        }
        switch (a%10){
            case 1:
                result+="I";
                break;
            case 2:
                result+="II";
                break;
            case 3:
                result+="III";
                break;
            case 4:
                result+="IV";
                break;
            case 5:
                result+="V";
                break;
            case 6:
                result+="VI";
                break;
            case 7:
                result+="VII";
                break;
            case 8:
                result+="VIII";
                break;
            case 9:
                result+="IX";
                break;
        }
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
            return "Арифметической операции +-/* НЕ найдено \n";
        }

        if (Main.isRim(aMass)&&Main.isRim(bMass)){
            if (rimToArab(aMass)>10 || rimToArab(bMass)>10) return "Римские цифры больше 10! Не соотвествует ТЗ.";
            return arabToRim(getCalcResult(Main.rimToArab(aMass),Main.rimToArab(bMass),operation));
        }
        if (Main.isRim(aMass)^Main.isRim(bMass)){
            return "Написаны арабские и римские - НЕДОПУСТИМО по ТЗ";
        }

        try {
            a = Integer.parseInt(aMass);
            b = Integer.parseInt(bMass);
            if (a<1 || a>10 || b<1 || b>10) return "Недопустимые значение арабских цифр! Не соответствует ТЗ";
            result = getCalcResult(a,b,operation);
        } catch (NumberFormatException  e){
            return "Недопустимые значения!";
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