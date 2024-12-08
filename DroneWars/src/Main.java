public class Main {
    public static void main(String[] args) {
        // Orbital period of Arrakis in LTU
        Planet arrakis = new Planet("Arrakis", 12.0);
        Planet giediPrime = new Planet("Giedi Prime", 60.0);

        BaseStation baseStation = new BaseStation(arrakis, giediPrime);
        Receiver receiver = new Receiver(arrakis, giediPrime);

        baseStation.start();
        receiver.start();

        // Simulate time passing (for example, 100 LTU units)
        for (int i = 0; i < 100; i++) {
            arrakis.updatePosition(1);
            giediPrime.updatePosition(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
