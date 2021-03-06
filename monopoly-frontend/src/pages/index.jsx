import React, { useState } from 'react';
import LoginPage from '~/pages/loginPage';
import { PlayerContext } from '~/utils/PlayerContext';
import GamePage from '~/pages/gamePage';

let stompClient;
const Stomp = require('stompjs');
let SockJS = require('sockjs-client');
const randomstring = require('randomstring');

const Home = () => {
	const [userJoint, setUserJoint] = useState(false);
	const [players, setPlayers] = useState([{}]);
	const [isPartyFullHooks, setPartyFull] = useState(false);
	const [isDiceNumberBtnDisabled, setDiceNumberBtn] = useState(true);
	const [isNextPlayerBtnDisabled, setNextPlayerBtn] = useState(true);
	const [isBuyEstateBtnDisabled, setBuyEstateBtn] = useState(true);
	const [currentPlayer, setCurrentPlayer] = useState(0);
	const [diceNumber, setDiceNumber] = useState('0');
	const [eventFieldMessage, setEventFieldMessage] = useState();
	const [freeParkingCredit, setFreeParkingCredit] = useState(0);
	const array = [];
	const [boughtEstates, setBoughtEstates] = useState([{}]);
	const [isNotificationActiv, setNotification] = useState();
	const [message, setMessage] = useState('');
	let isPartyFull;

	const sendMessage = (msg) => {
		stompClient.send('/server/isPartyFull', {}, JSON.stringify({ name: msg }));
	};

	const onConnected = () => {
		sendMessage('connected');
		stompClient.subscribe('/user/client/playerList', function (greeting) {
			setPlayers(JSON.parse(greeting.body));
			if (JSON.parse(greeting.body).length <= 4 && !isPartyFull) {
				setUserJoint(true);
			}
		});
		stompClient.subscribe('/client/highlightPlayer', function (greeting) {
			setCurrentPlayer(JSON.parse(greeting.body));
		});
		stompClient.subscribe('/client/playerList', function (greeting) {
			setPlayers(JSON.parse(greeting.body));
		});
		stompClient.subscribe('/user/client/reply', function (greeting) {
			isPartyFull = greeting.body === 'true';
			setPartyFull(isPartyFull);
		});
		stompClient.subscribe('/user/client/toggleAllBtn', function (greeting) {
			setDiceNumberBtn(greeting.body === 'true');
			setNextPlayerBtn(greeting.body === 'true');
			setBuyEstateBtn(greeting.body === 'true');
		});
		stompClient.subscribe(
			'/user/client/eventFieldMessage',
			function (greeting) {
				setEventFieldMessage(greeting.body);
			},
		);
		stompClient.subscribe(
			'/user/client/toggleDiceNumberBtn',
			function (greeting) {
				setDiceNumberBtn(greeting.body === 'true');
			},
		);
		stompClient.subscribe('/client/diceNumber', function (greeting) {
			setDiceNumber(JSON.parse(greeting.body));
		});
		stompClient.subscribe('/client/buyEstate', function (greeting) {
			const body = JSON.parse(greeting.body);
			const fieldPosition = parseInt(body[0], 10);
			const estateName = body[1];
			const playerColour = body[2];
			const estateColour = body[3];

			array.push({ fieldPosition, estateName, playerColour, estateColour });
			setBoughtEstates(array);
		});
		stompClient.subscribe(
			'/user/client/toggleNextPlayerBtn',
			function (greeting) {
				setNextPlayerBtn(greeting.body === 'true');
			},
		);

		stompClient.subscribe('/client/freeParkingCredit', function (greeting) {
			setFreeParkingCredit(JSON.parse(greeting.body));
		});
		stompClient.subscribe(
			'/user/client/toggleBuyEstateBtn',
			function (greeting) {
				setBuyEstateBtn(greeting.body === 'true');
			},
		);
		stompClient.subscribe('/client/notification', function (greeting) {
			setNotification(false);
			setNotification(true);
			setMessage(greeting.body);
		});
		stompClient.subscribe('/user/client/notification', function (greeting) {
			setNotification(true);
			setMessage(greeting.body);
		});
	};

	const onError = () => {
		console.error('Error appeared when connecting to the server');
	};

	const connect = () => {
		const sessionId = randomstring.generate(12);
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
					boughtEstates,
					currentPlayer,
					diceNumber,
					eventFieldMessage,
					setEventFieldMessage,
					freeParkingCredit,
					message,
					isNotificationActiv,
					setNotification,
				}}
			>
				<GamePage />
			</PlayerContext.Provider>
		);
	}
	return (
		<PlayerContext.Provider value={{ stompClient, isPartyFullHooks }}>
			<LoginPage />
		</PlayerContext.Provider>
	);
};

export default Home;
