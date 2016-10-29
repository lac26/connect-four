import org.json.simple.*;
import org.json.simple.parser.*;


public class myPlayer {
		

	public static void main(String[] args) {
		
		//read in board as string
		String strBoard = args[1];
		//String strBoard = "[[1 0 0 0 0 0 0] [0 0 0 0 0 0 0] [0 0 0 0 0 0 0] [0 1 0 0 0 0 0]  [0 2 0 0 0 0 0] [0 1 0 0 0 0 0]]";
		
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
		
		//(0, jsonArraysBoard);
		//jsonObject = (JSONObject) obj;
		//System.out.println("PRINTING JOSN OBJECT +++" + jsonObject);
		
		
		
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
		for(int i = 0; i < 7; i++ ) {
			if(isValidMove(i, board)) {
				System.out.println("\nI will return " + i);
				return i;
			} else {
				System.out.println("\nI ddecided this move is invalid: " + i);
			}
		}
		System.out.println("Will return negative one! ");
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
