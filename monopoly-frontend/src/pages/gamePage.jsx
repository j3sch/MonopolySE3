import { GameBoard } from '~/components/GameBoard';
import { GameMenu } from '~/components/GameMenu';

const GamePage = () => {
	return (
		<div className="flex mx-auto">
			<div className="grid grid-cols-5">
				<GameMenu />
				<div className="col-span-4">
					<GameBoard />
				</div>
			</div>
		</div>
	);
};

export default GamePage;
