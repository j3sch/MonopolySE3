import { PlayerContext } from '~/utils/PlayerContext';
import { useContext } from 'react';

export function DiceNumberButton() {
	const { isButtonDisabled } = useContext(PlayerContext);

	return (
		<button
			type="submit"
			className="px-4 py-2 text-lg bg-blue-600 hover:bg-blue-700 active:bg-blue-300  
			shadow-lg block font-semibold tracking-wider text-white rounded disabled:opacity-70 disabled:bg-blue-600"
			disabled={isButtonDisabled}
		>
			Dice number
		</button>
	);
}
