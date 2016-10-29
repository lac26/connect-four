import org.json.simple.*;
import org.json.simple.parser.*;


public class myPlayer {
		
	static int myPlayer;
	static int otherPlayer;
	
	public static void main(String[] args) {
		
		//read in board as string
		String strBoard = args[1];
		//String strBoard = "[[1 0 0 0 0 0 0] [0 0 0 0 0 0 0] [0 0 0 0 0 0 0] [0 1 0 0 0 0 0]  [0 2 0 0 0 0 0] [0 1 0 0 0 0 0]]";
		
		String player = args[3];
		if(player.equals("player-one")) {
			myPlayer = 1;
			otherPlayer = 2;
		} else {
			myPlayer = 2;
			otherPlayer = 1;
		}
		
		
		myPlayer = 1;
		otherPlayer = 2;
		
		
		int[][] board = new int[6][7];
		
		
		JSONParser parser = new JSONParser();
		JSONArray arrayBoard = new JSONArray();
		Object obj = new JSONObject();
		//JSONObject jsonObject;
		JSONArray jsonArraysBoard; 
		
		try {

			Object jsonObj = parser.parse(strBoard);

			jsonArraysBoard = (JSONArray) jsonObj;

			JSONArray firstArray = (JSONArray) jsonArraysBoard.get(3);

			int first = ((Long) firstArray.get(1)).intValue();
			 
			int myMove = minimax(jsonArraysBoard);
			System.out.println(isValidMove(myMove, jsonArraysBoard) + "Decided that this move is valid " + myMove);
			System.exit(myMove);

		} catch (ParseException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		
//		int myMove = minimax(jsonArraysBoard);
//		System.exit(myMove);

	}
	
	// checks if move is allowed
	public static Boolean isValidMove(int move, JSONArray board) {
		JSONArray thisArray = (JSONArray) board.get(0);
		
		if( (long) thisArray.get(move) != 0) {
			System.out.println("!!!1decided that this move is not valid: " + move);
				return false;
		} else {
			return true;
		}
	}
	
	// chooses a move to make, calls isVAlidMove to make sure
	// move is valid, if is, calls makeMove to make the move
	public static int chooseMove(JSONArray board) {
		int bestVal = Integer.MIN_VALUE;
		int bestMove = -1;
		for(int i = 0; i < 7; i++ ) {
			if(isValidMove(i, board)) {
				int val = evaluate(i, board);
				if(val > bestVal ) {
					bestVal = val;
					bestMove = i;
					System.out.println("\nBestval has been updated to " + bestVal + " at move " + bestMove);
				}
			} else {
				System.out.println("\nI ddecided this move is invalid: " + i);
			}
		}
		System.out.println("\nReturning bestMove of " + bestMove);
		return bestMove;
	}
	
	/*never should be called if not a valid move */
	public static int evaluate(int move, JSONArray board) {
		int myVal = 0;
		for (int i=5; i>=0;i--) {
			JSONArray thisArray = (JSONArray) board.get(i);
			if ((long) thisArray.get(move) == 0) {
				// check for left val
				if(move - 1 > 0) {
					if((long) thisArray.get(move-1) == myPlayer ) {
						myVal = myVal + 10;
					} else if ((long) thisArray.get(move-1) == otherPlayer ){
						myVal = myVal - 7;
					}
				}
				// check to the right
				if(move + 1 < 6) {
					if((long) thisArray.get(move+1) == myPlayer ) {
						myVal = myVal + 10;
					} else if ((long) thisArray.get(move+1) == otherPlayer ){
						myVal = myVal - 7;
					}
				
				}
				//check below
				if(i + 1 < 5) {
					JSONArray newArray = (JSONArray) board.get(i+1);
					if((long) newArray.get(move) == myPlayer) {
						myVal = myVal + 10;
					} else if((long) newArray.get(move) == otherPlayer) {
						myVal = myVal - 7;
					}
				}
					
					return myVal;
			} // else keep incrementing
		}
		System.err.println("Reached end of evaluate - returning -1");
		return -1;
	}
	
	public static int makeMove(int move, JSONArray baord) {	
		
		return 0;
	}
	
	public static int minimax(JSONArray board) {
		int moveToMake = chooseMove(board);
		// makeMove(moveToMake, board);		
		return moveToMake;
	}	


}
