import { PlayerContext } from '~/utils/PlayerContext';
import { useContext } from 'react';

export const sendButtonClickEvent = () => {
	const { stompClient } = useContext(PlayerContext);
        
	stompClient.send('/server/buttonClicked', {}, JSON.stringify({ message : "Player has rolled a number" }));
};
