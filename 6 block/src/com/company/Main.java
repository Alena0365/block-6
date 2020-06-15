package com.company;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static int inputInt() {
        Scanner in = new Scanner(System.in);
        int vvod = in.nextInt();
        return vvod;
    }

    public static long inputLong() {
        Scanner in = new Scanner(System.in);
        long vvod = in.nextLong();
        return vvod;
    }

    public static double inputDouble() {
        Scanner in = new Scanner(System.in);
        double vvod = in.nextDouble();
        return vvod;
    }

    public static String inputStr() {
        Scanner in = new Scanner(System.in);
        String vvod = in.nextLine();
        return vvod;
    }

    public static char inputchar() throws IOException {
        Scanner in = new Scanner(System.in);
        char vvod = (char) System.in.read();
        return vvod;
    }

    public static void task1( int n) {
        if (n<2){
            System.out.println("1");
            return;
        }

        int [][] nums = new int[n][n];
        for (int i=0; i<n;i++){
            for (int j = 0; j<n; j++){
                nums[i][j]=0;
            }
        }

        int c = 1, buf = 1;
        nums[0][0]=buf;

        for (int i=1; i<n;i++){
            nums[i][0]= buf;
            for (int j = 0; j<c; j++){
                nums[i][j+1]=nums[i][j]+nums[i-1][j];
                buf = nums[i][j]+nums[i-1][j];
            }
            c++;
        }

        for (int i = 0; i < n; i++){
            System.out.println(Arrays.toString(nums[i]));
        }
        System.out.println(nums[n-1][n-1]);
    }

    public static String task2_chapter1(String str) {
        boolean flag = false;
        String out = "";
        String first = "";
        String last = "";
        int ind = 0;

        if (str.charAt(0)=='a' || str.charAt(0)=='A'
                || str.charAt(0)=='e' || str.charAt(0)=='E'
                || str.charAt(0)=='i' || str.charAt(0)=='I'
                || str.charAt(0)=='o' || str.charAt(0)=='O'
                || str.charAt(0)=='u' || str.charAt(0)=='U'){
            out = str + "yay";
        }
        else{
            if (str.charAt(0)>=65 && str.charAt(0)<=90){
                flag = true;
            }
            for (int i = 0; i < str.length(); i++){
                if (str.charAt(i)=='a' || str.charAt(i)=='A'
                        || str.charAt(i)=='e' || str.charAt(i)=='E'
                        || str.charAt(i)=='i' || str.charAt(i)=='I'
                        || str.charAt(i)=='o' || str.charAt(i)=='O'
                        || str.charAt(i)=='u' || str.charAt(i)=='U') {
                    break;
                }
                else{
                    last += (char)str.charAt(i);
                    ind = i;
                }
            }
            out = str.substring(ind+1) + last;
            out = out.toLowerCase() + "ay";
            if (flag == true){
                first = "";
                first += (char)out.charAt(0);
                first = first.toUpperCase();
                last = out.substring(1);
                out = "";
                out = first + last;
            }
        }
        return out;
    }
    public static String task2_chapter2(String str) {
    String [] arr=str.split(" ");
    String out, out1="";
    for(int i=0;i<arr.length;i++){
        if((arr[i].charAt(arr[i].length()-1)<65||arr[i].charAt(arr[i].length()-1)>90)
        &&(arr[i].charAt(arr[i].length()-1)<97||arr[i].charAt(arr[i].length()-1)>122)){
            out=arr[i].substring(0,arr[i].length()-1);
            out=task2_chapter1(out);
            out+=arr[i].charAt(arr[i].length()-1);
        }
        else{
            out=arr[i].substring(0);
            out=task2_chapter1(out);
        }
        out1+=out+" ";
    }
    return out1;
    }
    public static boolean task3(String arr) {
        if (!arr.contains("rgb"))
            return false;
        String value = arr.substring(arr.indexOf("(") + 1, arr.indexOf(")"));
        String[] numbers = value.split(",");

        if (arr.contains("rgba") && numbers.length != 4)
            return false;
        if (arr.contains("rgb") && !arr.contains("rgba") && numbers.length != 3)
            return false;

        for (int i = 0; i < 3; i++)
        {
            if (Integer.parseInt(numbers[i]) < 0 || Integer.parseInt(numbers[i]) > 255)
                return false;
        }

        if (numbers.length == 4)
        {
            if (Double.parseDouble(numbers[3]) < 0 || Double.parseDouble(numbers[3]) > 1)
                return false;
        }
        return true;
    }
    public static String task4(String str) {
        String out = "";
        int ind = 0, ind1;
        while (str.charAt(ind) != 61) {
            ind++;
        }
        ind1 = ind - 1;
        String str1 = str.substring(ind - 1);
        String[] strs = str1.split("&");

        for (ind = 0; ind < strs.length; ind++) {
            for (int j = ind + 1; j < strs.length; j++) {
                if (strs[ind] != "" && strs[j] != "") {
                    if (strs[ind].charAt(0) == strs[j].charAt(0)) {
                        strs[ind] = strs[j];
                        strs[j] = "";
                    }
                }
            }
        }
        out+=str.substring(0,ind1);
        for(ind=0;ind<strs.length-1;ind++){
            out+=strs[ind]+"&";
        }
        while(out.charAt(out.length()-1)==38){
            out=out.substring(0,out.length()-1);
        }
        return out;
    }

    public static String[] task5(String str) {
        String [] val={"","",""};
        String [] sentence=str.split(" ");

        for(String word:sentence) {
            if (!Character.isLetter(word.charAt(word.length() - 1)))
                word = word.substring(0, word.length() - 1);

            if (word.length() > val[0].length()) {
                val[2] = val[1];
                val[1] = val[0];
                val[0] = word;
            } else if (word.length() > val[1].length()) {
                val[2] = val[1];
                val[1] = word;
            } else if (word.length() > val[2].length()) {
                val[2] = word;
            }
        }
        for (int i = 0; i < val.length; i++) {
            val[i] = "#" + val[i];
        }
        return val;
    }

    public static int task6(int n) {
        Vector<Integer> val = new Vector<Integer>();

        val.add(1);
        val.add(2);

        for (int i = 3; i < 2000; i++)
        {
            int c = 0;
            for (int j = 0; j < val.size() - 1; j++)
            {
                for (int k = j + 1; k < val.size(); k++)
                {
                    if (val.get(j) + val.get(k) == i)
                        c++;
                }
            }
            if (c == 1)
                val.add(i);
            if (val.size() == n)
                return i;
        }
        return -1;
    }

    public static void task7(String str) {
        boolean flag = false;
        String print = "";
        for (int i = 0; i < str.length()-1; i++) {
            String str1 = "";
            str1 += (char)str.charAt(i);
            for (int k = i + 1; k < str.length(); k++) {
                for (int j = 0; j < str1.length(); j++) {
                    if (str.charAt(k) == str1.charAt(j)) {
                        flag = true;
                        break;
                    }
                }
                if (flag != true) {
                    str1 += (char) str.charAt(k);
                }
                else{
                    flag = false;
                }
            }
            if (str1.length()>print.length()){
                print = "";
                print += str1;
            }
        }
        System.out.println("Результат: " + print);
    }

    public static void task8(int num) {
        if (num > 3999){
            return;
        }
        int [] nums = {1,2,3,4,5,6,7,8,9,10,20,30,40,50,90,100,200,300,400,500,900,1000,2000,3000};
        String [] roman_num = {"I","II","III","IV","V","VI","VII","VIII","IX","X","XX","XXX","XL","L",
                "XC","C","CC","CCC","CD","D","CM","M","MM","MMM"};
        String print = "";
        for (int i = nums.length-1; i >= 0; i--){
            if (nums[i]<=num){
                num=num-nums[i];
                print += roman_num[i];
            }
        }
        System.out.println("Римское число: " + print);
    }

    public static boolean task9 ( String str)    {
        String[]nums=str.split("=");
        int[]print=new int[nums.length];

        for(int i=0;i<nums.length;i++){
            String rep=nums[i].trim();
            nums[i]="";
            nums[i]=rep;
        }
        for(int i=0;i<nums.length;i++){
            String[]chapter=nums[i].split(" ");
            int oper=0;
            if(chapter.length==1){
                oper=Integer.parseInt(chapter[0]);
            }
            else{
                char oper1=chapter[1].charAt(0);
                if((int)oper1=='+'){
                    oper = Integer.parseInt(chapter[0]) + Integer.parseInt(chapter[2]);
                }
                if((int)oper1=='-'){
                    oper = Integer.parseInt(chapter[0]) - Integer.parseInt(chapter[2]);
                }
                if((int)oper1=='*'){
                    oper = Integer.parseInt(chapter[0]) * Integer.parseInt(chapter[2]);
                }
                if((int)oper1=='/'){
                    oper = Integer.parseInt(chapter[0]) / Integer.parseInt(chapter[2]);
                }
            }
            print[i]=oper;
        }
        int flag=print[0];
        for(int i=0;i<print.length;i++){
            if(print[i]!=flag){
                return false;
            }
        }
        return true;
    }

    public static boolean task10(int num){
        String str = "";
        String nums = "";
        int first , second , sum ;
        str += num;
        if (str.length() % 2 != 0){
            return false;
        }

        while (str.length() >= 2) {
            for (int i = 0; i < str.length() / 2; i++) {
                first = 9 - (57 - str.charAt(i * 2));
                second = 9 - (57 - str.charAt((i * 2) + 1));
                sum = first + second;
                nums += sum;
            }
            if (nums.length() >= 2) {
                str = nums;
            }
            else{
                break;
            }
            nums = "";
        }
        System.out.println(str);
        return str.length()==2 && (str.charAt(0)==str.charAt(1));
    }
    public static void main(String[] args) {
        int key=0;
        int key1=0;
        do{
            System.out.println("Введите номер задания или 11 для выхода:");
            key=inputInt();
            switch (key){
                case (1):
                    System.out.println("Введите число n: ");
                    int n=inputInt();
                    task1(n);
                    break;
                case(2):
                    do{
                        System.out.println("Введите номер подзадания или 3 для выбора основного задания:");
                        key1=inputInt();
                        switch (key1) {
                            case (1):
                                System.out.println("Введите слово: ");
                                String str = inputStr();
                                System.out.println("Перевод слова:" + task2_chapter1(str));
                                break;
                            case (2):
                                System.out.println("Введите предложение: ");
                                String str1=inputStr();
                                System.out.println("Перевод предложения: " + task2_chapter2(str1));
                            case(3):
                                break;
                        }
                    }while(key1!=3);
                    break;
                case(3):
                    System.out.println("Введите цвет в формате rgb(0,0,0)/rgba(0,0,0,0): ");
                    String str=inputStr();
                    System.out.println("Результат: " + task3(str));
                    break;
                case(4):
                    System.out.println("Введите URL: ");
                    String str1=inputStr();
                    System.out.println("Результат: "+ task4(str1));
                    break;
                case (5):
                    System.out.println("Введите заголовок газеты: ");
                    String str2= inputStr();
                    System.out.println("Результат: "+ Arrays.toString(task5(str2)));
                    break;
                case(6):
                    System.out.println("Введите число: ");
                    int a=inputInt();
                    System.out.println("Результат: " + task6(a));
                    break;
                case(7):
                    System.out.println("Введите строку: ");
                    String str3=inputStr();
                    task7(str3);
                    break;
                case(8):
                    System.out.println("Введите арабское число:");
                    int b = inputInt();
                    task8(b);
                    break;
                case(9):
                    System.out.println("Введите формулу: ");
                    String str4=inputStr();
                    System.out.println("Результат: "+ task9(str4));
                    break;
                case(10):
                    System.out.println("Введите число: ");
                    int c=inputInt();
                    System.out.println("Результат: "+ task10(c));
                    break;
                case(11):
                    System.out.println("До свидания!");
                    break;
            }
        } while (key!=11);
    }
}