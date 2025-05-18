package org.example.connection;

import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

@Getter
public class DatagramClient {

    private DatagramChannel client;
    private InetSocketAddress serverAddress = new InetSocketAddress(DataServer.host, DataServer.port);

    protected DatagramClient() throws IOException{
        this.client = startClient();
        this.client.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        this.client.configureBlocking(false);
    }

    private DatagramChannel startClient() throws IOException {
        DatagramChannel client = DatagramChannelBuilder.openChannel();
        System.out.println("Client started.");
        return client;
    }

    public void sendMessage(Object[] msg) throws IOException {
        byte[] message = toByteArray(msg);
        if (message != null) {
            ByteBuffer buffer = ByteBuffer.wrap(message);
            this.client.send(buffer, this.serverAddress);
            System.out.println("Client send message.");
        }else{
            System.out.println("Client don't send message. Message is null.");
        }
    }

    private static byte[] toByteArray(Object[] obj) {
        try (
                ByteArrayOutputStream data = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(data);
        ) {
            for (Object cur : obj) {
                oos.writeObject(cur);
            }
            return data.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    public byte[] receiveMessage() throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(65535);
        Thread.sleep(1000);
        SocketAddress remoteAdd = this.client.receive(buffer);
        if (remoteAdd != null) {
            byte[] message = extractMessage(buffer);
//            System.out.println("Server at #" + remoteAdd + "  sent: " + new String(message));
            return message;
        }
        return null;
    }

    private static byte[] extractMessage(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }
}