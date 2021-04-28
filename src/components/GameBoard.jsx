import { GameField } from './GameFields'
import { gameFieldData } from '~/data/gameFieldData'
import { borderColor } from '~/utils/borderColor'

export function GameBoard() {
  return (
    <div className='grid grid-cols-9 h-screen'>
      <div className='row-start-2 row-end-7 col-start-3 col-end-8 bg-white' />
      {gameFieldData.map((gameField) => (
        <GameField
          key={gameField.id}
          title={gameField.title}
          price={gameField.price}
          condition={gameField.condition}
          borderColor={borderColor(gameField.id)}
          color={gameField.color}
        />
      ))}
    </div>
  )
}
