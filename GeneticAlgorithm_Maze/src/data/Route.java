package data;

import java.util.Random;

public class Route {
	private int[] route;
	private double fitScore = 0.0;
	final int roateLen = 35;
	//final int childNum = 140;
	
	public double getFitScore() {
		return fitScore;
	}
	public int[] getRoute() {
		return this.route;
	}
	public void setRoute(int[] r) {
		this.route = r;
	}
	
	/*public void crossFromK(int[] rdad, int[] rmom, int k) {
		if (k < len) {
			//int[] r1 = rdad.getRoute();
			//int[] r2 = rmom.getRoute();
			for (int i=k;i<len;i++) {
				int dir = rdad[i];
				rdad[i] = rmom[i];
				rmom[i] = dir;
			}
		}
	}*/
	
	public Route() {
		route = new int[roateLen];
		Random ran = new Random();
		for (int i = 0; i < roateLen; i++)
			route[i] = ran.nextInt(4);

		fitScore = new Maze().checkFitScore(this.route);
	}
	
	public Route(int[] r) {
		this.route = r;
	}
	
	public void updateFitScore() {
		fitScore = new Maze().checkFitScore(this.route);
	}
}
