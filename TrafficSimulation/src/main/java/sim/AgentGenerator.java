package sim;

import core.UserMap;
import core.UserProfile;
import nodes.impl.TNode;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by aron on 3/24/17.
 */
public class AgentGenerator {

    private AgentPool pool;
    private UserProfile userProfile;
    private UserMap userMap;

    public AgentGenerator() {
        pool = AgentPool.getInstance();
        userProfile = UserProfile.getInstance();
        userMap = userProfile.getMap();
    }

    public void randomAgentGenerator1(int amount) {
        List<TNode> nodeList = new ArrayList<>();
        double bound = 0.005;
        userProfile.getWayMap().values().forEach(way ->
            way.getNodes().forEach(node -> {
                double dist = euclideanDistanceToCenter(node.getGeoPosition());
                if (dist > bound) {
                    nodeList.add(node);
                }
            })
        );
        List<TNode> invertedList = new ArrayList<>();
        for (int i = nodeList.size() - 1; i > 0; i--) {
            invertedList.add(nodeList.get(i));
        }

        for (int i = 0; i < amount; i++) {
            pool.addAgent(new Agent(nodeList.get(i), invertedList.get(i)));
        }
    }

    private double euclideanDistanceToCenter(GeoPosition node) {
        double latDist = Math.abs(node.getLatitude() - userMap.getLatitude());
        double lonDist = Math.abs(node.getLongitude() - userMap.getLongitude());
;
        return Math.sqrt(Math.pow(latDist, 2) + Math.pow(lonDist, 2));
    }

    public void randomAgentGenerator(int amount) {
        double leftBound = userMap.getLatitude() - userMap.getRadius();
        double rightBound = userMap.getLatitude() + userMap.getRadius();
        double lowerBound = userMap.getLongitude() - userMap.getRadius();
        double upperBound = userMap.getLongitude() + userMap.getRadius();

        List<TNode> upperLeftBoundNodes = new ArrayList<>();
        List<TNode> lowerLeftBoundNodes = new ArrayList<>();
        List<TNode> upperRightBoundNodes = new ArrayList<>();
        List<TNode> lowerRightBoundNodes = new ArrayList<>();

        double upperBounding = 0.0020;
        double lowerBounding = 0.0500;
        userProfile.getWayMap().values().forEach(way ->
            way.getNodes().forEach(node -> {
                if (node.getLat() - leftBound < upperBounding && node.getLon() - upperBound < upperBounding) {
                    upperLeftBoundNodes.add(node);
                } else if (node.getLat() - rightBound < upperBounding && node.getLon() - upperBound < upperBounding) {
                    upperRightBoundNodes.add(node);
                } else if (node.getLat() - leftBound < lowerBounding && node.getLon() - lowerBound < lowerBounding) {
                    lowerLeftBoundNodes.add(node);
                } else if (node.getLat() - rightBound < lowerBounding && node.getLon() - lowerBound < lowerBounding) {
                    lowerRightBoundNodes.add(node);
                }
            })
        );

        for (int i = 0; i < amount; i++) {
            boolean upper = ThreadLocalRandom.current().nextInt(100) > 50;
            boolean left = ThreadLocalRandom.current().nextInt(100) > 50;

            int randomStart;
            int randomEnd;
            TNode start;
            TNode end;
            if (upper && left) {
                randomStart = ThreadLocalRandom.current().nextInt(upperLeftBoundNodes.size());
                randomEnd = ThreadLocalRandom.current().nextInt(lowerRightBoundNodes.size());
                start = upperLeftBoundNodes.get(randomStart);
                end = lowerRightBoundNodes.get(randomEnd);
                pool.addAgent(new Agent(start, end));
            } else if (upper) {
                randomStart = ThreadLocalRandom.current().nextInt(upperRightBoundNodes.size());
                randomEnd = ThreadLocalRandom.current().nextInt(lowerLeftBoundNodes.size());
                start = upperRightBoundNodes.get(randomStart);
                end = lowerLeftBoundNodes.get(randomEnd);
                pool.addAgent(new Agent(start, end));
            } else if (left) {
                randomStart = ThreadLocalRandom.current().nextInt(lowerLeftBoundNodes.size());
                randomEnd = ThreadLocalRandom.current().nextInt(upperRightBoundNodes.size());
                start = lowerLeftBoundNodes.get(randomStart);
                end = upperRightBoundNodes.get(randomEnd);
                pool.addAgent(new Agent(start, end));
            } else {
                randomStart = ThreadLocalRandom.current().nextInt(lowerRightBoundNodes.size());
                randomEnd = ThreadLocalRandom.current().nextInt(upperLeftBoundNodes.size());
                start = lowerRightBoundNodes.get(randomStart);
                end = upperLeftBoundNodes.get(randomEnd);
                pool.addAgent(new Agent(start, end));
            }
        }

    }
}
