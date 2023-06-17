import { messageCallbackType } from "@stomp/stompjs";
import { useEffect } from "react";
import { client } from "../clients/YapWebSocketClient";

export const useChatSubscription = (chatId: string, onMessage: messageCallbackType) => {
  useEffect(() => {
    const subscription = client.subscribe("/topic/chat/1", onMessage);
    return () => subscription.unsubscribe();
  }, [chatId, onMessage]);
};
