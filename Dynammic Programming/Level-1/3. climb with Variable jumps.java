1. You are given a number n, representing the number of stairs in a staircase.
2. You are on the 0th step and are required to climb to the top.
3. You are given n numbers, where ith element's value represents - till how far from the step you 
     could jump to in a single move.  
     You can of course jump fewer number of steps in the move.
4. You are required to print the number of different paths via which you can climb to the top.
Input Format
A number n
.. n more elements
Output Format
A number representing the number of ways to climb the stairs from 0 to top.
  Constraints
0 <= n <= 20
0 <= n1, n2, .. <= 20
Sample Input
10
3
3
0
2
1
2
4
2
0
0
Sample Output
5

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = scn.nextInt();
        
        System.out.println(csvm(0, n, arr));
        
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(csvmMem(0, n, arr, dp));
        System.out.println(csvmTab(n, arr));
    }

    public static int csvm(int src, int dest, int[] arr){
        if(src > dest) return 0;
        if(src == dest) return 1;
        
        int totalPaths = 0;
        for(int jumps = 1; jumps <= arr[src]; jumps++){
            int xi = csvm(src + jumps, dest, arr);
            totalPaths += xi;
        }
        
        return totalPaths;
    }
    
    public static int csvmMem(int src, int dest, int[] arr, int[] dp){
        if(src > dest) return 0;
        if(src == dest) return 1;
        
        if(dp[src] != -1){
            return dp[src];    
        }
        
        int totalPaths = 0;
        for(int jumps = 1; jumps <= arr[src]; jumps++){
            int xi = csvmMem(src + jumps, dest, arr, dp);
            totalPaths += xi;
        }
        
        dp[src] = totalPaths;
        return dp[src];
    }
    
    public static int csvmTab(int dest, int[] arr){
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[dest] = 1;
        for(int i=n-1; i>=0; i--){
            
            int totalPaths = 0;
            for(int jumps = 1; jumps <= arr[i]; jumps++){
                if(i + jumps < dp.length){
                    totalPaths += dp[i + jumps];
                }
            }
            
            dp[i] = totalPaths;
        }
        
        return dp[0];
    }
}
