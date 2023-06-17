export interface IChatCreationRequest {
  chatName: string;
  username: string;
}

export interface IChat {
  name: string;
  id: string;
  creationDt: string;
}
