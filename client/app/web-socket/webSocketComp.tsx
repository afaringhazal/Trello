
import {useEffect} from 'react'
function WebSocketComponent() {
    useEffect(() => {
    // WebSocket connection setup
    const socket = new WebSocket('wss://your-url');
    // Event listeners
    socket.onopen = function (event) {
    console.log('WebSocket connection established');
    };
    socket.onmessage = function (event) {
    console.log('Message from server:', event.data);
    };
    socket.onerror = function (error) {
    console.error('WebSocket error:', error);
    };
    socket.onclose = function (event) {
    console.log('WebSocket connection closed:', event);
    };
    // Cleanup function
    return () => {
    socket.close();
    console.log('WebSocket connection closed');
    };
    }, []);
    return <div>WebSocket Component</div>;
}
export default WebSocketComponent;