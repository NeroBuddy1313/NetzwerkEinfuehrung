package de.nerobuddy.netzwerkeinfuehrung.client;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author m_wei
 * @project NetzwerkEinfuehrung
 * @created 04.06.2022 - 10:04
 */

public final class MeinClient {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static InetSocketAddress address;

    public MeinClient(final String hostname, final int port) {
        address = new InetSocketAddress(hostname, port);
    }

    public static void main(final String[] args) {
        MeinClient client = new MeinClient("127.0.0.1", 3000);
        while (true) {
            System.out.print("[Client] Eingabe: ");
            client.sendMessage(SCANNER.next());
        }
    }

    public void sendMessage(final String msg) {
        try {

            System.out.println("[Client] Verbinde zu Server...");
            Socket socket = new Socket();
            socket.connect(address, 5000);
            System.out.println("[Client] Verbunden.");

            System.out.println("[Client] Sende Nachricht...");
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            printWriter.write(msg);
            printWriter.flush();
            System.out.println("[Client] Nachricht gesendet.");

            printWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
