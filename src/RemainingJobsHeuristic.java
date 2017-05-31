import java.util.ArrayList;
import java.util.List;

/**
 * @author z5060165
 */
public class RemainingJobsHeuristic implements Heuristic {

	private List<Edge> jobList;
	private List<Edge> remainingJobs;
	
	/**
	 * Constructs the heuristic using a job list
	 * @param jobList The job list used to calculate the heuristic estimation
	 */
	public RemainingJobsHeuristic(List<Edge> jobList) {
		this.jobList = new ArrayList<Edge>(jobList);
		this.remainingJobs = new ArrayList<Edge>();
	}
	
	/**
	 * @return int An estimation of the cost
	 */
	@Override
	public int estimateCost(AStarNode node) {
		int cost = 0;
		this.remainingJobs.addAll(jobList);
		this.remainingJobs.removeAll(node.getPath());
		for (Edge job : remainingJobs)
			cost += job.getWeight();
		this.remainingJobs.clear();
		return cost;
	}

}
