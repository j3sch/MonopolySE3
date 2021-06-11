export const reducer = (state, action) => {
	if (action.type === 'USER_ADDED') {
		const newPeople = [...state.people, action.payload];
		return {
			...state,
			people: newPeople,
			isNotificationActiv: true,
			message: 'player added',
			backgroundColor: 'bg-green-500',
		};
	}
	if (action.type === 'NO_INPUT') {
		return {
			...state,
			isNotificationActiv: true,
			message: 'please enter your name',
			backgroundColor: 'bg-red-500',
		};
	}
	if (action.type === 'PARTY_FULL') {
		return {
			...state,
			isNotificationActiv: true,
			message: 'Sry, party is full',
			backgroundColor: 'bg-red-500',
		};
	}
	if (action.type === 'CLOSE_NOTIFICATION') {
		return {
			...state,
			isNotificationActiv: false,
		};
	}
	throw new Error('no matching action type');
};
