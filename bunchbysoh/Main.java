package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    final double rated_capacity=120.0;
    for(int i=0;i<presentCapacities.length;i++){
        if(presentCapacities[i]>=0){
            double SOH=100.0 *((double)presentCapacities[i]/rated_capacity);
            
            // Classify each battery based on SoH percentage
            if(SOH>83 && SOH<=100){
                counts.healthy++;
            }
            else if(SOH>=63 && SOH<=83){
                counts.exchange++;
            }
            else{
                counts.failed++;
            }
        }
    }
    System.out.println("Summary of Counts:");
    System.out.println("Healthy: " + counts.healthy);
    System.out.println("Exchange: " + counts.exchange);
    System.out.println("Failed: " + counts.failed);

    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    System.out.println("Test 1 Passed");
    System.out.println();
    
    //Testing with Boundary Conditions
    int[] presentCapacities2 = {100,83,63,120};
    CountsBySoH counts2 = countBatteriesByHealth(presentCapacities2);
    assert(counts2.healthy == 2);
    assert(counts2.exchange == 1);
    assert(counts2.failed == 1);
    System.out.println("Test 2 Passed");
    System.out.println();
    
    //Testing with Empty Array of Capacities
    int[] presentCapacities3 = {};
    CountsBySoH counts3 = countBatteriesByHealth(presentCapacities3);
    assert(counts3.healthy == 0);
    assert(counts3.exchange == 0);
    assert(counts3.failed == 0);
    System.out.println("Test 3 Passed");
    System.out.println();
    
    //Testing with Extreme Values
    int[] presentCapacities4 = {-3,0, 10, 120, 130};
    CountsBySoH counts4 = countBatteriesByHealth(presentCapacities4);
    assert(counts4.healthy == 1);  
    assert(counts4.exchange == 0);
    assert(counts4.failed == 3);
    System.out.println("Test 4 Passed");
    System.out.println();
    
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}