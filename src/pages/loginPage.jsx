import React, { useState, useReducer } from 'react';
import LoginNotification from '~/components/LoginNotification';
import { reducer } from '~/utils/reducer';

const defaultState = {
	people: [],
	isNotificationActiv: false,
	message: '',
};
const LoginPage = () => {
	const [name, setName] = useState('');
	const [state, dispatch] = useReducer(reducer, defaultState);

	const handleSubmit = (e) => {
		e.preventDefault();
		if (name) {
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