package connect4;

import java.util.Scanner;

public class ComputerPlayer 
{
    public static int computerCoins = 100;

    
    public static int checkIfComputerWins(String[][] f) 
    {
        for (int row =0; row<6; row++) 
        {
            for (int col =0; col<7; col++) 
            {                
                //Check vertical (RRR_ or _RRR)
                if (row > 1 && f[row][col] == "R" && f[row-1][col] == "R" && f[row-2][col] == "R") 
                {
                    if (row > 2 && row < 5 && f[row-3][col] == " " || row < 5 && f[row+1][col] == " ") 
                    {
                        return col;                  
                    }
                }
                
                //Check horizontal, one empty between 4 R (RR_R or R_RR) 
                if (col < 5  && f[row][col] == "R" && f[row][col+ 1] == "R")   
                {
                    //(RR_R)
                    if (col < 4 && row < 5 && f[row][col+ 2] == " " && f[row][col+3] != " " && f[row + 1][col+2] != " ") 
                    {
                        return col+ 2;
                    }
                    else if (col >= 2 && row < 5 && f[row][col -1] == " " && f[row][col -2] != " " && f[row + 1][col-1] != " ") 
                    {//R_RR
                        return col - 1;
                    }
                }  
                //Check horizontal, 3 R continous(RRR_ or _RRR)
                if (col < 5  && row < 5 && f[row][col] == "R" && f[row][col+ 1] == "R" && f[row][col+ 2] == "R")   
                {
                    if (col < 4 &&  f[row][col+ 3] == " " && f[row + 1][col+3] != " ") {
                        return col+ 3;
                    }
                    else if (col >= 1 &&  f[row][col -1] == " " && f[row + 1][col-1] != " ") {
                        return col -1;
                    }
                }    
                
                //check diagonal bottom up
                if (col < 5 && row > 1 && f[row][col] == "R" && f[row-1][col + 1] == "R" && f[row-2][col + 2] == "R") {
                    
                    if (row > 3 && col < 4 && f[row -3 ][col + 3] == " " && f[row -2 ][col + 3] != " ") {
                        System.out.println("col computer=" + col);
                        return col + 3;
                    }
                    else if (col > 1 && row < 4 && f[row +1 ][col -1] == " " && f[row + 2 ][col - 1] != " ") {
                        System.out.println("col computer=" + col);
                        return col + 2;
                    }
                }                  
            }
        }        
        return -1; //player chance to play
    }
    
    
    public static int getComputerTurn(String[][] f)
    {
        int colCheck = checkIfComputerWins(f);
        if (colCheck != -1) {
            System.out.println("checkIfComputerWins col="  + colCheck);
            return colCheck;
        }
        
        for (int row =0; row<6; row++) 
        {
          for (int col =0; col<7; col++)
          {
              //Check horizontal RRR
              if (col < 5  && f[row][col] == "R" && f[row][col+ 1] == "R" && f[row][col+ 2] == "R")   
              {
                  //RRR_
                  if (col < 4 &&  f[row][col+ 3] == " "  && (row == 5 || row <= 5 && f[row + 1][col+3] != " ")) {
                      return col+ 3;
                  } //_RRR
                  else if (col >= 1 &&  f[row][col -1] == " " && (row == 5 || row < 5 && f[row + 1][col-1] != " ")) {
                      return col -1;
                  }                  
              } else if (col < 6  && f[row][col] == "R" && f[row][col+ 1] == "R") {
                  //RR_R
                  if (col < 4 && f[row][col+ 2] == " "  &&  (row == 5 || row < 5 && f[row + 1][col+2] != " ") 
                          && f[row][col+ 3] == "R") {
                      return col+ 2;
                  } //R_RR
                  else if (col >= 2 &&  f[row][col -1] == " " && (row == 5 || row < 5 && f[row + 1][col-1] != " ")
                          && f[row][col- 2] == "R") {
                      return col -1;
                  }                                    
              }
              //diagonal RRR_
              if (col < 3 && row > 2 && 
                      f[row][col] == "R" && f[row-1][col + 1] == "R" && f[row -2][col + 2] == "R" 
                          && f[row-3][col + 3] == " " && f[row-2][col + 3] != " ") {
                  return col + 3;
              } //diagonal RR_R 
              else if (col < 3 && row > 2 && 
                          f[row][col] == "R" && f[row-1][col + 1] == "R" && f[row -2][col + 2] == " " 
                              && f[row-3][col + 3] == "R" && f[row-1][col + 2] != " ") {
                      return col + 2;
              } //diagonal R_RR 
              else if (col < 3 && row > 2 && 
                      f[row][col] == "R" && f[row-1][col + 1] == " " && f[row -2][col + 2] == "R" 
                          && f[row-3][col + 3] == "R" && f[row][col + 1] != " ") {
                  return col + 1;
              }  //diagonal _RRR 
              else if (col < 3 && row > 2 && 
                      f[row][col] == " " && f[row-1][col + 1] == "R" && f[row -2][col + 2] == "R" 
                          && f[row-3][col + 3] == "R") {
                  return col;
              }
    
              //Check vertical RRR_
              if (row > 1 && f[row][col] == "R" && f[row-1][col] == "R" && f[row-2][col] == "R") {
                  if (row > 2 && f[row-3][col] == " ") {
                      return col;                  
                  }
              //    System.out.println("No vertical");
              }

              //Check horizontal YYY
              if (col < 5  && f[row][col] == "Y" && f[row][col+ 1] == "Y" && f[row][col+ 2] == "Y")   
              {
                  //YYY_
                  if (col < 4 &&  f[row][col+ 3] == " "  && (row == 5 || row < 5 && f[row + 1][col+3] != " ")) {
                      return col+ 3;
                  } //_YYY
                  else if (col >= 1 &&  f[row][col -1] == " " && (row == 5 || row < 5 && f[row + 1][col-1] != " ")) {
                      return col -1;
                  }                  
              }
              //Check vertical YYY_
              if (row > 1 && f[row][col] == "Y" && f[row-1][col] == "Y" && f[row-2][col] == "Y") {
                  if (row > 2 && f[row-3][col] == " ") {
                      return col;                  
                  }
              //    System.out.println("No vertical");
              }
              
              //diagonal YYY_
              if (col < 3 && row > 2 && 
                      f[row][col] == "Y" && f[row-1][col + 1] == "Y" && f[row -2][col + 2] == "Y" 
                          && f[row-3][col + 3] == " " && f[row-2][col + 3] != " ") {
                  return col + 3;
              } //diagonal YY_Y 
              else if (col < 3 && row > 2 && 
                          f[row][col] == "Y" && f[row-1][col + 1] == "Y" && f[row -2][col + 2] == " " 
                              && f[row-3][col + 3] == "Y" && f[row-1][col + 2] != " ") {
                      return col + 2;
              } //diagonal Y_YY 
              else if (col < 3 && row > 2 && 
                      f[row][col] == "Y" && f[row-1][col + 1] == " " && f[row -2][col + 2] == "Y" 
                          && f[row-3][col + 3] == "Y" && f[row][col + 1] != " ") {
                  return col + 1;
              }  //diagonal _YYY 
              else if (col < 3 && row > 2 && 
                      f[row][col] == " " && f[row-1][col + 1] == "Y" && f[row -2][col + 2] == "Y" 
                          && f[row-3][col + 3] == "Y") {
                  return col;
              }

              //Check horizontal YY
              if (col < 6  && f[row][col] == "Y" && f[row][col+ 1] == "Y")   
              {    
                  //System.out.println("horizontal row=" + row + " col=" + col);
                  //YY_ horizontal
                  if (col < 5 &&  f[row][col+ 2] == " "  && (row == 5 || row < 5 && f[row + 1][col+2] != " ")) {
                      //Y_YY_
                      if (col >= 2 &&  f[row][col -1] == " " && f[row][col -2] == "Y" &&  
                              (row == 5 || row < 5 && f[row + 1][col-1] != " ")) {
                          return col -1;
                      }
                      return col+ 2;
                  } //_YY horizontal
                  else if (col >= 1 &&  f[row][col -1] == " " && (row == 5 || row < 5 && f[row + 1][col-1] != " ")) {
                      return col -1;
                  }
              //    System.out.println("No horizontol");
              } 
              //vertical YY_
              if (row > 0 && f[row][col] == "Y" && f[row-1][col] == "Y") {
                  if (row > 1 && f[row-2][col] == " " || row < 5 && f[row+1][col] == " ") {
                      return col;                  
                  }
              //    System.out.println("No vertical");
              }
              
            }
        }
        //Now check for diagonal YY_(left to right upward)
        for (int row =0; row<6; row++) {
            for (int col =0; col<7; col++) {
                if (col < 5 && row > 0 && 
                        f[row][col] == "Y" && f[row-1][col + 1] == "Y" && f[row-1][col + 2] != " ") {
                    //System.out.println("col computer=" + col);
                    return col + 2;
                }                  
            }
        }

        int scan = (int) (Math.random() * 7);
        return scan;
    }
    
    public static void dropRedPattern(String[][] f)

    {
      //System.out.println("Drop a red disk at column (0–6): ");
      //Scanner scan = new Scanner (System.in);

      int changeToOdd = getComputerTurn(f);
      
      //System.out.println("computer=" + changeToOdd);
    //  int changeToOdd = 2*scan +1;
     
      for (int row =5; row>=0; row--)
     {
       if (f[row][changeToOdd] == " " || f[row][changeToOdd] == "|")

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
