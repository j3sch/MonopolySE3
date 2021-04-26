import { GameField } from './GameField'
import { gameFieldData } from '~/data/gameFieldData'

export function GameBoard() {
  let player1 = 5
  let player2 = 21
  let player3 = 2
  let player4 = 13

  return (
    <div className='grid grid-cols-9 h-screen'>
      <div className='row-start-2 row-end-7 col-start-3 col-end-8 bg-white' />
      {gameFieldData.map((gameField) => (
        <GameField
          key={gameField.id}
          title={gameField.title}
          price={gameField.price}
          condition={gameField.condition}
          player1={gameField.id === player1}
          player2={gameField.id === player2}
          player3={gameField.id === player3}
          player4={gameField.id === player4}
          color={gameField.color}
        />
      ))}
    </div>
  )
}
