import LoginPage from '~/pages/loginPage';
import { PlayerContext } from '~/utils/PlayerContext';
import GamePage from '~/pages/gamePage';
let stompClient;
import React, { useState } from 'react';

const Home = () => {
	const [userJoint, setUserJoint] = useState(false);
	const [players, setPlayers] = useState([{}]);
	const [isPartyFullHooks, setPartyFull] = useState(false);
	const [isDiceNumberBtnDisabled, setDiceNumberBtn] = useState(true);
	const [isNextPlayerBtnDisabled, setNextPlayerBtn] = useState(true);
	const [isBuyEstateBtnDisabled, setBuyEstateBtn] = useState(true);

	const [fieldPosition, setFieldPosition] = useState(-1);
	const [estateColor, setEstateColor] = useState();

	let isPartyFull;

	const sendMessage = (msg) => {
		stompClient.send('/server/message', {}, JSON.stringify({ name: msg }));
	};

	const onConnected = () => {
		sendMessage('connected');
		stompClient.subscribe('/client/playerList', function (greeting) {
			setPlayers(JSON.parse(greeting.body));
			if (JSON.parse(greeting.body).length <= 4 && !isPartyFull) {
				setUserJoint(true);
			}
		});
		stompClient.subscribe('/user/client/reply', function (greeting) {
			isPartyFull = greeting.body === 'true';
			setPartyFull(isPartyFull);
		});
		stompClient.subscribe('/client/notification', function (greeting) {});
		stompClient.subscribe('/user/client/toggleAllBtn', function (greeting) {
			setDiceNumberBtn(greeting.body === 'true');
			setNextPlayerBtn(greeting.body === 'true');
			setBuyEstateBtn(greeting.body === 'true');
		});
		stompClient.subscribe(
			'/user/client/toggleDiceNumberBtn',
			function (greeting) {
				setDiceNumberBtn(greeting.body === 'true');
			},
		);
		stompClient.subscribe('/client/buyEstate', function (greeting) {
			setFieldPosition(parseInt(greeting.body.split(' ')[0], 10));
			setEstateColor(greeting.body.split(' ')[1]);
		});
		stompClient.subscribe(
			'/user/client/toggleNextPlayerBtn',
			function (greeting) {
				setNextPlayerBtn(greeting.body === 'true');
			},
		);
		stompClient.subscribe(
			'/user/client/toggleBuyEstateBtn',
			function (greeting) {
				setBuyEstateBtn(greeting.body === 'true');
			},
		);
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
				return sessionId;
			},
		});
		stompClient = Stomp.over(SockJS);

		stompClient.connect({}, onConnected, onError);
	};

	if (stompClient === undefined) {
		connect();
	}

	if (userJoint) {
		return (
			<PlayerContext.Provider
				value={{
					players,
					isDiceNumberBtnDisabled,
					stompClient,
					isNextPlayerBtnDisabled,
					isBuyEstateBtnDisabled,
					fieldPosition,
					estateColor,
				}}
			>
				<GamePage />
			</PlayerContext.Provider>
		);
	}
	return (
		<PlayerContext.Provider value={{ stompClient, isPartyFullHooks }}>
			<LoginPage />;
		</PlayerContext.Provider>
	);
};

export default Home;
