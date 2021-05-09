import { GameBoard } from '~/components/GameBoard';
import { Leaderboard } from '~/components/Leaderboard';
import { GameMenu } from '~/components/GameMenu';

const GamePage = ({ userJoint }) => {
	return userJoint === false ? (
		<div></div>
	) : (
		<div className="flex mx-auto">
			<div className="grid grid-cols-5">
				<div className="text-white">
					<Leaderboard />
					<GameMenu />
				</div>
				<div className="col-span-4">
					<GameBoard />
				</div>
			</div>
		</div>
	);
};

export default GamePage;
