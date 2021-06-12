import { useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export const borderColor = (id) => {
	const { players } = useContext(PlayerContext);

	let color;

	(players[0] === null
		? 0
		: id === players[0].position && (color = 'border-red-500 border-4')) ||
		(players[1] === null
			? 0
			: id === players[1].position && (color = 'border-blue-500 border-4')) ||
		(players[2] === null
			? 0
			: id === players[2].position && (color = 'border-green-500 border-4')) ||
		(players[3] === null
			? 0
			: id === players[3].position && (color = 'border-yellow-500 border-4')) ||
		(color = 'border-black border-2');

	return color;
};
