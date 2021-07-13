import React, { useContext, useState } from 'react';
import { PlayerContext } from '~/utils/PlayerContext';

export function Leaderboard() {
	const { players, currentPlayer, boughtEstates } =
		useContext(PlayerContext) || {};
	const [isShowBoughtStreetsActiv, setShowBoughtStreetsActiv] = useState(false);

	const showBoughtStreets = (colour) => {
		if (colour === isShowBoughtStreetsActiv) {
			setShowBoughtStreetsActiv('');
		} else {
			setShowBoughtStreetsActiv(colour);
		}
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
								role="button"
								tabIndex={i}
								onClick={() => showBoughtStreets(player.colour)}
								onKeyDown={() => showBoughtStreets(player.colour)}
							>
								<div
									className={`bg-[${
										player.colour
									}] mr-2 group-hover:mr-1 h-16 col-span-1 grid items-center justify-items-center ${
										i === currentPlayer &&
										'border-2 border-gray-900 dark:border-white'
									}`}
								>
									{i + 1}
								</div>
								<div
									className={`bg-[${
										player.colour
									}] col-span-5 grid grid-cols-2 items-center px-3 ${
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
										className={`grid relative col-span-6 pb-2 font-medium mt-2 bg-[${player.colour}]`}
									>
										{boughtEstates.map((boughtEstate) => {
											return (
												boughtEstate.playerColour === player.colour && (
													<div>
														<p className="inline-block 2xl:ml-3 xl:ml-2 ml-1 p-1">
															{boughtEstate.fieldPosition}
														</p>
														<p className="inline-block absolute 2xl:left-[4.5rem] xl:left-[3.5rem] left-[2.5rem] mt-1">
															4f1b9e1935484316dd56fcfd39e21381
															{boughtEstate.estateName}
														</p>
														<div
															className={`inline-block h-5 w-5 absolute 2xl:right-5 xl:right-4 right-3 mt-3 border bg-[${boughtEstate.estateColour}]`}
														/>
													</div>
												)
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
