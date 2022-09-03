import { Client } from "@stomp/stompjs";

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

const client = getWebSocketClient(
  "ws://localhost:8080/ws",
  () => console.log("WS success"),
  () => console.error("WS failure")
);

export { client };
