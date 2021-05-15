/* eslint-disable no-unused-expressions */
// fehler nicht gefunden
export const borderColor = (id) => {
	const player1 = 18;
	const player2 = 21;
	const player3 = 6;
	const player4 = 13;
	let color;

	(id === player1 && (color = 'border-red-500 border-4')) ||
		(id === player2 && (color = 'border-blue-500 border-4')) ||
		(id === player3 && (color = 'border-green-500 border-4')) ||
		(id === player4 && (color = 'border-yellow-500 border-4')) ||
		(color = 'border-black border-2');

	return color;
};
