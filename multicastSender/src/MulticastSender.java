import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
    public static void main(String[] args) {
        String multicastAddress = "230.0.0.1";
        int port = 4446;

        try (MulticastSocket socket = new MulticastSocket()) {
            InetAddress group = InetAddress.getByName(multicastAddress);
            String message="";
            BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));

            // Inviamo 5 messaggi di esempio
            while (!message.equals("exit")) {
                message = ui.readLine();
                byte[] buffer = message.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, port);
                socket.send(packet);

                System.out.println("Inviato: " + message);

                // Pausa di 1 secondo tra un invio e l’altro
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}