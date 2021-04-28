import { DiceNumberButton } from '~/ui/DiceNumberButton'
import { BuyEstateButton } from '~/ui/BuyEstateButton'

export function GameMenu() {
  return (
    <div className='h-3/5 flex'>
      <div className='px-4 lg:px-8 xl:px-12 space-y-6 self-end grid items-center w-full'>
        <BuyEstateButton />
        <DiceNumberButton />
      </div>
    </div>
  )
}
