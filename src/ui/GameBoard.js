import { GameFieldTop, GameFieldLeft, GameFieldRight, GameFieldBottom } from '~/ui/GameField'

export function GameBoard() {
  return (
    <div className='grid grid-cols-10 h-screen'>
      <div className='row-start-2 row-end-10 col-start-2 col-end-10 bg-white' />
      <div className='h-auto bg-red-600 '>dddd</div>
      <GameFieldTop />
      <GameFieldTop />
      <GameFieldTop />
      <GameFieldTop />
      <GameFieldTop />
      <GameFieldTop />
      <GameFieldTop />
      <GameFieldTop />
      <div className='h-auto  bg-red-600' />
      <GameFieldLeft />
      <GameFieldRight />
      <GameFieldLeft />
      <GameFieldRight />
      <GameFieldLeft />
      <GameFieldRight />
      <GameFieldLeft />
      <GameFieldRight />
      <GameFieldLeft />
      <GameFieldRight />
      <GameFieldLeft />
      <GameFieldRight />
      <GameFieldLeft />
      <GameFieldRight />
      <GameFieldLeft />
      <GameFieldRight />
      <div className='h-auto bg-red-600' />
      <GameFieldBottom />
      <GameFieldBottom />
      <GameFieldBottom />
      <GameFieldBottom />
      <GameFieldBottom />
      <GameFieldBottom />
      <GameFieldBottom />
      <GameFieldBottom />
      <div className='h-auto bg-red-600' />
    </div>
  )
}
