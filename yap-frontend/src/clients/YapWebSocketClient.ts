import { Client, IMessage } from "@stomp/stompjs";

const getWebSocketClient = (url: string, onConnect?: any, onStompError?: any) =>
  new Client({
    brokerURL: url,
    debug: function (str) {
      console.log(str);
    },
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
    onConnect,
    onStompError,
  });

class YapWebSocketClient {
  private client: Client;
  private connected: boolean = false;

  constructor() {
    this.client = getWebSocketClient(
      "ws://localhost:8080/ws",
      () => console.log("WS success"),
      () => console.error("WS failure"),
    );
    this.client.onConnect = () => (this.connected = true);
    this.client.activate();
  }

  subscribe = (destination: string, onMessage: (message: IMessage) => void) => {
    if (!this.connected) {
      throw new Error("YapClient is not yet connected");
    }
    return this.client.subscribe(destination, onMessage);
  };
}

const client = new YapWebSocketClient();

export { client };
