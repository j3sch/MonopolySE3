import { GameFieldTop, GameFieldLeft, GameFieldRight, GameFieldBottom } from '~/ui/GameField'

export function GameBoard(props) {
 const content = props.gameFields.map((gameField) => (
   
   <div key={gameField.id}>
     <h3>{gameField.title}</h3>
     <p>{gameField.price}</p>
   </div>
 ))
  return (
    <div>
      {content}
    </div>
  )
 
    
    
    

    // <div className='grid grid-cols-9 h-screen'>
    //   <div className='row-start-2 row-end-7 col-start-3 col-end-8 bg-white' />
    //   <div className='h-auto bg-red-600 col-span-2'></div>
    //   <GameFieldTop name='sss' price='dssd' />
    //   <GameFieldTop name='sss' price='dssd' />
    //   <GameFieldTop name='sss' price='dssd' />
    //   <GameFieldTop name='sss' price='dssd' />
    //   <GameFieldTop name='sss' price='dssd' />
    //   <div className='h-auto  bg-red-600 col-span-2'></div>
    //   <GameFieldLeft />
    //   <GameFieldRight />
    //   <GameFieldLeft />
    //   <GameFieldRight />
    //   <GameFieldLeft />
    //   <GameFieldRight />
    //   <GameFieldLeft />
    //   <GameFieldRight />
    //   <GameFieldLeft />
    //   <GameFieldRight />
    //   <div className='h-auto bg-red-600 col-span-2' />
    //   <GameFieldBottom />
    //   <GameFieldBottom />
    //   <GameFieldBottom />
    //   <GameFieldBottom />
    //   <GameFieldBottom />
    //   <div className='h-auto bg-red-600 col-span-2' />
    // </div>
  
}
