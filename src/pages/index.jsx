import LoginPage from '~/pages/loginPage';
import { PlayerContext } from '~/utils/PlayerContext';
import GamePage from '~/pages/gamePage';
let stompClient;
let players = [{ name: 'tim' }, { name: 'anne' }];

/* eslint-disable */
const Home = () => {
	connect();

	return (
		<PlayerContext.Provider value={{ players, stompClient }}>
			<LoginPage />
			<GamePage />
		</PlayerContext.Provider>
	);
};

const connect = () => {
	const Stomp = require('stompjs');
	let SockJS = require('sockjs-client');
	SockJS = new SockJS('http://localhost:8080//gs-guide-websocket');
	stompClient = Stomp.over(SockJS);
	stompClient.connect({}, onConnected, onError);
};

const onConnected = () => {
	console.log('connected');
	stompClient.subscribe('/topic/greetings', function (greeting) {
		console.log(JSON.parse(greeting.body).content);
		players = JSON.parse(greeting.body).content;
	});
};

const onError = () => {
	console.log('error');
};

const sendMessage = (msg) => {
	stompClient.send('/app/hello', {}, JSON.stringify({ name: msg }));
};

export default Home;
