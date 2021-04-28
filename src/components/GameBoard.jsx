import { GameField } from './GameFields'
import { gameFieldData } from '~/data/GameFieldData'

export function GameBoard() {
  let player1 = 5
  let player2 = 21
  let player3 = 2
  let player4 = 13

  let borderColor = 'border-black border-2'

  return (
    <div className='grid grid-cols-9 h-screen'>
      <div className='row-start-2 row-end-7 col-start-3 col-end-8 bg-white' />
      {gameFieldData.map((gameField) => (
        <GameField
          key={gameField.id}
          title={gameField.title}
          price={gameField.price}
          condition={gameField.condition}
          borderColor={
            (gameField.id === player1 &&
              (borderColor = 'border-red-500 border-4')) ||
            (gameField.id === player2 &&
              (borderColor = 'border-blue-500 border-4')) ||
            (gameField.id === player3 &&
              (borderColor = 'border-green-500 border-4')) ||
            (gameField.id === player4 &&
              (borderColor = 'border-yellow-500 border-4')) ||
            (borderColor = 'border-black border-2')
          }
          color={gameField.color}
        />
      ))}
    </div>
  )
}
