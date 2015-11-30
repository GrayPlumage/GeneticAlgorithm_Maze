package data;

public class Maze {
	
	int m_startR = 7;
	int m_startL = 14;
	int m_endR = 2;
	int m_endL = 0;
	
	public int map[][];
	public int memory[][];		//留作轨迹显示，暂未设计UI
	
	public Maze() {
		map = new int[10][15];
		memory = new int[10][15];
		
		map[0] = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		map[1] = new int[]{1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1};
		map[2] = new int[]{8, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1};
		map[3] = new int[]{1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1};
		map[4] = new int[]{1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1};
		map[5] = new int[]{1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1};
		map[6] = new int[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1};
		map[7] = new int[]{1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5};
		map[8] = new int[]{1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1};
		map[9] = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	}
	
	public void resetMemory() {
		for (int i=0;i<10;i++)
			for (int j=0;j<15;j++)
				memory[i][j] = 0;
	}
	
	public double checkFitScore(int route[]) {
		int pos_row = m_startR;
		int pos_line = m_startL;
		resetMemory();
		
		for (int i=0;i<route.length;i++) {
			//0:up,1:down,2:left,3:right
			switch(route[i]) {
			case 0:
				if (pos_row != 0 && map[pos_row-1][pos_line] == 0)
					pos_row -= 1;
				break;
			case 1:
				if (pos_row != 9 && map[pos_row+1][pos_line] == 0)
					pos_row += 1;
				break;
			case 2:
				if (pos_line != 0 && (map[pos_row][pos_line-1] == 0 ||
										map[pos_row][pos_line-1] == 8))
					pos_line -= 1;
				break;
			case 3:
				if (pos_line != 14 && map[pos_row][pos_line+1] == 0)
					pos_line += 1;
				break;
			}	
			memory[pos_row][pos_line] = 1;
			
			if (pos_row == m_endR && pos_line == m_endL)
				break;
		}//end of for
		
		int dif_r = Math.abs(m_endR - pos_row);
		int dif_l = Math.abs(m_endL - pos_line);
		
		return (double) 1/(dif_r+dif_l+1);	//+1: avoid 0 in divisor
	}
}
