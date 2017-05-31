import java.util.ArrayList;
import java.util.List;

/**
 * @author z5060165
 */
public class MinHeuristic implements Heuristic {

	private List<Edge> jobList;
	private List<Edge> remainingJobs;
	private int minimum;
	
	/**
	 * Constructs the heuristic using a job list
	 * @param jobList The job list used to calculate the heuristic estimation
	 */
	public MinHeuristic(List<Edge> jobList) {
		this.jobList = new ArrayList<Edge>(jobList);
		this.remainingJobs = new ArrayList<Edge>();
		this.minimum = Integer.MAX_VALUE;
	}
	
	/**
	 * @return int An estimation of the cost
	 */
	@Override
	public int estimateCost(AStarNode node) {
		int cost = 0;
		this.remainingJobs.addAll(jobList);
		this.remainingJobs.removeAll(node.getPath());
		int average = getCurrentAverage();
		int epsilon = average != 0 ? (int) (Math.floor((getMinimum() + getMaximum())/average) - 1) : 1;
		this.minimum = epsilon < minimum ? epsilon : minimum;
		for (Edge job : remainingJobs)
			cost += job.getWeight();
		this.remainingJobs.clear();
		return cost * this.minimum;
	}
	
	/**
	 * @return int The current average of the remaining list cost
	 */
	public int getCurrentAverage() {
		int total = 0;
		if (jobList.size() == 0)
			return 0;
		for (Edge job : this.remainingJobs)
			total += job.getWeight();
		return total / jobList.size();
	}
	
	/**
	 * The minimum cost in the job list
	 * @return
	 */
	public int getMinimum() {
		int min = Integer.MAX_VALUE;
		for (Edge job : this.jobList)
			min = job.getWeight() < min ? job.getWeight() : min;
		return min;
	}
	
	/**
	 * The maximum cost in the cost list
	 */
	public int getMaximum() {
		int max = 0;
		for (Edge job : this.jobList)
			max = job.getWeight() > max ? job.getWeight() : max;
		return max;
	}

}
