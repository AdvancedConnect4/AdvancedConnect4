package connect4;
import java.util.Scanner;

public class ConnectFour
{
    public static int yellow = 0;
    public static int red = 1;
    public static int tie = 2;
    public static int yellowCoins = 100;
    public static int redCoins = 100;
    
public static String[][] createPattern()
{
   String[][] f = new String[7][15];
  for (int i =0;i<f.length;i++)
  {
     for (int j =0;j<f[i].length;j++)
    {
      if (j% 2 == 0)
      {
          f[i][j] ="|";
      }
      else
      {
          f[i][j] = " ";
      }
      if (i==6) 
      {
          f[i][j]= "-";
      }
    }
  }
  return f;
}




public static void printPattern(String[][] f)
{
  for (int i =0;i<f.length;i++)
  {
    for (int j=0;j<f[i].length;j++)
    {
      System.out.print(f[i][j]);
    }
    System.out.println();
  }
}


public static int getNextPlayer()
{
    System.out.println("How much to bid(red): ");
    Scanner scan = new Scanner(System.in);
    int playerRed = scan.nextInt();
    if (playerRed > redCoins)
    {
        System.out.println( "You don't have that many coins. This bid is invalid and "
            + "your turn is skipped" );
        playerRed = 0;
    }
    redCoins = redCoins - playerRed;
    
    System.out.println("How much to bid(yellow): ");
    int playerYellow = scan.nextInt();
    if (playerYellow > yellowCoins)
    {
        System.out.println( "You don't have that many coins. This bid is invalid and "
                        + "your turn is skipped" );
        playerYellow = 0;
    }
    yellowCoins = yellowCoins - playerYellow;
    
    if(playerYellow > playerRed)
    {
        return yellow;
    }
    else if(playerYellow == playerRed)
    {
        return tie;
    }
    else
    {
        return red;
    }


}

public static void dropRedPattern(String[][] f)
{
  System.out.println("Drop a red disk at column (0–6): ");
  Scanner scan = new Scanner (System.in);

  int c = 2*scan.nextInt()+1;
  
  for (int i =5;i>=0;i--)
  {
    if (f[i][c] == " ")
    {
      f[i][c] = "R";
      break;
    }
  }

}



public static void dropYellowPattern(String[][] f)
{
  System.out.println("Drop a yellow disk at column (0–6): ");
  Scanner scan = new Scanner (System.in);
  int c = 2*scan.nextInt()+1;
  for (int i =5;i>=0;i--)
  {
    if (f[i][c] == " ")
    {
      f[i][c] = "Y";
      break;
    }
  }

}

public static String checkWinner(String[][] f)
{
  for (int i =0;i<6;i++)
  {
    for (int j=0;j<7;j+=2)
    {
      if ((f[i][j+1] != " ")
      && (f[i][j+3] != " ")
      && (f[i][j+5] != " ")
      && (f[i][j+7] != " ")
      && ((f[i][j+1] == f[i][j+3])
      && (f[i][j+3] == f[i][j+5])
      && (f[i][j+5] == f[i][j+7])))
      return f[i][j+1]; 
    }
  }

  for (int i=1;i<15;i+=2)
  {   
      for (int j =0;j<3;j++)
      {
          if((f[j][i] != " ")
          && (f[j+1][i] != " ")
          && (f[j+2][i] != " ")
          && (f[j+3][i] != " ")
          && ((f[j][i] == f[j+1][i])
          && (f[j+1][i] == f[j+2][i])
          && (f[j+2][i] == f[j+3][i])))
            return f[j][i]; 
      } 
  }
 
  for (int i=0;i<3;i++)
  {
      for (int j=1;j<9;j+=2)
      {
          if((f[i][j] != " ")
          && (f[i+1][j+2] != " ")
          && (f[i+2][j+4] != " ")
          && (f[i+3][j+6] != " ")
          && ((f[i][j] == f[i+1][j+2])
          && (f[i+1][j+2] == f[i+2][j+4])
          && (f[i+2][j+4] == f[i+3][j+6])))
            return f[i][j]; 
    } 
  }
 
  for (int i=0;i<3;i++)
  {
    for (int j=7;j<15;j+=2)
    {
          if((f[i][j] != " ")
          && (f[i+1][j-2] != " ")
          && (f[i+2][j-4] != " ")
          && (f[i+3][j-6] != " ")
          && ((f[i][j] == f[i+1][j-2])
          && (f[i+1][j-2] == f[i+2][j-4])
          && (f[i+2][j-4] == f[i+3][j-6])))
            return f[i][j]; 
    } 
  }
  return null;
}



public static void main (String[] args)
{
  String[][] f = createPattern();
  boolean loop = true;
  printPattern(f);
  while(loop)
  {
      int playerTurn = getNextPlayer();
      if(playerTurn == red)
      {
          dropRedPattern(f);
          System.out.println( "Yellow has " + Integer.valueOf( yellowCoins ) + " coins left");
          System.out.println( "Red has " + Integer.valueOf( redCoins ) + " coins left");
      } 
      else if (playerTurn == yellow) 
      {
         dropYellowPattern(f);
         System.out.println( "Yellow has " + Integer.valueOf( yellowCoins ) + " coins left");
         System.out.println( "Red has " + Integer.valueOf( redCoins ) + " coins left");
     }
     else
     {
         System.out.println("There was a tie");
         System.out.println( "Yellow has " + Integer.valueOf( yellowCoins ) + " coins left");
         System.out.println( "Red has " + Integer.valueOf( redCoins ) + " coins left");
     }
     printPattern(f);
     
     if (checkWinner(f) != null)
     {
        if (checkWinner(f) == "R")
        {
            System.out.println("The red player won.");
            
        }           
        else if (checkWinner(f)== "Y")
        {
            System.out.println("The yellow player won.");
        }
       loop = false;
  }
  }
}

}
