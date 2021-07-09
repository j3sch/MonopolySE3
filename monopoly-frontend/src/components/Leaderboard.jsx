import React, { useContext, useState } from 'react';
import { forEach } from 'sockjs-client/lib/transport-list';
import { PlayerContext } from '~/utils/PlayerContext';

// colors are missing by players
export function Leaderboard() {
	const { players, currentPlayer, boughtEstates } =
		useContext(PlayerContext) || {};
	const [isShowBoughtStreetsActiv, setShowBoughtStreetsActiv] = useState(false);

	const showBoughtStreets = (colour) => {
		colour === isShowBoughtStreetsActiv
			? setShowBoughtStreetsActiv('')
			: setShowBoughtStreetsActiv(colour);
	};

	return (
		<div>
			{players !== undefined &&
				players.map(
					(player, i) =>
						player !== null && (
							<div
								key={player.id}
								className="grid grid-cols-6  justify-items-stretch p-1 group hover:text-2xl font-bold text-xl transition hover:p-0"
								onClick={() => showBoughtStreets(player.colour)}
							>
								<div
									className={`bg-[${
										player.colour
									}] mr-2 group-hover:mr-1 h-16 col-span-2 xl:col-span-1 grid items-center justify-items-center ${
										i === currentPlayer &&
										'border-2 border-gray-900 dark:border-white'
									}`}
								>
									{i + 1}
								</div>
								<div
									className={`bg-[${
										player.colour
									}] col-span-4 xl:col-span-5 grid grid-cols-2 items-center px-3 ${
										i === currentPlayer &&
										'border-2 border-gray-900 dark:border-white'
									}`}
								>
									<div className="col-span-1">{player.name}</div>
									<div className="col-span-1 justify-self-end">
										{player.playerBankBalance}
									</div>
								</div>
								{isShowBoughtStreetsActiv === player.colour && (
									<div
										className={`grid col-span-6 p-1 pb-2 mt-2 bg-[${player.colour}]`}
									>
										{boughtEstates.map((boughtEstate, i) => {
											return boughtEstate.playerColour == player.colour ? (
												<div>
													<p key={i} className="inline-block p-1">
														{boughtEstate.fieldPosition}
													</p>
													<p key={i} className="inline-block p-1">
														{boughtEstate.estateName}
													</p>
													<div
														className={`inline-block h-5 w-5 border bg-[${boughtEstate.estateColour}]`}
													></div>
												</div>
											) : (
												<p></p>
											);
										})}
									</div>
								)}
							</div>
						),
				)}
		</div>
	);
}
