package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucket_count = new int[M];
        int N = oomages.size();
        for (Oomage o : oomages)
        {
            int bucket_num = (o.hashCode() & 0x7FFFFFFF) % M;
            bucket_count[bucket_num]++;
        }
        for (int i = 0; i < bucket_count.length; i++)
        {
            if ((bucket_count[i] < (N / 50)) || (bucket_count[i] > (N / 2.5)))
            {
                return false;
            }
        }
        return true;
    }
}
