# TCP client and server in Java

A course project for Computer Networks (CNET101) at the German International University, spring semester 2024. Team project.

Two programs talk to each other over a TCP socket: first on one machine, then on two laptops on the same Wi-Fi network. The traffic was then recorded in Wireshark and read packet by packet.

## What is in here

| Stage | Files | What it does |
| --- | --- | --- |
| 1. One machine | `TCPServer.java`, `TCPClient.java` | The client sends one line. The server sends it back in capitals. |
| 2. Two laptops | `TCPServer2.java`, `TCPClient2.java` | The client opens the connection only after you type `CONNECT`. It then sends a message, and the server shows it and sends back a reply typed at its own keyboard. |
| 3. Packet capture | (no code) | The Wi-Fi traffic was captured in Wireshark, filtered by IP address, and a packet was opened layer by layer to read its bytes. |

All four programs are in `src/TCP`.

## Run it

You need Java 17 or newer. The folder is an Eclipse project, so you can import it into Eclipse and run each class from there. From a terminal:

```
javac -d bin src/module-info.java src/TCP/*.java
```

### Stage 1, on one machine

Start the server in one terminal:

```
java -cp bin TCP.TCPServer
```

Then run the client in a second terminal and type a line:

```
java -cp bin TCP.TCPClient
```

```
hello from giu
FROM SERVER: HELLO FROM GIU
```

### Stage 2, on two laptops

Before you compile, edit two things so the programs can find each other:

1. In `TCPClient2.java`, set `serverAddress` to the server laptop's IPv4 address. On Windows, `ipconfig` shows it.
2. Use the same port number in `TCPServer2.java` and `TCPClient2.java`. Any free port from 1024 to 49151 works.

Both laptops must be on the same Wi-Fi network or hotspot. Start `TCP.TCPServer2` on the server laptop, then `TCP.TCPClient2` on the client laptop:

```
Type 'CONNECT' to connect to the server
CONNECT
Connected to server
Hello
Server: Hello
```

Anything other than `CONNECT` is answered with `INVALID INPUT` and the connection stays closed.

## Good to know

- The stage 2 server reads one message and sends one reply for each connection, then waits for the next client.
- The address and ports in the stage 2 files are the ones from our own test network, so they need changing as described above.
