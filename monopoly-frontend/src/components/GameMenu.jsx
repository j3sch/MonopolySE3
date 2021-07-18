import { DiceNumberButton } from '~/ui/DiceNumberButton';
import { BuyEstateButton } from '~/ui/BuyEstateButton';
import { NextPlayerButton } from '~/ui/NextPlayerButton';
import { Leaderboard } from '~/components/Leaderboard';
import { DiceNumber } from '~/components/DiceNumber';

export function GameMenu() {
	return (
		<div className="text-white">
			<Leaderboard />
			<div className="absolute bottom-6 lg:bottom-14 w-1/5 flex">
				<div className="px-4 lg:px-8 xl:px-12 space-y-6 self-end grid items-center w-full">
					<DiceNumber />
					<BuyEstateButton />
					<DiceNumberButton />
					<NextPlayerButton />
				</div>
			</div>
		</div>
	);
}
