package de.nerobuddy.netzwerkeinfuehrung.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author m_wei
 * @project NetzwerkEinfuehrung
 * @created 04.06.2022 - 10:04
 */

public record MeinServer(int port) {

    private static ServerSocket serverSocket;
    private static Socket remoteClientSocket;

    public static void main(final String[] args) {
        MeinServer server = new MeinServer(3000);
        server.startListening();
    }

    public void startListening() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("[Server] Server startet...");
                    serverSocket = new ServerSocket(3000);
                    System.out.println("[Server] Warten auf Verbindung...");
                    remoteClientSocket = serverSocket.accept();
                    System.out.println("[Server] Client verbunden: " + remoteClientSocket.getRemoteSocketAddress());

                    Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
                    if (scanner.hasNextLine()) {
                        System.out.println("[Server] Neue Nachricht vom Client: " + scanner.nextLine());
                    }

                    scanner.close();
                    serverSocket.close();
                    remoteClientSocket.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
