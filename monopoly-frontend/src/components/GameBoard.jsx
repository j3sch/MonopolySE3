import { GameField } from './GameFields';
import { gameFieldData } from '~/data/gameFieldData';
import { borderColor } from '~/utils/borderColor';
import { EventFieldCard } from './EventFieldCard';
import NotificationMessage from './NotificationMessage';
import React, { useState, useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export function GameBoard() {
	const [isNotificationActiv, setNotification] = useState();
	const [message, setMessage] = useState('');
	const { stompClient } = useContext(PlayerContext) || {};

	stompClient.subscribe('/client/notification', function (greeting) {
		setNotification(true);
		setMessage(greeting.body);
	});
	stompClient.subscribe('/user/client/notification', function (greeting) {
		setNotification(true);
		setMessage(greeting.body);
	});

	const closeNotification = () => {
		setNotification(false);
	};

	return (
		<div className="grid grid-cols-9 h-screen text-black">
			<div className="row-start-2 row-end-7 col-start-3 col-end-8 bg-gray-100 dark:bg-gray-900 flex justify-center items-center">
				<div className="flow-root">
					<EventFieldCard />
					{isNotificationActiv && (
						<NotificationMessage
							closeNotification={closeNotification}
							message={message}
						/>
					)}
				</div>
			</div>
			{gameFieldData.map((gameField) => (
				<GameField
					key={gameField.id.toString()}
					id={gameField.id}
					title={gameField.title}
					price={gameField.price}
					condition={gameField.condition}
					borderColor={borderColor(gameField.id)}
					color={gameField.color}
				/>
			))}
		</div>
	);
}
