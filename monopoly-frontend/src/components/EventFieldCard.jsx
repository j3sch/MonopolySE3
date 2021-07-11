import React, { useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export function EventFieldCard() {
    const { eventFieldMessage } = useContext(PlayerContext) || {};

    return (
        eventFieldMessage !== undefined &&
        <div className="h-60 w-96 bg-white bg-opacity-80 border-8 shadow-lg border-black text-black flex justify-center items-center text-2xl text-center">
            {eventFieldMessage}
        </div>
	);
}
