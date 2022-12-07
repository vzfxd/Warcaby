let ws = new WebSocket("ws://localhost:8080");
ws.onmessage = (event)=>{
    console.log(event.data);
}