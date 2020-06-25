package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>
{
    private SolverOutcome outcome;
    private LinkedList<Vertex> solution;
    private int numStateExplored;

    private AStarGraph<Vertex> G;
    private ExtrinsicMinPQ<Vertex> minPQ;
    private Map<Vertex, Double> distTo;
    private Map<Vertex, Vertex> edgeTo;
    private Vertex start;
    private Vertex end;
    private double timeElapsed;


    public AStarSolver(AStarGraph<Vertex> input, Vertex start,
                       Vertex end, double timeout)
    {
        G = input;
        this.start = start;
        this.end = end;

        solution = new LinkedList<>();
        numStateExplored = 0;
        minPQ = new ArrayHeapMinPQ<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();

        AStarAlgorithm(timeout);
    }

    private void AStarAlgorithm(double timeout)
    {
        minPQ.add(start, 0);
        distTo.put(start, 0.0);
        while (minPQ.size() != 0)
        {
            Stopwatch sw = new Stopwatch();
            Vertex v = minPQ.removeSmallest();
            timeElapsed += sw.elapsedTime();
            if (! v.equals(start))
            {
                numStateExplored++;
            }
            if (explorationTime() >= timeout)
            {
                outcome = SolverOutcome.TIMEOUT;
                return;
            }
            // Goal reached
            if (v.equals(end))
            {
                fillInSolution();
                outcome = SolverOutcome.SOLVED;
                return;
            }
            // Relaxation
            for (WeightedEdge<Vertex> e : G.neighbors(v))
            {
                relaxEdge(e);
            }
        }
        // Empty min PQ
        outcome = SolverOutcome.UNSOLVABLE;
    }

    private void fillInSolution()
    {
        Vertex v = end;
        while (! v.equals(start))
        {
            solution.addFirst(v);
            v = edgeTo.get(v);
        }
        solution.addFirst(start);
    }

    private void relaxEdge(WeightedEdge<Vertex> e)
    {
        Vertex src = e.from();
        Vertex tar = e.to();
        double distToTarget = distTo.get(src) + e.weight();
        double estimatedDist = distToTarget;
        if (! tar.equals(end))
        {
            estimatedDist += G.estimatedDistanceToGoal(tar, end);
        }

        if (! distTo.containsKey(tar))
        {
            minPQ.add(tar, estimatedDist);
            distTo.put(tar, distToTarget);
            edgeTo.put(tar, src);
            return;
        }

        if (distToTarget < distTo.get(tar))
        {
            distTo.replace(tar, distToTarget);
            edgeTo.replace(tar, src);
            minPQ.changePriority(tar, estimatedDist);
        }
    }

    /** Returns the solution outcome
     *
     * @return SolverOutCome (SOLVED, TIMEOUT, UNSOLVABLE)
     */
    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    /**
     * @return List of vertex on the shortest path
     */
    @Override
    public List<Vertex> solution() {
        return solution;
    }

    /** Returns the total weights of the shortest path
     *
     * @return The total weights of the shortest path
     */
    @Override
    public double solutionWeight() {
        return distTo.get(end);
    }

    /** Return the number of explored states
     *
     * @return numStatesExplored
     */
    @Override
    public int numStatesExplored() {
        return numStateExplored;
    }

    /** Return the total time elapsed
     *
     * @return timeElapsed
     */
    @Override
    public double explorationTime() {
        return timeElapsed;
    }
}
