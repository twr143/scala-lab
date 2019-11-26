package algo;

import java.util.*;

/**
 * Created by Ilya Volynin on 26.11.2019 at 8:46.
 */
public class SkylineJ {

    static List<List<Integer>> getSkyline(int[][] buildings) {
        class Rect {
            public Rect(int l, int r, int h) {
                this.l = l;
                this.r = r;
                this.h = h;
            }

            int l;
            int r;
            int h;
        }
        if (buildings.length == 0) return new ArrayList<>();
        //there are two treesets: top and bottom
        Comparator<Rect> orderingForTopTreeSet = new Comparator<Rect>() {
            @Override
            public int compare(Rect o1, Rect o2) {
                if (o1.l != o2.l) return o1.l - o2.l;
                else if (o1.h != o2.h) return o2.h - o1.h;
                else return o2.r - o1.r;
            }
        };
        Comparator<Rect> orderingForBottomTreeSet = new Comparator<Rect>() {
            @Override
            public int compare(Rect o1, Rect o2) {
                if (o1.h != o2.h) return o2.h - o1.h;
                else if (o1.r != o2.r) return o2.r - o1.r;
                else return o2.l - o1.l;
            }
        };
        TreeSet<Rect> topTreeSet = new TreeSet<Rect>(orderingForTopTreeSet);
        TreeSet<Rect> bottomTreeSet = new TreeSet<Rect>(orderingForBottomTreeSet);
        int l = Integer.MIN_VALUE;
        int r = Integer.MIN_VALUE;
        int h = 0;

        for (int i = 0; i < buildings.length; i++)
            topTreeSet.add(new Rect(buildings[i][0], buildings[i][1], buildings[i][2]));
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        while (!topTreeSet.isEmpty() || !bottomTreeSet.isEmpty()) {
            if (!topTreeSet.isEmpty() && topTreeSet.first().l <= r) {
                if (topTreeSet.first().h > h) {
                    if (topTreeSet.first().r < r)
                        bottomTreeSet.add(new Rect(l, r, h));
                    l = topTreeSet.first().l;
                    r = topTreeSet.first().r;
                    h = topTreeSet.first().h;
                    resultList.add(new ArrayList<>(Arrays.asList(l, h)));
                } else if (topTreeSet.first().r > r)
                    bottomTreeSet.add(new Rect(topTreeSet.first().l, topTreeSet.first().r,
                            topTreeSet.first().h));
                topTreeSet.pollFirst();
            } else if (!bottomTreeSet.isEmpty()) {
                if (bottomTreeSet.first().r > r) {
                    l = r;
                    r = bottomTreeSet.first().r;
                    if (bottomTreeSet.first().h < h) {
                        h = bottomTreeSet.first().h;
                        resultList.add(new ArrayList<>(Arrays.asList(l, h)));
                    }
                }
                bottomTreeSet.pollFirst();
            } else {
                if (h > 0)  resultList.add(new ArrayList<>(Arrays.asList(r, 0)));
                l = topTreeSet.first().l;
                r = topTreeSet.first().r;
                h = topTreeSet.first().h;
                resultList.add(new ArrayList<>(Arrays.asList(l, h)));
                topTreeSet.pollFirst();
            }
        }
        if (h > 0)  resultList.add(new ArrayList<>(Arrays.asList(r, 0)));
        return resultList;
    }

    public static void main(String[] args) {

    }
}
