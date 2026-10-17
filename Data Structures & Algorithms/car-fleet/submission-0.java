class Solution {
    record Car(int position, int speed, double eta) { }
    private static Comparator<Car> CMP_POS = Comparator.<Car>comparingInt(Car::position);

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) return 0;
        if (n == 1) return 1;

        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            var pos = position[i];
            var spd = speed[i];
            var eta = ((double) target - pos) / spd;

            cars.add(new Car(pos, spd, eta));
        }

        Collections.sort(cars, CMP_POS);

        double lastEta = cars.get(n - 1).eta;
        int fleets = 1;
        
        for (int i = n - 2; i >= 0; i--) {
            Car car = cars.get(i);
            if (car.eta > lastEta) {
                fleets++;
                lastEta = car.eta;
            }
        }

        return fleets;
    }
}
