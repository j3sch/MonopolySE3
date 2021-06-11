import { PlayerContext } from '~/utils/PlayerContext';
import { useContext } from 'react';

export function NextPlayerButton() {
	const { isNextPlayerBtnDisabled } = useContext(PlayerContext);
	const { stompClient } = useContext(PlayerContext);

	const sendButtonClickEvent = () => {
		stompClient.send(
			'/server/nextPlayerBtnClicked',
			{},
			JSON.stringify({ message: 'Next players turn' }),
		);
	};

	return (
		<button
			onClick={sendButtonClickEvent}
			type="submit"
			className="px-4 py-2 text-lg bg-blue-600 hover:bg-blue-700
			active:bg-blue-300 shadow-lg block font-semibold tracking-wider text-white
			rounded disabled:opacity-70 disabled:bg-blue-600"
			disabled={isNextPlayerBtnDisabled}
		>
			Next player
		</button>
	);
}
