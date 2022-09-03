import { FormEvent, useEffect, useState } from "react";
import "./App.css";
import { client } from "./clients/WebSocketClient";

function App() {
  const [ready, setready] = useState(false);

  useEffect(() => {
    client.onConnect = () => setready(true);
    client.activate();
  }, []);

  useEffect(() => {
    if (!ready) return;
    const subscription = client.subscribe("/topic/chat/1", (message) =>
      console.log(message.body)
    );
    return () => subscription.unsubscribe();
  }, [ready]);

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    const message = formData.get("message")?.toString();
    console.log(message);
    client.publish({
      destination: "/app/chat/1",
      body: JSON.stringify({
        id: 123,
        from: "Long",
        message,
        time: Date.now,
      }),
    });
  };

  return (
    <div className="App">
      <form onSubmit={handleSubmit}>
        <label htmlFor="message">message</label>
        <input id="message" name="message"/>
      </form>
    </div>
  );
}

export default App;
