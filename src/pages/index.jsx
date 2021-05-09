import LoginPage from '~/pages/loginPage';
import { PlayerContext } from '~/utils/PlayerContext';
import GamePage from '~/pages/gamePage';
let stompClient;
let players = [{ name: 'tim' }, { name: 'anne' }];
import React, { useState } from 'react';

/* eslint-disable */
const Home = () => {
	const [userJoint, setUserJoint] = useState(false);

	const onConnected = () => {
		console.log('connected');
		stompClient.subscribe('/topic/greetings', function (greeting) {
			players.push({ name: JSON.parse(greeting.body).content });
			setUserJoint(true);
			console.log('Hello :' + JSON.parse(greeting.body).content);
		});
	};

	const onError = () => {
		console.log('error');
	};

	const connect = () => {
		const Stomp = require('stompjs');
		let SockJS = require('sockjs-client');
		SockJS = new SockJS('http://localhost:8080//gs-guide-websocket');
		stompClient = Stomp.over(SockJS);
		stompClient.connect({}, onConnected, onError);
	};

	if (stompClient === undefined) {
		connect();
	}

	const sendMessage = (msg) => {
		stompClient.send('/app/hello', {}, JSON.stringify({ name: msg }));
	};

	return (
		<PlayerContext.Provider value={{ players, stompClient }}>
			<LoginPage userJoint={userJoint} />
			<GamePage userJoint={userJoint} />
		</PlayerContext.Provider>
	);
};

export default Home;
