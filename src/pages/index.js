// import { GameField } from '~/ui/GameField'
// import { Square } from '~/ui/Square'
// import { GameBoard } from '~/ui/GameBoard'
// import { gameFields } from '~/data/gameFields'

export default function Home() {
    const gameFieldsBottom = [
      { id: 1, title: 'BURGER JOINT', price: '1$' },
      { id: 2, title: 'PIZZA HOUSE', price: '1$' },
      { id: 4, title: 'CANDY STORE', price: '1$' },
      { id: 5, title: 'ICE CREAM PALOR', price: '1$' },
    ]
  return (
    <div className='grid grid-cols-9 h-screen'>
      <div className='row-start-2 row-end-7 col-start-3 col-end-8 bg-white' />
      <div className='h-auto bg-red-600 col-span-2'></div>
      {gameFieldsBottom.map((gameField) => (
        <div className='border-black border-2' key={gameField.id}>
          <div className='h-1/4 bg-red-200 border-black border-1'></div>
          <div className='h-3/4 bg-green-200 border-black border-1 grid justify-items-center'>
            <p className='text-xl font-bold'>{gameField.title}</p>
            <p className='text-xl font-semibold'>{gameField.price}</p>
          </div>
        </div>
      ))}
      {/* <GameBoard gameFieldsBottom={gameFieldsBottom}/> */}
      </div>
  )
  }
  // return (
  //   <div className='min-h-screen mx-auto'>
  //     <GameBoard gameFields={gameFields} />
  //   </div>
  //   // <div className='flex'>
  //   //   <div className='w-1/6'>left</div>
  //   //   <div className='flex-1'>
  //   //       <div className='flex absolute w-4/6 top-0'>
  //   //         <Square />
  //   //         <div className='flex w-full'>
  //   //           <GameField />
  //   //           <GameField />
  //   //           <GameField />
  //   //           <GameField />
  //   //           <GameField />
  //   //           <GameField />
  //   //           <GameField />
  //   //           <GameField />
  //   //         </div>
  //   //         <Square />
  //   //       </div>
  //   //     <div className='flex absolute w-2/5 right-0 top-96 mt-14'>
  //   //       <div className='flex w-full transform rotate-90 left-0'>
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //       </div>
  //   //   </div>
  //   //     <div className='flex absolute w-4/6 bottom-0'>
  //   //       <Square />
  //   //       <div className='flex w-full items-end'>
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //         <GameField />
  //   //       </div>
  //   //       <Square />
  //   //     </div>
  //   //     <div className='w-1/6'>left</div>
  //   //   </div>
  //   // </div>
  // )
// }
