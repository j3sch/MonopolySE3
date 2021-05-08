import { GameBoard } from '~/components/GameBoard';
import { Leaderboard } from '~/components/Leaderboard';
import { GameMenu } from '~/components/GameMenu';
// import LoginPage from '~/pages/loginPage';

export default function Home() {
	return (
		// <div>
		// 	<LoginPage />
		// </div>
		<div className="h-screen flex mx-auto">
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
}
