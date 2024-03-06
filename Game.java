package Utilities;

import java.util.LinkedList;
import java.util.Queue;

public class Game {
	
	Board board;
	Dice dice;
	Queue<Player> turn;
	int board_length;

	Game(int board_length,int snakes,int ladders,int dice_count,int players_count){
		initializeGame(board_length,snakes,ladders,dice_count,players_count);
	}
	
	void initializeGame(int board_length,int snakes,int ladders,int dice_count,int players_count){
		this.board_length=board_length;
		board=new Board(board_length,snakes,ladders);
		dice=new Dice(dice_count);
		turn=new LinkedList<Player>();
		for(int i=1;i<=players_count;i++) {
			Player player=new Player(i,-1);
			turn.add(player);
		}
	}
	
	void startGame() {
		Player winner=null;
		while(winner==null) {
			int moves = dice.rollDice();
			Player curr_player=turn.poll();
			
			System.out.println("Turn of Player " + curr_player.getId());
			System.out.println("Rolling Dice... " + "Got " + moves);
			
			int next_position=moves+curr_player.getPosition();
			
			if(next_position>=board_length*board_length) {
				winner=curr_player;
				break;
			}
			
			Cell cell=board.getCell(next_position);
			if(cell.jump!=null) {
				next_position=cell.jump.end;
				if(cell.jump.start>cell.jump.end) System.out.println("Ufff!! Snake there..");
				else System.out.println("Woooww!! Got Ladder..");
			}
			
			curr_player.setPosition(next_position);
			System.out.println("Next Position of " + curr_player.getId() + " is " + (curr_player.getPosition()+1));
			turn.add(curr_player);
		}
		System.out.println("Winner is : "+ winner.getId());
	}
}
