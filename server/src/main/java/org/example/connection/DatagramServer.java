package org.example.connection;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

@Getter
public class DatagramServer {

    private DatagramChannel server;
    private InetSocketAddress address = new InetSocketAddress(DataServer.host, DataServer.port);
    private SocketAddress clientAddress;
    private static final Logger log = LoggerFactory.getLogger(DatagramServer.class);

    public DatagramServer() throws IOException{
        this.server = startServer();
        this.server.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        this.server.configureBlocking(true);
    }

    private DatagramChannel startServer() throws IOException {
        DatagramChannel server = DatagramChannelBuilder.bindChannel(this.address);
        log.info("Server started at #{}", this.address);
        return server;
    }

    public byte[] receiveMessage() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(65535);
        this.clientAddress = this.server.receive(buffer);
        byte[] message = extractMessage(buffer);
        log.info("Client at #{}  sent: {}", this.clientAddress, new String(message));
        return message;
    }

    private static byte[] extractMessage(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }

    public void sendMessage(Object[] msg) throws IOException{
        byte[] message = toByteArray(msg);
        if (message != null){
            ByteBuffer buffer = ByteBuffer.wrap(message);
            this.server.send(buffer, this.clientAddress);
            log.info("Sender send message.");
        }else{
            log.warn("Sender don't send message.");
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
}