package Utilities;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
	
	Cell[][] cells;
	int board_length;
	
	Board(int size,int snakes,int ladders){
		this.board_length=size;
		initializeBoard();
		addSnakesLadders(snakes,ladders);
	}
	
	void initializeBoard() {
		cells=new Cell[board_length][board_length];
		for(int row=0;row<board_length;row++) {
			for(int col=0;col<board_length;col++) {
				cells[row][col]=new Cell();
			}
		}
	}
	
	Cell getCell(int position) {
		return cells[position/board_length][position%board_length];
	}

	void addSnakesLadders(int snakes,int ladders) {
		while(snakes>0) {
			int start=ThreadLocalRandom.current().nextInt(0,board_length*board_length);
			int end= ThreadLocalRandom.current().nextInt(0,board_length*board_length);
			if(end>=start) continue;
			
			Jump snake=new Jump(start,end);
			Cell cell=getCell(start);
			cell.jump=snake;
			snakes--;
		}
		while(ladders>0) {
			int start=ThreadLocalRandom.current().nextInt(0,board_length*board_length);
			int end= ThreadLocalRandom.current().nextInt(0,board_length*board_length);
			if(start>=end) continue;
			
			Jump ladder=new Jump(start,end);
			Cell cell=getCell(start);
			cell.jump=ladder;
			ladders--;
		}
	}
}
