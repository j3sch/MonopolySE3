import React, { useState } from 'react';
import LoginPage from '~/pages/loginPage';
import { PlayerContext } from '~/utils/PlayerContext';
import GamePage from '~/pages/gamePage';
import NotificationMessage from '~/components/NotificationMessage';

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
	const [currentPlayer, setCurrentPlayer] = useState(0)

	const array = [];
	const [boughtEstate, setBoughtEstate] = useState([{}]);
	// for receiving messages
	const [isNotificationActiv, setNotification] = useState();
	const [message, setMessage] = useState('');

	let isPartyFull;

	const sendMessage = (msg) => {
		stompClient.send('/server/message', {}, JSON.stringify({ name: msg }));
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
			'/user/client/toggleDiceNumberBtn',
			function (greeting) {
				setDiceNumberBtn(greeting.body === 'true');
			},
		);
		stompClient.subscribe('/client/buyEstate', function (greeting) {
			const fieldPosition = parseInt(greeting.body.split(' ')[0], 10);
			const estateColor = greeting.body.split(' ')[1];
			array.push({ fieldPosition, estateColor });
			setBoughtEstate(array);
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
		stompClient.subscribe('/client/notification', function (greeting) {
			setNotification(true);
			setMessage(greeting.body);
		});
		stompClient.subscribe('/user/client/notification', function (greeting) {
			setNotification(true);
			setMessage(greeting.body);
		});
	};

	const onError = () => {
		console.log('error');
	};

	const connect = () => {
		const sessionId = randomstring.generate(12);
		SockJS = new SockJS(
			'https://radiant-falls-52552.herokuapp.com//ws-monopoly',
			[],
			{
				sessionId: () => {
					return sessionId;
				},
			},
		);
		stompClient = Stomp.over(SockJS);

		stompClient.connect({}, onConnected, onError);
	};

	if (stompClient === undefined) {
		connect();
	}

	const closeNotification = () => {
		setNotification(false);
	};

	if (userJoint) {
		return (
			<PlayerContext.Provider
				value={{
					players,
					isDiceNumberBtnDisabled,
					stompClient,
					isNextPlayerBtnDisabled,
					isBuyEstateBtnDisabled,
					boughtEstate,
					currentPlayer
				}}
			>
				<GamePage />
				{isNotificationActiv && (
					<NotificationMessage
						closeNotification={closeNotification}
						message={message}
					/>
				)}
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
