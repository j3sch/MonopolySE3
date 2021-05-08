import LoginPage from '~/pages/loginPage';

let stompClient;

/* eslint-disable */
const Home = () => {
	connect();
	return <LoginPage stompClient={stompClient} />;
};

const connect = () => {
	const Stomp = require('stompjs');
	var SockJS = require('sockjs-client');
	SockJS = new SockJS('http://localhost:8080//gs-guide-websocket');
	stompClient = Stomp.over(SockJS);
	stompClient.connect({}, onConnected, onError);
};

const onConnected = () => {
	console.log('connected');
	stompClient.subscribe('/topic/greetings', function (greeting) {
		console.log(JSON.parse(greeting.body).content);
	});
};

const onError = () => {
	console.log('error');
};

const sendMessage = (msg) => {
	stompClient.send('/app/hello', {}, JSON.stringify({ name: msg }));
};

export default Home;
