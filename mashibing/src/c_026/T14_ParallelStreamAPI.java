package c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ParallelStreamAPI
 * 
 */
public class T14_ParallelStreamAPI {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1_0000; i++) {
            nums.add(100_0000 + random.nextInt(100_0000));
        }

        long start, end;

        start = System.currentTimeMillis();
        nums.stream().forEach(v -> isPrime(v));
        end =System.currentTimeMillis();

        System.out.println(end - start);
        
        
        // 使用parallel stream api

        start = System.currentTimeMillis();
        nums.parallelStream().forEach(v -> isPrime(v));
        end =System.currentTimeMillis();

        System.out.println(end - start);
    }

    static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
