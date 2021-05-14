import LoginPage from '~/pages/loginPage';
import { PlayerContext } from '~/utils/PlayerContext';
import GamePage from '~/pages/gamePage';
let stompClient;
import React, { useState } from 'react';

const Home = () => {
	const [userJoint, setUserJoint] = useState(false);
	const [players, setPlayers] = useState([{}]);
	const [isPartyFullHooks, setPartyFull] = useState(false);
	let isPartyFull;
	const onConnected = () => {
		console.log('connected');
		sendMessage('connected');
		stompClient.subscribe('/client/playerList', function (greeting) {
			setPlayers(JSON.parse(greeting.body));
			console.log(isPartyFull);
			if (JSON.parse(greeting.body).length <= 4 && !isPartyFull) {
				setUserJoint(true);
			}
		});
		stompClient.subscribe('/user/client/reply', function (greeting) {
			isPartyFull = greeting.body === 'true';
			setPartyFull(isPartyFull);
			console.log(isPartyFull);
		});
		stompClient.subscribe('/user/client/something', function (greeting) {
			console.log(greeting.body);
		});
	};

	const onError = () => {
		console.log('error');
	};

	const connect = () => {
		const randomstring = require('randomstring');
		let sessionId = randomstring.generate(12);
		const Stomp = require('stompjs');
		let SockJS = require('sockjs-client');
		SockJS = new SockJS('http://localhost:8080//ws-monopoly', [], {
			sessionId: () => {
				console.log(`SessionId: ${sessionId}`);
				return sessionId;
			},
		});
		stompClient = Stomp.over(SockJS);
		stompClient.connect({}, onConnected, onError);
	};

	if (stompClient === undefined) {
		connect();
	}

	const sendMessage = (msg) => {
		stompClient.send('/server/message', {}, JSON.stringify({ name: msg }));
	};

	if (userJoint) {
		return (
			<PlayerContext.Provider
				value={{ players, stompClient, isPartyFullHooks }}
			>
				<GamePage />
			</PlayerContext.Provider>
		);
	}
	return (
		<PlayerContext.Provider value={{ players, stompClient, isPartyFullHooks }}>
			<LoginPage />;
		</PlayerContext.Provider>
	);
};

export default Home;
