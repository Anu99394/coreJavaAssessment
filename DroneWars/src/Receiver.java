import java.io.*;

public class Receiver extends Thread {
    private Planet arrakis;
    private Planet giediPrime;
    private String transmissionFileName = "trans.mxt";
    private String acknowledgmentFileName = "recvrs.mxt";

    public Receiver(Planet arrakis, Planet giediPrime) {
        this.arrakis = arrakis;
        this.giediPrime = giediPrime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (giediPrime.isAlignedWith(arrakis)) {
                    processTransmission();
                    sendAcknowledgment();
                }
                // Wait for a short time before checking alignment again
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processTransmission() {
        try (BufferedReader reader = new BufferedReader(new FileReader(transmissionFileName)))
        {
            String transmission = reader.readLine();
            if (transmission != null)
            {
                System.out.println("Received transmission: " + transmission);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendAcknowledgment() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(acknowledgmentFileName))) {
            writer.write("/.>.>.<.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
