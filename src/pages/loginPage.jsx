import React, { useState, useReducer, useContext } from 'react';
import LoginNotification from '~/components/LoginNotification';
import { reducer } from '~/utils/reducer';
import { PlayerContext } from '~/utils/PlayerContext';

const defaultState = {
	people: [],
	isNotificationActiv: false,
	message: '',
};
const LoginPage = () => {
	const { isPartyFullHooks } = useContext(PlayerContext);
	const { stompClient } = useContext(PlayerContext);

	const [name, setName] = useState('');
	const [state, dispatch] = useReducer(reducer, defaultState);

	const handleSubmit = (e) => {
		sendMessage(name);
		e.preventDefault();
		if (isPartyFullHooks) {
			dispatch({ type: 'PARTY_FULL' });
		} else if (name) {
			const newItem = { id: new Date().getTime().toString(), name };
			dispatch({ type: 'USER_ADDED', payload: newItem });

			setName('');
		} else {
			dispatch({ type: 'NO_INPUT' });
		}
	};

	const closeNotification = () => {
		dispatch({ type: 'CLOSE_NOTIFICATION' });
	};

	const sendMessage = (name) => {
		stompClient.send('/server/playerName', {}, JSON.stringify({ name }));
	};

	return (
		<div className="flex h-full">
			<div className="mx-auto text-white">
				<h1 className="text-white font-semibold text-4xl mt-28 mb-6">
					Welcome to Monopoly Game
				</h1>
				<form
					onSubmit={handleSubmit}
					className="form bg-blue-200 py-14 px-20 flex bg-opacity-20 rounded text-lg mb-6"
				>
					<div>
						<label htmlFor="submit">
							Pls, enter your name
							<input
								className="py-1 text-black mx-8 rounded"
								type="text"
								value={name}
								onChange={(e) => setName(e.target.value)}
							/>
						</label>
					</div>
					<button
						type="submit"
						className="bg-green-600 py-1 px-3 rounded font-semibold"
					>
						Add
					</button>
				</form>
				{state.isNotificationActiv && (
					<LoginNotification
						closeNotification={closeNotification}
						message={state.message}
						backgroundColor={state.backgroundColor}
					/>
				)}
			</div>
		</div>
	);
};

export default LoginPage;
