import java.io.*;

public class BaseStation extends Thread {
    private Planet arrakis;
    private Planet giediPrime;
    private String transmissionFileName = "trans.mxt";

    public BaseStation(Planet arrakis, Planet giediPrime)
    {
        this.arrakis = arrakis;
        this.giediPrime = giediPrime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (arrakis.isAlignedWith(giediPrime)) {
                    sendTransmission();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendTransmission() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transmissionFileName)))
        {
            writer.write("><>><</<<>>/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
