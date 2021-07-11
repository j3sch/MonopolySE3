import { GameField } from './GameFields';
import { gameFieldData } from '~/data/gameFieldData';
import { borderColor } from '~/utils/borderColor';
import { EventFieldCard } from './EventFieldCard';

export function GameBoard() {
	return (
		<div className="grid grid-cols-9 h-screen text-black">
			<div className="row-start-2 row-end-7 col-start-3 col-end-8 bg-gray-100 dark:bg-gray-900 flex items-center justify-center">
				<EventFieldCard />
			</div>
			{gameFieldData.map((gameField) => (
				<GameField
					key={gameField.id.toString()}
					id={gameField.id}
					title={gameField.title}
					price={gameField.price}
					condition={gameField.condition}
					borderColor={borderColor(gameField.id)}
					color={gameField.color}
				/>
			))}
		</div>
	);
}
