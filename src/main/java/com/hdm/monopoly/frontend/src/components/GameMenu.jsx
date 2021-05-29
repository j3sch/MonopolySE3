import { DiceNumberButton } from '~/ui/DiceNumberButton';
import { BuyEstateButton } from '~/ui/BuyEstateButton';
import { NextPlayerButton } from '~/ui/NextPlayerButton';

export function GameMenu() {
	return (
		<div className="absolute bottom-6 lg:bottom-16 w-1/5 flex">
			<div className="px-4 lg:px-8 xl:px-12 space-y-6 self-end grid items-center w-full">
				<BuyEstateButton />
				<DiceNumberButton />
				<NextPlayerButton />
			</div>
		</div>
	);
}
