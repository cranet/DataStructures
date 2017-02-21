/**
 *
 * Timing class runs a series of problems and displays
 * the time taken to execute each one in nanoseconds and seconds
 *
 * @author Ka Yee Yeung
 * @author todc
 * @version 1/16/2017
 */
class Timing {

    public static final int NUM_PROBLEMS = 7; //Total number of problems
    public static final long[] NUM_TIMINGS = {10, 100, 200, 300, 400, 500}; //Test values

    public static void main(String[] args) {
        //Fields
        long startTime;
        long endTime;
        long elapsedTime;
        double elapsedTimeSeconds;

        //Run for each problem
        for(int i = 0; i < NUM_PROBLEMS; i++) {

            //Time for each value
            for(int timing = 0; timing < NUM_TIMINGS.length; timing++) {
                //Value to be tested
                long n = NUM_TIMINGS[timing];
                //Start time
                startTime = System.nanoTime();

                //Run correct problem
                if(i == 0) {
                    problem1(n);
                } else if(i == 1) {
                    problem2(n);
                } else if (i == 2) {
                    problem3(n);
                } else if (i == 3) {
                    problem4(n);
                } else if (i == 4) {
                    problem5(n);
                } else if (i == 5) {
                    problem6(n);
                } else {
                    problem7(n);
                }

                //End time and display
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                elapsedTimeSeconds = elapsedTime/(1000000000.0); //1 second = 1000000000 (10^9) nanoseconds.
                System.out.printf("Problem%d with value %d: %d nanoseconds or %f seconds elapsed %n",
                        i + 1, NUM_TIMINGS[timing], elapsedTime, elapsedTimeSeconds);
            }
            System.out.println();
        }
    }

    public static void problem1(long n) {
        long sum = 0;
        for (long i = 0; i < n; i++) {
            sum++;
        }
        //System.out.println(sum);
    }

    public static void problem2(long n) {
        long sum = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < n; j++) {
                sum++;
            }
        }
        //System.out.println(sum);
    }

    public static void problem3(long n) {
        long sum = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < i; j++) {
                sum++;
            }
        }
        //System.out.println(sum);
    }

    public static void problem4(long n) {
        long sum = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < n * n; j++) {
                sum++;
            }
        }
        //System.out.println(sum);
    }

    public static void problem5(long n) {
        long sum = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < i; j++) {
                sum++;
            }
            for (long k = 0; k < 8000; k++) {
                sum++;
            }
        }
        //System.out.println(sum);
    }

    public static void problem6(long n) {
        long sum = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < i * i; j++) {
                if (j % i == 0) {
                    for (long k = 0; k < j; k++) {
                        sum++;
                    }
                }
            }
        }
        //System.out.println(sum);
    }

    public static void problem7(long n) {
        long sum = 0;
        for (long i = 0; i < n; i++) {
            for (long j = 0; j < i * i; j++) {
                sum++;
            }
        }
        //System.out.println(sum);
    }
}
