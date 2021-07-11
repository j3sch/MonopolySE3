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
	const { isPartyFullHooks } = useContext(PlayerContext) || false;
	const { stompClient } = useContext(PlayerContext) || {};

	const [name, setName] = useState('');
	const [state, dispatch] = useReducer(reducer, defaultState);

	const sendMessage = (playerName) => {
		stompClient.send(
			'/server/playerName',
			{},
			JSON.stringify({ name: playerName }),
		);
	};

	const handleSubmit = (e) => {
		
		e.preventDefault();
		if (isPartyFullHooks) {
			dispatch({ type: 'PARTY_FULL' });
		} else if (name) {
			const newItem = { name };
			dispatch({ type: 'USER_ADDED', payload: newItem });
			sendMessage(name);
			setName('');
		} else {
			dispatch({ type: 'NO_INPUT' });
		}
	};

	const closeNotification = () => {
		dispatch({ type: 'CLOSE_NOTIFICATION' });
	};

	return (
		<div className="flex h-full">
			<div className="mx-auto">
				<h1 className="font-semibold text-4xl mt-28 mb-6">
					Welcome to the Monopoly Game
				</h1>
				<form
					onSubmit={handleSubmit}
					className="form shadow-md bg-white py-14 px-20 flex bg-opacity-10 rounded text-lg mb-6 "
				>
					<div className="pr-4">
						<label className="block sm:inline-block pr-8" htmlFor="submit">
							Pls, enter your name
						</label>
						<input
							className="py-1 text-black rounded appearance-none leading-tight border focus:border-blue-400 outline-none"
							placeholder="Name"
							type="text"
							value={name}
							onChange={(e) => setName(e.target.value)}
						/>
					</div>
					<button
						type="submit"
						className=" py-1 px-3 bg-green-400 rounded font-semibold text-gray-900"
					>
						Submit
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
