import java.util.*;
public class BattleShipGame
{
//Method returns a 2d char array with the first row and col as coordinate labels and the remaining 8x8 as a game board
   public static char[][] generateBoard()
   {
      char[][] board = new char[9][9];
      for(int i = 1; i < 9; i++)
      {
         for(int j = 1; j < 9; j++)
            board[i][j] = '-';
      }
      board[0][0] = ' ';
      for(int i = 1; i < 9; i++)
      {
         board[0][i] = (char)(64+i);
      }
      for(int i = 1; i < 9; i++)
         board[i][0] = (char)(48+i);
      return board;
   }
   //Method displays the game board
   public static void printBoard(char[][] board)
   {
      for(int i = 0; i < board.length; i++)
      {
         for(int j = 0; j < board[0].length; j++)
            System.out.print(board[i][j]);
         System.out.println();
      }
   }
   //Method generates 4 ship pieces on the game board
   public static void generatePieces(char[][]board)
   {
      for(int i = 0; i < 5; i++)
         board[2][3+i] = '=';
      for(int i = 0; i < 4; i++)
         board[5+i][2] = '=';
      for(int i = 0; i < 3; i++)
         board[4+i][6] = '=';
      for(int i = 0; i < 2; i++)
         board[8][7+i] = '=';
   }
   public static void main(String[] args)
   {
      boolean run = true;
      Scanner input = new Scanner(System.in);
      while(run)           //Continue until player says no
      {
         int shots = 0;
         int hits = 0;
         char[][] board = generateBoard();   //Generate hidden game board 
         char[][] displayBoard = generateBoard();  //Generate game board for user to see
      
         generatePieces(board);              //add pieces to hidden board
      
         while(hits < 14)           //Continue until all ships have sunk
         {
            //printBoard(board);
            printBoard(displayBoard);
            System.out.println("Enter a target location. Example: D 3");      //Store user input for shot
            char column = input.next().charAt(0);
            int row = input.nextInt();
            int col = (int)(column - 64);
            //System.out.println(col+ " "+row);
            if(board[row][col] == '=')          //If the board has a ship at the location 
            {
               System.out.println("Hit");
               displayBoard[row][col] = 'X';    //Mark user board has a hit
               board[row][col] = 'X';           //Mark real board has a hit
               shots++;
               hits++;
            }
            else if(board[row][col] == 'X')     //If the user inputs a location that is already a hit do nothing
               System.out.println("That location has already been hit, Pick a new location");
            else if(board[row][col] == 'O')
            System.out.println("That location already is a miss, pick a new location"); //If the user inputs a location that is already a miss do nothing
            else
            {
               System.out.println("Miss");      //Mark the board as a miss
               displayBoard[row][col] = 'O';
               shots++;
            }
         }
         System.out.println("Congratulations! All ships have been sunk.\nYou won in " + shots +" shots");
         System.out.println("Would you like to play again? ( y / n )");
         String response = input.next();
         if(response.equals("n"))      
            run = false;
      }
   }
}