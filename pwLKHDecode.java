import java.util.Scanner;

public class pwLKHDecode {
    public static void main(String[] args) {

        // Example1:
        // Raw: 43087920; Encrypted: 46312478
        // Example2:
        // Raw: 9068900782366; Encrypted: 0076214432043
        // Example3:
        // Raw: 6789012345432109876; Encrypted: 0123456789876543210


        System.out.println("Please enter the code that you want to decode by LKH (No shorter than 5 digits and no longer than 19 digits)");
        Scanner input = new Scanner(System.in);
        String pw = input.next();
        // If input is shorter than 5 digits and longer than 19 digits, try again
        while(pw.length()<5 || pw.length()>19){
            System.out.println("Please try again. Your password should not be shorter than 5 digits and longer than 19 digits");
            pw = input.next();
        }

        // Get password decoded
        if(pw.length()<=10){
            // In this case, password is an int

            // Turn a string password into an int array
            int[] pwIntArr = getPasswordArray(pw);
            String decodedCode = decode(pwIntArr);
            // print out your original password
            System.out.println("Your original password is "+decodedCode);
        }
        else{
            // In this case, password is a long

            // Turn a string password into an long array
            long[] pwIntArr = getPasswordArrayLong(pw);
            String decodedCode = decode(pwIntArr);
            // print out your original password
            System.out.println("Your original password is "+decodedCode);
        }


    }

    public static int[] getPasswordArray(String pw){
        // Entering password into an array

        int password = Integer.parseInt(pw);
        int temp = password;


        int count = 0;
        while(temp != 0){
            temp/=10;
            count++;
        }

        // See how many starting zero(s) get missed
        int missZero=pw.length() - count;

        // Initializing array for each digit in password
        int[] passwordArray = new int[pw.length()];

        // Push every digit of password into the array
        for (int i = pw.length()-1; i >= 0; i--) {
            if(i<missZero){
                // push 0 into the beginning
                passwordArray[i]=0;
            }
            else{
                // Get last digit and push it to the last index
                passwordArray[i]=password%10;
                password/=10;
            }
        }

        // Print out array
        // printArray(passwordArray);

        return passwordArray;
    }

    public static long[] getPasswordArrayLong(String pw){
        // Entering password into an array

        long password = Long.parseLong(pw);
        long temp = password;

        int count = 0;
        while(temp != 0){
            temp/=10;
            count++;
        }

        // See how many starting zero(s) get missed
        int missZero=pw.length() - count;

        // Initializing array for each digit in password
        long[] passwordArray = new long[pw.length()];

        // Push every digit of password into the array
        for (int i = pw.length()-1; i >= 0; i--) {
            if(i<missZero){
                // push 0 into the beginning
                passwordArray[i]=0;
            }
            else{
                // Get last digit and push it to the last index
                passwordArray[i]=password%10;
                password/=10;
            }
        }

        // Print out array
        // printArray(passwordArray);

        return passwordArray;
    }

    public static String decode(int[] pwIntArr){

        // Decode the password following LKH policy

        // add 4 into each element, modulus
        for (int i = 0; i < pwIntArr.length; i++) {
            pwIntArr[i]=pwIntArr[i]+10-6;
            pwIntArr[i]=pwIntArr[i]%10;
        }

        // reverse the array
        pwIntArr=reverse(pwIntArr);

        String key = "";
        for (int i = 0; i < pwIntArr.length; i++) {
            key = key+pwIntArr[i];
        }
        return key;
    }

    // 重载Long
    public static String decode(long[] pwIntArr){

        // Decode the password following LKH policy

        // add 4 into each element, modulus
        for (int i = 0; i < pwIntArr.length; i++) {
            pwIntArr[i]=pwIntArr[i]+10-6;
            pwIntArr[i]=pwIntArr[i]%10;
        }

        // reverse the array
        pwIntArr=reverse(pwIntArr);

        String key = "";
        for (int i = 0; i < pwIntArr.length; i++) {
            key = key+pwIntArr[i];
        }
        return key;
    }

    public static int[] reverse(int[] arr){
        // reverse the array
        for (int i = 0, j=arr.length-1; i < j; i++, j--) {
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        return arr;
    }

    public static long[] reverse(long[] arr){
        // reverse the array
        for (int i = 0, j=arr.length-1; i < j; i++, j--) {
            long temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        return arr;
    }
}
