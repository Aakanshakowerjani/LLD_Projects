package Utilities;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
	int diceCount;
	int min=1;
	int max=6;
	
	Dice(int diceCount){
		this.diceCount=diceCount;
	}
	
	int rollDice() {
		int count=diceCount,ans=0;
		while(count>0) {
			ans += ThreadLocalRandom.current().nextInt(min,max+1);
			count--;
		}
		return ans;
	}
}
