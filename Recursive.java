
/**
 * This class performs various tasks recursively.
 * 
 * @author michael king
 * @version 03.06.02
 */
public class Recursive
{
    /**
     * This method determines if a string is a palindrome
     * pre: word contains no whitespace or punctual characters
     * post: returns true if its a palindrome. else, false.
     * @param  String word 
     */
    public boolean isPali(String word)
    {
        String newWord;
        
        //if we reach the 'middle' of the string
        if(!(word.length() <= 1))

            //if the front matches the back
            if(word.charAt(0) == word.charAt(word.length() - 1))
            {   newWord = word.substring(1, word.length() - 1);
                isPali(newWord);
                return true;
            }
        return false;
    }

    /**
     * This method determines the number of cannonballs in a pyramid.
     * pre: levels >= 0
     * post: returns number of cannonballs
     * @param  int levels
     */
    public int cannonball(int levels)
    {
        //if there are no levels
        if(levels == 0)
            return 0;

        //if we're at the top
        if(levels == 1)
            return 1;
        //number of cannonballs = (the level you're on)^2 + the above level count 
        else
            return levels * levels + cannonball(levels-1);
    }

    /**
     * This method generates all possible letter combinations for a given number
     * pre: none
     * post: returns all letter combinations
     * @param  String number
     */
    public void listMnemonics(String number)
    {
        mnemonicWorkhorse("", number);
    }
    
    /**
     * This method does the bulk of the work for listing mnemonics
     */
    private void mnemonicWorkhorse(String resultSoFar, String digRemain)
    {
        //if we reach the end of the string
        if(digRemain.equals(""))
        {
            System.out.println(resultSoFar + " ");
            return;
        }
        
        //creat a digit from the first of the remaning digits
        char digit = digRemain.charAt(0);

        //if theres more than one digit still left to count
        if(digRemain.length() > 1)
            digRemain = digRemain.substring(1);
        else
            digRemain = "";

        //get number of possible characters based on the digit
        String characters = digitLetters(digit);

        //add each possible character and all their combos to result
        for(int pos = 0; pos < characters.length(); pos++)
        {
            char possibleLetter = characters.charAt(pos);
            mnemonicWorkhorse(resultSoFar + possibleLetter, digRemain);
        }
    }

    /**This method lists all characters for a given digit */
    public static String digitLetters(char ch)
    {
        switch (ch) 
        {   case '0': return ("0");
            case '1': return ("1");
            case '2': return ("ABC");
            case '3': return ("DEF");
            case '4': return ("GHI");
            case '5': return ("JKL");
            case '6': return ("MNO");
            case '7': return ("PRS");
            case '8': return ("TUV");
            case '9': return ("WXY");
            default: {System.out.println("Illegal digit"); System.exit(1);return"";}
        }
    }
        

    /**
     * This method converts a number to binary.
     * pre: N >= 0
     * post: binary representation of N is printed
     * @param  int N
     */
    public void printBinary(int N)
    {
        //if the number isn't 1 or 0
        if(N >= 2)
        {
            //recursively call it until it is 
            printBinary(N/2);
            //print out the result of each recursive call
            System.out.print(N%2);          
        }
        else
        {
            System.out.print(N%2);
        }
    }

    /**
     * This method prints out a string in reverse.
     * pre: none
     * post: prints out a string in reverse.
     * @param  String target
     */
    public void revString(String target)
    {
        //if we're at the beginning of the string
        if(target.length() == 1)
            System.out.print(target.charAt(0));

        //print the character at the end of the string
        //then remove that letter and do it again
        else
        {
            System.out.print(target.charAt(target.length() - 1));
            revString(target.substring(0, target.length() - 1));
        }
    }

    /**
     * This method determines the sum of an integer's digits
     * pre: N >= 0
     * post: returns the integer value of the sum of an integer's digits
     * @param  int N
     */
    public int sumOfDigits(int N)
    {
        //convert the number to a string
        String temp = N + ""; 
        int endOfString = temp.length() - 1;

        //if there's only one number in the string, return that number
        if(temp.length() == 1)
            return Integer.parseInt(temp);

        //parse an integer from a new string from the number start to the end of the number - 1
        int newStringToNumber = Integer.parseInt(temp.substring(0, endOfString));

        //return the end of the number + the sum of the other digits
        return Integer.parseInt(temp.substring(endOfString)) + sumOfDigits(newStringToNumber);
   }

    /**
     * This method determines the number of 'organisms' in a matrix
     * pre: world is NxM
     * post: returns the number of organisms
     * @param  boolean [][] world
     */
    public int numThings(boolean [][] world)
    {
        int totalOrganisms = 0;
        boolean [][]cellsCounted = new boolean[world.length][world[0].length];

        for(int row = 0; row < world.length; row++)
            for(int col = 0; col < world[0].length; col++)
            {
                //if theres a cell
                if(world[row][col])
                    //if it hasn't been counted
                    if(!cellsCounted[row][col])
                    {
                        //mark all connecting cells
                        numThingsHelper(world, row, col, cellsCounted);
                        totalOrganisms++;
                    }    
            }
        return totalOrganisms;
    }

    private void numThingsHelper(boolean [][]world, int row, int col, boolean [][]cellsCounted)
    {
        //if we're in bounds of the array
        if(row < world.length && row >= 0 && col < world[0].length && col >= 0)
        {
            //if we reach a cell in the organism
            if(world[row][col])

                //if we haven't checked this one already
                if(!cellsCounted[row][col])
                {
                    cellsCounted[row][col] = true;
                    //check north
                    numThingsHelper(world, row-1, col, cellsCounted);
                    //check east
                    numThingsHelper(world, row, col+1, cellsCounted);
                    //check south
                    numThingsHelper(world, row+1, col, cellsCounted);
                    //check west
                    numThingsHelper(world, row, col-1, cellsCounted);
                }
        }
    }
    /**
     * This method determines the min from a given position to the end of the array
     * pre:  0 <= pos < list.length
     * post: returns the integer value of the min from pos to (list.length - 1)
     * @param  int []list, int pos
     */
    public int min(int [] list, int pos)
    {
        int min = list[pos];

        for(int x = pos; x < list.length; x++)
        {
            if(list[x] < min)
            {
                min = list[x];
                
                //find the new min between new min to list.length
                min(list, x);
            }
        }
        return min;
    } 
            
}
