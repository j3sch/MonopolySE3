import { useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export function BuyEstateButton() {
	const { isBuyEstateBtnDisabled } = useContext(PlayerContext) || true;
	const { stompClient } = useContext(PlayerContext) || {};

	const sendButtonClickEvent = () => {
		stompClient.send(
			'/server/buyEstateBtnClicked',
			{},
			JSON.stringify({ message: 'Player has bought the estate' }),
		);
	};

	return (
		<button
			onClick={sendButtonClickEvent}
			type="submit"
			className="px-4 py-2 text-lg bg-blue-600 hover:bg-blue-700 active:bg-blue-300 shadow-lg 
			block font-semibold tracking-wider text-white rounded disabled:opacity-70 disabled:bg-blue-600"
			disabled={isBuyEstateBtnDisabled}
		>
			Buy estate
		</button>
	);
}
