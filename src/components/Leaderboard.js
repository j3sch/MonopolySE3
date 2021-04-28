import { playerListData } from '~/data/PlayerListData'

export function Leaderboard() {
  return (
    <div>
      {playerListData.map((player) => (
        <div
          key={player.place}
          className='grid justify-items-stretch grid-cols-6 h-16 p-1 group hover:text-2xl font-bold text-xl transition hover:p-0 hover:bg-opacity-10 '
        >
          <div
            className={
              player.color +
              ' mr-2 group-hover:mr-1 items-center grid justify-items-center'
            }
          >
            {player.place}
          </div>
          <div
            className={
              player.color + ' col-span-5 grid grid-cols-2 items-center px-3 '
            }
          >
            <div className='col-span-1 '>{player.name}</div>
            <div className='col-span-1 justify-self-end'>{player.money}</div>
          </div>
        </div>
      ))}
    </div>
    
  )
}
