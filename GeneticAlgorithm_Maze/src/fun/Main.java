package fun;

import java.util.ArrayList;

import data.Route;

public class Main {

	public static void main(String[] args) {
		
		Heritage her = new Heritage();
		ArrayList<Route> routeList = new ArrayList<Route>();
		for (int i=0;i<her.babyNum;i++) {
			Route r = new Route();
			//r.updateFitScore();
			routeList.add(r);
		}
		//boolean isDone = false;
		
		while (true) {
			routeList = her.epoch(routeList);
			for (int i=0;i<routeList.size();i++) {
				if (routeList.get(i).getFitScore() == 1.0){
					System.out.println("第" + her.totalTime + "代走出迷宫！");
					return;
				}
			}
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(her.totalTime);
			
		
		
		//System.out.println(new Maze().memory[4][0]);
		/*ArrayList<Route> routeList = new ArrayList<Route>();
		for (int i=0;i<100;i++) {
			Route r = new Route();
			routeList.add(r);
		}
		
		ArrayList<Route> babys = new ArrayList<Route>();
		new Heritage().crossOver(routeList.get(0),routeList.get(1),babys);*/
		
		
		//System.out.println("\n" + routeList.get(24).getFitScore());
		//System.out.println(routeList.get(5).getFitScore());
		
	}

}
