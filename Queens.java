// javac Queens.java StdDraw.java
// java Queens 10
// How to run Queen from command prompt?
// How to specify board size from command prompt?

class Queens {
	
    void draw(int[] board) {
    	// int[] board is an integer array containing the y position
        int n = board.length;
        StdDraw.setXscale(0, n+2);
        StdDraw.setYscale(0, n+2);
        
        for (int x=1; x<=n; x++) {
            for (int y=1; y<=n; y++) {
                if ((y % 2) != 0) {
                    if ((x % 2) != 0)  {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                    else {
                        StdDraw.setPenColor(StdDraw.ORANGE);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                }
                else {
                    if ((x % 2) != 0)  {
                        StdDraw.setPenColor(StdDraw.ORANGE);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                    else {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.filledSquare(x+0.5, y+0.5, 0.5);
                    }
                }
            }
        }
        StdDraw.show();
        StdDraw.pause(200);
        
        StdDraw.setPenColor(StdDraw.RED);
        for (int x=0; x<n; x++) {
            StdDraw.filledCircle(x+1+0.5, board[x]+1+0.5, 0.375);
            StdDraw.show();
            StdDraw.pause(200);
        }
    }
  
    // place queens 
    public int[] solve(int size) {
    	if(size < 4) {
    		System.out.println("Can't solve anything smaller than 4x4. ");
    	} 
    	
		int[] board = new int[size]; // Example of size would be 8x8
		displayQueen(board, 0); // start at row zero
    	return board;
    }
    
    // recursive method to place all the queens
    public static boolean displayQueen(int[] board, int row) {
    	
    	int column;
    	
    	// base case
    	if(row == board.length) {
    		return true;
    	}
    	
    	boolean boardNotFinished = false;
    	for(column = 0; column < board.length; column++) {
    		board[row] = column;
    		if(isQueenSafe(board, row)) {
    			// general case
    			boardNotFinished = displayQueen(board, row + 1);
    		}
    		
    		if(boardNotFinished) {
    			return true;
    		}
    	}
    	
    	return false;
    }
	
    // checks rows and diagonals (row wise)
    // Returns true if queen placement board[row] does not conflict with other
    // queens board[0] through board[row - 1]
    public static boolean isQueenSafe(int[] board, int row) {
    	
    	int column;
    	
    	for(column = 0; column < row; column++) {
    		
    		// Check any column
    		if(board[row] == board[column]) {
    			return false;
    			// If the current row has another queen on it, it doesn't work. 
    		}
    		
    		// Check upper right and upper left diagonals
    		if(Math.abs(board[row] - board[column]) == Math.abs(row - column)) {
    			return false;
    		}
    		// Not returning the absolute value has caused certain sizes not to work. 
    	}
    	
    	return true;
    }

@SuppressWarnings("unused")
  public static void main (String args[]) {
       if (args.length == 1) {
           int n = Integer.parseInt(args[0]);
           // not the n in queens.draw
           Queens queen = new Queens();
           
           int[] solution = queen.solve(n);
           queen.draw(solution);
       }
       else {
           System.out.println("Please input the size of the board ...");
       }
   	}
}
