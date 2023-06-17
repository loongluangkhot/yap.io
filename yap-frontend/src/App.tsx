import { useEffect, useState } from "react";
import "./App.css";
import axios from "axios";
import { IChat, IChatCreationRequest } from "./models/Chat";

function App() {
  const [chat, setchat] = useState<IChat | null>(null);

  useEffect(() => {
    if (!chat) return;
    console.log("chat is created");
    console.log(chat);
  }, [chat]);

  return (
    <div className="App">
      <CreateChat onChatCreation={setchat} />
    </div>
  );
}

interface ICreateChatProps {
  onChatCreation: (chat: IChat) => void;
}

const CreateChat = (props: ICreateChatProps) => {
  const [chatName, setChatName] = useState("CHATNAME");
  const [username, setUsername] = useState("USERNAME");

  const handleClick = async () => {
    const payload: IChatCreationRequest = {
      chatName: chatName,
      username: username,
    };
    const res = await axios.post("https://localhost:8080/chat", payload);
    props.onChatCreation(res.data);
  };

  return (
    <div>
      <input value={chatName} onChange={(i) => setChatName(i.target.value)} />
      <input value={username} onChange={(i) => setUsername(i.target.value)} />
      <button onClick={handleClick}>+</button>
    </div>
  );
};

export default App;
