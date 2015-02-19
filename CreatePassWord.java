import java.util.Scanner;
import java.util.Random;
public class CreatePassWord
{
    public static UniChars GetChars = new UniChars();
    public static int totalAlphabetic = GetChars.charUpper.length + GetChars.charLower.length;
    public static char[] charMixed = new char[totalAlphabetic];
    public static char[] password = new char[200];
    public static Random randNum = new Random();
    public static void main(String[] args)
    {
        int length = 0;
        boolean mixedCase = true;
        boolean upperCase = false;
        boolean lowerCase = false;
        int numberAmt = 0;
        int specialAmt = 0;
        int totalChars = 0;
        boolean userIsMoron = true;
        char answer = '\u0000';
        int charCount = 0;
        Scanner input = new Scanner(System.in);
        if (args.length == 3)
        {
            length = Integer.parseInt(args[0]);
            numberAmt = Integer.parseInt(args[1]);
            specialAmt = Integer.parseInt(args[2]);
        }
        else
        {
            while (userIsMoron)
            {
                totalChars = 0;
                System.out.print("enter number of characters      -> ");
                length = input.nextInt();
                System.out.print("number of numerical characters -> ");
                numberAmt = input.nextInt();
                totalChars += numberAmt;
                System.out.print("number of special characters    -> ");
                specialAmt = input.nextInt();
                totalChars += specialAmt;
                userIsMoron = checkTotal(totalChars,length) ? false : true;
            }
            System.out.print("upper case only? y or n          -> ");
            answer = input.next().charAt(0);
            upperCase = (answer == 'y' || answer == 'Y');
            if (! upperCase)
            {
                System.out.print("lower case only? y or n          -> ");
                answer = input.next().charAt(0);
                lowerCase = (answer == 'y' || answer == 'Y');
            }
            if ( upperCase == true || lowerCase == true) { mixedCase = false; }
        }
        System.arraycopy(GetChars.charUpper, 0, charMixed, 0, GetChars.charUpper.length);
        System.arraycopy(GetChars.charLower, 0, charMixed, GetChars.charUpper.length, GetChars.charLower.length);
        populatePassword(length, numberAmt, specialAmt, mixedCase, upperCase, lowerCase);
        scramblePassword(length);
        for ( char charMyChar : password ) { System.out.print(charMyChar); }
        System.out.println();
    } // end of main
    public static boolean checkTotal(int soFar, int max)
    {
        if (soFar <= max) { return true; }
        else { return false; }
    }
    public static void populatePassword(int totChars, int icnt, int scnt, boolean mxd, boolean uc, boolean lc)
    {
        int passCount = 0;
        while (passCount < icnt) { password[passCount++] = GetChars.charNumber[randNum.nextInt(GetChars.charNumber.length)]; }
        while (passCount < icnt + scnt) { password[passCount++] = GetChars.charSpecial[randNum.nextInt(GetChars.charSpecial.length)]; }
        if ((totChars - passCount >= 1) && (mxd == true)) // if we have 2 or more spaces left and are writing mixed case
        {
            password[passCount++] = GetChars.charUpper[randNum.nextInt(GetChars.charUpper.length)]; // make one upper case
            password[passCount++] = GetChars.charLower[randNum.nextInt(GetChars.charLower.length)]; // and one lower case
        }
        if (uc == true)
        { 
            while (passCount < totChars)
            {
                password[passCount++] = GetChars.charUpper[randNum.nextInt(GetChars.charUpper.length)];
            }
        }
        else if (lc == true)
        {
            while (passCount < totChars) 
            {
                password[passCount++] = GetChars.charLower[randNum.nextInt(GetChars.charLower.length)];
            }
        }
        else
        {
            while (passCount < totChars) 
            {
                password[passCount++] = charMixed[randNum.nextInt(charMixed.length)];
            }
        }
    }
    public static void scramblePassword(int lng)
    {
        char tmpChar;
        int rnd;
        for (int gosix = 0; gosix < 6; gosix++)
        {
            for (int stepNum = 0; stepNum < lng; stepNum++)
            {
                rnd = randNum.nextInt(lng);
                tmpChar = password[stepNum];
                password[stepNum] = password[rnd];
                password[rnd] = tmpChar;
            }
        }
    }
}
