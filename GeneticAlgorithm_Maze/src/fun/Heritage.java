package fun;

import java.util.ArrayList;
import java.util.Random;

import data.Route;

public class Heritage {
	final int babyNum = 140;
	
	final double crossover_rate = 0.7;
	final double mutate_rate = 0.001;
	
	public int totalTime = 0;
	
	public ArrayList<Route> epoch(ArrayList<Route> routeList) {
		ArrayList<Route> babys = new ArrayList<Route>();
		totalTime++;
		
		for (int i=0;i<routeList.size();i++)
			routeList.get(i).updateFitScore();
		
		//check if done
		double max = 0.0;
		for (int i=0;i<routeList.size();i++) {			
			if (routeList.get(i).getFitScore() > max)
				max = routeList.get(i).getFitScore();
		}
		System.out.println("第" + totalTime + "代最佳权重: " +max);
		
		
		for (int i=0;i<babyNum;i+=2) {
			Route dad = routeWheelSelect(routeList);
			Route mom = routeWheelSelect(routeList);
			crossOver(dad, mom, babys);
		}
		
		for (int i=0;i<babyNum;i++)
			mutate(babys.get(i));
		
		//routeList = babys;
		
		
		/*if (totalTime%50 == 0) {
			Route r = routeList.get(0);
			//System.out.println(routeList.size());
			for (int i=0;i<r.getRoute().length;i++)
				System.out.print(r.getRoute()[i]+" ");
			System.out.print("\n");
		}*/
		
		return babys;
	}
	
	public Route routeWheelSelect(ArrayList<Route> r) {
		double fitScoreSum = 0.0;
		double tempScoreSum = 0.0;
		int selected = 0;
		
		for (int i=0;i<r.size();i++) {
			fitScoreSum += r.get(i).getFitScore();
		}
		
		double rate = new Random().nextDouble();
		while (rate == 0.0)
			rate = new Random().nextDouble();
		//System.out.println("before:" + fitScoreSum);
		fitScoreSum *= rate;
		//System.out.println("after:" + fitScoreSum);
		
		for (int i=0;i<r.size();i++) {
			tempScoreSum += r.get(i).getFitScore();
			if (tempScoreSum >= fitScoreSum) {
				selected = i;
				break;
			}
		}
		//System.out.println("temp:" + tempScoreSum);
		//System.out.println("choose:" + r.get(selected).getFitScore()+"\n");
		return r.get(selected);
	}
	
	public void crossOver(Route rdad, Route rmom, ArrayList<Route> babys) {
		
		if (new Random().nextDouble() > crossover_rate) {
			//System.out.println("未交换");
			babys.add(rdad);
			babys.add(rmom);
		}
		else {
			int[] rdd = rdad.getRoute();
			int[] rmm = rmom.getRoute();
			int length = rdd.length;
			
			//don't give them the source
			int[] rd = new int[length];
			int[] rm = new int[length];
			for (int i=0;i<rdd.length;i++) {
				rd[i] = rdd[i];
				rm[i] = rmm[i];
			}
			int crossPoint = new Random().nextInt(rd.length);
			//System.out.println(rd[crossPoint] + " " + rm[crossPoint]);
			
			for (int i=crossPoint;i<rd.length;i++) {
				int dir = rd[i];
				rd[i] = rm[i];
				rm[i] = dir;
			}
			//System.out.println("交换");
			//System.out.println(rd[crossPoint] + " " + rm[crossPoint]);
			Route rcdad = new Route(rd);
			Route rcmom = new Route(rm);
			babys.add(rcdad);
			babys.add(rcmom);
		}
	}

	public void mutate(Route route) {
		int[] r = route.getRoute();
		Random ran = new Random();
		for (int i=0;i<r.length;i++) {
			double mu = ran.nextDouble();
			if (mu < mutate_rate)				//mutate by %
				r[i] = (ran.nextInt(3) + 1) % 4;
		}
	}
}
