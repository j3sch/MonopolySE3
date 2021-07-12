import React, { useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export function DiceNumber() {
    const { diceNumber } = useContext(PlayerContext) || {};
    
	return (
		<div className="flex justify-center mb-3">
			<div className="h-20 w-20 bg-black dark:bg-white dark:text-black font-semibold shadow-lg text-5xl flex items-center justify-center dark:bg-opacity-80 rounded-md">
				{diceNumber}
			</div>
		</div>
	);
}
