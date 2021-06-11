import { useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export const borderColor = (id) => {
	const { players } = useContext(PlayerContext);

	let color;
	const borderColors = [
		'border-red-500 border-4',
		'border-blue-500 border-4',
		'border-green-500 border-4',
		'border-yellow-500 border-4',
	];

	for (let i = 0; i < borderColors.length; i += 1) {
		if (players[i] !== null && id === players[i].position) {
			color = borderColors[i];
			break;
		} else {
			color = 'border-black border-2';
		}
	}
	return color;
};
