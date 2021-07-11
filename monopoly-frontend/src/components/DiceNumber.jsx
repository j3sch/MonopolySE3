import React, { useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export function DiceNumber() {
    const { diceNumber } = useContext(PlayerContext) || {};
    
	return (
		<div className="flex justify-center mb-3">
			<div className="h-20 w-20 bg-black dark:bg-gray-300 dark:text-black font-semibold shadow-lg text-5xl flex items-center justify-center bg-opacity-60">
				{diceNumber}
			</div>
		</div>
	);
}
