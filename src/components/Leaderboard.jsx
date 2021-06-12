import React, { useContext } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

// colors are missing by players
export function Leaderboard() {
	const { players } = useContext(PlayerContext);

	return (
		<div>
			{players.map(
				(player, i) =>
					player !== null && (
						<div
							key={i}
							className="grid justify-items-stretch grid-cols-6 h-16 p-1 group hover:text-2xl font-bold text-xl transition hover:p-0 hover:bg-opacity-10 "
						>
							<div
								className={`bg-[${player.colour}] mr-2 group-hover:mr-1 col-span-2 xl:col-span-1 items-center grid justify-items-center`}
							>
								{i + 1}
							</div>
							<div
								className={`bg-[${player.colour}] col-span-4 xl:col-span-5 grid grid-cols-2 items-center px-3 `}
							>
								<div className="col-span-1 ">{player.name}</div>
								<div className="col-span-1 justify-self-end">
									{player.playerBankBalance}
								</div>
							</div>
						</div>
					),
			)}
		</div>
	);
}
