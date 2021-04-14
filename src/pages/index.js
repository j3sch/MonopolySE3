import { GameField } from '~/ui/GameField'
import { Square } from '~/ui/Square'
import { GameBoard } from '~/ui/GameBoard'

export default function Home() {
  return (
     <div className='min-h-screen mx-auto'>
      <GameBoard />
      </div>
    // <div className='flex'>
    //   <div className='w-1/6'>left</div>
    //   <div className='flex-1'>
    //       <div className='flex absolute w-4/6 top-0'>
    //         <Square />
    //         <div className='flex w-full'>
    //           <GameField />
    //           <GameField />
    //           <GameField />
    //           <GameField />
    //           <GameField />
    //           <GameField />
    //           <GameField />
    //           <GameField />
    //         </div>
    //         <Square />
    //       </div>
    //     <div className='flex absolute w-2/5 right-0 top-96 mt-14'>
    //       <div className='flex w-full transform rotate-90 left-0'>
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //       </div>
    //   </div>
    //     <div className='flex absolute w-4/6 bottom-0'>
    //       <Square />
    //       <div className='flex w-full items-end'>
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //         <GameField />
    //       </div>
    //       <Square />
    //     </div>
    //     <div className='w-1/6'>left</div>
    //   </div>
    // </div>
  )
}
