package connect4;

import java.util.Scanner;

public class ComputerPlayer 
{
    public static int computerCoins = 100;

    public static int getComputerBid()
    {
        int bid = (int)(computerCoins/8 + (int)(Math.random() * ((computerCoins/6 - computerCoins/8) + 1)));
        if (bid > computerCoins)
        {
            return 0;
        }
        computerCoins -= bid;
        return bid;
    }
    public static int checkIfComputerWins(String[][] f) {
        int changeToOdd = 0;
        for (int row =0; row<6; row++) {
            for (int col =0; col<7; col++) {
                changeToOdd = 2*col +1;
                //Check horizontal, one empty between 4 R (RR_R or R_RR) 
                if (changeToOdd < 12  && f[row][changeToOdd] == "R" && f[row][changeToOdd+ 2] == "R")   
                {
                    //(RR_R)
                    if (changeToOdd < 8 && f[row][changeToOdd+ 4] == " " && f[row][changeToOdd+6] != " " && f[row + 1][changeToOdd+4] != " ") {
                        return changeToOdd+ 4;
                    }
                    else if (changeToOdd >= 2 && f[row][changeToOdd -2] == " " && f[row][changeToOdd -4] != " " && f[row + 1][changeToOdd-2] != " ") {//R_RR
                        return changeToOdd -2;
                    }
                }  
                //Check horizontal, 3 R continous(RRR_ or _RRR)
                if (changeToOdd < 10  && f[row][changeToOdd] == "R" && f[row][changeToOdd+ 2] == "R" && f[row][changeToOdd+ 4] == "R")   
                {
                    if (changeToOdd < 8 &&  f[row][changeToOdd+ 6] == " " && f[row + 1][changeToOdd+6] != " ") {
                        return changeToOdd+ 6;
                    }
                    else if (changeToOdd >= 2 &&  f[row][changeToOdd -2] == " " && f[row + 1][changeToOdd-2] != " ") {
                        return changeToOdd -2;
                    }
                }    
                //Check vertical (RRR_ or _RRR)
                if (row > 1 && f[row][changeToOdd] == "R" && f[row-1][changeToOdd] == "R" && f[row-2][changeToOdd] == "R") {
           //         System.out.println("vertical row=" + row + " changeToOdd=" + changeToOdd);
                    if (row > 2 && f[row-3][changeToOdd] == " " || f[row+1][changeToOdd] == " ") {
                //        System.out.println("Return row changeToOdd=" + changeToOdd);
                        return changeToOdd;                  
                    }
                }
                //check diagonal bottom up
                if (changeToOdd < 10 && row > 1 && f[row][changeToOdd] == "R" && f[row-1][changeToOdd + 2] == "R" && f[row-2][changeToOdd + 4] == "R") {
                    
                    if (row > 3 && changeToOdd < 8 && f[row -3 ][changeToOdd + 6] == " " && f[row -2 ][changeToOdd + 6] != " ") {
                        System.out.println("col computer=" + changeToOdd);
                        return changeToOdd + 6;
                    }
                    else if (changeToOdd > 1 && f[row +1 ][changeToOdd -2] == " " && f[row + 2 ][changeToOdd - 2] != " ") {
                        System.out.println("col computer=" + changeToOdd);
                        return changeToOdd + 4;
                    }
                }                  
            }
        }        
        return -1; //player chance to play
    }
    
    public static int getComputerChoice(String[][] f)
    {
        int changeToOdd = checkIfComputerWins(f);
        if (changeToOdd != -1) {
            System.out.println("checkIfComputerWins changeToOdd="  + changeToOdd);
            return changeToOdd;
        }
        
        for (int row =0; row<6; row++) {
          for (int col =0; col<7; col++) {
              changeToOdd = 2*col +1;
              //Check horizontal 
              if (changeToOdd < 12  && f[row][changeToOdd] == "Y" && f[row][changeToOdd+ 2] == "Y")   
              {    
                  System.out.println("horizontal row=" + row + " changeToOdd=" + changeToOdd);
                  if (changeToOdd < 10 &&  f[row][changeToOdd+ 4] == " " && f[row + 1][changeToOdd+4] != " ") {
                      return changeToOdd+ 4;
                  }
                  else if (changeToOdd >= 2 &&  f[row][changeToOdd -2] == " " && f[row + 1][changeToOdd-2] != " ") {
                      return changeToOdd -2;
                  }
              //    System.out.println("No horizontol");
              } 
              //Check vertical
              if (row > 0 && f[row][changeToOdd] == "Y" && f[row-1][changeToOdd] == "Y") {
                  //System.out.println("vertical row=" + row + " changeToOdd=" + changeToOdd);
                  if (row > 1 && f[row-2][changeToOdd] == " " || f[row+1][changeToOdd] == " ") {
                      //System.out.println("Return changeToOdd=" + changeToOdd);
                      return changeToOdd;                  
                  }
              //    System.out.println("No vertical");
              }
              if (changeToOdd < 10 && row > 0 && 
                      f[row][changeToOdd] == "Y" && f[row-1][changeToOdd + 2] == "Y" && f[row][changeToOdd + 4] != " " && f[row -1 ][changeToOdd + 4] != " ") {
                  System.out.println("col computer=" + changeToOdd);
                  return changeToOdd + 4;
              }  
            }
        }
        //Now check for diagonal 
        for (int row =0; row<6; row++) {
            for (int col =0; col<7; col++) {
                changeToOdd = 2*col +1;
                //System.out.println("diagonal row=" + row + " changeToOdd=" + changeToOdd);
                if (changeToOdd < 10 && row > 0 && 
                        f[row][changeToOdd] == "Y" && f[row-1][changeToOdd + 2] == "Y" && f[row][changeToOdd + 4] != " " && f[row -1 ][changeToOdd + 4] != " ") {
                    System.out.println("col computer=" + changeToOdd);
                    return changeToOdd + 4;
                }                  
              }
          }
        int scan = (int) (Math.random() * 7);
        return 2*scan +1;
    }
    
    public static void dropRedPattern(String[][] f)

    {
      //System.out.println("Drop a red disk at column (0–6): ");
      //Scanner scan = new Scanner (System.in);

      int changeToOdd = getComputerChoice(f);
      
      //System.out.println("computer=" + changeToOdd);
    //  int changeToOdd = 2*scan +1;
     
      for (int row =5; row>=0; row--)
     {
       if (f[row][changeToOdd] == " ")

       {
         f[row][changeToOdd] = "R";

          break;

        }


      }

    }
    public static int getRedCoins()
    {
        return computerCoins;
    }
}
