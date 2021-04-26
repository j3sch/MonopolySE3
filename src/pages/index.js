import { GameBoard } from '~/components/GameBoard'
import { Leaderboard } from '~/components/Leaderboard'

export default function Home() {
  return (
    <div className='min-h-screen mx-auto'>
      <div className='grid grid-cols-5'>
        <Leaderboard />
        <div className='col-span-4'>
          <GameBoard />
        </div>
      </div>
    </div>
  )
  }

